/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.db.entity;

public class MatchCache {
    public String bubble_result;
    public String card_result;
    public String extend;
    public long id;
    public String msg_id;
    public String msg_num_md5;
    public String phonenum;
    public String popup_window_result;
    public long save_time;
    public String scene_id;
    public String session_reuslt;

    public MatchCache() {
    }

    public MatchCache(String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10) {
        this.msg_num_md5 = string2;
        this.phonenum = string3;
        this.msg_id = string4;
        this.scene_id = string5;
        this.popup_window_result = string6;
        this.bubble_result = string7;
        this.session_reuslt = string8;
        this.card_result = string9;
        this.extend = string10;
    }
}

