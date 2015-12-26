/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.fw;

import android.os.Bundle;
import com.xiaomi.smack.packet.CommonPacketExtension;
import com.xiaomi.smack.packet.Message;

public class MipubMessageUtils {
    private MipubMessageUtils() {
    }

    public static CommonPacketExtension getMsgExtension(Message message) {
        if (message == null) {
            return null;
        }
        return message.getExtension("ext").getChildByName("msg");
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean isGlobal(Message object) {
        if (object == null) {
            return false;
        }
        CommonPacketExtension commonPacketExtension = object.getExtension("metadata");
        object = null;
        if (commonPacketExtension == null) return false;
        object = commonPacketExtension.getChildByName("global");
        if (object == null) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static boolean isTemplate(Message object) {
        if (object == null) {
            return false;
        }
        CommonPacketExtension commonPacketExtension = object.getExtension("metadata");
        object = null;
        if (commonPacketExtension == null) return false;
        object = commonPacketExtension.getChildByName("template");
        if (object == null) return false;
        return true;
    }

    public static boolean isTransient(Message message) {
        if (message == null) {
            return false;
        }
        return message.toBundle().getBoolean("ext_msg_trans", false);
    }
}

