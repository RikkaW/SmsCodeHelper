/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.nio.ByteBuffer
 */
package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.mipush.sdk.AppInfoHolder;
import com.xiaomi.xmpush.thrift.ActionType;
import com.xiaomi.xmpush.thrift.Target;
import com.xiaomi.xmpush.thrift.XmPushActionAckMessage;
import com.xiaomi.xmpush.thrift.XmPushActionCommandResult;
import com.xiaomi.xmpush.thrift.XmPushActionContainer;
import com.xiaomi.xmpush.thrift.XmPushActionNotification;
import com.xiaomi.xmpush.thrift.XmPushActionRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendFeedbackResult;
import com.xiaomi.xmpush.thrift.XmPushActionSendMessage;
import com.xiaomi.xmpush.thrift.XmPushActionSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnRegistrationResult;
import com.xiaomi.xmpush.thrift.XmPushActionUnSubscriptionResult;
import com.xiaomi.xmpush.thrift.XmPushThriftSerializeUtils;
import java.nio.ByteBuffer;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;

public class PushContainerHelper {
    private static final byte[] DEFAULT_IV = new byte[]{100, 23, 84, 114, 72, 0, 4, 97, 73, 97, 2, 52, 84, 102, 18, 32};

    public static byte[] MIPushDecrypt(byte[] arrby, byte[] arrby2) throws Exception {
        return PushContainerHelper.createCipher(arrby, 2).doFinal(arrby2);
    }

    public static byte[] MIPushEncrypt(byte[] arrby, byte[] arrby2) throws Exception {
        return PushContainerHelper.createCipher(arrby, 1).doFinal(arrby2);
    }

    private static Cipher createCipher(byte[] object, int n) throws Exception {
        object = new SecretKeySpec((byte[])object, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(DEFAULT_IV);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(n, (Key)object, ivParameterSpec);
        return cipher;
    }

    private static TBase createRespMessageFromAction(ActionType actionType) {
        switch (.$SwitchMap$com$xiaomi$xmpush$thrift$ActionType[actionType.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return new XmPushActionRegistrationResult();
            }
            case 2: {
                return new XmPushActionUnRegistrationResult();
            }
            case 3: {
                return new XmPushActionSubscriptionResult();
            }
            case 4: {
                return new XmPushActionUnSubscriptionResult();
            }
            case 5: {
                return new XmPushActionSendMessage();
            }
            case 6: {
                return new XmPushActionAckMessage();
            }
            case 7: {
                return new XmPushActionCommandResult();
            }
            case 8: {
                return new XmPushActionSendFeedbackResult();
            }
            case 9: {
                return new XmPushActionNotification();
            }
            case 10: 
        }
        return new XmPushActionCommandResult();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(Context context, T t, ActionType actionType) {
        boolean bl;
        if (!actionType.equals((Object)ActionType.Registration)) {
            bl = true;
            do {
                return PushContainerHelper.generateRequestContainer(context, t, actionType, bl, context.getPackageName(), AppInfoHolder.getInstance(context).getAppID());
                break;
            } while (true);
        }
        bl = false;
        return PushContainerHelper.generateRequestContainer(context, t, actionType, bl, context.getPackageName(), AppInfoHolder.getInstance(context).getAppID());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static <T extends TBase<T, ?>> XmPushActionContainer generateRequestContainer(Context object, T object2, ActionType actionType, boolean bl, String string2, String string3) {
        byte[] arrby = XmPushThriftSerializeUtils.convertThriftObjectToBytes(object2);
        if (arrby == null) {
            MyLog.warn("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        XmPushActionContainer xmPushActionContainer = new XmPushActionContainer();
        object2 = arrby;
        if (bl) {
            if (TextUtils.isEmpty((CharSequence)(object = AppInfoHolder.getInstance((Context)object).getRegSecret()))) {
                MyLog.warn("regSecret is empty, return null");
                return null;
            }
            object = Base64Coder.decode((String)object);
            try {
                object2 = PushContainerHelper.MIPushEncrypt((byte[])object, arrby);
            }
            catch (Exception var0_1) {
                MyLog.e("encryption error. ");
                object2 = arrby;
            }
        }
        object = new Target();
        object.channelId = 5;
        object.userId = "fakeid";
        xmPushActionContainer.setTarget((Target)object);
        xmPushActionContainer.setPushAction(ByteBuffer.wrap((byte[])object2));
        xmPushActionContainer.setAction(actionType);
        xmPushActionContainer.setIsRequest(true);
        xmPushActionContainer.setPackageName(string2);
        xmPushActionContainer.setEncryptAction(bl);
        xmPushActionContainer.setAppid(string3);
        return xmPushActionContainer;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected static TBase getResponseMessageBodyFromContainer(Context object, XmPushActionContainer xmPushActionContainer) throws TException {
        TBase tBase;
        if (xmPushActionContainer.isEncryptAction()) {
            object = Base64Coder.decode(AppInfoHolder.getInstance((Context)object).getRegSecret());
            try {
                object = PushContainerHelper.MIPushDecrypt((byte[])object, xmPushActionContainer.getPushAction());
            }
            catch (Exception var0_1) {
                throw new TException("the aes decrypt failed.", var0_1);
            }
        } else {
            object = xmPushActionContainer.getPushAction();
        }
        if ((tBase = PushContainerHelper.createRespMessageFromAction(xmPushActionContainer.getAction())) != null) {
            XmPushThriftSerializeUtils.convertByteArrayToThriftObject(tBase, (byte[])object);
        }
        return tBase;
    }

}

