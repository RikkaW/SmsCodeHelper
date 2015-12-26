import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

public final class aii$c
  extends Filter
{
  private final int b;
  private final long c;
  
  public aii$c(aii paramaii, int paramInt, long paramLong)
  {
    b = paramInt;
    c = paramLong;
  }
  
  public CharSequence convertResultToString(Object paramObject)
  {
    return null;
  }
  
  protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
  {
    Filter.FilterResults localFilterResults = new Filter.FilterResults();
    values = a.a(paramCharSequence, c);
    return localFilterResults;
  }
  
  protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
  {
    Cursor localCursor = (Cursor)values;
    a.a(paramCharSequence, c, localCursor);
    count = a.getCount();
  }
}

/* Location:
 * Qualified Name:     aii.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */