/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Message
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.concurrent.FutureTask
 *  java.util.concurrent.LinkedBlockingQueue
 *  java.util.concurrent.ThreadPoolExecutor
 *  java.util.concurrent.TimeUnit
 *  java.util.concurrent.atomic.AtomicBoolean
 */
package android.support.v4.content;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ModernAsyncTask$1;
import android.support.v4.content.ModernAsyncTask$2;
import android.support.v4.content.ModernAsyncTask$3;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class ModernAsyncTask<Params, Progress, Result> {
    private static final int CORE_POOL_SIZE = 5;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor;
    private static final InternalHandler sHandler;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    private final FutureTask<Result> mFuture;
    private volatile Status mStatus = Status.PENDING;
    private final AtomicBoolean mTaskInvoked = new AtomicBoolean();
    private final WorkerRunnable<Params, Result> mWorker;

    static {
        sThreadFactory = new ModernAsyncTask$1();
        sPoolWorkQueue = new LinkedBlockingQueue(10);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, 128, 1, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        sHandler = new InternalHandler();
        sDefaultExecutor = THREAD_POOL_EXECUTOR;
    }

    public ModernAsyncTask() {
        this.mWorker = new ModernAsyncTask$2(this);
        this.mFuture = new ModernAsyncTask$3(this, this.mWorker);
    }

    static /* synthetic */ AtomicBoolean access$200(ModernAsyncTask modernAsyncTask) {
        return modernAsyncTask.mTaskInvoked;
    }

    static /* synthetic */ Object access$300(ModernAsyncTask modernAsyncTask, Object object) {
        return modernAsyncTask.postResult(object);
    }

    static /* synthetic */ void access$400(ModernAsyncTask modernAsyncTask, Object object) {
        modernAsyncTask.postResultIfNotInvoked(object);
    }

    public static void execute(Runnable runnable) {
        sDefaultExecutor.execute(runnable);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void finish(Result Result) {
        if (this.isCancelled()) {
            this.onCancelled(Result);
        } else {
            this.onPostExecute(Result);
        }
        this.mStatus = Status.FINISHED;
    }

    public static void init() {
        sHandler.getLooper();
    }

    private Result postResult(Result Result) {
        sHandler.obtainMessage(1, new AsyncTaskResult<Object>(this, Result)).sendToTarget();
        return Result;
    }

    private void postResultIfNotInvoked(Result Result) {
        if (!this.mTaskInvoked.get()) {
            this.postResult(Result);
        }
    }

    public static void setDefaultExecutor(Executor executor) {
        sDefaultExecutor = executor;
    }

    public final boolean cancel(boolean bl2) {
        return this.mFuture.cancel(bl2);
    }

    protected /* varargs */ abstract Result doInBackground(Params ... var1);

    public final /* varargs */ ModernAsyncTask<Params, Progress, Result> execute(Params ... arrParams) {
        return this.executeOnExecutor(sDefaultExecutor, arrParams);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public final /* varargs */ ModernAsyncTask<Params, Progress, Result> executeOnExecutor(Executor var1_1, Params ... var2_2) {
        if (this.mStatus == Status.PENDING) ** GOTO lbl-1000
        switch (.$SwitchMap$android$support$v4$content$ModernAsyncTask$Status[this.mStatus.ordinal()]) {
            default: lbl-1000: // 2 sources:
            {
                this.mStatus = Status.RUNNING;
                this.onPreExecute();
                this.mWorker.mParams = var2_2;
                var1_1.execute((Runnable)this.mFuture);
                return this;
            }
            case 1: {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            case 2: 
        }
        throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
    }

    public final Result get() {
        return (Result)this.mFuture.get();
    }

    public final Result get(long l2, TimeUnit timeUnit) {
        return (Result)this.mFuture.get(l2, timeUnit);
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean isCancelled() {
        return this.mFuture.isCancelled();
    }

    protected void onCancelled() {
    }

    protected void onCancelled(Result Result) {
        this.onCancelled();
    }

    protected void onPostExecute(Result Result) {
    }

    protected void onPreExecute() {
    }

    protected /* varargs */ void onProgressUpdate(Progress ... arrProgress) {
    }

    protected final /* varargs */ void publishProgress(Progress ... arrProgress) {
        if (!this.isCancelled()) {
            sHandler.obtainMessage(2, new AsyncTaskResult<Progress>(this, arrProgress)).sendToTarget();
        }
    }

    static class AsyncTaskResult<Data> {
        final Data[] mData;
        final ModernAsyncTask mTask;

        /* varargs */ AsyncTaskResult(ModernAsyncTask modernAsyncTask, Data ... arrData) {
            this.mTask = modernAsyncTask;
            this.mData = arrData;
        }
    }

    static class InternalHandler
    extends Handler {
        private InternalHandler() {
        }

        public void handleMessage(Message message) {
            AsyncTaskResult asyncTaskResult = (AsyncTaskResult)message.obj;
            switch (message.what) {
                default: {
                    return;
                }
                case 1: {
                    asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
                    return;
                }
                case 2: 
            }
            asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
        }
    }

    public static enum Status {
        PENDING,
        RUNNING,
        FINISHED;
        

        private Status() {
        }
    }

    static abstract class WorkerRunnable<Params, Result>
    implements Callable<Result> {
        Params[] mParams;

        private WorkerRunnable() {
        }
    }

}

