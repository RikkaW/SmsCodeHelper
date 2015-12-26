package android.support.v4.app;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

class FragmentTabHost$DummyTabFactory
  implements TabHost.TabContentFactory
{
  private final Context mContext;
  
  public FragmentTabHost$DummyTabFactory(Context paramContext)
  {
    mContext = paramContext;
  }
  
  public View createTabContent(String paramString)
  {
    paramString = new View(mContext);
    paramString.setMinimumWidth(0);
    paramString.setMinimumHeight(0);
    return paramString;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.FragmentTabHost.DummyTabFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */