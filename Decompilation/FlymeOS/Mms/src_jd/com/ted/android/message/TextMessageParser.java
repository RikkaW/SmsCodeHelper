package com.ted.android.message;

import java.util.regex.Pattern;

public class TextMessageParser
{
  private static Pattern a = Pattern.compile("^\\[姓名\\] (.*)\n\\[电话\\] (.*)");
  private static Pattern b = Pattern.compile("\\[姓名\\] (.*)");
  private static Pattern c = Pattern.compile("\\[(电话|公司|职位|邮箱|地址)\\] (.*)");
  
  public static class BusinessCardItem
  {
    public String name = null;
    public String phone = null;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.message.TextMessageParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */