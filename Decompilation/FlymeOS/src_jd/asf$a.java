import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class asf$a
{
  String a;
  int b;
  
  public static a a(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      a locala = new a();
      Object localObject = paramString.keys();
      if ((localObject != null) && (((Iterator)localObject).hasNext()))
      {
        localObject = (String)((Iterator)localObject).next();
        int i = paramString.getInt((String)localObject);
        a = ((String)localObject);
        b = i;
      }
      return locala;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static List<a> b(String paramString)
  {
    localArrayList = new ArrayList();
    try
    {
      paramString = new JSONArray(paramString);
      if ((paramString != null) && (paramString.length() > 1))
      {
        int i = 0;
        for (;;)
        {
          if (i >= paramString.length()) {
            return localArrayList;
          }
          a locala = a(paramString.getString(i));
          if (locala != null) {
            localArrayList.add(locala);
          }
          i += 1;
        }
      }
      return localArrayList;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     asf.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */