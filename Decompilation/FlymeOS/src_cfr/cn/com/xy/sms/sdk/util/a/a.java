/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Xml
 *  java.lang.Object
 *  java.lang.String
 *  org.xmlpull.v1.XmlPullParser
 */
package cn.com.xy.sms.sdk.util.a;

import android.util.Xml;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;

public final class a {
    /*
     * Enabled aggressive block sorting
     */
    private static void a(InputStream object, String[] arrstring, XyCallBack xyCallBack) {
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput((InputStream)object, "UTF-8");
        int n2 = xmlPullParser.getEventType();
        while (n2 != 1) {
            switch (n2) {
                default: {
                    break;
                }
                case 2: {
                    object = xmlPullParser.getName();
                    int n3 = arrstring.length;
                    for (n2 = 0; n2 < n3; ++n2) {
                        if (!object.equals((Object)arrstring[n2])) continue;
                        xyCallBack.execute(object, xmlPullParser.nextText());
                    }
                }
            }
            n2 = xmlPullParser.next();
        }
        return;
    }
}

