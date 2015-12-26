package android.support.v7.internal.widget;

class AdapterViewCompat$SelectionNotifier
  implements Runnable
{
  private AdapterViewCompat$SelectionNotifier(AdapterViewCompat paramAdapterViewCompat) {}
  
  public void run()
  {
    if (this$0.mDataChanged)
    {
      if (this$0.getAdapter() != null) {
        this$0.post(this);
      }
      return;
    }
    AdapterViewCompat.access$200(this$0);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.AdapterViewCompat.SelectionNotifier
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */