package com.londonappbrewery.quizzler;

public class trueFalse {
    private int mquestionId;
    private boolean manswerId;
     public trueFalse(int questioResourseId,boolean trueorfalse){
         mquestionId=questioResourseId;
         manswerId=trueorfalse;
     }

    public int getMquestionId() {
        return mquestionId;
    }

    public void setMquestionId(int mquestionId) {
        this.mquestionId = mquestionId;
    }

    public boolean isManswerId() {
        return manswerId;
    }

    public void setManswerId(boolean manswerId) {
        this.manswerId = manswerId;
    }
}
