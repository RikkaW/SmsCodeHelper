package rikka.smscodehelper.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Rikka on 2015/12/14.
 */
public class SMSCode {

    public static SMSInfo findSMSCode(String content) {
        if (content.length() == 0)
            return null;

        // 去掉 URL
        String pattern = "[a-zA-z]+://[^\\s]*";
        content = content.replaceAll(pattern, "");

        // 找到并去掉发送者
        ArrayList<String> sender = new ArrayList<>();
        content = cutSenderFromString(sender, content, "[", "]");
        content = cutSenderFromString(sender, content, "【", "】");

        // 大概是最短的那个
        Collections.sort(sender, new Comparator<String>() {
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
        });

        for (String oneSender : sender) {
            content = content.replace(oneSender, "");
        }


        // 分出每个句子 并在有关键词的句子里找验证码
        ArrayList<String> sentence;
        sentence = findSentence(content);

        for (String str : sentence) {
            if (findKeyWord(str) != -1) {
                String code = findCode(str);

                if (code != null)
                    return new SMSInfo(sender.size() > 0 ? sender.get(0) : "", code);
            }
        }
        return null;
    }

    private static String cutSenderFromString(ArrayList<String> list, String content, String start, String end) {
        StringBuilder stringBuilder = new StringBuilder(content);

        int curPos = 0;
        while (curPos != -1) {

            curPos = content.indexOf(start, curPos);
            if (curPos != -1) {
                curPos++;

                int endPos = content.indexOf(end, curPos);

                if (endPos != -1) {
                    list.add(content.substring(curPos, endPos));

                    for (int i = curPos; i < endPos; i++)
                        stringBuilder.setCharAt(i, '-');
                }
            }
        }

        return stringBuilder.toString();
    }

    private static int findKeyWord(String content) {
        int codeFindStart = content.indexOf("验证码");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf("校验码");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf("码");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf("碼");

        if (codeFindStart == -1)
            codeFindStart = content.indexOf(" code");

        return codeFindStart;
    }

    private static ArrayList<String> findSentence(String content) {
        ArrayList<String> list = new ArrayList<>();

        int last = 0;
        for (int i = 0; i < content.length() - 1; i++) {
            char ch = content.charAt(i);

            if (ch == ',' || ch == '，'
                    || ch == '.' || ch == '。'
                    || ch == '!' || ch == '！') {
                list.add(content.substring(last, i));
                last = i + 1;
            }
        }

        if (last < content.length()) {
            list.add(content.substring(last, content.length()));
        }

        return list;
    }

    private static boolean isCodeChar(char ch) {
        return ((ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'));
    }

    private static String getStringAfter(String content, String string) {
        int startPos = content.indexOf(string);
        if (startPos == -1) {
            return content;
        } else {
            return content.substring(startPos);
        }
    }

    private static String findCode(String content) {
        content += " ";

        // 如果有冒号
        content = getStringAfter(content, "：");
        content = getStringAfter(content, ":");


        int startPos = -1;
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);

            if (startPos == -1 && isCodeChar(ch)) {
                startPos = i;
            }

            if (startPos != -1 && !isCodeChar(ch)) {
                // 长度4以上
                if (i - startPos >= 4) {
                    return (content.substring(startPos, i));
                }
                startPos = -1;
            }
        }

        return null;
    }

    public static class SMSInfo {
        public String sender;
        public String code;

        public SMSInfo(String sender, String code) {
            this.sender = sender;
            this.code = code;
        }
    }
}
