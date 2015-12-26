import java.util.ArrayList;

class gm$a$a
{
  Thread a = new Thread(new gp(this), "Contact.ContactsCache.TaskStack worker thread");
  private final ArrayList<Runnable> b = new ArrayList();
  
  public gm$a$a()
  {
    a.setPriority(1);
    a.start();
  }
  
  public void a(Runnable paramRunnable)
  {
    synchronized (b)
    {
      b.add(paramRunnable);
      b.notify();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     gm.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */