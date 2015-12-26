import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.widget.TextView;

public class aqr
{
  private final Context a;
  private final Window b;
  private final DialogInterface c;
  private TextView d;
  private CharSequence e;
  
  public aqr(Context paramContext, DialogInterface paramDialogInterface, Window paramWindow)
  {
    a = paramContext;
    b = paramWindow;
    c = paramDialogInterface;
  }
  
  public void a()
  {
    d = ((TextView)b.findViewById(16908299));
    if (e != null) {
      d.post(new aqs(this));
    }
  }
  
  public void a(CharSequence paramCharSequence)
  {
    e = paramCharSequence;
  }
}

/* Location:
 * Qualified Name:     aqr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */