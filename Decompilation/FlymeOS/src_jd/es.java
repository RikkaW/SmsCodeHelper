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
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class es
  extends Dialog
{
  public JSONArray a = null;
  private fd b = null;
  private boolean c = true;
  private Context d;
  private String e;
  
  public es(fd paramfd, JSONArray paramJSONArray, Context paramContext, int paramInt, String paramString)
  {
    super(paramContext, paramInt);
    a = paramJSONArray;
    b = paramfd;
    d = paramContext;
    e = paramString;
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
    es.a locala = new es.a();
    paramBundle.setAdapter(locala);
    a(paramBundle);
    findViewById(br.d.sure).setOnClickListener(new et(this, locala));
    findViewById(br.d.cancle).setOnClickListener(new eu(this));
  }
  
  class a
    extends BaseAdapter
  {
    private HashMap<String, Boolean> b = new HashMap();
    
    public a()
    {
      int i = 0;
      int j = -1;
      if (i < a.length())
      {
        if (a.optJSONObject(i).optString("name").equals(es.b(es.this))) {
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
        paramViewGroup = new es.b(es.this);
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
  
  class b
  {
    protected TextView a;
    protected RadioButton b;
    
    b() {}
  }
}

/* Location:
 * Qualified Name:     es
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */