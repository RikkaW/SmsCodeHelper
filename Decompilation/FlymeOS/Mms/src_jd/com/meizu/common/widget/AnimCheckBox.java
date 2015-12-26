package com.meizu.common.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.CheckBox;

public class AnimCheckBox
  extends CheckBox
{
  private CheckBoxAnimHelper checkBoxHelper;
  int mInitVisible = getVisibility();
  private UpdateListener mUpdateListener;
  
  public AnimCheckBox(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AnimCheckBox(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public AnimCheckBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setIsAnimation(true);
  }
  
  public AnimCheckBox(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    setIsAnimation(true);
  }
  
  public void setActivated(boolean paramBoolean)
  {
    if (checkBoxHelper == null)
    {
      super.setActivated(paramBoolean);
      return;
    }
    checkBoxHelper.setActivated(paramBoolean);
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (checkBoxHelper == null)
    {
      super.setChecked(paramBoolean);
      return;
    }
    checkBoxHelper.setChecked(paramBoolean);
  }
  
  public void setInitVisible(int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 4) || (paramInt == 8)) {
      mInitVisible = paramInt;
    }
  }
  
  public void setIsAnimation(boolean paramBoolean)
  {
    if (checkBoxHelper == null) {
      checkBoxHelper = new CheckBoxAnimHelper(this);
    }
    checkBoxHelper.setIsAnimation(paramBoolean);
  }
  
  public void setUpdateListner(UpdateListener paramUpdateListener)
  {
    mUpdateListener = paramUpdateListener;
  }
  
  public void superSetActivate(boolean paramBoolean)
  {
    super.setActivated(paramBoolean);
  }
  
  public void superSetCheck(boolean paramBoolean)
  {
    super.setChecked(paramBoolean);
  }
  
  static class CheckBoxAnimHelper
  {
    private boolean DEBUG = false;
    private ObjectAnimator mAnimator1;
    private ObjectAnimator mAnimator2;
    private ValueAnimator mAnimator3;
    private AnimatorSet mAnimatorSet;
    private boolean mHasInit = false;
    private TimeInterpolator mInterpolator1;
    private TimeInterpolator mInterpolator2;
    private TimeInterpolator mInterpolator3;
    private TimeInterpolator mInterpolator4;
    private boolean mIsAnimation = true;
    private AnimCheckBox mTarget;
    private boolean targetActivatedState;
    private boolean targetChecekedState;
    
    public CheckBoxAnimHelper(AnimCheckBox paramAnimCheckBox)
    {
      mTarget = paramAnimCheckBox;
      init();
      mHasInit = true;
    }
    
    private void init()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        mInterpolator1 = new PathInterpolator(0.33F, 0.0F, 1.0F, 1.0F);
        mInterpolator2 = new PathInterpolator(0.0F, 0.0F, 0.01F, 1.0F);
        mInterpolator3 = new PathInterpolator(0.4F, 0.0F, 0.01F, 1.0F);
        mInterpolator4 = new PathInterpolator(0.0F, 0.0F, 0.1F, 1.0F);
      }
      for (;;)
      {
        Object localObject = PropertyValuesHolder.ofFloat("scaleY", new float[] { 1.0F, 0.0F });
        mAnimator1 = ObjectAnimator.ofPropertyValuesHolder(mTarget, new PropertyValuesHolder[] { localObject });
        mAnimator1.setInterpolator(mInterpolator1);
        mAnimator1.addListener(new AnimCheckBox.CheckBoxAnimHelper.1(this));
        localObject = PropertyValuesHolder.ofFloat("scaleY", new float[] { 0.0F, 1.0F });
        mAnimator2 = ObjectAnimator.ofPropertyValuesHolder(mTarget, new PropertyValuesHolder[] { localObject });
        mAnimator2.setInterpolator(mInterpolator2);
        mAnimator3 = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
        mAnimator3.setInterpolator(mInterpolator3);
        mAnimator3.addUpdateListener(new AnimCheckBox.CheckBoxAnimHelper.2(this));
        mAnimator3.addListener(new AnimCheckBox.CheckBoxAnimHelper.3(this));
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(new Animator[] { mAnimator1, mAnimator3 });
        return;
        localObject = new DecelerateInterpolator();
        mInterpolator4 = ((TimeInterpolator)localObject);
        mInterpolator3 = ((TimeInterpolator)localObject);
        mInterpolator2 = ((TimeInterpolator)localObject);
        mInterpolator1 = ((TimeInterpolator)localObject);
      }
    }
    
    public void setActivated(boolean paramBoolean)
    {
      targetActivatedState = paramBoolean;
      if ((!mHasInit) || (!mIsAnimation)) {
        mTarget.superSetActivate(paramBoolean);
      }
      do
      {
        do
        {
          do
          {
            return;
            if (DEBUG) {
              Log.i("xx", "setActivated activated = " + paramBoolean + " " + mTarget.isActivated() + " " + targetActivatedState + " targetChecekedState = " + targetChecekedState + " " + mTarget.isChecked() + " " + mAnimatorSet.isRunning() + " " + mAnimator2.isRunning());
            }
          } while ((paramBoolean == mTarget.isActivated()) || ((!paramBoolean) && (!targetChecekedState) && (mTarget.isChecked())));
          if ((!mTarget.isChecked()) || (!targetChecekedState)) {
            break;
          }
          mTarget.superSetActivate(paramBoolean);
        } while ((mAnimatorSet.isRunning()) || (mAnimator2.isRunning()));
        Object localObject = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[] { 0.0F, 1.0F });
        PropertyValuesHolder localPropertyValuesHolder = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[] { 0.0F, 1.0F });
        localObject = ObjectAnimator.ofPropertyValuesHolder(mTarget, new PropertyValuesHolder[] { localObject, localPropertyValuesHolder });
        ((ObjectAnimator)localObject).setDuration(40L).setInterpolator(mInterpolator4);
        ((ObjectAnimator)localObject).start();
        return;
      } while (paramBoolean);
      mAnimatorSet.end();
      mAnimator2.end();
      mTarget.superSetActivate(paramBoolean);
    }
    
    public void setChecked(boolean paramBoolean)
    {
      if ((!mHasInit) || (!mIsAnimation))
      {
        mTarget.superSetCheck(paramBoolean);
        targetChecekedState = paramBoolean;
      }
      do
      {
        return;
        if (DEBUG) {
          Log.i("xx", "setChecked checked = " + paramBoolean + " targetChecekedState = " + targetChecekedState + " " + " " + mAnimatorSet.isRunning() + " " + mAnimator2.isRunning());
        }
      } while (paramBoolean == targetChecekedState);
      targetChecekedState = paramBoolean;
      if (paramBoolean)
      {
        if ((!mAnimatorSet.isRunning()) && (!mAnimator2.isRunning()))
        {
          mAnimator1.setDuration(150L);
          mAnimator2.setDuration(230L);
          mAnimator3.setDuration(380L);
          mAnimatorSet.start();
          return;
        }
        mAnimatorSet.end();
        mAnimator2.end();
        targetChecekedState = false;
        setChecked(paramBoolean);
        return;
      }
      if ((!mAnimatorSet.isRunning()) && (!mAnimator2.isRunning()))
      {
        mAnimator1.setDuration(0L);
        mAnimator2.setDuration(476L);
        mAnimator3.setDuration(476L);
        mAnimatorSet.start();
        return;
      }
      mTarget.superSetCheck(paramBoolean);
      mAnimatorSet.end();
      mAnimator2.end();
    }
    
    public void setIsAnimation(boolean paramBoolean)
    {
      mIsAnimation = paramBoolean;
    }
  }
  
  public static abstract interface UpdateListener
  {
    public abstract void getUpdateTransition(float paramFloat);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.AnimCheckBox
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */