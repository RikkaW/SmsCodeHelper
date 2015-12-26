package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.text.Html;
import java.util.ArrayList;

public class ShareCompat$IntentBuilder
{
  private Activity mActivity;
  private ArrayList<String> mBccAddresses;
  private ArrayList<String> mCcAddresses;
  private CharSequence mChooserTitle;
  private Intent mIntent;
  private ArrayList<Uri> mStreams;
  private ArrayList<String> mToAddresses;
  
  private ShareCompat$IntentBuilder(Activity paramActivity)
  {
    mActivity = paramActivity;
    mIntent = new Intent().setAction("android.intent.action.SEND");
    mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE", paramActivity.getPackageName());
    mIntent.putExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY", paramActivity.getComponentName());
    mIntent.addFlags(524288);
  }
  
  private void combineArrayExtra(String paramString, ArrayList<String> paramArrayList)
  {
    String[] arrayOfString1 = mIntent.getStringArrayExtra(paramString);
    if (arrayOfString1 != null) {}
    for (int i = arrayOfString1.length;; i = 0)
    {
      String[] arrayOfString2 = new String[paramArrayList.size() + i];
      paramArrayList.toArray(arrayOfString2);
      if (arrayOfString1 != null) {
        System.arraycopy(arrayOfString1, 0, arrayOfString2, paramArrayList.size(), i);
      }
      mIntent.putExtra(paramString, arrayOfString2);
      return;
    }
  }
  
  private void combineArrayExtra(String paramString, String[] paramArrayOfString)
  {
    Intent localIntent = getIntent();
    String[] arrayOfString1 = localIntent.getStringArrayExtra(paramString);
    if (arrayOfString1 != null) {}
    for (int i = arrayOfString1.length;; i = 0)
    {
      String[] arrayOfString2 = new String[paramArrayOfString.length + i];
      if (arrayOfString1 != null) {
        System.arraycopy(arrayOfString1, 0, arrayOfString2, 0, i);
      }
      System.arraycopy(paramArrayOfString, 0, arrayOfString2, i, paramArrayOfString.length);
      localIntent.putExtra(paramString, arrayOfString2);
      return;
    }
  }
  
  public static IntentBuilder from(Activity paramActivity)
  {
    return new IntentBuilder(paramActivity);
  }
  
  public IntentBuilder addEmailBcc(String paramString)
  {
    if (mBccAddresses == null) {
      mBccAddresses = new ArrayList();
    }
    mBccAddresses.add(paramString);
    return this;
  }
  
  public IntentBuilder addEmailBcc(String[] paramArrayOfString)
  {
    combineArrayExtra("android.intent.extra.BCC", paramArrayOfString);
    return this;
  }
  
  public IntentBuilder addEmailCc(String paramString)
  {
    if (mCcAddresses == null) {
      mCcAddresses = new ArrayList();
    }
    mCcAddresses.add(paramString);
    return this;
  }
  
  public IntentBuilder addEmailCc(String[] paramArrayOfString)
  {
    combineArrayExtra("android.intent.extra.CC", paramArrayOfString);
    return this;
  }
  
  public IntentBuilder addEmailTo(String paramString)
  {
    if (mToAddresses == null) {
      mToAddresses = new ArrayList();
    }
    mToAddresses.add(paramString);
    return this;
  }
  
  public IntentBuilder addEmailTo(String[] paramArrayOfString)
  {
    combineArrayExtra("android.intent.extra.EMAIL", paramArrayOfString);
    return this;
  }
  
  public IntentBuilder addStream(Uri paramUri)
  {
    Uri localUri = (Uri)mIntent.getParcelableExtra("android.intent.extra.STREAM");
    if (localUri == null) {
      return setStream(paramUri);
    }
    if (mStreams == null) {
      mStreams = new ArrayList();
    }
    if (localUri != null)
    {
      mIntent.removeExtra("android.intent.extra.STREAM");
      mStreams.add(localUri);
    }
    mStreams.add(paramUri);
    return this;
  }
  
  public Intent createChooserIntent()
  {
    return Intent.createChooser(getIntent(), mChooserTitle);
  }
  
  Activity getActivity()
  {
    return mActivity;
  }
  
  public Intent getIntent()
  {
    if (mToAddresses != null)
    {
      combineArrayExtra("android.intent.extra.EMAIL", mToAddresses);
      mToAddresses = null;
    }
    if (mCcAddresses != null)
    {
      combineArrayExtra("android.intent.extra.CC", mCcAddresses);
      mCcAddresses = null;
    }
    if (mBccAddresses != null)
    {
      combineArrayExtra("android.intent.extra.BCC", mBccAddresses);
      mBccAddresses = null;
    }
    int i;
    if ((mStreams != null) && (mStreams.size() > 1))
    {
      i = 1;
      boolean bool = mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
      if ((i == 0) && (bool))
      {
        mIntent.setAction("android.intent.action.SEND");
        if ((mStreams == null) || (mStreams.isEmpty())) {
          break label219;
        }
        mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)mStreams.get(0));
        label155:
        mStreams = null;
      }
      if ((i != 0) && (!bool))
      {
        mIntent.setAction("android.intent.action.SEND_MULTIPLE");
        if ((mStreams == null) || (mStreams.isEmpty())) {
          break label231;
        }
        mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", mStreams);
      }
    }
    for (;;)
    {
      return mIntent;
      i = 0;
      break;
      label219:
      mIntent.removeExtra("android.intent.extra.STREAM");
      break label155;
      label231:
      mIntent.removeExtra("android.intent.extra.STREAM");
    }
  }
  
  public IntentBuilder setChooserTitle(int paramInt)
  {
    return setChooserTitle(mActivity.getText(paramInt));
  }
  
  public IntentBuilder setChooserTitle(CharSequence paramCharSequence)
  {
    mChooserTitle = paramCharSequence;
    return this;
  }
  
  public IntentBuilder setEmailBcc(String[] paramArrayOfString)
  {
    mIntent.putExtra("android.intent.extra.BCC", paramArrayOfString);
    return this;
  }
  
  public IntentBuilder setEmailCc(String[] paramArrayOfString)
  {
    mIntent.putExtra("android.intent.extra.CC", paramArrayOfString);
    return this;
  }
  
  public IntentBuilder setEmailTo(String[] paramArrayOfString)
  {
    if (mToAddresses != null) {
      mToAddresses = null;
    }
    mIntent.putExtra("android.intent.extra.EMAIL", paramArrayOfString);
    return this;
  }
  
  public IntentBuilder setHtmlText(String paramString)
  {
    mIntent.putExtra("android.intent.extra.HTML_TEXT", paramString);
    if (!mIntent.hasExtra("android.intent.extra.TEXT")) {
      setText(Html.fromHtml(paramString));
    }
    return this;
  }
  
  public IntentBuilder setStream(Uri paramUri)
  {
    if (!mIntent.getAction().equals("android.intent.action.SEND")) {
      mIntent.setAction("android.intent.action.SEND");
    }
    mStreams = null;
    mIntent.putExtra("android.intent.extra.STREAM", paramUri);
    return this;
  }
  
  public IntentBuilder setSubject(String paramString)
  {
    mIntent.putExtra("android.intent.extra.SUBJECT", paramString);
    return this;
  }
  
  public IntentBuilder setText(CharSequence paramCharSequence)
  {
    mIntent.putExtra("android.intent.extra.TEXT", paramCharSequence);
    return this;
  }
  
  public IntentBuilder setType(String paramString)
  {
    mIntent.setType(paramString);
    return this;
  }
  
  public void startChooser()
  {
    mActivity.startActivity(createChooserIntent());
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ShareCompat.IntentBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */