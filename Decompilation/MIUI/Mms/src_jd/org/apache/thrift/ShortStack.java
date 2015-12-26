package org.apache.thrift;

public class ShortStack
{
  private int top = -1;
  private short[] vector;
  
  public ShortStack(int paramInt)
  {
    vector = new short[paramInt];
  }
  
  private void grow()
  {
    short[] arrayOfShort = new short[vector.length * 2];
    System.arraycopy(vector, 0, arrayOfShort, 0, vector.length);
    vector = arrayOfShort;
  }
  
  public void clear()
  {
    top = -1;
  }
  
  public short pop()
  {
    short[] arrayOfShort = vector;
    int i = top;
    top = (i - 1);
    return arrayOfShort[i];
  }
  
  public void push(short paramShort)
  {
    if (vector.length == top + 1) {
      grow();
    }
    short[] arrayOfShort = vector;
    int i = top + 1;
    top = i;
    arrayOfShort[i] = paramShort;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<ShortStack vector:[");
    int i = 0;
    while (i < vector.length)
    {
      if (i != 0) {
        localStringBuilder.append(" ");
      }
      if (i == top) {
        localStringBuilder.append(">>");
      }
      localStringBuilder.append(vector[i]);
      if (i == top) {
        localStringBuilder.append("<<");
      }
      i += 1;
    }
    localStringBuilder.append("]>");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     org.apache.thrift.ShortStack
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */