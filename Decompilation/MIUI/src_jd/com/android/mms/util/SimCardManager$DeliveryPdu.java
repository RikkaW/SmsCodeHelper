package com.android.mms.util;

import java.util.Arrays;

class SimCardManager$DeliveryPdu
{
  public byte[] encodedMessage;
  public byte[] encodedScAddress;
  
  public String toString()
  {
    return "DeliveryPdu: encodedScAddress = " + Arrays.toString(encodedScAddress) + ", encodedMessage = " + Arrays.toString(encodedMessage);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.SimCardManager.DeliveryPdu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */