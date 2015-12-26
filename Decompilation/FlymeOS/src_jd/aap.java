import android.util.Log;
import com.meizu.android.mms.pdu.MzGenericPdu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

class aap
  implements Runnable
{
  aap(aan.b paramb, MzGenericPdu paramMzGenericPdu, lr paramlr, Throwable paramThrowable) {}
  
  public void run()
  {
    Object localObject = (Set)d.a.b.get(aan.b.a(d));
    if (localObject != null)
    {
      localObject = zh.a((Set)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        zy localzy = (zy)((Iterator)localObject).next();
        if (Log.isLoggable("Mms:PduLoaderManager", 3)) {
          Log.d("Mms:PduLoaderManager", "Invoking pdu callback " + localzy);
        }
        localzy.a(new aan.a(a, b), c);
      }
    }
    if (b != null) {
      aan.c(d.a).a(aan.b.a(d), b);
    }
    d.a.b.remove(aan.b.a(d));
    d.a.a.remove(aan.b.a(d));
    if (Log.isLoggable("Mms:pducache", 3)) {
      Log.d("Mms:PduLoaderManager", "Pdu task for " + aan.b.a(d) + "exiting; " + d.a.a.size() + " remain");
    }
  }
}

/* Location:
 * Qualified Name:     aap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */