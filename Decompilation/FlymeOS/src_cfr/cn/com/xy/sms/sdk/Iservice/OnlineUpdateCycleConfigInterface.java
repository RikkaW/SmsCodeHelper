/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package cn.com.xy.sms.sdk.Iservice;

public interface OnlineUpdateCycleConfigInterface {
    public static final int TYPE_BATCH_PUBLIC_NUM_INFO_UPDATE_CYCLE = 1;
    public static final int TYPE_CENTER_NUM_LOCATION_INFO_CHECK_CYCLE = 0;
    public static final int TYPE_CHECK_INIT_CYCLE = 7;
    public static final int TYPE_EXIST_PUBLIC_NUM_INFO_UPDATE_CYCLE = 2;
    public static final int TYPE_FIND_MENU_LIST_CYCLE = 5;
    public static final int TYPE_FIRST_POST_ICCID_SCENE_COUNT_CYCLE = 12;
    public static final int TYPE_JAR_FILE_UPDATE_CYCLE = 8;
    public static final int TYPE_JAR_UPDATE_CYCLE = 6;
    public static final int TYPE_LAST_LOAD_TIME_ADD = 18;
    public static final int TYPE_LOGO_RESOURSE_UPDATE_CYCLE = 9;
    public static final int TYPE_PARSE_FAIL_REPARSE_CYCLE = 23;
    public static final int TYPE_POST_ICCID_SCENE_COUNT_CYCLE = 13;
    public static final int TYPE_PUBLIC_NUM_INFO_UPDATE_CYCLE = 3;
    public static final int TYPE_QUERY_PUB_INFO_CYCLE = 24;
    public static final int TYPE_REDOWNLOAD_LOGO_INTERVAL = 19;
    public static final int TYPE_REDOWNLOAD_RESOURSE_CYCLE = 17;
    public static final int TYPE_RELOAD_ICCID_LOCATE_CYCLE = 16;
    public static final int TYPE_REPARSE_BUBBLE_CYCLE = 14;
    public static final int TYPE_REPARSE_CYCLE = 22;
    public static final int TYPE_REPARSE_SIMPLE_CYCLE = 15;
    public static final int TYPE_REQUERY_ICCID_INFO_CYCLE = 4;
    public static final int TYPE_RUN_RESOURSE_QUEUE_CYCLE = 20;
    public static final int TYPE_SCENE_CONFIG_UPDATE_CYCLE = 10;
    public static final int TYPE_SCENE_RULE_UPDATE_CYCLE = 11;
    public static final int TYPE_TRAIN_DATA_VALID_CYCLE = 21;

    public long getUpdateCycle(int var1, long var2);
}

