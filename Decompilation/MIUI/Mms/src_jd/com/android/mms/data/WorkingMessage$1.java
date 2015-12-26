package com.android.mms.data;

class WorkingMessage$1
  implements Runnable
{
  WorkingMessage$1(WorkingMessage paramWorkingMessage, Conversation paramConversation, boolean paramBoolean) {}
  
  /* Error */
  public void run()
  {
    // Byte code:
    //   0: invokestatic 36	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   3: iconst_1
    //   4: invokevirtual 40	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   7: aload_0
    //   8: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   11: getfield 44	com/android/mms/data/WorkingMessage:mContext	Landroid/content/Context;
    //   14: invokestatic 50	com/google/android/mms/pdu/MiuiPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/google/android/mms/pdu/MiuiPduPersister;
    //   17: astore_1
    //   18: aload_0
    //   19: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
    //   22: aload_0
    //   23: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   26: getfield 54	com/android/mms/data/WorkingMessage:mSubject	Ljava/lang/CharSequence;
    //   29: invokestatic 58	com/android/mms/data/WorkingMessage:access$100	(Lcom/android/mms/data/Conversation;Ljava/lang/CharSequence;)Lcom/google/android/mms/pdu/SendReq;
    //   32: astore_2
    //   33: aload_0
    //   34: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   37: getfield 62	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   40: ifnonnull +87 -> 127
    //   43: aload_0
    //   44: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   47: aload_1
    //   48: aload_2
    //   49: aload_0
    //   50: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   53: getfield 66	com/android/mms/data/WorkingMessage:mSlideshow	Lcom/android/mms/model/SlideshowModel;
    //   56: invokestatic 70	com/android/mms/data/WorkingMessage:access$500	(Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/google/android/mms/pdu/SendReq;Lcom/android/mms/model/SlideshowModel;)Landroid/net/Uri;
    //   59: putfield 62	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   62: aload_0
    //   63: getfield 25	com/android/mms/data/WorkingMessage$1:val$isStopping	Z
    //   66: ifeq +20 -> 86
    //   69: aload_0
    //   70: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
    //   73: invokevirtual 76	com/android/mms/data/Conversation:getMessageCount	()I
    //   76: ifne +10 -> 86
    //   79: aload_0
    //   80: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
    //   83: invokevirtual 79	com/android/mms/data/Conversation:clearThreadId	()V
    //   86: aload_0
    //   87: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   90: aload_0
    //   91: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
    //   94: invokevirtual 83	com/android/mms/data/Conversation:ensureThreadId	()J
    //   97: invokestatic 87	com/android/mms/data/WorkingMessage:access$800	(Lcom/android/mms/data/WorkingMessage;J)V
    //   100: aload_0
    //   101: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
    //   104: iconst_1
    //   105: invokevirtual 90	com/android/mms/data/Conversation:setDraftState	(Z)V
    //   108: aload_0
    //   109: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   112: aload_0
    //   113: getfield 23	com/android/mms/data/WorkingMessage$1:val$conv	Lcom/android/mms/data/Conversation;
    //   116: invokevirtual 94	com/android/mms/data/WorkingMessage:asyncDeleteDraftSmsMessage	(Lcom/android/mms/data/Conversation;)V
    //   119: invokestatic 36	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   122: iconst_0
    //   123: invokevirtual 40	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   126: return
    //   127: aload_0
    //   128: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   131: aload_0
    //   132: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   135: getfield 62	com/android/mms/data/WorkingMessage:mMessageUri	Landroid/net/Uri;
    //   138: aload_1
    //   139: aload_0
    //   140: getfield 21	com/android/mms/data/WorkingMessage$1:this$0	Lcom/android/mms/data/WorkingMessage;
    //   143: getfield 66	com/android/mms/data/WorkingMessage:mSlideshow	Lcom/android/mms/model/SlideshowModel;
    //   146: aload_2
    //   147: invokestatic 98	com/android/mms/data/WorkingMessage:access$600	(Lcom/android/mms/data/WorkingMessage;Landroid/net/Uri;Lcom/google/android/mms/pdu/MiuiPduPersister;Lcom/android/mms/model/SlideshowModel;Lcom/google/android/mms/pdu/SendReq;)V
    //   150: goto -88 -> 62
    //   153: astore_1
    //   154: invokestatic 36	com/android/mms/util/DraftCache:getInstance	()Lcom/android/mms/util/DraftCache;
    //   157: iconst_0
    //   158: invokevirtual 40	com/android/mms/util/DraftCache:setSavingDraft	(Z)V
    //   161: aload_1
    //   162: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	this	1
    //   17	122	1	localMiuiPduPersister	com.google.android.mms.pdu.MiuiPduPersister
    //   153	9	1	localObject	Object
    //   32	115	2	localSendReq	com.google.android.mms.pdu.SendReq
    // Exception table:
    //   from	to	target	type
    //   0	62	153	finally
    //   62	86	153	finally
    //   86	119	153	finally
    //   127	150	153	finally
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.WorkingMessage.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */