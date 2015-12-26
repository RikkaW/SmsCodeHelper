package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
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
  implements TBase<XmPushActionContainer, _Fields>, Serializable, Cloneable
{
  private static final TField ACTION_FIELD_DESC;
  private static final TField APPID_FIELD_DESC;
  private static final TField ENCRYPT_ACTION_FIELD_DESC;
  private static final TField IS_REQUEST_FIELD_DESC;
  private static final TField META_INFO_FIELD_DESC;
  private static final TField PACKAGE_NAME_FIELD_DESC;
  private static final TField PUSH_ACTION_FIELD_DESC;
  private static final TStruct STRUCT_DESC = new TStruct("XmPushActionContainer");
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
  
  static
  {
    ACTION_FIELD_DESC = new TField("action", (byte)8, (short)1);
    ENCRYPT_ACTION_FIELD_DESC = new TField("encryptAction", (byte)2, (short)2);
    IS_REQUEST_FIELD_DESC = new TField("isRequest", (byte)2, (short)3);
    PUSH_ACTION_FIELD_DESC = new TField("pushAction", (byte)11, (short)4);
    APPID_FIELD_DESC = new TField("appid", (byte)11, (short)5);
    PACKAGE_NAME_FIELD_DESC = new TField("packageName", (byte)11, (short)6);
    TARGET_FIELD_DESC = new TField("target", (byte)12, (short)7);
    META_INFO_FIELD_DESC = new TField("metaInfo", (byte)12, (short)8);
    EnumMap localEnumMap = new EnumMap(_Fields.class);
    localEnumMap.put(_Fields.ACTION, new FieldMetaData("action", (byte)1, new EnumMetaData((byte)16, ActionType.class)));
    localEnumMap.put(_Fields.ENCRYPT_ACTION, new FieldMetaData("encryptAction", (byte)1, new FieldValueMetaData((byte)2)));
    localEnumMap.put(_Fields.IS_REQUEST, new FieldMetaData("isRequest", (byte)1, new FieldValueMetaData((byte)2)));
    localEnumMap.put(_Fields.PUSH_ACTION, new FieldMetaData("pushAction", (byte)1, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.APPID, new FieldMetaData("appid", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.PACKAGE_NAME, new FieldMetaData("packageName", (byte)2, new FieldValueMetaData((byte)11)));
    localEnumMap.put(_Fields.TARGET, new FieldMetaData("target", (byte)1, new StructMetaData((byte)12, Target.class)));
    localEnumMap.put(_Fields.META_INFO, new FieldMetaData("metaInfo", (byte)2, new StructMetaData((byte)12, PushMetaInfo.class)));
    metaDataMap = Collections.unmodifiableMap(localEnumMap);
    FieldMetaData.addStructMetaDataMap(XmPushActionContainer.class, metaDataMap);
  }
  
  public int compareTo(XmPushActionContainer paramXmPushActionContainer)
  {
    int i;
    if (!getClass().equals(paramXmPushActionContainer.getClass())) {
      i = getClass().getName().compareTo(paramXmPushActionContainer.getClass().getName());
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
                                    j = Boolean.valueOf(isSetAction()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetAction()));
                                    i = j;
                                  } while (j != 0);
                                  if (!isSetAction()) {
                                    break;
                                  }
                                  j = TBaseHelper.compareTo(action, action);
                                  i = j;
                                } while (j != 0);
                                j = Boolean.valueOf(isSetEncryptAction()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetEncryptAction()));
                                i = j;
                              } while (j != 0);
                              if (!isSetEncryptAction()) {
                                break;
                              }
                              j = TBaseHelper.compareTo(encryptAction, encryptAction);
                              i = j;
                            } while (j != 0);
                            j = Boolean.valueOf(isSetIsRequest()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetIsRequest()));
                            i = j;
                          } while (j != 0);
                          if (!isSetIsRequest()) {
                            break;
                          }
                          j = TBaseHelper.compareTo(isRequest, isRequest);
                          i = j;
                        } while (j != 0);
                        j = Boolean.valueOf(isSetPushAction()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetPushAction()));
                        i = j;
                      } while (j != 0);
                      if (!isSetPushAction()) {
                        break;
                      }
                      j = TBaseHelper.compareTo(pushAction, pushAction);
                      i = j;
                    } while (j != 0);
                    j = Boolean.valueOf(isSetAppid()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetAppid()));
                    i = j;
                  } while (j != 0);
                  if (!isSetAppid()) {
                    break;
                  }
                  j = TBaseHelper.compareTo(appid, appid);
                  i = j;
                } while (j != 0);
                j = Boolean.valueOf(isSetPackageName()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetPackageName()));
                i = j;
              } while (j != 0);
              if (!isSetPackageName()) {
                break;
              }
              j = TBaseHelper.compareTo(packageName, packageName);
              i = j;
            } while (j != 0);
            j = Boolean.valueOf(isSetTarget()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetTarget()));
            i = j;
          } while (j != 0);
          if (!isSetTarget()) {
            break;
          }
          j = TBaseHelper.compareTo(target, target);
          i = j;
        } while (j != 0);
        j = Boolean.valueOf(isSetMetaInfo()).compareTo(Boolean.valueOf(paramXmPushActionContainer.isSetMetaInfo()));
        i = j;
      } while (j != 0);
      if (!isSetMetaInfo()) {
        break;
      }
      j = TBaseHelper.compareTo(metaInfo, metaInfo);
      i = j;
    } while (j != 0);
    return 0;
  }
  
  public boolean equals(XmPushActionContainer paramXmPushActionContainer)
  {
    if (paramXmPushActionContainer == null) {
      return false;
    }
    boolean bool1 = isSetAction();
    boolean bool2 = paramXmPushActionContainer.isSetAction();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!action.equals(action)) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (encryptAction != encryptAction) {
        return false;
      }
    }
    if ((1 != 0) || (1 != 0))
    {
      if ((1 == 0) || (1 == 0)) {
        return false;
      }
      if (isRequest != isRequest) {
        return false;
      }
    }
    bool1 = isSetPushAction();
    bool2 = paramXmPushActionContainer.isSetPushAction();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!pushAction.equals(pushAction)) {
        return false;
      }
    }
    bool1 = isSetAppid();
    bool2 = paramXmPushActionContainer.isSetAppid();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!appid.equals(appid)) {
        return false;
      }
    }
    bool1 = isSetPackageName();
    bool2 = paramXmPushActionContainer.isSetPackageName();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!packageName.equals(packageName)) {
        return false;
      }
    }
    bool1 = isSetTarget();
    bool2 = paramXmPushActionContainer.isSetTarget();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!target.equals(target)) {
        return false;
      }
    }
    bool1 = isSetMetaInfo();
    bool2 = paramXmPushActionContainer.isSetMetaInfo();
    if ((bool1) || (bool2))
    {
      if ((!bool1) || (!bool2)) {
        return false;
      }
      if (!metaInfo.equals(metaInfo)) {
        return false;
      }
    }
    return true;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {}
    while (!(paramObject instanceof XmPushActionContainer)) {
      return false;
    }
    return equals((XmPushActionContainer)paramObject);
  }
  
  public ActionType getAction()
  {
    return action;
  }
  
  public String getAppid()
  {
    return appid;
  }
  
  public PushMetaInfo getMetaInfo()
  {
    return metaInfo;
  }
  
  public String getPackageName()
  {
    return packageName;
  }
  
  public byte[] getPushAction()
  {
    setPushAction(TBaseHelper.rightSize(pushAction));
    return pushAction.array();
  }
  
  public int hashCode()
  {
    return 0;
  }
  
  public boolean isEncryptAction()
  {
    return encryptAction;
  }
  
  public boolean isSetAction()
  {
    return action != null;
  }
  
  public boolean isSetAppid()
  {
    return appid != null;
  }
  
  public boolean isSetEncryptAction()
  {
    return __isset_bit_vector.get(0);
  }
  
  public boolean isSetIsRequest()
  {
    return __isset_bit_vector.get(1);
  }
  
  public boolean isSetMetaInfo()
  {
    return metaInfo != null;
  }
  
  public boolean isSetPackageName()
  {
    return packageName != null;
  }
  
  public boolean isSetPushAction()
  {
    return pushAction != null;
  }
  
  public boolean isSetTarget()
  {
    return target != null;
  }
  
  public void read(TProtocol paramTProtocol)
    throws TException
  {
    paramTProtocol.readStructBegin();
    TField localTField = paramTProtocol.readFieldBegin();
    if (type == 0)
    {
      paramTProtocol.readStructEnd();
      if (!isSetEncryptAction()) {
        throw new TProtocolException("Required field 'encryptAction' was not found in serialized data! Struct: " + toString());
      }
    }
    else
    {
      switch (id)
      {
      default: 
        TProtocolUtil.skip(paramTProtocol, type);
      }
      for (;;)
      {
        paramTProtocol.readFieldEnd();
        break;
        if (type == 8)
        {
          action = ActionType.findByValue(paramTProtocol.readI32());
        }
        else
        {
          TProtocolUtil.skip(paramTProtocol, type);
          continue;
          if (type == 2)
          {
            encryptAction = paramTProtocol.readBool();
            setEncryptActionIsSet(true);
          }
          else
          {
            TProtocolUtil.skip(paramTProtocol, type);
            continue;
            if (type == 2)
            {
              isRequest = paramTProtocol.readBool();
              setIsRequestIsSet(true);
            }
            else
            {
              TProtocolUtil.skip(paramTProtocol, type);
              continue;
              if (type == 11)
              {
                pushAction = paramTProtocol.readBinary();
              }
              else
              {
                TProtocolUtil.skip(paramTProtocol, type);
                continue;
                if (type == 11)
                {
                  appid = paramTProtocol.readString();
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
                    if (type == 12)
                    {
                      target = new Target();
                      target.read(paramTProtocol);
                    }
                    else
                    {
                      TProtocolUtil.skip(paramTProtocol, type);
                      continue;
                      if (type == 12)
                      {
                        metaInfo = new PushMetaInfo();
                        metaInfo.read(paramTProtocol);
                      }
                      else
                      {
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
    if (!isSetIsRequest()) {
      throw new TProtocolException("Required field 'isRequest' was not found in serialized data! Struct: " + toString());
    }
    validate();
  }
  
  public XmPushActionContainer setAction(ActionType paramActionType)
  {
    action = paramActionType;
    return this;
  }
  
  public XmPushActionContainer setAppid(String paramString)
  {
    appid = paramString;
    return this;
  }
  
  public XmPushActionContainer setEncryptAction(boolean paramBoolean)
  {
    encryptAction = paramBoolean;
    setEncryptActionIsSet(true);
    return this;
  }
  
  public void setEncryptActionIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(0, paramBoolean);
  }
  
  public XmPushActionContainer setIsRequest(boolean paramBoolean)
  {
    isRequest = paramBoolean;
    setIsRequestIsSet(true);
    return this;
  }
  
  public void setIsRequestIsSet(boolean paramBoolean)
  {
    __isset_bit_vector.set(1, paramBoolean);
  }
  
  public XmPushActionContainer setMetaInfo(PushMetaInfo paramPushMetaInfo)
  {
    metaInfo = paramPushMetaInfo;
    return this;
  }
  
  public XmPushActionContainer setPackageName(String paramString)
  {
    packageName = paramString;
    return this;
  }
  
  public XmPushActionContainer setPushAction(ByteBuffer paramByteBuffer)
  {
    pushAction = paramByteBuffer;
    return this;
  }
  
  public XmPushActionContainer setTarget(Target paramTarget)
  {
    target = paramTarget;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("XmPushActionContainer(");
    localStringBuilder.append("action:");
    if (action == null)
    {
      localStringBuilder.append("null");
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("encryptAction:");
      localStringBuilder.append(encryptAction);
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("isRequest:");
      localStringBuilder.append(isRequest);
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("pushAction:");
      if (pushAction != null) {
        break label313;
      }
      localStringBuilder.append("null");
      label127:
      if (isSetAppid())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("appid:");
        if (appid != null) {
          break label324;
        }
        localStringBuilder.append("null");
      }
      label169:
      if (isSetPackageName())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("packageName:");
        if (packageName != null) {
          break label336;
        }
        localStringBuilder.append("null");
      }
      label211:
      if (0 == 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("target:");
      if (target != null) {
        break label348;
      }
      localStringBuilder.append("null");
      label246:
      if (isSetMetaInfo())
      {
        if (0 == 0) {
          localStringBuilder.append(", ");
        }
        localStringBuilder.append("metaInfo:");
        if (metaInfo != null) {
          break label360;
        }
        localStringBuilder.append("null");
      }
    }
    for (;;)
    {
      localStringBuilder.append(")");
      return localStringBuilder.toString();
      localStringBuilder.append(action);
      break;
      label313:
      TBaseHelper.toString(pushAction, localStringBuilder);
      break label127;
      label324:
      localStringBuilder.append(appid);
      break label169;
      label336:
      localStringBuilder.append(packageName);
      break label211;
      label348:
      localStringBuilder.append(target);
      break label246;
      label360:
      localStringBuilder.append(metaInfo);
    }
  }
  
  public void validate()
    throws TException
  {
    if (action == null) {
      throw new TProtocolException("Required field 'action' was not present! Struct: " + toString());
    }
    if (pushAction == null) {
      throw new TProtocolException("Required field 'pushAction' was not present! Struct: " + toString());
    }
    if (target == null) {
      throw new TProtocolException("Required field 'target' was not present! Struct: " + toString());
    }
  }
  
  public void write(TProtocol paramTProtocol)
    throws TException
  {
    validate();
    paramTProtocol.writeStructBegin(STRUCT_DESC);
    if (action != null)
    {
      paramTProtocol.writeFieldBegin(ACTION_FIELD_DESC);
      paramTProtocol.writeI32(action.getValue());
      paramTProtocol.writeFieldEnd();
    }
    paramTProtocol.writeFieldBegin(ENCRYPT_ACTION_FIELD_DESC);
    paramTProtocol.writeBool(encryptAction);
    paramTProtocol.writeFieldEnd();
    paramTProtocol.writeFieldBegin(IS_REQUEST_FIELD_DESC);
    paramTProtocol.writeBool(isRequest);
    paramTProtocol.writeFieldEnd();
    if (pushAction != null)
    {
      paramTProtocol.writeFieldBegin(PUSH_ACTION_FIELD_DESC);
      paramTProtocol.writeBinary(pushAction);
      paramTProtocol.writeFieldEnd();
    }
    if ((appid != null) && (isSetAppid()))
    {
      paramTProtocol.writeFieldBegin(APPID_FIELD_DESC);
      paramTProtocol.writeString(appid);
      paramTProtocol.writeFieldEnd();
    }
    if ((packageName != null) && (isSetPackageName()))
    {
      paramTProtocol.writeFieldBegin(PACKAGE_NAME_FIELD_DESC);
      paramTProtocol.writeString(packageName);
      paramTProtocol.writeFieldEnd();
    }
    if (target != null)
    {
      paramTProtocol.writeFieldBegin(TARGET_FIELD_DESC);
      target.write(paramTProtocol);
      paramTProtocol.writeFieldEnd();
    }
    if ((metaInfo != null) && (isSetMetaInfo()))
    {
      paramTProtocol.writeFieldBegin(META_INFO_FIELD_DESC);
      metaInfo.write(paramTProtocol);
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
      APPID = new _Fields("APPID", 4, (short)5, "appid");
      PACKAGE_NAME = new _Fields("PACKAGE_NAME", 5, (short)6, "packageName");
      TARGET = new _Fields("TARGET", 6, (short)7, "target");
      META_INFO = new _Fields("META_INFO", 7, (short)8, "metaInfo");
      $VALUES = new _Fields[] { ACTION, ENCRYPT_ACTION, IS_REQUEST, PUSH_ACTION, APPID, PACKAGE_NAME, TARGET, META_INFO };
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
 * Qualified Name:     com.xiaomi.xmpush.thrift.XmPushActionContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */