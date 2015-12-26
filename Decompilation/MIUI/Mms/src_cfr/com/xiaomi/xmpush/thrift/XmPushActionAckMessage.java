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
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
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

public class XmPushActionAckMessage
implements TBase<XmPushActionAckMessage, _Fields>,
Serializable,
Cloneable {
    private static final TField ALIAS_NAME_FIELD_DESC;
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField MESSAGE_TS_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField REQUEST_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TOPIC_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(1);
    public String aliasName;
    public String appId;
    public String category;
    public String debug;
    public String id;
    public long messageTs;
    public String packageName;
    public XmPushActionSendMessage request;
    public Target target;
    public String topic;

    static {
        STRUCT_DESC = new TStruct("XmPushActionAckMessage");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        MESSAGE_TS_FIELD_DESC = new TField("messageTs", 10, 5);
        TOPIC_FIELD_DESC = new TField("topic", 11, 6);
        ALIAS_NAME_FIELD_DESC = new TField("aliasName", 11, 7);
        REQUEST_FIELD_DESC = new TField("request", 12, 8);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 9);
        CATEGORY_FIELD_DESC = new TField("category", 11, 10);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.MESSAGE_TS, new FieldMetaData("messageTs", 1, new FieldValueMetaData(10)));
        enumMap.put(_Fields.TOPIC, new FieldMetaData("topic", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.REQUEST, new FieldMetaData("request", 2, new StructMetaData(12, XmPushActionSendMessage.class)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionAckMessage.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionAckMessage xmPushActionAckMessage) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionAckMessage.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionAckMessage.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionAckMessage.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionAckMessage.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionAckMessage.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionAckMessage.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetMessageTs()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetMessageTs()));
        if (n != 0) return n2;
        if (this.isSetMessageTs()) {
            n2 = n = TBaseHelper.compareTo(this.messageTs, xmPushActionAckMessage.messageTs);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTopic()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetTopic()));
        if (n != 0) return n2;
        if (this.isSetTopic()) {
            n2 = n = TBaseHelper.compareTo(this.topic, xmPushActionAckMessage.topic);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAliasName()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetAliasName()));
        if (n != 0) return n2;
        if (this.isSetAliasName()) {
            n2 = n = TBaseHelper.compareTo(this.aliasName, xmPushActionAckMessage.aliasName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetRequest()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetRequest()));
        if (n != 0) return n2;
        if (this.isSetRequest()) {
            n2 = n = TBaseHelper.compareTo(this.request, xmPushActionAckMessage.request);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionAckMessage.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)xmPushActionAckMessage.isSetCategory()));
        if (n != 0) return n2;
        if (!this.isSetCategory()) return 0;
        n2 = n = TBaseHelper.compareTo(this.category, xmPushActionAckMessage.category);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionAckMessage xmPushActionAckMessage) {
        if (xmPushActionAckMessage == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionAckMessage.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionAckMessage.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionAckMessage.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionAckMessage.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionAckMessage.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionAckMessage.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionAckMessage.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionAckMessage.appId)) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.messageTs != xmPushActionAckMessage.messageTs) {
                return false;
            }
        }
        bl = this.isSetTopic();
        bl2 = xmPushActionAckMessage.isSetTopic();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.topic.equals((Object)xmPushActionAckMessage.topic)) {
                return false;
            }
        }
        bl = this.isSetAliasName();
        bl2 = xmPushActionAckMessage.isSetAliasName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.aliasName.equals((Object)xmPushActionAckMessage.aliasName)) {
                return false;
            }
        }
        bl = this.isSetRequest();
        bl2 = xmPushActionAckMessage.isSetRequest();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.request.equals(xmPushActionAckMessage.request)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionAckMessage.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionAckMessage.packageName)) {
                return false;
            }
        }
        bl = this.isSetCategory();
        bl2 = xmPushActionAckMessage.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)xmPushActionAckMessage.category)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionAckMessage)) {
            return false;
        }
        return this.equals((XmPushActionAckMessage)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetAliasName() {
        if (this.aliasName != null) {
            return true;
        }
        return false;
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

    public boolean isSetId() {
        if (this.id != null) {
            return true;
        }
        return false;
    }

    public boolean isSetMessageTs() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetPackageName() {
        if (this.packageName != null) {
            return true;
        }
        return false;
    }

    public boolean isSetRequest() {
        if (this.request != null) {
            return true;
        }
        return false;
    }

    public boolean isSetTarget() {
        if (this.target != null) {
            return true;
        }
        return false;
    }

    public boolean isSetTopic() {
        if (this.topic != null) {
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
            TField tField = tProtocol.readFieldBegin();
            if (tField.type == 0) {
                tProtocol.readStructEnd();
                if (this.isSetMessageTs()) break;
                throw new TProtocolException("Required field 'messageTs' was not found in serialized data! Struct: " + this.toString());
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 11) {
                        this.debug = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 12) {
                        this.target = new Target();
                        this.target.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 11) {
                        this.id = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.appId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 5: {
                    if (tField.type == 10) {
                        this.messageTs = tProtocol.readI64();
                        this.setMessageTsIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 6: {
                    if (tField.type == 11) {
                        this.topic = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 7: {
                    if (tField.type == 11) {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 8: {
                    if (tField.type == 12) {
                        this.request = new XmPushActionSendMessage();
                        this.request.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 9: {
                    if (tField.type == 11) {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 10: {
                    if (tField.type == 11) {
                        this.category = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        this.validate();
    }

    public XmPushActionAckMessage setAliasName(String string2) {
        this.aliasName = string2;
        return this;
    }

    public XmPushActionAckMessage setAppId(String string2) {
        this.appId = string2;
        return this;
    }

    public XmPushActionAckMessage setId(String string2) {
        this.id = string2;
        return this;
    }

    public XmPushActionAckMessage setMessageTs(long l) {
        this.messageTs = l;
        this.setMessageTsIsSet(true);
        return this;
    }

    public void setMessageTsIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public XmPushActionAckMessage setTopic(String string2) {
        this.topic = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block34 : {
            stringBuilder = new StringBuilder("XmPushActionAckMessage(");
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
                if (bl2) break block34;
            }
            stringBuilder.append(", ");
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
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.messageTs);
        if (this.isSetTopic()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("topic:");
            if (this.topic == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.topic);
            }
        }
        if (this.isSetAliasName()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("aliasName:");
            if (this.aliasName == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.aliasName);
            }
        }
        if (this.isSetRequest()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("request:");
            if (this.request == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.request);
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
        if (this.appId == null) {
            throw new TProtocolException("Required field 'appId' was not present! Struct: " + this.toString());
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
        if (this.appId != null) {
            tProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
            tProtocol.writeString(this.appId);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(MESSAGE_TS_FIELD_DESC);
        tProtocol.writeI64(this.messageTs);
        tProtocol.writeFieldEnd();
        if (this.topic != null && this.isSetTopic()) {
            tProtocol.writeFieldBegin(TOPIC_FIELD_DESC);
            tProtocol.writeString(this.topic);
            tProtocol.writeFieldEnd();
        }
        if (this.aliasName != null && this.isSetAliasName()) {
            tProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            tProtocol.writeString(this.aliasName);
            tProtocol.writeFieldEnd();
        }
        if (this.request != null && this.isSetRequest()) {
            tProtocol.writeFieldBegin(REQUEST_FIELD_DESC);
            this.request.write(tProtocol);
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
        MESSAGE_TS(5, "messageTs"),
        TOPIC(6, "topic"),
        ALIAS_NAME(7, "aliasName"),
        REQUEST(8, "request"),
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

