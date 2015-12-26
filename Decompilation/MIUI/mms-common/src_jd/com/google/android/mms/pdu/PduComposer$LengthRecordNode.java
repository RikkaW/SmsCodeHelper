package com.google.android.mms.pdu;

import java.io.ByteArrayOutputStream;

class PduComposer$LengthRecordNode
{
  ByteArrayOutputStream currentMessage = null;
  public int currentPosition = 0;
  public LengthRecordNode next = null;
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduComposer.LengthRecordNode
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */