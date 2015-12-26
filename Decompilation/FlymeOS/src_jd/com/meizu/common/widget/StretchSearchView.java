package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.R.attr;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.styleable;

public class StretchSearchView
  extends RelativeLayout
{
  private static final int SIZE_DEFAULT = 0;
  public static final int STATE_ERROR = -1;
  public static final int STATE_READY = 0;
  public static final int STATE_SHORTENED = 4;
  public static final int STATE_SHORTENING = 3;
  public static final int STATE_STRETCHED = 2;
  public static final int STATE_STRETCHING = 1;
  public static final String TAG = "StretchSearchView";
  public static final int TYPE_CUSTOM = 0;
  public static final int TYPE_MIDDLE_TO_LEFT = 3;
  public static final int TYPE_MIDDLE_TO_LEFT_TEXTVIEW = 4;
  public static final int TYPE_RIGHT_TO_LEFT = 1;
  public static final int TYPE_RIGHT_TO_LEFT_TEXTVIEW = 2;
  private boolean mAlignRightWhenAnim = false;
  private int mAnimationState = -1;
  private TextView mButton;
  private int mButtonColor = -1;
  private Context mContext;
  private float mCustomAnimValueFrom = 0.0F;
  private float mCustomAnimValueTo = 1.0F;
  private boolean mHasBtn = false;
  private boolean mHasVoiceIcon = false;
  private ImageView mImgSearch;
  private ImageView mImgSearchClear;
  private float mInputClearAlphaFrom = 0.0F;
  private float mInputClearAlphaTo = 1.0F;
  private SearchEditText mInputText;
  private float mInputTextAlphaFrom = 0.0F;
  private float mInputTextAlphaTo = 1.0F;
  private int mLayoutMarginLeftAdjust = 0;
  private int mLayoutMarginRightAdjust = 0;
  private int mLayoutPaddingLeft = 0;
  private int mLayoutPaddingRight = 0;
  private RelativeLayout mMainLayout;
  private boolean mPlayInputTextAlhpaAnim = true;
  private boolean mPlayMoveXAnim = true;
  private boolean mPlaySearchImgScaleAnim = true;
  private boolean mPlaySearchclearAlphaAnim = true;
  private boolean mPlayStretchOnPreDraw = false;
  private boolean mPlayStretchWidthAnim = true;
  private float mScaleFrom = 1.0F;
  private float mScaleTo = 0.9F;
  private RelativeLayout mSearchLayout;
  private float mSearchLayoutInitAlpha = 0.0F;
  private String mSearchTextHint = "搜索";
  private View mSearchView;
  private ShortenAnimationListener mShortenAnimationListener = null;
  private int mShortenDuration = mStretchDuration;
  private StretchAnimationListener mStretchAnimationListener = null;
  private int mStretchDuration = 320;
  private int mStretchTpye = 1;
  private int mStretchWidthFrom = 0;
  private int mStretchWidthTo = 0;
  private int mStretchXfrom = 0;
  private int mStretchXto = 0;
  private String mTextViewContent;
  private boolean mUseSysInterpolater = false;
  private ImageView mVoiceIcon;
  
  public StretchSearchView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public StretchSearchView(Context paramContext, int paramInt)
  {
    this(paramContext);
    mStretchTpye = paramInt;
  }
  
  public StretchSearchView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_StretchSearchViewStyle);
  }
  
  public StretchSearchView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    paramContext = mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.StretchSearchView, paramInt, 0);
    mStretchTpye = paramContext.getInteger(R.styleable.StretchSearchView_mcStretchTpye, mStretchTpye);
    mHasVoiceIcon = paramContext.getBoolean(R.styleable.StretchSearchView_mcHasVoiceIcon, mHasVoiceIcon);
    mPlayStretchOnPreDraw = paramContext.getBoolean(R.styleable.StretchSearchView_mcPlayStretchOnPreDraw, mPlayStretchOnPreDraw);
    mAlignRightWhenAnim = paramContext.getBoolean(R.styleable.StretchSearchView_mcAlignRightWhenAnim, mAlignRightWhenAnim);
    mUseSysInterpolater = paramContext.getBoolean(R.styleable.StretchSearchView_mcUseSysInterpolater, mUseSysInterpolater);
    mStretchDuration = paramContext.getInteger(R.styleable.StretchSearchView_mcStretchDuration, mStretchDuration);
    mShortenDuration = paramContext.getInteger(R.styleable.StretchSearchView_mcShortenDuration, mShortenDuration);
    mSearchTextHint = paramContext.getString(R.styleable.StretchSearchView_mcSearchTextHint);
    mTextViewContent = paramContext.getString(R.styleable.StretchSearchView_mcTextViewContent);
    mSearchLayoutInitAlpha = paramContext.getFloat(R.styleable.StretchSearchView_mcSearchLayoutInitAlpha, mSearchLayoutInitAlpha);
    mButtonColor = paramContext.getColor(R.styleable.StretchSearchView_mcTextViewColor, -1);
    mLayoutMarginLeftAdjust = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcLayoutMarginLeftAdjust, mLayoutMarginLeftAdjust));
    mLayoutMarginRightAdjust = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcLayoutMarginRightAdjust, mLayoutMarginRightAdjust));
    mLayoutPaddingLeft = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcLayoutPaddingLeft, mLayoutPaddingLeft));
    mLayoutPaddingRight = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcLayoutPaddingRight, mLayoutPaddingRight));
    mStretchWidthFrom = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcStretchWidthFrom, 0.0F));
    mStretchWidthTo = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcStretchWidthTo, 0.0F));
    mStretchXfrom = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcStretchXfrom, 0.0F));
    mStretchXto = ((int)paramContext.getDimension(R.styleable.StretchSearchView_mcStretchXto, 0.0F));
    paramContext.recycle();
    initAll();
  }
  
  private void addGlobalPreDrawListener()
  {
    ViewTreeObserver localViewTreeObserver = mMainLayout.getViewTreeObserver();
    localViewTreeObserver.addOnGlobalLayoutListener(new StretchSearchView.5(this, localViewTreeObserver));
  }
  
  private void addPreDrawListener()
  {
    mMainLayout.getViewTreeObserver().addOnPreDrawListener(new StretchSearchView.6(this));
  }
  
  private Interpolator getMovingInterpolater()
  {
    Object localObject = new DecelerateInterpolator();
    if (Build.VERSION.SDK_INT >= 21) {
      localObject = new PathInterpolator(0.3333F, 0.0F, 0.1F, 1.0F);
    }
    return (Interpolator)localObject;
  }
  
  private Interpolator getScaleInterpolater()
  {
    Object localObject = new DecelerateInterpolator();
    if (Build.VERSION.SDK_INT >= 21) {
      localObject = new PathInterpolator(0.3333F, 0.0F, 0.0F, 1.0F);
    }
    return (Interpolator)localObject;
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    new DisplayMetrics();
    paramContext = paramContext.getResources().getDisplayMetrics();
    int i = widthPixels;
    int j = heightPixels;
    return i;
  }
  
  private Interpolator getStretchInterpolater()
  {
    Object localObject = new DecelerateInterpolator();
    if (Build.VERSION.SDK_INT >= 21) {
      localObject = new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
    }
    return (Interpolator)localObject;
  }
  
  private void initMeasure()
  {
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    int j = View.MeasureSpec.makeMeasureSpec(0, 0);
    mMainLayout.measure(i, j);
    mSearchLayout.measure(i, j);
    mImgSearch.measure(i, j);
    mInputText.measure(i, j);
  }
  
  private void shortenAmin()
  {
    if (mAnimationState != 2) {
      return;
    }
    mAnimationState = 3;
    beforeShortenViewState();
    AnimatorSet localAnimatorSet1 = new AnimatorSet();
    localAnimatorSet1.setDuration(mShortenDuration);
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(mSearchLayout, "x", new float[] { mStretchXto, mStretchXfrom });
    localObjectAnimator1.setDuration(mShortenDuration);
    Object localObject = ObjectAnimator.ofFloat(mSearchLayout, "width", new float[] { mStretchWidthTo, mStretchWidthFrom });
    ((ObjectAnimator)localObject).setDuration(mShortenDuration);
    ((ObjectAnimator)localObject).addUpdateListener(new StretchSearchView.10(this));
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(mSearchLayout, "StretchSearchViewAnimValue", new float[] { mCustomAnimValueTo, mCustomAnimValueFrom });
    localObjectAnimator2.setDuration(mShortenDuration);
    localObjectAnimator2.addUpdateListener(new StretchSearchView.11(this));
    localAnimatorSet1.addListener(new StretchSearchView.12(this));
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(mInputText, "alpha", new float[] { mInputTextAlphaTo, mInputTextAlphaFrom });
    localObjectAnimator3.setDuration(mShortenDuration * 4 / 5);
    ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(mImgSearchClear, "alpha", new float[] { mInputClearAlphaTo, mInputClearAlphaFrom });
    localObjectAnimator4.setDuration(mShortenDuration * 4 / 5);
    ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(mImgSearch, "scaleX", new float[] { mScaleTo, mScaleFrom });
    ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(mImgSearch, "scaleY", new float[] { mScaleTo, mScaleFrom });
    if (mUseSysInterpolater)
    {
      localObjectAnimator1.setInterpolator(getMovingInterpolater());
      ((ObjectAnimator)localObject).setInterpolator(getStretchInterpolater());
      localObjectAnimator5.setInterpolator(getScaleInterpolater());
      localObjectAnimator6.setInterpolator(getScaleInterpolater());
    }
    AnimatorSet localAnimatorSet2 = new AnimatorSet();
    localAnimatorSet2.setDuration(mShortenDuration * 4 / 5);
    if (mPlaySearchclearAlphaAnim) {
      localAnimatorSet2.play(localObjectAnimator4);
    }
    if (mPlayInputTextAlhpaAnim) {
      localAnimatorSet2.play(localObjectAnimator3);
    }
    localAnimatorSet2.start();
    localAnimatorSet1.play(localObjectAnimator2);
    if (mPlayMoveXAnim) {
      localAnimatorSet1.play(localObjectAnimator2).with(localObjectAnimator1);
    }
    if (mPlayStretchWidthAnim) {
      localAnimatorSet1.play(localObjectAnimator2).with((Animator)localObject);
    }
    if (mPlaySearchImgScaleAnim) {
      localAnimatorSet1.play(localObjectAnimator2).with(localObjectAnimator5).with(localObjectAnimator6);
    }
    if (mHasBtn)
    {
      localObjectAnimator1 = ObjectAnimator.ofFloat(mButton, "alpha", new float[] { 1.0F, 0.0F });
      localObjectAnimator1.setDuration(mShortenDuration * 2 / 3);
      localObject = new AnimatorSet();
      ((AnimatorSet)localObject).setDuration(mShortenDuration * 2 / 3);
      ((AnimatorSet)localObject).play(localObjectAnimator1);
      ((AnimatorSet)localObject).start();
    }
    localAnimatorSet1.start();
  }
  
  private void stretchAmin()
  {
    if ((mAnimationState != 0) && (mAnimationState != 4)) {
      return;
    }
    mAnimationState = 1;
    beforeStretchViewState();
    AnimatorSet localAnimatorSet = new AnimatorSet();
    localAnimatorSet.setDuration(mStretchDuration);
    ObjectAnimator localObjectAnimator1 = ObjectAnimator.ofFloat(mSearchLayout, "x", new float[] { mStretchXto });
    localObjectAnimator1.setDuration(mStretchDuration);
    Object localObject = ObjectAnimator.ofFloat(mSearchLayout, "width", new float[] { mStretchWidthTo });
    ((ObjectAnimator)localObject).setDuration(mStretchDuration);
    ((ObjectAnimator)localObject).addUpdateListener(new StretchSearchView.7(this));
    ObjectAnimator localObjectAnimator2 = ObjectAnimator.ofFloat(mSearchLayout, "StretchSearchViewAnimValue", new float[] { mCustomAnimValueFrom, mCustomAnimValueTo });
    localObjectAnimator2.setDuration(mStretchDuration);
    localObjectAnimator2.addUpdateListener(new StretchSearchView.8(this));
    localAnimatorSet.addListener(new StretchSearchView.9(this));
    ObjectAnimator localObjectAnimator3 = ObjectAnimator.ofFloat(mImgSearchClear, "alpha", new float[] { mInputClearAlphaFrom, mInputClearAlphaTo });
    localObjectAnimator3.setDuration(mStretchDuration);
    ObjectAnimator localObjectAnimator4 = ObjectAnimator.ofFloat(mInputText, "alpha", new float[] { mInputTextAlphaFrom, mInputTextAlphaTo });
    localObjectAnimator4.setDuration(mStretchDuration);
    ObjectAnimator localObjectAnimator5 = ObjectAnimator.ofFloat(mImgSearch, "scaleX", new float[] { mScaleFrom, mScaleTo });
    ObjectAnimator localObjectAnimator6 = ObjectAnimator.ofFloat(mImgSearch, "scaleY", new float[] { mScaleFrom, mScaleTo });
    localObjectAnimator5.setDuration(mStretchDuration);
    localObjectAnimator6.setDuration(mStretchDuration);
    if (mUseSysInterpolater)
    {
      localObjectAnimator1.setInterpolator(getMovingInterpolater());
      ((ObjectAnimator)localObject).setInterpolator(getStretchInterpolater());
      localObjectAnimator5.setInterpolator(getScaleInterpolater());
      localObjectAnimator6.setInterpolator(getScaleInterpolater());
    }
    localAnimatorSet.play(localObjectAnimator2);
    if (mPlayMoveXAnim) {
      localAnimatorSet.play(localObjectAnimator2).with(localObjectAnimator1);
    }
    if (mPlaySearchclearAlphaAnim) {
      localAnimatorSet.play(localObjectAnimator2).with(localObjectAnimator3);
    }
    if (mPlayInputTextAlhpaAnim) {
      localAnimatorSet.play(localObjectAnimator2).with(localObjectAnimator4);
    }
    if (mPlayStretchWidthAnim) {
      localAnimatorSet.play(localObjectAnimator2).with((Animator)localObject);
    }
    if (mPlaySearchImgScaleAnim) {
      localAnimatorSet.play(localObjectAnimator2).with(localObjectAnimator5).with(localObjectAnimator6);
    }
    if (mHasBtn)
    {
      localObjectAnimator1 = ObjectAnimator.ofFloat(mButton, "alpha", new float[] { 0.0F, 1.0F });
      localObjectAnimator1.setDuration(mStretchDuration * 2 / 3);
      localObject = new AnimatorSet();
      ((AnimatorSet)localObject).setDuration(mStretchDuration * 2 / 3);
      ((AnimatorSet)localObject).play(localObjectAnimator1).after(mStretchDuration / 3);
      ((AnimatorSet)localObject).start();
    }
    localAnimatorSet.start();
  }
  
  public void addEditTextChangedListener(TextWatcher paramTextWatcher)
  {
    mInputText.addTextChangedListener(paramTextWatcher);
  }
  
  protected void afterShortenViewState()
  {
    mSearchView.requestLayout();
    mInputText.setVisibility(8);
    mSearchView.setVisibility(0);
    if (mHasBtn) {
      mButton.setVisibility(4);
    }
  }
  
  protected void afterStretchViewState()
  {
    mSearchView.requestLayout();
    mInputText.showIme(true);
    if (mHasVoiceIcon) {
      mVoiceIcon.setVisibility(0);
    }
  }
  
  protected void beforeShortenViewState()
  {
    mSearchView.requestLayout();
    mInputText.showIme(false);
    if (mHasVoiceIcon) {
      mVoiceIcon.setVisibility(8);
    }
  }
  
  protected void beforeStretchViewState()
  {
    mSearchView.requestLayout();
    mSearchView.setVisibility(0);
    mInputText.setVisibility(0);
    mInputText.setText("");
    if (mHasBtn)
    {
      mButton.setVisibility(0);
      mButton.setAlpha(0.0F);
    }
  }
  
  protected void calcWidth()
  {
    mStretchWidthFrom = mSearchLayout.getMeasuredWidth();
    mStretchWidthTo = getMaxStretchWidth();
  }
  
  protected void calcX()
  {
    mStretchXfrom = ((int)mSearchLayout.getX());
    mStretchXto = getMinMoveX();
  }
  
  public int getAnimationState()
  {
    return mAnimationState;
  }
  
  public String getBtnText()
  {
    if (!mHasBtn) {
      return null;
    }
    return mButton.getText().toString();
  }
  
  public float getCustomAnimValueFrom()
  {
    return mCustomAnimValueFrom;
  }
  
  public float getCustomAnimValueTo()
  {
    return mInputClearAlphaTo;
  }
  
  public float getInputClearAlphaFrom()
  {
    return mInputTextAlphaFrom;
  }
  
  public int getInputClearAlphaTo()
  {
    return mStretchWidthTo;
  }
  
  public float getInputTextAlphaFrom()
  {
    return mInputTextAlphaFrom;
  }
  
  public float getInputTextAlphaTo()
  {
    return mInputClearAlphaTo;
  }
  
  public boolean getIsAlignRight()
  {
    return mAlignRightWhenAnim;
  }
  
  public int getLayoutMarginLeftAdjust()
  {
    return mLayoutMarginLeftAdjust;
  }
  
  public int getLayoutMarginRightAdjust()
  {
    return mLayoutMarginRightAdjust;
  }
  
  protected int getMaxStretchWidth()
  {
    int i = mMainLayout.getMeasuredWidth();
    int j = mMainLayout.getPaddingLeft();
    int k = mMainLayout.getPaddingRight();
    if (mHasBtn) {
      return i - mButton.getLayoutParams().width - j;
    }
    return i - j - k;
  }
  
  protected int getMinMoveX()
  {
    return mMainLayout.getPaddingLeft() + (int)mMainLayout.getX() + mLayoutMarginLeftAdjust;
  }
  
  public float getScaleFrom()
  {
    return mScaleFrom;
  }
  
  public float getScaleTo()
  {
    return mScaleTo;
  }
  
  public String getSearchText()
  {
    return mInputText.getText().toString();
  }
  
  public int getShortenAnimDuration()
  {
    return mShortenDuration;
  }
  
  public int getStretchAnimDuration()
  {
    return mStretchDuration;
  }
  
  public int getStretchWidthFrom()
  {
    return mStretchWidthFrom;
  }
  
  public int getStretchWidthTo()
  {
    return mStretchWidthTo;
  }
  
  public int getStretchXfrom()
  {
    return mStretchXfrom;
  }
  
  public int getStretchXto()
  {
    return mStretchXto;
  }
  
  public boolean getUseInterpolater()
  {
    return mUseSysInterpolater;
  }
  
  protected void initAll()
  {
    initStretchType();
    initView(mContext);
    initViewState();
    initListener();
  }
  
  protected void initListener()
  {
    mImgSearch.setOnClickListener(new StretchSearchView.1(this));
    mImgSearchClear.setOnClickListener(new StretchSearchView.2(this));
    mInputText.addTextChangedListener(new StretchSearchView.3(this));
    if ((4 == mStretchTpye) || (3 == mStretchTpye)) {
      mInputText.setOnClickListener(new StretchSearchView.4(this));
    }
    addPreDrawListener();
  }
  
  protected void initStretchType()
  {
    boolean bool2 = false;
    if ((2 == mStretchTpye) || (4 == mStretchTpye) || (mStretchTpye == 0))
    {
      bool1 = true;
      mHasBtn = bool1;
      if (true != mAlignRightWhenAnim) {
        break label53;
      }
    }
    label53:
    for (boolean bool1 = bool2;; bool1 = true)
    {
      mPlayMoveXAnim = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  protected void initView(Context paramContext)
  {
    mSearchView = null;
    if (mStretchTpye == 0)
    {
      mSearchView = View.inflate(paramContext, R.layout.mc_stretch_search_layout_ext, this);
      paramContext = "R.layout.mc_move_search_layout";
    }
    while (mSearchView == null)
    {
      throw new IllegalArgumentException("StretchSearchView cannot inflate " + paramContext + "!");
      if (3 == mStretchTpye)
      {
        mSearchView = View.inflate(paramContext, R.layout.mc_move_search_layout, this);
        paramContext = "R.layout.mc_move_search_layout";
      }
      else if (2 == mStretchTpye)
      {
        mSearchView = View.inflate(paramContext, R.layout.mc_stretch_search_layout_ext, this);
        paramContext = "R.layout.mc_stretch_search_layout_ext";
      }
      else
      {
        mSearchView = View.inflate(paramContext, R.layout.mc_stretch_search_layout, this);
        paramContext = "R.layout.mc_stretch_search_layout";
      }
    }
    mMainLayout = ((RelativeLayout)mSearchView.findViewById(R.id.mc_stretch_search_layout));
    mSearchLayout = ((RelativeLayout)mSearchView.findViewById(R.id.mc_search_layout));
    mVoiceIcon = ((ImageView)mSearchView.findViewById(R.id.mc_voice_icon));
    mImgSearch = ((ImageView)mSearchView.findViewById(R.id.mc_search_icon));
    mImgSearchClear = ((ImageView)mSearchView.findViewById(R.id.mc_search_icon_input_clear));
    mInputText = ((SearchEditText)mSearchView.findViewById(R.id.mc_search_edit));
    mInputText.setAlpha(mSearchLayoutInitAlpha);
    mInputText.setHint(mSearchTextHint);
    if (mHasBtn)
    {
      mButton = ((TextView)mSearchView.findViewById(R.id.mc_search_textview));
      mButton.setTextColor(mButtonColor);
      mButton.setText(mTextViewContent);
      mButton.setAlpha(0.0F);
    }
    paramContext = new RelativeLayout.LayoutParams(-2, -1);
    rightMargin = mLayoutMarginRightAdjust;
    mSearchLayout.setLayoutParams(paramContext);
    mMainLayout.setPadding(mLayoutPaddingLeft, mMainLayout.getTop(), mLayoutPaddingRight, mMainLayout.getBottom());
    mMainLayout.requestLayout();
  }
  
  protected void initViewState()
  {
    if ((3 == mStretchTpye) || (4 == mStretchTpye))
    {
      mSearchView.setVisibility(0);
      mInputText.setVisibility(0);
      mInputText.setBackground(null);
      mInputTextAlphaFrom = 0.8F;
    }
    for (;;)
    {
      mAnimationState = 0;
      return;
      mSearchView.setVisibility(0);
      mInputText.setVisibility(8);
    }
  }
  
  public boolean isAutoPlayStretchOnPreDraw()
  {
    return mPlayStretchOnPreDraw;
  }
  
  public boolean isHaveVoiceIcon()
  {
    return mHasVoiceIcon;
  }
  
  public boolean isPlayInputTextAlhpaAnim()
  {
    return mPlayInputTextAlhpaAnim;
  }
  
  public boolean isPlayMoveXAnim()
  {
    return mPlayMoveXAnim;
  }
  
  public boolean isPlaySearchImgScaleAnim()
  {
    return mPlaySearchImgScaleAnim;
  }
  
  public boolean isPlaySearchclearAlphaAnim()
  {
    return mPlaySearchclearAlphaAnim;
  }
  
  public boolean isPlayStretchWidthAnim()
  {
    return mPlayStretchWidthAnim;
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }
  
  protected void onInitLayout()
  {
    if (mStretchTpye != 0)
    {
      calcX();
      calcWidth();
    }
    if ((3 == mStretchTpye) || (4 == mStretchTpye)) {
      recalcFromMiddle();
    }
    Log.i("StretchSearchView", "Stretch width from " + mStretchWidthFrom + " to " + mStretchWidthTo + ", move X from " + mStretchXfrom + " to " + mStretchXto + " !");
  }
  
  protected void recalcFromMiddle()
  {
    int i = mStretchXfrom;
    i = mImgSearch.getMeasuredWidth() + (int)mInputText.getPaint().measureText(mInputText.getHint().toString()) / 2;
    mStretchWidthFrom = (getMaxStretchWidth() / 2 + i);
    mStretchXfrom = (mMainLayout.getMeasuredWidth() / 2 - i);
    mStretchXto = getMinMoveX();
    mSearchLayout.setX(mStretchXfrom);
    Log.i("StretchSearchView", "Reset stretch layout, search icon location X to layout right:  " + mStretchWidthFrom + ",search icon location X: " + mStretchXfrom + " !");
    ImageView localImageView = (ImageView)mMainLayout.findViewById(R.id.mc_stretch_search_layout_1);
    if (localImageView != null) {
      localImageView.setX(mStretchXto - mImgSearch.getPaddingLeft());
    }
  }
  
  public void setAutoPlayStretchOnPreDraw(boolean paramBoolean)
  {
    mPlayStretchOnPreDraw = paramBoolean;
  }
  
  public void setBtnListener(View.OnClickListener paramOnClickListener)
  {
    if (mButton != null) {
      mButton.setOnClickListener(paramOnClickListener);
    }
  }
  
  public void setBtnText(String paramString)
  {
    if (!mHasBtn) {
      return;
    }
    mButton.setText(paramString);
  }
  
  public void setCustomAnimValueFrom(float paramFloat)
  {
    mCustomAnimValueFrom = paramFloat;
  }
  
  public void setCustomAnimValueTo(float paramFloat)
  {
    mCustomAnimValueTo = paramFloat;
  }
  
  public void setEditTextListener(View.OnClickListener paramOnClickListener)
  {
    mInputText.setOnClickListener(paramOnClickListener);
  }
  
  public void setHaveVoiceIcon(boolean paramBoolean)
  {
    mHasVoiceIcon = paramBoolean;
  }
  
  public void setInputClearAlphaFrom(float paramFloat)
  {
    mInputTextAlphaFrom = paramFloat;
  }
  
  public void setInputClearAlphaTo(float paramFloat)
  {
    mInputTextAlphaTo = paramFloat;
  }
  
  public void setInputTextAlphaFrom(float paramFloat)
  {
    mInputTextAlphaFrom = paramFloat;
  }
  
  public void setInputTextAlphaTo(float paramFloat)
  {
    mInputTextAlphaTo = paramFloat;
  }
  
  public void setIsAlignRigh(boolean paramBoolean)
  {
    mAlignRightWhenAnim = paramBoolean;
  }
  
  public void setLayoutMarginLeftAdjust(int paramInt)
  {
    mLayoutMarginLeftAdjust = paramInt;
  }
  
  public void setLayoutMarginRightAdjust(int paramInt)
  {
    mLayoutMarginRightAdjust = paramInt;
  }
  
  public void setOnShortenAnimationListener(ShortenAnimationListener paramShortenAnimationListener)
  {
    mShortenAnimationListener = paramShortenAnimationListener;
  }
  
  public void setOnStretchAnimationListener(StretchAnimationListener paramStretchAnimationListener)
  {
    mStretchAnimationListener = paramStretchAnimationListener;
  }
  
  public void setPlayInputTextAlhpaAnim(boolean paramBoolean)
  {
    mPlayInputTextAlhpaAnim = paramBoolean;
  }
  
  public void setPlayMoveXAnim(boolean paramBoolean)
  {
    mPlayMoveXAnim = paramBoolean;
  }
  
  public void setPlaySearchImgScaleAnim(boolean paramBoolean)
  {
    mPlaySearchImgScaleAnim = paramBoolean;
  }
  
  public void setPlaySearchclearAlphaAnim(boolean paramBoolean)
  {
    mPlaySearchclearAlphaAnim = paramBoolean;
  }
  
  public void setPlayStretchWidthAnim(boolean paramBoolean)
  {
    mPlayStretchWidthAnim = paramBoolean;
  }
  
  public void setScaleFrom(float paramFloat)
  {
    mScaleFrom = paramFloat;
  }
  
  public void setScaleTo(float paramFloat)
  {
    mScaleTo = paramFloat;
  }
  
  public void setSearchText(String paramString)
  {
    mInputText.setText(paramString);
  }
  
  public void setShortenAnimDuration(int paramInt)
  {
    mShortenDuration = paramInt;
  }
  
  public void setStretchAnimDuration(int paramInt)
  {
    mStretchDuration = paramInt;
  }
  
  public void setStretchWidthFrom(int paramInt)
  {
    mStretchWidthFrom = paramInt;
  }
  
  public void setStretchWidthTo(int paramInt)
  {
    mStretchWidthTo = paramInt;
  }
  
  public void setStretchXfrom(int paramInt)
  {
    mStretchXfrom = paramInt;
  }
  
  public void setStretchXto(int paramInt)
  {
    mStretchXto = paramInt;
  }
  
  public void setUseInterpolater(boolean paramBoolean)
  {
    mUseSysInterpolater = paramBoolean;
  }
  
  public void setVoiceIconListener(View.OnClickListener paramOnClickListener)
  {
    if (mHasVoiceIcon) {
      mVoiceIcon.setOnClickListener(paramOnClickListener);
    }
  }
  
  public void showIme(boolean paramBoolean)
  {
    mInputText.showIme(paramBoolean);
  }
  
  public void startShorten()
  {
    shortenAmin();
  }
  
  public void startStretch()
  {
    stretchAmin();
  }
  
  public void startStretchOnPreDraw()
  {
    stretchAmin();
  }
  
  public static abstract interface ShortenAnimationListener
  {
    public abstract void onShortenAnimationEnd(View paramView);
    
    public abstract void onShortenAnimationStart(View paramView);
    
    public abstract void onShortenAnimationUpdate(View paramView, float paramFloat);
  }
  
  public static abstract interface StretchAnimationListener
  {
    public abstract void onStetchAnimationEnd(View paramView);
    
    public abstract void onStetchAnimationStart(View paramView);
    
    public abstract void onStetchAnimationUpdate(View paramView, float paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.StretchSearchView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */