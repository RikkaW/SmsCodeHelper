package org.apache.thrift.meta_data;

import java.io.Serializable;

public class FieldValueMetaData
  implements Serializable
{
  private final boolean isTypedefType;
  public final byte type;
  private final String typedefName;
  
  public FieldValueMetaData(byte paramByte)
  {
    type = paramByte;
    isTypedefType = false;
    typedefName = null;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.FieldValueMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */