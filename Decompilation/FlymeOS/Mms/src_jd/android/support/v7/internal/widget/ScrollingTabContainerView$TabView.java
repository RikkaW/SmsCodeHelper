package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.appcompat.R.attr;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class ScrollingTabContainerView$TabView
  extends LinearLayoutCompat
  implements View.OnLongClickListener
{
  private final int[] BG_ATTRS;
  private View mCustomView;
  private ImageView mIconView;
  private ActionBar.Tab mTab;
  private TextView mTextView;
  
  public ScrollingTabContainerView$TabView(ScrollingTabContainerView paramScrollingTabContainerView, Context paramContext, ActionBar.Tab paramTab, boolean paramBoolean) {}
  
  public void bindTab(ActionBar.Tab paramTab)
  {
    mTab = paramTab;
    update();
  }
  
  public ActionBar.Tab getTab()
  {
    return mTab;
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(ActionBar.Tab.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    if (Build.VERSION.SDK_INT >= 14) {
      paramAccessibilityNodeInfo.setClassName(ActionBar.Tab.class.getName());
    }
  }
  
  public boolean onLongClick(View paramView)
  {
    paramView = new int[2];
    getLocationOnScreen(paramView);
    Object localObject = getContext();
    int i = getWidth();
    int j = getHeight();
    int k = getResourcesgetDisplayMetricswidthPixels;
    localObject = Toast.makeText((Context)localObject, mTab.getContentDescription(), 0);
    ((Toast)localObject).setGravity(49, paramView[0] + i / 2 - k / 2, j);
    ((Toast)localObject).show();
    return true;
  }
  
  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setSelected(boolean paramBoolean)
  {
    if (isSelected() != paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      super.setSelected(paramBoolean);
      if ((i != 0) && (paramBoolean)) {
        sendAccessibilityEvent(4);
      }
      return;
    }
  }
  
  public void update()
  {
    ActionBar.Tab localTab = mTab;
    Object localObject1 = localTab.getCustomView();
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = ((View)localObject1).getParent();
      if (localObject2 != this)
      {
        if (localObject2 != null) {
          ((ViewGroup)localObject2).removeView((View)localObject1);
        }
        addView((View)localObject1);
      }
      mCustomView = ((View)localObject1);
      if (mTextView != null) {
        mTextView.setVisibility(8);
      }
      if (mIconView != null)
      {
        mIconView.setVisibility(8);
        mIconView.setImageDrawable(null);
      }
    }
    for (;;)
    {
      setEnabled(localTab.isEnabled());
      return;
      if (mCustomView != null)
      {
        removeView(mCustomView);
        mCustomView = null;
      }
      localObject2 = localTab.getIcon();
      localObject1 = localTab.getText();
      label219:
      int i;
      label229:
      int j;
      if (localObject2 != null)
      {
        Object localObject3;
        if (mIconView == null)
        {
          localObject3 = new ImageView(getContext());
          LinearLayoutCompat.LayoutParams localLayoutParams = new LinearLayoutCompat.LayoutParams(-2, -2);
          gravity = 16;
          ((ImageView)localObject3).setLayoutParams(localLayoutParams);
          addView((View)localObject3, 0);
          mIconView = ((ImageView)localObject3);
        }
        mIconView.setImageDrawable((Drawable)localObject2);
        mIconView.setVisibility(0);
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          break label415;
        }
        i = 1;
        if (i == 0) {
          break label427;
        }
        if (mTextView == null)
        {
          localObject2 = getContext();
          if (!ScrollingTabContainerView.access$300(this$0)) {
            break label420;
          }
          j = R.attr.mzToolBarTabTextStyle;
          label260:
          localObject2 = new AppCompatTextView((Context)localObject2, null, j);
          ((TextView)localObject2).setEllipsize(TextUtils.TruncateAt.END);
          localObject3 = new LinearLayoutCompat.LayoutParams(-2, -2);
          gravity = 16;
          ((TextView)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
          addView((View)localObject2);
          mTextView = ((TextView)localObject2);
        }
        mTextView.setText((CharSequence)localObject1);
        mTextView.setVisibility(0);
        mTextView.setEnabled(localTab.isEnabled());
      }
      for (;;)
      {
        if (mIconView != null) {
          mIconView.setContentDescription(localTab.getContentDescription());
        }
        if ((i != 0) || (TextUtils.isEmpty(localTab.getContentDescription()))) {
          break label454;
        }
        setOnLongClickListener(this);
        break;
        if (mIconView == null) {
          break label219;
        }
        mIconView.setVisibility(8);
        mIconView.setImageDrawable(null);
        break label219;
        label415:
        i = 0;
        break label229;
        label420:
        j = R.attr.actionBarTabTextStyle;
        break label260;
        label427:
        if (mTextView != null)
        {
          mTextView.setVisibility(8);
          mTextView.setText(null);
        }
      }
      label454:
      setOnLongClickListener(null);
      setLongClickable(false);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ScrollingTabContainerView.TabView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */