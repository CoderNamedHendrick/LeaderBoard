package com.example.leaderboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabItem;

public class TabAdapter extends FragmentStatePagerAdapter {
    private int numOfTabs;
    public TabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm);
        this.numOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case (0):
                LearningLeadersFragment learningLeadersFragment = new LearningLeadersFragment();
                return learningLeadersFragment;
            case (1):
                SkillLeadersFragment skillLeadersFragment = new SkillLeadersFragment();
                return skillLeadersFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
