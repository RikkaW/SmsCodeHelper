import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ex$a
  extends BaseAdapter
{
  private HashMap<String, Boolean> b = new HashMap();
  
  public ex$a(ex paramex)
  {
    int i = 0;
    while (i < a.length())
    {
      a.optJSONObject(i).optString("num");
      i += 1;
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
      paramViewGroup = new ex.b(a);
      b = ((RadioButton)paramView.findViewById(br.d.item_rb));
      a = ((TextView)paramView.findViewById(br.d.item_text));
      paramView.setTag(paramViewGroup);
      a.setText(((JSONObject)getItem(paramInt)).optString("num"));
      b.setOnClickListener(new fa(this, paramInt));
      paramView.setOnClickListener(new fb(this, paramInt));
      if ((b.get(String.valueOf(paramInt)) == null) || (!((Boolean)b.get(String.valueOf(paramInt))).booleanValue())) {
        b.put(String.valueOf(paramInt), Boolean.valueOf(false));
      }
      if (ex.c(a).getValue("db_air_data_index") == null) {
        break label259;
      }
    }
    label259:
    for (int i = Integer.parseInt((String)ex.c(a).getValue("db_air_data_index"));; i = 0)
    {
      if (paramInt == i)
      {
        b.setChecked(true);
        ex.a(a, "#62be66", br.a.duoqu_meizu_dialog_textcolor_green);
        return paramView;
        paramViewGroup = (ex.b)paramView.getTag();
        break;
      }
      b.setChecked(false);
      ex.a(a, "#000000", br.a.duoqu_meizu_dialog_textcolor_black);
      return paramView;
    }
  }
}

/* Location:
 * Qualified Name:     ex.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */