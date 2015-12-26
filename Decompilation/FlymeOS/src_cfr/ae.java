/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Byte
 *  java.lang.Character
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.Short
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Constructor
 *  java.lang.reflect.Method
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  javax.xml.parsers.DocumentBuilder
 *  javax.xml.parsers.DocumentBuilderFactory
 *  org.xml.sax.SAXException
 */
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ae {
    private static ae a = new ae();
    private HashMap<String, b> b = new HashMap();

    private ae() {
        try {
            this.b();
            this.a();
            return;
        }
        catch (Exception var1_1) {
            var1_1.printStackTrace();
            throw new IllegalArgumentException(var1_1);
        }
    }

    public static Object a(String object, Object[] arrobject) {
        if ((object = (b)ae.a.b.get(object)) == null) {
            throw new NoSuchMethodException();
        }
        return object.a(arrobject);
    }

    public static Method a(String object) {
        if ((object = (b)ae.a.b.get(object)) == null) {
            throw new NoSuchMethodException();
        }
        return object.a;
    }

    private List<c> a(Node object) {
        object = object.getChildNodes();
        ArrayList arrayList = new ArrayList();
        int n2 = 0;
        while (n2 < object.getLength()) {
            Node node = object.item(n2);
            if (node.getNodeName().equals((Object)"constructor-arg")) {
                arrayList.add(new c(this, node.getAttributes().getNamedItem("type").getNodeValue(), node.getTextContent()));
            }
            ++n2;
        }
        return arrayList;
    }

    private void a() {
        Object object = af.class.newInstance();
        Method[] arrmethod = af.class.getDeclaredMethods();
        int n2 = arrmethod.length;
        int n3 = 0;
        block0 : while (n3 < n2) {
            Method method = arrmethod[n3];
            Annotation[] arrannotation = method.getAnnotations();
            int n4 = arrannotation.length;
            int n5 = 0;
            do {
                if (n5 >= n4) {
                    ++n3;
                    continue block0;
                }
                Annotation annotation = arrannotation[n5];
                if (annotation instanceof j) {
                    this.b.put((Object)((j)annotation).a(), (Object)new b(method, object));
                }
                ++n5;
            } while (true);
            break;
        }
        return;
    }

    private Class[] a(List<c> list) {
        if (list == null) {
            return null;
        }
        Class[] arrclass = new Class[list.size()];
        int n2 = 0;
        while (n2 < list.size()) {
            arrclass[n2] = list.get((int)n2).a;
            ++n2;
        }
        return arrclass;
    }

    private a b(Node object) {
        a a2 = new a(object.getAttributes().getNamedItem("name").getNodeValue(), object.getAttributes().getNamedItem("method").getNodeValue());
        object = object.getChildNodes();
        int n2 = 0;
        while (n2 < object.getLength()) {
            Node node = object.item(n2);
            if (node.getNodeName().equals((Object)"parameter-type")) {
                a2.a(node.getTextContent());
            }
            ++n2;
        }
        return a2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void b() {
        block14 : {
            var3_1 = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            try {
                var3_1 = var3_1.parse(ae.class.getResourceAsStream("/IKExpression.cfg.xml"));
                if (var3_1 == null) {
                    return;
                }
            }
            catch (IllegalArgumentException var3_2) {
                return;
            }
            if ((var3_1 = var3_1.getElementsByTagName("function-configuration")).getLength() < 1) return;
            var5_3 = var3_1.item(0).getChildNodes();
            var1_4 = 0;
            block2 : while (var1_4 < var5_3.getLength()) {
                var3_1 = var5_3.item(var1_4);
                if (var3_1.getNodeName().equals((Object)"bean")) {
                    var6_7 = Class.forName((String)var3_1.getAttributes().getNamedItem("class").getNodeValue());
                    var8_9 = var3_1.getChildNodes();
                    var7_8 = new HashSet();
                    var2_5 = 0;
                    var3_1 = null;
                    break block14;
                }
lbl21: // 4 sources:
                do {
                    ++var1_4;
                    continue block2;
                    break;
                } while (true);
            }
            return;
        }
        do {
            if (var2_5 < var8_9.getLength()) ** GOTO lbl42
            if (var7_8.size() <= 0) ** GOTO lbl21
            if (var3_1 == null || var3_1.size() <= 0) {
                var3_1 = var6_7.newInstance();
            } else {
                var4_6 = this.a((List<c>)var3_1);
                var3_1 = this.b((List<c>)var3_1);
                var3_1 = var6_7.getConstructor((Class[])var4_6).newInstance((Object[])var3_1);
            }
            var4_6 = var7_8.iterator();
            do {
                if (!var4_6.hasNext()) ** continue;
                var7_8 = (a)var4_6.next();
                var8_9 = var6_7.getMethod(var7_8.b, this.a(var7_8.c));
                this.b.put((Object)var7_8.a, (Object)new b((Method)var8_9, var3_1));
            } while (true);
lbl42: // 1 sources:
            var9_10 = var8_9.item(var2_5);
            if (var9_10.getNodeName().equals((Object)"constructor-args") && var3_1 == null) {
                var4_6 = this.a(var9_10);
            } else {
                var4_6 = var3_1;
                if (var9_10.getNodeName().equals((Object)"function")) {
                    var4_6 = var3_1;
                    if (!var7_8.add((Object)this.b(var9_10))) {
                        throw new SAXException("\u65b9\u6cd5\u540d\u4e0d\u80fd\u91cd\u590d");
                    }
                }
            }
            ++var2_5;
            var3_1 = var4_6;
        } while (true);
    }

    private Object[] b(List<c> list) {
        if (list == null) {
            return null;
        }
        Object[] arrobject = new Object[list.size()];
        int n2 = 0;
        while (n2 < list.size()) {
            arrobject[n2] = list.get((int)n2).b;
            ++n2;
        }
        return arrobject;
    }

    class a {
        String a;
        String b;
        List<c> c;

        public a(String string2, String string3) {
            if (string2 == null || string3 == null) {
                throw new IllegalArgumentException();
            }
            this.a = string2;
            this.b = string3;
            this.c = new ArrayList();
        }

        public void a(String string2) {
            this.c.add(new c(ae.this, string2));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            boolean bl2 = false;
            if (this == object) {
                return true;
            }
            boolean bl3 = bl2;
            if (object == null) return bl3;
            bl3 = bl2;
            if (this.getClass() != object.getClass()) return bl3;
            object = (a)object;
            return this.a.equals((Object)object.a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

    class b {
        Method a;
        Object b;

        public b(Method method, Object object) {
            this.a = method;
            this.b = object;
        }

        public Object a(Object[] arrobject) {
            return this.a.invoke(this.b, arrobject);
        }
    }

    class c {
        Class a;
        Object b;
        final /* synthetic */ ae c;

        public c(ae ae2, String string2) {
            this.c = ae2;
            try {
                this.a = this.a(string2);
                return;
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }

        public c(ae ae2, String string2, String string3) {
            this.c = ae2;
            try {
                this.a = this.a(string2);
                this.b = this.a.getConstructor(new Class[]{String.class}).newInstance(new Object[]{string3});
                return;
            }
            catch (Exception var1_2) {
                var1_2.printStackTrace();
                return;
            }
        }

        private Class a(String string2) {
            if ("boolean".equals((Object)string2)) {
                return Boolean.TYPE;
            }
            if ("byte".equals((Object)string2)) {
                return Byte.TYPE;
            }
            if ("char".equals((Object)string2)) {
                return Character.TYPE;
            }
            if ("double".equals((Object)string2)) {
                return Double.TYPE;
            }
            if ("float".equals((Object)string2)) {
                return Float.TYPE;
            }
            if ("int".equals((Object)string2)) {
                return Integer.TYPE;
            }
            if ("long".equals((Object)string2)) {
                return Long.TYPE;
            }
            if ("short".equals((Object)string2)) {
                return Short.TYPE;
            }
            return Class.forName((String)string2);
        }
    }

}

