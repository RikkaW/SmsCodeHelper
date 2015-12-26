package org.apache.thrift.meta_data;

import org.apache.thrift.TBase;

public class StructMetaData
  extends FieldValueMetaData
{
  public final Class<? extends TBase> structClass;
  
  public StructMetaData(byte paramByte, Class<? extends TBase> paramClass)
  {
    super(paramByte);
    structClass = paramClass;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.StructMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */