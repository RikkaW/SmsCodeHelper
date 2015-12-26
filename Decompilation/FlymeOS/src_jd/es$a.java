import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class es$a
  extends BaseAdapter
{
  private HashMap<String, Boolean> b = new HashMap();
  
  public es$a(es parames)
  {
    int i = 0;
    int j = -1;
    if (i < a.length())
    {
      if (a.optJSONObject(i).optString("name").equals(es.b(parames))) {
        j = i;
      }
      if (j == i) {
        b.put(String.valueOf(i), Boolean.valueOf(true));
      }
      for (;;)
      {
        if ((j == -1) && (i == a.length() - 1)) {
          b.put(String.valueOf(i), Boolean.valueOf(true));
        }
        i += 1;
        break;
        b.put(String.valueOf(i), Boolean.valueOf(false));
      }
    }
  }
  
  public int getCount()
  {
    return a.a.length();
  }
  
  public Object getItem(int paramInt)
  {
    try
    {
      JSONObject localJSONObject = a.a.getJSONObject(paramInt);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return 0L;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = a.getLayoutInflater().inflate(br.e.duoqu_list_items_content, null);
      paramViewGroup = new es.b(a);
      b = ((RadioButton)paramView.findViewById(br.d.item_rb));
      a = ((TextView)paramView.findViewById(br.d.item_text));
      paramView.setTag(paramViewGroup);
      a.setText(((JSONObject)getItem(paramInt)).optString("name"));
      b.setOnClickListener(new ev(this, paramInt));
      paramView.setOnClickListener(new ew(this, paramInt));
      if ((b.get(String.valueOf(paramInt)) != null) && (((Boolean)b.get(String.valueOf(paramInt))).booleanValue())) {
        break label205;
      }
      b.put(String.valueOf(paramInt), Boolean.valueOf(false));
    }
    label205:
    for (boolean bool = false;; bool = true)
    {
      b.setChecked(bool);
      if (!bool) {
        break label211;
      }
      es.a(a, "#62be66", br.a.duoqu_meizu_dialog_textcolor_green);
      return paramView;
      paramViewGroup = (es.b)paramView.getTag();
      break;
    }
    label211:
    es.a(a, "#000000", br.a.duoqu_meizu_dialog_textcolor_black);
    return paramView;
  }
}

/* Location:
 * Qualified Name:     es.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */