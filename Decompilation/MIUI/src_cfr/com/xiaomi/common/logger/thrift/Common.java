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
package com.xiaomi.common.logger.thrift;

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
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class Common
implements TBase<Common, _Fields>,
Serializable,
Cloneable {
    private static final TField CLIENT_IP_FIELD_DESC;
    private static final TField SERVER_HOST_FIELD_DESC;
    private static final TField SERVER_IP_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TIME_FIELD_DESC;
    private static final TField UUID_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(1);
    public String clientIp = "";
    public String serverHost = "";
    public String serverIp = "";
    public String time = "";
    public long uuid = 0;

    static {
        STRUCT_DESC = new TStruct("Common");
        UUID_FIELD_DESC = new TField("uuid", 10, 1);
        TIME_FIELD_DESC = new TField("time", 11, 2);
        CLIENT_IP_FIELD_DESC = new TField("clientIp", 11, 3);
        SERVER_IP_FIELD_DESC = new TField("serverIp", 11, 4);
        SERVER_HOST_FIELD_DESC = new TField("serverHost", 11, 5);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.UUID, new FieldMetaData("uuid", 2, new FieldValueMetaData(10)));
        enumMap.put(_Fields.TIME, new FieldMetaData("time", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CLIENT_IP, new FieldMetaData("clientIp", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.SERVER_IP, new FieldMetaData("serverIp", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.SERVER_HOST, new FieldMetaData("serverHost", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(Common.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(Common common) {
        int n;
        if (!this.getClass().equals((Object)common.getClass())) {
            return this.getClass().getName().compareTo(common.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetUuid()).compareTo(Boolean.valueOf((boolean)common.isSetUuid()));
        if (n != 0) return n2;
        if (this.isSetUuid()) {
            n2 = n = TBaseHelper.compareTo(this.uuid, common.uuid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTime()).compareTo(Boolean.valueOf((boolean)common.isSetTime()));
        if (n != 0) return n2;
        if (this.isSetTime()) {
            n2 = n = TBaseHelper.compareTo(this.time, common.time);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetClientIp()).compareTo(Boolean.valueOf((boolean)common.isSetClientIp()));
        if (n != 0) return n2;
        if (this.isSetClientIp()) {
            n2 = n = TBaseHelper.compareTo(this.clientIp, common.clientIp);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetServerIp()).compareTo(Boolean.valueOf((boolean)common.isSetServerIp()));
        if (n != 0) return n2;
        if (this.isSetServerIp()) {
            n2 = n = TBaseHelper.compareTo(this.serverIp, common.serverIp);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetServerHost()).compareTo(Boolean.valueOf((boolean)common.isSetServerHost()));
        if (n != 0) return n2;
        if (!this.isSetServerHost()) return 0;
        n2 = n = TBaseHelper.compareTo(this.serverHost, common.serverHost);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(Common common) {
        if (common == null) {
            return false;
        }
        boolean bl = this.isSetUuid();
        boolean bl2 = common.isSetUuid();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.uuid != common.uuid) {
                return false;
            }
        }
        bl = this.isSetTime();
        bl2 = common.isSetTime();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.time.equals((Object)common.time)) {
                return false;
            }
        }
        bl = this.isSetClientIp();
        bl2 = common.isSetClientIp();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.clientIp.equals((Object)common.clientIp)) {
                return false;
            }
        }
        bl = this.isSetServerIp();
        bl2 = common.isSetServerIp();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.serverIp.equals((Object)common.serverIp)) {
                return false;
            }
        }
        bl = this.isSetServerHost();
        bl2 = common.isSetServerHost();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.serverHost.equals((Object)common.serverHost)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Common)) {
            return false;
        }
        return this.equals((Common)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetClientIp() {
        if (this.clientIp != null) {
            return true;
        }
        return false;
    }

    public boolean isSetServerHost() {
        if (this.serverHost != null) {
            return true;
        }
        return false;
    }

    public boolean isSetServerIp() {
        if (this.serverIp != null) {
            return true;
        }
        return false;
    }

    public boolean isSetTime() {
        if (this.time != null) {
            return true;
        }
        return false;
    }

    public boolean isSetUuid() {
        return this.__isset_bit_vector.get(0);
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
                    if (tField.type == 10) {
                        this.uuid = tProtocol.readI64();
                        this.setUuidIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 11) {
                        this.time = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 11) {
                        this.clientIp = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.serverIp = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 5: {
                    if (tField.type == 11) {
                        this.serverHost = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public void setUuidIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Common(");
        boolean bl = true;
        if (this.isSetUuid()) {
            stringBuilder.append("uuid:");
            stringBuilder.append(this.uuid);
            bl = false;
        }
        boolean bl2 = bl;
        if (this.isSetTime()) {
            if (!bl) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("time:");
            if (this.time == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.time);
            }
            bl2 = false;
        }
        bl = bl2;
        if (this.isSetClientIp()) {
            if (!bl2) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("clientIp:");
            if (this.clientIp == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.clientIp);
            }
            bl = false;
        }
        bl2 = bl;
        if (this.isSetServerIp()) {
            if (!bl) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("serverIp:");
            if (this.serverIp == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.serverIp);
            }
            bl2 = false;
        }
        if (this.isSetServerHost()) {
            if (!bl2) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("serverHost:");
            if (this.serverHost == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.serverHost);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.isSetUuid()) {
            tProtocol.writeFieldBegin(UUID_FIELD_DESC);
            tProtocol.writeI64(this.uuid);
            tProtocol.writeFieldEnd();
        }
        if (this.time != null && this.isSetTime()) {
            tProtocol.writeFieldBegin(TIME_FIELD_DESC);
            tProtocol.writeString(this.time);
            tProtocol.writeFieldEnd();
        }
        if (this.clientIp != null && this.isSetClientIp()) {
            tProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
            tProtocol.writeString(this.clientIp);
            tProtocol.writeFieldEnd();
        }
        if (this.serverIp != null && this.isSetServerIp()) {
            tProtocol.writeFieldBegin(SERVER_IP_FIELD_DESC);
            tProtocol.writeString(this.serverIp);
            tProtocol.writeFieldEnd();
        }
        if (this.serverHost != null && this.isSetServerHost()) {
            tProtocol.writeFieldBegin(SERVER_HOST_FIELD_DESC);
            tProtocol.writeString(this.serverHost);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        UUID(1, "uuid"),
        TIME(2, "time"),
        CLIENT_IP(3, "clientIp"),
        SERVER_IP(4, "serverIp"),
        SERVER_HOST(5, "serverHost");
        
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

