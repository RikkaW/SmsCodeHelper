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
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.xmpush.thrift;

import com.xiaomi.xmpush.thrift.Target;
import java.io.Serializable;
import java.util.ArrayList;
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

public class XmPushActionCommand
implements TBase<XmPushActionCommand, _Fields>,
Serializable,
Cloneable {
    private static final TField APP_ID_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField CMD_ARGS_FIELD_DESC;
    private static final TField CMD_NAME_FIELD_DESC;
    private static final TField DEBUG_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField PACKAGE_NAME_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TARGET_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public String appId;
    public String category;
    public List<String> cmdArgs;
    public String cmdName;
    public String debug;
    public String id;
    public String packageName;
    public Target target;

    static {
        STRUCT_DESC = new TStruct("XmPushActionCommand");
        DEBUG_FIELD_DESC = new TField("debug", 11, 1);
        TARGET_FIELD_DESC = new TField("target", 12, 2);
        ID_FIELD_DESC = new TField("id", 11, 3);
        APP_ID_FIELD_DESC = new TField("appId", 11, 4);
        CMD_NAME_FIELD_DESC = new TField("cmdName", 11, 5);
        CMD_ARGS_FIELD_DESC = new TField("cmdArgs", 15, 6);
        PACKAGE_NAME_FIELD_DESC = new TField("packageName", 11, 7);
        CATEGORY_FIELD_DESC = new TField("category", 11, 9);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.DEBUG, new FieldMetaData("debug", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TARGET, new FieldMetaData("target", 2, new StructMetaData(12, Target.class)));
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_ID, new FieldMetaData("appId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CMD_NAME, new FieldMetaData("cmdName", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CMD_ARGS, new FieldMetaData("cmdArgs", 2, new ListMetaData(15, new FieldValueMetaData(11))));
        enumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(XmPushActionCommand.class, metaDataMap);
    }

    public void addToCmdArgs(String string2) {
        if (this.cmdArgs == null) {
            this.cmdArgs = new ArrayList();
        }
        this.cmdArgs.add(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(XmPushActionCommand xmPushActionCommand) {
        int n;
        if (!this.getClass().equals((Object)xmPushActionCommand.getClass())) {
            return this.getClass().getName().compareTo(xmPushActionCommand.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetDebug()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetDebug()));
        if (n != 0) return n2;
        if (this.isSetDebug()) {
            n2 = n = TBaseHelper.compareTo(this.debug, xmPushActionCommand.debug);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTarget()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetTarget()));
        if (n != 0) return n2;
        if (this.isSetTarget()) {
            n2 = n = TBaseHelper.compareTo(this.target, xmPushActionCommand.target);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, xmPushActionCommand.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetAppId()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetAppId()));
        if (n != 0) return n2;
        if (this.isSetAppId()) {
            n2 = n = TBaseHelper.compareTo(this.appId, xmPushActionCommand.appId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCmdName()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetCmdName()));
        if (n != 0) return n2;
        if (this.isSetCmdName()) {
            n2 = n = TBaseHelper.compareTo(this.cmdName, xmPushActionCommand.cmdName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCmdArgs()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetCmdArgs()));
        if (n != 0) return n2;
        if (this.isSetCmdArgs()) {
            n2 = n = TBaseHelper.compareTo(this.cmdArgs, xmPushActionCommand.cmdArgs);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPackageName()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetPackageName()));
        if (n != 0) return n2;
        if (this.isSetPackageName()) {
            n2 = n = TBaseHelper.compareTo(this.packageName, xmPushActionCommand.packageName);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)xmPushActionCommand.isSetCategory()));
        if (n != 0) return n2;
        if (!this.isSetCategory()) return 0;
        n2 = n = TBaseHelper.compareTo(this.category, xmPushActionCommand.category);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(XmPushActionCommand xmPushActionCommand) {
        if (xmPushActionCommand == null) {
            return false;
        }
        boolean bl = this.isSetDebug();
        boolean bl2 = xmPushActionCommand.isSetDebug();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.debug.equals((Object)xmPushActionCommand.debug)) {
                return false;
            }
        }
        bl = this.isSetTarget();
        bl2 = xmPushActionCommand.isSetTarget();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.target.equals(xmPushActionCommand.target)) {
                return false;
            }
        }
        bl = this.isSetId();
        bl2 = xmPushActionCommand.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)xmPushActionCommand.id)) {
                return false;
            }
        }
        bl = this.isSetAppId();
        bl2 = xmPushActionCommand.isSetAppId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.appId.equals((Object)xmPushActionCommand.appId)) {
                return false;
            }
        }
        bl = this.isSetCmdName();
        bl2 = xmPushActionCommand.isSetCmdName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.cmdName.equals((Object)xmPushActionCommand.cmdName)) {
                return false;
            }
        }
        bl = this.isSetCmdArgs();
        bl2 = xmPushActionCommand.isSetCmdArgs();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.cmdArgs.equals(xmPushActionCommand.cmdArgs)) {
                return false;
            }
        }
        bl = this.isSetPackageName();
        bl2 = xmPushActionCommand.isSetPackageName();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.packageName.equals((Object)xmPushActionCommand.packageName)) {
                return false;
            }
        }
        bl = this.isSetCategory();
        bl2 = xmPushActionCommand.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)xmPushActionCommand.category)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof XmPushActionCommand)) {
            return false;
        }
        return this.equals((XmPushActionCommand)object);
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
                        this.cmdName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
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
                case 7: {
                    if (object.type == 11) {
                        this.packageName = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 9: {
                    if (object.type == 11) {
                        this.category = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public XmPushActionCommand setAppId(String string2) {
        this.appId = string2;
        return this;
    }

    public XmPushActionCommand setCategory(String string2) {
        this.category = string2;
        return this;
    }

    public XmPushActionCommand setCmdName(String string2) {
        this.cmdName = string2;
        return this;
    }

    public XmPushActionCommand setId(String string2) {
        this.id = string2;
        return this;
    }

    public XmPushActionCommand setPackageName(String string2) {
        this.packageName = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder;
        block28 : {
            stringBuilder = new StringBuilder("XmPushActionCommand(");
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
                if (bl2) break block28;
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
        CMD_NAME(5, "cmdName"),
        CMD_ARGS(6, "cmdArgs"),
        PACKAGE_NAME(7, "packageName"),
        CATEGORY(9, "category");
        
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

