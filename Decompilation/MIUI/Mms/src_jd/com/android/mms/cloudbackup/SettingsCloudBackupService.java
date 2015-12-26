package com.android.mms.cloudbackup;

import miui.cloud.backup.CloudBackupServiceBase;
import miui.cloud.backup.ICloudBackup;

public class SettingsCloudBackupService
  extends CloudBackupServiceBase
{
  protected ICloudBackup getBackupImpl()
  {
    return new SettingsCloudBackupImpl();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.cloudbackup.SettingsCloudBackupService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */