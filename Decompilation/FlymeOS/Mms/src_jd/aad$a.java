import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class aad$a
  extends ann.a
{
  public aad$a(aad paramaad) {}
  
  public void a(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("result_dialog_answer");
    Log.d("MzAssistantHelper", "IVoiceAssistantCallback.Stub | onSuccess | answer = " + str + ", rlt = " + paramIntent);
    aad.a(a, aad.c(a), str);
  }
  
  public void b(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("error_code", -1);
    Log.d("MzAssistantHelper", "IVoiceAssistantCallback.Stub | onFailure rlt = " + paramIntent + ", errorCode = " + i);
    aad.d(a).sendEmptyMessage(i);
    a.a(aad.e(a), 5);
  }
}

/* Location:
 * Qualified Name:     aad.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */