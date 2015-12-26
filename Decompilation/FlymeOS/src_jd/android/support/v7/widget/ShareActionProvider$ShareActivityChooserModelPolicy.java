package android.support.v7.widget;

import android.content.Intent;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserModel.OnChooseActivityListener;

class ShareActionProvider$ShareActivityChooserModelPolicy
  implements ActivityChooserModel.OnChooseActivityListener
{
  private ShareActionProvider$ShareActivityChooserModelPolicy(ShareActionProvider paramShareActionProvider) {}
  
  public boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent)
  {
    if (ShareActionProvider.access$500(this$0) != null) {
      ShareActionProvider.access$500(this$0).onShareTargetSelected(this$0, paramIntent);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.ShareActionProvider.ShareActivityChooserModelPolicy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */