package com.android.mms.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ul;
import um;
import wd;

public class EditSlideDurationActivity
  extends Activity
{
  private TextView a;
  private Button b;
  private EditText c;
  private int d;
  private int e;
  private Bundle f;
  private final View.OnKeyListener g = new ul(this);
  private final View.OnClickListener h = new um(this);
  
  private void a(int paramInt)
  {
    c.requestFocus();
    c.selectAll();
    wd.a(paramInt, this, 0, 1, true, 0);
  }
  
  public void a()
  {
    String str = c.getText().toString();
    try
    {
      int i = Integer.valueOf(str).intValue();
      if (i <= 0)
      {
        a(2131492952);
        return;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      a(2131492949);
      return;
    }
    setResult(-1, new Intent(c.getText().toString()));
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130968658);
    if (paramBundle == null)
    {
      paramBundle = getIntent();
      d = paramBundle.getIntExtra("slide_index", 1);
      e = paramBundle.getIntExtra("slide_total", 1);
    }
    for (int i = paramBundle.getIntExtra("dur", 8);; i = f.getInt("dur", 8))
    {
      a = ((TextView)findViewById(2131886447));
      a.setText(getString(2131492951) + " " + (d + 1) + "/" + e);
      c = ((EditText)findViewById(2131886213));
      c.setText(String.valueOf(i));
      c.setOnKeyListener(g);
      b = ((Button)findViewById(2131886448));
      b.setOnClickListener(h);
      return;
      f = paramBundle.getBundle("state");
      d = f.getInt("slide_index", 1);
      e = f.getInt("slide_total", 1);
    }
  }
  
  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    f = new Bundle();
    f.putInt("slide_index", d);
    f.putInt("slide_total", e);
    try
    {
      i = Integer.parseInt(c.getText().toString());
      f.putInt("dur", i);
      paramBundle.putBundle("state", f);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        int i = 5;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.EditSlideDurationActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */