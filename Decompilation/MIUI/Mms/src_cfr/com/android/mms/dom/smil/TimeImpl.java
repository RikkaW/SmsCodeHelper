/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.dom.smil;

import org.w3c.dom.smil.Time;

public class TimeImpl
implements Time {
    boolean mResolved;
    double mResolvedOffset;
    short mTimeType;

    /*
     * Enabled aggressive block sorting
     */
    TimeImpl(String string, int n) {
        String string2;
        if (string.equals((Object)"indefinite") && (n & 1) != 0) {
            this.mTimeType = 0;
            return;
        }
        if ((n & 2) == 0) {
            throw new IllegalArgumentException("Unsupported time value");
        }
        n = 1;
        if (string.startsWith("+")) {
            string2 = string.substring(1);
        } else {
            string2 = string;
            if (string.startsWith("-")) {
                string2 = string.substring(1);
                n = -1;
            }
        }
        this.mResolvedOffset = (double)((float)n * TimeImpl.parseClockValue(string2)) / 1000.0;
        this.mResolved = true;
        this.mTimeType = 1;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static float parseClockValue(String arrstring) {
        float f2;
        block13 : {
            f2 = 0.0f;
            arrstring = arrstring.trim();
            if (arrstring.endsWith("ms")) {
                return TimeImpl.parseFloat((String)arrstring, 2, true);
            }
            if (arrstring.endsWith("s")) {
                return 1000.0f * TimeImpl.parseFloat((String)arrstring, 1, true);
            }
            if (arrstring.endsWith("min")) {
                return 60000.0f * TimeImpl.parseFloat((String)arrstring, 3, true);
            }
            if (!arrstring.endsWith("h")) break block13;
            f2 = TimeImpl.parseFloat((String)arrstring, 1, true);
            return 3600000.0f * f2;
            {
                catch (NumberFormatException numberFormatException) {
                    throw new IllegalArgumentException();
                }
            }
        }
        try {
            float f3 = TimeImpl.parseFloat((String)arrstring, 0, true);
            return f3 * 1000.0f;
        }
        catch (NumberFormatException var6_5) {
            block14 : {
                int n;
                int n2;
                arrstring = arrstring.split(":");
                if (arrstring.length == 2) {
                    n = 0;
                } else {
                    if (arrstring.length != 3) throw new IllegalArgumentException();
                    f2 = 3600000 * (int)TimeImpl.parseFloat(arrstring[0], 0, false);
                    n = 1;
                }
                if ((n2 = (int)TimeImpl.parseFloat(arrstring[n], 0, false)) < 0 || n2 > 59) break block14;
                float f4 = 60000 * n2;
                float f5 = TimeImpl.parseFloat(arrstring[n + 1], 0, true);
                if (f5 < 0.0f) throw new IllegalArgumentException();
                if (f5 >= 60.0f) throw new IllegalArgumentException();
                return f2 + f4 + 60000.0f * f5;
            }
            throw new IllegalArgumentException();
        }
    }

    private static float parseFloat(String string, int n, boolean bl) {
        string = string.substring(0, string.length() - n);
        n = string.indexOf(46);
        if (n != -1) {
            if (!bl) {
                throw new IllegalArgumentException("int value contains decimal");
            }
            string = string + "000";
            return Float.parseFloat((String)string.substring(0, n)) + Float.parseFloat((String)string.substring(n + 1, n + 4)) / 1000.0f;
        }
        return Integer.parseInt((String)string);
    }

    @Override
    public double getOffset() {
        return 0.0;
    }

    @Override
    public boolean getResolved() {
        return this.mResolved;
    }

    @Override
    public double getResolvedOffset() {
        return this.mResolvedOffset;
    }

    @Override
    public short getTimeType() {
        return this.mTimeType;
    }
}

