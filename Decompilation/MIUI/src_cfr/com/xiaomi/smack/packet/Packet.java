/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.text.TextUtils
 *  java.io.ByteArrayOutputStream
 *  java.io.ObjectOutputStream
 *  java.io.OutputStream
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Double
 *  java.lang.Float
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.text.DateFormat
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.Locale
 *  java.util.TimeZone
 *  java.util.concurrent.CopyOnWriteArrayList
 */
package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.XMPPError;
import com.xiaomi.smack.util.StringUtils;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Packet {
    protected static final String DEFAULT_LANGUAGE = Locale.getDefault().getLanguage().toLowerCase();
    private static String DEFAULT_XML_NS = null;
    public static final DateFormat XEP_0082_UTC_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private static long id;
    private static String prefix;
    private String chId = null;
    private XMPPError error = null;
    private String from = null;
    private String packageName = null;
    private List<CommonPacketExtension> packetExtensions = new CopyOnWriteArrayList();
    private String packetID = null;
    private final Map<String, Object> properties = new HashMap();
    private String to = null;
    private String xmlns = DEFAULT_XML_NS;

    static {
        XEP_0082_UTC_FORMAT.setTimeZone(TimeZone.getTimeZone((String)"UTC"));
        prefix = StringUtils.randomString(5) + "-";
        id = 0;
    }

    public Packet() {
    }

    public Packet(Bundle bundle) {
        this.to = bundle.getString("ext_to");
        this.from = bundle.getString("ext_from");
        this.chId = bundle.getString("ext_chid");
        this.packetID = bundle.getString("ext_pkt_id");
        Parcelable[] arrparcelable = bundle.getParcelableArray("ext_exts");
        if (arrparcelable != null) {
            this.packetExtensions = new ArrayList(arrparcelable.length);
            int n = arrparcelable.length;
            for (int i = 0; i < n; ++i) {
                CommonPacketExtension commonPacketExtension = CommonPacketExtension.parseFromBundle((Bundle)arrparcelable[i]);
                if (commonPacketExtension == null) continue;
                this.packetExtensions.add(commonPacketExtension);
            }
        }
        if ((bundle = bundle.getBundle("ext_ERROR")) != null) {
            this.error = new XMPPError(bundle);
        }
    }

    public static String getDefaultLanguage() {
        return DEFAULT_LANGUAGE;
    }

    public static String nextID() {
        synchronized (Packet.class) {
            CharSequence charSequence = new StringBuilder().append(prefix);
            long l = id;
            id = 1 + l;
            charSequence = charSequence.append(Long.toString((long)l)).toString();
            return charSequence;
        }
    }

    public void addExtension(CommonPacketExtension commonPacketExtension) {
        this.packetExtensions.add(commonPacketExtension);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        boolean bl = true;
        boolean bl2 = false;
        if (this == object) {
            return true;
        }
        boolean bl3 = bl2;
        if (object == null) return bl3;
        bl3 = bl2;
        if (this.getClass() != object.getClass()) return bl3;
        object = (Packet)object;
        if (this.error != null) {
            bl3 = bl2;
            if (!this.error.equals((Object)object.error)) return bl3;
        } else if (object.error != null) {
            return false;
        }
        if (this.from != null) {
            bl3 = bl2;
            if (!this.from.equals((Object)object.from)) return bl3;
        } else if (object.from != null) {
            return false;
        }
        bl3 = bl2;
        if (!this.packetExtensions.equals(object.packetExtensions)) return bl3;
        if (this.packetID != null) {
            bl3 = bl2;
            if (!this.packetID.equals((Object)object.packetID)) return bl3;
        } else if (object.packetID != null) {
            return false;
        }
        if (this.chId != null) {
            bl3 = bl2;
            if (!this.chId.equals((Object)object.chId)) return bl3;
        } else if (object.chId != null) {
            return false;
        }
        if (this.properties != null) {
            bl3 = bl2;
            if (!this.properties.equals(object.properties)) return bl3;
        } else if (object.properties != null) {
            return false;
        }
        if (this.to != null) {
            bl3 = bl2;
            if (!this.to.equals((Object)object.to)) return bl3;
        } else if (object.to != null) {
            return false;
        }
        if (this.xmlns == null) {
            if (object.xmlns != null) return false;
            return bl;
        }
        bl3 = bl;
        if (this.xmlns.equals((Object)object.xmlns)) return bl3;
        return false;
    }

    public String getChannelId() {
        return this.chId;
    }

    public XMPPError getError() {
        return this.error;
    }

    public CommonPacketExtension getExtension(String string2) {
        return this.getExtension(string2, null);
    }

    public CommonPacketExtension getExtension(String string2, String string3) {
        for (CommonPacketExtension commonPacketExtension : this.packetExtensions) {
            if (string3 != null && !string3.equals((Object)commonPacketExtension.getNamespace()) || !string2.equals((Object)commonPacketExtension.getElementName())) continue;
            return commonPacketExtension;
        }
        return null;
    }

    public Collection<CommonPacketExtension> getExtensions() {
        synchronized (this) {
            List list;
            if (this.packetExtensions == null) {
                list = Collections.emptyList();
                return list;
            }
            list = Collections.unmodifiableList((List)new ArrayList(this.packetExtensions));
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected String getExtensionsXML() {
        // MONITORENTER : this
        var7_1 = new StringBuilder();
        var1_2 = this.getExtensions().iterator();
        while (var1_2.hasNext()) {
            var7_1.append(var1_2.next().toXML());
        }
        if (this.properties == null || this.properties.isEmpty()) ** GOTO lbl58
        var7_1.append("<properties xmlns=\"http://www.jivesoftware.com/xmlns/xmpp/properties\">");
        var8_6 = this.getPropertyNames().iterator();
        do {
            if (!var8_6.hasNext()) ** GOTO lbl57
            var1_2 = var8_6.next();
            var9_21 = this.getProperty((String)var1_2);
            var7_1.append("<property>");
            var7_1.append("<name>").append(StringUtils.escapeForXML((String)var1_2)).append("</name>");
            var7_1.append("<value type=\"");
            if (!(var9_21 instanceof Integer)) ** GOTO lbl20
            var7_1.append("integer\">").append(var9_21).append("</value>");
            ** GOTO lbl102
lbl20: // 1 sources:
            if (!(var9_21 instanceof Long)) ** GOTO lbl23
            var7_1.append("long\">").append(var9_21).append("</value>");
            ** GOTO lbl102
lbl23: // 1 sources:
            if (!(var9_21 instanceof Float)) ** GOTO lbl26
            var7_1.append("float\">").append(var9_21).append("</value>");
            ** GOTO lbl102
lbl26: // 1 sources:
            if (!(var9_21 instanceof Double)) ** GOTO lbl29
            var7_1.append("double\">").append(var9_21).append("</value>");
            ** GOTO lbl102
lbl29: // 1 sources:
            if (!(var9_21 instanceof Boolean)) ** GOTO lbl32
            var7_1.append("boolean\">").append(var9_21).append("</value>");
            ** GOTO lbl102
lbl32: // 1 sources:
            if (!(var9_21 instanceof String)) ** GOTO lbl37
            var7_1.append("string\">");
            var7_1.append(StringUtils.escapeForXML((String)var9_21));
            var7_1.append("</value>");
            ** GOTO lbl102
lbl37: // 1 sources:
            var2_7 = null;
            var6_20 = null;
            var3_11 = null;
            var4_13 = null;
            var5_18 = null;
            var1_2 = new ByteArrayOutputStream();
            var2_7 = new ObjectOutputStream((OutputStream)var1_2);
            var2_7.writeObject(var9_21);
            var7_1.append("java-object\">");
            var7_1.append(StringUtils.encodeBase64(var1_2.toByteArray())).append("</value>");
            ** if (var2_7 == null) goto lbl53
lbl-1000: // 1 sources:
            {
                var2_7.close();
            }
lbl53: // 2 sources:
            ** GOTO lbl62
            catch (Exception var4_14) {
                block34 : {
                    var1_2 = var6_20;
                    ** GOTO lbl82
lbl57: // 1 sources:
                    var7_1.append("</properties>");
lbl58: // 2 sources:
                    var1_2 = var7_1.toString();
                    // MONITOREXIT : this
                    return var1_2;
                    catch (Exception var2_8) {}
lbl62: // 2 sources:
                    if (var1_2 != null) {
                        try {
                            var1_2.close();
                        }
                        catch (Exception var1_3) {}
                    }
                    ** GOTO lbl102
                    catch (Throwable var5_19) {
                        var2_7 = var1_2;
                        var3_11 = var4_13;
                        var1_2 = var5_19;
                        ** GOTO lbl-1000
                    }
                    catch (Throwable var4_15) {
                        var3_11 = var2_7;
                        var2_7 = var1_2;
                        var1_2 = var4_15;
                        ** GOTO lbl-1000
                    }
                    catch (Exception var4_16) {
                        break block34;
                    }
                    catch (Exception var4_17) {
                        var5_18 = var2_7;
                    }
                }
                var2_7 = var1_2;
                var3_11 = var5_18;
                try {
                    var4_13.printStackTrace();
                    ** if (var5_18 == null) goto lbl90
                }
                catch (Throwable var1_5) lbl-1000: // 3 sources:
                {
                    if (var3_11 != null) {
                        var3_11.close();
                    }
                    ** GOTO lbl105
                    catch (Exception var2_9) {}
lbl97: // 2 sources:
                    if (var1_2 != null) {
                        try {
                            var1_2.close();
                        }
                        catch (Exception var1_4) {}
                    }
lbl102: // 12 sources:
                    var7_1.append("</property>");
                    continue;
                    catch (Exception var3_12) {}
lbl105: // 2 sources:
                    if (var2_7 == null) throw var1_2;
                    try {
                        var2_7.close();
                    }
                    catch (Exception var2_10) {
                        throw var1_2;
                    }
                    throw var1_2;
                }
lbl-1000: // 1 sources:
                {
                    var5_18.close();
                }
lbl90: // 2 sources:
                ** GOTO lbl97
            }
            break;
        } while (true);
    }

    public String getFrom() {
        return this.from;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getPacketID() {
        if ("ID_NOT_AVAILABLE".equals((Object)this.packetID)) {
            return null;
        }
        if (this.packetID == null) {
            this.packetID = Packet.nextID();
        }
        return this.packetID;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Object getProperty(String object) {
        synchronized (this) {
            block6 : {
                Map<String, Object> map = this.properties;
                if (map != null) break block6;
                return null;
            }
            object = this.properties.get(object);
            return object;
        }
    }

    public Collection<String> getPropertyNames() {
        synchronized (this) {
            Set set;
            if (this.properties == null) {
                set = Collections.emptySet();
                return set;
            }
            set = Collections.unmodifiableSet((Set)new HashSet(this.properties.keySet()));
        }
    }

    public String getTo() {
        return this.to;
    }

    public String getXmlns() {
        return this.xmlns;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        int n2 = this.xmlns != null ? this.xmlns.hashCode() : 0;
        int n3 = this.packetID != null ? this.packetID.hashCode() : 0;
        int n4 = this.to != null ? this.to.hashCode() : 0;
        int n5 = this.from != null ? this.from.hashCode() : 0;
        int n6 = this.chId != null ? this.chId.hashCode() : 0;
        int n7 = this.packetExtensions.hashCode();
        int n8 = this.properties.hashCode();
        if (this.error != null) {
            n = this.error.hashCode();
        }
        return ((((((n2 * 31 + n3) * 31 + n4) * 31 + n5) * 31 + n6) * 31 + n7) * 31 + n8) * 31 + n;
    }

    public void setChannelId(String string2) {
        this.chId = string2;
    }

    public void setError(XMPPError xMPPError) {
        this.error = xMPPError;
    }

    public void setFrom(String string2) {
        this.from = string2;
    }

    public void setPackageName(String string2) {
        this.packageName = string2;
    }

    public void setPacketID(String string2) {
        this.packetID = string2;
    }

    public void setTo(String string2) {
        this.to = string2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty((CharSequence)this.xmlns)) {
            bundle.putString("ext_ns", this.xmlns);
        }
        if (!TextUtils.isEmpty((CharSequence)this.from)) {
            bundle.putString("ext_from", this.from);
        }
        if (!TextUtils.isEmpty((CharSequence)this.to)) {
            bundle.putString("ext_to", this.to);
        }
        if (!TextUtils.isEmpty((CharSequence)this.packetID)) {
            bundle.putString("ext_pkt_id", this.packetID);
        }
        if (!TextUtils.isEmpty((CharSequence)this.chId)) {
            bundle.putString("ext_chid", this.chId);
        }
        if (this.error != null) {
            bundle.putBundle("ext_ERROR", this.error.toBundle());
        }
        if (this.packetExtensions != null) {
            Bundle[] arrbundle = new Bundle[this.packetExtensions.size()];
            int n = 0;
            Iterator<CommonPacketExtension> iterator = this.packetExtensions.iterator();
            while (iterator.hasNext()) {
                Bundle bundle2 = iterator.next().toBundle();
                if (bundle2 == null) continue;
                arrbundle[n] = bundle2;
                ++n;
            }
            bundle.putParcelableArray("ext_exts", (Parcelable[])arrbundle);
        }
        return bundle;
    }

    public abstract String toXML();
}

