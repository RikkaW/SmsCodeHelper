package com.android.mms.ui;

import android.content.Context;
import android.os.Handler;
import com.android.mms.model.Model;
import java.util.Iterator;
import le;
import lk;
import lm;
import lm.a;
import lo;
import lp;
import lq;
import lr;
import lu;
import lw;
import ps;
import ps.a;
import xf;
import yx;
import yy;
import yz;
import za;
import zb;
import zy;

public class SlideshowPresenter
  extends xf
{
  private static final boolean DEBUG = false;
  private static final boolean LOCAL_LOGV = false;
  private static final String TAG = "SlideshowPresenter";
  protected final Handler mHandler = new Handler();
  public float mHeightTransformRatio = 1.0F;
  protected int mLocation;
  protected final int mSlideNumber;
  private final ps.a mViewSizeChangedListener = new yx(this);
  public float mWidthTransformRatio = 1.0F;
  
  public SlideshowPresenter(Context paramContext, ViewInterface paramViewInterface, Model paramModel)
  {
    super(paramContext, paramViewInterface, paramModel);
    if (mModel != null) {
      mModel.c(this);
    }
    mLocation = 0;
    mSlideNumber = ((lr)mModel).size();
    if ((paramViewInterface instanceof ps)) {
      ((ps)paramViewInterface).setOnSizeChangedListener(mViewSizeChangedListener);
    }
  }
  
  private float getHeightTransformRatio(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0) {
      return paramInt2 / paramInt1;
    }
    return 1.0F;
  }
  
  private float getWidthTransformRatio(int paramInt1, int paramInt2)
  {
    if (paramInt1 > 0) {
      return paramInt2 / paramInt1;
    }
    return 1.0F;
  }
  
  private int transformHeight(int paramInt)
  {
    return (int)(paramInt / mHeightTransformRatio);
  }
  
  private int transformWidth(int paramInt)
  {
    return (int)(paramInt / mWidthTransformRatio);
  }
  
  public void cancelBackgroundLoading() {}
  
  public int getLocation()
  {
    return mLocation;
  }
  
  public void goBackward()
  {
    if (mLocation > 0) {
      mLocation -= 1;
    }
  }
  
  public void goForward()
  {
    if (mLocation < mSlideNumber - 1) {
      mLocation += 1;
    }
  }
  
  public void onModelChanged(Model paramModel, boolean paramBoolean)
  {
    SlideViewInterface localSlideViewInterface = (SlideViewInterface)mView;
    if ((paramModel instanceof lr)) {}
    do
    {
      do
      {
        return;
        if ((paramModel instanceof lq))
        {
          if (((lq)paramModel).c())
          {
            mHandler.post(new yy(this, localSlideViewInterface, paramModel));
            return;
          }
          mHandler.post(new yz(this));
          return;
        }
        if (!(paramModel instanceof lm)) {
          break;
        }
        if ((paramModel instanceof lo))
        {
          mHandler.post(new za(this, localSlideViewInterface, paramModel, paramBoolean));
          return;
        }
      } while (!((lm)paramModel).t());
      mHandler.post(new zb(this, localSlideViewInterface, paramModel, paramBoolean));
      return;
    } while (!(paramModel instanceof lp));
  }
  
  public void present(zy paramzy)
  {
    presentSlide((SlideViewInterface)mView, ((lr)mModel).a(mLocation));
  }
  
  public void presentAudio(SlideViewInterface paramSlideViewInterface, le paramle, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramSlideViewInterface.a(paramle.k(), paramle.n(), paramle.a());
    }
    lm.a locala = paramle.z();
    if (locala == lm.a.b) {
      paramSlideViewInterface.l();
    }
    do
    {
      return;
      if (locala == lm.a.d)
      {
        paramSlideViewInterface.n();
        return;
      }
      if (locala == lm.a.c)
      {
        paramSlideViewInterface.m();
        return;
      }
    } while (locala != lm.a.e);
    paramSlideViewInterface.b(paramle.y());
  }
  
  protected void presentImage(SlideViewInterface paramSlideViewInterface, lk paramlk, lp paramlp, boolean paramBoolean)
  {
    int i = transformWidth(paramlp.e());
    int j = transformWidth(paramlp.f());
    if (paramBoolean) {
      paramSlideViewInterface.a(paramlk.n(), paramlk.a(i, j));
    }
    if ((paramSlideViewInterface instanceof ps)) {
      ((ps)paramSlideViewInterface).b(transformWidth(paramlp.c()), transformHeight(paramlp.d()), i, j);
    }
    paramSlideViewInterface.setImageRegionFit(paramlp.b());
    paramSlideViewInterface.setImageVisibility(paramlk.D());
  }
  
  public void presentRegionMedia(SlideViewInterface paramSlideViewInterface, lo paramlo, boolean paramBoolean)
  {
    lp locallp = paramlo.C();
    if (paramlo.q()) {
      presentText(paramSlideViewInterface, (lu)paramlo, locallp, paramBoolean);
    }
    do
    {
      return;
      if (paramlo.r())
      {
        presentImage(paramSlideViewInterface, (lk)paramlo, locallp, paramBoolean);
        return;
      }
    } while (!paramlo.s());
    presentVideo(paramSlideViewInterface, (lw)paramlo, locallp, paramBoolean);
  }
  
  public void presentSlide(SlideViewInterface paramSlideViewInterface, lq paramlq)
  {
    paramSlideViewInterface.h();
    if ((paramSlideViewInterface instanceof SlideView)) {
      ((SlideView)paramSlideViewInterface).c(paramlq.x());
    }
    paramlq = paramlq.iterator();
    while (paramlq.hasNext())
    {
      lm locallm = (lm)paramlq.next();
      if ((locallm instanceof lo)) {
        presentRegionMedia(paramSlideViewInterface, (lo)locallm, true);
      } else if (locallm.t()) {
        presentAudio(paramSlideViewInterface, (le)locallm, true);
      }
    }
  }
  
  protected void presentText(SlideViewInterface paramSlideViewInterface, lu paramlu, lp paramlp, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramSlideViewInterface.a(paramlu.n(), paramlu.a());
    }
    if ((paramSlideViewInterface instanceof ps)) {
      ((ps)paramSlideViewInterface).a(transformWidth(paramlp.c()), transformHeight(paramlp.d()), transformWidth(paramlp.e()), transformHeight(paramlp.f()));
    }
    paramSlideViewInterface.setTextVisibility(paramlu.D());
  }
  
  protected void presentVideo(SlideViewInterface paramSlideViewInterface, lw paramlw, lp paramlp, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramSlideViewInterface.a(paramlw.n(), paramlw.k());
    }
    if ((paramSlideViewInterface instanceof ps)) {
      ((ps)paramSlideViewInterface).c(transformWidth(paramlp.c()), transformHeight(paramlp.d()), transformWidth(paramlp.e()), transformHeight(paramlp.f()));
    }
    paramSlideViewInterface.setVideoVisibility(paramlw.D());
    paramlp = paramlw.z();
    if (paramlp == lm.a.b) {
      paramSlideViewInterface.i();
    }
    do
    {
      return;
      if (paramlp == lm.a.d)
      {
        paramSlideViewInterface.k();
        return;
      }
      if (paramlp == lm.a.c)
      {
        paramSlideViewInterface.j();
        return;
      }
    } while (paramlp != lm.a.e);
    paramSlideViewInterface.a(paramlw.y());
  }
  
  public void setLocation(int paramInt)
  {
    mLocation = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowPresenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */