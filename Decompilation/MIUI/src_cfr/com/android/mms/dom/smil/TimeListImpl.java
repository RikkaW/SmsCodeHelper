/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.ArrayList
 */
package com.android.mms.dom.smil;

import java.util.ArrayList;
import org.w3c.dom.smil.Time;
import org.w3c.dom.smil.TimeList;

public class TimeListImpl
implements TimeList {
    private final ArrayList<Time> mTimes;

    TimeListImpl(ArrayList<Time> arrayList) {
        this.mTimes = arrayList;
    }

    @Override
    public int getLength() {
        return this.mTimes.size();
    }

    @Override
    public Time item(int n) {
        try {
            Time time = (Time)this.mTimes.get(n);
            return time;
        }
        catch (IndexOutOfBoundsException var2_3) {
            return null;
        }
    }
}

