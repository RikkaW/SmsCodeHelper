/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.HashMap
 *  java.util.Random
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.HashMap;
import java.util.Random;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public final class x {
    public static int a(String string2) {
        if (!StringUtils.isNull(string2)) {
            try {
                int n2 = Integer.parseInt((String)string2);
                return n2;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return 0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Element object, String string2) {
        object = object.getElementsByTagName(string2);
        if (object == null) return "";
        try {
            if (object.getLength() <= 0) return "";
            return x.a(object.item(0)).toString();
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
        }
        return "";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Node object) {
        if (object == null) return "";
        object = object.getFirstChild();
        if (object == null) return "";
        try {
            return object.getNodeValue().trim();
        }
        catch (Throwable var0_1) {
            // empty catch block
        }
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static HashMap<String, String> a(Document document) {
        int n2 = 0;
        if (document == null) {
            return null;
        }
        try {
            NodeList nodeList = document.getElementsByTagName("popu");
            if (nodeList == null) return null;
            if (nodeList.getLength() <= 0) return null;
            int n3 = nodeList.getLength();
            document = new HashMap();
            n3 = n3 > 1 ? new Random().nextInt(n3 * 3) % n3 : 0;
            nodeList = nodeList.item(n3).getChildNodes();
            n3 = n2;
            do {
                if (n3 >= nodeList.getLength()) {
                    return document;
                }
                Node node = nodeList.item(n3);
                document.put((Object)node.getNodeName(), (Object)x.a(node));
                ++n3;
            } while (true);
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static HashMap<String, String> a(Document var0, String var1_2) {
        if (var0 == null) {
            return null;
        }
        try {
            var0 = ViewUtil.getChannelType() == 7 ? var0.getFirstChild().getChildNodes() : var0.getElementsByTagName("title_" + var1_2);
            if (var0 == null) return null;
            if (var0.getLength() <= 0) return null;
            var4_3 = var0.getLength();
            var1_2 = new HashMap();
            var2_4 = 0;
            ** GOTO lbl23
        }
        catch (Throwable var0_1) {
            return null;
        }
lbl-1000: // 1 sources:
        {
            var5_6 = ((Element)var0.item(var2_4)).getElementsByTagName("popu");
            if (var5_6 != null && var5_6.getLength() > 0) {
                var3_5 = var5_6.getLength();
                var3_5 = var3_5 > 1 ? new Random().nextInt(var3_5 * 3) % var3_5 : 0;
                var5_6 = var5_6.item(var3_5).getChildNodes();
                for (var3_5 = 0; var3_5 < var5_6.getLength(); ++var3_5) {
                    var6_7 = var5_6.item(var3_5);
                    var1_2.put((Object)var6_7.getNodeName(), (Object)x.a(var6_7));
                }
            }
            ++var2_4;
lbl23: // 2 sources:
            ** while (var2_4 < var4_3)
        }
lbl24: // 1 sources:
        return var1_2;
    }
}

