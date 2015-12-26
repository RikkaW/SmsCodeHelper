package android.support.v4.print;

import android.graphics.Bitmap;
import android.net.Uri;

final class PrintHelper$PrintHelperStubImpl
  implements PrintHelper.PrintHelperVersionImpl
{
  int mColorMode = 2;
  int mOrientation = 1;
  int mScaleMode = 2;
  
  public int getColorMode()
  {
    return mColorMode;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  public int getScaleMode()
  {
    return mScaleMode;
  }
  
  public void printBitmap(String paramString, Bitmap paramBitmap, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback) {}
  
  public void printBitmap(String paramString, Uri paramUri, PrintHelper.OnPrintFinishCallback paramOnPrintFinishCallback) {}
  
  public void setColorMode(int paramInt)
  {
    mColorMode = paramInt;
  }
  
  public void setOrientation(int paramInt)
  {
    mOrientation = paramInt;
  }
  
  public void setScaleMode(int paramInt)
  {
    mScaleMode = paramInt;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.print.PrintHelper.PrintHelperStubImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */