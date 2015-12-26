import android.content.ContentResolver;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Html.ImageGetter;
import android.util.Log;
import com.android.ex.editstyledtext.EditStyledText;
import com.android.ex.editstyledtext.EditStyledText.f;
import java.io.InputStream;

public class fi
  implements Html.ImageGetter
{
  public fi(EditStyledText.f paramf) {}
  
  public Drawable getDrawable(String paramString)
  {
    Log.d("EditStyledText", "--- sethtml: src=" + paramString);
    if (paramString.startsWith("content://"))
    {
      Uri localUri = Uri.parse(paramString);
      try
      {
        System.gc();
        InputStream localInputStream = EditStyledText.f.a(a).getContext().getContentResolver().openInputStream(localUri);
        paramString = new BitmapFactory.Options();
        inJustDecodeBounds = true;
        BitmapFactory.decodeStream(localInputStream, null, paramString);
        localInputStream.close();
        localInputStream = EditStyledText.f.a(a).getContext().getContentResolver().openInputStream(localUri);
        int i = outWidth;
        int j = outHeight;
        if (outWidth > EditStyledText.a(a.a))
        {
          i = EditStyledText.a(a.a);
          j = j * EditStyledText.a(a.a) / outWidth;
        }
        for (paramString = BitmapFactory.decodeStream(localInputStream, new Rect(0, 0, i, j), null);; paramString = BitmapFactory.decodeStream(localInputStream))
        {
          paramString = new BitmapDrawable(EditStyledText.f.a(a).getContext().getResources(), paramString);
          paramString.setBounds(0, 0, i, j);
          localInputStream.close();
          return paramString;
        }
        return null;
      }
      catch (Exception paramString)
      {
        Log.e("EditStyledText", "--- set html: Failed to loaded content " + localUri, paramString);
        return null;
      }
      catch (OutOfMemoryError paramString)
      {
        Log.e("EditStyledText", "OutOfMemoryError");
        EditStyledText.f.a(a).setHint(5);
        return null;
      }
    }
  }
}

/* Location:
 * Qualified Name:     fi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */