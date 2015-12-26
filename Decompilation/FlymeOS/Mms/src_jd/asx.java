import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asx
  extends asv
{
  public boolean a(String paramString)
  {
    return Pattern.compile("直接回复10为非常满意").matcher(paramString).find();
  }
}

/* Location:
 * Qualified Name:     asx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */