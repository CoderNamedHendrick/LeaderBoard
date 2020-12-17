package com.example.leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetSkillLeadersService {

    @GET("/api/skilliq")
    Call<List<Leader>> getAllSkillLeaders();
}
