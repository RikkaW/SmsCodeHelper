/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.provider.DocumentsContract
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;
import java.util.ArrayList;

class DocumentsContractApi21 {
    private static final String TAG = "DocumentFile";

    DocumentsContractApi21() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void closeQuietly(AutoCloseable autoCloseable) {
        if (autoCloseable == null) return;
        try {
            autoCloseable.close();
            return;
        }
        catch (RuntimeException var0_1) {
            throw var0_1;
        }
        catch (Exception var0_2) {
            return;
        }
    }

    public static Uri createDirectory(Context context, Uri uri, String string2) {
        return DocumentsContractApi21.createFile(context, uri, "vnd.android.document/directory", string2);
    }

    public static Uri createFile(Context context, Uri uri, String string2, String string3) {
        return DocumentsContract.createDocument((ContentResolver)context.getContentResolver(), (Uri)uri, (String)string2, (String)string3);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Uri[] listFiles(Context var0, Uri var1_1) {
        var0 = var0.getContentResolver();
        var2_8 = DocumentsContract.buildChildDocumentsUriUsingTree((Uri)var1_1, (String)DocumentsContract.getDocumentId((Uri)var1_1));
        var3_9 = new ArrayList();
        try {
            var2_8 = var0.query(var2_8, new String[]{"document_id"}, null, null, null);
            ** GOTO lbl13
        }
        catch (Throwable var1_4) {
            var0 = null;
            ** GOTO lbl30
            catch (Exception var1_7) {
                var2_8 = null;
                ** GOTO lbl-1000
            }
lbl13: // 1 sources:
            do {
                var0 = var2_8;
                try {
                    if (!var2_8.moveToNext()) break;
                    var0 = var2_8;
                    var3_9.add((Object)DocumentsContract.buildDocumentUriUsingTree((Uri)var1_1, (String)var2_8.getString(0)));
                    continue;
                }
                catch (Exception var1_2) lbl-1000: // 2 sources:
                {
                    var0 = var2_8;
                    Log.w((String)"DocumentFile", (String)("Failed query: " + var1_3));
                    DocumentsContractApi21.closeQuietly((AutoCloseable)var2_8);
                    return (Uri[])var3_9.toArray((Object[])new Uri[var3_9.size()]);
                }
            } while (true);
            DocumentsContractApi21.closeQuietly((AutoCloseable)var2_8);
            return (Uri[])var3_9.toArray((Object[])new Uri[var3_9.size()]);
            catch (Throwable var1_6) {}
lbl30: // 2 sources:
            DocumentsContractApi21.closeQuietly((AutoCloseable)var0);
            throw var1_5;
        }
    }

    public static Uri prepareTreeUri(Uri uri) {
        return DocumentsContract.buildDocumentUriUsingTree((Uri)uri, (String)DocumentsContract.getTreeDocumentId((Uri)uri));
    }

    public static Uri renameTo(Context context, Uri uri, String string2) {
        return DocumentsContract.renameDocument((ContentResolver)context.getContentResolver(), (Uri)uri, (String)string2);
    }
}

