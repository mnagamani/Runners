package com.app.runners.model;

import com.google.gson.annotations.SerializedName;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Race extends RealmObject {

    public static final String NAME = "name";
    @PrimaryKey
    @Index
    @SerializedName("Name")
    private String name;

    @SerializedName("Runners")
    private RealmList<Runner> runnersList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Runner> getRunnersList() {
        return runnersList;
    }

    public void setRunnersList(RealmList<Runner> runnersList) {
        this.runnersList = runnersList;
    }
}
