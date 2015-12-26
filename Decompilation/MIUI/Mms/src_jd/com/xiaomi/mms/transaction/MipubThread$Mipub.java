package com.xiaomi.mms.transaction;

import android.net.Uri;
import com.xiaomi.mms.mx.fw.futils.HmsConstants;

public abstract interface MipubThread$Mipub
{
  public static final Uri CONTENT_URI;
  public static final Uri CONTENT_URI_DETAIL;
  public static final Uri CONTENT_URI_MIPUB_DETAIL_ITEM = Uri.withAppendedPath(CONTENT_URI_DETAIL, "mipub_detail_item");
  public static final Uri CONTENT_URI_MIPUB_DETAIL_ITEM_NET;
  public static final String[] STATUS_PROJECTION = { "thread_id", "date", "_id", "snippet", "address" };
  
  static
  {
    CONTENT_URI = HmsConstants.MIPUB_MSG_URI;
    CONTENT_URI_DETAIL = Uri.parse("content://com.miui.mipub.MipubDetailProvider/");
    CONTENT_URI_MIPUB_DETAIL_ITEM_NET = Uri.withAppendedPath(CONTENT_URI_DETAIL, "mipub_detail_item_net");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MipubThread.Mipub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */