import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.android.mms.ui.EditSlideDurationActivity;
import com.android.mms.ui.SlideEditorActivity;

public class yk
  implements DialogInterface.OnClickListener
{
  public yk(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 10)) {
      SlideEditorActivity.c(a).a(SlideEditorActivity.a(a), (paramInt + 1) * 1000);
    }
    for (;;)
    {
      paramDialogInterface.dismiss();
      return;
      Intent localIntent = new Intent(a, EditSlideDurationActivity.class);
      localIntent.putExtra("slide_index", SlideEditorActivity.a(a));
      localIntent.putExtra("slide_total", SlideEditorActivity.b(a).size());
      localIntent.putExtra("dur", SlideEditorActivity.b(a).a(SlideEditorActivity.a(a)).a() / 1000);
      a.startActivityForResult(localIntent, 6);
    }
  }
}

/* Location:
 * Qualified Name:     yk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */