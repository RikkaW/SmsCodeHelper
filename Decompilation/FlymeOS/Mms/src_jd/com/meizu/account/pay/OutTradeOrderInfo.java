package com.meizu.account.pay;

import android.os.Bundle;
import android.text.TextUtils;

public class OutTradeOrderInfo
{
  private static final String KEY_BODY = "body";
  private static final String KEY_EXT_CONTENT = "extContent";
  private static final String KEY_LABEL_1 = "label1";
  private static final String KEY_LABEL_2 = "label2";
  private static final String KEY_NOTIFY_URL = "notifyUrl";
  private static final String KEY_OUT_TRADE = "outTrade";
  private static final String KEY_PARTNER = "partner";
  private static final String KEY_PAY_ACCOUNTS = "payAccounts";
  private static final String KEY_SIGN = "sign";
  private static final String KEY_SIGN_TYPE = "signType";
  private static final String KEY_SUBJECT = "subject";
  private static final String KEY_TOTAL_FEE = "totalFee";
  private String mBody;
  private String mExtContent;
  private String mLabel1;
  private String mLabel2;
  private String mNotifyUrl;
  private String mOutTrade;
  private String mPartner;
  private String mPayAccounts;
  private String mSign;
  private String mSignType;
  private String mSubject;
  private String mTotalFee;
  
  public static OutTradeOrderInfo fromBundle(Bundle paramBundle)
  {
    OutTradeOrderInfo localOutTradeOrderInfo = new OutTradeOrderInfo();
    localOutTradeOrderInfo.setExtContent(paramBundle.getString("extContent")).setLabel1(paramBundle.getString("label1")).setLabel2(paramBundle.getString("label2")).setBody(paramBundle.getString("body")).setNotifyUrl(paramBundle.getString("notifyUrl")).setOutTrade(paramBundle.getString("outTrade")).setPartner(paramBundle.getString("partner")).setPayAccounts(paramBundle.getString("payAccounts")).setSign(paramBundle.getString("sign")).setSignType(paramBundle.getString("signType")).setSubject(paramBundle.getString("subject")).setTotalFee(paramBundle.getString("totalFee"));
    if ((!TextUtils.isEmpty(localOutTradeOrderInfo.getNotifyUrl())) && (!TextUtils.isEmpty(localOutTradeOrderInfo.getOutTrade())) && (!TextUtils.isEmpty(localOutTradeOrderInfo.getPartner())) && (!TextUtils.isEmpty(localOutTradeOrderInfo.getSign())) && (!TextUtils.isEmpty(localOutTradeOrderInfo.getSignType())) && (!TextUtils.isEmpty(localOutTradeOrderInfo.getSubject())))
    {
      paramBundle = localOutTradeOrderInfo;
      if (!TextUtils.isEmpty(localOutTradeOrderInfo.getTotalFee())) {}
    }
    else
    {
      paramBundle = null;
    }
    return paramBundle;
  }
  
  public String getBody()
  {
    return mBody;
  }
  
  public String getExtContent()
  {
    return mExtContent;
  }
  
  public String getLabel1()
  {
    return mLabel1;
  }
  
  public String getLabel2()
  {
    return mLabel2;
  }
  
  public String getNotifyUrl()
  {
    return mNotifyUrl;
  }
  
  public String getOutTrade()
  {
    return mOutTrade;
  }
  
  public String getPartner()
  {
    return mPartner;
  }
  
  public String getPayAccounts()
  {
    return mPayAccounts;
  }
  
  public String getSign()
  {
    return mSign;
  }
  
  public String getSignType()
  {
    return mSignType;
  }
  
  public String getSubject()
  {
    return mSubject;
  }
  
  public String getTotalFee()
  {
    return mTotalFee;
  }
  
  public OutTradeOrderInfo setBody(String paramString)
  {
    mBody = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setExtContent(String paramString)
  {
    mExtContent = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setLabel1(String paramString)
  {
    mLabel1 = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setLabel2(String paramString)
  {
    mLabel2 = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setNotifyUrl(String paramString)
  {
    mNotifyUrl = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setOutTrade(String paramString)
  {
    mOutTrade = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setPartner(String paramString)
  {
    mPartner = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setPayAccounts(String paramString)
  {
    mPayAccounts = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setSign(String paramString)
  {
    mSign = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setSignType(String paramString)
  {
    mSignType = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setSubject(String paramString)
  {
    mSubject = paramString;
    return this;
  }
  
  public OutTradeOrderInfo setTotalFee(String paramString)
  {
    mTotalFee = paramString;
    return this;
  }
  
  public Bundle toBundle()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("extContent", getExtContent());
    localBundle.putString("label1", getLabel1());
    localBundle.putString("label2", getLabel2());
    localBundle.putString("body", getBody());
    localBundle.putString("notifyUrl", getNotifyUrl());
    localBundle.putString("outTrade", getOutTrade());
    localBundle.putString("partner", getPartner());
    localBundle.putString("payAccounts", getPayAccounts());
    localBundle.putString("sign", getSign());
    localBundle.putString("signType", getSignType());
    localBundle.putString("subject", getSubject());
    localBundle.putString("totalFee", getTotalFee());
    return localBundle;
  }
}

/* Location:
 * Qualified Name:     com.meizu.account.pay.OutTradeOrderInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */