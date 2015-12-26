package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.text.method.HideReturnsTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import kd;
import ke;
import ps;
import ps.a;
import yl;
import ym;
import yn;
import yo;
import yp;

public class SlideView
  extends AbsoluteLayout
  implements ps
{
  MediaPlayer.OnPreparedListener a = new yl(this);
  private View b;
  private ImageView c;
  private VideoView d;
  private ScrollView e;
  private TextView f;
  private ps.a g;
  private MediaPlayer h;
  private boolean i;
  private boolean j;
  private int k;
  private boolean l;
  private ScrollView m;
  private LinearLayout n;
  private boolean o;
  private MediaController p;
  private b q;
  private int r;
  private int s;
  private Context t;
  private View.OnTouchListener u = new yp(this);
  
  public SlideView(Context paramContext)
  {
    super(paramContext);
    t = paramContext;
    r = paramContext.getResources().getDimensionPixelSize(2131559299);
    s = paramContext.getResources().getDimensionPixelSize(2131559282);
  }
  
  public SlideView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    t = paramContext;
    r = paramContext.getResources().getDimensionPixelSize(2131559299);
    s = paramContext.getResources().getDimensionPixelSize(2131559282);
  }
  
  private void a()
  {
    if (b != null) {
      b.setVisibility(0);
    }
  }
  
  private void a(String paramString)
  {
    if (b == null)
    {
      b = LayoutInflater.from(getContext()).inflate(2130968817, null);
      b.getHeight();
      if (!o) {
        break label86;
      }
      n.addView(b, new LinearLayout.LayoutParams(-1, 82));
    }
    for (;;)
    {
      ((TextView)b.findViewById(2131886669)).setText(paramString);
      b.setVisibility(8);
      return;
      label86:
      addView(b, new AbsoluteLayout.LayoutParams(-1, 82, 0, getHeight() - 82));
    }
  }
  
  private void b()
  {
    if (b != null) {
      b.setVisibility(8);
    }
  }
  
  public void a(int paramInt)
  {
    if ((d != null) && (paramInt > 0)) {
      d.seekTo(paramInt);
    }
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((e != null) && (!o)) {
      e.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
    }
  }
  
  public void a(long paramLong) {}
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap)
  {
    if (paramUri == null) {
      throw new IllegalArgumentException("Audio URI may not be null.");
    }
    if (h != null)
    {
      h.reset();
      h.release();
      h = null;
    }
    i = false;
    j = false;
    k = 0;
    l = false;
    try
    {
      h = new MediaPlayer();
      h.setOnPreparedListener(a);
      h.setDataSource(t, paramUri);
      h.prepareAsync();
      a(paramString);
      return;
    }
    catch (IOException paramUri)
    {
      for (;;)
      {
        Log.e("SlideView", "Unexpected IOException.", paramUri);
        h.release();
        h = null;
      }
    }
  }
  
  public void a(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong) {}
  
  public void a(Uri paramUri, String paramString1, Map<String, ?> paramMap, String paramString2) {}
  
  public void a(String paramString, long paramLong) {}
  
  public void a(String paramString, Bitmap paramBitmap)
  {
    if (c == null)
    {
      c = new ImageView(t);
      c.setPadding(0, 5, 0, 5);
      addView(c, new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
    }
    paramString = paramBitmap;
    if (paramBitmap == null) {}
    try
    {
      paramString = BitmapFactory.decodeResource(getResources(), 2130837744);
      c.setVisibility(0);
      c.setImageBitmap(paramString);
      return;
    }
    catch (OutOfMemoryError paramString)
    {
      Log.e("SlideView", "setImage: out of memory: ", paramString);
    }
  }
  
  public void a(String paramString, Uri paramUri)
  {
    if (d == null)
    {
      d = new VideoView(t);
      addView(d, new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
    }
    d.setVisibility(0);
    d.setVideoURI(paramUri);
  }
  
  public void a(String paramString1, String paramString2)
  {
    if (!o)
    {
      if (e == null)
      {
        e = new ScrollView(t);
        e.setScrollBarStyle(50331648);
        addView(e, new AbsoluteLayout.LayoutParams(-2, -2, 0, 0));
      }
      if (f == null)
      {
        f = new TextView(t);
        f.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        f.setAutoLinkMask(15);
        f.setLinksClickable(true);
        f.setTextSize(0, r);
        f.setOnTouchListener(u);
        f.setPadding(s, 0, s, 0);
        e.addView(f);
      }
      e.requestFocus();
    }
    f.setVisibility(0);
    f.setText(paramString2);
    f.setTextIsSelectable(true);
    f.setAutoLinkMask(15);
    f.setLinksClickable(true);
    f.setOnTouchListener(u);
  }
  
  public void b(int paramInt)
  {
    if ((h != null) && (i))
    {
      h.seekTo(paramInt);
      return;
    }
    k = paramInt;
  }
  
  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((c != null) && (!o)) {
      c.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
    }
  }
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap) {}
  
  public void b(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong) {}
  
  public void b(String paramString, Bitmap paramBitmap) {}
  
  public void c(int paramInt)
  {
    if (q != null) {
      q.a(paramInt);
    }
  }
  
  public void c(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((d != null) && (!o)) {
      d.setLayoutParams(new AbsoluteLayout.LayoutParams(paramInt3, paramInt4, paramInt1, paramInt2));
    }
  }
  
  public void c(Uri paramUri, String paramString, Map<String, ?> paramMap, long paramLong) {}
  
  public void d(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    o = true;
    if (m == null)
    {
      m = new ym(this, t);
      m.setScrollBarStyle(0);
      n = new LinearLayout(t);
      n.setOrientation(1);
      n.setGravity(17);
      n.setOnClickListener(new yn(this));
      m.addView(n, new FrameLayout.LayoutParams(-1, -2));
      addView(m);
    }
    Object localObject = new TreeMap(new yo(this));
    if ((paramInt1 >= 0) && (paramInt2 >= 0))
    {
      f = new TextView(t);
      f.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      f.setTextSize(0, r);
      f.setPadding(s, 0, s, 0);
      f.setAutoLinkMask(15);
      f.setLinksClickable(true);
      f.setOnTouchListener(u);
      ((TreeMap)localObject).put(new a(paramInt1, paramInt2), f);
    }
    if ((paramInt3 >= 0) && (paramInt4 >= 0))
    {
      c = new ImageView(t);
      c.setPadding(0, 5, 0, 5);
      ((TreeMap)localObject).put(new a(paramInt3, paramInt4), c);
      d = new VideoView(t);
      ((TreeMap)localObject).put(new a(paramInt3 + 1, paramInt4), d);
    }
    localObject = ((TreeMap)localObject).values().iterator();
    if (((Iterator)localObject).hasNext())
    {
      View localView = (View)((Iterator)localObject).next();
      if ((localView instanceof VideoView)) {
        n.addView(localView, new LinearLayout.LayoutParams(-1, kd.a().b().b()));
      }
      for (;;)
      {
        localView.setVisibility(8);
        break;
        n.addView(localView, new LinearLayout.LayoutParams(-1, -2));
      }
    }
  }
  
  public void h()
  {
    if (e != null) {
      e.setVisibility(8);
    }
    if (c != null) {
      c.setVisibility(8);
    }
    if (h != null) {
      m();
    }
    if (d != null)
    {
      j();
      d.setVisibility(8);
    }
    if (f != null) {
      f.setVisibility(8);
    }
    if (m != null)
    {
      m.scrollTo(0, 0);
      m.setLayoutParams(new AbsoluteLayout.LayoutParams(-1, -1, 0, 0));
    }
  }
  
  public void i()
  {
    if (d != null) {
      d.start();
    }
  }
  
  public void j()
  {
    if (d != null) {
      d.stopPlayback();
    }
  }
  
  public void k()
  {
    if (d != null) {
      d.pause();
    }
  }
  
  public void l()
  {
    if ((h != null) && (i))
    {
      h.start();
      j = false;
      a();
      return;
    }
    j = true;
  }
  
  public void m()
  {
    if ((h != null) && (i))
    {
      h.stop();
      h.release();
      h = null;
      b();
      return;
    }
    l = true;
  }
  
  public void n()
  {
    if ((h != null) && (i) && (h.isPlaying())) {
      h.pause();
    }
    j = false;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (g != null) {
      g.a(paramInt1, paramInt2 - 82);
    }
  }
  
  public void setImageRegionFit(String paramString) {}
  
  public void setImageVisibility(boolean paramBoolean)
  {
    int i2 = 0;
    int i1 = 0;
    if (c != null)
    {
      if (!o) {
        break label41;
      }
      localImageView = c;
      if (!paramBoolean) {
        break label35;
      }
    }
    for (;;)
    {
      localImageView.setVisibility(i1);
      return;
      label35:
      i1 = 8;
    }
    label41:
    ImageView localImageView = c;
    if (paramBoolean) {}
    for (i1 = i2;; i1 = 4)
    {
      localImageView.setVisibility(i1);
      return;
    }
  }
  
  public void setMediaController(MediaController paramMediaController)
  {
    p = paramMediaController;
  }
  
  public void setOnSizeChangedListener(ps.a parama)
  {
    g = parama;
  }
  
  public void setOnSlideChangedListener(b paramb)
  {
    q = paramb;
  }
  
  public void setTextVisibility(boolean paramBoolean)
  {
    int i2 = 0;
    int i1 = 0;
    if (o) {
      if (f != null)
      {
        localObject = f;
        if (!paramBoolean) {
          break label35;
        }
        ((TextView)localObject).setVisibility(i1);
      }
    }
    label35:
    while (e == null) {
      for (;;)
      {
        return;
        i1 = 8;
      }
    }
    Object localObject = e;
    if (paramBoolean) {}
    for (i1 = i2;; i1 = 4)
    {
      ((ScrollView)localObject).setVisibility(i1);
      return;
    }
  }
  
  public void setVideoVisibility(boolean paramBoolean)
  {
    int i2 = 0;
    int i1 = 0;
    if (d != null)
    {
      if (!o) {
        break label41;
      }
      localVideoView = d;
      if (!paramBoolean) {
        break label35;
      }
    }
    for (;;)
    {
      localVideoView.setVisibility(i1);
      return;
      label35:
      i1 = 8;
    }
    label41:
    VideoView localVideoView = d;
    if (paramBoolean) {}
    for (i1 = i2;; i1 = 4)
    {
      localVideoView.setVisibility(i1);
      return;
    }
  }
  
  public void setVisibility(boolean paramBoolean) {}
  
  public class a
  {
    public int a;
    public int b;
    
    public a(int paramInt1, int paramInt2)
    {
      a = paramInt2;
      b = paramInt1;
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */