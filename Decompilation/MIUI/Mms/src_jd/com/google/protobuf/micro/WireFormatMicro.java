package com.google.protobuf.micro;

public final class WireFormatMicro
{
  static final int MESSAGE_SET_ITEM_END_TAG;
  static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
  static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);
  static final int MESSAGE_SET_TYPE_ID_TAG;
  
  static
  {
    MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
  }
  
  public static int getTagFieldNumber(int paramInt)
  {
    return paramInt >>> 3;
  }
  
  static int getTagWireType(int paramInt)
  {
    return paramInt & 0x7;
  }
  
  static int makeTag(int paramInt1, int paramInt2)
  {
    return paramInt1 << 3 | paramInt2;
  }
}

/* Location:
 * Qualified Name:     com.google.protobuf.micro.WireFormatMicro
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */