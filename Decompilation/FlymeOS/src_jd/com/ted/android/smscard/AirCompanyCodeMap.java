package com.ted.android.smscard;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class AirCompanyCodeMap
{
  private static Map<String, String> codeMap = new HashMap();
  
  static
  {
    codeMap.put("3U", "四川航空");
    codeMap.put("8L", "祥鹏航空");
    codeMap.put("9C", "春秋航空");
    codeMap.put("AQ", "九元航空");
    codeMap.put("BK", "奥凯航空");
    codeMap.put("CA", "中国国航");
    codeMap.put("CN", "新华航空");
    codeMap.put("CZ", "南方航空");
    codeMap.put("DR", "瑞丽航空");
    codeMap.put("DZ", "东海航空");
    codeMap.put("EU", "成都航空");
    codeMap.put("FM", "上海航空");
    codeMap.put("FU", "福州航空");
    codeMap.put("G5", "华夏航空");
    codeMap.put("GJ", "长龙航空");
    codeMap.put("GS", "天津航空");
    codeMap.put("GX", "部湾航空");
    codeMap.put("HO", "吉祥航空");
    codeMap.put("HU", "海南航空");
    codeMap.put("JD", "首都航空");
    codeMap.put("JR", "幸福航空");
    codeMap.put("KN", "中国联航");
    codeMap.put("KY", "昆明航空");
    codeMap.put("MF", "厦门航空");
    codeMap.put("MU", "东方航空");
    codeMap.put("NS", "河北航空");
    codeMap.put("OQ", "重庆航空");
    codeMap.put("PN", "西部航空");
    codeMap.put("QW", "青岛航空");
    codeMap.put("SC", "山东航空");
    codeMap.put("TV", "西藏航空");
    codeMap.put("UQ", "乌鲁木齐航空");
    codeMap.put("YI", "英安航空");
    codeMap.put("ZH", "深圳航空");
  }
  
  public static String getCompanyNameByCode(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      return (String)codeMap.get(paramString);
    }
    return null;
  }
  
  public static String getCompanyNameByFlightNo(String paramString)
  {
    String str = null;
    if (!TextUtils.isEmpty(paramString)) {
      str = getCompanyNameByCode(paramString.substring(0, 2));
    }
    return str;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.smscard.AirCompanyCodeMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */