import android.content.pm.IPackageInstallObserver.Stub;

final class ams
  extends IPackageInstallObserver.Stub
{
  ams(int paramInt, amr.a parama, Object paramObject) {}
  
  public void packageInstalled(String arg1, int paramInt)
  {
    if (paramInt != a) {
      anf.c("install return code : " + paramInt);
    }
    b.a(paramInt);
    synchronized (c)
    {
      c.notify();
      return;
    }
  }
}

/* Location:
 * Qualified Name:     ams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */