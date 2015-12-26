package com.meizu.common.preference;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.styleable;
import java.lang.reflect.Field;

@SuppressLint({"NewApi"})
public class ExpandableListPreference
  extends Preference
{
  private int ANIMATION_DURATION = 400;
  private PreferenceAdapter mAdapter;
  private AnimHelper mAnimHelper;
  private CharSequence[] mEntries;
  private CharSequence[] mEntryValues;
  private Handler mHandler = new Handler();
  private ImageView mImageView;
  public boolean mIsExtand = false;
  private boolean mIsWaitingToChange = false;
  private LinearLayout mLinearLayout;
  private ListView mListView;
  private TextView mSummary;
  private String mValue;
  private boolean mValueSet;
  private Runnable performClick = new ExpandableListPreference.2(this);
  
  public ExpandableListPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public ExpandableListPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ExpandableListPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ExpandableListPreference, 0, 0);
    mEntries = paramAttributeSet.getTextArray(R.styleable.ExpandableListPreference_mcEntries);
    mEntryValues = paramAttributeSet.getTextArray(R.styleable.ExpandableListPreference_mcEntryValues);
    paramAttributeSet.recycle();
    setLayoutResource(R.layout.mc_expandable_preference_layout);
    mAnimHelper = new AnimHelper();
    mAnimHelper.setMarginTop(-paramContext.getResources().getDimensionPixelSize(R.dimen.mc_expandable_preference_inner_list_margin));
  }
  
  private int getValueIndex()
  {
    return findIndexOfValue(mValue);
  }
  
  private void setListSummary(CharSequence paramCharSequence)
  {
    try
    {
      Field localField = Preference.class.getDeclaredField("mSummary");
      localField.setAccessible(true);
      localField.set(this, paramCharSequence);
      return;
    }
    catch (NoSuchFieldException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
      return;
    }
    catch (IllegalArgumentException paramCharSequence)
    {
      paramCharSequence.printStackTrace();
    }
  }
  
  public int findIndexOfValue(String paramString)
  {
    if ((paramString != null) && (mEntryValues != null))
    {
      int i = mEntryValues.length - 1;
      while (i >= 0)
      {
        if (mEntryValues[i].equals(paramString)) {
          return i;
        }
        i -= 1;
      }
    }
    return -1;
  }
  
  public CharSequence[] getEntries()
  {
    return mEntries;
  }
  
  public CharSequence getEntry()
  {
    int i = getValueIndex();
    if ((i >= 0) && (mEntries != null)) {
      return mEntries[i];
    }
    return null;
  }
  
  public CharSequence[] getEntryValues()
  {
    return mEntryValues;
  }
  
  public String getValue()
  {
    return mValue;
  }
  
  public boolean iSAnimating()
  {
    if (mAnimHelper != null) {
      return mAnimHelper.iSAnimating();
    }
    return false;
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    mListView = ((ListView)paramView.findViewById(R.id.expand_listview));
    mAdapter = new PreferenceAdapter(getContext(), mEntries);
    mSummary = ((TextView)paramView.findViewById(16908304));
    mImageView = ((ImageView)paramView.findViewById(R.id.pull_icon));
    if ((mEntries != null) && (mEntries.length > 0)) {
      if (getValueIndex() == -1) {
        break label303;
      }
    }
    label303:
    for (int i = getValueIndex();; i = 0)
    {
      setSummary(mEntries[i]);
      mSummary.setText(mEntries[i]);
      mAdapter.setSelectedPosition(i);
      mAdapter.notifyDataSetChanged();
      if (mIsExtand) {
        mSummary.setVisibility(4);
      }
      for (;;)
      {
        mListView.setAdapter(mAdapter);
        mAdapter.setTargetList(mListView);
        mListView.setChoiceMode(1);
        mListView.setOnItemClickListener(new ExpandableListPreference.1(this));
        mLinearLayout = ((LinearLayout)paramView.findViewById(R.id.container));
        mLinearLayout.measure(0, 0);
        paramView = (LinearLayout.LayoutParams)mLinearLayout.getLayoutParams();
        if ((mEntries != null) && (mEntries.length > 0)) {
          height = (mLinearLayout.getMeasuredHeight() * mEntries.length);
        }
        if (!mIsExtand) {
          break;
        }
        mLinearLayout.setVisibility(0);
        mLinearLayout.setFocusable(false);
        return;
        mSummary.setVisibility(0);
      }
      mLinearLayout.setVisibility(8);
      return;
    }
  }
  
  protected void onClick()
  {
    if ((iSAnimating()) || (mIsWaitingToChange)) {
      return;
    }
    if (mIsExtand)
    {
      mAnimHelper.init(mLinearLayout, mImageView, mSummary, 1, ANIMATION_DURATION);
      mAnimHelper.animateHeightPrt();
      mIsExtand = false;
      return;
    }
    mAnimHelper.init(mLinearLayout, mImageView, mSummary, 0, ANIMATION_DURATION);
    mAnimHelper.animateHeightPrt();
    mIsExtand = true;
  }
  
  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    return paramTypedArray.getString(paramInt);
  }
  
  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    if (paramBoolean) {}
    for (paramObject = getPersistedString(mValue);; paramObject = (String)paramObject)
    {
      setValue((String)paramObject);
      return;
    }
  }
  
  public void performCollapseAnim()
  {
    if (mIsExtand)
    {
      mAnimHelper.init(mLinearLayout, mImageView, mSummary, 1, ANIMATION_DURATION);
      mAnimHelper.animateHeightPrt();
      mIsExtand = false;
    }
  }
  
  public void setEntries(CharSequence[] paramArrayOfCharSequence)
  {
    mEntries = paramArrayOfCharSequence;
  }
  
  public void setEntryValues(CharSequence[] paramArrayOfCharSequence)
  {
    mEntryValues = paramArrayOfCharSequence;
  }
  
  public void setValue(String paramString)
  {
    if (!TextUtils.equals(mValue, paramString)) {}
    for (int i = 1;; i = 0)
    {
      if ((i != 0) || (!mValueSet))
      {
        mValue = paramString;
        mValueSet = true;
        persistString(paramString);
        if (i != 0) {
          notifyChanged();
        }
      }
      return;
    }
  }
  
  @SuppressLint({"NewApi"})
  public class AnimHelper
  {
    public static final int COLLAPSE = 1;
    public static final int EXPAND = 0;
    private float mEndAlpha;
    private int mEndBottomMargin;
    private int mEndHeight;
    private boolean mIsAnimating = false;
    private LinearLayout.LayoutParams mLayoutParams;
    private int mMarginTop = 0;
    private long mMillisTime;
    private View mRonateView;
    private float mStartAlpha;
    private int mStartBottomMargin;
    private View mSummaryView;
    private View mTarget;
    private int mType;
    
    public AnimHelper() {}
    
    private Interpolator getInterpolator()
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
      }
      return new AnimInterpolator(null);
    }
    
    public void animateHeightPrt()
    {
      if (mType == 0)
      {
        mStartBottomMargin = (-mEndHeight + mMarginTop);
        mEndBottomMargin = 0;
        mStartAlpha = 0.0F;
        mEndAlpha = 1.0F;
      }
      for (;;)
      {
        AnimatorSet localAnimatorSet = new AnimatorSet();
        ValueAnimator localValueAnimator1 = ValueAnimator.ofFloat(new float[] { mEndAlpha, mStartAlpha });
        localValueAnimator1.setDuration((int)(mMillisTime * 0.4D));
        if (mType == 1) {
          localValueAnimator1.setStartDelay((int)(mMillisTime * 0.6D));
        }
        localValueAnimator1.setInterpolator(getInterpolator());
        localValueAnimator1.addUpdateListener(new ExpandableListPreference.AnimHelper.1(this));
        localValueAnimator1.addListener(new ExpandableListPreference.AnimHelper.2(this));
        ValueAnimator localValueAnimator2 = ValueAnimator.ofFloat(new float[] { mStartAlpha, mEndAlpha });
        localValueAnimator2.setDuration((int)(mMillisTime * 0.5D));
        if (mType == 0) {
          localValueAnimator2.setStartDelay((int)(mMillisTime * 0.4D));
        }
        localValueAnimator2.setInterpolator(getInterpolator());
        localValueAnimator2.addUpdateListener(new ExpandableListPreference.AnimHelper.3(this));
        ValueAnimator localValueAnimator3 = ValueAnimator.ofInt(new int[] { mStartBottomMargin, mEndBottomMargin });
        localValueAnimator3.setInterpolator(getInterpolator());
        localValueAnimator3.addUpdateListener(new ExpandableListPreference.AnimHelper.4(this));
        localValueAnimator3.addListener(new ExpandableListPreference.AnimHelper.5(this, localValueAnimator3));
        localValueAnimator3.setDuration(mMillisTime);
        localAnimatorSet.playTogether(new Animator[] { localValueAnimator3, localValueAnimator2, localValueAnimator1 });
        localAnimatorSet.start();
        return;
        if (mType == 1)
        {
          mStartBottomMargin = 0;
          mEndBottomMargin = (-mEndHeight + mMarginTop);
          mStartAlpha = 1.0F;
          mEndAlpha = 0.0F;
        }
      }
    }
    
    public boolean iSAnimating()
    {
      return mIsAnimating;
    }
    
    public void init(View paramView1, View paramView2, View paramView3, int paramInt, long paramLong)
    {
      float f2 = 1.0F;
      mTarget = paramView1;
      mSummaryView = paramView3;
      mRonateView = paramView2;
      mType = paramInt;
      mMillisTime = paramLong;
      mLayoutParams = ((LinearLayout.LayoutParams)mTarget.getLayoutParams());
      mEndHeight = mLayoutParams.height;
      if (mType == 0)
      {
        mLayoutParams.bottomMargin = (-mEndHeight);
        mTarget.setVisibility(0);
        paramView1 = mTarget;
        if (mType != 0) {
          break label145;
        }
        f1 = 0.0F;
        label97:
        paramView1.setAlpha(f1);
        mSummaryView.setVisibility(0);
        paramView1 = mSummaryView;
        if (mType != 0) {
          break label151;
        }
      }
      label145:
      label151:
      for (float f1 = f2;; f1 = 0.0F)
      {
        paramView1.setAlpha(f1);
        return;
        mLayoutParams.bottomMargin = 0;
        break;
        f1 = 1.0F;
        break label97;
      }
    }
    
    public void setMarginTop(int paramInt)
    {
      mMarginTop = paramInt;
    }
    
    class AnimInterpolator
      implements Interpolator
    {
      private AnimInterpolator() {}
      
      public float getInterpolation(float paramFloat)
      {
        return (float)(1.0D - Math.pow(1.0F - paramFloat, 3.0D));
      }
    }
  }
  
  class PreferenceAdapter
    extends BaseAdapter
  {
    private Context mContext;
    private CharSequence[] mData;
    private ListView mList;
    private int mSelectedPosition = -1;
    
    public PreferenceAdapter(Context paramContext, CharSequence[] paramArrayOfCharSequence)
    {
      mContext = paramContext;
      mData = paramArrayOfCharSequence;
    }
    
    public int getCount()
    {
      return mData.length;
    }
    
    public Object getItem(int paramInt)
    {
      return mData[paramInt];
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if (paramView == null)
      {
        paramView = new Holder(null);
        paramViewGroup = ((LayoutInflater)mContext.getSystemService("layout_inflater")).inflate(R.layout.mc_expandable_preference_list_item, null);
        checkedTextView = ((CheckedTextView)paramViewGroup);
        paramViewGroup.setLayoutParams(new AbsListView.LayoutParams(-1, mContext.getResources().getDimensionPixelOffset(R.dimen.mc_expandable_preference_list_item_height)));
        paramViewGroup.setTag(paramView);
      }
      for (;;)
      {
        checkedTextView.setText(mData[paramInt]);
        if (paramInt == mSelectedPosition) {
          mList.setItemChecked(paramInt, true);
        }
        return paramViewGroup;
        Holder localHolder = (Holder)paramView.getTag();
        paramViewGroup = paramView;
        paramView = localHolder;
      }
    }
    
    public void setSelectedPosition(int paramInt)
    {
      mSelectedPosition = paramInt;
    }
    
    public void setTargetList(ListView paramListView)
    {
      mList = paramListView;
    }
    
    class Holder
    {
      public CheckedTextView checkedTextView;
      
      private Holder() {}
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.preference.ExpandableListPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */