package com.android.ex.editstyledtext;

import android.graphics.Color;
import android.util.Log;
import fi;

public class EditStyledText$f
{
  private EditStyledText b;
  private EditStyledText.h c;
  
  public String a()
  {
    b.clearComposingText();
    EditStyledText.c(b);
    String str = c.a(b.getText(), true, EditStyledText.b(a), EditStyledText.d(a));
    int i = b.getBackgroundColor();
    str = String.format("<body bgcolor=\"#%02X%02X%02X\">%s</body>", new Object[] { Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), str });
    Log.d("EditStyledText", "--- getPreviewHtml:" + str + "," + b.getWidth());
    return str;
  }
  
  public String a(boolean paramBoolean)
  {
    b.clearComposingText();
    EditStyledText.c(b);
    String str = c.a(b.getText(), paramBoolean);
    Log.d("EditStyledText", "--- getHtml:" + str);
    return str;
  }
  
  public void a(EditStyledText.h paramh)
  {
    c = paramh;
  }
  
  public void a(String paramString)
  {
    paramString = c.a(paramString, new fi(this), null);
    b.setText(paramString);
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.f
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */