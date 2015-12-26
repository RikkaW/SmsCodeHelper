import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class asw
  extends asv
{
  public void a(asu paramasu)
  {
    if (!Pattern.compile("[0-9a-zA-Z]+").matcher(paramasu.a()).matches()) {}
    for (boolean bool = true;; bool = false)
    {
      paramasu.a(bool);
      return;
    }
  }
  
  public boolean b(String paramString)
  {
    return !paramString.equals("业务序号");
  }
}

/* Location:
 * Qualified Name:     asw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */