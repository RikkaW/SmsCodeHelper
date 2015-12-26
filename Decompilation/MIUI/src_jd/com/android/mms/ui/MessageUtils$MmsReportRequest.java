package com.android.mms.ui;

final class MessageUtils$MmsReportRequest
{
  private final boolean mIsDeliveryReportRequsted;
  private final boolean mIsReadReportRequested;
  private final String mRecipient;
  
  public MessageUtils$MmsReportRequest(String paramString, int paramInt1, int paramInt2)
  {
    mRecipient = paramString;
    if (paramInt1 == 128)
    {
      bool1 = true;
      mIsDeliveryReportRequsted = bool1;
      if (paramInt2 != 128) {
        break label52;
      }
    }
    label52:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      mIsReadReportRequested = bool1;
      return;
      bool1 = false;
      break;
    }
  }
  
  public String getRecipient()
  {
    return mRecipient;
  }
  
  public boolean isReadReportRequested()
  {
    return mIsReadReportRequested;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.MmsReportRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */