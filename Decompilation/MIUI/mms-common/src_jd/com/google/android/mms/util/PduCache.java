package com.google.android.mms.util;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.Telephony.Mms;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class PduCache
  extends AbstractCache<Uri, PduCacheEntry>
{
  private static final boolean DEBUG = false;
  private static final boolean LOCAL_LOGV = false;
  private static final HashMap<Integer, Integer> MATCH_TO_MSGBOX_ID_MAP;
  private static final int MMS_ALL = 0;
  private static final int MMS_ALL_ID = 1;
  private static final int MMS_CONVERSATION = 10;
  private static final int MMS_CONVERSATION_ID = 11;
  private static final int MMS_DRAFTS = 6;
  private static final int MMS_DRAFTS_ID = 7;
  private static final int MMS_INBOX = 2;
  private static final int MMS_INBOX_ID = 3;
  private static final int MMS_OUTBOX = 8;
  private static final int MMS_OUTBOX_ID = 9;
  private static final int MMS_SENT = 4;
  private static final int MMS_SENT_ID = 5;
  private static final String TAG = "PduCache";
  private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
  private static PduCache sInstance;
  private final HashMap<Integer, HashSet<Uri>> mMessageBoxes = new HashMap();
  private final HashMap<Long, HashSet<Uri>> mThreads = new HashMap();
  private final HashSet<Uri> mUpdating = new HashSet();
  
  static
  {
    URI_MATCHER.addURI("mms", null, 0);
    URI_MATCHER.addURI("mms", "#", 1);
    URI_MATCHER.addURI("mms", "inbox", 2);
    URI_MATCHER.addURI("mms", "inbox/#", 3);
    URI_MATCHER.addURI("mms", "sent", 4);
    URI_MATCHER.addURI("mms", "sent/#", 5);
    URI_MATCHER.addURI("mms", "drafts", 6);
    URI_MATCHER.addURI("mms", "drafts/#", 7);
    URI_MATCHER.addURI("mms", "outbox", 8);
    URI_MATCHER.addURI("mms", "outbox/#", 9);
    URI_MATCHER.addURI("mms-sms", "conversations", 10);
    URI_MATCHER.addURI("mms-sms", "conversations/#", 11);
    MATCH_TO_MSGBOX_ID_MAP = new HashMap();
    MATCH_TO_MSGBOX_ID_MAP.put(Integer.valueOf(2), Integer.valueOf(1));
    MATCH_TO_MSGBOX_ID_MAP.put(Integer.valueOf(4), Integer.valueOf(2));
    MATCH_TO_MSGBOX_ID_MAP.put(Integer.valueOf(6), Integer.valueOf(3));
    MATCH_TO_MSGBOX_ID_MAP.put(Integer.valueOf(8), Integer.valueOf(4));
  }
  
  public static final PduCache getInstance()
  {
    try
    {
      if (sInstance == null) {
        sInstance = new PduCache();
      }
      PduCache localPduCache = sInstance;
      return localPduCache;
    }
    finally {}
  }
  
  private Uri normalizeKey(Uri paramUri)
  {
    switch (URI_MATCHER.match(paramUri))
    {
    case 2: 
    case 4: 
    case 6: 
    case 8: 
    default: 
      return null;
    }
    for (;;)
    {
      return paramUri;
      paramUri = paramUri.getLastPathSegment();
      paramUri = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, paramUri);
    }
  }
  
  private void purgeByMessageBox(Integer paramInteger)
  {
    if (paramInteger != null)
    {
      paramInteger = (HashSet)mMessageBoxes.remove(paramInteger);
      if (paramInteger != null)
      {
        paramInteger = paramInteger.iterator();
        while (paramInteger.hasNext())
        {
          Uri localUri = (Uri)paramInteger.next();
          mUpdating.remove(localUri);
          PduCacheEntry localPduCacheEntry = (PduCacheEntry)super.purge(localUri);
          if (localPduCacheEntry != null) {
            removeFromThreads(localUri, localPduCacheEntry);
          }
        }
      }
    }
  }
  
  private void purgeByThreadId(long paramLong)
  {
    Object localObject = (HashSet)mThreads.remove(Long.valueOf(paramLong));
    if (localObject != null)
    {
      localObject = ((HashSet)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        Uri localUri = (Uri)((Iterator)localObject).next();
        mUpdating.remove(localUri);
        PduCacheEntry localPduCacheEntry = (PduCacheEntry)super.purge(localUri);
        if (localPduCacheEntry != null) {
          removeFromMessageBoxes(localUri, localPduCacheEntry);
        }
      }
    }
  }
  
  private PduCacheEntry purgeSingleEntry(Uri paramUri)
  {
    mUpdating.remove(paramUri);
    PduCacheEntry localPduCacheEntry = (PduCacheEntry)super.purge(paramUri);
    if (localPduCacheEntry != null)
    {
      removeFromThreads(paramUri, localPduCacheEntry);
      removeFromMessageBoxes(paramUri, localPduCacheEntry);
      return localPduCacheEntry;
    }
    return null;
  }
  
  private void removeFromMessageBoxes(Uri paramUri, PduCacheEntry paramPduCacheEntry)
  {
    paramPduCacheEntry = (HashSet)mThreads.get(Long.valueOf(paramPduCacheEntry.getMessageBox()));
    if (paramPduCacheEntry != null) {
      paramPduCacheEntry.remove(paramUri);
    }
  }
  
  private void removeFromThreads(Uri paramUri, PduCacheEntry paramPduCacheEntry)
  {
    paramPduCacheEntry = (HashSet)mThreads.get(Long.valueOf(paramPduCacheEntry.getThreadId()));
    if (paramPduCacheEntry != null) {
      paramPduCacheEntry.remove(paramUri);
    }
  }
  
  public boolean isUpdating(Uri paramUri)
  {
    try
    {
      boolean bool = mUpdating.contains(paramUri);
      return bool;
    }
    finally
    {
      paramUri = finally;
      throw paramUri;
    }
  }
  
  /* Error */
  public PduCacheEntry purge(Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 61	com/google/android/mms/util/PduCache:URI_MATCHER	Landroid/content/UriMatcher;
    //   5: aload_1
    //   6: invokevirtual 129	android/content/UriMatcher:match	(Landroid/net/Uri;)I
    //   9: istore_2
    //   10: iload_2
    //   11: tableswitch	default:+61->72, 0:+96->107, 1:+67->78, 2:+105->116, 3:+76->87, 4:+105->116, 5:+76->87, 6:+105->116, 7:+76->87, 8:+105->116, 9:+76->87, 10:+96->107, 11:+127->138
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_0
    //   75: monitorexit
    //   76: aload_1
    //   77: areturn
    //   78: aload_0
    //   79: aload_1
    //   80: invokespecial 207	com/google/android/mms/util/PduCache:purgeSingleEntry	(Landroid/net/Uri;)Lcom/google/android/mms/util/PduCacheEntry;
    //   83: astore_1
    //   84: goto -10 -> 74
    //   87: aload_1
    //   88: invokevirtual 135	android/net/Uri:getLastPathSegment	()Ljava/lang/String;
    //   91: astore_1
    //   92: aload_0
    //   93: getstatic 141	android/provider/Telephony$Mms:CONTENT_URI	Landroid/net/Uri;
    //   96: aload_1
    //   97: invokestatic 145	android/net/Uri:withAppendedPath	(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   100: invokespecial 207	com/google/android/mms/util/PduCache:purgeSingleEntry	(Landroid/net/Uri;)Lcom/google/android/mms/util/PduCacheEntry;
    //   103: astore_1
    //   104: goto -30 -> 74
    //   107: aload_0
    //   108: invokevirtual 210	com/google/android/mms/util/PduCache:purgeAll	()V
    //   111: aconst_null
    //   112: astore_1
    //   113: goto -39 -> 74
    //   116: aload_0
    //   117: getstatic 97	com/google/android/mms/util/PduCache:MATCH_TO_MSGBOX_ID_MAP	Ljava/util/HashMap;
    //   120: iload_2
    //   121: invokestatic 103	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   124: invokevirtual 196	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   127: checkcast 99	java/lang/Integer
    //   130: invokespecial 212	com/google/android/mms/util/PduCache:purgeByMessageBox	(Ljava/lang/Integer;)V
    //   133: aconst_null
    //   134: astore_1
    //   135: goto -61 -> 74
    //   138: aload_0
    //   139: aload_1
    //   140: invokestatic 218	android/content/ContentUris:parseId	(Landroid/net/Uri;)J
    //   143: invokespecial 220	com/google/android/mms/util/PduCache:purgeByThreadId	(J)V
    //   146: aconst_null
    //   147: astore_1
    //   148: goto -74 -> 74
    //   151: astore_1
    //   152: aload_0
    //   153: monitorexit
    //   154: aload_1
    //   155: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	this	PduCache
    //   0	156	1	paramUri	Uri
    //   9	112	2	i	int
    // Exception table:
    //   from	to	target	type
    //   2	10	151	finally
    //   78	84	151	finally
    //   87	104	151	finally
    //   107	111	151	finally
    //   116	133	151	finally
    //   138	146	151	finally
  }
  
  public void purgeAll()
  {
    try
    {
      super.purgeAll();
      mMessageBoxes.clear();
      mThreads.clear();
      mUpdating.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean put(Uri paramUri, PduCacheEntry paramPduCacheEntry)
  {
    try
    {
      int i = paramPduCacheEntry.getMessageBox();
      Object localObject2 = (HashSet)mMessageBoxes.get(Integer.valueOf(i));
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new HashSet();
        mMessageBoxes.put(Integer.valueOf(i), localObject1);
      }
      long l = paramPduCacheEntry.getThreadId();
      Object localObject3 = (HashSet)mThreads.get(Long.valueOf(l));
      localObject2 = localObject3;
      if (localObject3 == null)
      {
        localObject2 = new HashSet();
        mThreads.put(Long.valueOf(l), localObject2);
      }
      localObject3 = normalizeKey(paramUri);
      boolean bool = super.put(localObject3, paramPduCacheEntry);
      if (bool)
      {
        ((HashSet)localObject1).add(localObject3);
        ((HashSet)localObject2).add(localObject3);
      }
      setUpdating(paramUri, false);
      return bool;
    }
    finally {}
  }
  
  public void setUpdating(Uri paramUri, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (;;)
    {
      try
      {
        mUpdating.add(paramUri);
        return;
      }
      finally {}
      mUpdating.remove(paramUri);
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.util.PduCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */