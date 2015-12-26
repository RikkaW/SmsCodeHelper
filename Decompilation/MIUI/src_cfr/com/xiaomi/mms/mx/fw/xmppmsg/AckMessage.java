/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.xiaomi.mms.mx.fw.xmppmsg;

import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils;
import com.xiaomi.mms.mx.fw.xmppmsg.MessageDecor;
import com.xiaomi.smack.packet.CommonPacketExtension;
import java.util.ArrayList;
import java.util.List;

public class AckMessage
extends MessageDecor {
    private String mAckEleName;
    private String mExtId;
    private boolean mIsGlobal = false;
    private String mSeq;

    public AckMessage(String string2, String string3, String string4, String string5, String string6, boolean bl) {
        this.mAckEleName = string4;
        this.mExtId = string5;
        this.mSeq = string6;
        this.mIsGlobal = bl;
        this.buildMessage(XiaoMiJIDUtils.getUserIDWithResId(), string2, string3);
    }

    @Override
    public List<CommonPacketExtension> createCommonPacketExtensions() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList2.add((Object)"id");
        arrayList3.add((Object)this.mExtId);
        if (!TextUtils.isEmpty((CharSequence)this.mSeq)) {
            arrayList2.add((Object)"seq");
            arrayList3.add((Object)this.mSeq);
        }
        arrayList.add((Object)new CommonPacketExtension(this.mAckEleName, "xm:chat", (List<String>)arrayList2, (List<String>)arrayList3));
        if (this.mIsGlobal) {
            arrayList.add((Object)this.createGlobalPacketExtensions());
        }
        return arrayList;
    }

    protected CommonPacketExtension createGlobalPacketExtensions() {
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        CommonPacketExtension commonPacketExtension = new CommonPacketExtension("metadata", null, (List<String>)arrayList, (List<String>)arrayList2);
        commonPacketExtension.appendChild(new CommonPacketExtension("global", null, (List<String>)arrayList, (List<String>)arrayList2));
        return commonPacketExtension;
    }

    @Override
    public void setBodyElement() {
    }

    @Override
    public void setTypeAttribute() {
        this.setType("ack");
    }
}

