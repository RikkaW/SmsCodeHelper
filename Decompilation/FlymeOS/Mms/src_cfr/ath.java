/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  org.apache.http.Header
 */
import org.apache.http.Header;

public abstract class ath<JSON_TYPE>
extends auv {
    public ath() {
        this("UTF-8");
    }

    public ath(String string2) {
        super(string2);
    }

    @Override
    public final void a(int n2, Header[] object, String string2) {
        if (n2 != 204) {
            object = new ati(this, string2, n2, (Header[])object);
            if (!this.d()) {
                new Thread((Runnable)object).start();
                return;
            }
            object.run();
            return;
        }
        this.a(n2, (Header[])object, (String)null, (JSON_TYPE)null);
    }

    public abstract void a(int var1, Header[] var2, String var3, JSON_TYPE var4);

    @Override
    public final void a(int n2, Header[] object, String string2, Throwable throwable) {
        if (string2 != null) {
            object = new atp(this, string2, n2, (Header[])object, throwable);
            if (!this.d()) {
                new Thread((Runnable)object).start();
                return;
            }
            object.run();
            return;
        }
        this.a(n2, (Header[])object, throwable, null, null);
    }

    public abstract void a(int var1, Header[] var2, Throwable var3, String var4, JSON_TYPE var5);

    protected abstract JSON_TYPE b(String var1, boolean var2);
}

