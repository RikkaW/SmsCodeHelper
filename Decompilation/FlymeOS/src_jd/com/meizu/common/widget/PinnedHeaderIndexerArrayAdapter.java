package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import java.util.List;

public abstract class PinnedHeaderIndexerArrayAdapter<T>
  extends PinnedHeaderArrayAdapter<T>
  implements SectionIndexer
{
  protected Context mContext;
  private View mHeader;
  private SparseIntArray mHeaderMap;
  private int mIndexedPartition = 0;
  private SectionIndexer mIndexer;
  private int mLastSection = -1;
  private int mLastSectionOverScrollDistance = 0;
  private Placement mPlacementCache = new Placement();
  private boolean mSectionHeaderDisplayEnabled;
  private boolean mShowSectionHeaders;
  
  public PinnedHeaderIndexerArrayAdapter(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    mHeaderMap = new SparseIntArray(getSections().length);
  }
  
  public PinnedHeaderIndexerArrayAdapter(Context paramContext, List<T>... paramVarArgs)
  {
    super(paramContext, paramVarArgs);
    mContext = paramContext;
    mHeaderMap = new SparseIntArray(getSections().length);
  }
  
  private void ensureSectionHeaders()
  {
    mHeaderMap.clear();
    if ((mShowSectionHeaders) && (mIndexer != null) && (mPartitions[mIndexedPartition].mItemCount > 0))
    {
      int m = mPartitions[mIndexedPartition].mHeaderViewsCount;
      int j = -1;
      int i = 0;
      while (i < mPartitions[mIndexedPartition].mItemCount)
      {
        int k = mIndexer.getSectionForPosition(i);
        if ((j == k) || (k <= j)) {
          break;
        }
        j = mHeaderMap.size();
        mHeaderMap.put(k, i + m + j);
        j = mIndexer.getPositionForSection(k + 1);
        if (i == j) {
          break;
        }
        i = j;
        j = k;
      }
      i = mHeaderMap.size();
      BasePartitionAdapter.Partition localPartition = mPartitions[mIndexedPartition];
      mCount += i;
      localPartition = mPartitions[mIndexedPartition];
      mSize += i;
      mCount = (i + mCount);
    }
  }
  
  private int getSectionPosition(int paramInt)
  {
    int j = getPositionForPartition(mIndexedPartition);
    int i = j;
    if (hasHeader(mIndexedPartition)) {
      i = j + 1;
    }
    return i + getPositionForSection(paramInt);
  }
  
  public boolean areAllItemsEnabled()
  {
    ensureCacheValid();
    if ((mShowSectionHeaders) && (mHeaderMap.size() > 0)) {
      return false;
    }
    return super.areAllItemsEnabled();
  }
  
  protected void bindSectionHeaderView(View paramView, Context paramContext, int paramInt1, int paramInt2)
  {
    paramView.setVisibility(0);
    ((TextView)paramView.findViewById(R.id.mc_header_text1)).setText((String)getSections()[paramInt2]);
    if (paramInt1 == 0)
    {
      paramView.setMinimumHeight(paramContext.getResources().getDimensionPixelSize(R.dimen.mz_pinned_top_header_minHeight));
      return;
    }
    paramView.setMinimumHeight(paramContext.getResources().getDimensionPixelSize(R.dimen.mz_pinned_interval_header_minHeight));
  }
  
  protected boolean canSelect(int paramInt1, int paramInt2)
  {
    if ((mIndexedPartition == paramInt1) && (mShowSectionHeaders) && (mHeaderMap.indexOfValue(paramInt2) >= 0)) {
      return false;
    }
    return super.canSelect(paramInt1, paramInt2);
  }
  
  protected void configureItemHeader(ListView paramListView, int paramInt1, int paramInt2, boolean paramBoolean) {}
  
  public void configurePinnedHeaders(PinnedHeaderListView paramPinnedHeaderListView)
  {
    super.configurePinnedHeaders(paramPinnedHeaderListView);
    if (!isSectionHeaderDisplayEnabled()) {
      return;
    }
    int j = paramPinnedHeaderListView.getCurrentOverScrollDistance();
    if ((j <= 0) && (paramPinnedHeaderListView.getFirstVisiblePosition() == 0)) {}
    int k;
    for (int i = 1;; i = 0)
    {
      if ((i != 0) && (mLastSectionOverScrollDistance >= 0)) {
        configureItemHeader(paramPinnedHeaderListView, getSectionPosition(0) + paramPinnedHeaderListView.getHeaderViewsCount(), 0, true);
      }
      mLastSectionOverScrollDistance = j;
      k = getPinnedHeaderCount() - 1;
      if ((mIndexer != null) && (getCount() != 0) && (i == 0)) {
        break;
      }
      paramPinnedHeaderListView.setHeaderInvisible(k, false);
      mLastSection = -1;
      return;
    }
    int m = paramPinnedHeaderListView.getPositionAt(paramPinnedHeaderListView.getTotalTopPinnedHeaderHeight());
    int n = m - paramPinnedHeaderListView.getHeaderViewsCount();
    if ((paramPinnedHeaderListView.getChildAt(0) != null) && (paramPinnedHeaderListView.getChildAt(0).getTop() <= paramPinnedHeaderListView.getHeaderPaddingTop()))
    {
      i = getPartitionForPosition(n);
      if (i == mIndexedPartition)
      {
        j = getOffsetInPartition(n);
        if (j < mPartitions[i].mHeaderViewsCount) {}
      }
    }
    for (i = getSectionForPosition(j);; i = -1)
    {
      if (mLastSection > i)
      {
        j = mLastSection;
        while (j > i)
        {
          configureItemHeader(paramPinnedHeaderListView, getSectionPosition(j) + paramPinnedHeaderListView.getHeaderViewsCount(), j, true);
          j -= 1;
        }
      }
      if (mLastSection < i)
      {
        j = mLastSection + 1;
        while (j <= i)
        {
          configureItemHeader(paramPinnedHeaderListView, getSectionPosition(j) + paramPinnedHeaderListView.getHeaderViewsCount(), j, false);
          j += 1;
        }
      }
      if ((mLastSection == i) && (i != -1) && (getSectionPosition(i) == n)) {
        configureItemHeader(paramPinnedHeaderListView, m, i, false);
      }
      mLastSection = i;
      if ((i == -1) || (!isPinnedSectionHeaderVisible(i)))
      {
        paramPinnedHeaderListView.setHeaderInvisible(k, false);
        return;
      }
      setPinnedSectionHeaderView(mHeader, i);
      if (n == getSectionPosition(i + 1) - 1) {}
      for (boolean bool = true;; bool = false)
      {
        paramPinnedHeaderListView.setFadingHeader(k, m, bool);
        return;
      }
    }
  }
  
  protected View createPinnedSectionHeaderView(Context paramContext, ViewGroup paramViewGroup)
  {
    paramContext = LayoutInflater.from(paramContext).inflate(R.layout.mc_pinned_header_view, paramViewGroup, false);
    if (paramContext != null) {
      ((ImageView)paramContext.findViewById(16908288)).setVisibility(8);
    }
    return paramContext;
  }
  
  protected void ensureCacheValid()
  {
    if (mCacheValid) {
      return;
    }
    super.ensureCacheValid();
    ensureSectionHeaders();
  }
  
  protected int getDataPosition(int paramInt1, int paramInt2)
  {
    if ((mIndexedPartition == paramInt1) && (mShowSectionHeaders))
    {
      int j;
      if (mHeaderMap.indexOfValue(paramInt2) >= 0)
      {
        j = -1;
        return j;
      }
      paramInt1 = paramInt2 - mPartitions[mIndexedPartition].mHeaderViewsCount;
      int i = 0;
      for (;;)
      {
        j = paramInt1;
        if (i >= mHeaderMap.size()) {
          break;
        }
        j = paramInt1;
        if (mHeaderMap.valueAt(i) > paramInt2) {
          break;
        }
        paramInt1 -= 1;
        i += 1;
      }
    }
    return super.getDataPosition(paramInt1, paramInt2);
  }
  
  public int getIndexedPartition()
  {
    return mIndexedPartition;
  }
  
  public SectionIndexer getIndexer()
  {
    return mIndexer;
  }
  
  protected int getItemBackgroundType(int paramInt1, int paramInt2)
  {
    if ((mIndexedPartition == paramInt1) && (paramInt2 >= 0) && (mIndexer != null))
    {
      if (isHeaderView(mIndexedPartition, paramInt2))
      {
        paramInt1 = mPartitions[mIndexedPartition].mHeaderViewsCount;
        if (paramInt1 == 1) {
          return 4;
        }
        if (paramInt2 == 0) {
          return 1;
        }
        if (paramInt2 == paramInt1 - 1) {
          return 3;
        }
        return 2;
      }
      if (isFooterView(mIndexedPartition, paramInt2))
      {
        paramInt1 = mPartitions[mIndexedPartition].mFooterViewsCount;
        i = mPartitions[mIndexedPartition].mCount - paramInt1;
        if (paramInt1 == 1) {
          return 4;
        }
        if (paramInt2 == i) {
          return 1;
        }
        if (paramInt2 - i == paramInt1 - 1) {
          return 3;
        }
        return 2;
      }
      int i = getSectionForPosition(paramInt2);
      int j = getPositionForSection(i);
      if (i == getSections().length - 1) {}
      for (paramInt1 = getCountForPartition(paramInt1);; paramInt1 = getPositionForSection(i + 1))
      {
        i = j;
        if (!mShowSectionHeaders) {
          break label199;
        }
        if (paramInt2 != j) {
          break;
        }
        return 0;
      }
      i = j + 1;
      label199:
      if ((paramInt2 == i) && (paramInt1 - i == 1)) {
        return 4;
      }
      if (paramInt2 == i) {
        return 1;
      }
      if (paramInt2 == paramInt1 - 1) {
        return 3;
      }
      return 2;
    }
    return super.getItemBackgroundType(paramInt1, paramInt2);
  }
  
  public Placement getItemPlacementInSection(int paramInt)
  {
    if (mPlacementCache.position == paramInt) {
      return mPlacementCache;
    }
    Placement.access$002(mPlacementCache, paramInt);
    boolean bool;
    if (isSectionHeaderDisplayEnabled())
    {
      int i = getSectionForPosition(paramInt);
      if ((i != -1) && (getPositionForSection(i) == paramInt))
      {
        mPlacementCache.firstInSection = true;
        mPlacementCache.sectionHeader = ((String)getSections()[i]);
        Placement localPlacement = mPlacementCache;
        if (getPositionForSection(i + 1) - 1 != paramInt) {
          break label127;
        }
        bool = true;
        label97:
        lastInSection = bool;
      }
    }
    for (;;)
    {
      return mPlacementCache;
      mPlacementCache.firstInSection = false;
      mPlacementCache.sectionHeader = null;
      break;
      label127:
      bool = false;
      break label97;
      mPlacementCache.firstInSection = false;
      mPlacementCache.lastInSection = false;
      mPlacementCache.sectionHeader = null;
    }
  }
  
  protected int getItemViewType(int paramInt1, int paramInt2)
  {
    if ((mIndexedPartition == paramInt1) && (mShowSectionHeaders) && (mHeaderMap.size() > 0))
    {
      int i = getOffsetInPartition(paramInt2);
      if (mHeaderMap.indexOfValue(i) >= 0) {
        return getViewTypeCount() - 1;
      }
    }
    return super.getItemViewType(paramInt1, paramInt2);
  }
  
  public int getPinnedHeaderCount()
  {
    if (isSectionHeaderDisplayEnabled()) {
      return super.getPinnedHeaderCount() + 1;
    }
    return super.getPinnedHeaderCount();
  }
  
  public View getPinnedHeaderView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((isSectionHeaderDisplayEnabled()) && (paramInt == getPinnedHeaderCount() - 1))
    {
      if (mHeader == null) {
        mHeader = createPinnedSectionHeaderView(mContext, paramViewGroup);
      }
      return mHeader;
    }
    return super.getPinnedHeaderView(paramInt, paramView, paramViewGroup);
  }
  
  public int getPositionForSection(int paramInt)
  {
    int k = 0;
    int i;
    if (mIndexer == null) {
      i = -1;
    }
    int j;
    do
    {
      return i;
      if (paramInt < 0) {
        return 0;
      }
      j = mIndexer.getPositionForSection(paramInt) + mPartitions[mIndexedPartition].mHeaderViewsCount;
      i = j;
    } while (!mShowSectionHeaders);
    for (;;)
    {
      i = j;
      if (k >= paramInt) {
        break;
      }
      i = j;
      if (mHeaderMap.indexOfKey(k) >= 0) {
        i = j + 1;
      }
      k += 1;
      j = i;
    }
  }
  
  public int getSectionForPosition(int paramInt)
  {
    if (mIndexer == null) {}
    int i;
    do
    {
      return -1;
      if ((isFooterView(mIndexedPartition, paramInt)) || (paramInt > mPartitions[mIndexedPartition].mCount - 1)) {
        return getSections().length - 1;
      }
      i = paramInt - mPartitions[mIndexedPartition].mHeaderViewsCount;
    } while (i < 0);
    int k = i;
    int j;
    if (mShowSectionHeaders) {
      j = 0;
    }
    for (;;)
    {
      k = i;
      if (j < mHeaderMap.size())
      {
        if (mHeaderMap.valueAt(j) >= paramInt) {
          k = i;
        }
      }
      else {
        return mIndexer.getSectionForPosition(k);
      }
      i -= 1;
      j += 1;
    }
  }
  
  protected View getSectionHeaderView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null) {}
    for (;;)
    {
      bindSectionHeaderView(paramView, mContext, paramInt1, paramInt2);
      return paramView;
      paramView = newSectionHeaderView(mContext, paramInt1, paramInt2, paramViewGroup);
    }
  }
  
  public Object[] getSections()
  {
    if (mIndexer == null) {
      return new String[] { " " };
    }
    return mIndexer.getSections();
  }
  
  protected View getView(int paramInt1, int paramInt2, int paramInt3, int paramInt4, View paramView, ViewGroup paramViewGroup)
  {
    if ((mIndexedPartition == paramInt2) && (mShowSectionHeaders))
    {
      int i = mHeaderMap.indexOfValue(paramInt3);
      if (i >= 0) {
        return getSectionHeaderView(paramInt3, mHeaderMap.keyAt(i), paramView, paramViewGroup);
      }
    }
    return super.getView(paramInt1, paramInt2, paramInt3, paramInt4, paramView, paramViewGroup);
  }
  
  public int getViewTypeCount()
  {
    return super.getViewTypeCount() + 1;
  }
  
  protected boolean isEnabled(int paramInt1, int paramInt2)
  {
    if ((mIndexedPartition == paramInt1) && (mShowSectionHeaders) && (mHeaderMap.indexOfValue(paramInt2) >= 0)) {
      return false;
    }
    return super.isEnabled(paramInt1, paramInt2);
  }
  
  protected boolean isPinnedSectionHeaderVisible(int paramInt)
  {
    return true;
  }
  
  public boolean isSectionHeaderDisplayEnabled()
  {
    return mSectionHeaderDisplayEnabled;
  }
  
  protected View newSectionHeaderView(Context paramContext, int paramInt1, int paramInt2, ViewGroup paramViewGroup)
  {
    return LayoutInflater.from(paramContext).inflate(R.layout.mc_pinned_group_header, paramViewGroup, false);
  }
  
  public void setIndexedPartition(int paramInt)
  {
    mIndexedPartition = paramInt;
    if (mShowSectionHeaders) {
      invalidate();
    }
  }
  
  public void setIndexer(SectionIndexer paramSectionIndexer)
  {
    mIndexer = paramSectionIndexer;
    mPlacementCache.invalidate();
    if (mShowSectionHeaders) {
      invalidate();
    }
  }
  
  protected void setPinnedSectionHeaderView(View paramView, int paramInt)
  {
    if (paramView != null) {
      ((TextView)paramView.findViewById(R.id.mc_header_text1)).setText((String)getSections()[paramInt]);
    }
  }
  
  public void setSectionHeaderDisplayEnabled(boolean paramBoolean)
  {
    mSectionHeaderDisplayEnabled = paramBoolean;
  }
  
  public void showSectionHeaders(boolean paramBoolean)
  {
    mShowSectionHeaders = paramBoolean;
    invalidate();
  }
  
  public static final class Placement
  {
    public boolean firstInSection;
    public boolean lastInSection;
    private int position = -1;
    public String sectionHeader;
    
    public void invalidate()
    {
      position = -1;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.PinnedHeaderIndexerArrayAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */