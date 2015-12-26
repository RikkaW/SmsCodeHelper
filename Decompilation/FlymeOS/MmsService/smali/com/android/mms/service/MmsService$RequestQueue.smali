.class Lcom/android/mms/service/MmsService$RequestQueue;
.super Landroid/os/Handler;
.source "MmsService.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/android/mms/service/MmsService;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "RequestQueue"
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsService;


# direct methods
.method public constructor <init>(Lcom/android/mms/service/MmsService;Landroid/os/Looper;)V
    .locals 0
    .param p2, "looper"    # Landroid/os/Looper;

    .prologue
    .line 129
    iput-object p1, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    .line 130
    invoke-direct {p0, p2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    .line 131
    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 6
    .param p1, "msg"    # Landroid/os/Message;

    .prologue
    .line 135
    iget-object v1, p1, Landroid/os/Message;->obj:Ljava/lang/Object;

    check-cast v1, Lcom/android/mms/service/MmsRequest;

    .line 136
    .local v1, "request":Lcom/android/mms/service/MmsRequest;
    if-eqz v1, :cond_2

    .line 138
    :try_start_0
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    iget-object v4, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v1}, Lcom/android/mms/service/MmsRequest;->getSubId()I

    move-result v5

    # invokes: Lcom/android/mms/service/MmsService;->getNetworkManager(I)Lcom/android/mms/service/MmsNetworkManager;
    invoke-static {v4, v5}, Lcom/android/mms/service/MmsService;->access$000(Lcom/android/mms/service/MmsService;I)Lcom/android/mms/service/MmsNetworkManager;

    move-result-object v4

    invoke-virtual {v1, v3, v4}, Lcom/android/mms/service/MmsRequest;->execute(Landroid/content/Context;Lcom/android/mms/service/MmsNetworkManager;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 140
    iget-object v4, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    monitor-enter v4

    .line 141
    :try_start_1
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # operator-- for: Lcom/android/mms/service/MmsService;->mRunningRequestCount:I
    invoke-static {v3}, Lcom/android/mms/service/MmsService;->access$110(Lcom/android/mms/service/MmsService;)I

    .line 142
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # getter for: Lcom/android/mms/service/MmsService;->mRunningRequestCount:I
    invoke-static {v3}, Lcom/android/mms/service/MmsService;->access$100(Lcom/android/mms/service/MmsService;)I

    move-result v3

    if-gtz v3, :cond_0

    .line 143
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->movePendingSimRequestsToRunningSynchronized()V
    invoke-static {v3}, Lcom/android/mms/service/MmsService;->access$200(Lcom/android/mms/service/MmsService;)V

    .line 144
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_0
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # getter for: Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;
    invoke-static {v3}, Lcom/android/mms/service/MmsService;->access$300(Lcom/android/mms/service/MmsService;)Landroid/util/SparseArray;

    move-result-object v3

    invoke-virtual {v3}, Landroid/util/SparseArray;->size()I

    move-result v3

    if-ge v0, v3, :cond_0

    .line 145
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # getter for: Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;
    invoke-static {v3}, Lcom/android/mms/service/MmsService;->access$300(Lcom/android/mms/service/MmsService;)Landroid/util/SparseArray;

    move-result-object v3

    invoke-virtual {v3, v0}, Landroid/util/SparseArray;->keyAt(I)I

    move-result v2

    .line 146
    .local v2, "subId":I
    iget-object v3, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->updatePendingMmsRequestCountForSubId(I)V
    invoke-static {v3, v2}, Lcom/android/mms/service/MmsService;->access$400(Lcom/android/mms/service/MmsService;I)V

    .line 144
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 149
    .end local v0    # "i":I
    .end local v2    # "subId":I
    :cond_0
    monitor-exit v4

    .line 154
    :goto_1
    return-void

    .line 149
    :catchall_0
    move-exception v3

    monitor-exit v4
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v3

    .line 140
    :catchall_1
    move-exception v3

    iget-object v4, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    monitor-enter v4

    .line 141
    :try_start_2
    iget-object v5, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # operator-- for: Lcom/android/mms/service/MmsService;->mRunningRequestCount:I
    invoke-static {v5}, Lcom/android/mms/service/MmsService;->access$110(Lcom/android/mms/service/MmsService;)I

    .line 142
    iget-object v5, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # getter for: Lcom/android/mms/service/MmsService;->mRunningRequestCount:I
    invoke-static {v5}, Lcom/android/mms/service/MmsService;->access$100(Lcom/android/mms/service/MmsService;)I

    move-result v5

    if-gtz v5, :cond_1

    .line 143
    iget-object v5, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->movePendingSimRequestsToRunningSynchronized()V
    invoke-static {v5}, Lcom/android/mms/service/MmsService;->access$200(Lcom/android/mms/service/MmsService;)V

    .line 144
    const/4 v0, 0x0

    .restart local v0    # "i":I
    :goto_2
    iget-object v5, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # getter for: Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;
    invoke-static {v5}, Lcom/android/mms/service/MmsService;->access$300(Lcom/android/mms/service/MmsService;)Landroid/util/SparseArray;

    move-result-object v5

    invoke-virtual {v5}, Landroid/util/SparseArray;->size()I

    move-result v5

    if-ge v0, v5, :cond_1

    .line 145
    iget-object v5, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # getter for: Lcom/android/mms/service/MmsService;->mPendingSimRequestQueues:Landroid/util/SparseArray;
    invoke-static {v5}, Lcom/android/mms/service/MmsService;->access$300(Lcom/android/mms/service/MmsService;)Landroid/util/SparseArray;

    move-result-object v5

    invoke-virtual {v5, v0}, Landroid/util/SparseArray;->keyAt(I)I

    move-result v2

    .line 146
    .restart local v2    # "subId":I
    iget-object v5, p0, Lcom/android/mms/service/MmsService$RequestQueue;->this$0:Lcom/android/mms/service/MmsService;

    # invokes: Lcom/android/mms/service/MmsService;->updatePendingMmsRequestCountForSubId(I)V
    invoke-static {v5, v2}, Lcom/android/mms/service/MmsService;->access$400(Lcom/android/mms/service/MmsService;I)V

    .line 144
    add-int/lit8 v0, v0, 0x1

    goto :goto_2

    .line 149
    .end local v0    # "i":I
    .end local v2    # "subId":I
    :cond_1
    monitor-exit v4
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    throw v3

    :catchall_2
    move-exception v3

    :try_start_3
    monitor-exit v4
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    throw v3

    .line 152
    :cond_2
    const-string v3, "MmsService"

    const-string v4, "RequestQueue: handling empty request"

    invoke-static {v3, v4}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1
.end method
