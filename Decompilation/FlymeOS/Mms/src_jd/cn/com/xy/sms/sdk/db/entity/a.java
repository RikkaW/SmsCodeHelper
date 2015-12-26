package cn.com.xy.sms.sdk.db.entity;

public final class a
{
  private static String h = "tb_centernum_location_info";
  private static String i = " DROP TABLE IF EXISTS tb_centernum_location_info";
  private static String j = " create table  if not exists tb_centernum_location_info ( id INTEGER PRIMARY KEY, cnum TEXT not null unique, areaCode TEXT, city TEXT, checkTime long, operator TEXT )";
  public int a;
  public String b;
  public String c;
  public String d;
  public String e;
  public String f;
  public long g;
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */