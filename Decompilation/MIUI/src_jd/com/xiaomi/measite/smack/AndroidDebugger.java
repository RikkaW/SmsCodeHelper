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
  implements SmackDebugger
{
  public static boolean printInterpreted = false;
  private ConnectionListener connListener = null;
  private Connection connection = null;
  private SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");
  private PacketListener listener = null;
  private Reader reader;
  private ReaderListener readerListener;
  private Writer writer;
  private WriterListener writerListener;
  
  public AndroidDebugger(Connection paramConnection, Writer paramWriter, Reader paramReader)
  {
    connection = paramConnection;
    writer = paramWriter;
    reader = paramReader;
    createDebug();
  }
  
  private void createDebug()
  {
    ObservableReader localObservableReader = new ObservableReader(reader);
    readerListener = new ReaderListener()
    {
      public void read(String paramAnonymousString)
      {
        MyLog.v("SMACK " + dateFormatter.format(new Date()) + " RCV  (" + connection.hashCode() + "): " + paramAnonymousString);
      }
    };
    localObservableReader.addReaderListener(readerListener);
    ObservableWriter localObservableWriter = new ObservableWriter(writer);
    writerListener = new WriterListener()
    {
      public void write(String paramAnonymousString)
      {
        MyLog.v("SMACK " + dateFormatter.format(new Date()) + " SENT (" + connection.hashCode() + "): " + paramAnonymousString);
      }
    };
    localObservableWriter.addWriterListener(writerListener);
    reader = localObservableReader;
    writer = localObservableWriter;
    listener = new PacketListener()
    {
      public void processPacket(Packet paramAnonymousPacket)
      {
        if (AndroidDebugger.printInterpreted) {
          MyLog.v("SMACK " + dateFormatter.format(new Date()) + " RCV PKT (" + connection.hashCode() + "): " + paramAnonymousPacket.toXML());
        }
      }
    };
    connListener = new ConnectionListener()
    {
      public void connectionClosed(Connection paramAnonymousConnection, int paramAnonymousInt, Exception paramAnonymousException)
      {
        MyLog.v("SMACK " + dateFormatter.format(new Date()) + " Connection closed (" + connection.hashCode() + ")");
      }
      
      public void connectionStarted(Connection paramAnonymousConnection)
      {
        MyLog.v("SMACK " + dateFormatter.format(new Date()) + " Connection started (" + connection.hashCode() + ")");
      }
      
      public void reconnectionFailed(Connection paramAnonymousConnection, Exception paramAnonymousException)
      {
        MyLog.v("SMACK " + dateFormatter.format(new Date()) + " Reconnection failed due to an exception (" + connection.hashCode() + ")");
        paramAnonymousException.printStackTrace();
      }
      
      public void reconnectionSuccessful(Connection paramAnonymousConnection)
      {
        MyLog.v("SMACK " + dateFormatter.format(new Date()) + " Connection reconnected (" + connection.hashCode() + ")");
      }
    };
  }
  
  public Reader getReader()
  {
    return reader;
  }
  
  public PacketListener getReaderListener()
  {
    return listener;
  }
  
  public Writer getWriter()
  {
    return writer;
  }
  
  public PacketListener getWriterListener()
  {
    return null;
  }
  
  public Reader newConnectionReader(Reader paramReader)
  {
    ((ObservableReader)reader).removeReaderListener(readerListener);
    paramReader = new ObservableReader(paramReader);
    paramReader.addReaderListener(readerListener);
    reader = paramReader;
    return reader;
  }
  
  public Writer newConnectionWriter(Writer paramWriter)
  {
    ((ObservableWriter)writer).removeWriterListener(writerListener);
    paramWriter = new ObservableWriter(paramWriter);
    paramWriter.addWriterListener(writerListener);
    writer = paramWriter;
    return writer;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.measite.smack.AndroidDebugger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */