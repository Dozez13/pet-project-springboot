package com.example.task16.db.entity;



public enum CarState {
    READY("ready"),ON_ORDER("on order"),EMPTY("car doesn't have a taxi driver");
    private String stateName;
    public String getStateName() {
        return stateName;
    }
     CarState(String stateName){
        this.stateName = stateName;
    }
    @Override
    public String toString() {
        return getStateName();
    }

}
