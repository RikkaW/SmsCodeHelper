package com.xiaomi.smack.packet;

public class StreamError
{
  private String code;
  
  public StreamError(String paramString)
  {
    code = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("stream:error (").append(code).append(")");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.packet.StreamError
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */