package flyme.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.appcompat.R.string;
import android.util.AttributeSet;
import android.widget.TextView;

public class TwoStateTextView
  extends TextView
{
  private int a = 0;
  private int b = Integer.MAX_VALUE;
  private String c;
  private String d;
  private a e;
  private boolean f = false;
  
  public TwoStateTextView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TwoStateTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a();
  }
  
  private void a()
  {
    c = getContext().getResources().getString(R.string.mz_action_bar_multi_choice_select_all);
    d = getContext().getResources().getString(R.string.mz_action_bar_multi_choice_select_all_cancel);
  }
  
  private void b()
  {
    if (f)
    {
      f = false;
      if (b >= a)
      {
        e = a.a;
        setText(d);
      }
    }
    do
    {
      return;
      e = a.b;
      setText(c);
      return;
      if ((e == a.b) && (b >= a))
      {
        setText(d);
        e = a.a;
        return;
      }
    } while ((e != a.a) || (b >= a));
    setText(c);
    e = a.b;
  }
  
  public void setSelectedCount(int paramInt)
  {
    b = paramInt;
    b();
  }
  
  public void setTotalCount(int paramInt)
  {
    a = paramInt;
    f = true;
  }
  
  static enum a
  {
    private a() {}
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.TwoStateTextView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */