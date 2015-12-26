package com.android.ex.editstyledtext;

import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

class EditStyledText$e
  implements MenuItem.OnMenuItemClickListener
{
  private EditStyledText$e(EditStyledText paramEditStyledText) {}
  
  public boolean onMenuItemClick(MenuItem paramMenuItem)
  {
    return a.onTextContextMenuItem(paramMenuItem.getItemId());
  }
}

/* Location:
 * Qualified Name:     com.android.ex.editstyledtext.EditStyledText.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */