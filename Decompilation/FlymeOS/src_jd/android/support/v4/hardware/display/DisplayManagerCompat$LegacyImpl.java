package android.support.v4.hardware.display;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

class DisplayManagerCompat$LegacyImpl
  extends DisplayManagerCompat
{
  private final WindowManager mWindowManager;
  
  public DisplayManagerCompat$LegacyImpl(Context paramContext)
  {
    mWindowManager = ((WindowManager)paramContext.getSystemService("window"));
  }
  
  public Display getDisplay(int paramInt)
  {
    Display localDisplay = mWindowManager.getDefaultDisplay();
    if (localDisplay.getDisplayId() == paramInt) {
      return localDisplay;
    }
    return null;
  }
  
  public Display[] getDisplays()
  {
    return new Display[] { mWindowManager.getDefaultDisplay() };
  }
  
  public Display[] getDisplays(String paramString)
  {
    if (paramString == null) {
      return getDisplays();
    }
    return new Display[0];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.hardware.display.DisplayManagerCompat.LegacyImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */