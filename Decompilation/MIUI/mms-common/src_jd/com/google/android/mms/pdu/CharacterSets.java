package com.google.android.mms.pdu;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class CharacterSets
{
  public static final int ANY_CHARSET = 0;
  public static final int BIG5 = 2026;
  public static final int DEFAULT_CHARSET = 106;
  public static final String DEFAULT_CHARSET_NAME = "utf-8";
  public static final int ISO_8859_1 = 4;
  public static final int ISO_8859_2 = 5;
  public static final int ISO_8859_3 = 6;
  public static final int ISO_8859_4 = 7;
  public static final int ISO_8859_5 = 8;
  public static final int ISO_8859_6 = 9;
  public static final int ISO_8859_7 = 10;
  public static final int ISO_8859_8 = 11;
  public static final int ISO_8859_9 = 12;
  private static final int[] MIBENUM_NUMBERS;
  private static final HashMap<Integer, String> MIBENUM_TO_NAME_MAP;
  public static final String MIMENAME_ANY_CHARSET = "*";
  public static final String MIMENAME_BIG5 = "big5";
  public static final String MIMENAME_ISO_8859_1 = "iso-8859-1";
  public static final String MIMENAME_ISO_8859_2 = "iso-8859-2";
  public static final String MIMENAME_ISO_8859_3 = "iso-8859-3";
  public static final String MIMENAME_ISO_8859_4 = "iso-8859-4";
  public static final String MIMENAME_ISO_8859_5 = "iso-8859-5";
  public static final String MIMENAME_ISO_8859_6 = "iso-8859-6";
  public static final String MIMENAME_ISO_8859_7 = "iso-8859-7";
  public static final String MIMENAME_ISO_8859_8 = "iso-8859-8";
  public static final String MIMENAME_ISO_8859_9 = "iso-8859-9";
  public static final String MIMENAME_SHIFT_JIS = "shift_JIS";
  public static final String MIMENAME_UCS2 = "iso-10646-ucs-2";
  public static final String MIMENAME_US_ASCII = "us-ascii";
  public static final String MIMENAME_UTF_16 = "utf-16";
  public static final String MIMENAME_UTF_8 = "utf-8";
  private static final String[] MIME_NAMES;
  private static final HashMap<String, Integer> NAME_TO_MIBENUM_MAP;
  public static final int SHIFT_JIS = 17;
  public static final int UCS2 = 1000;
  public static final int US_ASCII = 3;
  public static final int UTF_16 = 1015;
  public static final int UTF_8 = 106;
  
  static
  {
    if (!CharacterSets.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      MIBENUM_NUMBERS = new int[] { 0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 106, 2026, 1000, 1015 };
      MIME_NAMES = new String[] { "*", "us-ascii", "iso-8859-1", "iso-8859-2", "iso-8859-3", "iso-8859-4", "iso-8859-5", "iso-8859-6", "iso-8859-7", "iso-8859-8", "iso-8859-9", "shift_JIS", "utf-8", "big5", "iso-10646-ucs-2", "utf-16" };
      MIBENUM_TO_NAME_MAP = new HashMap();
      NAME_TO_MIBENUM_MAP = new HashMap();
      if (($assertionsDisabled) || (MIBENUM_NUMBERS.length == MIME_NAMES.length)) {
        break;
      }
      throw new AssertionError();
    }
    int j = MIBENUM_NUMBERS.length;
    int i = 0;
    while (i <= j - 1)
    {
      MIBENUM_TO_NAME_MAP.put(Integer.valueOf(MIBENUM_NUMBERS[i]), MIME_NAMES[i]);
      NAME_TO_MIBENUM_MAP.put(MIME_NAMES[i], Integer.valueOf(MIBENUM_NUMBERS[i]));
      i += 1;
    }
  }
  
  public static int getMibEnumValue(String paramString)
    throws UnsupportedEncodingException
  {
    if (paramString == null) {
      return -1;
    }
    paramString = (Integer)NAME_TO_MIBENUM_MAP.get(paramString);
    if (paramString == null) {
      throw new UnsupportedEncodingException();
    }
    return paramString.intValue();
  }
  
  public static String getMimeName(int paramInt)
    throws UnsupportedEncodingException
  {
    String str = (String)MIBENUM_TO_NAME_MAP.get(Integer.valueOf(paramInt));
    if (str == null) {
      throw new UnsupportedEncodingException();
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.CharacterSets
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */