package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class ResourceCursorAdapter
  extends CursorAdapter
{
  private int mDropDownLayout;
  private LayoutInflater mInflater;
  private int mLayout;
  
  @Deprecated
  public ResourceCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor)
  {
    super(paramContext, paramCursor);
    mDropDownLayout = paramInt;
    mLayout = paramInt;
    mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public ResourceCursorAdapter(Context paramContext, int paramInt1, Cursor paramCursor, int paramInt2)
  {
    super(paramContext, paramCursor, paramInt2);
    mDropDownLayout = paramInt1;
    mLayout = paramInt1;
    mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public ResourceCursorAdapter(Context paramContext, int paramInt, Cursor paramCursor, boolean paramBoolean)
  {
    super(paramContext, paramCursor, paramBoolean);
    mDropDownLayout = paramInt;
    mLayout = paramInt;
    mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public View newDropDownView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return mInflater.inflate(mDropDownLayout, paramViewGroup, false);
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return mInflater.inflate(mLayout, paramViewGroup, false);
  }
  
  public void setDropDownViewResource(int paramInt)
  {
    mDropDownLayout = paramInt;
  }
  
  public void setViewResource(int paramInt)
  {
    mLayout = paramInt;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.widget.ResourceCursorAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */