package com.android.mms.audio;

import android.app.Application;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony.Mms;
import com.android.mms.MmsApp;
import com.android.mms.transaction.CleanFileService;
import com.android.mms.ui.PreviewImageLoader;

public class Mx2DeleteHelper
  extends AsyncQueryHandler
{
  private String[] mArgs;
  private AudioItemCache mAudioItemCache = null;
  private DeleteCallback mCallback;
  private String[] mPaths;
  private String mSelection;
  private Uri mUri;
  
  public Mx2DeleteHelper(ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  public static void deleteMms(int paramInt, Object paramObject, Uri paramUri, String paramString, String[] paramArrayOfString, DeleteCallback paramDeleteCallback, AudioItemCache paramAudioItemCache)
  {
    Mx2DeleteHelper localMx2DeleteHelper = new Mx2DeleteHelper(MmsApp.getApp().getContentResolver());
    mAudioItemCache = paramAudioItemCache;
    mUri = paramUri;
    mSelection = paramString;
    mArgs = paramArrayOfString;
    mCallback = paramDeleteCallback;
    localMx2DeleteHelper.startQuery(paramInt, paramObject, Telephony.Mms.CONTENT_URI, new String[] { "mx_extension", "_id" }, paramString, paramArrayOfString, null);
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    if (mPaths != null)
    {
      Intent localIntent = new Intent(MmsApp.getApp(), CleanFileService.class);
      Bundle localBundle = new Bundle();
      localBundle.putStringArray("paths", mPaths);
      localIntent.putExtras(localBundle);
      MmsApp.getApp().startService(localIntent);
    }
    long l = ContentUris.parseId(mUri);
    PreviewImageLoader.getInstance().cancelAndClear(l);
    if (mCallback != null) {
      mCallback.onDeleteComplete(paramInt1, paramObject, paramInt2);
    }
  }
  
  /* Error */
  protected void onQueryComplete(int paramInt, Object paramObject, android.database.Cursor paramCursor)
  {
    // Byte code:
    //   0: new 118	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 119	java/util/ArrayList:<init>	()V
    //   7: astore 12
    //   9: aconst_null
    //   10: astore 9
    //   12: aload_3
    //   13: ifnull +176 -> 189
    //   16: aload_3
    //   17: invokeinterface 125 1 0
    //   22: istore 6
    //   24: iload 6
    //   26: ifeq +131 -> 157
    //   29: new 127	org/json/JSONArray
    //   32: dup
    //   33: aload_3
    //   34: iconst_0
    //   35: invokeinterface 131 2 0
    //   40: invokespecial 134	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   43: astore 10
    //   45: aload_3
    //   46: iconst_1
    //   47: invokeinterface 138 2 0
    //   52: lstore 7
    //   54: aload_0
    //   55: getfield 21	com/android/mms/audio/Mx2DeleteHelper:mAudioItemCache	Lcom/android/mms/audio/AudioItemCache;
    //   58: ifnull +21 -> 79
    //   61: aload_0
    //   62: getfield 21	com/android/mms/audio/Mx2DeleteHelper:mAudioItemCache	Lcom/android/mms/audio/AudioItemCache;
    //   65: lload 7
    //   67: invokevirtual 143	com/android/mms/audio/AudioItemCache:remove	(J)V
    //   70: aload_0
    //   71: getfield 21	com/android/mms/audio/Mx2DeleteHelper:mAudioItemCache	Lcom/android/mms/audio/AudioItemCache;
    //   74: lload 7
    //   76: invokevirtual 146	com/android/mms/audio/AudioItemCache:removeMmsAudioPath	(J)V
    //   79: iconst_0
    //   80: istore 4
    //   82: aload 10
    //   84: invokevirtual 150	org/json/JSONArray:length	()I
    //   87: istore 5
    //   89: aload 10
    //   91: astore 9
    //   93: iload 4
    //   95: iload 5
    //   97: if_icmpge +47 -> 144
    //   100: aload 12
    //   102: aload 10
    //   104: iload 4
    //   106: invokevirtual 154	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   109: checkcast 156	org/json/JSONObject
    //   112: ldc -98
    //   114: invokevirtual 161	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   117: invokeinterface 167 2 0
    //   122: pop
    //   123: iload 4
    //   125: iconst_1
    //   126: iadd
    //   127: istore 4
    //   129: goto -47 -> 82
    //   132: astore 11
    //   134: ldc -87
    //   136: ldc -85
    //   138: aload 11
    //   140: invokestatic 177	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   143: pop
    //   144: aload_3
    //   145: invokeinterface 180 1 0
    //   150: istore 6
    //   152: iload 6
    //   154: ifne -125 -> 29
    //   157: aload_3
    //   158: invokeinterface 183 1 0
    //   163: aload_0
    //   164: aload 12
    //   166: invokeinterface 186 1 0
    //   171: anewarray 52	java/lang/String
    //   174: putfield 64	com/android/mms/audio/Mx2DeleteHelper:mPaths	[Ljava/lang/String;
    //   177: aload 12
    //   179: aload_0
    //   180: getfield 64	com/android/mms/audio/Mx2DeleteHelper:mPaths	[Ljava/lang/String;
    //   183: invokeinterface 190 2 0
    //   188: pop
    //   189: aload_0
    //   190: iload_1
    //   191: aload_2
    //   192: aload_0
    //   193: getfield 39	com/android/mms/audio/Mx2DeleteHelper:mUri	Landroid/net/Uri;
    //   196: aload_0
    //   197: getfield 41	com/android/mms/audio/Mx2DeleteHelper:mSelection	Ljava/lang/String;
    //   200: aload_0
    //   201: getfield 43	com/android/mms/audio/Mx2DeleteHelper:mArgs	[Ljava/lang/String;
    //   204: invokevirtual 194	com/android/mms/audio/Mx2DeleteHelper:startDelete	(ILjava/lang/Object;Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)V
    //   207: aload_0
    //   208: iload_1
    //   209: aload_2
    //   210: aload_3
    //   211: invokespecial 196	android/content/AsyncQueryHandler:onQueryComplete	(ILjava/lang/Object;Landroid/database/Cursor;)V
    //   214: return
    //   215: astore 11
    //   217: ldc -87
    //   219: ldc -58
    //   221: aload 11
    //   223: invokestatic 177	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   226: pop
    //   227: goto -83 -> 144
    //   230: astore_2
    //   231: aload_3
    //   232: invokeinterface 183 1 0
    //   237: aload_2
    //   238: athrow
    //   239: astore 9
    //   241: ldc -87
    //   243: ldc -85
    //   245: aload 9
    //   247: invokestatic 177	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   250: pop
    //   251: goto -128 -> 123
    //   254: astore_2
    //   255: goto -24 -> 231
    //   258: astore 11
    //   260: aload 10
    //   262: astore 9
    //   264: goto -47 -> 217
    //   267: astore 11
    //   269: aload 10
    //   271: astore 9
    //   273: goto -139 -> 134
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	276	0	this	Mx2DeleteHelper
    //   0	276	1	paramInt	int
    //   0	276	2	paramObject	Object
    //   0	276	3	paramCursor	android.database.Cursor
    //   80	48	4	i	int
    //   87	11	5	j	int
    //   22	131	6	bool	boolean
    //   52	23	7	l	long
    //   10	82	9	localObject1	Object
    //   239	7	9	localJSONException1	org.json.JSONException
    //   262	10	9	localObject2	Object
    //   43	227	10	localJSONArray	org.json.JSONArray
    //   132	7	11	localJSONException2	org.json.JSONException
    //   215	7	11	localNullPointerException1	NullPointerException
    //   258	1	11	localNullPointerException2	NullPointerException
    //   267	1	11	localJSONException3	org.json.JSONException
    //   7	171	12	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   29	45	132	org/json/JSONException
    //   29	45	215	java/lang/NullPointerException
    //   16	24	230	finally
    //   45	79	230	finally
    //   82	89	230	finally
    //   100	123	230	finally
    //   134	144	230	finally
    //   144	152	230	finally
    //   217	227	230	finally
    //   241	251	230	finally
    //   100	123	239	org/json/JSONException
    //   29	45	254	finally
    //   45	79	258	java/lang/NullPointerException
    //   45	79	267	org/json/JSONException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.Mx2DeleteHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */