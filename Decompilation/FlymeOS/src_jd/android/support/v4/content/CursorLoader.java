package android.support.v4.content;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader
  extends AsyncTaskLoader<Cursor>
{
  Cursor mCursor;
  final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
  String[] mProjection;
  String mSelection;
  String[] mSelectionArgs;
  String mSortOrder;
  Uri mUri;
  
  public CursorLoader(Context paramContext)
  {
    super(paramContext);
  }
  
  public CursorLoader(Context paramContext, Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    super(paramContext);
    mUri = paramUri;
    mProjection = paramArrayOfString1;
    mSelection = paramString1;
    mSelectionArgs = paramArrayOfString2;
    mSortOrder = paramString2;
  }
  
  public void deliverResult(Cursor paramCursor)
  {
    if (isReset()) {
      if (paramCursor != null) {
        paramCursor.close();
      }
    }
    Cursor localCursor;
    do
    {
      return;
      localCursor = mCursor;
      mCursor = paramCursor;
      if (isStarted()) {
        super.deliverResult(paramCursor);
      }
    } while ((localCursor == null) || (localCursor == paramCursor) || (localCursor.isClosed()));
    localCursor.close();
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mUri=");
    paramPrintWriter.println(mUri);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mProjection=");
    paramPrintWriter.println(Arrays.toString(mProjection));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelection=");
    paramPrintWriter.println(mSelection);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelectionArgs=");
    paramPrintWriter.println(Arrays.toString(mSelectionArgs));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSortOrder=");
    paramPrintWriter.println(mSortOrder);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCursor=");
    paramPrintWriter.println(mCursor);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mContentChanged=");
    paramPrintWriter.println(mContentChanged);
  }
  
  public String[] getProjection()
  {
    return mProjection;
  }
  
  public String getSelection()
  {
    return mSelection;
  }
  
  public String[] getSelectionArgs()
  {
    return mSelectionArgs;
  }
  
  public String getSortOrder()
  {
    return mSortOrder;
  }
  
  public Uri getUri()
  {
    return mUri;
  }
  
  public Cursor loadInBackground()
  {
    Cursor localCursor = getContext().getContentResolver().query(mUri, mProjection, mSelection, mSelectionArgs, mSortOrder);
    if (localCursor != null)
    {
      localCursor.getCount();
      localCursor.registerContentObserver(mObserver);
    }
    return localCursor;
  }
  
  public void onCanceled(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  protected void onReset()
  {
    super.onReset();
    onStopLoading();
    if ((mCursor != null) && (!mCursor.isClosed())) {
      mCursor.close();
    }
    mCursor = null;
  }
  
  protected void onStartLoading()
  {
    if (mCursor != null) {
      deliverResult(mCursor);
    }
    if ((takeContentChanged()) || (mCursor == null)) {
      forceLoad();
    }
  }
  
  protected void onStopLoading()
  {
    cancelLoad();
  }
  
  public void setProjection(String[] paramArrayOfString)
  {
    mProjection = paramArrayOfString;
  }
  
  public void setSelection(String paramString)
  {
    mSelection = paramString;
  }
  
  public void setSelectionArgs(String[] paramArrayOfString)
  {
    mSelectionArgs = paramArrayOfString;
  }
  
  public void setSortOrder(String paramString)
  {
    mSortOrder = paramString;
  }
  
  public void setUri(Uri paramUri)
  {
    mUri = paramUri;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.content.CursorLoader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */