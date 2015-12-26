package android.support.v4.media.session;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.List;

class IMediaSession$Stub$Proxy
  implements IMediaSession
{
  private IBinder mRemote;
  
  IMediaSession$Stub$Proxy(IBinder paramIBinder)
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
  public android.app.PendingIntent getLaunchPendingIntent()
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
    //   52	13	1	localPendingIntent	android.app.PendingIntent
    //   68	10	1	localObject	Object
    //   3	71	2	localParcel1	Parcel
    //   7	63	3	localParcel2	Parcel
    // Exception table:
    //   from	to	target	type
    //   8	53	68	finally
  }
  
  /* Error */
  public android.support.v4.media.MediaMetadataCompat getMetadata()
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
    //   52	13	1	localMediaMetadataCompat	android.support.v4.media.MediaMetadataCompat
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
  public void rate(android.support.v4.media.RatingCompat paramRatingCompat)
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
    //   0	76	1	paramRatingCompat	android.support.v4.media.RatingCompat
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

/* Location:
 * Qualified Name:     android.support.v4.media.session.IMediaSession.Stub.Proxy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */