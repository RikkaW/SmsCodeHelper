import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.color;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.styleable;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.TintImageView;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;

public class aqy
  extends LinearLayoutCompat
{
  private boolean a;
  private ScrollingTabContainerView b;
  private aqy.a c;
  private Context d;
  private int e;
  private int f;
  private final Paint g;
  private boolean h;
  private View.OnClickListener i;
  
  public aqy(Context paramContext)
  {
    super(paramContext, null, R.attr.mzActionBarTabContainerStyle);
    TintTypedArray localTintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, null, R.styleable.MzActionBarTabContainer, R.attr.mzActionBarTabContainerStyle, 0);
    a = localTintTypedArray.getBoolean(R.styleable.MzActionBarTabContainer_mzAllowCollapse, false);
    localTintTypedArray.recycle();
    localTintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, null, R.styleable.MzActionBarTabScrollView, R.attr.mzActionBarTabScrollViewStyle, 0);
    f = localTintTypedArray.getColor(R.styleable.MzActionBarTabScrollView_mzTopDividerColor, getResources().getColor(R.color.mz_action_bar_scrollview_divider_default_color));
    e = localTintTypedArray.getDimensionPixelSize(R.styleable.MzActionBarTabScrollView_mzTopDividerHeight, getResources().getDimensionPixelSize(R.dimen.mz_action_bar_tab_scroll_top_divider_height));
    localTintTypedArray.recycle();
    d = paramContext;
    g = new Paint();
    g.setColor(f);
    setOrientation(0);
  }
  
  private void a()
  {
    int j = getResources().getDimensionPixelOffset(R.dimen.mz_action_tab_bar_margin_left);
    setPadding(j, 0, j, 0);
    if (!c()) {
      return;
    }
    removeView(c);
  }
  
  private void b()
  {
    if (c()) {
      return;
    }
    if (c == null) {
      c = new aqy.a(d);
    }
    addView(c, new ViewGroup.LayoutParams(-2, -1));
    setPadding(getResources().getDimensionPixelOffset(R.dimen.mz_action_tab_bar_margin_left), 0, 0, 0);
  }
  
  private boolean c()
  {
    return (c != null) && (c.getParent() == this);
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    int j = getResources().getDimensionPixelSize(R.dimen.mz_action_tab_bar_margin_left);
    paramCanvas.drawRect(j, 0.0F, getMeasuredWidth() - j, e, g);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    boolean bool2 = false;
    int j = getResources().getDimensionPixelSize(R.dimen.mz_action_tab_bar_margin_left);
    int k = getMeasuredWidth();
    boolean bool1 = bool2;
    if (a)
    {
      bool1 = bool2;
      if (b != null)
      {
        bool1 = bool2;
        if (b.getVisibility() == 0)
        {
          bool1 = bool2;
          if (k < j * 2 + b.getTabStripWidth()) {
            bool1 = true;
          }
        }
      }
    }
    if (bool1 != h)
    {
      h = bool1;
      if (!bool1) {
        break label116;
      }
      b();
    }
    for (;;)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
      label116:
      a();
    }
  }
  
  public void setAllowCollapse(boolean paramBoolean)
  {
    a = paramBoolean;
  }
  
  public void setCollapseButtonClickListener(View.OnClickListener paramOnClickListener)
  {
    i = paramOnClickListener;
  }
  
  public void setDividerDrawable(Drawable paramDrawable)
  {
    super.setDividerDrawable(paramDrawable);
    setWillNotDraw(false);
  }
  
  public void setTabView(ScrollingTabContainerView paramScrollingTabContainerView)
  {
    if (b != null) {
      removeView(b);
    }
    if (c()) {
      removeView(c);
    }
    b = paramScrollingTabContainerView;
    if (paramScrollingTabContainerView != null)
    {
      addView(paramScrollingTabContainerView);
      LinearLayoutCompat.LayoutParams localLayoutParams = (LinearLayoutCompat.LayoutParams)paramScrollingTabContainerView.getLayoutParams();
      int j = getResources().getDimensionPixelOffset(R.dimen.mz_action_tab_bar_margin_left);
      setPadding(j, 0, j, 0);
      width = 0;
      weight = 1.0F;
      height = -2;
      paramScrollingTabContainerView.setAllowCollapse(false);
    }
  }
  
  class a
    extends TintImageView
  {
    public a(Context paramContext)
    {
      super(null, R.attr.mzTabContainerCollapseButtonStyle);
      setClickable(true);
      setFocusable(true);
      setVisibility(0);
      setEnabled(true);
      setOnClickListener(new aqz(this, aqy.this));
      setBackgroundDrawable(new aqt(this, R.attr.mzActionButtonRippleStyle));
    }
    
    public boolean performClick()
    {
      if (super.performClick()) {
        return true;
      }
      playSoundEffect(0);
      return true;
    }
  }
}

/* Location:
 * Qualified Name:     aqy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */