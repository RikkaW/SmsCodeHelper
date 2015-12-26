/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.db.entity;

public class IccidInfo {
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

    public String toString() {
        return "IccidInfo [iccid=" + this.iccid + ", city=" + this.city + ", provinces=" + this.provinces + ", cnum=" + this.cnum + ", num=" + this.num + ", isPost=" + this.isPost + ", netUpdateTime=" + this.netUpdateTime + ", areaCode=" + this.areaCode + ", operator=" + this.operator + ", updateTime=" + this.updateTime + ", userProvinces=" + this.userProvinces + ", userAreacode=" + this.userAreacode + ", userOperator=" + this.userOperator + ", simIndex=" + this.simIndex + "]";
    }
}

