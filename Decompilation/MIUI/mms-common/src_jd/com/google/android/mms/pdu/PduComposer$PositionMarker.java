package com.google.android.mms.pdu;

class PduComposer$PositionMarker
{
  private int c_pos;
  private int currentStackSize;
  
  private PduComposer$PositionMarker(PduComposer paramPduComposer) {}
  
  int getLength()
  {
    if (currentStackSize != access$100this$0).stackSize) {
      throw new RuntimeException("BUG: Invalid call to getLength()");
    }
    return this$0.mPosition - c_pos;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduComposer.PositionMarker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */