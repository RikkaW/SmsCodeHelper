/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.Reader
 *  java.io.Writer
 *  java.lang.Object
 *  java.lang.String
 *  java.text.SimpleDateFormat
 *  java.util.Date
 */
package com.xiaomi.measite.smack;

import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.smack.Connection;
import com.xiaomi.smack.ConnectionListener;
import com.xiaomi.smack.PacketListener;
import com.xiaomi.smack.debugger.SmackDebugger;
import com.xiaomi.smack.packet.Packet;
import com.xiaomi.smack.util.ObservableReader;
import com.xiaomi.smack.util.ObservableWriter;
import com.xiaomi.smack.util.ReaderListener;
import com.xiaomi.smack.util.WriterListener;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AndroidDebugger
implements SmackDebugger {
    public static boolean printInterpreted = false;
    private ConnectionListener connListener = null;
    private Connection connection = null;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");
    private PacketListener listener = null;
    private Reader reader;
    private ReaderListener readerListener;
    private Writer writer;
    private WriterListener writerListener;

    public AndroidDebugger(Connection connection, Writer writer, Reader reader) {
        this.connection = connection;
        this.writer = writer;
        this.reader = reader;
        this.createDebug();
    }

    private void createDebug() {
        ObservableReader observableReader = new ObservableReader(this.reader);
        this.readerListener = new ReaderListener(){

            @Override
            public void read(String string2) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " RCV  (" + AndroidDebugger.this.connection.hashCode() + "): " + string2);
            }
        };
        observableReader.addReaderListener(this.readerListener);
        ObservableWriter observableWriter = new ObservableWriter(this.writer);
        this.writerListener = new WriterListener(){

            @Override
            public void write(String string2) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " SENT (" + AndroidDebugger.this.connection.hashCode() + "): " + string2);
            }
        };
        observableWriter.addWriterListener(this.writerListener);
        this.reader = observableReader;
        this.writer = observableWriter;
        this.listener = new PacketListener(){

            @Override
            public void processPacket(Packet packet) {
                if (AndroidDebugger.printInterpreted) {
                    MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " RCV PKT (" + AndroidDebugger.this.connection.hashCode() + "): " + packet.toXML());
                }
            }
        };
        this.connListener = new ConnectionListener(){

            @Override
            public void connectionClosed(Connection connection, int n, Exception exception) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection closed (" + AndroidDebugger.this.connection.hashCode() + ")");
            }

            @Override
            public void connectionStarted(Connection connection) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection started (" + AndroidDebugger.this.connection.hashCode() + ")");
            }

            @Override
            public void reconnectionFailed(Connection connection, Exception exception) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Reconnection failed due to an exception (" + AndroidDebugger.this.connection.hashCode() + ")");
                exception.printStackTrace();
            }

            @Override
            public void reconnectionSuccessful(Connection connection) {
                MyLog.v("SMACK " + AndroidDebugger.this.dateFormatter.format(new Date()) + " Connection reconnected (" + AndroidDebugger.this.connection.hashCode() + ")");
            }
        };
    }

    @Override
    public Reader getReader() {
        return this.reader;
    }

    @Override
    public PacketListener getReaderListener() {
        return this.listener;
    }

    @Override
    public Writer getWriter() {
        return this.writer;
    }

    @Override
    public PacketListener getWriterListener() {
        return null;
    }

    @Override
    public Reader newConnectionReader(Reader reader) {
        ((ObservableReader)this.reader).removeReaderListener(this.readerListener);
        reader = new ObservableReader(reader);
        reader.addReaderListener(this.readerListener);
        this.reader = reader;
        return this.reader;
    }

    @Override
    public Writer newConnectionWriter(Writer writer) {
        ((ObservableWriter)this.writer).removeWriterListener(this.writerListener);
        writer = new ObservableWriter(writer);
        writer.addWriterListener(this.writerListener);
        this.writer = writer;
        return this.writer;
    }

}

