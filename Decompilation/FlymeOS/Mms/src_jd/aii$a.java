import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

public final class aii$a
  extends Filter
{
  public aii$a(aii paramaii) {}
  
  public CharSequence convertResultToString(Object paramObject)
  {
    return null;
  }
  
  protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
  {
    Filter.FilterResults localFilterResults = new Filter.FilterResults();
    values = a.a(paramCharSequence);
    return localFilterResults;
  }
  
  protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
  {
    if (values != null)
    {
      Cursor[] arrayOfCursor = (Cursor[])values;
      a.a(paramCharSequence, arrayOfCursor[0], arrayOfCursor[1], arrayOfCursor[2]);
    }
    count = a.getCount();
  }
}

/* Location:
 * Qualified Name:     aii.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */