package com.android.mms.fragmentstyle;

import aan;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.View;
import com.android.mms.MmsApp;
import com.android.mms.transaction.MessagingNotification;
import hb;
import java.util.List;
import jv;
import jy;
import vv;

public class FavoriteActivity$a
  extends AsyncQueryHandler
{
  public FavoriteActivity$a(FavoriteActivity paramFavoriteActivity, ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  protected void onDeleteComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return;
    }
    paramObject = (List)paramObject;
    Long[] arrayOfLong = new Long[((List)paramObject).size()];
    paramInt1 = 0;
    if (paramInt1 < ((List)paramObject).size())
    {
      if (((vv)((List)paramObject).get(paramInt1)).E()) {
        hb.a(((vv)((List)paramObject).get(paramInt1)).K());
      }
      for (;;)
      {
        arrayOfLong[paramInt1] = Long.valueOf(getf);
        paramInt1 += 1;
        break;
        hb.a(((vv)((List)paramObject).get(paramInt1)).Q());
        MmsApp.c().e().a(gett);
      }
    }
    MessagingNotification.a(a, -2L, false);
    jy.a(arrayOfLong, a);
    FavoriteActivity.d(a);
  }
  
  protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
  {
    if (paramCursor == null) {
      return;
    }
    switch (paramInt)
    {
    default: 
      return;
    }
    paramInt = paramCursor.getCount();
    if (FavoriteActivity.b(a).getEmptyView() != null)
    {
      paramObject = FavoriteActivity.b(a).getEmptyView();
      if (paramInt != 0) {
        break label126;
      }
    }
    label126:
    for (paramInt = 0;; paramInt = 8)
    {
      ((View)paramObject).setVisibility(paramInt);
      paramInt = FavoriteActivity.a(a).getCount();
      FavoriteActivity.a(a).changeCursor(paramCursor);
      int i = FavoriteActivity.a(a).getCount();
      if ((FavoriteActivity.c(a) == null) || (paramInt == i)) {
        break;
      }
      FavoriteActivity.a(a, paramCursor);
      return;
    }
  }
  
  protected void onUpdateComplete(int paramInt1, Object paramObject, int paramInt2)
  {
    switch (paramInt1)
    {
    default: 
      return;
    }
    FavoriteActivity.d(a);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.fragmentstyle.FavoriteActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */