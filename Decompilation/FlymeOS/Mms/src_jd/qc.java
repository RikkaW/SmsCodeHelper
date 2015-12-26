import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.recipient.MessageRecipient;
import com.android.mms.ui.ComposeMessageActivity;
import java.util.ArrayList;
import java.util.List;

public class qc
  implements View.OnClickListener
{
  public qc(ComposeMessageActivity paramComposeMessageActivity) {}
  
  public void onClick(View paramView)
  {
    ComposeMessageActivity.b(a, true);
    ComposeMessageActivity.c(a, wd.g(a));
    Intent localIntent = new Intent("android.intent.action.MULTIPLE_PICK");
    localIntent.putExtra("com.android.contacts.extra.TITLE_EXTRA", a.getString(2131493570));
    if (aba.a().b()) {}
    for (paramView = "vnd.android.cursor.dir/phone_email";; paramView = "vnd.android.cursor.dir/phone_v2")
    {
      localIntent.setType(paramView);
      paramView = new ArrayList();
      localObject = ComposeMessageActivity.j(a).getValidNumbers();
      if (localObject == null) {
        break;
      }
      int i = 0;
      while (i < ((List)localObject).size())
      {
        ContentValues localContentValues = new ContentValues();
        localContentValues.put("data1", (String)((List)localObject).get(i));
        paramView.add(localContentValues);
        i += 1;
      }
    }
    Object localObject = localIntent.getExtras();
    ((Bundle)localObject).putParcelableArray("com.android.contacts.extra.MULTIPLE_PICK_DATAS", (Parcelable[])paramView.toArray(new Parcelable[paramView.size()]));
    paramView = new ArrayList(1);
    paramView.add("data1");
    ((Bundle)localObject).putStringArrayList("com.meizu.contacts.extra.KEY_COLUMNS", paramView);
    localIntent.putExtras((Bundle)localObject);
    a.startActivityForResult(localIntent, 109);
  }
}

/* Location:
 * Qualified Name:     qc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */