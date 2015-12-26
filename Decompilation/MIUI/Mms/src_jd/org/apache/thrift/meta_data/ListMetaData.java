package org.apache.thrift.meta_data;

public class ListMetaData
  extends FieldValueMetaData
{
  public final FieldValueMetaData elemMetaData;
  
  public ListMetaData(byte paramByte, FieldValueMetaData paramFieldValueMetaData)
  {
    super(paramByte);
    elemMetaData = paramFieldValueMetaData;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.ListMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */