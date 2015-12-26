import android.os.AsyncTask;
import java.util.HashMap;
import java.util.Map;

class amw
  extends AsyncTask<Void, Void, Void>
{
  amw(amv paramamv, int paramInt1, int paramInt2, String paramString) {}
  
  protected Void a(Void... paramVarArgs)
  {
    paramVarArgs = new HashMap();
    paramVarArgs.put("result_mark", String.valueOf(a));
    paramVarArgs.put("rescode", String.valueOf(b));
    if (c != null) {
      paramVarArgs.put("msg", c);
    }
    String str1 = anl.d(amv.a(d));
    String str2 = anl.c(amv.a(d));
    String str3 = anl.b(amv.a(d));
    String str4 = anl.a(amv.a(d));
    if (str1 != null) {
      paramVarArgs.put("local_model", str1);
    }
    if (str2 != null) {
      paramVarArgs.put("android_version", str2);
    }
    if (str3 != null) {
      paramVarArgs.put("flyme_version", str3);
    }
    if (str4 != null) {
      paramVarArgs.put("app_version", str4);
    }
    amv.a(d, paramVarArgs, "http://u.meizu.com/appupgrade/check");
    return null;
  }
}

/* Location:
 * Qualified Name:     amw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */