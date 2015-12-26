import android.content.ContentResolver;
import android.util.Log;
import com.meizu.android.mms.MzContentType;
import java.util.ArrayList;

public class lf
  implements lg
{
  private static final ArrayList<String> a = ;
  private static final ArrayList<String> b = MzContentType.getAudioTypes();
  private static final ArrayList<String> c = MzContentType.getVideoTypes();
  
  public void a(int paramInt, long paramLong1, long paramLong2, ContentResolver paramContentResolver)
  {
    Log.d("Mms:app", "CarrierContentRestriction.checkMessageSize messageSize: " + paramLong1 + " increaseSize: " + paramLong2 + " MmsConfig.getMaxMessageSize: " + ga.f());
    if ((paramLong1 < 0L) || (paramLong2 < 0L)) {
      throw new fj("Negative message size or increase size");
    }
    paramLong1 += paramLong2;
    paramLong2 = lr.c(paramInt);
    if ((paramLong1 < 0L) || (paramLong1 > paramLong2)) {
      throw new fk("Exceed message size limitation");
    }
  }
  
  public void a(String paramString)
  {
    if (paramString == null) {
      throw new fj("Null content type to be check");
    }
    if (!a.contains(paramString)) {
      throw new gd("Unsupported image content type : " + paramString);
    }
  }
  
  public void b(String paramString)
  {
    if (paramString == null) {
      throw new fj("Null content type to be check");
    }
    if (!b.contains(paramString)) {
      throw new gd("Unsupported audio content type : " + paramString);
    }
  }
  
  public void c(String paramString)
  {
    if (paramString == null) {
      throw new fj("Null content type to be check");
    }
    if (!c.contains(paramString)) {
      throw new gd("Unsupported video content type : " + paramString);
    }
  }
}

/* Location:
 * Qualified Name:     lf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */