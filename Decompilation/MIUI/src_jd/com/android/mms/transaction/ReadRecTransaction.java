package com.android.mms.transaction;

import android.content.Context;
import android.net.Uri;

public class ReadRecTransaction
  extends Transaction
{
  private final Uri mReadReportURI;
  
  public ReadRecTransaction(Context paramContext, TransactionSettings paramTransactionSettings, String paramString)
  {
    super(paramContext, paramTransactionSettings);
    mReadReportURI = Uri.parse(paramString);
    mTransactionState.setContentUri(mReadReportURI);
    mId = paramString;
  }
  
  public int getType()
  {
    return 3;
  }
  
  /* Error */
  public void process()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 48	com/android/mms/transaction/ReadRecTransaction:mContext	Landroid/content/Context;
    //   4: invokestatic 54	com/google/android/mms/pdu/MiuiPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/google/android/mms/pdu/MiuiPduPersister;
    //   7: astore 4
    //   9: aload 4
    //   11: aload_0
    //   12: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   15: invokevirtual 58	com/google/android/mms/pdu/MiuiPduPersister:load	(Landroid/net/Uri;)Lcom/google/android/mms/pdu/GenericPdu;
    //   18: checkcast 60	com/google/android/mms/pdu/ReadRecInd
    //   21: astore 5
    //   23: aload_0
    //   24: getfield 64	com/android/mms/transaction/ReadRecTransaction:mSimId	J
    //   27: invokestatic 70	com/android/mms/util/MSimUtils:getSlotIdBySimInfoId	(J)I
    //   30: istore_1
    //   31: invokestatic 76	miui/telephony/TelephonyManager:getDefault	()Lmiui/telephony/TelephonyManager;
    //   34: iload_1
    //   35: invokevirtual 80	miui/telephony/TelephonyManager:getLine1NumberForSlot	(I)Ljava/lang/String;
    //   38: astore_3
    //   39: aload_3
    //   40: astore_2
    //   41: aload_3
    //   42: invokestatic 86	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   45: ifeq +14 -> 59
    //   48: ldc 88
    //   50: ldc 90
    //   52: invokestatic 96	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: ldc 98
    //   58: astore_2
    //   59: aload 5
    //   61: new 100	com/google/android/mms/pdu/EncodedStringValue
    //   64: dup
    //   65: aload_2
    //   66: invokespecial 103	com/google/android/mms/pdu/EncodedStringValue:<init>	(Ljava/lang/String;)V
    //   69: invokevirtual 107	com/google/android/mms/pdu/ReadRecInd:setFrom	(Lcom/google/android/mms/pdu/EncodedStringValue;)V
    //   72: aload_0
    //   73: new 109	com/google/android/mms/pdu/PduComposer
    //   76: dup
    //   77: aload_0
    //   78: getfield 48	com/android/mms/transaction/ReadRecTransaction:mContext	Landroid/content/Context;
    //   81: aload 5
    //   83: invokespecial 112	com/google/android/mms/pdu/PduComposer:<init>	(Landroid/content/Context;Lcom/google/android/mms/pdu/GenericPdu;)V
    //   86: invokevirtual 116	com/google/android/mms/pdu/PduComposer:make	()[B
    //   89: aconst_null
    //   90: invokevirtual 120	com/android/mms/transaction/ReadRecTransaction:sendPdu	([BLcom/android/mms/transaction/ProgressReceiver;)[B
    //   93: pop
    //   94: aload 4
    //   96: aload_0
    //   97: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   100: getstatic 125	android/provider/Telephony$Mms$Sent:CONTENT_URI	Landroid/net/Uri;
    //   103: invokevirtual 129	com/google/android/mms/pdu/MiuiPduPersister:move	(Landroid/net/Uri;Landroid/net/Uri;)Landroid/net/Uri;
    //   106: astore_2
    //   107: aload_0
    //   108: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   111: iconst_1
    //   112: invokevirtual 133	com/android/mms/transaction/TransactionState:setState	(I)V
    //   115: aload_0
    //   116: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   119: aload_2
    //   120: invokevirtual 29	com/android/mms/transaction/TransactionState:setContentUri	(Landroid/net/Uri;)V
    //   123: aload_0
    //   124: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   127: invokevirtual 136	com/android/mms/transaction/TransactionState:getState	()I
    //   130: iconst_1
    //   131: if_icmpeq +22 -> 153
    //   134: aload_0
    //   135: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   138: iconst_2
    //   139: invokevirtual 133	com/android/mms/transaction/TransactionState:setState	(I)V
    //   142: aload_0
    //   143: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   146: aload_0
    //   147: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   150: invokevirtual 29	com/android/mms/transaction/TransactionState:setContentUri	(Landroid/net/Uri;)V
    //   153: aload_0
    //   154: invokevirtual 139	com/android/mms/transaction/ReadRecTransaction:notifyObservers	()V
    //   157: return
    //   158: astore_2
    //   159: aload_0
    //   160: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   163: invokevirtual 136	com/android/mms/transaction/TransactionState:getState	()I
    //   166: iconst_1
    //   167: if_icmpeq +22 -> 189
    //   170: aload_0
    //   171: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   174: iconst_2
    //   175: invokevirtual 133	com/android/mms/transaction/TransactionState:setState	(I)V
    //   178: aload_0
    //   179: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   182: aload_0
    //   183: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   186: invokevirtual 29	com/android/mms/transaction/TransactionState:setContentUri	(Landroid/net/Uri;)V
    //   189: aload_0
    //   190: invokevirtual 139	com/android/mms/transaction/ReadRecTransaction:notifyObservers	()V
    //   193: aload_2
    //   194: athrow
    //   195: astore_2
    //   196: aload_0
    //   197: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   200: invokevirtual 136	com/android/mms/transaction/TransactionState:getState	()I
    //   203: iconst_1
    //   204: if_icmpeq -51 -> 153
    //   207: aload_0
    //   208: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   211: iconst_2
    //   212: invokevirtual 133	com/android/mms/transaction/TransactionState:setState	(I)V
    //   215: aload_0
    //   216: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   219: aload_0
    //   220: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   223: invokevirtual 29	com/android/mms/transaction/TransactionState:setContentUri	(Landroid/net/Uri;)V
    //   226: goto -73 -> 153
    //   229: astore_2
    //   230: aload_0
    //   231: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   234: invokevirtual 136	com/android/mms/transaction/TransactionState:getState	()I
    //   237: iconst_1
    //   238: if_icmpeq -85 -> 153
    //   241: aload_0
    //   242: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   245: iconst_2
    //   246: invokevirtual 133	com/android/mms/transaction/TransactionState:setState	(I)V
    //   249: aload_0
    //   250: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   253: aload_0
    //   254: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   257: invokevirtual 29	com/android/mms/transaction/TransactionState:setContentUri	(Landroid/net/Uri;)V
    //   260: goto -107 -> 153
    //   263: astore_2
    //   264: aload_0
    //   265: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   268: invokevirtual 136	com/android/mms/transaction/TransactionState:getState	()I
    //   271: iconst_1
    //   272: if_icmpeq -119 -> 153
    //   275: aload_0
    //   276: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   279: iconst_2
    //   280: invokevirtual 133	com/android/mms/transaction/TransactionState:setState	(I)V
    //   283: aload_0
    //   284: getfield 23	com/android/mms/transaction/ReadRecTransaction:mTransactionState	Lcom/android/mms/transaction/TransactionState;
    //   287: aload_0
    //   288: getfield 19	com/android/mms/transaction/ReadRecTransaction:mReadReportURI	Landroid/net/Uri;
    //   291: invokevirtual 29	com/android/mms/transaction/TransactionState:setContentUri	(Landroid/net/Uri;)V
    //   294: goto -141 -> 153
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	297	0	this	ReadRecTransaction
    //   30	5	1	i	int
    //   40	80	2	localObject1	Object
    //   158	36	2	localObject2	Object
    //   195	1	2	localIOException	java.io.IOException
    //   229	1	2	localMmsException	com.google.android.mms.MmsException
    //   263	1	2	localRuntimeException	RuntimeException
    //   38	4	3	str	String
    //   7	88	4	localMiuiPduPersister	com.google.android.mms.pdu.MiuiPduPersister
    //   21	61	5	localReadRecInd	com.google.android.mms.pdu.ReadRecInd
    // Exception table:
    //   from	to	target	type
    //   9	39	158	finally
    //   41	56	158	finally
    //   59	123	158	finally
    //   9	39	195	java/io/IOException
    //   41	56	195	java/io/IOException
    //   59	123	195	java/io/IOException
    //   9	39	229	com/google/android/mms/MmsException
    //   41	56	229	com/google/android/mms/MmsException
    //   59	123	229	com/google/android/mms/MmsException
    //   9	39	263	java/lang/RuntimeException
    //   41	56	263	java/lang/RuntimeException
    //   59	123	263	java/lang/RuntimeException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.ReadRecTransaction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */