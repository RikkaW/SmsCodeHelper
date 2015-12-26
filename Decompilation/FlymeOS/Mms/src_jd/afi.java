import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class afi
  extends afc
{
  static final Set<String> a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "BEGIN", "END", "VERSION", "SOURCE", "KIND", "FN", "N", "NICKNAME", "PHOTO", "BDAY", "ANNIVERSARY", "GENDER", "ADR", "TEL", "EMAIL", "IMPP", "LANG", "TZ", "GEO", "TITLE", "ROLE", "LOGO", "ORG", "MEMBER", "RELATED", "CATEGORIES", "NOTE", "PRODID", "REV", "SOUND", "UID", "CLIENTPIDMAP", "URL", "KEY", "FBURL", "CALENDRURI", "CALURI", "XML" })));
  static final Set<String> b = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "8BIT", "B" })));
  private final aff c;
  
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
 * Qualified Name:     afi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */