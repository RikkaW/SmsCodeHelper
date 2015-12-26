package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NotificationCompat$WearableExtender
  implements NotificationCompat.Extender
{
  private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
  private static final int DEFAULT_FLAGS = 1;
  private static final int DEFAULT_GRAVITY = 80;
  private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
  private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
  private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
  private static final int FLAG_HINT_HIDE_ICON = 2;
  private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
  private static final int FLAG_START_SCROLL_BOTTOM = 8;
  private static final String KEY_ACTIONS = "actions";
  private static final String KEY_BACKGROUND = "background";
  private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
  private static final String KEY_CONTENT_ICON = "contentIcon";
  private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
  private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
  private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
  private static final String KEY_DISPLAY_INTENT = "displayIntent";
  private static final String KEY_FLAGS = "flags";
  private static final String KEY_GRAVITY = "gravity";
  private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
  private static final String KEY_PAGES = "pages";
  public static final int SCREEN_TIMEOUT_LONG = -1;
  public static final int SCREEN_TIMEOUT_SHORT = 0;
  public static final int SIZE_DEFAULT = 0;
  public static final int SIZE_FULL_SCREEN = 5;
  public static final int SIZE_LARGE = 4;
  public static final int SIZE_MEDIUM = 3;
  public static final int SIZE_SMALL = 2;
  public static final int SIZE_XSMALL = 1;
  public static final int UNSET_ACTION_INDEX = -1;
  private ArrayList<NotificationCompat.Action> mActions = new ArrayList();
  private Bitmap mBackground;
  private int mContentActionIndex = -1;
  private int mContentIcon;
  private int mContentIconGravity = 8388613;
  private int mCustomContentHeight;
  private int mCustomSizePreset = 0;
  private PendingIntent mDisplayIntent;
  private int mFlags = 1;
  private int mGravity = 80;
  private int mHintScreenTimeout;
  private ArrayList<Notification> mPages = new ArrayList();
  
  public NotificationCompat$WearableExtender() {}
  
  public NotificationCompat$WearableExtender(Notification paramNotification)
  {
    paramNotification = NotificationCompat.getExtras(paramNotification);
    if (paramNotification != null) {}
    for (paramNotification = paramNotification.getBundle("android.wearable.EXTENSIONS");; paramNotification = null)
    {
      if (paramNotification != null)
      {
        Object localObject = NotificationCompat.access$200().getActionsFromParcelableArrayList(paramNotification.getParcelableArrayList("actions"));
        if (localObject != null) {
          Collections.addAll(mActions, (Object[])localObject);
        }
        mFlags = paramNotification.getInt("flags", 1);
        mDisplayIntent = ((PendingIntent)paramNotification.getParcelable("displayIntent"));
        localObject = NotificationCompat.access$500(paramNotification, "pages");
        if (localObject != null) {
          Collections.addAll(mPages, (Object[])localObject);
        }
        mBackground = ((Bitmap)paramNotification.getParcelable("background"));
        mContentIcon = paramNotification.getInt("contentIcon");
        mContentIconGravity = paramNotification.getInt("contentIconGravity", 8388613);
        mContentActionIndex = paramNotification.getInt("contentActionIndex", -1);
        mCustomSizePreset = paramNotification.getInt("customSizePreset", 0);
        mCustomContentHeight = paramNotification.getInt("customContentHeight");
        mGravity = paramNotification.getInt("gravity", 80);
        mHintScreenTimeout = paramNotification.getInt("hintScreenTimeout");
      }
      return;
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
  
  public WearableExtender addAction(NotificationCompat.Action paramAction)
  {
    mActions.add(paramAction);
    return this;
  }
  
  public WearableExtender addActions(List<NotificationCompat.Action> paramList)
  {
    mActions.addAll(paramList);
    return this;
  }
  
  public WearableExtender addPage(Notification paramNotification)
  {
    mPages.add(paramNotification);
    return this;
  }
  
  public WearableExtender addPages(List<Notification> paramList)
  {
    mPages.addAll(paramList);
    return this;
  }
  
  public WearableExtender clearActions()
  {
    mActions.clear();
    return this;
  }
  
  public WearableExtender clearPages()
  {
    mPages.clear();
    return this;
  }
  
  public WearableExtender clone()
  {
    WearableExtender localWearableExtender = new WearableExtender();
    mActions = new ArrayList(mActions);
    mFlags = mFlags;
    mDisplayIntent = mDisplayIntent;
    mPages = new ArrayList(mPages);
    mBackground = mBackground;
    mContentIcon = mContentIcon;
    mContentIconGravity = mContentIconGravity;
    mContentActionIndex = mContentActionIndex;
    mCustomSizePreset = mCustomSizePreset;
    mCustomContentHeight = mCustomContentHeight;
    mGravity = mGravity;
    mHintScreenTimeout = mHintScreenTimeout;
    return localWearableExtender;
  }
  
  public NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder)
  {
    Bundle localBundle = new Bundle();
    if (!mActions.isEmpty()) {
      localBundle.putParcelableArrayList("actions", NotificationCompat.access$200().getParcelableArrayListForActions((NotificationCompat.Action[])mActions.toArray(new NotificationCompat.Action[mActions.size()])));
    }
    if (mFlags != 1) {
      localBundle.putInt("flags", mFlags);
    }
    if (mDisplayIntent != null) {
      localBundle.putParcelable("displayIntent", mDisplayIntent);
    }
    if (!mPages.isEmpty()) {
      localBundle.putParcelableArray("pages", (Parcelable[])mPages.toArray(new Notification[mPages.size()]));
    }
    if (mBackground != null) {
      localBundle.putParcelable("background", mBackground);
    }
    if (mContentIcon != 0) {
      localBundle.putInt("contentIcon", mContentIcon);
    }
    if (mContentIconGravity != 8388613) {
      localBundle.putInt("contentIconGravity", mContentIconGravity);
    }
    if (mContentActionIndex != -1) {
      localBundle.putInt("contentActionIndex", mContentActionIndex);
    }
    if (mCustomSizePreset != 0) {
      localBundle.putInt("customSizePreset", mCustomSizePreset);
    }
    if (mCustomContentHeight != 0) {
      localBundle.putInt("customContentHeight", mCustomContentHeight);
    }
    if (mGravity != 80) {
      localBundle.putInt("gravity", mGravity);
    }
    if (mHintScreenTimeout != 0) {
      localBundle.putInt("hintScreenTimeout", mHintScreenTimeout);
    }
    paramBuilder.getExtras().putBundle("android.wearable.EXTENSIONS", localBundle);
    return paramBuilder;
  }
  
  public List<NotificationCompat.Action> getActions()
  {
    return mActions;
  }
  
  public Bitmap getBackground()
  {
    return mBackground;
  }
  
  public int getContentAction()
  {
    return mContentActionIndex;
  }
  
  public int getContentIcon()
  {
    return mContentIcon;
  }
  
  public int getContentIconGravity()
  {
    return mContentIconGravity;
  }
  
  public boolean getContentIntentAvailableOffline()
  {
    return (mFlags & 0x1) != 0;
  }
  
  public int getCustomContentHeight()
  {
    return mCustomContentHeight;
  }
  
  public int getCustomSizePreset()
  {
    return mCustomSizePreset;
  }
  
  public PendingIntent getDisplayIntent()
  {
    return mDisplayIntent;
  }
  
  public int getGravity()
  {
    return mGravity;
  }
  
  public boolean getHintAvoidBackgroundClipping()
  {
    return (mFlags & 0x10) != 0;
  }
  
  public boolean getHintHideIcon()
  {
    return (mFlags & 0x2) != 0;
  }
  
  public int getHintScreenTimeout()
  {
    return mHintScreenTimeout;
  }
  
  public boolean getHintShowBackgroundOnly()
  {
    return (mFlags & 0x4) != 0;
  }
  
  public List<Notification> getPages()
  {
    return mPages;
  }
  
  public boolean getStartScrollBottom()
  {
    return (mFlags & 0x8) != 0;
  }
  
  public WearableExtender setBackground(Bitmap paramBitmap)
  {
    mBackground = paramBitmap;
    return this;
  }
  
  public WearableExtender setContentAction(int paramInt)
  {
    mContentActionIndex = paramInt;
    return this;
  }
  
  public WearableExtender setContentIcon(int paramInt)
  {
    mContentIcon = paramInt;
    return this;
  }
  
  public WearableExtender setContentIconGravity(int paramInt)
  {
    mContentIconGravity = paramInt;
    return this;
  }
  
  public WearableExtender setContentIntentAvailableOffline(boolean paramBoolean)
  {
    setFlag(1, paramBoolean);
    return this;
  }
  
  public WearableExtender setCustomContentHeight(int paramInt)
  {
    mCustomContentHeight = paramInt;
    return this;
  }
  
  public WearableExtender setCustomSizePreset(int paramInt)
  {
    mCustomSizePreset = paramInt;
    return this;
  }
  
  public WearableExtender setDisplayIntent(PendingIntent paramPendingIntent)
  {
    mDisplayIntent = paramPendingIntent;
    return this;
  }
  
  public WearableExtender setGravity(int paramInt)
  {
    mGravity = paramInt;
    return this;
  }
  
  public WearableExtender setHintAvoidBackgroundClipping(boolean paramBoolean)
  {
    setFlag(16, paramBoolean);
    return this;
  }
  
  public WearableExtender setHintHideIcon(boolean paramBoolean)
  {
    setFlag(2, paramBoolean);
    return this;
  }
  
  public WearableExtender setHintScreenTimeout(int paramInt)
  {
    mHintScreenTimeout = paramInt;
    return this;
  }
  
  public WearableExtender setHintShowBackgroundOnly(boolean paramBoolean)
  {
    setFlag(4, paramBoolean);
    return this;
  }
  
  public WearableExtender setStartScrollBottom(boolean paramBoolean)
  {
    setFlag(8, paramBoolean);
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.WearableExtender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */