/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smack.packet.PacketExtension;
import com.xiaomi.smack.util.StringUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommonPacketExtension
implements PacketExtension {
    private String[] mAttributeNames = null;
    private String[] mAttributeValues = null;
    private List<CommonPacketExtension> mChildrenEles = null;
    private String mExtensionElementName;
    private String mNamespace;
    private String mText;

    public CommonPacketExtension(String string2, String string3, List<String> list, List<String> list2) {
        this.mExtensionElementName = string2;
        this.mNamespace = string3;
        this.mAttributeNames = list.toArray(new String[list.size()]);
        this.mAttributeValues = list2.toArray(new String[list2.size()]);
    }

    public CommonPacketExtension(String string2, String string3, String[] arrstring, String[] arrstring2) {
        this.mExtensionElementName = string2;
        this.mNamespace = string3;
        this.mAttributeNames = arrstring;
        this.mAttributeValues = arrstring2;
    }

    public CommonPacketExtension(String string2, String string3, String[] arrstring, String[] arrstring2, String string4, List<CommonPacketExtension> list) {
        this.mExtensionElementName = string2;
        this.mNamespace = string3;
        this.mAttributeNames = arrstring;
        this.mAttributeValues = arrstring2;
        this.mText = string4;
        this.mChildrenEles = list;
    }

    public static CommonPacketExtension parseFromBundle(Bundle bundle) {
        String string2 = bundle.getString("ext_ele_name");
        String string3 = bundle.getString("ext_ns");
        String string4 = bundle.getString("ext_text");
        Parcelable[] arrparcelable = bundle.getBundle("attributes");
        Object object = arrparcelable.keySet();
        String[] arrstring = new String[object.size()];
        String[] arrstring2 = new String[object.size()];
        Bundle bundle2 = null;
        int n = 0;
        object = object.iterator();
        while (object.hasNext()) {
            String string5;
            arrstring[n] = string5 = (String)object.next();
            arrstring2[n] = arrparcelable.getString(string5);
            ++n;
        }
        if (bundle.containsKey("children")) {
            arrparcelable = bundle.getParcelableArray("children");
            bundle = new ArrayList(arrparcelable.length);
            int n2 = arrparcelable.length;
            n = 0;
            do {
                bundle2 = bundle;
                if (n >= n2) break;
                bundle.add(CommonPacketExtension.parseFromBundle((Bundle)arrparcelable[n]));
                ++n;
            } while (true);
        }
        return new CommonPacketExtension(string2, string3, arrstring, arrstring2, string4, (List<CommonPacketExtension>)bundle2);
    }

    public static Parcelable[] toParcelableArray(List<CommonPacketExtension> list) {
        return CommonPacketExtension.toParcelableArray(list.toArray(new CommonPacketExtension[list.size()]));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Parcelable[] toParcelableArray(CommonPacketExtension[] arrcommonPacketExtension) {
        if (arrcommonPacketExtension == null) {
            return null;
        }
        Parcelable[] arrparcelable = new Parcelable[arrcommonPacketExtension.length];
        int n = 0;
        do {
            Parcelable[] arrparcelable2 = arrparcelable;
            if (n >= arrcommonPacketExtension.length) return arrparcelable2;
            arrparcelable[n] = arrcommonPacketExtension[n].toParcelable();
            ++n;
        } while (true);
    }

    public void appendChild(CommonPacketExtension commonPacketExtension) {
        if (this.mChildrenEles == null) {
            this.mChildrenEles = new ArrayList();
        }
        if (!this.mChildrenEles.contains(commonPacketExtension)) {
            this.mChildrenEles.add(commonPacketExtension);
        }
    }

    public String getAttributeValue(String string2) {
        if (string2 == null) {
            throw new IllegalArgumentException();
        }
        if (this.mAttributeNames != null) {
            for (int i = 0; i < this.mAttributeNames.length; ++i) {
                if (!string2.equals((Object)this.mAttributeNames[i])) continue;
                return this.mAttributeValues[i];
            }
        }
        return null;
    }

    public CommonPacketExtension getChildByName(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2) || this.mChildrenEles == null) {
            return null;
        }
        for (CommonPacketExtension commonPacketExtension : this.mChildrenEles) {
            if (!commonPacketExtension.mExtensionElementName.equals((Object)string2)) continue;
            return commonPacketExtension;
        }
        return null;
    }

    public String getElementName() {
        return this.mExtensionElementName;
    }

    public String getNamespace() {
        return this.mNamespace;
    }

    public String getText() {
        if (!TextUtils.isEmpty((CharSequence)this.mText)) {
            return StringUtils.unescapeFromXML(this.mText);
        }
        return this.mText;
    }

    public void setText(String string2) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.mText = StringUtils.escapeForXML(string2);
            return;
        }
        this.mText = string2;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.mExtensionElementName);
        bundle.putString("ext_ns", this.mNamespace);
        bundle.putString("ext_text", this.mText);
        Bundle bundle2 = new Bundle();
        if (this.mAttributeNames != null && this.mAttributeNames.length > 0) {
            for (int i = 0; i < this.mAttributeNames.length; ++i) {
                bundle2.putString(this.mAttributeNames[i], this.mAttributeValues[i]);
            }
        }
        bundle.putBundle("attributes", bundle2);
        if (this.mChildrenEles != null && this.mChildrenEles.size() > 0) {
            bundle.putParcelableArray("children", CommonPacketExtension.toParcelableArray(this.mChildrenEles));
        }
        return bundle;
    }

    public Parcelable toParcelable() {
        return this.toBundle();
    }

    public String toString() {
        return this.toXML();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(this.mExtensionElementName);
        if (!TextUtils.isEmpty((CharSequence)this.mNamespace)) {
            stringBuilder.append(" ").append("xmlns=").append("\"").append(this.mNamespace).append("\"");
        }
        if (this.mAttributeNames != null && this.mAttributeNames.length > 0) {
            for (int i = 0; i < this.mAttributeNames.length; ++i) {
                if (TextUtils.isEmpty((CharSequence)this.mAttributeValues[i])) continue;
                stringBuilder.append(" ").append(this.mAttributeNames[i]).append("=\"").append(StringUtils.escapeForXML(this.mAttributeValues[i])).append("\"");
            }
        }
        if (!TextUtils.isEmpty((CharSequence)this.mText)) {
            stringBuilder.append(">").append(this.mText).append("</").append(this.mExtensionElementName).append(">");
            do {
                return stringBuilder.toString();
                break;
            } while (true);
        }
        if (this.mChildrenEles != null && this.mChildrenEles.size() > 0) {
            stringBuilder.append(">");
            Iterator<CommonPacketExtension> iterator = this.mChildrenEles.iterator();
            while (iterator.hasNext()) {
                stringBuilder.append(iterator.next().toXML());
            }
            stringBuilder.append("</").append(this.mExtensionElementName).append(">");
            return stringBuilder.toString();
        }
        stringBuilder.append("/>");
        return stringBuilder.toString();
    }
}

