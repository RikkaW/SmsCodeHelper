package com.android.common.content;

import android.content.ContentProvider;
import android.database.sqlite.SQLiteTransactionListener;

public abstract class SQLiteContentProvider
  extends ContentProvider
  implements SQLiteTransactionListener
{
  private final ThreadLocal<Boolean> a = new ThreadLocal();
}

/* Location:
 * Qualified Name:     com.android.common.content.SQLiteContentProvider
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */