/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  javax.xml.parsers.SAXParser
 *  javax.xml.parsers.SAXParserFactory
 *  org.json.JSONObject
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ahr {
    protected ahr() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    ahf a(String var1_1) {
        if (var1_1 == null) return null;
        if (var1_1.length() == 0) {
            return null;
        }
        if (var1_1.contains((CharSequence)"SuccessCode=\"0\"") != false) return null;
        try {
            var1_1 = new ByteArrayInputStream(var1_1.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException var1_2) {
            var1_1 = null;
        }
        var3_5 = SAXParserFactory.newInstance();
        var2_6 = new a();
        if (var1_1 == null) ** GOTO lbl22
        try {
            var3_5.newSAXParser().parse((InputStream)var1_1, (DefaultHandler)var2_6);
            var1_1.close();
            ** GOTO lbl22
        }
        catch (Throwable var1_3) {
            var1_3.printStackTrace();
lbl22: // 3 sources:
            var2_6.a.f("network");
            if (var2_6.a.h() != 0) return var2_6.a;
            var2_6.a.a(ahz.a());
            return var2_6.a;
        }
    }

    static class a
    extends DefaultHandler {
        public ahf a = new ahf();
        private String b = "";

        private a() {
        }

        @Override
        public void characters(char[] arrc, int n2, int n3) {
            this.b = String.valueOf((char[])arrc, (int)n2, (int)n3);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void endElement(String string2, String string3, String string4) {
            if (string3.equals((Object)"retype")) {
                this.a.h(this.b);
            } else if (string3.equals((Object)"adcode")) {
                this.a.k(this.b);
            } else if (string3.equals((Object)"citycode")) {
                this.a.i(this.b);
            } else if (string3.equals((Object)"radius")) {
                try {
                    this.a.a(Float.valueOf((String)this.b).floatValue());
                }
                catch (Throwable var1_2) {
                    var1_2.printStackTrace();
                    this.a.a(3891.0f);
                }
            } else if (string3.equals((Object)"cenx")) {
                try {
                    this.b = ahv.a((Object)Double.valueOf((String)this.b), "#.000000");
                    this.a.a(Double.valueOf((String)this.b));
                }
                catch (Throwable var1_3) {
                    var1_3.printStackTrace();
                    this.a.a(0.0);
                }
            } else if (string3.equals((Object)"ceny")) {
                try {
                    this.b = ahv.a((Object)Double.valueOf((String)this.b), "#.000000");
                    this.a.b(Double.valueOf((String)this.b));
                }
                catch (Throwable var1_4) {
                    var1_4.printStackTrace();
                    this.a.b(0.0);
                }
            } else if (string3.equals((Object)"desc")) {
                this.a.j(this.b);
            } else if (string3.equals((Object)"country")) {
                this.a.l(this.b);
            } else if (string3.equals((Object)"province")) {
                this.a.m(this.b);
            } else if (string3.equals((Object)"city")) {
                this.a.n(this.b);
            } else if (string3.equals((Object)"road")) {
                this.a.o(this.b);
            } else if (string3.equals((Object)"street")) {
                this.a.p(this.b);
            } else if (string3.equals((Object)"poiname")) {
                this.a.q(this.b);
            } else if (string3.equals((Object)"BIZ")) {
                if (this.a.t() == null) {
                    this.a.a(new JSONObject());
                }
                try {
                    this.a.t().put("BIZ", (Object)this.b);
                }
                catch (Throwable var1_5) {
                    var1_5.printStackTrace();
                }
            } else if (string3.equals((Object)"flr")) {
                this.a.b(this.b);
            } else if (string3.equals((Object)"pid")) {
                this.a.a(this.b);
            } else if (string3.equals((Object)"apiTime")) {
                try {
                    if (!"".equals((Object)this.b)) {
                        long l2 = Long.parseLong((String)this.b);
                        this.a.a(l2);
                    }
                }
                catch (Throwable var1_6) {
                    var1_6.printStackTrace();
                    this.a.a(ahz.a());
                }
            } else if (string3.equals((Object)"coord")) {
                try {
                    this.a.d(this.b);
                }
                catch (Throwable var1_7) {
                    var1_7.printStackTrace();
                }
            } else if (string3.equals((Object)"mcell")) {
                try {
                    this.a.e(this.b);
                }
                catch (Throwable var1_8) {
                    var1_8.printStackTrace();
                }
            } else if (string3.equals((Object)"district")) {
                try {
                    this.a.c(this.b);
                }
                catch (Throwable var1_9) {
                    var1_9.printStackTrace();
                }
            }
            if (this.a.t() == null) {
                this.a.a(new JSONObject());
            }
            try {
                if (string3.equals((Object)"eab")) {
                    this.a.t().put(string3, (Object)this.b);
                    return;
                }
                if (string3.equals((Object)"ctl")) {
                    this.a.t().put(string3, (Object)this.b);
                    return;
                }
                if (string3.equals((Object)"suc")) {
                    this.a.t().put(string3, (Object)this.b);
                    return;
                }
            }
            catch (Throwable var1_10) {
                var1_10.printStackTrace();
                return;
            }
            if (!string3.equals((Object)"spa")) return;
            {
                this.a.t().put(string3, (Object)this.b);
                return;
            }
        }

        @Override
        public void startElement(String string2, String string3, String string4, Attributes attributes) {
            this.b = "";
        }
    }

}

