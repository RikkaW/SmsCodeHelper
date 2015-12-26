/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.understand;

import java.util.ArrayList;

public class UnderstandMessage {
    public int mActionID;
    public String mFrameName;
    public ArrayList<Item> mItems;

    public static class Item {
        public int mEndPosition;
        public int mHas;
        public int mStartPosition;
        public int mType;
        public String mValue;
    }

}

