package com.android.mms.ui;

import abm.a;
import android.content.Context;
import com.android.mms.MmsApp;
import com.android.mms.model.Model;
import le;
import li;
import lk;
import lq;
import lr;
import lv;
import lw;
import wx;
import xf;
import zy;
import zz;

public class MmsThumbnailPresenter
  extends xf
{
  private static final String TAG = "MmsThumbnailPresenter";
  private zy<abm.a> mImageLoadedCallback = new wx(this);
  private zz mItemLoadedFuture;
  private zy mOnLoadedCallback;
  
  public MmsThumbnailPresenter(Context paramContext, ViewInterface paramViewInterface, Model paramModel)
  {
    super(paramContext, paramViewInterface, paramModel);
  }
  
  private void presentFirstSlide(SlideViewInterface paramSlideViewInterface, lq paramlq)
  {
    paramSlideViewInterface.h();
    if (paramlq.e())
    {
      presentImageThumbnail(paramSlideViewInterface, paramlq.q());
      return;
    }
    if (paramlq.g())
    {
      presentVideoThumbnail(paramSlideViewInterface, paramlq.s());
      return;
    }
    if (paramlq.f())
    {
      presentAudioThumbnail(paramSlideViewInterface, paramlq.r());
      return;
    }
    if (paramlq.i())
    {
      presentVcardThumbnail(paramSlideViewInterface, paramlq.t());
      return;
    }
    if (paramlq.j())
    {
      presentFileThumbnail(paramSlideViewInterface, paramlq.u());
      return;
    }
    presentOtherThumbnail(paramSlideViewInterface);
  }
  
  private void presentFirstSlide(SlideViewInterface paramSlideViewInterface, lq paramlq, long paramLong)
  {
    paramSlideViewInterface.h();
    if (paramlq.e())
    {
      presentImageThumbnail(paramSlideViewInterface, paramlq.q(), paramLong);
      return;
    }
    if (paramlq.g())
    {
      presentVideoThumbnail(paramSlideViewInterface, paramlq.s(), paramLong);
      return;
    }
    if (paramlq.f())
    {
      presentAudioThumbnail(paramSlideViewInterface, paramlq.r(), paramLong);
      return;
    }
    if (paramlq.i())
    {
      presentVcardThumbnail(paramSlideViewInterface, paramlq.t(), paramLong);
      return;
    }
    if (paramlq.j())
    {
      presentFileThumbnail(paramSlideViewInterface, paramlq.u(), paramLong);
      return;
    }
    presentOtherThumbnail(paramSlideViewInterface);
  }
  
  private void presentImageThumbnail(SlideViewInterface paramSlideViewInterface, lk paramlk)
  {
    mItemLoadedFuture = paramlk.a(mImageLoadedCallback);
    paramSlideViewInterface.a(paramlk.n(), paramlk.p());
  }
  
  private void presentImageThumbnail(SlideViewInterface paramSlideViewInterface, lk paramlk, long paramLong)
  {
    mItemLoadedFuture = paramlk.a(mImageLoadedCallback);
    paramSlideViewInterface.a(paramlk.n(), paramLong);
  }
  
  private void presentOtherThumbnail(SlideViewInterface paramSlideViewInterface)
  {
    paramSlideViewInterface.a("", null);
  }
  
  private void presentVideoThumbnail(SlideViewInterface paramSlideViewInterface, lw paramlw)
  {
    mItemLoadedFuture = paramlw.a(mImageLoadedCallback);
    paramSlideViewInterface.a(paramlw.n(), paramlw.p());
  }
  
  private void presentVideoThumbnail(SlideViewInterface paramSlideViewInterface, lw paramlw, long paramLong)
  {
    mItemLoadedFuture = paramlw.a(mImageLoadedCallback);
    paramSlideViewInterface.a(paramlw.n(), paramLong);
  }
  
  public void cancelBackgroundLoading()
  {
    lq locallq = ((lr)mModel).a(0);
    if (locallq == null) {}
    do
    {
      return;
      if (locallq.e()) {
        locallq.q().d();
      }
    } while (!locallq.g());
    locallq.s().b();
  }
  
  public void onModelChanged(Model paramModel, boolean paramBoolean) {}
  
  public void present(zy paramzy)
  {
    mOnLoadedCallback = paramzy;
    paramzy = ((lr)mModel).a(0);
    if (paramzy != null)
    {
      if (MmsApp.a) {
        presentFirstSlide((SlideViewInterface)mView, paramzy, ((lr)mModel).d());
      }
    }
    else {
      return;
    }
    presentFirstSlide((SlideViewInterface)mView, paramzy);
  }
  
  protected void presentAudioThumbnail(SlideViewInterface paramSlideViewInterface, le paramle)
  {
    paramSlideViewInterface.a(paramle.k(), paramle.n(), paramle.a());
  }
  
  protected void presentAudioThumbnail(SlideViewInterface paramSlideViewInterface, le paramle, long paramLong)
  {
    paramSlideViewInterface.a(paramle.k(), paramle.n(), paramle.a(), paramLong);
  }
  
  protected void presentFileThumbnail(SlideViewInterface paramSlideViewInterface, li paramli)
  {
    paramSlideViewInterface.a(paramli.k(), paramli.n(), paramli.a(), paramli.j());
  }
  
  protected void presentFileThumbnail(SlideViewInterface paramSlideViewInterface, li paramli, long paramLong)
  {
    paramSlideViewInterface.c(paramli.k(), paramli.n(), paramli.a(), paramLong);
  }
  
  protected void presentVcardThumbnail(SlideViewInterface paramSlideViewInterface, lv paramlv)
  {
    paramSlideViewInterface.b(paramlv.k(), paramlv.n(), paramlv.a());
  }
  
  protected void presentVcardThumbnail(SlideViewInterface paramSlideViewInterface, lv paramlv, long paramLong)
  {
    paramSlideViewInterface.b(paramlv.k(), paramlv.n(), paramlv.a(), paramLong);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MmsThumbnailPresenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */