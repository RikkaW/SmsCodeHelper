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

import com.xiaomi.xmpush.thrift.PushMessage;
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

public class XmPushActionSendMessage
implements TBase<XmPushActionSendMessage, _Fields>,
Serializable,
Cloneable {
    private static final TField ALIAS_NAME_FIELD_DESC;
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField MESSAGE_FIELD_DESC;
    private static final TField NEED_ACK_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PARAMS_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TOPIC_FIELD_DESC;
    private static final TField USER_ACCOUNT_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(1);
    public String aliasName;
    public String appId;
    public String category;
    public String debug;
    public String id;
    public PushMessage message;
    public boolean needAck = true;
    public String packageName;
    public Map<String, String> params;
    public Target target;
    public String topic;
    public String userAccount;

    static {
        STRUCT_DESC = new TStruct("XmPushActionSendMessage");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 5);
        TOPIC_FIELD_DESC = new TField("topic", 11, 6);
        ALIAS_NAME_FIELD_DESC = new TField("aliasName", 11, 7);
        MESSAGE_FIELD_DESC = new TField("message", 12, 8);
        NEED_ACK_FIELD_DESC = new TField("needAck", 2, 9);
        PARAMS_FIELD_DESC = new TField("params", 13, 10);
        CATEGORY_FIELD_DESC = new TField("category", 11, 11);
        USER_ACCOUNT_FIELD_DESC = new TField("userAccount", 11, 12);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TOPIC, new FieldMetaData("topic", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.MESSAGE, new FieldMetaData("message", 2, new StructMetaData(12, PushMessage.class)));
        enumMap.put(_Fields.NEED_ACK, new FieldMetaData("needAck", 2, new FieldValueMetaData(2)));
        enumMap.put(_Fields.PARAMS, new FieldMetaData("params", 2, new MapMetaData(13, new FieldValueMetaData(11), new FieldValueMetaData(11))));
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.USER_ACCOUNT, new FieldMetaData("userAccount", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionSendMessage.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionSendMessage xmPushActionSendMessage) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionSendMessage.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionSendMessage.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionSendMessage.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionSendMessage.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionSendMessage.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionSendMessage.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionSendMessage.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTopic()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetTopic()));
        if (n != 0) return n2;
        if (this.isSetTopic()) {
            n2 = n = TBaseHelper.compareTo(this.topic, xmPushActionSendMessage.topic);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAliasName()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetAliasName()));
        if (n != 0) return n2;
        if (this.isSetAliasName()) {
            n2 = n = TBaseHelper.compareTo(this.aliasName, xmPushActionSendMessage.aliasName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetMessage()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetMessage()));
        if (n != 0) return n2;
        if (this.isSetMessage()) {
            n2 = n = TBaseHelper.compareTo(this.message, xmPushActionSendMessage.message);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetNeedAck()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetNeedAck()));
        if (n != 0) return n2;
        if (this.isSetNeedAck()) {
            n2 = n = TBaseHelper.compareTo(this.needAck, xmPushActionSendMessage.needAck);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetParams()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetParams()));
        if (n != 0) return n2;
        if (this.isSetParams()) {
            n2 = n = TBaseHelper.compareTo(this.params, xmPushActionSendMessage.params);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetCategory()));
        if (n != 0) return n2;
        if (this.isSetCategory()) {
            n2 = n = TBaseHelper.compareTo(this.category, xmPushActionSendMessage.category);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetUserAccount()).compareTo(Boolean.valueOf((boolean)xmPushActionSendMessage.isSetUserAccount()));
        if (n != 0) return n2;
        if (!this.isSetUserAccount()) return 0;
        n2 = n = TBaseHelper.compareTo(this.userAccount, xmPushActionSendMessage.userAccount);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionSendMessage xmPushActionSendMessage) {
        if (xmPushActionSendMessage == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionSendMessage.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionSendMessage.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionSendMessage.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionSendMessage.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionSendMessage.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionSendMessage.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionSendMessage.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionSendMessage.appId)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionSendMessage.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionSendMessage.packageName)) {
                return false;
            }
        }
        bl = this.isSetTopic();
        bl2 = xmPushActionSendMessage.isSetTopic();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.topic.equals((Object)xmPushActionSendMessage.topic)) {
                return false;
            }
        }
        bl = this.isSetAliasName();
        bl2 = xmPushActionSendMessage.isSetAliasName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.aliasName.equals((Object)xmPushActionSendMessage.aliasName)) {
                return false;
            }
        }
        bl = this.isSetMessage();
        bl2 = xmPushActionSendMessage.isSetMessage();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.message.equals(xmPushActionSendMessage.message)) {
                return false;
            }
        }
        bl = this.isSetNeedAck();
        bl2 = xmPushActionSendMessage.isSetNeedAck();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.needAck != xmPushActionSendMessage.needAck) {
                return false;
            }
        }
        bl = this.isSetParams();
        bl2 = xmPushActionSendMessage.isSetParams();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.params.equals(xmPushActionSendMessage.params)) {
                return false;
            }
        }
        bl = this.isSetCategory();
        bl2 = xmPushActionSendMessage.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)xmPushActionSendMessage.category)) {
                return false;
            }
        }
        bl = this.isSetUserAccount();
        bl2 = xmPushActionSendMessage.isSetUserAccount();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.userAccount.equals((Object)xmPushActionSendMessage.userAccount)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionSendMessage)) {
            return false;
        }
        return this.equals((XmPushActionSendMessage)object);
    }

    public String getAliasName() {
        return this.aliasName;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getCategory() {
        return this.category;
    }

    public String getId() {
        return this.id;
    }

    public PushMessage getMessage() {
        return this.message;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getUserAccount() {
        return this.userAccount;
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

    public boolean isSetMessage() {
        if (this.message != null) {
            return true;
        }
        return false;
    }

    public boolean isSetNeedAck() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetPackageName() {
        if (this.packageName != null) {
            return true;
        }
        return false;
    }

    public boolean isSetParams() {
        if (this.params != null) {
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

    public boolean isSetUserAccount() {
        if (this.userAccount != null) {
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
                this.validate();
                return;
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
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
                    if (object.type == 11) {
                        this.topic = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 7: {
                    if (object.type == 11) {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 8: {
                    if (object.type == 12) {
                        this.message = new PushMessage();
                        this.message.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 9: {
                    if (object.type == 2) {
                        this.needAck = tProtocol.readBool();
                        this.setNeedAckIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 10: {
                    if (object.type == 13) {
                        object = tProtocol.readMapBegin();
                        this.params = new HashMap(object.size * 2);
                        for (int i = 0; i < object.size; ++i) {
                            String string2 = tProtocol.readString();
                            String string3 = tProtocol.readString();
                            this.params.put(string2, string3);
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 11: {
                    if (object.type == 11) {
                        this.category = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 12: {
                    if (object.type == 11) {
                        this.userAccount = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public void setNeedAckIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block43 : {
            stringBuilder = new StringBuilder("XmPushActionSendMessage(");
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
                if (bl2) break block43;
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
        if (this.isSetMessage()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("message:");
            if (this.message == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.message);
            }
        }
        if (this.isSetNeedAck()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("needAck:");
            stringBuilder.append(this.needAck);
        }
        if (this.isSetParams()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("params:");
            if (this.params == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.params);
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
        if (this.isSetUserAccount()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("userAccount:");
            if (this.userAccount == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.userAccount);
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
        if (this.packageName != null && this.isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
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
        if (this.message != null && this.isSetMessage()) {
            tProtocol.writeFieldBegin(MESSAGE_FIELD_DESC);
            this.message.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetNeedAck()) {
            tProtocol.writeFieldBegin(NEED_ACK_FIELD_DESC);
            tProtocol.writeBool(this.needAck);
            tProtocol.writeFieldEnd();
        }
        if (this.params != null && this.isSetParams()) {
            tProtocol.writeFieldBegin(PARAMS_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap(11, 11, this.params.size()));
            for (Map.Entry<String, String> entry : this.params.entrySet()) {
                tProtocol.writeString((String)entry.getKey());
                tProtocol.writeString((String)entry.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.category != null && this.isSetCategory()) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        if (this.userAccount != null && this.isSetUserAccount()) {
            tProtocol.writeFieldBegin(USER_ACCOUNT_FIELD_DESC);
            tProtocol.writeString(this.userAccount);
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
        PACKAGE_NAME(5, "packageName"),
        TOPIC(6, "topic"),
        ALIAS_NAME(7, "aliasName"),
        MESSAGE(8, "message"),
        NEED_ACK(9, "needAck"),
        PARAMS(10, "params"),
        CATEGORY(11, "category"),
        USER_ACCOUNT(12, "userAccount");
        
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

