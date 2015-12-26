/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.Cursor
 *  java.lang.Object
 */
import android.database.Cursor;

public class anr {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(Cursor cursor) {
        if (cursor == null) return;
        try {
            if (cursor.isClosed()) return;
            cursor.close();
            return;
        }
        catch (Exception var0_1) {
            return;
        }
    }
}

