package org.apache.thrift.meta_data;

public class SetMetaData
  extends FieldValueMetaData
{
  public final FieldValueMetaData elemMetaData;
  
  public SetMetaData(byte paramByte, FieldValueMetaData paramFieldValueMetaData)
  {
    super(paramByte);
    elemMetaData = paramFieldValueMetaData;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.SetMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */