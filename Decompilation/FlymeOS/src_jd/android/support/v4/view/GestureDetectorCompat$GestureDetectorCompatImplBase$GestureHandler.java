package android.support.v4.view;

import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;

class GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler
  extends Handler
{
  GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(GestureDetectorCompat.GestureDetectorCompatImplBase paramGestureDetectorCompatImplBase) {}
  
  GestureDetectorCompat$GestureDetectorCompatImplBase$GestureHandler(GestureDetectorCompat.GestureDetectorCompatImplBase paramGestureDetectorCompatImplBase, Handler paramHandler)
  {
    super(paramHandler.getLooper());
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      throw new RuntimeException("Unknown message " + paramMessage);
    case 1: 
      GestureDetectorCompat.GestureDetectorCompatImplBase.access$100(this$0).onShowPress(GestureDetectorCompat.GestureDetectorCompatImplBase.access$000(this$0));
    }
    do
    {
      return;
      GestureDetectorCompat.GestureDetectorCompatImplBase.access$200(this$0);
      return;
    } while (GestureDetectorCompat.GestureDetectorCompatImplBase.access$300(this$0) == null);
    if (!GestureDetectorCompat.GestureDetectorCompatImplBase.access$400(this$0))
    {
      GestureDetectorCompat.GestureDetectorCompatImplBase.access$300(this$0).onSingleTapConfirmed(GestureDetectorCompat.GestureDetectorCompatImplBase.access$000(this$0));
      return;
    }
    GestureDetectorCompat.GestureDetectorCompatImplBase.access$502(this$0, true);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.GestureDetectorCompat.GestureDetectorCompatImplBase.GestureHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */