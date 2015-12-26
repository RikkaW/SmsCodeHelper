package com.amap.api.mapcore2d;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;
import com.amap.api.maps2d.AMap.CancelableCallback;
import com.amap.api.maps2d.AMap.OnCameraChangeListener;
import com.amap.api.maps2d.AMap.OnMapLoadedListener;
import com.amap.api.maps2d.AMap.OnMapScreenShotListener;
import com.amap.api.maps2d.model.CameraPosition;

class e
  extends Handler
{
  String a = "handleMessage";
  
  e(b paramb) {}
  
  public void handleMessage(Message paramMessage)
  {
    if ((paramMessage == null) || (b.b(b) == null) || (bb).b == null)) {}
    do
    {
      do
      {
        do
        {
          for (;;)
          {
            return;
            try
            {
              switch (what)
              {
              case 2: 
                Toast.makeText(b.e(b), db.b, 0).show();
                return;
              }
            }
            catch (Throwable paramMessage)
            {
              cy.a(paramMessage, "AMapDelegateImpGLSurfaceView", "handle_handleMessage");
              return;
            }
            b.c(b);
            return;
            if (b.d(b) != null)
            {
              b.d(b).onMapLoaded();
              return;
              Object localObject;
              try
              {
                localObject = (ba)obj;
                paramMessage = (Message)localObject;
                if (localObject == null) {
                  paramMessage = new ba(false, false);
                }
                ed.a(b.e(b), y.a(paramMessage.a()));
                return;
              }
              catch (Throwable paramMessage)
              {
                cy.a(paramMessage, "AMapDelegateImpGLSurfaceView", a);
                return;
              }
              try
              {
                paramMessage = (Bitmap)obj;
                if (!paramMessage.isRecycled())
                {
                  paramMessage = Bitmap.createBitmap(paramMessage);
                  if (paramMessage != null)
                  {
                    localObject = new Canvas(paramMessage);
                    if (b.f(b) != null) {
                      b.f(b).onDraw((Canvas)localObject);
                    }
                    if ((b.g(b) != null) && (b.h(b) != null))
                    {
                      Bitmap localBitmap = b.g(b).getDrawingCache(true);
                      if (localBitmap != null)
                      {
                        int i = b.g(b).getLeft();
                        int j = b.g(b).getTop();
                        ((Canvas)localObject).drawBitmap(localBitmap, i, j, new Paint());
                      }
                    }
                    if (b.i(b) != null) {
                      b.i(b).onMapScreenShot(paramMessage);
                    }
                    b.destroyDrawingCache();
                    b.a(b, null);
                    return;
                  }
                }
              }
              catch (Exception paramMessage)
              {
                for (;;)
                {
                  cy.a(paramMessage, "AMapDelegateImpGLSurfaceView", a);
                  paramMessage = null;
                  continue;
                  if (b.i(b) != null) {
                    b.i(b).onMapScreenShot(null);
                  }
                }
              }
            }
          }
          if (b.j(b) != null)
          {
            paramMessage = b.k(b);
            b.a(b, true, paramMessage);
          }
          if (b.l(b) != null)
          {
            b.a(b, true);
            b.l(b).onFinish();
            b.a(b, false);
          }
          if (!b.m(b))
          {
            b.a(b, null);
            return;
          }
          b.b(b, false);
          return;
        } while (b.j(b) == null);
        paramMessage = new CameraPosition(b.n(b), b.f(), 0.0F, 0.0F);
        b.j(b).onCameraChange(paramMessage);
        return;
      } while ((b.o(b) == null) || (!b.o(b).g()));
      switch (b.o(b).h())
      {
      case 2: 
        paramMessage = u.a(new an(b.o(b).b(), b.o(b).c()), b.o(b).d(), b.o(b).e(), b.o(b).f());
        if (b.o(b).a()) {
          i = true;
        }
        b.d.a(paramMessage);
        return;
      }
    } while ((b.b(b) == null) || (bb).c == null));
    bb).c.a();
    return;
    return;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.e
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */