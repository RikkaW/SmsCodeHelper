package cn.com.xy.sms.sdk.ui.popu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import eo;
import java.util.ArrayList;
import java.util.List;

public abstract class DuoquBaseTable
  extends RelativeLayout
{
  protected int a = 0;
  protected int b = 1000;
  protected List<eo> c = null;
  
  public DuoquBaseTable(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }
  
  protected abstract RelativeLayout.LayoutParams a(int paramInt);
  
  protected abstract void a(int paramInt1, BusinessSmsMessage paramBusinessSmsMessage, int paramInt2, String paramString, boolean paramBoolean);
  
  protected abstract void a(Context paramContext, AttributeSet paramAttributeSet);
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, int paramInt, String paramString, boolean paramBoolean)
  {
    int m = 5;
    int i = 0;
    int k = 0;
    int j = 0;
    List localList;
    label63:
    eo localeo;
    if (paramInt == 0)
    {
      try
      {
        setVisibility(8);
        return;
      }
      catch (Exception paramBusinessSmsMessage)
      {
        paramBusinessSmsMessage.printStackTrace();
        return;
      }
      setVisibility(0);
      localList = c;
      if ((paramBoolean) && (localList != null))
      {
        k = localList.size();
        if (k > paramInt)
        {
          i = j;
          if (i >= k) {
            break label245;
          }
          localeo = (eo)localList.get(i);
          if (i < paramInt)
          {
            localeo.a(0);
            localeo.a(i, paramBusinessSmsMessage, paramString, paramBoolean);
            break label257;
          }
          localeo.a(8);
          break label257;
        }
      }
    }
    for (;;)
    {
      if (i < paramInt)
      {
        if (i < k)
        {
          localeo = (eo)localList.get(i);
          localeo.a(0);
          localeo.a(i, paramBusinessSmsMessage, paramString, paramBoolean);
        }
        else
        {
          a(i, paramBusinessSmsMessage, paramInt, paramString, false);
          break label266;
          c = new ArrayList();
          a = 0;
          b = 1000;
          removeAllViews();
          i = k;
          while (i < paramInt)
          {
            a(i, paramBusinessSmsMessage, paramInt, paramString, false);
            i += 1;
          }
        }
      }
      else
      {
        label245:
        do
        {
          break;
          return;
        } while (paramInt <= 5);
        paramInt = m;
        break;
        label257:
        i += 1;
        break label63;
      }
      label266:
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.widget.DuoquBaseTable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */