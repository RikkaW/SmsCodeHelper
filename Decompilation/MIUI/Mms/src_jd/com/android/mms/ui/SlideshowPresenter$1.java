package com.android.mms.ui;

import com.android.mms.model.LayoutModel;
import com.android.mms.model.SlideshowModel;

class SlideshowPresenter$1
  implements AdaptableSlideViewInterface.OnSizeChangedListener
{
  SlideshowPresenter$1(SlideshowPresenter paramSlideshowPresenter) {}
  
  public void onSizeChanged(int paramInt1, int paramInt2)
  {
    LayoutModel localLayoutModel = ((SlideshowModel)this$0.mModel).getLayout();
    this$0.mWidthTransformRatio = SlideshowPresenter.access$000(this$0, paramInt1, localLayoutModel.getLayoutWidth());
    this$0.mHeightTransformRatio = SlideshowPresenter.access$100(this$0, paramInt2, localLayoutModel.getLayoutHeight());
    if (this$0.mWidthTransformRatio > this$0.mHeightTransformRatio) {}
    for (float f = this$0.mWidthTransformRatio;; f = this$0.mHeightTransformRatio)
    {
      this$0.mWidthTransformRatio = f;
      this$0.mHeightTransformRatio = f;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowPresenter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */