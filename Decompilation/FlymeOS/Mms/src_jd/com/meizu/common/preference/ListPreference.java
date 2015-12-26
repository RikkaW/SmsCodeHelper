package com.meizu.common.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.styleable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ListPreference
  extends android.preference.ListPreference
  implements ViewTreeObserver.OnGlobalLayoutListener, PopupWindow.OnDismissListener
{
  private static Field sPreferenceView;
  private static Method sSetLayoutMode;
  private ListAdapter mAdapter = null;
  private int mClickedDialogEntryIndex;
  private int mCurrentOrientation;
  private int mDropDownWidth;
  private int mMaxDropDownHeight;
  private DropdownPopup mPopup;
  private View mPreferenceViewExt;
  private int mSingleChoiceItemLayout;
  private ViewTreeObserver mVto;
  
  public ListPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ListPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ListPreference, 0, 0);
    mDropDownWidth = paramAttributeSet.getLayoutDimension(R.styleable.ListPreference_mcDropDownWidth, paramContext.getResources().getDimensionPixelSize(R.dimen.mz_popup_menu_item_min_width));
    mMaxDropDownHeight = paramAttributeSet.getLayoutDimension(R.styleable.ListPreference_mcMaxDropDownHeight, mMaxDropDownHeight);
    mSingleChoiceItemLayout = paramAttributeSet.getResourceId(R.styleable.ListPreference_mcSingleChoiceItemLayout, R.layout.mz_select_popup_singlechoice);
    paramAttributeSet.recycle();
  }
  
  public ListPopupWindow getDropdownPopup()
  {
    return mPopup;
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    mPreferenceViewExt = paramView;
  }
  
  protected void onClick()
  {
    try
    {
      if (sPreferenceView == null) {
        sPreferenceView = Preference.class.getDeclaredField("mPreferenceView");
      }
      Object localObject = sPreferenceView.get(this);
      if ((localObject instanceof View)) {
        mPreferenceViewExt = ((View)localObject);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        continue;
        mPopup.setAdapter(new ArrayAdapter(getContext(), mSingleChoiceItemLayout, R.id.text1, getEntries()));
      }
    }
    mCurrentOrientation = getContextgetResourcesgetConfigurationorientation;
    if (mPopup == null) {
      mPopup = new DropdownPopup(getContext());
    }
    mPreferenceViewExt.setActivated(true);
    mVto = mPreferenceViewExt.getViewTreeObserver();
    if (mVto != null) {
      mVto.addOnGlobalLayoutListener(this);
    }
    mPopup.setAnchorView(mPreferenceViewExt);
    if (mAdapter != null)
    {
      mPopup.setAdapter(mAdapter);
      mPopup.show();
      return;
    }
  }
  
  @TargetApi(16)
  public void onDismiss()
  {
    onDropdownPopupClosed();
    mPreferenceViewExt.setActivated(false);
    ViewTreeObserver localViewTreeObserver = mPreferenceViewExt.getViewTreeObserver();
    if (localViewTreeObserver != null) {
      localViewTreeObserver.removeOnGlobalLayoutListener(this);
    }
  }
  
  protected void onDropdownPopupClosed()
  {
    Object localObject = getEntryValues();
    if ((mClickedDialogEntryIndex >= 0) && (localObject != null))
    {
      localObject = localObject[mClickedDialogEntryIndex].toString();
      if (callChangeListener(localObject)) {
        setValue((String)localObject);
      }
    }
  }
  
  public void onGlobalLayout()
  {
    if (mPopup.isShowing())
    {
      if ((mPreferenceViewExt != null) && (mPreferenceViewExt.isShown()) && (mCurrentOrientation == getContextgetResourcesgetConfigurationorientation)) {
        break label55;
      }
      mPopup.dismiss();
    }
    label55:
    while ((!mPopup.isShowing()) || (mPreferenceViewExt == mPopup.getAnchorView())) {
      return;
    }
    mPopup.setAnchorView(mPreferenceViewExt);
    mPopup.show();
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (paramListAdapter != null) {
      mAdapter = paramListAdapter;
    }
  }
  
  public void setDropDownWidth(int paramInt)
  {
    mDropDownWidth = paramInt;
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    if ((!paramBoolean) && (mPopup != null) && (mPopup.isShowing())) {
      mPopup.dismiss();
    }
  }
  
  public void setMaxDropDownHeight(int paramInt)
  {
    mMaxDropDownHeight = paramInt;
  }
  
  class DropdownPopup
    extends ListPopupWindow
  {
    private ListAdapter mAdapter;
    
    public DropdownPopup(Context paramContext)
    {
      super();
      setModal(true);
      setPromptPosition(0);
      setOnItemClickListener(new ListPreference.DropdownPopup.1(this, ListPreference.this));
    }
    
    public void setAdapter(ListAdapter paramListAdapter)
    {
      super.setAdapter(paramListAdapter);
      mAdapter = paramListAdapter;
    }
    
    public void show()
    {
      int j = 0;
      int i = mPreferenceViewExt.getPaddingLeft();
      int k = mPreferenceViewExt.getPaddingRight();
      int m = mPreferenceViewExt.getWidth();
      if ((mDropDownWidth <= 0) || (mDropDownWidth > m - i - k)) {
        ListPreference.access$202(ListPreference.this, m - i - k);
      }
      setContentWidth(mDropDownWidth);
      try
      {
        if (ListPreference.sSetLayoutMode == null) {
          ListPreference.access$302(getClass().getMethod("setLayoutMode", new Class[] { Integer.TYPE }));
        }
        ListPreference.sSetLayoutMode.invoke(this, new Object[] { Integer.valueOf(4) });
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
      if (mMaxDropDownHeight > 0)
      {
        i = j;
        if (mAdapter != null)
        {
          i = j;
          if (mAdapter.getCount() > 0)
          {
            j = 0;
            k = 0;
            do
            {
              View localView = mAdapter.getView(j, null, getListView());
              i = k;
              if (localView != null)
              {
                localView.measure(0, 0);
                i = k + localView.getMeasuredHeight();
              }
              m = j + 1;
              j = m;
              k = i;
            } while (m < mAdapter.getCount());
          }
        }
        if (i > mMaxDropDownHeight) {
          setHeight(mMaxDropDownHeight);
        }
      }
      ListPreference.access$002(ListPreference.this, findIndexOfValue(getValue()));
      setInputMethodMode(2);
      super.show();
      getListView().setChoiceMode(1);
      setSelection(mClickedDialogEntryIndex);
      setOnDismissListener(ListPreference.this);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ListPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */