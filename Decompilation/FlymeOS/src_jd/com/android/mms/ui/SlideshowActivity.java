package com.android.mms.ui;

import aaa;
import aau;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import android.widget.SeekBar;
import android.widget.TextView;
import att;
import atv;
import atw;
import auc;
import aud;
import com.google.android.mms.MmsException;
import hm;
import ig;
import lr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xg;
import yq;
import yr;
import ys;
import yt;
import yu;

public class SlideshowActivity
  extends AppCompatActivity
  implements atv
{
  private static boolean i = false;
  private MediaController a;
  private ig b;
  private Handler c;
  private auc d;
  private SlideView e;
  private int f;
  private TextView g;
  private String h;
  private boolean j = false;
  
  private void a()
  {
    a = new MediaController(this, false);
    a.setMediaPlayer(new a(b));
    a.setAnchorView(findViewById(2131886737));
    a.setPrevNextListeners(new yr(this), new ys(this));
  }
  
  private void b()
  {
    g = ((TextView)findViewById(2131886736));
    g.setPadding(g.getPaddingLeft(), aaa.a(), g.getPaddingRight(), g.getPaddingBottom());
    e.setOnSlideChangedListener(new yu(this));
  }
  
  private static final boolean b(auc paramauc)
  {
    paramauc = paramauc.k();
    if (paramauc == null) {}
    Object localObject2;
    label122:
    label129:
    int m;
    label150:
    do
    {
      do
      {
        int n;
        do
        {
          do
          {
            do
            {
              do
              {
                return false;
                paramauc = paramauc.getChildNodes();
              } while ((paramauc == null) || (paramauc.getLength() != 1));
              paramauc = paramauc.item(0);
            } while ((paramauc == null) || (!"layout".equals(paramauc.getNodeName())));
            paramauc = paramauc.getChildNodes();
          } while (paramauc == null);
          n = paramauc.getLength();
        } while (n <= 0);
        int k = 0;
        for (;;)
        {
          if (k >= n) {
            break label293;
          }
          localObject1 = paramauc.item(k);
          if (localObject1 == null) {
            break;
          }
          localObject2 = ((Node)localObject1).getNodeName();
          if (!"root-layout".equals(localObject2)) {
            break label129;
          }
          k += 1;
        }
      } while (!"region".equals(localObject2));
      Object localObject1 = ((Node)localObject1).getAttributes();
      m = 0;
      if (m >= ((NamedNodeMap)localObject1).getLength()) {
        break;
      }
      localObject2 = ((NamedNodeMap)localObject1).item(m);
    } while (localObject2 == null);
    String str = ((Node)localObject2).getNodeName();
    if (("left".equals(str)) || ("top".equals(str)) || ("height".equals(str)) || ("width".equals(str)) || ("fit".equals(str))) {}
    for (;;)
    {
      m += 1;
      break label150;
      break label122;
      if ((!"id".equals(str)) || (!(localObject2 instanceof hm))) {
        break;
      }
      localObject2 = ((hm)localObject2).getValue();
      if (!"Text".equals(localObject2)) {
        if (!"Image".equals(localObject2)) {
          break;
        }
      }
    }
    label293:
    return true;
  }
  
  public void a(att paramatt)
  {
    c.post(new yt(this, paramatt));
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    paramConfiguration = (ViewGroup.MarginLayoutParams)e.getLayoutParams();
    bottomMargin = getResources().getDimensionPixelOffset(2131558606);
    e.setLayoutParams(paramConfiguration);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    c = new Handler();
    getWindow().setFormat(-3);
    setContentView(2130968837);
    getSupportActionBar().setDisplayOptions(12);
    paramBundle = getIntent();
    Uri localUri = paramBundle.getData();
    h = paramBundle.getStringExtra("slideshow_subject");
    try
    {
      paramBundle = lr.b(this, localUri);
      f = paramBundle.size();
      e = ((SlideView)findViewById(2131886737));
      b();
      xg.a("SlideshowPresenter", this, e, paramBundle);
      j = true;
      c.post(new yq(this, paramBundle));
      return;
    }
    catch (MmsException paramBundle)
    {
      Log.e("SlideshowActivity", "Cannot present the slide show.", paramBundle);
      finish();
    }
  }
  
  protected void onDestroy()
  {
    if (e != null)
    {
      e.setMediaController(null);
      e.setOnSlideChangedListener(null);
    }
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default: 
      if ((b != null) && (a != null)) {
        a.show();
      }
      break;
    }
    for (;;)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      if ((b != null) && ((b.d()) || (b.b()) || (b.c()))) {
        b.i();
      }
    }
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332) {
      onBackPressed();
    }
    return true;
  }
  
  protected void onPause()
  {
    super.onPause();
    if (a != null) {
      a.hide();
    }
    if (d != null) {
      ((atw)d).b("SimlDocumentEnd", this, false);
    }
    if (b != null)
    {
      if (!b.b()) {
        break label71;
      }
      b.g();
      i = true;
    }
    label71:
    while (!b.d()) {
      return;
    }
    i = false;
  }
  
  protected void onResume()
  {
    super.onResume();
    if (a != null) {
      a.hide();
    }
    if (d != null) {
      ((atw)d).a("SimlDocumentEnd", this, false);
    }
    if (!i)
    {
      j = false;
      return;
    }
    if (b == null) {
      b = ig.a();
    }
    if ((b != null) && (!isFinishing()) && (b.d()))
    {
      if (!j) {
        break label112;
      }
      b.p();
    }
    for (;;)
    {
      j = false;
      return;
      label112:
      b.h();
    }
  }
  
  protected void onStop()
  {
    super.onStop();
    if (b != null)
    {
      if (!isFinishing()) {
        break label69;
      }
      b.i();
    }
    for (;;)
    {
      if (a != null)
      {
        View localView = a.findViewById(aau.g("mediacontroller_progress"));
        if ((localView instanceof SeekBar)) {
          ((SeekBar)localView).setOnSeekBarChangeListener(null);
        }
        a.hide();
      }
      return;
      label69:
      b.j();
    }
  }
  
  class a
    implements MediaController.MediaPlayerControl
  {
    private final ig b;
    private boolean c = true;
    
    public a(ig paramig)
    {
      b = paramig;
    }
    
    public boolean canPause()
    {
      return true;
    }
    
    public boolean canSeekBackward()
    {
      return true;
    }
    
    public boolean canSeekForward()
    {
      return true;
    }
    
    public int getAudioSessionId()
    {
      return 0;
    }
    
    public int getBufferPercentage()
    {
      return 100;
    }
    
    public int getCurrentPosition()
    {
      if (b != null) {
        return b.o();
      }
      return 0;
    }
    
    public int getDuration()
    {
      return b.n();
    }
    
    public boolean isPlaying()
    {
      return c;
    }
    
    public void pause()
    {
      b.g();
      c = false;
    }
    
    public void seekTo(int paramInt) {}
    
    public void start()
    {
      b.h();
      c = true;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */