package org.apache.thrift.meta_data;

public class EnumMetaData
  extends FieldValueMetaData
{
  public final Class enumClass;
  
  public EnumMetaData(byte paramByte, Class paramClass)
  {
    super(paramByte);
    enumClass = paramClass;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.EnumMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */