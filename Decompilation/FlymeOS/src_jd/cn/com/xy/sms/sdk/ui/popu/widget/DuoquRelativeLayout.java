package cn.com.xy.sms.sdk.ui.popu.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import br.h;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import er;

public class DuoquRelativeLayout
  extends RelativeLayout
  implements er
{
  public TypedArray a = null;
  
  public DuoquRelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a = paramContext.obtainStyledAttributes(paramAttributeSet, br.h.duoqu_attr);
  }
  
  public Object a(byte paramByte, int paramInt)
  {
    return ViewManger.obtainStyledAttributes(a, paramByte, paramInt);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.widget.DuoquRelativeLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */