import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class afh
  extends afc
{
  static final Set<String> a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "BEGIN", "END", "LOGO", "PHOTO", "LABEL", "FN", "TITLE", "SOUND", "VERSION", "TEL", "EMAIL", "TZ", "GEO", "NOTE", "URL", "BDAY", "ROLE", "REV", "UID", "KEY", "MAILER", "NAME", "PROFILE", "SOURCE", "NICKNAME", "CLASS", "SORT-STRING", "CATEGORIES", "PRODID", "IMPP" })));
  static final Set<String> b = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "7BIT", "8BIT", "BASE64", "B" })));
  private final afe c;
  
  public afh()
  {
    c = new afe();
  }
  
  public afh(int paramInt)
  {
    c = new afe(paramInt);
  }
  
  public void a(afb paramafb)
  {
    c.a(paramafb);
  }
  
  public void a(InputStream paramInputStream)
  {
    c.a(paramInputStream);
  }
}

/* Location:
 * Qualified Name:     afh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */