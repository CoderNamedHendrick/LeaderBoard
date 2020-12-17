package com.example.leaderboard;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeaderViewHolder>{

    private List<Leader> leaders = new ArrayList<>();
    private int resource;

    public LeaderAdapter(int resource){ this.resource = resource;}

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new LeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder holder, int position) {
        Leader leader = leaders.get(position);
        holder.bind(leader);
    }

    @Override
    public int getItemCount() {
        return leaders.size();
    }

    public class LeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView badge;
        TextView txtName;
        TextView txtInfo;

        public LeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            badge = itemView.findViewById(R.id.badge);
            txtName = itemView.findViewById(R.id.leader_name);
            txtInfo = itemView.findViewById(R.id.leader_info);
        }

        public void bind(Leader leader) {
            if (resource == R.layout.item_learning_leaders){
                Picasso.get()
                        .load(leader.getBadgeUrl())
                        .into(badge);

                txtName.setText(leader.getName());
                txtInfo.setText(leader.getHours() + " learning Hours, " + leader.getCountry());
            } else if (resource == R.layout.item_skill_leaders){
                Picasso.get()
                        .load(leader.getBadgeUrl())
                        .into(badge);

                txtName.setText(leader.getName());
                txtInfo.setText(leader.getScore() + " skill IQ Score, " + leader.getCountry());
            }
        }
    }

    public void setLeaders(List<Leader> leaders){
        this.leaders = leaders;
        notifyDataSetChanged();
    }
}
