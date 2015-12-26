package com.android.mms.data;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.MmsSms;
import android.provider.Telephony.Sms.Inbox;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PrivatePreferenceActivity;
import com.android.mms.util.AddressUtils;
import com.google.android.collect.Lists;
import com.google.android.collect.Sets;
import com.google.android.mms.util.PduCache;
import com.xiaomi.mms.transaction.MipubThread.Mipub;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import miui.date.DateUtils;
import miui.provider.ExtraTelephony.Hms;
import miui.provider.ExtraTelephony.MmsSms;
import miui.telephony.PhoneNumberUtils;

public class Conversation
{
  public static final String[] ALL_BLOCKED_THREADS_PROJECTION;
  public static final String[] ALL_THREADS_PROJECTION;
  private static final String[] COUNT_PROJECTION;
  static final String[] SMS_PROJECTION = { "_id", "thread_id", "address", "body", "date", "read", "type", "status", "locked", "error_code" };
  private static final String[] THREAD_ID;
  private static final String[] UNREAD_PROJECTION;
  private static boolean mLoadingThreads;
  private static ContentValues mReadContentValues;
  private static final Uri nAllThreadsUri;
  private static final Uri sAllThreadsUri = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").appendQueryParameter("privacy_flag", "2").build();
  private static final Uri sBlockedUri;
  private static Thread sMarkAllConversationsAsSeenThread;
  private static Thread sMarkConversationAsNotifiedThread;
  private static Thread sMarkServiceProviderConversationAsSeenThread;
  private static final Uri sMmsSeenUri;
  private static final Uri sSmsSeenUri;
  private final Context mContext;
  private long mDate;
  private String mDateString = "";
  private boolean mHasAttachment;
  private boolean mHasError;
  private boolean mInCache = false;
  private boolean mIsB2cConv = false;
  private boolean mIsBlocked = false;
  private boolean mIsChecked;
  private boolean mIsServiceProvider;
  private long mLastSimId;
  private Object mMarkAsBlockedSyncer = new Object();
  private int mMessageCount;
  private long mPostMarkUnread;
  private Object mPostMarkUnreadObj = new Object();
  private boolean mPreMarkUnread;
  private boolean mPrivate;
  private ContactList mRecipients;
  private String mSeq = null;
  private String mSnippet;
  private long mStickTime;
  private long mThreadId;
  private int mUnreadMessageCount;
  
  static
  {
    nAllThreadsUri = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
    sBlockedUri = ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI;
    sSmsSeenUri = Uri.parse("content://sms/seen");
    sMmsSeenUri = Uri.parse("content://mms/seen");
    ALL_THREADS_PROJECTION = new String[] { "_id", "date", "message_count", "unread_count", "recipient_ids", "snippet", "snippet_cs", "read", "error", "has_attachment", "stick_time", "private_addr_ids", "last_sim_id", "sp_type", "mx_seq" };
    ALL_BLOCKED_THREADS_PROJECTION = new String[] { "blocked_threads._id", "recipient_ids", "date", "message_count", "unread_count", "snippet", "snippet_cs", "read" };
    UNREAD_PROJECTION = new String[] { "_id", "read" };
    COUNT_PROJECTION = new String[] { "count(*)" };
    THREAD_ID = new String[] { "_id" };
  }
  
  private Conversation(Context paramContext)
  {
    mContext = paramContext;
    mRecipients = new ContactList();
    mThreadId = 0L;
  }
  
  private Conversation(Context paramContext, long paramLong)
  {
    mContext = paramContext;
    if (!loadFromThreadId(paramLong))
    {
      mRecipients = new ContactList();
      mThreadId = 0L;
    }
  }
  
  private Conversation(Context paramContext, long paramLong, String paramString)
  {
    mContext = paramContext;
    int j;
    int i;
    if (paramLong != -1L)
    {
      paramString = paramContext.getContentResolver().query(Uri.withAppendedPath(sBlockedUri, Long.toString(paramLong)), ALL_BLOCKED_THREADS_PROJECTION, null, null, null);
      j = 0;
      i = 0;
      if (paramString == null) {
        break label241;
      }
    }
    for (;;)
    {
      try
      {
        if (paramString.moveToFirst())
        {
          fillFromBlockedCursor(paramContext, this, paramString);
          i = 1;
          paramString.close();
          if (i == 0)
          {
            LogTag.debug("Cannot load thread %d, forcing threadId=0", new Object[] { Long.valueOf(paramLong) });
            mRecipients = new ContactList();
            mThreadId = 0L;
          }
          return;
          paramString = "PHONE_NUMBERS_EQUAL(address, '" + paramString + "', 0)";
          paramString = paramContext.getContentResolver().query(sBlockedUri, ALL_BLOCKED_THREADS_PROJECTION, paramString, null, null);
          break;
        }
        LogTag.debug("Cannot move to first cursor", new Object[0]);
        continue;
        LogTag.debug("cursor is null", new Object[0]);
      }
      finally
      {
        paramString.close();
      }
      label241:
      i = j;
    }
  }
  
  private Conversation(Context paramContext, Cursor paramCursor)
  {
    mContext = paramContext;
    fillFromCursor(paramContext, this, paramCursor);
  }
  
  public static void asyncDeleteObsoleteThreads(AsyncQueryHandler paramAsyncQueryHandler, int paramInt)
  {
    paramAsyncQueryHandler.startDelete(paramInt, null, Telephony.Threads.OBSOLETE_THREADS_URI, null, null);
  }
  
  private static void blockingMarkAllHmsAsNotified(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    Object localObject = paramContext.query(ExtraTelephony.Hms.CONTENT_URI, COUNT_PROJECTION, "(advanced_seen=1)", null, null);
    if (localObject != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!((Cursor)localObject).moveToFirst()) {
          break label116;
        }
        i = ((Cursor)localObject).getInt(0);
        ((Cursor)localObject).close();
        if (Thread.currentThread().isInterrupted()) {
          return;
        }
      }
      finally
      {
        ((Cursor)localObject).close();
      }
      if (i > 0)
      {
        localObject = new ContentValues(2);
        ((ContentValues)localObject).put("seen", Integer.valueOf(1));
        ((ContentValues)localObject).put("advanced_seen", Integer.valueOf(2));
        paramContext.update(ExtraTelephony.Hms.CONTENT_URI, (ContentValues)localObject, "(advanced_seen=1 AND seen = 0)", null);
        return;
        label116:
        i = 0;
        continue;
        i = 0;
      }
    }
  }
  
  private static void blockingMarkAllMipubMessagesAsSeen(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    Object localObject = paramContext.query(MipubThread.Mipub.CONTENT_URI, new String[] { "count(*)" }, "seen=0", null, null);
    if (localObject != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!((Cursor)localObject).moveToFirst()) {
          break label148;
        }
        i = ((Cursor)localObject).getInt(0);
        ((Cursor)localObject).close();
        if (i == 0) {
          return;
        }
      }
      finally
      {
        ((Cursor)localObject).close();
      }
      if (!Thread.currentThread().isInterrupted())
      {
        LogTag.debug("mark %d Mipub msgs as seen", new Object[] { Integer.valueOf(i) });
        localObject = new ContentValues(2);
        ((ContentValues)localObject).put("seen", Integer.valueOf(1));
        ((ContentValues)localObject).put("advanced_seen", Integer.valueOf(3));
        paramContext.update(MipubThread.Mipub.CONTENT_URI, (ContentValues)localObject, "seen = ?", new String[] { "0" });
        return;
        label148:
        i = 0;
        continue;
        i = 0;
      }
    }
  }
  
  private static void blockingMarkAllMmsMessagesAsSeen(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    Object localObject = paramContext.query(Telephony.Mms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0", null, null);
    if (localObject != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!((Cursor)localObject).moveToFirst()) {
          break label116;
        }
        i = ((Cursor)localObject).getInt(0);
        ((Cursor)localObject).close();
        if (i == 0) {
          return;
        }
      }
      finally
      {
        ((Cursor)localObject).close();
      }
      if (!Thread.currentThread().isInterrupted())
      {
        localObject = new ContentValues(1);
        ((ContentValues)localObject).put("seen", Integer.valueOf(1));
        ((ContentValues)localObject).put("advanced_seen", Integer.valueOf(3));
        paramContext.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)localObject, "seen=0", null);
        return;
        label116:
        i = 0;
        continue;
        i = 0;
      }
    }
  }
  
  private static void blockingMarkAllSmsMessagesAsSeen(Context paramContext)
  {
    paramContext = paramContext.getContentResolver();
    Object localObject = paramContext.query(Telephony.Sms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0", null, null);
    if (localObject != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (!((Cursor)localObject).moveToFirst()) {
          break label116;
        }
        i = ((Cursor)localObject).getInt(0);
        ((Cursor)localObject).close();
        if (i == 0) {
          return;
        }
      }
      finally
      {
        ((Cursor)localObject).close();
      }
      if (!Thread.currentThread().isInterrupted())
      {
        localObject = new ContentValues(1);
        ((ContentValues)localObject).put("seen", Integer.valueOf(1));
        ((ContentValues)localObject).put("advanced_seen", Integer.valueOf(3));
        paramContext.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)localObject, "seen=0", null);
        return;
        label116:
        i = 0;
        continue;
        i = 0;
      }
    }
  }
  
  private void buildReadContentValues()
  {
    if (mReadContentValues == null)
    {
      mReadContentValues = new ContentValues(2);
      mReadContentValues.put("read", Integer.valueOf(1));
      mReadContentValues.put("seen", Integer.valueOf(1));
    }
  }
  
  private static void cacheAllThreads(Context arg0)
  {
    if (Log.isLoggable("Mms:threadcache", 2)) {
      LogTag.debug("cacheAllThreads: begin", new Object[0]);
    }
    for (;;)
    {
      HashSet localHashSet;
      synchronized (Cache.getInstance())
      {
        if (mLoadingThreads) {
          return;
        }
        mLoadingThreads = true;
        localHashSet = new HashSet();
        ??? = ???.getContentResolver().query(sAllThreadsUri, ALL_THREADS_PROJECTION, null, null, null);
        if (??? != null) {
          try
          {
            if (((Cursor)???).moveToNext())
            {
              l = ((Cursor)???).getLong(0);
              localHashSet.add(Long.valueOf(l));
            }
          }
          finally
          {
            synchronized (Cache.getInstance())
            {
              long l;
              Conversation localConversation1 = Cache.get(l);
              if (localConversation1 == null)
              {
                localConversation1 = new Conversation(???, (Cursor)???);
                try
                {
                  synchronized (Cache.getInstance())
                  {
                    Cache.put(localConversation1);
                  }
                }
                catch (IllegalStateException localIllegalStateException)
                {
                  LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
                }
                ??? = finally;
                if (??? != null) {
                  ((Cursor)???).close();
                }
              }
            }
          }
        }
      }
      synchronized (Cache.getInstance())
      {
        mLoadingThreads = false;
        throw ???;
        ??? = finally;
        throw ???;
        ??? = finally;
        throw ???;
        fillFromCursor(???, localConversation2, (Cursor)???);
        continue;
        if (??? != null) {
          ((Cursor)???).close();
        }
        synchronized (Cache.getInstance())
        {
          mLoadingThreads = false;
          Cache.keepOnly(localHashSet);
          return;
        }
      }
    }
  }
  
  public static boolean checkContentScheme(Uri paramUri)
  {
    paramUri = paramUri.getScheme();
    if (TextUtils.isEmpty(paramUri)) {}
    while (!paramUri.equals("content")) {
      return false;
    }
    return true;
  }
  
  public static Conversation createNew(Context paramContext)
  {
    return new Conversation(paramContext);
  }
  
  public static Conversation createNew(Context paramContext, long paramLong)
  {
    return new Conversation(paramContext, paramLong);
  }
  
  public static Conversation createNew(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return createNew(paramContext);
    }
    if (paramUri.getPathSegments().size() >= 2) {
      try
      {
        Conversation localConversation = createNew(paramContext, Long.parseLong((String)paramUri.getPathSegments().get(1)));
        return localConversation;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        LogTag.error("Invalid URI: %s", new Object[] { paramUri });
      }
    }
    if (checkContentScheme(paramUri)) {
      return createNew(paramContext);
    }
    return createNew(paramContext, ContactList.getByNumbers(getRecipientsFromUri(paramUri), true));
  }
  
  public static Conversation createNew(Context paramContext, ContactList paramContactList)
  {
    if (paramContactList.size() < 1) {
      paramContext = createNew(paramContext);
    }
    Conversation localConversation;
    do
    {
      return paramContext;
      localConversation = new Conversation(paramContext, getOrCreateThreadId(paramContext, paramContactList));
      paramContext = localConversation;
    } while (localConversation.getThreadId() != 0L);
    localConversation.setRecipients(paramContactList);
    return localConversation;
  }
  
  private static void fillFromBlockedCursor(Context paramContext, Conversation paramConversation, Cursor paramCursor)
  {
    try
    {
      mIsBlocked = true;
      mThreadId = paramCursor.getLong(0);
      mDate = paramCursor.getLong(2);
      mDateString = "";
      try
      {
        Object localObject = new StringBuilder();
        DateUtils.formatRelativeTime((StringBuilder)localObject, mDate, false);
        mDateString = ((StringBuilder)localObject).toString();
        mMessageCount = paramCursor.getInt(3);
        mUnreadMessageCount = paramCursor.getInt(4);
        String str = MessageUtils.extractEncStrFromCursor(paramCursor, 5, 6);
        localObject = str;
        if (TextUtils.isEmpty(str)) {
          localObject = paramContext.getString(2131361807);
        }
        mSnippet = formatSnippet(paramContext, (String)localObject);
        paramContext = ContactList.getByIds(paramCursor.getString(1));
        try
        {
          if ((mInCache) && (!paramContext.equals(mRecipients))) {
            throw new IllegalStateException(String.format("Unexpected recipient change in conversation %d", new Object[] { Long.valueOf(mThreadId) }));
          }
        }
        finally {}
      }
      catch (BufferOverflowException localBufferOverflowException)
      {
        for (;;)
        {
          Log.w("Mms/conv", "Not enough space for date buffer.");
        }
      }
      mRecipients = paramContext;
    }
    finally {}
  }
  
  private static void fillFromCursor(Context paramContext, Conversation paramConversation, Cursor paramCursor)
  {
    boolean bool2 = true;
    for (;;)
    {
      try
      {
        mThreadId = paramCursor.getLong(0);
        mDate = paramCursor.getLong(1);
        mDateString = "";
        try
        {
          Object localObject = new StringBuilder();
          DateUtils.formatRelativeTime((StringBuilder)localObject, mDate, false);
          mDateString = ((StringBuilder)localObject).toString();
          mMessageCount = paramCursor.getInt(2);
          mUnreadMessageCount = paramCursor.getInt(3);
          String str = MessageUtils.extractEncStrFromCursor(paramCursor, 5, 6);
          localObject = str;
          if (TextUtils.isEmpty(str)) {
            localObject = paramContext.getString(2131361807);
          }
          mSnippet = formatSnippet(paramContext, (String)localObject);
          if (paramCursor.getInt(8) != 0)
          {
            bool1 = true;
            mHasError = bool1;
            if (paramCursor.getInt(9) == 0) {
              break label351;
            }
            bool1 = true;
            mHasAttachment = bool1;
            mStickTime = paramCursor.getLong(10);
            if (paramCursor.getString(11) == null) {
              break label356;
            }
            bool1 = true;
            mPrivate = bool1;
            mLastSimId = paramCursor.getInt(12);
            if (paramCursor.getInt(13) == 0) {
              break label361;
            }
            bool1 = bool2;
            mIsServiceProvider = bool1;
            mSeq = paramCursor.getString(14);
            if (!TextUtils.isEmpty(mSeq)) {
              mIsB2cConv = true;
            }
            paramContext = ContactList.getByIds(paramCursor.getString(4));
            try
            {
              if ((!mInCache) || (paramContext.equals(mRecipients))) {
                break;
              }
              throw new IllegalStateException(String.format("Unexpected recipient change in conversation %d from %s to %s", new Object[] { Long.valueOf(mThreadId), mRecipients, paramContext }));
            }
            finally {}
          }
        }
        catch (BufferOverflowException localBufferOverflowException)
        {
          Log.w("Mms/conv", "Not enough space for date buffer.");
          continue;
        }
        bool1 = false;
      }
      finally {}
      continue;
      label351:
      boolean bool1 = false;
      continue;
      label356:
      bool1 = false;
      continue;
      label361:
      bool1 = false;
    }
    mRecipients = paramContext;
  }
  
  private static String formatSnippet(Context paramContext, String paramString)
  {
    String str;
    if ("[audio]".equals(paramString)) {
      str = paramContext.getString(2131362363);
    }
    do
    {
      return str;
      str = paramString;
    } while (!"[picture]".equals(paramString));
    return paramContext.getString(2131362364);
  }
  
  public static Conversation from(Context paramContext, Cursor paramCursor)
  {
    long l = paramCursor.getLong(0);
    if (l > 0L)
    {
      Conversation localConversation = Cache.get(l);
      if (localConversation != null)
      {
        fillFromCursor(paramContext, localConversation, paramCursor);
        return localConversation;
      }
    }
    paramContext = new Conversation(paramContext, paramCursor);
    try
    {
      Cache.put(paramContext);
      return paramContext;
    }
    catch (IllegalStateException paramCursor)
    {
      for (;;)
      {
        LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
      }
    }
  }
  
  public static Conversation get(long paramLong)
  {
    return get(MmsApp.getApp(), paramLong);
  }
  
  public static Conversation get(Context paramContext, long paramLong)
  {
    Conversation localConversation = Cache.get(paramLong);
    if (localConversation != null) {
      return localConversation;
    }
    paramContext = new Conversation(paramContext, paramLong);
    try
    {
      Cache.put(paramContext);
      return paramContext;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;)
      {
        LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
        if (!Cache.replace(paramContext)) {
          LogTag.error("get by recipients cache.replace failed on", new Object[0]);
        }
      }
    }
  }
  
  public static Conversation get(Context paramContext, Uri paramUri)
  {
    if (paramUri == null) {
      return createNew(paramContext);
    }
    if (paramUri.getPathSegments().size() >= 2) {
      try
      {
        Conversation localConversation = get(paramContext, Long.parseLong((String)paramUri.getPathSegments().get(1)));
        return localConversation;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        LogTag.error("Invalid URI: %s", new Object[] { paramUri });
      }
    }
    return get(paramContext, ContactList.getByNumbers(getRecipientsFromUri(paramUri), true));
  }
  
  public static Conversation get(Context paramContext, ContactList paramContactList)
  {
    Object localObject;
    if (paramContactList.size() < 1) {
      localObject = createNew(paramContext);
    }
    Conversation localConversation;
    do
    {
      return (Conversation)localObject;
      localConversation = Cache.get(paramContactList);
      localObject = localConversation;
    } while (localConversation != null);
    long l = getOrCreateThreadId(paramContext, paramContactList);
    paramContext = new Conversation(paramContext, l);
    if (paramContext.getThreadId() == 0L)
    {
      paramContext.setRecipients(paramContactList);
      return paramContext;
    }
    if (!paramContext.getRecipients().equals(paramContactList)) {
      LogTag.error(String.format("Conversation.get: new conv %d 's recipients %s don't match input recpients %s", new Object[] { Long.valueOf(l), paramContext.getRecipients(), paramContactList }), new Object[0]);
    }
    try
    {
      Cache.put(paramContext);
      return paramContext;
    }
    catch (IllegalStateException paramContactList)
    {
      LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
    }
    return paramContext;
  }
  
  public static Conversation get(Uri paramUri)
  {
    return get(MmsApp.getApp(), paramUri);
  }
  
  public static Conversation get(ContactList paramContactList)
  {
    return get(MmsApp.getApp(), paramContactList);
  }
  
  public static Conversation getBlockedConv(Context paramContext, long paramLong, String paramString)
  {
    return new Conversation(paramContext, paramLong, paramString);
  }
  
  public static Conversation getFromCache(Context paramContext, ContactList paramContactList)
  {
    if (paramContactList.size() < 1) {
      return null;
    }
    return Cache.get(paramContactList);
  }
  
  private static long getOrCreateThreadId(Context paramContext, ContactList paramContactList)
  {
    HashSet localHashSet = new HashSet();
    StringBuilder localStringBuilder = new StringBuilder("[");
    paramContactList = paramContactList.iterator();
    while (paramContactList.hasNext())
    {
      Contact localContact1 = (Contact)paramContactList.next();
      Contact localContact2 = Contact.get(localContact1.getNumber());
      if (localStringBuilder.length() > 1) {
        localStringBuilder.append(",");
      }
      if (localContact2 != null)
      {
        localHashSet.add(localContact2.getNumber());
        localStringBuilder.append(AddressUtils.cutPhoneNumberTail(localContact2.getNumber()));
        localStringBuilder.append("(cached)");
      }
      else
      {
        localHashSet.add(localContact1.getNumber());
        localStringBuilder.append(AddressUtils.cutPhoneNumberTail(localContact1.getNumber()));
      }
    }
    localStringBuilder.append("]");
    return Telephony.Threads.getOrCreateThreadId(paramContext, localHashSet);
  }
  
  public static String getRecipients(Uri paramUri)
  {
    paramUri = paramUri.getSchemeSpecificPart();
    int i = paramUri.indexOf('?');
    if (i == -1) {
      return paramUri;
    }
    return paramUri.substring(0, i);
  }
  
  private static String getRecipientsFromUri(Uri paramUri)
  {
    paramUri = paramUri.getSchemeSpecificPart();
    int i = paramUri.indexOf('?');
    if (i == -1) {
      return paramUri;
    }
    return paramUri.substring(0, i);
  }
  
  public static void init(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Conversation.cacheAllThreads(val$context);
      }
    }).start();
  }
  
  private void initPrivateState()
  {
    if (mRecipients.size() != 1)
    {
      setPrivate(false);
      return;
    }
    Contact localContact = (Contact)mRecipients.get(0);
    setPrivate(PrivatePreferenceActivity.checkPrivateMessage(mContext, localContact.getNumber()));
  }
  
  public static boolean isBlockedPlaceHolder(int paramInt)
  {
    return (paramInt & 0x1) == 1;
  }
  
  public static boolean isMipubPlaceHolder(int paramInt)
  {
    return (paramInt & 0x2) == 2;
  }
  
  private boolean loadFromThreadId(long paramLong)
  {
    if (mIsBlocked) {}
    for (Cursor localCursor = mContext.getContentResolver().query(ContentUris.withAppendedId(sBlockedUri, paramLong), ALL_BLOCKED_THREADS_PROJECTION, null, null, null); localCursor == null; localCursor = mContext.getContentResolver().query(sAllThreadsUri, ALL_THREADS_PROJECTION, "_id=" + Long.toString(paramLong), null, null))
    {
      LogTag.error("loadFromThreadId: Can't find thread ID %d", new Object[] { Long.valueOf(paramLong) });
      return false;
    }
    for (;;)
    {
      try
      {
        if (!localCursor.moveToFirst()) {
          break;
        }
        if (mIsBlocked)
        {
          fillFromBlockedCursor(mContext, this, localCursor);
          if (paramLong == mThreadId) {
            break label222;
          }
          throw new IllegalStateException("loadFromThreadId: fillFromCursor returned different thread_id! threadId=" + paramLong + ", mThreadId=" + mThreadId);
        }
      }
      finally
      {
        localCursor.close();
      }
      fillFromCursor(mContext, this, localCursor);
    }
    LogTag.error("loadFromThreadId: Can't find thread ID %d, no such record", new Object[] { Long.valueOf(paramLong) });
    localCursor.close();
    return false;
    label222:
    localCursor.close();
    return true;
  }
  
  public static void markAllConversationsAsSeen(Context paramContext)
  {
    if (sMarkAllConversationsAsSeenThread != null) {
      sMarkAllConversationsAsSeenThread.interrupt();
    }
    sMarkAllConversationsAsSeenThread = new Thread(new Runnable()
    {
      public void run()
      {
        long l1 = System.currentTimeMillis();
        if (Thread.currentThread().isInterrupted()) {}
        do
        {
          do
          {
            do
            {
              return;
              Conversation.blockingMarkAllSmsMessagesAsSeen(val$context);
            } while (Thread.currentThread().isInterrupted());
            Conversation.blockingMarkAllMmsMessagesAsSeen(val$context);
          } while (Thread.currentThread().isInterrupted());
          Conversation.blockingMarkAllMipubMessagesAsSeen(val$context);
          long l2 = System.currentTimeMillis();
          Log.d("Mms/conv", "update all seen: " + (l2 - l1) + "ms");
        } while (Thread.currentThread().isInterrupted());
        MessagingNotification.blockingUpdateAllNotifications(val$context);
      }
    });
    sMarkAllConversationsAsSeenThread.start();
  }
  
  public static void markBlockAsReadSync(Context paramContext)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("read", Integer.valueOf(1));
    paramContext.getContentResolver().update(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, localContentValues, null, null);
  }
  
  public static void markConversationAsNotified(Context paramContext)
  {
    if (sMarkConversationAsNotifiedThread != null) {
      sMarkConversationAsNotifiedThread.interrupt();
    }
    sMarkConversationAsNotifiedThread = new Thread(new Runnable()
    {
      public void run()
      {
        if (Thread.currentThread().isInterrupted()) {}
        for (;;)
        {
          return;
          ContentResolver localContentResolver = val$context.getContentResolver();
          long l = System.currentTimeMillis();
          int i = 0;
          localObject5 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, Conversation.COUNT_PROJECTION, "seen=0 AND advanced_seen=0", null, null);
          if (localObject5 != null) {}
          try
          {
            if (((Cursor)localObject5).moveToFirst()) {
              i = ((Cursor)localObject5).getInt(0);
            }
            ((Cursor)localObject5).close();
            if (Thread.currentThread().isInterrupted()) {
              continue;
            }
            if (i > 0)
            {
              localObject5 = new ContentValues(2);
              ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
              ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(3));
              localContentResolver.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen = 0 AND advanced_seen=0", null);
            }
            System.currentTimeMillis();
            Log.d("Mms/conv", "count:" + i + ",update notified non-sp sms:" + (System.currentTimeMillis() - l) + "ms");
            if (Thread.currentThread().isInterrupted()) {
              continue;
            }
            i = 0;
            l = System.currentTimeMillis();
            localObject5 = localContentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, Conversation.COUNT_PROJECTION, "seen=0 AND advanced_seen=0", null, null);
            if (localObject5 == null) {}
          }
          finally
          {
            try
            {
              if (((Cursor)localObject5).moveToFirst()) {
                i = ((Cursor)localObject5).getInt(0);
              }
              ((Cursor)localObject5).close();
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              if (i > 0)
              {
                localObject5 = new ContentValues(2);
                ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
                ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(3));
                localContentResolver.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen=0 AND advanced_seen=0", null);
              }
              Log.d("Mms/conv", "count:" + i + ",update notified non-sp mms:" + (System.currentTimeMillis() - l) + "ms");
              l = System.currentTimeMillis();
              i = 0;
              localObject5 = localContentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, Conversation.COUNT_PROJECTION, "seen=0 AND advanced_seen=1", null, null);
              if (localObject5 == null) {}
            }
            finally
            {
              ((Cursor)localObject5).close();
            }
            try
            {
              if (((Cursor)localObject5).moveToFirst()) {
                i = ((Cursor)localObject5).getInt(0);
              }
              ((Cursor)localObject5).close();
              if (i > 0)
              {
                localObject5 = new ContentValues(2);
                ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
                ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(2));
                localContentResolver.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen=0 AND advanced_seen=1", null);
              }
              Log.d("Mms/conv", "count:" + i + ",update notified sp sms:" + (System.currentTimeMillis() - l) + "ms");
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              l = System.currentTimeMillis();
              i = 0;
              localObject5 = localContentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, Conversation.COUNT_PROJECTION, "seen=0 AND advanced_seen=1", null, null);
              if (localObject5 == null) {}
            }
            finally
            {
              ((Cursor)localObject5).close();
            }
            try
            {
              if (((Cursor)localObject5).moveToFirst()) {
                i = ((Cursor)localObject5).getInt(0);
              }
              ((Cursor)localObject5).close();
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              if (i > 0)
              {
                localObject5 = new ContentValues(2);
                ((ContentValues)localObject5).put("seen", Integer.valueOf(1));
                ((ContentValues)localObject5).put("advanced_seen", Integer.valueOf(2));
                localContentResolver.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)localObject5, "seen=0 AND advanced_seen=1", null);
              }
              Log.d("Mms/conv", "count:" + i + ",update notified sp mms:" + (System.currentTimeMillis() - l) + "ms");
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              Conversation.blockingMarkAllHmsAsNotified(val$context);
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              l = System.currentTimeMillis();
              MessagingNotification.blockingUpdateAllNotifications(val$context);
              Log.d("Mms/conv", "update notified sp update all notification:" + (System.currentTimeMillis() - l) + "ms");
              return;
            }
            finally
            {
              ((Cursor)localObject5).close();
            }
            localObject1 = finally;
            ((Cursor)localObject5).close();
          }
        }
      }
    });
    sMarkConversationAsNotifiedThread.start();
  }
  
  public static void markMipubAsReadAsync(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.miui.mipub.MARK_ALL_MSG_READ");
    localIntent.setPackage("com.miui.mipub");
    paramContext.sendBroadcast(localIntent);
  }
  
  public static void markSPAsReadSync(Context paramContext, int paramInt)
  {
    if (paramInt <= 0) {}
    label156:
    do
    {
      return;
      if (paramInt == 1) {
        localObject1 = "read=0 and sp_type>=1 and stick_time=0";
      }
      for (;;)
      {
        localObject2 = nAllThreadsUri.buildUpon();
        localObject1 = paramContext.getContentResolver().query(((Uri.Builder)localObject2).appendQueryParameter("privacy_flag", "0").build(), UNREAD_PROJECTION, (String)localObject1, null, null);
        localObject3 = new ArrayList(8);
        if (localObject1 == null) {
          break label156;
        }
        try
        {
          while (((Cursor)localObject1).moveToNext())
          {
            long l = ((Cursor)localObject1).getLong(0);
            if (l > 0L) {
              ((List)localObject3).add(Long.valueOf(l));
            }
          }
          localObject1 = "read=0 and sp_type=" + paramInt + " and stick_time=0";
        }
        finally
        {
          ((Cursor)localObject1).close();
        }
      }
      ((Cursor)localObject1).close();
    } while (((List)localObject3).isEmpty());
    Object localObject1 = new ArrayList();
    Object localObject2 = new ContentValues(2);
    ((ContentValues)localObject2).put("read", Integer.valueOf(1));
    ((ContentValues)localObject2).put("seen", Integer.valueOf(1));
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Object localObject3 = ((List)localObject3).iterator();
    for (;;)
    {
      if (((Iterator)localObject3).hasNext())
      {
        Long localLong = (Long)((Iterator)localObject3).next();
        if (localLong.longValue() <= 0L) {
          continue;
        }
        ((ArrayList)localObject1).add(ContentProviderOperation.newUpdate(ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, localLong.longValue())).withValues((ContentValues)localObject2).withSelection("(read=0 OR seen=0)", null).build());
        if (((ArrayList)localObject1).size() < 50) {
          continue;
        }
        try
        {
          localContentResolver.applyBatch("mms-sms", (ArrayList)localObject1);
          ((ArrayList)localObject1).clear();
        }
        catch (RemoteException localRemoteException)
        {
          for (;;)
          {
            Log.d("Mms/conv", localRemoteException.getMessage());
          }
        }
        catch (OperationApplicationException localOperationApplicationException)
        {
          for (;;)
          {
            Log.d("Mms/conv", localOperationApplicationException.getMessage());
          }
        }
      }
    }
    if (!((ArrayList)localObject1).isEmpty()) {}
    try
    {
      localContentResolver.applyBatch("mms-sms", (ArrayList)localObject1);
      ((ArrayList)localObject1).clear();
      MessagingNotification.blockingUpdateNewMessageIndicator(paramContext, false, false);
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.d("Mms/conv", localException.getMessage());
      }
    }
  }
  
  public static void markServiceProviderConversationAsSeen(Context paramContext, final int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    if (sMarkServiceProviderConversationAsSeenThread != null) {
      sMarkServiceProviderConversationAsSeenThread.interrupt();
    }
    sMarkServiceProviderConversationAsSeenThread = new Thread(new Runnable()
    {
      public void run()
      {
        if (Thread.currentThread().isInterrupted()) {}
        for (;;)
        {
          return;
          ContentResolver localContentResolver = val$context.getContentResolver();
          long l = System.currentTimeMillis();
          int i = 0;
          Uri localUri = Telephony.Sms.Inbox.CONTENT_URI;
          if (paramInt > 1) {
            localUri = ContentUris.withAppendedId(Conversation.sSmsSeenUri, paramInt);
          }
          localObject3 = localContentResolver.query(localUri, Conversation.COUNT_PROJECTION, "(advanced_seen=1 OR advanced_seen=2)", null, null);
          if (localObject3 != null) {}
          try
          {
            if (((Cursor)localObject3).moveToFirst()) {
              i = ((Cursor)localObject3).getInt(0);
            }
            ((Cursor)localObject3).close();
            if (Thread.currentThread().isInterrupted()) {
              continue;
            }
            if (i > 0)
            {
              localObject3 = new ContentValues(2);
              ((ContentValues)localObject3).put("seen", Integer.valueOf(1));
              ((ContentValues)localObject3).put("advanced_seen", Integer.valueOf(3));
              localContentResolver.update(localUri, (ContentValues)localObject3, "(advanced_seen=1 OR advanced_seen=2)", null);
            }
            Log.d("Mms/conv", "count:" + i + ",update ad_seen sp sms:" + (System.currentTimeMillis() - l) + "ms");
            if (Thread.currentThread().isInterrupted()) {
              continue;
            }
            i = 0;
            l = System.currentTimeMillis();
            localUri = Telephony.Mms.Inbox.CONTENT_URI;
            if (paramInt > 1) {
              localUri = ContentUris.withAppendedId(Conversation.sMmsSeenUri, paramInt);
            }
            localObject3 = localContentResolver.query(localUri, Conversation.COUNT_PROJECTION, "(advanced_seen=1 OR advanced_seen=2)", null, null);
            if (localObject3 == null) {}
          }
          finally
          {
            try
            {
              if (((Cursor)localObject3).moveToFirst()) {
                i = ((Cursor)localObject3).getInt(0);
              }
              ((Cursor)localObject3).close();
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              if (i > 0)
              {
                localObject3 = new ContentValues(2);
                ((ContentValues)localObject3).put("seen", Integer.valueOf(1));
                ((ContentValues)localObject3).put("advanced_seen", Integer.valueOf(3));
                localContentResolver.update(localUri, (ContentValues)localObject3, "(advanced_seen=1 OR advanced_seen=2)", null);
              }
              Log.d("Mms/conv", "count:" + i + ",update ad_seen sp mms:" + (System.currentTimeMillis() - l) + "ms");
              if (Thread.currentThread().isInterrupted()) {
                continue;
              }
              l = System.currentTimeMillis();
              MessagingNotification.blockingUpdateAllNotifications(val$context);
              Log.d("Mms/conv", "update ad_seen sp update all notification:" + (System.currentTimeMillis() - l) + "ms");
              return;
            }
            finally
            {
              ((Cursor)localObject3).close();
            }
            localObject1 = finally;
            ((Cursor)localObject3).close();
          }
        }
      }
    });
    sMarkServiceProviderConversationAsSeenThread.start();
  }
  
  public static ArrayList<Conversation> searchForConversations(Context paramContext, String paramString, boolean paramBoolean)
  {
    return Cache.searchForConversations(paramContext, paramString, paramBoolean);
  }
  
  public static void startDelete(AsyncQueryHandler paramAsyncQueryHandler, int paramInt, boolean paramBoolean, Collection<Long> paramCollection)
  {
    if (paramBoolean) {}
    String str2;
    for (String str1 = null;; str1 = "locked=0")
    {
      str2 = "thread_id IN (" + TextUtils.join(",", paramCollection) + ")";
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Object localObject = (Long)paramCollection.next();
        localObject = ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, ((Long)localObject).longValue());
        PduCache.getInstance().purge((Uri)localObject);
      }
    }
    paramAsyncQueryHandler.startDelete(paramInt, null, Telephony.Threads.CONTENT_URI, DatabaseUtils.concatenateWhere(str2, str1), null);
  }
  
  public static void startDeleteAll(AsyncQueryHandler paramAsyncQueryHandler, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (String str = null;; str = "locked=0")
    {
      PduCache.getInstance().purge(Telephony.Threads.CONTENT_URI);
      paramAsyncQueryHandler.startDelete(paramInt, null, Telephony.Threads.CONTENT_URI, str, null);
      return;
    }
  }
  
  public static void startQueryForAll(AsyncQueryHandler paramAsyncQueryHandler, int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt1);
    Uri.Builder localBuilder = nAllThreadsUri.buildUpon();
    if (paramBoolean1) {}
    for (String str = "1";; str = "0")
    {
      localBuilder.appendQueryParameter("privacy_flag", str);
      if (paramBoolean2) {
        localBuilder.appendQueryParameter("threads_list_type", String.valueOf(0));
      }
      if (paramInt2 != 0) {
        localBuilder.appendQueryParameter("placeholder", String.valueOf(paramInt2));
      }
      paramAsyncQueryHandler.startQuery(paramInt1, null, localBuilder.build(), ALL_THREADS_PROJECTION, null, null, "stick_time desc, date DESC");
      return;
    }
  }
  
  public static void startQueryForServiceProvider(AsyncQueryHandler paramAsyncQueryHandler, int paramInt1, int paramInt2)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt1);
    paramAsyncQueryHandler.startQuery(paramInt1, null, nAllThreadsUri.buildUpon().appendQueryParameter("privacy_flag", "0").appendQueryParameter("threads_list_type", String.valueOf(paramInt2)).build(), ALL_THREADS_PROJECTION, null, null, "stick_time desc, date DESC");
  }
  
  public static void startQueryHaveLockedMessages(AsyncQueryHandler paramAsyncQueryHandler, long paramLong, int paramInt, boolean paramBoolean)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    Uri localUri2 = Telephony.MmsSms.CONTENT_LOCKED_URI;
    Uri localUri1;
    if (paramLong != -1L) {
      localUri1 = ContentUris.withAppendedId(localUri2, paramLong);
    }
    for (;;)
    {
      paramAsyncQueryHandler.startQuery(paramInt, new Long(paramLong), localUri1, ALL_THREADS_PROJECTION, null, null, "date DESC limit 1");
      return;
      localUri1 = localUri2;
      if (!paramBoolean) {
        localUri1 = localUri2.buildUpon().appendQueryParameter("privacy_flag", "0").build();
      }
    }
  }
  
  public static void startQueryHaveLockedMessages(AsyncQueryHandler paramAsyncQueryHandler, Collection<Long> paramCollection, int paramInt)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    String str = "thread_id IN (" + TextUtils.join(",", paramCollection) + ")";
    paramAsyncQueryHandler.startQuery(paramInt, paramCollection, Telephony.MmsSms.CONTENT_LOCKED_URI, ALL_THREADS_PROJECTION, str, null, "date DESC limit 1");
  }
  
  public static void startQuerySpSentinelId(AsyncQueryHandler paramAsyncQueryHandler, int paramInt)
  {
    paramAsyncQueryHandler.cancelOperation(paramInt);
    paramAsyncQueryHandler.startQuery(paramInt, null, nAllThreadsUri.buildUpon().appendQueryParameter("privacy_flag", "0").build(), THREAD_ID, "(sp_type=1 AND stick_time=0)", null, "stick_time desc, date DESC limit 1");
  }
  
  public static void startUpdateThreads(AsyncQueryHandler paramAsyncQueryHandler, int paramInt, Set<Long> paramSet, ContentValues paramContentValues)
  {
    paramAsyncQueryHandler.startUpdate(paramInt, null, Telephony.Threads.CONTENT_URI, paramContentValues, "_id in (" + TextUtils.join(",", paramSet) + ")", null);
  }
  
  public static void updateCachedThreadStickTime(Set<Long> paramSet, final long paramLong)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = val$threadIds.iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = (Long)localIterator.next();
          synchronized (Conversation.Cache.getInstance())
          {
            localObject2 = Conversation.Cache.get(((Long)localObject2).longValue());
            if (localObject2 != null) {
              Conversation.access$302((Conversation)localObject2, paramLong);
            }
          }
        }
      }
    }).start();
  }
  
  public static String verifySingleRecipient(Context paramContext, long paramLong, String paramString)
  {
    if (paramLong <= 0L) {}
    for (;;)
    {
      return paramString;
      Object localObject = paramContext.getContentResolver().query(sAllThreadsUri, ALL_THREADS_PROJECTION, "_id=" + Long.toString(paramLong), null, null);
      if (localObject == null)
      {
        LogTag.error("verifySingleRecipient threadId: " + paramLong + " resulted in NULL cursor", new Object[0]);
        return paramString;
      }
      try
      {
        if (!((Cursor)localObject).moveToFirst())
        {
          LogTag.error("verifySingleRecipient threadId: " + paramLong + " can't moveToFirst", new Object[0]);
          return paramString;
        }
        String str = ((Cursor)localObject).getString(4);
        ((Cursor)localObject).close();
        localObject = str.split(" ");
        if (localObject.length != 1) {
          continue;
        }
        localObject = RecipientIdCache.getSingleAddressFromCanonicalAddressInDb(paramContext, localObject[0]);
        if ((TextUtils.isEmpty((CharSequence)localObject)) || (PhoneNumberUtils.compareLoosely(paramString, (String)localObject))) {
          continue;
        }
        if ((paramContext instanceof Activity)) {
          LogTag.warnPossibleRecipientMismatch("verifySingleRecipient for threadId: " + paramLong, (Activity)paramContext);
        }
        if (Log.isLoggable("Mms:threadcache", 2)) {
          LogTag.debug("verifySingleRecipient for threadId: " + paramLong, new Object[0]);
        }
        return (String)localObject;
      }
      finally
      {
        ((Cursor)localObject).close();
      }
    }
  }
  
  public void clearThreadId()
  {
    try
    {
      Cache.remove(mThreadId);
      mThreadId = 0L;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long ensureThreadId()
  {
    try
    {
      if ((mThreadId <= 0L) || (mMessageCount == 0)) {
        mThreadId = getOrCreateThreadId(mContext, mRecipients);
      }
      long l = mThreadId;
      return l;
    }
    finally {}
  }
  
  public boolean equals(Object paramObject)
  {
    try
    {
      paramObject = (Conversation)paramObject;
      bool = mRecipients.equals(mRecipients);
      return bool;
    }
    catch (ClassCastException paramObject)
    {
      for (;;)
      {
        paramObject = paramObject;
        boolean bool = false;
      }
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
  
  /* Error */
  public Uri getBlockedUri()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +9 -> 19
    //   13: aconst_null
    //   14: astore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_3
    //   18: areturn
    //   19: getstatic 972	miui/provider/ExtraTelephony$MmsSms:BLOCKED_THREAD_CONTENT_URI	Landroid/net/Uri;
    //   22: aload_0
    //   23: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   26: invokestatic 711	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   29: astore_3
    //   30: goto -15 -> 15
    //   33: astore_3
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_3
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	Conversation
    //   6	2	1	l	long
    //   14	16	3	localUri	Uri
    //   33	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   19	30	33	finally
  }
  
  public String getContactNames()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = mRecipients.iterator();
    while (localIterator.hasNext())
    {
      Contact localContact = (Contact)localIterator.next();
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append(localContact.getName());
    }
    return localStringBuilder.toString();
  }
  
  public long getDate()
  {
    try
    {
      long l = mDate;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getDateString()
  {
    try
    {
      String str = mDateString;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getLastSimId()
  {
    try
    {
      long l = mLastSimId;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getMessageCount()
  {
    try
    {
      int i = mMessageCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public long getPostMarkUnread()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 201	com/android/mms/data/Conversation:mPostMarkUnreadObj	Ljava/lang/Object;
    //   6: astore_3
    //   7: aload_3
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield 985	com/android/mms/data/Conversation:mPostMarkUnread	J
    //   13: lstore_1
    //   14: aload_3
    //   15: monitorexit
    //   16: aload_0
    //   17: monitorexit
    //   18: lload_1
    //   19: lreturn
    //   20: astore 4
    //   22: aload_3
    //   23: monitorexit
    //   24: aload 4
    //   26: athrow
    //   27: astore_3
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_3
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	Conversation
    //   13	6	1	l	long
    //   27	4	3	localObject2	Object
    //   20	5	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	16	20	finally
    //   22	24	20	finally
    //   2	9	27	finally
    //   24	27	27	finally
  }
  
  public boolean getPreMarkUnread()
  {
    return mPreMarkUnread;
  }
  
  public ContactList getRecipients()
  {
    try
    {
      ContactList localContactList = mRecipients;
      return localContactList;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public String getSnippet()
  {
    try
    {
      String str = mSnippet;
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public long getThreadId()
  {
    try
    {
      long l = mThreadId;
      return l;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int getUnreadMessageCount()
  {
    try
    {
      int i = mUnreadMessageCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public Uri getUri()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +9 -> 19
    //   13: aconst_null
    //   14: astore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_3
    //   18: areturn
    //   19: getstatic 79	android/provider/Telephony$Threads:CONTENT_URI	Landroid/net/Uri;
    //   22: aload_0
    //   23: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   26: invokestatic 711	android/content/ContentUris:withAppendedId	(Landroid/net/Uri;J)Landroid/net/Uri;
    //   29: astore_3
    //   30: goto -15 -> 15
    //   33: astore_3
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_3
    //   37: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	38	0	this	Conversation
    //   6	2	1	l	long
    //   14	16	3	localUri	Uri
    //   33	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   19	30	33	finally
  }
  
  public boolean hasAttachment()
  {
    try
    {
      boolean bool = mHasAttachment;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public boolean hasDraft()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   6: lstore_1
    //   7: lload_1
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +9 -> 19
    //   13: iconst_0
    //   14: istore_3
    //   15: aload_0
    //   16: monitorexit
    //   17: iload_3
    //   18: ireturn
    //   19: invokestatic 998	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   22: aload_0
    //   23: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   26: invokevirtual 1000	com/android/mms/util/DraftCache:hasDraft	(J)Z
    //   29: istore_3
    //   30: goto -15 -> 15
    //   33: astore 4
    //   35: aload_0
    //   36: monitorexit
    //   37: aload 4
    //   39: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	40	0	this	Conversation
    //   6	2	1	l	long
    //   14	16	3	bool	boolean
    //   33	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   19	30	33	finally
  }
  
  public boolean hasError()
  {
    try
    {
      boolean bool = mHasError;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = mRecipients.hashCode();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isB2c()
  {
    try
    {
      boolean bool = mIsB2cConv;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isChecked()
  {
    try
    {
      boolean bool = mIsChecked;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isPrivate()
  {
    try
    {
      boolean bool = mPrivate;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isServiceProvider()
  {
    try
    {
      boolean bool = mIsServiceProvider;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean isSticky()
  {
    return mStickTime > 0L;
  }
  
  public void loadRecipients(boolean paramBoolean1, boolean paramBoolean2)
  {
    mRecipients.load(paramBoolean1, paramBoolean2);
  }
  
  public void markAsReadSync()
  {
    for (;;)
    {
      synchronized (mMarkAsBlockedSyncer)
      {
        Uri localUri = getUri();
        Cursor localCursor;
        if (localUri != null)
        {
          buildReadContentValues();
          i = 1;
          localCursor = mContext.getContentResolver().query(localUri, UNREAD_PROJECTION, "(read=0 OR seen=0)", null, null);
          if (localCursor == null) {}
        }
        try
        {
          i = localCursor.getCount();
          if (i > 0)
          {
            i = 1;
            localCursor.close();
            if (i != 0)
            {
              LogTag.debug("markAsRead: update read/seen for thread uri: %s", new Object[] { localUri });
              mContext.getContentResolver().update(localUri, mReadContentValues, "(read=0 OR seen=0)", null);
            }
            setPostMarkUnread(System.currentTimeMillis());
            if (i != 0) {
              MessagingNotification.blockingUpdateNewMessageIndicator(mContext, false, false);
            }
            return;
          }
        }
        finally
        {
          localObject3 = finally;
          localCursor.close();
          throw ((Throwable)localObject3);
        }
      }
      int i = 0;
    }
  }
  
  /* Error */
  public void setDraftState(boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   6: lstore_2
    //   7: lload_2
    //   8: lconst_0
    //   9: lcmp
    //   10: ifgt +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: invokestatic 998	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   19: aload_0
    //   20: getfield 218	com/android/mms/data/Conversation:mThreadId	J
    //   23: iload_1
    //   24: invokevirtual 1038	com/android/mms/util/DraftCache:setDraftState	(JZ)V
    //   27: goto -14 -> 13
    //   30: astore 4
    //   32: aload_0
    //   33: monitorexit
    //   34: aload 4
    //   36: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	this	Conversation
    //   0	37	1	paramBoolean	boolean
    //   6	2	2	l	long
    //   30	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	7	30	finally
    //   16	27	30	finally
  }
  
  public void setIsChecked(boolean paramBoolean)
  {
    try
    {
      mIsChecked = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void setPostMarkUnread(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 201	com/android/mms/data/Conversation:mPostMarkUnreadObj	Ljava/lang/Object;
    //   6: astore_3
    //   7: aload_3
    //   8: monitorenter
    //   9: aload_0
    //   10: lload_1
    //   11: putfield 985	com/android/mms/data/Conversation:mPostMarkUnread	J
    //   14: aload_3
    //   15: monitorexit
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore 4
    //   21: aload_3
    //   22: monitorexit
    //   23: aload 4
    //   25: athrow
    //   26: astore_3
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_3
    //   30: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	31	0	this	Conversation
    //   0	31	1	paramLong	long
    //   26	4	3	localObject2	Object
    //   19	5	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   9	16	19	finally
    //   21	23	19	finally
    //   2	9	26	finally
    //   23	26	26	finally
  }
  
  public void setPreMarkUnread(boolean paramBoolean)
  {
    mPreMarkUnread = paramBoolean;
  }
  
  public void setPrivate(boolean paramBoolean)
  {
    try
    {
      mPrivate = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setRecipients(ContactList paramContactList)
  {
    try
    {
      if (mInCache) {
        throw new IllegalStateException(String.format("Unexpected recipient change in cached conversation %d", new Object[] { Long.valueOf(mThreadId) }));
      }
    }
    finally {}
    mRecipients = paramContactList;
    initPrivateState();
    mThreadId = 0L;
  }
  
  public String toString()
  {
    try
    {
      String str = String.format("%s (tid %d)", new Object[] { mRecipients.toString(), Long.valueOf(mThreadId) });
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void update()
  {
    if (mThreadId <= 0L) {
      return;
    }
    loadFromThreadId(mThreadId);
  }
  
  private static class Cache
  {
    private static Cache sInstance = new Cache();
    private final HashMap<ContactList, Conversation> mRecipientsConversationMap = new HashMap(10);
    private final HashMap<Long, Conversation> mThreadIdConversationMap = new HashMap(10);
    
    static Conversation get(long paramLong)
    {
      synchronized (sInstance)
      {
        Conversation localConversation = (Conversation)sInstancemThreadIdConversationMap.get(Long.valueOf(paramLong));
        return localConversation;
      }
    }
    
    static Conversation get(ContactList paramContactList)
    {
      synchronized (sInstance)
      {
        paramContactList = (Conversation)sInstancemRecipientsConversationMap.get(paramContactList);
        return paramContactList;
      }
    }
    
    public static Cache getInstance()
    {
      return sInstance;
    }
    
    static void keepOnly(Set<Long> paramSet)
    {
      ArrayList localArrayList;
      synchronized (sInstance)
      {
        localArrayList = new ArrayList();
        Iterator localIterator = sInstancemThreadIdConversationMap.values().iterator();
        while (localIterator.hasNext())
        {
          Conversation localConversation = (Conversation)localIterator.next();
          if (!paramSet.contains(Long.valueOf(localConversation.getThreadId()))) {
            localArrayList.add(Long.valueOf(localConversation.getThreadId()));
          }
        }
      }
      paramSet = localArrayList.iterator();
      while (paramSet.hasNext()) {
        remove(((Long)paramSet.next()).longValue());
      }
    }
    
    static void put(Conversation paramConversation)
    {
      synchronized (sInstance)
      {
        if (paramConversation.getThreadId() == 0L) {
          throw new IllegalStateException("cannot put a conversation with threadId = 0 into cache");
        }
      }
      if (paramConversation.getThreadId() < 0L)
      {
        localConversation = (Conversation)sInstancemThreadIdConversationMap.get(Long.valueOf(paramConversation.getThreadId()));
        if ((localConversation != null) && (localConversation != paramConversation)) {
          LogTag.error("cache already contains conversation for thread %d", new Object[] { Long.valueOf(paramConversation.getThreadId()) });
        }
        sInstancemThreadIdConversationMap.put(Long.valueOf(paramConversation.getThreadId()), paramConversation);
        Conversation.access$002(paramConversation, true);
        return;
      }
      Conversation localConversation = (Conversation)sInstancemThreadIdConversationMap.get(Long.valueOf(paramConversation.getThreadId()));
      if ((localConversation != null) && (localConversation != paramConversation)) {
        LogTag.error("cache already contains conversation for thread %d", new Object[] { Long.valueOf(paramConversation.getThreadId()) });
      }
      localConversation = (Conversation)sInstancemRecipientsConversationMap.get(paramConversation.getRecipients());
      if ((localConversation != null) && (localConversation != paramConversation)) {
        LogTag.error("cache already contains conversation for recipeints %s", new Object[] { paramConversation.getRecipients() });
      }
      sInstancemThreadIdConversationMap.put(Long.valueOf(paramConversation.getThreadId()), paramConversation);
      sInstancemRecipientsConversationMap.put(paramConversation.getRecipients(), paramConversation);
      Conversation.access$002(paramConversation, true);
    }
    
    static void remove(long paramLong)
    {
      synchronized (sInstance)
      {
        Conversation localConversation = (Conversation)sInstancemThreadIdConversationMap.get(Long.valueOf(paramLong));
        if (localConversation == null) {
          LogTag.debug("Failed removing thread %d", new Object[] { Long.valueOf(paramLong) });
        }
        do
        {
          return;
          Conversation.access$002(localConversation, false);
          if (sInstancemRecipientsConversationMap.remove(localConversation.getRecipients()) == null) {
            LogTag.error("Failed removing thread %d from mRecipientsConversationMap", new Object[] { Long.valueOf(paramLong) });
          }
        } while (sInstancemThreadIdConversationMap.remove(Long.valueOf(paramLong)) != null);
        LogTag.error("Failed removing thread %d from mThreadIdConversationMap", new Object[] { Long.valueOf(paramLong) });
      }
    }
    
    static boolean replace(Conversation paramConversation)
    {
      synchronized (sInstance)
      {
        if (sInstancemRecipientsConversationMap.remove(paramConversation.getRecipients()) != null) {
          sInstancemRecipientsConversationMap.put(paramConversation.getRecipients(), paramConversation);
        }
        if (sInstancemThreadIdConversationMap.remove(Long.valueOf(paramConversation.getThreadId())) != null) {
          sInstancemThreadIdConversationMap.put(Long.valueOf(paramConversation.getThreadId()), paramConversation);
        }
        return true;
      }
    }
    
    static ArrayList<Conversation> searchForConversations(Context paramContext, String arg1, boolean paramBoolean)
    {
      Object localObject2 = ???.toLowerCase();
      ArrayList localArrayList = Lists.newArrayList();
      ??? = Lists.newArrayList();
      synchronized (sInstance)
      {
        Iterator localIterator = sInstancemThreadIdConversationMap.values().iterator();
        while (localIterator.hasNext())
        {
          Conversation localConversation = (Conversation)localIterator.next();
          Object localObject3 = localConversation.getRecipients();
          if (((ContactList)localObject3).size() == 1)
          {
            localObject3 = (Contact)((ContactList)localObject3).get(0);
            if (((!localConversation.isB2c()) && (((Contact)localObject3).getNumber().toLowerCase().indexOf((String)localObject2) != -1)) || (((Contact)localObject3).getName().toLowerCase().indexOf((String)localObject2) != -1)) {
              ???.add(Long.valueOf(localConversation.getThreadId()));
            }
          }
        }
      }
      if (???.size() - 1 >= 0)
      {
        ??? = new StringBuilder();
        ((StringBuilder)???).append("_id IN ( " + TextUtils.join(",", ???) + ")");
        localObject2 = Conversation.nAllThreadsUri.buildUpon();
        if (paramBoolean) {
          ??? = "1";
        }
        for (;;)
        {
          ??? = ((Uri.Builder)localObject2).appendQueryParameter("privacy_flag", ???).build();
          paramContext = paramContext.getContentResolver();
          ??? = ((StringBuilder)???).toString();
          paramContext = paramContext.query(???, new String[] { "_id" }, (String)???, null, null);
          if (paramContext == null) {
            break label359;
          }
          try
          {
            paramContext.moveToPosition(-1);
            while (paramContext.moveToNext()) {
              synchronized (sInstance)
              {
                ??? = (Conversation)sInstancemThreadIdConversationMap.get(Long.valueOf(paramContext.getLong(0)));
                if (??? != null) {
                  localArrayList.add(???);
                }
              }
            }
            ??? = "0";
          }
          finally
          {
            paramContext.close();
          }
        }
        paramContext.close();
      }
      label359:
      Collections.sort(localList, new Comparator()
      {
        public int compare(Conversation paramAnonymousConversation1, Conversation paramAnonymousConversation2)
        {
          return Long.signum(paramAnonymousConversation2.getDate() - paramAnonymousConversation1.getDate());
        }
      });
      return localList;
    }
  }
  
  public static final class TimedThreads
  {
    private static final HashSet<Long> mTimedThreads = ;
    
    public static void clear()
    {
      mTimedThreads.clear();
    }
    
    public static boolean hasTimedMessage(long paramLong)
    {
      return mTimedThreads.contains(Long.valueOf(paramLong));
    }
    
    public static void setHasTimedMessage(long paramLong)
    {
      mTimedThreads.add(Long.valueOf(paramLong));
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */