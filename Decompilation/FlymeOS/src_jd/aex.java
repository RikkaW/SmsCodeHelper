import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class aex
{
  static String a = "v21_generic";
  public static int b = -1073741824;
  private static final Map<String, Integer> c = new HashMap();
  private static final Set<Integer> d;
  
  static
  {
    c.put(a, Integer.valueOf(-1073741824));
    c.put("v30_generic", Integer.valueOf(-1073741823));
    c.put("v21_europe", Integer.valueOf(-1073741820));
    c.put("v30_europe", Integer.valueOf(-1073741819));
    c.put("v21_japanese_utf8", Integer.valueOf(-1073741816));
    c.put("v30_japanese_utf8", Integer.valueOf(-1073741815));
    c.put("v21_japanese_mobile", Integer.valueOf(402653192));
    c.put("docomo", Integer.valueOf(939524104));
    d = new HashSet();
    d.add(Integer.valueOf(-1073741816));
    d.add(Integer.valueOf(-1073741815));
    d.add(Integer.valueOf(402653192));
    d.add(Integer.valueOf(939524104));
  }
  
  public static boolean a(int paramInt)
  {
    return (paramInt & 0x3) == 0;
  }
  
  public static boolean b(int paramInt)
  {
    return (paramInt & 0x3) == 1;
  }
  
  public static boolean c(int paramInt)
  {
    return (paramInt & 0x3) == 2;
  }
  
  public static int d(int paramInt)
  {
    return paramInt & 0xC;
  }
  
  public static boolean e(int paramInt)
  {
    return d.contains(Integer.valueOf(paramInt));
  }
  
  static boolean f(int paramInt)
  {
    return (0x2000000 & paramInt) != 0;
  }
}

/* Location:
 * Qualified Name:     aex
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */