/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.xiaomi.push.service;

import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.provider.PacketExtensionProvider;
import com.xiaomi.smack.provider.ProviderManager;
import com.xiaomi.smack.util.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class CommonPacketExtensionProvider
implements PacketExtensionProvider {
    public static CommonPacketExtension parseExtensionFromStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int n;
        if (xmlPullParser.getEventType() != 2) {
            return null;
        }
        String[] arrstring = null;
        String[] arrstring2 = null;
        String string2 = null;
        CommonPacketExtension commonPacketExtension = null;
        String string3 = xmlPullParser.getName();
        String string4 = xmlPullParser.getNamespace();
        String string5 = string2;
        Object object = commonPacketExtension;
        if (xmlPullParser.getAttributeCount() > 0) {
            String[] arrstring3 = new String[xmlPullParser.getAttributeCount()];
            String[] arrstring4 = new String[xmlPullParser.getAttributeCount()];
            n = 0;
            do {
                arrstring = arrstring3;
                arrstring2 = arrstring4;
                string5 = string2;
                object = commonPacketExtension;
                if (n >= xmlPullParser.getAttributeCount()) break;
                arrstring3[n] = xmlPullParser.getAttributeName(n);
                arrstring4[n] = StringUtils.unescapeFromXML(xmlPullParser.getAttributeValue(n));
                ++n;
            } while (true);
        }
        while ((n = xmlPullParser.next()) != 3) {
            if (n == 4) {
                string5 = xmlPullParser.getText().trim();
                continue;
            }
            if (n != 2) continue;
            string2 = object;
            if (object == null) {
                string2 = new ArrayList();
            }
            commonPacketExtension = CommonPacketExtensionProvider.parseExtensionFromStartTag(xmlPullParser);
            object = string2;
            if (commonPacketExtension == null) continue;
            string2.add(commonPacketExtension);
            object = string2;
        }
        return new CommonPacketExtension(string3, string4, arrstring, arrstring2, string5, (List<CommonPacketExtension>)object);
    }

    public CommonPacketExtension parseExtension(XmlPullParser xmlPullParser) throws Exception {
        int n = xmlPullParser.getEventType();
        while (n != 1 && n != 2) {
            n = xmlPullParser.next();
        }
        if (n == 2) {
            return CommonPacketExtensionProvider.parseExtensionFromStartTag(xmlPullParser);
        }
        return null;
    }

    public void register() {
        ProviderManager.getInstance().addExtensionProvider("all", "xm:chat", this);
    }
}

