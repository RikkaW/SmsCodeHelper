import android.os.Parcel;
import android.os.Parcelable.Creator;
import flyme.support.v7.widget.StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem;

public final class ars
  implements Parcelable.Creator<StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem>
{
  public StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem a(Parcel paramParcel)
  {
    return new StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem(paramParcel);
  }
  
  public StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem[] a(int paramInt)
  {
    return new StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     ars
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */