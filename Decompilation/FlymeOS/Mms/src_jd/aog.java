import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import com.ted.android.contacts.netparser.model.NumItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aog
  extends ath<List<NumItem>>
{
  private static final String a = aog.class.getSimpleName();
  private Context b;
  private List<String> c;
  private int d = 0;
  private aod.a e = aod.a.c;
  private aod f;
  private List<ape> g;
  
  private boolean a(String paramString, List<NumItem> paramList)
  {
    paramList = paramList.iterator();
    do
    {
      if (!paramList.hasNext()) {
        return false;
      }
    } while (!paramString.equals(((NumItem)paramList.next()).a()));
    return true;
  }
  
  private void c(List<NumItem> paramList)
  {
    if (g == null) {
      return;
    }
    Iterator localIterator = g.iterator();
    label18:
    ape localape;
    Object localObject;
    if (localIterator.hasNext())
    {
      localape = (ape)localIterator.next();
      localObject = paramList.iterator();
    }
    for (int i = 0;; i = 1)
    {
      NumItem localNumItem;
      do
      {
        if (!((Iterator)localObject).hasNext())
        {
          if (i != 0) {
            break label18;
          }
          localObject = new NumItem();
          ((NumItem)localObject).c(localape.c());
          ((NumItem)localObject).d(localape.a());
          ((NumItem)localObject).a(localape.b());
          paramList.add(localObject);
          break label18;
          break;
        }
        localNumItem = (NumItem)((Iterator)localObject).next();
      } while ((localape.c() == null) || (!localape.c().equals(localNumItem.a())));
      localNumItem.d(localape.a());
      localNumItem.a(localape.b());
    }
  }
  
  private void d(List<NumItem> paramList)
  {
    Iterator localIterator = c.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      String str = (String)localIterator.next();
      if (!a(str, paramList))
      {
        Log.i(a, "not recognized: " + str);
        aoh.a(b).c(str);
      }
    }
  }
  
  protected List<NumItem> a(String paramString, boolean paramBoolean)
  {
    JSONObject localJSONObject = null;
    i = 0;
    if (paramBoolean) {
      return null;
    }
    paramString = apm.b(paramString);
    localArrayList = new ArrayList();
    Log.i(a, "NumListHttpResponseHandler parseResponse, size: " + c.size());
    for (;;)
    {
      try
      {
        paramString = new JSONObject(paramString);
        int j = paramString.getInt("code");
        if (j != 0) {
          d = j;
        }
        if (paramString.has("data"))
        {
          paramString = paramString.get("data");
          if ((paramString instanceof JSONArray))
          {
            localJSONArray = (JSONArray)paramString;
            paramString = localJSONObject;
            j = localJSONArray.length();
            if (i < j) {
              continue;
            }
          }
        }
      }
      catch (JSONException paramString)
      {
        JSONArray localJSONArray;
        Log.e(a, "parser network number json error:", paramString);
        continue;
        e = aod.a.c;
        continue;
      }
      c(localArrayList);
      if (localArrayList.size() <= 0) {
        continue;
      }
      e = aod.a.a;
      if (f != null) {
        f.a(e, localArrayList);
      }
      d(localArrayList);
      return localArrayList;
      try
      {
        localJSONObject = localJSONArray.getJSONObject(i);
        paramString = localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        Log.e(a, "JSONObject Convert Error:" + localJSONException.getMessage());
        continue;
        NumItem localNumItem = apj.a(paramString);
        if (localNumItem == null) {
          continue;
        }
        if ((i > c.size()) || (localNumItem.a() == null) || (!localNumItem.a().equals(apo.a((String)c.get(i))))) {
          continue;
        }
        localNumItem.c((String)c.get(i));
        localArrayList.add(localNumItem);
        String str1 = new JSONObject().put("data", paramString).toString();
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("json", apm.a(str1));
        localContentValues.put("m_t", Integer.valueOf(localNumItem.f()));
        localContentValues.put("c_t", Integer.valueOf((int)(System.currentTimeMillis() / 1000L)));
        String str2 = localNumItem.a();
        aoh.a(b).a(localContentValues, "phone = ?", new String[] { str2 });
        Log.i(a, "recognized: " + localNumItem.a() + "json: " + str1);
        continue;
      }
      if (paramString != null) {
        continue;
      }
      i += 1;
    }
  }
  
  public void a(int paramInt, Header[] paramArrayOfHeader, String paramString, List<NumItem> paramList) {}
  
  public void a(int paramInt, Header[] paramArrayOfHeader, Throwable paramThrowable, String paramString, List<NumItem> paramList) {}
  
  public void a(Context paramContext)
  {
    b = paramContext;
  }
  
  public void a(aod paramaod)
  {
    f = paramaod;
  }
  
  public void a(List<String> paramList)
  {
    Log.i(a, "setReqNumberList: " + paramList.size());
    c = paramList;
  }
  
  public void b(List<ape> paramList)
  {
    g = paramList;
  }
}

/* Location:
 * Qualified Name:     aog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */