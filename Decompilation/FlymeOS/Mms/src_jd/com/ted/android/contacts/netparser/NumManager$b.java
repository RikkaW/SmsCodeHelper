package com.ted.android.contacts.netparser;

import aoc;
import aoh;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

class NumManager$b
  extends aoc
{
  final String a;
  
  public NumManager$b(NumManager paramNumManager, String paramString)
  {
    a = paramString;
  }
  
  public void a(HttpResponse paramHttpResponse)
  {
    if (!Thread.currentThread().isInterrupted())
    {
      int i = paramHttpResponse.getStatusLine().getStatusCode();
      if ((i >= 200) && (i < 300) && (!Thread.currentThread().isInterrupted())) {
        aoh.a(NumManager.a(b)).f(a);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.netparser.NumManager.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */