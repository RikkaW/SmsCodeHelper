package com.ted.android.smscard;

import android.text.TextUtils;
import com.ted.android.utils.TedSDKLog;
import java.util.HashMap;
import java.util.Map;

public class CardBaseUtils
{
  private static final String TAG = CardBaseUtils.class.getSimpleName();
  public static final String TYPE_AUTO_FETCH_CARRIER = "01010000";
  public static final int TYPE_AUTO_FETCH_CARRIER_INT = 40;
  public static final String TYPE_BANK = "14000000";
  public static final int TYPE_BANK_INT = 34;
  public static final String TYPE_CARRIER = "01000000";
  public static final int TYPE_CARRIER_INT = 28;
  public static final int TYPE_COUPON_INT = 37;
  public static final String TYPE_DIANPING = "07000000";
  public static final String TYPE_DIANPING_COUPON = "07010000";
  public static final int TYPE_DIANPING_INT = 14;
  public static final String TYPE_E_BUSINESS = "08000000";
  public static final int TYPE_E_BUSINESS_INT = 17;
  public static final String TYPE_E_PAY = "09000000";
  public static final int TYPE_E_PAY_INT = 18;
  public static final String TYPE_FLIGHT = "04000000";
  public static final int TYPE_FLIGHT_INT = 21;
  public static final String TYPE_FLIGHT_TICKET_BOOK = "04010000";
  public static final int TYPE_FLIGHT_TICKET_BOOK_INT = 36;
  public static final String TYPE_HOTEL = "13000000";
  public static final int TYPE_HOTEL_INT = 25;
  public static final String TYPE_LIFE = "05000000";
  public static final int TYPE_LIFE_INT = 31;
  public static final String TYPE_MEITUAN = "11000000";
  public static final String TYPE_MEITUAN_GROUPON = "11010000";
  public static final int TYPE_MEITUAN_INT = 29;
  public static final String TYPE_MOVIE = "10000000";
  public static final String TYPE_MOVIE_COUPON = "10010000";
  public static final int TYPE_MOVIE_COUPON_INT = 42;
  public static final int TYPE_MOVIE_INT = 15;
  public static final String TYPE_MOVIE_TICKET = "10020000";
  public static final String TYPE_OPERATOR = "运营商";
  public static final int TYPE_OPERATOR_INT = 35;
  public static final String TYPE_OTHERS = "00000000";
  public static final int TYPE_OTHERS_INT = 30;
  public static final String TYPE_TAXI = "02000000";
  public static final String TYPE_TAXI_COUPON = "02010000";
  public static final int TYPE_TAXI_INT = 10;
  public static final String TYPE_TRAIN = "06000000";
  public static final int TYPE_TRAIN_INT = 19;
  public static final String TYPE_TUANGOU_COUPON = "07020000";
  public static final int TYPE_TUANGOU_COUPON_INT = 41;
  public static final String TYPE_VERIFICATION = "15000000";
  public static final int TYPE_VERIFICATION_INT = 33;
  public static final String TYPE_VIEW = "03000000";
  public static final int TYPE_VIEW_INT = 24;
  static Map<String, Integer> categoryMap = new HashMap();
  
  static
  {
    categoryMap.put("02000000", Integer.valueOf(10));
    categoryMap.put("07000000", Integer.valueOf(14));
    categoryMap.put("11000000", Integer.valueOf(29));
    categoryMap.put("10000000", Integer.valueOf(15));
    categoryMap.put("08000000", Integer.valueOf(17));
    categoryMap.put("09000000", Integer.valueOf(18));
    categoryMap.put("06000000", Integer.valueOf(19));
    categoryMap.put("04000000", Integer.valueOf(21));
    categoryMap.put("13000000", Integer.valueOf(25));
    categoryMap.put("01000000", Integer.valueOf(28));
    categoryMap.put("03000000", Integer.valueOf(24));
    categoryMap.put("05000000", Integer.valueOf(31));
    categoryMap.put("15000000", Integer.valueOf(33));
    categoryMap.put("14000000", Integer.valueOf(34));
    categoryMap.put("运营商", Integer.valueOf(35));
    categoryMap.put("00000000", Integer.valueOf(30));
    categoryMap.put("04010000", Integer.valueOf(36));
    categoryMap.put("02010000", Integer.valueOf(37));
    categoryMap.put("07010000", Integer.valueOf(37));
    categoryMap.put("07020000", Integer.valueOf(37));
    categoryMap.put("10020000", Integer.valueOf(37));
    categoryMap.put("10010000", Integer.valueOf(37));
    categoryMap.put("11010000", Integer.valueOf(37));
    categoryMap.put("01010000", Integer.valueOf(40));
    categoryMap.put("07020000", Integer.valueOf(41));
    categoryMap.put("11010000", Integer.valueOf(41));
    categoryMap.put("10020000", Integer.valueOf(42));
  }
  
  public static int convertTypeToInt(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (categoryMap == null)) {
      return 30;
    }
    Integer localInteger = (Integer)categoryMap.get(paramString);
    if (localInteger == null)
    {
      TedSDKLog.e(TAG, "[ " + paramString + " ]Return result = null");
      return 30;
    }
    return localInteger.intValue();
  }
}

/* Location:
 * Qualified Name:     com.ted.android.smscard.CardBaseUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */