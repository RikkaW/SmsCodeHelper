/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Character
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.text.DateFormat
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Calendar
 *  java.util.Date
 *  java.util.HashMap
 *  java.util.Locale
 *  java.util.Map$Entry
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParseUtils {
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
    private static final DateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM;
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

    static {
        DATE_FORMAT_YYYY_MM_DD_HH_MM = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
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
        MONTH_DAY_ENG_PATTERN = Pattern.compile((String)MONTH_DAY_ENG);
        TIME_PHRASE_PATTERN = Pattern.compile((String)"((((((2[0-4]|(1|0?)[0-9]):([1-5][0-9]|0?[0-9])(:([1-5][0-9]|0?[0-9]))?([aApP][mM])?)|(2[0-4]|(1|0?)[0-9])([aApP][mM]))(\\s+,?|(,\\s*))(((((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))))(\\s+,?|(,\\s*))\\d{4})|(((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))))|(((\\d{4})-)?(1[012]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9]))|(((\\d{4})/)?(1[012]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9]))|((\\d{4})\\.(1[012]|0?[1-9])\\.(3[01]|[12][0-9]|0?[1-9]))))|((((2[0-4]|(1|0?)[0-9]):([1-5][0-9]|0?[0-9])(:([1-5][0-9]|0?[0-9]))?([aApP][mM])?)|(2[0-4]|(1|0?)[0-9])([aApP][mM]))(\\s+(,|([Oo][Nn]\\s+))?|(,\\s*))(\\b(([Mm][Oo][Nn]|[Tt][Uu][Ee][Ss]|[Tt][Hh][Uu][Rr]|[Ff][Rr][Ii]|[Ss][Uu][Nn])((\\.)|([Dd][Aa][Yy]))?|[Ww][Ee][Dd]((\\.)|([Nn][Ee][Ss][Dd][Aa][Yy]))?|[Ss][Aa][Tt]((\\.)|([Uu][Rr][Dd][Aa][Yy]))?)))|(((((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))))(\\s+,?|(,\\s*))\\d{4})|(((\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?))\\s+(1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3]))|((1([sS][tT])|2([nN][dD])|3([rR][dD])|((3[01]|[12][0-9]|0?[4-9])([tT][hH])?)|[1-3])\\s+(\\b([Jj](([Aa][Nn]((\\.)|([Uu][Aa][Rr][Yy]))?)|([Uu](([Nn]((\\.)|[Ee])?)|([Ll]((\\.)|[Yy])?))))|[Ff][Ee][Bb]((\\.)|([Rr][Uu][Aa][Rr][Yy]))?|[Mm][Aa](([Rr]((\\.)|([Cc][Hh]))?)|([Yy](\\.)?))|[Aa](([Pp][Rr]((\\.)|([Ii][Ll]))?)|([Uu][Gg]((\\.)|([Uu][Ss][Tt]))?))|([Ss][Ee][Pp][Tt]|[Nn][Oo][Vv]|[Dd][Ee][Cc])((\\.)|([Ee][Mm][Bb][Ee][Rr]))?|[Oo][Cc][Tt]((\\.)|([Oo][Bb][Ee][Rr]))?)))))|(((2[0-4]|(1|0?)[0-9]):([1-5][0-9]|0?[0-9])(:([1-5][0-9]|0?[0-9]))?([aApP][mM])?)|(2[0-4]|(1|0?)[0-9])([aApP][mM]))))|((((([\u5468\u9031]|(\u661f\u671f))[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u65e5\u5929])(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348)?)|(((\u4eca|\u660e|\u5927?\u540e)\u5929|\u6b21\u65e5)(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348))|(((((((\\d{4}|[\u96f6\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]{4}|(\u4eca|\u660e|\u5927?[\u540e\u5f8c]))\u5e74))?((1[012]|0?[1-9]|\u5341[\u4e00\u4e8c]?|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d])\u6708))?|((\u672c|\u4e0b[\u4e2a\u500b]?|\u8fd9\u4e2a|\u9019\u500b)\u6708))((3[01]|[12][0-9]|0?[1-9]|\u4e09\u5341\u4e00?|(\u4e8c?\u5341)[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d])(\u65e5|\u53f7|\u865f)))(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348)?)|(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348)|((\u4eca|\u660e|\u5927?\u540e)\u5929|\u6b21\u65e5))?(2[0-4]|(1|0?)[0-9]|\u4e8c\u5341[\u4e00\u4e8c\u4e09\u56db]?|\u5341?[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169]|\u96f6|\u5341)(((\u65f6|\u70b9|\u6642|\u9ede|[Pp][Mm])(([123\u4e00\u4e8c\u4e09\u4e24]\u523b|\u534a|[1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169])(\u5206(([1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169])\u79d2)?)?)?)|(:([123\u4e00\u4e8c\u4e09\u4e24]\u523b|\u534a|[1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169])(:([1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169]))?))|((([\u5468\u9031]|(\u661f\u671f))[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u65e5\u5929])(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348)?)|(((\u4eca|\u660e|\u5927?\u540e)\u5929|\u6b21\u65e5)(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348))|(((((((\\d{4}|[\u96f6\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]{4}|(\u4eca|\u660e|\u5927?[\u540e\u5f8c]))\u5e74))?((1[012]|0?[1-9]|\u5341[\u4e00\u4e8c]?|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d])\u6708))?|((\u672c|\u4e0b[\u4e2a\u500b]?|\u8fd9\u4e2a|\u9019\u500b)\u6708))((3[01]|[12][0-9]|0?[1-9]|\u4e09\u5341\u4e00?|(\u4e8c?\u5341)[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d])(\u65e5|\u53f7|\u865f)))(\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348)?)|((((\\d{4})-)?(1[012]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])|((\\d{4})/)?(1[012]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])|(\\d{4})\\.(1[012]|0?[1-9])\\.(3[01]|[12][0-9]|0?[1-9]))((\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348)|(\\s)+)((2[0-4]|(1|0?)[0-9]|\u4e8c\u5341[\u4e00\u4e8c\u4e09\u56db]?|\u5341?[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169]|\u96f6|\u5341)(((\u65f6|\u70b9|\u6642|\u9ede|[Pp][Mm])(([123\u4e00\u4e8c\u4e09\u4e24]\u523b|\u534a|[1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169])(\u5206(([1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169])\u79d2)?)?)?)|(:([123\u4e00\u4e8c\u4e09\u4e24]\u523b|\u534a|[1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169])(:([1-5][0-9]|0?[0-9]|[\u4e8c\u4e09\u56db\u4e94]?\u5341[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d]?|\u96f6|\u5341|[\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u4e24\u5169]))?))))|((((\\d{4})-)?(1[012]|0?[1-9])-(3[01]|[12][0-9]|0?[1-9])|((\\d{4})/)?(1[012]|0?[1-9])/(3[01]|[12][0-9]|0?[1-9])|(\\d{4})\\.(1[012]|0?[1-9])\\.(3[01]|[12][0-9]|0?[1-9]))((\u51cc\u6668|\u4e0a\u5348|\u4e0b\u5348|\u65e9\u4e0a|\u665a\u4e0a|\u508d\u665a|\u4e2d\u5348))?))");
        DATE_FORMATS = new DateFormat[]{DATE_FORMAT_YYYY_MM_DD_HH_MM, DATE_FORMAT_HH_MM_YYYY_MM_DD, DATE_FORMAT_YYYY_MM_DD_HH_MM_DOT, DATE_FORMAT_HH_MM_YYYY_MM_DD_DOT, DATE_FORMAT_YYYY_MM_DD_HH_, DATE_FORMAT_HH_YYYY_MM_DD, DATE_FORMAT_YYYY_MM_DD_HH_DOT, DATE_FORMAT_HH_YYYY_MM_DD_DOT, DATE_FORMAT_YYYY_MM_DD, DATE_FORMAT_YYYY_MM_DD_DOT, DATE_FORMAT_MM_DD_HH_MM, DATE_FORMAT_MM_DD_HH_MM_DOT, DATE_FORMAT_MM_DD_HH_, DATE_FORMAT_HH_MM_DD, DATE_FORMAT_MM_DD_HH_DOT, DATE_FORMAT_DD_HH_MM, DATE_FORMAT_DD_HH_, DATE_FORMAT_MM_DD_YYYY_HH_MM, DATE_FORMAT_HH_MM_MM_DD_YYYY, DATE_FORMAT_MM_DD_YYYY_HH_, DATE_FORMAT_HH_MM_DD_YYYY, DATE_FORMAT_MM_DD, DATE_FORMAT_MM_DD_DOT, DATE_FORMAT_HH_MM_SS, DATE_FORMAT_HH_MM, DATE_FORMAT_HH, DATE_FORMAT_DD};
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
        PREFIXES_TEN = "\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d";
        SUFFIXES_TEN = "\u4e00\u4e8c\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d";
        sDateCharacterMap.put(Character.valueOf((char)'\u5e74'), Character.valueOf((char)'/'));
        sDateCharacterMap.put(Character.valueOf((char)'\u6708'), Character.valueOf((char)'/'));
        sDateCharacterMap.put(Character.valueOf((char)'\u65e5'), Character.valueOf((char)' '));
        sDateCharacterMap.put(Character.valueOf((char)'\u53f7'), Character.valueOf((char)' '));
        sDateCharacterMap.put(Character.valueOf((char)'\u865f'), Character.valueOf((char)' '));
        sDateCharacterMap.put(Character.valueOf((char)'\u65f6'), Character.valueOf((char)':'));
        sDateCharacterMap.put(Character.valueOf((char)'\u6642'), Character.valueOf((char)' '));
        sDateCharacterMap.put(Character.valueOf((char)'\u70b9'), Character.valueOf((char)':'));
        sDateCharacterMap.put(Character.valueOf((char)'\u9ede'), Character.valueOf((char)' '));
        sDateCharacterMap.put(Character.valueOf((char)'\u5206'), Character.valueOf((char)' '));
        sDateCharacterMap.put(Character.valueOf((char)'-'), Character.valueOf((char)'/'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e00'), Character.valueOf((char)'1'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e8c'), Character.valueOf((char)'2'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e09'), Character.valueOf((char)'3'));
        sDateCharacterMap.put(Character.valueOf((char)'\u56db'), Character.valueOf((char)'4'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e94'), Character.valueOf((char)'5'));
        sDateCharacterMap.put(Character.valueOf((char)'\u516d'), Character.valueOf((char)'6'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e03'), Character.valueOf((char)'7'));
        sDateCharacterMap.put(Character.valueOf((char)'\u516b'), Character.valueOf((char)'8'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e5d'), Character.valueOf((char)'9'));
        sDateCharacterMap.put(Character.valueOf((char)'\u5341'), Character.valueOf((char)'1'));
        sDateCharacterMap.put(Character.valueOf((char)'\u4e24'), Character.valueOf((char)'2'));
        sDateCharacterMap.put(Character.valueOf((char)'\u5169'), Character.valueOf((char)'2'));
        sDateCharacterMap.put(Character.valueOf((char)'\u96f6'), Character.valueOf((char)'0'));
        sChineseDayStringMap.put("\u4eca\u5929", 0);
        sChineseDayStringMap.put("\u4eca\u65e5", 0);
        sChineseDayStringMap.put("\u6b21\u65e5", 1);
        sChineseDayStringMap.put("\u660e\u5929", 1);
        sChineseDayStringMap.put("\u660e\u65e5", 1);
        sChineseDayStringMap.put("\u540e\u5929", 2);
        sChineseDayStringMap.put("\u5f8c\u5929", 2);
        sChineseDayStringMap.put("\u5927\u540e\u5929", 3);
        sChineseDayStringMap.put("\u5927\u5f8c\u5929", 3);
        sChineseMonthStringMap.put("\u672c\u6708", 0);
        sChineseMonthStringMap.put("\u8fd9\u4e2a\u6708", 0);
        sChineseMonthStringMap.put("\u9019\u500b\u6708", 0);
        sChineseMonthStringMap.put("\u4e0b\u6708", 1);
        sChineseMonthStringMap.put("\u4e0b\u4e2a\u6708", 1);
        sChineseMonthStringMap.put("\u4e0b\u500b\u6708", 1);
        sChineseYearStringMap.put("\u4eca\u5e74", 0);
        sChineseYearStringMap.put("\u660e\u5e74", 1);
        sChineseYearStringMap.put("\u540e\u5e74", 2);
        sChineseYearStringMap.put("\u5f8c\u5e74", 2);
        sChineseYearStringMap.put("\u5927\u540e\u5e74", 3);
        sChineseYearStringMap.put("\u5927\u5f8c\u5e74", 3);
        sChineseHourAlphaMap.put("\u51cc\u6668", 0);
        sChineseHourAlphaMap.put("\u65e9\u4e0a", 0);
        sChineseHourAlphaMap.put("\u65e9\u6668", 0);
        sChineseHourAlphaMap.put("\u4e0a\u5348", 0);
        sChineseHourAlphaMap.put("\u4e2d\u5348", 0);
        sChineseHourAlphaMap.put("\u4e0b\u5348", 12);
        sChineseHourAlphaMap.put("\u508d\u665a", 12);
        sChineseHourAlphaMap.put("\u665a\u4e0a", 12);
        sEnglishHourAlphaMap.put("pm", 12);
        sEnglishHourAlphaMap.put("am", 0);
        sChineseHourOfDayMap.put("\u51cc\u6668", 0);
        sChineseHourOfDayMap.put("\u65e9\u4e0a", 9);
        sChineseHourOfDayMap.put("\u65e9\u6668", 9);
        sChineseHourOfDayMap.put("\u4e0a\u5348", 9);
        sChineseHourOfDayMap.put("\u4e2d\u5348", 12);
        sChineseHourOfDayMap.put("\u4e0b\u5348", 14);
        sChineseHourOfDayMap.put("\u508d\u665a", 17);
        sChineseHourOfDayMap.put("\u665a\u4e0a", 20);
        sChineseMinuteMap.put("\u534a", "30");
        sChineseMinuteMap.put("1\u523b", "15");
        sChineseMinuteMap.put("2\u523b", "30");
        sChineseMinuteMap.put("3\u523b", "45");
        sWeeklyDayMap.put("\u54681", 2);
        sWeeklyDayMap.put("\u5468\u4e00", 2);
        sWeeklyDayMap.put("\u90311", 2);
        sWeeklyDayMap.put("\u9031\u4e00", 2);
        sWeeklyDayMap.put("\u54682", 3);
        sWeeklyDayMap.put("\u5468\u4e8c", 3);
        sWeeklyDayMap.put("\u90312", 3);
        sWeeklyDayMap.put("\u9031\u4e8c", 3);
        sWeeklyDayMap.put("\u54683", 4);
        sWeeklyDayMap.put("\u5468\u4e09", 4);
        sWeeklyDayMap.put("\u90313", 4);
        sWeeklyDayMap.put("\u9031\u4e09", 4);
        sWeeklyDayMap.put("\u54684", 5);
        sWeeklyDayMap.put("\u5468\u56db", 5);
        sWeeklyDayMap.put("\u90314", 5);
        sWeeklyDayMap.put("\u9031\u56db", 5);
        sWeeklyDayMap.put("\u54685", 6);
        sWeeklyDayMap.put("\u5468\u4e94", 6);
        sWeeklyDayMap.put("\u90315", 6);
        sWeeklyDayMap.put("\u9031\u4e94", 6);
        sWeeklyDayMap.put("\u54686", 7);
        sWeeklyDayMap.put("\u5468\u516d", 7);
        sWeeklyDayMap.put("\u90316", 7);
        sWeeklyDayMap.put("\u9031\u516d", 7);
        sWeeklyDayMap.put("\u5468\u65e5", 1);
        sWeeklyDayMap.put("\u9031\u65e5", 1);
        sWeeklyDayMap.put("\u5468\u5929", 1);
        sWeeklyDayMap.put("\u9031\u5929", 1);
        sWeeklyDayMap.put("monday", 2);
        sWeeklyDayMap.put("tuesday", 3);
        sWeeklyDayMap.put("wednesday", 4);
        sWeeklyDayMap.put("thursday", 5);
        sWeeklyDayMap.put("friday", 6);
        sWeeklyDayMap.put("saturday", 7);
        sWeeklyDayMap.put("sunday", 1);
        sWeeklyDayMap.put("mon.", 2);
        sWeeklyDayMap.put("tues.", 3);
        sWeeklyDayMap.put("wed.", 4);
        sWeeklyDayMap.put("thur.", 5);
        sWeeklyDayMap.put("fri.", 6);
        sWeeklyDayMap.put("sat.", 7);
        sWeeklyDayMap.put("sun.", 1);
        sWeeklyDayMap.put("mon", 2);
        sWeeklyDayMap.put("tues", 3);
        sWeeklyDayMap.put("wed", 4);
        sWeeklyDayMap.put("thur", 5);
        sWeeklyDayMap.put("fri", 6);
        sWeeklyDayMap.put("sat", 7);
        sWeeklyDayMap.put("sun", 1);
        sMonthMap.put("january", 0);
        sMonthMap.put("february", 1);
        sMonthMap.put("march", 2);
        sMonthMap.put("april", 3);
        sMonthMap.put("may", 4);
        sMonthMap.put("june", 5);
        sMonthMap.put("july", 6);
        sMonthMap.put("august", 7);
        sMonthMap.put("september", 8);
        sMonthMap.put("october", 9);
        sMonthMap.put("november", 10);
        sMonthMap.put("december", 11);
        sMonthMap.put("jan.", 0);
        sMonthMap.put("feb.", 1);
        sMonthMap.put("mar.", 2);
        sMonthMap.put("apr.", 3);
        sMonthMap.put("may.", 4);
        sMonthMap.put("jun.", 5);
        sMonthMap.put("jul.", 6);
        sMonthMap.put("aug.", 7);
        sMonthMap.put("sept.", 8);
        sMonthMap.put("oct.", 9);
        sMonthMap.put("nov.", 10);
        sMonthMap.put("dec.", 11);
        sMonthMap.put("jan", 0);
        sMonthMap.put("feb", 1);
        sMonthMap.put("mar", 2);
        sMonthMap.put("apr", 3);
        sMonthMap.put("may", 4);
        sMonthMap.put("jun", 5);
        sMonthMap.put("jul", 6);
        sMonthMap.put("aug", 7);
        sMonthMap.put("sept", 8);
        sMonthMap.put("oct", 9);
        sMonthMap.put("nov", 10);
        sMonthMap.put("dec", 11);
    }

    public static DateFormat findMatchedPattern(String string2) {
        for (DateFormat dateFormat : DATE_FORMATS) {
            ParsePosition parsePosition = new ParsePosition(0);
            if (dateFormat.parse(string2, parsePosition) == null || parsePosition.getErrorIndex() != -1) continue;
            Log.d((String)"DateParseUtils", (String)("matched pattern: " + ((SimpleDateFormat)dateFormat).toPattern()));
            return dateFormat;
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String formatDateString(String string2) {
        if (string2 == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        string2 = string2.trim();
        int n = 0;
        while (n < string2.length()) {
            char c2 = string2.charAt(n);
            if (c2 == '\u5341') {
                char c3;
                if (n > 0) {
                    c3 = string2.charAt(n - 1);
                    if (PREFIXES_TEN.indexOf((int)c3) != -1) {
                        if (n < string2.length() - 1) {
                            c3 = string2.charAt(n + 1);
                            if (SUFFIXES_TEN.indexOf((int)c3) == -1) {
                                stringBuffer.append("0");
                            }
                        } else {
                            stringBuffer.append("0");
                        }
                    } else if (n < string2.length() - 1) {
                        c3 = string2.charAt(n + 1);
                        if (SUFFIXES_TEN.indexOf((int)c3) != -1) {
                            stringBuffer.append((Object)sDateCharacterMap.get((Object)Character.valueOf((char)c2)));
                        } else {
                            stringBuffer.append("10");
                        }
                    } else {
                        stringBuffer.append("10");
                    }
                } else if (n < string2.length() - 1) {
                    c3 = string2.charAt(n + 1);
                    if (SUFFIXES_TEN.indexOf((int)c3) != -1) {
                        stringBuffer.append((Object)sDateCharacterMap.get((Object)Character.valueOf((char)c2)));
                    } else {
                        stringBuffer.append("10");
                    }
                } else {
                    stringBuffer.append("10");
                }
            } else if (sDateCharacterMap.containsKey((Object)Character.valueOf((char)c2))) {
                stringBuffer.append((Object)sDateCharacterMap.get((Object)Character.valueOf((char)c2)));
            } else {
                stringBuffer.append(c2);
            }
            ++n;
        }
        return stringBuffer.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static EventDate parseDate(String var0, Date var1_1, int var2_2) {
        block75 : {
            var23_3 = var0.toLowerCase().trim().replace((CharSequence)"\u661f\u671f", (CharSequence)"\u5468");
            var9_4 = 0;
            var6_5 = 0;
            var7_6 = -1;
            var8_7 = -1;
            var24_8 = DateParseUtils.MONTH_DAY_ENG_PATTERN.matcher((CharSequence)var23_3);
            var5_9 = var8_7;
            var4_10 = var7_6;
            var22_11 = var23_3;
            var3_13 = var9_4;
            if (!var24_8.matches()) ** GOTO lbl41
            var24_8 = var23_3.substring(var24_8.start(), var24_8.end());
            var5_9 = var8_7;
            var4_10 = var7_6;
            var22_11 = var23_3;
            var3_13 = var9_4;
            if (TextUtils.isEmpty((CharSequence)var24_8)) ** GOTO lbl41
            var26_14 = DateParseUtils.sMonthMap.keySet().iterator();
            var22_11 = var24_8;
            do {
                block74 : {
                    block73 : {
                        block72 : {
                            block71 : {
                                block70 : {
                                    block69 : {
                                        var25_15 = var22_11;
                                        var5_9 = var8_7;
                                        var4_10 = var7_6;
                                        var22_11 = var23_3;
                                        var3_13 = var6_5;
                                        if (var26_14.hasNext()) {
                                            var27_16 = var26_14.next();
                                            var22_11 = var25_15;
                                            if (!var25_15.contains((CharSequence)var27_16)) continue;
                                            var4_10 = DateParseUtils.sMonthMap.get(var27_16);
                                            var25_15 = var25_15.replace((CharSequence)var27_16, (CharSequence)"").trim().replaceAll("((th)|(rd)|(st)|(nd))", "").trim();
                                            var7_6 = var4_10;
                                            var22_11 = var25_15;
                                            if (TextUtils.isEmpty((CharSequence)var25_15)) continue;
                                            var5_9 = Integer.valueOf((String)var25_15);
                                            var6_5 = 1;
                                            var3_13 = 1;
                                            var8_7 = var5_9;
                                            var22_11 = var23_3.replace((CharSequence)var24_8, (CharSequence)"");
                                        }
lbl41: // 5 sources:
                                        var6_5 = 0;
                                        var9_4 = 0;
                                        var0 = var22_11;
                                        var8_7 = var6_5;
                                        var7_6 = var9_4;
                                        if (!TextUtils.isEmpty((CharSequence)var22_11)) {
                                            var23_3 = DateParseUtils.sChineseYearStringMap.keySet().iterator();
                                            do {
                                                var0 = var22_11;
                                                var8_7 = var6_5;
                                                var7_6 = var9_4;
                                                if (!var23_3.hasNext()) break block69;
                                            } while (!var22_11.contains((CharSequence)(var0 = (String)var23_3.next())));
                                            var8_7 = 1;
                                            var7_6 = DateParseUtils.sChineseYearStringMap.get(var0);
                                            var0 = var22_11.replace((CharSequence)var0, (CharSequence)"").trim();
                                        }
                                    }
                                    var6_5 = 0;
                                    var11_17 = 0;
                                    var22_11 = var0;
                                    var10_18 = var6_5;
                                    var9_4 = var11_17;
                                    if (!TextUtils.isEmpty((CharSequence)var0)) {
                                        var23_3 = DateParseUtils.sChineseMonthStringMap.keySet().iterator();
                                        do {
                                            var22_11 = var0;
                                            var10_18 = var6_5;
                                            var9_4 = var11_17;
                                            if (!var23_3.hasNext()) break block70;
                                        } while (!var0.contains((CharSequence)(var22_11 = var23_3.next())));
                                        var10_18 = 1;
                                        var9_4 = DateParseUtils.sChineseMonthStringMap.get(var22_11);
                                        var22_11 = var0.replace((CharSequence)var22_11, (CharSequence)"").trim();
                                    }
                                }
                                var6_5 = 0;
                                var12_20 = var13_19 = 0;
                                var0 = var22_11;
                                var11_17 = var6_5;
                                if (!TextUtils.isEmpty((CharSequence)var22_11)) {
                                    var23_3 = DateParseUtils.sChineseDayStringMap.keySet().iterator();
                                    do {
                                        var12_20 = var13_19;
                                        var0 = var22_11;
                                        var11_17 = var6_5;
                                        if (!var23_3.hasNext()) break block71;
                                    } while (!var22_11.contains((CharSequence)(var0 = var23_3.next())));
                                    var11_17 = 1;
                                    var12_20 = DateParseUtils.sChineseDayStringMap.get(var0);
                                    var0 = var22_11.replace((CharSequence)var0, (CharSequence)"").trim();
                                }
                            }
                            var15_21 = 0;
                            var16_22 = 0;
                            var14_24 = var17_23 = 0;
                            var22_11 = var0;
                            var13_19 = var15_21;
                            var6_5 = var16_22;
                            if (!TextUtils.isEmpty((CharSequence)var0)) {
                                var23_3 = DateParseUtils.sChineseHourAlphaMap.keySet().iterator();
                                do {
                                    var14_24 = var17_23;
                                    var22_11 = var0;
                                    var13_19 = var15_21;
                                    var6_5 = var16_22;
                                    if (!var23_3.hasNext()) break block72;
                                } while (!var0.contains((CharSequence)(var22_11 = var23_3.next())));
                                var13_19 = 1;
                                var6_5 = DateParseUtils.sChineseHourAlphaMap.get(var22_11);
                                var14_24 = DateParseUtils.sChineseHourOfDayMap.get(var22_11);
                                var22_11 = var0.replace((CharSequence)var22_11, (CharSequence)" ").trim();
                            }
                        }
                        var17_23 = 0;
                        var0 = var22_11;
                        var16_22 = var17_23;
                        var15_21 = var6_5;
                        if (!TextUtils.isEmpty((CharSequence)var22_11)) {
                            var23_3 = DateParseUtils.sEnglishHourAlphaMap.keySet().iterator();
                            do {
                                var0 = var22_11;
                                var16_22 = var17_23;
                                var15_21 = var6_5;
                                if (!var23_3.hasNext()) break block73;
                            } while (!var22_11.contains((CharSequence)(var0 = var23_3.next())));
                            var16_22 = 1;
                            var15_21 = DateParseUtils.sEnglishHourAlphaMap.get(var0);
                            var0 = var22_11.replace(var0, (CharSequence)":").trim();
                        }
                    }
                    var18_25 = 0;
                    var19_26 = 0;
                    var22_11 = var0;
                    var17_23 = var18_25;
                    var6_5 = var19_26;
                    if (!TextUtils.isEmpty((CharSequence)var0)) {
                        var23_3 = DateParseUtils.sWeeklyDayMap.keySet().iterator();
                        do {
                            var22_11 = var0;
                            var17_23 = var18_25;
                            var6_5 = var19_26;
                            if (!var23_3.hasNext()) break block74;
                        } while (!var0.contains((CharSequence)(var22_11 = var23_3.next())));
                        var17_23 = 1;
                        var6_5 = DateParseUtils.sWeeklyDayMap.get(var22_11);
                        Log.d((String)"DateParseUtils", (String)((String)var0 + ", weekAlpha:" + var6_5));
                        var22_11 = var0.replace((CharSequence)var22_11, (CharSequence)"").trim();
                    }
                }
                var0 = var22_11 = DateParseUtils.formatDateString((String)var22_11);
                if (TextUtils.isEmpty((CharSequence)var22_11)) break block75;
                for (Map.Entry<String, String> var23_3 : DateParseUtils.sChineseMinuteMap.entrySet()) {
                    if (!var22_11.contains((CharSequence)((String)var23_3.getKey()))) continue;
                    var22_11 = var22_11.replace((CharSequence)var23_3.getKey(), (CharSequence)var23_3.getValue());
                }
                break;
                catch (NumberFormatException var22_12) {
                    Log.e((String)"DateParseUtils", (String)("exception thrown when parsing english date format:" + (String)var0 + ", " + var22_12.toString()));
                    var7_6 = var4_10;
                    var22_11 = var25_15;
                    continue;
                }
                break;
            } while (true);
            var0 = var22_11.replace((CharSequence)"on", (CharSequence)"").trim();
        }
        Log.d((String)"DateParseUtils", (String)("formatDate:" + var0));
        var22_11 = Calendar.getInstance();
        var21_27 = false;
        var20_28 = false;
        var23_3 = DateParseUtils.findMatchedPattern((String)var0);
        if (var23_3 != null) {
            var0 = var23_3.parse((String)var0, new ParsePosition(0));
            if (DateParseUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_DOT.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_MM_YYYY_MM_DD.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_MM_YYYY_MM_DD_DOT.equals((Object)var23_3)) {
                var22_11.set(var0.getYear() + 1900, var0.getMonth(), var0.getDate(), var0.getHours(), var0.getMinutes());
            } else if (DateParseUtils.DATE_FORMAT_YYYY_MM_DD_HH_.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_YYYY_MM_DD_HH_DOT.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_YYYY_MM_DD.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_YYYY_MM_DD_DOT.equals((Object)var23_3)) {
                var22_11.set(var0.getYear() + 1900, var0.getMonth(), var0.getDate(), var0.getHours(), 0);
            } else if (DateParseUtils.DATE_FORMAT_YYYY_MM_DD.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_YYYY_MM_DD_DOT.equals((Object)var23_3)) {
                var22_11.set(var0.getYear() + 1900, var0.getMonth(), var0.getDate());
                var20_28 = true;
            } else if (DateParseUtils.DATE_FORMAT_MM_DD_YYYY_HH_MM.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_MM_MM_DD_YYYY.equals((Object)var23_3)) {
                var22_11.set(var0.getYear() + 1900, var0.getMonth(), var0.getDate(), var0.getHours(), var0.getMinutes());
            } else if (DateParseUtils.DATE_FORMAT_MM_DD_YYYY_HH_.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_MM_DD_YYYY.equals((Object)var23_3)) {
                var22_11.set(var0.getYear() + 1900, var0.getMonth(), var0.getDate(), var0.getHours(), 0);
            } else if (DateParseUtils.DATE_FORMAT_MM_DD_HH_MM.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_MM_DD_HH_MM_DOT.equals((Object)var23_3)) {
                if (var1_1 != null && var8_7 == 0) {
                    var22_11.set(1, var1_1.getYear() + 1900);
                }
                var22_11.set(2, var0.getMonth());
                var22_11.set(5, var0.getDate());
                var22_11.set(11, var0.getHours());
                var22_11.set(12, var0.getMinutes());
                var22_11.set(13, 0);
            } else if (DateParseUtils.DATE_FORMAT_MM_DD_HH_.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_MM_DD_HH_DOT.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_HH_MM_DD.equals((Object)var23_3)) {
                if (var1_1 != null && var8_7 == 0) {
                    var22_11.set(1, var1_1.getYear() + 1900);
                }
                var22_11.set(2, var0.getMonth());
                var22_11.set(5, var0.getDate());
                var22_11.set(11, var0.getHours());
                var22_11.set(12, 0);
                var22_11.set(13, 0);
            } else if (DateParseUtils.DATE_FORMAT_DD_HH_MM.equals((Object)var23_3)) {
                if (var1_1 != null && var8_7 == 0 && var10_18 == 0) {
                    var22_11.set(1, var1_1.getYear() + 1900);
                    var22_11.set(2, var1_1.getMonth());
                }
                var22_11.set(5, var0.getDate());
                var22_11.set(11, var0.getHours());
                var22_11.set(12, var0.getMinutes());
            } else if (DateParseUtils.DATE_FORMAT_DD_HH_.equals((Object)var23_3)) {
                if (var1_1 != null && var8_7 == 0 && var10_18 == 0) {
                    var22_11.set(1, var1_1.getYear() + 1900);
                    var22_11.set(2, var1_1.getMonth());
                }
                var22_11.set(5, var0.getDate());
                var22_11.set(11, var0.getHours());
                var22_11.set(12, 0);
            } else if (DateParseUtils.DATE_FORMAT_HH_MM_SS.equals((Object)var23_3)) {
                if (var1_1 != null && var11_17 == 0 && var3_13 == 0) {
                    var22_11.set(var1_1.getYear() + 1900, var1_1.getMonth(), var1_1.getDate());
                }
                var22_11.set(11, var0.getHours());
                var22_11.set(12, var0.getMinutes());
                var22_11.set(13, var0.getSeconds());
                var22_11.add(5, var0.getDate() - 1);
            } else if (DateParseUtils.DATE_FORMAT_MM_DD.equals((Object)var23_3) || DateParseUtils.DATE_FORMAT_MM_DD_DOT.equals((Object)var23_3)) {
                if (var1_1 != null && var8_7 == 0) {
                    var22_11.set(1, var1_1.getYear() + 1900);
                }
                var22_11.set(2, var0.getMonth());
                var22_11.set(5, var0.getDate());
                var20_28 = true;
            } else if (DateParseUtils.DATE_FORMAT_HH_MM.equals((Object)var23_3)) {
                if (var1_1 != null && var11_17 == 0 && var3_13 == 0) {
                    var22_11.set(var1_1.getYear() + 1900, var1_1.getMonth(), var1_1.getDate());
                }
                var22_11.set(11, var0.getHours());
                var22_11.set(12, var0.getMinutes());
                var22_11.set(13, 0);
                var22_11.add(5, var0.getDate() - 1);
            } else if (DateParseUtils.DATE_FORMAT_HH.equals((Object)var23_3)) {
                if (var1_1 != null && var11_17 == 0 && var3_13 == 0) {
                    var22_11.set(var1_1.getYear() + 1900, var1_1.getMonth(), var1_1.getDate());
                }
                var22_11.set(11, var0.getHours());
                var22_11.set(12, 0);
                var22_11.set(13, 0);
                var22_11.add(5, var0.getDate() - 1);
            } else if (DateParseUtils.DATE_FORMAT_DD.equals((Object)var23_3)) {
                if (var1_1 != null && var10_18 == 0) {
                    var22_11.set(1, var1_1.getYear() + 1900);
                    var22_11.set(2, var1_1.getMonth());
                }
                var22_11.set(5, var0.getDate());
                var20_28 = true;
            }
            if (var0 == null) return null;
            if (var8_7 != 0) {
                var22_11.add(1, var7_6);
            }
            if (var10_18 != 0) {
                var22_11.add(2, var9_4);
            }
            if (var11_17 != 0) {
                var22_11.add(5, var12_20);
            }
            if (var13_19 != 0 || var16_22 != 0) {
                if (var20_28 && var13_19 != 0) {
                    var21_27 = false;
                    var22_11.set(11, var14_24);
                    var22_11.set(12, 0);
                    var22_11.set(13, 0);
                } else {
                    var21_27 = var20_28;
                    if (var0.getHours() < 12) {
                        var22_11.add(11, var15_21);
                        var21_27 = var20_28;
                    }
                }
            } else {
                var21_27 = var20_28;
                if (var1_1 != null) {
                    var21_27 = var20_28;
                    if (var2_2 > 0) {
                        var21_27 = var20_28;
                        if (var0.getHours() < 12) {
                            var22_11.add(11, 12);
                            var21_27 = var20_28;
                        }
                    }
                }
            }
            if (var17_23 != 0) {
                var22_11.set(7, var6_5);
            }
            if (var3_13 == 0) return new EventDate(var21_27, var22_11.getTime(), var15_21);
            if (var4_10 == -1) return new EventDate(var21_27, var22_11.getTime(), var15_21);
            if (var5_9 == -1) return new EventDate(var21_27, var22_11.getTime(), var15_21);
            var22_11.set(2, var4_10);
            var22_11.set(5, var5_9);
            return new EventDate(var21_27, var22_11.getTime(), var15_21);
        }
        if (var11_17 != 0) {
            var22_11.add(5, var12_20);
        }
        var20_28 = var21_27;
        if (var17_23 != 0) {
            var20_28 = true;
            var22_11.set(7, var6_5);
        }
        if (var3_13 != 0 && var4_10 != -1 && var5_9 != -1) {
            var22_11.set(2, var4_10);
            var22_11.set(5, var5_9);
        }
        if (var13_19 == 0) return new EventDate(var20_28, var22_11.getTime(), var15_21);
        var22_11.set(11, var14_24);
        var22_11.set(12, 0);
        var22_11.set(13, 0);
        var20_28 = false;
        return new EventDate(var20_28, var22_11.getTime(), var15_21);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static List<EventDate> parseDate(String object) {
        ArrayList arrayList = new ArrayList(2);
        ArrayList arrayList2 = new ArrayList(2);
        if (TIME_PHRASE_PATTERN != null) {
            Matcher matcher = TIME_PHRASE_PATTERN.matcher((CharSequence)object);
            while (matcher.find()) {
                arrayList2.add(object.substring(matcher.start(), matcher.end()));
            }
        }
        if (arrayList2.size() == 1) {
            object = DateParseUtils.parseDate((String)arrayList2.get(0), null, 0);
            if (object == null) return arrayList;
            {
                arrayList.add(object);
                return arrayList;
            }
        } else {
            if (arrayList2.size() != 2 || (object = DateParseUtils.parseDate((String)arrayList2.get(0), null, 0)) == null) return arrayList;
            {
                arrayList.add(object);
                object = DateParseUtils.parseDate((String)arrayList2.get(1), object.getDate(), object.getHourAlpha());
                if (object == null) return arrayList;
                {
                    arrayList.add(object);
                    return arrayList;
                }
            }
        }
    }

    public static class EventDate {
        private Date mDate;
        private int mHourAlpha;
        private boolean mIsAllDayEvent = false;

        public EventDate(boolean bl, Date date, int n) {
            this.mIsAllDayEvent = bl;
            this.mDate = date;
            this.mHourAlpha = n;
        }

        public Date getDate() {
            return this.mDate;
        }

        public int getHourAlpha() {
            return this.mHourAlpha;
        }

        public boolean isAllDayEvent() {
            return this.mIsAllDayEvent;
        }
    }

}

