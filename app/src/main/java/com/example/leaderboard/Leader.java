package com.example.leaderboard;

import com.google.gson.annotations.SerializedName;

public class Leader implements Comparable<Leader> {
    @SerializedName("name")
    private String name;
    @SerializedName("hours")
    private Integer hours;
    @SerializedName("score")
    private Integer score;
    @SerializedName("country")
    private String country;
    @SerializedName("badgeUrl")
    private String badgeUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public int compareTo(Leader leader) {
        if (this.hours != null && leader.getHours() != null){
            return (Integer.compare(this.hours, leader.getHours()));
        }else
            return (Integer.compare(this.score, leader.getScore()));
    }
}
