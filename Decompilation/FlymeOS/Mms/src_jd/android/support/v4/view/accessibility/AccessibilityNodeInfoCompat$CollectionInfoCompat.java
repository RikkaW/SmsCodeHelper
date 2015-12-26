package android.support.v4.view.accessibility;

public class AccessibilityNodeInfoCompat$CollectionInfoCompat
{
  public static final int SELECTION_MODE_MULTIPLE = 2;
  public static final int SELECTION_MODE_NONE = 0;
  public static final int SELECTION_MODE_SINGLE = 1;
  final Object mInfo;
  
  private AccessibilityNodeInfoCompat$CollectionInfoCompat(Object paramObject)
  {
    mInfo = paramObject;
  }
  
  public static CollectionInfoCompat obtain(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    return new CollectionInfoCompat(AccessibilityNodeInfoCompat.access$000().obtainCollectionInfo(paramInt1, paramInt2, paramBoolean, paramInt3));
  }
  
  public int getColumnCount()
  {
    return AccessibilityNodeInfoCompat.access$000().getCollectionInfoColumnCount(mInfo);
  }
  
  public int getRowCount()
  {
    return AccessibilityNodeInfoCompat.access$000().getCollectionInfoRowCount(mInfo);
  }
  
  public boolean isHierarchical()
  {
    return AccessibilityNodeInfoCompat.access$000().isCollectionInfoHierarchical(mInfo);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */