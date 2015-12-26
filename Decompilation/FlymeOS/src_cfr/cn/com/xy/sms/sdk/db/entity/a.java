/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.db.entity;

public final class a {
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

