package com.google.common.util.concurrent;

import java.util.logging.Level;
import java.util.logging.Logger;

final class UncaughtExceptionHandlers$Exiter
  implements Thread.UncaughtExceptionHandler
{
  private static final Logger logger = Logger.getLogger(Exiter.class.getName());
  private final Runtime runtime;
  
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    logger.log(Level.SEVERE, String.format("Caught an exception in %s.  Shutting down.", new Object[] { paramThread }), paramThrowable);
    runtime.exit(1);
  }
}

/* Location:
 * Qualified Name:     com.google.common.util.concurrent.UncaughtExceptionHandlers.Exiter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */