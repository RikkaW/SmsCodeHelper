/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.InputStreamReader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  org.xml.sax.SAXException
 *  org.xml.sax.helpers.AttributeListImpl
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributeListImpl;

public class ajh {
    private String[] a;
    private String[] b;
    private String[] c;
    private byte[] d;

    /*
     * Enabled aggressive block sorting
     */
    private AttributeList a(ajg ajg2) {
        AttributeListImpl attributeListImpl = new AttributeListImpl();
        byte by2 = ajg2.b();
        while (by2 != 1) {
            StringBuffer stringBuffer = new StringBuffer();
            String string2 = this.a(ajg2, this.b, by2);
            int n2 = string2.indexOf(61);
            String string3 = string2;
            if (n2 != -1) {
                stringBuffer.append(string2.substring(n2 + 1));
                string3 = string2.substring(0, n2);
            }
            by2 = ajg2.b();
            while ((by2 & 128) != 0 || by2 == 2 || by2 == 3 || by2 == -125 || by2 >= 64 && by2 <= 66 || by2 >= -128 && by2 <= -126) {
                switch (by2) {
                    default: {
                        stringBuffer.append(this.a(ajg2, this.c, by2));
                        break;
                    }
                    case 2: {
                        stringBuffer.append((char)ajg2.b());
                        break;
                    }
                    case 3: {
                        stringBuffer.append(ajg2.d());
                        break;
                    }
                    case -125: {
                        stringBuffer.append(ajg2.e());
                        break;
                    }
                    case -128: 
                    case -127: 
                    case -126: 
                    case -64: 
                    case -63: 
                    case -62: 
                    case 64: 
                    case 65: 
                    case 66: {
                        throw new SAXException("Unsupported token " + by2);
                    }
                    case -61: {
                        int n3 = ajg2.b();
                        if (n3 < 0) {
                            throw new SAXException("Unsupported token OPAQUE" + by2);
                        }
                        for (n2 = 0; n2 < n3; ++n2) {
                            ajg2.b();
                        }
                    }
                }
                by2 = ajg2.b();
            }
            attributeListImpl.addAttribute(string3, null, stringBuffer.toString());
        }
        return attributeListImpl;
    }

    private void a(ajg ajg2, byte by2, DocumentHandler documentHandler, Stack<String> stack) {
        byte by3 = (byte)(by2 & 63);
        String string2 = this.a(ajg2, this.a, by3);
        Object object = new AttributeListImpl();
        if ((by2 & 128) != 0) {
            object = this.a(ajg2);
        }
        documentHandler.startElement(string2, (AttributeList)object);
        if ((by2 & 64) != 0) {
            stack.addElement((Object)string2);
            return;
        }
        documentHandler.endElement(string2);
    }

    public String a(int n2) {
        InputStreamReader inputStreamReader = new InputStreamReader((InputStream)new ByteArrayInputStream(this.d, n2, this.d.length), "UTF-8");
        StringBuffer stringBuffer = new StringBuffer();
        do {
            if ((n2 = inputStreamReader.read()) == -1) {
                throw new IOException("Unexpected stream EOF met");
            }
            if (n2 == 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append((char)n2);
        } while (true);
    }

    String a(ajg ajg2, String[] arrstring, byte by2) {
        int n2 = (by2 & 127) - 5;
        if (n2 == -1) {
            return ajg2.d();
        }
        if (n2 < 0 || arrstring == null || n2 >= arrstring.length || arrstring[n2] == null) {
            throw new SAXException("Undefined Token " + by2);
        }
        return arrstring[n2];
    }

    public void a(InputStream object, ajf object2, DocumentHandler documentHandler) {
        block15 : {
            byte by2;
            int n2;
            char[] arrc = new char[1];
            Stack<String> stack = new Stack<String>();
            ajg ajg2 = new ajg((InputStream)object);
            object2.a = ajg2.b();
            object2.b = ajg2.b();
            if (object2.b == 0) {
                ajg2.c();
            }
            object2.c = ajg2.b();
            int n3 = ajg2.b();
            this.d = new byte[n3];
            for (n2 = 0; n2 < n3; ++n2) {
                this.d[n2] = ajg2.b();
            }
            documentHandler.startDocument();
            block9 : do {
                if ((n2 = ajg2.a()) == -1) {
                    if (!stack.isEmpty()) {
                        throw new SAXException("Inconsistent WAP nodes");
                    }
                    break block15;
                }
                by2 = (byte)n2;
                switch (by2) {
                    default: {
                        this.a(ajg2, by2, documentHandler, stack);
                        continue block9;
                    }
                    case 0: {
                        n2 = ajg2.b();
                        if (n2 == 0) continue block9;
                        throw new SAXException("Unsupported Code Page " + n2);
                    }
                    case 1: {
                        if (stack.isEmpty()) {
                            throw new SAXException("Parse Error, two many end flag");
                        }
                        documentHandler.endElement(stack.pop());
                        continue block9;
                    }
                    case 2: {
                        arrc[0] = (char)ajg2.c();
                        documentHandler.characters(arrc, 0, 1);
                        continue block9;
                    }
                    case 3: {
                        object = ajg2.d();
                        documentHandler.characters(object.toCharArray(), 0, object.length());
                        continue block9;
                    }
                    case -125: {
                        object = object2 = this.a(ajg2.c());
                        if (object2 == null) {
                            object = "";
                        }
                        documentHandler.characters(object.toCharArray(), 0, object.length());
                        continue block9;
                    }
                    case -128: 
                    case -127: 
                    case -126: 
                    case -64: 
                    case -63: 
                    case -62: 
                    case -61: 
                    case 64: 
                    case 65: 
                    case 66: 
                    case 67: 
                }
                break;
            } while (true);
            throw new SAXException("Not support token " + by2);
        }
    }

    public void a(String[] arrstring) {
        this.a = arrstring;
    }

    public void b(String[] arrstring) {
        this.b = arrstring;
    }

    public void c(String[] arrstring) {
        this.c = arrstring;
    }
}

