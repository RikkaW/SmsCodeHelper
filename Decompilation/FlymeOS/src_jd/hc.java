import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Pair;

final class hc
  extends AsyncTask<Void, Void, Pair<String, String>>
{
  hc(hb paramhb, gr paramgr, long paramLong, Runnable paramRunnable) {}
  
  protected Pair<String, String> a(Void... paramVarArgs)
  {
    String str = hb.a(a, b);
    Object localObject = null;
    paramVarArgs = (Void[])localObject;
    if (TextUtils.isEmpty(str))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Uri localUri = hb.a(hb.a(a), b, localStringBuilder, c);
      paramVarArgs = (Void[])localObject;
      if (localUri != null)
      {
        paramVarArgs = (Void[])localObject;
        if (hb.a(a, localUri)) {
          paramVarArgs = localStringBuilder.toString();
        }
      }
    }
    return new Pair(str, paramVarArgs);
  }
  
  protected void a(Pair<String, String> paramPair)
  {
    if (!TextUtils.isEmpty((CharSequence)first))
    {
      hb.a(a, true);
      a.a((CharSequence)first);
    }
    if (second != null)
    {
      hb.b(a, true);
      a.a((CharSequence)second, false);
    }
    if (d != null) {
      d.run();
    }
  }
}

/* Location:
 * Qualified Name:     hc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */