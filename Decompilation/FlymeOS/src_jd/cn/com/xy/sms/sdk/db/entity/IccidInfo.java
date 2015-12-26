package cn.com.xy.sms.sdk.db.entity;

public class IccidInfo
{
  public String areaCode;
  public String city = "";
  public String cnum;
  public String iccid;
  public int isPost;
  public long netUpdateTime;
  public String num;
  public String operator = "";
  public String provinces;
  public int simIndex;
  public long updateTime;
  public String userAreacode;
  public String userOperator;
  public String userProvinces;
  
  public String toString()
  {
    return "IccidInfo [iccid=" + iccid + ", city=" + city + ", provinces=" + provinces + ", cnum=" + cnum + ", num=" + num + ", isPost=" + isPost + ", netUpdateTime=" + netUpdateTime + ", areaCode=" + areaCode + ", operator=" + operator + ", updateTime=" + updateTime + ", userProvinces=" + userProvinces + ", userAreacode=" + userAreacode + ", userOperator=" + userOperator + ", simIndex=" + simIndex + "]";
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.entity.IccidInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */