package rikka.smscodehelper.utils;

import com.mokee.mms.utils.CaptchasUtils;

import rikka.smscodehelper.bean.SMSInfo;

/**
 * Created by gitai on 15-12-12.
 */
public class SMSCode {
    public final static int PARSE_TYPE_V1 = 0x1;
    public final static int PARSE_TYPE_MOKEE = 0x2;
    public final static int PARSE_TYPE_REGEX = 0x3;

    public static SMSInfo parse(String messageBody, String originatingAddress, int type){
        SMSInfo SMSCode = null;
        switch (type){
            case PARSE_TYPE_V1:
                SMSCode = V1.parse(messageBody);
                break;
            case PARSE_TYPE_MOKEE:
                SMSCode = findByMokee(messageBody, originatingAddress);
                break;
            case PARSE_TYPE_REGEX:
                SMSCode = Regex.parse(messageBody);
                break;
        }
        return SMSCode;
    }

    private static SMSInfo findByMokee(String messageBody, String originatingAddress){
        String provider = CaptchasUtils.getCaptchaProvider(messageBody, originatingAddress);
        if (!CaptchasUtils.isChineseContains(messageBody) && CaptchasUtils.isCaptchasEnMessage(messageBody)){
            return new SMSInfo(provider, CaptchasUtils.tryToGetEnCaptchas(messageBody));
        }else if(CaptchasUtils.isCaptchasMessage(messageBody)){
            return new SMSInfo(provider, CaptchasUtils.tryToGetCnCaptchas(messageBody));
        }
        return null;
    }
}
