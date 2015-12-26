import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.io.PrintStream;
import org.json.JSONObject;

public class eo
{
  public TextView a;
  public TextView b;
  public TextView c;
  public TextView d;
  public ImageView e;
  private String f = "";
  private String g = "";
  private String h = "\r\n";
  
  private String a(String paramString)
  {
    String str = paramString;
    if (paramString.indexOf(h) == 0)
    {
      str = paramString.replaceFirst(h, "");
      System.out.println(str.length());
    }
    int i = str.length();
    paramString = str;
    if (str.endsWith(h))
    {
      paramString = str.substring(0, i - 2);
      System.out.println(paramString.length());
    }
    return paramString;
  }
  
  private void a(BusinessSmsMessage paramBusinessSmsMessage, JSONObject paramJSONObject)
  {
    if (!StringUtils.isNull(f)) {
      ContentUtil.textSetColor(a, f);
    }
    while (!StringUtils.isNull(g))
    {
      ContentUtil.textSetColor(b, f);
      ContentUtil.textSetColor(c, f);
      ContentUtil.textSetColor(d, f);
      return;
      ContentUtil.textSetColor(a, "#000000");
    }
    ContentUtil.textSetColor(b, "#000000");
    ContentUtil.textSetColor(c, "#000000");
    ContentUtil.textSetColor(d, "#000000");
  }
  
  public void a(int paramInt)
  {
    try
    {
      a.setVisibility(paramInt);
      d.setVisibility(paramInt);
      b.setVisibility(paramInt);
      c.setVisibility(paramInt);
      e.setVisibility(paramInt);
      if (b.getTag(br.d.tag_parent_layout) != null) {
        ((RelativeLayout)b.getTag(br.d.tag_parent_layout)).setVisibility(paramInt);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void a(int paramInt, BusinessSmsMessage paramBusinessSmsMessage, String paramString, boolean paramBoolean)
  {
    Object localObject2;
    Object localObject1;
    try
    {
      paramString = (JSONObject)paramBusinessSmsMessage.getTableData(paramInt, paramString);
      localObject2 = (String)JsonUtil.getValFromJsonObject(paramString, "t1");
      localObject1 = a((String)JsonUtil.getValFromJsonObject(paramString, "t2"));
      a.setText((CharSequence)localObject2);
      f = paramBusinessSmsMessage.getImgNameByKey("v_by_l_color");
      g = paramBusinessSmsMessage.getImgNameByKey("v_by_r_color");
      if ((!paramBoolean) || ("1".equals(paramBusinessSmsMessage.getValue("view_set_view_style")))) {
        a(paramBusinessSmsMessage, paramString);
      }
      localObject2 = (String)JsonUtil.getValFromJsonObject(paramString, "s2");
      if (((!StringUtils.isNull((String)localObject1)) && (((String)localObject1).contains(h))) || (StringUtils.isNull((String)localObject2)))
      {
        if (((String)localObject1).contains(h))
        {
          ContentUtil.setText(b, (String)localObject1, "");
          ContentUtil.setText(d, "", "");
          ContentUtil.setText(c, "", "");
          d.setVisibility(8);
          c.setVisibility(8);
          b.setVisibility(0);
          return;
        }
        ContentUtil.setText(c, (String)localObject1, "");
        ContentUtil.setText(b, "", "");
        ContentUtil.setText(d, "", "");
        d.setVisibility(8);
        b.setVisibility(8);
        c.setVisibility(0);
        return;
      }
    }
    catch (Exception paramBusinessSmsMessage)
    {
      paramBusinessSmsMessage.printStackTrace();
      return;
    }
    paramString = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg");
    paramBusinessSmsMessage = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg_fir");
    if (!StringUtils.isNull((String)localObject2)) {
      if (((String)localObject1).length() <= 8)
      {
        ContentUtil.setText(d, (String)localObject1, "");
        ContentUtil.setText(b, "", "");
        ContentUtil.setText(c, "", "");
        b.setVisibility(8);
        c.setVisibility(8);
        d.setVisibility(0);
        d.setTextSize(1, Float.parseFloat((String)localObject2));
        if ((StringUtils.isNull(paramString)) && (StringUtils.isNull(paramBusinessSmsMessage))) {
          break label503;
        }
        localObject2 = d;
        if (!StringUtils.isNull(paramString)) {
          break label578;
        }
        localObject1 = paramBusinessSmsMessage;
        label401:
        ContentUtil.textSetColor((TextView)localObject2, (String)localObject1);
        localObject1 = c;
        if (!StringUtils.isNull(paramString)) {
          break label584;
        }
      }
    }
    for (;;)
    {
      ContentUtil.textSetColor((TextView)localObject1, paramBusinessSmsMessage);
      return;
      ContentUtil.setText(d, "", "");
      ContentUtil.setText(b, "", "");
      ContentUtil.setText(c, (String)localObject1, "");
      b.setVisibility(8);
      c.setVisibility(0);
      d.setVisibility(8);
      d.setTextSize(1, Float.parseFloat("14"));
      break;
      label503:
      if (!StringUtils.isNull(g))
      {
        ContentUtil.textSetColor(d, f);
        ContentUtil.textSetColor(c, f);
        return;
      }
      ContentUtil.textSetColor(d, "#000000");
      ContentUtil.textSetColor(c, "#000000");
      return;
      d.setTextSize(1, Float.parseFloat("14"));
      ContentUtil.textSetColor(d, "#000000");
      return;
      label578:
      localObject1 = paramString;
      break label401;
      label584:
      paramBusinessSmsMessage = paramString;
    }
  }
}

/* Location:
 * Qualified Name:     eo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */