package com.android.mms.ui;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

class PreviewImageLoader$LoaderThread$1
  implements Runnable
{
  PreviewImageLoader$LoaderThread$1(PreviewImageLoader.LoaderThread paramLoaderThread, long paramLong1, Drawable paramDrawable, long paramLong2, ArrayList paramArrayList) {}
  
  public void run()
  {
    while (PreviewImageLoader.access$300(this$1.this$0).size() >= 31) {
      PreviewImageLoader.access$300(this$1.this$0).remove(PreviewImageLoader.access$300(this$1.this$0).eldest().getKey());
    }
    Object localObject = new PreviewImageLoader.CacheNode(this$1.this$0, null);
    drawable = val$drawable;
    timestamp = val$dataTs;
    PreviewImageLoader.access$300(this$1.this$0).put(Long.valueOf(val$finalMsgId), localObject);
    localObject = val$finalList.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((ImageView)((Iterator)localObject).next()).setImageDrawable(val$drawable);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PreviewImageLoader.LoaderThread.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */