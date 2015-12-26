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

public class aje {
    static final String a;
    static final String b;
    private static final String[] c;
    private static final String[] d;
    private static final String[] e;

    static {
        c = new String[]{"sl"};
        d = new String[]{"action=signal-low", "action=signal-high", "action=cache", "href", "href=http://", "href=http://www.", "href=https://", "href=https://www."};
        e = new String[]{".com/", ".edu/", ".net/", ".org/"};
        a = c[0];
        b = d[3];
    }

    public ajd a(InputStream inputStream) {
        ajf ajf2 = new ajf();
        ajd ajd2 = new ajd();
        a a2 = new a(ajd2);
        ajh ajh2 = new ajh();
        ajh2.a(c);
        ajh2.b(d);
        ajh2.c(e);
        try {
            ajh2.a(inputStream, ajf2, a2);
            return ajd2;
        }
        catch (Exception var1_2) {
            ajd2.a = var1_2;
            return ajd2;
        }
    }

    class a
    implements DocumentHandler {
        String a;
        String b;
        private ajd d;

        public a(ajd ajd2) {
            this.d = ajd2;
        }

        @Override
        public void characters(char[] arrc, int n2, int n3) {
            this.b = new String(arrc, n2, n3);
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
            if (this.a.equals((Object)aje.a)) {
                this.d.a(attributeList);
            }
        }
    }

}

