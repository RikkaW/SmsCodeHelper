Flyme OS 5.5.12.22 beta
====
`2015-12-26`

[[固件发布]Flyme OS 5.5.12.22 beta](http://bbs.flyme.cn/thread-524635-1-1.html)

## Mms(Messaging/多趣短信Sdk)

![icon](./res/mipmap-xxxhdpi-v4/ic_launcher_smsmms.png)

### Location
Apk: `Mms.apk`    
Data: 2015-12-26 16:30:34     
Builder: gitai    
LSB: Description: Debian GNU/Linux 8.2 (jessie) Release: 8.2    

### apkDec     
A tool for decompile Android apk files    
Version:  `v1.0.1`     
Dependencies:    
+ Apktool `v2.0.2`    
+ dex2jar `reader-2.0, translator-2.0, ir-2.0`    
+ jd-cli `0.9.1.Final`    

## 完全没找到相关的类～只在多趣短信Sdk中发现相关痕迹(按照可能性排序)

1. [aqn](./src_cfr/aqn.java) //节气,票？

2. [apy](./src_cfr/apy.java) //快递

2. [asj](./src_cfr/asj.java) //订单什么的~

2. [asw](./src_cfr/asw.java) //业务序号

2. [asz](./src_cfr/asz.java) //服务?

2. [atc](./src_cfr/atc.java) //回复balabala

2. [atc](./src_cfr/atc.java) //服务回复序号

3. [SmsParserEngine](./src_jd/com/ted/android/core/SmsParserEngine.java) //这个比较靠谱!!!

5. [CommonAction](./src_jd/com/ted/android/data/bubbleAction/CommonAction.java) //微信公众号,快递单号,验证码 靠谱 +1

4. [CarrierAction](./src_jd/com/ted/android/data/bubbleAction/CarrierAction.java) //快递查询

5. [BubbleUtils](./src_jd/com/ted/android/message/BubbleUtils.java) //短信提醒

5. [CardEBusiness](./src_jd/com/ted/android/smscard/CardEBusiness.java) //RT

5. [CardExpress](./src_jd/com/ted/android/smscard/CardExpress.java) //RT

5. [CardFetchExpress](./src_jd/com/ted/android/smscard/CardFetchExpress.java) //RT

5. [CardHotel](./src_jd/com/ted/android/smscard/CardHotel.java) //RT

6. [CardLife](./src_jd/com/ted/android/smscard/CardLife.java)

6. [CardMeituan](./src_jd/com/ted/android/smscard/CardMeituan.java)

6. [CardMovie](./src_jd/com/ted/android/smscard/CardMovie.java)

6. [CardPay](./src_jd/com/ted/android/smscard/CardPay.java)

6. [CardPlaneTicket](./src_jd/com/ted/android/smscard/CardPlaneTicket.java)

6. [CardTrain](./src_jd/com/ted/android/smscard/CardTrain.java)

6. [BubbleUtils](./src_jd/com/ted/sdk/bubble/BubbleUtils.java)

6. [CardGroup](./src_jd/com/ted/sdk/smscard/CardGroup.java)

6. [CardMovieTicket](./src_jd/com/ted/sdk/smscard/CardMovieTicket.java)

6. [CardPlaneTicket](./src_jd/com/ted/sdk/smscard/CardPlaneTicket.java)

6. [cq](./src_jd/cq.java) //航班

6. [g](./src_jd/g.java) //操作栈??

3. [ParseDateManager](./src_cfr/cn/com/xy/sms/util/ParseDateManager.java)

4. [DexUtil](./src_cfr/cn/com/xy/sms/sdk/dex/DexUtil.java)


### Apk Info    
```
package: name='com.android.mms' versionCode='4' versionName='4.0.0'
sdkVersion:'22'
targetSdkVersion:'22'
original-package:'com.android.mms'
uses-permission:'android.permission.RECEIVE_WAP_PUSH'
uses-permission:'android.permission.RECEIVE_BOOT_COMPLETED'
uses-permission:'android.permission.CALL_PHONE'
uses-permission:'android.permission.READ_CONTACTS'
uses-permission:'android.permission.WRITE_CONTACTS'
uses-permission:'android.permission.READ_PROFILE'
uses-permission:'android.permission.RECEIVE_SMS'
uses-permission:'android.permission.RECEIVE_MMS'
uses-permission:'android.permission.SEND_SMS'
uses-permission:'android.permission.VIBRATE'
uses-permission:'android.permission.INTERNET'
uses-permission:'android.permission.READ_SMS'
uses-permission:'android.permission.WRITE_SMS'
uses-permission:'android.permission.ACCESS_NETWORK_STATE'
uses-permission:'android.permission.CHANGE_NETWORK_STATE'
uses-permission:'android.permission.READ_PHONE_STATE'
uses-permission:'android.permission.MODIFY_PHONE_STATE'
uses-permission:'android.permission.WAKE_LOCK'
uses-permission:'android.permission.WRITE_EXTERNAL_STORAGE'
uses-permission:'android.permission.INSTALL_DRM'
uses-permission:'android.permission.SYSTEM_ALERT_WINDOW'
uses-permission:'android.permission.INTERNAL_SYSTEM_WINDOW'
uses-permission:'android.permission.STATUS_BAR'
uses-permission:'android.permission.EXPAND_STATUS_BAR'
uses-permission:'com.meizu.stats.permission.READ_USAGESTATS'
uses-permission:'com.meizu.stats.permission.WRITE_USAGESTATS'
uses-permission:'android.permission.WRITE_APN_SETTINGS'
uses-permission:'android.permission.WRITE_SETTINGS'
uses-permission:'android.permission.CALL_PRIVILEGED'
uses-permission:'android.permission.WRITE_SECURE_SETTINGS'
uses-permission:'com.meizu.flyme.push.permission.RECEIVE'
uses-permission:'android.permission.BACKUP'
uses-permission:'android.permission.RECORD_AUDIO'
uses-permission:'android.permission.GET_TASKS'
uses-permission:'android.permission.GET_ACCOUNTS'
uses-permission:'android.permission.CHANGE_WIFI_STATE'
uses-permission:'android.permission.ACCESS_FINE_LOCATION'
uses-permission:'android.permission.DISABLE_KEYGUARD'
uses-permission:'android.permission.RECEIVE_SMS_SUPER'
uses-permission:'android.permission.RECEIVE_MMS_SUPER'
uses-permission:'android.permission.RECEIVE_WAP_PUSH_SUPER'
uses-permission:'android.permission.MODIFY_AUDIO_SETTINGS'
uses-permission:'com.android.mms.permission.MAPS_RECEIVE'
uses-permission:'com.google.android.providers.gsf.permission.READ_GSERVICES'
uses-permission:'android.permission.ACCESS_COARSE_LOCATION'
uses-permission:'android.permission.INTERACT_ACROSS_USERS'
uses-permission:'android.permission.GET_PACKAGE_SIZE'
uses-permission:'android.permission.MANAGE_USERS'
uses-permission:'android.permission.USE_CREDENTIALS'
uses-permission:'com.android.email.permission.READ_ATTACHMENT'
uses-gl-es:'0x20000'
uses-permission:'android.permission.MMS_SEND_OUTBOX_MSG'
uses-permission:'android.permission.READ_CALL_LOG'
uses-permission:'android.permission.GET_PACKAGE_SIZE'
uses-permission:'android.permission.INSTALL_PACKAGES'
uses-permission:'com.meizu.flyme.permission.UPDATE'
uses-permission:'com.android.mms.push.permission.MESSAGE'
uses-permission:'com.meizu.flyme.push.permission.RECEIVE'
uses-permission:'android.permission.BAIDU_LOCATION_SERVICE'
uses-permission:'android.permission.MOUNT_UNMOUNT_FILESYSTEMS'
uses-permission:'android.permission.READ_LOGS'
uses-permission:'android.permission.ACCESS_WIFI_STATE'
uses-permission:'android.permission.READ_EXTERNAL_STORAGE'
application-label:'Messages'
application-label-ca:'Missatgeria'
application-label-da:'Beskeder'
application-label-fa:'پیام رسانی'
application-label-ja:'メッセージ'
application-label-ka:'შეტყობინებები'
application-label-nb:'Meldinger'
application-label-be:'Паведамленні'
application-label-de:'Nachrichtenfunktionen'
application-label-ne:'सन्देश पठाउँदै'
application-label-af:'Boodskappe'
application-label-bg:'Съобщения'
application-label-th:'ข้อความ'
application-label-fi:'Viestit'
application-label-hi:'संदेश सेवा'
application-label-si:'පණිවිඩ යැවීම'
application-label-vi:'Nhắn tin'
application-label-sk:'SMS a MMS'
application-label-uk:'Повідомлення'
application-label-el:'Μηνύματα'
application-label-nl:'Berichten'
application-label-pl:'SMS/MMS'
application-label-sl:'Sporočanje'
application-label-tl:'Pagpapadala ng mensahe'
application-label-am:'መልዕክት በመላክ ላይ'
application-label-km:'​ផ្ញើ​សារ'
application-label-rm:'SMS/MMS'
application-label-in:'Perpesanan'
application-label-mn:'Зурвас'
application-label-ko:'메시지'
application-label-lo:'ຂໍ້​ຄວາມ'
application-label-ro:'Mesagerie'
application-label-ar:'المراسلات'
application-label-fr:'Messages'
application-label-hr:'Slanje poruka'
application-label-sr:'Размена порука'
application-label-tr:'Mesajlaş.'
application-label-cs:'SMS a MMS'
application-label-es:'Mensajería'
application-label-ms:'mesej-mesej'
application-label-et:'Sõnumside'
application-label-it:'Messaggi'
application-label-lt:'Pranešimai'
application-label-pt:'Mensagens'
application-label-hu:'Üzenetek'
application-label-ru:'SMS/MMS'
application-label-zu:'Umlayezo'
application-label-lv:'Ziņapmaiņa'
application-label-sv:'Medd.'
application-label-iw:'הודעות'
application-label-sw:'SMS/MMS'
application-label-hy:'Հաղորդակցում'
application-label-az:'Mesajlaşma'
application-label-fr_CA:'SMS/MMS'
application-label-lo_LA:'ຂໍ້​ຄວາມ'
application-label-en_GB:'Messaging'
application-label-in_ID:'Pesan'
application-label-et_EE:'Sõnumside'
application-label-ka_GE:'შეტყობინებები'
application-label-km_KH:'​ផ្ញើ​សារ'
application-label-th_TH:'ข้อความ'
application-label-zh_HK:'訊息'
application-label-si_LK:'පණිවිඩ යැවීම'
application-label-hy_AM:'Հաղորդակցում'
application-label-my_MM:'မက္ေဆ့ခ်္မ်ား'
application-label-zh_CN:'信息'
application-label-hi_IN:'संदेश'
application-label-en_IN:'Messaging'
application-label-mn_MN:'Зурвас'
application-label-vi_VN:'Tin nhắn'
application-label-ne_NP:'सन्देश पठाउँदै'
application-label-sr_RS:'Razmena poruka'
application-label-es_US:'Mensajería'
application-label-pt_PT:'Mensag.'
application-label-zh_TW:'簡訊'
application-label-ms_MY:'Pemesejan'
application-label-az_AZ:'Mesajlaşma'
application-label-mk:'Messages'
application-label-bn_BD:'Messages'
application-label-ky_KG:'Messages'
application-label-mk_MK:'Messages'
application-label-ur_PK:'Messages'
application-label-ta_IN:'Messages'
application-label-te_IN:'Messages'
application-label-ml_IN:'Messages'
application-label-kn_IN:'Messages'
application-label-mr_IN:'Messages'
application-label-pt_BR:'Mensagens'
application-label-gl_ES:'Messages'
application-label-eu_ES:'Messages'
application-label-is_IS:'Messages'
application-label-kk_KZ:'Messages'
application-label-uz_UZ:'Messages'
application-icon-160:'res/mipmap-hdpi-v4/ic_launcher_smsmms.png'
application-icon-240:'res/mipmap-hdpi-v4/ic_launcher_smsmms.png'
application-icon-320:'res/mipmap-xhdpi-v4/ic_launcher_smsmms.png'
application-icon-480:'res/mipmap-xxhdpi-v4/ic_launcher_smsmms.png'
application-icon-640:'res/mipmap-xxxhdpi-v4/ic_launcher_smsmms.png'
application: label='Messages' icon='res/mipmap-hdpi-v4/ic_launcher_smsmms.png'
launchable-activity: name='com.android.mms.ui.ConversationList'  label='Messages' icon=''
```

