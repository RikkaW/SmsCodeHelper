/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.xmpush.thrift;

import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.PushMetaInfo;
import com.xiaomi.xmpush.thrift.Target;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.EnumMetaData;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionContainer
implements TBase<XmPushActionContainer, _Fields>,
Serializable,
Cloneable {
    private static final TField ACTION_FIELD_DESC;
    private static final TField APPID_FIELD_DESC;
    private static final TField ENCRYPT_ACTION_FIELD_DESC;
    private static final TField IS_REQUEST_FIELD_DESC;
    private static final TField META_INFO_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField PUSH_ACTION_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(2);
    public ActionType action;
    public String appid;
    public boolean encryptAction = true;
    public boolean isRequest = true;
    public PushMetaInfo metaInfo;
    public String packageName;
    public ByteBuffer pushAction;
    public Target target;

    static {
        STRUCT_DESC = new TStruct("XmPushActionContainer");
        ACTION_FIELD_DESC = new TField("action", 8, 1);
        ENCRYPT_ACTION_FIELD_DESC = new TField("encryptAction", 2, 2);
        IS_REQUEST_FIELD_DESC = new TField("isRequest", 2, 3);
        PUSH_ACTION_FIELD_DESC = new TField("pushAction", 11, 4);
        APPID_FIELD_DESC = new TField("appid", 11, 5);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 6);
        TARGET_FIELD_DESC = new TField("target", 12, 7);
        META_INFO_FIELD_DESC = new TField("metaInfo", 12, 8);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.ACTION, new FieldMetaData("action", 1, new EnumMetaData(16, ActionType.class)));
        enumMap.put(_Fields.ENCRYPT_ACTION, new FieldMetaData("encryptAction", 1, new FieldValueMetaData(2)));
        enumMap.put(_Fields.IS_REQUEST, new FieldMetaData("isRequest", 1, new FieldValueMetaData(2)));
        enumMap.put(_Fields.PUSH_ACTION, new FieldMetaData("pushAction", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APPID, new FieldMetaData("appid", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 1, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.META_INFO, new FieldMetaData("metaInfo", 2, new StructMetaData(12, PushMetaInfo.class)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionContainer.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionContainer xmPushActionContainer) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionContainer.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionContainer.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetAction()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetAction()));
        if (n != 0) return n2;
        if (this.isSetAction()) {
            n2 = n = TBaseHelper.compareTo((Comparable)((Object)this.action), (Comparable)((Object)xmPushActionContainer.action));
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetEncryptAction()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetEncryptAction()));
        if (n != 0) return n2;
        if (this.isSetEncryptAction()) {
            n2 = n = TBaseHelper.compareTo(this.encryptAction, xmPushActionContainer.encryptAction);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetIsRequest()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetIsRequest()));
        if (n != 0) return n2;
        if (this.isSetIsRequest()) {
            n2 = n = TBaseHelper.compareTo(this.isRequest, xmPushActionContainer.isRequest);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPushAction()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetPushAction()));
        if (n != 0) return n2;
        if (this.isSetPushAction()) {
            n2 = n = TBaseHelper.compareTo((Comparable)this.pushAction, (Comparable)xmPushActionContainer.pushAction);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppid()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetAppid()));
        if (n != 0) return n2;
        if (this.isSetAppid()) {
            n2 = n = TBaseHelper.compareTo(this.appid, xmPushActionContainer.appid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionContainer.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionContainer.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetMetaInfo()).compareTo(Boolean.valueOf((boolean)xmPushActionContainer.isSetMetaInfo()));
        if (n != 0) return n2;
        if (!this.isSetMetaInfo()) return 0;
        n2 = n = TBaseHelper.compareTo(this.metaInfo, xmPushActionContainer.metaInfo);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionContainer xmPushActionContainer) {
        if (xmPushActionContainer == null) {
            return false;
        }
        boolean bl = this.isSetAction();
        boolean bl2 = xmPushActionContainer.isSetAction();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.action.equals((Object)xmPushActionContainer.action)) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.encryptAction != xmPushActionContainer.encryptAction) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.isRequest != xmPushActionContainer.isRequest) {
                return false;
            }
        }
        bl = this.isSetPushAction();
        bl2 = xmPushActionContainer.isSetPushAction();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.pushAction.equals((Object)xmPushActionContainer.pushAction)) {
                return false;
            }
        }
        bl = this.isSetAppid();
        bl2 = xmPushActionContainer.isSetAppid();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appid.equals((Object)xmPushActionContainer.appid)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionContainer.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionContainer.packageName)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionContainer.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionContainer.target)) {
                return false;
            }
        }
        bl = this.isSetMetaInfo();
        bl2 = xmPushActionContainer.isSetMetaInfo();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.metaInfo.equals(xmPushActionContainer.metaInfo)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionContainer)) {
            return false;
        }
        return this.equals((XmPushActionContainer)object);
    }

    public ActionType getAction() {
        return this.action;
    }

    public String getAppid() {
        return this.appid;
    }

    public PushMetaInfo getMetaInfo() {
        return this.metaInfo;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public byte[] getPushAction() {
        this.setPushAction(TBaseHelper.rightSize(this.pushAction));
        return this.pushAction.array();
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEncryptAction() {
        return this.encryptAction;
    }

    public boolean isSetAction() {
        if (this.action != null) {
            return true;
        }
        return false;
    }

    public boolean isSetAppid() {
        if (this.appid != null) {
            return true;
        }
        return false;
    }

    public boolean isSetEncryptAction() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetIsRequest() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetMetaInfo() {
        if (this.metaInfo != null) {
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

    public boolean isSetPushAction() {
        if (this.pushAction != null) {
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
                if (this.isSetEncryptAction()) break;
                throw new TProtocolException("Required field 'encryptAction' was not found in serialized data! Struct: " + this.toString());
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 8) {
                        this.action = ActionType.findByValue(tProtocol.readI32());
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 2) {
                        this.encryptAction = tProtocol.readBool();
                        this.setEncryptActionIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 2) {
                        this.isRequest = tProtocol.readBool();
                        this.setIsRequestIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.pushAction = tProtocol.readBinary();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 5: {
                    if (tField.type == 11) {
                        this.appid = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 6: {
                    if (tField.type == 11) {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 7: {
                    if (tField.type == 12) {
                        this.target = new Target();
                        this.target.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 8: {
                    if (tField.type == 12) {
                        this.metaInfo = new PushMetaInfo();
                        this.metaInfo.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        if (!this.isSetIsRequest()) {
            throw new TProtocolException("Required field 'isRequest' was not found in serialized data! Struct: " + this.toString());
        }
        this.validate();
    }

    public XmPushActionContainer setAction(ActionType actionType) {
        this.action = actionType;
        return this;
    }

    public XmPushActionContainer setAppid(String string2) {
        this.appid = string2;
        return this;
    }

    public XmPushActionContainer setEncryptAction(boolean bl) {
        this.encryptAction = bl;
        this.setEncryptActionIsSet(true);
        return this;
    }

    public void setEncryptActionIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public XmPushActionContainer setIsRequest(boolean bl) {
        this.isRequest = bl;
        this.setIsRequestIsSet(true);
        return this;
    }

    public void setIsRequestIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    public XmPushActionContainer setMetaInfo(PushMetaInfo pushMetaInfo) {
        this.metaInfo = pushMetaInfo;
        return this;
    }

    public XmPushActionContainer setPackageName(String string2) {
        this.packageName = string2;
        return this;
    }

    public XmPushActionContainer setPushAction(ByteBuffer byteBuffer) {
        this.pushAction = byteBuffer;
        return this;
    }

    public XmPushActionContainer setTarget(Target target) {
        this.target = target;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionContainer(");
        stringBuilder.append("action:");
        if (this.action == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append((Object)this.action);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("encryptAction:");
        stringBuilder.append(this.encryptAction);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("isRequest:");
        stringBuilder.append(this.isRequest);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("pushAction:");
        if (this.pushAction == null) {
            stringBuilder.append("null");
        } else {
            TBaseHelper.toString(this.pushAction, stringBuilder);
        }
        if (this.isSetAppid()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("appid:");
            if (this.appid == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.appid);
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
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("target:");
        if (this.target == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.target);
        }
        if (this.isSetMetaInfo()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("metaInfo:");
            if (this.metaInfo == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.metaInfo);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.action == null) {
            throw new TProtocolException("Required field 'action' was not present! Struct: " + this.toString());
        }
        if (this.pushAction == null) {
            throw new TProtocolException("Required field 'pushAction' was not present! Struct: " + this.toString());
        }
        if (this.target == null) {
            throw new TProtocolException("Required field 'target' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.action != null) {
            tProtocol.writeFieldBegin(ACTION_FIELD_DESC);
            tProtocol.writeI32(this.action.getValue());
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(ENCRYPT_ACTION_FIELD_DESC);
        tProtocol.writeBool(this.encryptAction);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(IS_REQUEST_FIELD_DESC);
        tProtocol.writeBool(this.isRequest);
        tProtocol.writeFieldEnd();
        if (this.pushAction != null) {
            tProtocol.writeFieldBegin(PUSH_ACTION_FIELD_DESC);
            tProtocol.writeBinary(this.pushAction);
            tProtocol.writeFieldEnd();
        }
        if (this.appid != null && this.isSetAppid()) {
            tProtocol.writeFieldBegin(APPID_FIELD_DESC);
            tProtocol.writeString(this.appid);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && this.isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.target != null) {
            tProtocol.writeFieldBegin(TARGET_FIELD_DESC);
            this.target.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.metaInfo != null && this.isSetMetaInfo()) {
            tProtocol.writeFieldBegin(META_INFO_FIELD_DESC);
            this.metaInfo.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        ACTION(1, "action"),
        ENCRYPT_ACTION(2, "encryptAction"),
        IS_REQUEST(3, "isRequest"),
        PUSH_ACTION(4, "pushAction"),
        APPID(5, "appid"),
        PACKAGE_NAME(6, "packageName"),
        TARGET(7, "target"),
        META_INFO(8, "metaInfo");
        
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

