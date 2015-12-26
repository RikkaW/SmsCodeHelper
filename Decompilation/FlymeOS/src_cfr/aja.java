/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.provider.Telephony$Sms$Intents
 *  android.provider.Telephony$Threads
 *  android.telephony.SmsMessage
 *  android.util.Log
 *  com.android.internal.telephony.IccUtils
 *  com.meizu.android.mms.util.MzSqliteWrapper
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  org.xml.sax.SAXException
 */
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import com.android.internal.telephony.IccUtils;
import com.android.mms.transaction.MessagingNotification;
import com.meizu.android.mms.util.MzSqliteWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.SAXException;

public class aja {
    private static ContentValues a(SmsMessage smsMessage) {
        int n2 = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", smsMessage.getDisplayOriginatingAddress());
        contentValues.put("date", Long.valueOf((long)smsMessage.getTimestampMillis()));
        contentValues.put("protocol", Integer.valueOf((int)smsMessage.getProtocolIdentifier()));
        contentValues.put("read", Integer.valueOf((int)0));
        if (smsMessage.getPseudoSubject().length() > 0) {
            contentValues.put("subject", smsMessage.getPseudoSubject());
        }
        if (smsMessage.isReplyPathPresent()) {
            n2 = 1;
        }
        contentValues.put("reply_path_present", Integer.valueOf((int)n2));
        contentValues.put("service_center", smsMessage.getServiceCenterAddress());
        return contentValues;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static Uri a(Context context, ContentValues contentValues, gm gm2) {
        String string2 = contentValues.getAsString("address");
        if (gm2 == null) {
            gm2 = gm.a(string2, true);
        }
        if (gm2 != null) {
            string2 = gm2.d();
        }
        if (string2 != null) {
            contentValues.put("thread_id", (Long)aau.a(Telephony.Threads.class, "getOrCreateThreadId", new Class[]{Context.class, String.class}, new Object[]{context, string2}));
        }
        gm2 = MzSqliteWrapper.insert((Context)context, (ContentResolver)context.getContentResolver(), (Uri)Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)contentValues);
        long l2 = contentValues.getAsLong("thread_id");
        aat.a().a(context, l2);
        return gm2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Uri a(Context context, Intent object, int n2) {
        ContentValues contentValues;
        String string2;
        Object object2;
        block6 : {
            object2 = object.getByteArrayExtra("data");
            long l2 = zv.a((Intent)object);
            int n3 = aac.a(l2);
            Log.d((String)"PushProcesser", (String)("intent is " + object + ", subId = " + l2 + ", slotId = " + n3));
            contentValues = aja.a(Telephony.Sms.Intents.getMessagesFromIntent((Intent)object)[0]);
            contentValues.put("sim_id", Integer.valueOf((int)n3));
            contentValues.put("imsi", aac.d(context, n3));
            contentValues.put("sub_id", Long.valueOf((long)l2));
            string2 = contentValues.getAsString("address");
            try {
                object = aja.a((byte[])object2, n2);
            }
            catch (IOException var1_2) {
                object = var1_2.toString() + "\r\n" + IccUtils.bytesToHexString((byte[])object2);
            }
            catch (SAXException var1_3) {
                object = var1_3.toString() + "\r\n" + IccUtils.bytesToHexString((byte[])object2);
            }
            if (object == null) {
                object2 = object;
                if (object.isEmpty()) break block6;
            }
            object2 = wd.k((String)object);
        }
        contentValues.put("body", (String)object2);
        object = abl.a(string2, (String)object2, contentValues.getAsLong("date"));
        if (object.b) {
            return abl.a(context, contentValues, (abl.a)object);
        }
        return aja.a(context, contentValues, object.a);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String a(byte[] object, int n2) {
        Object object2 = new aiz();
        String string2 = "";
        Object object3 = "";
        int n3 = object2.a((aiy)new aiy((InputStream)new ByteArrayInputStream((byte[])object))).d;
        if (n2 == 46) {
            object3 = new ajc().a(new ByteArrayInputStream((byte[])object, n3, object.length));
            if (object3 == null) {
                string2 = "\r\nGeneral parse error: \r\n" + IccUtils.bytesToHexString((byte[])object);
                object = "";
                return (String)object + string2;
            }
            if (object3.d() != null) {
                string2 = "\r\n" + IccUtils.bytesToHexString((byte[])object);
            }
            object2 = new StringBuilder();
            object = object3.b() != null ? object3.b() : "";
            object2 = object2.append((String)object).append("\r\n");
            object = object3.c() != null ? object3.c() : "";
            object = object2.append((String)object).toString();
            return (String)object + string2;
        }
        if (n2 != 48) throw new SAXException("Unsupported Push Type");
        object2 = new aje().a(new ByteArrayInputStream((byte[])object, n3, object.length));
        if (object2 == null) {
            string2 = "\r\nGeneral parse error: \r\n" + IccUtils.bytesToHexString((byte[])object);
            object = object3;
            return (String)object + string2;
        }
        if (object2.b() != null) {
            string2 = "\r\n" + IccUtils.bytesToHexString((byte[])object);
        }
        if (object2.a() != null) {
            object = object2.a();
            return (String)object + string2;
        }
        object = "";
        return (String)object + string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Context context, Intent intent) {
        if (!intent.getAction().equals((Object)"android.provider.Telephony.WAP_PUSH_DELIVER")) return;
        {
            if ("application/vnd.wap.sic".equals((Object)intent.getType())) {
                if ((intent = aja.a(context, intent, 46)) == null) return;
                {
                    MessagingNotification.a(context, MessagingNotification.b(context, (Uri)intent), false, (Uri)intent, false, true);
                    return;
                }
            } else {
                if (!"application/vnd.wap.slc".equals((Object)intent.getType()) || (intent = aja.a(context, intent, 48)) == null) return;
                {
                    MessagingNotification.a(context, MessagingNotification.b(context, (Uri)intent), false, (Uri)intent, false, true);
                    return;
                }
            }
        }
    }
}

