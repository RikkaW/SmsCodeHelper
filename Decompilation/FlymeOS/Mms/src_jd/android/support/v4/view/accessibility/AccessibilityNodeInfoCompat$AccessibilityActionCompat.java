package android.support.v4.view.accessibility;

public class AccessibilityNodeInfoCompat$AccessibilityActionCompat
{
  private final Object mAction;
  
  public AccessibilityNodeInfoCompat$AccessibilityActionCompat(int paramInt, CharSequence paramCharSequence)
  {
    this(AccessibilityNodeInfoCompat.access$000().newAccessibilityAction(paramInt, paramCharSequence));
  }
  
  private AccessibilityNodeInfoCompat$AccessibilityActionCompat(Object paramObject)
  {
    mAction = paramObject;
  }
  
  public int getId()
  {
    return AccessibilityNodeInfoCompat.access$000().getAccessibilityActionId(mAction);
  }
  
  public CharSequence getLabel()
  {
    return AccessibilityNodeInfoCompat.access$000().getAccessibilityActionLabel(mAction);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */