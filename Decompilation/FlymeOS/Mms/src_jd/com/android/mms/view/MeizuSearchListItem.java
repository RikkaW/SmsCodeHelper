package com.android.mms.view;

import aaa;
import acz;
import ada;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.mms.MmsApp;
import gm;
import gm.b;
import gq;
import gr;
import java.util.Iterator;
import wd;

public class MeizuSearchListItem
  extends MmsGridLayout
  implements gm.b
{
  private static int a = 0;
  private static int b = 0;
  private String A;
  private int B = 1;
  private boolean C;
  private gr D;
  private String E;
  private String F;
  private Context G;
  private int z;
  
  public MeizuSearchListItem(Context paramContext)
  {
    super(paramContext);
    G = paramContext;
  }
  
  public MeizuSearchListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet, aaa.a(1), 2131886592);
    G = paramContext;
  }
  
  private void a(boolean paramBoolean)
  {
    String str = b(D);
    if ((z == 1) || (z == 2)) {
      d.setText(str);
    }
    for (;;)
    {
      b(paramBoolean);
      return;
      d.b(str, A);
    }
  }
  
  public static boolean a(long paramLong)
  {
    return (paramLong != 1L) && (paramLong != 2L);
  }
  
  private String b(gr paramgr)
  {
    if (paramgr == null) {
      return "";
    }
    Object localObject = paramgr.h();
    paramgr = new StringBuilder();
    int j = ((gq)localObject).size();
    localObject = ((gq)localObject).iterator();
    int i = 0;
    for (;;)
    {
      if (((Iterator)localObject).hasNext())
      {
        paramgr.append(((gm)((Iterator)localObject).next()).g());
        if (i < j - 1) {
          paramgr.append(", ");
        }
        i += 1;
        if (i < 8) {}
      }
      else
      {
        return paramgr.toString();
      }
    }
  }
  
  private void b(boolean paramBoolean)
  {
    if (!a(h)) {}
    do
    {
      return;
      h.setImageBitmap(aaa.a(G, D.h(), x, x));
    } while (!paramBoolean);
    Iterator localIterator = D.h().iterator();
    while (localIterator.hasNext()) {
      gm.a(((gm)localIterator.next()).d(), false);
    }
    h.setLongClickable(false);
    h.setOnClickListener(new acz(this));
  }
  
  public final void a(Cursor paramCursor, String paramString)
  {
    z = paramCursor.getInt(7);
    A = paramString;
    switch (z)
    {
    default: 
      C = false;
      D = gr.a(MmsApp.c(), paramCursor.getLong(1), true);
      B = paramCursor.getInt(6);
      j.setVisibility(8);
      a(true);
      f.setText(wd.a(getContext(), paramCursor.getLong(4)));
      paramString = paramCursor.getString(3);
      if ((z == 1) || (z == 2))
      {
        c.a(paramString, A);
        label150:
        long l1 = paramCursor.getLong(1);
        long l2 = paramCursor.getLong(0);
        E = paramCursor.getString(11);
        F = paramCursor.getString(13);
        paramCursor = new Bundle();
        paramCursor.putLong("thread_id", l1);
        if (!a(z))
        {
          paramCursor.putString("highlight", A);
          paramCursor.putLong("select_id", l2);
          if (z != 1) {
            break label401;
          }
          paramCursor.putString("msg_type", "sms");
        }
      }
      break;
    }
    for (;;)
    {
      paramCursor.putString("group_msg_id", E);
      if (!TextUtils.isEmpty(F)) {
        paramCursor.putString("conversation_last_imsi", F);
      }
      setTag(paramCursor);
      gm.a(this);
      a(D);
      return;
      if (paramCursor.getInt(8) == 3) {}
      for (boolean bool = true;; bool = false)
      {
        C = bool;
        break;
      }
      if (paramCursor.getInt(8) == 3) {}
      for (bool = true;; bool = false)
      {
        C = bool;
        break;
      }
      paramString = wd.a(paramCursor, 3, 5);
      if (TextUtils.isEmpty(paramString))
      {
        c.setText(D.l());
        break label150;
      }
      c.setText(paramString);
      break label150;
      label401:
      paramCursor.putString("msg_type", "mms");
    }
  }
  
  public void a(gm paramgm)
  {
    if ((D != null) && (D.h().contains(paramgm))) {
      d.post(new ada(this));
    }
  }
  
  public final boolean a()
  {
    return B == 0;
  }
  
  public final void b() {}
  
  public final void c()
  {
    gm.b(this);
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    gm.b(this);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    if (a == 0) {
      a = getResources().getDimensionPixelSize(2131559498);
    }
    if (b == 0) {
      b = getResources().getDimensionPixelSize(2131559497);
    }
    c.setSingleLine(false);
    c.setMaxLines(2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.view.MeizuSearchListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */