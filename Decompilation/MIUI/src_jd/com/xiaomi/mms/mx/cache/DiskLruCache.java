package com.xiaomi.mms.mx.cache;

import com.xiaomi.mms.mx.utils.Log;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DiskLruCache
  implements Closeable
{
  private static final Charset UTF_8 = Charset.forName("UTF-8");
  private final int appVersion;
  private final Callable<Void> cleanupCallable = new Callable()
  {
    public Void call()
      throws Exception
    {
      synchronized (DiskLruCache.this)
      {
        if (journalWriter == null) {
          return null;
        }
        DiskLruCache.this.trimToSize();
        if (DiskLruCache.this.journalRebuildRequired())
        {
          DiskLruCache.this.rebuildJournal();
          DiskLruCache.access$402(DiskLruCache.this, 0);
        }
        return null;
      }
    }
  };
  private final File directory;
  private final ExecutorService executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue());
  private final File journalFile;
  private final File journalFileTmp;
  private Writer journalWriter;
  private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private final long maxSize;
  private long nextSequenceNumber = 0L;
  private int redundantOpCount;
  private long size = 0L;
  private final int valueCount;
  
  private DiskLruCache(File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    directory = paramFile;
    appVersion = paramInt1;
    journalFile = new File(paramFile, "journal");
    journalFileTmp = new File(paramFile, "journal.tmp");
    valueCount = paramInt2;
    maxSize = paramLong;
  }
  
  private void checkNotClosed()
  {
    if (journalWriter == null) {
      throw new IllegalStateException("cache is closed");
    }
  }
  
  private void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    Entry localEntry;
    Object localObject;
    for (;;)
    {
      try
      {
        localEntry = entry;
        localObject = currentEditor;
        if (localObject != paramEditor) {
          return;
        }
        if ((!paramBoolean) || (readable)) {
          break;
        }
        i = 0;
        if (i >= valueCount) {
          break;
        }
        if (!localEntry.getDirtyFile(i).exists()) {
          paramEditor.abort();
        } else {
          i += 1;
        }
      }
      finally {}
    }
    int i = 0;
    for (;;)
    {
      long l1;
      if (i < valueCount)
      {
        paramEditor = localEntry.getDirtyFile(i);
        if (paramBoolean)
        {
          if (paramEditor.exists())
          {
            localObject = localEntry.getCleanFile(i);
            paramEditor.renameTo((File)localObject);
            l1 = lengths[i];
            long l2 = ((File)localObject).length();
            lengths[i] = l2;
            size = (size - l1 + l2);
          }
        }
        else {
          deleteIfExists(paramEditor);
        }
      }
      else
      {
        redundantOpCount += 1;
        Entry.access$702(localEntry, null);
        if ((readable | paramBoolean))
        {
          Entry.access$602(localEntry, true);
          journalWriter.write("CLEAN " + key + localEntry.getLengths() + '\n');
          if (paramBoolean)
          {
            l1 = nextSequenceNumber;
            nextSequenceNumber = (1L + l1);
            Entry.access$1202(localEntry, l1);
          }
        }
        for (;;)
        {
          if ((size <= maxSize) && (!journalRebuildRequired())) {
            break label357;
          }
          executorService.submit(cleanupCallable);
          break;
          lruEntries.remove(key);
          journalWriter.write("REMOVE " + key + '\n');
        }
        label357:
        break;
      }
      i += 1;
    }
  }
  
  private static <T> T[] copyOfRange(T[] paramArrayOfT, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfT.length;
    if (paramInt1 > paramInt2) {
      throw new IllegalArgumentException();
    }
    if ((paramInt1 < 0) || (paramInt1 > i)) {
      throw new ArrayIndexOutOfBoundsException();
    }
    paramInt2 -= paramInt1;
    i = Math.min(paramInt2, i - paramInt1);
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), paramInt2);
    System.arraycopy(paramArrayOfT, paramInt1, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  private static void deleteContents(File paramFile)
    throws IOException
  {
    if (paramFile.isDirectory())
    {
      paramFile = paramFile.listFiles();
      if (paramFile != null)
      {
        int j = paramFile.length;
        int i = 0;
        while (i < j)
        {
          File localFile = paramFile[i];
          if (localFile.isDirectory()) {
            deleteContents(localFile);
          }
          if (!localFile.delete()) {
            throw new IOException("failed to delete file: " + localFile);
          }
          i += 1;
        }
      }
    }
    else
    {
      paramFile.delete();
    }
  }
  
  private static void deleteIfExists(File paramFile)
    throws IOException
  {
    if ((paramFile.exists()) && (!paramFile.delete())) {
      throw new IOException();
    }
  }
  
  public static String getCommonUrlDiskKey(String paramString)
  {
    return paramString.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
  }
  
  private boolean journalRebuildRequired()
  {
    return (redundantOpCount >= 2000) && (redundantOpCount >= lruEntries.size());
  }
  
  public static DiskLruCache open(File paramFile, int paramInt1, int paramInt2, long paramLong)
    throws IOException
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("valueCount <= 0");
    }
    DiskLruCache localDiskLruCache = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
    if (journalFile.exists()) {
      try
      {
        localDiskLruCache.readJournal();
        localDiskLruCache.processJournal();
        journalWriter = new BufferedWriter(new FileWriter(journalFile, true));
        return localDiskLruCache;
      }
      catch (IOException localIOException)
      {
        Log.e("DiskLruCache", "DiskLruCache " + paramFile + " is corrupt: " + localIOException.getMessage() + ", removing");
        localDiskLruCache.delete();
      }
    }
    paramFile.mkdirs();
    paramFile = new DiskLruCache(paramFile, paramInt1, paramInt2, paramLong);
    paramFile.rebuildJournal();
    return paramFile;
  }
  
  private void processJournal()
    throws IOException
  {
    deleteIfExists(journalFileTmp);
    Iterator localIterator = lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      int i;
      if (currentEditor == null)
      {
        i = 0;
        while (i < valueCount)
        {
          size += lengths[i];
          i += 1;
        }
      }
      else
      {
        Entry.access$702(localEntry, null);
        i = 0;
        while (i < valueCount)
        {
          deleteIfExists(localEntry.getCleanFile(i));
          deleteIfExists(localEntry.getDirtyFile(i));
          i += 1;
        }
        localIterator.remove();
      }
    }
  }
  
  private static String readAsciiLine(InputStream paramInputStream)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder(80);
    for (;;)
    {
      int i = paramInputStream.read();
      if (i == -1) {
        throw new EOFException();
      }
      if (i == 10)
      {
        i = localStringBuilder.length();
        if ((i > 0) && (localStringBuilder.charAt(i - 1) == '\r')) {
          localStringBuilder.setLength(i - 1);
        }
        return localStringBuilder.toString();
      }
      localStringBuilder.append((char)i);
    }
  }
  
  private void readJournal()
    throws IOException
  {
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(new FileInputStream(journalFile));
    try
    {
      String str1 = readAsciiLine(localBufferedInputStream);
      String str2 = readAsciiLine(localBufferedInputStream);
      String str3 = readAsciiLine(localBufferedInputStream);
      String str4 = readAsciiLine(localBufferedInputStream);
      String str5 = readAsciiLine(localBufferedInputStream);
      if ((!"libcore.io.DiskLruCache".equals(str1)) || (!"1".equals(str2)) || (!Integer.toString(appVersion).equals(str3)) || (!Integer.toString(valueCount).equals(str4)) || (!"".equals(str5))) {
        throw new IOException("unexpected journal header: [" + str1 + ", " + str2 + ", " + str4 + ", " + str5 + "]");
      }
    }
    finally
    {
      ImageCacheUtils.closeQuietly(localBufferedInputStream);
    }
  }
  
  private void readJournalLine(String paramString)
    throws IOException
  {
    String[] arrayOfString = paramString.split(" ");
    if (arrayOfString.length < 2) {
      throw new IOException("unexpected journal line: " + paramString);
    }
    String str = arrayOfString[1];
    if ((arrayOfString[0].equals("REMOVE")) && (arrayOfString.length == 2)) {
      lruEntries.remove(str);
    }
    do
    {
      return;
      Entry localEntry2 = (Entry)lruEntries.get(str);
      Entry localEntry1 = localEntry2;
      if (localEntry2 == null)
      {
        localEntry1 = new Entry(str, null);
        lruEntries.put(str, localEntry1);
      }
      if ((arrayOfString[0].equals("CLEAN")) && (arrayOfString.length == valueCount + 2))
      {
        Entry.access$602(localEntry1, true);
        Entry.access$702(localEntry1, null);
        localEntry1.setLengths((String[])copyOfRange(arrayOfString, 2, arrayOfString.length));
        return;
      }
      if ((arrayOfString[0].equals("DIRTY")) && (arrayOfString.length == 2))
      {
        Entry.access$702(localEntry1, new Editor(localEntry1, null));
        return;
      }
    } while ((arrayOfString[0].equals("READ")) && (arrayOfString.length == 2));
    throw new IOException("unexpected journal line: " + paramString);
  }
  
  private void rebuildJournal()
    throws IOException
  {
    for (;;)
    {
      try
      {
        if (journalWriter != null) {
          journalWriter.close();
        }
        BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(journalFileTmp));
        localBufferedWriter.write("libcore.io.DiskLruCache");
        localBufferedWriter.write("\n");
        localBufferedWriter.write("1");
        localBufferedWriter.write("\n");
        localBufferedWriter.write(Integer.toString(appVersion));
        localBufferedWriter.write("\n");
        localBufferedWriter.write(Integer.toString(valueCount));
        localBufferedWriter.write("\n");
        localBufferedWriter.write("\n");
        Iterator localIterator = lruEntries.values().iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        Entry localEntry = (Entry)localIterator.next();
        if (currentEditor != null) {
          localBufferedWriter.write("DIRTY " + key + '\n');
        } else {
          ((Writer)localObject).write("CLEAN " + key + localEntry.getLengths() + '\n');
        }
      }
      finally {}
    }
    ((Writer)localObject).close();
    journalFileTmp.renameTo(journalFile);
    journalWriter = new BufferedWriter(new FileWriter(journalFile, true));
  }
  
  private void trimToSize()
    throws IOException
  {
    while (size > maxSize) {
      remove((String)((Map.Entry)lruEntries.entrySet().iterator().next()).getKey(), false);
    }
  }
  
  private String validateKey(String paramString)
  {
    return Pattern.compile("\\s+|\t|\r|\n").matcher(paramString).replaceAll("");
  }
  
  public void close()
    throws IOException
  {
    for (;;)
    {
      try
      {
        Object localObject1 = journalWriter;
        if (localObject1 == null) {
          return;
        }
        localObject1 = new ArrayList(lruEntries.values()).iterator();
        if (((Iterator)localObject1).hasNext())
        {
          Entry localEntry = (Entry)((Iterator)localObject1).next();
          if (currentEditor == null) {
            continue;
          }
          currentEditor.abort();
          continue;
        }
        trimToSize();
      }
      finally {}
      journalWriter.close();
      journalWriter = null;
    }
  }
  
  public void delete()
    throws IOException
  {
    close();
    deleteContents(directory);
  }
  
  /* Error */
  public String getCacheFilePath(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 541	com/xiaomi/mms/mx/cache/DiskLruCache:checkNotClosed	()V
    //   6: aload_0
    //   7: aload_1
    //   8: invokespecial 543	com/xiaomi/mms/mx/cache/DiskLruCache:validateKey	(Ljava/lang/String;)Ljava/lang/String;
    //   11: astore_1
    //   12: aload_0
    //   13: getfield 65	com/xiaomi/mms/mx/cache/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   16: aload_1
    //   17: invokevirtual 467	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast 13	com/xiaomi/mms/mx/cache/DiskLruCache$Entry
    //   23: astore_1
    //   24: aload_1
    //   25: ifnull +12 -> 37
    //   28: aload_1
    //   29: invokestatic 166	com/xiaomi/mms/mx/cache/DiskLruCache$Entry:access$600	(Lcom/xiaomi/mms/mx/cache/DiskLruCache$Entry;)Z
    //   32: istore_2
    //   33: iload_2
    //   34: ifne +9 -> 43
    //   37: aconst_null
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: areturn
    //   43: aload_1
    //   44: iconst_0
    //   45: invokevirtual 179	com/xiaomi/mms/mx/cache/DiskLruCache$Entry:getCleanFile	(I)Ljava/io/File;
    //   48: invokevirtual 546	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   51: astore_1
    //   52: goto -13 -> 39
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	60	0	this	DiskLruCache
    //   0	60	1	paramString	String
    //   32	2	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	24	55	finally
    //   28	33	55	finally
    //   43	52	55	finally
  }
  
  public boolean remove(String paramString, boolean paramBoolean)
    throws IOException
  {
    for (;;)
    {
      String str;
      int i;
      try
      {
        checkNotClosed();
        str = paramString;
        if (paramBoolean) {
          str = validateKey(paramString);
        }
        paramString = (Entry)lruEntries.get(str);
        if (paramString != null)
        {
          localObject = currentEditor;
          if (localObject == null) {}
        }
        else
        {
          paramBoolean = false;
          return paramBoolean;
        }
        i = 0;
        if (i >= valueCount) {
          break label142;
        }
        Object localObject = paramString.getCleanFile(i);
        if (!((File)localObject).delete()) {
          throw new IOException("failed to delete " + localObject);
        }
      }
      finally {}
      size -= lengths[i];
      lengths[i] = 0L;
      i += 1;
      continue;
      label142:
      redundantOpCount += 1;
      journalWriter.append("REMOVE " + str + '\n');
      lruEntries.remove(str);
      if (journalRebuildRequired()) {
        executorService.submit(cleanupCallable);
      }
      paramBoolean = true;
    }
  }
  
  public final class Editor
  {
    private final DiskLruCache.Entry entry;
    
    private Editor(DiskLruCache.Entry paramEntry)
    {
      entry = paramEntry;
    }
    
    public void abort()
      throws IOException
    {
      DiskLruCache.this.completeEdit(this, false);
    }
  }
  
  private final class Entry
  {
    private DiskLruCache.Editor currentEditor;
    private final String key;
    private final long[] lengths;
    private boolean readable;
    private long sequenceNumber;
    
    private Entry(String paramString)
    {
      key = paramString;
      lengths = new long[valueCount];
    }
    
    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      throw new IOException("unexpected journal line: " + Arrays.toString(paramArrayOfString));
    }
    
    private void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != valueCount) {
        throw invalidLengths(paramArrayOfString);
      }
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw invalidLengths(paramArrayOfString);
      }
    }
    
    public File getCleanFile(int paramInt)
    {
      return new File(directory, key + "." + paramInt);
    }
    
    public File getDirtyFile(int paramInt)
    {
      return new File(directory, key + "." + paramInt + ".tmp");
    }
    
    public String getLengths()
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      long[] arrayOfLong = lengths;
      int j = arrayOfLong.length;
      int i = 0;
      while (i < j)
      {
        long l = arrayOfLong[i];
        localStringBuilder.append(' ').append(l);
        i += 1;
      }
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.DiskLruCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */