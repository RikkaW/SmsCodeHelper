package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.android.mms.LogTag;
import com.android.mms.data.FestivalDatabase;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;

class FestivalFragment$4
  extends AsyncTask<Void, Void, ArrayList<LinearLayout>>
{
  FestivalFragment$4(FestivalFragment paramFestivalFragment) {}
  
  protected ArrayList<LinearLayout> doInBackground(Void... paramVarArgs)
  {
    LogTag.verbose("Loading categories.", new Object[0]);
    LayoutInflater localLayoutInflater = FestivalFragment.access$300(this$0).getLayoutInflater();
    int m = this$0.getResources().getDimensionPixelSize(2131427373);
    int n = this$0.getResources().getDimensionPixelSize(2131427374);
    Cursor localCursor = FestivalDatabase.getInstance().query("categories LEFT JOIN data ON categories.image_id=data._id", new String[] { "category_id", "row", "title", "data", "sms_count" }, null, null, null, null, null);
    if (localCursor == null)
    {
      LogTag.error("Null cursor while loading categories", new Object[0]);
      return null;
    }
    ArrayList localArrayList = Lists.newArrayList();
    for (;;)
    {
      String str;
      Object localObject2;
      Object localObject1;
      try
      {
        localCursor.moveToPosition(-1);
        LogTag.verbose("Got %d results.", new Object[] { Integer.valueOf(localCursor.getCount()) });
        int i = -1;
        paramVarArgs = null;
        if (!localCursor.moveToNext()) {
          break;
        }
        if (Thread.currentThread().isInterrupted())
        {
          LogTag.warn("Load process interrupted.", new Object[0]);
          return null;
        }
        final long l = localCursor.getLong(0);
        str = localCursor.getString(2);
        localObject2 = localCursor.getBlob(3);
        localObject1 = null;
        if (localObject2 != null)
        {
          localObject1 = FestivalFragment.makeRoundImage(BitmapFactory.decodeByteArray((byte[])localObject2, 0, localObject2.length), 10, 10);
          localObject1 = new BitmapDrawable(this$0.getResources(), (Bitmap)localObject1);
        }
        localObject2 = localCursor.getString(4);
        int k = localCursor.getInt(1);
        int j = i;
        if (k != i)
        {
          j = k;
          paramVarArgs = (LinearLayout)localLayoutInflater.inflate(2130968613, null);
          localArrayList.add(paramVarArgs);
        }
        if (localObject1 != null)
        {
          localObject2 = localLayoutInflater.inflate(2130968611, null);
          ((View)localObject2).setBackground((Drawable)localObject1);
          localObject1 = new LinearLayout.LayoutParams(-2, -2, 1.0F);
          ((LinearLayout.LayoutParams)localObject1).setMargins(m, m, m, m);
          ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject1);
          ((View)localObject2).setContentDescription(str);
          paramVarArgs.addView((View)localObject2);
          localObject1 = localObject2;
          if (paramVarArgs.getPaddingLeft() != n)
          {
            paramVarArgs.setPadding(n, 0, n, 0);
            localObject1 = localObject2;
          }
          ((View)localObject1).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              paramAnonymousView = new Intent(FestivalFragment.access$300(this$0), FestivalSmsList.class);
              paramAnonymousView.putExtra("category_id", l);
              Log.v("FestivalFragment", "Clicked category " + l);
              if (FestivalFragment.access$700(this$0))
              {
                paramAnonymousView.setAction("android.intent.action.PICK");
                this$0.startActivityForResult(paramAnonymousView, 1000);
                return;
              }
              paramAnonymousView.setAction("android.intent.action.VIEW");
              this$0.startActivity(paramAnonymousView);
            }
          });
          i = j;
          continue;
        }
        localObject1 = (LinearLayout)localLayoutInflater.inflate(2130968612, null);
      }
      finally
      {
        localCursor.close();
      }
      TextView localTextView1 = (TextView)((LinearLayout)localObject1).findViewById(2131820640);
      TextView localTextView2 = (TextView)((LinearLayout)localObject1).findViewById(2131820641);
      localTextView1.setText(str);
      localTextView2.setText((CharSequence)localObject2);
      ((View)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
      paramVarArgs.addView((View)localObject1);
    }
    localCursor.close();
    return localArrayList;
  }
  
  protected void onPostExecute(ArrayList<LinearLayout> paramArrayList)
  {
    if (paramArrayList != null)
    {
      FestivalFragment.access$102(this$0, false);
      FestivalFragment.access$800(this$0).removeAllViews();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        LinearLayout localLinearLayout = (LinearLayout)paramArrayList.next();
        FestivalFragment.access$800(this$0).addView(localLinearLayout);
      }
    }
    FestivalFragment.access$902(this$0, null);
    if (FestivalFragment.isAllowNetworkingDialog(FestivalFragment.access$300(this$0))) {
      FestivalFragment.access$1000(this$0);
    }
    while (!FestivalFragment.isAllowNetworking(FestivalFragment.access$300(this$0))) {
      return;
    }
    this$0.checkForUpdate(false);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalFragment.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */