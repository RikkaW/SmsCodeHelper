import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class bd
{
  private static String a = bd.class.getSimpleName();
  private List<be> b;
  private List<be> c;
  private List<be> d;
  private List<be> e;
  
  public static bd a(String paramString)
  {
    Object localObject = null;
    bd localbd;
    try
    {
      paramString = new JSONObject(paramString);
      if (paramString == null) {
        return (bd)localObject;
      }
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        if (aux.a) {
          Log.e(a, "error formatted json string", paramString);
        }
        paramString = null;
      }
      localbd = new bd();
      if (paramString.isNull("a")) {}
    }
    for (;;)
    {
      try
      {
        localObject = paramString.getJSONArray("a");
        localArrayList = new ArrayList();
        i = 0;
        if (i < ((JSONArray)localObject).length()) {
          continue;
        }
        localbd.b(localArrayList);
      }
      catch (JSONException localJSONException1)
      {
        if (!aux.a) {
          continue;
        }
        Log.e(a, "error formatted json string", localJSONException1);
        continue;
        localArrayList.add(be.a(localJSONException1.getJSONObject(i)));
        i += 1;
        continue;
      }
      if (!paramString.isNull("r")) {}
      try
      {
        localObject = paramString.getJSONArray("r");
        localArrayList = new ArrayList();
        i = 0;
        if (i < ((JSONArray)localObject).length()) {
          continue;
        }
        localbd.d(localArrayList);
      }
      catch (JSONException localJSONException2)
      {
        if (!aux.a) {
          continue;
        }
        Log.e(a, "error formatted json string", localJSONException2);
        continue;
        localArrayList.add(be.a(localJSONException2.getJSONObject(i)));
        i += 1;
        continue;
      }
      if (!paramString.isNull("d")) {}
      try
      {
        localObject = paramString.getJSONArray("d");
        localArrayList = new ArrayList();
        i = 0;
        if (i < ((JSONArray)localObject).length()) {
          break label320;
        }
        localbd.c(localArrayList);
      }
      catch (JSONException localJSONException3)
      {
        if (!aux.a) {
          continue;
        }
        Log.e(a, "error formatted json string", localJSONException3);
        continue;
        localJSONException3.add(be.a(paramString.getJSONObject(i)));
        i += 1;
        continue;
      }
      localObject = localbd;
      if (paramString.isNull("u")) {
        break;
      }
      try
      {
        paramString = paramString.getJSONArray("u");
        localObject = new ArrayList();
        i = 0;
        if (i < paramString.length()) {
          break label363;
        }
        localbd.a((List)localObject);
        return localbd;
      }
      catch (JSONException paramString)
      {
        localObject = localbd;
      }
      if (!aux.a) {
        break;
      }
      Log.e(a, "error formatted json string", paramString);
      return localbd;
      localArrayList.add(be.a(((JSONArray)localObject).getJSONObject(i)));
      i += 1;
    }
  }
  
  public List<be> a()
  {
    return b;
  }
  
  public void a(List<be> paramList)
  {
    b = paramList;
  }
  
  public List<be> b()
  {
    return c;
  }
  
  public void b(List<be> paramList)
  {
    c = paramList;
  }
  
  public List<be> c()
  {
    return e;
  }
  
  public void c(List<be> paramList)
  {
    e = paramList;
  }
  
  public List<be> d()
  {
    return d;
  }
  
  public void d(List<be> paramList)
  {
    d = paramList;
  }
  
  public String e()
  {
    JSONObject localJSONObject = new JSONObject();
    JSONArray localJSONArray;
    Iterator localIterator;
    if ((b() != null) && (b().size() > 0))
    {
      localJSONArray = new JSONArray();
      localIterator = b().iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {}
      try
      {
        localJSONObject.put("a", localJSONArray);
        if ((c() != null) && (c().size() > 0))
        {
          localJSONArray = new JSONArray();
          localIterator = c().iterator();
          label99:
          if (localIterator.hasNext()) {
            break label249;
          }
        }
        try
        {
          localJSONObject.put("d", localJSONArray);
          if ((a() != null) && (a().size() > 0))
          {
            localJSONArray = new JSONArray();
            localIterator = a().iterator();
            label153:
            if (localIterator.hasNext()) {
              break label269;
            }
          }
          try
          {
            localJSONObject.put("u", localJSONArray);
            if ((d() != null) && (d().size() > 0))
            {
              localJSONArray = new JSONArray();
              localIterator = d().iterator();
              if (localIterator.hasNext()) {
                break label289;
              }
            }
            try
            {
              localJSONObject.put("r", localJSONArray);
              return localJSONObject.toString();
              localJSONArray.put(((be)localIterator.next()).e());
              continue;
              label249:
              localJSONArray.put(((be)localIterator.next()).e());
              break label99;
              label269:
              localJSONArray.put(((be)localIterator.next()).e());
              break label153;
              label289:
              localJSONArray.put(((be)localIterator.next()).e());
            }
            catch (JSONException localJSONException1)
            {
              for (;;) {}
            }
          }
          catch (JSONException localJSONException2)
          {
            for (;;) {}
          }
        }
        catch (JSONException localJSONException3)
        {
          for (;;) {}
        }
      }
      catch (JSONException localJSONException4)
      {
        for (;;) {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     bd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */