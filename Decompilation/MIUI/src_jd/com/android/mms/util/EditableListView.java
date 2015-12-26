package com.android.mms.util;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.R.styleable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import junit.framework.Assert;

public class EditableListView
  extends ListView
{
  private static final int DOUBLE_CLICK_TIMEOUT = ;
  private ActionMode mActionMode;
  private EditableListData mCheckedData;
  private DataSetObserver mDataSetObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      if ((mHasFillArea) && (mHeaderHidden))
      {
        EditableListView.this.unregisterDataSetObserver();
        EditableListView.access$1402(EditableListView.this, true);
      }
    }
  };
  private boolean mDragEnabled = false;
  private float mDragStartX = 0.0F;
  private float mDragStartY = 0.0F;
  private Draggable mDraggingItem;
  private ImageView mEmptyImgView;
  private TextView mEmptyTxtView;
  private View mFooterContainer;
  private View mFooterView;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      default: 
        return;
      case 1: 
        mOnDoubleClickListener.onSingleClick(mParent, mView, mPosition, mId);
        EditableListView.access$502(EditableListView.this, false);
        return;
      }
      mOnDoubleClickListener.onDoubleClick(mParent, mView, mPosition, mId);
      EditableListView.access$502(EditableListView.this, false);
    }
  };
  private boolean mHasFillArea;
  private boolean mHasFirstEvent = false;
  private int mHeaderHeight;
  private boolean mHeaderHidden = true;
  private boolean mHeaderHiddenLayoutEnabled;
  private int mHiddenItemCount = 1;
  private long mId = 0L;
  private Integer mInitPosition;
  private Message mMessage = null;
  private EditModeWrapper mModeCallback;
  private int mMoveMode = 0;
  private boolean mNeedToScrollEnd = false;
  private OnItemDoubleClickListener mOnDoubleClickListener;
  private AdapterView<?> mParent = null;
  private int mPosition = -1;
  private int mPositionHolder = -1;
  private boolean mPreClickable;
  private AdapterView.OnItemClickListener mPreItemClickListener;
  private boolean mPreLongClickable;
  private int mRowHeight;
  private View mView = null;
  
  public EditableListView(Context paramContext)
  {
    super(paramContext);
    init(null);
  }
  
  public EditableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }
  
  private void init(AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null) {
      paramAttributeSet = getContext().getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.EditableListView, 0, 0);
    }
    try
    {
      mHasFillArea = paramAttributeSet.getBoolean(0, mHasFillArea);
      paramAttributeSet.recycle();
      if (mHasFillArea)
      {
        mFooterView = LayoutInflater.from(getContext()).inflate(2130968599, this, false);
        addFooterView(mFooterView);
        mFooterContainer = mFooterView.findViewById(2131820606);
        mEmptyTxtView = ((TextView)mFooterView.findViewById(2131820608));
        mEmptyImgView = ((ImageView)mFooterView.findViewById(2131820607));
      }
      return;
    }
    finally
    {
      paramAttributeSet.recycle();
    }
  }
  
  private void onDraggableItemDragged(int paramInt1, int paramInt2)
  {
    if (mDraggingItem == null)
    {
      View localView = getChildAt(paramInt1);
      if ((localView instanceof Draggable)) {
        mDraggingItem = ((Draggable)localView);
      }
    }
    if (mDraggingItem != null) {
      mDraggingItem.onItemDragged(paramInt2);
    }
  }
  
  private void onDraggableItemRelease(int paramInt)
  {
    if (mDraggingItem != null)
    {
      mDraggingItem.onItemDragReleased(paramInt);
      mDraggingItem = null;
    }
  }
  
  private void registerDataSetObserver()
  {
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter != null) {
      localListAdapter.registerDataSetObserver(mDataSetObserver);
    }
  }
  
  private void setListHeaderHeight(int paramInt)
  {
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter == null) {
      resetHeaderHeight();
    }
    while (mHeaderHeight != 0) {
      return;
    }
    int i = mHiddenItemCount;
    if (!mHeaderHidden) {
      i = 0;
    }
    while (i < getHeaderViewsCount())
    {
      if (localListAdapter.getItemViewType(i) == -2)
      {
        View localView = localListAdapter.getView(i, null, null);
        if (localView != null)
        {
          localView.measure(paramInt, 0);
          mHeaderHeight += localView.getMeasuredHeight();
        }
      }
      i += 1;
    }
    mHeaderHeight = Math.max(mHeaderHeight, 0);
  }
  
  private void setListItemHeight(int paramInt)
  {
    Object localObject = getAdapter();
    if (localObject == null) {
      mRowHeight = 0;
    }
    while ((mRowHeight != 0) || (((ListAdapter)localObject).getItemViewType(getHeaderViewsCount()) == -2)) {
      return;
    }
    localObject = ((ListAdapter)localObject).getView(getHeaderViewsCount(), null, null);
    if (localObject != null)
    {
      ((View)localObject).measure(paramInt, 0);
      mRowHeight = ((View)localObject).getMeasuredHeight();
    }
    mRowHeight = Math.max(mRowHeight, 0);
  }
  
  private void triggerDragEvent(MotionEvent paramMotionEvent)
  {
    onDraggableItemDragged(pointToPosition((int)mDragStartX, (int)mDragStartY) - getFirstVisiblePosition(), (int)(paramMotionEvent.getX() - mDragStartX));
  }
  
  private void unregisterDataSetObserver()
  {
    ListAdapter localListAdapter = getAdapter();
    if (localListAdapter != null) {}
    try
    {
      localListAdapter.unregisterDataSetObserver(mDataSetObserver);
      return;
    }
    catch (IllegalStateException localIllegalStateException) {}
  }
  
  public void clearDragAnimation()
  {
    if (mDraggingItem != null)
    {
      mDraggingItem.onItemDragged(0);
      mDraggingItem = null;
    }
  }
  
  public void clearEmptyImgAndTxt()
  {
    mEmptyImgView.setVisibility(8);
    mEmptyTxtView.setVisibility(8);
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!mDragEnabled) || (isEditMode())) {
      return super.dispatchTouchEvent(paramMotionEvent);
    }
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      mMoveMode = 1;
      mDragStartX = paramMotionEvent.getX();
      mDragStartY = paramMotionEvent.getY();
      continue;
      if (mMoveMode == 1)
      {
        if ((paramMotionEvent.getX() - mDragStartX > 30.0F) && (Math.abs(paramMotionEvent.getY() - mDragStartY) < 15.0F))
        {
          triggerDragEvent(paramMotionEvent);
          mMoveMode = 2;
          paramMotionEvent.setAction(3);
          super.dispatchTouchEvent(paramMotionEvent);
          return true;
        }
        if (Math.abs(paramMotionEvent.getY() - mDragStartY) > 100.0F) {
          mMoveMode = 0;
        }
      }
      else if (mMoveMode == 2)
      {
        triggerDragEvent(paramMotionEvent);
        paramMotionEvent.setAction(3);
        super.dispatchTouchEvent(paramMotionEvent);
        return true;
        if (mMoveMode == 2) {
          onDraggableItemRelease((int)(paramMotionEvent.getX() - mDragStartX));
        }
        mMoveMode = 0;
      }
    }
  }
  
  public void enableDragEvent(boolean paramBoolean)
  {
    mDragEnabled = paramBoolean;
  }
  
  public void enableEmptyImgView(boolean paramBoolean)
  {
    if (mHasFillArea) {
      mEmptyImgView.setEnabled(paramBoolean);
    }
  }
  
  public void enterEditMode(int paramInt)
  {
    if ((mModeCallback == null) || (mCheckedData.mEditMode)) {
      return;
    }
    mInitPosition = Integer.valueOf(paramInt);
    mActionMode = startActionMode(mModeCallback);
  }
  
  public void exitEditMode()
  {
    if ((mModeCallback == null) || (!mCheckedData.mEditMode)) {
      return;
    }
    mInitPosition = null;
    mActionMode.finish();
  }
  
  public EditableListViewCheckable getEditableListViewCheckable()
  {
    if (mModeCallback != null) {
      return mCheckedData;
    }
    return null;
  }
  
  public boolean isDataSetChanged()
  {
    if (mModeCallback != null) {
      return mCheckedData.mDataSetChanged;
    }
    return false;
  }
  
  public boolean isEditMode()
  {
    if (mModeCallback != null) {
      return mCheckedData.mEditMode;
    }
    return false;
  }
  
  public void moveToEnd()
  {
    int i = getAdapter().getCount() - getFooterViewsCount();
    if (i > 0) {
      setSelectionFromTop(i - 1, -100000);
    }
  }
  
  protected void onDetachedFromWindow()
  {
    unregisterDataSetObserver();
    super.onDetachedFromWindow();
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((mHasFillArea) && (mHeaderHidden) && (mHeaderHiddenLayoutEnabled))
    {
      mHeaderHiddenLayoutEnabled = false;
      setSelectionFromTop(mHiddenItemCount, 0);
    }
    for (;;)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
      if (mNeedToScrollEnd) {
        moveToEnd();
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = 8;
    int i;
    if (mHasFillArea)
    {
      i = View.MeasureSpec.getMode(paramInt2);
      int m = View.MeasureSpec.getSize(paramInt2);
      if ((i == 1073741824) || (i == Integer.MIN_VALUE))
      {
        i = 0;
        setListItemHeight(paramInt1);
        Object localObject = getAdapter();
        if (localObject != null)
        {
          int n = ((ListAdapter)localObject).getCount() - getHeaderViewsCount() - getFooterViewsCount();
          int k = n * mRowHeight;
          i = k;
          if (mEmptyImgView.isEnabled())
          {
            localObject = mEmptyImgView;
            if (n != 0) {
              break label185;
            }
            i = 0;
            ((ImageView)localObject).setVisibility(i);
            localObject = mEmptyTxtView;
            i = j;
            if (n == 0) {
              i = 0;
            }
            ((TextView)localObject).setVisibility(i);
            i = k;
          }
        }
        setListHeaderHeight(paramInt1);
        i = Math.max(m - (i + mHeaderHeight), 0);
        mFooterContainer.getLayoutParams().height = i;
        if (i <= 0) {
          break label191;
        }
        setVerticalScrollBarEnabled(false);
      }
    }
    for (;;)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
      label185:
      i = 8;
      break;
      label191:
      setVerticalScrollBarEnabled(true);
    }
  }
  
  public void resetHeaderHeight()
  {
    mHeaderHeight = 0;
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    unregisterDataSetObserver();
    super.setAdapter(paramListAdapter);
    registerDataSetObserver();
    if (mModeCallback != null) {
      mCheckedData.setAdapter(paramListAdapter);
    }
  }
  
  public void setEditModeListener(EditModeListener paramEditModeListener)
  {
    setEditModeListener(paramEditModeListener, true);
  }
  
  public void setEditModeListener(EditModeListener paramEditModeListener, boolean paramBoolean)
  {
    if (paramEditModeListener != null)
    {
      if (mModeCallback == null) {
        mModeCallback = new EditModeWrapper();
      }
      mModeCallback.setWrapped(paramEditModeListener);
      if (mCheckedData == null) {
        mCheckedData = new EditableListData();
      }
      for (;;)
      {
        if (paramBoolean) {
          setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
          {
            public boolean onItemLongClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
              if (getAdapter().getItemViewType(paramAnonymousInt) == -2) {
                return false;
              }
              enterEditMode(paramAnonymousInt);
              return true;
            }
          });
        }
        if ((!mCheckedData.hasAdapter()) && (getAdapter() != null))
        {
          ListAdapter localListAdapter = getAdapter();
          paramEditModeListener = localListAdapter;
          if ((localListAdapter instanceof HeaderViewListAdapter)) {
            paramEditModeListener = ((HeaderViewListAdapter)localListAdapter).getWrappedAdapter();
          }
          mCheckedData.setAdapter(paramEditModeListener);
        }
        return;
        mCheckedData.clear();
      }
    }
    mModeCallback = null;
  }
  
  public void setEmptyImgViewImageResource(int paramInt)
  {
    if (mHasFillArea) {
      mEmptyImgView.setImageResource(paramInt);
    }
  }
  
  public void setEmptyTxtViewText(int paramInt)
  {
    if (mHasFillArea) {
      mEmptyTxtView.setText(paramInt);
    }
  }
  
  public void setHeaderHidden(boolean paramBoolean)
  {
    mHeaderHidden = paramBoolean;
  }
  
  public void setNeedToScrollEnd(boolean paramBoolean)
  {
    mNeedToScrollEnd = paramBoolean;
  }
  
  public void setOnItemDoubleClickListener(OnItemDoubleClickListener paramOnItemDoubleClickListener)
  {
    mOnDoubleClickListener = paramOnItemDoubleClickListener;
    if (mOnDoubleClickListener == null) {
      return;
    }
    setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        EditableListView.access$002(EditableListView.this, paramAnonymousAdapterView);
        EditableListView.access$102(EditableListView.this, paramAnonymousView);
        EditableListView.access$202(EditableListView.this, paramAnonymousInt);
        EditableListView.access$302(EditableListView.this, paramAnonymousLong);
        if (!mHasFirstEvent)
        {
          EditableListView.access$602(EditableListView.this, paramAnonymousInt);
          EditableListView.access$502(EditableListView.this, true);
          EditableListView.access$702(EditableListView.this, mHandler.obtainMessage());
          mHandler.removeMessages(1);
          mMessage.what = 1;
          mHandler.sendMessageDelayed(mMessage, EditableListView.DOUBLE_CLICK_TIMEOUT);
          return;
        }
        if (mPositionHolder == paramAnonymousInt)
        {
          mHandler.removeMessages(1);
          EditableListView.access$202(EditableListView.this, paramAnonymousInt);
          EditableListView.access$702(EditableListView.this, mHandler.obtainMessage());
          mMessage.what = 2;
          mHandler.sendMessageAtFrontOfQueue(mMessage);
          EditableListView.access$502(EditableListView.this, false);
          return;
        }
        EditableListView.access$702(EditableListView.this, mHandler.obtainMessage());
        mHandler.removeMessages(1);
        EditableListView.access$502(EditableListView.this, true);
        mMessage.what = 1;
        EditableListView.access$602(EditableListView.this, paramAnonymousInt);
        mHandler.sendMessageDelayed(mMessage, EditableListView.DOUBLE_CLICK_TIMEOUT);
      }
    });
  }
  
  public static abstract interface Draggable
  {
    public abstract void onItemDragReleased(int paramInt);
    
    public abstract void onItemDragged(int paramInt);
  }
  
  public static abstract interface EditModeListener
    extends ActionMode.Callback
  {
    public abstract void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable);
    
    public abstract void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean);
  }
  
  class EditModeWrapper
    implements EditableListView.EditModeListener
  {
    private EditableListView.EditModeListener mWrapped;
    
    EditModeWrapper() {}
    
    private int handleHeadFooterPosition(int paramInt)
    {
      if (paramInt < 0) {
        return paramInt;
      }
      int i = getHeaderViewsCount();
      if ((paramInt < i) || (paramInt >= getCount() - getFooterViewsCount())) {
        return -1;
      }
      return paramInt - i;
    }
    
    private void innerEnterEditMode(Integer paramInteger)
    {
      if (mCheckedData.mEditMode) {
        return;
      }
      EditableListView.access$2002(EditableListView.this, isClickable());
      EditableListView.access$2102(EditableListView.this, isLongClickable());
      EditableListView.access$2202(EditableListView.this, getOnItemClickListener());
      setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousInt = EditableListView.EditModeWrapper.this.handleHeadFooterPosition(paramAnonymousInt);
          if (paramAnonymousInt != -1) {
            EditableListView.EditableListData.access$2400(mCheckedData, paramAnonymousView, paramAnonymousInt);
          }
        }
      });
      setLongClickable(false);
      Integer localInteger = null;
      if (paramInteger != null)
      {
        paramInteger = Integer.valueOf(handleHeadFooterPosition(paramInteger.intValue()));
        localInteger = paramInteger;
        if (paramInteger.intValue() == -1) {
          localInteger = null;
        }
      }
      EditableListView.EditableListData.access$2500(mCheckedData, localInteger);
    }
    
    private void innerExitEditMode()
    {
      if (!mCheckedData.mEditMode) {
        return;
      }
      setOnItemClickListener(mPreItemClickListener);
      setClickable(mPreClickable);
      setLongClickable(mPreLongClickable);
      EditableListView.EditableListData.access$2600(mCheckedData);
    }
    
    public boolean onActionItemClicked(ActionMode paramActionMode, MenuItem paramMenuItem)
    {
      return mWrapped.onActionItemClicked(paramActionMode, paramMenuItem);
    }
    
    public void onCheckStateChanged(EditableListView.EditableListViewCheckable paramEditableListViewCheckable)
    {
      mWrapped.onCheckStateChanged(paramEditableListViewCheckable);
    }
    
    public boolean onCreateActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      if (mWrapped.onCreateActionMode(paramActionMode, paramMenu))
      {
        innerEnterEditMode(mInitPosition);
        return true;
      }
      return false;
    }
    
    public void onDestroyActionMode(ActionMode paramActionMode)
    {
      mWrapped.onDestroyActionMode(paramActionMode);
      EditableListView.access$1802(EditableListView.this, null);
      innerExitEditMode();
    }
    
    public boolean onPrepareActionMode(ActionMode paramActionMode, Menu paramMenu)
    {
      return mWrapped.onPrepareActionMode(paramActionMode, paramMenu);
    }
    
    public void onVisibleViewCheckStateChanged(View paramView, boolean paramBoolean)
    {
      mWrapped.onVisibleViewCheckStateChanged(paramView, paramBoolean);
    }
    
    public void setWrapped(EditableListView.EditModeListener paramEditModeListener)
    {
      mWrapped = paramEditModeListener;
    }
  }
  
  private class EditableListData
    extends DataSetObserver
    implements EditableListView.EditableListViewCheckable
  {
    private ListAdapter mAdapter;
    private HashSet<Long> mCheckedIds;
    public boolean mDataSetChanged;
    public boolean mEditMode;
    private HashMap<Long, Integer> mIdPositionMap;
    private boolean mInnerDateSetChange;
    private boolean mIsCheckDataDirty;
    private Integer mToggleIndex;
    
    protected EditableListData()
    {
      clear();
    }
    
    private void cleanupCheckedData()
    {
      if (mIsCheckDataDirty)
      {
        HashSet localHashSet = new HashSet();
        mIdPositionMap.clear();
        int i = 0;
        while (i < mAdapter.getCount())
        {
          Long localLong = Long.valueOf(getIdFromPosition(i));
          if (mCheckedIds.contains(localLong)) {
            localHashSet.add(localLong);
          }
          mIdPositionMap.put(localLong, Integer.valueOf(i));
          i += 1;
        }
        mCheckedIds = localHashSet;
        mIsCheckDataDirty = false;
      }
    }
    
    private void enterEditMode(Integer paramInteger)
    {
      if ((!mEditMode) && (mAdapter != null) && (mModeCallback != null))
      {
        mEditMode = true;
        mCheckedIds.clear();
        mToggleIndex = paramInteger;
        if (paramInteger != null)
        {
          innerToggleAt(paramInteger.intValue());
          mModeCallback.onCheckStateChanged(this);
        }
        if ((mAdapter instanceof BaseAdapter))
        {
          mInnerDateSetChange = true;
          mDataSetChanged = true;
          ((BaseAdapter)mAdapter).notifyDataSetChanged();
          mDataSetChanged = false;
        }
      }
    }
    
    private void exitEditMode()
    {
      if ((mEditMode) && (mAdapter != null) && (mModeCallback != null))
      {
        mEditMode = false;
        mCheckedIds.clear();
        if ((mAdapter instanceof BaseAdapter))
        {
          mInnerDateSetChange = true;
          mDataSetChanged = true;
          ((BaseAdapter)mAdapter).notifyDataSetChanged();
          mDataSetChanged = false;
        }
      }
    }
    
    private long getIdFromPosition(int paramInt)
    {
      if ((mAdapter instanceof EditableListView.EditableListIdMapper)) {
        return ((EditableListView.EditableListIdMapper)mAdapter).mapPositionToId(paramInt);
      }
      return mAdapter.getItemId(paramInt);
    }
    
    private boolean hasAdapter()
    {
      return mAdapter != null;
    }
    
    private boolean innerToggleAt(int paramInt)
    {
      if (((mAdapter instanceof EditableListView.ICheckableAdapter)) && (!((EditableListView.ICheckableAdapter)mAdapter).allowChecked(paramInt))) {
        return false;
      }
      long l = getIdFromPosition(paramInt);
      if (mCheckedIds.contains(Long.valueOf(l)))
      {
        mCheckedIds.remove(Long.valueOf(l));
        return false;
      }
      mCheckedIds.add(Long.valueOf(l));
      return true;
    }
    
    private void notifyCheckStateChanged(Integer paramInteger)
    {
      mToggleIndex = paramInteger;
      if (mModeCallback != null) {
        mModeCallback.onCheckStateChanged(this);
      }
    }
    
    private void setAdapter(ListAdapter paramListAdapter)
    {
      if (mAdapter != null) {
        mAdapter.unregisterDataSetObserver(this);
      }
      mAdapter = paramListAdapter;
      if (mAdapter != null)
      {
        Assert.assertEquals(true, mAdapter instanceof BaseAdapter);
        if (!mAdapter.hasStableIds()) {
          Assert.assertEquals(true, mAdapter instanceof EditableListView.EditableListIdMapper);
        }
        mAdapter.registerDataSetObserver(this);
      }
      mCheckedIds.clear();
      mIdPositionMap.clear();
      mIsCheckDataDirty = true;
      if (mEditMode) {
        notifyCheckStateChanged(null);
      }
    }
    
    private Boolean toggleAt(View paramView, int paramInt)
    {
      if ((mEditMode) && (mAdapter != null))
      {
        boolean bool = innerToggleAt(paramInt);
        CheckBox localCheckBox = (CheckBox)paramView.findViewById(16908289);
        if ((localCheckBox != null) && (localCheckBox.isEnabled())) {
          localCheckBox.setChecked(bool);
        }
        if (mModeCallback != null) {
          mModeCallback.onVisibleViewCheckStateChanged(paramView, bool);
        }
        notifyCheckStateChanged(Integer.valueOf(paramInt));
        return Boolean.valueOf(bool);
      }
      return null;
    }
    
    public void checkAll()
    {
      if ((mEditMode) && (mAdapter != null))
      {
        Object localObject;
        long l;
        if ((mAdapter instanceof EditableListView.ICheckableAdapter))
        {
          localObject = (EditableListView.ICheckableAdapter)mAdapter;
          int k = ((EditableListView.ICheckableAdapter)localObject).getDisableCheckedCount();
          int j = 0;
          i = 0;
          if (i < mAdapter.getCount())
          {
            if (j == k)
            {
              l = getIdFromPosition(i);
              mCheckedIds.add(Long.valueOf(l));
            }
            for (;;)
            {
              i += 1;
              break;
              if (((EditableListView.ICheckableAdapter)localObject).allowChecked(i))
              {
                l = getIdFromPosition(i);
                mCheckedIds.add(Long.valueOf(l));
              }
              else
              {
                j += 1;
              }
            }
          }
        }
        else
        {
          i = 0;
          while (i < mAdapter.getCount())
          {
            l = getIdFromPosition(i);
            mCheckedIds.add(Long.valueOf(l));
            i += 1;
          }
        }
        int i = 0;
        while (i < getChildCount())
        {
          localObject = getChildAt(i);
          CheckBox localCheckBox = (CheckBox)((View)localObject).findViewById(16908289);
          if ((localCheckBox != null) && (localCheckBox.isEnabled())) {
            localCheckBox.setChecked(true);
          }
          if (mModeCallback != null) {
            mModeCallback.onVisibleViewCheckStateChanged((View)localObject, true);
          }
          i += 1;
        }
        notifyCheckStateChanged(null);
      }
    }
    
    public void checkNothing()
    {
      if ((mEditMode) && (mAdapter != null))
      {
        mCheckedIds.clear();
        int i = 0;
        while (i < getChildCount())
        {
          View localView = getChildAt(i);
          CheckBox localCheckBox = (CheckBox)localView.findViewById(16908289);
          if ((localCheckBox != null) && (localCheckBox.isEnabled())) {
            localCheckBox.setChecked(false);
          }
          if (mModeCallback != null) {
            mModeCallback.onVisibleViewCheckStateChanged(localView, false);
          }
          i += 1;
        }
        notifyCheckStateChanged(null);
      }
    }
    
    protected void clear()
    {
      mInnerDateSetChange = false;
      mEditMode = false;
      mIsCheckDataDirty = true;
      if (mCheckedIds == null) {
        mCheckedIds = new HashSet();
      }
      for (;;)
      {
        mToggleIndex = null;
        if (mIdPositionMap != null) {
          break;
        }
        mIdPositionMap = new HashMap();
        return;
        mCheckedIds.clear();
      }
      mIdPositionMap.clear();
    }
    
    public int count()
    {
      if ((mEditMode) && (mAdapter != null))
      {
        cleanupCheckedData();
        return mCheckedIds.size();
      }
      return 0;
    }
    
    public HashSet<Long> getCheckedItemInIds()
    {
      if ((mEditMode) && (mAdapter != null))
      {
        cleanupCheckedData();
        return new HashSet(mCheckedIds);
      }
      return new HashSet();
    }
    
    public HashSet<Integer> getCheckedItemInPositions()
    {
      HashSet localHashSet = new HashSet();
      if ((mEditMode) && (mAdapter != null))
      {
        cleanupCheckedData();
        Iterator localIterator = mCheckedIds.iterator();
        while (localIterator.hasNext())
        {
          Long localLong = (Long)localIterator.next();
          localHashSet.add(mIdPositionMap.get(localLong));
        }
      }
      return localHashSet;
    }
    
    public boolean isAllChecked()
    {
      boolean bool2 = true;
      boolean bool1 = true;
      EditableListView.ICheckableAdapter localICheckableAdapter;
      int j;
      int i;
      long l;
      if ((mEditMode) && (mAdapter != null))
      {
        cleanupCheckedData();
        if (!(mAdapter instanceof EditableListView.ICheckableAdapter)) {
          break label163;
        }
        localICheckableAdapter = (EditableListView.ICheckableAdapter)mAdapter;
        int k = localICheckableAdapter.getDisableCheckedCount();
        j = 0;
        i = 0;
        if (i >= mAdapter.getCount()) {
          break label144;
        }
        if (j != k) {
          break label97;
        }
        l = getIdFromPosition(i);
        if (mCheckedIds.contains(Long.valueOf(l))) {
          break label130;
        }
      }
      label97:
      do
      {
        return false;
        if (!localICheckableAdapter.allowChecked(i)) {
          break;
        }
        l = getIdFromPosition(i);
      } while (!mCheckedIds.contains(Long.valueOf(l)));
      for (;;)
      {
        label130:
        i += 1;
        break;
        j += 1;
      }
      label144:
      if (mCheckedIds.size() > 0) {}
      for (;;)
      {
        return bool1;
        bool1 = false;
      }
      label163:
      if (mAdapter.getCount() == mCheckedIds.size()) {}
      for (bool1 = bool2;; bool1 = false) {
        return bool1;
      }
    }
    
    public void onChanged()
    {
      if (!mInnerDateSetChange)
      {
        mIsCheckDataDirty = true;
        if (mEditMode) {
          notifyCheckStateChanged(null);
        }
      }
      mInnerDateSetChange = false;
    }
    
    public void onInvalidated()
    {
      mCheckedIds.clear();
      mIdPositionMap.clear();
      mIsCheckDataDirty = true;
      if (mEditMode) {
        notifyCheckStateChanged(null);
      }
    }
  }
  
  public static abstract interface EditableListIdMapper
  {
    public abstract long mapPositionToId(int paramInt);
  }
  
  public static abstract interface EditableListViewCheckable
  {
    public abstract void checkAll();
    
    public abstract void checkNothing();
    
    public abstract int count();
    
    public abstract HashSet<Long> getCheckedItemInIds();
    
    public abstract HashSet<Integer> getCheckedItemInPositions();
    
    public abstract boolean isAllChecked();
  }
  
  public static abstract interface ICheckableAdapter
  {
    public abstract boolean allowChecked(int paramInt);
    
    public abstract int getDisableCheckedCount();
  }
  
  public static abstract interface OnItemDoubleClickListener
  {
    public abstract void onDoubleClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong);
    
    public abstract void onSingleClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.EditableListView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */