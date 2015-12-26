/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.telephony.SmsManager
 *  android.telephony.TelephonyManager
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 */
import android.content.Context;
import android.os.Build;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.mms.MmsApp;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class aac {
    private static Integer a = null;

    public static int a(int n2) {
        if (zv.a) {
            return (Integer)aau.a("android.telephony.TelephonyManager", "getDefault", "getSimState", Integer.TYPE, (Object)n2);
        }
        return ((TelephonyManager)aau.b("android.telephony.TelephonyManager", "getDefault")).getSimState();
    }

    public static int a(long l2) {
        if (zv.a) {
            if (Build.VERSION.SDK_INT > 21) {
                return (Integer)aau.b("android.telephony.SubscriptionManager", "getSlotId", Integer.TYPE, (Object)((int)l2));
            }
            return (Integer)aau.b("android.telephony.SubscriptionManager", "getSlotId", Long.TYPE, (Object)l2);
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static a a(Context object, int n2) {
        object = a.a;
        object = ((Boolean)aau.a("android.telephony.TelephonyManager", "getDefault", "hasIccCard", Integer.TYPE, (Object)n2)).booleanValue() ? ((Integer)aau.a("android.telephony.TelephonyManager", "getDefault", "getSimState", Integer.TYPE, (Object)n2) == 5 ? a.d : a.c) : a.b;
        aac.a("getCardState(" + n2 + "):" + object.name());
        return object;
    }

    private static TelephonyManager a() {
        return (TelephonyManager)aau.b("android.telephony.TelephonyManager", "getDefault");
    }

    private static void a(String string2) {
        Log.d((String)"MmsTelephonyManager", (String)string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static long b(int n2) {
        try {
            Object object = Class.forName((String)"android.telephony.SubscriptionManager");
            if (Build.VERSION.SDK_INT > 21) {
                if ((object = (Object)((int[])object.getDeclaredMethod("getSubId", new Class[]{Integer.TYPE}).invoke((Object)null, new Object[]{n2}))) == null) return -1;
                if (object.length >= 0) return -1;
                return Integer.valueOf((int)object[0]).longValue();
            }
            if ((object = (Object)((long[])object.getDeclaredMethod("getSubId", new Class[]{Integer.TYPE}).invoke((Object)null, new Object[]{n2}))) == null) return -1;
            if (object.length >= 0) return -1;
            return (long)object[0];
        }
        catch (Exception var3_2) {
            var3_2.printStackTrace();
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String b(Context object, int n2) {
        Object var2_3 = null;
        try {
            object = Class.forName((String)"android.telephony.SubscriptionManager");
            if (Build.VERSION.SDK_INT > 21) {
                Object object2 = object.getDeclaredMethod("from", new Class[]{Context.class}).invoke((Object)null, new Object[]{MmsApp.c()});
                object = (object = object.getDeclaredMethod("getActiveSubscriptionInfoForSimSlotIndex", new Class[]{Integer.TYPE}).invoke(object2, new Object[]{n2})) != null ? object.getClass().getDeclaredMethod("getDisplayName", new Class[0]).invoke(object, new Object[0]).toString() : null;
            } else {
                List list = (List)object.getDeclaredMethod("getSubInfoUsingSlotId", new Class[]{Integer.TYPE}).invoke((Object)null, new Object[]{n2});
                object = var2_3;
                if (list != null) {
                    object = var2_3;
                    if (!list.isEmpty()) {
                        object = Class.forName((String)"android.telephony.SubInfoRecord").getDeclaredField("displayName").get(list.get(0)).toString();
                    }
                }
            }
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            object = var2_3;
        }
        aac.a("getCardName(" + n2 + "):" + (String)object);
        return object;
    }

    public static boolean b(long l2) {
        if (l2 > -1) {
            return true;
        }
        return false;
    }

    public static int c(Context context, int n2) {
        context = (TelephonyManager)context.getSystemService("phone");
        if (zv.a) {
            return (Integer)aau.a(TelephonyManager.class, (Object)context, "getSimState", Integer.TYPE, (Object)n2);
        }
        return context.getSimState();
    }

    public static SmsManager c(long l2) {
        try {
            Class class_ = Class.forName((String)"android.telephony.SmsManager");
            if (Build.VERSION.SDK_INT > 21) {
                return (SmsManager)class_.getDeclaredMethod("getSmsManagerForSubscriptionId", new Class[]{Integer.TYPE}).invoke((Object)class_, new Object[]{(int)l2});
            }
            class_ = (SmsManager)class_.getDeclaredMethod("getSmsManagerForSubscriber", new Class[]{Long.TYPE}).invoke((Object)class_, new Object[]{l2});
            return class_;
        }
        catch (ClassNotFoundException var2_2) {
            var2_2.printStackTrace();
            return null;
        }
        catch (NoSuchMethodException var2_3) {
            var2_3.printStackTrace();
            return null;
        }
        catch (InvocationTargetException var2_4) {
            var2_4.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var2_5) {
            var2_5.printStackTrace();
            return null;
        }
    }

    public static String d(Context context, int n2) {
        if (n2 == -10) {
            return "-10";
        }
        context = (TelephonyManager)context.getSystemService("phone");
        if (zv.a) {
            if (Build.VERSION.SDK_INT > 21) {
                return (String)aau.a(TelephonyManager.class, (Object)context, "getSubscriberId", Integer.TYPE, (Object)((int)aac.b(n2)));
            }
            return (String)aau.a(TelephonyManager.class, (Object)context, "getSubscriberId", Long.TYPE, (Object)aac.b(n2));
        }
        return context.getSubscriberId();
    }

    public static boolean e(Context context, int n2) {
        if (zv.a) {
            return (Boolean)aau.a("android.telephony.TelephonyManager", "getDefault", "hasIccCard", Integer.TYPE, (Object)n2);
        }
        return aac.a().hasIccCard();
    }

    public static enum a {
        a,
        b,
        c,
        d;
        

        private a() {
        }
    }

}

