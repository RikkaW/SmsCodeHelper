package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;

class SpinnerCompat$DropdownPopup
  extends ListPopupWindow
  implements SpinnerCompat.SpinnerPopup
{
  private ListAdapter mAdapter;
  private CharSequence mHintText;
  
  public SpinnerCompat$DropdownPopup(SpinnerCompat paramSpinnerCompat, Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setAnchorView(paramSpinnerCompat);
    setModal(true);
    setPromptPosition(0);
    setOnItemClickListener(new SpinnerCompat.DropdownPopup.1(this, paramSpinnerCompat));
  }
  
  void computeContentWidth()
  {
    Object localObject = getBackground();
    int i;
    int m;
    int n;
    int i1;
    int j;
    if (localObject != null)
    {
      ((Drawable)localObject).getPadding(SpinnerCompat.access$400(this$0));
      if (ViewUtils.isLayoutRtl(this$0))
      {
        i = access$400this$0).right;
        m = this$0.getPaddingLeft();
        n = this$0.getPaddingRight();
        i1 = this$0.getWidth();
        if (this$0.mDropDownWidth != -2) {
          break label240;
        }
        j = this$0.measureContentWidth((SpinnerAdapter)mAdapter, getBackground());
        int k = this$0.getContext().getResources().getDisplayMetrics().widthPixels - access$400this$0).left - access$400this$0).right;
        if (j <= k) {
          break label288;
        }
        j = k;
      }
    }
    label165:
    label240:
    label288:
    for (;;)
    {
      setContentWidth(Math.max(j, i1 - m - n));
      if (ViewUtils.isLayoutRtl(this$0)) {
        i = i1 - n - getWidth() + i;
      }
      for (;;)
      {
        setHorizontalOffset(i);
        return;
        i = -access$400this$0).left;
        break;
        localObject = SpinnerCompat.access$400(this$0);
        access$400this$0).right = 0;
        left = 0;
        i = 0;
        break;
        if (this$0.mDropDownWidth == -1)
        {
          setContentWidth(i1 - m - n);
          break label165;
        }
        setContentWidth(this$0.mDropDownWidth);
        break label165;
        i += m;
      }
    }
  }
  
  public CharSequence getHintText()
  {
    return mHintText;
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    super.setAdapter(paramListAdapter);
    mAdapter = paramListAdapter;
  }
  
  public void setPromptText(CharSequence paramCharSequence)
  {
    mHintText = paramCharSequence;
  }
  
  public void show(int paramInt1, int paramInt2)
  {
    boolean bool = isShowing();
    computeContentWidth();
    setInputMethodMode(2);
    super.show();
    getListView().setChoiceMode(1);
    setSelection(this$0.getSelectedItemPosition());
    if (bool) {}
    ViewTreeObserver localViewTreeObserver;
    do
    {
      return;
      localViewTreeObserver = this$0.getViewTreeObserver();
    } while (localViewTreeObserver == null);
    SpinnerCompat.DropdownPopup.2 local2 = new SpinnerCompat.DropdownPopup.2(this);
    localViewTreeObserver.addOnGlobalLayoutListener(local2);
    setOnDismissListener(new SpinnerCompat.DropdownPopup.3(this, local2));
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.DropdownPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */