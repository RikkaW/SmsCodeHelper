package android.support.v4.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ListFragment
  extends Fragment
{
  static final int INTERNAL_EMPTY_ID = 16711681;
  static final int INTERNAL_LIST_CONTAINER_ID = 16711683;
  static final int INTERNAL_PROGRESS_CONTAINER_ID = 16711682;
  ListAdapter mAdapter;
  CharSequence mEmptyText;
  View mEmptyView;
  private final Handler mHandler = new Handler();
  ListView mList;
  View mListContainer;
  boolean mListShown;
  private final AdapterView.OnItemClickListener mOnClickListener = new ListFragment.2(this);
  View mProgressContainer;
  private final Runnable mRequestFocus = new ListFragment.1(this);
  TextView mStandardEmptyView;
  
  private void ensureList()
  {
    if (mList != null) {
      return;
    }
    Object localObject = getView();
    if (localObject == null) {
      throw new IllegalStateException("Content view not yet created");
    }
    if ((localObject instanceof ListView))
    {
      mList = ((ListView)localObject);
      mListShown = true;
      mList.setOnItemClickListener(mOnClickListener);
      if (mAdapter == null) {
        break label254;
      }
      localObject = mAdapter;
      mAdapter = null;
      setListAdapter((ListAdapter)localObject);
    }
    for (;;)
    {
      mHandler.post(mRequestFocus);
      return;
      mStandardEmptyView = ((TextView)((View)localObject).findViewById(16711681));
      if (mStandardEmptyView == null) {
        mEmptyView = ((View)localObject).findViewById(16908292);
      }
      for (;;)
      {
        mProgressContainer = ((View)localObject).findViewById(16711682);
        mListContainer = ((View)localObject).findViewById(16711683);
        localObject = ((View)localObject).findViewById(16908298);
        if ((localObject instanceof ListView)) {
          break label193;
        }
        if (localObject != null) {
          break;
        }
        throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
        mStandardEmptyView.setVisibility(8);
      }
      throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
      label193:
      mList = ((ListView)localObject);
      if (mEmptyView != null)
      {
        mList.setEmptyView(mEmptyView);
        break;
      }
      if (mEmptyText == null) {
        break;
      }
      mStandardEmptyView.setText(mEmptyText);
      mList.setEmptyView(mStandardEmptyView);
      break;
      label254:
      if (mProgressContainer != null) {
        setListShown(false, false);
      }
    }
  }
  
  private void setListShown(boolean paramBoolean1, boolean paramBoolean2)
  {
    ensureList();
    if (mProgressContainer == null) {
      throw new IllegalStateException("Can't be used with a custom content view");
    }
    if (mListShown == paramBoolean1) {
      return;
    }
    mListShown = paramBoolean1;
    if (paramBoolean1)
    {
      if (paramBoolean2)
      {
        mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432577));
        mListContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432576));
      }
      for (;;)
      {
        mProgressContainer.setVisibility(8);
        mListContainer.setVisibility(0);
        return;
        mProgressContainer.clearAnimation();
        mListContainer.clearAnimation();
      }
    }
    if (paramBoolean2)
    {
      mProgressContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432576));
      mListContainer.startAnimation(AnimationUtils.loadAnimation(getActivity(), 17432577));
    }
    for (;;)
    {
      mProgressContainer.setVisibility(0);
      mListContainer.setVisibility(8);
      return;
      mProgressContainer.clearAnimation();
      mListContainer.clearAnimation();
    }
  }
  
  public ListAdapter getListAdapter()
  {
    return mAdapter;
  }
  
  public ListView getListView()
  {
    ensureList();
    return mList;
  }
  
  public long getSelectedItemId()
  {
    ensureList();
    return mList.getSelectedItemId();
  }
  
  public int getSelectedItemPosition()
  {
    ensureList();
    return mList.getSelectedItemPosition();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramViewGroup = getActivity();
    paramLayoutInflater = new FrameLayout(paramViewGroup);
    paramBundle = new LinearLayout(paramViewGroup);
    paramBundle.setId(16711682);
    paramBundle.setOrientation(1);
    paramBundle.setVisibility(8);
    paramBundle.setGravity(17);
    paramBundle.addView(new ProgressBar(paramViewGroup, null, 16842874), new FrameLayout.LayoutParams(-2, -2));
    paramLayoutInflater.addView(paramBundle, new FrameLayout.LayoutParams(-1, -1));
    paramViewGroup = new FrameLayout(paramViewGroup);
    paramViewGroup.setId(16711683);
    paramBundle = new TextView(getActivity());
    paramBundle.setId(16711681);
    paramBundle.setGravity(17);
    paramViewGroup.addView(paramBundle, new FrameLayout.LayoutParams(-1, -1));
    paramBundle = new ListView(getActivity());
    paramBundle.setId(16908298);
    paramBundle.setDrawSelectorOnTop(false);
    paramViewGroup.addView(paramBundle, new FrameLayout.LayoutParams(-1, -1));
    paramLayoutInflater.addView(paramViewGroup, new FrameLayout.LayoutParams(-1, -1));
    paramLayoutInflater.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    return paramLayoutInflater;
  }
  
  public void onDestroyView()
  {
    mHandler.removeCallbacks(mRequestFocus);
    mList = null;
    mListShown = false;
    mListContainer = null;
    mProgressContainer = null;
    mEmptyView = null;
    mStandardEmptyView = null;
    super.onDestroyView();
  }
  
  public void onListItemClick(ListView paramListView, View paramView, int paramInt, long paramLong) {}
  
  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    ensureList();
  }
  
  public void setEmptyText(CharSequence paramCharSequence)
  {
    ensureList();
    if (mStandardEmptyView == null) {
      throw new IllegalStateException("Can't be used with a custom content view");
    }
    mStandardEmptyView.setText(paramCharSequence);
    if (mEmptyText == null) {
      mList.setEmptyView(mStandardEmptyView);
    }
    mEmptyText = paramCharSequence;
  }
  
  public void setListAdapter(ListAdapter paramListAdapter)
  {
    boolean bool = false;
    if (mAdapter != null) {}
    for (int i = 1;; i = 0)
    {
      mAdapter = paramListAdapter;
      if (mList != null)
      {
        mList.setAdapter(paramListAdapter);
        if ((!mListShown) && (i == 0))
        {
          if (getView().getWindowToken() != null) {
            bool = true;
          }
          setListShown(true, bool);
        }
      }
      return;
    }
  }
  
  public void setListShown(boolean paramBoolean)
  {
    setListShown(paramBoolean, true);
  }
  
  public void setListShownNoAnimation(boolean paramBoolean)
  {
    setListShown(paramBoolean, false);
  }
  
  public void setSelection(int paramInt)
  {
    ensureList();
    mList.setSelection(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ListFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */