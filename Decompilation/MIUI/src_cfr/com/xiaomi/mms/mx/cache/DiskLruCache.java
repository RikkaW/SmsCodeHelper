/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedInputStream
 *  java.io.BufferedWriter
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.Writer
 *  java.lang.Class
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.nio.charset.Charset
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.Map$Entry
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.xiaomi.mms.mx.cache;

import com.xiaomi.mms.mx.cache.ImageCacheUtils;
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
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DiskLruCache
implements Closeable {
    private static final Charset UTF_8 = Charset.forName((String)"UTF-8");
    private final int appVersion;
    private final Callable<Void> cleanupCallable;
    private final File directory;
    private final ExecutorService executorService = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, (BlockingQueue)new LinkedBlockingQueue());
    private final File journalFile;
    private final File journalFileTmp;
    private Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75f, true);
    private final long maxSize;
    private long nextSequenceNumber = 0;
    private int redundantOpCount;
    private long size = 0;
    private final int valueCount;

    private DiskLruCache(File file, int n, int n2, long l) {
        this.cleanupCallable = new Callable<Void>(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public Void call() throws Exception {
                DiskLruCache diskLruCache = DiskLruCache.this;
                synchronized (diskLruCache) {
                    if (DiskLruCache.this.journalWriter == null) {
                        return null;
                    }
                    DiskLruCache.this.trimToSize();
                    if (DiskLruCache.this.journalRebuildRequired()) {
                        DiskLruCache.this.rebuildJournal();
                        DiskLruCache.this.redundantOpCount = 0;
                    }
                    return null;
                }
            }
        };
        this.directory = file;
        this.appVersion = n;
        this.journalFile = new File(file, "journal");
        this.journalFileTmp = new File(file, "journal.tmp");
        this.valueCount = n2;
        this.maxSize = l;
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void completeEdit(Editor editor, boolean bl) throws IOException {
        synchronized (this) {
            Entry entry = editor.entry;
            Editor editor2 = entry.currentEditor;
            if (editor2 == editor) {
                int n;
                if (bl && !entry.readable) {
                    for (n = 0; n < this.valueCount; ++n) {
                        if (entry.getDirtyFile(n).exists()) continue;
                        editor.abort();
                        break;
                    }
                } else {
                    n = 0;
                    do {
                        long l;
                        if (n >= this.valueCount) {
                            ++this.redundantOpCount;
                            entry.currentEditor = null;
                            if (entry.readable | bl) {
                                entry.readable = true;
                                this.journalWriter.write("CLEAN " + entry.key + entry.getLengths() + '\n');
                                if (bl) {
                                    l = this.nextSequenceNumber;
                                    this.nextSequenceNumber = 1 + l;
                                    entry.sequenceNumber = l;
                                }
                            } else {
                                this.lruEntries.remove((Object)entry.key);
                                this.journalWriter.write("REMOVE " + entry.key + '\n');
                            }
                            if (this.size <= this.maxSize && !this.journalRebuildRequired()) break;
                            this.executorService.submit(this.cleanupCallable);
                            break;
                        }
                        editor = entry.getDirtyFile(n);
                        if (bl) {
                            if (editor.exists()) {
                                long l2;
                                editor2 = entry.getCleanFile(n);
                                editor.renameTo((File)editor2);
                                l = entry.lengths[n];
                                Entry.access$1000((Entry)entry)[n] = l2 = editor2.length();
                                this.size = this.size - l + l2;
                            }
                        } else {
                            DiskLruCache.deleteIfExists((File)editor);
                        }
                        ++n;
                    } while (true);
                }
            }
            return;
        }
    }

    private static <T> T[] copyOfRange(T[] arrT, int n, int n2) {
        int n3 = arrT.length;
        if (n > n2) {
            throw new IllegalArgumentException();
        }
        if (n < 0 || n > n3) {
            throw new ArrayIndexOutOfBoundsException();
        }
        n3 = Math.min((int)(n2 -= n), (int)(n3 - n));
        Object[] arrobject = (Object[])Array.newInstance(arrT.getClass().getComponentType(), n2);
        System.arraycopy(arrT, (int)n, (Object)arrobject, (int)0, (int)n3);
        return arrobject;
    }

    private static void deleteContents(File arrfile) throws IOException {
        if (arrfile.isDirectory()) {
            if ((arrfile = arrfile.listFiles()) != null) {
                for (File file : arrfile) {
                    if (file.isDirectory()) {
                        DiskLruCache.deleteContents(file);
                    }
                    if (file.delete()) continue;
                    throw new IOException("failed to delete file: " + (Object)file);
                }
            }
        } else {
            arrfile.delete();
        }
    }

    private static void deleteIfExists(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public static String getCommonUrlDiskKey(String string2) {
        return string2.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
    }

    private boolean journalRebuildRequired() {
        if (this.redundantOpCount >= 2000 && this.redundantOpCount >= this.lruEntries.size()) {
            return true;
        }
        return false;
    }

    public static DiskLruCache open(File object, int n, int n2, long l) throws IOException {
        if (l <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (n2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        DiskLruCache diskLruCache = new DiskLruCache((File)object, n, n2, l);
        if (diskLruCache.journalFile.exists()) {
            try {
                diskLruCache.readJournal();
                diskLruCache.processJournal();
                diskLruCache.journalWriter = new BufferedWriter((Writer)new FileWriter(diskLruCache.journalFile, true));
                return diskLruCache;
            }
            catch (IOException var6_5) {
                Log.e("DiskLruCache", "DiskLruCache " + object + " is corrupt: " + var6_5.getMessage() + ", removing");
                diskLruCache.delete();
            }
        }
        object.mkdirs();
        object = new DiskLruCache((File)object, n, n2, l);
        object.rebuildJournal();
        return object;
    }

    private void processJournal() throws IOException {
        DiskLruCache.deleteIfExists(this.journalFileTmp);
        Iterator<Entry> iterator = this.lruEntries.values().iterator();
        while (iterator.hasNext()) {
            int n;
            Entry entry = iterator.next();
            if (entry.currentEditor == null) {
                for (n = 0; n < this.valueCount; ++n) {
                    this.size += entry.lengths[n];
                }
                continue;
            }
            entry.currentEditor = null;
            for (n = 0; n < this.valueCount; ++n) {
                DiskLruCache.deleteIfExists(entry.getCleanFile(n));
                DiskLruCache.deleteIfExists(entry.getDirtyFile(n));
            }
            iterator.remove();
        }
    }

    private static String readAsciiLine(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(80);
        do {
            int n;
            if ((n = inputStream.read()) == -1) {
                throw new EOFException();
            }
            if (n == 10) {
                n = stringBuilder.length();
                if (n > 0 && stringBuilder.charAt(n - 1) == '\r') {
                    stringBuilder.setLength(n - 1);
                }
                return stringBuilder.toString();
            }
            stringBuilder.append((char)n);
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void readJournal() throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream((InputStream)new FileInputStream(this.journalFile));
        try {
            String string2 = DiskLruCache.readAsciiLine((InputStream)bufferedInputStream);
            String string3 = DiskLruCache.readAsciiLine((InputStream)bufferedInputStream);
            String string4 = DiskLruCache.readAsciiLine((InputStream)bufferedInputStream);
            String string5 = DiskLruCache.readAsciiLine((InputStream)bufferedInputStream);
            String string6 = DiskLruCache.readAsciiLine((InputStream)bufferedInputStream);
            if (!("libcore.io.DiskLruCache".equals((Object)string2) && "1".equals((Object)string3) && Integer.toString((int)this.appVersion).equals((Object)string4) && Integer.toString((int)this.valueCount).equals((Object)string5) && "".equals((Object)string6))) {
                throw new IOException("unexpected journal header: [" + string2 + ", " + string3 + ", " + string5 + ", " + string6 + "]");
            }
            do {
                this.readJournalLine(DiskLruCache.readAsciiLine((InputStream)bufferedInputStream));
            } while (true);
        }
        finally {
            ImageCacheUtils.closeQuietly((Closeable)bufferedInputStream);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void readJournalLine(String string2) throws IOException {
        String[] arrstring = string2.split(" ");
        if (arrstring.length < 2) {
            throw new IOException("unexpected journal line: " + string2);
        }
        String string3 = arrstring[1];
        if (arrstring[0].equals((Object)"REMOVE") && arrstring.length == 2) {
            this.lruEntries.remove((Object)string3);
            return;
        } else {
            Entry entry;
            Entry entry2 = entry = this.lruEntries.get(string3);
            if (entry == null) {
                entry2 = new Entry(string3);
                this.lruEntries.put((Object)string3, (Object)entry2);
            }
            if (arrstring[0].equals((Object)"CLEAN") && arrstring.length == this.valueCount + 2) {
                entry2.readable = true;
                entry2.currentEditor = null;
                entry2.setLengths(DiskLruCache.copyOfRange(arrstring, 2, arrstring.length));
                return;
            }
            if (arrstring[0].equals((Object)"DIRTY") && arrstring.length == 2) {
                entry2.currentEditor = new Editor(entry2);
                return;
            }
            if (arrstring[0].equals((Object)"READ") && arrstring.length == 2) return;
            {
                throw new IOException("unexpected journal line: " + string2);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void rebuildJournal() throws IOException {
        synchronized (this) {
            if (this.journalWriter != null) {
                this.journalWriter.close();
            }
            BufferedWriter bufferedWriter = new BufferedWriter((Writer)new FileWriter(this.journalFileTmp));
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString((int)this.appVersion));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString((int)this.valueCount));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            Iterator<Entry> iterator = this.lruEntries.values().iterator();
            do {
                if (!iterator.hasNext()) {
                    bufferedWriter.close();
                    this.journalFileTmp.renameTo(this.journalFile);
                    this.journalWriter = new BufferedWriter((Writer)new FileWriter(this.journalFile, true));
                    return;
                }
                Entry entry = iterator.next();
                if (entry.currentEditor != null) {
                    bufferedWriter.write("DIRTY " + entry.key + '\n');
                    continue;
                }
                bufferedWriter.write("CLEAN " + entry.key + entry.getLengths() + '\n');
            } while (true);
        }
    }

    private void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            this.remove((String)this.lruEntries.entrySet().iterator().next().getKey(), false);
        }
    }

    private String validateKey(String string2) {
        return Pattern.compile((String)"\\s+|\t|\r|\n").matcher((CharSequence)string2).replaceAll("");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void close() throws IOException {
        synchronized (this) {
            Writer writer = this.journalWriter;
            if (writer != null) {
                for (Entry entry : new ArrayList(this.lruEntries.values())) {
                    if (entry.currentEditor == null) continue;
                    entry.currentEditor.abort();
                }
                this.trimToSize();
                this.journalWriter.close();
                this.journalWriter = null;
            }
            return;
        }
    }

    public void delete() throws IOException {
        this.close();
        DiskLruCache.deleteContents(this.directory);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getCacheFilePath(String object) {
        synchronized (this) {
            block7 : {
                this.checkNotClosed();
                object = this.validateKey((String)object);
                object = this.lruEntries.get(object);
                if (object == null) return null;
                boolean bl = ((Entry)object).readable;
                if (bl) break block7;
                return null;
            }
            object = object.getCleanFile(0).getAbsolutePath();
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean remove(String object, boolean bl) throws IOException {
        synchronized (this) {
            this.checkNotClosed();
            String string2 = object;
            if (bl) {
                string2 = this.validateKey((String)object);
            }
            if ((object = this.lruEntries.get(string2)) == null) return false;
            Editor editor = ((Entry)object).currentEditor;
            if (editor != null) {
                return false;
            }
            for (int i = 0; i < this.valueCount; this.size -= Entry.access$1000((Entry)object)[i], ++i) {
                editor = object.getCleanFile(i);
                if (!editor.delete()) {
                    throw new IOException("failed to delete " + editor);
                }
                Entry.access$1000((Entry)object)[i] = 0;
            }
            ++this.redundantOpCount;
            this.journalWriter.append((CharSequence)("REMOVE " + string2 + '\n'));
            this.lruEntries.remove((Object)string2);
            if (!this.journalRebuildRequired()) return true;
            this.executorService.submit(this.cleanupCallable);
            return true;
        }
    }

    public final class Editor {
        private final Entry entry;

        private Editor(Entry entry) {
            this.entry = entry;
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }
    }

    private final class Entry {
        private Editor currentEditor;
        private final String key;
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;

        private Entry(String string2) {
            this.key = string2;
            this.lengths = new long[DiskLruCache.this.valueCount];
        }

        private IOException invalidLengths(String[] arrstring) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString((Object[])arrstring));
        }

        private void setLengths(String[] arrstring) throws IOException {
            if (arrstring.length != DiskLruCache.this.valueCount) {
                throw this.invalidLengths(arrstring);
            }
            int n = 0;
            do {
                try {
                    if (n >= arrstring.length) break;
                    this.lengths[n] = Long.parseLong((String)arrstring[n]);
                    ++n;
                    continue;
                }
                catch (NumberFormatException var3_3) {
                    throw this.invalidLengths(arrstring);
                }
            } while (true);
        }

        public File getCleanFile(int n) {
            return new File(DiskLruCache.this.directory, this.key + "." + n);
        }

        public File getDirtyFile(int n) {
            return new File(DiskLruCache.this.directory, this.key + "." + n + ".tmp");
        }

        public String getLengths() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long l : this.lengths) {
                stringBuilder.append(' ').append(l);
            }
            return stringBuilder.toString();
        }
    }

}

