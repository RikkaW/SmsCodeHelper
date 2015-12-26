package com.ted.sdk.yellow.util;

import android.text.TextUtils;

public class ProvinceUtils
{
  public static final String[] ALL = { "北京", "天津", "重庆", "辽宁", "陕西", "河北", "山西", "河南", "吉林", "黑龙江", "内蒙古", "山东", "浙江", "福建", "湖南", "广东", "广西", "江西", "云南", "西藏", "甘肃", "宁夏", "新疆", "江苏", "四川", "安徽", "贵州", "青海", "海南", "湖北", "上海" };
  public static final String[][] CITIES;
  private static int INDEX = 0;
  public static final String PROVINCE_AH;
  public static final String PROVINCE_BJ;
  public static final String PROVINCE_COUNTRY = "全国";
  public static final String PROVINCE_CQ;
  public static final String PROVINCE_FJ;
  public static final String PROVINCE_GD;
  public static final String PROVINCE_GS;
  public static final String PROVINCE_GX;
  public static final String PROVINCE_GZ;
  public static final String PROVINCE_HA;
  public static final String PROVINCE_HB;
  public static final String PROVINCE_HE;
  public static final String PROVINCE_HLJ;
  public static final String PROVINCE_HN;
  public static final String PROVINCE_HU;
  public static final String PROVINCE_JL;
  public static final String PROVINCE_JS;
  public static final String PROVINCE_JX;
  public static final String PROVINCE_LN;
  public static final String PROVINCE_NM;
  public static final String PROVINCE_NX;
  public static final String PROVINCE_QH;
  public static final String PROVINCE_SA;
  public static final String PROVINCE_SC;
  public static final String PROVINCE_SD;
  public static final String PROVINCE_SH;
  public static final String PROVINCE_SX;
  public static final String PROVINCE_TJ;
  public static final String PROVINCE_XJ;
  public static final String PROVINCE_XZ;
  public static final String PROVINCE_YN;
  public static final String PROVINCE_ZJ;
  public static final int UNAVAILABLE_INDEX = -1;
  
  static
  {
    String[] arrayOfString1 = ALL;
    int i = INDEX;
    INDEX = i + 1;
    PROVINCE_BJ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_TJ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_CQ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_LN = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_SA = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_HE = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_SX = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_HN = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_JL = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_HLJ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_NM = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_SD = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_ZJ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_FJ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_HU = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_GD = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_GX = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_JX = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_YN = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_XZ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_GS = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_NX = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_XJ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_JS = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_SC = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_AH = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_GZ = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_QH = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_HA = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_HB = arrayOfString1[i];
    arrayOfString1 = ALL;
    i = INDEX;
    INDEX = i + 1;
    PROVINCE_SH = arrayOfString1[i];
    arrayOfString1 = new String[] { "北京" };
    String[] arrayOfString2 = { "天津" };
    String[] arrayOfString3 = { "重庆", "万州", "涪陵", "黔江" };
    String[] arrayOfString4 = { "丹东", "大连", "抚顺", "朝阳", "本溪", "沈阳", "盘锦", "营口", "葫芦岛", "辽阳", "铁岭", "锦州", "阜新", "鞍山" };
    String[] arrayOfString5 = { "咸阳", "商洛", "安康", "宝鸡", "延安", "榆林", "汉中", "渭南", "西安", "铜川" };
    String[] arrayOfString6 = { "保定", "唐山", "廊坊", "张家口", "承德", "沧州", "石家庄", "秦皇岛", "衡水", "邢台", "邯郸" };
    String[] arrayOfString7 = { "临汾", "吕梁", "大同", "太原", "忻州", "晋中", "晋城", "朔州", "运城", "长治", "阳泉" };
    String[] arrayOfString8 = { "三门峡", "信阳", "南阳", "周口", "商丘", "安阳", "平顶山", "开封", "新乡", "洛阳", "漯河", "濮阳", "焦作", "许昌", "郑州", "驻马店", "鹤壁", "潢川", "焦作", "济源" };
    String[] arrayOfString9 = { "吉林", "四平", "松原", "通化", "白城", "通化", "长春", "延边", "延边", "白山", "辽源", "延边" };
    String[] arrayOfString10 = { "七台河", "伊春", "佳木斯", "双鸭山", "哈尔滨", "大兴安岭", "大庆", "牡丹江", "绥化", "鸡西", "鹤岗", "黑河", "齐齐哈尔" };
    String[] arrayOfString11 = { "巴彦淖尔", "乌兰浩特", "包头", "呼和浩特", "呼伦贝尔", "赤峰", "通辽", "鄂尔多斯", "锡林郭勒", "乌兰察布", "乌海", "阿拉善", "阿拉善", "兴安", "锡林郭勒", "巴彦淖尔", "乌兰察布", "呼伦贝尔", "锡林郭勒" };
    String[] arrayOfString12 = { "东营", "临沂", "威海", "德州", "日照", "枣庄", "泰安", "济南", "济宁", "淄博", "滨州", "潍坊", "烟台", "聊城", "莱芜", "菏泽", "青岛" };
    String[] arrayOfString13 = { "丽水", "台州", "嘉兴", "宁波", "杭州", "温州", "湖州", "绍兴", "舟山", "衢州", "金华" };
    String[] arrayOfString14 = { "三明", "南平", "厦门", "宁德", "泉州", "漳州", "福州", "莆田", "龙岩" };
    String[] arrayOfString15 = { "湘西", "娄底", "岳阳", "常德", "张家界", "怀化", "株洲", "永州", "湘潭", "益阳", "衡阳", "邵阳", "郴州", "长沙", "湘西" };
    String[] arrayOfString16 = { "南宁", "柳州", "桂林", "梧州", "河池", "玉林", "百色", "贵港", "贺州", "钦州", "防城港", "北海", "崇左", "来宾" };
    String[] arrayOfString17 = { "上饶", "九江", "南昌", "吉安", "宜春", "抚州", "新余", "景德镇", "萍乡", "赣州", "鹰潭" };
    String[] arrayOfString18 = { "临沧", "保山", "大理", "普洱", "文山", "昭通", "曲靖", "红河", "丽江", "德宏", "怒江", "昆明", "楚雄", "西双版纳", "玉溪", "迪庆", "普洱" };
    String[] arrayOfString19 = { "拉萨", "日喀则", "昌都", "林芝", "那曲", "山南", "阿里" };
    String[] arrayOfString20 = { "临夏", "兰州", "天水", "定西", "平凉", "庆阳", "张掖", "甘南", "白银", "酒泉", "金昌", "陇南", "嘉峪关", "武威", "酒泉", "金昌" };
    String[] arrayOfString21 = { "中卫", "吴忠", "固原", "石嘴山", "银川" };
    String[] arrayOfString22 = { "乌鲁木齐", "伊犁", "克州", "克拉玛依", "博尔塔拉", "吐鲁番", "和田", "哈密", "喀什", "塔城", "奎屯", "巴音郭楞", "昌吉", "石河子", "阿克苏", "阿勒泰", "巴音郭楞" };
    String[] arrayOfString23 = { "南京", "南通", "宿迁", "常州", "徐州", "扬州", "无锡", "泰州", "淮安", "盐城", "苏州", "连云港", "镇江" };
    String[] arrayOfString24 = { "乐山", "内江", "凉山", "南充", "宜宾", "巴中", "广元", "广安", "德阳", "成都", "攀枝花", "泸州", "甘孜", "眉山", "绵阳", "自贡", "资阳", "达州", "遂宁", "阿坝", "雅安" };
    String[] arrayOfString25 = { "亳州", "六安", "合肥", "安庆", "宣城", "宿州", "池州", "淮北", "淮南", "滁州", "芜湖", "蚌埠", "铜陵", "阜阳", "马鞍山", "黄山", "巢湖" };
    String[] arrayOfString26 = { "六盘水", "黔西南", "黔东南", "安顺", "毕节", "贵阳", "遵义", "黔南", "铜仁", "黔东南", "黔南", "黔西南" };
    String[] arrayOfString27 = { "海口", "三亚", "儋州" };
    String[] arrayOfString28 = { "上海" };
    CITIES = new String[][] { arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, arrayOfString8, arrayOfString9, arrayOfString10, arrayOfString11, arrayOfString12, arrayOfString13, arrayOfString14, arrayOfString15, { "东莞", "中山", "云浮", "佛山", "广州", "惠州", "揭阳", "梅州", "汕头", "汕尾", "江门", "河源", "深圳", "清远", "湛江", "潮州", "珠海", "肇庆", "茂名", "阳江", "韶关" }, arrayOfString16, arrayOfString17, arrayOfString18, arrayOfString19, arrayOfString20, arrayOfString21, arrayOfString22, arrayOfString23, arrayOfString24, arrayOfString25, arrayOfString26, { "格尔木", "海东", "西宁", "共和", "德令哈", "果洛", "海北", "玉树", "黄南", "海西", "海南", "海北" }, arrayOfString27, { "十堰", "咸宁", "孝感", "宜昌", "恩施", "武汉", "江汉", "荆州", "荆门", "襄阳", "鄂州", "随州", "黄冈", "黄石", "仙桃" }, arrayOfString28 };
  }
  
  public static String formatProvince(String paramString)
  {
    Object localObject;
    if (TextUtils.isEmpty(paramString))
    {
      localObject = "全国";
      return (String)localObject;
    }
    String[] arrayOfString = ALL;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      if (i >= j)
      {
        if (!PROVINCE_NM.contains(paramString)) {
          break label67;
        }
        return PROVINCE_NM;
      }
      String str = arrayOfString[i];
      localObject = str;
      if (paramString.contains(str)) {
        break;
      }
      i += 1;
    }
    label67:
    return "全国";
  }
  
  public static int getProCityIdx(String paramString1, String paramString2)
  {
    int m = 0;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      return -1;
    }
    paramString1 = paramString1.replace("省", "").replace("市", "");
    int i = 0;
    int k;
    for (;;)
    {
      if (i >= ALL.length) {}
      for (k = -1;; k = i)
      {
        if (k != -1) {
          break label85;
        }
        return -1;
        if (!ALL[i].equals(paramString1)) {
          break;
        }
      }
      i += 1;
    }
    label85:
    int j = 0;
    if (j >= CITIES[k].length)
    {
      i = -1;
      label100:
      if (i == -1) {
        j = m;
      }
    }
    for (;;)
    {
      if (j >= CITIES[k].length) {
        m = i;
      }
      do
      {
        if (m != -1) {
          break label179;
        }
        return -1;
        i = j;
        if (CITIES[k][j].equals(paramString2)) {
          break label100;
        }
        j += 1;
        break;
        m = j;
      } while (CITIES[k][j].contains(paramString2));
      j += 1;
    }
    label179:
    return (k << 5) + m;
  }
}

/* Location:
 * Qualified Name:     com.ted.sdk.yellow.util.ProvinceUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */