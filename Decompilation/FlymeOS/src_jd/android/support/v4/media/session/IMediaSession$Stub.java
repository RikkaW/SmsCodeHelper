package android.support.v4.media.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class IMediaSession$Stub
  extends Binder
  implements IMediaSession
{
  private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaSession";
  static final int TRANSACTION_adjustVolume = 11;
  static final int TRANSACTION_fastForward = 21;
  static final int TRANSACTION_getExtras = 30;
  static final int TRANSACTION_getFlags = 9;
  static final int TRANSACTION_getLaunchPendingIntent = 8;
  static final int TRANSACTION_getMetadata = 26;
  static final int TRANSACTION_getPackageName = 6;
  static final int TRANSACTION_getPlaybackState = 27;
  static final int TRANSACTION_getQueue = 28;
  static final int TRANSACTION_getQueueTitle = 29;
  static final int TRANSACTION_getRatingType = 31;
  static final int TRANSACTION_getTag = 7;
  static final int TRANSACTION_getVolumeAttributes = 10;
  static final int TRANSACTION_isTransportControlEnabled = 5;
  static final int TRANSACTION_next = 19;
  static final int TRANSACTION_pause = 17;
  static final int TRANSACTION_play = 13;
  static final int TRANSACTION_playFromMediaId = 14;
  static final int TRANSACTION_playFromSearch = 15;
  static final int TRANSACTION_previous = 20;
  static final int TRANSACTION_rate = 24;
  static final int TRANSACTION_registerCallbackListener = 3;
  static final int TRANSACTION_rewind = 22;
  static final int TRANSACTION_seekTo = 23;
  static final int TRANSACTION_sendCommand = 1;
  static final int TRANSACTION_sendCustomAction = 25;
  static final int TRANSACTION_sendMediaButton = 2;
  static final int TRANSACTION_setVolumeTo = 12;
  static final int TRANSACTION_skipToQueueItem = 16;
  static final int TRANSACTION_stop = 18;
  static final int TRANSACTION_unregisterCallbackListener = 4;
  
  public IMediaSession$Stub()
  {
    attachInterface(this, "android.support.v4.media.session.IMediaSession");
  }
  
  public static IMediaSession asInterface(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
    if ((localIInterface != null) && ((localIInterface instanceof IMediaSession))) {
      return (IMediaSession)localIInterface;
    }
    return new Proxy(paramIBinder);
  }
  
  public IBinder asBinder()
  {
    return this;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    int i = 0;
    Object localObject;
    label364:
    boolean bool;
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("android.support.v4.media.session.IMediaSession");
      return true;
    case 1: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      String str = paramParcel1.readString();
      if (paramParcel1.readInt() != 0)
      {
        localObject = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label364;
        }
      }
      for (paramParcel1 = (MediaSessionCompat.ResultReceiverWrapper)MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        sendCommand(str, (Bundle)localObject, paramParcel1);
        paramParcel2.writeNoException();
        return true;
        localObject = null;
        break;
      }
    case 2: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      if (paramParcel1.readInt() != 0)
      {
        paramParcel1 = (KeyEvent)KeyEvent.CREATOR.createFromParcel(paramParcel1);
        bool = sendMediaButton(paramParcel1);
        paramParcel2.writeNoException();
        if (!bool) {
          break label425;
        }
      }
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        paramParcel2.writeInt(paramInt1);
        return true;
        paramParcel1 = null;
        break;
      }
    case 3: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      registerCallbackListener(IMediaControllerCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 4: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 5: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      bool = isTransportControlEnabled();
      paramParcel2.writeNoException();
      paramInt1 = i;
      if (bool) {
        paramInt1 = 1;
      }
      paramParcel2.writeInt(paramInt1);
      return true;
    case 6: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getPackageName();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 7: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getTag();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 8: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getLaunchPendingIntent();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 9: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      long l = getFlags();
      paramParcel2.writeNoException();
      paramParcel2.writeLong(l);
      return true;
    case 10: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getVolumeAttributes();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 11: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      adjustVolume(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 12: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      setVolumeTo(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    case 13: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      play();
      paramParcel2.writeNoException();
      return true;
    case 14: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      localObject = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        playFromMediaId((String)localObject, paramParcel1);
        paramParcel2.writeNoException();
        return true;
      }
    case 15: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      localObject = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        playFromSearch((String)localObject, paramParcel1);
        paramParcel2.writeNoException();
        return true;
      }
    case 16: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      skipToQueueItem(paramParcel1.readLong());
      paramParcel2.writeNoException();
      return true;
    case 17: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      pause();
      paramParcel2.writeNoException();
      return true;
    case 18: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      stop();
      paramParcel2.writeNoException();
      return true;
    case 19: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      next();
      paramParcel2.writeNoException();
      return true;
    case 20: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      previous();
      paramParcel2.writeNoException();
      return true;
    case 21: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      fastForward();
      paramParcel2.writeNoException();
      return true;
    case 22: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      rewind();
      paramParcel2.writeNoException();
      return true;
    case 23: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      seekTo(paramParcel1.readLong());
      paramParcel2.writeNoException();
      return true;
    case 24: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (RatingCompat)RatingCompat.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        rate(paramParcel1);
        paramParcel2.writeNoException();
        return true;
      }
    case 25: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      localObject = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        sendCustomAction((String)localObject, paramParcel1);
        paramParcel2.writeNoException();
        return true;
      }
    case 26: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getMetadata();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 27: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getPlaybackState();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 28: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getQueue();
      paramParcel2.writeNoException();
      paramParcel2.writeTypedList(paramParcel1);
      return true;
    case 29: 
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getQueueTitle();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        TextUtils.writeToParcel(paramParcel1, paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    case 30: 
      label425:
      paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
      paramParcel1 = getExtras();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    }
    paramParcel1.enforceInterface("android.support.v4.media.session.IMediaSession");
    paramInt1 = getRatingType();
    paramParcel2.writeNoException();
    paramParcel2.writeInt(paramInt1);
    return true;
  }
  
  static class Proxy
    implements IMediaSession
  {
    private IBinder mRemote;
    
    Proxy(IBinder paramIBinder)
    {
      mRemote = paramIBinder;
    }
    
    public void adjustVolume(int paramInt1, int paramInt2, String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        localParcel1.writeInt(paramInt1);
        localParcel1.writeInt(paramInt2);
        localParcel1.writeString(paramString);
        mRemote.transact(11, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public IBinder asBinder()
    {
      return mRemote;
    }
    
    public void fastForward()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(21, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public Bundle getExtras()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: bipush 30
      //   20: aload_2
      //   21: aload_3
      //   22: iconst_0
      //   23: invokeinterface 47 5 0
      //   28: pop
      //   29: aload_3
      //   30: invokevirtual 50	android/os/Parcel:readException	()V
      //   33: aload_3
      //   34: invokevirtual 62	android/os/Parcel:readInt	()I
      //   37: ifeq +26 -> 63
      //   40: getstatic 68	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
      //   43: aload_3
      //   44: invokeinterface 74 2 0
      //   49: checkcast 64	android/os/Bundle
      //   52: astore_1
      //   53: aload_3
      //   54: invokevirtual 53	android/os/Parcel:recycle	()V
      //   57: aload_2
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_1
      //   62: areturn
      //   63: aconst_null
      //   64: astore_1
      //   65: goto -12 -> 53
      //   68: astore_1
      //   69: aload_3
      //   70: invokevirtual 53	android/os/Parcel:recycle	()V
      //   73: aload_2
      //   74: invokevirtual 53	android/os/Parcel:recycle	()V
      //   77: aload_1
      //   78: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Proxy
      //   52	13	1	localBundle	Bundle
      //   68	10	1	localObject	Object
      //   3	71	2	localParcel1	Parcel
      //   7	63	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	53	68	finally
    }
    
    public long getFlags()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(9, localParcel1, localParcel2, 0);
        localParcel2.readException();
        long l = localParcel2.readLong();
        return l;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public String getInterfaceDescriptor()
    {
      return "android.support.v4.media.session.IMediaSession";
    }
    
    /* Error */
    public PendingIntent getLaunchPendingIntent()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: bipush 8
      //   20: aload_2
      //   21: aload_3
      //   22: iconst_0
      //   23: invokeinterface 47 5 0
      //   28: pop
      //   29: aload_3
      //   30: invokevirtual 50	android/os/Parcel:readException	()V
      //   33: aload_3
      //   34: invokevirtual 62	android/os/Parcel:readInt	()I
      //   37: ifeq +26 -> 63
      //   40: getstatic 86	android/app/PendingIntent:CREATOR	Landroid/os/Parcelable$Creator;
      //   43: aload_3
      //   44: invokeinterface 74 2 0
      //   49: checkcast 85	android/app/PendingIntent
      //   52: astore_1
      //   53: aload_3
      //   54: invokevirtual 53	android/os/Parcel:recycle	()V
      //   57: aload_2
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_1
      //   62: areturn
      //   63: aconst_null
      //   64: astore_1
      //   65: goto -12 -> 53
      //   68: astore_1
      //   69: aload_3
      //   70: invokevirtual 53	android/os/Parcel:recycle	()V
      //   73: aload_2
      //   74: invokevirtual 53	android/os/Parcel:recycle	()V
      //   77: aload_1
      //   78: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Proxy
      //   52	13	1	localPendingIntent	PendingIntent
      //   68	10	1	localObject	Object
      //   3	71	2	localParcel1	Parcel
      //   7	63	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	53	68	finally
    }
    
    /* Error */
    public MediaMetadataCompat getMetadata()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: bipush 26
      //   20: aload_2
      //   21: aload_3
      //   22: iconst_0
      //   23: invokeinterface 47 5 0
      //   28: pop
      //   29: aload_3
      //   30: invokevirtual 50	android/os/Parcel:readException	()V
      //   33: aload_3
      //   34: invokevirtual 62	android/os/Parcel:readInt	()I
      //   37: ifeq +26 -> 63
      //   40: getstatic 91	android/support/v4/media/MediaMetadataCompat:CREATOR	Landroid/os/Parcelable$Creator;
      //   43: aload_3
      //   44: invokeinterface 74 2 0
      //   49: checkcast 90	android/support/v4/media/MediaMetadataCompat
      //   52: astore_1
      //   53: aload_3
      //   54: invokevirtual 53	android/os/Parcel:recycle	()V
      //   57: aload_2
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_1
      //   62: areturn
      //   63: aconst_null
      //   64: astore_1
      //   65: goto -12 -> 53
      //   68: astore_1
      //   69: aload_3
      //   70: invokevirtual 53	android/os/Parcel:recycle	()V
      //   73: aload_2
      //   74: invokevirtual 53	android/os/Parcel:recycle	()V
      //   77: aload_1
      //   78: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Proxy
      //   52	13	1	localMediaMetadataCompat	MediaMetadataCompat
      //   68	10	1	localObject	Object
      //   3	71	2	localParcel1	Parcel
      //   7	63	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	53	68	finally
    }
    
    public String getPackageName()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(6, localParcel1, localParcel2, 0);
        localParcel2.readException();
        String str = localParcel2.readString();
        return str;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public PlaybackStateCompat getPlaybackState()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: bipush 27
      //   20: aload_2
      //   21: aload_3
      //   22: iconst_0
      //   23: invokeinterface 47 5 0
      //   28: pop
      //   29: aload_3
      //   30: invokevirtual 50	android/os/Parcel:readException	()V
      //   33: aload_3
      //   34: invokevirtual 62	android/os/Parcel:readInt	()I
      //   37: ifeq +26 -> 63
      //   40: getstatic 100	android/support/v4/media/session/PlaybackStateCompat:CREATOR	Landroid/os/Parcelable$Creator;
      //   43: aload_3
      //   44: invokeinterface 74 2 0
      //   49: checkcast 99	android/support/v4/media/session/PlaybackStateCompat
      //   52: astore_1
      //   53: aload_3
      //   54: invokevirtual 53	android/os/Parcel:recycle	()V
      //   57: aload_2
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_1
      //   62: areturn
      //   63: aconst_null
      //   64: astore_1
      //   65: goto -12 -> 53
      //   68: astore_1
      //   69: aload_3
      //   70: invokevirtual 53	android/os/Parcel:recycle	()V
      //   73: aload_2
      //   74: invokevirtual 53	android/os/Parcel:recycle	()V
      //   77: aload_1
      //   78: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Proxy
      //   52	13	1	localPlaybackStateCompat	PlaybackStateCompat
      //   68	10	1	localObject	Object
      //   3	71	2	localParcel1	Parcel
      //   7	63	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	53	68	finally
    }
    
    public List<MediaSessionCompat.QueueItem> getQueue()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(28, localParcel1, localParcel2, 0);
        localParcel2.readException();
        ArrayList localArrayList = localParcel2.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR);
        return localArrayList;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public CharSequence getQueueTitle()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: bipush 29
      //   20: aload_2
      //   21: aload_3
      //   22: iconst_0
      //   23: invokeinterface 47 5 0
      //   28: pop
      //   29: aload_3
      //   30: invokevirtual 50	android/os/Parcel:readException	()V
      //   33: aload_3
      //   34: invokevirtual 62	android/os/Parcel:readInt	()I
      //   37: ifeq +26 -> 63
      //   40: getstatic 118	android/text/TextUtils:CHAR_SEQUENCE_CREATOR	Landroid/os/Parcelable$Creator;
      //   43: aload_3
      //   44: invokeinterface 74 2 0
      //   49: checkcast 120	java/lang/CharSequence
      //   52: astore_1
      //   53: aload_3
      //   54: invokevirtual 53	android/os/Parcel:recycle	()V
      //   57: aload_2
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_1
      //   62: areturn
      //   63: aconst_null
      //   64: astore_1
      //   65: goto -12 -> 53
      //   68: astore_1
      //   69: aload_3
      //   70: invokevirtual 53	android/os/Parcel:recycle	()V
      //   73: aload_2
      //   74: invokevirtual 53	android/os/Parcel:recycle	()V
      //   77: aload_1
      //   78: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Proxy
      //   52	13	1	localCharSequence	CharSequence
      //   68	10	1	localObject	Object
      //   3	71	2	localParcel1	Parcel
      //   7	63	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	53	68	finally
    }
    
    public int getRatingType()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(31, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int i = localParcel2.readInt();
        return i;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public String getTag()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(7, localParcel1, localParcel2, 0);
        localParcel2.readException();
        String str = localParcel2.readString();
        return str;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public ParcelableVolumeInfo getVolumeAttributes()
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_0
      //   15: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   18: bipush 10
      //   20: aload_2
      //   21: aload_3
      //   22: iconst_0
      //   23: invokeinterface 47 5 0
      //   28: pop
      //   29: aload_3
      //   30: invokevirtual 50	android/os/Parcel:readException	()V
      //   33: aload_3
      //   34: invokevirtual 62	android/os/Parcel:readInt	()I
      //   37: ifeq +26 -> 63
      //   40: getstatic 127	android/support/v4/media/session/ParcelableVolumeInfo:CREATOR	Landroid/os/Parcelable$Creator;
      //   43: aload_3
      //   44: invokeinterface 74 2 0
      //   49: checkcast 126	android/support/v4/media/session/ParcelableVolumeInfo
      //   52: astore_1
      //   53: aload_3
      //   54: invokevirtual 53	android/os/Parcel:recycle	()V
      //   57: aload_2
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_1
      //   62: areturn
      //   63: aconst_null
      //   64: astore_1
      //   65: goto -12 -> 53
      //   68: astore_1
      //   69: aload_3
      //   70: invokevirtual 53	android/os/Parcel:recycle	()V
      //   73: aload_2
      //   74: invokevirtual 53	android/os/Parcel:recycle	()V
      //   77: aload_1
      //   78: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	79	0	this	Proxy
      //   52	13	1	localParcelableVolumeInfo	ParcelableVolumeInfo
      //   68	10	1	localObject	Object
      //   3	71	2	localParcel1	Parcel
      //   7	63	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	53	68	finally
    }
    
    public boolean isTransportControlEnabled()
    {
      boolean bool = false;
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(5, localParcel1, localParcel2, 0);
        localParcel2.readException();
        int i = localParcel2.readInt();
        if (i != 0) {
          bool = true;
        }
        return bool;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void next()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(19, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void pause()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(17, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void play()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(13, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public void playFromMediaId(String paramString, Bundle paramBundle)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 30
      //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: aload_1
      //   17: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   20: aload_2
      //   21: ifnull +45 -> 66
      //   24: aload_3
      //   25: iconst_1
      //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   29: aload_2
      //   30: aload_3
      //   31: iconst_0
      //   32: invokevirtual 138	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
      //   35: aload_0
      //   36: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   39: bipush 14
      //   41: aload_3
      //   42: aload 4
      //   44: iconst_0
      //   45: invokeinterface 47 5 0
      //   50: pop
      //   51: aload 4
      //   53: invokevirtual 50	android/os/Parcel:readException	()V
      //   56: aload 4
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_3
      //   62: invokevirtual 53	android/os/Parcel:recycle	()V
      //   65: return
      //   66: aload_3
      //   67: iconst_0
      //   68: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   71: goto -36 -> 35
      //   74: astore_1
      //   75: aload 4
      //   77: invokevirtual 53	android/os/Parcel:recycle	()V
      //   80: aload_3
      //   81: invokevirtual 53	android/os/Parcel:recycle	()V
      //   84: aload_1
      //   85: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	86	0	this	Proxy
      //   0	86	1	paramString	String
      //   0	86	2	paramBundle	Bundle
      //   3	78	3	localParcel1	Parcel
      //   7	69	4	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   9	20	74	finally
      //   24	35	74	finally
      //   35	56	74	finally
      //   66	71	74	finally
    }
    
    /* Error */
    public void playFromSearch(String paramString, Bundle paramBundle)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 30
      //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: aload_1
      //   17: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   20: aload_2
      //   21: ifnull +45 -> 66
      //   24: aload_3
      //   25: iconst_1
      //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   29: aload_2
      //   30: aload_3
      //   31: iconst_0
      //   32: invokevirtual 138	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
      //   35: aload_0
      //   36: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   39: bipush 15
      //   41: aload_3
      //   42: aload 4
      //   44: iconst_0
      //   45: invokeinterface 47 5 0
      //   50: pop
      //   51: aload 4
      //   53: invokevirtual 50	android/os/Parcel:readException	()V
      //   56: aload 4
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_3
      //   62: invokevirtual 53	android/os/Parcel:recycle	()V
      //   65: return
      //   66: aload_3
      //   67: iconst_0
      //   68: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   71: goto -36 -> 35
      //   74: astore_1
      //   75: aload 4
      //   77: invokevirtual 53	android/os/Parcel:recycle	()V
      //   80: aload_3
      //   81: invokevirtual 53	android/os/Parcel:recycle	()V
      //   84: aload_1
      //   85: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	86	0	this	Proxy
      //   0	86	1	paramString	String
      //   0	86	2	paramBundle	Bundle
      //   3	78	3	localParcel1	Parcel
      //   7	69	4	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   9	20	74	finally
      //   24	35	74	finally
      //   35	56	74	finally
      //   66	71	74	finally
    }
    
    public void previous()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(20, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public void rate(RatingCompat paramRatingCompat)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_2
      //   19: iconst_1
      //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   23: aload_1
      //   24: aload_2
      //   25: iconst_0
      //   26: invokevirtual 145	android/support/v4/media/RatingCompat:writeToParcel	(Landroid/os/Parcel;I)V
      //   29: aload_0
      //   30: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   33: bipush 24
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 47 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 50	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 53	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 53	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aload_2
      //   58: iconst_0
      //   59: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   62: goto -33 -> 29
      //   65: astore_1
      //   66: aload_3
      //   67: invokevirtual 53	android/os/Parcel:recycle	()V
      //   70: aload_2
      //   71: invokevirtual 53	android/os/Parcel:recycle	()V
      //   74: aload_1
      //   75: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	76	0	this	Proxy
      //   0	76	1	paramRatingCompat	RatingCompat
      //   3	68	2	localParcel1	Parcel
      //   7	60	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	65	finally
      //   18	29	65	finally
      //   29	48	65	finally
      //   57	62	65	finally
    }
    
    /* Error */
    public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 151 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 154	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   34: iconst_3
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 47 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 50	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 53	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 53	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 53	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 53	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	Proxy
      //   0	73	1	paramIMediaControllerCallback	IMediaControllerCallback
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
    
    public void rewind()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(22, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void seekTo(long paramLong)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        localParcel1.writeLong(paramLong);
        mRemote.transact(23, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      for (;;)
      {
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          localParcel1.writeString(paramString);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
            if (paramResultReceiverWrapper != null)
            {
              localParcel1.writeInt(1);
              paramResultReceiverWrapper.writeToParcel(localParcel1, 0);
              mRemote.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
            }
          }
          else
          {
            localParcel1.writeInt(0);
            continue;
          }
          localParcel1.writeInt(0);
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
    
    /* Error */
    public void sendCustomAction(String paramString, Bundle paramBundle)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_3
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore 4
      //   9: aload_3
      //   10: ldc 30
      //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   15: aload_3
      //   16: aload_1
      //   17: invokevirtual 41	android/os/Parcel:writeString	(Ljava/lang/String;)V
      //   20: aload_2
      //   21: ifnull +45 -> 66
      //   24: aload_3
      //   25: iconst_1
      //   26: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   29: aload_2
      //   30: aload_3
      //   31: iconst_0
      //   32: invokevirtual 138	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
      //   35: aload_0
      //   36: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   39: bipush 25
      //   41: aload_3
      //   42: aload 4
      //   44: iconst_0
      //   45: invokeinterface 47 5 0
      //   50: pop
      //   51: aload 4
      //   53: invokevirtual 50	android/os/Parcel:readException	()V
      //   56: aload 4
      //   58: invokevirtual 53	android/os/Parcel:recycle	()V
      //   61: aload_3
      //   62: invokevirtual 53	android/os/Parcel:recycle	()V
      //   65: return
      //   66: aload_3
      //   67: iconst_0
      //   68: invokevirtual 38	android/os/Parcel:writeInt	(I)V
      //   71: goto -36 -> 35
      //   74: astore_1
      //   75: aload 4
      //   77: invokevirtual 53	android/os/Parcel:recycle	()V
      //   80: aload_3
      //   81: invokevirtual 53	android/os/Parcel:recycle	()V
      //   84: aload_1
      //   85: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	86	0	this	Proxy
      //   0	86	1	paramString	String
      //   0	86	2	paramBundle	Bundle
      //   3	78	3	localParcel1	Parcel
      //   7	69	4	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   9	20	74	finally
      //   24	35	74	finally
      //   35	56	74	finally
      //   66	71	74	finally
    }
    
    public boolean sendMediaButton(KeyEvent paramKeyEvent)
    {
      boolean bool = true;
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      for (;;)
      {
        try
        {
          localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
          if (paramKeyEvent != null)
          {
            localParcel1.writeInt(1);
            paramKeyEvent.writeToParcel(localParcel1, 0);
            mRemote.transact(2, localParcel1, localParcel2, 0);
            localParcel2.readException();
            int i = localParcel2.readInt();
            if (i != 0) {
              return bool;
            }
          }
          else
          {
            localParcel1.writeInt(0);
            continue;
          }
          bool = false;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
    
    public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        localParcel1.writeInt(paramInt1);
        localParcel1.writeInt(paramInt2);
        localParcel1.writeString(paramString);
        mRemote.transact(12, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void skipToQueueItem(long paramLong)
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        localParcel1.writeLong(paramLong);
        mRemote.transact(16, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    public void stop()
    {
      Parcel localParcel1 = Parcel.obtain();
      Parcel localParcel2 = Parcel.obtain();
      try
      {
        localParcel1.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
        mRemote.transact(18, localParcel1, localParcel2, 0);
        localParcel2.readException();
        return;
      }
      finally
      {
        localParcel2.recycle();
        localParcel1.recycle();
      }
    }
    
    /* Error */
    public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
    {
      // Byte code:
      //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   3: astore_2
      //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
      //   7: astore_3
      //   8: aload_2
      //   9: ldc 30
      //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
      //   14: aload_1
      //   15: ifnull +42 -> 57
      //   18: aload_1
      //   19: invokeinterface 151 1 0
      //   24: astore_1
      //   25: aload_2
      //   26: aload_1
      //   27: invokevirtual 154	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
      //   30: aload_0
      //   31: getfield 19	android/support/v4/media/session/IMediaSession$Stub$Proxy:mRemote	Landroid/os/IBinder;
      //   34: iconst_4
      //   35: aload_2
      //   36: aload_3
      //   37: iconst_0
      //   38: invokeinterface 47 5 0
      //   43: pop
      //   44: aload_3
      //   45: invokevirtual 50	android/os/Parcel:readException	()V
      //   48: aload_3
      //   49: invokevirtual 53	android/os/Parcel:recycle	()V
      //   52: aload_2
      //   53: invokevirtual 53	android/os/Parcel:recycle	()V
      //   56: return
      //   57: aconst_null
      //   58: astore_1
      //   59: goto -34 -> 25
      //   62: astore_1
      //   63: aload_3
      //   64: invokevirtual 53	android/os/Parcel:recycle	()V
      //   67: aload_2
      //   68: invokevirtual 53	android/os/Parcel:recycle	()V
      //   71: aload_1
      //   72: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	73	0	this	Proxy
      //   0	73	1	paramIMediaControllerCallback	IMediaControllerCallback
      //   3	65	2	localParcel1	Parcel
      //   7	57	3	localParcel2	Parcel
      // Exception table:
      //   from	to	target	type
      //   8	14	62	finally
      //   18	25	62	finally
      //   25	48	62	finally
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.IMediaSession.Stub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */