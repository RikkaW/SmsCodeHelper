package android.support.v4.view;

class KeyEventCompat$HoneycombKeyEventVersionImpl
  extends KeyEventCompat.EclairKeyEventVersionImpl
{
  public boolean metaStateHasModifiers(int paramInt1, int paramInt2)
  {
    return KeyEventCompatHoneycomb.metaStateHasModifiers(paramInt1, paramInt2);
  }
  
  public boolean metaStateHasNoModifiers(int paramInt)
  {
    return KeyEventCompatHoneycomb.metaStateHasNoModifiers(paramInt);
  }
  
  public int normalizeMetaState(int paramInt)
  {
    return KeyEventCompatHoneycomb.normalizeMetaState(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.KeyEventCompat.HoneycombKeyEventVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */