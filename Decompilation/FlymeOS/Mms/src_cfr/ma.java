/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.KeyEvent
 *  android.view.View
 *  android.widget.EditText
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Method
 *  java.lang.reflect.Proxy
 */
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ma
implements InvocationHandler {
    private EditText a;

    public ma() {
    }

    public ma(EditText editText) {
        this.a = editText;
    }

    private Object a(Class<?> class_) {
        return Proxy.newProxyInstance((ClassLoader)class_.getClassLoader(), (Class[])new Class[]{class_}, (InvocationHandler)this);
    }

    public void a(EditText editText) {
        this.a = editText;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(boolean bl2) {
        try {
            Object object = Class.forName((String)"android.widget.EditText$OnKeyPreImeListener");
            Method method = EditText.class.getDeclaredMethod("setOnKeyPreImeListener", new Class[]{object});
            if (bl2) {
                method.invoke((Object)this.a, new Object[]{null});
                return;
            }
            if ((object = this.a(object)) == null) return;
            {
                method.invoke((Object)this.a, new Object[]{object});
                return;
            }
        }
        catch (Exception var2_4) {
            var2_4.printStackTrace();
        }
    }

    protected boolean a(View view, int n2, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] arrobject) {
        object = null;
        if ("onKeyPreIme".equals((Object)method.getName())) {
            object = this.a((View)arrobject[0], (Integer)arrobject[1], (KeyEvent)arrobject[2]);
        }
        return object;
    }
}

