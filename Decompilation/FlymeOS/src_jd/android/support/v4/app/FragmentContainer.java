package android.support.v4.app;

import android.support.annotation.Nullable;
import android.view.View;

abstract interface FragmentContainer
{
  @Nullable
  public abstract View findViewById(int paramInt);
  
  public abstract boolean hasView();
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentContainer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */