package com.android.mms.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.android.mms.LogTag;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class Conversation$Cache
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
      localObject2 = Conversation.access$100().buildUpon();
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

/* Location:
 * Qualified Name:     com.android.mms.data.Conversation.Cache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */