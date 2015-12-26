/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.io.InputStreamReader
 *  java.io.PrintStream
 *  java.io.Reader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashMap
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 *  org.xmlpull.v1.XmlPullParserFactory
 */
package com.xiaomi.smack.util;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.push.service.CommonPacketExtensionProvider;
import com.xiaomi.push.service.PushClientsManager;
import com.xiaomi.push.service.RC4Cryption;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.XMBinder;
import com.xiaomi.smack.XMPPException;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.IQ;
import com.xiaomi.smack.packet.Message;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.packet.Presence;
import com.xiaomi.smack.packet.StreamError;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.provider.ProviderManager;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class PacketParserUtils {
    private static XmlPullParser sDecryptedMsgParser = null;

    private static String getLanguageAttribute(XmlPullParser xmlPullParser) {
        for (int i = 0; i < xmlPullParser.getAttributeCount(); ++i) {
            String string2 = xmlPullParser.getAttributeName(i);
            if (!"xml:lang".equals((Object)string2) && (!"lang".equals((Object)string2) || !"xml".equals((Object)xmlPullParser.getAttributePrefix(i)))) continue;
            return xmlPullParser.getAttributeValue(i);
        }
        return null;
    }

    public static XMBinder.BindResult parseBindResult(XmlPullParser xmlPullParser) throws Exception {
        XMBinder.BindResult bindResult = new XMBinder.BindResult();
        Object object = xmlPullParser.getAttributeValue("", "id");
        String string2 = xmlPullParser.getAttributeValue("", "to");
        String string3 = xmlPullParser.getAttributeValue("", "from");
        String string4 = xmlPullParser.getAttributeValue("", "chid");
        XMBinder.BindResult.Type type = XMBinder.BindResult.Type.fromString(xmlPullParser.getAttributeValue("", "type"));
        bindResult.setPacketID((String)object);
        bindResult.setTo(string2);
        bindResult.setFrom(string3);
        bindResult.setChannelId(string4);
        bindResult.setType(type);
        boolean bl = false;
        object = null;
        while (!bl) {
            int n = xmlPullParser.next();
            if (n == 2) {
                if (!xmlPullParser.getName().equals((Object)"error")) continue;
                object = PacketParserUtils.parseError(xmlPullParser);
                continue;
            }
            if (n != 3 || !xmlPullParser.getName().equals((Object)"bind")) continue;
            bl = true;
        }
        bindResult.setError((XMPPError)object);
        return bindResult;
    }

    private static String parseContent(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String string2 = "";
        int n = xmlPullParser.getDepth();
        while (xmlPullParser.next() != 3 || xmlPullParser.getDepth() != n) {
            string2 = string2 + xmlPullParser.getText();
        }
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static XMPPError parseError(XmlPullParser object) throws Exception {
        int n;
        String string2 = "-1";
        String string3 = null;
        String string4 = null;
        String string5 = null;
        String string6 = null;
        ArrayList arrayList = new ArrayList();
        for (n = 0; n < object.getAttributeCount(); ++n) {
            if (object.getAttributeName(n).equals((Object)"code")) {
                string2 = object.getAttributeValue("", "code");
            }
            if (object.getAttributeName(n).equals((Object)"type")) {
                string3 = object.getAttributeValue("", "type");
            }
            if (!object.getAttributeName(n).equals((Object)"reason")) continue;
            string6 = object.getAttributeValue("", "reason");
        }
        n = 0;
        while (n == 0) {
            int n2 = object.next();
            if (n2 == 2) {
                if (object.getName().equals((Object)"text")) {
                    string4 = object.nextText();
                    continue;
                }
                String string7 = object.getName();
                String string8 = object.getNamespace();
                if ("urn:ietf:params:xml:ns:xmpp-stanzas".equals((Object)string8)) {
                    string5 = string7;
                    continue;
                }
                arrayList.add(PacketParserUtils.parsePacketExtension(string7, string8, (XmlPullParser)object));
                continue;
            }
            if (n2 == 3) {
                if (!object.getName().equals((Object)"error")) continue;
                n = 1;
                continue;
            }
            if (n2 != 4) continue;
            string4 = object.getText();
        }
        if (string3 == null) {
            object = "cancel";
            do {
                return new XMPPError(Integer.parseInt((String)string2), (String)object, string6, string5, string4, (List<CommonPacketExtension>)arrayList);
                break;
            } while (true);
        }
        object = string3;
        return new XMPPError(Integer.parseInt((String)string2), (String)object, string6, string5, string4, (List<CommonPacketExtension>)arrayList);
    }

    public static IQ parseIQ(XmlPullParser object, Connection connection) throws Exception {
        int n;
        Object object2;
        IQ iQ = null;
        String string2 = object.getAttributeValue("", "id");
        String string3 = object.getAttributeValue("", "to");
        String string4 = object.getAttributeValue("", "from");
        String string5 = object.getAttributeValue("", "chid");
        IQ.Type type = IQ.Type.fromString(object.getAttributeValue("", "type"));
        HashMap hashMap = new HashMap();
        for (n = 0; n < object.getAttributeCount(); ++n) {
            object2 = object.getAttributeName(n);
            hashMap.put(object2, (Object)object.getAttributeValue("", (String)object2));
        }
        object2 = null;
        n = 0;
        while (n == 0) {
            int n2 = object.next();
            if (n2 == 2) {
                String string6 = object.getName();
                String string7 = object.getNamespace();
                if (string6.equals((Object)"error")) {
                    object2 = PacketParserUtils.parseError((XmlPullParser)object);
                    continue;
                }
                iQ = new IQ();
                iQ.addExtension(PacketParserUtils.parsePacketExtension(string6, string7, (XmlPullParser)object));
                continue;
            }
            if (n2 != 3 || !object.getName().equals((Object)"iq")) continue;
            n = 1;
        }
        object = iQ;
        if (iQ == null) {
            if (IQ.Type.GET == type || IQ.Type.SET == type) {
                object = new IQ(){

                    @Override
                    public String getChildElementXML() {
                        return null;
                    }
                };
                object.setPacketID(string2);
                object.setTo(string4);
                object.setFrom(string3);
                object.setType(IQ.Type.ERROR);
                object.setChannelId(string5);
                object.setError(new XMPPError(XMPPError.Condition.feature_not_implemented));
                connection.sendPacket((Packet)object);
                MyLog.e("iq usage error. send packet in packet parser.");
                return null;
            }
            object = new IQ(){

                @Override
                public String getChildElementXML() {
                    return null;
                }
            };
        }
        object.setPacketID(string2);
        object.setTo(string3);
        object.setChannelId(string5);
        object.setFrom(string4);
        object.setType(type);
        object.setError((XMPPError)object2);
        object.setAttributes((Map<String, String>)hashMap);
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Packet parseMessage(XmlPullParser object) throws Exception {
        String string2;
        String string3;
        Message message;
        if (!"1".equals((Object)object.getAttributeValue("", "s"))) {
            message = new Message();
            string2 = string3 = object.getAttributeValue("", "id");
            if (string3 == null) {
                string2 = "ID_NOT_AVAILABLE";
            }
            message.setPacketID(string2);
            message.setTo(object.getAttributeValue("", "to"));
            message.setFrom(object.getAttributeValue("", "from"));
            message.setChannelId(object.getAttributeValue("", "chid"));
            message.setAppId(object.getAttributeValue("", "appid"));
            string2 = null;
            try {
                string2 = string3 = object.getAttributeValue("", "transient");
            }
            catch (Exception var5_15) {}
            try {
                string3 = object.getAttributeValue("", "seq");
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    message.setSeq(string3);
                }
            }
            catch (Exception var5_14) {}
            try {
                string3 = object.getAttributeValue("", "mseq");
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    message.setMSeq(string3);
                }
            }
            catch (Exception var5_13) {}
            try {
                string3 = object.getAttributeValue("", "fseq");
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    message.setFSeq(string3);
                }
            }
            catch (Exception var5_12) {}
            try {
                string3 = object.getAttributeValue("", "status");
                if (!TextUtils.isEmpty((CharSequence)string3)) {
                    message.setStatus(string3);
                }
            }
            catch (Exception var5_11) {}
            boolean bl = !TextUtils.isEmpty((CharSequence)string2) && string2.equalsIgnoreCase("true");
            message.setIsTransient(bl);
            message.setType(object.getAttributeValue("", "type"));
            string2 = PacketParserUtils.getLanguageAttribute((XmlPullParser)object);
            if (string2 != null && !"".equals((Object)string2.trim())) {
                message.setLanguage(string2);
            } else {
                string2 = Packet.getDefaultLanguage();
            }
        } else {
            Object object2;
            String string4 = object.getAttributeValue("", "chid");
            String string5 = object.getAttributeValue("", "id");
            String string6 = object.getAttributeValue("", "from");
            String string7 = object.getAttributeValue("", "to");
            String string8 = object.getAttributeValue("", "type");
            Object object3 = object2 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(string4, string7);
            if (object2 == null) {
                object3 = PushClientsManager.getInstance().getClientLoginInfoByChidAndUserId(string4, string6);
            }
            object2 = null;
            if (object3 == null) {
                throw new XMPPException("the channel id is wrong while receiving a encrypted message");
            }
            boolean bl = false;
            while (!bl) {
                int n = object.next();
                if (n == 2) {
                    if (!"s".equals((Object)object.getName())) {
                        throw new XMPPException("error while receiving a encrypted message with wrong format");
                    }
                    if (object.next() != 4) {
                        throw new XMPPException("error while receiving a encrypted message with wrong format");
                    }
                    object2 = object.getText();
                    if ("5".equals((Object)string4) || "6".equals((Object)string4)) {
                        object = new Message();
                        object.setChannelId(string4);
                        object.setEncrypted(true);
                        object.setFrom(string6);
                        object.setTo(string7);
                        object.setPacketID(string5);
                        object.setType(string8);
                        object3 = new CommonPacketExtension("s", null, (String[])null, (String[])null);
                        object3.setText((String)object2);
                        object.addExtension((CommonPacketExtension)object3);
                        return object;
                    }
                    PacketParserUtils.resetDecryptedMsgParser(RC4Cryption.decrypt(RC4Cryption.generateKeyForRC4(object3.security, string5), (String)object2));
                    sDecryptedMsgParser.next();
                    object2 = PacketParserUtils.parseMessage(sDecryptedMsgParser);
                    continue;
                }
                if (n != 3 || !object.getName().equals((Object)"message")) continue;
                bl = true;
            }
            if (object2 != null) {
                return object2;
            }
            throw new XMPPException("error while receiving a encrypted message with wrong format");
        }
        boolean bl = false;
        string2 = null;
        do {
            if (bl) {
                message.setThread(string2);
                return message;
            }
            int n = object.next();
            if (n == 2) {
                String string9;
                String string10 = object.getName();
                string3 = string9 = object.getNamespace();
                if (TextUtils.isEmpty((CharSequence)string9)) {
                    string3 = "xm";
                }
                if (string10.equals((Object)"subject")) {
                    if (PacketParserUtils.getLanguageAttribute((XmlPullParser)object) == null) {
                        // empty if block
                    }
                    message.setSubject(PacketParserUtils.parseContent((XmlPullParser)object));
                    continue;
                }
                if (string10.equals((Object)"body")) {
                    string3 = object.getAttributeValue("", "encode");
                    string9 = PacketParserUtils.parseContent((XmlPullParser)object);
                    if (!TextUtils.isEmpty((CharSequence)string3)) {
                        message.setBody(string9, string3);
                        continue;
                    }
                    message.setBody(string9);
                    continue;
                }
                if (string10.equals((Object)"thread")) {
                    if (string2 != null) continue;
                    string2 = object.nextText();
                    continue;
                }
                if (string10.equals((Object)"error")) {
                    message.setError(PacketParserUtils.parseError((XmlPullParser)object));
                    continue;
                }
                message.addExtension(PacketParserUtils.parsePacketExtension(string10, string3, (XmlPullParser)object));
                continue;
            }
            if (n != 3 || !object.getName().equals((Object)"message")) continue;
            bl = true;
        } while (true);
    }

    public static CommonPacketExtension parsePacketExtension(String object, String string2, XmlPullParser xmlPullParser) throws Exception {
        object = ProviderManager.getInstance().getExtensionProvider("all", "xm:chat");
        if (object != null && object instanceof CommonPacketExtensionProvider) {
            return ((CommonPacketExtensionProvider)object).parseExtension(xmlPullParser);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Presence parsePresence(XmlPullParser xmlPullParser) throws Exception {
        Object object = Presence.Type.available;
        Object object2 = xmlPullParser.getAttributeValue("", "type");
        Object object3 = object;
        if (object2 != null) {
            object3 = object;
            if (!object2.equals((Object)"")) {
                try {
                    object3 = Presence.Type.valueOf((String)object2);
                }
                catch (IllegalArgumentException var3_5) {
                    System.err.println("Found invalid presence type " + (String)object2);
                    object3 = object;
                }
            }
        }
        object2 = new Presence((Presence.Type)((Object)object3));
        object2.setTo(xmlPullParser.getAttributeValue("", "to"));
        object2.setFrom(xmlPullParser.getAttributeValue("", "from"));
        object2.setChannelId(xmlPullParser.getAttributeValue("", "chid"));
        object3 = object = xmlPullParser.getAttributeValue("", "id");
        if (object == null) {
            object3 = "ID_NOT_AVAILABLE";
        }
        object2.setPacketID((String)object3);
        boolean bl = false;
        do {
            if (bl) {
                return object2;
            }
            int n = xmlPullParser.next();
            if (n == 2) {
                object3 = xmlPullParser.getName();
                object = xmlPullParser.getNamespace();
                if (object3.equals((Object)"status")) {
                    object2.setStatus(xmlPullParser.nextText());
                    continue;
                }
                if (object3.equals((Object)"priority")) {
                    try {
                        object2.setPriority(Integer.parseInt((String)xmlPullParser.nextText()));
                    }
                    catch (NumberFormatException var3_6) {
                    }
                    catch (IllegalArgumentException var3_7) {
                        object2.setPriority(0);
                    }
                    continue;
                }
                if (object3.equals((Object)"show")) {
                    object3 = xmlPullParser.nextText();
                    try {
                        object2.setMode(Presence.Mode.valueOf((String)object3));
                    }
                    catch (IllegalArgumentException var4_2) {
                        System.err.println("Found invalid presence mode " + (String)object3);
                    }
                    continue;
                }
                if (object3.equals((Object)"error")) {
                    object2.setError(PacketParserUtils.parseError(xmlPullParser));
                    continue;
                }
                object2.addExtension(PacketParserUtils.parsePacketExtension((String)object3, (String)object, xmlPullParser));
                continue;
            }
            if (n == 3 && xmlPullParser.getName().equals((Object)"presence")) break;
        } while (true);
        return object2;
    }

    public static StreamError parseStreamError(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        StreamError streamError = null;
        boolean bl = false;
        while (!bl) {
            int n = xmlPullParser.next();
            if (n == 2) {
                streamError = new StreamError(xmlPullParser.getName());
                continue;
            }
            if (n != 3 || !xmlPullParser.getName().equals((Object)"error")) continue;
            bl = true;
        }
        return streamError;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void resetDecryptedMsgParser(byte[] arrby) throws XmlPullParserException {
        if (sDecryptedMsgParser == null) {
            try {
                sDecryptedMsgParser = XmlPullParserFactory.newInstance().newPullParser();
                sDecryptedMsgParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            }
            catch (XmlPullParserException var1_1) {
                var1_1.printStackTrace();
            }
        }
        sDecryptedMsgParser.setInput((Reader)new InputStreamReader((InputStream)new ByteArrayInputStream(arrby)));
    }

}

