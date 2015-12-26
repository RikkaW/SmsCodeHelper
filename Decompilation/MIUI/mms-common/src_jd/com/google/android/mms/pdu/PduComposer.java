package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

public class PduComposer
{
  private static final int END_STRING_FLAG = 0;
  private static final int LENGTH_QUOTE = 31;
  private static final int LONG_INTEGER_LENGTH_MAX = 8;
  private static final int PDU_COMPOSER_BLOCK_SIZE = 1024;
  private static final int PDU_COMPOSE_CONTENT_ERROR = 1;
  private static final int PDU_COMPOSE_FIELD_NOT_SET = 2;
  private static final int PDU_COMPOSE_FIELD_NOT_SUPPORTED = 3;
  private static final int PDU_COMPOSE_SUCCESS = 0;
  private static final int PDU_EMAIL_ADDRESS_TYPE = 2;
  private static final int PDU_IPV4_ADDRESS_TYPE = 3;
  private static final int PDU_IPV6_ADDRESS_TYPE = 4;
  private static final int PDU_PHONE_NUMBER_ADDRESS_TYPE = 1;
  private static final int PDU_UNKNOWN_ADDRESS_TYPE = 5;
  private static final int QUOTED_STRING_FLAG = 34;
  static final String REGEXP_EMAIL_ADDRESS_TYPE = "[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}";
  static final String REGEXP_IPV4_ADDRESS_TYPE = "[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}";
  static final String REGEXP_IPV6_ADDRESS_TYPE = "[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}";
  static final String REGEXP_PHONE_NUMBER_ADDRESS_TYPE = "\\+?[0-9|\\.|\\-]+";
  private static final int SHORT_INTEGER_MAX = 127;
  static final String STRING_IPV4_ADDRESS_TYPE = "/TYPE=IPV4";
  static final String STRING_IPV6_ADDRESS_TYPE = "/TYPE=IPV6";
  static final String STRING_PHONE_NUMBER_ADDRESS_TYPE = "/TYPE=PLMN";
  private static final int TEXT_MAX = 127;
  private static HashMap<String, Integer> mContentTypeMap;
  protected ByteArrayOutputStream mMessage = null;
  private GenericPdu mPdu = null;
  private PduHeaders mPduHeader = null;
  protected int mPosition = 0;
  private final ContentResolver mResolver;
  private BufferStack mStack = null;
  
  static
  {
    if (!PduComposer.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      mContentTypeMap = null;
      mContentTypeMap = new HashMap();
      int i = 0;
      while (i < PduContentTypes.contentTypes.length)
      {
        mContentTypeMap.put(PduContentTypes.contentTypes[i], Integer.valueOf(i));
        i += 1;
      }
    }
  }
  
  public PduComposer(Context paramContext, GenericPdu paramGenericPdu)
  {
    mPdu = paramGenericPdu;
    mResolver = paramContext.getContentResolver();
    mPduHeader = paramGenericPdu.getPduHeaders();
    mStack = new BufferStack(null);
    mMessage = new ByteArrayOutputStream();
    mPosition = 0;
  }
  
  private EncodedStringValue appendAddressType(EncodedStringValue paramEncodedStringValue)
  {
    try
    {
      int i = checkAddressType(paramEncodedStringValue.getString());
      paramEncodedStringValue = EncodedStringValue.copy(paramEncodedStringValue);
      if (1 == i) {
        paramEncodedStringValue.appendTextString("/TYPE=PLMN".getBytes());
      } else if (3 == i) {
        paramEncodedStringValue.appendTextString("/TYPE=IPV4".getBytes());
      } else if (4 == i) {
        paramEncodedStringValue.appendTextString("/TYPE=IPV6".getBytes());
      }
      return paramEncodedStringValue;
    }
    catch (NullPointerException paramEncodedStringValue) {}
    return null;
  }
  
  private int appendHeader(int paramInt)
  {
    switch (paramInt)
    {
    case 131: 
    case 132: 
    case 135: 
    case 140: 
    case 142: 
    case 146: 
    case 147: 
    case 148: 
    default: 
      return 3;
    case 141: 
      appendOctet(paramInt);
      paramInt = mPduHeader.getOctet(paramInt);
      if (paramInt == 0) {
        appendShortInteger(18);
      }
      break;
    }
    for (;;)
    {
      return 0;
      appendShortInteger(paramInt);
      continue;
      Object localObject = mPduHeader.getTextString(paramInt);
      if (localObject == null) {
        return 2;
      }
      appendOctet(paramInt);
      appendTextString((byte[])localObject);
      continue;
      localObject = mPduHeader.getEncodedStringValues(paramInt);
      if (localObject == null) {
        return 2;
      }
      int i = 0;
      while (i < localObject.length)
      {
        localEncodedStringValue = appendAddressType(localObject[i]);
        if (localEncodedStringValue == null) {
          return 1;
        }
        appendOctet(paramInt);
        appendEncodedString(localEncodedStringValue);
        i += 1;
      }
      appendOctet(paramInt);
      EncodedStringValue localEncodedStringValue = mPduHeader.getEncodedStringValue(paramInt);
      if ((localEncodedStringValue == null) || (TextUtils.isEmpty(localEncodedStringValue.getString())) || (new String(localEncodedStringValue.getTextString()).equals("insert-address-token")))
      {
        append(1);
        append(129);
      }
      else
      {
        mStack.newbuf();
        localObject = mStack.mark();
        append(128);
        localEncodedStringValue = appendAddressType(localEncodedStringValue);
        if (localEncodedStringValue == null) {
          return 1;
        }
        appendEncodedString(localEncodedStringValue);
        paramInt = ((PositionMarker)localObject).getLength();
        mStack.pop();
        appendValueLength(paramInt);
        mStack.copy();
        continue;
        i = mPduHeader.getOctet(paramInt);
        if (i == 0) {
          return 2;
        }
        appendOctet(paramInt);
        appendOctet(i);
        continue;
        long l = mPduHeader.getLongInteger(paramInt);
        if (-1L == l) {
          return 2;
        }
        appendOctet(paramInt);
        appendDateValue(l);
        continue;
        localObject = mPduHeader.getEncodedStringValue(paramInt);
        if (localObject == null) {
          return 2;
        }
        appendOctet(paramInt);
        appendEncodedString((EncodedStringValue)localObject);
        continue;
        localObject = mPduHeader.getTextString(paramInt);
        if (localObject == null) {
          return 2;
        }
        appendOctet(paramInt);
        if (Arrays.equals((byte[])localObject, "advertisement".getBytes()))
        {
          appendOctet(129);
        }
        else if (Arrays.equals((byte[])localObject, "auto".getBytes()))
        {
          appendOctet(131);
        }
        else if (Arrays.equals((byte[])localObject, "personal".getBytes()))
        {
          appendOctet(128);
        }
        else if (Arrays.equals((byte[])localObject, "informational".getBytes()))
        {
          appendOctet(130);
        }
        else
        {
          appendTextString((byte[])localObject);
          continue;
          l = mPduHeader.getLongInteger(paramInt);
          if (-1L == l) {
            return 2;
          }
          appendOctet(paramInt);
          mStack.newbuf();
          localObject = mStack.mark();
          append(129);
          appendLongInteger(l);
          paramInt = ((PositionMarker)localObject).getLength();
          mStack.pop();
          appendValueLength(paramInt);
          mStack.copy();
        }
      }
    }
  }
  
  protected static int checkAddressType(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return 5;
      if (paramString.matches("[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}\\.{1}[0-9]{1,3}")) {
        return 3;
      }
      if (paramString.matches("\\+?[0-9|\\.|\\-]+")) {
        return 1;
      }
      if (paramString.matches("[a-zA-Z| ]*\\<{0,1}[a-zA-Z| ]+@{1}[a-zA-Z| ]+\\.{1}[a-zA-Z| ]+\\>{0,1}")) {
        return 2;
      }
    } while (!paramString.matches("[a-fA-F]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}\\:{1}[a-fA-F0-9]{4}"));
    return 4;
  }
  
  private int makeAckInd()
  {
    if (mMessage == null)
    {
      mMessage = new ByteArrayOutputStream();
      mPosition = 0;
    }
    appendOctet(140);
    appendOctet(133);
    if (appendHeader(152) != 0) {}
    while (appendHeader(141) != 0) {
      return 1;
    }
    appendHeader(145);
    return 0;
  }
  
  private int makeMessageBody(int paramInt)
  {
    mStack.newbuf();
    Object localObject1 = mStack.mark();
    Object localObject3 = new String(mPduHeader.getTextString(132));
    localObject3 = (Integer)mContentTypeMap.get(localObject3);
    if (localObject3 == null) {}
    for (;;)
    {
      return 1;
      appendShortInteger(((Integer)localObject3).intValue());
      if (paramInt == 132) {}
      for (localObject3 = ((RetrieveConf)mPdu).getBody(); (localObject3 == null) || (((PduBody)localObject3).getPartsNum() == 0); localObject3 = ((SendReq)mPdu).getBody())
      {
        appendUintvarInteger(0L);
        mStack.pop();
        mStack.copy();
        return 0;
      }
      try
      {
        PduPart localPduPart1 = ((PduBody)localObject3).getPart(0);
        localObject5 = localPduPart1.getContentId();
        if (localObject5 != null)
        {
          appendOctet(138);
          if ((60 != localObject5[0]) || (62 != localObject5[(localObject5.length - 1)])) {
            break label305;
          }
          appendTextString((byte[])localObject5);
        }
        for (;;)
        {
          appendOctet(137);
          appendTextString(localPduPart1.getContentType());
          paramInt = ((PositionMarker)localObject1).getLength();
          mStack.pop();
          appendValueLength(paramInt);
          mStack.copy();
          int k = ((PduBody)localObject3).getPartsNum();
          appendUintvarInteger(k);
          i = 0;
          label245:
          if (i >= k) {
            break label926;
          }
          localPduPart2 = ((PduBody)localObject3).getPart(i);
          mStack.newbuf();
          localPositionMarker = mStack.mark();
          mStack.newbuf();
          localObject5 = mStack.mark();
          localObject1 = localPduPart2.getContentType();
          if (localObject1 != null) {
            break;
          }
          return 1;
          label305:
          appendTextString("<" + new String((byte[])localObject5) + ">");
        }
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        Object localObject5;
        int i;
        PduPart localPduPart2;
        PositionMarker localPositionMarker;
        for (;;)
        {
          localArrayIndexOutOfBoundsException.printStackTrace();
        }
        Object localObject4 = (Integer)mContentTypeMap.get(new String((byte[])localObject1));
        if (localObject4 == null) {
          appendTextString((byte[])localObject1);
        }
        for (;;)
        {
          localObject4 = localPduPart2.getName();
          localObject1 = localObject4;
          if (localObject4 != null) {
            break;
          }
          localObject4 = localPduPart2.getFilename();
          localObject1 = localObject4;
          if (localObject4 != null) {
            break;
          }
          localObject4 = localPduPart2.getContentLocation();
          localObject1 = localObject4;
          if (localObject4 != null) {
            break;
          }
          return 1;
          appendShortInteger(((Integer)localObject4).intValue());
        }
        appendOctet(133);
        appendTextString((byte[])localObject1);
        paramInt = localPduPart2.getCharset();
        if (paramInt != 0)
        {
          appendOctet(129);
          appendShortInteger(paramInt);
        }
        paramInt = ((PositionMarker)localObject5).getLength();
        mStack.pop();
        appendValueLength(paramInt);
        mStack.copy();
        localObject1 = localPduPart2.getContentId();
        label557:
        int m;
        int j;
        if (localObject1 != null)
        {
          appendOctet(192);
          if ((60 == localObject1[0]) && (62 == localObject1[(localObject1.length - 1)])) {
            appendQuotedString((byte[])localObject1);
          }
        }
        else
        {
          localObject1 = localPduPart2.getContentLocation();
          if (localObject1 != null)
          {
            appendOctet(142);
            appendTextString((byte[])localObject1);
          }
          m = localPositionMarker.getLength();
          paramInt = 0;
          localObject1 = localPduPart2.getData();
          if (localObject1 == null) {
            break label681;
          }
          arraycopy((byte[])localObject1, 0, localObject1.length);
          j = localObject1.length;
        }
        for (;;)
        {
          label681:
          Object localObject7;
          if (j != localPositionMarker.getLength() - m)
          {
            throw new RuntimeException("BUG: Length sanity check failed");
            appendQuotedString("<" + new String((byte[])localObject1) + ">");
            break label557;
            Object localObject8 = null;
            Object localObject9 = null;
            Object localObject10 = null;
            localObject1 = null;
            localObject4 = localObject1;
            localObject5 = localObject8;
            Object localObject6 = localObject9;
            localObject7 = localObject10;
            try
            {
              byte[] arrayOfByte = new byte['Ѐ'];
              localObject4 = localObject1;
              localObject5 = localObject8;
              localObject6 = localObject9;
              localObject7 = localObject10;
              localObject1 = mResolver.openInputStream(localPduPart2.getDataUri());
              for (;;)
              {
                localObject4 = localObject1;
                localObject5 = localObject1;
                localObject6 = localObject1;
                localObject7 = localObject1;
                j = ((InputStream)localObject1).read(arrayOfByte);
                if (j == -1) {
                  break;
                }
                localObject4 = localObject1;
                localObject5 = localObject1;
                localObject6 = localObject1;
                localObject7 = localObject1;
                mMessage.write(arrayOfByte, 0, j);
                localObject4 = localObject1;
                localObject5 = localObject1;
                localObject6 = localObject1;
                localObject7 = localObject1;
                mPosition += j;
                paramInt += j;
              }
            }
            catch (FileNotFoundException localFileNotFoundException) {}catch (IOException localIOException1)
            {
              localIOException1 = localIOException1;
              if (localObject5 == null) {
                break;
              }
              ((InputStream)localObject5).close();
              return 1;
            }
            catch (RuntimeException localRuntimeException)
            {
              if (localObject6 == null) {
                break;
              }
              ((InputStream)localObject6).close();
              return 1;
            }
            finally
            {
              if (localObject7 == null) {}
            }
          }
          try
          {
            ((InputStream)localObject7).close();
            throw ((Throwable)localObject2);
            mStack.pop();
            appendUintvarInteger(m);
            appendUintvarInteger(j);
            mStack.copy();
            i += 1;
            break label245;
            label926:
            return 0;
          }
          catch (IOException localIOException4)
          {
            for (;;) {}
          }
          j = paramInt;
          if (localObject2 != null) {
            try
            {
              ((InputStream)localObject2).close();
              j = paramInt;
            }
            catch (IOException localIOException2)
            {
              j = paramInt;
            }
          }
        }
      }
    }
    return 1;
  }
  
  private int makeNotifyResp()
  {
    if (mMessage == null)
    {
      mMessage = new ByteArrayOutputStream();
      mPosition = 0;
    }
    appendOctet(140);
    appendOctet(131);
    if (appendHeader(152) != 0) {}
    while ((appendHeader(141) != 0) || (appendHeader(149) != 0)) {
      return 1;
    }
    appendHeader(145);
    return 0;
  }
  
  private int makeReadRecInd()
  {
    if (mMessage == null)
    {
      mMessage = new ByteArrayOutputStream();
      mPosition = 0;
    }
    appendOctet(140);
    appendOctet(135);
    if (appendHeader(141) != 0) {}
    do
    {
      do
      {
        return 1;
      } while ((appendHeader(139) != 0) || (appendHeader(151) != 0) || (appendHeader(137) != 0));
      appendHeader(133);
    } while (appendHeader(155) != 0);
    return 0;
  }
  
  private int makeSendRetrievePdu(int paramInt)
  {
    if (mMessage == null)
    {
      mMessage = new ByteArrayOutputStream();
      mPosition = 0;
    }
    appendOctet(140);
    appendOctet(paramInt);
    appendOctet(152);
    byte[] arrayOfByte = mPduHeader.getTextString(152);
    if (arrayOfByte == null) {
      throw new IllegalArgumentException("Transaction-ID is null.");
    }
    appendTextString(arrayOfByte);
    if (appendHeader(141) != 0) {}
    int i;
    do
    {
      do
      {
        return 1;
        appendHeader(133);
      } while (appendHeader(137) != 0);
      i = 0;
      if (appendHeader(151) != 1) {
        i = 1;
      }
      if (appendHeader(130) != 1) {
        i = 1;
      }
      if (appendHeader(129) != 1) {
        i = 1;
      }
    } while (i == 0);
    appendHeader(150);
    appendHeader(138);
    appendHeader(136);
    appendHeader(143);
    appendHeader(134);
    appendHeader(144);
    if (paramInt == 132)
    {
      appendHeader(153);
      appendHeader(154);
    }
    appendOctet(132);
    return makeMessageBody(paramInt);
  }
  
  protected void append(int paramInt)
  {
    mMessage.write(paramInt);
    mPosition += 1;
  }
  
  protected void appendDateValue(long paramLong)
  {
    appendLongInteger(paramLong);
  }
  
  protected void appendEncodedString(EncodedStringValue paramEncodedStringValue)
  {
    assert (paramEncodedStringValue != null);
    int i = paramEncodedStringValue.getCharacterSet();
    paramEncodedStringValue = paramEncodedStringValue.getTextString();
    if (paramEncodedStringValue == null) {
      return;
    }
    mStack.newbuf();
    PositionMarker localPositionMarker = mStack.mark();
    appendShortInteger(i);
    appendTextString(paramEncodedStringValue);
    i = localPositionMarker.getLength();
    mStack.pop();
    appendValueLength(i);
    mStack.copy();
  }
  
  protected void appendLongInteger(long paramLong)
  {
    long l = paramLong;
    int i = 0;
    while ((l != 0L) && (i < 8))
    {
      l >>>= 8;
      i += 1;
    }
    appendShortLength(i);
    int k = (i - 1) * 8;
    int j = 0;
    while (j < i)
    {
      append((int)(paramLong >>> k & 0xFF));
      k -= 8;
      j += 1;
    }
  }
  
  protected void appendOctet(int paramInt)
  {
    append(paramInt);
  }
  
  protected void appendQuotedString(String paramString)
  {
    appendQuotedString(paramString.getBytes());
  }
  
  protected void appendQuotedString(byte[] paramArrayOfByte)
  {
    append(34);
    arraycopy(paramArrayOfByte, 0, paramArrayOfByte.length);
    append(0);
  }
  
  protected void appendShortInteger(int paramInt)
  {
    append((paramInt | 0x80) & 0xFF);
  }
  
  protected void appendShortLength(int paramInt)
  {
    append(paramInt);
  }
  
  protected void appendTextString(String paramString)
  {
    appendTextString(paramString.getBytes());
  }
  
  protected void appendTextString(byte[] paramArrayOfByte)
  {
    if ((paramArrayOfByte[0] & 0xFF) > Byte.MAX_VALUE) {
      append(127);
    }
    arraycopy(paramArrayOfByte, 0, paramArrayOfByte.length);
    append(0);
  }
  
  protected void appendUintvarInteger(long paramLong)
  {
    long l = 127L;
    int i = 0;
    for (;;)
    {
      int j = i;
      if (i < 5)
      {
        if (paramLong < l) {
          j = i;
        }
      }
      else {
        while (j > 0)
        {
          append((int)((0x80 | paramLong >>> j * 7 & 0x7F) & 0xFF));
          j -= 1;
        }
      }
      l = l << 7 | 0x7F;
      i += 1;
    }
    append((int)(paramLong & 0x7F));
  }
  
  protected void appendValueLength(long paramLong)
  {
    if (paramLong < 31L)
    {
      appendShortLength((int)paramLong);
      return;
    }
    append(31);
    appendUintvarInteger(paramLong);
  }
  
  protected void arraycopy(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    mMessage.write(paramArrayOfByte, paramInt1, paramInt2);
    mPosition += paramInt2;
  }
  
  public byte[] make()
  {
    int i = mPdu.getMessageType();
    switch (i)
    {
    case 129: 
    case 130: 
    case 134: 
    default: 
    case 128: 
    case 132: 
      do
      {
        return null;
      } while (makeSendRetrievePdu(i) != 0);
    }
    do
    {
      do
      {
        do
        {
          return mMessage.toByteArray();
        } while (makeNotifyResp() == 0);
        return null;
      } while (makeAckInd() == 0);
      return null;
    } while (makeReadRecInd() == 0);
    return null;
  }
  
  private class BufferStack
  {
    private PduComposer.LengthRecordNode stack = null;
    int stackSize = 0;
    private PduComposer.LengthRecordNode toCopy = null;
    
    private BufferStack() {}
    
    void copy()
    {
      arraycopy(toCopy.currentMessage.toByteArray(), 0, toCopy.currentPosition);
      toCopy = null;
    }
    
    PduComposer.PositionMarker mark()
    {
      PduComposer.PositionMarker localPositionMarker = new PduComposer.PositionMarker(PduComposer.this, null);
      PduComposer.PositionMarker.access$402(localPositionMarker, mPosition);
      PduComposer.PositionMarker.access$502(localPositionMarker, stackSize);
      return localPositionMarker;
    }
    
    void newbuf()
    {
      if (toCopy != null) {
        throw new RuntimeException("BUG: Invalid newbuf() before copy()");
      }
      PduComposer.LengthRecordNode localLengthRecordNode = new PduComposer.LengthRecordNode(null);
      currentMessage = mMessage;
      currentPosition = mPosition;
      next = stack;
      stack = localLengthRecordNode;
      stackSize += 1;
      mMessage = new ByteArrayOutputStream();
      mPosition = 0;
    }
    
    void pop()
    {
      ByteArrayOutputStream localByteArrayOutputStream = mMessage;
      int i = mPosition;
      mMessage = stack.currentMessage;
      mPosition = stack.currentPosition;
      toCopy = stack;
      stack = stack.next;
      stackSize -= 1;
      toCopy.currentMessage = localByteArrayOutputStream;
      toCopy.currentPosition = i;
    }
  }
  
  private static class LengthRecordNode
  {
    ByteArrayOutputStream currentMessage = null;
    public int currentPosition = 0;
    public LengthRecordNode next = null;
  }
  
  private class PositionMarker
  {
    private int c_pos;
    private int currentStackSize;
    
    private PositionMarker() {}
    
    int getLength()
    {
      if (currentStackSize != mStack.stackSize) {
        throw new RuntimeException("BUG: Invalid call to getLength()");
      }
      return mPosition - c_pos;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduComposer
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */