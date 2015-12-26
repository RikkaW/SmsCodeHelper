package com.xiaomi.channel.commonutils.file;

import java.io.File;
import java.io.FileFilter;

final class IOUtils$1
  implements FileFilter
{
  public boolean accept(File paramFile)
  {
    return paramFile.isDirectory();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.file.IOUtils.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */