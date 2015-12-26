package com.android.mms.ui;

import android.app.AlertDialog.Builder;

class SlideshowActivity$FlatshowAdapter$3$1
  implements Runnable
{
  SlideshowActivity$FlatshowAdapter$3$1(SlideshowActivity.FlatshowAdapter.3 param3) {}
  
  public void run()
  {
    String str = MessageUtils.saveMmsPartToDisk(this$2.this$1.this$0, this$2.val$part, SlideshowActivity.FlatshowAdapter.access$100(this$2.this$1));
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this$2.this$1.this$0);
    localBuilder.setTitle(2131362012);
    localBuilder.setIconAttribute(16843605);
    if (str != null) {
      localBuilder.setMessage(this$2.this$1.this$0.getString(2131362013, new Object[] { str }));
    }
    for (;;)
    {
      localBuilder.setNeutralButton(17039370, null);
      localBuilder.show();
      return;
      localBuilder.setMessage(2131362014);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.FlatshowAdapter.3.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */