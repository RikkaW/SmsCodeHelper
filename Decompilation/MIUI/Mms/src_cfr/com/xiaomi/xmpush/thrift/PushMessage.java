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
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class PushMessage
implements TBase<PushMessage, _Fields>,
Serializable,
Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField COLLAPSE_KEY_FIELD_DESC;
    private static final TField CREATE_AT_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PAYLOAD_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TO_FIELD_DESC;
    private static final TField TTL_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(2);
    public String appId;
    public String collapseKey;
    public long createAt;
    public String id;
    public String packageName;
    public String payload;
    public Target to;
    public long ttl;

    static {
        STRUCT_DESC = new TStruct("PushMessage");
        TO_FIELD_DESC = new TField("to", 12, 1);
        ID_FIELD_DESC = new TField("id", 11, 2);
        APP_ID_FIELD_DESC = new TField("appId", 11, 3);
        PAYLOAD_FIELD_DESC = new TField("payload", 11, 4);
        CREATE_AT_FIELD_DESC = new TField("createAt", 10, 5);
        TTL_FIELD_DESC = new TField("ttl", 10, 6);
        COLLAPSE_KEY_FIELD_DESC = new TField("collapseKey", 11, 7);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 8);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.TO, new FieldMetaData("to", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PAYLOAD, new FieldMetaData("payload", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CREATE_AT, new FieldMetaData("createAt", 2, new FieldValueMetaData(10)));
        enumMap.put(_Fields.TTL, new FieldMetaData("ttl", 2, new FieldValueMetaData(10)));
        enumMap.put(_Fields.COLLAPSE_KEY, new FieldMetaData("collapseKey", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(PushMessage.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(PushMessage pushMessage) {
        int n;
        if (!this.getClass().equals((Object)pushMessage.getClass())) {
            return this.getClass().getName().compareTo(pushMessage.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetTo()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetTo()));
        if (n != 0) return n2;
        if (this.isSetTo()) {
            n2 = n = TBaseHelper.compareTo(this.to, pushMessage.to);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, pushMessage.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, pushMessage.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPayload()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetPayload()));
        if (n != 0) return n2;
        if (this.isSetPayload()) {
            n2 = n = TBaseHelper.compareTo(this.payload, pushMessage.payload);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCreateAt()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetCreateAt()));
        if (n != 0) return n2;
        if (this.isSetCreateAt()) {
            n2 = n = TBaseHelper.compareTo(this.createAt, pushMessage.createAt);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTtl()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetTtl()));
        if (n != 0) return n2;
        if (this.isSetTtl()) {
            n2 = n = TBaseHelper.compareTo(this.ttl, pushMessage.ttl);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCollapseKey()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetCollapseKey()));
        if (n != 0) return n2;
        if (this.isSetCollapseKey()) {
            n2 = n = TBaseHelper.compareTo(this.collapseKey, pushMessage.collapseKey);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)pushMessage.isSetPackageName()));
        if (n != 0) return n2;
        if (!this.isSetPackageName()) return 0;
        n2 = n = TBaseHelper.compareTo(this.packageName, pushMessage.packageName);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(PushMessage pushMessage) {
        if (pushMessage == null) {
            return false;
        }
        boolean bl = this.isSetTo();
        boolean bl2 = pushMessage.isSetTo();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.to.equals(pushMessage.to)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = pushMessage.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)pushMessage.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = pushMessage.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)pushMessage.appId)) {
                return false;
            }
        }
        bl = this.isSetPayload();
        bl2 = pushMessage.isSetPayload();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.payload.equals((Object)pushMessage.payload)) {
                return false;
            }
        }
        bl = this.isSetCreateAt();
        bl2 = pushMessage.isSetCreateAt();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.createAt != pushMessage.createAt) {
                return false;
            }
        }
        bl = this.isSetTtl();
        bl2 = pushMessage.isSetTtl();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.ttl != pushMessage.ttl) {
                return false;
            }
        }
        bl = this.isSetCollapseKey();
        bl2 = pushMessage.isSetCollapseKey();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.collapseKey.equals((Object)pushMessage.collapseKey)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = pushMessage.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)pushMessage.packageName)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof PushMessage)) {
            return false;
        }
        return this.equals((PushMessage)object);
    }

    public String getAppId() {
        return this.appId;
    }

    public long getCreateAt() {
        return this.createAt;
    }

    public String getId() {
        return this.id;
    }

    public String getPayload() {
        return this.payload;
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

    public boolean isSetCollapseKey() {
        if (this.collapseKey != null) {
            return true;
        }
        return false;
    }

    public boolean isSetCreateAt() {
        return this.__isset_bit_vector.get(0);
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

    public boolean isSetTo() {
        if (this.to != null) {
            return true;
        }
        return false;
    }

    public boolean isSetTtl() {
        return this.__isset_bit_vector.get(1);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        do {
            TField tField = tProtocol.readFieldBegin();
            if (tField.type == 0) {
                tProtocol.readStructEnd();
                this.validate();
                return;
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 12) {
                        this.to = new Target();
                        this.to.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 11) {
                        this.id = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 11) {
                        this.appId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.payload = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 5: {
                    if (tField.type == 10) {
                        this.createAt = tProtocol.readI64();
                        this.setCreateAtIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 6: {
                    if (tField.type == 10) {
                        this.ttl = tProtocol.readI64();
                        this.setTtlIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 7: {
                    if (tField.type == 11) {
                        this.collapseKey = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 8: {
                    if (tField.type == 11) {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public void setCreateAtIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public void setTtlIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMessage(");
        boolean bl = true;
        if (this.isSetTo()) {
            stringBuilder.append("to:");
            if (this.to == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.to);
            }
            bl = false;
            if (!bl) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("id:");
        if (this.id == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.id);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("appId:");
        if (this.appId == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.appId);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("payload:");
        if (this.payload == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.payload);
        }
        if (this.isSetCreateAt()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("createAt:");
            stringBuilder.append(this.createAt);
        }
        if (this.isSetTtl()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("ttl:");
            stringBuilder.append(this.ttl);
        }
        if (this.isSetCollapseKey()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("collapseKey:");
            if (this.collapseKey == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.collapseKey);
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
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + this.toString());
        }
        if (this.appId == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + this.toString());
        }
        if (this.payload == null) {
            throw new TProtocolException("Required field 'payload' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.to != null && this.isSetTo()) {
            tProtocol.writeFieldBegin(TO_FIELD_DESC);
            this.to.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.id != null) {
            tProtocol.writeFieldBegin(ID_FIELD_DESC);
            tProtocol.writeString(this.id);
            tProtocol.writeFieldEnd();
        }
        if (this.appId != null) {
            tProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
            tProtocol.writeString(this.appId);
            tProtocol.writeFieldEnd();
        }
        if (this.payload != null) {
            tProtocol.writeFieldBegin(PAYLOAD_FIELD_DESC);
            tProtocol.writeString(this.payload);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetCreateAt()) {
            tProtocol.writeFieldBegin(CREATE_AT_FIELD_DESC);
            tProtocol.writeI64(this.createAt);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetTtl()) {
            tProtocol.writeFieldBegin(TTL_FIELD_DESC);
            tProtocol.writeI64(this.ttl);
            tProtocol.writeFieldEnd();
        }
        if (this.collapseKey != null && this.isSetCollapseKey()) {
            tProtocol.writeFieldBegin(COLLAPSE_KEY_FIELD_DESC);
            tProtocol.writeString(this.collapseKey);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && this.isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        TO(1, "to"),
        ID(2, "id"),
        APP_ID(3, "appId"),
        PAYLOAD(4, "payload"),
        CREATE_AT(5, "createAt"),
        TTL(6, "ttl"),
        COLLAPSE_KEY(7, "collapseKey"),
        PACKAGE_NAME(8, "packageName");
        
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

