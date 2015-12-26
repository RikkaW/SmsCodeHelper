package com.android.mms.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

class FestivalFragment$4$1
  implements View.OnClickListener
{
  FestivalFragment$4$1(FestivalFragment.4 param4, long paramLong) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent(FestivalFragment.access$300(this$1.this$0), FestivalSmsList.class);
    paramView.putExtra("category_id", val$id);
    Log.v("FestivalFragment", "Clicked category " + val$id);
    if (FestivalFragment.access$700(this$1.this$0))
    {
      paramView.setAction("android.intent.action.PICK");
      this$1.this$0.startActivityForResult(paramView, 1000);
      return;
    }
    paramView.setAction("android.intent.action.VIEW");
    this$1.this$0.startActivity(paramView);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalFragment.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */