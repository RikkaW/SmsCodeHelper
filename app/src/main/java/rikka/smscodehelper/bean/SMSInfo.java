package rikka.smscodehelper.bean;

/**
 * Created by gitai on 15-12-25.
 */
public class SMSInfo {
    public String sender;
    public String code;

    public SMSInfo(String sender, String code){
        this.sender = sender;
        this.code = code;
    }
}
