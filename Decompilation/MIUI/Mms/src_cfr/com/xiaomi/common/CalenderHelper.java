/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.ArrayList
 *  java.util.Date
 */
package com.xiaomi.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalenderHelper {
    private static Date baseDate;
    private static SimpleDateFormat dateFormat;
    static final long[] lunarInfo;

    static {
        dateFormat = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5");
        baseDate = null;
        lunarInfo = new long[]{19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448};
    }

    private static boolean check(int n, int n2, int n3, int n4, int n5, int n6) throws ParseException {
        n = CalenderHelper.getLunarDate(n, n2, n3);
        n2 = n / 10000;
        n3 = n % 10000 / 100;
        if (n2 == n4 && n3 == n5 && n % 100 == n6) {
            return true;
        }
        return false;
    }

    private static int compare(int n, int n2, int n3, int n4, int n5, int n6) throws ParseException {
        if ((n = CalenderHelper.getLunarDate(n, n2, n3)) == n4 * 10000 + n5 * 100 + n6) {
            return 0;
        }
        return (int)((dateFormat.parse(String.valueOf((int)(n / 10000)) + "\u5e74" + n % 10000 / 100 + "\u6708" + n % 100 + "\u65e5").getTime() - dateFormat.parse(String.valueOf((int)n4) + "\u5e74" + n5 + "\u6708" + n6 + "\u65e5").getTime()) / 86400000);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getLunarDate(int n, int n2, int n3) throws ParseException {
        int n4;
        int n5;
        int n6;
        block11 : {
            if (baseDate == null) {
                baseDate = dateFormat.parse("1900\u5e741\u670831\u65e5");
            }
            n5 = (int)((dateFormat.parse(String.valueOf((int)n) + "\u5e74" + n2 + "\u6708" + n3 + "\u65e5").getTime() - baseDate.getTime()) / 86400000);
            n = 14;
            n4 = 1900;
            int n7 = 0;
            do {
                if (n4 >= 2050 || n5 <= 0) {
                    n6 = n4;
                    n2 = n;
                    n3 = n5;
                    if (n5 < 0) {
                        n3 = n5 + n7;
                        n6 = n4 - 1;
                        n2 = n - 12;
                        break;
                    }
                    break;
                }
                n7 = CalenderHelper.yearDays(n4);
                n5 -= n7;
                n += 12;
                ++n4;
            } while (true);
            int n8 = CalenderHelper.leapMonth(n6);
            n5 = 0;
            n = 1;
            n4 = 0;
            do {
                if (n >= 13 || n3 <= 0) {
                    if (n3 == 0 && n8 > 0 && n == n8 + 1 && n4 == 0) break;
                    break block11;
                }
                if (n8 > 0 && n == n8 + 1 && n4 == 0) {
                    n4 = CalenderHelper.leapDays(n6);
                    n5 = n - 1;
                    n = 1;
                } else {
                    n7 = CalenderHelper.monthDays(n6, n);
                    n5 = n;
                    n = n4;
                    n4 = n7;
                }
                if (n != 0 && n5 == n8 + 1) {
                    n = 0;
                }
                n7 = n2;
                if (n == 0) {
                    n7 = n2 + 1;
                }
                int n9 = n;
                n3 -= n4;
                n2 = n7;
                n = ++n5;
                n5 = n4;
                n4 = n9;
            } while (true);
            --n2;
            --n;
        }
        n4 = n;
        n2 = n3;
        if (n3 < 0) {
            n2 = n3 + n5;
            n4 = n - 1;
        }
        return n6 * 10000 + n4 * 100 + (n2 + 1);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static List<int[]> getSunDate(int n, int n2, int n3) throws ParseException {
        int n4;
        Date date;
        ArrayList arrayList;
        int n5 = 2014;
        int n6 = 12;
        int n7 = 10;
        do {
            if (Math.abs((int)(n4 = CalenderHelper.compare(n5, n6, n7, n, n2, n3))) < 3) {
                date = dateFormat.parse(String.valueOf((int)n5) + "\u5e74" + n6 + "\u6708" + n7 + "\u65e5");
                date.setTime(date.getTime() - 3024000000L);
                arrayList = new ArrayList();
                break;
            }
            date = new Date(dateFormat.parse(String.valueOf((int)n5) + "\u5e74" + n6 + "\u6708" + n7 + "\u65e5").getTime() - (long)n4 * 86400000);
            n5 = date.getYear() + 1900;
            n6 = date.getMonth() + 1;
            n7 = date.getDate();
        } while (true);
        for (n5 = 0; n5 < 70; ++n5) {
            n6 = date.getYear() + 1900;
            int n8 = CalenderHelper.compare(n6, n7 = date.getMonth() + 1, n4 = date.getDate(), n, n2, n3);
            if (n8 < -3) {
                n6 = n8 < -30 ? n8 + 6 : n8 + 3;
                date.setTime(date.getTime() - (long)n6 * 86400000);
                n5 -= n6;
                continue;
            }
            if (n8 > 0 && n8 < 5) {
                n5 += 25;
                date.setTime(date.getTime() + 2160000000L);
                continue;
            }
            if (n8 > 32) break;
            if (n8 == 0 && CalenderHelper.check(n6, n7, n4, n, n2, n3)) {
                arrayList.add(new int[]{n6, n7, n4});
            }
            date.setTime(date.getTime() + 86400000);
        }
        return arrayList;
    }

    private static final int leapDays(int n) {
        if (CalenderHelper.leapMonth(n) != 0) {
            if ((lunarInfo[n - 1900] & 65536) != 0) {
                return 30;
            }
            return 29;
        }
        return 0;
    }

    private static final int leapMonth(int n) {
        return (int)(lunarInfo[n - 1900] & 15);
    }

    private static final int monthDays(int n, int n2) {
        if ((lunarInfo[n - 1900] & (long)(65536 >> n2)) == 0) {
            return 29;
        }
        return 30;
    }

    private static final int yearDays(int n) {
        int n2 = 348;
        int n3 = 32768;
        while (n3 > 8) {
            int n4 = n2;
            if ((lunarInfo[n - 1900] & (long)n3) != 0) {
                n4 = n2 + 1;
            }
            n3 >>= 1;
            n2 = n4;
        }
        return n2 + CalenderHelper.leapDays(n);
    }
}

