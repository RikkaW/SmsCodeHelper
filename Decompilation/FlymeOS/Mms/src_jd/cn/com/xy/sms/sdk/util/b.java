package cn.com.xy.sms.sdk.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class b
{
  private static String a = "(?:(?:http|https|ftp)://)?(?:[a-zA-Z0-9]{1,30})(?:\\.[a-zA-Z0-9]{1,30}){1,4}(?:[/?][^\\s]+)?|谨防|诈骗|(?:温馨|特别)?提[醒示]|泄露|回复|屏蔽|拨打|[致速]电|呼叫|请勿|勿向|注意";
  private static Pattern b = Pattern.compile("(?:(?:http|https|ftp)://)?(?:[a-zA-Z0-9]{1,30})(?:\\.[a-zA-Z0-9]{1,30}){1,4}(?:[/?][^\\s]+)?|谨防|诈骗|(?:温馨|特别)?提[醒示]|泄露|回复|屏蔽|拨打|[致速]电|呼叫|请勿|勿向|注意");
  private static String c = "([\\[〔])|([〕\\]])";
  private static Pattern d = Pattern.compile("([\\[〔])|([〕\\]])");
  
  public static String a(String paramString)
  {
    Object localObject = d.matcher(paramString);
    if (((Matcher)localObject).find())
    {
      paramString = new StringBuffer();
      do
      {
        String str1 = ((Matcher)localObject).group(1);
        String str2 = ((Matcher)localObject).group(2);
        if (str1 != null) {
          ((Matcher)localObject).appendReplacement(paramString, "【");
        }
        if (str2 != null) {
          ((Matcher)localObject).appendReplacement(paramString, "】");
        }
      } while (((Matcher)localObject).find());
      ((Matcher)localObject).appendTail(paramString);
      paramString = paramString.toString();
    }
    localObject = paramString.replaceAll("([:： ])[:： ]+", "$1").replaceAll("([,，。；！!;\\?][^【,，。；！!;\\?]*)【(?=[^】]*[,，。；！!;\\?])[^】]+】", "$1:").replaceFirst("[\\(（【]\\d/\\d[\\)）】]", "");
    int i = ((String)localObject).length();
    int j;
    if ('【' == ((String)localObject).charAt(0))
    {
      j = ((String)localObject).indexOf('】');
      if (j != -1)
      {
        paramString = ((String)localObject).substring(1, j);
        if (!b(paramString)) {}
      }
    }
    do
    {
      return paramString;
      i -= 1;
      if ('】' != ((String)localObject).charAt(i)) {
        break;
      }
      j = ((String)localObject).lastIndexOf('【');
      if (j < 0) {
        break;
      }
      localObject = ((String)localObject).substring(j + 1, i);
      paramString = (String)localObject;
    } while (b((String)localObject));
    return null;
  }
  
  private static boolean b(String paramString)
  {
    return (paramString != null) && (paramString.trim().length() > 0) && (!b.matcher(paramString).find());
  }
  
  private static String c(String paramString)
  {
    Matcher localMatcher = d.matcher(paramString);
    if (localMatcher.find())
    {
      paramString = new StringBuffer();
      do
      {
        String str1 = localMatcher.group(1);
        String str2 = localMatcher.group(2);
        if (str1 != null) {
          localMatcher.appendReplacement(paramString, "【");
        }
        if (str2 != null) {
          localMatcher.appendReplacement(paramString, "】");
        }
      } while (localMatcher.find());
      localMatcher.appendTail(paramString);
      paramString = paramString.toString();
    }
    return paramString.replaceAll("([:： ])[:： ]+", "$1").replaceAll("([,，。；！!;\\?][^【,，。；！!;\\?]*)【(?=[^】]*[,，。；！!;\\?])[^】]+】", "$1:").replaceFirst("[\\(（【]\\d/\\d[\\)）】]", "");
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */