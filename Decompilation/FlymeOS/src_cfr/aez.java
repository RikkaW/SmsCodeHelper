/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accounts.Account
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.accounts.Account;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aez
implements afb {
    private static String a = "vCard";
    private final List<aey> b = new ArrayList();
    private aey c;
    private final int d;
    private final Account e;
    private final List<afa> f = new ArrayList();

    public aez() {
        this(-1073741824, null, null);
    }

    public aez(int n2, Account account) {
        this(n2, account, null);
    }

    @Deprecated
    public aez(int n2, Account account, String string2) {
        this.d = n2;
        this.e = account;
    }

    @Override
    public void a() {
        Iterator<afa> iterator = this.f.iterator();
        while (iterator.hasNext()) {
            iterator.next().a();
        }
    }

    public void a(afa afa2) {
        this.f.add(afa2);
    }

    @Override
    public void a(afj afj2) {
        this.c.a(afj2);
    }

    @Override
    public void b() {
        Iterator<afa> iterator = this.f.iterator();
        while (iterator.hasNext()) {
            iterator.next().b();
        }
    }

    @Override
    public void c() {
        this.c = new aey(this.d, this.e);
        this.b.add(this.c);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void d() {
        this.c.a();
        Object object = this.f.iterator();
        while (object.hasNext()) {
            object.next().a(this.c);
        }
        int n2 = this.b.size();
        if (n2 > 1) {
            object = this.b.get(n2 - 2);
            object.a(this.c);
            this.c = object;
        } else {
            this.c = null;
        }
        this.b.remove(n2 - 1);
    }
}

