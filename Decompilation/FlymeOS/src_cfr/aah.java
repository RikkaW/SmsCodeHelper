/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.net.Uri
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

class aah
implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Uri b;
    final /* synthetic */ aad c;

    aah(aad aad2, int n2, Uri uri) {
        this.c = aad2;
        this.a = n2;
        this.b = uri;
    }

    @Override
    public void run() {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("type", Integer.valueOf((int)this.a));
        aad.c(this.c).getContentResolver().update(this.b, contentValues, null, null);
    }
}

