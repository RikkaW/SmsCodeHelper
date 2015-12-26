package com.android.mms.model;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.ContentRestrictionException;
import com.android.mms.dom.events.EventImpl;
import com.google.android.mms.MmsException;
import org.w3c.dom.events.Event;

public class VideoModel
  extends RegionMediaModel
{
  public VideoModel(Context paramContext, Uri paramUri, RegionModel paramRegionModel)
    throws MmsException
  {
    this(paramContext, null, null, paramUri, paramRegionModel);
    initModelFromUri(paramUri);
    checkContentRestriction();
  }
  
  public VideoModel(Context paramContext, String paramString1, String paramString2, Uri paramUri, RegionModel paramRegionModel)
    throws MmsException
  {
    super(paramContext, "video", paramString1, paramString2, paramUri, paramRegionModel);
  }
  
  /* Error */
  private void initFromContentUri(Uri paramUri)
    throws MmsException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 35	com/android/mms/model/VideoModel:mContext	Landroid/content/Context;
    //   4: invokevirtual 41	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 35	com/android/mms/model/VideoModel:mContext	Landroid/content/Context;
    //   13: aload 4
    //   15: aload_1
    //   16: aconst_null
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: invokestatic 47	android/database/sqlite/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   23: astore 5
    //   25: aload 5
    //   27: ifnull +333 -> 360
    //   30: aload 5
    //   32: invokeinterface 53 1 0
    //   37: istore_3
    //   38: iload_3
    //   39: ifeq +294 -> 333
    //   42: aload 5
    //   44: aload 5
    //   46: ldc 55
    //   48: invokeinterface 59 2 0
    //   53: invokeinterface 63 2 0
    //   58: astore 4
    //   60: aload_0
    //   61: aload 4
    //   63: aload 4
    //   65: bipush 47
    //   67: invokevirtual 69	java/lang/String:lastIndexOf	(I)I
    //   70: iconst_1
    //   71: iadd
    //   72: invokevirtual 72	java/lang/String:substring	(I)Ljava/lang/String;
    //   75: invokevirtual 76	com/android/mms/model/VideoModel:setInternalSrc	(Ljava/lang/String;)V
    //   78: ldc 78
    //   80: aload_1
    //   81: invokevirtual 84	android/net/Uri:getHost	()Ljava/lang/String;
    //   84: invokevirtual 88	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   87: ifeq +64 -> 151
    //   90: aload_0
    //   91: aload 5
    //   93: aload 5
    //   95: ldc 90
    //   97: invokeinterface 59 2 0
    //   102: invokeinterface 63 2 0
    //   107: putfield 94	com/android/mms/model/VideoModel:mContentType	Ljava/lang/String;
    //   110: aload_0
    //   111: getfield 94	com/android/mms/model/VideoModel:mContentType	Ljava/lang/String;
    //   114: invokestatic 100	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   117: ifeq +57 -> 174
    //   120: new 8	com/google/android/mms/MmsException
    //   123: dup
    //   124: ldc 102
    //   126: invokespecial 104	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   129: athrow
    //   130: astore_1
    //   131: aload 5
    //   133: invokeinterface 107 1 0
    //   138: aload_1
    //   139: athrow
    //   140: astore 4
    //   142: aload_1
    //   143: invokevirtual 110	android/net/Uri:toString	()Ljava/lang/String;
    //   146: astore 4
    //   148: goto -88 -> 60
    //   151: aload_0
    //   152: aload 5
    //   154: aload 5
    //   156: ldc 112
    //   158: invokeinterface 59 2 0
    //   163: invokeinterface 63 2 0
    //   168: putfield 94	com/android/mms/model/VideoModel:mContentType	Ljava/lang/String;
    //   171: goto -61 -> 110
    //   174: aload_0
    //   175: getfield 94	com/android/mms/model/VideoModel:mContentType	Ljava/lang/String;
    //   178: ldc 114
    //   180: invokevirtual 88	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   183: ifeq +84 -> 267
    //   186: aload_0
    //   187: getfield 117	com/android/mms/model/VideoModel:mSrc	Ljava/lang/String;
    //   190: invokestatic 100	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   193: ifne +74 -> 267
    //   196: aload_0
    //   197: getfield 117	com/android/mms/model/VideoModel:mSrc	Ljava/lang/String;
    //   200: ldc 119
    //   202: invokevirtual 121	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   205: istore_2
    //   206: iload_2
    //   207: iconst_m1
    //   208: if_icmpeq +59 -> 267
    //   211: aload_0
    //   212: getfield 117	com/android/mms/model/VideoModel:mSrc	Ljava/lang/String;
    //   215: iload_2
    //   216: iconst_1
    //   217: iadd
    //   218: invokevirtual 72	java/lang/String:substring	(I)Ljava/lang/String;
    //   221: astore 4
    //   223: aload 4
    //   225: invokestatic 100	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   228: ifne +39 -> 267
    //   231: aload 4
    //   233: ldc 123
    //   235: invokevirtual 127	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   238: ifne +23 -> 261
    //   241: aload 4
    //   243: ldc -127
    //   245: invokevirtual 127	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   248: ifne +13 -> 261
    //   251: aload 4
    //   253: ldc -125
    //   255: invokevirtual 127	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   258: ifeq +9 -> 267
    //   261: aload_0
    //   262: ldc -123
    //   264: putfield 94	com/android/mms/model/VideoModel:mContentType	Ljava/lang/String;
    //   267: ldc -121
    //   269: iconst_2
    //   270: invokestatic 141	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   273: ifeq +52 -> 325
    //   276: ldc -113
    //   278: new 145	java/lang/StringBuilder
    //   281: dup
    //   282: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   285: ldc -107
    //   287: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: aload_0
    //   291: getfield 117	com/android/mms/model/VideoModel:mSrc	Ljava/lang/String;
    //   294: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc -101
    //   299: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: aload_0
    //   303: getfield 94	com/android/mms/model/VideoModel:mContentType	Ljava/lang/String;
    //   306: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   309: ldc -99
    //   311: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   314: aload_1
    //   315: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: invokestatic 165	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   324: pop
    //   325: aload 5
    //   327: invokeinterface 107 1 0
    //   332: return
    //   333: new 8	com/google/android/mms/MmsException
    //   336: dup
    //   337: new 145	java/lang/StringBuilder
    //   340: dup
    //   341: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   344: ldc -89
    //   346: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: aload_1
    //   350: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   353: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: invokespecial 104	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   359: athrow
    //   360: new 8	com/google/android/mms/MmsException
    //   363: dup
    //   364: new 145	java/lang/StringBuilder
    //   367: dup
    //   368: invokespecial 147	java/lang/StringBuilder:<init>	()V
    //   371: ldc -87
    //   373: invokevirtual 153	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload_1
    //   377: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 161	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: invokespecial 104	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   386: athrow
    //   387: astore 4
    //   389: goto -122 -> 267
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	392	0	this	VideoModel
    //   0	392	1	paramUri	Uri
    //   205	13	2	i	int
    //   37	2	3	bool	boolean
    //   7	57	4	localObject	Object
    //   140	1	4	localIllegalArgumentException	IllegalArgumentException
    //   146	106	4	str	String
    //   387	1	4	localIndexOutOfBoundsException	IndexOutOfBoundsException
    //   23	303	5	localCursor	android.database.Cursor
    // Exception table:
    //   from	to	target	type
    //   30	38	130	finally
    //   42	60	130	finally
    //   60	110	130	finally
    //   110	130	130	finally
    //   142	148	130	finally
    //   151	171	130	finally
    //   174	206	130	finally
    //   211	261	130	finally
    //   261	267	130	finally
    //   267	325	130	finally
    //   333	360	130	finally
    //   42	60	140	java/lang/IllegalArgumentException
    //   211	261	387	java/lang/IndexOutOfBoundsException
    //   261	267	387	java/lang/IndexOutOfBoundsException
  }
  
  private void initFromFile(Uri paramUri)
  {
    setInternalSrc(paramUri.getPath());
    MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
    String str2 = MimeTypeMap.getFileExtensionFromUrl(mSrc);
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
    {
      int i = mSrc.lastIndexOf('.');
      str1 = str2;
      if (i >= 0) {
        str1 = mSrc.substring(i + 1);
      }
    }
    if (str1 == null) {}
    for (str1 = null;; str1 = localMimeTypeMap.getMimeTypeFromExtension(str1.toLowerCase()))
    {
      mContentType = str1;
      if (Log.isLoggable("Mms:app", 2)) {
        Log.v("Mms/media", "New VideoModel initFromFile created: mSrc=" + mSrc + " mContentType=" + mContentType + " mUri=" + paramUri);
      }
      return;
    }
  }
  
  private void initModelFromUri(Uri paramUri)
    throws MmsException
  {
    if (paramUri.getScheme().equals("content")) {
      initFromContentUri(paramUri);
    }
    for (;;)
    {
      initMediaDuration();
      return;
      if (paramUri.getScheme().equals("file")) {
        initFromFile(paramUri);
      }
    }
  }
  
  protected void checkContentRestriction()
    throws ContentRestrictionException
  {
    ContentRestrictionFactory.getContentRestriction().checkVideoContentType(mContentType);
  }
  
  public void handleEvent(Event paramEvent)
  {
    String str = paramEvent.getType();
    if (Log.isLoggable("Mms:app", 2)) {
      Log.v("Mms/media", "[VideoModel] handleEvent " + paramEvent.getType() + " on " + this);
    }
    Object localObject = MediaModel.MediaAction.NO_ACTIVE_ACTION;
    if (str.equals("SmilMediaStart"))
    {
      localObject = MediaModel.MediaAction.START;
      pauseMusicPlayer();
      mVisible = true;
    }
    for (;;)
    {
      appendAction((MediaModel.MediaAction)localObject);
      notifyModelChanged(false);
      return;
      if (str.equals("SmilMediaEnd"))
      {
        paramEvent = MediaModel.MediaAction.STOP;
        localObject = paramEvent;
        if (mFill != 1)
        {
          mVisible = false;
          localObject = paramEvent;
        }
      }
      else if (str.equals("SmilMediaPause"))
      {
        localObject = MediaModel.MediaAction.PAUSE;
        mVisible = true;
      }
      else if (str.equals("SmilMediaSeek"))
      {
        localObject = MediaModel.MediaAction.SEEK;
        mSeekTo = ((EventImpl)paramEvent).getSeekTo();
        mVisible = true;
      }
    }
  }
  
  protected boolean isPlayable()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.model.VideoModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */