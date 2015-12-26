package android.support.v4.print;

import android.graphics.Bitmap;
import android.net.Uri;

abstract interface PrintHelper$PrintHelperVersionImpl
{
  public abstract int getColorMode();
  
  public abstract int getOrientation();
  
  public abstract int getScaleMode();
  
  public abstract void printBitmap(String paramString, Bitmap paramBitmap, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback);
  
  public abstract void printBitmap(String paramString, Uri paramUri, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback);
  
  public abstract void setColorMode(int paramInt);
  
  public abstract void setOrientation(int paramInt);
  
  public abstract void setScaleMode(int paramInt);
}

/* Location:
 * Qualified Name:     android.support.v4.print.PrintHelper.PrintHelperVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */