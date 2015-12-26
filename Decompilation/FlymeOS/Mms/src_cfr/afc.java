/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
import java.io.InputStream;

public abstract class afc {
    public abstract void a(afb var1);

    public abstract void a(InputStream var1);

    @Deprecated
    public void a(InputStream inputStream, afb afb2) {
        this.a(afb2);
        this.a(inputStream);
    }
}

