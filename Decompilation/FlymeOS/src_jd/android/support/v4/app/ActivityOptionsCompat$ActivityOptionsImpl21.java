package android.support.v4.app;

import android.os.Bundle;

class ActivityOptionsCompat$ActivityOptionsImpl21
  extends ActivityOptionsCompat
{
  private final ActivityOptionsCompat21 mImpl;
  
  ActivityOptionsCompat$ActivityOptionsImpl21(ActivityOptionsCompat21 paramActivityOptionsCompat21)
  {
    mImpl = paramActivityOptionsCompat21;
  }
  
  public Bundle toBundle()
  {
    return mImpl.toBundle();
  }
  
  public void update(ActivityOptionsCompat paramActivityOptionsCompat)
  {
    if ((paramActivityOptionsCompat instanceof ActivityOptionsImpl21))
    {
      paramActivityOptionsCompat = (ActivityOptionsImpl21)paramActivityOptionsCompat;
      mImpl.update(mImpl);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ActivityOptionsCompat.ActivityOptionsImpl21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */