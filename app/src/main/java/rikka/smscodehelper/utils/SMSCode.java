package rikka.smscodehelper.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gitai on 15-12-12.
 */
public class SMSCode {
    public final static int PARSE_TYPE_V1 = 0x1;
    public final static int PARSE_TYPE_REGEX = 0x2;

    public static SMSInfo parse(String raw, int type){
        SMSInfo SMSCode = null;
        switch (type){
            case PARSE_TYPE_V1:
                SMSCode = findByV1(raw);
                break;
            case PARSE_TYPE_REGEX:
                SMSCode = findByRegex(raw);
                break;
        }
        return SMSCode;
    }

    private static SMSInfo findByV1(String raw){
        // 找到 [] 之间内容
        ArrayList<String> sender = new ArrayList<>();
        makeSenderList(sender, raw, "[", "]");
        makeSenderList(sender, raw, "【", "】");

        Comparator<String> compactor = new Comparator<String>(){
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };

        // 大概是最短的那个
        Collections.sort(sender, compactor);

        // 找到数字
        ArrayList<String> code = new ArrayList<>();

        // 先找到 "验证码" 什么的
        int start = findStart(raw);

        // 大概应该在 "验证码" 什么的后面吧
        // 只找1个
        findcode(code, raw, start == -1 ? 0 : start, 1);

        // 出现了 "验证码" 什么的但是又没找到的情况
        if (start != -1 && code.size() == 0){
            findcode(code, raw, 0);
        }

        if ((sender.size() > 0 || start != -1) && code.size() > 0){
            return new SMSInfo(sender.get(0), code.get(0));
        }
        return null;
    }

    private static void makeSenderList(ArrayList<String> list, String content, String start, String end) {
        int length = content.length();
        int curpos = 0;
        while (curpos != -1) {

            curpos = content.indexOf(start, curpos);
            if (curpos != -1) {
                curpos ++;

                int endpos = content.indexOf(end, curpos);

                if (endpos != -1) {
                    list.add(content.substring(curpos, endpos));
                }
            }
        }
    }

    private static int findStart(String content) {
        int codeFindStart;
        codeFindStart = content.indexOf("验证码");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf("校验码");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf("码");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf(" code");

        if (codeFindStart == -1)
            codeFindStart = 0;

        return codeFindStart;
    }

    private static void findcode(ArrayList<String> list, String content, int start) {
        findcode(list, content, start, -1);
    }

    private static void findcode(ArrayList<String> list, String content, int start, int max) {
        int curpos = start;
        int startpos = -1;
        int length = content.length();

        int i = 0;
        while (curpos < length) {
            char ch = content.charAt(curpos);

            if (startpos == -1 && ((ch >= 'A' && ch <= 'Z') || Character.isDigit(ch))) {
                startpos = curpos;
            }

            if (startpos != -1 && (!(ch >= 'A' && ch <= 'Z') && !Character.isDigit(ch))) {
                // 长度4以上
                if (curpos - startpos >= 4) {
                    list.add(content.substring(startpos, curpos));

                    i++;
                    if (i != -1 && i == max)
                        return;
                }

                startpos = -1;
            }

            curpos ++;
        }
    }

    private static SMSInfo findByRegex(String raw){
        // Sender
        Pattern sender = Pattern.compile("(?<=(\\[|【))(.*)(?=(\\]|】))");

        // A-Z|a-z|0-9
        Pattern code = Pattern.compile("\\d{6}");

        Matcher matcher_sender = sender.matcher(raw);
        Matcher matcher_code = code.matcher(raw);
        if (matcher_code.find()){
            if (matcher_sender.find()){
                return new SMSInfo(matcher_sender.group(0), matcher_code.group(0));
            }else{
                return new SMSInfo(null, matcher_code.group(0));
            }
        }
        return null;

    }

    public static class SMSInfo{
        public String sender;
        public String code;

        public SMSInfo(String sender, String code){
            this.sender = sender;
            this.code = code;
        }
    }
}
