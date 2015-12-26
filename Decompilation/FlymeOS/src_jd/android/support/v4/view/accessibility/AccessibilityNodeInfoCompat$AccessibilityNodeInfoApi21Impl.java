package android.support.v4.view.accessibility;

import android.view.View;
import java.util.List;

class AccessibilityNodeInfoCompat$AccessibilityNodeInfoApi21Impl
  extends AccessibilityNodeInfoCompat.AccessibilityNodeInfoKitKatImpl
{
  public void addAction(Object paramObject1, Object paramObject2)
  {
    AccessibilityNodeInfoCompatApi21.addAction(paramObject1, paramObject2);
  }
  
  public int getAccessibilityActionId(Object paramObject)
  {
    return AccessibilityNodeInfoCompatApi21.getAccessibilityActionId(paramObject);
  }
  
  public CharSequence getAccessibilityActionLabel(Object paramObject)
  {
    return AccessibilityNodeInfoCompatApi21.getAccessibilityActionLabel(paramObject);
  }
  
  public List<Object> getActionList(Object paramObject)
  {
    return AccessibilityNodeInfoCompatApi21.getActionList(paramObject);
  }
  
  public CharSequence getError(Object paramObject)
  {
    return AccessibilityNodeInfoCompatApi21.getError(paramObject);
  }
  
  public boolean isCollectionItemSelected(Object paramObject)
  {
    return AccessibilityNodeInfoCompatApi21.CollectionItemInfo.isSelected(paramObject);
  }
  
  public Object newAccessibilityAction(int paramInt, CharSequence paramCharSequence)
  {
    return AccessibilityNodeInfoCompatApi21.newAccessibilityAction(paramInt, paramCharSequence);
  }
  
  public Object obtainCollectionInfo(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    return AccessibilityNodeInfoCompatApi21.obtainCollectionInfo(paramInt1, paramInt2, paramBoolean, paramInt3);
  }
  
  public Object obtainCollectionItemInfo(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
  {
    return AccessibilityNodeInfoCompatApi21.obtainCollectionItemInfo(paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean1, paramBoolean2);
  }
  
  public void setError(Object paramObject, CharSequence paramCharSequence)
  {
    AccessibilityNodeInfoCompatApi21.setError(paramObject, paramCharSequence);
  }
  
  public void setLabelFor(Object paramObject, View paramView)
  {
    AccessibilityNodeInfoCompatApi21.setLabelFor(paramObject, paramView);
  }
  
  public void setLabelFor(Object paramObject, View paramView, int paramInt)
  {
    AccessibilityNodeInfoCompatApi21.setLabelFor(paramObject, paramView, paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityNodeInfoApi21Impl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */