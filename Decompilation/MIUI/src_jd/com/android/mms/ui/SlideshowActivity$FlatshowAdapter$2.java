package com.android.mms.ui;

import android.content.ActivityNotFoundException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

class SlideshowActivity$FlatshowAdapter$2
  implements View.OnClickListener
{
  SlideshowActivity$FlatshowAdapter$2(SlideshowActivity.FlatshowAdapter paramFlatshowAdapter, SimplePduPart paramSimplePduPart) {}
  
  public void onClick(View paramView)
  {
    try
    {
      this$1.this$0.startActivity(val$part.getIntent());
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      Toast.makeText(this$1.this$0, this$1.this$0.getString(2131362136), 0).show();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.FlatshowAdapter.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */