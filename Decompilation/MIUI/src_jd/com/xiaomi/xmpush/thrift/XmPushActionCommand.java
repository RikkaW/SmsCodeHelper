package com.xiaomi.xmpush.thrift;

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
  implements TBase<XmPushActionCommand, _Fields>, Serializable, Cloneable
{
  private static final TField APP_ID_FIELD_DESC;
  private static final TField CATEGORY_FIELD_DESC;
  private static final TField CMD_ARGS_FIELD_DESC;
  private static final TField CMD_NAME_FIELD_DESC;
  private static final TField DEBUG_FIELD_DESC;
  private static final TField ID_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionCommand");
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
  
  static
  {
    DEBUG_FIELD_DESC = new TField("debug", (byte)11, (short)1);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)2);
    ID_FIELD_DESC = new TField("id", (byte)11, (short)3);
    APP_ID_FIELD_DESC = new TField("appId", (byte)11, (short)4);
    CMD_NAME_FIELD_DESC = new TField("cmdName", (byte)11, (short)5);
    CMD_ARGS_FIELD_DESC = new TField("cmdArgs", (byte)15, (short)6);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)7);
    CATEGORY_FIELD_DESC = new TField("category", (byte)11, (short)9);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.DEBUG, new FieldMetaData("debug", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)2, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.ID, new FieldMetaData("id", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APP_ID, new FieldMetaData("appId", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CMD_NAME, new FieldMetaData("cmdName", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CMD_ARGS, new FieldMetaData("cmdArgs", (byte)2, new ListMetaData((byte)15, new FieldValueMetaData((byte)11))));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.CATEGORY, new FieldMetaData("category", (byte)2, new FieldValueMetaData((byte)11)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionCommand.class, metaDataMap);
  }
  
  public void addToCmdArgs(String paramString)
  {
    if (cmdArgs == null) {
      cmdArgs = new ArrayList();
    }
    cmdArgs.add(paramString);
  }
  
  public int compareTo(XmPushActionCommand paramXmPushActionCommand)
  {
    int i;
    if (!getClass().equals(paramXmPushActionCommand.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionCommand.getClass().getName());
    }
    int j;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          do
                          {
                            do
                            {
                              do
                              {
                                do
                                {
                                  do
                                  {
                                    return i;
                                    j = Boolean.valueOf(isSetDebug()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetDebug()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetDebug()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(debug, debug);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetTarget()));
                                i = j;
                              } while (j != 0);
                              if (!isSetTarget()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(target, target);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetId()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetId()));
                            i = j;
                          } while (j != 0);
                          if (!isSetId()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(id, id);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetAppId()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetAppId()));
                        i = j;
                      } while (j != 0);
                      if (!isSetAppId()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(appId, appId);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetCmdName()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetCmdName()));
                    i = j;
                  } while (j != 0);
                  if (!isSetCmdName()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(cmdName, cmdName);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetCmdArgs()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetCmdArgs()));
                i = j;
              } while (j != 0);
              if (!isSetCmdArgs()) {
                break;
              }
              j = TBaseHelper.compareTo(cmdArgs, cmdArgs);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetPackageName()));
            i = j;
          } while (j != 0);
          if (!isSetPackageName()) {
            break;
          }
          j = TBaseHelper.compareTo(packageName, packageName);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetCategory()).compareTo(Boolean.valueOf(paramXmPushActionCommand.isSetCategory()));
        i = j;
      } while (j != 0);
      if (!isSetCategory()) {
        break;
      }
      j = TBaseHelper.compareTo(category, category);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(XmPushActionCommand paramXmPushActionCommand)
  {
    if (paramXmPushActionCommand == null) {
      return false;
    }
    boolean bool1 = isSetDebug();
    boolean bool2 = paramXmPushActionCommand.isSetDebug();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!debug.equals(debug)) {
        return false;
      }
    }
    bool1 = isSetTarget();
    bool2 = paramXmPushActionCommand.isSetTarget();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!target.equals(target)) {
        return false;
      }
    }
    bool1 = isSetId();
    bool2 = paramXmPushActionCommand.isSetId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!id.equals(id)) {
        return false;
      }
    }
    bool1 = isSetAppId();
    bool2 = paramXmPushActionCommand.isSetAppId();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appId.equals(appId)) {
        return false;
      }
    }
    bool1 = isSetCmdName();
    bool2 = paramXmPushActionCommand.isSetCmdName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!cmdName.equals(cmdName)) {
        return false;
      }
    }
    bool1 = isSetCmdArgs();
    bool2 = paramXmPushActionCommand.isSetCmdArgs();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!cmdArgs.equals(cmdArgs)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramXmPushActionCommand.isSetPackageName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!packageName.equals(packageName)) {
        return false;
      }
    }
    bool1 = isSetCategory();
    bool2 = paramXmPushActionCommand.isSetCategory();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!category.equals(category)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof XmPushActionCommand)) {
      return false;
    }
    return equals((XmPushActionCommand)paramObject);
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isSetAppId()
  {
    return appId != null;
  }
  
  public boolean isSetCategory()
  {
    return category != null;
  }
  
  public boolean isSetCmdArgs()
  {
    return cmdArgs != null;
  }
  
  public boolean isSetCmdName()
  {
    return cmdName != null;
  }
  
  public boolean isSetDebug()
  {
    return debug != null;
  }
  
  public boolean isSetId()
  {
    return id != null;
  }
  
  public boolean isSetPackageName()
  {
    return packageName != null;
  }
  
  public boolean isSetTarget()
  {
    return target != null;
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    Object localObject = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      validate();
      return;
    }
    switch (id)
    {
    case 8: 
    default: 
      TProtocolUtil.skip(paramTProtocol, type);
    }
    for (;;)
    {
      paramTProtocol.readFieldEnd();
      break;
      if (type == 11)
      {
        debug = paramTProtocol.readString();
      }
      else
      {
        TProtocolUtil.skip(paramTProtocol, type);
        continue;
        if (type == 12)
        {
          target = new Target();
          target.read(paramTProtocol);
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 11)
          {
            id = paramTProtocol.readString();
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 11)
            {
              appId = paramTProtocol.readString();
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 11)
              {
                cmdName = paramTProtocol.readString();
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 15)
                {
                  localObject = paramTProtocol.readListBegin();
                  cmdArgs = new ArrayList(size);
                  int i = 0;
                  while (i < size)
                  {
                    String str = paramTProtocol.readString();
                    cmdArgs.add(str);
                    i += 1;
                  }
                  paramTProtocol.readListEnd();
                }
                else
                {
                  TProtocolUtil.skip(paramTProtocol, type);
                  continue;
                  if (type == 11)
                  {
                    packageName = paramTProtocol.readString();
                  }
                  else
                  {
                    TProtocolUtil.skip(paramTProtocol, type);
                    continue;
                    if (type == 11) {
                      category = paramTProtocol.readString();
                    } else {
                      TProtocolUtil.skip(paramTProtocol, type);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public XmPushActionCommand setAppId(String paramString)
  {
    appId = paramString;
    return this;
  }
  
  public XmPushActionCommand setCategory(String paramString)
  {
    category = paramString;
    return this;
  }
  
  public XmPushActionCommand setCmdName(String paramString)
  {
    cmdName = paramString;
    return this;
  }
  
  public XmPushActionCommand setId(String paramString)
  {
    id = paramString;
    return this;
  }
  
  public XmPushActionCommand setPackageName(String paramString)
  {
    packageName = paramString;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionCommand(");
    int i = 1;
    if (isSetDebug())
    {
      localStringBuilder.append("debug:");
      if (debug == null)
      {
        localStringBuilder.append("null");
        i = 0;
      }
    }
    else
    {
      int j = i;
      if (isSetTarget())
      {
        if (i == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("target:");
        if (target != null) {
          break label347;
        }
        localStringBuilder.append("null");
        label89:
        j = 0;
      }
      if (j == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("id:");
      if (id != null) {
        break label359;
      }
      localStringBuilder.append("null");
      label126:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("appId:");
      if (appId != null) {
        break label371;
      }
      localStringBuilder.append("null");
      label161:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("cmdName:");
      if (cmdName != null) {
        break label383;
      }
      localStringBuilder.append("null");
      label196:
      if (isSetCmdArgs())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("cmdArgs:");
        if (cmdArgs != null) {
          break label395;
        }
        localStringBuilder.append("null");
      }
      label238:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label407;
        }
        localStringBuilder.append("null");
      }
      label280:
      if (isSetCategory())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("category:");
        if (category != null) {
          break label419;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(debug);
      break;
      label347:
      localStringBuilder.append(target);
      break label89;
      label359:
      localStringBuilder.append(id);
      break label126;
      label371:
      localStringBuilder.append(appId);
      break label161;
      label383:
      localStringBuilder.append(cmdName);
      break label196;
      label395:
      localStringBuilder.append(cmdArgs);
      break label238;
      label407:
      localStringBuilder.append(packageName);
      break label280;
      label419:
      localStringBuilder.append(category);
    }
  }
  
  public void validate()
    throws TException
  {
    if (id == null) {
      throw new TProtocolException("Required field 'id' was not present! Struct: " + toString());
    }
    if (appId == null) {
      throw new TProtocolException("Required field 'appId' was not present! Struct: " + toString());
    }
    if (cmdName == null) {
      throw new TProtocolException("Required field 'cmdName' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if ((debug != null) && (isSetDebug()))
    {
      paramTProtocol.writeFieldBegin(DEBUG_FIELD_DESC);
      paramTProtocol.writeString(debug);
      paramTProtocol.writeFieldEnd();
    }
    if ((target != null) && (isSetTarget()))
    {
      paramTProtocol.writeFieldBegin(TARGET_FIELD_DESC);
      target.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if (id != null)
    {
      paramTProtocol.writeFieldBegin(ID_FIELD_DESC);
      paramTProtocol.writeString(id);
      paramTProtocol.writeFieldEnd();
    }
    if (appId != null)
    {
      paramTProtocol.writeFieldBegin(APP_ID_FIELD_DESC);
      paramTProtocol.writeString(appId);
      paramTProtocol.writeFieldEnd();
    }
    if (cmdName != null)
    {
      paramTProtocol.writeFieldBegin(CMD_NAME_FIELD_DESC);
      paramTProtocol.writeString(cmdName);
      paramTProtocol.writeFieldEnd();
    }
    if ((cmdArgs != null) && (isSetCmdArgs()))
    {
      paramTProtocol.writeFieldBegin(CMD_ARGS_FIELD_DESC);
      paramTProtocol.writeListBegin(new TList((byte)11, cmdArgs.size()));
      Iterator localIterator = cmdArgs.iterator();
      while (localIterator.hasNext()) {
        paramTProtocol.writeString((String)localIterator.next());
      }
      paramTProtocol.writeListEnd();
      paramTProtocol.writeFieldEnd();
    }
    if ((packageName != null) && (isSetPackageName()))
    {
      paramTProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
      paramTProtocol.writeString(packageName);
      paramTProtocol.writeFieldEnd();
    }
    if ((category != null) && (isSetCategory()))
    {
      paramTProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
      paramTProtocol.writeString(category);
      paramTProtocol.writeFieldEnd();
    }
    paramTProtocol.writeFieldStop();
    paramTProtocol.writeStructEnd();
  }
  
  public static enum _Fields
  {
    private static final Map<String, _Fields> byName;
    private final String _fieldName;
    private final short _thriftId;
    
    static
    {
      ID = new _Fields("ID", 2, (short)3, "id");
      APP_ID = new _Fields("APP_ID", 3, (short)4, "appId");
      CMD_NAME = new _Fields("CMD_NAME", 4, (short)5, "cmdName");
      CMD_ARGS = new _Fields("CMD_ARGS", 5, (short)6, "cmdArgs");
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 6, (short)7, "packageName");
      CATEGORY = new _Fields("CATEGORY", 7, (short)9, "category");
      $VALUES = new _Fields[] { DEBUG, TARGET, ID, APP_ID, CMD_NAME, CMD_ARGS, PACKAGE_NAME, CATEGORY };
      byName = new HashMap();
      Iterator localIterator = EnumSet.allOf(_Fields.class).iterator();
      while (localIterator.hasNext())
      {
        _Fields local_Fields = (_Fields)localIterator.next();
        byName.put(local_Fields.getFieldName(), local_Fields);
      }
    }
    
    private _Fields(short paramShort, String paramString)
    {
      _thriftId = paramShort;
      _fieldName = paramString;
    }
    
    public String getFieldName()
    {
      return _fieldName;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionCommand
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */