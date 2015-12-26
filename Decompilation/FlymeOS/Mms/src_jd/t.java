import java.io.IOException;
import java.io.StringReader;

public class t
  implements u
{
  public static String a(String paramString)
  {
    if (paramString == null) {
      throw new s("不是有效的时间表达式");
    }
    paramString = new StringReader(paramString.trim());
    StringBuffer localStringBuffer = new StringBuffer();
    for (;;)
    {
      int i;
      char c;
      try
      {
        i = paramString.read();
        if (i == -1)
        {
          if (localStringBuffer.length() != 10) {
            break label804;
          }
          localStringBuffer.append(" 00:00:00");
          if (localStringBuffer.length() == 19) {
            break;
          }
          throw new s("不是有效的时间表达式");
        }
        c = (char)i;
        if (localStringBuffer.length() >= 4) {
          break label142;
        }
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9)) {
          throw new s("年份必需为4位数字");
        }
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
        throw new s("不是有效的时间表达式");
      }
      localStringBuffer.append(c);
      continue;
      label142:
      if (localStringBuffer.length() == 4)
      {
        if (c != '-') {
          throw new s("日期分割符必需为“－”");
        }
        localStringBuffer.append(c);
      }
      else if (localStringBuffer.length() == 5)
      {
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9)) {
          throw new s("月份必需为2位以内的数字");
        }
        localStringBuffer.append(c);
        paramString.mark(0);
        c = (char)paramString.read();
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9))
        {
          localStringBuffer.insert(5, '0');
          paramString.reset();
        }
        else
        {
          localStringBuffer.append(c);
        }
      }
      else if (localStringBuffer.length() == 7)
      {
        if (c != '-') {
          throw new s("日期分割符必需为“－”");
        }
        localStringBuffer.append(c);
      }
      else if (localStringBuffer.length() == 8)
      {
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9)) {
          throw new s("日必需为2位以内的数字");
        }
        localStringBuffer.append(c);
        paramString.mark(0);
        c = (char)paramString.read();
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9))
        {
          localStringBuffer.insert(8, '0');
          paramString.reset();
        }
        else
        {
          localStringBuffer.append(c);
        }
      }
      else if (localStringBuffer.length() == 10)
      {
        if (c != ' ') {
          throw new s("日期后分割符必需为“ ”");
        }
        localStringBuffer.append(c);
      }
      else if (localStringBuffer.length() == 11)
      {
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9)) {
          throw new s("小时必需为2位以内的数字");
        }
        localStringBuffer.append(c);
        paramString.mark(0);
        c = (char)paramString.read();
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9))
        {
          localStringBuffer.insert(11, '0');
          paramString.reset();
        }
        else
        {
          localStringBuffer.append(c);
        }
      }
      else if (localStringBuffer.length() == 13)
      {
        if (c != ':') {
          throw new s("时间分割符必需为“:”");
        }
        localStringBuffer.append(c);
      }
      else if (localStringBuffer.length() == 14)
      {
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9)) {
          throw new s("分钟必需为2位以内的数字");
        }
        localStringBuffer.append(c);
        paramString.mark(0);
        c = (char)paramString.read();
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9))
        {
          localStringBuffer.insert(14, '0');
          paramString.reset();
        }
        else
        {
          localStringBuffer.append(c);
        }
      }
      else if (localStringBuffer.length() == 16)
      {
        if (c != ':') {
          throw new s("时间分割符必需为“:”");
        }
        localStringBuffer.append(c);
      }
      else if (localStringBuffer.length() == 17)
      {
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9)) {
          throw new s("秒必需为2位以内的数字");
        }
        localStringBuffer.append(c);
        paramString.mark(0);
        c = (char)paramString.read();
        i = "0123456789-:. ".indexOf(c);
        if ((i == -1) || (i > 9))
        {
          localStringBuffer.insert(17, '0');
          paramString.reset();
        }
        else
        {
          localStringBuffer.append(c);
        }
      }
      else
      {
        throw new s("不是有效的时间表达式");
        label804:
        if (localStringBuffer.length() == 16) {
          localStringBuffer.append(":00");
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  public p a(r paramr)
  {
    int i = paramr.a();
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramr.read();
    if ((j == -1) || (j != 91)) {
      throw new s("不是有效的时间开始");
    }
    do
    {
      char c = (char)j;
      if (c == ']') {
        return new p(a(localStringBuffer.toString()), i, p.a.h);
      }
      if ("0123456789-:. ".indexOf(c) == -1) {
        throw new s("时间类型不能包函非法字符：" + c);
      }
      localStringBuffer.append(c);
      j = paramr.read();
    } while (j != -1);
    throw new s("不是有效的时间结束");
  }
}

/* Location:
 * Qualified Name:     t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */