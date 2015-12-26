package com.ted.sdk.yellow.util;

import android.text.TextUtils;
import android.util.Pair;
import com.ted.sdk.location.Location;
import com.ted.sdk.yellow.entry.MessageItem.SpItem.SpType;

public class ImsiUtils
{
  private static String convert2CmMsisdn(String paramString)
  {
    String str = "";
    switch (Integer.valueOf(paramString.substring(4, 6)).intValue())
    {
    default: 
      if (paramString.charAt(4) == '0') {
        switch (paramString.charAt(8))
        {
        default: 
          if (paramString.length() <= 9) {
            break label938;
          }
          str = "8613" + paramString.charAt(8) + "0" + paramString.substring(5, 8) + paramString.charAt(9);
        }
      }
    case 75: 
    case 70: 
      do
      {
        return str;
        return "86178" + paramString.substring(6);
      } while (paramString.charAt(6) != '5');
      return "861705" + paramString.substring(7);
    case 24: 
      return "86184" + paramString.substring(6);
    case 25: 
      return "86183" + paramString.substring(6);
    case 26: 
      return "86182" + paramString.substring(6);
    case 23: 
      return "86150" + paramString.substring(6);
    case 21: 
    case 22: 
    case 28: 
    case 29: 
      return "8615" + paramString.substring(5);
    case 27: 
      return "8618" + paramString.substring(5);
    case 77: 
      return "8615" + paramString.substring(5);
    case 78: 
      return "8618" + paramString.substring(5);
    case 79: 
      return "86147" + paramString.substring(6);
    }
    return "86134" + paramString.substring(6);
    if (paramString.length() > 10) {
      return "86135" + paramString.charAt(9) + paramString.substring(5, 8) + paramString.charAt(10);
    }
    return "86135" + paramString.charAt(9) + paramString.substring(5, 8);
    if (paramString.length() > 10) {
      return "86136" + paramString.charAt(9) + paramString.substring(5, 8) + paramString.charAt(10);
    }
    return "86136" + paramString.charAt(9) + paramString.substring(5, 8);
    if (paramString.length() > 10) {
      return "86137" + paramString.charAt(9) + paramString.substring(5, 8) + paramString.charAt(10);
    }
    return "86137" + paramString.charAt(9) + paramString.substring(5, 8);
    if (paramString.length() > 10) {
      return "86138" + paramString.charAt(9) + paramString.substring(5, 8) + paramString.charAt(10);
    }
    return "86138" + paramString.charAt(9) + paramString.substring(5, 8);
    if (paramString.length() > 10) {
      return "86139" + paramString.charAt(9) + paramString.substring(5, 8) + paramString.charAt(10);
    }
    return "86139" + paramString.charAt(9) + paramString.substring(5, 8);
    label938:
    return "8613" + paramString.charAt(8) + "0" + paramString.substring(5, 8);
  }
  
  private static String convert2CtMsisdn(String paramString)
  {
    if (paramString.startsWith("460030900")) {
      return "8613301" + paramString.substring(9);
    }
    if (paramString.startsWith("460030953")) {
      return "8613398" + paramString.substring(9);
    }
    if (paramString.startsWith("460030954")) {
      return "8613399" + paramString.substring(9);
    }
    if (paramString.startsWith("46003030")) {
      return "861335" + paramString.substring(8);
    }
    if (paramString.startsWith("46003099")) {
      return "861339" + paramString.substring(8);
    }
    if (paramString.startsWith("4600309")) {
      return "86133" + paramString.substring(7);
    }
    if ((paramString.startsWith("460036")) && (paramString.charAt(8) == '0')) {
      return "86153" + paramString.substring(6, 8) + paramString.substring(9);
    }
    if ((paramString.startsWith("460036")) && (paramString.charAt(8) == '1')) {
      return "86189" + paramString.substring(6, 8) + paramString.substring(9);
    }
    if (paramString.startsWith("460037410")) {
      return "8617000" + paramString.substring(9);
    }
    if (paramString.startsWith("460037411")) {
      return "8617001" + paramString.substring(9);
    }
    if (paramString.startsWith("460037420")) {
      return "8617002" + paramString.substring(9);
    }
    if (paramString.startsWith("460037421")) {
      return "8617003" + paramString.substring(9);
    }
    if (paramString.startsWith("460037430")) {
      return "8617004" + paramString.substring(9);
    }
    if (paramString.startsWith("460037431")) {
      return "8617005" + paramString.substring(9);
    }
    if (paramString.startsWith("460037440")) {
      return "8617006" + paramString.substring(9);
    }
    if (paramString.startsWith("460037441")) {
      return "8617007" + paramString.substring(9);
    }
    if (paramString.startsWith("460037450")) {
      return "8617008" + paramString.substring(9);
    }
    if (paramString.startsWith("460037451")) {
      return "8617009" + paramString.substring(9);
    }
    if (paramString.startsWith("460037460")) {
      return "8617700" + paramString.substring(9);
    }
    if (paramString.startsWith("460037461")) {
      return "8617701" + paramString.substring(9);
    }
    if (paramString.startsWith("460037470")) {
      return "8617702" + paramString.substring(9);
    }
    if (paramString.startsWith("460037471")) {
      return "8617703" + paramString.substring(9);
    }
    if (paramString.startsWith("460037480")) {
      return "8617704" + paramString.substring(9);
    }
    if (paramString.startsWith("460037481")) {
      return "8617705" + paramString.substring(9);
    }
    if (paramString.startsWith("460037490")) {
      return "8617706" + paramString.substring(9);
    }
    if (paramString.startsWith("460037491")) {
      return "8617707" + paramString.substring(9);
    }
    if (paramString.startsWith("460037500")) {
      return "8617708" + paramString.substring(9);
    }
    if (paramString.startsWith("460037501")) {
      return "8617709" + paramString.substring(9);
    }
    if (paramString.startsWith("460037510")) {
      return "8617710" + paramString.substring(9);
    }
    if (paramString.startsWith("460037511")) {
      return "8617711" + paramString.substring(9);
    }
    if (paramString.startsWith("460037520")) {
      return "8617712" + paramString.substring(9);
    }
    if (paramString.startsWith("460037521")) {
      return "8617713" + paramString.substring(9);
    }
    if (paramString.startsWith("460037530")) {
      return "8617714" + paramString.substring(9);
    }
    if (paramString.startsWith("460037531")) {
      return "8617715" + paramString.substring(9);
    }
    if (paramString.startsWith("460037540")) {
      return "8617716" + paramString.substring(9);
    }
    if (paramString.startsWith("460031274")) {
      return "8617717" + paramString.substring(9);
    }
    if (paramString.startsWith("460031275")) {
      return "8617718" + paramString.substring(9);
    }
    if (paramString.startsWith("460031276")) {
      return "8617719" + paramString.substring(9);
    }
    if (paramString.startsWith("460031277")) {
      return "8617720" + paramString.substring(9);
    }
    if (paramString.startsWith("460031278")) {
      return "8617721" + paramString.substring(9);
    }
    if (paramString.startsWith("460031279")) {
      return "8617722" + paramString.substring(9);
    }
    if (paramString.startsWith("460031280")) {
      return "8617723" + paramString.substring(9);
    }
    if (paramString.startsWith("460031281")) {
      return "8617724" + paramString.substring(9);
    }
    if (paramString.startsWith("460031290")) {
      return "8617725" + paramString.substring(9);
    }
    if (paramString.startsWith("460031291")) {
      return "8617726" + paramString.substring(9);
    }
    if (paramString.startsWith("460031292")) {
      return "8617727" + paramString.substring(9);
    }
    if (paramString.startsWith("460031293")) {
      return "8617728" + paramString.substring(9);
    }
    if (paramString.startsWith("460031294")) {
      return "8617729" + paramString.substring(9);
    }
    if (paramString.startsWith("460031295")) {
      return "8617730" + paramString.substring(9);
    }
    if (paramString.startsWith("460031298")) {
      return "8617731" + paramString.substring(9);
    }
    if (paramString.startsWith("460031299")) {
      return "8617732" + paramString.substring(9);
    }
    int i;
    if (paramString.startsWith("4600313"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((i >= 0) && (i <= 8)) {
        return String.valueOf(i + 8617733) + paramString.substring(9);
      }
      if ((22 <= i) && (i <= 41)) {
        return String.valueOf(i + 8617742 - 22) + paramString.substring(9);
      }
      if ((83 <= i) && (i <= 88)) {
        return String.valueOf(i + 8617762 - 83) + paramString.substring(9);
      }
      if ((90 <= i) && (i <= 95)) {
        return String.valueOf(i + 8617768 - 90) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600314"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((10 <= i) && (i <= 16)) {
        return String.valueOf(i + 8617774 - 10) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600315"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((39 <= i) && (i <= 49)) {
        return String.valueOf(i + 8617781 - 39) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600316"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((92 <= i) && (i <= 99)) {
        return "8617792" + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600301"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((30 <= i) && (i <= 38)) {
        return "861800" + paramString.substring(8);
      }
    }
    else if (paramString.startsWith("4600312"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((1 <= i) && (i <= 16)) {
        return String.valueOf(i + 8618163 - 1) + paramString.substring(9);
      }
      if ((18 <= i) && (i <= 26)) {
        return String.valueOf(i + 8618179 - 18) + paramString.substring(9);
      }
      if ((40 <= i) && (i <= 51)) {
        return String.valueOf(i + 8618188 - 40) + paramString.substring(9);
      }
      if ((65 <= i) && (i <= 73)) {
        return String.valueOf(i + 8618009 - 65) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600302"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((30 <= i) && (i <= 38)) {
        return String.valueOf(i + 8618018 - 30) + paramString.substring(9);
      }
      if ((50 <= i) && (i <= 58)) {
        return String.valueOf(i + 8618027 - 50) + paramString.substring(9);
      }
      if ((60 <= i) && (i <= 68)) {
        return String.valueOf(i + 8618036 - 60) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600303"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((60 <= i) && (i <= 70)) {
        return String.valueOf(i + 8618045 - 60) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600304"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((i >= 0) && (i <= 9)) {
        return String.valueOf(i + 8618056) + paramString.substring(9);
      }
      if ((13 <= i) && (i <= 20)) {
        return String.valueOf(i + 8618066 - 13) + paramString.substring(9);
      }
      if ((23 <= i) && (i <= 30)) {
        return String.valueOf(i + 8618074 - 23) + paramString.substring(9);
      }
      if ((93 <= i) && (i <= 98)) {
        return String.valueOf(i + 8618082 - 93) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600307"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((20 <= i) && (i <= 31)) {
        return String.valueOf(i + 8618088 - 20) + paramString.substring(9);
      }
      if (i == 45) {
        return "8618100" + paramString.substring(9);
      }
      if ((60 <= i) && (i <= 76)) {
        return String.valueOf(i + 8618101 - 60) + paramString.substring(9);
      }
      if ((81 <= i) && (i <= 88)) {
        return String.valueOf(i + 8618118 - 81) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600308"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((10 <= i) && (i <= 19)) {
        return String.valueOf(i + 8618126 - 10) + paramString.substring(9);
      }
      if ((70 <= i) && (i <= 79)) {
        return String.valueOf(i + 8618136 - 70) + paramString.substring(9);
      }
      if ((90 <= i) && (i <= 99)) {
        return String.valueOf(i + 8618146 - 90) + paramString.substring(9);
      }
    }
    else if (paramString.startsWith("4600310"))
    {
      i = Integer.parseInt(paramString.substring(7, 9));
      if ((20 <= i) && (i <= 26)) {
        return String.valueOf(i + 8618156 - 20) + paramString.substring(9);
      }
    }
    else
    {
      if (paramString.startsWith("460037620")) {
        return "8614905" + paramString.substring(9);
      }
      if (paramString.startsWith("460037621")) {
        return "8614906" + paramString.substring(9);
      }
      if (paramString.startsWith("460037630")) {
        return "8614907" + paramString.substring(9);
      }
      if (paramString.startsWith("460037631")) {
        return "8614908" + paramString.substring(9);
      }
      if (paramString.startsWith("460037640")) {
        return "8614909" + paramString.substring(9);
      }
    }
    return "";
  }
  
  private static String convert2CuMsisdn(String paramString)
  {
    switch (paramString.charAt(9))
    {
    case '8': 
    default: 
      return "";
    case '0': 
      return "86130" + paramString.charAt(8) + paramString.substring(5, 8);
    case '1': 
      return "86176" + paramString.charAt(8) + paramString.substring(5, 8);
    case '2': 
      return "86132" + paramString.charAt(8) + paramString.substring(5, 8);
    case '3': 
      return "86156" + paramString.charAt(8) + paramString.substring(5, 8);
    case '4': 
      return "86155" + paramString.charAt(8) + paramString.substring(5, 8);
    case '5': 
      return "86185" + paramString.charAt(8) + paramString.substring(5, 8);
    case '6': 
      return "86186" + paramString.charAt(8) + paramString.substring(5, 8);
    case '7': 
      return "86145" + paramString.charAt(8) + paramString.substring(5, 8);
    }
    return "86131" + paramString.charAt(8) + paramString.substring(5, 8);
  }
  
  public static String convert2Msisdn(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    MessageItem.SpItem.SpType localSpType;
    do
    {
      return null;
      localSpType = getSpTypeByImsi(paramString);
    } while (localSpType == null);
    switch (localSpType)
    {
    default: 
      return null;
    case CM: 
      return convert2CmMsisdn(paramString);
    case CU: 
      return convert2CuMsisdn(paramString);
    }
    return convert2CtMsisdn(paramString);
  }
  
  public static String getProvinceByImsi(String paramString)
  {
    paramString = convert2Msisdn(paramString);
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      paramString = Location.getProvinceCity(paramString);
    } while (paramString == null);
    return (String)first;
  }
  
  public static MessageItem.SpItem.SpType getSpTypeByImsi(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramString.length() < 6)) {
      return null;
    }
    switch (Integer.valueOf(paramString.substring(3, 5)).intValue())
    {
    case 4: 
    case 8: 
    case 10: 
    default: 
      return null;
    case 0: 
    case 2: 
    case 7: 
      return MessageItem.SpItem.SpType.CM;
    case 1: 
    case 6: 
    case 9: 
      return MessageItem.SpItem.SpType.CU;
    }
    return MessageItem.SpItem.SpType.CE;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.ImsiUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */