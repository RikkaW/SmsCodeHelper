package rikka.smscodehelper.receiver;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.content.ClipboardManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import rikka.smscodehelper.R;

/**
 * Created by Rikka on 2015/12/7.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getExtras().get("pdus");

        for (Object p : pdus){
            byte[] sms = (byte[]) p;

            SmsMessage message = SmsMessage.createFromPdu(sms);

            String content = message.getMessageBody();
            content += " ";

            String number = message.getOriginatingAddress();

            // 找到 [] 之间内容
            ArrayList<String> sender = new ArrayList<>();
            makeSenderList(sender, content, "[", "]");
            makeSenderList(sender, content, "【", "】");

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
            int start = findStart(content);

            // 大概应该在 "验证码" 什么的后面吧
            // 只找1个
            findcode(code, content, start == -1 ? 0 : start, 1);

            // 出现了 "验证码" 什么的但是又没找到的情况
            if (start != -1 && code.size() == 0)
                findcode(code, content, 0);

            // 找到了 [] 或者出现了 "验证码"
            if ((sender.size() > 0 || start != -1) && code.size() > 0) {
                // toast
                Toast.makeText(context, String.format(context.getString(R.string.toast_format), code.get(0)), Toast.LENGTH_LONG).show();

                // 复制到剪贴板
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("SmsCode", code.get(0));
                clipboardManager.setPrimaryClip(clipData);

                // 来条通知吗 0.0
                /*
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setCategory(Notification.CATEGORY_MESSAGE)
                                .setAutoCancel(true)
                                .setContentTitle(number);

                if (sender.size() > 0)
                    builder.setContentText(String.format(context.getString(R.string.notify_format), sender.get(0), code.get(0)));
                else
                    builder.setContentText(String.format(context.getString(R.string.notify_no_sender_format), code.get(0)));

                NotificationManager notificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());*/
            }
        }
    }

    public void makeSenderList(ArrayList<String>list, String content, String start, String end) {
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

    public int findStart(String content) {
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

    public void findcode(ArrayList<String>list, String content, int start) {
        findcode(list, content, start, -1);
    }

    public void findcode(ArrayList<String>list, String content, int start, int max) {
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
}
