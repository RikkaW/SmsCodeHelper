import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

class et
  implements View.OnClickListener
{
  et(es parames, es.a parama) {}
  
  public void onClick(View paramView)
  {
    paramView = es.a.a(a).keySet().iterator();
    while (paramView.hasNext())
    {
      String str = (String)paramView.next();
      if (((Boolean)es.a.a(a).get(str)).equals(Boolean.valueOf(true))) {
        try
        {
          es.a(b, false);
          if (es.a(b) != null) {
            es.a(b).a(b.a.getJSONObject(Integer.parseInt(str)));
          }
          b.dismiss();
        }
        catch (NumberFormatException localNumberFormatException)
        {
          localNumberFormatException.printStackTrace();
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     et
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */