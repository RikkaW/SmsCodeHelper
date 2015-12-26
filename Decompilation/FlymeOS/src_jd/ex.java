import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ex
  extends Dialog
{
  public JSONArray a = null;
  private fc b = null;
  private boolean c = true;
  private Context d;
  private BusinessSmsMessage e;
  private int f = 0;
  private String g = "";
  private TextView h = null;
  
  public ex(fc paramfc, BusinessSmsMessage paramBusinessSmsMessage, JSONArray paramJSONArray, Context paramContext, int paramInt, String paramString)
  {
    super(paramContext, paramInt);
    a = paramJSONArray;
    b = paramfc;
    d = paramContext;
    e = paramBusinessSmsMessage;
    g = paramString;
  }
  
  public static void a(TextView paramTextView, String paramString, int paramInt)
  {
    if (paramTextView == null) {
      return;
    }
    int i = ResourceCacheUtil.parseColor(paramString);
    if (i != -1)
    {
      paramTextView.setTextColor(i);
      return;
    }
    paramTextView.setTextColor(paramInt);
  }
  
  public void a(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null) {
      return;
    }
    int i = 0;
    int j = 0;
    while (i < localListAdapter.getCount())
    {
      localObject = localListAdapter.getView(i, null, paramListView);
      ((View)localObject).measure(0, 0);
      j += ((View)localObject).getMeasuredHeight();
      i += 1;
    }
    Object localObject = paramListView.getLayoutParams();
    height = (paramListView.getDividerHeight() * (localListAdapter.getCount() - 1) + j);
    if (j < ViewUtil.dp2px(d, 174)) {
      height = ViewUtil.dp2px(d, 174);
    }
    if (j > ViewUtil.dp2px(d, 174)) {
      height = ViewUtil.dp2px(d, 174);
    }
    paramListView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }
  
  public void dismiss()
  {
    if ((c) && (b != null)) {
      b.a(null);
    }
    super.dismiss();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(br.e.duoqu_list_dialog);
    paramBundle = (ListView)findViewById(br.d.list);
    h = ((TextView)findViewById(br.d.title));
    h.setText(g);
    paramBundle.setAdapter(new ex.a());
    a(paramBundle);
    findViewById(br.d.sure).setOnClickListener(new ey(this));
    findViewById(br.d.cancle).setOnClickListener(new ez(this));
  }
  
  public void show()
  {
    super.show();
    if (e.getValue("db_air_data_index") != null) {
      f = Integer.parseInt((String)e.getValue("db_air_data_index"));
    }
  }
  
  class a
    extends BaseAdapter
  {
    private HashMap<String, Boolean> b = new HashMap();
    
    public a()
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
      return a.length();
    }
    
    public Object getItem(int paramInt)
    {
      try
      {
        JSONObject localJSONObject = a.getJSONObject(paramInt);
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
        paramView = getLayoutInflater().inflate(br.e.duoqu_list_items_content, null);
        paramViewGroup = new ex.b(ex.this);
        b = ((RadioButton)paramView.findViewById(br.d.item_rb));
        a = ((TextView)paramView.findViewById(br.d.item_text));
        paramView.setTag(paramViewGroup);
        a.setText(((JSONObject)getItem(paramInt)).optString("num"));
        b.setOnClickListener(new fa(this, paramInt));
        paramView.setOnClickListener(new fb(this, paramInt));
        if ((b.get(String.valueOf(paramInt)) == null) || (!((Boolean)b.get(String.valueOf(paramInt))).booleanValue())) {
          b.put(String.valueOf(paramInt), Boolean.valueOf(false));
        }
        if (ex.c(ex.this).getValue("db_air_data_index") == null) {
          break label259;
        }
      }
      label259:
      for (int i = Integer.parseInt((String)ex.c(ex.this).getValue("db_air_data_index"));; i = 0)
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
  
  class b
  {
    protected TextView a;
    protected RadioButton b;
    
    b() {}
  }
}

/* Location:
 * Qualified Name:     ex
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */