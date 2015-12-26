/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import org.xml.sax.AttributeList;

public class ajd {
    public Exception a = null;
    protected AttributeList b;

    public String a() {
        if (this.b != null) {
            return this.b.getValue("href");
        }
        return "";
    }

    public void a(AttributeList attributeList) {
        this.b = attributeList;
    }

    public Exception b() {
        return this.a;
    }
}

