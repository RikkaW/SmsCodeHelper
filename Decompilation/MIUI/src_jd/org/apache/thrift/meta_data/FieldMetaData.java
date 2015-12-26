package org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;

public class FieldMetaData
  implements Serializable
{
  private static Map<Class<? extends TBase>, Map<?, FieldMetaData>> structMap = new HashMap();
  public final String fieldName;
  public final byte requirementType;
  public final FieldValueMetaData valueMetaData;
  
  public FieldMetaData(String paramString, byte paramByte, FieldValueMetaData paramFieldValueMetaData)
  {
    fieldName = paramString;
    requirementType = paramByte;
    valueMetaData = paramFieldValueMetaData;
  }
  
  public static void addStructMetaDataMap(Class<? extends TBase> paramClass, Map<?, FieldMetaData> paramMap)
  {
    structMap.put(paramClass, paramMap);
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.FieldMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */