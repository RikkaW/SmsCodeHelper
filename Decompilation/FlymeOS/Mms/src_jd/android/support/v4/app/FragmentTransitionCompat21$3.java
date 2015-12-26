package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;

final class FragmentTransitionCompat21$3
  extends Transition.EpicenterCallback
{
  private Rect mEpicenter;
  
  FragmentTransitionCompat21$3(FragmentTransitionCompat21.EpicenterView paramEpicenterView) {}
  
  public Rect onGetEpicenter(Transition paramTransition)
  {
    if ((mEpicenter == null) && (val$epicenterView.epicenter != null)) {
      mEpicenter = FragmentTransitionCompat21.access$100(val$epicenterView.epicenter);
    }
    return mEpicenter;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentTransitionCompat21.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */