package com.android.mms.view;

import aaa;
import acx;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.ImageView;
import gr;

public class ConversationListItem$a
  extends AsyncTask<Void, Void, Bitmap>
{
  boolean a;
  
  private ConversationListItem$a(ConversationListItem paramConversationListItem) {}
  
  protected Bitmap a(Void... paramVarArgs)
  {
    return aaa.a(ConversationListItem.a(b), ConversationListItem.b(b).h(), b.x, b.x);
  }
  
  protected void a(Bitmap paramBitmap)
  {
    b.h.setImageBitmap(paramBitmap);
    if (!a) {
      return;
    }
    b.h.setVisibility(0);
    b.i.setLongClickable(false);
    b.i.setOnClickListener(new acx(this));
    b.b = null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.ConversationListItem.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */