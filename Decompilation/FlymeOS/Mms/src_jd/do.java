import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

class do
  implements View.OnClickListener
{
  do(dn paramdn, Activity paramActivity) {}
  
  public void onClick(View paramView)
  {
    paramView = new dl(a, br.e.duoqu_popup_alertdialog, br.g.Theme_dialog);
    ((TextView)paramView.findViewById(br.d.duoqu_alertdialog_content)).setText(dn.a(b));
    ((Button)paramView.findViewById(br.d.duoqu_alertdialog_ok)).setOnClickListener(new dp(this, paramView));
    ((Button)paramView.findViewById(br.d.duoqu_alertdialog_cancel)).setOnClickListener(new dq(this, paramView));
    paramView.show();
  }
}

/* Location:
 * Qualified Name:     do
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */