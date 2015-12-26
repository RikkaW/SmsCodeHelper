package com.android.mms.ui;

import aan;
import aba;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.view.EditTextEx;
import com.android.mms.view.MzContactHeaderWidget;
import gm;
import gq;
import gr;
import gr.b;
import hb;
import java.util.Iterator;
import java.util.List;
import lx;
import nd.c;
import tk;
import vv;
import vx;

public final class ComposeMessageActivity$a
  extends gr.b
{
  public ComposeMessageActivity$a(ComposeMessageActivity paramComposeMessageActivity, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    default: 
      super.handleMessage(paramMessage);
      return;
    }
    ComposeMessageActivity.c(a, 9527);
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    super.onDeleteComplete(paramInt1, paramObject, paramInt2);
    switch (paramInt1)
    {
    }
    while (paramInt1 == 1801)
    {
      paramObject = a.a.h();
      Log.d("Mms/compose", "onDeleteComplete(DELETE_CONVERSATION_TOKEN), mWorkingMessage.isDiscarded() = " + a.c.r());
      a.c.q();
      a.c.p();
      if (paramObject != null)
      {
        paramObject = ((gq)paramObject).iterator();
        for (;;)
        {
          if (((Iterator)paramObject).hasNext())
          {
            ((gm)((Iterator)paramObject).next()).a();
            continue;
            a.a.a(0);
            MessagingNotification.a(a, -2L, false);
            ComposeMessageActivity.Y(a);
            break;
            paramObject = (List)paramObject;
            paramInt2 = 0;
            if (paramInt2 < ((List)paramObject).size())
            {
              if (((vv)((List)paramObject).get(paramInt2)).E()) {
                hb.a(((vv)((List)paramObject).get(paramInt2)).K());
              }
              for (;;)
              {
                paramInt2 += 1;
                break;
                hb.a(((vv)((List)paramObject).get(paramInt2)).Q());
                MmsApp.c().e().a(gett);
              }
            }
            MessagingNotification.a(a, -2L, false);
            ComposeMessageActivity.Y(a);
            ComposeMessageActivity.J(a);
            break;
          }
        }
      }
      gr.b(MmsApp.c());
      a.finish();
    }
    while (paramInt1 != 9700) {
      return;
    }
    ComposeMessageActivity.c(a, 9528);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    int i = -1;
    if (a.isFinishing())
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      return;
    }
    long l;
    int j;
    String str;
    boolean bool;
    switch (paramInt)
    {
    default: 
      return;
    case 9527: 
      a.a.a(false);
      l = ((Long)paramObject).longValue();
      if (Log.isLoggable("Mms:app", 2)) {
        ComposeMessageActivity.b("##### onQueryComplete: msg history result for threadId " + l);
      }
      if (a.a.e() == 0L)
      {
        a.finish();
        return;
      }
      if (l != a.a.e())
      {
        ComposeMessageActivity.b("onQueryComplete: msg history query result is for threadId " + l + ", but mConversation has threadId " + a.a.e() + " starting a new query");
        if (paramCursor != null) {
          paramCursor.close();
        }
        ComposeMessageActivity.S(a);
        return;
      }
      ComposeMessageActivity.T(a);
      j = a.b.getCount();
      if ((paramCursor != null) && (paramCursor.getCount() == 0) && (j > 0)) {
        a.finish();
      }
      l = a.getIntent().getLongExtra("select_id", 0L);
      paramObject = a.getIntent().getStringExtra("group_msg_id");
      str = a.getIntent().getStringExtra("msg_type");
      if ((l != 0L) && (paramCursor != null))
      {
        a.getIntent().putExtra("select_id", 0L);
        paramCursor.moveToPosition(-1);
        if (str == null)
        {
          bool = false;
          for (;;)
          {
            label331:
            if (paramCursor.moveToNext()) {
              if (bool == paramCursor.getString(0).equals("mms")) {
                if (bool)
                {
                  if (paramCursor.getLong(1) != l) {
                    continue;
                  }
                  paramInt = paramCursor.getPosition();
                }
              }
            }
          }
        }
      }
      break;
    }
    for (;;)
    {
      if (a.isFinishing())
      {
        if (paramCursor == null) {
          break;
        }
        paramCursor.close();
        return;
        bool = str.equals("mms");
        break label331;
        str = paramCursor.getString(37);
        if ((TextUtils.isEmpty(str)) || (!str.equals(paramObject))) {
          break label331;
        }
        paramInt = paramCursor.getPosition();
        continue;
      }
      a.b.a(ComposeMessageActivity.U(a));
      a.b.changeCursor(paramCursor);
      int k = a.b.getCount();
      if (paramInt != -1)
      {
        ComposeMessageActivity.R(a).setSelection(paramInt);
        label510:
        if (j != k) {
          ComposeMessageActivity.R(a).a(paramCursor);
        }
        if ((paramCursor != null) && (aba.a().b()) && (aba.a().d()) && (!a.r()) && (!a.p()) && (a.j != null) && (paramCursor.moveToLast()))
        {
          paramObject = a.b;
          l = paramCursor.getLong(30);
          paramObject = a.b;
          if (!"sms".equals(paramCursor.getString(0))) {
            break label913;
          }
          paramCursor = a.j;
          if (l != 256L) {
            break label906;
          }
        }
      }
      label906:
      for (paramObject = nd.c.c;; paramObject = nd.c.a)
      {
        if (l == 256L) {
          i = 0;
        }
        paramCursor.a((nd.c)paramObject, i);
        a.a.a(a.b.getCount());
        if (a.a.k() > 0) {
          a.a.e(false);
        }
        if (((ComposeMessageActivity.k(a) != null) && (ComposeMessageActivity.k(a).isFocused()) && (ComposeMessageActivity.k(a).isShown())) || (ComposeMessageActivity.l(a) == null) || (!ComposeMessageActivity.l(a).isShown()) || (ComposeMessageActivity.l(a).isFocused())) {
          break;
        }
        ComposeMessageActivity.l(a).requestFocus();
        return;
        if (j > k)
        {
          ComposeMessageActivity.q(a, false);
          break label510;
        }
        if (ComposeMessageActivity.V(a))
        {
          ComposeMessageActivity.b(a, ComposeMessageActivity.V(a), 0);
          ComposeMessageActivity.q(a, false);
          break label510;
        }
        if ((j == k - 1) && (ComposeMessageActivity.v(a)))
        {
          ComposeMessageActivity.b(a, true, 0);
          break label510;
        }
        if (j == k) {
          break label510;
        }
        ComposeMessageActivity.W(a).c();
        break label510;
      }
      label913:
      paramCursor = a.j;
      if (l == 2L) {}
      for (paramObject = nd.c.c;; paramObject = nd.c.a)
      {
        if (l == 2L) {
          i = 1;
        }
        paramCursor.a((nd.c)paramObject, i);
        break;
      }
      l = ((Long)paramObject).longValue();
      if (Log.isLoggable("Mms:app", 2)) {
        ComposeMessageActivity.b("##### onQueryComplete (after delete): msg history result for threadId " + l);
      }
      if (paramCursor == null) {
        break;
      }
      if ((l > 0L) && (paramCursor.getCount() == 0))
      {
        ComposeMessageActivity.b("##### MESSAGE_LIST_QUERY_AFTER_DELETE_TOKEN clearing thread id: " + l);
        paramObject = gr.a(MmsApp.c(), l, false);
        if (paramObject != null)
        {
          ((gr)paramObject).g();
          ((gr)paramObject).b(false);
        }
        ComposeMessageActivity.a(a, new tk(this));
      }
      paramCursor.close();
      return;
      paramInt = -1;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */