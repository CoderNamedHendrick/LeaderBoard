package com.example.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {

    ProgressBar loadBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.leaders_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView leaderRecyclerView = view.findViewById(R.id.learning_leaders_recyclerview);
        final LeaderAdapter leaderAdapter = new LeaderAdapter(R.layout.item_learning_leaders);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        leaderRecyclerView.setLayoutManager(layoutManager);
        leaderRecyclerView.setAdapter(leaderAdapter);

        final GetLearningLeadersService learningLeadersService = RetrofitClientInstance.getRetrofitInstance().
                create(GetLearningLeadersService.class);
        Call<List<Leader>> call = learningLeadersService.getAllLeaders();
        call.enqueue(new Callback<List<Leader>>() {
            @Override
            public void onResponse(Call<List<Leader>> call, Response<List<Leader>> response) {
                leaderAdapter.setLeaders(response.body());
            }

            @Override
            public void onFailure(Call<List<Leader>> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
