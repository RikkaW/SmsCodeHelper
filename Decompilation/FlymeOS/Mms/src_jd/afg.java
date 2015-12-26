import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class afg
  extends afc
{
  static final Set<String> a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "BEGIN", "END", "LOGO", "PHOTO", "LABEL", "FN", "TITLE", "SOUND", "VERSION", "TEL", "EMAIL", "TZ", "GEO", "NOTE", "URL", "BDAY", "ROLE", "REV", "UID", "KEY", "MAILER" })));
  static final Set<String> b = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "DOM", "INTL", "POSTAL", "PARCEL", "HOME", "WORK", "PREF", "VOICE", "FAX", "MSG", "CELL", "PAGER", "BBS", "MODEM", "CAR", "ISDN", "VIDEO", "AOL", "APPLELINK", "ATTMAIL", "CIS", "EWORLD", "INTERNET", "IBMMAIL", "MCIMAIL", "POWERSHARE", "PRODIGY", "TLX", "X400", "GIF", "CGM", "WMF", "BMP", "MET", "PMB", "DIB", "PICT", "TIFF", "PDF", "PS", "JPEG", "QTIME", "MPEG", "MPEG2", "AVI", "WAVE", "AIFF", "PCM", "X509", "PGP" })));
  static final Set<String> c = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "INLINE", "URL", "CONTENT-ID", "CID" })));
  static final Set<String> d = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] { "7BIT", "8BIT", "QUOTED-PRINTABLE", "BASE64", "B" })));
  private final afd e;
  
  public afg()
  {
    e = new afd();
  }
  
  public afg(int paramInt)
  {
    e = new afd(paramInt);
  }
  
  public void a(afb paramafb)
  {
    e.a(paramafb);
  }
  
  public void a(InputStream paramInputStream)
  {
    e.a(paramInputStream);
  }
}

/* Location:
 * Qualified Name:     afg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */