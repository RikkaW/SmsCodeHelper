package com.ted.sdk.yellow.util;

import android.text.TextUtils;
import com.ted.sdk.yellow.entry.MessageItem.SpItem.SpType;
import java.util.HashMap;

public class IccIdUtils
{
  private static final HashMap<String, String> CM_MAP;
  private static final HashMap<String, String> CU_MAP = new IccIdUtils.1();
  private static final char FLAG_CM = '0';
  private static final char FLAG_CT = '3';
  private static final char FLAG_CU = '1';
  private static final String[][] PROVINCE_AREA_CODES;
  
  static
  {
    CM_MAP = new IccIdUtils.2();
    String[] arrayOfString1 = { "310", "311", "312", "313", "314", "315", "316", "317", "318", "319", "335" };
    String[] arrayOfString2 = { "021" };
    PROVINCE_AREA_CODES = new String[][] { { "010" }, { "022" }, { "023" }, { "024", "411", "412", "414", "415", "416", "417", "418", "419", "421", "427", "429" }, { "029", "911", "912", "913", "914", "915", "916", "917", "919" }, arrayOfString1, { "349", "350", "351", "352", "353", "354", "355", "356", "357", "358", "359" }, { "370", "371", "372", "373", "374", "375", "376", "377", "378", "379", "391", "391", "392", "393", "394", "395", "396", "398" }, { "431", "432", "433", "434", "435", "436", "437", "438", "439" }, { "451", "452", "453", "454", "455", "456", "457", "458", "459", "464", "467", "468", "469" }, { "470", "471", "472", "473", "474", "475", "476", "477", "478", "479", "482", "483" }, { "530", "531", "532", "533", "534", "535", "536", "537", "538", "539", "543", "546", "631", "632", "633", "634", "635" }, { "570", "571", "572", "573", "574", "575", "576", "577", "578", "579", "580" }, { "591", "592", "593", "594", "595", "596", "597", "598", "599" }, { "730", "731", "734", "735", "736", "737", "738", "739", "743", "744", "745", "746" }, { "020", "660", "662", "663", "668", "750", "751", "752", "753", "754", "755", "756", "757", "758", "759", "760", "762", "763", "766", "768", "769" }, { "770", "771", "772", "773", "774", "775", "776", "777", "778", "779" }, { "701", "790", "791", "792", "793", "794", "795", "796", "797", "798", "799" }, { "691", "692", "870", "871", "872", "873", "874", "875", "876", "877", "878", "879", "883", "886", "887", "888" }, { "891", "892", "893", "894", "895", "896", "897" }, { "930", "931", "932", "933", "934", "935", "936", "937", "938", "939", "941", "943" }, { "951", "952", "953", "954", "955" }, { "901", "902", "903", "906", "908", "909", "990", "991", "994", "995", "996", "997", "998", "999" }, { "025", "510", "511", "512", "513", "514", "515", "516", "517", "518", "519", "523", "527" }, { "028", "812", "813", "816", "817", "818", "825", "826", "827", "830", "831", "832", "833", "834", "835", "836", "837", "838", "839" }, { "550", "551", "552", "553", "554", "555", "556", "557", "558", "559", "561", "562", "563", "564", "565", "566" }, { "851", "852", "853", "854", "855", "856", "857", "858", "859" }, { "970", "971", "972", "973", "974", "975", "976", "979" }, { "898" }, { "027", "710", "711", "712", "713", "714", "715", "716", "717", "718", "719", "722", "724" }, arrayOfString2 };
  }
  
  private static String getCm(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 10)) {
      return null;
    }
    paramString = paramString.substring(8, 10);
    return (String)CM_MAP.get(paramString);
  }
  
  private static String getCt(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 13)) {
      return null;
    }
    return getProvinceByArea(paramString.substring(10, 13));
  }
  
  private static String getCu(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 11)) {
      return null;
    }
    if (paramString.charAt(8) == '8')
    {
      paramString = paramString.substring(9, 11);
      return (String)CU_MAP.get(paramString);
    }
    paramString = paramString.substring(10, 12);
    return (String)CU_MAP.get(paramString);
  }
  
  private static String getProvinceByArea(String paramString)
  {
    int j = -1;
    String[][] arrayOfString = PROVINCE_AREA_CODES;
    int m = arrayOfString.length;
    int i = 0;
    if (i >= m) {
      return null;
    }
    String[] arrayOfString1 = arrayOfString[i];
    int k = j + 1;
    int n = arrayOfString1.length;
    j = 0;
    for (;;)
    {
      if (j >= n)
      {
        i += 1;
        j = k;
        break;
      }
      if (arrayOfString1[j].equals(paramString)) {
        return ProvinceUtils.ALL[k];
      }
      j += 1;
    }
  }
  
  public static String getProvinceByIccId(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 6)) {
      return null;
    }
    switch (paramString.charAt(5))
    {
    case '2': 
    default: 
      return null;
    case '0': 
      return getCm(paramString);
    case '1': 
      return getCu(paramString);
    }
    return getCt(paramString);
  }
  
  public static MessageItem.SpItem.SpType getSpTypeByIccId(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 6)) {
      return MessageItem.SpItem.SpType.NONE;
    }
    switch (paramString.charAt(5))
    {
    case '2': 
    default: 
      return MessageItem.SpItem.SpType.NONE;
    case '0': 
      return MessageItem.SpItem.SpType.CM;
    case '1': 
      return MessageItem.SpItem.SpType.CU;
    }
    return MessageItem.SpItem.SpType.CE;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.IccIdUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */