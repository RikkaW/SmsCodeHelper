package rikka.smscodehelper.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
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

            String number = message.getOriginatingAddress();

            // 找到 [] 之间内容
            ArrayList<String> sender = new ArrayList<>();
            makeSenderList(sender, content, "[", "]");
            makeSenderList(sender, content, "【", "】");

            // 大概是最短的那个
            Collections.sort(sender,  new Comparator<String>(){
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

            // 找到数字
            ArrayList<String> code = new ArrayList<>();

            // 先找到 "验证码" 什么的
            int start = findStart(content);
            // 大概应该在 "验证码" 什么的后面吧
            findNumber(code, content, start);

            // 出现了 "验证码" 什么的但是又没找到数字的情况
            if (start > 0 && code.size() == 0)
                findNumber(code, content, 0);

            // 大概也是最短的那个
            Collections.sort(code, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    // 长度小于4的大概不是验证码吧
                    if (o1.length() < 4 || o1.length() > o2.length()) {
                        return 1;
                    } else if (o1.length() < o2.length()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            // 找到了 [] 或者出现了 "验证码"
            if ((sender.size() > 0 || start > 0) && code.size() > 0) {
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

    public void findNumber(ArrayList<String>list, String content, int start) {
        int curpos = start;
        int startpos = -1;
        int length = content.length();

        while (curpos < length) {
            char ch = content.charAt(curpos);

            if (startpos == -1 && Character.isDigit(ch)) {
                startpos = curpos;
            }

            if (startpos != -1 && !Character.isDigit(ch)) {
                list.add(content.substring(startpos, curpos));
                startpos = -1;
            }

            curpos ++;
        }

        if (startpos != -1) {
            list.add(content.substring(startpos, content.length()));
        }
    }
}
