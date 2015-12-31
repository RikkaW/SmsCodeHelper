package rikka.smscodehelper.utils;

import android.text.TextUtils;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yulan on 2015/12/29.
 */
public class SMSCodeTest extends TestCase {

    private HashMap<String, String> SmsContactCodeMap;

    public void testFindSMSCode() throws Exception {
        setSmsContact();
        for (Map.Entry entry : SmsContactCodeMap.entrySet()) {
            SMSCode.SMSInfo smsInfo = SMSCode.findSMSCode((String) entry.getKey());
            if (TextUtils.isEmpty((String) entry.getValue())) {
                assertNull(smsInfo);
            } else {
                assertNotNull(smsInfo);
                assertEquals(smsInfo.code, entry.getValue());
            }
        }
    }

    private void setSmsContact() {
        SmsContactCodeMap = new HashMap<>(10);
        String SMSContact1 = "145879 is your Twitter login code.";
        SmsContactCodeMap.put(SMSContact1, "145879");
        String SMSContact2 = "你的 Tumblr 驗證碼為「145879」，此碼將會在兩分鐘後失效。";
        SmsContactCodeMap.put(SMSContact2, "145879");
        String SMSContact3 = "【饿了么】您的验证码是917440，在5分钟内有效。如非本人操作请忽略本短信。";
        SmsContactCodeMap.put(SMSContact3, "917440");
        String SMSContact4 = "验证码347828，下载尚WiFi客户端，一键免费上网更便捷，点击http://t.cn/RhpFIU2 下载，赠送更多免费时长【验证助手】";
        SmsContactCodeMap.put(SMSContact4, "347828");
        String SMSContact5 = "12306用户注册或既有用户手机核验专用验证码：060973。如非本人直接访问12306，请停止操作，切勿将验证码提供给第三方。【铁路客服】";
        SmsContactCodeMap.put(SMSContact5, "060973");
        String SMSContact6 = "您在付款，为防诈骗千万不要告诉他人验证码505513，商户为汇付天下，金额80元。如有疑问请停止操作。（短信编号：245747）【工商银行】";
        SmsContactCodeMap.put(SMSContact6, "505513");
        String SMSContact7 = "任何向你索要验证码的都是骗子，千万别给！您正在向www（尾号4832）转账，验证码370000，100元。";
        SmsContactCodeMap.put(SMSContact7, "370000");
    }
}
