/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 *  java.util.Map$Entry
 */
package com.xiaomi.xmpush.thrift;

import com.xiaomi.xmpush.thrift.Target;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionNotification
implements TBase<XmPushActionNotification, _Fields>,
Serializable,
Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField EXTRA_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PAYLOAD_FIELD_DESC;
    private static final TField REQUIRE_ACK_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TYPE_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(1);
    public String appId;
    public String category;
    public String debug;
    public Map<String, String> extra;
    public String id;
    public String packageName;
    public String payload;
    public boolean requireAck = true;
    public Target target;
    public String type;

    static {
        STRUCT_DESC = new TStruct("XmPushActionNotification");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        TYPE_FIELD_DESC = new TField("type", 11, 5);
        REQUIRE_ACK_FIELD_DESC = new TField("requireAck", 2, 6);
        PAYLOAD_FIELD_DESC = new TField("payload", 11, 7);
        EXTRA_FIELD_DESC = new TField("extra", 13, 8);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 9);
        CATEGORY_FIELD_DESC = new TField("category", 11, 10);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TYPE, new FieldMetaData("type", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.REQUIRE_ACK, new FieldMetaData("requireAck", 1, new FieldValueMetaData(2)));
        enumMap.put(_Fields.PAYLOAD, new FieldMetaData("payload", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.EXTRA, new FieldMetaData("extra", 2, new MapMetaData(13, new FieldValueMetaData(11), new FieldValueMetaData(11))));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionNotification.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionNotification xmPushActionNotification) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionNotification.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionNotification.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionNotification.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionNotification.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionNotification.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionNotification.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetType()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetType()));
        if (n != 0) return n2;
        if (this.isSetType()) {
            n2 = n = TBaseHelper.compareTo(this.type, xmPushActionNotification.type);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetRequireAck()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetRequireAck()));
        if (n != 0) return n2;
        if (this.isSetRequireAck()) {
            n2 = n = TBaseHelper.compareTo(this.requireAck, xmPushActionNotification.requireAck);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPayload()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetPayload()));
        if (n != 0) return n2;
        if (this.isSetPayload()) {
            n2 = n = TBaseHelper.compareTo(this.payload, xmPushActionNotification.payload);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetExtra()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetExtra()));
        if (n != 0) return n2;
        if (this.isSetExtra()) {
            n2 = n = TBaseHelper.compareTo(this.extra, xmPushActionNotification.extra);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionNotification.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)xmPushActionNotification.isSetCategory()));
        if (n != 0) return n2;
        if (!this.isSetCategory()) return 0;
        n2 = n = TBaseHelper.compareTo(this.category, xmPushActionNotification.category);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionNotification xmPushActionNotification) {
        if (xmPushActionNotification == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionNotification.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionNotification.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionNotification.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionNotification.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionNotification.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionNotification.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionNotification.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionNotification.appId)) {
                return false;
            }
        }
        bl = this.isSetType();
        bl2 = xmPushActionNotification.isSetType();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.type.equals((Object)xmPushActionNotification.type)) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.requireAck != xmPushActionNotification.requireAck) {
                return false;
            }
        }
        bl = this.isSetPayload();
        bl2 = xmPushActionNotification.isSetPayload();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.payload.equals((Object)xmPushActionNotification.payload)) {
                return false;
            }
        }
        bl = this.isSetExtra();
        bl2 = xmPushActionNotification.isSetExtra();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.extra.equals(xmPushActionNotification.extra)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionNotification.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionNotification.packageName)) {
                return false;
            }
        }
        bl = this.isSetCategory();
        bl2 = xmPushActionNotification.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)xmPushActionNotification.category)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionNotification)) {
            return false;
        }
        return this.equals((XmPushActionNotification)object);
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAppId() {
        if (this.appId != null) {
            return true;
        }
        return false;
    }

    public boolean isSetCategory() {
        if (this.category != null) {
            return true;
        }
        return false;
    }

    public boolean isSetDebug() {
        if (this.debug != null) {
            return true;
        }
        return false;
    }

    public boolean isSetExtra() {
        if (this.extra != null) {
            return true;
        }
        return false;
    }

    public boolean isSetId() {
        if (this.id != null) {
            return true;
        }
        return false;
    }

    public boolean isSetPackageName() {
        if (this.packageName != null) {
            return true;
        }
        return false;
    }

    public boolean isSetPayload() {
        if (this.payload != null) {
            return true;
        }
        return false;
    }

    public boolean isSetRequireAck() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetTarget() {
        if (this.target != null) {
            return true;
        }
        return false;
    }

    public boolean isSetType() {
        if (this.type != null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        do {
            Object object = tProtocol.readFieldBegin();
            if (object.type == 0) {
                tProtocol.readStructEnd();
                if (this.isSetRequireAck()) break;
                throw new TProtocolException("Required field 'requireAck' was not found in serialized data! Struct: " + this.toString());
            }
            switch (object.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 1: {
                    if (object.type == 11) {
                        this.debug = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 2: {
                    if (object.type == 12) {
                        this.target = new Target();
                        this.target.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 3: {
                    if (object.type == 11) {
                        this.id = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 4: {
                    if (object.type == 11) {
                        this.appId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 5: {
                    if (object.type == 11) {
                        this.type = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
                    if (object.type == 2) {
                        this.requireAck = tProtocol.readBool();
                        this.setRequireAckIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 7: {
                    if (object.type == 11) {
                        this.payload = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 8: {
                    if (object.type == 13) {
                        object = tProtocol.readMapBegin();
                        this.extra = new HashMap(object.size * 2);
                        for (int i = 0; i < object.size; ++i) {
                            String string2 = tProtocol.readString();
                            String string3 = tProtocol.readString();
                            this.extra.put(string2, string3);
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 9: {
                    if (object.type == 11) {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 10: {
                    if (object.type == 11) {
                        this.category = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        this.validate();
    }

    public XmPushActionNotification setAppId(String string2) {
        this.appId = string2;
        return this;
    }

    public XmPushActionNotification setExtra(Map<String, String> map) {
        this.extra = map;
        return this;
    }

    public XmPushActionNotification setId(String string2) {
        this.id = string2;
        return this;
    }

    public XmPushActionNotification setRequireAck(boolean bl) {
        this.requireAck = bl;
        this.setRequireAckIsSet(true);
        return this;
    }

    public void setRequireAckIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public XmPushActionNotification setType(String string2) {
        this.type = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block35 : {
            stringBuilder = new StringBuilder("XmPushActionNotification(");
            boolean bl = true;
            if (this.isSetDebug()) {
                stringBuilder.append("debug:");
                if (this.debug == null) {
                    stringBuilder.append("null");
                } else {
                    stringBuilder.append(this.debug);
                }
                bl = false;
            }
            boolean bl2 = bl;
            if (this.isSetTarget()) {
                if (!bl) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append("target:");
                if (this.target == null) {
                    stringBuilder.append("null");
                } else {
                    stringBuilder.append(this.target);
                }
                bl2 = false;
                if (bl2) break block35;
            }
            stringBuilder.append(", ");
        }
        stringBuilder.append("id:");
        if (this.id == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.id);
        }
        if (this.isSetAppId()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("appId:");
            if (this.appId == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.appId);
            }
        }
        if (this.isSetType()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("type:");
            if (this.type == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.type);
            }
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("requireAck:");
        stringBuilder.append(this.requireAck);
        if (this.isSetPayload()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("payload:");
            if (this.payload == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.payload);
            }
        }
        if (this.isSetExtra()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("extra:");
            if (this.extra == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.extra);
            }
        }
        if (this.isSetPackageName()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("packageName:");
            if (this.packageName == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.packageName);
            }
        }
        if (this.isSetCategory()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("category:");
            if (this.category == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.category);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.debug != null && this.isSetDebug()) {
            tProtocol.writeFieldBegin(DEBUG_FIELD_DESC);
            tProtocol.writeString(this.debug);
            tProtocol.writeFieldEnd();
        }
        if (this.target != null && this.isSetTarget()) {
            tProtocol.writeFieldBegin(TARGET_FIELD_DESC);
            this.target.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.id != null) {
            tProtocol.writeFieldBegin(ID_FIELD_DESC);
            tProtocol.writeString(this.id);
            tProtocol.writeFieldEnd();
        }
        if (this.appId != null && this.isSetAppId()) {
            tProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
            tProtocol.writeString(this.appId);
            tProtocol.writeFieldEnd();
        }
        if (this.type != null && this.isSetType()) {
            tProtocol.writeFieldBegin(TYPE_FIELD_DESC);
            tProtocol.writeString(this.type);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(REQUIRE_ACK_FIELD_DESC);
        tProtocol.writeBool(this.requireAck);
        tProtocol.writeFieldEnd();
        if (this.payload != null && this.isSetPayload()) {
            tProtocol.writeFieldBegin(PAYLOAD_FIELD_DESC);
            tProtocol.writeString(this.payload);
            tProtocol.writeFieldEnd();
        }
        if (this.extra != null && this.isSetExtra()) {
            tProtocol.writeFieldBegin(EXTRA_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap(11, 11, this.extra.size()));
            for (Map.Entry<String, String> entry : this.extra.entrySet()) {
                tProtocol.writeString((String)entry.getKey());
                tProtocol.writeString((String)entry.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && this.isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.category != null && this.isSetCategory()) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        DEBUG(1, "debug"),
        TARGET(2, "target"),
        ID(3, "id"),
        APP_ID(4, "appId"),
        TYPE(5, "type"),
        REQUIRE_ACK(6, "requireAck"),
        PAYLOAD(7, "payload"),
        EXTRA(8, "extra"),
        PACKAGE_NAME(9, "packageName"),
        CATEGORY(10, "category");
        
        private static final Map<String, _Fields> byName;
        private final String _fieldName;
        private final short _thriftId;

        static {
            byName = new HashMap();
            for (_Fields _Fields2 : EnumSet.allOf((Class)_Fields.class)) {
                byName.put(_Fields2.getFieldName(), _Fields2);
            }
        }

        private _Fields(short s, String string3) {
            this._thriftId = s;
            this._fieldName = string3;
        }

        public String getFieldName() {
            return this._fieldName;
        }
    }

}

