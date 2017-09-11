package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 梅 on 2017-9-11.
 */

public class Crime {
    private UUID mId;  //uuid
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime() {
        mId= UUID.randomUUID();
        mDate= new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
