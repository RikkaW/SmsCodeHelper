package android.support.v4.app;

import android.view.MenuItem;

class ShareCompat$ShareCompatImplJB
  extends ShareCompat.ShareCompatImplICS
{
  public String escapeHtml(CharSequence paramCharSequence)
  {
    return ShareCompatJB.escapeHtml(paramCharSequence);
  }
  
  boolean shouldAddChooserIntent(MenuItem paramMenuItem)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.ShareCompat.ShareCompatImplJB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */