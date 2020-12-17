package com.example.leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

public interface GetLearningLeadersService {

    @GET("/api/hours")
    Call<List<Leader>> getAllLeaders();
}
