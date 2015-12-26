import android.content.Context;
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class aof
  extends ath<NumItem>
{
  private static final String a = aof.class.getSimpleName();
  private aod b;
  private Context c;
  private String d;
  private aod.a e = aod.a.c;
  private int f = 0;
  private ape g;
  
  protected NumItem a(String paramString, boolean paramBoolean)
  {
    Object localObject1 = apm.b(paramString);
    Log.i(a, d + " json: " + (String)localObject1);
    i = 0;
    for (;;)
    {
      try
      {
        localObject1 = new JSONObject((String)localObject1);
        int j = ((JSONObject)localObject1).getInt("code");
        if (j != 0) {
          f = j;
        }
        if (!((JSONObject)localObject1).has("data")) {
          continue;
        }
        localObject1 = ((JSONObject)localObject1).get("data");
        if ((localObject1 == null) || (!(localObject1 instanceof JSONObject))) {
          continue;
        }
        localObject1 = (JSONObject)localObject1;
        if ((!((JSONObject)localObject1).has("status")) || (((JSONObject)localObject1).getInt("status") != -1)) {
          continue;
        }
        j = ((JSONObject)localObject1).getInt("status");
        i = j;
        localObject1 = null;
      }
      catch (JSONException localJSONException)
      {
        Log.e(a, "parser network number json error:", localJSONException);
        Object localObject2 = null;
        continue;
        e = aod.a.c;
        if (i != -1) {
          continue;
        }
        aoh.a(c).d(d);
        continue;
        aoh.a(c).c(d);
        continue;
      }
      if (localObject1 == null) {
        continue;
      }
      e = aod.a.a;
      ((NumItem)localObject1).c(d);
      aoh.a(c).a(d, paramString, ((NumItem)localObject1).f());
      paramString = (String)localObject1;
      if (g != null)
      {
        paramString = (String)localObject1;
        if (localObject1 == null)
        {
          paramString = new NumItem();
          paramString.c(d);
        }
        paramString.d(g.a());
        paramString.a(g.b());
      }
      return paramString;
      localObject1 = apj.a((JSONObject)localObject1);
    }
  }
  
  public void a(int paramInt, Header[] paramArrayOfHeader, String paramString, NumItem paramNumItem)
  {
    if (b != null) {
      b.a(e, paramNumItem);
    }
  }
  
  public void a(int paramInt, Header[] paramArrayOfHeader, Throwable paramThrowable, String paramString, NumItem paramNumItem)
  {
    if (b != null) {
      b.a(paramThrowable);
    }
  }
  
  public void a(Context paramContext)
  {
    c = paramContext.getApplicationContext();
  }
  
  public void a(aod paramaod)
  {
    b = paramaod;
  }
  
  public void a(ape paramape)
  {
    g = paramape;
  }
  
  public void a(String paramString)
  {
    d = paramString;
  }
}

/* Location:
 * Qualified Name:     aof
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */