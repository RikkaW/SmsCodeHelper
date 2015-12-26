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
 *  java.lang.Throwable
 */
package com.android.fileexplorer.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IQueryCallBack
extends IInterface {
    public void onQueryFinish() throws RemoteException;

    public boolean onQueryItem(String var1, int var2) throws RemoteException;

    public void onQueryItemEnd(String var1, String var2) throws RemoteException;

    public void onStartQuery(int var1) throws RemoteException;

    public static abstract class Stub
    extends Binder
    implements IQueryCallBack {
        public Stub() {
            this.attachInterface((IInterface)this, "com.android.fileexplorer.service.IQueryCallBack");
        }

        public static IQueryCallBack asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterface = iBinder.queryLocalInterface("com.android.fileexplorer.service.IQueryCallBack");
            if (iInterface != null && iInterface instanceof IQueryCallBack) {
                return (IQueryCallBack)iInterface;
            }
            return new Proxy(iBinder);
        }

        public IBinder asBinder() {
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean onTransact(int n, Parcel parcel, Parcel parcel2, int n2) throws RemoteException {
            switch (n) {
                default: {
                    return super.onTransact(n, parcel, parcel2, n2);
                }
                case 1598968902: {
                    parcel2.writeString("com.android.fileexplorer.service.IQueryCallBack");
                    return true;
                }
                case 1: {
                    parcel.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
                    this.onStartQuery(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                }
                case 2: {
                    parcel.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
                    boolean bl = this.onQueryItem(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    n = bl ? 1 : 0;
                    parcel2.writeInt(n);
                    return true;
                }
                case 3: {
                    parcel.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
                    this.onQueryItemEnd(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                }
                case 4: 
            }
            parcel.enforceInterface("com.android.fileexplorer.service.IQueryCallBack");
            this.onQueryFinish();
            parcel2.writeNoException();
            return true;
        }

        private static class Proxy
        implements IQueryCallBack {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override
            public void onQueryFinish() throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
                    this.mRemote.transact(4, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public boolean onQueryItem(String string, int n) throws RemoteException {
                Parcel parcel;
                boolean bl;
                Parcel parcel2;
                block2 : {
                    bl = false;
                    parcel = Parcel.obtain();
                    parcel2 = Parcel.obtain();
                    try {
                        parcel.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
                        parcel.writeString(string);
                        parcel.writeInt(n);
                        this.mRemote.transact(2, parcel, parcel2, 0);
                        parcel2.readException();
                        n = parcel2.readInt();
                        if (n == 0) break block2;
                        bl = true;
                    }
                    catch (Throwable var1_2) {
                        parcel2.recycle();
                        parcel.recycle();
                        throw var1_2;
                    }
                }
                parcel2.recycle();
                parcel.recycle();
                return bl;
            }

            @Override
            public void onQueryItemEnd(String string, String string2) throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
                    parcel.writeString(string);
                    parcel.writeString(string2);
                    this.mRemote.transact(3, parcel, parcel2, 0);
                    parcel2.readException();
                    return;
                }
                finally {
                    parcel2.recycle();
                    parcel.recycle();
                }
            }

            @Override
            public void onStartQuery(int n) throws RemoteException {
                Parcel parcel = Parcel.obtain();
                Parcel parcel2 = Parcel.obtain();
                try {
                    parcel.writeInterfaceToken("com.android.fileexplorer.service.IQueryCallBack");
                    parcel.writeInt(n);
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

