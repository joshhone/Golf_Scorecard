package com.disney.golfscorecard;

public class Score {
    public static int DEFAULT_SCORE = 0;
    private int mHoleNumber;
    private int mHoleScore;

    public int getHoleScore() {
        return mHoleScore;
    }

    public void setHoleScore(int holeScore) {
        mHoleScore = holeScore;
    }

    public int getHoleNumber() {
        return mHoleNumber;
    }

    public void setHoleNumber(int holeNumber) {
        mHoleNumber = holeNumber;
    }

}
