package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlaybackStateCompat
  implements Parcelable
{
  public static final long ACTION_FAST_FORWARD = 64L;
  public static final long ACTION_PAUSE = 2L;
  public static final long ACTION_PLAY = 4L;
  public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
  public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
  public static final long ACTION_PLAY_PAUSE = 512L;
  public static final long ACTION_REWIND = 8L;
  public static final long ACTION_SEEK_TO = 256L;
  public static final long ACTION_SET_RATING = 128L;
  public static final long ACTION_SKIP_TO_NEXT = 32L;
  public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
  public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
  public static final long ACTION_STOP = 1L;
  public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new PlaybackStateCompat.1();
  public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
  public static final int STATE_BUFFERING = 6;
  public static final int STATE_CONNECTING = 8;
  public static final int STATE_ERROR = 7;
  public static final int STATE_FAST_FORWARDING = 4;
  public static final int STATE_NONE = 0;
  public static final int STATE_PAUSED = 2;
  public static final int STATE_PLAYING = 3;
  public static final int STATE_REWINDING = 5;
  public static final int STATE_SKIPPING_TO_NEXT = 10;
  public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
  public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
  public static final int STATE_STOPPED = 1;
  private final long mActions;
  private final long mActiveItemId;
  private final long mBufferedPosition;
  private List<CustomAction> mCustomActions;
  private final CharSequence mErrorMessage;
  private final Bundle mExtras;
  private final long mPosition;
  private final float mSpeed;
  private final int mState;
  private Object mStateObj;
  private final long mUpdateTime;
  
  private PlaybackStateCompat(int paramInt, long paramLong1, long paramLong2, float paramFloat, long paramLong3, CharSequence paramCharSequence, long paramLong4, List<CustomAction> paramList, long paramLong5, Bundle paramBundle)
  {
    mState = paramInt;
    mPosition = paramLong1;
    mBufferedPosition = paramLong2;
    mSpeed = paramFloat;
    mActions = paramLong3;
    mErrorMessage = paramCharSequence;
    mUpdateTime = paramLong4;
    mCustomActions = new ArrayList(paramList);
    mActiveItemId = paramLong5;
    mExtras = paramBundle;
  }
  
  private PlaybackStateCompat(Parcel paramParcel)
  {
    mState = paramParcel.readInt();
    mPosition = paramParcel.readLong();
    mSpeed = paramParcel.readFloat();
    mUpdateTime = paramParcel.readLong();
    mBufferedPosition = paramParcel.readLong();
    mActions = paramParcel.readLong();
    mErrorMessage = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
    mCustomActions = paramParcel.createTypedArrayList(CustomAction.CREATOR);
    mActiveItemId = paramParcel.readLong();
    mExtras = paramParcel.readBundle();
  }
  
  public static PlaybackStateCompat fromPlaybackState(Object paramObject)
  {
    if ((paramObject == null) || (Build.VERSION.SDK_INT < 21)) {
      return null;
    }
    Object localObject3 = PlaybackStateCompatApi21.getCustomActions(paramObject);
    Object localObject1 = null;
    if (localObject3 != null)
    {
      localObject2 = new ArrayList(((List)localObject3).size());
      localObject3 = ((List)localObject3).iterator();
      for (;;)
      {
        localObject1 = localObject2;
        if (!((Iterator)localObject3).hasNext()) {
          break;
        }
        ((List)localObject2).add(CustomAction.fromCustomAction(((Iterator)localObject3).next()));
      }
    }
    if (Build.VERSION.SDK_INT >= 22) {}
    for (Object localObject2 = PlaybackStateCompatApi22.getExtras(paramObject);; localObject2 = null)
    {
      localObject1 = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(paramObject), PlaybackStateCompatApi21.getPosition(paramObject), PlaybackStateCompatApi21.getBufferedPosition(paramObject), PlaybackStateCompatApi21.getPlaybackSpeed(paramObject), PlaybackStateCompatApi21.getActions(paramObject), PlaybackStateCompatApi21.getErrorMessage(paramObject), PlaybackStateCompatApi21.getLastPositionUpdateTime(paramObject), (List)localObject1, PlaybackStateCompatApi21.getActiveQueueItemId(paramObject), (Bundle)localObject2);
      mStateObj = paramObject;
      return (PlaybackStateCompat)localObject1;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public long getActions()
  {
    return mActions;
  }
  
  public long getActiveQueueItemId()
  {
    return mActiveItemId;
  }
  
  public long getBufferedPosition()
  {
    return mBufferedPosition;
  }
  
  public List<CustomAction> getCustomActions()
  {
    return mCustomActions;
  }
  
  public CharSequence getErrorMessage()
  {
    return mErrorMessage;
  }
  
  @Nullable
  public Bundle getExtras()
  {
    return mExtras;
  }
  
  public long getLastPositionUpdateTime()
  {
    return mUpdateTime;
  }
  
  public float getPlaybackSpeed()
  {
    return mSpeed;
  }
  
  public Object getPlaybackState()
  {
    if ((mStateObj != null) || (Build.VERSION.SDK_INT < 21)) {
      return mStateObj;
    }
    Object localObject = null;
    if (mCustomActions != null)
    {
      ArrayList localArrayList = new ArrayList(mCustomActions.size());
      Iterator localIterator = mCustomActions.iterator();
      for (;;)
      {
        localObject = localArrayList;
        if (!localIterator.hasNext()) {
          break;
        }
        localArrayList.add(((CustomAction)localIterator.next()).getCustomAction());
      }
    }
    if (Build.VERSION.SDK_INT >= 22) {}
    for (mStateObj = PlaybackStateCompatApi22.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime, (List)localObject, mActiveItemId, mExtras);; mStateObj = PlaybackStateCompatApi21.newInstance(mState, mPosition, mBufferedPosition, mSpeed, mActions, mErrorMessage, mUpdateTime, (List)localObject, mActiveItemId)) {
      return mStateObj;
    }
  }
  
  public long getPosition()
  {
    return mPosition;
  }
  
  public int getState()
  {
    return mState;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("PlaybackState {");
    localStringBuilder.append("state=").append(mState);
    localStringBuilder.append(", position=").append(mPosition);
    localStringBuilder.append(", buffered position=").append(mBufferedPosition);
    localStringBuilder.append(", speed=").append(mSpeed);
    localStringBuilder.append(", updated=").append(mUpdateTime);
    localStringBuilder.append(", actions=").append(mActions);
    localStringBuilder.append(", error=").append(mErrorMessage);
    localStringBuilder.append(", custom actions=").append(mCustomActions);
    localStringBuilder.append(", active item id=").append(mActiveItemId);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(mState);
    paramParcel.writeLong(mPosition);
    paramParcel.writeFloat(mSpeed);
    paramParcel.writeLong(mUpdateTime);
    paramParcel.writeLong(mBufferedPosition);
    paramParcel.writeLong(mActions);
    TextUtils.writeToParcel(mErrorMessage, paramParcel, paramInt);
    paramParcel.writeTypedList(mCustomActions);
    paramParcel.writeLong(mActiveItemId);
    paramParcel.writeBundle(mExtras);
  }
  
  public static final class Builder
  {
    private long mActions;
    private long mActiveItemId = -1L;
    private long mBufferedPosition;
    private final List<PlaybackStateCompat.CustomAction> mCustomActions = new ArrayList();
    private CharSequence mErrorMessage;
    private Bundle mExtras;
    private long mPosition;
    private float mRate;
    private int mState;
    private long mUpdateTime;
    
    public Builder() {}
    
    public Builder(PlaybackStateCompat paramPlaybackStateCompat)
    {
      mState = mState;
      mPosition = mPosition;
      mRate = mSpeed;
      mUpdateTime = mUpdateTime;
      mBufferedPosition = mBufferedPosition;
      mActions = mActions;
      mErrorMessage = mErrorMessage;
      if (mCustomActions != null) {
        mCustomActions.addAll(mCustomActions);
      }
      mActiveItemId = mActiveItemId;
      mExtras = mExtras;
    }
    
    public Builder addCustomAction(PlaybackStateCompat.CustomAction paramCustomAction)
    {
      if (paramCustomAction == null) {
        throw new IllegalArgumentException("You may not add a null CustomAction to PlaybackStateCompat.");
      }
      mCustomActions.add(paramCustomAction);
      return this;
    }
    
    public Builder addCustomAction(String paramString1, String paramString2, int paramInt)
    {
      return addCustomAction(new PlaybackStateCompat.CustomAction(paramString1, paramString2, paramInt, null, null));
    }
    
    public PlaybackStateCompat build()
    {
      return new PlaybackStateCompat(mState, mPosition, mBufferedPosition, mRate, mActions, mErrorMessage, mUpdateTime, mCustomActions, mActiveItemId, mExtras, null);
    }
    
    public Builder setActions(long paramLong)
    {
      mActions = paramLong;
      return this;
    }
    
    public Builder setActiveQueueItemId(long paramLong)
    {
      mActiveItemId = paramLong;
      return this;
    }
    
    public Builder setBufferedPosition(long paramLong)
    {
      mBufferedPosition = paramLong;
      return this;
    }
    
    public Builder setErrorMessage(CharSequence paramCharSequence)
    {
      mErrorMessage = paramCharSequence;
      return this;
    }
    
    public Builder setExtras(Bundle paramBundle)
    {
      mExtras = paramBundle;
      return this;
    }
    
    public Builder setState(int paramInt, long paramLong, float paramFloat)
    {
      return setState(paramInt, paramLong, paramFloat, SystemClock.elapsedRealtime());
    }
    
    public Builder setState(int paramInt, long paramLong1, float paramFloat, long paramLong2)
    {
      mState = paramInt;
      mPosition = paramLong1;
      mUpdateTime = paramLong2;
      mRate = paramFloat;
      return this;
    }
  }
  
  public static final class CustomAction
    implements Parcelable
  {
    public static final Parcelable.Creator<CustomAction> CREATOR = new PlaybackStateCompat.CustomAction.1();
    private final String mAction;
    private Object mCustomActionObj;
    private final Bundle mExtras;
    private final int mIcon;
    private final CharSequence mName;
    
    private CustomAction(Parcel paramParcel)
    {
      mAction = paramParcel.readString();
      mName = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
      mIcon = paramParcel.readInt();
      mExtras = paramParcel.readBundle();
    }
    
    private CustomAction(String paramString, CharSequence paramCharSequence, int paramInt, Bundle paramBundle)
    {
      mAction = paramString;
      mName = paramCharSequence;
      mIcon = paramInt;
      mExtras = paramBundle;
    }
    
    public static CustomAction fromCustomAction(Object paramObject)
    {
      if ((paramObject == null) || (Build.VERSION.SDK_INT < 21)) {
        return null;
      }
      CustomAction localCustomAction = new CustomAction(PlaybackStateCompatApi21.CustomAction.getAction(paramObject), PlaybackStateCompatApi21.CustomAction.getName(paramObject), PlaybackStateCompatApi21.CustomAction.getIcon(paramObject), PlaybackStateCompatApi21.CustomAction.getExtras(paramObject));
      mCustomActionObj = paramObject;
      return localCustomAction;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public String getAction()
    {
      return mAction;
    }
    
    public Object getCustomAction()
    {
      if ((mCustomActionObj != null) || (Build.VERSION.SDK_INT < 21)) {
        return mCustomActionObj;
      }
      mCustomActionObj = PlaybackStateCompatApi21.CustomAction.newInstance(mAction, mName, mIcon, mExtras);
      return mCustomActionObj;
    }
    
    public Bundle getExtras()
    {
      return mExtras;
    }
    
    public int getIcon()
    {
      return mIcon;
    }
    
    public CharSequence getName()
    {
      return mName;
    }
    
    public String toString()
    {
      return "Action:mName='" + mName + ", mIcon=" + mIcon + ", mExtras=" + mExtras;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeString(mAction);
      TextUtils.writeToParcel(mName, paramParcel, paramInt);
      paramParcel.writeInt(mIcon);
      paramParcel.writeBundle(mExtras);
    }
    
    public static final class Builder
    {
      private final String mAction;
      private Bundle mExtras;
      private final int mIcon;
      private final CharSequence mName;
      
      public Builder(String paramString, CharSequence paramCharSequence, int paramInt)
      {
        if (TextUtils.isEmpty(paramString)) {
          throw new IllegalArgumentException("You must specify an action to build a CustomAction.");
        }
        if (TextUtils.isEmpty(paramCharSequence)) {
          throw new IllegalArgumentException("You must specify a name to build a CustomAction.");
        }
        if (paramInt == 0) {
          throw new IllegalArgumentException("You must specify an icon resource id to build a CustomAction.");
        }
        mAction = paramString;
        mName = paramCharSequence;
        mIcon = paramInt;
      }
      
      public PlaybackStateCompat.CustomAction build()
      {
        return new PlaybackStateCompat.CustomAction(mAction, mName, mIcon, mExtras, null);
      }
      
      public Builder setExtras(Bundle paramBundle)
      {
        mExtras = paramBundle;
        return this;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.PlaybackStateCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */