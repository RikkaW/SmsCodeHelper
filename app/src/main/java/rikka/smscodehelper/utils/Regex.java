package rikka.smscodehelper.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rikka.smscodehelper.bean.SMSInfo;

/**
 * Created by gitai on 15-12-25.
 */
public class Regex {
    public static SMSInfo parse(String messageBody){
        // Sender
        Pattern sender = Pattern.compile("(?<=(\\[|【))(.*)(?=(\\]|】))");

        // A-Z|a-z|0-9
        Pattern code = Pattern.compile("\\d{6}");

        Matcher matcher_sender = sender.matcher(messageBody);
        Matcher matcher_code = code.matcher(messageBody);
        if (matcher_code.find()){
            if (matcher_sender.find()){
                return new SMSInfo(matcher_sender.group(0), matcher_code.group(0));
            }else{
                return new SMSInfo(null, matcher_code.group(0));
            }
        }
        return null;
    }
}
