package android.support.v7.internal.widget;

import android.util.SparseArray;
import android.view.View;

class AbsSpinnerCompat$RecycleBin
{
  private final SparseArray<View> mScrapHeap = new SparseArray();
  
  AbsSpinnerCompat$RecycleBin(AbsSpinnerCompat paramAbsSpinnerCompat) {}
  
  void clear()
  {
    SparseArray localSparseArray = mScrapHeap;
    int j = localSparseArray.size();
    int i = 0;
    while (i < j)
    {
      View localView = (View)localSparseArray.valueAt(i);
      if (localView != null) {
        AbsSpinnerCompat.access$000(this$0, localView, true);
      }
      i += 1;
    }
    localSparseArray.clear();
  }
  
  View get(int paramInt)
  {
    View localView = (View)mScrapHeap.get(paramInt);
    if (localView != null) {
      mScrapHeap.delete(paramInt);
    }
    return localView;
  }
  
  public void put(int paramInt, View paramView)
  {
    mScrapHeap.put(paramInt, paramView);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.AbsSpinnerCompat.RecycleBin
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */