package com.android.mms.ui;

import aan;
import abl;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.os.Message;
import android.provider.MzContactsContract.MzNetContacts;
import android.provider.Telephony.Mms;
import android.provider.Telephony.MmsSms;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import gf;
import gm;
import gq;
import gr;
import gr.b;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import vm;

public class MeizuSearchActivity$a
  extends gr.b
{
  public MeizuSearchActivity$a(MeizuSearchActivity paramMeizuSearchActivity, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  private void a(long paramLong)
  {
    Object localObject = gr.a(MmsApp.c(), paramLong, false);
    if (localObject != null)
    {
      localObject = ((gr)localObject).h().iterator();
      while (((Iterator)localObject).hasNext()) {
        ((gm)((Iterator)localObject).next()).a();
      }
    }
  }
  
  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (what)
    {
    }
    do
    {
      return;
      MeizuSearchActivity.d(a).removeCallbacks(a.d);
      MeizuSearchActivity.d(a).postDelayed(a.d, 500L);
      if (MeizuSearchActivity.n(a) == null) {
        MeizuSearchActivity.a(a, new vm(this, new Handler()));
      }
      for (;;)
      {
        MeizuSearchActivity.a(a, Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", MeizuSearchActivity.m(a)).appendQueryParameter("meizuMmsSearch", "true").build());
        paramMessage = MeizuSearchActivity.a(a, a.getApplicationContext(), MeizuSearchActivity.m(a));
        MeizuSearchActivity.d(a).startQuery(MeizuSearchActivity.p(a), null, MeizuSearchActivity.q(a), null, null, new String[] { paramMessage }, null);
        a.getContentResolver().registerContentObserver(MeizuSearchActivity.q(a), true, MeizuSearchActivity.n(a));
        return;
        a.getContentResolver().unregisterContentObserver(MeizuSearchActivity.n(a));
      }
      MeizuSearchActivity.r(a).setVisibility(8);
    } while (MeizuSearchActivity.c.a(MeizuSearchActivity.s(a)));
    MeizuSearchActivity.t(a).setVisibility(0);
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    super.onDeleteComplete(paramInt1, paramObject, paramInt2);
    switch (paramInt1)
    {
    }
    do
    {
      do
      {
        return;
        if (paramInt2 <= 0)
        {
          MeizuSearchActivity.u(a);
          MeizuSearchActivity.b(a, false);
        }
        paramObject = (Collection[])paramObject;
      } while (paramObject.length != 3);
      if (paramObject[0].size() > 0) {}
      for (paramInt1 = 1;; paramInt1 = 0)
      {
        Iterator localIterator = paramObject[0].iterator();
        while (localIterator.hasNext())
        {
          a(((Long)localIterator.next()).longValue());
          MmsApp.c().e().a(ContentUris.withAppendedId(Telephony.MmsSms.CONTENT_CONVERSATIONS_URI, ((Long)localIterator.next()).longValue()));
        }
      }
      paramObject = paramObject[1].iterator();
      while (((Iterator)paramObject).hasNext()) {
        MmsApp.c().e().a(ContentUris.withAppendedId(Telephony.Mms.CONTENT_URI, ((Long)((Iterator)paramObject).next()).longValue()));
      }
      if (paramInt1 != 0) {
        gr.b(MmsApp.c());
      }
      MessagingNotification.a(a, -2L, false);
      MessagingNotification.e(a);
    } while (paramInt2 <= 0);
    MeizuSearchActivity.d(a, false);
    MeizuSearchActivity.f(a);
    MeizuSearchActivity.d(a, true);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    MeizuSearchActivity.d(a).removeCallbacks(a.d);
    switch (paramInt)
    {
    case 4: 
    default: 
      if (MeizuSearchActivity.v(a))
      {
        MeizuSearchActivity.u(a);
        MeizuSearchActivity.b(a, false);
      }
      if ((paramInt == MeizuSearchActivity.p(a)) && (!MeizuSearchActivity.s(a).a()))
      {
        MeizuSearchActivity.t(a).setVisibility(8);
        MeizuSearchActivity.r(a).setVisibility(0);
        MeizuSearchActivity.c(a, false);
        if (paramCursor != null)
        {
          MeizuSearchActivity.g(a).a(paramCursor, MeizuSearchActivity.m(a));
          paramInt = paramCursor.getCount();
          if (MeizuSearchActivity.h(a) != null)
          {
            if (MeizuSearchActivity.w(a))
            {
              MeizuSearchActivity.d(a, false);
              MeizuSearchActivity.h(a).c();
            }
            MeizuSearchActivity.h(a).a(paramInt);
          }
        }
        if (MeizuSearchActivity.x(a))
        {
          MeizuSearchActivity.e(a, false);
          if ((MeizuSearchActivity.y(a) > 0) && (MeizuSearchActivity.g(a).getCount() > MeizuSearchActivity.y(a)))
          {
            MeizuSearchActivity.c(a).setSelection(MeizuSearchActivity.y(a));
            MeizuSearchActivity.a(a, 0);
          }
        }
        return;
      }
      break;
    }
    for (int i = 1; ((paramObject != null) || ((paramObject instanceof Collection))) && (paramCursor != null); i = 0)
    {
      HashMap localHashMap = (HashMap)paramObject;
      paramCursor.close();
      paramCursor = localHashMap.keySet().iterator();
      int j = localHashMap.size();
      long[] arrayOfLong = new long[j];
      paramInt = 0;
      while (paramInt < j)
      {
        arrayOfLong[paramInt] = -1L;
        paramInt += 1;
      }
      for (;;)
      {
        if (paramCursor.hasNext())
        {
          long l = MeizuSearchActivity.a((String)paramCursor.next(), 1, 0L, 5);
          j = 0;
          for (;;)
          {
            if ((j >= paramInt) || (l == arrayOfLong[j]))
            {
              if (j < paramInt) {
                break;
              }
              j = paramInt + 1;
              arrayOfLong[paramInt] = l;
              paramObject = gr.a(MmsApp.c(), l, true);
              if (paramObject == null) {
                break label517;
              }
              paramObject = ((gr)paramObject).h();
              if (((gq)paramObject).size() == 1) {
                break label469;
              }
              paramCursor.remove();
              paramInt = j;
              break;
            }
            j += 1;
          }
          label469:
          gm localgm = (gm)((gq)paramObject).get(0);
          String str = localgm.d();
          if (MzContactsContract.MzNetContacts.isYPContact(localgm.n())) {}
          for (paramObject = ((gm)((gq)paramObject).get(0)).g();; paramObject = null)
          {
            abl.a(str, (String)paramObject, null);
            label517:
            paramInt = j;
            break;
          }
        }
        if ((localHashMap.size() == 0) || (i == 0))
        {
          MeizuSearchActivity.u(a);
          MeizuSearchActivity.c(a).invalidateViews();
          return;
        }
        if (i == 0) {
          break;
        }
        MeizuSearchActivity.a(a, localHashMap, true);
        return;
        if (paramCursor == null) {
          break;
        }
        paramCursor.close();
        return;
        paramInt = 0;
      }
    }
  }
  
  protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    super.onUpdateComplete(paramInt1, paramObject, paramInt2);
    switch (paramInt1)
    {
    }
    do
    {
      return;
    } while (paramInt2 <= 0);
    Log.d("MeizuSearchActivity", "--更新未读标识：result = " + paramInt2);
    MeizuSearchActivity.f(a);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MeizuSearchActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */