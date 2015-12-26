import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.ui.popu.util.ViewUtil;
import cn.com.xy.sms.sdk.util.StringUtils;

public class de
  extends dg
{
  private TextView a = null;
  private ImageView b = null;
  private ImageView c = null;
  private LinearLayout d = null;
  private LinearLayout j = null;
  
  public de(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  private void b()
  {
    ContentUtil.setTextColor(a, (String)g.getValue("view_title_name_color"));
  }
  
  public void a()
  {
    a = ((TextView)e.findViewById(br.d.duoqu_tv_head_split_title));
    b = ((ImageView)e.findViewById(br.d.duoqu_tv_head_split_logo));
    c = ((ImageView)e.findViewById(br.d.dotted_split));
    d = ((LinearLayout)e.findViewById(br.d.duoqu_tv_head_title_split_view));
    j = ((LinearLayout)e.findViewById(br.d.duoqu_tv_head_split_view));
  }
  
  @SuppressLint({"NewApi"})
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    if (paramBusinessSmsMessage == null)
    {
      Log.e("BubbleTitleSplitHead", "setContent message is null");
      return;
    }
    Object localObject1 = (String)paramBusinessSmsMessage.getValue("view_title_name");
    Object localObject2 = (String)paramBusinessSmsMessage.getValue("title_name");
    Object localObject3 = a;
    label54:
    label98:
    label144:
    label154:
    int i;
    LinearLayout localLinearLayout;
    if (StringUtils.isNull((String)localObject2))
    {
      ContentUtil.setText((TextView)localObject3, (String)localObject1, null);
      if (!paramBoolean) {
        b();
      }
      localObject1 = (String)paramBusinessSmsMessage.getValue("v_hd_color");
      if (StringUtils.isNull((String)localObject1)) {
        break label251;
      }
      ContentUtil.textSetColor(a, (String)localObject1);
      localObject1 = paramBusinessSmsMessage.getImgNameByKey("v_hd_logo");
      if (StringUtils.isNull((String)localObject1)) {
        break label271;
      }
      ContentUtil.setViewVisibility(b, 0);
      localObject2 = f;
      localObject3 = b;
      if (viewType != 1) {
        break label266;
      }
      paramBoolean = true;
      ViewUtil.setImageSrc((Context)localObject2, (ImageView)localObject3, (String)localObject1, paramBoolean);
      localObject1 = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg");
      localObject2 = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg_fir");
      i = br.c.duoqu_top_rectangle;
      localObject3 = Constant.getContext();
      localLinearLayout = d;
      if (!StringUtils.isNull((String)localObject1)) {
        break label284;
      }
      localObject1 = localObject2;
    }
    label251:
    label266:
    label271:
    label284:
    for (;;)
    {
      ViewManger.setViewBg((Context)localObject3, localLinearLayout, (String)localObject1, i, -1, true);
      paramBusinessSmsMessage = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg_sec");
      if (StringUtils.isNull(paramBusinessSmsMessage)) {
        break;
      }
      try
      {
        ViewManger.setViewBg(Constant.getContext(), j, paramBusinessSmsMessage, 0, -1, true);
        return;
      }
      catch (Throwable paramBusinessSmsMessage)
      {
        paramBusinessSmsMessage.printStackTrace();
        return;
      }
      localObject1 = localObject2;
      break label54;
      a.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
      break label98;
      paramBoolean = false;
      break label144;
      b.setImageResource(br.c.duoqu_default_logo);
      break label154;
    }
  }
}

/* Location:
 * Qualified Name:     de
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */