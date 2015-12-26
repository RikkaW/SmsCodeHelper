package com.xiaomi.smsunderstand;

public class AttrInfo
{
  private int endPosition;
  private int has;
  private String name;
  private int startPosition;
  private int type;
  private String value;
  
  public AttrInfo(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    name = paramString1;
    value = paramString2;
    type = paramInt1;
    has = paramInt2;
    startPosition = paramInt3;
    endPosition = paramInt4;
  }
  
  public int getEndPosition()
  {
    return endPosition;
  }
  
  public int getHas()
  {
    return has;
  }
  
  public String getName()
  {
    return name;
  }
  
  public int getStartPosition()
  {
    return startPosition;
  }
  
  public int getType()
  {
    return type;
  }
  
  public String getValue()
  {
    return value;
  }
  
  public void setEndPosition(int paramInt)
  {
    endPosition = paramInt;
  }
  
  public void setHas(int paramInt)
  {
    has = paramInt;
  }
  
  public void setStartPosition(int paramInt)
  {
    startPosition = paramInt;
  }
  
  public void setValue(String paramString)
  {
    value = paramString;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.AttrInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */