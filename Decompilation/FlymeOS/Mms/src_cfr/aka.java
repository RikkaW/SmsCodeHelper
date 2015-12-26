/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 */
public abstract class aka
extends ajz {
    private String i;
    private String j;
    private String k;
    private String l;

    public aka(String string2, String string3, String string4, String string5) {
        if (string2 == null) {
            throw new IllegalArgumentException("Name must not be null");
        }
        this.i = string2;
        this.j = string3;
        this.k = string4;
        this.l = string5;
    }

    @Override
    public String b() {
        return this.i;
    }

    @Override
    public String c() {
        return this.j;
    }

    @Override
    public String d() {
        return this.k;
    }

    @Override
    public String e() {
        return this.l;
    }
}

