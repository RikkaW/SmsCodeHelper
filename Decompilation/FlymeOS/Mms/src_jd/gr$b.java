import android.content.AsyncQueryHandler;
import android.content.ContentResolver;

public class gr$b
  extends AsyncQueryHandler
{
  private int a;
  
  public gr$b(ContentResolver paramContentResolver)
  {
    super(paramContentResolver);
  }
  
  public void a(int paramInt)
  {
    a = paramInt;
  }
  
  public void onDeleteComplete(int paramInt1, Object arg2, int paramInt2)
  {
    if (paramInt1 == a) {
      synchronized (gr.A())
      {
        gr.f(false);
        gr.A().notifyAll();
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     gr.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */