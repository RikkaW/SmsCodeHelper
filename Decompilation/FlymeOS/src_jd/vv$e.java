import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import com.android.mms.MmsApp;
import com.meizu.android.mms.pdu.MzMultimediaMessagePdu;
import com.meizu.android.mms.pdu.MzNotificationInd;
import com.meizu.android.mms.pdu.MzRetrieveConf;
import com.meizu.android.mms.pdu.MzSendReq;

public class vv$e
  implements zy
{
  public vv$e(vv paramvv) {}
  
  public void a(Object paramObject, Throwable arg2)
  {
    if (??? != null)
    {
      a.y = false;
      Log.e(vv.am(), "PduLoadedMessageItemCallback PDU couldn't be loaded: ", ???);
    }
    long l1;
    label309:
    do
    {
      return;
      a.y = true;
      if (vv.a(a) != null) {}
      for (;;)
      {
        synchronized (vv.a(a))
        {
          vv.a(a).a(true);
          ??? = (aan.a)paramObject;
          if (130 != a.u) {
            break label309;
          }
          a.h = vv.a.a;
          if ((a instanceof MzNotificationInd))
          {
            paramObject = (MzNotificationInd)a;
            vv.a(a, ((MzNotificationInd)paramObject).getFrom(), a.t);
            a.C = ((MzNotificationInd)paramObject).getMessageSize();
            l1 = ((MzNotificationInd)paramObject).getExpiry() * 1000L;
            if ((!a.x()) && (130 != a.u)) {
              a.k = wd.a(a.c, l1, 1, true);
            }
            if (vv.b(a) == null) {
              break;
            }
            vv.b(a).a(a);
            return;
          }
        }
        Log.e(vv.am(), "getPdu()---wrong--" + a + "-- uri = " + a.t);
        Log.e(vv.am(), "getPdu()----mMessageProtocol = " + a.L);
        l1 = 0L;
      }
    } while (a.H.isClosed());
    paramObject = (MzMultimediaMessagePdu)a;
    a.B = b;
    if (((a.v != 6) && (!a.y())) || ((a.v == vv.b) && (a.y()))) {
      a.v = wd.a(a.B, (MzMultimediaMessagePdu)paramObject);
    }
    if (a.v == 8) {
      a.R = wd.a(a.B);
    }
    label470:
    label482:
    long l2;
    if (a.u == 132) {
      if (paramObject == null)
      {
        vv.a(a, null, a.t);
        l1 = 0L;
        if (a.B != null) {
          break label832;
        }
        paramObject = null;
        if ((paramObject != null) && (((lq)paramObject).d()))
        {
          paramObject = ((lq)paramObject).p();
          a.o = ((lu)paramObject).a();
          a.p = ((lu)paramObject).j();
        }
        if ((a.m()) && (a.B != null))
        {
          paramObject = a.B.j();
          if (paramObject != null) {
            a.x = ((lm)paramObject).n();
          }
        }
        paramObject = a;
        ??? = a;
        if (a.B != null) {
          break label847;
        }
        l2 = a.C;
        C = l2;
        z = l2;
        paramObject = a.H.getString(a.I.q);
        if ((paramObject != null) && (a.m.equals(a.c.getString(2131493015)))) {
          break label901;
        }
        a.h = vv.a.a;
      }
    }
    for (;;)
    {
      paramObject = a.H.getString(a.I.r);
      if ((paramObject != null) && (a.m.equals(a.c.getString(2131493015)))) {
        break label961;
      }
      a.i = false;
      break;
      paramObject = (MzRetrieveConf)paramObject;
      vv.a(a, ((MzRetrieveConf)paramObject).getFrom(), a.t);
      l1 = ((MzRetrieveConf)paramObject).getDate() * 1000L;
      break label470;
      ??? = a;
      vv localvv = a;
      String str = a.c.getString(2131493015);
      m = str;
      n = str;
      if (paramObject == null)
      {
        l1 = 0L;
        break label470;
      }
      l1 = ((MzSendReq)paramObject).getDate() * 1000L;
      break label470;
      label832:
      paramObject = a.B.a(0);
      break label482;
      label847:
      long l3 = a.C;
      if (MmsApp.a) {}
      for (l2 = a.B.d();; l2 = a.B.e())
      {
        l2 = Math.max(l3, l2);
        break;
      }
      try
      {
        label901:
        if (Integer.parseInt((String)paramObject) != 128) {
          break label948;
        }
        a.h = vv.a.e;
      }
      catch (NumberFormatException paramObject)
      {
        Log.e(vv.am(), "Value for delivery report was invalid.");
        a.h = vv.a.a;
      }
      continue;
      label948:
      a.h = vv.a.a;
    }
    for (;;)
    {
      try
      {
        label961:
        int i = Integer.parseInt((String)paramObject);
        paramObject = a;
        if (i != 128) {
          break label1012;
        }
        bool = true;
        i = bool;
      }
      catch (NumberFormatException paramObject)
      {
        Log.e(vv.am(), "Value for read report was invalid.");
        a.i = false;
      }
      break;
      label1012:
      boolean bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     vv.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */