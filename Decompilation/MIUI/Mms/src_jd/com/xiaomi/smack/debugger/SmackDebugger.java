package com.xiaomi.smack.debugger;

import com.xiaomi.smack.PacketListener;
import java.io.Reader;
import java.io.Writer;

public abstract interface SmackDebugger
{
  public abstract Reader getReader();
  
  public abstract PacketListener getReaderListener();
  
  public abstract Writer getWriter();
  
  public abstract PacketListener getWriterListener();
  
  public abstract Reader newConnectionReader(Reader paramReader);
  
  public abstract Writer newConnectionWriter(Writer paramWriter);
}

/* Location:
 * Qualified Name:     com.xiaomi.smack.debugger.SmackDebugger
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */