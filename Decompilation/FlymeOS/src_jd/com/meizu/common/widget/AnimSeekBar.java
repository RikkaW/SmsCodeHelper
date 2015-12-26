package com.meizu.common.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.meizu.common.R.attr;
import com.meizu.common.R.styleable;

public class AnimSeekBar
  extends SkposSeekBar
  implements GestureDetector.OnGestureListener
{
  private static final int DEFAULT_ANIMSEEKBAE_HEIGHT = 65;
  private static final int DEFAULT_DISTANCE_BEW = 40;
  private static final int DEFAULT_EXPANDED_PIN_RADIUS_DP = 15;
  private static final int DEFAULT_LAGRECIRCLE_MOVEUP_VALUE = 24;
  private static final int DEFAULT_TEXT_COLOR = -1;
  private static final int DEFAULT_TEXT_SIZE = 15;
  private static final float DEFAULT_THUMB_RADIUS_DP = 0.0F;
  private static final boolean Debug = false;
  private static final String TAG = "AnimSeekBar";
  private int mAinmSeekBarHeight;
  private Rect mBounds = new Rect();
  private boolean mCheckRadisChanged = false;
  private Drawable mCircleAnimDrawble;
  private int mCircleRadius;
  private int mDefaultHeight;
  private int mDefaultWidth = 500;
  private int mDistanceBwCircle;
  private ValueAnimator mFadeAnim;
  private Interpolator mFadeInterpolator;
  private int mFadeValue = 0;
  private GestureDetector mGesture = null;
  private boolean mIsLongOrScroll = false;
  private boolean mIsStartAnim = false;
  private boolean mIsTapPressed = false;
  private ValueAnimator mMoveAnim;
  private Interpolator mMoveDownInterpolator;
  private Interpolator mMoveUpInterpolator;
  private int mMoveUpValue;
  private int mMoveUpValueDp;
  private float mPinRadisMax = 0.0F;
  private float mPinRadisMin = 0.0F;
  private float mPinRadiusPx = 0.0F;
  private Drawable mProgressDrawble;
  private Resources mRes;
  private Interpolator mScaleInterpolator;
  private ValueAnimator mScaleanim;
  private int mSdkApi;
  private String mTextNumber;
  private int mTextNumberColor;
  private int mTextNumberSize;
  private Paint mTextPaint;
  private float mThumbHeight;
  private int mThumbRadis;
  private float mX;
  private float mY;
  
  public AnimSeekBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AnimSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.MeizuCommon_AnimSeekBarStyle);
  }
  
  public AnimSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    seekBarAnimationInit(paramContext, paramAttributeSet, paramInt);
  }
  
  private void onActionUp(float paramFloat1, float paramFloat2)
  {
    if (mIsStartAnim) {
      mIsStartAnim = false;
    }
    releasePin();
    mIsLongOrScroll = false;
  }
  
  private void pressPin()
  {
    mScaleanim = ValueAnimator.ofFloat(new float[] { mThumbRadis, mThumbRadis / 2 });
    mScaleanim.addUpdateListener(new AnimSeekBar.1(this));
    mScaleanim.setInterpolator(mScaleInterpolator);
    mScaleanim.setDuration(166L);
    mScaleanim.start();
    mFadeAnim = ValueAnimator.ofInt(new int[] { 0, 255 });
    mFadeAnim.addUpdateListener(new AnimSeekBar.2(this));
    mFadeAnim.setInterpolator(mFadeInterpolator);
    mFadeAnim.setDuration(166L);
    mFadeAnim.start();
    mMoveAnim = ValueAnimator.ofInt(new int[] { 0, mMoveUpValueDp });
    mMoveAnim.addUpdateListener(new AnimSeekBar.3(this));
    mMoveAnim.setDuration(166L);
    mMoveAnim.setInterpolator(mMoveUpInterpolator);
    mMoveAnim.start();
  }
  
  private void releasePin()
  {
    mScaleanim = ValueAnimator.ofFloat(new float[] { mThumbRadis / 2, mThumbRadis });
    mScaleanim.addUpdateListener(new AnimSeekBar.4(this));
    mScaleanim.setInterpolator(mScaleInterpolator);
    mScaleanim.setDuration(166L);
    mScaleanim.start();
    mFadeAnim = ValueAnimator.ofInt(new int[] { 255, 0 });
    mFadeAnim.addUpdateListener(new AnimSeekBar.5(this));
    mFadeAnim.setInterpolator(mFadeInterpolator);
    mFadeAnim.setDuration(166L);
    mFadeAnim.start();
    mMoveAnim = ValueAnimator.ofInt(new int[] { mMoveUpValueDp, 0 });
    mMoveAnim.addUpdateListener(new AnimSeekBar.6(this));
    mMoveAnim.setDuration(166L);
    mMoveAnim.setInterpolator(mMoveDownInterpolator);
    mMoveAnim.start();
  }
  
  private void seekBarAnimationInit(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    mRes = paramContext.getResources();
    mGesture = new GestureDetector(paramContext, this);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AnimSeekBar, paramInt, 0);
    mCircleAnimDrawble = paramContext.getDrawable(R.styleable.AnimSeekBar_mcLargeCircleDrawble);
    mCircleRadius = ((int)paramContext.getDimension(R.styleable.AnimSeekBar_mcLargeCircleRadis, 15.0F));
    mTextNumberColor = paramContext.getColor(R.styleable.AnimSeekBar_mcTextNumberColor, -1);
    mDistanceBwCircle = ((int)paramContext.getDimension(R.styleable.AnimSeekBar_mcDistanceToCircle, 40.0F));
    mTextNumberSize = ((int)paramContext.getDimension(R.styleable.AnimSeekBar_mcTextNumberSize, 15.0F));
    mMoveUpValueDp = ((int)TypedValue.applyDimension(1, 24.0F, mRes.getDisplayMetrics()));
    mDefaultHeight = ((int)TypedValue.applyDimension(1, 65.0F, mRes.getDisplayMetrics()));
    mTextPaint = new Paint();
    mTextPaint.setColor(mTextNumberColor);
    mTextPaint.setAntiAlias(true);
    mTextPaint.setTextSize(mTextNumberSize);
    paramContext.recycle();
    mSdkApi = Build.VERSION.SDK_INT;
    if (mSdkApi >= 21)
    {
      mScaleInterpolator = new PathInterpolator(0.0F, 0.0F, 0.1F, 1.0F);
      mFadeInterpolator = new PathInterpolator(0.33F, 0.0F, 0.67F, 1.0F);
      mMoveUpInterpolator = new PathInterpolator(0.33F, 0.0F, 0.1F, 1.0F);
      mMoveDownInterpolator = new PathInterpolator(0.66F, 0.0F, 0.67F, 1.0F);
      return;
    }
    mScaleInterpolator = new AccelerateInterpolator();
    mFadeInterpolator = new AccelerateInterpolator();
    mMoveUpInterpolator = new AccelerateInterpolator();
    mMoveDownInterpolator = new AccelerateInterpolator();
  }
  
  private void setFadeValue(int paramInt)
  {
    mFadeValue = paramInt;
  }
  
  private void setMoveValue(int paramInt)
  {
    mMoveUpValue = paramInt;
  }
  
  private void setSize(float paramFloat)
  {
    mPinRadiusPx = ((int)paramFloat);
  }
  
  private void setmY(float paramFloat)
  {
    mY = paramFloat;
  }
  
  public int getDistanceToCircle()
  {
    return mDistanceBwCircle;
  }
  
  public Drawable getLargeCircleDrawble()
  {
    if (mCircleAnimDrawble != null) {
      return mCircleAnimDrawble;
    }
    return null;
  }
  
  public int getLargeCircleRadius()
  {
    return mCircleRadius;
  }
  
  public int getTextNumberColor()
  {
    return mTextNumberColor;
  }
  
  public int getTextNumberSize()
  {
    return mTextNumberSize;
  }
  
  public boolean onDown(MotionEvent paramMotionEvent)
  {
    if (getParent() != null) {
      getParent().requestDisallowInterceptTouchEvent(true);
    }
    return false;
  }
  
  /* Error */
  protected void onDraw(android.graphics.Canvas paramCanvas)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: fconst_0
    //   4: aload_0
    //   5: getfield 346	com/meizu/common/widget/AnimSeekBar:mAinmSeekBarHeight	I
    //   8: iconst_2
    //   9: idiv
    //   10: i2f
    //   11: aload_0
    //   12: getfield 348	com/meizu/common/widget/AnimSeekBar:mThumbHeight	F
    //   15: fsub
    //   16: aload_0
    //   17: invokevirtual 351	com/meizu/common/widget/AnimSeekBar:getPaddingBottom	()I
    //   20: i2f
    //   21: fsub
    //   22: invokevirtual 356	android/graphics/Canvas:translate	(FF)V
    //   25: aload_1
    //   26: invokevirtual 359	android/graphics/Canvas:save	()I
    //   29: pop
    //   30: aload_0
    //   31: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   34: ifnull +380 -> 414
    //   37: aload_0
    //   38: getfield 236	com/meizu/common/widget/AnimSeekBar:mCircleAnimDrawble	Landroid/graphics/drawable/Drawable;
    //   41: ifnull +373 -> 414
    //   44: aload_0
    //   45: aload_0
    //   46: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   49: invokevirtual 368	android/graphics/drawable/Drawable:getBounds	()Landroid/graphics/Rect;
    //   52: invokevirtual 371	android/graphics/Rect:centerX	()I
    //   55: aload_0
    //   56: invokevirtual 374	com/meizu/common/widget/AnimSeekBar:getPaddingLeft	()I
    //   59: iadd
    //   60: aload_0
    //   61: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   64: invokevirtual 377	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
    //   67: iconst_2
    //   68: idiv
    //   69: isub
    //   70: i2f
    //   71: putfield 379	com/meizu/common/widget/AnimSeekBar:mX	F
    //   74: aload_0
    //   75: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   78: invokevirtual 368	android/graphics/drawable/Drawable:getBounds	()Landroid/graphics/Rect;
    //   81: invokevirtual 371	android/graphics/Rect:centerX	()I
    //   84: i2f
    //   85: fstore_2
    //   86: aload_0
    //   87: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   90: invokevirtual 368	android/graphics/drawable/Drawable:getBounds	()Landroid/graphics/Rect;
    //   93: invokevirtual 371	android/graphics/Rect:centerX	()I
    //   96: i2f
    //   97: fstore_3
    //   98: aload_0
    //   99: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   102: invokevirtual 368	android/graphics/drawable/Drawable:getBounds	()Landroid/graphics/Rect;
    //   105: invokevirtual 383	android/graphics/Rect:exactCenterY	()F
    //   108: f2i
    //   109: i2f
    //   110: fstore 4
    //   112: aload_0
    //   113: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   116: invokevirtual 368	android/graphics/drawable/Drawable:getBounds	()Landroid/graphics/Rect;
    //   119: invokevirtual 383	android/graphics/Rect:exactCenterY	()F
    //   122: f2i
    //   123: i2f
    //   124: fstore 5
    //   126: aload_0
    //   127: invokevirtual 362	com/meizu/common/widget/AnimSeekBar:getThumb	()Landroid/graphics/drawable/Drawable;
    //   130: fload_2
    //   131: aload_0
    //   132: getfield 88	com/meizu/common/widget/AnimSeekBar:mPinRadiusPx	F
    //   135: fsub
    //   136: f2i
    //   137: fload 4
    //   139: aload_0
    //   140: getfield 88	com/meizu/common/widget/AnimSeekBar:mPinRadiusPx	F
    //   143: fsub
    //   144: f2i
    //   145: fload_3
    //   146: aload_0
    //   147: getfield 88	com/meizu/common/widget/AnimSeekBar:mPinRadiusPx	F
    //   150: fadd
    //   151: f2i
    //   152: fload 5
    //   154: aload_0
    //   155: getfield 88	com/meizu/common/widget/AnimSeekBar:mPinRadiusPx	F
    //   158: fadd
    //   159: f2i
    //   160: invokevirtual 387	android/graphics/drawable/Drawable:setBounds	(IIII)V
    //   163: aload_0
    //   164: getfield 111	com/meizu/common/widget/AnimSeekBar:mBounds	Landroid/graphics/Rect;
    //   167: aload_0
    //   168: getfield 379	com/meizu/common/widget/AnimSeekBar:mX	F
    //   171: aload_0
    //   172: getfield 246	com/meizu/common/widget/AnimSeekBar:mCircleRadius	I
    //   175: i2f
    //   176: fsub
    //   177: f2i
    //   178: aload_0
    //   179: getfield 324	com/meizu/common/widget/AnimSeekBar:mY	F
    //   182: aload_0
    //   183: getfield 246	com/meizu/common/widget/AnimSeekBar:mCircleRadius	I
    //   186: i2f
    //   187: fsub
    //   188: aload_0
    //   189: getfield 321	com/meizu/common/widget/AnimSeekBar:mMoveUpValue	I
    //   192: i2f
    //   193: fsub
    //   194: aload_0
    //   195: getfield 261	com/meizu/common/widget/AnimSeekBar:mDistanceBwCircle	I
    //   198: i2f
    //   199: fsub
    //   200: f2i
    //   201: aload_0
    //   202: getfield 379	com/meizu/common/widget/AnimSeekBar:mX	F
    //   205: aload_0
    //   206: getfield 246	com/meizu/common/widget/AnimSeekBar:mCircleRadius	I
    //   209: i2f
    //   210: fadd
    //   211: f2i
    //   212: aload_0
    //   213: getfield 324	com/meizu/common/widget/AnimSeekBar:mY	F
    //   216: aload_0
    //   217: getfield 246	com/meizu/common/widget/AnimSeekBar:mCircleRadius	I
    //   220: i2f
    //   221: fadd
    //   222: aload_0
    //   223: getfield 321	com/meizu/common/widget/AnimSeekBar:mMoveUpValue	I
    //   226: i2f
    //   227: fsub
    //   228: aload_0
    //   229: getfield 261	com/meizu/common/widget/AnimSeekBar:mDistanceBwCircle	I
    //   232: i2f
    //   233: fsub
    //   234: f2i
    //   235: invokevirtual 390	android/graphics/Rect:set	(IIII)V
    //   238: aload_0
    //   239: getfield 236	com/meizu/common/widget/AnimSeekBar:mCircleAnimDrawble	Landroid/graphics/drawable/Drawable;
    //   242: aload_0
    //   243: getfield 111	com/meizu/common/widget/AnimSeekBar:mBounds	Landroid/graphics/Rect;
    //   246: invokevirtual 393	android/graphics/drawable/Drawable:setBounds	(Landroid/graphics/Rect;)V
    //   249: aload_0
    //   250: getfield 236	com/meizu/common/widget/AnimSeekBar:mCircleAnimDrawble	Landroid/graphics/drawable/Drawable;
    //   253: aload_0
    //   254: getfield 90	com/meizu/common/widget/AnimSeekBar:mFadeValue	I
    //   257: invokevirtual 396	android/graphics/drawable/Drawable:setAlpha	(I)V
    //   260: aload_0
    //   261: getfield 236	com/meizu/common/widget/AnimSeekBar:mCircleAnimDrawble	Landroid/graphics/drawable/Drawable;
    //   264: aload_1
    //   265: invokevirtual 399	android/graphics/drawable/Drawable:draw	(Landroid/graphics/Canvas;)V
    //   268: aload_0
    //   269: getfield 90	com/meizu/common/widget/AnimSeekBar:mFadeValue	I
    //   272: bipush 100
    //   274: if_icmple +148 -> 422
    //   277: aload_0
    //   278: aload_0
    //   279: invokevirtual 402	com/meizu/common/widget/AnimSeekBar:getProgress	()I
    //   282: invokestatic 408	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   285: putfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   288: aload_0
    //   289: getfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   292: invokevirtual 415	java/lang/String:length	()I
    //   295: iconst_4
    //   296: if_icmple +16 -> 312
    //   299: aload_0
    //   300: aload_0
    //   301: getfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   304: iconst_0
    //   305: iconst_4
    //   306: invokevirtual 419	java/lang/String:substring	(II)Ljava/lang/String;
    //   309: putfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   312: aload_0
    //   313: getfield 287	com/meizu/common/widget/AnimSeekBar:mTextPaint	Landroid/graphics/Paint;
    //   316: aload_0
    //   317: getfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   320: iconst_0
    //   321: aload_0
    //   322: getfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   325: invokevirtual 415	java/lang/String:length	()I
    //   328: aload_0
    //   329: getfield 111	com/meizu/common/widget/AnimSeekBar:mBounds	Landroid/graphics/Rect;
    //   332: invokevirtual 423	android/graphics/Paint:getTextBounds	(Ljava/lang/String;IILandroid/graphics/Rect;)V
    //   335: aload_0
    //   336: getfield 287	com/meizu/common/widget/AnimSeekBar:mTextPaint	Landroid/graphics/Paint;
    //   339: getstatic 429	android/graphics/Paint$Align:CENTER	Landroid/graphics/Paint$Align;
    //   342: invokevirtual 433	android/graphics/Paint:setTextAlign	(Landroid/graphics/Paint$Align;)V
    //   345: aload_0
    //   346: getfield 287	com/meizu/common/widget/AnimSeekBar:mTextPaint	Landroid/graphics/Paint;
    //   349: invokevirtual 437	android/graphics/Paint:getFontMetricsInt	()Landroid/graphics/Paint$FontMetricsInt;
    //   352: astore 7
    //   354: aload_0
    //   355: getfield 324	com/meizu/common/widget/AnimSeekBar:mY	F
    //   358: aload_0
    //   359: getfield 321	com/meizu/common/widget/AnimSeekBar:mMoveUpValue	I
    //   362: i2f
    //   363: fsub
    //   364: aload_0
    //   365: getfield 261	com/meizu/common/widget/AnimSeekBar:mDistanceBwCircle	I
    //   368: i2f
    //   369: fsub
    //   370: aload 7
    //   372: getfield 442	android/graphics/Paint$FontMetricsInt:bottom	I
    //   375: aload 7
    //   377: getfield 445	android/graphics/Paint$FontMetricsInt:top	I
    //   380: isub
    //   381: iconst_2
    //   382: idiv
    //   383: i2f
    //   384: fsub
    //   385: aload 7
    //   387: getfield 445	android/graphics/Paint$FontMetricsInt:top	I
    //   390: i2f
    //   391: fsub
    //   392: f2i
    //   393: istore 6
    //   395: aload_1
    //   396: aload_0
    //   397: getfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   400: aload_0
    //   401: getfield 379	com/meizu/common/widget/AnimSeekBar:mX	F
    //   404: iload 6
    //   406: i2f
    //   407: aload_0
    //   408: getfield 287	com/meizu/common/widget/AnimSeekBar:mTextPaint	Landroid/graphics/Paint;
    //   411: invokevirtual 449	android/graphics/Canvas:drawText	(Ljava/lang/String;FFLandroid/graphics/Paint;)V
    //   414: aload_0
    //   415: aload_1
    //   416: invokespecial 451	com/meizu/common/widget/SkposSeekBar:onDraw	(Landroid/graphics/Canvas;)V
    //   419: aload_0
    //   420: monitorexit
    //   421: return
    //   422: aload_0
    //   423: ldc_w 453
    //   426: putfield 410	com/meizu/common/widget/AnimSeekBar:mTextNumber	Ljava/lang/String;
    //   429: goto -141 -> 288
    //   432: astore_1
    //   433: aload_0
    //   434: monitorexit
    //   435: aload_1
    //   436: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	437	0	this	AnimSeekBar
    //   0	437	1	paramCanvas	android.graphics.Canvas
    //   85	46	2	f1	float
    //   97	49	3	f2	float
    //   110	28	4	f3	float
    //   124	29	5	f4	float
    //   393	12	6	i	int
    //   352	34	7	localFontMetricsInt	android.graphics.Paint.FontMetricsInt
    // Exception table:
    //   from	to	target	type
    //   2	288	432	finally
    //   288	312	432	finally
    //   312	414	432	finally
    //   414	419	432	finally
    //   422	429	432	finally
  }
  
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if (getThumb() != null)
    {
      releasePin();
      invalidate();
    }
    mIsLongOrScroll = false;
    return true;
  }
  
  public void onLongPress(MotionEvent paramMotionEvent)
  {
    mIsTapPressed = false;
    mIsLongOrScroll = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      try
      {
        mAinmSeekBarHeight = ((int)(mCircleRadius + mDistanceBwCircle + mMoveUpValueDp + getPaddingTop() + getPaddingBottom() + mThumbHeight));
        int k = View.MeasureSpec.getMode(paramInt1);
        int i = View.MeasureSpec.getMode(paramInt2);
        paramInt1 = View.MeasureSpec.getSize(paramInt1);
        int j = View.MeasureSpec.getSize(paramInt2);
        if (k == Integer.MIN_VALUE)
        {
          paramInt2 = paramInt1;
          if (i == Integer.MIN_VALUE)
          {
            paramInt1 = mAinmSeekBarHeight;
            setMeasuredDimension(paramInt2, paramInt1);
          }
        }
        else
        {
          if (k == 1073741824)
          {
            paramInt2 = paramInt1;
            continue;
          }
          paramInt2 = mDefaultWidth;
          continue;
        }
        if (i != 1073741824) {
          break label187;
        }
        if (j < mDefaultHeight)
        {
          paramInt1 = mDefaultHeight;
          mCircleRadius = ((int)TypedValue.applyDimension(1, 15.0F, mRes.getDisplayMetrics()));
          mDistanceBwCircle = ((int)TypedValue.applyDimension(1, 10.0F, mRes.getDisplayMetrics()));
          continue;
        }
        paramInt1 = mAinmSeekBarHeight;
      }
      finally {}
      continue;
      label187:
      paramInt1 = mAinmSeekBarHeight;
    }
  }
  
  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    mIsTapPressed = false;
    mIsLongOrScroll = true;
    if (getThumb() != null)
    {
      paramFloat1 = getThumb().getBounds().centerX();
      paramFloat2 = getThumb().getBounds().centerY();
      getThumb().setBounds((int)(paramFloat1 - mThumbRadis / 2), (int)(paramFloat2 - mThumbRadis / 2), (int)(paramFloat1 + mThumbRadis / 2), (int)(paramFloat2 + mThumbRadis / 2));
    }
    if (!mIsStartAnim)
    {
      mIsStartAnim = true;
      pressPin();
    }
    for (;;)
    {
      invalidate();
      return true;
      mScaleanim.end();
      mPinRadiusPx = mPinRadisMin;
    }
  }
  
  public void onShowPress(MotionEvent paramMotionEvent)
  {
    if (!mIsStartAnim)
    {
      pressPin();
      mIsStartAnim = true;
    }
  }
  
  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if ((mPinRadiusPx != mPinRadisMax) || (mIsStartAnim))
    {
      mIsTapPressed = false;
      return true;
    }
    mIsTapPressed = true;
    return true;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    mProgressDrawble = getProgressDrawable();
    Drawable localDrawable = getThumb();
    if ((localDrawable != null) && (mProgressDrawble != null))
    {
      mThumbRadis = (localDrawable.getBounds().width() / 2);
      mPinRadisMax = mThumbRadis;
      mPinRadisMin = (mThumbRadis / 2);
      mThumbHeight = localDrawable.getBounds().height();
      mPinRadiusPx = mThumbRadis;
      setmY(mProgressDrawble.getBounds().centerY());
      if (mSdkApi <= 21) {
        getThumb().setBounds(localDrawable.getBounds().centerX() - mThumbRadis, mProgressDrawble.getBounds().centerY() - mThumbRadis, localDrawable.getBounds().centerX() + mThumbRadis, mProgressDrawble.getBounds().centerY() + mThumbRadis);
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    super.onTouchEvent(paramMotionEvent);
    if (!isEnabled()) {
      return false;
    }
    mGesture.onTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return true;
      if ((mIsLongOrScroll) && (getThumb() != null))
      {
        float f1 = getThumb().getBounds().centerX();
        float f2 = getThumb().getBounds().centerY();
        getThumb().setBounds((int)(f1 - mThumbRadis / 2), (int)(f2 - mThumbRadis / 2), (int)(f1 + mThumbRadis / 2), (int)(f2 + mThumbRadis / 2));
      }
      invalidate();
      continue;
      if (getParent() != null) {
        getParent().requestDisallowInterceptTouchEvent(false);
      }
      if (!mIsTapPressed) {
        onActionUp(paramMotionEvent.getX(), paramMotionEvent.getY());
      }
    }
  }
  
  public void setDistanceToCircle(int paramInt)
  {
    Context localContext = getContext();
    if (mRes == null) {}
    for (mRes = Resources.getSystem();; mRes = localContext.getResources())
    {
      int i = (int)TypedValue.applyDimension(1, paramInt, mRes.getDisplayMetrics());
      if (paramInt != mDistanceBwCircle)
      {
        mDistanceBwCircle = i;
        mCheckRadisChanged = true;
        requestLayout();
      }
      invalidate();
      return;
    }
  }
  
  public void setLargeCircleDrawble(Drawable paramDrawable)
  {
    int i = -1;
    int k;
    int m;
    int j;
    if (mCircleAnimDrawble != paramDrawable)
    {
      k = mCircleAnimDrawble.getBounds().width();
      m = mCircleAnimDrawble.getBounds().height();
      mCircleAnimDrawble = paramDrawable;
      if (paramDrawable == null) {
        break label80;
      }
      j = paramDrawable.getBounds().width();
      i = paramDrawable.getBounds().height();
    }
    for (;;)
    {
      if ((k != j) || (m != i)) {
        requestLayout();
      }
      invalidate();
      return;
      label80:
      j = -1;
    }
  }
  
  public void setLargeCircleRadis(int paramInt)
  {
    Context localContext = getContext();
    if (mRes == null) {}
    for (mRes = Resources.getSystem();; mRes = localContext.getResources())
    {
      int i = (int)TypedValue.applyDimension(1, paramInt, mRes.getDisplayMetrics());
      if (mCircleRadius != paramInt)
      {
        mCircleRadius = i;
        requestLayout();
      }
      invalidate();
      return;
    }
  }
  
  public void setTextNumberColor(int paramInt)
  {
    if (mTextNumberColor != paramInt)
    {
      mTextNumberColor = paramInt;
      mTextPaint.setColor(mTextNumberColor);
      postInvalidate();
    }
  }
  
  public void setTextNumberSize(int paramInt)
  {
    Context localContext = getContext();
    if (mRes == null) {}
    for (mRes = Resources.getSystem();; mRes = localContext.getResources())
    {
      if (paramInt != mTextNumberSize)
      {
        mTextNumberSize = ((int)TypedValue.applyDimension(2, paramInt, mRes.getDisplayMetrics()));
        mTextPaint.setTextSize(mTextNumberSize);
        requestLayout();
      }
      invalidate();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimSeekBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */