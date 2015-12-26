package com.android.mms.ui;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.FestivalDatabase;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;

public class FestivalSmsListAdapter
  extends CursorAdapter
{
  private long mCategoryId;
  private Context mContext;
  private LayoutInflater mInflater;
  private ArrayList<Integer> mOrder = Lists.newArrayList();
  
  public FestivalSmsListAdapter(Context paramContext, long paramLong)
  {
    super(paramContext, null);
    mContext = paramContext;
    mCategoryId = paramLong;
    mInflater = ((LayoutInflater)mContext.getSystemService("layout_inflater"));
  }
  
  private void internalRequery()
  {
    Object localObject = FestivalDatabase.getInstance();
    String str = "category_id=" + mCategoryId;
    localObject = ((FestivalDatabase)localObject).query("messages", new String[] { "_id", "text" }, str, null, null, null, null);
    mOrder.clear();
    if (localObject != null)
    {
      int i = 0;
      while (i < ((Cursor)localObject).getCount())
      {
        mOrder.add(Integer.valueOf(i));
        i += 1;
      }
    }
    changeCursor((Cursor)localObject);
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    paramContext = getItemMessage(paramCursor.getPosition());
    ((TextView)paramView.getTag()).setText(paramContext);
  }
  
  protected void close()
  {
    changeCursor(null);
    mOrder.clear();
  }
  
  public int getCount()
  {
    return mOrder.size();
  }
  
  public Object getItem(int paramInt)
  {
    return super.getItem(((Integer)mOrder.get(paramInt)).intValue());
  }
  
  public String getItemMessage(int paramInt)
  {
    Cursor localCursor = (Cursor)getItem(paramInt);
    if (localCursor == null) {
      return null;
    }
    return localCursor.getString(1);
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    paramContext = mInflater.inflate(2130968616, null, false);
    paramContext.setTag(paramContext.findViewById(2131820645));
    return paramContext;
  }
  
  public void requery()
  {
    try
    {
      internalRequery();
      return;
    }
    catch (SQLiteDatabaseCorruptException localSQLiteDatabaseCorruptException)
    {
      Toast.makeText(mContext, 2131362061, 1).show();
    }
  }
  
  public void shuffle()
  {
    Collections.shuffle(mOrder);
    notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FestivalSmsListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */