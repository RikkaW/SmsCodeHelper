package com.xiaomi.mms.mx.fw.xmppmsg;

import android.text.TextUtils;
import com.xiaomi.mms.mx.fw.faccount.XiaoMiJIDUtils;
import com.xiaomi.smack.packet.CommonPacketExtension;
import java.util.ArrayList;
import java.util.List;

public class AckMessage
  extends MessageDecor
{
  private String mAckEleName;
  private String mExtId;
  private boolean mIsGlobal = false;
  private String mSeq;
  
  public AckMessage(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean)
  {
    mAckEleName = paramString3;
    mExtId = paramString4;
    mSeq = paramString5;
    mIsGlobal = paramBoolean;
    buildMessage(XiaoMiJIDUtils.getUserIDWithResId(), paramString1, paramString2);
  }
  
  public List<CommonPacketExtension> createCommonPacketExtensions()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    localArrayList2.add("id");
    localArrayList3.add(mExtId);
    if (!TextUtils.isEmpty(mSeq))
    {
      localArrayList2.add("seq");
      localArrayList3.add(mSeq);
    }
    localArrayList1.add(new CommonPacketExtension(mAckEleName, "xm:chat", localArrayList2, localArrayList3));
    if (mIsGlobal) {
      localArrayList1.add(createGlobalPacketExtensions());
    }
    return localArrayList1;
  }
  
  protected CommonPacketExtension createGlobalPacketExtensions()
  {
    new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    CommonPacketExtension localCommonPacketExtension = new CommonPacketExtension("metadata", null, localArrayList1, localArrayList2);
    localCommonPacketExtension.appendChild(new CommonPacketExtension("global", null, localArrayList1, localArrayList2));
    return localCommonPacketExtension;
  }
  
  public void setBodyElement() {}
  
  public void setTypeAttribute()
  {
    setType("ack");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.xmppmsg.AckMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */