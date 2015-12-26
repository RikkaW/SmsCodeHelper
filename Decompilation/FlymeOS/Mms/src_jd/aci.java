import android.util.Log;
import com.android.mms.MmsApp.e;

class aci
  implements MmsApp.e
{
  aci(ach paramach) {}
  
  public int a()
  {
    Log.d("VoicePlayer", "ProximityCallback:mCurrentAudioMode = " + ach.a(a));
    return ach.a(a);
  }
  
  public boolean b()
  {
    return ach.b(a);
  }
  
  public void c()
  {
    Log.d("VoicePlayer", "ProximityCallback:Set to the pthoneCall model");
    ach.a(a, true);
  }
  
  public void d()
  {
    Log.d("VoicePlayer", "ProximityCallback:Set to the speaker model");
    ach.a(a, false);
  }
}

/* Location:
 * Qualified Name:     aci
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */