/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ak
implements ah {
    public static final ai a = ai.s;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private m a(m m2, m m3) {
        if (m2 == null) throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e22\u5931");
        if (m3 == null) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e22\u5931");
        }
        ArrayList arrayList = new ArrayList();
        if (l.a.i == m2.a()) {
            if (m2.k() != null) {
                arrayList.addAll(m2.k());
            }
        } else {
            try {
                arrayList.add(m2.o());
            }
            catch (ParseException var1_2) {
                var1_2.printStackTrace();
            }
        }
        if (l.a.i == m3.a()) {
            if (m3.k() == null) return new m(l.a.i, (Object)arrayList);
            arrayList.addAll(m3.k());
            return new m(l.a.i, (Object)arrayList);
        }
        try {
            arrayList.add(m3.o());
            return new m(l.a.i, (Object)arrayList);
        }
        catch (ParseException var1_3) {
            var1_3.printStackTrace();
            return new m(l.a.i, (Object)arrayList);
        }
    }

    @Override
    public m a(int n2, l[] object) {
        if (object == null) {
            throw new IllegalArgumentException("\u8fd0\u7b97\u64cd\u4f5c\u7b26\u53c2\u6570\u4e3a\u7a7a");
        }
        if (object.length != 2) {
            throw new i("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d", a.a(), n2);
        }
        l l2 = object[1];
        object = object[0];
        if (l2 == null || object == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        return new m(l.a.i, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public m a(m[] object) {
        if (object == null || object.length != 2) {
            throw new IllegalArgumentException("\u64cd\u4f5c\u7b26\"" + a.a() + "\u53c2\u6570\u4e2a\u6570\u4e0d\u5339\u914d");
        }
        Object object2 = object[1];
        Object object3 = object[0];
        if (object2 == null || object3 == null) {
            throw new NullPointerException("\u64cd\u4f5c\u7b26\"" + a.a() + "\"\u53c2\u6570\u4e3a\u7a7a");
        }
        object = object2.p() ? ((n)object2.b()).b() : object2;
        if (object3.p()) {
            object3 = ((n)object3.b()).b();
        }
        return this.a((m)object, (m)object3);
    }
}

