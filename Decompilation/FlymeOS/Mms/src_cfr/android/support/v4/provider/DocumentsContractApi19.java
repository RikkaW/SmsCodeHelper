/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.provider.DocumentsContract
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package android.support.v4.provider;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.util.Log;

class DocumentsContractApi19 {
    private static final String TAG = "DocumentFile";

    DocumentsContractApi19() {
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean canRead(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 1) != 0 || TextUtils.isEmpty((CharSequence)DocumentsContractApi19.getRawType(context, uri))) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean canWrite(Context context, Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) != 0) {
            return false;
        }
        String string2 = DocumentsContractApi19.getRawType(context, uri);
        int n2 = DocumentsContractApi19.queryForInt(context, uri, "flags", 0);
        if (TextUtils.isEmpty((CharSequence)string2)) return false;
        if ((n2 & 4) != 0) {
            return true;
        }
        if ("vnd.android.document/directory".equals((Object)string2) && (n2 & 8) != 0) {
            return true;
        }
        if (TextUtils.isEmpty((CharSequence)string2)) return false;
        if ((n2 & 2) == 0) return false;
        return true;
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

    public static boolean delete(Context context, Uri uri) {
        return DocumentsContract.deleteDocument((ContentResolver)context.getContentResolver(), (Uri)uri);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static boolean exists(Context var0, Uri var1_1) {
        block10 : {
            var0 = var0.getContentResolver();
            var0 = var1_1 = var0.query(var1_1, new String[]{"document_id"}, null, null, null);
            try {
                var2_5 = var1_1.getCount();
                if (var2_5 <= 0) break block10;
                var3_6 = true;
            }
            catch (Exception var4_9) {
                ** GOTO lbl16
            }
lbl8: // 2 sources:
            do {
                DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
                return var3_6;
                break;
            } while (true);
        }
        var3_6 = false;
        ** while (true)
        catch (Exception var4_7) {
            var1_1 = null;
lbl16: // 2 sources:
            var0 = var1_1;
            Log.w((String)"DocumentFile", (String)("Failed query: " + var4_8));
            DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
            return false;
        }
        catch (Throwable var1_2) {
            var0 = null;
lbl23: // 2 sources:
            do {
                DocumentsContractApi19.closeQuietly((AutoCloseable)var0);
                throw var1_3;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_4) {
                ** continue;
            }
        }
    }

    public static String getName(Context context, Uri uri) {
        return DocumentsContractApi19.queryForString(context, uri, "_display_name", null);
    }

    private static String getRawType(Context context, Uri uri) {
        return DocumentsContractApi19.queryForString(context, uri, "mime_type", null);
    }

    public static String getType(Context object, Uri object2) {
        object = object2 = DocumentsContractApi19.getRawType((Context)object, (Uri)object2);
        if ("vnd.android.document/directory".equals(object2)) {
            object = null;
        }
        return object;
    }

    public static boolean isDirectory(Context context, Uri uri) {
        return "vnd.android.document/directory".equals((Object)DocumentsContractApi19.getRawType(context, uri));
    }

    public static boolean isDocumentUri(Context context, Uri uri) {
        return DocumentsContract.isDocumentUri((Context)context, (Uri)uri);
    }

    public static boolean isFile(Context object, Uri uri) {
        if ("vnd.android.document/directory".equals(object = DocumentsContractApi19.getRawType((Context)object, uri)) || TextUtils.isEmpty((CharSequence)object)) {
            return false;
        }
        return true;
    }

    public static long lastModified(Context context, Uri uri) {
        return DocumentsContractApi19.queryForLong(context, uri, "last_modified", 0);
    }

    public static long length(Context context, Uri uri) {
        return DocumentsContractApi19.queryForLong(context, uri, "_size", 0);
    }

    private static int queryForInt(Context context, Uri uri, String string2, int n2) {
        return (int)DocumentsContractApi19.queryForLong(context, uri, string2, n2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static long queryForLong(Context var0, Uri var1_1, String var2_5, long var3_9) {
        block9 : {
            var0 = var0.getContentResolver();
            var0 = var1_1 = var0.query(var1_1, new String[]{var2_5}, null, null, null);
            try {
                if (!var1_1.moveToFirst()) break block9;
                var0 = var1_1;
                if (var1_1.isNull(0)) break block9;
                var0 = var1_1;
                var5_10 = var1_1.getLong(0);
            }
            catch (Exception var2_8) {
                ** GOTO lbl17
            }
            DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
            return var5_10;
        }
        DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
        return var3_9;
        catch (Exception var2_6) {
            var1_1 = null;
lbl17: // 2 sources:
            var0 = var1_1;
            Log.w((String)"DocumentFile", (String)("Failed query: " + var2_7));
            DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
            return var3_9;
        }
        catch (Throwable var1_2) {
            var0 = null;
lbl24: // 2 sources:
            do {
                DocumentsContractApi19.closeQuietly((AutoCloseable)var0);
                throw var1_3;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_4) {
                ** continue;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static String queryForString(Context var0, Uri var1_1, String var2_5, String var3_9) {
        block9 : {
            var0 = var0.getContentResolver();
            var0 = var1_1 = var0.query(var1_1, new String[]{var2_5}, null, null, null);
            try {
                if (!var1_1.moveToFirst()) break block9;
                var0 = var1_1;
                if (var1_1.isNull(0)) break block9;
                var0 = var1_1;
                var2_5 = var1_1.getString(0);
            }
            catch (Exception var2_8) {
                ** GOTO lbl17
            }
            DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
            return var2_5;
        }
        DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
        return var3_9;
        catch (Exception var2_6) {
            var1_1 = null;
lbl17: // 2 sources:
            var0 = var1_1;
            Log.w((String)"DocumentFile", (String)("Failed query: " + var2_7));
            DocumentsContractApi19.closeQuietly((AutoCloseable)var1_1);
            return var3_9;
        }
        catch (Throwable var1_2) {
            var0 = null;
lbl24: // 2 sources:
            do {
                DocumentsContractApi19.closeQuietly((AutoCloseable)var0);
                throw var1_3;
                break;
            } while (true);
        }
        {
            catch (Throwable var1_4) {
                ** continue;
            }
        }
    }
}

