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
package com.xiaomi.common.logger.thrift.mfs;

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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class PassportLandNodeInfo
implements TBase<PassportLandNodeInfo, _Fields>,
Serializable,
Cloneable {
    private static final TField EID_FIELD_DESC;
    private static final TField IP_FIELD_DESC;
    private static final TField RT_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(3);
    private int eid;
    private int ip;
    private int rt;

    static {
        STRUCT_DESC = new TStruct("PassportLandNodeInfo");
        IP_FIELD_DESC = new TField("ip", 8, 1);
        EID_FIELD_DESC = new TField("eid", 8, 2);
        RT_FIELD_DESC = new TField("rt", 8, 3);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.IP, new FieldMetaData("ip", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.EID, new FieldMetaData("eid", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.RT, new FieldMetaData("rt", 1, new FieldValueMetaData(8)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(PassportLandNodeInfo.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(PassportLandNodeInfo passportLandNodeInfo) {
        int n;
        if (!this.getClass().equals((Object)passportLandNodeInfo.getClass())) {
            return this.getClass().getName().compareTo(passportLandNodeInfo.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetIp()).compareTo(Boolean.valueOf((boolean)passportLandNodeInfo.isSetIp()));
        if (n != 0) return n2;
        if (this.isSetIp()) {
            n2 = n = TBaseHelper.compareTo(this.ip, passportLandNodeInfo.ip);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetEid()).compareTo(Boolean.valueOf((boolean)passportLandNodeInfo.isSetEid()));
        if (n != 0) return n2;
        if (this.isSetEid()) {
            n2 = n = TBaseHelper.compareTo(this.eid, passportLandNodeInfo.eid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetRt()).compareTo(Boolean.valueOf((boolean)passportLandNodeInfo.isSetRt()));
        if (n != 0) return n2;
        if (!this.isSetRt()) return 0;
        n2 = n = TBaseHelper.compareTo(this.rt, passportLandNodeInfo.rt);
        if (n != 0) return n2;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(PassportLandNodeInfo passportLandNodeInfo) {
        if (!(passportLandNodeInfo != null && (!true && !true || true && true && this.ip == passportLandNodeInfo.ip) && (!true && !true || true && true && this.eid == passportLandNodeInfo.eid) && (!true && !true || true && true && this.rt == passportLandNodeInfo.rt))) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof PassportLandNodeInfo)) {
            return false;
        }
        return this.equals((PassportLandNodeInfo)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetEid() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetIp() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetRt() {
        return this.__isset_bit_vector.get(2);
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
                if (this.isSetIp()) break;
                throw new TProtocolException("Required field 'ip' was not found in serialized data! Struct: " + this.toString());
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 8) {
                        this.ip = tProtocol.readI32();
                        this.setIpIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 8) {
                        this.eid = tProtocol.readI32();
                        this.setEidIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 8) {
                        this.rt = tProtocol.readI32();
                        this.setRtIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        if (!this.isSetEid()) {
            throw new TProtocolException("Required field 'eid' was not found in serialized data! Struct: " + this.toString());
        }
        if (!this.isSetRt()) {
            throw new TProtocolException("Required field 'rt' was not found in serialized data! Struct: " + this.toString());
        }
        this.validate();
    }

    public void setEidIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    public void setIpIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public void setRtIsSet(boolean bl) {
        this.__isset_bit_vector.set(2, bl);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PassportLandNodeInfo(");
        stringBuilder.append("ip:");
        stringBuilder.append(this.ip);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("eid:");
        stringBuilder.append(this.eid);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("rt:");
        stringBuilder.append(this.rt);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        tProtocol.writeFieldBegin(IP_FIELD_DESC);
        tProtocol.writeI32(this.ip);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(EID_FIELD_DESC);
        tProtocol.writeI32(this.eid);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(RT_FIELD_DESC);
        tProtocol.writeI32(this.rt);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        IP(1, "ip"),
        EID(2, "eid"),
        RT(3, "rt");
        
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

