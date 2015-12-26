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

class aqn
{
  private static final String a = aqn.class.getSimpleName();
  private static Pattern b = Pattern.compile("[\\u4e00-\\u9fa5]");
  private static final String[] c = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九", "三十", "三十一" };
  private static String[] d = { "小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至" };
  private static final int[] e = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  private static long[] f;
  private static SimpleDateFormat g;
  private static SimpleDateFormat h;
  private static HashMap<String, String> i;
  private static HashMap<String, String> j;
  private static HashMap<String, String> k;
  private static int[] l;
  private static int[] m;
  private static int[] n;
  
  static
  {
    long[] arrayOfLong = new long[24];
    arrayOfLong[1] = 21208L;
    arrayOfLong[2] = 42467L;
    arrayOfLong[3] = 63836L;
    arrayOfLong[4] = 85337L;
    arrayOfLong[5] = 107014L;
    arrayOfLong[6] = 128867L;
    arrayOfLong[7] = 150921L;
    arrayOfLong[8] = 173149L;
    arrayOfLong[9] = 195551L;
    arrayOfLong[10] = 218072L;
    arrayOfLong[11] = 240693L;
    arrayOfLong[12] = 263343L;
    arrayOfLong[13] = 285989L;
    arrayOfLong[14] = 308563L;
    arrayOfLong[15] = 331033L;
    arrayOfLong[16] = 353350L;
    arrayOfLong[17] = 375494L;
    arrayOfLong[18] = 397447L;
    arrayOfLong[19] = 419210L;
    arrayOfLong[20] = 440795L;
    arrayOfLong[21] = 462224L;
    arrayOfLong[22] = 483532L;
    arrayOfLong[23] = 504758L;
    f = arrayOfLong;
    g = new SimpleDateFormat("yyyy年MM月dd日", Locale.ROOT);
    h = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.ROOT);
    i = new HashMap();
    j = new HashMap();
    k = new HashMap();
    l = new int[] { 5, 1, 2, 5, 7, 3, 5, 1, 4, 6, 2, 4 };
    m = new int[] { 4, 7, 7, 3, 5, 1, 3, 6, 2, 4, 7, 2 };
    n = new int[] { 3, 6, 6, 2, 4, 7, 2, 5, 1, 3, 6, 1 };
    i.put("元旦", "01月01日");
    i.put("黑人日", "01月04日");
    i.put("小寒", "01月05日");
    i.put("国际麻风节", "01月25日");
    i.put("腊八节", "01月27日");
    i.put("世界湿地日", "02月02日");
    i.put("立春", "02月04日");
    i.put("国际声援南非日", "02月07日");
    i.put("国际气象节", "02月10日");
    i.put("小年", "02月11日");
    i.put("情人节", "02月14日");
    i.put("除夕", "02月18日");
    i.put("春节", "02月19日");
    i.put("雨水", "02月19日");
    i.put("第三世界青年日", "02月24日");
    i.put("世界居住条件调查日", "02月28日");
    i.put("国际海豹日", "03月01日");
    i.put("全国爱耳日", "03月03日");
    i.put("元宵节", "03月05日");
    i.put("学雷锋日", "03月05日");
    i.put("惊蛰", "03月06日");
    i.put("三八妇女节", "03月08日");
    i.put("植树节", "03月12日");
    i.put("白色情人节", "03月14日");
    i.put("消费者权益日", "03月15日");
    i.put("国际航海日", "03月17日");
    i.put("世界无肉日", "03月20日");
    i.put("春分", "03月21日");
    i.put("龙抬头", "03月21日");
    i.put("世界睡眠日", "03月21日");
    i.put("世界水日", "03月22日");
    i.put("世界气象日", "03月23日");
    i.put("愚人节", "04月01日");
    i.put("国际儿童图书日", "04月02日");
    i.put("寒食节", "04月04日");
    i.put("清明节", "04月05日");
    i.put("复活节", "04月05日");
    i.put("世界卫生日", "04月07日");
    i.put("世界帕金森病日", "04月11日");
    i.put("谷雨", "04月20日");
    i.put("世界地球日", "04月22日");
    i.put("世界读书日", "04月23日");
    i.put("知识产权日", "04月26日");
    i.put("全国交通安全反思日", "04月26日");
    i.put("世界儿童日", "04月26日");
    i.put("国际劳动节", "05月01日");
    i.put("五四青年节", "05月04日");
    i.put("立夏", "05月06日");
    i.put("佛诞", "05月25日");
    i.put("世界红十字日", "05月08日");
    i.put("世界微笑日", "05月08日");
    i.put("母亲节", "05月10日");
    i.put("国际护士节", "05月12日");
    i.put("国际家庭日", "05月15日");
    i.put("世界电信日", "05月17日");
    i.put("全国助残日", "05月17日");
    i.put("国际博物馆日", "05月18日");
    i.put("小满", "05月21日");
    i.put("五卅运动纪念", "05月30日");
    i.put("世界无烟日", "05月31日");
    i.put("六一儿童节", "06月01日");
    i.put("世界环境日", "06月05日");
    i.put("芒种", "06月06日");
    i.put("全国爱眼日", "06月06日");
    i.put("中国人口日", "06月11日");
    i.put("世界难民日", "06月20日");
    i.put("端午节", "06月20日");
    i.put("父亲节", "06月21日");
    i.put("夏至", "06月22日");
    i.put("中国儿童慈善活动日", "06月22日");
    i.put("国际奥林匹克日", "06月23日");
    i.put("全国土地日", "06月25日");
    i.put("国际禁毒日", "06月26日");
    i.put("世界青年联欢节", "06月30日");
    i.put("中国共产党诞生日", "07月01日");
    i.put("香港回归日", "07月01日");
    i.put("国际体育记者日", "07月02日");
    i.put("国际接吻日", "07月06日");
    i.put("抗日战争胜利纪念日", "07月07日");
    i.put("小暑", "07月07日");
    i.put("世界人口日", "07月11日");
    i.put("大暑", "07月23日");
    i.put("非洲妇女日", "07月30日");
    i.put("八一建军节", "08月01日");
    i.put("国际电影节", "08月06日");
    i.put("立秋", "08月08日");
    i.put("日本投降日", "08月15日");
    i.put("七夕", "08月20日");
    i.put("处暑", "08月23日");
    i.put("中元节", "08月28日");
    i.put("抗日胜利纪念日", "09月03日");
    i.put("白露", "09月08日");
    i.put("国际扫盲日", "09月08日");
    i.put("教师节", "09月10日");
    i.put("世界清洁地球日", "09月12日");
    i.put("国际臭氧层保护日", "09月12日");
    i.put("国际和平日", "09月16日");
    i.put("九一八事变纪念日", "09月18日");
    i.put("国际爱牙日", "09月20日");
    i.put("世界无车日", "09月22日");
    i.put("秋分", "09月23日");
    i.put("中秋节", "09月27日");
    i.put("世界旅游日", "09月27日");
    i.put("国际聋人节", "09月28日");
    i.put("国庆节", "10月01日");
    i.put("世界动物日", "10月04日");
    i.put("寒露", "10月08日");
    i.put("世界邮政日", "10月09日");
    i.put("辛亥革命纪念日", "10月10日");
    i.put("中国少年先锋队诞辰日", "10月13日");
    i.put("世界标准日", "10月14日");
    i.put("国际盲人节", "10月15日");
    i.put("世界粮食日", "10月16日");
    i.put("国际消除贫困日", "10月17日");
    i.put("重阳节", "10月21日");
    i.put("世界传统医药日", "10月22日");
    i.put("霜降", "10月24日");
    i.put("联合国日", "10月22日");
    i.put("世界勤俭日", "10月31日");
    i.put("万圣节", "10月31日");
    i.put("立冬", "11月08日");
    i.put("中国记者日", "11月08日");
    i.put("世界青年节", "11月10日");
    i.put("光棍节", "11月11日");
    i.put("世界糖尿病日", "11月14日");
    i.put("国际大学生节", "11月17日");
    i.put("世界问候日", "11月21日");
    i.put("小雪", "11月22日");
    i.put("感恩节", "11月26日");
    i.put("世界艾滋病日", "12月01日");
    i.put("国际残疾人日", "12月03日");
    i.put("大雪", "12月07日");
    i.put("一二九运动纪念日", "12月09日");
    i.put("世界足球日", "12月09日");
    i.put("世界人权日", "12月10日");
    i.put("西安事变纪念日", "12月12日");
    i.put("南京大屠杀", "12月13日");
    i.put("澳门回归日", "12月20日");
    i.put("国际篮球日", "12月21日");
    i.put("冬至", "12月22日");
    i.put("平安夜", "12月24日");
    i.put("圣诞节", "12月25日");
    j.put("元旦", "01月01日");
    j.put("黑人日", "01月04日");
    j.put("小寒", "01月05日");
    j.put("国际麻风节", "01月25日");
    j.put("腊八节", "01月27日");
    j.put("世界湿地日", "02月02日");
    j.put("立春", "02月04日");
    j.put("国际声援南非日", "02月07日");
    j.put("国际气象节", "02月10日");
    j.put("小年", "02月11日");
    j.put("情人节", "02月14日");
    j.put("除夕", "02月18日");
    j.put("春节", "02月19日");
    j.put("雨水", "02月19日");
    j.put("第三世界青年日", "02月24日");
    j.put("世界居住条件调查日", "02月28日");
    j.put("国际海豹日", "03月01日");
    j.put("全国爱耳日", "03月03日");
    j.put("元宵节", "03月05日");
    j.put("学雷锋日", "03月05日");
    j.put("惊蛰", "03月06日");
    j.put("三八妇女节", "03月08日");
    j.put("植树节", "03月12日");
    j.put("白色情人节", "03月14日");
    j.put("消费者权益日", "03月15日");
    j.put("国际航海日", "03月17日");
    j.put("世界无肉日", "03月20日");
    j.put("春分", "03月21日");
    j.put("龙抬头", "03月21日");
    j.put("世界睡眠日", "03月21日");
    j.put("世界水日", "03月22日");
    j.put("世界气象日", "03月23日");
    j.put("愚人节", "04月01日");
    j.put("国际儿童图书日", "04月02日");
    j.put("寒食节", "04月04日");
    j.put("清明节", "04月05日");
    j.put("复活节", "04月05日");
    j.put("世界卫生日", "04月07日");
    j.put("世界帕金森病日", "04月11日");
    j.put("谷雨", "04月20日");
    j.put("世界地球日", "04月22日");
    j.put("世界读书日", "04月23日");
    j.put("知识产权日", "04月26日");
    j.put("全国交通安全反思日", "04月26日");
    j.put("世界儿童日", "04月26日");
    j.put("国际劳动节", "05月01日");
    j.put("五四青年节", "05月04日");
    j.put("立夏", "05月06日");
    j.put("佛诞", "05月25日");
    j.put("世界红十字日", "05月08日");
    j.put("世界微笑日", "05月08日");
    j.put("母亲节", "05月10日");
    j.put("国际护士节", "05月12日");
    j.put("国际家庭日", "05月15日");
    j.put("世界电信日", "05月17日");
    j.put("全国助残日", "05月17日");
    j.put("国际博物馆日", "05月18日");
    j.put("小满", "05月21日");
    j.put("五卅运动纪念", "05月30日");
    j.put("世界无烟日", "05月31日");
    j.put("六一儿童节", "06月01日");
    j.put("世界环境日", "06月05日");
    j.put("芒种", "06月06日");
    j.put("全国爱眼日", "06月06日");
    j.put("中国人口日", "06月11日");
    j.put("世界难民日", "06月20日");
    j.put("端午节", "06月20日");
    j.put("父亲节", "06月21日");
    j.put("夏至", "06月22日");
    j.put("中国儿童慈善活动日", "06月22日");
    j.put("国际奥林匹克日", "06月23日");
    j.put("全国土地日", "06月25日");
    j.put("国际禁毒日", "06月26日");
    j.put("世界青年联欢节", "06月30日");
    j.put("中国共产党诞生日", "07月01日");
    j.put("香港回归日", "07月01日");
    j.put("国际体育记者日", "07月02日");
    j.put("国际接吻日", "07月06日");
    j.put("抗日战争胜利纪念日", "07月07日");
    j.put("小暑", "07月07日");
    j.put("世界人口日", "07月11日");
    j.put("大暑", "07月23日");
    j.put("非洲妇女日", "07月30日");
    j.put("八一建军节", "08月01日");
    j.put("国际电影节", "08月06日");
    j.put("立秋", "08月08日");
    j.put("日本投降日", "08月15日");
    j.put("七夕", "08月20日");
    j.put("处暑", "08月23日");
    j.put("中元节", "08月28日");
    j.put("抗日胜利纪念日", "09月03日");
    j.put("白露", "09月08日");
    j.put("国际扫盲日", "09月08日");
    j.put("教师节", "09月10日");
    j.put("世界清洁地球日", "09月12日");
    j.put("国际臭氧层保护日", "09月12日");
    j.put("国际和平日", "09月16日");
    j.put("九一八事变纪念日", "09月18日");
    j.put("国际爱牙日", "09月20日");
    j.put("世界无车日", "09月22日");
    j.put("秋分", "09月23日");
    j.put("中秋节", "09月27日");
    j.put("世界旅游日", "09月27日");
    j.put("国际聋人节", "09月28日");
    j.put("国庆节", "10月01日");
    j.put("世界动物日", "10月04日");
    j.put("寒露", "10月08日");
    j.put("世界邮政日", "10月09日");
    j.put("辛亥革命纪念日", "10月10日");
    j.put("中国少年先锋队诞辰日", "10月13日");
    j.put("世界标准日", "10月14日");
    j.put("国际盲人节", "10月15日");
    j.put("世界粮食日", "10月16日");
    j.put("国际消除贫困日", "10月17日");
    j.put("重阳节", "10月21日");
    j.put("世界传统医药日", "10月22日");
    j.put("霜降", "10月24日");
    j.put("联合国日", "10月22日");
    j.put("世界勤俭日", "10月31日");
    j.put("万圣节", "10月31日");
    j.put("立冬", "11月08日");
    j.put("中国记者日", "11月08日");
    j.put("世界青年节", "11月10日");
    j.put("光棍节", "11月11日");
    j.put("世界糖尿病日", "11月14日");
    j.put("国际大学生节", "11月17日");
    j.put("世界问候日", "11月21日");
    j.put("小雪", "11月22日");
    j.put("感恩节", "11月26日");
    j.put("世界艾滋病日", "12月01日");
    j.put("国际残疾人日", "12月03日");
    j.put("大雪", "12月07日");
    j.put("一二九运动纪念日", "12月09日");
    j.put("世界足球日", "12月09日");
    j.put("世界人权日", "12月10日");
    j.put("西安事变纪念日", "12月12日");
    j.put("南京大屠杀", "12月13日");
    j.put("澳门回归日", "12月20日");
    j.put("国际篮球日", "12月21日");
    j.put("冬至", "12月22日");
    j.put("平安夜", "12月24日");
    j.put("元旦 ", "01月01日");
    j.put("小寒", "01月05日");
    j.put("腊八节", "01月08日");
    j.put("小年 ", "01月23日");
    j.put("除夕", "01月30日");
    j.put("春节 ", "01月31日");
    j.put("立春", "02月04日");
    j.put("元宵节", "02月14日");
    j.put("雨水", "02月19日");
    j.put("龙抬头", "03月02日");
    j.put("惊蛰", "03月05日");
    j.put("妇女节", "03月08日");
    j.put("植树节", "03月12日");
    j.put("春分", "03月21日");
    j.put("愚人节", "04月01日");
    j.put("清明节", "04月05日");
    j.put("谷雨", "04月20日");
    j.put("劳动节", "05月01日");
    j.put("青年节", "05月04日");
    j.put("立夏", "05月05日");
    j.put("母亲节", "05月11日");
    j.put("小满", "05月21日");
    j.put("六一儿童节", "06月01日");
    j.put("芒种", "06月06日");
    j.put("父亲节", "06月15日");
    j.put("夏至", "06月21日");
    j.put("香港回归", "07月01日");
    j.put("大暑", "07月23日");
    j.put("八一建军节", "08月01日");
    j.put("七夕", "08月02日");
    j.put("立秋", "08月07日");
    j.put("中元节", "08月10日");
    j.put("处暑", "08月23日");
    j.put("教师节", "09月10日");
    j.put("秋分", "09月23日");
    j.put("国庆节", "10月01日");
    j.put("重阳节", "10月02日");
    j.put("寒露", "10月08日");
    j.put("霜降", "10月23日");
    j.put("万圣节", "11月01日");
    j.put("立冬", "11月07日");
    j.put("小雪", "11月22日");
    j.put("大雪", "12月07日");
    j.put("国际民航日", "12月07日");
    j.put("冬至", "12月22日");
    j.put("平安夜", "12月24日");
    j.put("圣诞节", "12月24日");
    k.put("黑人日", "01月04日");
    k.put("国际麻风节", "01月25日");
    k.put("腊八节", "01月27日");
    k.put("世界湿地日", "02月02日");
    k.put("立春", "02月04日");
    k.put("国际声援南非日", "02月07日");
    k.put("国际气象节", "02月10日");
    k.put("小年", "02月11日");
    k.put("除夕", "02月18日");
    k.put("第三世界青年日", "02月24日");
    k.put("世界居住条件调查日", "02月28日");
    k.put("国际海豹日", "03月01日");
    k.put("全国爱耳日", "03月03日");
    k.put("元宵节", "03月05日");
    k.put("学雷锋日", "03月05日");
    k.put("惊蛰", "03月06日");
    k.put("三八妇女节", "03月08日");
    k.put("植树节", "03月12日");
    k.put("白色情人节", "03月14日");
    k.put("国际航海日", "03月17日");
    k.put("世界无肉日", "03月20日");
    k.put("春分", "03月21日");
    k.put("龙抬头", "03月21日");
    k.put("世界睡眠日", "03月21日");
    k.put("世界水日", "03月22日");
    k.put("世界气象日", "03月23日");
    k.put("愚人节", "04月01日");
    k.put("国际儿童图书日", "04月02日");
    k.put("寒食节", "04月04日");
    k.put("世界卫生日", "04月07日");
    k.put("世界帕金森病日", "04月11日");
    k.put("谷雨", "04月20日");
    k.put("世界地球日", "04月22日");
    k.put("世界读书日", "04月23日");
    k.put("知识产权日", "04月26日");
    k.put("全国交通安全反思日", "04月26日");
    k.put("世界儿童日", "04月26日");
    k.put("国际劳动节", "05月01日");
    k.put("五四青年节", "05月04日");
    k.put("立夏", "05月06日");
    k.put("佛诞", "05月25日");
    k.put("世界红十字日", "05月08日");
    k.put("世界微笑日", "05月08日");
    k.put("国际家庭日", "05月15日");
    k.put("世界电信日", "05月17日");
    k.put("全国助残日", "05月17日");
    k.put("国际博物馆日", "05月18日");
    k.put("五卅运动纪念", "05月30日");
    k.put("世界无烟日", "05月31日");
    k.put("六一儿童节", "06月01日");
    k.put("世界环境日", "06月05日");
    k.put("芒种", "06月06日");
    k.put("全国爱眼日", "06月06日");
    k.put("中国人口日", "06月11日");
    k.put("世界难民日", "06月20日");
    k.put("夏至", "06月22日");
    k.put("中国儿童慈善活动日", "06月22日");
    k.put("国际奥林匹克日", "06月23日");
    k.put("全国土地日", "06月25日");
    k.put("国际禁毒日", "06月26日");
    k.put("世界青年联欢节", "06月30日");
    k.put("中国共产党诞生日", "07月01日");
    k.put("香港回归日", "07月01日");
    k.put("国际体育记者日", "07月02日");
    k.put("国际接吻日", "07月06日");
    k.put("抗日战争胜利纪念日", "07月07日");
    k.put("小暑", "07月07日");
    k.put("世界人口日", "07月11日");
    k.put("大暑", "07月23日");
    k.put("非洲妇女日", "07月30日");
    k.put("八一建军节", "08月01日");
    k.put("国际电影节", "08月06日");
    k.put("立秋", "08月08日");
    k.put("日本投降日", "08月15日");
    k.put("七夕", "08月20日");
    k.put("处暑", "08月23日");
    k.put("中元节", "08月28日");
    k.put("抗日胜利纪念日", "09月03日");
    k.put("白露", "09月08日");
    k.put("国际扫盲日", "09月08日");
    k.put("教师节", "09月10日");
    k.put("世界清洁地球日", "09月12日");
    k.put("国际臭氧层保护日", "09月12日");
    k.put("国际和平日", "09月16日");
    k.put("九一八事变纪念日", "09月18日");
    k.put("国际爱牙日", "09月20日");
    k.put("世界无车日", "09月22日");
    k.put("秋分", "09月23日");
    k.put("中秋节", "09月27日");
    k.put("世界旅游日", "09月27日");
    k.put("国际聋人节", "09月28日");
    k.put("国庆节", "10月01日");
    k.put("世界动物日", "10月04日");
    k.put("世界邮政日", "10月09日");
    k.put("辛亥革命纪念日", "10月10日");
    k.put("中国少年先锋队诞辰日", "10月13日");
    k.put("世界标准日", "10月14日");
    k.put("国际盲人节", "10月15日");
    k.put("世界粮食日", "10月16日");
    k.put("国际消除贫困日", "10月17日");
    k.put("世界传统医药日", "10月22日");
    k.put("霜降", "10月24日");
    k.put("联合国日", "10月22日");
    k.put("世界勤俭日", "10月31日");
    k.put("中国记者日", "11月08日");
    k.put("世界青年节", "11月10日");
    k.put("光棍节", "11月11日");
    k.put("世界糖尿病日", "11月14日");
    k.put("国际大学生节", "11月17日");
    k.put("世界问候日", "11月21日");
    k.put("小雪", "11月22日");
    k.put("感恩节", "11月26日");
    k.put("世界艾滋病日", "12月01日");
    k.put("国际残疾人日", "12月03日");
    k.put("大雪", "12月07日");
    k.put("一二九运动纪念日", "12月09日");
    k.put("世界足球日", "12月09日");
    k.put("世界人权日", "12月10日");
    k.put("西安事变纪念日", "12月12日");
    k.put("南京大屠杀", "12月13日");
    k.put("澳门回归日", "12月20日");
    k.put("国际篮球日", "12月21日");
    k.put("元旦", "01月01日");
    k.put("腊八节", "01月17日");
    k.put("祭灶节", "02月01日");
    k.put("除夕", "02月07日");
    k.put("春节", "02月08日");
    k.put("破五", "02月12日");
    k.put("情人节", "02月14日");
    k.put("元宵节", "02月22日");
    k.put("三八妇女节", "03月08日");
    k.put("二月二龙抬头", "03月10日");
    k.put("植树节", "03月12日");
    k.put("消费者权益日", "03月15日");
    k.put("复活节", "03月27日");
    k.put("愚人节", "04月01日");
    k.put("清明", "04月04日");
    k.put("泼水节", "04月13日");
    k.put("三月街", "04月21日");
    k.put("五一劳动节", "05月01日");
    k.put("五四青年节", "05月04日");
    k.put("母亲节", "05月08日");
    k.put("国际护士节", "05月12日");
    k.put("释迦牟尼佛圣诞", "05月14日");
    k.put("佛吉祥日", "05月21日");
    k.put("迁徙节", "05月24日");
    k.put("六一儿童节", "06月01日");
    k.put("端午节", "06月09日");
    k.put("父亲节", "06月19日");
    k.put("建党节", "07月01日");
    k.put("七七卢沟桥事变", "07月07日");
    k.put("八一建军节", "08月01日");
    k.put("七夕", "08月09日");
    k.put("日本投降日", "08月15日");
    k.put("鬼节", "08月17日");
    k.put("教师节", "09月10日");
    k.put("中秋节", "09月15日");
    k.put("九一八事变纪念日", "09月18日");
    k.put("国庆节", "10月01日");
    k.put("重阳节", "10月09日");
    k.put("万圣节前夜", "10月31日");
    k.put("光棍节", "11月11日");
    k.put("下元节", "11月14日");
    k.put("感恩节", "11月24日");
    k.put("世界艾滋病日", "12月1日");
    k.put("南京大屠杀悼念日", "12月13日");
    k.put("平安夜", "12月24日");
    k.put("圣诞节", "12月25日");
    k.put("小寒", "01月05日");
    k.put("小年 ", "01月23日");
    k.put("立春", "02月04日");
    k.put("雨水", "02月19日");
    k.put("龙抬头", "03月02日");
    k.put("惊蛰", "03月05日");
    k.put("妇女节", "03月08日");
    k.put("春分", "03月21日");
    k.put("愚人节", "04月01日");
    k.put("清明节", "04月05日");
    k.put("谷雨", "04月20日");
    k.put("劳动节", "05月01日");
    k.put("青年节", "05月04日");
    k.put("立夏", "05月05日");
    k.put("小满", "05月21日");
    k.put("六一儿童节", "06月01日");
    k.put("夏至", "06月21日");
    k.put("香港回归", "07月01日");
    k.put("大暑", "07月23日");
    k.put("中元节", "08月10日");
    k.put("处暑", "08月23日");
    k.put("教师节", "09月10日");
    k.put("秋分", "09月23日");
    k.put("寒露", "10月08日");
    k.put("霜降", "10月23日");
    k.put("万圣节", "11月01日");
    k.put("立冬", "11月07日");
    k.put("小雪", "11月22日");
    k.put("大雪", "12月07日");
    k.put("国际民航日", "12月07日");
    k.put("冬至", "12月22日");
    a(k, 2016);
    a(i, 2015);
    a(j, 2014);
    a(j);
    a(i);
    a(k);
  }
  
  static int a(String paramString)
  {
    int i1 = 0;
    String str;
    if (!TextUtils.isEmpty(paramString))
    {
      str = paramString.replace("分", "");
      if (!c(str)) {
        break label69;
      }
      if (str.length() >= 2)
      {
        paramString = str.substring(0, 1);
        str = str.substring(1, 2);
        i1 = b(paramString) * 10 + b(str);
      }
    }
    else
    {
      return i1;
    }
    return b(str);
    try
    {
      label69:
      i1 = Integer.parseInt(str);
      return i1;
    }
    catch (NumberFormatException paramString)
    {
      paramString.printStackTrace();
    }
    return 0;
  }
  
  static int a(String paramString, int paramInt1, int paramInt2, long paramLong)
  {
    TedSDKLog.d(a, "parseDayType2 :  " + paramString);
    int i1 = d(paramLong);
    if (TextUtils.isEmpty(paramString)) {
      return i1;
    }
    i1 = e(paramLong);
    int i2 = f(paramLong);
    String str2 = paramString.substring(1, 2);
    String str1 = paramString.substring(paramString.length() - 1);
    paramString = str1;
    if ("末".equals(str1)) {
      paramString = "6";
    }
    str1 = paramString;
    if ("日".equals(paramString)) {
      str1 = "7";
    }
    if (c(str2)) {
      i1 = b(str2);
    }
    for (;;)
    {
      if (c(str1)) {
        i2 = b(str1);
      }
      for (;;)
      {
        paramString = Calendar.getInstance();
        paramString.set(1, paramInt1);
        paramString.set(2, paramInt2);
        paramString.set(4, i1);
        paramString.set(7, i2);
        paramInt1 = paramString.get(5);
        TedSDKLog.d(a, "parseDayType2 return:  " + paramInt1);
        return paramInt1;
        int i3;
        try
        {
          i3 = Integer.parseInt(str2);
          i1 = i3;
        }
        catch (NumberFormatException paramString) {}
        try
        {
          i3 = Integer.parseInt(str1);
          i2 = i3;
        }
        catch (NumberFormatException paramString) {}
      }
    }
  }
  
  static int a(String paramString, long paramLong)
  {
    if (TextUtils.isEmpty(paramString)) {
      return b(paramLong);
    }
    paramString = paramString.trim().replace("-", "").replace("/", "");
    if (("13".equals(paramString)) || ("2013".equals(paramString)) || ("2013年".equals(paramString))) {
      return 2013;
    }
    if (("12".equals(paramString)) || ("2012".equals(paramString)) || ("2012年".equals(paramString))) {
      return 2012;
    }
    if (("11".equals(paramString)) || ("2011".equals(paramString)) || ("2011年".equals(paramString))) {
      return 2011;
    }
    if (("今年".equals(paramString)) || ("15".equals(paramString)) || ("2015".equals(paramString)) || ("2015年".equals(paramString))) {
      return 2015;
    }
    if (("去年".equals(paramString)) || ("14".equals(paramString)) || ("2014".equals(paramString)) || ("2014年".equals(paramString))) {
      return 2014;
    }
    if (("明年".equals(paramString)) || ("16".equals(paramString)) || ("2016".equals(paramString)) || ("2016年".equals(paramString))) {
      return 2016;
    }
    if (("后年".equals(paramString)) || ("17".equals(paramString)) || ("2017".equals(paramString)) || ("2017年".equals(paramString))) {
      return 2017;
    }
    if (("大后年".equals(paramString)) || ("18".equals(paramString)) || ("2018".equals(paramString)) || ("2018年".equals(paramString))) {
      return 2018;
    }
    if ("次年".equals(paramString)) {
      return b(paramLong) + 1;
    }
    return b(paramLong);
  }
  
  static int a(String paramString1, String paramString2)
  {
    TedSDKLog.d(a, "startHourString: " + paramString1);
    paramString1 = paramString1.replace("点", "").replace("时", "");
    int i1 = -1;
    if ("两".equals(paramString1)) {
      i1 = 2;
    }
    for (;;)
    {
      int i2 = i1;
      if (i1 >= 0)
      {
        i2 = i1;
        if (i1 < 12) {
          if (!"下午".equals(paramString2))
          {
            i2 = i1;
            if (!"晚上".equals(paramString2)) {}
          }
          else
          {
            i2 = i1 + 12;
          }
        }
      }
      TedSDKLog.d(a, "hours: " + i2);
      return i2;
      if (c(paramString1)) {
        i1 = b(paramString1);
      } else {
        try
        {
          i2 = Integer.parseInt(paramString1);
          i1 = i2;
        }
        catch (Exception paramString1) {}
      }
    }
  }
  
  static long a(int paramInt1, int paramInt2, int paramInt3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt1).append("年").append(paramInt2).append("月").append(paramInt3).append("日");
    try
    {
      long l1 = g.parse(localStringBuilder.toString()).getTime();
      return l1;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return -1L;
  }
  
  static long a(long paramLong)
  {
    TedSDKLog.d(a, "getCurrentDayByTime: " + paramLong);
    Object localObject = new Date(paramLong);
    localObject = g.format((Date)localObject);
    try
    {
      long l1 = g.parse((String)localObject).getTime();
      TedSDKLog.d(a, "getCurrentDayByTime: " + l1);
      return l1;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return paramLong;
  }
  
  static aqn.a a(String paramString, long paramLong, int paramInt)
  {
    int i1 = 1;
    TedSDKLog.d(a, "parseDay1 :  " + paramString);
    int i2 = d(paramLong);
    aqn.a locala = new aqn.a();
    a = i2;
    if (TextUtils.isEmpty(paramString)) {
      return locala;
    }
    String str = paramString.replace("日", "").replace("号", "");
    if (!paramString.contains("次")) {
      if (c(str)) {
        paramInt = b(str);
      }
    }
    for (;;)
    {
      TedSDKLog.d(a, "parseDateType1 return:  " + paramInt);
      return locala;
      try
      {
        paramInt = Integer.parseInt(str);
        try
        {
          a = paramInt;
        }
        catch (NumberFormatException paramString) {}
      }
      catch (NumberFormatException paramString)
      {
        for (;;)
        {
          paramInt = i2;
        }
        paramInt = i2;
      }
      paramString.printStackTrace();
      continue;
      i2 += 1;
      if ((paramInt > 0) && (paramInt <= 12) && (i2 > e[(paramInt - 1)]))
      {
        a = 1;
        b = true;
        paramInt = i1;
      }
    }
  }
  
  private static void a(Map<String, String> paramMap)
  {
    paramMap.put("元旦节", "01月01日");
    paramMap.put("世界湿地日", "02月02日");
    paramMap.put("国际气象节", "02月10日");
    paramMap.put("情人节", "02月14日");
    paramMap.put("国际海豹日", "03月01日");
    paramMap.put("全国爱耳日", "03月03日");
    paramMap.put("学雷锋纪念日", "03月05日");
    paramMap.put("妇女节", "03月08日");
    paramMap.put("植树节", "03月12日");
    paramMap.put("孙中山逝世纪念日", "03月12日");
    paramMap.put("国际警察日", "03月14日");
    paramMap.put("消费者权益日", "03月15日");
    paramMap.put("中国国医节", "03月17日");
    paramMap.put("国际航海日", "03月17日");
    paramMap.put("世界森林日", "03月21日");
    paramMap.put("消除种族歧视国际日", "03月21日");
    paramMap.put("世界儿歌日", "03月21日");
    paramMap.put("世界水日", "03月22日");
    paramMap.put("世界气象日", "03月23日");
    paramMap.put("世界防治结核病日", "03月24日");
    paramMap.put("全国中小学生安全教育日", "03月25日");
    paramMap.put("巴勒斯坦国土日", "03月30日");
    paramMap.put("愚人节", "04月01日");
    paramMap.put("世界卫生日", "04月07日");
    paramMap.put("世界地球日", "04月22日");
    paramMap.put("世界图书和版权日", "04月23日");
    paramMap.put("亚非新闻工作者日", "04月24日");
    paramMap.put("劳动节", "05月01日");
    paramMap.put("青年节", "05月04日");
    paramMap.put("碘缺乏病防治日", "05月05日");
    paramMap.put("世界红十字日", "05月08日");
    paramMap.put("国际护士节", "05月12日");
    paramMap.put("国际家庭日", "05月15日");
    paramMap.put("世界电信日", "05月17日");
    paramMap.put("国际博物馆日", "05月18日");
    paramMap.put("全国学生营养日", "05月20日");
    paramMap.put("国际生物多样性日", "05月22日");
    paramMap.put("国际牛奶日", "05月23日");
    paramMap.put("世界无烟日", "05月31日");
    paramMap.put("国际儿童节", "06月01日");
    paramMap.put("世界环境日", "06月05日");
    paramMap.put("全国爱眼日", "06月06日");
    paramMap.put("防治荒漠化和干旱日", "06月17日");
    paramMap.put("国际奥林匹克日", "06月23日");
    paramMap.put("全国土地日", "06月25日");
    paramMap.put("国际禁毒日", "06月26日");
    paramMap.put("香港回归纪念日", "07月01日");
    paramMap.put("中共诞辰", "07月01日");
    paramMap.put("世界建筑日", "07月01日");
    paramMap.put("国际体育记者日", "07月02日");
    paramMap.put("抗日战争纪念日", "07月07日");
    paramMap.put("世界人口日", "07月11日");
    paramMap.put("非洲妇女日", "07月30日");
    paramMap.put("建军节", "08月01日");
    paramMap.put("中国男子节", "08月08日");
    paramMap.put("爸爸节", "08月08日");
    paramMap.put("抗日战争胜利纪念", "08月15日");
    paramMap.put("国际扫盲日", "09月08日");
    paramMap.put("国际新闻工作者日", "09月08日");
    paramMap.put("毛泽东逝世纪念", "09月09日");
    paramMap.put("中国教师节", "09月10日");
    paramMap.put("世界清洁地球日", "09月14日");
    paramMap.put("国际臭氧层保护日", "09月16日");
    paramMap.put("九一八事变纪念日", "09月18日");
    paramMap.put("国际爱牙日", "09月20日");
    paramMap.put("世界旅游日", "09月27日");
    paramMap.put("孔子诞辰", "09月28日");
    paramMap.put("国庆节", "10月01日");
    paramMap.put("世界音乐日", "10月01日");
    paramMap.put("国际老人节", "10月01日");
    paramMap.put("国际和平与民主自由斗争日", "10月02日");
    paramMap.put("世界动物日", "10月04日");
    paramMap.put("老人节", "10月06日");
    paramMap.put("全国高血压日", "10月08日");
    paramMap.put("世界视觉日", "10月08日");
    paramMap.put("世界邮政日", "10月09日");
    paramMap.put("万国邮联日", "10月09日");
    paramMap.put("辛亥革命纪念日", "10月10日");
    paramMap.put("世界精神卫生日", "10月10日");
    paramMap.put("世界保健日", "10月13日");
    paramMap.put("国际教师节", "10月13日");
    paramMap.put("世界标准日", "10月14日");
    paramMap.put("国际盲人节", "10月15日");
    paramMap.put("白手杖节", "10月15日");
    paramMap.put("世界粮食日", "10月16日");
    paramMap.put("世界消除贫困日", "10月17日");
    paramMap.put("世界传统医药日", "10月22日");
    paramMap.put("联合国日", "10月24日");
    paramMap.put("世界发展信息日", "10月24日");
    paramMap.put("世界勤俭日", "10月31日");
    paramMap.put("十月社会主义革命纪念日", "11月07日");
    paramMap.put("中国记者日", "11月08日");
    paramMap.put("全国消防安全宣传教育日", "11月09日");
    paramMap.put("世界青年节", "11月10日");
    paramMap.put("孙中山诞辰纪念日", "11月12日");
    paramMap.put("世界糖尿病日", "11月14日");
    paramMap.put("国际大学生节", "11月17日");
    paramMap.put("世界学生节", "11月17日");
    paramMap.put("世界问候日", "11月21日");
    paramMap.put("世界电视日", "11月21日");
    paramMap.put("国际声援巴勒斯坦人民国际日", "11月29日");
    paramMap.put("世界艾滋病日", "12月01日");
    paramMap.put("世界残疾人日", "12月03日");
    paramMap.put("国际经济和社会发展志愿人员日", "12月05日");
    paramMap.put("国际儿童电视日", "12月08日");
    paramMap.put("世界足球日", "12月09日");
    paramMap.put("世界人权日", "12月10日");
    paramMap.put("西安事变纪念日", "12月12日");
    paramMap.put("南京大屠杀纪念日", "12月13日");
    paramMap.put("澳门回归纪念", "12月20日");
    paramMap.put("国际篮球日", "12月21日");
    paramMap.put("平安夜", "12月24日");
    paramMap.put("圣诞节", "12月25日");
    paramMap.put("毛泽东诞辰纪念", "12月26日");
  }
  
  private static void a(Map<String, String> paramMap, int paramInt)
  {
    try
    {
      Date localDate = h.parse("2014年1月5日 18:24");
      int i1 = 0;
      for (;;)
      {
        if (i1 >= f.length) {
          return;
        }
        Object localObject1 = new Date(((paramInt - 2014) * 3.15569254E10F + (float)(f[i1] * 60000L) + (float)localDate.getTime()));
        Object localObject2 = Calendar.getInstance();
        ((Calendar)localObject2).setTime((Date)localObject1);
        int i2 = ((Calendar)localObject2).get(2) + 1;
        int i3 = ((Calendar)localObject2).get(5);
        String str = i2;
        localObject2 = i3;
        localObject1 = str;
        if (i2 < 10) {
          localObject1 = "0" + str;
        }
        if (i3 < 10) {
          localObject2 = "0" + i3;
        }
        localObject1 = localObject1 + "月" + (String)localObject2 + "日";
        paramMap.put(d[i1], localObject1);
        i1 += 1;
      }
      return;
    }
    catch (ParseException paramMap)
    {
      paramMap.printStackTrace();
    }
  }
  
  static int b(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance(Locale.CHINA);
    localCalendar.setTime(new Date(paramLong));
    return localCalendar.get(1);
  }
  
  static int b(String paramString)
  {
    int i1 = 0;
    for (;;)
    {
      int i2;
      if (i1 >= c.length) {
        i2 = 1;
      }
      do
      {
        return i2;
        i2 = i1;
      } while (c[i1].equals(paramString));
      i1 += 1;
    }
  }
  
  static aqn.a b(String paramString, long paramLong)
  {
    int i1 = 1;
    TedSDKLog.d(a, "parseMonthString :  " + paramString);
    aqn.a locala = new aqn.a();
    int i2 = c(paramLong);
    a = i2;
    if (TextUtils.isEmpty(paramString)) {
      return locala;
    }
    paramString = paramString.replace("月", "").replace("-", "").replace("/", "").trim();
    if ((!paramString.contains("次")) && (!paramString.contains("下"))) {
      if (c(paramString)) {
        i1 = b(paramString);
      }
    }
    for (;;)
    {
      TedSDKLog.d(a, "parseMonthString return:  " + i1);
      return locala;
      try
      {
        i1 = Integer.parseInt(paramString);
        try
        {
          a = i1;
        }
        catch (NumberFormatException paramString) {}
      }
      catch (NumberFormatException paramString)
      {
        for (;;)
        {
          i1 = i2;
        }
        i1 = i2;
      }
      paramString.printStackTrace();
      continue;
      i2 += 1;
      if (i2 > 12)
      {
        a = 1;
        b = true;
      }
    }
  }
  
  static int c(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance(Locale.CHINA);
    localCalendar.setTime(new Date(paramLong));
    return localCalendar.get(2) + 1;
  }
  
  static long c(String paramString, long paramLong)
  {
    TedSDKLog.d(a, "parseDateType3 :  " + paramString);
    long l1 = a(paramLong);
    if (("明天".equals(paramString)) || ("明日".equals(paramString))) {
      paramLong = l1 + 86400000L;
    }
    do
    {
      return paramLong;
      if (("后天".equals(paramString)) || ("后日".equals(paramString))) {
        return l1 + 172800000L;
      }
      if (("大后天".equals(paramString)) || ("大后日".equals(paramString))) {
        return l1 + 259200000L;
      }
      paramLong = l1;
    } while (!"昨天".equals(paramString));
    return l1 - 86400000L;
  }
  
  static boolean c(String paramString)
  {
    return b.matcher(paramString).find();
  }
  
  static int d(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance(Locale.CHINA);
    localCalendar.setTime(new Date(paramLong));
    return localCalendar.get(5);
  }
  
  static long d(String paramString, long paramLong)
  {
    TedSDKLog.d(a, "parseDateType4 :  " + paramString);
    if (TextUtils.isEmpty(paramString)) {
      return a(paramLong);
    }
    String str = paramString.substring(paramString.length() - 1);
    int i2 = f(paramLong);
    paramString = str;
    if ("末".equals(str)) {
      paramString = "6";
    }
    str = paramString;
    if ("日".equals(paramString)) {
      str = "7";
    }
    int i1;
    if (c(str)) {
      i1 = b(str);
    }
    for (;;)
    {
      i2 = i1 - i2 + 1;
      i1 = i2;
      if (i2 < 0) {
        i1 = i2 + 7;
      }
      TedSDKLog.d(a, "parseDateType4 Offset :" + i1);
      paramLong = a(paramLong);
      return (i1 + 1) * 86400000L + paramLong;
      try
      {
        i1 = Integer.parseInt(str);
      }
      catch (NumberFormatException paramString)
      {
        i1 = i2;
      }
    }
  }
  
  static int e(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance(Locale.CHINA);
    localCalendar.setTime(new Date(paramLong));
    return localCalendar.get(4) + 1;
  }
  
  static int f(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance(Locale.CHINA);
    localCalendar.setTime(new Date(paramLong));
    return localCalendar.get(7) + 1;
  }
  
  static class a
  {
    int a;
    boolean b = false;
  }
}

/* Location:
 * Qualified Name:     aqn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */