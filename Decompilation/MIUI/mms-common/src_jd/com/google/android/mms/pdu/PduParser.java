package com.google.android.mms.pdu;

import android.content.res.Resources;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;

public class PduParser
{
  private static final boolean DEBUG = false;
  private static final int END_STRING_FLAG = 0;
  private static final int LENGTH_QUOTE = 31;
  private static final boolean LOCAL_LOGV = false;
  private static final String LOG_TAG = "PduParser";
  private static final int LONG_INTEGER_LENGTH_MAX = 8;
  private static final int QUOTE = 127;
  private static final int QUOTED_STRING_FLAG = 34;
  private static final int SHORT_INTEGER_MAX = 127;
  private static final int SHORT_LENGTH_MAX = 30;
  private static final int TEXT_MAX = 127;
  private static final int TEXT_MIN = 32;
  private static final int THE_FIRST_PART = 0;
  private static final int THE_LAST_PART = 1;
  private static final int TYPE_QUOTED_STRING = 1;
  private static final int TYPE_TEXT_STRING = 0;
  private static final int TYPE_TOKEN_STRING = 2;
  private static byte[] mStartParam;
  private static byte[] mTypeParam;
  private PduBody mBody = null;
  private PduHeaders mHeaders = null;
  private ByteArrayInputStream mPduDataStream = null;
  
  static
  {
    if (!PduParser.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      mTypeParam = null;
      mStartParam = null;
      return;
    }
  }
  
  public PduParser(byte[] paramArrayOfByte)
  {
    mPduDataStream = new ByteArrayInputStream(paramArrayOfByte);
  }
  
  protected static boolean checkMandatoryHeader(PduHeaders paramPduHeaders)
  {
    if (paramPduHeaders == null) {
      return false;
    }
    int i = paramPduHeaders.getOctet(140);
    if (paramPduHeaders.getOctet(141) == 0) {
      return false;
    }
    switch (i)
    {
    default: 
      return false;
    case 128: 
      if (paramPduHeaders.getTextString(132) == null) {
        return false;
      }
      if (paramPduHeaders.getEncodedStringValue(137) == null) {
        return false;
      }
      if (paramPduHeaders.getTextString(152) == null) {
        return false;
      }
      break;
    case 129: 
      if (paramPduHeaders.getOctet(146) == 0) {
        return false;
      }
      if (paramPduHeaders.getTextString(152) == null) {
        return false;
      }
      break;
    case 130: 
      if (paramPduHeaders.getTextString(131) == null) {
        return false;
      }
      if (-1L == paramPduHeaders.getLongInteger(136)) {
        return false;
      }
      if (paramPduHeaders.getTextString(138) == null) {
        return false;
      }
      if (-1L == paramPduHeaders.getLongInteger(142)) {
        return false;
      }
      if (paramPduHeaders.getTextString(152) == null) {
        return false;
      }
      break;
    case 131: 
      if (paramPduHeaders.getOctet(149) == 0) {
        return false;
      }
      if (paramPduHeaders.getTextString(152) == null) {
        return false;
      }
      break;
    case 132: 
      if (paramPduHeaders.getTextString(132) == null) {
        return false;
      }
      if (-1L == paramPduHeaders.getLongInteger(133)) {
        return false;
      }
      break;
    case 134: 
      if (-1L == paramPduHeaders.getLongInteger(133)) {
        return false;
      }
      if (paramPduHeaders.getTextString(139) == null) {
        return false;
      }
      if (paramPduHeaders.getOctet(149) == 0) {
        return false;
      }
      if (paramPduHeaders.getEncodedStringValues(151) == null) {
        return false;
      }
      break;
    case 133: 
      if (paramPduHeaders.getTextString(152) == null) {
        return false;
      }
      break;
    case 136: 
      if (-1L == paramPduHeaders.getLongInteger(133)) {
        return false;
      }
      if (paramPduHeaders.getEncodedStringValue(137) == null) {
        return false;
      }
      if (paramPduHeaders.getTextString(139) == null) {
        return false;
      }
      if (paramPduHeaders.getOctet(155) == 0) {
        return false;
      }
      if (paramPduHeaders.getEncodedStringValues(151) == null) {
        return false;
      }
      break;
    case 135: 
      if (paramPduHeaders.getEncodedStringValue(137) == null) {
        return false;
      }
      if (paramPduHeaders.getTextString(139) == null) {
        return false;
      }
      if (paramPduHeaders.getOctet(155) == 0) {
        return false;
      }
      if (paramPduHeaders.getEncodedStringValues(151) == null) {
        return false;
      }
      break;
    }
    return true;
  }
  
  private static int checkPartPosition(PduPart paramPduPart)
  {
    assert (paramPduPart != null);
    if ((mTypeParam == null) && (mStartParam == null)) {}
    do
    {
      do
      {
        return 1;
        if (mStartParam != null)
        {
          byte[] arrayOfByte = paramPduPart.getContentId();
          if ((arrayOfByte != null) && (true == Arrays.equals(mStartParam, arrayOfByte))) {
            return 0;
          }
        }
      } while (mTypeParam == null);
      paramPduPart = paramPduPart.getContentType();
    } while ((paramPduPart == null) || (true != Arrays.equals(mTypeParam, paramPduPart)));
    return 0;
  }
  
  protected static int extractByteValue(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    return i & 0xFF;
  }
  
  protected static byte[] getWapString(ByteArrayInputStream paramByteArrayInputStream, int paramInt)
  {
    assert (paramByteArrayInputStream != null);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = paramByteArrayInputStream.read();
    int i = j;
    if (!$assertionsDisabled)
    {
      i = j;
      if (-1 == j) {
        throw new AssertionError();
      }
    }
    if ((-1 != i) && (i != 0))
    {
      if (paramInt == 2) {
        if (isTokenCharacter(i)) {
          localByteArrayOutputStream.write(i);
        }
      }
      for (;;)
      {
        j = paramByteArrayInputStream.read();
        i = j;
        if ($assertionsDisabled) {
          break;
        }
        i = j;
        if (-1 != j) {
          break;
        }
        throw new AssertionError();
        if (isText(i)) {
          localByteArrayOutputStream.write(i);
        }
      }
    }
    if (localByteArrayOutputStream.size() > 0) {
      return localByteArrayOutputStream.toByteArray();
    }
    return null;
  }
  
  protected static boolean isText(int paramInt)
  {
    if (((paramInt >= 32) && (paramInt <= 126)) || ((paramInt >= 128) && (paramInt <= 255))) {
      return true;
    }
    switch (paramInt)
    {
    }
    return false;
  }
  
  protected static boolean isTokenCharacter(int paramInt)
  {
    if ((paramInt < 33) || (paramInt > 126)) {
      return false;
    }
    switch (paramInt)
    {
    }
    return true;
  }
  
  private static void log(String paramString) {}
  
  protected static byte[] parseContentType(ByteArrayInputStream paramByteArrayInputStream, HashMap<Integer, Object> paramHashMap)
  {
    assert (paramByteArrayInputStream != null);
    paramByteArrayInputStream.mark(1);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    paramByteArrayInputStream.reset();
    i &= 0xFF;
    if (i < 32)
    {
      i = parseValueLength(paramByteArrayInputStream);
      int j = paramByteArrayInputStream.available();
      paramByteArrayInputStream.mark(1);
      int k = paramByteArrayInputStream.read();
      assert (-1 != k);
      paramByteArrayInputStream.reset();
      k &= 0xFF;
      if ((k >= 32) && (k <= 127)) {
        arrayOfByte = parseWapString(paramByteArrayInputStream, 0);
      }
      for (;;)
      {
        i -= j - paramByteArrayInputStream.available();
        if (i > 0) {
          parseContentTypeParams(paramByteArrayInputStream, paramHashMap, Integer.valueOf(i));
        }
        if (i >= 0) {
          break label260;
        }
        Log.e("PduParser", "Corrupt MMS message");
        return PduContentTypes.contentTypes[0].getBytes();
        if (k <= 127) {
          break;
        }
        k = parseShortInteger(paramByteArrayInputStream);
        if (k < PduContentTypes.contentTypes.length)
        {
          arrayOfByte = PduContentTypes.contentTypes[k].getBytes();
        }
        else
        {
          paramByteArrayInputStream.reset();
          arrayOfByte = parseWapString(paramByteArrayInputStream, 0);
        }
      }
      Log.e("PduParser", "Corrupt content-type");
      return PduContentTypes.contentTypes[0].getBytes();
    }
    if (i <= 127) {}
    for (byte[] arrayOfByte = parseWapString(paramByteArrayInputStream, 0);; arrayOfByte = PduContentTypes.contentTypes[parseShortInteger(paramByteArrayInputStream)].getBytes()) {
      label260:
      return arrayOfByte;
    }
  }
  
  protected static void parseContentTypeParams(ByteArrayInputStream paramByteArrayInputStream, HashMap<Integer, Object> paramHashMap, Integer paramInteger)
  {
    assert (paramByteArrayInputStream != null);
    assert (paramInteger.intValue() > 0);
    int j = paramByteArrayInputStream.available();
    int i = paramInteger.intValue();
    while (i > 0)
    {
      int k = paramByteArrayInputStream.read();
      assert (-1 != k);
      i -= 1;
      byte[] arrayOfByte;
      switch (k)
      {
      default: 
        if (-1 == skipWapValue(paramByteArrayInputStream, i)) {
          Log.e("PduParser", "Corrupt Content-Type");
        }
        break;
      case 131: 
      case 137: 
        paramByteArrayInputStream.mark(1);
        i = extractByteValue(paramByteArrayInputStream);
        paramByteArrayInputStream.reset();
        if (i > 127)
        {
          i = parseShortInteger(paramByteArrayInputStream);
          if (i < PduContentTypes.contentTypes.length) {
            paramHashMap.put(Integer.valueOf(131), PduContentTypes.contentTypes[i].getBytes());
          }
        }
        for (;;)
        {
          i = paramByteArrayInputStream.available();
          i = paramInteger.intValue() - (j - i);
          break;
          arrayOfByte = parseWapString(paramByteArrayInputStream, 0);
          if ((arrayOfByte != null) && (paramHashMap != null)) {
            paramHashMap.put(Integer.valueOf(131), arrayOfByte);
          }
        }
      case 138: 
      case 153: 
        arrayOfByte = parseWapString(paramByteArrayInputStream, 0);
        if ((arrayOfByte != null) && (paramHashMap != null)) {
          paramHashMap.put(Integer.valueOf(153), arrayOfByte);
        }
        i = paramByteArrayInputStream.available();
        i = paramInteger.intValue() - (j - i);
        break;
      case 129: 
        paramByteArrayInputStream.mark(1);
        i = extractByteValue(paramByteArrayInputStream);
        paramByteArrayInputStream.reset();
        if (((i > 32) && (i < 127)) || (i == 0)) {
          arrayOfByte = parseWapString(paramByteArrayInputStream, 0);
        }
        for (;;)
        {
          try
          {
            paramHashMap.put(Integer.valueOf(129), Integer.valueOf(CharacterSets.getMibEnumValue(new String(arrayOfByte))));
            i = paramByteArrayInputStream.available();
            i = paramInteger.intValue() - (j - i);
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException)
          {
            Log.e("PduParser", Arrays.toString(arrayOfByte), localUnsupportedEncodingException);
            paramHashMap.put(Integer.valueOf(129), Integer.valueOf(0));
            continue;
          }
          i = (int)parseIntegerValue(paramByteArrayInputStream);
          if (paramHashMap != null) {
            paramHashMap.put(Integer.valueOf(129), Integer.valueOf(i));
          }
        }
      case 133: 
      case 151: 
        arrayOfByte = parseWapString(paramByteArrayInputStream, 0);
        if ((arrayOfByte != null) && (paramHashMap != null)) {
          paramHashMap.put(Integer.valueOf(151), arrayOfByte);
        }
        i = paramByteArrayInputStream.available();
        i = paramInteger.intValue() - (j - i);
        continue;
        i = 0;
      }
    }
    if (i != 0) {
      Log.e("PduParser", "Corrupt Content-Type");
    }
  }
  
  protected static EncodedStringValue parseEncodedStringValue(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    paramByteArrayInputStream.mark(1);
    int i = 0;
    int j = paramByteArrayInputStream.read();
    assert (-1 != j);
    j &= 0xFF;
    if (j == 0) {
      return new EncodedStringValue("");
    }
    paramByteArrayInputStream.reset();
    if (j < 32)
    {
      parseValueLength(paramByteArrayInputStream);
      i = parseShortInteger(paramByteArrayInputStream);
    }
    paramByteArrayInputStream = parseWapString(paramByteArrayInputStream, 0);
    if (i != 0) {}
    try
    {
      paramByteArrayInputStream = new EncodedStringValue(i, paramByteArrayInputStream);
    }
    catch (Exception paramByteArrayInputStream)
    {
      return null;
    }
    paramByteArrayInputStream = new EncodedStringValue(paramByteArrayInputStream);
    return paramByteArrayInputStream;
  }
  
  protected static long parseIntegerValue(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    paramByteArrayInputStream.mark(1);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    paramByteArrayInputStream.reset();
    if (i > 127) {
      return parseShortInteger(paramByteArrayInputStream);
    }
    return parseLongInteger(paramByteArrayInputStream);
  }
  
  protected static long parseLongInteger(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    int j = i & 0xFF;
    if (j > 8) {
      throw new RuntimeException("Octet count greater than 8 and I can't represent that!");
    }
    long l = 0L;
    i = 0;
    while (i < j)
    {
      int k = paramByteArrayInputStream.read();
      assert (-1 != k);
      l = (l << 8) + (k & 0xFF);
      i += 1;
    }
    return l;
  }
  
  protected static boolean parsePartHeaders(ByteArrayInputStream paramByteArrayInputStream, PduPart paramPduPart, int paramInt)
  {
    assert (paramByteArrayInputStream != null);
    assert (paramPduPart != null);
    assert (paramInt > 0);
    int j = paramByteArrayInputStream.available();
    int i = paramInt;
    while (i > 0)
    {
      int k = paramByteArrayInputStream.read();
      assert (-1 != k);
      i -= 1;
      byte[] arrayOfByte1;
      if (k > 127)
      {
        switch (k)
        {
        default: 
          if (-1 == skipWapValue(paramByteArrayInputStream, i))
          {
            Log.e("PduParser", "Corrupt Part headers");
            return false;
          }
        case 142: 
          arrayOfByte1 = parseWapString(paramByteArrayInputStream, 0);
          if (arrayOfByte1 != null) {
            paramPduPart.setContentLocation(arrayOfByte1);
          }
          i = paramInt - (j - paramByteArrayInputStream.available());
          break;
        case 192: 
          arrayOfByte1 = parseWapString(paramByteArrayInputStream, 1);
          if (arrayOfByte1 != null) {
            paramPduPart.setContentId(arrayOfByte1);
          }
          i = paramInt - (j - paramByteArrayInputStream.available());
          break;
        case 174: 
        case 197: 
          if (!Resources.getSystem().getBoolean(17891393)) {
            continue;
          }
          i = parseValueLength(paramByteArrayInputStream);
          paramByteArrayInputStream.mark(1);
          k = paramByteArrayInputStream.available();
          int m = paramByteArrayInputStream.read();
          if (m == 128) {
            paramPduPart.setContentDisposition(PduPart.DISPOSITION_FROM_DATA);
          }
          for (;;)
          {
            if (k - paramByteArrayInputStream.available() < i)
            {
              if (paramByteArrayInputStream.read() == 152) {
                paramPduPart.setFilename(parseWapString(paramByteArrayInputStream, 0));
              }
              m = paramByteArrayInputStream.available();
              if (k - m < i)
              {
                i -= k - m;
                paramByteArrayInputStream.read(new byte[i], 0, i);
              }
            }
            i = paramInt - (j - paramByteArrayInputStream.available());
            break;
            if (m == 129)
            {
              paramPduPart.setContentDisposition(PduPart.DISPOSITION_ATTACHMENT);
            }
            else if (m == 130)
            {
              paramPduPart.setContentDisposition(PduPart.DISPOSITION_INLINE);
            }
            else
            {
              paramByteArrayInputStream.reset();
              paramPduPart.setContentDisposition(parseWapString(paramByteArrayInputStream, 0));
            }
          }
          i = 0;
          break;
        }
      }
      else if ((k >= 32) && (k <= 127))
      {
        arrayOfByte1 = parseWapString(paramByteArrayInputStream, 0);
        byte[] arrayOfByte2 = parseWapString(paramByteArrayInputStream, 0);
        if (true == "Content-Transfer-Encoding".equalsIgnoreCase(new String(arrayOfByte1))) {
          paramPduPart.setContentTransferEncoding(arrayOfByte2);
        }
        i = paramInt - (j - paramByteArrayInputStream.available());
      }
      else
      {
        if (-1 == skipWapValue(paramByteArrayInputStream, i))
        {
          Log.e("PduParser", "Corrupt Part headers");
          return false;
        }
        i = 0;
      }
    }
    if (i != 0)
    {
      Log.e("PduParser", "Corrupt Part headers");
      return false;
    }
    return true;
  }
  
  protected static PduBody parseParts(ByteArrayInputStream paramByteArrayInputStream)
  {
    if (paramByteArrayInputStream == null) {
      localObject1 = null;
    }
    int j;
    PduBody localPduBody;
    int i;
    do
    {
      return (PduBody)localObject1;
      j = parseUnsignedInt(paramByteArrayInputStream);
      localPduBody = new PduBody();
      i = 0;
      localObject1 = localPduBody;
    } while (i >= j);
    int m = parseUnsignedInt(paramByteArrayInputStream);
    int k = parseUnsignedInt(paramByteArrayInputStream);
    PduPart localPduPart = new PduPart();
    int n = paramByteArrayInputStream.available();
    if (n <= 0) {
      return null;
    }
    Object localObject1 = new HashMap();
    byte[] arrayOfByte = parseContentType(paramByteArrayInputStream, (HashMap)localObject1);
    if (arrayOfByte != null) {
      localPduPart.setContentType(arrayOfByte);
    }
    for (;;)
    {
      arrayOfByte = (byte[])((HashMap)localObject1).get(Integer.valueOf(151));
      if (arrayOfByte != null) {
        localPduPart.setName(arrayOfByte);
      }
      localObject1 = (Integer)((HashMap)localObject1).get(Integer.valueOf(129));
      if (localObject1 != null) {
        localPduPart.setCharset(((Integer)localObject1).intValue());
      }
      m -= n - paramByteArrayInputStream.available();
      if (m <= 0) {
        break;
      }
      if (parsePartHeaders(paramByteArrayInputStream, localPduPart, m)) {
        break label212;
      }
      return null;
      localPduPart.setContentType(PduContentTypes.contentTypes[0].getBytes());
    }
    if (m < 0) {
      return null;
    }
    label212:
    if ((localPduPart.getContentLocation() == null) && (localPduPart.getName() == null) && (localPduPart.getFilename() == null) && (localPduPart.getContentId() == null)) {
      localPduPart.setContentLocation(Long.toOctalString(System.currentTimeMillis()).getBytes());
    }
    localObject1 = localPduPart;
    if (k > 0)
    {
      arrayOfByte = new byte[k];
      localObject1 = new String(localPduPart.getContentType());
      paramByteArrayInputStream.read(arrayOfByte, 0, k);
      if (((String)localObject1).equalsIgnoreCase("application/vnd.wap.multipart.alternative")) {
        localObject1 = parseParts(new ByteArrayInputStream(arrayOfByte)).getPart(0);
      }
    }
    else
    {
      label323:
      if (checkPartPosition((PduPart)localObject1) != 0) {
        break label443;
      }
      localPduBody.addPart(0, (PduPart)localObject1);
    }
    for (;;)
    {
      i += 1;
      break;
      Object localObject2 = localPduPart.getContentTransferEncoding();
      localObject1 = arrayOfByte;
      if (localObject2 != null)
      {
        localObject2 = new String((byte[])localObject2);
        if (!((String)localObject2).equalsIgnoreCase("base64")) {
          break label404;
        }
        localObject1 = Base64.decodeBase64(arrayOfByte);
      }
      while (localObject1 == null)
      {
        log("Decode part data error!");
        return null;
        label404:
        localObject1 = arrayOfByte;
        if (((String)localObject2).equalsIgnoreCase("quoted-printable")) {
          localObject1 = QuotedPrintable.decodeQuotedPrintable(arrayOfByte);
        }
      }
      localPduPart.setData((byte[])localObject1);
      localObject1 = localPduPart;
      break label323;
      label443:
      localPduBody.addPart((PduPart)localObject1);
    }
  }
  
  protected static int parseShortInteger(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    return i & 0x7F;
  }
  
  protected static int parseUnsignedInt(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    int i = 0;
    int k = paramByteArrayInputStream.read();
    int j = k;
    if (k == -1) {
      return k;
    }
    while ((j & 0x80) != 0)
    {
      i = i << 7 | j & 0x7F;
      k = paramByteArrayInputStream.read();
      j = k;
      if (k == -1) {
        return k;
      }
    }
    return i << 7 | j & 0x7F;
  }
  
  protected static int parseValueLength(ByteArrayInputStream paramByteArrayInputStream)
  {
    assert (paramByteArrayInputStream != null);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    i &= 0xFF;
    if (i <= 30) {
      return i;
    }
    if (i == 31) {
      return parseUnsignedInt(paramByteArrayInputStream);
    }
    throw new RuntimeException("Value length > LENGTH_QUOTE!");
  }
  
  protected static byte[] parseWapString(ByteArrayInputStream paramByteArrayInputStream, int paramInt)
  {
    assert (paramByteArrayInputStream != null);
    paramByteArrayInputStream.mark(1);
    int i = paramByteArrayInputStream.read();
    assert (-1 != i);
    if ((1 == paramInt) && (34 == i)) {
      paramByteArrayInputStream.mark(1);
    }
    for (;;)
    {
      return getWapString(paramByteArrayInputStream, paramInt);
      if ((paramInt == 0) && (127 == i)) {
        paramByteArrayInputStream.mark(1);
      } else {
        paramByteArrayInputStream.reset();
      }
    }
  }
  
  protected static int skipWapValue(ByteArrayInputStream paramByteArrayInputStream, int paramInt)
  {
    assert (paramByteArrayInputStream != null);
    int j = paramByteArrayInputStream.read(new byte[paramInt], 0, paramInt);
    int i = j;
    if (j < paramInt) {
      i = -1;
    }
    return i;
  }
  
  public GenericPdu parse()
  {
    Object localObject;
    if (mPduDataStream == null) {
      localObject = null;
    }
    RetrieveConf localRetrieveConf;
    String str;
    do
    {
      do
      {
        do
        {
          return (GenericPdu)localObject;
          mHeaders = parseHeaders(mPduDataStream);
          if (mHeaders == null) {
            return null;
          }
          int i = mHeaders.getOctet(140);
          if (!checkMandatoryHeader(mHeaders))
          {
            log("check mandatory headers failed!");
            return null;
          }
          if ((128 == i) || (132 == i))
          {
            mBody = parseParts(mPduDataStream);
            if (mBody == null) {
              return null;
            }
          }
          switch (i)
          {
          default: 
            log("Parser doesn't support this message type in this version!");
            return null;
          case 128: 
            return new SendReq(mHeaders, mBody);
          case 129: 
            return new SendConf(mHeaders);
          case 130: 
            return new NotificationInd(mHeaders);
          case 131: 
            return new NotifyRespInd(mHeaders);
          case 132: 
            localRetrieveConf = new RetrieveConf(mHeaders, mBody);
            localObject = localRetrieveConf.getContentType();
            if (localObject == null) {
              return null;
            }
            str = new String((byte[])localObject);
            localObject = localRetrieveConf;
          }
        } while (str.equals("application/vnd.wap.multipart.mixed"));
        localObject = localRetrieveConf;
      } while (str.equals("application/vnd.wap.multipart.related"));
      localObject = localRetrieveConf;
    } while (str.equals("application/vnd.wap.multipart.alternative"));
    if (str.equals("application/vnd.wap.multipart.alternative"))
    {
      localObject = mBody.getPart(0);
      mBody.removeAll();
      mBody.addPart(0, (PduPart)localObject);
      return localRetrieveConf;
    }
    return null;
    return new DeliveryInd(mHeaders);
    return new AcknowledgeInd(mHeaders);
    return new ReadOrigInd(mHeaders);
    return new ReadRecInd(mHeaders);
  }
  
  /* Error */
  protected PduHeaders parseHeaders(ByteArrayInputStream paramByteArrayInputStream)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +9 -> 10
    //   4: aconst_null
    //   5: astore 9
    //   7: aload 9
    //   9: areturn
    //   10: iconst_1
    //   11: istore_2
    //   12: new 78	com/google/android/mms/pdu/PduHeaders
    //   15: dup
    //   16: invokespecial 464	com/google/android/mms/pdu/PduHeaders:<init>	()V
    //   19: astore 10
    //   21: aload 10
    //   23: astore 9
    //   25: iload_2
    //   26: ifeq -19 -> 7
    //   29: aload 10
    //   31: astore 9
    //   33: aload_1
    //   34: invokevirtual 164	java/io/ByteArrayInputStream:available	()I
    //   37: ifle -30 -> 7
    //   40: aload_1
    //   41: iconst_1
    //   42: invokevirtual 155	java/io/ByteArrayInputStream:mark	(I)V
    //   45: aload_1
    //   46: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   49: istore_3
    //   50: iload_3
    //   51: bipush 32
    //   53: if_icmplt +22 -> 75
    //   56: iload_3
    //   57: bipush 127
    //   59: if_icmpgt +16 -> 75
    //   62: aload_1
    //   63: invokevirtual 158	java/io/ByteArrayInputStream:reset	()V
    //   66: aload_1
    //   67: iconst_0
    //   68: invokestatic 167	com/google/android/mms/pdu/PduParser:parseWapString	(Ljava/io/ByteArrayInputStream;I)[B
    //   71: pop
    //   72: goto -51 -> 21
    //   75: iload_3
    //   76: tableswitch	default:+268->344, 129:+713->789, 130:+713->789, 131:+594->670, 132:+1699->1775, 133:+516->592, 134:+436->512, 135:+843->919, 136:+843->919, 137:+947->1023, 138:+1150->1226, 139:+594->670, 140:+277->353, 141:+1372->1448, 142:+516->592, 143:+436->512, 144:+436->512, 145:+436->512, 146:+436->512, 147:+654->730, 148:+436->512, 149:+436->512, 150:+654->730, 151:+713->789, 152:+594->670, 153:+436->512, 154:+654->730, 155:+436->512, 156:+436->512, 157:+843->919, 158:+594->670, 159:+516->592, 160:+1454->1530, 161:+1551->1627, 162:+436->512, 163:+436->512, 164:+1628->1704, 165:+436->512, 166:+654->730, 167:+436->512, 168:+268->344, 169:+436->512, 170:+1646->1722, 171:+436->512, 172:+1646->1722, 173:+555->631, 174:+268->344, 175:+555->631, 176:+268->344, 177:+436->512, 178:+1690->1766, 179:+555->631, 180:+436->512, 181:+654->730, 182:+654->730, 183:+594->670, 184:+594->670, 185:+594->670, 186:+436->512, 187:+436->512, 188:+436->512, 189:+594->670, 190:+594->670, 191:+436->512
    //   344: ldc_w 466
    //   347: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   350: goto -329 -> 21
    //   353: aload_1
    //   354: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   357: istore 4
    //   359: iload 4
    //   361: tableswitch	default:+75->436, 137:+123->484, 138:+123->484, 139:+123->484, 140:+123->484, 141:+123->484, 142:+123->484, 143:+123->484, 144:+123->484, 145:+123->484, 146:+123->484, 147:+123->484, 148:+123->484, 149:+123->484, 150:+123->484, 151:+123->484
    //   436: aload 10
    //   438: iload 4
    //   440: iload_3
    //   441: invokevirtual 470	com/google/android/mms/pdu/PduHeaders:setOctet	(II)V
    //   444: goto -423 -> 21
    //   447: astore_1
    //   448: new 472	java/lang/StringBuilder
    //   451: dup
    //   452: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   455: ldc_w 475
    //   458: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: iload 4
    //   463: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   466: ldc_w 484
    //   469: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: iload_3
    //   473: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   476: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   479: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   482: aconst_null
    //   483: areturn
    //   484: aconst_null
    //   485: areturn
    //   486: astore_1
    //   487: new 472	java/lang/StringBuilder
    //   490: dup
    //   491: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   494: iload_3
    //   495: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   498: ldc_w 489
    //   501: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   504: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   507: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   510: aconst_null
    //   511: areturn
    //   512: aload_1
    //   513: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   516: istore 4
    //   518: aload 10
    //   520: iload 4
    //   522: iload_3
    //   523: invokevirtual 470	com/google/android/mms/pdu/PduHeaders:setOctet	(II)V
    //   526: goto -505 -> 21
    //   529: astore_1
    //   530: new 472	java/lang/StringBuilder
    //   533: dup
    //   534: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   537: ldc_w 475
    //   540: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   543: iload 4
    //   545: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   548: ldc_w 484
    //   551: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: iload_3
    //   555: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   558: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   561: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   564: aconst_null
    //   565: areturn
    //   566: astore_1
    //   567: new 472	java/lang/StringBuilder
    //   570: dup
    //   571: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   574: iload_3
    //   575: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   578: ldc_w 489
    //   581: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   587: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   590: aconst_null
    //   591: areturn
    //   592: aload 10
    //   594: aload_1
    //   595: invokestatic 258	com/google/android/mms/pdu/PduParser:parseLongInteger	(Ljava/io/ByteArrayInputStream;)J
    //   598: iload_3
    //   599: invokevirtual 493	com/google/android/mms/pdu/PduHeaders:setLongInteger	(JI)V
    //   602: goto -581 -> 21
    //   605: astore_1
    //   606: new 472	java/lang/StringBuilder
    //   609: dup
    //   610: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   613: iload_3
    //   614: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   617: ldc_w 495
    //   620: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   623: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   626: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   629: aconst_null
    //   630: areturn
    //   631: aload 10
    //   633: aload_1
    //   634: invokestatic 240	com/google/android/mms/pdu/PduParser:parseIntegerValue	(Ljava/io/ByteArrayInputStream;)J
    //   637: iload_3
    //   638: invokevirtual 493	com/google/android/mms/pdu/PduHeaders:setLongInteger	(JI)V
    //   641: goto -620 -> 21
    //   644: astore_1
    //   645: new 472	java/lang/StringBuilder
    //   648: dup
    //   649: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   652: iload_3
    //   653: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   656: ldc_w 495
    //   659: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   662: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   665: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   668: aconst_null
    //   669: areturn
    //   670: aload_1
    //   671: iconst_0
    //   672: invokestatic 167	com/google/android/mms/pdu/PduParser:parseWapString	(Ljava/io/ByteArrayInputStream;I)[B
    //   675: astore 9
    //   677: aload 9
    //   679: ifnull -658 -> 21
    //   682: aload 10
    //   684: aload 9
    //   686: iload_3
    //   687: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   690: goto -669 -> 21
    //   693: astore 9
    //   695: ldc_w 501
    //   698: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   701: goto -680 -> 21
    //   704: astore_1
    //   705: new 472	java/lang/StringBuilder
    //   708: dup
    //   709: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   712: iload_3
    //   713: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   716: ldc_w 503
    //   719: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   722: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   725: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   728: aconst_null
    //   729: areturn
    //   730: aload_1
    //   731: invokestatic 505	com/google/android/mms/pdu/PduParser:parseEncodedStringValue	(Ljava/io/ByteArrayInputStream;)Lcom/google/android/mms/pdu/EncodedStringValue;
    //   734: astore 9
    //   736: aload 9
    //   738: ifnull -717 -> 21
    //   741: aload 10
    //   743: aload 9
    //   745: iload_3
    //   746: invokevirtual 509	com/google/android/mms/pdu/PduHeaders:setEncodedStringValue	(Lcom/google/android/mms/pdu/EncodedStringValue;I)V
    //   749: goto -728 -> 21
    //   752: astore 9
    //   754: ldc_w 501
    //   757: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   760: goto -739 -> 21
    //   763: astore_1
    //   764: new 472	java/lang/StringBuilder
    //   767: dup
    //   768: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   771: iload_3
    //   772: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   775: ldc_w 511
    //   778: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   781: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   784: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   787: aconst_null
    //   788: areturn
    //   789: aload_1
    //   790: invokestatic 505	com/google/android/mms/pdu/PduParser:parseEncodedStringValue	(Ljava/io/ByteArrayInputStream;)Lcom/google/android/mms/pdu/EncodedStringValue;
    //   793: astore 12
    //   795: aload 12
    //   797: ifnull -776 -> 21
    //   800: aload 12
    //   802: invokevirtual 513	com/google/android/mms/pdu/EncodedStringValue:getTextString	()[B
    //   805: astore 9
    //   807: aload 9
    //   809: ifnull +53 -> 862
    //   812: new 193	java/lang/String
    //   815: dup
    //   816: aload 9
    //   818: invokespecial 223	java/lang/String:<init>	([B)V
    //   821: astore 11
    //   823: aload 11
    //   825: ldc_w 515
    //   828: invokevirtual 518	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   831: istore 4
    //   833: aload 11
    //   835: astore 9
    //   837: iload 4
    //   839: ifle +13 -> 852
    //   842: aload 11
    //   844: iconst_0
    //   845: iload 4
    //   847: invokevirtual 522	java/lang/String:substring	(II)Ljava/lang/String;
    //   850: astore 9
    //   852: aload 12
    //   854: aload 9
    //   856: invokevirtual 196	java/lang/String:getBytes	()[B
    //   859: invokevirtual 524	com/google/android/mms/pdu/EncodedStringValue:setTextString	([B)V
    //   862: aload 10
    //   864: aload 12
    //   866: iload_3
    //   867: invokevirtual 527	com/google/android/mms/pdu/PduHeaders:appendEncodedStringValue	(Lcom/google/android/mms/pdu/EncodedStringValue;I)V
    //   870: goto -849 -> 21
    //   873: astore 9
    //   875: ldc_w 501
    //   878: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   881: goto -860 -> 21
    //   884: astore_1
    //   885: ldc_w 501
    //   888: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   891: aconst_null
    //   892: areturn
    //   893: astore_1
    //   894: new 472	java/lang/StringBuilder
    //   897: dup
    //   898: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   901: iload_3
    //   902: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   905: ldc_w 511
    //   908: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   911: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   914: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   917: aconst_null
    //   918: areturn
    //   919: aload_1
    //   920: invokestatic 161	com/google/android/mms/pdu/PduParser:parseValueLength	(Ljava/io/ByteArrayInputStream;)I
    //   923: pop
    //   924: aload_1
    //   925: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   928: istore 4
    //   930: aload_1
    //   931: invokestatic 258	com/google/android/mms/pdu/PduParser:parseLongInteger	(Ljava/io/ByteArrayInputStream;)J
    //   934: lstore 5
    //   936: lload 5
    //   938: lstore 7
    //   940: sipush 129
    //   943: iload 4
    //   945: if_icmpne +15 -> 960
    //   948: lload 5
    //   950: invokestatic 353	java/lang/System:currentTimeMillis	()J
    //   953: ldc2_w 528
    //   956: ldiv
    //   957: ladd
    //   958: lstore 7
    //   960: aload 10
    //   962: lload 7
    //   964: iload_3
    //   965: invokevirtual 493	com/google/android/mms/pdu/PduHeaders:setLongInteger	(JI)V
    //   968: goto -947 -> 21
    //   971: astore_1
    //   972: new 472	java/lang/StringBuilder
    //   975: dup
    //   976: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   979: iload_3
    //   980: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   983: ldc_w 495
    //   986: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   989: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   992: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   995: aconst_null
    //   996: areturn
    //   997: astore_1
    //   998: new 472	java/lang/StringBuilder
    //   1001: dup
    //   1002: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1005: iload_3
    //   1006: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1009: ldc_w 495
    //   1012: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1015: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1018: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1021: aconst_null
    //   1022: areturn
    //   1023: aload_1
    //   1024: invokestatic 161	com/google/android/mms/pdu/PduParser:parseValueLength	(Ljava/io/ByteArrayInputStream;)I
    //   1027: pop
    //   1028: sipush 128
    //   1031: aload_1
    //   1032: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   1035: if_icmpne +121 -> 1156
    //   1038: aload_1
    //   1039: invokestatic 505	com/google/android/mms/pdu/PduParser:parseEncodedStringValue	(Ljava/io/ByteArrayInputStream;)Lcom/google/android/mms/pdu/EncodedStringValue;
    //   1042: astore 11
    //   1044: aload 11
    //   1046: astore 9
    //   1048: aload 11
    //   1050: ifnull +73 -> 1123
    //   1053: aload 11
    //   1055: invokevirtual 513	com/google/android/mms/pdu/EncodedStringValue:getTextString	()[B
    //   1058: astore 12
    //   1060: aload 11
    //   1062: astore 9
    //   1064: aload 12
    //   1066: ifnull +57 -> 1123
    //   1069: new 193	java/lang/String
    //   1072: dup
    //   1073: aload 12
    //   1075: invokespecial 223	java/lang/String:<init>	([B)V
    //   1078: astore 12
    //   1080: aload 12
    //   1082: ldc_w 515
    //   1085: invokevirtual 518	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   1088: istore 4
    //   1090: aload 12
    //   1092: astore 9
    //   1094: iload 4
    //   1096: ifle +13 -> 1109
    //   1099: aload 12
    //   1101: iconst_0
    //   1102: iload 4
    //   1104: invokevirtual 522	java/lang/String:substring	(II)Ljava/lang/String;
    //   1107: astore 9
    //   1109: aload 11
    //   1111: aload 9
    //   1113: invokevirtual 196	java/lang/String:getBytes	()[B
    //   1116: invokevirtual 524	com/google/android/mms/pdu/EncodedStringValue:setTextString	([B)V
    //   1119: aload 11
    //   1121: astore 9
    //   1123: aload 10
    //   1125: aload 9
    //   1127: sipush 137
    //   1130: invokevirtual 509	com/google/android/mms/pdu/PduHeaders:setEncodedStringValue	(Lcom/google/android/mms/pdu/EncodedStringValue;I)V
    //   1133: goto -1112 -> 21
    //   1136: astore 9
    //   1138: ldc_w 501
    //   1141: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1144: goto -1123 -> 21
    //   1147: astore_1
    //   1148: ldc_w 501
    //   1151: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1154: aconst_null
    //   1155: areturn
    //   1156: new 247	com/google/android/mms/pdu/EncodedStringValue
    //   1159: dup
    //   1160: ldc_w 531
    //   1163: invokevirtual 196	java/lang/String:getBytes	()[B
    //   1166: invokespecial 255	com/google/android/mms/pdu/EncodedStringValue:<init>	([B)V
    //   1169: astore 9
    //   1171: goto -48 -> 1123
    //   1174: astore_1
    //   1175: new 472	java/lang/StringBuilder
    //   1178: dup
    //   1179: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1182: iload_3
    //   1183: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1186: ldc_w 511
    //   1189: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1192: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1195: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1198: aconst_null
    //   1199: areturn
    //   1200: astore_1
    //   1201: new 472	java/lang/StringBuilder
    //   1204: dup
    //   1205: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1208: iload_3
    //   1209: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1212: ldc_w 511
    //   1215: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1218: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1221: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1224: aconst_null
    //   1225: areturn
    //   1226: aload_1
    //   1227: iconst_1
    //   1228: invokevirtual 155	java/io/ByteArrayInputStream:mark	(I)V
    //   1231: aload_1
    //   1232: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   1235: istore 4
    //   1237: iload 4
    //   1239: sipush 128
    //   1242: if_icmplt +140 -> 1382
    //   1245: sipush 128
    //   1248: iload 4
    //   1250: if_icmpne +31 -> 1281
    //   1253: aload 10
    //   1255: ldc_w 533
    //   1258: invokevirtual 196	java/lang/String:getBytes	()[B
    //   1261: sipush 138
    //   1264: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   1267: goto -1246 -> 21
    //   1270: astore 9
    //   1272: ldc_w 501
    //   1275: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1278: goto -1257 -> 21
    //   1281: sipush 129
    //   1284: iload 4
    //   1286: if_icmpne +46 -> 1332
    //   1289: aload 10
    //   1291: ldc_w 535
    //   1294: invokevirtual 196	java/lang/String:getBytes	()[B
    //   1297: sipush 138
    //   1300: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   1303: goto -1282 -> 21
    //   1306: astore_1
    //   1307: new 472	java/lang/StringBuilder
    //   1310: dup
    //   1311: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1314: iload_3
    //   1315: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1318: ldc_w 503
    //   1321: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1324: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1327: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1330: aconst_null
    //   1331: areturn
    //   1332: sipush 130
    //   1335: iload 4
    //   1337: if_icmpne +20 -> 1357
    //   1340: aload 10
    //   1342: ldc_w 537
    //   1345: invokevirtual 196	java/lang/String:getBytes	()[B
    //   1348: sipush 138
    //   1351: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   1354: goto -1333 -> 21
    //   1357: sipush 131
    //   1360: iload 4
    //   1362: if_icmpne -1341 -> 21
    //   1365: aload 10
    //   1367: ldc_w 539
    //   1370: invokevirtual 196	java/lang/String:getBytes	()[B
    //   1373: sipush 138
    //   1376: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   1379: goto -1358 -> 21
    //   1382: aload_1
    //   1383: invokevirtual 158	java/io/ByteArrayInputStream:reset	()V
    //   1386: aload_1
    //   1387: iconst_0
    //   1388: invokestatic 167	com/google/android/mms/pdu/PduParser:parseWapString	(Ljava/io/ByteArrayInputStream;I)[B
    //   1391: astore 9
    //   1393: aload 9
    //   1395: ifnull -1374 -> 21
    //   1398: aload 10
    //   1400: aload 9
    //   1402: sipush 138
    //   1405: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   1408: goto -1387 -> 21
    //   1411: astore 9
    //   1413: ldc_w 501
    //   1416: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1419: goto -1398 -> 21
    //   1422: astore_1
    //   1423: new 472	java/lang/StringBuilder
    //   1426: dup
    //   1427: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1430: iload_3
    //   1431: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1434: ldc_w 503
    //   1437: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1440: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1443: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1446: aconst_null
    //   1447: areturn
    //   1448: aload_1
    //   1449: invokestatic 199	com/google/android/mms/pdu/PduParser:parseShortInteger	(Ljava/io/ByteArrayInputStream;)I
    //   1452: istore 4
    //   1454: aload 10
    //   1456: iload 4
    //   1458: sipush 141
    //   1461: invokevirtual 470	com/google/android/mms/pdu/PduHeaders:setOctet	(II)V
    //   1464: goto -1443 -> 21
    //   1467: astore_1
    //   1468: new 472	java/lang/StringBuilder
    //   1471: dup
    //   1472: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1475: ldc_w 475
    //   1478: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1481: iload 4
    //   1483: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1486: ldc_w 484
    //   1489: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1492: iload_3
    //   1493: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1496: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1499: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1502: aconst_null
    //   1503: areturn
    //   1504: astore_1
    //   1505: new 472	java/lang/StringBuilder
    //   1508: dup
    //   1509: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1512: iload_3
    //   1513: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1516: ldc_w 489
    //   1519: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1522: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1525: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1528: aconst_null
    //   1529: areturn
    //   1530: aload_1
    //   1531: invokestatic 161	com/google/android/mms/pdu/PduParser:parseValueLength	(Ljava/io/ByteArrayInputStream;)I
    //   1534: pop
    //   1535: aload_1
    //   1536: invokestatic 240	com/google/android/mms/pdu/PduParser:parseIntegerValue	(Ljava/io/ByteArrayInputStream;)J
    //   1539: pop2
    //   1540: aload_1
    //   1541: invokestatic 505	com/google/android/mms/pdu/PduParser:parseEncodedStringValue	(Ljava/io/ByteArrayInputStream;)Lcom/google/android/mms/pdu/EncodedStringValue;
    //   1544: astore 9
    //   1546: aload 9
    //   1548: ifnull -1527 -> 21
    //   1551: aload 10
    //   1553: aload 9
    //   1555: sipush 160
    //   1558: invokevirtual 509	com/google/android/mms/pdu/PduHeaders:setEncodedStringValue	(Lcom/google/android/mms/pdu/EncodedStringValue;I)V
    //   1561: goto -1540 -> 21
    //   1564: astore 9
    //   1566: ldc_w 501
    //   1569: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1572: goto -1551 -> 21
    //   1575: astore_1
    //   1576: new 472	java/lang/StringBuilder
    //   1579: dup
    //   1580: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1583: iload_3
    //   1584: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1587: ldc_w 541
    //   1590: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1593: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1596: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1599: aconst_null
    //   1600: areturn
    //   1601: astore_1
    //   1602: new 472	java/lang/StringBuilder
    //   1605: dup
    //   1606: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1609: iload_3
    //   1610: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1613: ldc_w 511
    //   1616: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1619: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1622: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1625: aconst_null
    //   1626: areturn
    //   1627: aload_1
    //   1628: invokestatic 161	com/google/android/mms/pdu/PduParser:parseValueLength	(Ljava/io/ByteArrayInputStream;)I
    //   1631: pop
    //   1632: aload_1
    //   1633: invokestatic 240	com/google/android/mms/pdu/PduParser:parseIntegerValue	(Ljava/io/ByteArrayInputStream;)J
    //   1636: pop2
    //   1637: aload 10
    //   1639: aload_1
    //   1640: invokestatic 258	com/google/android/mms/pdu/PduParser:parseLongInteger	(Ljava/io/ByteArrayInputStream;)J
    //   1643: sipush 161
    //   1646: invokevirtual 493	com/google/android/mms/pdu/PduHeaders:setLongInteger	(JI)V
    //   1649: goto -1628 -> 21
    //   1652: astore_1
    //   1653: new 472	java/lang/StringBuilder
    //   1656: dup
    //   1657: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1660: iload_3
    //   1661: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1664: ldc_w 495
    //   1667: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1670: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1673: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1676: aconst_null
    //   1677: areturn
    //   1678: astore_1
    //   1679: new 472	java/lang/StringBuilder
    //   1682: dup
    //   1683: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1686: iload_3
    //   1687: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1690: ldc_w 541
    //   1693: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1696: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1699: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1702: aconst_null
    //   1703: areturn
    //   1704: aload_1
    //   1705: invokestatic 161	com/google/android/mms/pdu/PduParser:parseValueLength	(Ljava/io/ByteArrayInputStream;)I
    //   1708: pop
    //   1709: aload_1
    //   1710: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   1713: pop
    //   1714: aload_1
    //   1715: invokestatic 505	com/google/android/mms/pdu/PduParser:parseEncodedStringValue	(Ljava/io/ByteArrayInputStream;)Lcom/google/android/mms/pdu/EncodedStringValue;
    //   1718: pop
    //   1719: goto -1698 -> 21
    //   1722: aload_1
    //   1723: invokestatic 161	com/google/android/mms/pdu/PduParser:parseValueLength	(Ljava/io/ByteArrayInputStream;)I
    //   1726: pop
    //   1727: aload_1
    //   1728: invokestatic 216	com/google/android/mms/pdu/PduParser:extractByteValue	(Ljava/io/ByteArrayInputStream;)I
    //   1731: pop
    //   1732: aload_1
    //   1733: invokestatic 240	com/google/android/mms/pdu/PduParser:parseIntegerValue	(Ljava/io/ByteArrayInputStream;)J
    //   1736: pop2
    //   1737: goto -1716 -> 21
    //   1740: astore_1
    //   1741: new 472	java/lang/StringBuilder
    //   1744: dup
    //   1745: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1748: iload_3
    //   1749: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1752: ldc_w 541
    //   1755: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1758: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1761: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1764: aconst_null
    //   1765: areturn
    //   1766: aload_1
    //   1767: aconst_null
    //   1768: invokestatic 322	com/google/android/mms/pdu/PduParser:parseContentType	(Ljava/io/ByteArrayInputStream;Ljava/util/HashMap;)[B
    //   1771: pop
    //   1772: goto -1751 -> 21
    //   1775: new 218	java/util/HashMap
    //   1778: dup
    //   1779: invokespecial 320	java/util/HashMap:<init>	()V
    //   1782: astore 9
    //   1784: aload_1
    //   1785: aload 9
    //   1787: invokestatic 322	com/google/android/mms/pdu/PduParser:parseContentType	(Ljava/io/ByteArrayInputStream;Ljava/util/HashMap;)[B
    //   1790: astore 11
    //   1792: aload 11
    //   1794: ifnull +13 -> 1807
    //   1797: aload 10
    //   1799: aload 11
    //   1801: sipush 132
    //   1804: invokevirtual 499	com/google/android/mms/pdu/PduHeaders:setTextString	([BI)V
    //   1807: aload 9
    //   1809: sipush 153
    //   1812: invokestatic 173	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1815: invokevirtual 329	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1818: checkcast 330	[B
    //   1821: checkcast 330	[B
    //   1824: putstatic 59	com/google/android/mms/pdu/PduParser:mStartParam	[B
    //   1827: aload 9
    //   1829: sipush 131
    //   1832: invokestatic 173	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1835: invokevirtual 329	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1838: checkcast 330	[B
    //   1841: checkcast 330	[B
    //   1844: putstatic 57	com/google/android/mms/pdu/PduParser:mTypeParam	[B
    //   1847: iconst_0
    //   1848: istore_2
    //   1849: goto -1828 -> 21
    //   1852: astore 11
    //   1854: ldc_w 501
    //   1857: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1860: goto -53 -> 1807
    //   1863: astore_1
    //   1864: new 472	java/lang/StringBuilder
    //   1867: dup
    //   1868: invokespecial 473	java/lang/StringBuilder:<init>	()V
    //   1871: iload_3
    //   1872: invokevirtual 482	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1875: ldc_w 503
    //   1878: invokevirtual 479	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1881: invokevirtual 487	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1884: invokestatic 388	com/google/android/mms/pdu/PduParser:log	(Ljava/lang/String;)V
    //   1887: aconst_null
    //   1888: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1889	0	this	PduParser
    //   0	1889	1	paramByteArrayInputStream	ByteArrayInputStream
    //   11	1838	2	i	int
    //   49	1823	3	j	int
    //   357	1125	4	k	int
    //   934	15	5	l1	long
    //   938	25	7	l2	long
    //   5	680	9	localObject1	Object
    //   693	1	9	localNullPointerException1	NullPointerException
    //   734	10	9	localEncodedStringValue1	EncodedStringValue
    //   752	1	9	localNullPointerException2	NullPointerException
    //   805	50	9	localObject2	Object
    //   873	1	9	localNullPointerException3	NullPointerException
    //   1046	80	9	localObject3	Object
    //   1136	1	9	localNullPointerException4	NullPointerException
    //   1169	1	9	localEncodedStringValue2	EncodedStringValue
    //   1270	1	9	localNullPointerException5	NullPointerException
    //   1391	10	9	arrayOfByte	byte[]
    //   1411	1	9	localNullPointerException6	NullPointerException
    //   1544	10	9	localEncodedStringValue3	EncodedStringValue
    //   1564	1	9	localNullPointerException7	NullPointerException
    //   1782	46	9	localHashMap	HashMap
    //   19	1779	10	localPduHeaders	PduHeaders
    //   821	979	11	localObject4	Object
    //   1852	1	11	localNullPointerException8	NullPointerException
    //   793	307	12	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   436	444	447	com/google/android/mms/InvalidHeaderValueException
    //   436	444	486	java/lang/RuntimeException
    //   518	526	529	com/google/android/mms/InvalidHeaderValueException
    //   518	526	566	java/lang/RuntimeException
    //   592	602	605	java/lang/RuntimeException
    //   631	641	644	java/lang/RuntimeException
    //   682	690	693	java/lang/NullPointerException
    //   682	690	704	java/lang/RuntimeException
    //   741	749	752	java/lang/NullPointerException
    //   741	749	763	java/lang/RuntimeException
    //   862	870	873	java/lang/NullPointerException
    //   852	862	884	java/lang/NullPointerException
    //   862	870	893	java/lang/RuntimeException
    //   960	968	971	java/lang/RuntimeException
    //   930	936	997	java/lang/RuntimeException
    //   1123	1133	1136	java/lang/NullPointerException
    //   1109	1119	1147	java/lang/NullPointerException
    //   1156	1171	1174	java/lang/NullPointerException
    //   1123	1133	1200	java/lang/RuntimeException
    //   1253	1267	1270	java/lang/NullPointerException
    //   1289	1303	1270	java/lang/NullPointerException
    //   1340	1354	1270	java/lang/NullPointerException
    //   1365	1379	1270	java/lang/NullPointerException
    //   1253	1267	1306	java/lang/RuntimeException
    //   1289	1303	1306	java/lang/RuntimeException
    //   1340	1354	1306	java/lang/RuntimeException
    //   1365	1379	1306	java/lang/RuntimeException
    //   1398	1408	1411	java/lang/NullPointerException
    //   1398	1408	1422	java/lang/RuntimeException
    //   1454	1464	1467	com/google/android/mms/InvalidHeaderValueException
    //   1454	1464	1504	java/lang/RuntimeException
    //   1551	1561	1564	java/lang/NullPointerException
    //   1535	1540	1575	java/lang/RuntimeException
    //   1551	1561	1601	java/lang/RuntimeException
    //   1637	1649	1652	java/lang/RuntimeException
    //   1632	1637	1678	java/lang/RuntimeException
    //   1732	1737	1740	java/lang/RuntimeException
    //   1797	1807	1852	java/lang/NullPointerException
    //   1797	1807	1863	java/lang/RuntimeException
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduParser
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */