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

public class XmPushActionRegistration
implements TBase<XmPushActionRegistration, _Fields>,
Serializable,
Cloneable {
    private static final TField ALIAS_NAME_FIELD_DESC;
    private static final TField APP_ID_FIELD_DESC;
    private static final TField APP_VERSION_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField DEVICE_ID_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField SDK_VERSION_FIELD_DESC;
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
    public String sdkVersion;
    public Target target;
    public String token;

    static {
        STRUCT_DESC = new TStruct("XmPushActionRegistration");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        APP_VERSION_FIELD_DESC = new TField("appVersion", 11, 5);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 6);
        TOKEN_FIELD_DESC = new TField("token", 11, 7);
        DEVICE_ID_FIELD_DESC = new TField("deviceId", 11, 8);
        ALIAS_NAME_FIELD_DESC = new TField("aliasName", 11, 9);
        SDK_VERSION_FIELD_DESC = new TField("sdkVersion", 11, 10);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_VERSION, new FieldMetaData("appVersion", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TOKEN, new FieldMetaData("token", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.DEVICE_ID, new FieldMetaData("deviceId", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.ALIAS_NAME, new FieldMetaData("aliasName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.SDK_VERSION, new FieldMetaData("sdkVersion", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionRegistration.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionRegistration xmPushActionRegistration) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionRegistration.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionRegistration.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionRegistration.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionRegistration.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionRegistration.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionRegistration.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppVersion()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetAppVersion()));
        if (n != 0) return n2;
        if (this.isSetAppVersion()) {
            n2 = n = TBaseHelper.compareTo(this.appVersion, xmPushActionRegistration.appVersion);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionRegistration.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetToken()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetToken()));
        if (n != 0) return n2;
        if (this.isSetToken()) {
            n2 = n = TBaseHelper.compareTo(this.token, xmPushActionRegistration.token);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetDeviceId()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetDeviceId()));
        if (n != 0) return n2;
        if (this.isSetDeviceId()) {
            n2 = n = TBaseHelper.compareTo(this.deviceId, xmPushActionRegistration.deviceId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAliasName()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetAliasName()));
        if (n != 0) return n2;
        if (this.isSetAliasName()) {
            n2 = n = TBaseHelper.compareTo(this.aliasName, xmPushActionRegistration.aliasName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetSdkVersion()).compareTo(Boolean.valueOf((boolean)xmPushActionRegistration.isSetSdkVersion()));
        if (n != 0) return n2;
        if (!this.isSetSdkVersion()) return 0;
        n2 = n = TBaseHelper.compareTo(this.sdkVersion, xmPushActionRegistration.sdkVersion);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionRegistration xmPushActionRegistration) {
        if (xmPushActionRegistration == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionRegistration.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionRegistration.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionRegistration.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionRegistration.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionRegistration.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionRegistration.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionRegistration.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionRegistration.appId)) {
                return false;
            }
        }
        bl = this.isSetAppVersion();
        bl2 = xmPushActionRegistration.isSetAppVersion();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appVersion.equals((Object)xmPushActionRegistration.appVersion)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionRegistration.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionRegistration.packageName)) {
                return false;
            }
        }
        bl = this.isSetToken();
        bl2 = xmPushActionRegistration.isSetToken();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.token.equals((Object)xmPushActionRegistration.token)) {
                return false;
            }
        }
        bl = this.isSetDeviceId();
        bl2 = xmPushActionRegistration.isSetDeviceId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.deviceId.equals((Object)xmPushActionRegistration.deviceId)) {
                return false;
            }
        }
        bl = this.isSetAliasName();
        bl2 = xmPushActionRegistration.isSetAliasName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.aliasName.equals((Object)xmPushActionRegistration.aliasName)) {
                return false;
            }
        }
        bl = this.isSetSdkVersion();
        bl2 = xmPushActionRegistration.isSetSdkVersion();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.sdkVersion.equals((Object)xmPushActionRegistration.sdkVersion)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionRegistration)) {
            return false;
        }
        return this.equals((XmPushActionRegistration)object);
    }

    public String getAppId() {
        return this.appId;
    }

    public String getToken() {
        return this.token;
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

    public boolean isSetSdkVersion() {
        if (this.sdkVersion != null) {
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
                        this.appVersion = tProtocol.readString();
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
                    if (tField.type == 11) {
                        this.token = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 8: {
                    if (tField.type == 11) {
                        this.deviceId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 9: {
                    if (tField.type == 11) {
                        this.aliasName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 10: {
                    if (tField.type == 11) {
                        this.sdkVersion = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public XmPushActionRegistration setAppId(String string2) {
        this.appId = string2;
        return this;
    }

    public XmPushActionRegistration setAppVersion(String string2) {
        this.appVersion = string2;
        return this;
    }

    public XmPushActionRegistration setDeviceId(String string2) {
        this.deviceId = string2;
        return this;
    }

    public XmPushActionRegistration setId(String string2) {
        this.id = string2;
        return this;
    }

    public XmPushActionRegistration setPackageName(String string2) {
        this.packageName = string2;
        return this;
    }

    public XmPushActionRegistration setToken(String string2) {
        this.token = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block36 : {
            stringBuilder = new StringBuilder("XmPushActionRegistration(");
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
                if (bl2) break block36;
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
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("token:");
        if (this.token == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.token);
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
        if (this.isSetSdkVersion()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("sdkVersion:");
            if (this.sdkVersion == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.sdkVersion);
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
        if (this.token == null) {
            throw new TProtocolException("Required field 'token' was not present! Struct: " + this.toString());
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
        if (this.token != null) {
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
        if (this.sdkVersion != null && this.isSetSdkVersion()) {
            tProtocol.writeFieldBegin(SDK_VERSION_FIELD_DESC);
            tProtocol.writeString(this.sdkVersion);
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
        APP_VERSION(5, "appVersion"),
        PACKAGE_NAME(6, "packageName"),
        TOKEN(7, "token"),
        DEVICE_ID(8, "deviceId"),
        ALIAS_NAME(9, "aliasName"),
        SDK_VERSION(10, "sdkVersion");
        
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

