package com.android.mms.ui;

import aaa;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.view.MmsSlidePartitionItemLayout;
import fm;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lm;
import vn;
import vp;
import vp.a;
import wd;

public class MeizuSlideshowActivity
  extends fm
{
  public static boolean d = true;
  vp a;
  Uri b;
  long c;
  private ListView e;
  private a f;
  private boolean g = false;
  private MenuItem h;
  private HashMap<String, Bitmap> i;
  
  /* Error */
  private Bitmap a(Uri paramUri)
  {
    // Byte code:
    //   0: new 39	android/media/MediaMetadataRetriever
    //   3: dup
    //   4: invokespecial 40	android/media/MediaMetadataRetriever:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_0
    //   10: aload_1
    //   11: invokevirtual 44	android/media/MediaMetadataRetriever:setDataSource	(Landroid/content/Context;Landroid/net/Uri;)V
    //   14: aload_2
    //   15: ldc2_w 45
    //   18: invokevirtual 50	android/media/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   21: astore_1
    //   22: aload_2
    //   23: invokevirtual 53	android/media/MediaMetadataRetriever:release	()V
    //   26: aload_1
    //   27: areturn
    //   28: astore_1
    //   29: aload_2
    //   30: invokevirtual 53	android/media/MediaMetadataRetriever:release	()V
    //   33: aconst_null
    //   34: areturn
    //   35: astore_1
    //   36: aconst_null
    //   37: areturn
    //   38: astore_1
    //   39: aload_2
    //   40: invokevirtual 53	android/media/MediaMetadataRetriever:release	()V
    //   43: aload_1
    //   44: athrow
    //   45: astore_2
    //   46: aload_1
    //   47: areturn
    //   48: astore_2
    //   49: goto -6 -> 43
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	MeizuSlideshowActivity
    //   0	52	1	paramUri	Uri
    //   7	33	2	localMediaMetadataRetriever	android.media.MediaMetadataRetriever
    //   45	1	2	localRuntimeException1	RuntimeException
    //   48	1	2	localRuntimeException2	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   8	22	28	java/lang/RuntimeException
    //   29	33	35	java/lang/RuntimeException
    //   8	22	38	finally
    //   22	26	45	java/lang/RuntimeException
    //   39	43	48	java/lang/RuntimeException
  }
  
  private Bitmap a(Uri paramUri, boolean paramBoolean)
  {
    if (paramUri == null) {
      localObject = null;
    }
    Bitmap localBitmap;
    do
    {
      return (Bitmap)localObject;
      localBitmap = (Bitmap)i.get(paramUri.toString());
      localObject = localBitmap;
    } while (localBitmap != null);
    if (paramBoolean) {}
    for (Object localObject = a(paramUri);; localObject = b(paramUri))
    {
      if (i == null) {
        i = new HashMap();
      }
      i.put(paramUri.toString(), localObject);
      return (Bitmap)localObject;
    }
  }
  
  private void a(Closeable paramCloseable)
  {
    if (paramCloseable == null) {
      return;
    }
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable paramCloseable)
    {
      Log.w("MeizuSlideshowActivity", "close fail", paramCloseable);
    }
  }
  
  /* Error */
  private Bitmap b(Uri paramUri)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 110	com/android/mms/ui/MeizuSlideshowActivity:getContentResolver	()Landroid/content/ContentResolver;
    //   4: aload_1
    //   5: invokevirtual 116	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   8: astore 4
    //   10: new 118	android/graphics/BitmapFactory$Options
    //   13: dup
    //   14: invokespecial 119	android/graphics/BitmapFactory$Options:<init>	()V
    //   17: astore_3
    //   18: aload_3
    //   19: getstatic 125	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   22: putfield 128	android/graphics/BitmapFactory$Options:inPreferredConfig	Landroid/graphics/Bitmap$Config;
    //   25: aload_3
    //   26: astore_2
    //   27: aload_3
    //   28: ifnonnull +11 -> 39
    //   31: new 118	android/graphics/BitmapFactory$Options
    //   34: dup
    //   35: invokespecial 119	android/graphics/BitmapFactory$Options:<init>	()V
    //   38: astore_2
    //   39: aload 4
    //   41: aconst_null
    //   42: aload_2
    //   43: invokestatic 134	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   46: astore_2
    //   47: aload_0
    //   48: aload 4
    //   50: invokespecial 136	com/android/mms/ui/MeizuSlideshowActivity:a	(Ljava/io/Closeable;)V
    //   53: aload_2
    //   54: areturn
    //   55: astore_2
    //   56: ldc 94
    //   58: new 138	java/lang/StringBuilder
    //   61: dup
    //   62: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   65: ldc -115
    //   67: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload_1
    //   71: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: aload_2
    //   78: invokestatic 151	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   81: pop
    //   82: aconst_null
    //   83: areturn
    //   84: astore_2
    //   85: ldc 94
    //   87: new 138	java/lang/StringBuilder
    //   90: dup
    //   91: invokespecial 139	java/lang/StringBuilder:<init>	()V
    //   94: ldc -103
    //   96: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_1
    //   100: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   103: ldc -101
    //   105: invokevirtual 145	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: aload_2
    //   109: invokevirtual 148	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   112: invokevirtual 149	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   115: invokestatic 158	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   118: pop
    //   119: aload_0
    //   120: aload 4
    //   122: invokespecial 136	com/android/mms/ui/MeizuSlideshowActivity:a	(Ljava/io/Closeable;)V
    //   125: aconst_null
    //   126: areturn
    //   127: astore_1
    //   128: aload_0
    //   129: aload 4
    //   131: invokespecial 136	com/android/mms/ui/MeizuSlideshowActivity:a	(Ljava/io/Closeable;)V
    //   134: aload_1
    //   135: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	MeizuSlideshowActivity
    //   0	136	1	paramUri	Uri
    //   26	28	2	localObject	Object
    //   55	23	2	localFileNotFoundException	java.io.FileNotFoundException
    //   84	25	2	localOutOfMemoryError	OutOfMemoryError
    //   17	11	3	localOptions	android.graphics.BitmapFactory.Options
    //   8	122	4	localInputStream	java.io.InputStream
    // Exception table:
    //   from	to	target	type
    //   0	10	55	java/io/FileNotFoundException
    //   10	25	84	java/lang/OutOfMemoryError
    //   31	39	84	java/lang/OutOfMemoryError
    //   39	47	84	java/lang/OutOfMemoryError
    //   10	25	127	finally
    //   31	39	127	finally
    //   39	47	127	finally
    //   85	119	127	finally
  }
  
  private void b()
  {
    Intent localIntent = getIntent();
    b = localIntent.getData();
    c = localIntent.getLongExtra("thread_id", 0L);
    g = localIntent.getBooleanExtra("view_slideshow_from_edit", false);
  }
  
  private void c()
  {
    e = ((ListView)findViewById(16908298));
    e.setDivider(null);
    e.clearFocus();
    e.setRecyclerListener(f);
    e.setItemsCanFocus(true);
  }
  
  private void d()
  {
    f = new a(this, a.a());
    e.setAdapter(f);
  }
  
  private void e()
  {
    if (a.b() != null)
    {
      TextView localTextView = (TextView)LayoutInflater.from(this).inflate(2130968735, null, false);
      localTextView.setText(a.b());
      e.addHeaderView(localTextView);
    }
  }
  
  public boolean a(lm paramlm)
  {
    if (paramlm == null) {
      return false;
    }
    return a.a(paramlm);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {}
    do
    {
      do
      {
        return;
        switch (paramInt1)
        {
        default: 
          return;
        }
      } while (paramIntent == null);
      wd.e(paramIntent.getData().getPath());
      return;
    } while (paramIntent == null);
    String str = paramIntent.getStringExtra("attach_name");
    wd.e(paramIntent.getData().getPath());
    a.a(b, str);
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968739);
    aaa.c(getSupportActionBar(), this);
    getSupportActionBar().setDisplayOptions(12);
    aaa.a(getSupportActionBar(), this);
    b();
    c();
    a = new vp(this, b);
    d();
    e();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131951629, paramMenu);
    h = paramMenu.findItem(2131886812);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      onBackPressed();
    }
    while (paramMenuItem.getItemId() != 2131886812) {
      return true;
    }
    wd.a(this, b, 0, c, 1, a.b());
    return true;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    h.setVisible(g);
    return true;
  }
  
  protected void onStop()
  {
    if (i != null)
    {
      i.clear();
      i = null;
    }
    super.onStop();
  }
  
  public class a
    extends ArrayAdapter<vp.a>
    implements AbsListView.RecyclerListener
  {
    private final LayoutInflater b;
    
    public a(ArrayList<vp.a> paramArrayList)
    {
      super(2130968736, localList);
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
        paramContext.a(2130968736, 1, "text", c);
        return paramContext;
      case 2: 
        paramContext.a(2130968734, 2, "img", c);
        return paramContext;
      case 3: 
        paramContext.a(2130968730, 3, "audio", c);
        return paramContext;
      case 4: 
        paramContext.a(2130968738, 4, "video", c);
        return paramContext;
      case 5: 
        paramContext.a(2130968737, 5, "ref", c);
        return paramContext;
      }
      paramContext.a(2130968732, 6, "file", c);
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
          if (MeizuSlideshowActivity.a(MeizuSlideshowActivity.this) == null) {
            MeizuSlideshowActivity.a(MeizuSlideshowActivity.this, new HashMap());
          }
          localBitmap = (Bitmap)MeizuSlideshowActivity.a(MeizuSlideshowActivity.this).get(c.k().toString());
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
        localView = a(MeizuSlideshowActivity.this, paramInt, paramViewGroup);
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
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MeizuSlideshowActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */