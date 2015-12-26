package cn.com.xy.sms.sdk.b;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.SceneconfigUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.x;
import java.util.HashMap;

public final class a
{
  private static String a = "<titles><title_01001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_430b2237f18c4c793d4e025abe5dcd4b.png</v_hd_del><v_hd_close>1_01dfb9f94b6ab802656e16619b0e0e6f.png</v_hd_close><v_hd_bg>#2e94e1</v_hd_bg><v_bt_by_sp_bg>#2e93e1</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_lt_bg>#FFFFFF</v_lt_bg><v_lt_stk>#9db6ca</v_lt_stk></popu></popus></title_01001001></titles>";
  private static String b = "<titles><title_01001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_2c6aa6ee55d09f274f312f9701982947.png</v_hd_del><v_hd_close>1_4a43b040c5ce163d5db96bdde9724fd5.png</v_hd_close><v_hd_bg>#2e94e1</v_hd_bg><v_bt_by_sp_bg>#2e93e1</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_lt_bg>#FFFFFF</v_lt_bg><v_lt_stk>#9db6ca</v_lt_stk></popu></popus></title_01001001></titles>";
  private static String c = "<titles><title_01001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_d19e64f3c2c90fc61ad6eb8b0a214aff.png</v_hd_del><v_hd_close>1_6099165f4f4f01d911252818cc0e851c.png</v_hd_close><v_hd_bg>S#0eabaa;E#0C9DBD</v_hd_bg><v_bt_by_sp_bg>SW2;S#0eabaa;E#0C9DBD</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_lt_bg>#FFFFFF</v_lt_bg><v_lt_stk>#9db6ca</v_lt_stk></popu></popus></title_01001001></titles>";
  private static String d = "<titles><title_01001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_6aba2f9cf8a52365e40c404ecc89e52d.png</v_hd_del><v_hd_close>1_ec3910a4ef4a64e2f8c78c9a80eef68d.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_hd_sp_bg>#307DD5</v_hd_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_lt_bg>#FFFFFF</v_lt_bg><v_lt_stk>#9db6ca</v_lt_stk></popu></popus></title_01001001></titles>";
  private static String e = "<titles><title_01001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_6aba2f9cf8a52365e40c404ecc89e52d.png</v_hd_del><v_hd_close>1_ec3910a4ef4a64e2f8c78c9a80eef68d.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_hd_sp_bg>#307DD5</v_hd_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_lt_bg>#FFFFFF</v_lt_bg><v_lt_stk>#9db6ca</v_lt_stk></popu></popus></title_01001001></titles>";
  private static String f = "<titles><title_01001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_card_bg>#F0F0F0</v_by_card_bg><v_lt_stk>#9fb5ca</v_lt_stk><v_by_bg>#f0f0f0</v_by_bg><v_hd_close>F40A881C456538D330ED67AFA042134A.png</v_hd_close><v_hd_bg>TL3;TR3;S#f0f0f0</v_hd_bg><v_bt_bg>#fafafa</v_bt_bg></popu></popus></title_01001001></titles>";
  
  /* Error */
  public static HashMap<String, String> a(String paramString)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 33	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   7: istore_1
    //   8: iload_1
    //   9: ifeq +10 -> 19
    //   12: aconst_null
    //   13: astore_0
    //   14: ldc 2
    //   16: monitorexit
    //   17: aload_0
    //   18: areturn
    //   19: invokestatic 39	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   22: iconst_1
    //   23: if_icmpne +17 -> 40
    //   26: ldc 14
    //   28: ldc 41
    //   30: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   33: invokestatic 50	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;)Ljava/util/HashMap;
    //   36: astore_0
    //   37: goto -23 -> 14
    //   40: invokestatic 39	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   43: iconst_2
    //   44: if_icmpne +17 -> 61
    //   47: ldc 17
    //   49: ldc 41
    //   51: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   54: invokestatic 50	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;)Ljava/util/HashMap;
    //   57: astore_0
    //   58: goto -44 -> 14
    //   61: invokestatic 39	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   64: iconst_5
    //   65: if_icmpne +17 -> 82
    //   68: ldc 21
    //   70: ldc 41
    //   72: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   75: invokestatic 50	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;)Ljava/util/HashMap;
    //   78: astore_0
    //   79: goto -65 -> 14
    //   82: invokestatic 39	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   85: bipush 8
    //   87: if_icmpne +17 -> 104
    //   90: ldc 17
    //   92: ldc 41
    //   94: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   97: invokestatic 50	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;)Ljava/util/HashMap;
    //   100: astore_0
    //   101: goto -87 -> 14
    //   104: invokestatic 39	cn/com/xy/sms/sdk/ui/popu/util/ViewUtil:getChannelType	()I
    //   107: iconst_3
    //   108: if_icmpne +17 -> 125
    //   111: ldc 8
    //   113: ldc 41
    //   115: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   118: invokestatic 50	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;)Ljava/util/HashMap;
    //   121: astore_0
    //   122: goto -108 -> 14
    //   125: ldc 11
    //   127: ldc 41
    //   129: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   132: invokestatic 50	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;)Ljava/util/HashMap;
    //   135: astore_0
    //   136: goto -122 -> 14
    //   139: astore_0
    //   140: ldc 2
    //   142: monitorexit
    //   143: aload_0
    //   144: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	145	0	paramString	String
    //   7	2	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	8	139	finally
    //   19	37	139	finally
    //   40	58	139	finally
    //   61	79	139	finally
    //   82	101	139	finally
    //   104	122	139	finally
    //   125	136	139	finally
  }
  
  /* Error */
  public static HashMap<String, String> a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 33	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   7: istore_2
    //   8: iload_2
    //   9: ifeq +10 -> 19
    //   12: aconst_null
    //   13: astore_0
    //   14: ldc 2
    //   16: monitorexit
    //   17: aload_0
    //   18: areturn
    //   19: aload_1
    //   20: ldc 41
    //   22: invokestatic 45	cn/com/xy/sms/sdk/util/StringUtils:stringConvertXML	(Ljava/lang/String;Ljava/lang/String;)Lorg/w3c/dom/Document;
    //   25: aload_0
    //   26: invokestatic 56	cn/com/xy/sms/sdk/util/x:a	(Lorg/w3c/dom/Document;Ljava/lang/String;)Ljava/util/HashMap;
    //   29: astore_0
    //   30: goto -16 -> 14
    //   33: astore_0
    //   34: ldc 2
    //   36: monitorexit
    //   37: aload_0
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	paramString1	String
    //   0	39	1	paramString2	String
    //   7	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   3	8	33	finally
    //   19	30	33	finally
  }
  
  public static HashMap<String, String> b(String paramString)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        boolean bool = StringUtils.isNull(paramString);
        if (bool) {
          return (HashMap<String, String>)localObject1;
        }
        localObject1 = localObject2;
        if (paramString.startsWith("01003"))
        {
          localObject1 = "<titles><title_01003004><popus><popu><layoutName>duoqu_popup</layoutName><v_cpy_logo>bank_logo.jpg</v_cpy_logo><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_d8ead49e9e8f07cf80c7f36cbc7c05dc.png</v_hd_del><v_hd_close>1_e3b50ad6b620985565cb9804a96ffe96.png</v_hd_close><v_bt_by_sp_bg>#2e93e1</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_hd_bg_one>#385DAB</v_hd_bg_one><v_hd_bg_two>#FFFFFF</v_hd_bg_two><v_hd_bg_thr>#16357C</v_hd_bg_thr><v_hd_bg_for>#457FC6</v_hd_bg_for></popu></popus></title_01003004></titles>";
          if ("NQIDAQABCOOL".equals(KeyManager.channel)) {
            localObject1 = "<titles><title_01003004><popus><popu><layoutName>duoqu_popup</layoutName><v_cpy_logo>bank_logo.jpg</v_cpy_logo><v_by_bg>#ffffff</v_by_bg><v_hd_del>1_d8ead49e9e8f07cf80c7f36cbc7c05dc.png</v_hd_del><v_hd_close>1_e3b50ad6b620985565cb9804a96ffe96.png</v_hd_close><v_bt_by_sp_bg>SW2;S#457dc5;E#2663b2</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_hd_bg_one>TL8;TR0;S#457dc5</v_hd_bg_one><v_hd_bg_two>TL0;TR8;S#FFFFFF</v_hd_bg_two><v_hd_bg_thr>#034aa6</v_hd_bg_thr><v_hd_bg_for>S#457dc5;E#2663b2</v_hd_bg_for></popu></popus></title_01003004></titles>";
          }
          localObject1 = x.a(StringUtils.stringConvertXML((String)localObject1, ""));
        }
        if (paramString.startsWith("02023002"))
        {
          localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02023><popus><popu><thunder_v_hd_bg>TL8;TR8;S#FA5F00</thunder_v_hd_bg><sunny_v_hd_bg>TL8;TR8;S#FBB900</sunny_v_hd_bg><v_by_bt_bg>#e2e2e2</v_by_bt_bg><v_hd_sp_bg>#00a9e1</v_hd_sp_bg><v_hd_bg>TL8;TR8;S#FFFFFF</v_hd_bg><v_hd_close>close.png</v_hd_close><v_l_bg>#4dc2eb</v_l_bg><img_sunny>sunny.png</img_sunny><img_thunder>thunder.png</img_thunder><img_rain>rain.png</img_rain><img_cloud>cloud.png</img_cloud><img_cloudy>cloudy.png</img_cloudy><img_wind>wind.png</img_wind><img_snow>snow.png</img_snow><thunder_v_by_bg>#FA5F00</thunder_v_by_bg><sunny_v_by_bg>#FBB900</sunny_v_by_bg><thunder_v_by_bt_bg>#FFFFFF</thunder_v_by_bt_bg><sunny_v_by_bt_bg>#FFFFFF</sunny_v_by_bt_bg><thunder_v_l_bg>#FFFFFF</thunder_v_l_bg><sunny_v_l_bg>#FFFFFF</sunny_v_l_bg><duoqu_drop_th>duoqu_drop_th.png</duoqu_drop_th><duoqu_drop_sunny>duoqu_drop_sunny.png</duoqu_drop_sunny><duoqu_drop_def>duoqu_drop_def.png</duoqu_drop_def><duoqu_back_orage>duoqu_back_orage.png</duoqu_back_orage><duoqu_back_yellow>duoqu_back_yellow.png</duoqu_back_yellow><duoqu_back_blue></duoqu_back_blue><duoqu_more_orage>duoqu_more_orage.png</duoqu_more_orage><duoqu_more_yellow>duoqu_more_yellow.png</duoqu_more_yellow><duoqu_more_blue>duoqu_more_blue.png</duoqu_more_blue><v_tc_def>#00a9e2</v_tc_def><v_tc_th>#fa6000</v_tc_th><v_tc_sunny>#fbba00</v_tc_sunny><v_hd_sp_bg_th>#fa6000</v_hd_sp_bg_th><v_hd_sp_bg_def>#00a9e2</v_hd_sp_bg_def><v_hd_sp_bg_sunny>#fbba00</v_hd_sp_bg_sunny></popu></popus></title_02023></titles>", ""));
          LogManager.e("businessTitle", String.valueOf(localObject1), null);
          continue;
        }
        if (!paramString.startsWith("02025002")) {
          break label121;
        }
      }
      finally {}
      localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02010002><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_del>1_6aba2f9cf8a52365e40c404ecc89e52d.png</v_hd_del><v_hd_close>1_e3b50ad6b620985565cb9804a96ffe96.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_bt_bg>#FFFFFF</v_bt_bg><v_by_bt_bg>#1c81d1</v_by_bt_bg><duoqu_content_lan>#FFFFFF</duoqu_content_lan><duoqu_yujing_cheng>a2.png</duoqu_yujing_cheng><duoqu_yujing_hong>a4.png</duoqu_yujing_hong><duoqu_yujing_huang>a3.png</duoqu_yujing_huang><duoqu_yujing_lan>a1.png</duoqu_yujing_lan><duoqu_dian_cheng>lei.png</duoqu_dian_cheng><duoqu_dian_hong>lei.png</duoqu_dian_hong><duoqu_dian_huang>lei.png</duoqu_dian_huang><duoqu_dian_lan>lei.png</duoqu_dian_lan><duoqu_feng_cheng>feng.png</duoqu_feng_cheng><duoqu_feng_hong>feng.png</duoqu_feng_hong><duoqu_feng_huang>feng.png</duoqu_feng_huang><duoqu_feng_lan>feng.png</duoqu_feng_lan><duoqu_gao_cheng>gao.png</duoqu_gao_cheng><duoqu_gao_hong>gao.png</duoqu_gao_hong><duoqu_gao_huang>gao.png</duoqu_gao_huang><duoqu_jian_cheng>jiangwen.png</duoqu_jian_cheng><duoqu_jian_huang>jiangwen.png</duoqu_jian_huang><duoqu_jian_lan>jiangwen.png</duoqu_jian_lan><duoqu_leifeng_cheng>leifeng.png</duoqu_leifeng_cheng><duoqu_leifeng_hong>leifeng.png</duoqu_leifeng_hong><duoqu_leifeng_huang>leifeng.png</duoqu_leifeng_huang><duoqu_leifeng_lan>leifeng.png</duoqu_leifeng_lan><duoqu_qi_cheng>dafeng.png</duoqu_qi_cheng><duoqu_qi_hong>dafeng.png</duoqu_qi_hong><duoqu_qi_huang>dafeng.png</duoqu_qi_huang><duoqu_qi_lan>dafeng.png</duoqu_qi_lan><duoqu_sha_hong>sha.png</duoqu_sha_hong><duoqu_sha_cheng>sha.png</duoqu_sha_cheng><duoqu_sha_huang>sha.png</duoqu_sha_huang><duoqu_yu_hong>yu.png</duoqu_yu_hong><duoqu_yu_cheng>yu.png</duoqu_yu_cheng><duoqu_yu_huang>yu.png</duoqu_yu_huang><duoqu_wu_hong>wu.png</duoqu_wu_hong><duoqu_wu_cheng>wu.png</duoqu_wu_cheng><duoqu_wu_huang>wu.png</duoqu_wu_huang><duoqu_content_cheng>#FFFFFF</duoqu_content_cheng><duoqu_content_hong>#FFFFFF</duoqu_content_hong><duoqu_content_huang>#FFFFFF</duoqu_content_huang><duoqu_content_lan>#FFFFFF</duoqu_content_lan><duoqu_bottom_cheng>#e2e2e2</duoqu_bottom_cheng><duoqu_bottom_hong>#e2e2e2</duoqu_bottom_hong><duoqu_bottom_huang>#e2e2e2</duoqu_bottom_huang><duoqu_bottom_lan>#e2e2e2</duoqu_bottom_lan><v_tc_hong>#e5354a</v_tc_hong><v_tc_huang>#fbba00</v_tc_huang><v_tc_lan>#00a9e2</v_tc_lan><v_tc_cheng>#fa6000</v_tc_cheng><v_hd_sp_bg_hong>#e5354a</v_hd_sp_bg_hong><v_hd_sp_bg_huang>#fbba00</v_hd_sp_bg_huang><v_hd_sp_bg_lan>#00a9e2</v_hd_sp_bg_lan><v_hd_sp_bg_cheng>#fa6000</v_hd_sp_bg_cheng></popu></popus></title_02010002></titles>", ""));
      continue;
      label121:
      if (paramString.startsWith("02010001")) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02010002><popus><popu><layoutName>duoqu_popup</layoutName><v_by_bg>#FD8B00</v_by_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FD8B00</v_hd_bg><v_bt_bg>#FFFFFF</v_bt_bg><v_by_bt_bg>#E06616</v_by_bt_bg><v_ld_tx>laidian_logo.jpg</v_ld_tx></popu></popus></title_02010002></titles>", ""));
      } else if ((paramString.startsWith("02010002")) || (paramString.startsWith("02010003"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02010002><popus><popu><layoutName>duoqu_popup</layoutName><v_by_card_bg>#F0F0F0</v_by_card_bg><v_by_bg>#FD8B00</v_by_bg><v_hd_del>1_d8ead49e9e8f07cf80c7f36cbc7c05dc.png</v_hd_del><v_hd_close>close.png</v_hd_close><v_hd_bg>TL3;TR3;S#FFFFFF</v_hd_bg><v_bt_bg>#FFFFFF</v_bt_bg><v_hd_sp_bg>#307dd5</v_hd_sp_bg><v_by_cir>#307dd5</v_by_cir><v_by_bt_bg>#E06616</v_by_bt_bg><v_ld_tx>laidian.jpg</v_ld_tx><v_cpy_logo>test.jpg</v_cpy_logo></popu></popus></title_02010002></titles>", ""));
      } else if (paramString.startsWith("02013001")) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02001001><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#ffffff</v_hd_bg><v_hd_sp_bg>#d90123</v_hd_sp_bg><v_bt_by_sp_bg>#d90123</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_cpy_logo>2_f9a4397207f121237641d99081c2d86a.png</v_cpy_logo></popu></popus></title_02001001></titles>", ""));
      } else if (((paramString.startsWith("02")) && (paramString.endsWith("01"))) || (paramString.startsWith("02001001")) || (paramString.startsWith("02002001")) || (paramString.startsWith("02003001")) || (paramString.startsWith("02004001")) || (paramString.startsWith("02005001")) || (paramString.startsWith("02006001")) || (paramString.startsWith("02007001")) || (paramString.startsWith("02008001"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02001001><popus><popu><layoutName>duoqu_popup</layoutName><v_lt_stk>#9fb5ca</v_lt_stk><v_by_bg>#f0f0f0</v_by_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>TL0;TR0;S#f0f0f0</v_hd_bg><v_hd_sp_bg>#0072b6</v_hd_sp_bg><v_bt_bg>#fafafa</v_bt_bg><view_hd_line>TL8;TR8;S#f0f0f0</view_hd_line><v_cpy_logo>yidong.png</v_cpy_logo><v_bt_by_sp_bg>#fafafa</v_bt_by_sp_bg></popu></popus></title_02001001></titles>", ""));
      } else if (((paramString.startsWith("02")) && (paramString.endsWith("02"))) || (paramString.startsWith("02001002")) || (paramString.startsWith("02002002")) || (paramString.startsWith("02003002")) || (paramString.startsWith("02004002")) || (paramString.startsWith("02005002")) || (paramString.startsWith("02006002")) || (paramString.startsWith("02007002")) || (paramString.startsWith("02008002"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02001001><popus><popu><layoutName>duoqu_popup</layoutName><v_by_card_bg>#F0F0F0</v_by_card_bg><v_lt_stk>#9fb5ca</v_lt_stk><v_by_bg>#f0f0f0</v_by_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>TL3;TR3;S#f0f0f0</v_hd_bg><v_bt_by_sp_bg>#0072b6</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_cpy_logo>yidong.png</v_cpy_logo><v_bt_by_sp_bg>#fafafa</v_bt_by_sp_bg></popu></popus></title_02001001></titles>", ""));
      } else if (((paramString.startsWith("02")) && (paramString.endsWith("03"))) || (paramString.startsWith("02001003")) || (paramString.startsWith("02002003")) || (paramString.startsWith("02003003")) || (paramString.startsWith("02004003")) || (paramString.startsWith("02005003")) || (paramString.startsWith("02006003")) || (paramString.startsWith("02007003")) || (paramString.startsWith("02008003"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02001001><popus><popu><layoutName>duoqu_popup</layoutName><v_lt_stk>#9fb5ca</v_lt_stk><v_by_bg>#f0f0f0</v_by_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>TL0;TR0;S#f0f0f0</v_hd_bg><view_hd_line>TL8;TR8;S#f0f0f0</view_hd_line><v_bt_by_sp_bg>#0072b6</v_bt_by_sp_bg><v_bt_bg>#ffffff</v_bt_bg><v_cpy_logo>yidong.png</v_cpy_logo><v_bt_by_sp_bg>#fafafa</v_bt_by_sp_bg></popu></popus></title_02001001></titles>", ""));
      } else if ((paramString.startsWith("13001")) || (paramString.startsWith("13002")) || (paramString.startsWith("13003")) || (paramString.startsWith("13005"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_02001001><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_by_bg>#ffffff</v_by_bg><v_hd_bg>#ffffff</v_hd_bg><v_hd_sp_bg>#CF4059</v_hd_sp_bg><v_cpy_logo>dianxin.png</v_cpy_logo><v_lt_stk>#C99D9E</v_lt_stk></popu></popus></title_02001001></titles>", ""));
      } else if ((paramString.startsWith("05015")) || (paramString.startsWith("05016")) || (paramString.startsWith("05017"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_05015004><popus><popu><v_lt_stk>#9fb5ca</v_lt_stk><v_text_big_color>#333333</v_text_big_color><v_by_bt_bg>#FFFFFF</v_by_bt_bg><v_hd_bg>#FFFFFF</v_hd_bg><v_by_bg>#FFFFFF</v_by_bg><v_hd_sp_bg>#307DD5</v_hd_sp_bg><v_cpy_logo>2_1cf05c8e6c46455c1b4af9c89e7085f9.png</v_cpy_logo><v_by_bt_bg>#FFFFFF</v_by_bt_bg><v_hd_bg>#FFFFFF</v_hd_bg><v_by_bg>#FFFFFF</v_by_bg><v_hd_del>1_581b57a08857281653f5180dea95f7cf.png</v_hd_del><v_hd_close>1_a0f2f38a6a05bd692573f1826980dbce.png</v_hd_close></popu></popus></title_05015004></titles>", ""));
      } else if ((paramString.startsWith("05001")) || (paramString.startsWith("05002")) || (paramString.startsWith("05003")) || (paramString.startsWith("05007")) || (paramString.startsWith("05004"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_05001001><popus><popu><v_by_card_bg>#f5f5f5</v_by_card_bg><v_hd_bg>TL3;TR3;S#f5f5f5</v_hd_bg><v_hd_bg>#f5f5f5</v_hd_bg><v_center_icon>sq_air.png</v_center_icon><v_text_big_color>#383838</v_text_big_color><v_text_small_color>#808080</v_text_small_color><v_text_top_color>#4ca5f7</v_text_top_color><v_by_bt_bg>#d2e2f1</v_by_bt_bg><v_cpy_logo>1_b98cc828a05786501d8a5192a2a5730c.jpg</v_cpy_logo><v_by_bg>#f5f5f5</v_by_bg><v_hd_close>1_ec3910a4ef4a64e2f8c78c9a80eef68d.png</v_hd_close></popu></popus></title_05001001></titles>", ""));
      } else if ((paramString.startsWith("05008")) || (paramString.startsWith("05009")) || (paramString.startsWith("05010")) || (paramString.startsWith("05013")) || (paramString.startsWith("05014"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_05008008><popus><popu><v_air_img>black_air.png</v_air_img><v_text_big_color>#333333</v_text_big_color><v_text_small_color>#666666</v_text_small_color><v_hd_sp_bg>#307DD5</v_hd_sp_bg><v_cpy_logo>2_1cf05c8e6c46455c1b4af9c89e7085f9.png</v_cpy_logo><v_by_bt_bg>#FFFFFF</v_by_bt_bg><v_hd_bg>#FFFFFF</v_hd_bg><v_by_bg>#FFFFFF</v_by_bg><v_hd_del>1_581b57a08857281653f5180dea95f7cf.png</v_hd_del><v_hd_close>1_a0f2f38a6a05bd692573f1826980dbce.png</v_hd_close></popu></popus></title_05008008></titles>", ""));
      } else if ((paramString.equals("11101001")) || (paramString.equals("11102001"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_11101004><popus><popu><layoutName>duoqu_popup</layoutName><v_by_card_bg>#f0f0f0</v_by_card_bg><v_cpy_logo>shunfeng.png</v_cpy_logo><v_hd_close>close.png</v_hd_close><v_by_bg>#f0f0f0</v_by_bg><v_hd_bg>TL3;TR3;S#f0f0f0</v_hd_bg><v_lt_stk>#cdcdcd</v_lt_stk><v_bt_by_sp_bg>#e0e0e0</v_bt_by_sp_bg></popu></popus></title_11101004></titles>", ""));
      } else if ((paramString.equals("11101003")) || (paramString.equals("11102003"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_11101004><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_sp_bg>#2abb8a</v_hd_sp_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_lt_stk>#c0d2b8</v_lt_stk><v_cpy_logo>2_0d53599161048bac4eb414e9da8b544a.png</v_cpy_logo></popu></popus></title_11101004></titles>", ""));
      } else if ((paramString.equals("11101005")) || (paramString.equals("11102005"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_11101004><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_sp_bg>#f1b80c</v_hd_sp_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_lt_stk>#FCD278</v_lt_stk><v_cpy_logo>2_ab4b21ac6bd2f7730ff9bc5a3b120b3a.png</v_cpy_logo></popu></popus></title_11101004></titles>", ""));
      } else if ((paramString.equals("11101008")) || (paramString.equals("11102008"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_11101004><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_sp_bg>#f1b80c</v_hd_sp_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_lt_stk>#FCD278</v_lt_stk><v_cpy_logo>shentong_logo.png</v_cpy_logo></popu></popus></title_11101004></titles>", ""));
      } else if ((paramString.equals("11101004")) || (paramString.equals("11102004"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_11101004><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_sp_bg>#895DAB</v_hd_sp_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_lt_stk>#DBC5DE</v_lt_stk><v_cpy_logo>2_7761a60cd0b5e53c7f7b5f1e69ee57bc.png</v_cpy_logo></popu></popus></title_11101004></titles>", ""));
      } else if ((paramString.equals("11101002")) || (paramString.equals("11102002"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_11101002><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_sp_bg>#2abb8a</v_hd_sp_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_lt_stk>#c0d2b8</v_lt_stk><v_cpy_logo>2_59e85e0ad851857d53bb2de08bc9a55f.png</v_cpy_logo></popu></popus></title_11101002></titles>", ""));
      } else if ((paramString.startsWith("0610")) && (paramString.endsWith("01"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_06101002><popus><popu><layoutName>duoqu_popup</layoutName><v_by_card_bg>#f0f0f0</v_by_card_bg><v_hd_del>delete.png</v_hd_del><v_bt_by_sp_bg>#e0e0e0</v_bt_by_sp_bg><v_hd_close>close.png</v_hd_close><v_lt_stk>#9fb5ca</v_lt_stk><head_num_text_color>#383838</head_num_text_color><v_by_bg>#fafafa</v_by_bg><v_hd_bg>TL3;TR3;S#f0f0f0</v_hd_bg><v_bt_bg>#F0F0F0</v_bt_bg><v_cpy_logo>nanfangdianwang.png</v_cpy_logo></popu></popus></title_06101002></titles>", ""));
      } else if ((paramString.startsWith("0610")) && (paramString.endsWith("02"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_06101002><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_del>duoqu_delete.png</v_hd_del><v_by_card_bg>#f0f0f0</v_by_card_bg><v_hd_close>duoqu_close.png</v_hd_close><v_lt_stk>#c1e2b3</v_lt_stk><head_num_text_color>#383838</head_num_text_color><v_hd_sp_bg>#006e68</v_hd_sp_bg><v_by_bg>#FFFFFF</v_by_bg><v_hd_bg>#FFFFFF</v_hd_bg><v_bt_bg>#fafafa</v_bt_bg><v_bt_by_sp_bg>#e0e0e0</v_bt_by_sp_bg><v_cpy_logo>gjdw_logo.png</v_cpy_logo></popu></popus></title_06101002></titles>", ""));
      } else if (paramString.startsWith("0620")) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_06101002><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_lt_stk>#9fb5ca</v_lt_stk><head_num_text_color>#383838</head_num_text_color><v_hd_sp_bg>#3774bf</v_hd_sp_bg><v_by_bg>#FFFFFF</v_by_bg><v_hd_bg>#FFFFFF</v_hd_bg><v_bt_bg>#fafafa</v_bt_bg><v_bt_by_sp_bg>#1d95cc</v_bt_by_sp_bg><v_cpy_delogo>2_c9d249fbfce92385061789c4d0595f49.png</v_cpy_delogo></popu></popus></title_06101002></titles>", ""));
      } else if (paramString.startsWith("0630")) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_06301023><popus><popu><layoutName>dianwangkapian</layoutName><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><head_num_text_color>#383838</head_num_text_color><v_hd_sp_bg>#3774bf</v_hd_sp_bg><v_by_bg>#FFFFFF</v_by_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_hd_bg>#FFFFFF</v_hd_bg><v_bt_bg>#FFFFFF</v_bt_bg><v_bt_by_sp_bg>#1d95cc</v_bt_by_sp_bg><v_lt_stk>#9fb5ca</v_lt_stk><v_cpy_delogo>2_47f526a692a25ee9c32a2277d232b65a.png</v_cpy_delogo></popu></popus></title_06301023></titles>", ""));
      } else if ((paramString.startsWith("13004001")) || (paramString.startsWith("13004004")) || (paramString.startsWith("13004005"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_03006001><popus><popu><v_code_title_color>#F49372</v_code_title_color><v_code_time_color>#f9acac</v_code_time_color><v_code_num_color>#F49372</v_code_num_color><v_hd_bg>#FFFFFF</v_hd_bg><v_cpy_logo>2_47f526a692a25ee9c32a2277d232b65a.png</v_cpy_logo><v_hd_sp_bg>#CF4059</v_hd_sp_bg><v_by_bg>#F8E3E5</v_by_bg><v_hd_del>duoqu_delete.png</v_hd_del><v_hd_close>duoqu_close.png</v_hd_close><v_bt_by_sp_bg>#CF4059</v_bt_by_sp_bg></popu></popus></title_03006001></titles>", ""));
      } else if ((paramString.startsWith("08000")) || (paramString.startsWith("08101")) || (paramString.startsWith("08102")) || (paramString.startsWith("08103"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_08000><popus><popu><view_hd_line>TL8;TR8;S#307dd5</view_hd_line><v_hd_bg>TL0;TR0;S#f0f0f0</v_hd_bg><v_code_num_color>#ee7a18</v_code_num_color><v_relative_bg_color>TL3;TR3;BL3;BR3;S#F8E3D2;</v_relative_bg_color><v_cpy_logo>1_b98cc828a05786501d8a5192a2a5730c.jpg</v_cpy_logo><v_hd_close>1_ec3910a4ef4a64e2f8c78c9a80eef68d.png</v_hd_close></popu></popus></title_08000></titles>", ""));
      } else if ((paramString.startsWith("08104")) || (paramString.startsWith("13004"))) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_08000><popus><popu><v_by_card_bg>#f5f5f5</v_by_card_bg><v_hd_bg>#f5f5f5</v_hd_bg><v_code_num_color>#fc7718</v_code_num_color><v_relative_bg_color>TL3;TR3;BL3;BR3;S#F5f5f5;</v_relative_bg_color><v_cpy_logo>1_b98cc828a05786501d8a5192a2a5730c.jpg</v_cpy_logo><v_hd_close>1_ec3910a4ef4a64e2f8c78c9a80eef68d.png</v_hd_close></popu></popus></title_08000></titles>", ""));
      } else if (paramString.startsWith("04002018")) {
        localObject1 = x.a(StringUtils.stringConvertXML("<titles><title_03006001><popus><popu><v_hd_close></v_hd_close><v_hd_del></v_hd_del><v_hd_bg></v_hd_bg><v_cpy_logo></v_cpy_logo><v_hd_sp_bg></v_hd_sp_bg></popu></popus></title_03006001></titles>", ""));
      }
    }
  }
  
  public static HashMap<String, String> c(String paramString)
  {
    Object localObject = null;
    HashMap localHashMap2 = null;
    HashMap localHashMap1 = localHashMap2;
    try
    {
      SceneRule localSceneRule = SceneconfigUtil.getSceneRule(paramString, 1);
      if (localSceneRule != null)
      {
        localHashMap1 = localHashMap2;
        new StringBuilder("getBubleSmsTitle : ").append(paramString).append(" is sceneRule not null");
        localHashMap1 = localHashMap2;
        Constant.getContext();
        localHashMap1 = localHashMap2;
        localHashMap2 = a(paramString, Scene_page_config);
        localObject = localHashMap2;
        if (localHashMap2 == null)
        {
          localHashMap1 = localHashMap2;
          new StringBuilder("getBubleSmsTitle : ").append(paramString).append(" is smsTitle  null： ").append(Scene_page_config);
          return localHashMap2;
        }
      }
      else
      {
        localHashMap1 = localHashMap2;
        new StringBuilder("getBubleSmsTitle : ").append(paramString).append(" is sceneRule null");
        localHashMap1 = localHashMap2;
        if (Constant.Test)
        {
          localHashMap1 = localHashMap2;
          SceneconfigUtil.getSceneRule(paramString, 1);
          localHashMap1 = localHashMap2;
          localObject = "<titles><title_" + paramString + "><popus><popu><layoutName>duoqu_popup</layoutName><v_hd_del>liantong.png</v_hd_del><v_hd_close>xiangqing.png</v_hd_close><v_bt_cell_split_bg>shuiyin.png</v_bt_cell_split_bg><v_by_bt_bg>#ebebeb</v_by_bt_bg><v_by_bg>#ffffff</v_by_bg><v_hd_bg>#fd7f8d</v_hd_bg><v_bt_bg>#ffffff</v_bt_bg></popu></popus></title_" + paramString + "></titles>";
          localHashMap1 = localHashMap2;
          if ("05001001".equals(paramString)) {
            localObject = "<titles><title_05001001><popus><popu><layoutName>chailvkapian</layoutName><v_by_bt_bg>#0398B9</v_by_bt_bg><v_hd_bg>#1BC4D9</v_hd_bg><v_by_bg>#1BC5D9</v_by_bg><v_bt_cell_split_bg>shuiyin.png</v_bt_cell_split_bg><v_hd_del>xiecheng.png</v_hd_del><v_bt_bg>#ffffff</v_bt_bg><v_hd_close>xiangqing.png</v_hd_close></popu></popus></title_05001001></titles>";
          }
          localHashMap1 = localHashMap2;
          Constant.getContext();
          localHashMap1 = localHashMap2;
          paramString = a(paramString, (String)localObject);
          return paramString;
        }
      }
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
      localObject = localHashMap1;
    }
    return (HashMap<String, String>)localObject;
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.b.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */