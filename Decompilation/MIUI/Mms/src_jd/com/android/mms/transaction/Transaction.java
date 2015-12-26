package com.android.mms.transaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.util.Pair;
import com.google.android.mms.MmsException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class Transaction
  extends Observable
{
  private static long mTokenGenerator = 1L;
  protected static volatile long sCurrentTransactionMsgId;
  protected static volatile int sCurrentTransactionProgress;
  protected Context mContext;
  protected String mId;
  protected long mSimId;
  protected long mToken;
  protected TransactionSettings mTransactionSettings;
  protected TransactionState mTransactionState;
  
  public Transaction(Context paramContext, TransactionSettings paramTransactionSettings)
  {
    mContext = paramContext;
    mTransactionState = new TransactionState();
    mTransactionSettings = paramTransactionSettings;
  }
  
  private void ensureRouteToHost(String paramString, TransactionSettings paramTransactionSettings)
    throws IOException
  {
    int i;
    if (paramTransactionSettings.isProxySet())
    {
      i = lookupHost(paramTransactionSettings.getProxyAddress());
      if (i == -1) {
        throw new IOException("Cannot establish route for " + paramString + ": Unknown host");
      }
      if (!((ConnectivityManager)mContext.getSystemService("connectivity")).requestRouteToHost(2, i)) {
        throw new IOException("Cannot establish route to proxy " + i);
      }
    }
    else
    {
      i = lookupHost(Uri.parse(paramString).getHost());
      if (i == -1) {
        throw new IOException("Cannot establish route for " + paramString + ": Unknown host");
      }
      if (!((ConnectivityManager)mContext.getSystemService("connectivity")).requestRouteToHost(2, i)) {
        throw new IOException("Cannot establish route to " + i + " for " + paramString);
      }
    }
  }
  
  public static long getCurrentTransactionMsgId()
  {
    return sCurrentTransactionMsgId;
  }
  
  public static int getCurrentTransactionProgress()
  {
    return sCurrentTransactionProgress;
  }
  
  public static int lookupHost(String paramString)
  {
    try
    {
      paramString = InetAddress.getByName(paramString);
      paramString = paramString.getAddress();
      return (paramString[3] & 0xFF) << 24 | (paramString[2] & 0xFF) << 16 | (paramString[1] & 0xFF) << 8 | paramString[0] & 0xFF;
    }
    catch (UnknownHostException paramString) {}
    return -1;
  }
  
  public boolean abort()
  {
    return HttpUtils.abortHttpConnection(mToken);
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Transaction)) {
      return false;
    }
    return isEquivalent((Transaction)paramObject);
  }
  
  protected byte[] getPdu(String paramString, ProgressReceiver paramProgressReceiver)
    throws IOException
  {
    ensureRouteToHost(paramString, mTransactionSettings);
    long l = mTokenGenerator;
    mTokenGenerator = 1L + l;
    mToken = l;
    return HttpUtils.httpConnection(mContext, mToken, paramString, null, 2, mTransactionSettings.isProxySet(), mTransactionSettings.getProxyAddress(), mTransactionSettings.getProxyPort(), paramProgressReceiver);
  }
  
  public TransactionState getState()
  {
    return mTransactionState;
  }
  
  public abstract int getType();
  
  public int hashCode()
  {
    return Pair.create(getClass(), mId).hashCode();
  }
  
  public boolean isEquivalent(Transaction paramTransaction)
  {
    return (getClass().equals(paramTransaction.getClass())) && (mId.equals(mId));
  }
  
  public void onEnqueue() {}
  
  public abstract void process();
  
  protected byte[] sendPdu(byte[] paramArrayOfByte, ProgressReceiver paramProgressReceiver)
    throws IOException, MmsException
  {
    return sendPdu(paramArrayOfByte, mTransactionSettings.getMmscUrl(), paramProgressReceiver);
  }
  
  protected byte[] sendPdu(byte[] paramArrayOfByte, String paramString, ProgressReceiver paramProgressReceiver)
    throws IOException
  {
    ensureRouteToHost(paramString, mTransactionSettings);
    long l = mTokenGenerator;
    mTokenGenerator = 1L + l;
    mToken = l;
    return HttpUtils.httpConnection(mContext, mToken, paramString, paramArrayOfByte, 1, mTransactionSettings.isProxySet(), mTransactionSettings.getProxyAddress(), mTransactionSettings.getProxyPort(), paramProgressReceiver);
  }
  
  public void setSimId(long paramLong)
  {
    mSimId = paramLong;
  }
  
  public String toString()
  {
    String str = getClass().getSimpleName();
    if ((mTransactionState != null) && (mTransactionState.getContentUri() != null)) {
      return str + ", uri=" + mTransactionState.getContentUri();
    }
    return str + ", uri=null";
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.Transaction
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */