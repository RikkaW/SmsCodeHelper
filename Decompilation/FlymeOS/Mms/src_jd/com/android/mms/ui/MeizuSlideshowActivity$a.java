package com.android.mms.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ArrayAdapter;
import com.android.mms.view.MmsSlidePartitionItemLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lm;
import vn;
import vp.a;

public class MeizuSlideshowActivity$a
  extends ArrayAdapter<vp.a>
  implements AbsListView.RecyclerListener
{
  private final LayoutInflater b;
  
  public MeizuSlideshowActivity$a(Context paramContext, ArrayList<vp.a> paramArrayList)
  {
    super(paramArrayList, 2130968736, localList);
    b = LayoutInflater.from(paramArrayList);
  }
  
  private int a(vp.a parama)
  {
    if ("text".equals(a)) {}
    do
    {
      return 1;
      if ("img".equals(a)) {
        return 2;
      }
      if ("audio".equals(a)) {
        return 3;
      }
      if ("video".equals(a)) {
        return 4;
      }
      if ("ref".equals(a)) {
        return 5;
      }
    } while (!"file".equals(a));
    return 6;
  }
  
  private View a(Context paramContext, int paramInt, ViewGroup paramViewGroup)
  {
    paramContext = (MmsSlidePartitionItemLayout)b.inflate(2130968733, paramViewGroup, false);
    switch (a((vp.a)getItem(paramInt)))
    {
    default: 
      return paramContext;
    case 1: 
      paramContext.a(2130968736, 1, "text", a.c);
      return paramContext;
    case 2: 
      paramContext.a(2130968734, 2, "img", a.c);
      return paramContext;
    case 3: 
      paramContext.a(2130968730, 3, "audio", a.c);
      return paramContext;
    case 4: 
      paramContext.a(2130968738, 4, "video", a.c);
      return paramContext;
    case 5: 
      paramContext.a(2130968737, 5, "ref", a.c);
      return paramContext;
    }
    paramContext.a(2130968732, 6, "file", a.c);
    return paramContext;
  }
  
  private void a(View paramView, int paramInt)
  {
    vp.a locala;
    Bitmap localBitmap;
    if ((paramView instanceof MmsSlidePartitionItemLayout))
    {
      locala = (vp.a)getItem(paramInt);
      int i = a(locala);
      if ((i != 4) && (i != 2)) {
        break label146;
      }
      if ((locala != null) && (c != null) && (c.k() != null))
      {
        if (MeizuSlideshowActivity.a(a) == null) {
          MeizuSlideshowActivity.a(a, new HashMap());
        }
        localBitmap = (Bitmap)MeizuSlideshowActivity.a(a).get(c.k().toString());
        if (localBitmap != null) {
          break label133;
        }
        ((MmsSlidePartitionItemLayout)paramView).a(paramInt, locala, null);
        a(locala, paramView);
      }
    }
    return;
    label133:
    ((MmsSlidePartitionItemLayout)paramView).a(paramInt, locala, localBitmap);
    return;
    label146:
    ((MmsSlidePartitionItemLayout)paramView).a(paramInt, locala, null);
  }
  
  private void a(vp.a parama, View paramView)
  {
    new Thread(new vn(this, parama, paramView), "SlideshowGetBitmap").start();
  }
  
  public int getItemViewType(int paramInt)
  {
    return a((vp.a)getItem(paramInt));
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null) {
      localView = a(a, paramInt, paramViewGroup);
    }
    a(localView, paramInt);
    return localView;
  }
  
  public int getViewTypeCount()
  {
    return 6;
  }
  
  public boolean isEnabled(int paramInt)
  {
    return false;
  }
  
  public void onMovedToScrapHeap(View paramView)
  {
    if ((paramView instanceof MmsSlidePartitionItemLayout)) {
      ((MmsSlidePartitionItemLayout)paramView).a();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MeizuSlideshowActivity.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */