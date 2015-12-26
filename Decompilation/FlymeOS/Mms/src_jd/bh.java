import java.util.Date;
import java.util.zip.ZipEntry;

public class bh
  extends ZipEntry
{
  String a;
  long b = -1L;
  long c = -1L;
  long d = -1L;
  long e = -1L;
  int f = -1;
  byte[] g;
  int h;
  int i;
  
  public bh(String paramString)
  {
    super(paramString);
    a = paramString;
  }
  
  private static long a(long paramLong)
  {
    Date localDate = new Date(paramLong);
    int j = localDate.getYear() + 1900;
    if (j < 1980) {
      return 2162688L;
    }
    int k = localDate.getMonth();
    int m = localDate.getDate();
    int n = localDate.getHours();
    int i1 = localDate.getMinutes();
    return localDate.getSeconds() >> 1 | j - 1980 << 25 | k + 1 << 21 | m << 16 | n << 11 | i1 << 5;
  }
  
  public void setTime(long paramLong)
  {
    b = a(paramLong);
  }
}

/* Location:
 * Qualified Name:     bh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */