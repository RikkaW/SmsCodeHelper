import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asz
  extends asv
{
  public boolean a(String paramString)
  {
    Matcher localMatcher = Pattern.compile("回|编辑|发送").matcher(paramString);
    paramString = Pattern.compile("序号|序列|编码|评价|更多业务|业务种类|获取服务").matcher(paramString);
    return (localMatcher.find()) && (paramString.find());
  }
  
  public boolean b(String paramString)
  {
    if (paramString.length() == 1) {}
    while (Pattern.compile("[a-z0-9A-Z:/]+").matcher(paramString).matches()) {
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     asz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */