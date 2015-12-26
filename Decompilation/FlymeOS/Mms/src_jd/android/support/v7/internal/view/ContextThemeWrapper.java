package android.support.v7.internal.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.support.v7.appcompat.R.style;
import android.view.LayoutInflater;

public class ContextThemeWrapper
  extends ContextWrapper
{
  private LayoutInflater mInflater;
  private Resources.Theme mTheme;
  private int mThemeResource;
  
  public ContextThemeWrapper(Context paramContext, int paramInt)
  {
    super(paramContext);
    mThemeResource = paramInt;
  }
  
  private void initializeTheme()
  {
    if (mTheme == null) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool)
      {
        mTheme = getResources().newTheme();
        Resources.Theme localTheme = getBaseContext().getTheme();
        if (localTheme != null) {
          mTheme.setTo(localTheme);
        }
      }
      onApplyThemeResource(mTheme, mThemeResource, bool);
      return;
    }
  }
  
  public Object getSystemService(String paramString)
  {
    if ("layout_inflater".equals(paramString))
    {
      if (mInflater == null) {
        mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
      }
      return mInflater;
    }
    return getBaseContext().getSystemService(paramString);
  }
  
  public Resources.Theme getTheme()
  {
    if (mTheme != null) {
      return mTheme;
    }
    if (mThemeResource == 0) {
      mThemeResource = R.style.Theme_AppCompat_Light;
    }
    initializeTheme();
    return mTheme;
  }
  
  public int getThemeResId()
  {
    return mThemeResource;
  }
  
  protected void onApplyThemeResource(Resources.Theme paramTheme, int paramInt, boolean paramBoolean)
  {
    paramTheme.applyStyle(paramInt, true);
  }
  
  public void setTheme(int paramInt)
  {
    mThemeResource = paramInt;
    initializeTheme();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.ContextThemeWrapper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */