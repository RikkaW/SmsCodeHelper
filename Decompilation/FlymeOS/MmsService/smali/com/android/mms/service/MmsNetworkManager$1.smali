.class Lcom/android/mms/service/MmsNetworkManager$1;
.super Landroid/net/ConnectivityManager$NetworkCallback;
.source "MmsNetworkManager.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/mms/service/MmsNetworkManager;->newRequest()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsNetworkManager;


# direct methods
.method constructor <init>(Lcom/android/mms/service/MmsNetworkManager;)V
    .locals 0

    .prologue
    .line 185
    iput-object p1, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    invoke-direct {p0}, Landroid/net/ConnectivityManager$NetworkCallback;-><init>()V

    return-void
.end method


# virtual methods
.method public onAvailable(Landroid/net/Network;)V
    .locals 3
    .param p1, "network"    # Landroid/net/Network;

    .prologue
    .line 188
    invoke-super {p0, p1}, Landroid/net/ConnectivityManager$NetworkCallback;->onAvailable(Landroid/net/Network;)V

    .line 189
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "NetworkCallbackListener.onAvailable: network="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 190
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    monitor-enter v1

    .line 191
    :try_start_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    # setter for: Lcom/android/mms/service/MmsNetworkManager;->mNetwork:Landroid/net/Network;
    invoke-static {v0, p1}, Lcom/android/mms/service/MmsNetworkManager;->access$002(Lcom/android/mms/service/MmsNetworkManager;Landroid/net/Network;)Landroid/net/Network;

    .line 192
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    invoke-virtual {v0}, Ljava/lang/Object;->notifyAll()V

    .line 193
    monitor-exit v1

    .line 194
    return-void

    .line 193
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public onLost(Landroid/net/Network;)V
    .locals 3
    .param p1, "network"    # Landroid/net/Network;

    .prologue
    .line 198
    invoke-super {p0, p1}, Landroid/net/ConnectivityManager$NetworkCallback;->onLost(Landroid/net/Network;)V

    .line 199
    const-string v0, "MmsService"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "NetworkCallbackListener.onLost: network="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 200
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    monitor-enter v1

    .line 201
    :try_start_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    const/4 v2, 0x1

    # setter for: Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkLost:Z
    invoke-static {v0, v2}, Lcom/android/mms/service/MmsNetworkManager;->access$102(Lcom/android/mms/service/MmsNetworkManager;Z)Z

    .line 202
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    # invokes: Lcom/android/mms/service/MmsNetworkManager;->releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V
    invoke-static {v0, p0}, Lcom/android/mms/service/MmsNetworkManager;->access$200(Lcom/android/mms/service/MmsNetworkManager;Landroid/net/ConnectivityManager$NetworkCallback;)V

    .line 203
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    invoke-virtual {v0}, Ljava/lang/Object;->notifyAll()V

    .line 204
    monitor-exit v1

    .line 205
    return-void

    .line 204
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method

.method public onUnavailable()V
    .locals 3

    .prologue
    .line 209
    invoke-super {p0}, Landroid/net/ConnectivityManager$NetworkCallback;->onUnavailable()V

    .line 210
    const-string v0, "MmsService"

    const-string v1, "NetworkCallbackListener.onUnavailable"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 211
    iget-object v1, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    monitor-enter v1

    .line 212
    :try_start_0
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    const/4 v2, 0x1

    # setter for: Lcom/android/mms/service/MmsNetworkManager;->mIsNetworkLost:Z
    invoke-static {v0, v2}, Lcom/android/mms/service/MmsNetworkManager;->access$102(Lcom/android/mms/service/MmsNetworkManager;Z)Z

    .line 213
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    # invokes: Lcom/android/mms/service/MmsNetworkManager;->releaseRequestLocked(Landroid/net/ConnectivityManager$NetworkCallback;)V
    invoke-static {v0, p0}, Lcom/android/mms/service/MmsNetworkManager;->access$200(Lcom/android/mms/service/MmsNetworkManager;Landroid/net/ConnectivityManager$NetworkCallback;)V

    .line 214
    iget-object v0, p0, Lcom/android/mms/service/MmsNetworkManager$1;->this$0:Lcom/android/mms/service/MmsNetworkManager;

    invoke-virtual {v0}, Ljava/lang/Object;->notifyAll()V

    .line 215
    monitor-exit v1

    .line 216
    return-void

    .line 215
    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0
.end method
