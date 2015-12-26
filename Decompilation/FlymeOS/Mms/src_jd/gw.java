import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony.Threads;
import java.util.Collection;
import java.util.Iterator;

final class gw
  extends AsyncTask<Void, Void, Void>
{
  gw(Collection paramCollection, boolean paramBoolean, Context paramContext) {}
  
  protected Void a(Void... paramVarArgs)
  {
    int j = 0;
    paramVarArgs = Uri.withAppendedPath(ContentUris.withAppendedId(Telephony.Threads.CONTENT_URI, 0L), "top");
    StringBuilder localStringBuilder = new StringBuilder("_id in (");
    Object localObject = a.iterator();
    int i = 0;
    while (((Iterator)localObject).hasNext())
    {
      long l = ((Long)((Iterator)localObject).next()).longValue();
      if (l > 0L)
      {
        if (i > 0) {
          localStringBuilder.append(",");
        }
        localStringBuilder.append(l);
        i += 1;
      }
    }
    localStringBuilder.append(")");
    gr.f = new ContentValues(1);
    localObject = gr.f;
    i = j;
    if (b) {
      i = 1;
    }
    ((ContentValues)localObject).put("top", Integer.valueOf(i));
    c.getContentResolver().update(paramVarArgs, gr.f, localStringBuilder.toString(), null);
    return null;
  }
}

/* Location:
 * Qualified Name:     gw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */