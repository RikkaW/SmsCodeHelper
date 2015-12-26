/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.xmpush.thrift;

import com.xiaomi.xmpush.thrift.Target;
import java.io.Serializable;
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

public class XmPushActionUnRegistration
implements TBase<XmPushActionUnRegistration, _Fields>,
Serializable,
Cloneable {
    private static final TField ALIAS_NAME_FIELD_DESC;
    private static final TField APP_ID_FIELD_DESC;
    private static final TField APP_VERSION_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField DEVICE_ID_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField REG_ID_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    private static final TField TOKEN_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String aliasName;
    public String appId;
    public String appVersion;
    public String debug;
    public String deviceId;
    public String id;
    public String packageName;
    public String regId;
    public Target target;
    public String token;

    static {
        STRUCT_DESC = new TStruct("XmPushActionUnRegistration");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        REG_ID_FIELD_DESC = new TField("regId", 11, 5);
        APP_VERSION_FIELD_DESC = new TField("appVersion", 11, 6);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 7);
        TOKEN_FIELD_DESC = new TField("token", 11, 8);
        DEVICE_ID_FIELD_DESC = new TField("deviceId", 11, 9);
        ALIAS_NAME_FIELD_DESC = new TField("aliasName", 11, 10);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.REG_ID, new FieldMetaData("regId", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_VERSION, new FieldMetaData("appVersion", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TOKEN, new FieldMetaData("token", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.DEVICE_ID, new FieldMetaData("deviceId", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionUnRegistration.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionUnRegistration xmPushActionUnRegistration) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionUnRegistration.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionUnRegistration.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionUnRegistration.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionUnRegistration.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionUnRegistration.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionUnRegistration.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetRegId()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetRegId()));
        if (n != 0) return n2;
        if (this.isSetRegId()) {
            n2 = n = TBaseHelper.compareTo(this.regId, xmPushActionUnRegistration.regId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppVersion()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetAppVersion()));
        if (n != 0) return n2;
        if (this.isSetAppVersion()) {
            n2 = n = TBaseHelper.compareTo(this.appVersion, xmPushActionUnRegistration.appVersion);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionUnRegistration.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetToken()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetToken()));
        if (n != 0) return n2;
        if (this.isSetToken()) {
            n2 = n = TBaseHelper.compareTo(this.token, xmPushActionUnRegistration.token);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetDeviceId()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetDeviceId()));
        if (n != 0) return n2;
        if (this.isSetDeviceId()) {
            n2 = n = TBaseHelper.compareTo(this.deviceId, xmPushActionUnRegistration.deviceId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAliasName()).compareTo(Boolean.valueOf((boolean)xmPushActionUnRegistration.isSetAliasName()));
        if (n != 0) return n2;
        if (!this.isSetAliasName()) return 0;
        n2 = n = TBaseHelper.compareTo(this.aliasName, xmPushActionUnRegistration.aliasName);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionUnRegistration xmPushActionUnRegistration) {
        if (xmPushActionUnRegistration == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionUnRegistration.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionUnRegistration.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionUnRegistration.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionUnRegistration.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionUnRegistration.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionUnRegistration.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionUnRegistration.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionUnRegistration.appId)) {
                return false;
            }
        }
        bl = this.isSetRegId();
        bl2 = xmPushActionUnRegistration.isSetRegId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.regId.equals((Object)xmPushActionUnRegistration.regId)) {
                return false;
            }
        }
        bl = this.isSetAppVersion();
        bl2 = xmPushActionUnRegistration.isSetAppVersion();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appVersion.equals((Object)xmPushActionUnRegistration.appVersion)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionUnRegistration.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionUnRegistration.packageName)) {
                return false;
            }
        }
        bl = this.isSetToken();
        bl2 = xmPushActionUnRegistration.isSetToken();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.token.equals((Object)xmPushActionUnRegistration.token)) {
                return false;
            }
        }
        bl = this.isSetDeviceId();
        bl2 = xmPushActionUnRegistration.isSetDeviceId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.deviceId.equals((Object)xmPushActionUnRegistration.deviceId)) {
                return false;
            }
        }
        bl = this.isSetAliasName();
        bl2 = xmPushActionUnRegistration.isSetAliasName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.aliasName.equals((Object)xmPushActionUnRegistration.aliasName)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionUnRegistration)) {
            return false;
        }
        return this.equals((XmPushActionUnRegistration)object);
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

    public boolean isSetAppVersion() {
        if (this.appVersion != null) {
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

    public boolean isSetDeviceId() {
        if (this.deviceId != null) {
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

    public boolean isSetRegId() {
        if (this.regId != null) {
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

    public boolean isSetToken() {
        if (this.token != null) {
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
                this.validate();
                return;
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
                    if (tField.type == 11) {
                        this.regId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 6: {
                    if (tField.type == 11) {
                        this.appVersion = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 7: {
                    if (tField.type == 11) {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 8: {
                    if (tField.type == 11) {
                        this.token = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 9: {
                    if (tField.type == 11) {
                        this.deviceId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 10: {
                    if (tField.type == 11) {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public XmPushActionUnRegistration setAppId(String string2) {
        this.appId = string2;
        return this;
    }

    public XmPushActionUnRegistration setId(String string2) {
        this.id = string2;
        return this;
    }

    public XmPushActionUnRegistration setPackageName(String string2) {
        this.packageName = string2;
        return this;
    }

    public XmPushActionUnRegistration setRegId(String string2) {
        this.regId = string2;
        return this;
    }

    public XmPushActionUnRegistration setToken(String string2) {
        this.token = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block37 : {
            stringBuilder = new StringBuilder("XmPushActionUnRegistration(");
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
                if (bl2) break block37;
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
        if (this.isSetRegId()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("regId:");
            if (this.regId == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.regId);
            }
        }
        if (this.isSetAppVersion()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("appVersion:");
            if (this.appVersion == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.appVersion);
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
        if (this.isSetToken()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("token:");
            if (this.token == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.token);
            }
        }
        if (this.isSetDeviceId()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("deviceId:");
            if (this.deviceId == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.deviceId);
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
        if (this.regId != null && this.isSetRegId()) {
            tProtocol.writeFieldBegin(REG_ID_FIELD_DESC);
            tProtocol.writeString(this.regId);
            tProtocol.writeFieldEnd();
        }
        if (this.appVersion != null && this.isSetAppVersion()) {
            tProtocol.writeFieldBegin(APP_VERSION_FIELD_DESC);
            tProtocol.writeString(this.appVersion);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && this.isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.token != null && this.isSetToken()) {
            tProtocol.writeFieldBegin(TOKEN_FIELD_DESC);
            tProtocol.writeString(this.token);
            tProtocol.writeFieldEnd();
        }
        if (this.deviceId != null && this.isSetDeviceId()) {
            tProtocol.writeFieldBegin(DEVICE_ID_FIELD_DESC);
            tProtocol.writeString(this.deviceId);
            tProtocol.writeFieldEnd();
        }
        if (this.aliasName != null && this.isSetAliasName()) {
            tProtocol.writeFieldBegin(ALIAS_NAME_FIELD_DESC);
            tProtocol.writeString(this.aliasName);
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
        REG_ID(5, "regId"),
        APP_VERSION(6, "appVersion"),
        PACKAGE_NAME(7, "packageName"),
        TOKEN(8, "token"),
        DEVICE_ID(9, "deviceId"),
        ALIAS_NAME(10, "aliasName");
        
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

