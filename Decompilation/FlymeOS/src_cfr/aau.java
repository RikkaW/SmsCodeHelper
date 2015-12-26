/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteException
 *  android.os.IBinder
 *  android.util.Log
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Constructor
 *  java.lang.reflect.Field
 *  java.lang.reflect.Method
 */
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.IBinder;
import android.util.Log;
import android.view.WindowManager;
import com.android.mms.MmsApp;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class aau {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(Class<?> object, Object object2, String string2) {
        try {
            object = object.getDeclaredField(string2);
            object.setAccessible(true);
            return object.get(object2);
        }
        catch (IllegalAccessException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchFieldException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
    }

    public static Object a(Class<?> object, Object object2, String string2, Class<?> class_, Object object3) {
        try {
            object = object.getDeclaredMethod(string2, new Class[]{class_}).invoke(object2, new Object[]{object3});
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(Class<?> field, Object object, String string2, Object object2) {
        try {
            field = field.getDeclaredField(string2);
            field.setAccessible(true);
            field.set(object, object2);
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchFieldException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(Class object, String string2) {
        try {
            return object.getDeclaredField(string2).get(object);
        }
        catch (NoSuchFieldException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (IllegalAccessException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (IllegalArgumentException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
    }

    public static Object a(Class<?> object, String string2, Class<?> class_, Object object2) {
        try {
            object = object.getDeclaredMethod(string2, new Class[]{class_}).invoke(object, new Object[]{object2});
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static Object a(Class<?> object, String string2, Class[] arrclass, Object[] arrobject) {
        try {
            object = object.getDeclaredMethod(string2, arrclass).invoke((Object)null, arrobject);
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(Class<?> object, Class<?>[] arrclass, Object[] arrobject) {
        try {
            return object.getDeclaredConstructor(arrclass).newInstance(arrobject);
        }
        catch (NoSuchMethodException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (InvocationTargetException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (InstantiationException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_4) {
            var0_4.printStackTrace();
            return null;
        }
    }

    public static Object a(Object object, String string2) {
        try {
            string2 = object.getClass().getDeclaredMethod(string2, new Class[0]);
            string2.setAccessible(true);
            object = string2.invoke(object, new Object[0]);
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(Object object, String string2, Class<?> class_, Object object2) {
        try {
            return object.getClass().getMethod(string2, new Class[]{class_}).invoke(object, new Object[]{object2});
        }
        catch (NoSuchMethodException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (InvocationTargetException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(String object, Class<?> class_, Object object2) {
        try {
            return Class.forName((String)object).getDeclaredConstructor(new Class[]{class_}).newInstance(new Object[]{object2});
        }
        catch (ClassNotFoundException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchMethodException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (InvocationTargetException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
        catch (InstantiationException var0_4) {
            var0_4.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_5) {
            var0_5.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(String object, Object object2, String string2) {
        try {
            object2 = Class.forName((String)"android.os.ServiceManager").getMethod("getService", new Class[]{String.class}).invoke(object2, new Object[]{string2});
            object = Class.forName((String)object);
            return object.getMethod("asInterface", new Class[]{IBinder.class}).invoke(object, new Object[]{object2});
        }
        catch (InvocationTargetException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchMethodException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
        catch (ClassNotFoundException var0_4) {
            var0_4.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object a(String object, String string2) {
        try {
            object = Class.forName((String)object);
            return object.getDeclaredField(string2).get(object);
        }
        catch (NoSuchFieldException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (ClassNotFoundException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
        catch (IllegalArgumentException var0_4) {
            var0_4.printStackTrace();
            return null;
        }
    }

    public static Object a(String object, String string2, Class<?> class_, Object object2) {
        try {
            object = Class.forName((String)object).getDeclaredMethod(string2, new Class[]{class_}).invoke((Object)null, new Object[]{object2});
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static Object a(String object, String object2, Class<?> class_, Object object3, String string2, String string3, Class<?> class_2, String string4, Class<?> class_3, Object object4) {
        block4 : {
            Object var10_11 = null;
            try {
                object = Class.forName((String)object);
                object2 = object.getDeclaredMethod((String)object2, new Class[]{class_}).invoke(object, new Object[]{object3});
                object = var10_11;
                if (object2 == null) break block4;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            object = Class.forName((String)string2);
            object2 = object.getDeclaredMethod(string3, new Class[]{class_2}).invoke(object, new Object[]{object2});
            object = var10_11;
            if (object2 == null) break block4;
            object = object2.getClass().getDeclaredMethod(string4, new Class[]{class_3}).invoke(object2, new Object[]{object4});
        }
        return object;
    }

    public static Object a(String object, String object2, String string2, Class<?> class_, Object object3) {
        block3 : {
            Class class_2;
            Object var5_6 = null;
            try {
                class_2 = Class.forName((String)object);
                object2 = class_2.getDeclaredMethod((String)object2, new Class[0]).invoke((Object)null, new Object[0]);
                object = var5_6;
                if (object2 == null) break block3;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return null;
            }
            object = class_2.getDeclaredMethod(string2, new Class[]{class_}).invoke(object2, new Object[]{object3});
        }
        return object;
    }

    public static Object a(String object, String string2, String string3, Class<?>[] arrclass, Context context) {
        try {
            object = Class.forName((String)object);
            string3 = object.getDeclaredField(string3);
            object = object.getMethod(string2, arrclass).invoke((Object)null, new Object[]{context, string3.get(object)});
            return object;
        }
        catch (ClassNotFoundException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        catch (InvocationTargetException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (NoSuchMethodException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_4) {
            var0_4.printStackTrace();
            return null;
        }
        catch (NoSuchFieldException var0_5) {
            var0_5.printStackTrace();
            return null;
        }
    }

    public static Object a(String object, String string2, Class<?>[] arrclass, Object[] arrobject) {
        try {
            object = Class.forName((String)object).getDeclaredMethod(string2, arrclass).invoke((Object)null, arrobject);
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static String a(String object) {
        if ((object = aau.a("com.android.internal.telephony.TelephonyProperties", (String)object)) != null && object instanceof String && (object = aau.a("android.os.SystemProperties", "get", String.class, object)) != null && object instanceof String) {
            return (String)object;
        }
        return null;
    }

    public static String a(String object, Object object2) {
        if ((object = aau.a("com.android.internal.telephony.TelephonyProperties", (String)object)) != null && object instanceof String) {
            Object[] arrobject = new Object[]{object, object2};
            object = aau.a("android.os.SystemProperties", "get", new Class[]{String.class, String.class}, arrobject);
            if (object != null && object instanceof String) {
                return (String)object;
            }
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void a() {
        Class class_;
        boolean bl2;
        block7 : {
            block6 : {
                try {
                    class_ = Class.forName((String)"android.os.BuildExt");
                    Object object = class_.newInstance();
                    Method method = class_.getDeclaredMethod("isChinaMobile", new Class[0]);
                    method.setAccessible(true);
                    MmsApp.a = (Boolean)method.invoke(object, new Object[0]);
                    method = class_.getDeclaredMethod("isChinaUnicom", new Class[0]);
                    method.setAccessible(true);
                    MmsApp.c = (Boolean)method.invoke(object, new Object[0]);
                    MmsApp.d = (Boolean)class_.getMethod("isProductInternational", new Class[0]).invoke((Object)null, new Object[0]);
                    MmsApp.b = (Boolean)aau.a(class_, "CUSTOMIZE_CHINATELECOM");
                    MmsApp.e = (Boolean)class_.getMethod("isFlymeRom", new Class[0]).invoke((Object)null, new Object[0]);
                    if (((Boolean)aau.a(class_, "IS_MX2")).booleanValue() || ((Boolean)aau.a(class_, "IS_MX3")).booleanValue() || ((Boolean)aau.a(class_, "IS_MX4_Pro")).booleanValue()) break block6;
                    bl2 = false;
                    break block7;
                }
                catch (ClassNotFoundException var1_1) {
                    var1_1.printStackTrace();
                    return;
                }
                catch (InstantiationException var1_2) {
                    var1_2.printStackTrace();
                    return;
                }
                catch (IllegalAccessException var1_3) {
                    var1_3.printStackTrace();
                    return;
                }
                catch (NoSuchMethodException var1_4) {
                    var1_4.printStackTrace();
                    return;
                }
                catch (InvocationTargetException var1_5) {
                    var1_5.printStackTrace();
                    return;
                }
            }
            bl2 = true;
        }
        MmsApp.g = bl2;
        MmsApp.f = (Boolean)aau.a(class_, "IS_CTA");
    }

    public static void a(Context context, SQLiteException sQLiteException) {
        aau.a("android.database.sqlite.SqliteWrapper", "checkSQLiteException", new Class[]{Context.class, SQLiteException.class}, (Object)null, new Object[]{context, sQLiteException});
    }

    public static void a(WindowManager.LayoutParams layoutParams, int n2) {
        try {
            Field field = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            field.setAccessible(true);
            field.set((Object)layoutParams, (Object)((Integer)field.get((Object)layoutParams) | n2));
            return;
        }
        catch (IllegalAccessException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (NoSuchFieldException var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    public static void a(Class<?> object, Object object2, String string2, String string3, Class<?> class_, Object object3) {
        try {
            object = object.getDeclaredField(string2);
            object.setAccessible(true);
            object = object.get(object2);
            object.getClass().getDeclaredMethod(string3, new Class[]{class_}).invoke(object, new Object[]{object3});
            return;
        }
        catch (NoSuchFieldException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (InvocationTargetException var0_2) {
            var0_2.printStackTrace();
            return;
        }
        catch (NoSuchMethodException var0_3) {
            var0_3.printStackTrace();
            return;
        }
        catch (IllegalAccessException var0_4) {
            var0_4.printStackTrace();
            return;
        }
    }

    public static void a(Class<?> object, Object object2, String string2, String string3, String string4, int n2, int n3) {
        try {
            object = object.getDeclaredField(string2).get(object2);
            object2 = object.getClass().getDeclaredField(string3);
            object2.setAccessible(true);
            object2.set(object, (Object)n2);
            object2 = object.getClass().getDeclaredField(string4);
            object2.setAccessible(true);
            object2.set(object, (Object)n3);
            return;
        }
        catch (NoSuchFieldException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (IllegalAccessException var0_2) {
            var0_2.printStackTrace();
            return;
        }
    }

    public static void a(Class<?> method, Object object, String string2, Class<?>[] arrclass, Object[] arrobject) {
        try {
            method = method.getDeclaredMethod(string2, arrclass);
            method.setAccessible(true);
            method.invoke(object, arrobject);
            return;
        }
        catch (InvocationTargetException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (NoSuchMethodException var0_2) {
            var0_2.printStackTrace();
            return;
        }
        catch (IllegalAccessException var0_3) {
            var0_3.printStackTrace();
            return;
        }
    }

    public static void a(Object object, String string2, Class<?>[] arrclass, Object[] arrobject) {
        try {
            string2 = object.getClass().getDeclaredMethod(string2, arrclass);
            string2.setAccessible(true);
            string2.invoke(object, arrobject);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void a(String string2, Object object, String string3, Class<?>[] arrclass, Object[] arrobject, String string4, Class<?>[] arrclass2, Object[] arrobject2) {
        try {
            string2 = Class.forName((String)string2);
            object = string2.getDeclaredMethod(string3, arrclass).invoke(object, arrobject);
            string2.getDeclaredMethod(string4, arrclass2).invoke(object, arrobject2);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(String string2, String object, String string3) {
        Class class_ = Class.forName((String)"android.os.ServiceManager");
        object = class_.getMethod("getService", new Class[]{String.class}).invoke((Object)class_, new Object[]{object});
        string2 = Class.forName((String)string2);
        object = string2.getMethod("asInterface", new Class[]{IBinder.class}).invoke((Object)string2, new Object[]{object});
        if (object == null) return;
        try {
            string2.getMethod(string3, new Class[0]).invoke(object, new Object[0]);
            return;
        }
        catch (ClassNotFoundException var0_1) {
            var0_1.printStackTrace();
            return;
        }
        catch (NoSuchMethodException var0_2) {
            var0_2.printStackTrace();
            return;
        }
        catch (InvocationTargetException var0_3) {
            var0_3.printStackTrace();
            return;
        }
        catch (IllegalAccessException var0_4) {
            var0_4.printStackTrace();
            return;
        }
    }

    public static void a(String string2, String string3, Class<?>[] arrclass, Object object, Object[] arrobject) {
        try {
            Class.forName((String)string2).getDeclaredMethod(string3, arrclass).invoke(object, arrobject);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object b(Class<?> object, Object object2, String string2) {
        try {
            return object.getDeclaredMethod(string2, new Class[0]).invoke(object2, new Object[0]);
        }
        catch (NoSuchMethodException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (InvocationTargetException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (IllegalAccessException var0_3) {
            var0_3.printStackTrace();
            return null;
        }
    }

    public static Object b(Class<?> object, Object object2, String string2, Class<?>[] arrclass, Object[] arrobject) {
        try {
            object = object.getDeclaredMethod(string2, arrclass);
            object.setAccessible(true);
            object = object.invoke(object2, arrobject);
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object b(Object object, String string2) {
        try {
            string2 = object.getClass().getDeclaredField(string2);
            string2.setAccessible(true);
            return string2.get(object);
        }
        catch (IllegalAccessException var0_1) {
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchFieldException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
    }

    public static Object b(String object, String string2) {
        try {
            object = Class.forName((String)object).getDeclaredMethod(string2, new Class[0]).invoke((Object)null, new Object[0]);
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static Object b(String object, String string2, Class<?> class_, Object object2) {
        try {
            object = Class.forName((String)object).getDeclaredMethod(string2, new Class[]{class_}).invoke((Object)null, new Object[]{object2});
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static String b(String object) {
        if ((object = aau.a("android.os.SystemProperties", "get", String.class, object)) != null && object instanceof String) {
            return (String)object;
        }
        return null;
    }

    public static void b(Class<?> method, Object object, String string2, Class<?> class_, Object object2) {
        try {
            method = method.getDeclaredMethod(string2, new Class[]{class_});
            method.setAccessible(true);
            method.invoke(object, new Object[]{object2});
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void b(Object object, String string2, Class<?> class_, Object object2) {
        try {
            string2 = object.getClass().getDeclaredMethod(string2, new Class[]{class_});
            string2.setAccessible(true);
            string2.invoke(object, new Object[]{object2});
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void b(String string2, String string3, Class[] arrclass, Object[] arrobject) {
        try {
            Class.forName((String)string2).getDeclaredMethod(string3, arrclass).invoke((Object)null, arrobject);
            return;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static Class<?> c(String string2) {
        try {
            string2 = Class.forName((String)string2);
            return string2;
        }
        catch (ClassNotFoundException var0_1) {
            var0_1.printStackTrace();
            return Object.class;
        }
    }

    public static Object c(Object object, String string2, Class<?> class_, Object object2) {
        try {
            string2 = object.getClass().getDeclaredMethod(string2, new Class[]{class_});
            string2.setAccessible(true);
            object = string2.invoke(object, new Object[]{object2});
            return object;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static boolean d(String string2) {
        return nc.d(string2);
    }

    public static int e(String string2) {
        try {
            Class class_ = Class.forName((String)"com.android.internal.R$style");
            Object object = class_.newInstance();
            int n2 = Integer.parseInt((String)class_.getField(string2).get(object).toString());
            return n2;
        }
        catch (Exception var0_1) {
            Log.e((String)"ReflectHelper", (String)"get getStyleId ", (Throwable)var0_1);
            return -1;
        }
    }

    public static int f(String string2) {
        try {
            Class class_ = Class.forName((String)"com.flyme.internal.R$anim");
            Object object = class_.newInstance();
            int n2 = Integer.parseInt((String)class_.getField(string2).get(object).toString());
            return n2;
        }
        catch (Exception var0_1) {
            Log.e((String)"ReflectHelper", (String)"get getAnimId ", (Throwable)var0_1);
            return -1;
        }
    }

    public static int g(String string2) {
        try {
            Class class_ = Class.forName((String)"com.android.internal.R$id");
            Object object = class_.newInstance();
            int n2 = Integer.parseInt((String)class_.getField(string2).get(object).toString());
            return n2;
        }
        catch (Exception var0_1) {
            Log.e((String)"ReflectHelper", (String)"get getInternalId ", (Throwable)var0_1);
            return -1;
        }
    }
}

