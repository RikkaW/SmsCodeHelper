import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class ash
{
  private static final String a = ash.class.getSimpleName();
  private Writer b;
  
  public void a(String paramString1, String paramString2)
  {
    File localFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "ted_logs");
    if (!localFile.isDirectory()) {
      localFile.mkdir();
    }
    try
    {
      if (!localFile.isDirectory()) {
        throw new IOException("Unable to create directory ted_logs. Maybe the SD card is mounted?");
      }
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
      return;
    }
    paramString1 = new File(localFile, paramString1);
    if (!paramString1.exists()) {
      paramString1.createNewFile();
    }
    b = new BufferedWriter(new FileWriter(paramString1, true));
    b.write(paramString2);
    b.flush();
  }
}

/* Location:
 * Qualified Name:     ash
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */