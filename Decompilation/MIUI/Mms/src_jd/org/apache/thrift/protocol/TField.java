package org.apache.thrift.protocol;

public class TField
{
  public final short id;
  public final String name;
  public final byte type;
  
  public TField()
  {
    this("", (byte)0, (short)0);
  }
  
  public TField(String paramString, byte paramByte, short paramShort)
  {
    name = paramString;
    type = paramByte;
    id = paramShort;
  }
  
  public String toString()
  {
    return "<TField name:'" + name + "' type:" + type + " field-id:" + id + ">";
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.protocol.TField
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */