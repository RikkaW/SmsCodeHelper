/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.Calendar
 *  java.util.Date
 *  java.util.HashMap
 *  java.util.Locale
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import android.text.TextUtils;
import com.ted.android.utils.TedSDKLog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class aqn {
    private static final String a = aqn.class.getSimpleName();
    private static Pattern b = Pattern.compile((String)"[\\u4e00-\\u9fa5]");
    private static final String[] c = new String[]{"\u96f6", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d", "\u4e03", "\u516b", "\u4e5d", "\u5341", "\u5341\u4e00", "\u5341\u4e8c", "\u5341\u4e09", "\u5341\u56db", "\u5341\u4e94", "\u5341\u516d", "\u5341\u4e03", "\u5341\u516b", "\u5341\u4e5d", "\u4e8c\u5341", "\u4e8c\u5341\u4e00", "\u4e8c\u5341\u4e8c", "\u4e8c\u5341\u4e09", "\u4e8c\u5341\u56db", "\u4e8c\u5341\u4e94", "\u4e8c\u5341\u516d", "\u4e8c\u5341\u4e03", "\u4e8c\u5341\u516b", "\u4e8c\u5341\u4e5d", "\u4e09\u5341", "\u4e09\u5341\u4e00"};
    private static String[] d = new String[]{"\u5c0f\u5bd2", "\u5927\u5bd2", "\u7acb\u6625", "\u96e8\u6c34", "\u60ca\u86f0", "\u6625\u5206", "\u6e05\u660e", "\u8c37\u96e8", "\u7acb\u590f", "\u5c0f\u6ee1", "\u8292\u79cd", "\u590f\u81f3", "\u5c0f\u6691", "\u5927\u6691", "\u7acb\u79cb", "\u5904\u6691", "\u767d\u9732", "\u79cb\u5206", "\u5bd2\u9732", "\u971c\u964d", "\u7acb\u51ac", "\u5c0f\u96ea", "\u5927\u96ea", "\u51ac\u81f3"};
    private static final int[] e = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static long[] f;
    private static SimpleDateFormat g;
    private static SimpleDateFormat h;
    private static HashMap<String, String> i;
    private static HashMap<String, String> j;
    private static HashMap<String, String> k;
    private static int[] l;
    private static int[] m;
    private static int[] n;

    static {
        long[] arrl = new long[24];
        arrl[1] = 21208;
        arrl[2] = 42467;
        arrl[3] = 63836;
        arrl[4] = 85337;
        arrl[5] = 107014;
        arrl[6] = 128867;
        arrl[7] = 150921;
        arrl[8] = 173149;
        arrl[9] = 195551;
        arrl[10] = 218072;
        arrl[11] = 240693;
        arrl[12] = 263343;
        arrl[13] = 285989;
        arrl[14] = 308563;
        arrl[15] = 331033;
        arrl[16] = 353350;
        arrl[17] = 375494;
        arrl[18] = 397447;
        arrl[19] = 419210;
        arrl[20] = 440795;
        arrl[21] = 462224;
        arrl[22] = 483532;
        arrl[23] = 504758;
        f = arrl;
        g = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5", Locale.ROOT);
        h = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5 HH:mm", Locale.ROOT);
        i = new HashMap();
        j = new HashMap();
        k = new HashMap();
        l = new int[]{5, 1, 2, 5, 7, 3, 5, 1, 4, 6, 2, 4};
        m = new int[]{4, 7, 7, 3, 5, 1, 3, 6, 2, 4, 7, 2};
        n = new int[]{3, 6, 6, 2, 4, 7, 2, 5, 1, 3, 6, 1};
        i.put((Object)"\u5143\u65e6", (Object)"01\u670801\u65e5");
        i.put((Object)"\u9ed1\u4eba\u65e5", (Object)"01\u670804\u65e5");
        i.put((Object)"\u5c0f\u5bd2", (Object)"01\u670805\u65e5");
        i.put((Object)"\u56fd\u9645\u9ebb\u98ce\u8282", (Object)"01\u670825\u65e5");
        i.put((Object)"\u814a\u516b\u8282", (Object)"01\u670827\u65e5");
        i.put((Object)"\u4e16\u754c\u6e7f\u5730\u65e5", (Object)"02\u670802\u65e5");
        i.put((Object)"\u7acb\u6625", (Object)"02\u670804\u65e5");
        i.put((Object)"\u56fd\u9645\u58f0\u63f4\u5357\u975e\u65e5", (Object)"02\u670807\u65e5");
        i.put((Object)"\u56fd\u9645\u6c14\u8c61\u8282", (Object)"02\u670810\u65e5");
        i.put((Object)"\u5c0f\u5e74", (Object)"02\u670811\u65e5");
        i.put((Object)"\u60c5\u4eba\u8282", (Object)"02\u670814\u65e5");
        i.put((Object)"\u9664\u5915", (Object)"02\u670818\u65e5");
        i.put((Object)"\u6625\u8282", (Object)"02\u670819\u65e5");
        i.put((Object)"\u96e8\u6c34", (Object)"02\u670819\u65e5");
        i.put((Object)"\u7b2c\u4e09\u4e16\u754c\u9752\u5e74\u65e5", (Object)"02\u670824\u65e5");
        i.put((Object)"\u4e16\u754c\u5c45\u4f4f\u6761\u4ef6\u8c03\u67e5\u65e5", (Object)"02\u670828\u65e5");
        i.put((Object)"\u56fd\u9645\u6d77\u8c79\u65e5", (Object)"03\u670801\u65e5");
        i.put((Object)"\u5168\u56fd\u7231\u8033\u65e5", (Object)"03\u670803\u65e5");
        i.put((Object)"\u5143\u5bb5\u8282", (Object)"03\u670805\u65e5");
        i.put((Object)"\u5b66\u96f7\u950b\u65e5", (Object)"03\u670805\u65e5");
        i.put((Object)"\u60ca\u86f0", (Object)"03\u670806\u65e5");
        i.put((Object)"\u4e09\u516b\u5987\u5973\u8282", (Object)"03\u670808\u65e5");
        i.put((Object)"\u690d\u6811\u8282", (Object)"03\u670812\u65e5");
        i.put((Object)"\u767d\u8272\u60c5\u4eba\u8282", (Object)"03\u670814\u65e5");
        i.put((Object)"\u6d88\u8d39\u8005\u6743\u76ca\u65e5", (Object)"03\u670815\u65e5");
        i.put((Object)"\u56fd\u9645\u822a\u6d77\u65e5", (Object)"03\u670817\u65e5");
        i.put((Object)"\u4e16\u754c\u65e0\u8089\u65e5", (Object)"03\u670820\u65e5");
        i.put((Object)"\u6625\u5206", (Object)"03\u670821\u65e5");
        i.put((Object)"\u9f99\u62ac\u5934", (Object)"03\u670821\u65e5");
        i.put((Object)"\u4e16\u754c\u7761\u7720\u65e5", (Object)"03\u670821\u65e5");
        i.put((Object)"\u4e16\u754c\u6c34\u65e5", (Object)"03\u670822\u65e5");
        i.put((Object)"\u4e16\u754c\u6c14\u8c61\u65e5", (Object)"03\u670823\u65e5");
        i.put((Object)"\u611a\u4eba\u8282", (Object)"04\u670801\u65e5");
        i.put((Object)"\u56fd\u9645\u513f\u7ae5\u56fe\u4e66\u65e5", (Object)"04\u670802\u65e5");
        i.put((Object)"\u5bd2\u98df\u8282", (Object)"04\u670804\u65e5");
        i.put((Object)"\u6e05\u660e\u8282", (Object)"04\u670805\u65e5");
        i.put((Object)"\u590d\u6d3b\u8282", (Object)"04\u670805\u65e5");
        i.put((Object)"\u4e16\u754c\u536b\u751f\u65e5", (Object)"04\u670807\u65e5");
        i.put((Object)"\u4e16\u754c\u5e15\u91d1\u68ee\u75c5\u65e5", (Object)"04\u670811\u65e5");
        i.put((Object)"\u8c37\u96e8", (Object)"04\u670820\u65e5");
        i.put((Object)"\u4e16\u754c\u5730\u7403\u65e5", (Object)"04\u670822\u65e5");
        i.put((Object)"\u4e16\u754c\u8bfb\u4e66\u65e5", (Object)"04\u670823\u65e5");
        i.put((Object)"\u77e5\u8bc6\u4ea7\u6743\u65e5", (Object)"04\u670826\u65e5");
        i.put((Object)"\u5168\u56fd\u4ea4\u901a\u5b89\u5168\u53cd\u601d\u65e5", (Object)"04\u670826\u65e5");
        i.put((Object)"\u4e16\u754c\u513f\u7ae5\u65e5", (Object)"04\u670826\u65e5");
        i.put((Object)"\u56fd\u9645\u52b3\u52a8\u8282", (Object)"05\u670801\u65e5");
        i.put((Object)"\u4e94\u56db\u9752\u5e74\u8282", (Object)"05\u670804\u65e5");
        i.put((Object)"\u7acb\u590f", (Object)"05\u670806\u65e5");
        i.put((Object)"\u4f5b\u8bde", (Object)"05\u670825\u65e5");
        i.put((Object)"\u4e16\u754c\u7ea2\u5341\u5b57\u65e5", (Object)"05\u670808\u65e5");
        i.put((Object)"\u4e16\u754c\u5fae\u7b11\u65e5", (Object)"05\u670808\u65e5");
        i.put((Object)"\u6bcd\u4eb2\u8282", (Object)"05\u670810\u65e5");
        i.put((Object)"\u56fd\u9645\u62a4\u58eb\u8282", (Object)"05\u670812\u65e5");
        i.put((Object)"\u56fd\u9645\u5bb6\u5ead\u65e5", (Object)"05\u670815\u65e5");
        i.put((Object)"\u4e16\u754c\u7535\u4fe1\u65e5", (Object)"05\u670817\u65e5");
        i.put((Object)"\u5168\u56fd\u52a9\u6b8b\u65e5", (Object)"05\u670817\u65e5");
        i.put((Object)"\u56fd\u9645\u535a\u7269\u9986\u65e5", (Object)"05\u670818\u65e5");
        i.put((Object)"\u5c0f\u6ee1", (Object)"05\u670821\u65e5");
        i.put((Object)"\u4e94\u5345\u8fd0\u52a8\u7eaa\u5ff5", (Object)"05\u670830\u65e5");
        i.put((Object)"\u4e16\u754c\u65e0\u70df\u65e5", (Object)"05\u670831\u65e5");
        i.put((Object)"\u516d\u4e00\u513f\u7ae5\u8282", (Object)"06\u670801\u65e5");
        i.put((Object)"\u4e16\u754c\u73af\u5883\u65e5", (Object)"06\u670805\u65e5");
        i.put((Object)"\u8292\u79cd", (Object)"06\u670806\u65e5");
        i.put((Object)"\u5168\u56fd\u7231\u773c\u65e5", (Object)"06\u670806\u65e5");
        i.put((Object)"\u4e2d\u56fd\u4eba\u53e3\u65e5", (Object)"06\u670811\u65e5");
        i.put((Object)"\u4e16\u754c\u96be\u6c11\u65e5", (Object)"06\u670820\u65e5");
        i.put((Object)"\u7aef\u5348\u8282", (Object)"06\u670820\u65e5");
        i.put((Object)"\u7236\u4eb2\u8282", (Object)"06\u670821\u65e5");
        i.put((Object)"\u590f\u81f3", (Object)"06\u670822\u65e5");
        i.put((Object)"\u4e2d\u56fd\u513f\u7ae5\u6148\u5584\u6d3b\u52a8\u65e5", (Object)"06\u670822\u65e5");
        i.put((Object)"\u56fd\u9645\u5965\u6797\u5339\u514b\u65e5", (Object)"06\u670823\u65e5");
        i.put((Object)"\u5168\u56fd\u571f\u5730\u65e5", (Object)"06\u670825\u65e5");
        i.put((Object)"\u56fd\u9645\u7981\u6bd2\u65e5", (Object)"06\u670826\u65e5");
        i.put((Object)"\u4e16\u754c\u9752\u5e74\u8054\u6b22\u8282", (Object)"06\u670830\u65e5");
        i.put((Object)"\u4e2d\u56fd\u5171\u4ea7\u515a\u8bde\u751f\u65e5", (Object)"07\u670801\u65e5");
        i.put((Object)"\u9999\u6e2f\u56de\u5f52\u65e5", (Object)"07\u670801\u65e5");
        i.put((Object)"\u56fd\u9645\u4f53\u80b2\u8bb0\u8005\u65e5", (Object)"07\u670802\u65e5");
        i.put((Object)"\u56fd\u9645\u63a5\u543b\u65e5", (Object)"07\u670806\u65e5");
        i.put((Object)"\u6297\u65e5\u6218\u4e89\u80dc\u5229\u7eaa\u5ff5\u65e5", (Object)"07\u670807\u65e5");
        i.put((Object)"\u5c0f\u6691", (Object)"07\u670807\u65e5");
        i.put((Object)"\u4e16\u754c\u4eba\u53e3\u65e5", (Object)"07\u670811\u65e5");
        i.put((Object)"\u5927\u6691", (Object)"07\u670823\u65e5");
        i.put((Object)"\u975e\u6d32\u5987\u5973\u65e5", (Object)"07\u670830\u65e5");
        i.put((Object)"\u516b\u4e00\u5efa\u519b\u8282", (Object)"08\u670801\u65e5");
        i.put((Object)"\u56fd\u9645\u7535\u5f71\u8282", (Object)"08\u670806\u65e5");
        i.put((Object)"\u7acb\u79cb", (Object)"08\u670808\u65e5");
        i.put((Object)"\u65e5\u672c\u6295\u964d\u65e5", (Object)"08\u670815\u65e5");
        i.put((Object)"\u4e03\u5915", (Object)"08\u670820\u65e5");
        i.put((Object)"\u5904\u6691", (Object)"08\u670823\u65e5");
        i.put((Object)"\u4e2d\u5143\u8282", (Object)"08\u670828\u65e5");
        i.put((Object)"\u6297\u65e5\u80dc\u5229\u7eaa\u5ff5\u65e5", (Object)"09\u670803\u65e5");
        i.put((Object)"\u767d\u9732", (Object)"09\u670808\u65e5");
        i.put((Object)"\u56fd\u9645\u626b\u76f2\u65e5", (Object)"09\u670808\u65e5");
        i.put((Object)"\u6559\u5e08\u8282", (Object)"09\u670810\u65e5");
        i.put((Object)"\u4e16\u754c\u6e05\u6d01\u5730\u7403\u65e5", (Object)"09\u670812\u65e5");
        i.put((Object)"\u56fd\u9645\u81ed\u6c27\u5c42\u4fdd\u62a4\u65e5", (Object)"09\u670812\u65e5");
        i.put((Object)"\u56fd\u9645\u548c\u5e73\u65e5", (Object)"09\u670816\u65e5");
        i.put((Object)"\u4e5d\u4e00\u516b\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"09\u670818\u65e5");
        i.put((Object)"\u56fd\u9645\u7231\u7259\u65e5", (Object)"09\u670820\u65e5");
        i.put((Object)"\u4e16\u754c\u65e0\u8f66\u65e5", (Object)"09\u670822\u65e5");
        i.put((Object)"\u79cb\u5206", (Object)"09\u670823\u65e5");
        i.put((Object)"\u4e2d\u79cb\u8282", (Object)"09\u670827\u65e5");
        i.put((Object)"\u4e16\u754c\u65c5\u6e38\u65e5", (Object)"09\u670827\u65e5");
        i.put((Object)"\u56fd\u9645\u804b\u4eba\u8282", (Object)"09\u670828\u65e5");
        i.put((Object)"\u56fd\u5e86\u8282", (Object)"10\u670801\u65e5");
        i.put((Object)"\u4e16\u754c\u52a8\u7269\u65e5", (Object)"10\u670804\u65e5");
        i.put((Object)"\u5bd2\u9732", (Object)"10\u670808\u65e5");
        i.put((Object)"\u4e16\u754c\u90ae\u653f\u65e5", (Object)"10\u670809\u65e5");
        i.put((Object)"\u8f9b\u4ea5\u9769\u547d\u7eaa\u5ff5\u65e5", (Object)"10\u670810\u65e5");
        i.put((Object)"\u4e2d\u56fd\u5c11\u5e74\u5148\u950b\u961f\u8bde\u8fb0\u65e5", (Object)"10\u670813\u65e5");
        i.put((Object)"\u4e16\u754c\u6807\u51c6\u65e5", (Object)"10\u670814\u65e5");
        i.put((Object)"\u56fd\u9645\u76f2\u4eba\u8282", (Object)"10\u670815\u65e5");
        i.put((Object)"\u4e16\u754c\u7cae\u98df\u65e5", (Object)"10\u670816\u65e5");
        i.put((Object)"\u56fd\u9645\u6d88\u9664\u8d2b\u56f0\u65e5", (Object)"10\u670817\u65e5");
        i.put((Object)"\u91cd\u9633\u8282", (Object)"10\u670821\u65e5");
        i.put((Object)"\u4e16\u754c\u4f20\u7edf\u533b\u836f\u65e5", (Object)"10\u670822\u65e5");
        i.put((Object)"\u971c\u964d", (Object)"10\u670824\u65e5");
        i.put((Object)"\u8054\u5408\u56fd\u65e5", (Object)"10\u670822\u65e5");
        i.put((Object)"\u4e16\u754c\u52e4\u4fed\u65e5", (Object)"10\u670831\u65e5");
        i.put((Object)"\u4e07\u5723\u8282", (Object)"10\u670831\u65e5");
        i.put((Object)"\u7acb\u51ac", (Object)"11\u670808\u65e5");
        i.put((Object)"\u4e2d\u56fd\u8bb0\u8005\u65e5", (Object)"11\u670808\u65e5");
        i.put((Object)"\u4e16\u754c\u9752\u5e74\u8282", (Object)"11\u670810\u65e5");
        i.put((Object)"\u5149\u68cd\u8282", (Object)"11\u670811\u65e5");
        i.put((Object)"\u4e16\u754c\u7cd6\u5c3f\u75c5\u65e5", (Object)"11\u670814\u65e5");
        i.put((Object)"\u56fd\u9645\u5927\u5b66\u751f\u8282", (Object)"11\u670817\u65e5");
        i.put((Object)"\u4e16\u754c\u95ee\u5019\u65e5", (Object)"11\u670821\u65e5");
        i.put((Object)"\u5c0f\u96ea", (Object)"11\u670822\u65e5");
        i.put((Object)"\u611f\u6069\u8282", (Object)"11\u670826\u65e5");
        i.put((Object)"\u4e16\u754c\u827e\u6ecb\u75c5\u65e5", (Object)"12\u670801\u65e5");
        i.put((Object)"\u56fd\u9645\u6b8b\u75be\u4eba\u65e5", (Object)"12\u670803\u65e5");
        i.put((Object)"\u5927\u96ea", (Object)"12\u670807\u65e5");
        i.put((Object)"\u4e00\u4e8c\u4e5d\u8fd0\u52a8\u7eaa\u5ff5\u65e5", (Object)"12\u670809\u65e5");
        i.put((Object)"\u4e16\u754c\u8db3\u7403\u65e5", (Object)"12\u670809\u65e5");
        i.put((Object)"\u4e16\u754c\u4eba\u6743\u65e5", (Object)"12\u670810\u65e5");
        i.put((Object)"\u897f\u5b89\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"12\u670812\u65e5");
        i.put((Object)"\u5357\u4eac\u5927\u5c60\u6740", (Object)"12\u670813\u65e5");
        i.put((Object)"\u6fb3\u95e8\u56de\u5f52\u65e5", (Object)"12\u670820\u65e5");
        i.put((Object)"\u56fd\u9645\u7bee\u7403\u65e5", (Object)"12\u670821\u65e5");
        i.put((Object)"\u51ac\u81f3", (Object)"12\u670822\u65e5");
        i.put((Object)"\u5e73\u5b89\u591c", (Object)"12\u670824\u65e5");
        i.put((Object)"\u5723\u8bde\u8282", (Object)"12\u670825\u65e5");
        j.put((Object)"\u5143\u65e6", (Object)"01\u670801\u65e5");
        j.put((Object)"\u9ed1\u4eba\u65e5", (Object)"01\u670804\u65e5");
        j.put((Object)"\u5c0f\u5bd2", (Object)"01\u670805\u65e5");
        j.put((Object)"\u56fd\u9645\u9ebb\u98ce\u8282", (Object)"01\u670825\u65e5");
        j.put((Object)"\u814a\u516b\u8282", (Object)"01\u670827\u65e5");
        j.put((Object)"\u4e16\u754c\u6e7f\u5730\u65e5", (Object)"02\u670802\u65e5");
        j.put((Object)"\u7acb\u6625", (Object)"02\u670804\u65e5");
        j.put((Object)"\u56fd\u9645\u58f0\u63f4\u5357\u975e\u65e5", (Object)"02\u670807\u65e5");
        j.put((Object)"\u56fd\u9645\u6c14\u8c61\u8282", (Object)"02\u670810\u65e5");
        j.put((Object)"\u5c0f\u5e74", (Object)"02\u670811\u65e5");
        j.put((Object)"\u60c5\u4eba\u8282", (Object)"02\u670814\u65e5");
        j.put((Object)"\u9664\u5915", (Object)"02\u670818\u65e5");
        j.put((Object)"\u6625\u8282", (Object)"02\u670819\u65e5");
        j.put((Object)"\u96e8\u6c34", (Object)"02\u670819\u65e5");
        j.put((Object)"\u7b2c\u4e09\u4e16\u754c\u9752\u5e74\u65e5", (Object)"02\u670824\u65e5");
        j.put((Object)"\u4e16\u754c\u5c45\u4f4f\u6761\u4ef6\u8c03\u67e5\u65e5", (Object)"02\u670828\u65e5");
        j.put((Object)"\u56fd\u9645\u6d77\u8c79\u65e5", (Object)"03\u670801\u65e5");
        j.put((Object)"\u5168\u56fd\u7231\u8033\u65e5", (Object)"03\u670803\u65e5");
        j.put((Object)"\u5143\u5bb5\u8282", (Object)"03\u670805\u65e5");
        j.put((Object)"\u5b66\u96f7\u950b\u65e5", (Object)"03\u670805\u65e5");
        j.put((Object)"\u60ca\u86f0", (Object)"03\u670806\u65e5");
        j.put((Object)"\u4e09\u516b\u5987\u5973\u8282", (Object)"03\u670808\u65e5");
        j.put((Object)"\u690d\u6811\u8282", (Object)"03\u670812\u65e5");
        j.put((Object)"\u767d\u8272\u60c5\u4eba\u8282", (Object)"03\u670814\u65e5");
        j.put((Object)"\u6d88\u8d39\u8005\u6743\u76ca\u65e5", (Object)"03\u670815\u65e5");
        j.put((Object)"\u56fd\u9645\u822a\u6d77\u65e5", (Object)"03\u670817\u65e5");
        j.put((Object)"\u4e16\u754c\u65e0\u8089\u65e5", (Object)"03\u670820\u65e5");
        j.put((Object)"\u6625\u5206", (Object)"03\u670821\u65e5");
        j.put((Object)"\u9f99\u62ac\u5934", (Object)"03\u670821\u65e5");
        j.put((Object)"\u4e16\u754c\u7761\u7720\u65e5", (Object)"03\u670821\u65e5");
        j.put((Object)"\u4e16\u754c\u6c34\u65e5", (Object)"03\u670822\u65e5");
        j.put((Object)"\u4e16\u754c\u6c14\u8c61\u65e5", (Object)"03\u670823\u65e5");
        j.put((Object)"\u611a\u4eba\u8282", (Object)"04\u670801\u65e5");
        j.put((Object)"\u56fd\u9645\u513f\u7ae5\u56fe\u4e66\u65e5", (Object)"04\u670802\u65e5");
        j.put((Object)"\u5bd2\u98df\u8282", (Object)"04\u670804\u65e5");
        j.put((Object)"\u6e05\u660e\u8282", (Object)"04\u670805\u65e5");
        j.put((Object)"\u590d\u6d3b\u8282", (Object)"04\u670805\u65e5");
        j.put((Object)"\u4e16\u754c\u536b\u751f\u65e5", (Object)"04\u670807\u65e5");
        j.put((Object)"\u4e16\u754c\u5e15\u91d1\u68ee\u75c5\u65e5", (Object)"04\u670811\u65e5");
        j.put((Object)"\u8c37\u96e8", (Object)"04\u670820\u65e5");
        j.put((Object)"\u4e16\u754c\u5730\u7403\u65e5", (Object)"04\u670822\u65e5");
        j.put((Object)"\u4e16\u754c\u8bfb\u4e66\u65e5", (Object)"04\u670823\u65e5");
        j.put((Object)"\u77e5\u8bc6\u4ea7\u6743\u65e5", (Object)"04\u670826\u65e5");
        j.put((Object)"\u5168\u56fd\u4ea4\u901a\u5b89\u5168\u53cd\u601d\u65e5", (Object)"04\u670826\u65e5");
        j.put((Object)"\u4e16\u754c\u513f\u7ae5\u65e5", (Object)"04\u670826\u65e5");
        j.put((Object)"\u56fd\u9645\u52b3\u52a8\u8282", (Object)"05\u670801\u65e5");
        j.put((Object)"\u4e94\u56db\u9752\u5e74\u8282", (Object)"05\u670804\u65e5");
        j.put((Object)"\u7acb\u590f", (Object)"05\u670806\u65e5");
        j.put((Object)"\u4f5b\u8bde", (Object)"05\u670825\u65e5");
        j.put((Object)"\u4e16\u754c\u7ea2\u5341\u5b57\u65e5", (Object)"05\u670808\u65e5");
        j.put((Object)"\u4e16\u754c\u5fae\u7b11\u65e5", (Object)"05\u670808\u65e5");
        j.put((Object)"\u6bcd\u4eb2\u8282", (Object)"05\u670810\u65e5");
        j.put((Object)"\u56fd\u9645\u62a4\u58eb\u8282", (Object)"05\u670812\u65e5");
        j.put((Object)"\u56fd\u9645\u5bb6\u5ead\u65e5", (Object)"05\u670815\u65e5");
        j.put((Object)"\u4e16\u754c\u7535\u4fe1\u65e5", (Object)"05\u670817\u65e5");
        j.put((Object)"\u5168\u56fd\u52a9\u6b8b\u65e5", (Object)"05\u670817\u65e5");
        j.put((Object)"\u56fd\u9645\u535a\u7269\u9986\u65e5", (Object)"05\u670818\u65e5");
        j.put((Object)"\u5c0f\u6ee1", (Object)"05\u670821\u65e5");
        j.put((Object)"\u4e94\u5345\u8fd0\u52a8\u7eaa\u5ff5", (Object)"05\u670830\u65e5");
        j.put((Object)"\u4e16\u754c\u65e0\u70df\u65e5", (Object)"05\u670831\u65e5");
        j.put((Object)"\u516d\u4e00\u513f\u7ae5\u8282", (Object)"06\u670801\u65e5");
        j.put((Object)"\u4e16\u754c\u73af\u5883\u65e5", (Object)"06\u670805\u65e5");
        j.put((Object)"\u8292\u79cd", (Object)"06\u670806\u65e5");
        j.put((Object)"\u5168\u56fd\u7231\u773c\u65e5", (Object)"06\u670806\u65e5");
        j.put((Object)"\u4e2d\u56fd\u4eba\u53e3\u65e5", (Object)"06\u670811\u65e5");
        j.put((Object)"\u4e16\u754c\u96be\u6c11\u65e5", (Object)"06\u670820\u65e5");
        j.put((Object)"\u7aef\u5348\u8282", (Object)"06\u670820\u65e5");
        j.put((Object)"\u7236\u4eb2\u8282", (Object)"06\u670821\u65e5");
        j.put((Object)"\u590f\u81f3", (Object)"06\u670822\u65e5");
        j.put((Object)"\u4e2d\u56fd\u513f\u7ae5\u6148\u5584\u6d3b\u52a8\u65e5", (Object)"06\u670822\u65e5");
        j.put((Object)"\u56fd\u9645\u5965\u6797\u5339\u514b\u65e5", (Object)"06\u670823\u65e5");
        j.put((Object)"\u5168\u56fd\u571f\u5730\u65e5", (Object)"06\u670825\u65e5");
        j.put((Object)"\u56fd\u9645\u7981\u6bd2\u65e5", (Object)"06\u670826\u65e5");
        j.put((Object)"\u4e16\u754c\u9752\u5e74\u8054\u6b22\u8282", (Object)"06\u670830\u65e5");
        j.put((Object)"\u4e2d\u56fd\u5171\u4ea7\u515a\u8bde\u751f\u65e5", (Object)"07\u670801\u65e5");
        j.put((Object)"\u9999\u6e2f\u56de\u5f52\u65e5", (Object)"07\u670801\u65e5");
        j.put((Object)"\u56fd\u9645\u4f53\u80b2\u8bb0\u8005\u65e5", (Object)"07\u670802\u65e5");
        j.put((Object)"\u56fd\u9645\u63a5\u543b\u65e5", (Object)"07\u670806\u65e5");
        j.put((Object)"\u6297\u65e5\u6218\u4e89\u80dc\u5229\u7eaa\u5ff5\u65e5", (Object)"07\u670807\u65e5");
        j.put((Object)"\u5c0f\u6691", (Object)"07\u670807\u65e5");
        j.put((Object)"\u4e16\u754c\u4eba\u53e3\u65e5", (Object)"07\u670811\u65e5");
        j.put((Object)"\u5927\u6691", (Object)"07\u670823\u65e5");
        j.put((Object)"\u975e\u6d32\u5987\u5973\u65e5", (Object)"07\u670830\u65e5");
        j.put((Object)"\u516b\u4e00\u5efa\u519b\u8282", (Object)"08\u670801\u65e5");
        j.put((Object)"\u56fd\u9645\u7535\u5f71\u8282", (Object)"08\u670806\u65e5");
        j.put((Object)"\u7acb\u79cb", (Object)"08\u670808\u65e5");
        j.put((Object)"\u65e5\u672c\u6295\u964d\u65e5", (Object)"08\u670815\u65e5");
        j.put((Object)"\u4e03\u5915", (Object)"08\u670820\u65e5");
        j.put((Object)"\u5904\u6691", (Object)"08\u670823\u65e5");
        j.put((Object)"\u4e2d\u5143\u8282", (Object)"08\u670828\u65e5");
        j.put((Object)"\u6297\u65e5\u80dc\u5229\u7eaa\u5ff5\u65e5", (Object)"09\u670803\u65e5");
        j.put((Object)"\u767d\u9732", (Object)"09\u670808\u65e5");
        j.put((Object)"\u56fd\u9645\u626b\u76f2\u65e5", (Object)"09\u670808\u65e5");
        j.put((Object)"\u6559\u5e08\u8282", (Object)"09\u670810\u65e5");
        j.put((Object)"\u4e16\u754c\u6e05\u6d01\u5730\u7403\u65e5", (Object)"09\u670812\u65e5");
        j.put((Object)"\u56fd\u9645\u81ed\u6c27\u5c42\u4fdd\u62a4\u65e5", (Object)"09\u670812\u65e5");
        j.put((Object)"\u56fd\u9645\u548c\u5e73\u65e5", (Object)"09\u670816\u65e5");
        j.put((Object)"\u4e5d\u4e00\u516b\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"09\u670818\u65e5");
        j.put((Object)"\u56fd\u9645\u7231\u7259\u65e5", (Object)"09\u670820\u65e5");
        j.put((Object)"\u4e16\u754c\u65e0\u8f66\u65e5", (Object)"09\u670822\u65e5");
        j.put((Object)"\u79cb\u5206", (Object)"09\u670823\u65e5");
        j.put((Object)"\u4e2d\u79cb\u8282", (Object)"09\u670827\u65e5");
        j.put((Object)"\u4e16\u754c\u65c5\u6e38\u65e5", (Object)"09\u670827\u65e5");
        j.put((Object)"\u56fd\u9645\u804b\u4eba\u8282", (Object)"09\u670828\u65e5");
        j.put((Object)"\u56fd\u5e86\u8282", (Object)"10\u670801\u65e5");
        j.put((Object)"\u4e16\u754c\u52a8\u7269\u65e5", (Object)"10\u670804\u65e5");
        j.put((Object)"\u5bd2\u9732", (Object)"10\u670808\u65e5");
        j.put((Object)"\u4e16\u754c\u90ae\u653f\u65e5", (Object)"10\u670809\u65e5");
        j.put((Object)"\u8f9b\u4ea5\u9769\u547d\u7eaa\u5ff5\u65e5", (Object)"10\u670810\u65e5");
        j.put((Object)"\u4e2d\u56fd\u5c11\u5e74\u5148\u950b\u961f\u8bde\u8fb0\u65e5", (Object)"10\u670813\u65e5");
        j.put((Object)"\u4e16\u754c\u6807\u51c6\u65e5", (Object)"10\u670814\u65e5");
        j.put((Object)"\u56fd\u9645\u76f2\u4eba\u8282", (Object)"10\u670815\u65e5");
        j.put((Object)"\u4e16\u754c\u7cae\u98df\u65e5", (Object)"10\u670816\u65e5");
        j.put((Object)"\u56fd\u9645\u6d88\u9664\u8d2b\u56f0\u65e5", (Object)"10\u670817\u65e5");
        j.put((Object)"\u91cd\u9633\u8282", (Object)"10\u670821\u65e5");
        j.put((Object)"\u4e16\u754c\u4f20\u7edf\u533b\u836f\u65e5", (Object)"10\u670822\u65e5");
        j.put((Object)"\u971c\u964d", (Object)"10\u670824\u65e5");
        j.put((Object)"\u8054\u5408\u56fd\u65e5", (Object)"10\u670822\u65e5");
        j.put((Object)"\u4e16\u754c\u52e4\u4fed\u65e5", (Object)"10\u670831\u65e5");
        j.put((Object)"\u4e07\u5723\u8282", (Object)"10\u670831\u65e5");
        j.put((Object)"\u7acb\u51ac", (Object)"11\u670808\u65e5");
        j.put((Object)"\u4e2d\u56fd\u8bb0\u8005\u65e5", (Object)"11\u670808\u65e5");
        j.put((Object)"\u4e16\u754c\u9752\u5e74\u8282", (Object)"11\u670810\u65e5");
        j.put((Object)"\u5149\u68cd\u8282", (Object)"11\u670811\u65e5");
        j.put((Object)"\u4e16\u754c\u7cd6\u5c3f\u75c5\u65e5", (Object)"11\u670814\u65e5");
        j.put((Object)"\u56fd\u9645\u5927\u5b66\u751f\u8282", (Object)"11\u670817\u65e5");
        j.put((Object)"\u4e16\u754c\u95ee\u5019\u65e5", (Object)"11\u670821\u65e5");
        j.put((Object)"\u5c0f\u96ea", (Object)"11\u670822\u65e5");
        j.put((Object)"\u611f\u6069\u8282", (Object)"11\u670826\u65e5");
        j.put((Object)"\u4e16\u754c\u827e\u6ecb\u75c5\u65e5", (Object)"12\u670801\u65e5");
        j.put((Object)"\u56fd\u9645\u6b8b\u75be\u4eba\u65e5", (Object)"12\u670803\u65e5");
        j.put((Object)"\u5927\u96ea", (Object)"12\u670807\u65e5");
        j.put((Object)"\u4e00\u4e8c\u4e5d\u8fd0\u52a8\u7eaa\u5ff5\u65e5", (Object)"12\u670809\u65e5");
        j.put((Object)"\u4e16\u754c\u8db3\u7403\u65e5", (Object)"12\u670809\u65e5");
        j.put((Object)"\u4e16\u754c\u4eba\u6743\u65e5", (Object)"12\u670810\u65e5");
        j.put((Object)"\u897f\u5b89\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"12\u670812\u65e5");
        j.put((Object)"\u5357\u4eac\u5927\u5c60\u6740", (Object)"12\u670813\u65e5");
        j.put((Object)"\u6fb3\u95e8\u56de\u5f52\u65e5", (Object)"12\u670820\u65e5");
        j.put((Object)"\u56fd\u9645\u7bee\u7403\u65e5", (Object)"12\u670821\u65e5");
        j.put((Object)"\u51ac\u81f3", (Object)"12\u670822\u65e5");
        j.put((Object)"\u5e73\u5b89\u591c", (Object)"12\u670824\u65e5");
        j.put((Object)"\u5143\u65e6 ", (Object)"01\u670801\u65e5");
        j.put((Object)"\u5c0f\u5bd2", (Object)"01\u670805\u65e5");
        j.put((Object)"\u814a\u516b\u8282", (Object)"01\u670808\u65e5");
        j.put((Object)"\u5c0f\u5e74 ", (Object)"01\u670823\u65e5");
        j.put((Object)"\u9664\u5915", (Object)"01\u670830\u65e5");
        j.put((Object)"\u6625\u8282 ", (Object)"01\u670831\u65e5");
        j.put((Object)"\u7acb\u6625", (Object)"02\u670804\u65e5");
        j.put((Object)"\u5143\u5bb5\u8282", (Object)"02\u670814\u65e5");
        j.put((Object)"\u96e8\u6c34", (Object)"02\u670819\u65e5");
        j.put((Object)"\u9f99\u62ac\u5934", (Object)"03\u670802\u65e5");
        j.put((Object)"\u60ca\u86f0", (Object)"03\u670805\u65e5");
        j.put((Object)"\u5987\u5973\u8282", (Object)"03\u670808\u65e5");
        j.put((Object)"\u690d\u6811\u8282", (Object)"03\u670812\u65e5");
        j.put((Object)"\u6625\u5206", (Object)"03\u670821\u65e5");
        j.put((Object)"\u611a\u4eba\u8282", (Object)"04\u670801\u65e5");
        j.put((Object)"\u6e05\u660e\u8282", (Object)"04\u670805\u65e5");
        j.put((Object)"\u8c37\u96e8", (Object)"04\u670820\u65e5");
        j.put((Object)"\u52b3\u52a8\u8282", (Object)"05\u670801\u65e5");
        j.put((Object)"\u9752\u5e74\u8282", (Object)"05\u670804\u65e5");
        j.put((Object)"\u7acb\u590f", (Object)"05\u670805\u65e5");
        j.put((Object)"\u6bcd\u4eb2\u8282", (Object)"05\u670811\u65e5");
        j.put((Object)"\u5c0f\u6ee1", (Object)"05\u670821\u65e5");
        j.put((Object)"\u516d\u4e00\u513f\u7ae5\u8282", (Object)"06\u670801\u65e5");
        j.put((Object)"\u8292\u79cd", (Object)"06\u670806\u65e5");
        j.put((Object)"\u7236\u4eb2\u8282", (Object)"06\u670815\u65e5");
        j.put((Object)"\u590f\u81f3", (Object)"06\u670821\u65e5");
        j.put((Object)"\u9999\u6e2f\u56de\u5f52", (Object)"07\u670801\u65e5");
        j.put((Object)"\u5927\u6691", (Object)"07\u670823\u65e5");
        j.put((Object)"\u516b\u4e00\u5efa\u519b\u8282", (Object)"08\u670801\u65e5");
        j.put((Object)"\u4e03\u5915", (Object)"08\u670802\u65e5");
        j.put((Object)"\u7acb\u79cb", (Object)"08\u670807\u65e5");
        j.put((Object)"\u4e2d\u5143\u8282", (Object)"08\u670810\u65e5");
        j.put((Object)"\u5904\u6691", (Object)"08\u670823\u65e5");
        j.put((Object)"\u6559\u5e08\u8282", (Object)"09\u670810\u65e5");
        j.put((Object)"\u79cb\u5206", (Object)"09\u670823\u65e5");
        j.put((Object)"\u56fd\u5e86\u8282", (Object)"10\u670801\u65e5");
        j.put((Object)"\u91cd\u9633\u8282", (Object)"10\u670802\u65e5");
        j.put((Object)"\u5bd2\u9732", (Object)"10\u670808\u65e5");
        j.put((Object)"\u971c\u964d", (Object)"10\u670823\u65e5");
        j.put((Object)"\u4e07\u5723\u8282", (Object)"11\u670801\u65e5");
        j.put((Object)"\u7acb\u51ac", (Object)"11\u670807\u65e5");
        j.put((Object)"\u5c0f\u96ea", (Object)"11\u670822\u65e5");
        j.put((Object)"\u5927\u96ea", (Object)"12\u670807\u65e5");
        j.put((Object)"\u56fd\u9645\u6c11\u822a\u65e5", (Object)"12\u670807\u65e5");
        j.put((Object)"\u51ac\u81f3", (Object)"12\u670822\u65e5");
        j.put((Object)"\u5e73\u5b89\u591c", (Object)"12\u670824\u65e5");
        j.put((Object)"\u5723\u8bde\u8282", (Object)"12\u670824\u65e5");
        k.put((Object)"\u9ed1\u4eba\u65e5", (Object)"01\u670804\u65e5");
        k.put((Object)"\u56fd\u9645\u9ebb\u98ce\u8282", (Object)"01\u670825\u65e5");
        k.put((Object)"\u814a\u516b\u8282", (Object)"01\u670827\u65e5");
        k.put((Object)"\u4e16\u754c\u6e7f\u5730\u65e5", (Object)"02\u670802\u65e5");
        k.put((Object)"\u7acb\u6625", (Object)"02\u670804\u65e5");
        k.put((Object)"\u56fd\u9645\u58f0\u63f4\u5357\u975e\u65e5", (Object)"02\u670807\u65e5");
        k.put((Object)"\u56fd\u9645\u6c14\u8c61\u8282", (Object)"02\u670810\u65e5");
        k.put((Object)"\u5c0f\u5e74", (Object)"02\u670811\u65e5");
        k.put((Object)"\u9664\u5915", (Object)"02\u670818\u65e5");
        k.put((Object)"\u7b2c\u4e09\u4e16\u754c\u9752\u5e74\u65e5", (Object)"02\u670824\u65e5");
        k.put((Object)"\u4e16\u754c\u5c45\u4f4f\u6761\u4ef6\u8c03\u67e5\u65e5", (Object)"02\u670828\u65e5");
        k.put((Object)"\u56fd\u9645\u6d77\u8c79\u65e5", (Object)"03\u670801\u65e5");
        k.put((Object)"\u5168\u56fd\u7231\u8033\u65e5", (Object)"03\u670803\u65e5");
        k.put((Object)"\u5143\u5bb5\u8282", (Object)"03\u670805\u65e5");
        k.put((Object)"\u5b66\u96f7\u950b\u65e5", (Object)"03\u670805\u65e5");
        k.put((Object)"\u60ca\u86f0", (Object)"03\u670806\u65e5");
        k.put((Object)"\u4e09\u516b\u5987\u5973\u8282", (Object)"03\u670808\u65e5");
        k.put((Object)"\u690d\u6811\u8282", (Object)"03\u670812\u65e5");
        k.put((Object)"\u767d\u8272\u60c5\u4eba\u8282", (Object)"03\u670814\u65e5");
        k.put((Object)"\u56fd\u9645\u822a\u6d77\u65e5", (Object)"03\u670817\u65e5");
        k.put((Object)"\u4e16\u754c\u65e0\u8089\u65e5", (Object)"03\u670820\u65e5");
        k.put((Object)"\u6625\u5206", (Object)"03\u670821\u65e5");
        k.put((Object)"\u9f99\u62ac\u5934", (Object)"03\u670821\u65e5");
        k.put((Object)"\u4e16\u754c\u7761\u7720\u65e5", (Object)"03\u670821\u65e5");
        k.put((Object)"\u4e16\u754c\u6c34\u65e5", (Object)"03\u670822\u65e5");
        k.put((Object)"\u4e16\u754c\u6c14\u8c61\u65e5", (Object)"03\u670823\u65e5");
        k.put((Object)"\u611a\u4eba\u8282", (Object)"04\u670801\u65e5");
        k.put((Object)"\u56fd\u9645\u513f\u7ae5\u56fe\u4e66\u65e5", (Object)"04\u670802\u65e5");
        k.put((Object)"\u5bd2\u98df\u8282", (Object)"04\u670804\u65e5");
        k.put((Object)"\u4e16\u754c\u536b\u751f\u65e5", (Object)"04\u670807\u65e5");
        k.put((Object)"\u4e16\u754c\u5e15\u91d1\u68ee\u75c5\u65e5", (Object)"04\u670811\u65e5");
        k.put((Object)"\u8c37\u96e8", (Object)"04\u670820\u65e5");
        k.put((Object)"\u4e16\u754c\u5730\u7403\u65e5", (Object)"04\u670822\u65e5");
        k.put((Object)"\u4e16\u754c\u8bfb\u4e66\u65e5", (Object)"04\u670823\u65e5");
        k.put((Object)"\u77e5\u8bc6\u4ea7\u6743\u65e5", (Object)"04\u670826\u65e5");
        k.put((Object)"\u5168\u56fd\u4ea4\u901a\u5b89\u5168\u53cd\u601d\u65e5", (Object)"04\u670826\u65e5");
        k.put((Object)"\u4e16\u754c\u513f\u7ae5\u65e5", (Object)"04\u670826\u65e5");
        k.put((Object)"\u56fd\u9645\u52b3\u52a8\u8282", (Object)"05\u670801\u65e5");
        k.put((Object)"\u4e94\u56db\u9752\u5e74\u8282", (Object)"05\u670804\u65e5");
        k.put((Object)"\u7acb\u590f", (Object)"05\u670806\u65e5");
        k.put((Object)"\u4f5b\u8bde", (Object)"05\u670825\u65e5");
        k.put((Object)"\u4e16\u754c\u7ea2\u5341\u5b57\u65e5", (Object)"05\u670808\u65e5");
        k.put((Object)"\u4e16\u754c\u5fae\u7b11\u65e5", (Object)"05\u670808\u65e5");
        k.put((Object)"\u56fd\u9645\u5bb6\u5ead\u65e5", (Object)"05\u670815\u65e5");
        k.put((Object)"\u4e16\u754c\u7535\u4fe1\u65e5", (Object)"05\u670817\u65e5");
        k.put((Object)"\u5168\u56fd\u52a9\u6b8b\u65e5", (Object)"05\u670817\u65e5");
        k.put((Object)"\u56fd\u9645\u535a\u7269\u9986\u65e5", (Object)"05\u670818\u65e5");
        k.put((Object)"\u4e94\u5345\u8fd0\u52a8\u7eaa\u5ff5", (Object)"05\u670830\u65e5");
        k.put((Object)"\u4e16\u754c\u65e0\u70df\u65e5", (Object)"05\u670831\u65e5");
        k.put((Object)"\u516d\u4e00\u513f\u7ae5\u8282", (Object)"06\u670801\u65e5");
        k.put((Object)"\u4e16\u754c\u73af\u5883\u65e5", (Object)"06\u670805\u65e5");
        k.put((Object)"\u8292\u79cd", (Object)"06\u670806\u65e5");
        k.put((Object)"\u5168\u56fd\u7231\u773c\u65e5", (Object)"06\u670806\u65e5");
        k.put((Object)"\u4e2d\u56fd\u4eba\u53e3\u65e5", (Object)"06\u670811\u65e5");
        k.put((Object)"\u4e16\u754c\u96be\u6c11\u65e5", (Object)"06\u670820\u65e5");
        k.put((Object)"\u590f\u81f3", (Object)"06\u670822\u65e5");
        k.put((Object)"\u4e2d\u56fd\u513f\u7ae5\u6148\u5584\u6d3b\u52a8\u65e5", (Object)"06\u670822\u65e5");
        k.put((Object)"\u56fd\u9645\u5965\u6797\u5339\u514b\u65e5", (Object)"06\u670823\u65e5");
        k.put((Object)"\u5168\u56fd\u571f\u5730\u65e5", (Object)"06\u670825\u65e5");
        k.put((Object)"\u56fd\u9645\u7981\u6bd2\u65e5", (Object)"06\u670826\u65e5");
        k.put((Object)"\u4e16\u754c\u9752\u5e74\u8054\u6b22\u8282", (Object)"06\u670830\u65e5");
        k.put((Object)"\u4e2d\u56fd\u5171\u4ea7\u515a\u8bde\u751f\u65e5", (Object)"07\u670801\u65e5");
        k.put((Object)"\u9999\u6e2f\u56de\u5f52\u65e5", (Object)"07\u670801\u65e5");
        k.put((Object)"\u56fd\u9645\u4f53\u80b2\u8bb0\u8005\u65e5", (Object)"07\u670802\u65e5");
        k.put((Object)"\u56fd\u9645\u63a5\u543b\u65e5", (Object)"07\u670806\u65e5");
        k.put((Object)"\u6297\u65e5\u6218\u4e89\u80dc\u5229\u7eaa\u5ff5\u65e5", (Object)"07\u670807\u65e5");
        k.put((Object)"\u5c0f\u6691", (Object)"07\u670807\u65e5");
        k.put((Object)"\u4e16\u754c\u4eba\u53e3\u65e5", (Object)"07\u670811\u65e5");
        k.put((Object)"\u5927\u6691", (Object)"07\u670823\u65e5");
        k.put((Object)"\u975e\u6d32\u5987\u5973\u65e5", (Object)"07\u670830\u65e5");
        k.put((Object)"\u516b\u4e00\u5efa\u519b\u8282", (Object)"08\u670801\u65e5");
        k.put((Object)"\u56fd\u9645\u7535\u5f71\u8282", (Object)"08\u670806\u65e5");
        k.put((Object)"\u7acb\u79cb", (Object)"08\u670808\u65e5");
        k.put((Object)"\u65e5\u672c\u6295\u964d\u65e5", (Object)"08\u670815\u65e5");
        k.put((Object)"\u4e03\u5915", (Object)"08\u670820\u65e5");
        k.put((Object)"\u5904\u6691", (Object)"08\u670823\u65e5");
        k.put((Object)"\u4e2d\u5143\u8282", (Object)"08\u670828\u65e5");
        k.put((Object)"\u6297\u65e5\u80dc\u5229\u7eaa\u5ff5\u65e5", (Object)"09\u670803\u65e5");
        k.put((Object)"\u767d\u9732", (Object)"09\u670808\u65e5");
        k.put((Object)"\u56fd\u9645\u626b\u76f2\u65e5", (Object)"09\u670808\u65e5");
        k.put((Object)"\u6559\u5e08\u8282", (Object)"09\u670810\u65e5");
        k.put((Object)"\u4e16\u754c\u6e05\u6d01\u5730\u7403\u65e5", (Object)"09\u670812\u65e5");
        k.put((Object)"\u56fd\u9645\u81ed\u6c27\u5c42\u4fdd\u62a4\u65e5", (Object)"09\u670812\u65e5");
        k.put((Object)"\u56fd\u9645\u548c\u5e73\u65e5", (Object)"09\u670816\u65e5");
        k.put((Object)"\u4e5d\u4e00\u516b\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"09\u670818\u65e5");
        k.put((Object)"\u56fd\u9645\u7231\u7259\u65e5", (Object)"09\u670820\u65e5");
        k.put((Object)"\u4e16\u754c\u65e0\u8f66\u65e5", (Object)"09\u670822\u65e5");
        k.put((Object)"\u79cb\u5206", (Object)"09\u670823\u65e5");
        k.put((Object)"\u4e2d\u79cb\u8282", (Object)"09\u670827\u65e5");
        k.put((Object)"\u4e16\u754c\u65c5\u6e38\u65e5", (Object)"09\u670827\u65e5");
        k.put((Object)"\u56fd\u9645\u804b\u4eba\u8282", (Object)"09\u670828\u65e5");
        k.put((Object)"\u56fd\u5e86\u8282", (Object)"10\u670801\u65e5");
        k.put((Object)"\u4e16\u754c\u52a8\u7269\u65e5", (Object)"10\u670804\u65e5");
        k.put((Object)"\u4e16\u754c\u90ae\u653f\u65e5", (Object)"10\u670809\u65e5");
        k.put((Object)"\u8f9b\u4ea5\u9769\u547d\u7eaa\u5ff5\u65e5", (Object)"10\u670810\u65e5");
        k.put((Object)"\u4e2d\u56fd\u5c11\u5e74\u5148\u950b\u961f\u8bde\u8fb0\u65e5", (Object)"10\u670813\u65e5");
        k.put((Object)"\u4e16\u754c\u6807\u51c6\u65e5", (Object)"10\u670814\u65e5");
        k.put((Object)"\u56fd\u9645\u76f2\u4eba\u8282", (Object)"10\u670815\u65e5");
        k.put((Object)"\u4e16\u754c\u7cae\u98df\u65e5", (Object)"10\u670816\u65e5");
        k.put((Object)"\u56fd\u9645\u6d88\u9664\u8d2b\u56f0\u65e5", (Object)"10\u670817\u65e5");
        k.put((Object)"\u4e16\u754c\u4f20\u7edf\u533b\u836f\u65e5", (Object)"10\u670822\u65e5");
        k.put((Object)"\u971c\u964d", (Object)"10\u670824\u65e5");
        k.put((Object)"\u8054\u5408\u56fd\u65e5", (Object)"10\u670822\u65e5");
        k.put((Object)"\u4e16\u754c\u52e4\u4fed\u65e5", (Object)"10\u670831\u65e5");
        k.put((Object)"\u4e2d\u56fd\u8bb0\u8005\u65e5", (Object)"11\u670808\u65e5");
        k.put((Object)"\u4e16\u754c\u9752\u5e74\u8282", (Object)"11\u670810\u65e5");
        k.put((Object)"\u5149\u68cd\u8282", (Object)"11\u670811\u65e5");
        k.put((Object)"\u4e16\u754c\u7cd6\u5c3f\u75c5\u65e5", (Object)"11\u670814\u65e5");
        k.put((Object)"\u56fd\u9645\u5927\u5b66\u751f\u8282", (Object)"11\u670817\u65e5");
        k.put((Object)"\u4e16\u754c\u95ee\u5019\u65e5", (Object)"11\u670821\u65e5");
        k.put((Object)"\u5c0f\u96ea", (Object)"11\u670822\u65e5");
        k.put((Object)"\u611f\u6069\u8282", (Object)"11\u670826\u65e5");
        k.put((Object)"\u4e16\u754c\u827e\u6ecb\u75c5\u65e5", (Object)"12\u670801\u65e5");
        k.put((Object)"\u56fd\u9645\u6b8b\u75be\u4eba\u65e5", (Object)"12\u670803\u65e5");
        k.put((Object)"\u5927\u96ea", (Object)"12\u670807\u65e5");
        k.put((Object)"\u4e00\u4e8c\u4e5d\u8fd0\u52a8\u7eaa\u5ff5\u65e5", (Object)"12\u670809\u65e5");
        k.put((Object)"\u4e16\u754c\u8db3\u7403\u65e5", (Object)"12\u670809\u65e5");
        k.put((Object)"\u4e16\u754c\u4eba\u6743\u65e5", (Object)"12\u670810\u65e5");
        k.put((Object)"\u897f\u5b89\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"12\u670812\u65e5");
        k.put((Object)"\u5357\u4eac\u5927\u5c60\u6740", (Object)"12\u670813\u65e5");
        k.put((Object)"\u6fb3\u95e8\u56de\u5f52\u65e5", (Object)"12\u670820\u65e5");
        k.put((Object)"\u56fd\u9645\u7bee\u7403\u65e5", (Object)"12\u670821\u65e5");
        k.put((Object)"\u5143\u65e6", (Object)"01\u670801\u65e5");
        k.put((Object)"\u814a\u516b\u8282", (Object)"01\u670817\u65e5");
        k.put((Object)"\u796d\u7076\u8282", (Object)"02\u670801\u65e5");
        k.put((Object)"\u9664\u5915", (Object)"02\u670807\u65e5");
        k.put((Object)"\u6625\u8282", (Object)"02\u670808\u65e5");
        k.put((Object)"\u7834\u4e94", (Object)"02\u670812\u65e5");
        k.put((Object)"\u60c5\u4eba\u8282", (Object)"02\u670814\u65e5");
        k.put((Object)"\u5143\u5bb5\u8282", (Object)"02\u670822\u65e5");
        k.put((Object)"\u4e09\u516b\u5987\u5973\u8282", (Object)"03\u670808\u65e5");
        k.put((Object)"\u4e8c\u6708\u4e8c\u9f99\u62ac\u5934", (Object)"03\u670810\u65e5");
        k.put((Object)"\u690d\u6811\u8282", (Object)"03\u670812\u65e5");
        k.put((Object)"\u6d88\u8d39\u8005\u6743\u76ca\u65e5", (Object)"03\u670815\u65e5");
        k.put((Object)"\u590d\u6d3b\u8282", (Object)"03\u670827\u65e5");
        k.put((Object)"\u611a\u4eba\u8282", (Object)"04\u670801\u65e5");
        k.put((Object)"\u6e05\u660e", (Object)"04\u670804\u65e5");
        k.put((Object)"\u6cfc\u6c34\u8282", (Object)"04\u670813\u65e5");
        k.put((Object)"\u4e09\u6708\u8857", (Object)"04\u670821\u65e5");
        k.put((Object)"\u4e94\u4e00\u52b3\u52a8\u8282", (Object)"05\u670801\u65e5");
        k.put((Object)"\u4e94\u56db\u9752\u5e74\u8282", (Object)"05\u670804\u65e5");
        k.put((Object)"\u6bcd\u4eb2\u8282", (Object)"05\u670808\u65e5");
        k.put((Object)"\u56fd\u9645\u62a4\u58eb\u8282", (Object)"05\u670812\u65e5");
        k.put((Object)"\u91ca\u8fe6\u725f\u5c3c\u4f5b\u5723\u8bde", (Object)"05\u670814\u65e5");
        k.put((Object)"\u4f5b\u5409\u7965\u65e5", (Object)"05\u670821\u65e5");
        k.put((Object)"\u8fc1\u5f99\u8282", (Object)"05\u670824\u65e5");
        k.put((Object)"\u516d\u4e00\u513f\u7ae5\u8282", (Object)"06\u670801\u65e5");
        k.put((Object)"\u7aef\u5348\u8282", (Object)"06\u670809\u65e5");
        k.put((Object)"\u7236\u4eb2\u8282", (Object)"06\u670819\u65e5");
        k.put((Object)"\u5efa\u515a\u8282", (Object)"07\u670801\u65e5");
        k.put((Object)"\u4e03\u4e03\u5362\u6c9f\u6865\u4e8b\u53d8", (Object)"07\u670807\u65e5");
        k.put((Object)"\u516b\u4e00\u5efa\u519b\u8282", (Object)"08\u670801\u65e5");
        k.put((Object)"\u4e03\u5915", (Object)"08\u670809\u65e5");
        k.put((Object)"\u65e5\u672c\u6295\u964d\u65e5", (Object)"08\u670815\u65e5");
        k.put((Object)"\u9b3c\u8282", (Object)"08\u670817\u65e5");
        k.put((Object)"\u6559\u5e08\u8282", (Object)"09\u670810\u65e5");
        k.put((Object)"\u4e2d\u79cb\u8282", (Object)"09\u670815\u65e5");
        k.put((Object)"\u4e5d\u4e00\u516b\u4e8b\u53d8\u7eaa\u5ff5\u65e5", (Object)"09\u670818\u65e5");
        k.put((Object)"\u56fd\u5e86\u8282", (Object)"10\u670801\u65e5");
        k.put((Object)"\u91cd\u9633\u8282", (Object)"10\u670809\u65e5");
        k.put((Object)"\u4e07\u5723\u8282\u524d\u591c", (Object)"10\u670831\u65e5");
        k.put((Object)"\u5149\u68cd\u8282", (Object)"11\u670811\u65e5");
        k.put((Object)"\u4e0b\u5143\u8282", (Object)"11\u670814\u65e5");
        k.put((Object)"\u611f\u6069\u8282", (Object)"11\u670824\u65e5");
        k.put((Object)"\u4e16\u754c\u827e\u6ecb\u75c5\u65e5", (Object)"12\u67081\u65e5");
        k.put((Object)"\u5357\u4eac\u5927\u5c60\u6740\u60bc\u5ff5\u65e5", (Object)"12\u670813\u65e5");
        k.put((Object)"\u5e73\u5b89\u591c", (Object)"12\u670824\u65e5");
        k.put((Object)"\u5723\u8bde\u8282", (Object)"12\u670825\u65e5");
        k.put((Object)"\u5c0f\u5bd2", (Object)"01\u670805\u65e5");
        k.put((Object)"\u5c0f\u5e74 ", (Object)"01\u670823\u65e5");
        k.put((Object)"\u7acb\u6625", (Object)"02\u670804\u65e5");
        k.put((Object)"\u96e8\u6c34", (Object)"02\u670819\u65e5");
        k.put((Object)"\u9f99\u62ac\u5934", (Object)"03\u670802\u65e5");
        k.put((Object)"\u60ca\u86f0", (Object)"03\u670805\u65e5");
        k.put((Object)"\u5987\u5973\u8282", (Object)"03\u670808\u65e5");
        k.put((Object)"\u6625\u5206", (Object)"03\u670821\u65e5");
        k.put((Object)"\u611a\u4eba\u8282", (Object)"04\u670801\u65e5");
        k.put((Object)"\u6e05\u660e\u8282", (Object)"04\u670805\u65e5");
        k.put((Object)"\u8c37\u96e8", (Object)"04\u670820\u65e5");
        k.put((Object)"\u52b3\u52a8\u8282", (Object)"05\u670801\u65e5");
        k.put((Object)"\u9752\u5e74\u8282", (Object)"05\u670804\u65e5");
        k.put((Object)"\u7acb\u590f", (Object)"05\u670805\u65e5");
        k.put((Object)"\u5c0f\u6ee1", (Object)"05\u670821\u65e5");
        k.put((Object)"\u516d\u4e00\u513f\u7ae5\u8282", (Object)"06\u670801\u65e5");
        k.put((Object)"\u590f\u81f3", (Object)"06\u670821\u65e5");
        k.put((Object)"\u9999\u6e2f\u56de\u5f52", (Object)"07\u670801\u65e5");
        k.put((Object)"\u5927\u6691", (Object)"07\u670823\u65e5");
        k.put((Object)"\u4e2d\u5143\u8282", (Object)"08\u670810\u65e5");
        k.put((Object)"\u5904\u6691", (Object)"08\u670823\u65e5");
        k.put((Object)"\u6559\u5e08\u8282", (Object)"09\u670810\u65e5");
        k.put((Object)"\u79cb\u5206", (Object)"09\u670823\u65e5");
        k.put((Object)"\u5bd2\u9732", (Object)"10\u670808\u65e5");
        k.put((Object)"\u971c\u964d", (Object)"10\u670823\u65e5");
        k.put((Object)"\u4e07\u5723\u8282", (Object)"11\u670801\u65e5");
        k.put((Object)"\u7acb\u51ac", (Object)"11\u670807\u65e5");
        k.put((Object)"\u5c0f\u96ea", (Object)"11\u670822\u65e5");
        k.put((Object)"\u5927\u96ea", (Object)"12\u670807\u65e5");
        k.put((Object)"\u56fd\u9645\u6c11\u822a\u65e5", (Object)"12\u670807\u65e5");
        k.put((Object)"\u51ac\u81f3", (Object)"12\u670822\u65e5");
        aqn.a(k, 2016);
        aqn.a(i, 2015);
        aqn.a(j, 2014);
        aqn.a(j);
        aqn.a(i);
        aqn.a(k);
    }

    aqn() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static int a(String string2) {
        int n2 = 0;
        if (TextUtils.isEmpty((CharSequence)string2)) return n2;
        String string3 = string2.replace((CharSequence)"\u5206", (CharSequence)"");
        if (aqn.c(string3)) {
            if (string3.length() < 2) return aqn.b(string3);
            string2 = string3.substring(0, 1);
            string3 = string3.substring(1, 2);
            return aqn.b(string2) * 10 + aqn.b(string3);
        }
        try {
            return Integer.parseInt((String)string3);
        }
        catch (NumberFormatException var0_1) {
            var0_1.printStackTrace();
            return 0;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static int a(String string2, int n2, int n3, long l2) {
        String string3;
        int n4;
        TedSDKLog.d(a, "parseDayType2 :  " + string2);
        int n5 = aqn.d(l2);
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return n5;
        }
        n5 = aqn.e(l2);
        int n6 = aqn.f(l2);
        String string4 = string2.substring(1, 2);
        string2 = string3 = string2.substring(string2.length() - 1);
        if ("\u672b".equals((Object)string3)) {
            string2 = "6";
        }
        string3 = string2;
        if ("\u65e5".equals((Object)string2)) {
            string3 = "7";
        }
        if (aqn.c(string4)) {
            n5 = aqn.b(string4);
        } else {
            try {
                n5 = n4 = Integer.parseInt((String)string4);
            }
            catch (NumberFormatException var0_2) {}
        }
        if (aqn.c(string3)) {
            n6 = aqn.b(string3);
        } else {
            try {
                n6 = n4 = Integer.parseInt((String)string3);
            }
            catch (NumberFormatException var0_1) {}
        }
        string2 = Calendar.getInstance();
        string2.set(1, n2);
        string2.set(2, n3);
        string2.set(4, n5);
        string2.set(7, n6);
        n2 = string2.get(5);
        TedSDKLog.d(a, "parseDayType2 return:  " + n2);
        return n2;
    }

    static int a(String string2, long l2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return aqn.b(l2);
        }
        if ("13".equals((Object)(string2 = string2.trim().replace((CharSequence)"-", (CharSequence)"").replace((CharSequence)"/", (CharSequence)""))) || "2013".equals((Object)string2) || "2013\u5e74".equals((Object)string2)) {
            return 2013;
        }
        if ("12".equals((Object)string2) || "2012".equals((Object)string2) || "2012\u5e74".equals((Object)string2)) {
            return 2012;
        }
        if ("11".equals((Object)string2) || "2011".equals((Object)string2) || "2011\u5e74".equals((Object)string2)) {
            return 2011;
        }
        if ("\u4eca\u5e74".equals((Object)string2) || "15".equals((Object)string2) || "2015".equals((Object)string2) || "2015\u5e74".equals((Object)string2)) {
            return 2015;
        }
        if ("\u53bb\u5e74".equals((Object)string2) || "14".equals((Object)string2) || "2014".equals((Object)string2) || "2014\u5e74".equals((Object)string2)) {
            return 2014;
        }
        if ("\u660e\u5e74".equals((Object)string2) || "16".equals((Object)string2) || "2016".equals((Object)string2) || "2016\u5e74".equals((Object)string2)) {
            return 2016;
        }
        if ("\u540e\u5e74".equals((Object)string2) || "17".equals((Object)string2) || "2017".equals((Object)string2) || "2017\u5e74".equals((Object)string2)) {
            return 2017;
        }
        if ("\u5927\u540e\u5e74".equals((Object)string2) || "18".equals((Object)string2) || "2018".equals((Object)string2) || "2018\u5e74".equals((Object)string2)) {
            return 2018;
        }
        if ("\u6b21\u5e74".equals((Object)string2)) {
            return aqn.b(l2) + 1;
        }
        return aqn.b(l2);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static int a(String var0, String var1_2) {
        TedSDKLog.d(aqn.a, "startHourString: " + var0);
        var0 = var0.replace((CharSequence)"\u70b9", (CharSequence)"").replace((CharSequence)"\u65f6", (CharSequence)"");
        var2_3 = -1;
        if ("\u4e24".equals((Object)var0)) {
            var2_3 = 2;
        } else if (aqn.c(var0)) {
            var2_3 = aqn.b(var0);
        } else {
            try {
                var2_3 = var3_4 = Integer.parseInt((String)var0);
            }
            catch (Exception var0_1) {}
        }
        var3_4 = var2_3;
        if (var2_3 < 0) ** GOTO lbl22
        var3_4 = var2_3;
        if (var2_3 >= 12) ** GOTO lbl22
        if ("\u4e0b\u5348".equals((Object)var1_2)) ** GOTO lbl-1000
        var3_4 = var2_3;
        if ("\u665a\u4e0a".equals((Object)var1_2)) lbl-1000: // 2 sources:
        {
            var3_4 = var2_3 + 12;
        }
lbl22: // 5 sources:
        TedSDKLog.d(aqn.a, "hours: " + var3_4);
        return var3_4;
    }

    static long a(int n2, int n3, int n4) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n2).append("\u5e74").append(n3).append("\u6708").append(n4).append("\u65e5");
        try {
            long l2 = g.parse(stringBuilder.toString()).getTime();
            return l2;
        }
        catch (ParseException var5_4) {
            var5_4.printStackTrace();
            return -1;
        }
    }

    static long a(long l2) {
        TedSDKLog.d(a, "getCurrentDayByTime: " + l2);
        Object object = new Date(l2);
        object = g.format((Date)object);
        try {
            long l3 = g.parse((String)object).getTime();
            TedSDKLog.d(a, "getCurrentDayByTime: " + l3);
            return l3;
        }
        catch (ParseException var4_2) {
            var4_2.printStackTrace();
            return l2;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static a a(String var0, long var1_4, int var3_5) {
        var4_6 = 1;
        TedSDKLog.d(aqn.a, "parseDay1 :  " + var0);
        var5_7 = aqn.d(var1_4);
        var6_8 = new a();
        var6_8.a = var5_7++;
        if (TextUtils.isEmpty((CharSequence)var0)) {
            return var6_8;
        }
        var7_9 = var0.replace((CharSequence)"\u65e5", (CharSequence)"").replace((CharSequence)"\u53f7", (CharSequence)"");
        if (var0.contains((CharSequence)"\u6b21")) ** GOTO lbl20
        if (!aqn.c(var7_9)) ** GOTO lbl14
        var3_5 = aqn.b(var7_9);
        ** GOTO lbl30
lbl14: // 2 sources:
        var3_5 = Integer.parseInt((String)var7_9);
        try {
            var6_8.a = var3_5;
        }
        catch (NumberFormatException var0_1) {}
        ** GOTO lbl-1000
lbl20: // 1 sources:
        if (var3_5 <= 0 || var3_5 > 12 || var5_7 <= aqn.e[var3_5 - 1]) ** GOTO lbl29
        var6_8.a = 1;
        var6_8.b = true;
        var3_5 = var4_6;
        ** GOTO lbl30
        catch (NumberFormatException var0_3) {
            var3_5 = var5_7;
        }
lbl-1000: // 2 sources:
        {
            var0_2.printStackTrace();
        }
        ** GOTO lbl30
lbl29: // 1 sources:
        var3_5 = var5_7;
lbl30: // 5 sources:
        TedSDKLog.d(aqn.a, "parseDateType1 return:  " + var3_5);
        return var6_8;
    }

    private static void a(Map<String, String> map) {
        map.put("\u5143\u65e6\u8282", "01\u670801\u65e5");
        map.put("\u4e16\u754c\u6e7f\u5730\u65e5", "02\u670802\u65e5");
        map.put("\u56fd\u9645\u6c14\u8c61\u8282", "02\u670810\u65e5");
        map.put("\u60c5\u4eba\u8282", "02\u670814\u65e5");
        map.put("\u56fd\u9645\u6d77\u8c79\u65e5", "03\u670801\u65e5");
        map.put("\u5168\u56fd\u7231\u8033\u65e5", "03\u670803\u65e5");
        map.put("\u5b66\u96f7\u950b\u7eaa\u5ff5\u65e5", "03\u670805\u65e5");
        map.put("\u5987\u5973\u8282", "03\u670808\u65e5");
        map.put("\u690d\u6811\u8282", "03\u670812\u65e5");
        map.put("\u5b59\u4e2d\u5c71\u901d\u4e16\u7eaa\u5ff5\u65e5", "03\u670812\u65e5");
        map.put("\u56fd\u9645\u8b66\u5bdf\u65e5", "03\u670814\u65e5");
        map.put("\u6d88\u8d39\u8005\u6743\u76ca\u65e5", "03\u670815\u65e5");
        map.put("\u4e2d\u56fd\u56fd\u533b\u8282", "03\u670817\u65e5");
        map.put("\u56fd\u9645\u822a\u6d77\u65e5", "03\u670817\u65e5");
        map.put("\u4e16\u754c\u68ee\u6797\u65e5", "03\u670821\u65e5");
        map.put("\u6d88\u9664\u79cd\u65cf\u6b67\u89c6\u56fd\u9645\u65e5", "03\u670821\u65e5");
        map.put("\u4e16\u754c\u513f\u6b4c\u65e5", "03\u670821\u65e5");
        map.put("\u4e16\u754c\u6c34\u65e5", "03\u670822\u65e5");
        map.put("\u4e16\u754c\u6c14\u8c61\u65e5", "03\u670823\u65e5");
        map.put("\u4e16\u754c\u9632\u6cbb\u7ed3\u6838\u75c5\u65e5", "03\u670824\u65e5");
        map.put("\u5168\u56fd\u4e2d\u5c0f\u5b66\u751f\u5b89\u5168\u6559\u80b2\u65e5", "03\u670825\u65e5");
        map.put("\u5df4\u52d2\u65af\u5766\u56fd\u571f\u65e5", "03\u670830\u65e5");
        map.put("\u611a\u4eba\u8282", "04\u670801\u65e5");
        map.put("\u4e16\u754c\u536b\u751f\u65e5", "04\u670807\u65e5");
        map.put("\u4e16\u754c\u5730\u7403\u65e5", "04\u670822\u65e5");
        map.put("\u4e16\u754c\u56fe\u4e66\u548c\u7248\u6743\u65e5", "04\u670823\u65e5");
        map.put("\u4e9a\u975e\u65b0\u95fb\u5de5\u4f5c\u8005\u65e5", "04\u670824\u65e5");
        map.put("\u52b3\u52a8\u8282", "05\u670801\u65e5");
        map.put("\u9752\u5e74\u8282", "05\u670804\u65e5");
        map.put("\u7898\u7f3a\u4e4f\u75c5\u9632\u6cbb\u65e5", "05\u670805\u65e5");
        map.put("\u4e16\u754c\u7ea2\u5341\u5b57\u65e5", "05\u670808\u65e5");
        map.put("\u56fd\u9645\u62a4\u58eb\u8282", "05\u670812\u65e5");
        map.put("\u56fd\u9645\u5bb6\u5ead\u65e5", "05\u670815\u65e5");
        map.put("\u4e16\u754c\u7535\u4fe1\u65e5", "05\u670817\u65e5");
        map.put("\u56fd\u9645\u535a\u7269\u9986\u65e5", "05\u670818\u65e5");
        map.put("\u5168\u56fd\u5b66\u751f\u8425\u517b\u65e5", "05\u670820\u65e5");
        map.put("\u56fd\u9645\u751f\u7269\u591a\u6837\u6027\u65e5", "05\u670822\u65e5");
        map.put("\u56fd\u9645\u725b\u5976\u65e5", "05\u670823\u65e5");
        map.put("\u4e16\u754c\u65e0\u70df\u65e5", "05\u670831\u65e5");
        map.put("\u56fd\u9645\u513f\u7ae5\u8282", "06\u670801\u65e5");
        map.put("\u4e16\u754c\u73af\u5883\u65e5", "06\u670805\u65e5");
        map.put("\u5168\u56fd\u7231\u773c\u65e5", "06\u670806\u65e5");
        map.put("\u9632\u6cbb\u8352\u6f20\u5316\u548c\u5e72\u65f1\u65e5", "06\u670817\u65e5");
        map.put("\u56fd\u9645\u5965\u6797\u5339\u514b\u65e5", "06\u670823\u65e5");
        map.put("\u5168\u56fd\u571f\u5730\u65e5", "06\u670825\u65e5");
        map.put("\u56fd\u9645\u7981\u6bd2\u65e5", "06\u670826\u65e5");
        map.put("\u9999\u6e2f\u56de\u5f52\u7eaa\u5ff5\u65e5", "07\u670801\u65e5");
        map.put("\u4e2d\u5171\u8bde\u8fb0", "07\u670801\u65e5");
        map.put("\u4e16\u754c\u5efa\u7b51\u65e5", "07\u670801\u65e5");
        map.put("\u56fd\u9645\u4f53\u80b2\u8bb0\u8005\u65e5", "07\u670802\u65e5");
        map.put("\u6297\u65e5\u6218\u4e89\u7eaa\u5ff5\u65e5", "07\u670807\u65e5");
        map.put("\u4e16\u754c\u4eba\u53e3\u65e5", "07\u670811\u65e5");
        map.put("\u975e\u6d32\u5987\u5973\u65e5", "07\u670830\u65e5");
        map.put("\u5efa\u519b\u8282", "08\u670801\u65e5");
        map.put("\u4e2d\u56fd\u7537\u5b50\u8282", "08\u670808\u65e5");
        map.put("\u7238\u7238\u8282", "08\u670808\u65e5");
        map.put("\u6297\u65e5\u6218\u4e89\u80dc\u5229\u7eaa\u5ff5", "08\u670815\u65e5");
        map.put("\u56fd\u9645\u626b\u76f2\u65e5", "09\u670808\u65e5");
        map.put("\u56fd\u9645\u65b0\u95fb\u5de5\u4f5c\u8005\u65e5", "09\u670808\u65e5");
        map.put("\u6bdb\u6cfd\u4e1c\u901d\u4e16\u7eaa\u5ff5", "09\u670809\u65e5");
        map.put("\u4e2d\u56fd\u6559\u5e08\u8282", "09\u670810\u65e5");
        map.put("\u4e16\u754c\u6e05\u6d01\u5730\u7403\u65e5", "09\u670814\u65e5");
        map.put("\u56fd\u9645\u81ed\u6c27\u5c42\u4fdd\u62a4\u65e5", "09\u670816\u65e5");
        map.put("\u4e5d\u4e00\u516b\u4e8b\u53d8\u7eaa\u5ff5\u65e5", "09\u670818\u65e5");
        map.put("\u56fd\u9645\u7231\u7259\u65e5", "09\u670820\u65e5");
        map.put("\u4e16\u754c\u65c5\u6e38\u65e5", "09\u670827\u65e5");
        map.put("\u5b54\u5b50\u8bde\u8fb0", "09\u670828\u65e5");
        map.put("\u56fd\u5e86\u8282", "10\u670801\u65e5");
        map.put("\u4e16\u754c\u97f3\u4e50\u65e5", "10\u670801\u65e5");
        map.put("\u56fd\u9645\u8001\u4eba\u8282", "10\u670801\u65e5");
        map.put("\u56fd\u9645\u548c\u5e73\u4e0e\u6c11\u4e3b\u81ea\u7531\u6597\u4e89\u65e5", "10\u670802\u65e5");
        map.put("\u4e16\u754c\u52a8\u7269\u65e5", "10\u670804\u65e5");
        map.put("\u8001\u4eba\u8282", "10\u670806\u65e5");
        map.put("\u5168\u56fd\u9ad8\u8840\u538b\u65e5", "10\u670808\u65e5");
        map.put("\u4e16\u754c\u89c6\u89c9\u65e5", "10\u670808\u65e5");
        map.put("\u4e16\u754c\u90ae\u653f\u65e5", "10\u670809\u65e5");
        map.put("\u4e07\u56fd\u90ae\u8054\u65e5", "10\u670809\u65e5");
        map.put("\u8f9b\u4ea5\u9769\u547d\u7eaa\u5ff5\u65e5", "10\u670810\u65e5");
        map.put("\u4e16\u754c\u7cbe\u795e\u536b\u751f\u65e5", "10\u670810\u65e5");
        map.put("\u4e16\u754c\u4fdd\u5065\u65e5", "10\u670813\u65e5");
        map.put("\u56fd\u9645\u6559\u5e08\u8282", "10\u670813\u65e5");
        map.put("\u4e16\u754c\u6807\u51c6\u65e5", "10\u670814\u65e5");
        map.put("\u56fd\u9645\u76f2\u4eba\u8282", "10\u670815\u65e5");
        map.put("\u767d\u624b\u6756\u8282", "10\u670815\u65e5");
        map.put("\u4e16\u754c\u7cae\u98df\u65e5", "10\u670816\u65e5");
        map.put("\u4e16\u754c\u6d88\u9664\u8d2b\u56f0\u65e5", "10\u670817\u65e5");
        map.put("\u4e16\u754c\u4f20\u7edf\u533b\u836f\u65e5", "10\u670822\u65e5");
        map.put("\u8054\u5408\u56fd\u65e5", "10\u670824\u65e5");
        map.put("\u4e16\u754c\u53d1\u5c55\u4fe1\u606f\u65e5", "10\u670824\u65e5");
        map.put("\u4e16\u754c\u52e4\u4fed\u65e5", "10\u670831\u65e5");
        map.put("\u5341\u6708\u793e\u4f1a\u4e3b\u4e49\u9769\u547d\u7eaa\u5ff5\u65e5", "11\u670807\u65e5");
        map.put("\u4e2d\u56fd\u8bb0\u8005\u65e5", "11\u670808\u65e5");
        map.put("\u5168\u56fd\u6d88\u9632\u5b89\u5168\u5ba3\u4f20\u6559\u80b2\u65e5", "11\u670809\u65e5");
        map.put("\u4e16\u754c\u9752\u5e74\u8282", "11\u670810\u65e5");
        map.put("\u5b59\u4e2d\u5c71\u8bde\u8fb0\u7eaa\u5ff5\u65e5", "11\u670812\u65e5");
        map.put("\u4e16\u754c\u7cd6\u5c3f\u75c5\u65e5", "11\u670814\u65e5");
        map.put("\u56fd\u9645\u5927\u5b66\u751f\u8282", "11\u670817\u65e5");
        map.put("\u4e16\u754c\u5b66\u751f\u8282", "11\u670817\u65e5");
        map.put("\u4e16\u754c\u95ee\u5019\u65e5", "11\u670821\u65e5");
        map.put("\u4e16\u754c\u7535\u89c6\u65e5", "11\u670821\u65e5");
        map.put("\u56fd\u9645\u58f0\u63f4\u5df4\u52d2\u65af\u5766\u4eba\u6c11\u56fd\u9645\u65e5", "11\u670829\u65e5");
        map.put("\u4e16\u754c\u827e\u6ecb\u75c5\u65e5", "12\u670801\u65e5");
        map.put("\u4e16\u754c\u6b8b\u75be\u4eba\u65e5", "12\u670803\u65e5");
        map.put("\u56fd\u9645\u7ecf\u6d4e\u548c\u793e\u4f1a\u53d1\u5c55\u5fd7\u613f\u4eba\u5458\u65e5", "12\u670805\u65e5");
        map.put("\u56fd\u9645\u513f\u7ae5\u7535\u89c6\u65e5", "12\u670808\u65e5");
        map.put("\u4e16\u754c\u8db3\u7403\u65e5", "12\u670809\u65e5");
        map.put("\u4e16\u754c\u4eba\u6743\u65e5", "12\u670810\u65e5");
        map.put("\u897f\u5b89\u4e8b\u53d8\u7eaa\u5ff5\u65e5", "12\u670812\u65e5");
        map.put("\u5357\u4eac\u5927\u5c60\u6740\u7eaa\u5ff5\u65e5", "12\u670813\u65e5");
        map.put("\u6fb3\u95e8\u56de\u5f52\u7eaa\u5ff5", "12\u670820\u65e5");
        map.put("\u56fd\u9645\u7bee\u7403\u65e5", "12\u670821\u65e5");
        map.put("\u5e73\u5b89\u591c", "12\u670824\u65e5");
        map.put("\u5723\u8bde\u8282", "12\u670825\u65e5");
        map.put("\u6bdb\u6cfd\u4e1c\u8bde\u8fb0\u7eaa\u5ff5", "12\u670826\u65e5");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void a(Map<String, String> map, int n2) {
        try {
            Date date = h.parse("2014\u5e741\u67085\u65e5 18:24");
            int n3 = 0;
            do {
                if (n3 >= f.length) {
                    return;
                }
                Object object = new Date((long)((float)(n2 - 2014) * 3.15569254E10f + (float)(f[n3] * 60000) + (float)date.getTime()));
                Object object2 = Calendar.getInstance();
                object2.setTime((Date)object);
                int n4 = object2.get(2) + 1;
                int n5 = object2.get(5);
                String string2 = "" + n4;
                object2 = "" + n5;
                object = string2;
                if (n4 < 10) {
                    object = "0" + string2;
                }
                if (n5 < 10) {
                    object2 = "0" + n5;
                }
                object = String.valueOf((Object)object) + "\u6708" + (String)object2 + "\u65e5";
                map.put(d[n3], (String)object);
                ++n3;
            } while (true);
        }
        catch (ParseException var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    static int b(long l2) {
        Calendar calendar = Calendar.getInstance((Locale)Locale.CHINA);
        calendar.setTime(new Date(l2));
        return calendar.get(1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static int b(String string2) {
        int n2 = 0;
        while (n2 < c.length) {
            int n3 = n2;
            if (c[n2].equals((Object)string2)) return n3;
            ++n2;
        }
        return 1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    static a b(String var0, long var1_4) {
        var3_5 = 1;
        TedSDKLog.d(aqn.a, "parseMonthString :  " + var0);
        var5_6 = new a();
        var5_6.a = var4_7 = aqn.c(var1_4);
        if (TextUtils.isEmpty((CharSequence)var0)) {
            return var5_6;
        }
        if ((var0 = var0.replace((CharSequence)"\u6708", (CharSequence)"").replace((CharSequence)"-", (CharSequence)"").replace((CharSequence)"/", (CharSequence)"").trim()).contains((CharSequence)"\u6b21") || var0.contains((CharSequence)"\u4e0b")) ** GOTO lbl18
        if (!aqn.c(var0)) ** GOTO lbl12
        var3_5 = aqn.b(var0);
        ** GOTO lbl27
lbl12: // 2 sources:
        var3_5 = Integer.parseInt((String)var0);
        try {
            var5_6.a = var3_5;
        }
        catch (NumberFormatException var0_1) {}
        ** GOTO lbl-1000
lbl18: // 1 sources:
        if (++var4_7 <= 12) ** GOTO lbl26
        var5_6.a = 1;
        var5_6.b = true;
        ** GOTO lbl27
        catch (NumberFormatException var0_3) {
            var3_5 = var4_7;
        }
lbl-1000: // 2 sources:
        {
            var0_2.printStackTrace();
        }
        ** GOTO lbl27
lbl26: // 1 sources:
        var3_5 = var4_7;
lbl27: // 5 sources:
        TedSDKLog.d(aqn.a, "parseMonthString return:  " + var3_5);
        return var5_6;
    }

    static int c(long l2) {
        Calendar calendar = Calendar.getInstance((Locale)Locale.CHINA);
        calendar.setTime(new Date(l2));
        return calendar.get(2) + 1;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static long c(String string2, long l2) {
        TedSDKLog.d(a, "parseDateType3 :  " + string2);
        long l3 = aqn.a(l2);
        if ("\u660e\u5929".equals((Object)string2)) return l3 + 86400000;
        if ("\u660e\u65e5".equals((Object)string2)) {
            return l3 + 86400000;
        }
        if ("\u540e\u5929".equals((Object)string2)) return l3 + 172800000;
        if ("\u540e\u65e5".equals((Object)string2)) {
            return l3 + 172800000;
        }
        if ("\u5927\u540e\u5929".equals((Object)string2)) return l3 + 259200000;
        if ("\u5927\u540e\u65e5".equals((Object)string2)) {
            return l3 + 259200000;
        }
        l2 = l3;
        if (!"\u6628\u5929".equals((Object)string2)) return l2;
        return l3 - 86400000;
    }

    static boolean c(String string2) {
        if (b.matcher((CharSequence)string2).find()) {
            return true;
        }
        return false;
    }

    static int d(long l2) {
        Calendar calendar = Calendar.getInstance((Locale)Locale.CHINA);
        calendar.setTime(new Date(l2));
        return calendar.get(5);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    static long d(String string2, long l2) {
        int n2;
        TedSDKLog.d(a, "parseDateType4 :  " + string2);
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return aqn.a(l2);
        }
        String string3 = string2.substring(string2.length() - 1);
        int n3 = aqn.f(l2);
        string2 = string3;
        if ("\u672b".equals((Object)string3)) {
            string2 = "6";
        }
        string3 = string2;
        if ("\u65e5".equals((Object)string2)) {
            string3 = "7";
        }
        if (aqn.c(string3)) {
            n2 = aqn.b(string3);
        } else {
            try {
                n2 = Integer.parseInt((String)string3);
            }
            catch (NumberFormatException var0_1) {
                n2 = n3;
            }
        }
        n2 = n3 = n2 - n3 + 1;
        if (n3 < 0) {
            n2 = n3 + 7;
        }
        TedSDKLog.d(a, "parseDateType4 Offset :" + n2);
        l2 = aqn.a(l2);
        return (long)(n2 + 1) * 86400000 + l2;
    }

    static int e(long l2) {
        Calendar calendar = Calendar.getInstance((Locale)Locale.CHINA);
        calendar.setTime(new Date(l2));
        return calendar.get(4) + 1;
    }

    static int f(long l2) {
        Calendar calendar = Calendar.getInstance((Locale)Locale.CHINA);
        calendar.setTime(new Date(l2));
        return calendar.get(7) + 1;
    }

    static class a {
        int a;
        boolean b = false;

        a() {
        }
    }

}

