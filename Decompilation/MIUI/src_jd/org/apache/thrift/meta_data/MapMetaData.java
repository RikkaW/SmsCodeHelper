package org.apache.thrift.meta_data;

public class MapMetaData
  extends FieldValueMetaData
{
  public final FieldValueMetaData keyMetaData;
  public final FieldValueMetaData valueMetaData;
  
  public MapMetaData(byte paramByte, FieldValueMetaData paramFieldValueMetaData1, FieldValueMetaData paramFieldValueMetaData2)
  {
    super(paramByte);
    keyMetaData = paramFieldValueMetaData1;
    valueMetaData = paramFieldValueMetaData2;
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.meta_data.MapMetaData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */