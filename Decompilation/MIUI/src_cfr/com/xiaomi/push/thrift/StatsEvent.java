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
package com.xiaomi.push.thrift;

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

public class StatsEvent
implements TBase<StatsEvent, _Fields>,
Serializable,
Cloneable {
    private static final TField ANNOTATION_FIELD_DESC;
    private static final TField CHID_FIELD_DESC;
    private static final TField CLIENT_IP_FIELD_DESC;
    private static final TField CONNPT_FIELD_DESC;
    private static final TField HOST_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField SUBVALUE_FIELD_DESC;
    private static final TField TIME_FIELD_DESC;
    private static final TField TYPE_FIELD_DESC;
    private static final TField USER_FIELD_DESC;
    private static final TField VALUE_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(6);
    public String annotation;
    public byte chid;
    public int clientIp;
    public String connpt;
    public String host;
    public int subvalue;
    public int time;
    public int type;
    public String user;
    public int value;

    static {
        STRUCT_DESC = new TStruct("StatsEvent");
        CHID_FIELD_DESC = new TField("chid", 3, 1);
        TYPE_FIELD_DESC = new TField("type", 8, 2);
        VALUE_FIELD_DESC = new TField("value", 8, 3);
        CONNPT_FIELD_DESC = new TField("connpt", 11, 4);
        HOST_FIELD_DESC = new TField("host", 11, 5);
        SUBVALUE_FIELD_DESC = new TField("subvalue", 8, 6);
        ANNOTATION_FIELD_DESC = new TField("annotation", 11, 7);
        USER_FIELD_DESC = new TField("user", 11, 8);
        TIME_FIELD_DESC = new TField("time", 8, 9);
        CLIENT_IP_FIELD_DESC = new TField("clientIp", 8, 10);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.CHID, new FieldMetaData("chid", 1, new FieldValueMetaData(3)));
        enumMap.put(_Fields.TYPE, new FieldMetaData("type", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.VALUE, new FieldMetaData("value", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.CONNPT, new FieldMetaData("connpt", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.HOST, new FieldMetaData("host", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.SUBVALUE, new FieldMetaData("subvalue", 2, new FieldValueMetaData(8)));
        enumMap.put(_Fields.ANNOTATION, new FieldMetaData("annotation", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.USER, new FieldMetaData("user", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TIME, new FieldMetaData("time", 2, new FieldValueMetaData(8)));
        enumMap.put(_Fields.CLIENT_IP, new FieldMetaData("clientIp", 2, new FieldValueMetaData(8)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(StatsEvent.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(StatsEvent statsEvent) {
        int n;
        if (!this.getClass().equals((Object)statsEvent.getClass())) {
            return this.getClass().getName().compareTo(statsEvent.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetChid()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetChid()));
        if (n != 0) return n2;
        if (this.isSetChid()) {
            n2 = n = TBaseHelper.compareTo(this.chid, statsEvent.chid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetType()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetType()));
        if (n != 0) return n2;
        if (this.isSetType()) {
            n2 = n = TBaseHelper.compareTo(this.type, statsEvent.type);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetValue()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetValue()));
        if (n != 0) return n2;
        if (this.isSetValue()) {
            n2 = n = TBaseHelper.compareTo(this.value, statsEvent.value);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetConnpt()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetConnpt()));
        if (n != 0) return n2;
        if (this.isSetConnpt()) {
            n2 = n = TBaseHelper.compareTo(this.connpt, statsEvent.connpt);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetHost()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetHost()));
        if (n != 0) return n2;
        if (this.isSetHost()) {
            n2 = n = TBaseHelper.compareTo(this.host, statsEvent.host);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetSubvalue()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetSubvalue()));
        if (n != 0) return n2;
        if (this.isSetSubvalue()) {
            n2 = n = TBaseHelper.compareTo(this.subvalue, statsEvent.subvalue);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAnnotation()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetAnnotation()));
        if (n != 0) return n2;
        if (this.isSetAnnotation()) {
            n2 = n = TBaseHelper.compareTo(this.annotation, statsEvent.annotation);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetUser()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetUser()));
        if (n != 0) return n2;
        if (this.isSetUser()) {
            n2 = n = TBaseHelper.compareTo(this.user, statsEvent.user);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTime()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetTime()));
        if (n != 0) return n2;
        if (this.isSetTime()) {
            n2 = n = TBaseHelper.compareTo(this.time, statsEvent.time);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetClientIp()).compareTo(Boolean.valueOf((boolean)statsEvent.isSetClientIp()));
        if (n != 0) return n2;
        if (!this.isSetClientIp()) return 0;
        n2 = n = TBaseHelper.compareTo(this.clientIp, statsEvent.clientIp);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(StatsEvent statsEvent) {
        if (statsEvent == null) {
            return false;
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.chid != statsEvent.chid) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.type != statsEvent.type) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.value != statsEvent.value) {
                return false;
            }
        }
        boolean bl = this.isSetConnpt();
        boolean bl2 = statsEvent.isSetConnpt();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.connpt.equals((Object)statsEvent.connpt)) {
                return false;
            }
        }
        bl = this.isSetHost();
        bl2 = statsEvent.isSetHost();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.host.equals((Object)statsEvent.host)) {
                return false;
            }
        }
        bl = this.isSetSubvalue();
        bl2 = statsEvent.isSetSubvalue();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.subvalue != statsEvent.subvalue) {
                return false;
            }
        }
        bl = this.isSetAnnotation();
        bl2 = statsEvent.isSetAnnotation();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.annotation.equals((Object)statsEvent.annotation)) {
                return false;
            }
        }
        bl = this.isSetUser();
        bl2 = statsEvent.isSetUser();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.user.equals((Object)statsEvent.user)) {
                return false;
            }
        }
        bl = this.isSetTime();
        bl2 = statsEvent.isSetTime();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.time != statsEvent.time) {
                return false;
            }
        }
        bl = this.isSetClientIp();
        bl2 = statsEvent.isSetClientIp();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.clientIp != statsEvent.clientIp) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof StatsEvent)) {
            return false;
        }
        return this.equals((StatsEvent)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAnnotation() {
        if (this.annotation != null) {
            return true;
        }
        return false;
    }

    public boolean isSetChid() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetClientIp() {
        return this.__isset_bit_vector.get(5);
    }

    public boolean isSetConnpt() {
        if (this.connpt != null) {
            return true;
        }
        return false;
    }

    public boolean isSetHost() {
        if (this.host != null) {
            return true;
        }
        return false;
    }

    public boolean isSetSubvalue() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetTime() {
        return this.__isset_bit_vector.get(4);
    }

    public boolean isSetType() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetUser() {
        if (this.user != null) {
            return true;
        }
        return false;
    }

    public boolean isSetValue() {
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
                if (this.isSetChid()) break;
                throw new TProtocolException("Required field 'chid' was not found in serialized data! Struct: " + this.toString());
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 3) {
                        this.chid = tProtocol.readByte();
                        this.setChidIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 8) {
                        this.type = tProtocol.readI32();
                        this.setTypeIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 8) {
                        this.value = tProtocol.readI32();
                        this.setValueIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.connpt = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 5: {
                    if (tField.type == 11) {
                        this.host = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 6: {
                    if (tField.type == 8) {
                        this.subvalue = tProtocol.readI32();
                        this.setSubvalueIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 7: {
                    if (tField.type == 11) {
                        this.annotation = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 8: {
                    if (tField.type == 11) {
                        this.user = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 9: {
                    if (tField.type == 8) {
                        this.time = tProtocol.readI32();
                        this.setTimeIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 10: {
                    if (tField.type == 8) {
                        this.clientIp = tProtocol.readI32();
                        this.setClientIpIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        if (!this.isSetType()) {
            throw new TProtocolException("Required field 'type' was not found in serialized data! Struct: " + this.toString());
        }
        if (!this.isSetValue()) {
            throw new TProtocolException("Required field 'value' was not found in serialized data! Struct: " + this.toString());
        }
        this.validate();
    }

    public StatsEvent setAnnotation(String string2) {
        this.annotation = string2;
        return this;
    }

    public StatsEvent setChid(byte by) {
        this.chid = by;
        this.setChidIsSet(true);
        return this;
    }

    public void setChidIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public StatsEvent setClientIp(int n) {
        this.clientIp = n;
        this.setClientIpIsSet(true);
        return this;
    }

    public void setClientIpIsSet(boolean bl) {
        this.__isset_bit_vector.set(5, bl);
    }

    public StatsEvent setConnpt(String string2) {
        this.connpt = string2;
        return this;
    }

    public StatsEvent setHost(String string2) {
        this.host = string2;
        return this;
    }

    public StatsEvent setSubvalue(int n) {
        this.subvalue = n;
        this.setSubvalueIsSet(true);
        return this;
    }

    public void setSubvalueIsSet(boolean bl) {
        this.__isset_bit_vector.set(3, bl);
    }

    public StatsEvent setTime(int n) {
        this.time = n;
        this.setTimeIsSet(true);
        return this;
    }

    public void setTimeIsSet(boolean bl) {
        this.__isset_bit_vector.set(4, bl);
    }

    public StatsEvent setType(int n) {
        this.type = n;
        this.setTypeIsSet(true);
        return this;
    }

    public void setTypeIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    public StatsEvent setUser(String string2) {
        this.user = string2;
        return this;
    }

    public StatsEvent setValue(int n) {
        this.value = n;
        this.setValueIsSet(true);
        return this;
    }

    public void setValueIsSet(boolean bl) {
        this.__isset_bit_vector.set(2, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StatsEvent(");
        stringBuilder.append("chid:");
        stringBuilder.append(this.chid);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("type:");
        stringBuilder.append(this.type);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("value:");
        stringBuilder.append(this.value);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("connpt:");
        if (this.connpt == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.connpt);
        }
        if (this.isSetHost()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("host:");
            if (this.host == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.host);
            }
        }
        if (this.isSetSubvalue()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("subvalue:");
            stringBuilder.append(this.subvalue);
        }
        if (this.isSetAnnotation()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("annotation:");
            if (this.annotation == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.annotation);
            }
        }
        if (this.isSetUser()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("user:");
            if (this.user == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.user);
            }
        }
        if (this.isSetTime()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("time:");
            stringBuilder.append(this.time);
        }
        if (this.isSetClientIp()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("clientIp:");
            stringBuilder.append(this.clientIp);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.connpt == null) {
            throw new TProtocolException("Required field 'connpt' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        tProtocol.writeFieldBegin(CHID_FIELD_DESC);
        tProtocol.writeByte(this.chid);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(TYPE_FIELD_DESC);
        tProtocol.writeI32(this.type);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(VALUE_FIELD_DESC);
        tProtocol.writeI32(this.value);
        tProtocol.writeFieldEnd();
        if (this.connpt != null) {
            tProtocol.writeFieldBegin(CONNPT_FIELD_DESC);
            tProtocol.writeString(this.connpt);
            tProtocol.writeFieldEnd();
        }
        if (this.host != null && this.isSetHost()) {
            tProtocol.writeFieldBegin(HOST_FIELD_DESC);
            tProtocol.writeString(this.host);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetSubvalue()) {
            tProtocol.writeFieldBegin(SUBVALUE_FIELD_DESC);
            tProtocol.writeI32(this.subvalue);
            tProtocol.writeFieldEnd();
        }
        if (this.annotation != null && this.isSetAnnotation()) {
            tProtocol.writeFieldBegin(ANNOTATION_FIELD_DESC);
            tProtocol.writeString(this.annotation);
            tProtocol.writeFieldEnd();
        }
        if (this.user != null && this.isSetUser()) {
            tProtocol.writeFieldBegin(USER_FIELD_DESC);
            tProtocol.writeString(this.user);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetTime()) {
            tProtocol.writeFieldBegin(TIME_FIELD_DESC);
            tProtocol.writeI32(this.time);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetClientIp()) {
            tProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
            tProtocol.writeI32(this.clientIp);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        CHID(1, "chid"),
        TYPE(2, "type"),
        VALUE(3, "value"),
        CONNPT(4, "connpt"),
        HOST(5, "host"),
        SUBVALUE(6, "subvalue"),
        ANNOTATION(7, "annotation"),
        USER(8, "user"),
        TIME(9, "time"),
        CLIENT_IP(10, "clientIp");
        
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

