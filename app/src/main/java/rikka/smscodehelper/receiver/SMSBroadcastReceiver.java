package rikka.smscodehelper.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsMessage;

import me.gitai.library.utils.SharedPreferencesUtil;
import me.gitai.library.utils.ToastUtil;
import rikka.smscodehelper.R;
import rikka.smscodehelper.utils.SMSCode;
import rikka.smscodehelper.utils.StringUtils;

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

            if (StringUtils.isEmpty(content)){
                return;
            }

            int parseType = Integer.parseInt(SharedPreferencesUtil.getInstence(null).getString("parse_type", String.valueOf(SMSCode.PARSE_TYPE_V1)));
            SMSCode.SMSInfo smsinfo = SMSCode.parse(content, parseType);
            if (smsinfo == null || StringUtils.isEmpty(smsinfo.code)){
                return;
            }
            // toast
            ToastUtil.showId(R.string.toast_format,smsinfo.code);

            // 复制到剪贴板
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("SMS Code", smsinfo.code);
            clipboardManager.setPrimaryClip(clipData);

            boolean notification = SharedPreferencesUtil.getInstence(null).getBoolean("notification", false);
            if (!notification){
                return;
            }
            // 来条通知吗 0.0
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setCategory(Notification.CATEGORY_MESSAGE)
                            .setAutoCancel(true)
                            .setContentTitle(number);

            if (!StringUtils.isEmpty(smsinfo.sender)){
                builder.setContentText(String.format("%s %s", smsinfo.sender, smsinfo.code));
            }else{
                builder.setContentText(String.format("%s", smsinfo.code));
            }

            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
        }
    }


}
