package com.eipipuz.ivaslist;

public class Tag {
    private int mId;
    private String mName;

    public Tag(int id, String name) {
        mId = id;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    @Override
    public String toString() {
        return getName();
    }
}
