package android.support.v7.internal.widget;

import android.database.DataSetObserver;

class ActivityChooserView$1
  extends DataSetObserver
{
  ActivityChooserView$1(ActivityChooserView paramActivityChooserView) {}
  
  public void onChanged()
  {
    super.onChanged();
    ActivityChooserView.access$000(this$0).notifyDataSetChanged();
  }
  
  public void onInvalidated()
  {
    super.onInvalidated();
    ActivityChooserView.access$000(this$0).notifyDataSetInvalidated();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserView.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */