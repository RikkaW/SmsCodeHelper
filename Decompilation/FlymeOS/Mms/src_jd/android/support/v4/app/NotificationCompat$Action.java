package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import java.util.ArrayList;

public class NotificationCompat$Action
  extends NotificationCompatBase.Action
{
  public static final NotificationCompatBase.Action.Factory FACTORY = new NotificationCompat.Action.1();
  public PendingIntent actionIntent;
  public int icon;
  private final Bundle mExtras;
  private final RemoteInput[] mRemoteInputs;
  public CharSequence title;
  
  public NotificationCompat$Action(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
  {
    this(paramInt, paramCharSequence, paramPendingIntent, new Bundle(), null);
  }
  
  private NotificationCompat$Action(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput)
  {
    icon = paramInt;
    title = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
    actionIntent = paramPendingIntent;
    if (paramBundle != null) {}
    for (;;)
    {
      mExtras = paramBundle;
      mRemoteInputs = paramArrayOfRemoteInput;
      return;
      paramBundle = new Bundle();
    }
  }
  
  public PendingIntent getActionIntent()
  {
    return actionIntent;
  }
  
  public Bundle getExtras()
  {
    return mExtras;
  }
  
  public int getIcon()
  {
    return icon;
  }
  
  public RemoteInput[] getRemoteInputs()
  {
    return mRemoteInputs;
  }
  
  public CharSequence getTitle()
  {
    return title;
  }
  
  public static final class Builder
  {
    private final Bundle mExtras;
    private final int mIcon;
    private final PendingIntent mIntent;
    private ArrayList<RemoteInput> mRemoteInputs;
    private final CharSequence mTitle;
    
    public Builder(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
    {
      this(paramInt, paramCharSequence, paramPendingIntent, new Bundle());
    }
    
    private Builder(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle)
    {
      mIcon = paramInt;
      mTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
      mIntent = paramPendingIntent;
      mExtras = paramBundle;
    }
    
    public Builder(NotificationCompat.Action paramAction)
    {
      this(icon, title, actionIntent, new Bundle(mExtras));
    }
    
    public Builder addExtras(Bundle paramBundle)
    {
      if (paramBundle != null) {
        mExtras.putAll(paramBundle);
      }
      return this;
    }
    
    public Builder addRemoteInput(RemoteInput paramRemoteInput)
    {
      if (mRemoteInputs == null) {
        mRemoteInputs = new ArrayList();
      }
      mRemoteInputs.add(paramRemoteInput);
      return this;
    }
    
    public NotificationCompat.Action build()
    {
      if (mRemoteInputs != null) {}
      for (RemoteInput[] arrayOfRemoteInput = (RemoteInput[])mRemoteInputs.toArray(new RemoteInput[mRemoteInputs.size()]);; arrayOfRemoteInput = null) {
        return new NotificationCompat.Action(mIcon, mTitle, mIntent, mExtras, arrayOfRemoteInput, null);
      }
    }
    
    public Builder extend(NotificationCompat.Action.Extender paramExtender)
    {
      paramExtender.extend(this);
      return this;
    }
    
    public Bundle getExtras()
    {
      return mExtras;
    }
  }
  
  public static abstract interface Extender
  {
    public abstract NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder paramBuilder);
  }
  
  public static final class WearableExtender
    implements NotificationCompat.Action.Extender
  {
    private static final int DEFAULT_FLAGS = 1;
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    private static final String KEY_FLAGS = "flags";
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    private CharSequence mCancelLabel;
    private CharSequence mConfirmLabel;
    private int mFlags = 1;
    private CharSequence mInProgressLabel;
    
    public WearableExtender() {}
    
    public WearableExtender(NotificationCompat.Action paramAction)
    {
      paramAction = paramAction.getExtras().getBundle("android.wearable.EXTENSIONS");
      if (paramAction != null)
      {
        mFlags = paramAction.getInt("flags", 1);
        mInProgressLabel = paramAction.getCharSequence("inProgressLabel");
        mConfirmLabel = paramAction.getCharSequence("confirmLabel");
        mCancelLabel = paramAction.getCharSequence("cancelLabel");
      }
    }
    
    private void setFlag(int paramInt, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        mFlags |= paramInt;
        return;
      }
      mFlags &= (paramInt ^ 0xFFFFFFFF);
    }
    
    public WearableExtender clone()
    {
      WearableExtender localWearableExtender = new WearableExtender();
      mFlags = mFlags;
      mInProgressLabel = mInProgressLabel;
      mConfirmLabel = mConfirmLabel;
      mCancelLabel = mCancelLabel;
      return localWearableExtender;
    }
    
    public NotificationCompat.Action.Builder extend(NotificationCompat.Action.Builder paramBuilder)
    {
      Bundle localBundle = new Bundle();
      if (mFlags != 1) {
        localBundle.putInt("flags", mFlags);
      }
      if (mInProgressLabel != null) {
        localBundle.putCharSequence("inProgressLabel", mInProgressLabel);
      }
      if (mConfirmLabel != null) {
        localBundle.putCharSequence("confirmLabel", mConfirmLabel);
      }
      if (mCancelLabel != null) {
        localBundle.putCharSequence("cancelLabel", mCancelLabel);
      }
      paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", localBundle);
      return paramBuilder;
    }
    
    public CharSequence getCancelLabel()
    {
      return mCancelLabel;
    }
    
    public CharSequence getConfirmLabel()
    {
      return mConfirmLabel;
    }
    
    public CharSequence getInProgressLabel()
    {
      return mInProgressLabel;
    }
    
    public boolean isAvailableOffline()
    {
      return (mFlags & 0x1) != 0;
    }
    
    public WearableExtender setAvailableOffline(boolean paramBoolean)
    {
      setFlag(1, paramBoolean);
      return this;
    }
    
    public WearableExtender setCancelLabel(CharSequence paramCharSequence)
    {
      mCancelLabel = paramCharSequence;
      return this;
    }
    
    public WearableExtender setConfirmLabel(CharSequence paramCharSequence)
    {
      mConfirmLabel = paramCharSequence;
      return this;
    }
    
    public WearableExtender setInProgressLabel(CharSequence paramCharSequence)
    {
      mInProgressLabel = paramCharSequence;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.Action
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */