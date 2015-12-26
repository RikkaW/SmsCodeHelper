package com.meizu.common.widget;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.R.styleable;
import java.util.Calendar;

public class CustomDatePickerDialog
  extends AlertDialog
  implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener
{
  private static final String DAY = "day";
  private static final String MONTH = "month";
  private static final String TAG = "CustomDatePickerDialog";
  private static final String YEAR = "year";
  final int gregorianColor;
  final int lunarColor;
  private final OnDateSetListener mCallBack;
  private final DatePicker mDatePicker;
  private long mDuration = 200L;
  private TextView mGregorianTab;
  private View mIndicator;
  private TextView mLunarTab;
  private ValueAnimator mValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
  final int tabTextSelectColor;
  final int unSlectColor;
  
  public CustomDatePickerDialog(Context paramContext, int paramInt1, OnDateSetListener paramOnDateSetListener, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramContext, paramInt1);
    mCallBack = paramOnDateSetListener;
    setButton(-1, paramContext.getText(R.string.mc_yes), this);
    setButton(-2, paramContext.getText(17039360), (DialogInterface.OnClickListener)null);
    paramOnDateSetListener = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.mc_custom_date_picker_dialog, null);
    setView(paramOnDateSetListener);
    mDatePicker = ((DatePicker)paramOnDateSetListener.findViewById(R.id.datePicker));
    mDatePicker.init(paramInt2, paramInt3, paramInt4, this, false);
    mDatePicker.setIsDrawLine(true);
    mDatePicker.setLineHeight(paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_line_one_height), paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_line_two_height));
    mValueAnimator.addUpdateListener(new CustomDatePickerDialog.1(this));
    mValueAnimator.setDuration(mDuration);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(R.styleable.MZTheme);
    lunarColor = localTypedArray.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_lunar_color));
    gregorianColor = localTypedArray.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_gregorian_color));
    localTypedArray.recycle();
    unSlectColor = paramContext.getResources().getColor(R.color.mc_custom_date_picker_unselected_color);
    tabTextSelectColor = paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_tab_color);
    initTabView(paramContext, paramOnDateSetListener);
    setOnShowListener(new CustomDatePickerDialog.2(this));
  }
  
  public CustomDatePickerDialog(Context paramContext, OnDateSetListener paramOnDateSetListener, int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramContext, 0, paramOnDateSetListener, paramInt1, paramInt2, paramInt3);
  }
  
  private void initTabView(Context paramContext, View paramView)
  {
    mLunarTab = ((TextView)paramView.findViewById(R.id.lunar));
    mGregorianTab = ((TextView)paramView.findViewById(R.id.gregorian));
    mIndicator = paramView.findViewById(R.id.indicator);
    paramView = paramContext.obtainStyledAttributes(R.styleable.MZTheme);
    int i = paramView.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_lunar_color));
    int j = paramView.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_gregorian_color));
    paramView.recycle();
    int k = paramContext.getResources().getColor(R.color.mc_custom_date_picker_unselected_color);
    int m = getContext().getResources().getColor(R.color.mc_custom_date_picker_unselected_tab_color);
    if (mDatePicker.isLunar())
    {
      mLunarTab.setTextColor(tabTextSelectColor);
      mGregorianTab.setTextColor(m);
    }
    for (;;)
    {
      m = paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_picker_dicator_height);
      paramContext = new GradientDrawable();
      paramContext.setShape(0);
      paramContext.setColor(j);
      paramContext.setCornerRadius(m / 2);
      mIndicator.setBackgroundDrawable(paramContext);
      mDatePicker.setTextColor(j, k, j);
      mLunarTab.setOnClickListener(new CustomDatePickerDialog.4(this, i, k));
      mGregorianTab.setOnClickListener(new CustomDatePickerDialog.5(this, j, k));
      return;
      mLunarTab.setTextColor(m);
      mGregorianTab.setTextColor(tabTextSelectColor);
    }
  }
  
  private void setTabColor(int paramInt, boolean paramBoolean)
  {
    float f2 = 1.5333F;
    paramInt = getContext().getResources().getColor(R.color.mc_custom_date_picker_unselected_tab_color);
    float f1;
    if (paramBoolean) {
      f1 = 0.0F;
    }
    for (;;)
    {
      Object localObject = new TranslateAnimation(1, f1, 1, f2, 1, 0.0F, 1, 0.0F);
      ((TranslateAnimation)localObject).setDuration(mDuration);
      ((TranslateAnimation)localObject).setFillAfter(true);
      mIndicator.startAnimation((Animation)localObject);
      int i = Color.red(paramInt);
      int j = Color.green(paramInt);
      int k = Color.blue(paramInt);
      int m = Color.red(tabTextSelectColor);
      int n = Color.red(tabTextSelectColor);
      int i1 = Color.red(tabTextSelectColor);
      localObject = ValueAnimator.ofPropertyValuesHolder(new PropertyValuesHolder[] { PropertyValuesHolder.ofInt("RgbRed", new int[] { i, m }), PropertyValuesHolder.ofInt("RgbGreen", new int[] { j, n }), PropertyValuesHolder.ofInt("RgbBlue", new int[] { k, i1 }) });
      if ((localObject != null) && ((((ValueAnimator)localObject).isStarted()) || (((ValueAnimator)localObject).isRunning()))) {
        ((ValueAnimator)localObject).cancel();
      }
      ((ValueAnimator)localObject).addUpdateListener(new CustomDatePickerDialog.3(this, (ValueAnimator)localObject, paramBoolean, paramInt));
      ((ValueAnimator)localObject).setDuration(mDuration);
      ((ValueAnimator)localObject).start();
      return;
      f1 = 1.5333F;
      f2 = 0.0F;
    }
  }
  
  public DatePicker getDatePicker()
  {
    return mDatePicker;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (mCallBack != null)
    {
      mDatePicker.clearFocus();
      mCallBack.onDateSet(mDatePicker, mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }
  
  public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
  {
    mDatePicker.init(paramInt1, paramInt2, paramInt3, null, true);
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    int i = paramBundle.getInt("year");
    int j = paramBundle.getInt("month");
    int k = paramBundle.getInt("day");
    mDatePicker.init(i, j, k, this, false);
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = super.onSaveInstanceState();
    localBundle.putInt("year", mDatePicker.getYear());
    localBundle.putInt("month", mDatePicker.getMonth());
    localBundle.putInt("day", mDatePicker.getDayOfMonth());
    return localBundle;
  }
  
  public void setMaxYear(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 2099) {
      i = 2099;
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(i, 1, 1);
    mDatePicker.setMaxDate(localCalendar.getTimeInMillis());
  }
  
  public void setMinYear(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1901) {
      i = 1901;
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(i, 1, 1);
    mDatePicker.setMinDate(localCalendar.getTimeInMillis());
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3)
  {
    mDatePicker.updateDate(paramInt1, paramInt2, paramInt3);
  }
  
  public static class FlipView
    extends View
  {
    private static final int DEPTH_CONSTANT = 1500;
    private static final int POLY_POINTS = 8;
    private Bitmap mBitmapGregorian;
    private int mBitmapHeight;
    private Bitmap mBitmapLunar;
    private int mBitmapWidth;
    float[] mDstPoly = new float[8];
    private float mFoldFactor = 0.0F;
    private boolean mIslunar = false;
    private Matrix[] mMatrices;
    private Paint mPaintShadow;
    private Rect mRect;
    float[] mSrcPoly = new float[8];
    
    public FlipView(Context paramContext)
    {
      super();
      init();
    }
    
    public FlipView(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      init();
    }
    
    private void calculateMatrix()
    {
      float f1 = Math.round((1.0F - mFoldFactor) * mBitmapWidth / 2.0F);
      f1 = ((float)Math.sqrt(mBitmapWidth / 2 * (mBitmapWidth / 2) - f1 * f1) + 1500.0F) / 1500.0F;
      float f2 = mBitmapHeight * f1;
      mSrcPoly[0] = 0.0F;
      mSrcPoly[1] = 0.0F;
      mSrcPoly[2] = 0.0F;
      mSrcPoly[3] = mBitmapHeight;
      mSrcPoly[4] = (mBitmapWidth / 2);
      mSrcPoly[5] = 0.0F;
      mSrcPoly[6] = (mBitmapWidth / 2);
      mSrcPoly[7] = mBitmapHeight;
      if (mFoldFactor < 0.5D) {}
      for (f1 = mFoldFactor;; f1 = 1.0F - mFoldFactor)
      {
        mMatrices[0].reset();
        mDstPoly[0] = (mBitmapWidth * f1);
        mDstPoly[1] = ((mBitmapHeight - f2) / 2.0F);
        mDstPoly[2] = mDstPoly[0];
        mDstPoly[3] = (mBitmapHeight + (f2 - mBitmapHeight) / 2.0F);
        mDstPoly[4] = (mBitmapWidth / 2);
        mDstPoly[5] = 0.0F;
        mDstPoly[6] = mDstPoly[4];
        mDstPoly[7] = mBitmapHeight;
        i = 0;
        while (i < 8)
        {
          mDstPoly[i] = Math.round(mDstPoly[i]);
          i += 1;
        }
      }
      mMatrices[0].setPolyToPoly(mSrcPoly, 0, mDstPoly, 0, 4);
      mMatrices[1].reset();
      mDstPoly[0] = (mBitmapWidth / 2);
      mDstPoly[1] = 0.0F;
      mDstPoly[2] = mDstPoly[0];
      mDstPoly[3] = mBitmapHeight;
      mDstPoly[4] = (mBitmapWidth - mBitmapWidth * f1);
      mDstPoly[5] = ((mBitmapHeight - f2) / 2.0F);
      mDstPoly[6] = mDstPoly[4];
      mDstPoly[7] = (mBitmapHeight + (f2 - mBitmapHeight) / 2.0F);
      int i = 0;
      while (i < 8)
      {
        mDstPoly[i] = Math.round(mDstPoly[i]);
        i += 1;
      }
      mMatrices[1].setPolyToPoly(mSrcPoly, 0, mDstPoly, 0, 4);
    }
    
    private void init()
    {
      int i = 0;
      mRect = new Rect(0, 0, mBitmapWidth, mBitmapHeight);
      mMatrices = new Matrix[2];
      mBitmapGregorian = BitmapFactory.decodeResource(getResources(), R.drawable.mc_ic_popup_calendar_gregorian);
      mBitmapLunar = BitmapFactory.decodeResource(getResources(), R.drawable.mc_ic_popup_calendar_lunar);
      mBitmapWidth = mBitmapLunar.getWidth();
      mBitmapHeight = mBitmapLunar.getHeight();
      while (i < 2)
      {
        mMatrices[i] = new Matrix();
        i += 1;
      }
      mPaintShadow = new Paint();
      Object localObject = new ColorMatrix();
      ((ColorMatrix)localObject).setSaturation(0.8F);
      localObject = new ColorMatrixColorFilter((ColorMatrix)localObject);
      mPaintShadow.setColorFilter((ColorFilter)localObject);
    }
    
    public void onDraw(Canvas paramCanvas)
    {
      int i = 1;
      calculateMatrix();
      Bitmap localBitmap;
      if (mFoldFactor == 0.0F)
      {
        if (mIslunar) {}
        for (localBitmap = mBitmapLunar;; localBitmap = mBitmapGregorian)
        {
          paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
          return;
        }
      }
      if (mFoldFactor == 1.0F)
      {
        if (mIslunar) {}
        for (localBitmap = mBitmapGregorian;; localBitmap = mBitmapLunar)
        {
          paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
          return;
        }
      }
      mRect.set(0, 0, mBitmapWidth, mBitmapHeight);
      paramCanvas.drawBitmap(mBitmapLunar, mRect, mRect, mPaintShadow);
      mRect.set(0, 0, mBitmapWidth / 2, mBitmapHeight);
      paramCanvas.drawBitmap(mBitmapGregorian, mRect, mRect, null);
      paramCanvas.save();
      if (mIslunar) {
        if (mFoldFactor < 0.5F)
        {
          mRect.set(0, 0, mBitmapWidth / 2, mBitmapHeight);
          localBitmap = mBitmapLunar;
          i = 0;
        }
      }
      for (;;)
      {
        paramCanvas.concat(mMatrices[i]);
        paramCanvas.clipRect(0, 0, mRect.right - mRect.left, mRect.bottom - mRect.top);
        paramCanvas.translate(-mRect.left, 0.0F);
        paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
        paramCanvas.restore();
        return;
        mRect.set(mBitmapWidth / 2, 0, mBitmapWidth, mBitmapHeight);
        localBitmap = mBitmapGregorian;
        continue;
        if (mFoldFactor < 0.5F)
        {
          mRect.set(mBitmapWidth / 2, 0, mBitmapWidth, mBitmapHeight);
          localBitmap = mBitmapGregorian;
        }
        else
        {
          mRect.set(0, 0, mBitmapWidth / 2, mBitmapHeight);
          localBitmap = mBitmapLunar;
          i = 0;
        }
      }
    }
    
    public void setFilpViewPrefer(boolean paramBoolean)
    {
      mIslunar = paramBoolean;
    }
    
    public void setFoldFactor(float paramFloat)
    {
      mFoldFactor = paramFloat;
      postInvalidate();
    }
  }
  
  public static abstract interface OnDateSetListener
  {
    public abstract void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomDatePickerDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */