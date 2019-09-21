package com.app.runners.model;

import com.google.gson.annotations.SerializedName;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Runner extends RealmObject {

    public static final String AGE = "age";
    public static final String TIME = "time";
    @PrimaryKey
    @Index
    @SerializedName("Name")
    private String name;

    @SerializedName("Time")
    private int time;

    @SerializedName("Age")
    private int age;
    private int rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank(){
        return rank;
    }
}
