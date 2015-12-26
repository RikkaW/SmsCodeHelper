import android.content.ContentUris;
import com.android.mms.ui.SlideEditorActivity;
import com.google.android.mms.MmsException;
import com.meizu.android.mms.pdu.MzPduPart;
import com.meizu.android.mms.pdu.MzPduPersister;

public class yb
  implements wd.d
{
  public yb(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void a(MzPduPart paramMzPduPart, boolean paramBoolean, int paramInt)
  {
    SlideEditorActivity localSlideEditorActivity = a;
    if (paramMzPduPart == null)
    {
      wd.a(SlideEditorActivity.a(a, 2131493832, SlideEditorActivity.k(a)), a, 0, 1, true, 0);
      return;
    }
    try
    {
      long l = ContentUris.parseId(SlideEditorActivity.l(a));
      paramMzPduPart = MzPduPersister.getPduPersister(localSlideEditorActivity).persistPart(paramMzPduPart, l, null);
      SlideEditorActivity.c(a).a(SlideEditorActivity.a(a), paramMzPduPart);
      SlideEditorActivity.a(a, 2131493085);
      SlideEditorActivity.f(a);
      return;
    }
    catch (MmsException paramMzPduPart)
    {
      for (;;)
      {
        SlideEditorActivity.a(a, "add picture failed");
        wd.a(SlideEditorActivity.a(a, 2131493832, SlideEditorActivity.k(a)), a, 0, 1, true, 0);
      }
    }
    catch (gd paramMzPduPart)
    {
      for (;;)
      {
        wd.a(a, SlideEditorActivity.b(a, 2131493160), null);
      }
    }
    catch (gc paramMzPduPart)
    {
      for (;;)
      {
        wd.a(a, SlideEditorActivity.b(a, 2131492963), SlideEditorActivity.b(a, 2131493086));
      }
    }
    catch (fk paramMzPduPart)
    {
      for (;;)
      {
        wd.a(a, SlideEditorActivity.b(a, 2131493820), SlideEditorActivity.a(a, 2131493832, SlideEditorActivity.k(a)));
      }
    }
  }
}

/* Location:
 * Qualified Name:     yb
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */