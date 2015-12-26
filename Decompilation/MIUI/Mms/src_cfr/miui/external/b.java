/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  dalvik.system.BaseDexClassLoader
 *  dalvik.system.DexClassLoader
 *  dalvik.system.PathClassLoader
 *  java.io.File
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.reflect.Field
 */
package miui.external;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

class b {
    b() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static Object a(ClassLoader classLoader) throws NoSuchFieldException {
        if (!(classLoader instanceof BaseDexClassLoader)) throw new NoSuchFieldException("dexPathList field not found.");
        Field[] arrfield = BaseDexClassLoader.class.getDeclaredFields();
        int n = arrfield.length;
        int n2 = 0;
        while (n2 < n) {
            Field field = arrfield[n2];
            if ("dalvik.system.DexPathList".equals((Object)field.getType().getName())) {
                field.setAccessible(true);
                try {
                    return field.get((Object)classLoader);
                }
                catch (IllegalArgumentException var4_7) {
                    // empty catch block
                }
                catch (IllegalAccessException var4_8) {}
            }
            ++n2;
        }
        throw new NoSuchFieldException("dexPathList field not found.");
    }

    private static Field a(Object arrfield) throws NoSuchFieldException {
        for (Field field : arrfield.getClass().getDeclaredFields()) {
            Class class_ = field.getType();
            if (!class_.isArray() || !"dalvik.system.DexPathList$Element".equals((Object)class_.getComponentType().getName())) continue;
            field.setAccessible(true);
            return field;
        }
        throw new NoSuchFieldException("dexElements field not found.");
    }

    private static void a(Object object, File file) throws NoSuchFieldException, IllegalAccessException {
        Field field = b.b(object);
        File[] arrfile = (File[])field.get(object);
        File[] arrfile2 = new File[arrfile.length + 1];
        arrfile2[0] = file;
        System.arraycopy((Object)arrfile, (int)0, (Object)arrfile2, (int)1, (int)arrfile.length);
        field.set(object, (Object)arrfile2);
    }

    private static void a(Object object, Object object2) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field field = b.a(object);
        Object[] arrobject = (Object[])field.get(object);
        Object[] arrobject2 = (Object[])Array.newInstance(Class.forName((String)"dalvik.system.DexPathList$Element"), arrobject.length + 1);
        arrobject2[0] = object2;
        System.arraycopy((Object)arrobject, (int)0, (Object)arrobject2, (int)1, (int)arrobject.length);
        field.set(object, (Object)arrobject2);
    }

    private static Field b(Object arrfield) throws NoSuchFieldException {
        for (Field field : arrfield.getClass().getDeclaredFields()) {
            Class class_ = field.getType();
            if (!class_.isArray() || class_.getComponentType() != File.class) continue;
            field.setAccessible(true);
            return field;
        }
        throw new NoSuchFieldException("nativeLibraryDirectories field not found.");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean load(String object, String string2, String string3, ClassLoader classLoader) {
        try {
            Object object2 = b.a(classLoader);
            object = string2 == null ? new PathClassLoader((String)object, string3, classLoader.getParent()) : new DexClassLoader((String)object, string2, string3, classLoader.getParent());
            object = b.a((ClassLoader)object);
            b.a(object2, ((Object[])b.a(object).get(object))[0]);
            if (string3 == null) return true;
            b.a(object2, new File(string3));
            return true;
        }
        catch (IllegalArgumentException var0_1) {
            return true;
        }
        catch (NoSuchFieldException var0_2) {
            return true;
        }
        catch (ClassNotFoundException var0_3) {
            return true;
        }
        catch (IllegalAccessException var0_4) {
            // empty catch block
        }
        return true;
    }
}

