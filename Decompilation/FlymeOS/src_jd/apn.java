public class apn
{
  public static final String[] a = { "无标记", "广告推销", "保险销售", "疑似诈骗", "骚扰电话", "房产中介", "快递服务", "外卖送餐", "出租司机", "招聘猎头", "金融理财" };
  
  public static int a(String paramString)
  {
    if ("无标记".equals(paramString)) {
      return -1;
    }
    if (("疑似诈骗".equals(paramString)) || ("骚扰电话".equals(paramString))) {
      return 0;
    }
    if (("广告推销".equals(paramString)) || ("房产中介".equals(paramString)) || ("保险销售".equals(paramString))) {
      return 1;
    }
    if (("快递服务".equals(paramString)) || ("外卖送餐".equals(paramString)) || ("招聘猎头".equals(paramString)) || ("出租司机".equals(paramString)) || ("金融理财".equals(paramString))) {
      return 2;
    }
    return Integer.MIN_VALUE;
  }
}

/* Location:
 * Qualified Name:     apn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */