package android.support.v4.app;

import android.graphics.Bitmap;

public class NotificationCompat$BigPictureStyle
  extends NotificationCompat.Style
{
  Bitmap mBigLargeIcon;
  boolean mBigLargeIconSet;
  Bitmap mPicture;
  
  public NotificationCompat$BigPictureStyle() {}
  
  public NotificationCompat$BigPictureStyle(NotificationCompat.Builder paramBuilder)
  {
    setBuilder(paramBuilder);
  }
  
  public BigPictureStyle bigLargeIcon(Bitmap paramBitmap)
  {
    mBigLargeIcon = paramBitmap;
    mBigLargeIconSet = true;
    return this;
  }
  
  public BigPictureStyle bigPicture(Bitmap paramBitmap)
  {
    mPicture = paramBitmap;
    return this;
  }
  
  public BigPictureStyle setBigContentTitle(CharSequence paramCharSequence)
  {
    mBigContentTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public BigPictureStyle setSummaryText(CharSequence paramCharSequence)
  {
    mSummaryText = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
    mSummaryTextSet = true;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.BigPictureStyle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */