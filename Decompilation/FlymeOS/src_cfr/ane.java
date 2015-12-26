/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
public class ane
extends Exception {
    private int a;
    private boolean b = false;

    public ane(int n2, String string2) {
        super(string2);
        this.a = n2;
        this.b = true;
    }

    public ane(String string2) {
        super(string2);
    }

    public int a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }
}

