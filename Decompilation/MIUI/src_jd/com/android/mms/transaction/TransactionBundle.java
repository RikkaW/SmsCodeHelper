package com.android.mms.transaction;

import android.os.Bundle;

public class TransactionBundle
{
  private final Bundle mBundle;
  
  private TransactionBundle(int paramInt)
  {
    mBundle = new Bundle();
    mBundle.putInt("type", paramInt);
  }
  
  public TransactionBundle(int paramInt, String paramString)
  {
    this(paramInt);
    mBundle.putString("uri", paramString);
  }
  
  public TransactionBundle(Bundle paramBundle)
  {
    mBundle = paramBundle;
  }
  
  public String getMmscUrl()
  {
    return mBundle.getString("mmsc-url");
  }
  
  public String getProxyAddress()
  {
    return mBundle.getString("proxy-address");
  }
  
  public int getProxyPort()
  {
    return mBundle.getInt("proxy-port");
  }
  
  public byte[] getPushData()
  {
    return mBundle.getByteArray("mms-push-data");
  }
  
  public int getTransactionType()
  {
    return mBundle.getInt("type");
  }
  
  public String getUri()
  {
    return mBundle.getString("uri");
  }
  
  public String toString()
  {
    return "transactionType: " + getTransactionType() + " uri: " + getUri() + " pushData: " + getPushData() + " mmscUrl: " + getMmscUrl() + " proxyAddress: " + getProxyAddress() + " proxyPort: " + getProxyPort();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.transaction.TransactionBundle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */