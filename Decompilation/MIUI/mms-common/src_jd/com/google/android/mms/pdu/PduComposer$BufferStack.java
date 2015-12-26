package com.google.android.mms.pdu;

import java.io.ByteArrayOutputStream;

class PduComposer$BufferStack
{
  private PduComposer.LengthRecordNode stack = null;
  int stackSize = 0;
  private PduComposer.LengthRecordNode toCopy = null;
  
  private PduComposer$BufferStack(PduComposer paramPduComposer) {}
  
  void copy()
  {
    this$0.arraycopy(toCopy.currentMessage.toByteArray(), 0, toCopy.currentPosition);
    toCopy = null;
  }
  
  PduComposer.PositionMarker mark()
  {
    PduComposer.PositionMarker localPositionMarker = new PduComposer.PositionMarker(this$0, null);
    PduComposer.PositionMarker.access$402(localPositionMarker, this$0.mPosition);
    PduComposer.PositionMarker.access$502(localPositionMarker, stackSize);
    return localPositionMarker;
  }
  
  void newbuf()
  {
    if (toCopy != null) {
      throw new RuntimeException("BUG: Invalid newbuf() before copy()");
    }
    PduComposer.LengthRecordNode localLengthRecordNode = new PduComposer.LengthRecordNode(null);
    currentMessage = this$0.mMessage;
    currentPosition = this$0.mPosition;
    next = stack;
    stack = localLengthRecordNode;
    stackSize += 1;
    this$0.mMessage = new ByteArrayOutputStream();
    this$0.mPosition = 0;
  }
  
  void pop()
  {
    ByteArrayOutputStream localByteArrayOutputStream = this$0.mMessage;
    int i = this$0.mPosition;
    this$0.mMessage = stack.currentMessage;
    this$0.mPosition = stack.currentPosition;
    toCopy = stack;
    stack = stack.next;
    stackSize -= 1;
    toCopy.currentMessage = localByteArrayOutputStream;
    toCopy.currentPosition = i;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduComposer.BufferStack
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */