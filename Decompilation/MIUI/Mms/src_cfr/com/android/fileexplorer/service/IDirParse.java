/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Binder
 *  android.os.IBinder
 *  android.os.IInterface
 *  android.os.Parcel
 *  android.os.RemoteException
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.fileexplorer.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.fileexplorer.service.IQueryCallBack;
import java.util.ArrayList;
import java.util.List;

public interface IDirParse
extends IInterface {
    public void queryDirInfo(List<String> var1, IQueryCallBack var2) throws RemoteException;

    public static abstract class Stub
    extends Binder
    implements IDirParse {
        public Stub() {
            this.attachInterface((IInterface)this, "com.android.fileexplorer.service.IDirParse");
        }

        public static IDirParse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.android.fileexplorer.service.IDirParse");
            if (iInterface != null && iInterface instanceof IDirParse) {
                return (IDirParse)iInterface;
            }
            return new Proxy(iBinder);
        }

        public boolean onTransact(int n, Parcel parcel, Parcel parcel2, int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.android.fileexplorer.service.IDirParse");
                    return true;
                }
                case 1: 
            }
            parcel.enforceInterface("com.android.fileexplorer.service.IDirParse");
            this.queryDirInfo((List)parcel.createStringArrayList(), IQueryCallBack.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy
        implements IDirParse {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void queryDirInfo(List<String> iBinder, IQueryCallBack iQueryCallBack) throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.android.fileexplorer.service.IDirParse");
                    parcel.writeStringList((List)iBinder);
                    iBinder = iQueryCallBack != null ? iQueryCallBack.asBinder() : null;
                    parcel.writeStrongBinder(iBinder);
                    this.mRemote.transact(1, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }
        }

    }

}

