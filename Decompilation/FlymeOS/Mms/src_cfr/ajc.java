/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
import java.io.InputStream;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;

public class ajc {
    static final String a;
    static final String b;
    static final String c;
    static final String d;
    private static final String[] e;
    private static final String[] f;
    private static final String[] g;

    static {
        e = new String[]{"si", "indication", "info", "item"};
        f = new String[]{"action=signal-none", "action=signal-low", "action=signal-medium", "action=signal-high", "action=delete", "create", "href", "href=http://", "href=http://www.", "href=https://", "href=https://www.", "si-expires", "si-id", "class"};
        g = new String[]{".com/", ".edu/", ".net/", ".org/"};
        a = e[0];
        b = e[1];
        c = f[6];
        d = f[12];
    }

    public ajb a(InputStream inputStream) {
        ajf ajf2 = new ajf();
        ajb ajb2 = new ajb();
        a a2 = new a(ajb2);
        ajh ajh2 = new ajh();
        ajh2.a(e);
        ajh2.b(f);
        ajh2.c(g);
        try {
            ajh2.a(inputStream, ajf2, a2);
            return ajb2;
        }
        catch (Exception var1_2) {
            ajb2.a = var1_2;
            return ajb2;
        }
    }

    class a
    implements DocumentHandler {
        String a;
        String b;
        private ajb d;

        public a(ajb ajb2) {
            this.d = ajb2;
        }

        @Override
        public void characters(char[] arrc, int n2, int n3) {
            this.b = new String(arrc, n2, n3);
            if (this.a.equals((Object)"indication")) {
                this.d.a().a(this.b);
            }
        }

        @Override
        public void endDocument() {
        }

        @Override
        public void endElement(String string2) {
        }

        @Override
        public void ignorableWhitespace(char[] arrc, int n2, int n3) {
        }

        @Override
        public void processingInstruction(String string2, String string3) {
        }

        @Override
        public void setDocumentLocator(Locator locator) {
        }

        @Override
        public void startDocument() {
        }

        @Override
        public void startElement(String string2, AttributeList attributeList) {
            this.a = string2;
            if (this.a.equals((Object)"indication")) {
                this.d.a().a(attributeList);
            }
        }
    }

}

