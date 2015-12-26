import android.content.Context;
import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public abstract class aii
  extends aif
  implements Filterable
{
  public static final String[] b = { "display_name", "data1", "organization_note", "_id" };
  private int a;
  
  public aii(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a = paramInt;
  }
  
  protected abstract Object a(CharSequence paramCharSequence);
  
  protected abstract Object a(CharSequence paramCharSequence, long paramLong);
  
  public abstract String a(String paramString);
  
  protected abstract void a(CharSequence paramCharSequence, long paramLong, Cursor paramCursor);
  
  protected abstract void a(CharSequence paramCharSequence, Cursor paramCursor1, Cursor paramCursor2, Cursor paramCursor3);
  
  public abstract void a(String paramString1, String paramString2);
  
  public abstract boolean b(String paramString);
  
  public abstract boolean c(String paramString);
  
  public abstract boolean d(String paramString);
  
  public boolean g(int paramInt)
  {
    return (c(e(paramInt)) instanceof aii.e);
  }
  
  public Filter getFilter()
  {
    return new aii.a();
  }
  
  public final class a
    extends Filter
  {
    public a() {}
    
    public CharSequence convertResultToString(Object paramObject)
    {
      return null;
    }
    
    protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
    {
      Filter.FilterResults localFilterResults = new Filter.FilterResults();
      values = a(paramCharSequence);
      return localFilterResults;
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
    {
      if (values != null)
      {
        Cursor[] arrayOfCursor = (Cursor[])values;
        a(paramCharSequence, arrayOfCursor[0], arrayOfCursor[1], arrayOfCursor[2]);
      }
      count = getCount();
    }
  }
  
  public class b
    extends aif.a
  {
    public long f;
    public String g;
    public String h;
    public String i;
    public String j;
    public CharSequence k;
    public aii.c l;
    
    public b()
    {
      super(false);
    }
  }
  
  public final class c
    extends Filter
  {
    private final int b;
    private final long c;
    
    public c(int paramInt, long paramLong)
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
      values = a(paramCharSequence, c);
      return localFilterResults;
    }
    
    protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
    {
      Cursor localCursor = (Cursor)values;
      a(paramCharSequence, c, localCursor);
      count = getCount();
    }
  }
  
  public class d
    extends aif.a
  {
    public d()
    {
      super(true);
    }
  }
  
  public class e
    extends aif.a
  {
    public e()
    {
      super(false);
    }
  }
  
  public static class f
  {
    public String a;
    public String b;
    
    private static f a(DataInputStream paramDataInputStream)
    {
      f localf = new f();
      a = paramDataInputStream.readUTF();
      b = paramDataInputStream.readUTF();
      return localf;
    }
    
    public static ArrayList<f> a(byte[] paramArrayOfByte)
    {
      ArrayList localArrayList = new ArrayList();
      DataInputStream localDataInputStream;
      if (paramArrayOfByte != null)
      {
        paramArrayOfByte = new ByteArrayInputStream(paramArrayOfByte);
        localDataInputStream = new DataInputStream(paramArrayOfByte);
      }
      try
      {
        while (localDataInputStream.available() > 0) {
          localArrayList.add(a(localDataInputStream));
        }
        try
        {
          paramArrayOfByte.close();
          if (localDataInputStream != null) {
            localDataInputStream.close();
          }
        }
        catch (IOException paramArrayOfByte)
        {
          for (;;)
          {
            paramArrayOfByte.printStackTrace();
          }
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        if (paramArrayOfByte != null) {}
        for (;;)
        {
          try
          {
            paramArrayOfByte.close();
            if (localDataInputStream != null) {
              localDataInputStream.close();
            }
            return localArrayList;
          }
          catch (IOException paramArrayOfByte)
          {
            paramArrayOfByte.printStackTrace();
            return localArrayList;
          }
          if (paramArrayOfByte != null) {}
          try
          {
            paramArrayOfByte.close();
            if (localDataInputStream != null)
            {
              localDataInputStream.close();
              return localArrayList;
            }
          }
          catch (IOException paramArrayOfByte)
          {
            paramArrayOfByte.printStackTrace();
            return localArrayList;
          }
        }
      }
      finally
      {
        if (paramArrayOfByte == null) {}
      }
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     aii
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */