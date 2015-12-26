/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.xmpush.thrift;

import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionCommand;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class XmPushActionCommandResult
implements TBase<XmPushActionCommandResult, _Fields>,
Serializable,
Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField CMD_ARGS_FIELD_DESC;
    private static final TField CMD_NAME_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ERROR_CODE_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TField REASON_FIELD_DESC;
    private static final TField REQUEST_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(1);
    public String appId;
    public String category;
    public List<String> cmdArgs;
    public String cmdName;
    public String debug;
    public long errorCode;
    public String id;
    public String packageName;
    public String reason;
    public XmPushActionCommand request;
    public Target target;

    static {
        STRUCT_DESC = new TStruct("XmPushActionCommandResult");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        CMD_NAME_FIELD_DESC = new TField("cmdName", 11, 5);
        REQUEST_FIELD_DESC = new TField("request", 12, 6);
        ERROR_CODE_FIELD_DESC = new TField("errorCode", 10, 7);
        REASON_FIELD_DESC = new TField("reason", 11, 8);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 9);
        CMD_ARGS_FIELD_DESC = new TField("cmdArgs", 15, 10);
        CATEGORY_FIELD_DESC = new TField("category", 11, 12);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CMD_NAME, new FieldMetaData("cmdName", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.REQUEST, new FieldMetaData("request", 2, new StructMetaData(12, XmPushActionCommand.class)));
        enumMap.put(_Fields.ERROR_CODE, new FieldMetaData("errorCode", 1, new FieldValueMetaData(10)));
        enumMap.put(_Fields.REASON, new FieldMetaData("reason", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CMD_ARGS, new FieldMetaData("cmdArgs", 2, new ListMetaData(15, new FieldValueMetaData(11))));
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionCommandResult.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionCommandResult xmPushActionCommandResult) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionCommandResult.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionCommandResult.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionCommandResult.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionCommandResult.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionCommandResult.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionCommandResult.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCmdName()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetCmdName()));
        if (n != 0) return n2;
        if (this.isSetCmdName()) {
            n2 = n = TBaseHelper.compareTo(this.cmdName, xmPushActionCommandResult.cmdName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetRequest()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetRequest()));
        if (n != 0) return n2;
        if (this.isSetRequest()) {
            n2 = n = TBaseHelper.compareTo(this.request, xmPushActionCommandResult.request);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetErrorCode()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetErrorCode()));
        if (n != 0) return n2;
        if (this.isSetErrorCode()) {
            n2 = n = TBaseHelper.compareTo(this.errorCode, xmPushActionCommandResult.errorCode);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetReason()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetReason()));
        if (n != 0) return n2;
        if (this.isSetReason()) {
            n2 = n = TBaseHelper.compareTo(this.reason, xmPushActionCommandResult.reason);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionCommandResult.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCmdArgs()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetCmdArgs()));
        if (n != 0) return n2;
        if (this.isSetCmdArgs()) {
            n2 = n = TBaseHelper.compareTo(this.cmdArgs, xmPushActionCommandResult.cmdArgs);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)xmPushActionCommandResult.isSetCategory()));
        if (n != 0) return n2;
        if (!this.isSetCategory()) return 0;
        n2 = n = TBaseHelper.compareTo(this.category, xmPushActionCommandResult.category);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionCommandResult xmPushActionCommandResult) {
        if (xmPushActionCommandResult == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionCommandResult.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionCommandResult.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionCommandResult.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionCommandResult.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionCommandResult.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionCommandResult.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionCommandResult.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionCommandResult.appId)) {
                return false;
            }
        }
        bl = this.isSetCmdName();
        bl2 = xmPushActionCommandResult.isSetCmdName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.cmdName.equals((Object)xmPushActionCommandResult.cmdName)) {
                return false;
            }
        }
        bl = this.isSetRequest();
        bl2 = xmPushActionCommandResult.isSetRequest();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.request.equals(xmPushActionCommandResult.request)) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.errorCode != xmPushActionCommandResult.errorCode) {
                return false;
            }
        }
        bl = this.isSetReason();
        bl2 = xmPushActionCommandResult.isSetReason();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.reason.equals((Object)xmPushActionCommandResult.reason)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionCommandResult.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionCommandResult.packageName)) {
                return false;
            }
        }
        bl = this.isSetCmdArgs();
        bl2 = xmPushActionCommandResult.isSetCmdArgs();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.cmdArgs.equals(xmPushActionCommandResult.cmdArgs)) {
                return false;
            }
        }
        bl = this.isSetCategory();
        bl2 = xmPushActionCommandResult.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)xmPushActionCommandResult.category)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionCommandResult)) {
            return false;
        }
        return this.equals((XmPushActionCommandResult)object);
    }

    public String getCategory() {
        return this.category;
    }

    public List<String> getCmdArgs() {
        return this.cmdArgs;
    }

    public String getCmdName() {
        return this.cmdName;
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

    public boolean isSetCmdArgs() {
        if (this.cmdArgs != null) {
            return true;
        }
        return false;
    }

    public boolean isSetCmdName() {
        if (this.cmdName != null) {
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

    public boolean isSetErrorCode() {
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

    public boolean isSetReason() {
        if (this.reason != null) {
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
                if (this.isSetErrorCode()) break;
                throw new TProtocolException("Required field 'errorCode' was not found in serialized data! Struct: " + this.toString());
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
                        this.cmdName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
                    if (object.type == 12) {
                        this.request = new XmPushActionCommand();
                        this.request.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 7: {
                    if (object.type == 10) {
                        this.errorCode = tProtocol.readI64();
                        this.setErrorCodeIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 8: {
                    if (object.type == 11) {
                        this.reason = tProtocol.readString();
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
                    if (object.type == 15) {
                        object = tProtocol.readListBegin();
                        this.cmdArgs = new ArrayList(object.size);
                        for (int i = 0; i < object.size; ++i) {
                            String string2 = tProtocol.readString();
                            this.cmdArgs.add(string2);
                        }
                        tProtocol.readListEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 12: {
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

    public void setErrorCodeIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block37 : {
            stringBuilder = new StringBuilder("XmPushActionCommandResult(");
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
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("cmdName:");
        if (this.cmdName == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.cmdName);
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
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("errorCode:");
        stringBuilder.append(this.errorCode);
        if (this.isSetReason()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("reason:");
            if (this.reason == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.reason);
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
        if (this.isSetCmdArgs()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("cmdArgs:");
            if (this.cmdArgs == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.cmdArgs);
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
        if (this.cmdName == null) {
            throw new TProtocolException("Required field 'cmdName' was not present! Struct: " + this.toString());
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
        if (this.cmdName != null) {
            tProtocol.writeFieldBegin(CMD_NAME_FIELD_DESC);
            tProtocol.writeString(this.cmdName);
            tProtocol.writeFieldEnd();
        }
        if (this.request != null && this.isSetRequest()) {
            tProtocol.writeFieldBegin(REQUEST_FIELD_DESC);
            this.request.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(ERROR_CODE_FIELD_DESC);
        tProtocol.writeI64(this.errorCode);
        tProtocol.writeFieldEnd();
        if (this.reason != null && this.isSetReason()) {
            tProtocol.writeFieldBegin(REASON_FIELD_DESC);
            tProtocol.writeString(this.reason);
            tProtocol.writeFieldEnd();
        }
        if (this.packageName != null && this.isSetPackageName()) {
            tProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
            tProtocol.writeString(this.packageName);
            tProtocol.writeFieldEnd();
        }
        if (this.cmdArgs != null && this.isSetCmdArgs()) {
            tProtocol.writeFieldBegin(CMD_ARGS_FIELD_DESC);
            tProtocol.writeListBegin(new TList(11, this.cmdArgs.size()));
            Iterator<String> iterator = this.cmdArgs.iterator();
            while (iterator.hasNext()) {
                tProtocol.writeString(iterator.next());
            }
            tProtocol.writeListEnd();
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
        CMD_NAME(5, "cmdName"),
        REQUEST(6, "request"),
        ERROR_CODE(7, "errorCode"),
        REASON(8, "reason"),
        PACKAGE_NAME(9, "packageName"),
        CMD_ARGS(10, "cmdArgs"),
        CATEGORY(12, "category");
        
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

