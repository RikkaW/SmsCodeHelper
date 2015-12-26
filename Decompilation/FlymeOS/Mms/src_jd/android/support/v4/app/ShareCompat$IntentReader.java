package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import java.util.ArrayList;

public class ShareCompat$IntentReader
{
  private static final String TAG = "IntentReader";
  private Activity mActivity;
  private ComponentName mCallingActivity;
  private String mCallingPackage;
  private Intent mIntent;
  private ArrayList<Uri> mStreams;
  
  private ShareCompat$IntentReader(Activity paramActivity)
  {
    mActivity = paramActivity;
    mIntent = paramActivity.getIntent();
    mCallingPackage = ShareCompat.getCallingPackage(paramActivity);
    mCallingActivity = ShareCompat.getCallingActivity(paramActivity);
  }
  
  public static IntentReader from(Activity paramActivity)
  {
    return new IntentReader(paramActivity);
  }
  
  public ComponentName getCallingActivity()
  {
    return mCallingActivity;
  }
  
  public Drawable getCallingActivityIcon()
  {
    if (mCallingActivity == null) {
      return null;
    }
    Object localObject = mActivity.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getActivityIcon(mCallingActivity);
      return (Drawable)localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("IntentReader", "Could not retrieve icon for calling activity", localNameNotFoundException);
    }
    return null;
  }
  
  public Drawable getCallingApplicationIcon()
  {
    if (mCallingPackage == null) {
      return null;
    }
    Object localObject = mActivity.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationIcon(mCallingPackage);
      return (Drawable)localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("IntentReader", "Could not retrieve icon for calling application", localNameNotFoundException);
    }
    return null;
  }
  
  public CharSequence getCallingApplicationLabel()
  {
    if (mCallingPackage == null) {
      return null;
    }
    Object localObject = mActivity.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getApplicationLabel(((PackageManager)localObject).getApplicationInfo(mCallingPackage, 0));
      return (CharSequence)localObject;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("IntentReader", "Could not retrieve label for calling application", localNameNotFoundException);
    }
    return null;
  }
  
  public String getCallingPackage()
  {
    return mCallingPackage;
  }
  
  public String[] getEmailBcc()
  {
    return mIntent.getStringArrayExtra("android.intent.extra.BCC");
  }
  
  public String[] getEmailCc()
  {
    return mIntent.getStringArrayExtra("android.intent.extra.CC");
  }
  
  public String[] getEmailTo()
  {
    return mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
  }
  
  public String getHtmlText()
  {
    String str = mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
    if (str == null)
    {
      CharSequence localCharSequence = getText();
      if ((localCharSequence instanceof Spanned)) {
        return Html.toHtml((Spanned)localCharSequence);
      }
      if (localCharSequence != null) {
        return ShareCompat.access$000().escapeHtml(localCharSequence);
      }
    }
    return str;
  }
  
  public Uri getStream()
  {
    return (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
  }
  
  public Uri getStream(int paramInt)
  {
    if ((mStreams == null) && (isMultipleShare())) {
      mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
    }
    if (mStreams != null) {
      return (Uri)mStreams.get(paramInt);
    }
    if (paramInt == 0) {
      return (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
    }
    throw new IndexOutOfBoundsException("Stream items available: " + getStreamCount() + " index requested: " + paramInt);
  }
  
  public int getStreamCount()
  {
    if ((mStreams == null) && (isMultipleShare())) {
      mStreams = mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
    }
    if (mStreams != null) {
      return mStreams.size();
    }
    if (mIntent.hasExtra("android.intent.extra.STREAM")) {
      return 1;
    }
    return 0;
  }
  
  public String getSubject()
  {
    return mIntent.getStringExtra("android.intent.extra.SUBJECT");
  }
  
  public CharSequence getText()
  {
    return mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
  }
  
  public String getType()
  {
    return mIntent.getType();
  }
  
  public boolean isMultipleShare()
  {
    return "android.intent.action.SEND_MULTIPLE".equals(mIntent.getAction());
  }
  
  public boolean isShareIntent()
  {
    String str = mIntent.getAction();
    return ("android.intent.action.SEND".equals(str)) || ("android.intent.action.SEND_MULTIPLE".equals(str));
  }
  
  public boolean isSingleShare()
  {
    return "android.intent.action.SEND".equals(mIntent.getAction());
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ShareCompat.IntentReader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */