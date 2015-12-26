/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  miui.cloud.backup.CloudBackupServiceBase
 *  miui.cloud.backup.ICloudBackup
 */
package com.android.mms.cloudbackup;

import com.android.mms.cloudbackup.SettingsCloudBackupImpl;
import miui.cloud.backup.CloudBackupServiceBase;
import miui.cloud.backup.ICloudBackup;

public class SettingsCloudBackupService
extends CloudBackupServiceBase {
    protected ICloudBackup getBackupImpl() {
        return new SettingsCloudBackupImpl();
    }
}

