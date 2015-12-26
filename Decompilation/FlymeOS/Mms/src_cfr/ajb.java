/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import org.xml.sax.AttributeList;

public class ajb {
    public Exception a = null;
    protected a b;

    public ajb() {
        this.b = new a();
    }

    public a a() {
        return this.b;
    }

    public String b() {
        return this.b.a();
    }

    public String c() {
        return this.b.b();
    }

    public Exception d() {
        return this.a;
    }

    public class a {
        protected String a;
        protected AttributeList b;

        public String a() {
            return this.a;
        }

        public void a(String string2) {
            this.a = string2;
        }

        public void a(AttributeList attributeList) {
            this.b = attributeList;
        }

        public String b() {
            if (this.b != null) {
                return this.b.getValue("href");
            }
            return "";
        }
    }

}

