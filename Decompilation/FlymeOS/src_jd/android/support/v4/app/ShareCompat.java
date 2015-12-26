package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;

public class ShareCompat
{
  public static final String EXTRA_CALLING_ACTIVITY = "android.support.v4.app.EXTRA_CALLING_ACTIVITY";
  public static final String EXTRA_CALLING_PACKAGE = "android.support.v4.app.EXTRA_CALLING_PACKAGE";
  private static ShareCompatImpl IMPL = new ShareCompatImplBase();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      IMPL = new ShareCompatImplJB();
      return;
    }
    if (Build.VERSION.SDK_INT >= 14)
    {
      IMPL = new ShareCompatImplICS();
      return;
    }
  }
  
  public static void configureMenuItem(Menu paramMenu, int paramInt, IntentBuilder paramIntentBuilder)
  {
    paramMenu = paramMenu.findItem(paramInt);
    if (paramMenu == null) {
      throw new IllegalArgumentException("Could not find menu item with id " + paramInt + " in the supplied menu");
    }
    configureMenuItem(paramMenu, paramIntentBuilder);
  }
  
  public static void configureMenuItem(MenuItem paramMenuItem, IntentBuilder paramIntentBuilder)
  {
    IMPL.configureMenuItem(paramMenuItem, paramIntentBuilder);
  }
  
  public static ComponentName getCallingActivity(Activity paramActivity)
  {
    ComponentName localComponentName2 = paramActivity.getCallingActivity();
    ComponentName localComponentName1 = localComponentName2;
    if (localComponentName2 == null) {
      localComponentName1 = (ComponentName)paramActivity.getIntent().getParcelableExtra("android.support.v4.app.EXTRA_CALLING_ACTIVITY");
    }
    return localComponentName1;
  }
  
  public static String getCallingPackage(Activity paramActivity)
  {
    String str2 = paramActivity.getCallingPackage();
    String str1 = str2;
    if (str2 == null) {
      str1 = paramActivity.getIntent().getStringExtra("android.support.v4.app.EXTRA_CALLING_PACKAGE");
    }
    return str1;
  }
  
  public static class IntentBuilder
  {
    private Activity mActivity;
    private ArrayList<String> mBccAddresses;
    private ArrayList<String> mCcAddresses;
    private CharSequence mChooserTitle;
    private Intent mIntent;
    private ArrayList<Uri> mStreams;
    private ArrayList<String> mToAddresses;
    
    private IntentBuilder(Activity paramActivity)
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
  
  public static class IntentReader
  {
    private static final String TAG = "IntentReader";
    private Activity mActivity;
    private ComponentName mCallingActivity;
    private String mCallingPackage;
    private Intent mIntent;
    private ArrayList<Uri> mStreams;
    
    private IntentReader(Activity paramActivity)
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
          return ShareCompat.IMPL.escapeHtml(localCharSequence);
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
  
  static abstract interface ShareCompatImpl
  {
    public abstract void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder);
    
    public abstract String escapeHtml(CharSequence paramCharSequence);
  }
  
  static class ShareCompatImplBase
    implements ShareCompat.ShareCompatImpl
  {
    private static void withinStyle(StringBuilder paramStringBuilder, CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      if (paramInt1 < paramInt2)
      {
        char c = paramCharSequence.charAt(paramInt1);
        if (c == '<') {
          paramStringBuilder.append("&lt;");
        }
        for (;;)
        {
          paramInt1 += 1;
          break;
          if (c == '>')
          {
            paramStringBuilder.append("&gt;");
          }
          else if (c == '&')
          {
            paramStringBuilder.append("&amp;");
          }
          else if ((c > '~') || (c < ' '))
          {
            paramStringBuilder.append("&#" + c + ";");
          }
          else if (c == ' ')
          {
            while ((paramInt1 + 1 < paramInt2) && (paramCharSequence.charAt(paramInt1 + 1) == ' '))
            {
              paramStringBuilder.append("&nbsp;");
              paramInt1 += 1;
            }
            paramStringBuilder.append(' ');
          }
          else
          {
            paramStringBuilder.append(c);
          }
        }
      }
    }
    
    public void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder)
    {
      paramMenuItem.setIntent(paramIntentBuilder.createChooserIntent());
    }
    
    public String escapeHtml(CharSequence paramCharSequence)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      withinStyle(localStringBuilder, paramCharSequence, 0, paramCharSequence.length());
      return localStringBuilder.toString();
    }
  }
  
  static class ShareCompatImplICS
    extends ShareCompat.ShareCompatImplBase
  {
    public void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder)
    {
      ShareCompatICS.configureMenuItem(paramMenuItem, paramIntentBuilder.getActivity(), paramIntentBuilder.getIntent());
      if (shouldAddChooserIntent(paramMenuItem)) {
        paramMenuItem.setIntent(paramIntentBuilder.createChooserIntent());
      }
    }
    
    boolean shouldAddChooserIntent(MenuItem paramMenuItem)
    {
      return !paramMenuItem.hasSubMenu();
    }
  }
  
  static class ShareCompatImplJB
    extends ShareCompat.ShareCompatImplICS
  {
    public String escapeHtml(CharSequence paramCharSequence)
    {
      return ShareCompatJB.escapeHtml(paramCharSequence);
    }
    
    boolean shouldAddChooserIntent(MenuItem paramMenuItem)
    {
      return false;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ShareCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */