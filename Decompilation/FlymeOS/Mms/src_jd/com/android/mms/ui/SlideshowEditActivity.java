package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.mms.MmsException;
import lj;
import lq;
import lr;
import wd;
import xf;
import xg;
import yv;
import yw;

public class SlideshowEditActivity
  extends AppCompatActivity
  implements AdapterView.OnItemClickListener
{
  private ListView a;
  private a b;
  private lr c = null;
  private yw d = null;
  private Bundle e;
  private Uri f;
  private Intent g;
  private boolean h;
  private View i;
  private final lj j = new yv(this);
  
  private View a()
  {
    View localView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(2130968840, null);
    ((TextView)localView.findViewById(2131886742)).setText(2131492880);
    TextView localTextView = (TextView)localView.findViewById(2131886744);
    localTextView.setText(2131492881);
    localTextView.setVisibility(0);
    ((ImageView)localView.findViewById(2131886741)).setImageResource(2130837689);
    return localView;
  }
  
  private void a(int paramInt)
  {
    Intent localIntent = new Intent(this, SlideEditorActivity.class);
    localIntent.setData(f);
    localIntent.putExtra("slide_index", paramInt);
    startActivityForResult(localIntent, 6);
  }
  
  private void b()
  {
    if (c != null)
    {
      c.d(j);
      c = null;
    }
  }
  
  private void c()
  {
    b();
    c = lr.b(this, f);
    c.c(j);
    d = new yw(this, c);
    b = new a(this, 2130968840, c);
    a.setAdapter(b);
  }
  
  private void d()
  {
    if (c.size() >= 20)
    {
      i.setVisibility(8);
      return;
    }
    i.setVisibility(0);
  }
  
  private void e()
  {
    if (d.a())
    {
      b.notifyDataSetChanged();
      a.requestFocus();
      a.setSelection(c.size() - 1);
      return;
    }
    wd.a(2131492903, this, 0, 1, true, 0);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 != -1) {
      return;
    }
    switch (paramInt1)
    {
    default: 
      return;
    }
    try
    {
      h = true;
      setResult(-1, g);
      if ((paramIntent != null) && (paramIntent.getBooleanExtra("done", false)))
      {
        finish();
        return;
      }
    }
    finally {}
    try
    {
      c();
      d();
      return;
    }
    catch (MmsException paramIntent)
    {
      Log.e("SlideshowEditActivity", "Failed to initialize the slide-list.", paramIntent);
      finish();
    }
  }
  
  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    int k = getMenuInfoposition;
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      if ((k > 0) && (k < c.size()))
      {
        d.f(k);
        b.notifyDataSetChanged();
        a.setSelection(k - 1);
        continue;
        if ((k >= 0) && (k < c.size() - 1))
        {
          d.g(k);
          b.notifyDataSetChanged();
          a.setSelection(k + 1);
          continue;
          if ((k >= 0) && (k < c.size()))
          {
            d.b(k);
            b.notifyDataSetChanged();
            continue;
            e();
          }
        }
      }
    }
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968839);
    getSupportActionBar().setDisplayOptions(12);
    a = ((ListView)findViewById(16908298));
    a.setOnItemClickListener(this);
    i = a();
    a.addFooterView(i);
    i.setVisibility(8);
    if (paramBundle != null) {
      e = paramBundle.getBundle("state");
    }
    if (e != null) {}
    for (f = Uri.parse(e.getString("message_uri")); f == null; f = getIntent().getData())
    {
      Log.e("SlideshowEditActivity", "Cannot startup activity, null Uri.");
      finish();
      return;
    }
    g = new Intent();
    g.setData(f);
    try
    {
      c();
      d();
      registerForContextMenu(a);
      return;
    }
    catch (MmsException paramBundle)
    {
      for (;;)
      {
        Log.e("SlideshowEditActivity", "Failed to initialize the slide-list.", paramBundle);
        finish();
      }
    }
  }
  
  public void onCreateContextMenu(ContextMenu paramContextMenu, View paramView, ContextMenu.ContextMenuInfo paramContextMenuInfo)
  {
    paramContextMenu.setHeaderTitle(2131493124);
    int k = position;
    if ((k >= 0) && (k != a.getCount() - 1))
    {
      if (k > 0) {
        paramContextMenu.add(0, 0, 0, 2131493018).setIcon(2130837734);
      }
      if (k < b.getCount() - 1) {
        paramContextMenu.add(0, 1, 0, 2131493017).setIcon(2130837733);
      }
      paramContextMenu.add(0, 3, 0, 2131492880).setIcon(2130837729);
      paramContextMenu.add(0, 2, 0, 2131493081).setIcon(17301564);
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    b();
  }
  
  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (paramInt == c.size())
    {
      e();
      return;
    }
    a(paramInt);
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    int k = a.getSelectedItemPosition();
    switch (paramMenuItem.getItemId())
    {
    }
    for (;;)
    {
      return true;
      onBackPressed();
      continue;
      if ((k > 0) && (k < c.size()))
      {
        d.f(k);
        b.notifyDataSetChanged();
        a.setSelection(k - 1);
        continue;
        if ((k >= 0) && (k < c.size() - 1))
        {
          d.g(k);
          b.notifyDataSetChanged();
          a.setSelection(k + 1);
          continue;
          if ((k >= 0) && (k < c.size()))
          {
            d.b(k);
            b.notifyDataSetChanged();
            continue;
            e();
            continue;
            d.b();
            b.notifyDataSetChanged();
            finish();
          }
        }
      }
    }
  }
  
  /* Error */
  protected void onPause()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 339	android/support/v7/app/AppCompatActivity:onPause	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 113	com/android/mms/ui/SlideshowEditActivity:h	Z
    //   10: istore_1
    //   11: iload_1
    //   12: ifeq +32 -> 44
    //   15: aload_0
    //   16: getfield 34	com/android/mms/ui/SlideshowEditActivity:c	Llr;
    //   19: invokevirtual 342	lr:a	()Lcom/meizu/android/mms/pdu/MzPduBody;
    //   22: astore_2
    //   23: aload_0
    //   24: invokestatic 348	com/meizu/android/mms/pdu/MzPduPersister:getPduPersister	(Landroid/content/Context;)Lcom/meizu/android/mms/pdu/MzPduPersister;
    //   27: aload_0
    //   28: getfield 96	com/android/mms/ui/SlideshowEditActivity:f	Landroid/net/Uri;
    //   31: aload_2
    //   32: aconst_null
    //   33: invokevirtual 352	com/meizu/android/mms/pdu/MzPduPersister:updateParts	(Landroid/net/Uri;Lcom/meizu/android/mms/pdu/MzPduBody;Ljava/util/HashMap;)V
    //   36: aload_0
    //   37: getfield 34	com/android/mms/ui/SlideshowEditActivity:c	Llr;
    //   40: aload_2
    //   41: invokevirtual 355	lr:a	(Lcom/meizu/android/mms/pdu/MzPduBody;)V
    //   44: aload_0
    //   45: monitorexit
    //   46: return
    //   47: astore_2
    //   48: ldc -65
    //   50: new 357	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 358	java/lang/StringBuilder:<init>	()V
    //   57: ldc_w 360
    //   60: invokevirtual 364	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: aload_0
    //   64: getfield 96	com/android/mms/ui/SlideshowEditActivity:f	Landroid/net/Uri;
    //   67: invokevirtual 367	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 371	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: aload_2
    //   74: invokestatic 198	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   77: pop
    //   78: goto -34 -> 44
    //   81: astore_2
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	SlideshowEditActivity
    //   10	2	1	bool	boolean
    //   22	19	2	localMzPduBody	com.meizu.android.mms.pdu.MzPduBody
    //   47	27	2	localMmsException	MmsException
    //   81	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   15	44	47	com/google/android/mms/MmsException
    //   6	11	81	finally
    //   15	44	81	finally
    //   44	46	81	finally
    //   48	78	81	finally
    //   82	84	81	finally
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.clear();
    int k = a.getSelectedItemPosition();
    if ((k >= 0) && (k != a.getCount() - 1))
    {
      if (k > 0) {
        paramMenu.add(0, 0, 0, 2131493018).setIcon(2130837734);
      }
      if (k < b.getCount() - 1) {
        paramMenu.add(0, 1, 0, 2131493017).setIcon(2130837733);
      }
      paramMenu.add(0, 3, 0, 2131492880).setIcon(2130837729);
      paramMenu.add(0, 2, 0, 2131493081).setIcon(17301564);
    }
    for (;;)
    {
      paramMenu.add(0, 4, 0, 2131492941).setIcon(2130837731);
      return true;
      paramMenu.add(0, 3, 0, 2131492880).setIcon(2130837729);
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    if (e != null) {
      a.setSelection(e.getInt("slide_index", 0));
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    e = new Bundle();
    if (a.getSelectedItemPosition() >= 0) {
      e.putInt("slide_index", a.getSelectedItemPosition());
    }
    if (f != null) {
      e.putString("message_uri", f.toString());
    }
    paramBundle.putBundle("state", e);
  }
  
  static class a
    extends ArrayAdapter<lq>
  {
    private final Context a;
    private final int b;
    private final LayoutInflater c;
    private final lr d;
    
    public a(Context paramContext, int paramInt, lr paramlr)
    {
      super(paramInt, paramlr);
      a = paramContext;
      b = paramInt;
      c = LayoutInflater.from(paramContext);
      d = paramlr;
    }
    
    private View a(int paramInt1, View paramView, int paramInt2)
    {
      paramView = (SlideListItemView)c.inflate(paramInt2, null);
      ((TextView)paramView.findViewById(2131886742)).setText(a.getString(2131493121, new Object[] { Integer.valueOf(paramInt1 + 1) }));
      paramInt2 = ((lq)getItem(paramInt1)).a() / 1000;
      ((TextView)paramView.findViewById(2131886743)).setText(a.getResources().getQuantityString(2131427332, paramInt2, new Object[] { Integer.valueOf(paramInt2) }));
      xf localxf = xg.a("SlideshowPresenter", a, paramView, d);
      ((SlideshowPresenter)localxf).setLocation(paramInt1);
      localxf.present(null);
      return paramView;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      return a(paramInt, paramView, b);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowEditActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */