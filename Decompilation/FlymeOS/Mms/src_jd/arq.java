import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import flyme.support.v7.widget.RecyclerView;
import flyme.support.v7.widget.RecyclerView.h;

public class arq
  extends AccessibilityDelegateCompat
{
  final RecyclerView a;
  final AccessibilityDelegateCompat b;
  
  private boolean b()
  {
    return a.s();
  }
  
  public AccessibilityDelegateCompat a()
  {
    return b;
  }
  
  public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
  {
    super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(RecyclerView.class.getName());
    if (((paramView instanceof RecyclerView)) && (!b()))
    {
      paramView = (RecyclerView)paramView;
      if (paramView.getLayoutManager() != null) {
        paramView.getLayoutManager().a(paramAccessibilityEvent);
      }
    }
  }
  
  public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
  {
    super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
    paramAccessibilityNodeInfoCompat.setClassName(RecyclerView.class.getName());
    if ((!b()) && (a.getLayoutManager() != null)) {
      a.getLayoutManager().a(paramAccessibilityNodeInfoCompat);
    }
  }
  
  public boolean performAccessibilityAction(View paramView, int paramInt, Bundle paramBundle)
  {
    if (super.performAccessibilityAction(paramView, paramInt, paramBundle)) {
      return true;
    }
    if ((!b()) && (a.getLayoutManager() != null)) {
      return a.getLayoutManager().a(paramInt, paramBundle);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     arq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */