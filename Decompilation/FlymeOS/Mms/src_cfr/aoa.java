/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.HttpEntity
 */
import android.content.Context;
import org.apache.http.HttpEntity;

public class aoa {
    private static final String a = aoa.class.getSimpleName();
    private static ab b = new ab();

    static {
        b.a("Accept-Encoding", "gzip");
    }

    public static void a(String string2, HttpEntity httpEntity, aut aut2) {
        b.a(null, string2, httpEntity, null, aut2);
    }
}

