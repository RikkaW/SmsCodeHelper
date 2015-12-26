package com.meizu.common.app;

import android.app.Activity;

final class SlideNoticeManagerService$NoticeRecord
{
  final Activity activity;
  final SlideNoticeManagerService.NoticeCallBack callback;
  int duration;
  CharSequence message;
  
  SlideNoticeManagerService$NoticeRecord(CharSequence paramCharSequence, int paramInt, SlideNoticeManagerService.NoticeCallBack paramNoticeCallBack)
  {
    message = paramCharSequence;
    duration = paramInt;
    callback = paramNoticeCallBack;
    activity = null;
  }
  
  SlideNoticeManagerService$NoticeRecord(CharSequence paramCharSequence, int paramInt, SlideNoticeManagerService.NoticeCallBack paramNoticeCallBack, Activity paramActivity)
  {
    message = paramCharSequence;
    duration = paramInt;
    callback = paramNoticeCallBack;
    activity = paramActivity;
  }
  
  public final String toString()
  {
    return "NoticeRecord{" + Integer.toHexString(System.identityHashCode(this)) + " callback=" + callback + " duration=" + duration;
  }
  
  void update(int paramInt)
  {
    duration = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.app.SlideNoticeManagerService.NoticeRecord
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */