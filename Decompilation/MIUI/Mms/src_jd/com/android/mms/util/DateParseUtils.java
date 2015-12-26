package com.android.mms.util;

import android.text.TextUtils;
import android.util.Log;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParseUtils
{
  private static final DateFormat[] DATE_FORMATS;
  private static final DateFormat DATE_FORMAT_DD;
  private static final DateFormat DATE_FORMAT_DD_HH_;
  private static final DateFormat DATE_FORMAT_DD_HH_MM;
  private static final DateFormat DATE_FORMAT_HH;
  private static final DateFormat DATE_FORMAT_HH_MM;
  private static final DateFormat DATE_FORMAT_HH_MM_DD;
  private static final DateFormat DATE_FORMAT_HH_MM_DD_YYYY;
  private static final DateFormat DATE_FORMAT_HH_MM_MM_DD_YYYY;
  private static final DateFormat DATE_FORMAT_HH_MM_SS;
  private static final DateFormat DATE_FORMAT_HH_MM_YYYY_MM_DD;
  private static final DateFormat DATE_FORMAT_HH_MM_YYYY_MM_DD_DOT;
  private static final DateFormat DATE_FORMAT_HH_YYYY_MM_DD;
  private static final DateFormat DATE_FORMAT_HH_YYYY_MM_DD_DOT;
  private static final DateFormat DATE_FORMAT_MM_DD;
  private static final DateFormat DATE_FORMAT_MM_DD_DOT;
  private static final DateFormat DATE_FORMAT_MM_DD_HH_;
  private static final DateFormat DATE_FORMAT_MM_DD_HH_DOT;
  private static final DateFormat DATE_FORMAT_MM_DD_HH_MM;
  private static final DateFormat DATE_FORMAT_MM_DD_HH_MM_DOT;
  private static final DateFormat DATE_FORMAT_MM_DD_YYYY_HH_;
  private static final DateFormat DATE_FORMAT_MM_DD_YYYY_HH_MM;
  private static final DateFormat DATE_FORMAT_YYYY_MM_DD;
  private static final DateFormat DATE_FORMAT_YYYY_MM_DD_DOT;
  private static final DateFormat DATE_FORMAT_YYYY_MM_DD_HH_;
  private static final DateFormat DATE_FORMAT_YYYY_MM_DD_HH_DOT;
  private static final DateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
  private static final DateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM_DOT;
  public static String DAY_ENG;
  public static String MONTH_DAY_ENG;
  private static Pattern MONTH_DAY_ENG_PATTERN;
  public static String MONTH_ENG;
  private static String PREFIXES_TEN;
  private static String SUFFIXES_TEN;
  private static Pattern TIME_PHRASE_PATTERN;
  private static Map<String, Integer> sChineseDayStringMap;
  private static Map<String, Integer> sChineseHourAlphaMap;
  private static Map<String, Integer> sChineseHourOfDayMap;
  private static Map<String, String> sChineseMinuteMap;
  private static Map<String, Integer> sChineseMonthStringMap;
  private static Map<String, Integer> sChineseYearStringMap;
  private static Map<Character, Character> sDateCharacterMap;
  private static Map<String, Integer> sEnglishHourAlphaMap;
  private static Map<String, Integer> sMonthMap;
  private static Map<String, Integer> sWeeklyDayMap;
  
  static
  {
    DATE_FORMAT_HH_MM_YYYY_MM_DD = new SimpleDateFormat("HH:mm yyyy/MM/dd", Locale.US);
    DATE_FORMAT_YYYY_MM_DD_HH_MM_DOT = new SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.US);
    DATE_FORMAT_HH_MM_YYYY_MM_DD_DOT = new SimpleDateFormat("HH:mm yyyy.MM.dd", Locale.US);
    DATE_FORMAT_YYYY_MM_DD_HH_ = new SimpleDateFormat("yyyy/MM/dd HH:", Locale.US);
    DATE_FORMAT_HH_YYYY_MM_DD = new SimpleDateFormat("HH: yyyy/MM/dd", Locale.US);
    DATE_FORMAT_YYYY_MM_DD_HH_DOT = new SimpleDateFormat("yyyy.MM.dd HH:", Locale.US);
    DATE_FORMAT_HH_YYYY_MM_DD_DOT = new SimpleDateFormat("HH: yyyy.MM.dd", Locale.US);
    DATE_FORMAT_YYYY_MM_DD = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
    DATE_FORMAT_YYYY_MM_DD_DOT = new SimpleDateFormat("yyyy.MM.dd", Locale.US);
    DATE_FORMAT_MM_DD_HH_MM = new SimpleDateFormat("MM/dd HH:mm", Locale.US);
    DATE_FORMAT_MM_DD_HH_MM_DOT = new SimpleDateFormat("MM.dd HH:mm", Locale.US);
    DATE_FORMAT_MM_DD_HH_ = new SimpleDateFormat("MM/dd HH:");
    DATE_FORMAT_HH_MM_DD = new SimpleDateFormat("HH: MM/dd");
    DATE_FORMAT_MM_DD_HH_DOT = new SimpleDateFormat("MM.dd HH:");
    DATE_FORMAT_DD_HH_MM = new SimpleDateFormat("dd HH:mm");
    DATE_FORMAT_DD_HH_ = new SimpleDateFormat("dd HH:");
    DATE_FORMAT_MM_DD_YYYY_HH_MM = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);
    DATE_FORMAT_HH_MM_MM_DD_YYYY = new SimpleDateFormat("HH:mm MM/dd/yyyy", Locale.US);
    DATE_FORMAT_MM_DD_YYYY_HH_ = new SimpleDateFormat("MM/dd/yyyy HH:", Locale.US);
    DATE_FORMAT_HH_MM_DD_YYYY = new SimpleDateFormat("HH: MM/dd/yyyy", Locale.US);
    DATE_FORMAT_MM_DD = new SimpleDateFormat("MM/dd", Locale.US);
    DATE_FORMAT_MM_DD_DOT = new SimpleDateFormat("MM.dd", Locale.US);
    DATE_FORMAT_HH_MM_SS = new SimpleDateFormat("HH:mm:ss", Locale.US);
    DATE_FORMAT_HH_MM = new SimpleDateFormat("HH:mm", Locale.US);
    DATE_FORMAT_HH = new SimpleDateFormat("HH:", Locale.US);
    DATE_FORMAT_DD = new SimpleDateFormat("dd", Locale.US);
    MONTH_ENG = "(january|jan(\\.)?|february|feb(\\.)?|march|[Mm]ar(\\.)?|april|apr(\\.)?|may|may(\\.)?|june|jun(\\.)?|july|jul(\\.)?|august|aug(\\.)?|september|sep(\\.)?|october|oct(\\.)?|november|nov(\\.)?|december|dec(\\.)?)";
    DAY_ENG = "(1st|2nd|3rd|((3[01]|[12][0-9]|0?[4-9])(th)?)|[1-3])";
    MONTH_DAY_ENG = "((" + MONTH_ENG + "\\s+" + DAY_ENG + ")|(" + DAY_ENG + "\\s+" + MONTH_ENG + "))";
    MONTH_DAY_ENG_PATTERN = Pattern.compile(MONTH_DAY_ENG);
    TIME_PHRASE_PATTERN = Pattern.compile("((((((2[0-4]|(1|0?)[0-9]):([1-5][0-9]|0?[0-9])(:([1-5][0-9]|0?[0-9]))?([aApP][mM])?)|(2[0-4]|(1|0?)[0-9])([aApP][mM]))(\\s+,?|(,\\s*))(((((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))))(\\s+,?|(,\\s*))\\d{4})|(((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))))|(((\\d{4})-)?(1[012]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9]))|(((\\d{4})/)?(1[012]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9]))|((\\d{4})\\.(1[012]|0?[1-9])\\.(3[01]|[12][0-9]|0?[1-9]))))|((((2[0-4]|(1|0?)[0-9]):([1-5][0-9]|0?[0-9])(:([1-5][0-9]|0?[0-9]))?([aApP][mM])?)|(2[0-4]|(1|0?)[0-9])([aApP][mM]))(\\s+(,|([Oo][Nn]\\s+))?|(,\\s*))(\\b(([Mm][Oo][Nn]|[Tt][Uu][Ee][Ss]|[Tt][Hh][Uu][Rr]|[Ff][Rr][Ii]|[Ss][Uu][Nn])((\\.)|([Dd][Aa][Yy]))?|[Ww][Ee][Dd]((\\.)|([Nn][Ee][Ss][Dd][Aa][Yy]))?|[Ss][Aa][Tt]((\\.)|([Uu][Rr][Dd][Aa][Yy]))?)))|(((((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))))(\\s+,?|(,\\s*))\\d{4})|(((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?)))))|(((2[0-4]|(1|0?)[0-9]):([1-5][0-9]|0?[0-9])(:([1-5][0-9]|0?[0-9]))?([aApP][mM])?)|(2[0-4]|(1|0?)[0-9])([aApP][mM]))))|((((([周週]|(星期))[一二三四五六日天])(凌晨|上午|下午|早上|晚上|傍晚|中午)?)|(((今|明|大?后)天|次日)(凌晨|上午|下午|早上|晚上|傍晚|中午))|(((((((\\d{4}|[零一二三四五六七八九]{4}|(今|明|大?[后後]))年))?((1[012]|0?[1-9]|十[一二]?|[一二三四五六七八九])月))?|((本|下[个個]?|这个|這個)月))((3[01]|[12][0-9]|0?[1-9]|三十一?|(二?十)[一二三四五六七八九]?|[一二三四五六七八九])(日|号|號)))(凌晨|上午|下午|早上|晚上|傍晚|中午)?)|(凌晨|上午|下午|早上|晚上|傍晚|中午)|((今|明|大?后)天|次日))?(2[0-4]|(1|0?)[0-9]|二十[一二三四]?|十?[一二三四五六七八九两兩]|零|十)(((时|点|時|點|[Pp][Mm])(([123一二三两]刻|半|[1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩])(分(([1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩])秒)?)?)?)|(:([123一二三两]刻|半|[1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩])(:([1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩]))?))|((([周週]|(星期))[一二三四五六日天])(凌晨|上午|下午|早上|晚上|傍晚|中午)?)|(((今|明|大?后)天|次日)(凌晨|上午|下午|早上|晚上|傍晚|中午))|(((((((\\d{4}|[零一二三四五六七八九]{4}|(今|明|大?[后後]))年))?((1[012]|0?[1-9]|十[一二]?|[一二三四五六七八九])月))?|((本|下[个個]?|这个|這個)月))((3[01]|[12][0-9]|0?[1-9]|三十一?|(二?十)[一二三四五六七八九]?|[一二三四五六七八九])(日|号|號)))(凌晨|上午|下午|早上|晚上|傍晚|中午)?)|((((\\d{4})-)?(1[012]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])|((\\d{4})/)?(1[012]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])|(\\d{4})\\.(1[012]|0?[1-9])\\.(3[01]|[12][0-9]|0?[1-9]))((凌晨|上午|下午|早上|晚上|傍晚|中午)|(\\s)+)((2[0-4]|(1|0?)[0-9]|二十[一二三四]?|十?[一二三四五六七八九两兩]|零|十)(((时|点|時|點|[Pp][Mm])(([123一二三两]刻|半|[1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩])(分(([1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩])秒)?)?)?)|(:([123一二三两]刻|半|[1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩])(:([1-5][0-9]|0?[0-9]|[二三四五]?十[一二三四五六七八九]?|零|十|[一二三四五六七八九两兩]))?))))|((((\\d{4})-)?(1[012]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])|((\\d{4})/)?(1[012]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])|(\\d{4})\\.(1[012]|0?[1-9])\\.(3[01]|[12][0-9]|0?[1-9]))((凌晨|上午|下午|早上|晚上|傍晚|中午))?))");
    DATE_FORMATS = new DateFormat[] { DATE_FORMAT_YYYY_MM_DD_HH_MM, DATE_FORMAT_HH_MM_YYYY_MM_DD, DATE_FORMAT_YYYY_MM_DD_HH_MM_DOT, DATE_FORMAT_HH_MM_YYYY_MM_DD_DOT, DATE_FORMAT_YYYY_MM_DD_HH_, DATE_FORMAT_HH_YYYY_MM_DD, DATE_FORMAT_YYYY_MM_DD_HH_DOT, DATE_FORMAT_HH_YYYY_MM_DD_DOT, DATE_FORMAT_YYYY_MM_DD, DATE_FORMAT_YYYY_MM_DD_DOT, DATE_FORMAT_MM_DD_HH_MM, DATE_FORMAT_MM_DD_HH_MM_DOT, DATE_FORMAT_MM_DD_HH_, DATE_FORMAT_HH_MM_DD, DATE_FORMAT_MM_DD_HH_DOT, DATE_FORMAT_DD_HH_MM, DATE_FORMAT_DD_HH_, DATE_FORMAT_MM_DD_YYYY_HH_MM, DATE_FORMAT_HH_MM_MM_DD_YYYY, DATE_FORMAT_MM_DD_YYYY_HH_, DATE_FORMAT_HH_MM_DD_YYYY, DATE_FORMAT_MM_DD, DATE_FORMAT_MM_DD_DOT, DATE_FORMAT_HH_MM_SS, DATE_FORMAT_HH_MM, DATE_FORMAT_HH, DATE_FORMAT_DD };
    sDateCharacterMap = new HashMap(32);
    sChineseDayStringMap = new HashMap(8);
    sChineseMonthStringMap = new HashMap(8);
    sChineseYearStringMap = new HashMap(8);
    sChineseHourAlphaMap = new HashMap(8);
    sEnglishHourAlphaMap = new HashMap(4);
    sChineseHourOfDayMap = new HashMap(8);
    sWeeklyDayMap = new HashMap(64);
    sMonthMap = new HashMap(48);
    sChineseMinuteMap = new HashMap(6);
    PREFIXES_TEN = "二三四五六七八九";
    SUFFIXES_TEN = "一二三四五六七八九";
    sDateCharacterMap.put(Character.valueOf('年'), Character.valueOf('/'));
    sDateCharacterMap.put(Character.valueOf('月'), Character.valueOf('/'));
    sDateCharacterMap.put(Character.valueOf('日'), Character.valueOf(' '));
    sDateCharacterMap.put(Character.valueOf('号'), Character.valueOf(' '));
    sDateCharacterMap.put(Character.valueOf(34399), Character.valueOf(' '));
    sDateCharacterMap.put(Character.valueOf('时'), Character.valueOf(':'));
    sDateCharacterMap.put(Character.valueOf('時'), Character.valueOf(' '));
    sDateCharacterMap.put(Character.valueOf('点'), Character.valueOf(':'));
    sDateCharacterMap.put(Character.valueOf(40670), Character.valueOf(' '));
    sDateCharacterMap.put(Character.valueOf('分'), Character.valueOf(' '));
    sDateCharacterMap.put(Character.valueOf('-'), Character.valueOf('/'));
    sDateCharacterMap.put(Character.valueOf('一'), Character.valueOf('1'));
    sDateCharacterMap.put(Character.valueOf('二'), Character.valueOf('2'));
    sDateCharacterMap.put(Character.valueOf('三'), Character.valueOf('3'));
    sDateCharacterMap.put(Character.valueOf('四'), Character.valueOf('4'));
    sDateCharacterMap.put(Character.valueOf('五'), Character.valueOf('5'));
    sDateCharacterMap.put(Character.valueOf('六'), Character.valueOf('6'));
    sDateCharacterMap.put(Character.valueOf('七'), Character.valueOf('7'));
    sDateCharacterMap.put(Character.valueOf('八'), Character.valueOf('8'));
    sDateCharacterMap.put(Character.valueOf('九'), Character.valueOf('9'));
    sDateCharacterMap.put(Character.valueOf('十'), Character.valueOf('1'));
    sDateCharacterMap.put(Character.valueOf('两'), Character.valueOf('2'));
    sDateCharacterMap.put(Character.valueOf('兩'), Character.valueOf('2'));
    sDateCharacterMap.put(Character.valueOf(38646), Character.valueOf('0'));
    sChineseDayStringMap.put("今天", Integer.valueOf(0));
    sChineseDayStringMap.put("今日", Integer.valueOf(0));
    sChineseDayStringMap.put("次日", Integer.valueOf(1));
    sChineseDayStringMap.put("明天", Integer.valueOf(1));
    sChineseDayStringMap.put("明日", Integer.valueOf(1));
    sChineseDayStringMap.put("后天", Integer.valueOf(2));
    sChineseDayStringMap.put("後天", Integer.valueOf(2));
    sChineseDayStringMap.put("大后天", Integer.valueOf(3));
    sChineseDayStringMap.put("大後天", Integer.valueOf(3));
    sChineseMonthStringMap.put("本月", Integer.valueOf(0));
    sChineseMonthStringMap.put("这个月", Integer.valueOf(0));
    sChineseMonthStringMap.put("這個月", Integer.valueOf(0));
    sChineseMonthStringMap.put("下月", Integer.valueOf(1));
    sChineseMonthStringMap.put("下个月", Integer.valueOf(1));
    sChineseMonthStringMap.put("下個月", Integer.valueOf(1));
    sChineseYearStringMap.put("今年", Integer.valueOf(0));
    sChineseYearStringMap.put("明年", Integer.valueOf(1));
    sChineseYearStringMap.put("后年", Integer.valueOf(2));
    sChineseYearStringMap.put("後年", Integer.valueOf(2));
    sChineseYearStringMap.put("大后年", Integer.valueOf(3));
    sChineseYearStringMap.put("大後年", Integer.valueOf(3));
    sChineseHourAlphaMap.put("凌晨", Integer.valueOf(0));
    sChineseHourAlphaMap.put("早上", Integer.valueOf(0));
    sChineseHourAlphaMap.put("早晨", Integer.valueOf(0));
    sChineseHourAlphaMap.put("上午", Integer.valueOf(0));
    sChineseHourAlphaMap.put("中午", Integer.valueOf(0));
    sChineseHourAlphaMap.put("下午", Integer.valueOf(12));
    sChineseHourAlphaMap.put("傍晚", Integer.valueOf(12));
    sChineseHourAlphaMap.put("晚上", Integer.valueOf(12));
    sEnglishHourAlphaMap.put("pm", Integer.valueOf(12));
    sEnglishHourAlphaMap.put("am", Integer.valueOf(0));
    sChineseHourOfDayMap.put("凌晨", Integer.valueOf(0));
    sChineseHourOfDayMap.put("早上", Integer.valueOf(9));
    sChineseHourOfDayMap.put("早晨", Integer.valueOf(9));
    sChineseHourOfDayMap.put("上午", Integer.valueOf(9));
    sChineseHourOfDayMap.put("中午", Integer.valueOf(12));
    sChineseHourOfDayMap.put("下午", Integer.valueOf(14));
    sChineseHourOfDayMap.put("傍晚", Integer.valueOf(17));
    sChineseHourOfDayMap.put("晚上", Integer.valueOf(20));
    sChineseMinuteMap.put("半", "30");
    sChineseMinuteMap.put("1刻", "15");
    sChineseMinuteMap.put("2刻", "30");
    sChineseMinuteMap.put("3刻", "45");
    sWeeklyDayMap.put("周1", Integer.valueOf(2));
    sWeeklyDayMap.put("周一", Integer.valueOf(2));
    sWeeklyDayMap.put("週1", Integer.valueOf(2));
    sWeeklyDayMap.put("週一", Integer.valueOf(2));
    sWeeklyDayMap.put("周2", Integer.valueOf(3));
    sWeeklyDayMap.put("周二", Integer.valueOf(3));
    sWeeklyDayMap.put("週2", Integer.valueOf(3));
    sWeeklyDayMap.put("週二", Integer.valueOf(3));
    sWeeklyDayMap.put("周3", Integer.valueOf(4));
    sWeeklyDayMap.put("周三", Integer.valueOf(4));
    sWeeklyDayMap.put("週3", Integer.valueOf(4));
    sWeeklyDayMap.put("週三", Integer.valueOf(4));
    sWeeklyDayMap.put("周4", Integer.valueOf(5));
    sWeeklyDayMap.put("周四", Integer.valueOf(5));
    sWeeklyDayMap.put("週4", Integer.valueOf(5));
    sWeeklyDayMap.put("週四", Integer.valueOf(5));
    sWeeklyDayMap.put("周5", Integer.valueOf(6));
    sWeeklyDayMap.put("周五", Integer.valueOf(6));
    sWeeklyDayMap.put("週5", Integer.valueOf(6));
    sWeeklyDayMap.put("週五", Integer.valueOf(6));
    sWeeklyDayMap.put("周6", Integer.valueOf(7));
    sWeeklyDayMap.put("周六", Integer.valueOf(7));
    sWeeklyDayMap.put("週6", Integer.valueOf(7));
    sWeeklyDayMap.put("週六", Integer.valueOf(7));
    sWeeklyDayMap.put("周日", Integer.valueOf(1));
    sWeeklyDayMap.put("週日", Integer.valueOf(1));
    sWeeklyDayMap.put("周天", Integer.valueOf(1));
    sWeeklyDayMap.put("週天", Integer.valueOf(1));
    sWeeklyDayMap.put("monday", Integer.valueOf(2));
    sWeeklyDayMap.put("tuesday", Integer.valueOf(3));
    sWeeklyDayMap.put("wednesday", Integer.valueOf(4));
    sWeeklyDayMap.put("thursday", Integer.valueOf(5));
    sWeeklyDayMap.put("friday", Integer.valueOf(6));
    sWeeklyDayMap.put("saturday", Integer.valueOf(7));
    sWeeklyDayMap.put("sunday", Integer.valueOf(1));
    sWeeklyDayMap.put("mon.", Integer.valueOf(2));
    sWeeklyDayMap.put("tues.", Integer.valueOf(3));
    sWeeklyDayMap.put("wed.", Integer.valueOf(4));
    sWeeklyDayMap.put("thur.", Integer.valueOf(5));
    sWeeklyDayMap.put("fri.", Integer.valueOf(6));
    sWeeklyDayMap.put("sat.", Integer.valueOf(7));
    sWeeklyDayMap.put("sun.", Integer.valueOf(1));
    sWeeklyDayMap.put("mon", Integer.valueOf(2));
    sWeeklyDayMap.put("tues", Integer.valueOf(3));
    sWeeklyDayMap.put("wed", Integer.valueOf(4));
    sWeeklyDayMap.put("thur", Integer.valueOf(5));
    sWeeklyDayMap.put("fri", Integer.valueOf(6));
    sWeeklyDayMap.put("sat", Integer.valueOf(7));
    sWeeklyDayMap.put("sun", Integer.valueOf(1));
    sMonthMap.put("january", Integer.valueOf(0));
    sMonthMap.put("february", Integer.valueOf(1));
    sMonthMap.put("march", Integer.valueOf(2));
    sMonthMap.put("april", Integer.valueOf(3));
    sMonthMap.put("may", Integer.valueOf(4));
    sMonthMap.put("june", Integer.valueOf(5));
    sMonthMap.put("july", Integer.valueOf(6));
    sMonthMap.put("august", Integer.valueOf(7));
    sMonthMap.put("september", Integer.valueOf(8));
    sMonthMap.put("october", Integer.valueOf(9));
    sMonthMap.put("november", Integer.valueOf(10));
    sMonthMap.put("december", Integer.valueOf(11));
    sMonthMap.put("jan.", Integer.valueOf(0));
    sMonthMap.put("feb.", Integer.valueOf(1));
    sMonthMap.put("mar.", Integer.valueOf(2));
    sMonthMap.put("apr.", Integer.valueOf(3));
    sMonthMap.put("may.", Integer.valueOf(4));
    sMonthMap.put("jun.", Integer.valueOf(5));
    sMonthMap.put("jul.", Integer.valueOf(6));
    sMonthMap.put("aug.", Integer.valueOf(7));
    sMonthMap.put("sept.", Integer.valueOf(8));
    sMonthMap.put("oct.", Integer.valueOf(9));
    sMonthMap.put("nov.", Integer.valueOf(10));
    sMonthMap.put("dec.", Integer.valueOf(11));
    sMonthMap.put("jan", Integer.valueOf(0));
    sMonthMap.put("feb", Integer.valueOf(1));
    sMonthMap.put("mar", Integer.valueOf(2));
    sMonthMap.put("apr", Integer.valueOf(3));
    sMonthMap.put("may", Integer.valueOf(4));
    sMonthMap.put("jun", Integer.valueOf(5));
    sMonthMap.put("jul", Integer.valueOf(6));
    sMonthMap.put("aug", Integer.valueOf(7));
    sMonthMap.put("sept", Integer.valueOf(8));
    sMonthMap.put("oct", Integer.valueOf(9));
    sMonthMap.put("nov", Integer.valueOf(10));
    sMonthMap.put("dec", Integer.valueOf(11));
  }
  
  public static DateFormat findMatchedPattern(String paramString)
  {
    DateFormat[] arrayOfDateFormat = DATE_FORMATS;
    int j = arrayOfDateFormat.length;
    int i = 0;
    while (i < j)
    {
      DateFormat localDateFormat = arrayOfDateFormat[i];
      ParsePosition localParsePosition = new ParsePosition(0);
      if ((localDateFormat.parse(paramString, localParsePosition) != null) && (localParsePosition.getErrorIndex() == -1))
      {
        Log.d("DateParseUtils", "matched pattern: " + ((SimpleDateFormat)localDateFormat).toPattern());
        return localDateFormat;
      }
      i += 1;
    }
    return null;
  }
  
  private static String formatDateString(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    paramString = paramString.trim();
    int i = 0;
    if (i < paramString.length())
    {
      char c = paramString.charAt(i);
      int j;
      if (c == '十') {
        if (i > 0)
        {
          j = paramString.charAt(i - 1);
          if (PREFIXES_TEN.indexOf(j) != -1) {
            if (i < paramString.length() - 1)
            {
              j = paramString.charAt(i + 1);
              if (SUFFIXES_TEN.indexOf(j) == -1) {}
            }
          }
        }
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuffer.append("0");
        continue;
        localStringBuffer.append("0");
        continue;
        if (i < paramString.length() - 1)
        {
          j = paramString.charAt(i + 1);
          if (SUFFIXES_TEN.indexOf(j) != -1) {
            localStringBuffer.append(sDateCharacterMap.get(Character.valueOf(c)));
          } else {
            localStringBuffer.append("10");
          }
        }
        else
        {
          localStringBuffer.append("10");
          continue;
          if (i < paramString.length() - 1)
          {
            j = paramString.charAt(i + 1);
            if (SUFFIXES_TEN.indexOf(j) != -1) {
              localStringBuffer.append(sDateCharacterMap.get(Character.valueOf(c)));
            } else {
              localStringBuffer.append("10");
            }
          }
          else
          {
            localStringBuffer.append("10");
            continue;
            if (sDateCharacterMap.containsKey(Character.valueOf(c))) {
              localStringBuffer.append(sDateCharacterMap.get(Character.valueOf(c)));
            } else {
              localStringBuffer.append(c);
            }
          }
        }
      }
    }
    return localStringBuffer.toString();
  }
  
  private static EventDate parseDate(String paramString, Date paramDate, int paramInt)
  {
    Object localObject3 = paramString.toLowerCase().trim().replace("星期", "周");
    int i2 = 0;
    int m = 0;
    int n = -1;
    int i1 = -1;
    Object localObject4 = MONTH_DAY_ENG_PATTERN.matcher((CharSequence)localObject3);
    int k = i1;
    int j = n;
    Object localObject1 = localObject3;
    int i = i2;
    Iterator localIterator;
    if (((Matcher)localObject4).matches())
    {
      localObject4 = ((String)localObject3).substring(((Matcher)localObject4).start(), ((Matcher)localObject4).end());
      k = i1;
      j = n;
      localObject1 = localObject3;
      i = i2;
      if (!TextUtils.isEmpty((CharSequence)localObject4))
      {
        localIterator = sMonthMap.keySet().iterator();
        localObject1 = localObject4;
      }
    }
    int i4;
    int i3;
    int i6;
    int i5;
    int i8;
    int i9;
    int i10;
    int i7;
    for (;;)
    {
      Object localObject5 = localObject1;
      k = i1;
      j = n;
      localObject1 = localObject3;
      i = m;
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localObject1 = localObject5;
        if (((String)localObject5).contains(str))
        {
          j = ((Integer)sMonthMap.get(str)).intValue();
          localObject5 = ((String)localObject5).replace(str, "").trim().replaceAll("((th)|(rd)|(st)|(nd))", "").trim();
          n = j;
          localObject1 = localObject5;
          if (TextUtils.isEmpty((CharSequence)localObject5)) {}
        }
      }
      else
      {
        try
        {
          k = Integer.valueOf((String)localObject5).intValue();
          m = 1;
          i = 1;
          i1 = k;
          localObject1 = ((String)localObject3).replace((CharSequence)localObject4, "");
          m = 0;
          i2 = 0;
          paramString = (String)localObject1;
          i1 = m;
          n = i2;
          if (!TextUtils.isEmpty((CharSequence)localObject1))
          {
            localObject3 = sChineseYearStringMap.keySet().iterator();
            do
            {
              paramString = (String)localObject1;
              i1 = m;
              n = i2;
              if (!((Iterator)localObject3).hasNext()) {
                break;
              }
              paramString = (String)((Iterator)localObject3).next();
            } while (!((String)localObject1).contains(paramString));
            i1 = 1;
            n = ((Integer)sChineseYearStringMap.get(paramString)).intValue();
            paramString = ((String)localObject1).replace(paramString, "").trim();
          }
          m = 0;
          i4 = 0;
          localObject1 = paramString;
          i3 = m;
          i2 = i4;
          if (!TextUtils.isEmpty(paramString))
          {
            localObject3 = sChineseMonthStringMap.keySet().iterator();
            do
            {
              localObject1 = paramString;
              i3 = m;
              i2 = i4;
              if (!((Iterator)localObject3).hasNext()) {
                break;
              }
              localObject1 = (String)((Iterator)localObject3).next();
            } while (!paramString.contains((CharSequence)localObject1));
            i3 = 1;
            i2 = ((Integer)sChineseMonthStringMap.get(localObject1)).intValue();
            localObject1 = paramString.replace((CharSequence)localObject1, "").trim();
          }
          m = 0;
          i6 = 0;
          i5 = i6;
          paramString = (String)localObject1;
          i4 = m;
          if (!TextUtils.isEmpty((CharSequence)localObject1))
          {
            localObject3 = sChineseDayStringMap.keySet().iterator();
            do
            {
              i5 = i6;
              paramString = (String)localObject1;
              i4 = m;
              if (!((Iterator)localObject3).hasNext()) {
                break;
              }
              paramString = (String)((Iterator)localObject3).next();
            } while (!((String)localObject1).contains(paramString));
            i4 = 1;
            i5 = ((Integer)sChineseDayStringMap.get(paramString)).intValue();
            paramString = ((String)localObject1).replace(paramString, "").trim();
          }
          i8 = 0;
          i9 = 0;
          i10 = 0;
          i7 = i10;
          localObject1 = paramString;
          i6 = i8;
          m = i9;
          if (!TextUtils.isEmpty(paramString))
          {
            localObject3 = sChineseHourAlphaMap.keySet().iterator();
            do
            {
              i7 = i10;
              localObject1 = paramString;
              i6 = i8;
              m = i9;
              if (!((Iterator)localObject3).hasNext()) {
                break;
              }
              localObject1 = (String)((Iterator)localObject3).next();
            } while (!paramString.contains((CharSequence)localObject1));
            i6 = 1;
            m = ((Integer)sChineseHourAlphaMap.get(localObject1)).intValue();
            i7 = ((Integer)sChineseHourOfDayMap.get(localObject1)).intValue();
            localObject1 = paramString.replace((CharSequence)localObject1, " ").trim();
          }
          i10 = 0;
          paramString = (String)localObject1;
          i9 = i10;
          i8 = m;
          if (!TextUtils.isEmpty((CharSequence)localObject1))
          {
            localObject3 = sEnglishHourAlphaMap.keySet().iterator();
            do
            {
              paramString = (String)localObject1;
              i9 = i10;
              i8 = m;
              if (!((Iterator)localObject3).hasNext()) {
                break;
              }
              paramString = (String)((Iterator)localObject3).next();
            } while (!((String)localObject1).contains(paramString));
            i9 = 1;
            i8 = ((Integer)sEnglishHourAlphaMap.get(paramString)).intValue();
            paramString = ((String)localObject1).replace(paramString, ":").trim();
          }
          int i11 = 0;
          int i12 = 0;
          localObject1 = paramString;
          i10 = i11;
          m = i12;
          if (!TextUtils.isEmpty(paramString))
          {
            localObject3 = sWeeklyDayMap.keySet().iterator();
            do
            {
              localObject1 = paramString;
              i10 = i11;
              m = i12;
              if (!((Iterator)localObject3).hasNext()) {
                break;
              }
              localObject1 = (String)((Iterator)localObject3).next();
            } while (!paramString.contains((CharSequence)localObject1));
            i10 = 1;
            m = ((Integer)sWeeklyDayMap.get(localObject1)).intValue();
            Log.d("DateParseUtils", paramString + ", weekAlpha:" + m);
            localObject1 = paramString.replace((CharSequence)localObject1, "").trim();
          }
          localObject1 = formatDateString((String)localObject1);
          paramString = (String)localObject1;
          if (TextUtils.isEmpty((CharSequence)localObject1)) {
            break label1186;
          }
          paramString = sChineseMinuteMap.entrySet().iterator();
          while (paramString.hasNext())
          {
            localObject3 = (Map.Entry)paramString.next();
            if (((String)localObject1).contains((String)((Map.Entry)localObject3).getKey())) {
              localObject1 = ((String)localObject1).replace((CharSequence)((Map.Entry)localObject3).getKey(), (CharSequence)((Map.Entry)localObject3).getValue());
            }
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          Log.e("DateParseUtils", "exception thrown when parsing english date format:" + paramString + ", " + localNumberFormatException.toString());
          n = j;
          localObject2 = localObject5;
        }
      }
    }
    paramString = ((String)localObject2).replace("on", "").trim();
    label1186:
    Log.d("DateParseUtils", "formatDate:" + paramString);
    Object localObject2 = Calendar.getInstance();
    boolean bool2 = false;
    boolean bool1 = false;
    localObject3 = findMatchedPattern(paramString);
    if (localObject3 != null)
    {
      paramString = ((DateFormat)localObject3).parse(paramString, new ParsePosition(0));
      if ((DATE_FORMAT_YYYY_MM_DD_HH_MM.equals(localObject3)) || (DATE_FORMAT_YYYY_MM_DD_HH_MM_DOT.equals(localObject3)) || (DATE_FORMAT_HH_MM_YYYY_MM_DD.equals(localObject3)) || (DATE_FORMAT_HH_MM_YYYY_MM_DD_DOT.equals(localObject3)))
      {
        ((Calendar)localObject2).set(paramString.getYear() + 1900, paramString.getMonth(), paramString.getDate(), paramString.getHours(), paramString.getMinutes());
        if (paramString == null) {
          break label2664;
        }
        if (i1 != 0) {
          ((Calendar)localObject2).add(1, n);
        }
        if (i3 != 0) {
          ((Calendar)localObject2).add(2, i2);
        }
        if (i4 != 0) {
          ((Calendar)localObject2).add(5, i5);
        }
        if ((i6 == 0) && (i9 == 0)) {
          break label2503;
        }
        if ((!bool1) || (i6 == 0)) {
          break label2474;
        }
        bool2 = false;
        ((Calendar)localObject2).set(11, i7);
        ((Calendar)localObject2).set(12, 0);
        ((Calendar)localObject2).set(13, 0);
      }
      for (;;)
      {
        if (i10 != 0) {
          ((Calendar)localObject2).set(7, m);
        }
        if ((i != 0) && (j != -1) && (k != -1))
        {
          ((Calendar)localObject2).set(2, j);
          ((Calendar)localObject2).set(5, k);
        }
        return new EventDate(bool2, ((Calendar)localObject2).getTime(), i8);
        if ((DATE_FORMAT_YYYY_MM_DD_HH_.equals(localObject3)) || (DATE_FORMAT_YYYY_MM_DD_HH_DOT.equals(localObject3)) || (DATE_FORMAT_HH_YYYY_MM_DD.equals(localObject3)) || (DATE_FORMAT_HH_YYYY_MM_DD_DOT.equals(localObject3)))
        {
          ((Calendar)localObject2).set(paramString.getYear() + 1900, paramString.getMonth(), paramString.getDate(), paramString.getHours(), 0);
          break;
        }
        if ((DATE_FORMAT_YYYY_MM_DD.equals(localObject3)) || (DATE_FORMAT_YYYY_MM_DD_DOT.equals(localObject3)))
        {
          ((Calendar)localObject2).set(paramString.getYear() + 1900, paramString.getMonth(), paramString.getDate());
          bool1 = true;
          break;
        }
        if ((DATE_FORMAT_MM_DD_YYYY_HH_MM.equals(localObject3)) || (DATE_FORMAT_HH_MM_MM_DD_YYYY.equals(localObject3)))
        {
          ((Calendar)localObject2).set(paramString.getYear() + 1900, paramString.getMonth(), paramString.getDate(), paramString.getHours(), paramString.getMinutes());
          break;
        }
        if ((DATE_FORMAT_MM_DD_YYYY_HH_.equals(localObject3)) || (DATE_FORMAT_HH_MM_DD_YYYY.equals(localObject3)))
        {
          ((Calendar)localObject2).set(paramString.getYear() + 1900, paramString.getMonth(), paramString.getDate(), paramString.getHours(), 0);
          break;
        }
        if ((DATE_FORMAT_MM_DD_HH_MM.equals(localObject3)) || (DATE_FORMAT_MM_DD_HH_MM_DOT.equals(localObject3)))
        {
          if ((paramDate != null) && (i1 == 0)) {
            ((Calendar)localObject2).set(1, paramDate.getYear() + 1900);
          }
          ((Calendar)localObject2).set(2, paramString.getMonth());
          ((Calendar)localObject2).set(5, paramString.getDate());
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, paramString.getMinutes());
          ((Calendar)localObject2).set(13, 0);
          break;
        }
        if ((DATE_FORMAT_MM_DD_HH_.equals(localObject3)) || (DATE_FORMAT_MM_DD_HH_DOT.equals(localObject3)) || (DATE_FORMAT_HH_MM_DD.equals(localObject3)))
        {
          if ((paramDate != null) && (i1 == 0)) {
            ((Calendar)localObject2).set(1, paramDate.getYear() + 1900);
          }
          ((Calendar)localObject2).set(2, paramString.getMonth());
          ((Calendar)localObject2).set(5, paramString.getDate());
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, 0);
          ((Calendar)localObject2).set(13, 0);
          break;
        }
        if (DATE_FORMAT_DD_HH_MM.equals(localObject3))
        {
          if ((paramDate != null) && (i1 == 0) && (i3 == 0))
          {
            ((Calendar)localObject2).set(1, paramDate.getYear() + 1900);
            ((Calendar)localObject2).set(2, paramDate.getMonth());
          }
          ((Calendar)localObject2).set(5, paramString.getDate());
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, paramString.getMinutes());
          break;
        }
        if (DATE_FORMAT_DD_HH_.equals(localObject3))
        {
          if ((paramDate != null) && (i1 == 0) && (i3 == 0))
          {
            ((Calendar)localObject2).set(1, paramDate.getYear() + 1900);
            ((Calendar)localObject2).set(2, paramDate.getMonth());
          }
          ((Calendar)localObject2).set(5, paramString.getDate());
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, 0);
          break;
        }
        if (DATE_FORMAT_HH_MM_SS.equals(localObject3))
        {
          if ((paramDate != null) && (i4 == 0) && (i == 0)) {
            ((Calendar)localObject2).set(paramDate.getYear() + 1900, paramDate.getMonth(), paramDate.getDate());
          }
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, paramString.getMinutes());
          ((Calendar)localObject2).set(13, paramString.getSeconds());
          ((Calendar)localObject2).add(5, paramString.getDate() - 1);
          break;
        }
        if ((DATE_FORMAT_MM_DD.equals(localObject3)) || (DATE_FORMAT_MM_DD_DOT.equals(localObject3)))
        {
          if ((paramDate != null) && (i1 == 0)) {
            ((Calendar)localObject2).set(1, paramDate.getYear() + 1900);
          }
          ((Calendar)localObject2).set(2, paramString.getMonth());
          ((Calendar)localObject2).set(5, paramString.getDate());
          bool1 = true;
          break;
        }
        if (DATE_FORMAT_HH_MM.equals(localObject3))
        {
          if ((paramDate != null) && (i4 == 0) && (i == 0)) {
            ((Calendar)localObject2).set(paramDate.getYear() + 1900, paramDate.getMonth(), paramDate.getDate());
          }
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, paramString.getMinutes());
          ((Calendar)localObject2).set(13, 0);
          ((Calendar)localObject2).add(5, paramString.getDate() - 1);
          break;
        }
        if (DATE_FORMAT_HH.equals(localObject3))
        {
          if ((paramDate != null) && (i4 == 0) && (i == 0)) {
            ((Calendar)localObject2).set(paramDate.getYear() + 1900, paramDate.getMonth(), paramDate.getDate());
          }
          ((Calendar)localObject2).set(11, paramString.getHours());
          ((Calendar)localObject2).set(12, 0);
          ((Calendar)localObject2).set(13, 0);
          ((Calendar)localObject2).add(5, paramString.getDate() - 1);
          break;
        }
        if (!DATE_FORMAT_DD.equals(localObject3)) {
          break;
        }
        if ((paramDate != null) && (i3 == 0))
        {
          ((Calendar)localObject2).set(1, paramDate.getYear() + 1900);
          ((Calendar)localObject2).set(2, paramDate.getMonth());
        }
        ((Calendar)localObject2).set(5, paramString.getDate());
        bool1 = true;
        break;
        label2474:
        bool2 = bool1;
        if (paramString.getHours() < 12)
        {
          ((Calendar)localObject2).add(11, i8);
          bool2 = bool1;
          continue;
          label2503:
          bool2 = bool1;
          if (paramDate != null)
          {
            bool2 = bool1;
            if (paramInt > 0)
            {
              bool2 = bool1;
              if (paramString.getHours() < 12)
              {
                ((Calendar)localObject2).add(11, 12);
                bool2 = bool1;
              }
            }
          }
        }
      }
    }
    else
    {
      if (i4 != 0) {
        ((Calendar)localObject2).add(5, i5);
      }
      bool1 = bool2;
      if (i10 != 0)
      {
        bool1 = true;
        ((Calendar)localObject2).set(7, m);
      }
      if ((i != 0) && (j != -1) && (k != -1))
      {
        ((Calendar)localObject2).set(2, j);
        ((Calendar)localObject2).set(5, k);
      }
      if (i6 != 0)
      {
        ((Calendar)localObject2).set(11, i7);
        ((Calendar)localObject2).set(12, 0);
        ((Calendar)localObject2).set(13, 0);
        bool1 = false;
      }
      return new EventDate(bool1, ((Calendar)localObject2).getTime(), i8);
    }
    label2664:
    return null;
  }
  
  public static List<EventDate> parseDate(String paramString)
  {
    ArrayList localArrayList1 = new ArrayList(2);
    ArrayList localArrayList2 = new ArrayList(2);
    if (TIME_PHRASE_PATTERN != null)
    {
      Matcher localMatcher = TIME_PHRASE_PATTERN.matcher(paramString);
      while (localMatcher.find()) {
        localArrayList2.add(paramString.substring(localMatcher.start(), localMatcher.end()));
      }
    }
    if (localArrayList2.size() == 1)
    {
      paramString = parseDate((String)localArrayList2.get(0), null, 0);
      if (paramString != null) {
        localArrayList1.add(paramString);
      }
    }
    do
    {
      do
      {
        do
        {
          return localArrayList1;
        } while (localArrayList2.size() != 2);
        paramString = parseDate((String)localArrayList2.get(0), null, 0);
      } while (paramString == null);
      localArrayList1.add(paramString);
      paramString = parseDate((String)localArrayList2.get(1), paramString.getDate(), paramString.getHourAlpha());
    } while (paramString == null);
    localArrayList1.add(paramString);
    return localArrayList1;
  }
  
  public static class EventDate
  {
    private Date mDate;
    private int mHourAlpha;
    private boolean mIsAllDayEvent = false;
    
    public EventDate(boolean paramBoolean, Date paramDate, int paramInt)
    {
      mIsAllDayEvent = paramBoolean;
      mDate = paramDate;
      mHourAlpha = paramInt;
    }
    
    public Date getDate()
    {
      return mDate;
    }
    
    public int getHourAlpha()
    {
      return mHourAlpha;
    }
    
    public boolean isAllDayEvent()
    {
      return mIsAllDayEvent;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.util.DateParseUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */