package com.android.mms.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;

class SlideshowActivity$2
  implements View.OnClickListener
{
  SlideshowActivity$2(SlideshowActivity paramSlideshowActivity, Uri paramUri) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent(this$0, SlideshowActivity.class);
    paramView.setData(val$msg);
    paramView.putExtra("useslide", true);
    this$0.startActivity(paramView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */