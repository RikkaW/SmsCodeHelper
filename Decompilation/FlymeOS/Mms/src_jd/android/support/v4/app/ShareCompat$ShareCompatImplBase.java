package android.support.v4.app;

import android.view.MenuItem;

class ShareCompat$ShareCompatImplBase
  implements ShareCompat.ShareCompatImpl
{
  private static void withinStyle(StringBuilder paramStringBuilder, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
    {
      char c = paramCharSequence.charAt(paramInt1);
      if (c == '<') {
        paramStringBuilder.append("&lt;");
      }
      for (;;)
      {
        paramInt1 += 1;
        break;
        if (c == '>')
        {
          paramStringBuilder.append("&gt;");
        }
        else if (c == '&')
        {
          paramStringBuilder.append("&amp;");
        }
        else if ((c > '~') || (c < ' '))
        {
          paramStringBuilder.append("&#" + c + ";");
        }
        else if (c == ' ')
        {
          while ((paramInt1 + 1 < paramInt2) && (paramCharSequence.charAt(paramInt1 + 1) == ' '))
          {
            paramStringBuilder.append("&nbsp;");
            paramInt1 += 1;
          }
          paramStringBuilder.append(' ');
        }
        else
        {
          paramStringBuilder.append(c);
        }
      }
    }
  }
  
  public void configureMenuItem(MenuItem paramMenuItem, ShareCompat.IntentBuilder paramIntentBuilder)
  {
    paramMenuItem.setIntent(paramIntentBuilder.createChooserIntent());
  }
  
  public String escapeHtml(CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    withinStyle(localStringBuilder, paramCharSequence, 0, paramCharSequence.length());
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ShareCompat.ShareCompatImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */