package com.android.mms.ui;

import com.android.mms.dom.smil.SmilPlayer;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.SmilHelper;
import org.w3c.dom.events.EventTarget;

class SlideshowActivity$5
  implements Runnable
{
  SlideshowActivity$5(SlideshowActivity paramSlideshowActivity, SlideshowModel paramSlideshowModel) {}
  
  private boolean isRotating()
  {
    return (SlideshowActivity.access$400(this$0).isPausedState()) || (SlideshowActivity.access$400(this$0).isPlayingState()) || (SlideshowActivity.access$400(this$0).isPlayedState());
  }
  
  public void run()
  {
    SlideshowActivity.access$402(this$0, SmilPlayer.getPlayer());
    SlideshowActivity.access$500(this$0);
    SlideshowActivity.access$700(this$0).setMediaController(SlideshowActivity.access$600(this$0));
    SlideshowActivity.access$802(this$0, SmilHelper.getDocument(val$model));
    if (SlideshowActivity.access$900(SlideshowActivity.access$800(this$0)))
    {
      int k = 0;
      int i = 0;
      int n = 0;
      int j = 0;
      int i2 = 0;
      int i3 = 0;
      Object localObject = val$model.getLayout();
      int i1 = i2;
      int m = i3;
      if (localObject != null)
      {
        RegionModel localRegionModel = ((LayoutModel)localObject).getImageRegion();
        if (localRegionModel != null)
        {
          i = localRegionModel.getLeft();
          j = localRegionModel.getTop();
        }
        localObject = ((LayoutModel)localObject).getTextRegion();
        k = i;
        n = j;
        i1 = i2;
        m = i3;
        if (localObject != null)
        {
          i1 = ((RegionModel)localObject).getLeft();
          m = ((RegionModel)localObject).getTop();
          n = j;
          k = i;
        }
      }
      SlideshowActivity.access$700(this$0).enableMMSConformanceMode(i1, m, k, n);
    }
    ((EventTarget)SlideshowActivity.access$800(this$0)).addEventListener("SimlDocumentEnd", this$0, false);
    SlideshowActivity.access$400(this$0).init(SlideshowActivity.access$800(this$0));
    if (isRotating())
    {
      SlideshowActivity.access$400(this$0).reload();
      return;
    }
    SlideshowActivity.access$400(this$0).play();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */