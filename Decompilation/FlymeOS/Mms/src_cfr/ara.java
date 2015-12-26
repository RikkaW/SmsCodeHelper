/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.widget.AbsListView
 *  android.widget.ListView
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.lang.reflect.Proxy
 */
import android.widget.AbsListView;
import android.widget.ListView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class ara
implements InvocationHandler {
    protected AbsListView a;
    private boolean b;

    public ara(AbsListView absListView) {
        this.a = absListView;
        this.b = true;
    }

    private Object a(Class<?> class_) {
        return Proxy.newProxyInstance((ClassLoader)class_.getClassLoader(), (Class[])new Class[]{class_}, (InvocationHandler)this);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean a() {
        Method method;
        Object object;
        boolean bl2 = true;
        if (!this.b) return false;
        if (!(this.a instanceof ListView)) {
            return false;
        }
        try {
            object = Class.forName((String)"android.widget.ListView$DividerPadding");
            method = ListView.class.getMethod("setDividerPadding", new Class[]{object});
        }
        catch (Exception var2_4) {
            var2_4.printStackTrace();
            return false;
        }
        object = this.a(object);
        if (object == null) return false;
        try {
            method.invoke((Object)this.a, new Object[]{object});
        }
        catch (IllegalArgumentException var2_5) {
            var2_5.printStackTrace();
            return false;
        }
        catch (IllegalAccessException var2_6) {
            var2_6.printStackTrace();
            return false;
        }
        catch (InvocationTargetException var2_7) {
            var2_7.printStackTrace();
            return false;
        }
        return bl2;
    }

    public abstract int[] a(int var1);

    @Override
    public Object invoke(Object object, Method method, Object[] arrobject) {
        try {
            if ("getDividerPadding".equals((Object)method.getName())) {
                object = this.a((Integer)arrobject[0]);
                return object;
            }
        }
        catch (Exception var1_2) {
            var1_2.printStackTrace();
        }
        return null;
    }
}

