/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.ted.sdk.yellow.provider.YellowPageProvider;

public class ass
implements aoj {
    private final Uri a;
    private final Context b;

    public ass(Context context, String string2) {
        this.b = context;
        this.a = Uri.withAppendedPath((Uri)YellowPageProvider.a, (String)string2);
    }

    @Override
    public int a(ContentValues contentValues, String string2, String[] arrstring) {
        if (this.b == null) {
            return 0;
        }
        return this.b.getContentResolver().update(this.a, contentValues, string2, arrstring);
    }

    @Override
    public int a(String string2, String[] arrstring) {
        if (this.b == null) {
            return 0;
        }
        return this.b.getContentResolver().delete(this.a, string2, arrstring);
    }

    @Override
    public Cursor a(String[] arrstring, String string2, String[] arrstring2, String string3) {
        if (this.b == null) {
            return null;
        }
        return this.b.getContentResolver().query(this.a, arrstring, string2, arrstring2, string3);
    }

    @Override
    public boolean a(ContentValues contentValues) {
        if (this.b == null) {
            return false;
        }
        this.b.getContentResolver().insert(this.a, contentValues);
        return true;
    }
}

