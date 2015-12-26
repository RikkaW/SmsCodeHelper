/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.AsyncQueryHandler
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.net.Uri
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Conversations
 *  android.telephony.MzTelephony
 *  android.telephony.MzTelephony$MmsSmsExt
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.telephony.MzTelephony;
import android.util.Log;

public abstract class aat {
    public static boolean a;
    private static final String[] b;
    private static b c;
    private static a d;
    private static boolean e;

    static {
        b = new String[]{"sum(message_count) as msg_count"};
        e = false;
        a = true;
    }

    public static b a() {
        if (c == null) {
            c = new b();
        }
        return c;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static void a(long l2) {
        boolean bl2 = l2 > 10000;
        if (bl2) {
            e = true;
            return;
        }
        e = false;
    }

    public static void a(AsyncQueryHandler asyncQueryHandler, int n2) {
        asyncQueryHandler.cancelOperation(n2);
        asyncQueryHandler.startQuery(n2, (Object)null, MzTelephony.MmsSmsExt.CONTENT_MSGCOUNT_URI, b, null, null, null);
    }

    public static void a(Context context) {
        a = PreferenceManager.getDefaultSharedPreferences((Context)context).getBoolean("show_over_limit", true);
    }

    public static void a(Context context, boolean bl2) {
        PreferenceManager.getDefaultSharedPreferences((Context)context).edit().putBoolean("show_over_limit", bl2).apply();
        a = bl2;
    }

    public static void a(Cursor cursor) {
        long l2;
        block5 : {
            long l3;
            l2 = l3 = 0;
            if (cursor != null) {
                l2 = l3;
                if (!cursor.moveToFirst()) break block5;
                l2 = cursor.getLong(0);
            }
        }
        aat.a(l2);
        return;
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static a b() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    public static boolean b(Context context) {
        return false;
    }

    public static void c(Context context) {
        if (!aat.b(context)) {
            return;
        }
        aat.a(aat.d(context));
    }

    public static boolean c() {
        if (e && a) {
            return true;
        }
        return false;
    }

    protected static long d(Context context) {
        if ((context = context.getContentResolver().query(MzTelephony.MmsSmsExt.CONTENT_MSGCOUNT_URI, b, null, null, "top DESC, date DESC")) != null) {
            context.moveToFirst();
            long l2 = context.getLong(0);
            context.close();
            return l2;
        }
        return 0;
    }

    public void a(Context context, long l2) {
        if (!aat.b(context)) {
            return;
        }
        this.a(context, l2, this.e(context));
    }

    protected abstract void a(Context var1, long var2, int var4);

    public abstract int e(Context var1);

    public static class a
    extends aat {
        private static final String[] b = new String[]{"thread_id", "count(*) as msg_count"};
        private static final String[] c = new String[]{"_id", "thread_id", "date"};
        private final String d = "MaxMmsMessagesPerThread";

        private void a(Context context, long l2, long l3) {
            l2 = context.getContentResolver().delete(Telephony.Mms.CONTENT_URI, "thread_id=" + l2 + " AND locked=0 AND date<" + l3, null);
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        @Override
        protected void a(Context context, long l2, int n2) {
            Cursor cursor;
            long l3;
            block9 : {
                block8 : {
                    block7 : {
                        if (l2 == 0) {
                            return;
                        }
                        cursor = context.getContentResolver().query(Telephony.Mms.CONTENT_URI, c, "thread_id=" + l2 + " AND locked=0", null, "date DESC");
                        if (cursor != null) break block7;
                        Log.e((String)"Recycler", (String)"MMS: deleteMessagesForThread got back null cursor");
                        if (cursor == null) return;
                        {
                            catch (Throwable throwable) {}
                        }
                        cursor.close();
                        return;
                    }
                    int n3 = cursor.getCount();
                    if (n3 - n2 > 0) break block8;
                    if (cursor == null) return;
                    cursor.close();
                    return;
                }
                cursor.move(n2);
                l3 = cursor.getLong(2);
                if (cursor == null) break block9;
                cursor.close();
            }
            this.a(context, l2, l3);
            return;
            catch (Throwable throwable) {
                void var1_3;
                cursor = null;
                if (cursor == null) throw var1_3;
                cursor.close();
                throw var1_3;
            }
        }

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public void a(Context context, Uri object) {
            long l3;
            long l2;
            block9 : {
                int n2;
                block8 : {
                    block7 : {
                        if (!a.b(context)) {
                            return;
                        }
                        object = object.getLastPathSegment();
                        object = context.getContentResolver().query(Telephony.Mms.CONTENT_URI, c, "thread_id in (select thread_id from pdu where _id=" + (String)object + ") AND locked=0", null, "date DESC");
                        if (object != null) break block7;
                        Log.e((String)"Recycler", (String)"MMS: deleteOldMessagesInSameThreadAsMessage got back null cursor");
                        if (object == null) return;
                        {
                            catch (Throwable throwable) {}
                        }
                        object.close();
                        return;
                    }
                    int n3 = object.getCount();
                    n2 = this.e(context);
                    if (n3 - n2 > 0) break block8;
                    if (object == null) return;
                    object.close();
                    return;
                }
                object.move(n2);
                l3 = object.getLong(2);
                l2 = object.getLong(1);
                if (object == null) break block9;
                object.close();
            }
            if (l2 == 0) return;
            this.a(context, l2, l3);
            return;
            catch (Throwable throwable) {
                void var1_3;
                object = null;
                if (object == null) throw var1_3;
                object.close();
                throw var1_3;
            }
        }

        @Override
        public int e(Context context) {
            return PreferenceManager.getDefaultSharedPreferences((Context)context).getInt("MaxMmsMessagesPerThread", ga.r());
        }
    }

    public static class b
    extends aat {
        private static final String[] b = new String[]{"thread_id", "msg_count"};
        private static final String[] c = new String[]{"_id", "thread_id", "address", "body", "date", "read", "type", "status"};
        private final String d = "MaxSmsMessagesPerThread";

        /*
         * Loose catch block
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        @Override
        protected void a(Context context, long l2, int n2) {
            ContentResolver contentResolver;
            block7 : {
                block6 : {
                    contentResolver = context.getContentResolver();
                    context = contentResolver.query(ContentUris.withAppendedId((Uri)Telephony.Sms.Conversations.CONTENT_URI, (long)l2), c, "locked=0", null, "date DESC");
                    if (context != null) break block6;
                    Log.e((String)"Recycler", (String)"SMS: deleteMessagesForThread got back null cursor");
                    if (context == null) return;
                    {
                        catch (Throwable throwable) {}
                    }
                    context.close();
                    return;
                }
                int n3 = context.getCount();
                if (n3 - n2 > 0) break block7;
                if (context == null) return;
                context.close();
                return;
            }
            context.move(n2);
            long l3 = context.getLong(4);
            n2 = contentResolver.delete(ContentUris.withAppendedId((Uri)Telephony.Sms.Conversations.CONTENT_URI, (long)l2), "locked=0 AND date<" + l3, null);
            l2 = n2;
            if (context == null) return;
            context.close();
            return;
            catch (Throwable throwable) {
                void var8_6;
                context = null;
                if (context == null) throw var8_6;
                context.close();
                throw var8_6;
            }
        }

        @Override
        public int e(Context context) {
            return PreferenceManager.getDefaultSharedPreferences((Context)context).getInt("MaxSmsMessagesPerThread", ga.q());
        }
    }

}

