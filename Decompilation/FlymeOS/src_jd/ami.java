import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class ami
{
  public String a;
  public List<Pair<String, String>> b;
  
  public ami(String paramString, List<Pair<String, String>> paramList)
  {
    a = paramString;
    b = paramList;
  }
  
  public void a(amm paramamm)
  {
    if (paramamm != null)
    {
      String str1 = paramamm.a();
      String str2 = paramamm.b();
      long l = paramamm.c();
      if ((!TextUtils.isEmpty(str1)) || (!TextUtils.isEmpty(str2)) || (l > 0L))
      {
        if (b == null) {
          b = new ArrayList(2);
        }
        if (!TextUtils.isEmpty(str1)) {
          b.add(new Pair("Mz_md5", str1));
        }
        if (!TextUtils.isEmpty(str2)) {
          b.add(new Pair("Mz_partial_md5", str2));
        }
        if (l > 0L) {
          b.add(new Pair("Mz_size", String.valueOf(l)));
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     ami
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */