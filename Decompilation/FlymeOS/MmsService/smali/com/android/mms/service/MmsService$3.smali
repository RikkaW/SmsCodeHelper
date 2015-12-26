.class Lcom/android/mms/service/MmsService$3;
.super Ljava/lang/Object;
.source "MmsService.java"

# interfaces
.implements Ljava/util/concurrent/Callable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/mms/service/MmsService;->writePduToContentUri(Landroid/net/Uri;[B)Z
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/concurrent/Callable",
        "<",
        "Ljava/lang/Boolean;",
        ">;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsService;

.field final synthetic val$contentUri:Landroid/net/Uri;

.field final synthetic val$pdu:[B


# direct methods
.method constructor <init>(Lcom/android/mms/service/MmsService;Landroid/net/Uri;[B)V
    .locals 0

    .prologue
    .line 965
    iput-object p1, p0, Lcom/android/mms/service/MmsService$3;->this$0:Lcom/android/mms/service/MmsService;

    iput-object p2, p0, Lcom/android/mms/service/MmsService$3;->val$contentUri:Landroid/net/Uri;

    iput-object p3, p0, Lcom/android/mms/service/MmsService$3;->val$pdu:[B

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public call()Ljava/lang/Boolean;
    .locals 7

    .prologue
    .line 967
    const/4 v2, 0x0

    .line 969
    .local v2, "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    :try_start_0
    iget-object v5, p0, Lcom/android/mms/service/MmsService$3;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v5}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    .line 970
    .local v0, "cr":Landroid/content/ContentResolver;
    iget-object v5, p0, Lcom/android/mms/service/MmsService$3;->val$contentUri:Landroid/net/Uri;

    const-string v6, "w"

    invoke-virtual {v0, v5, v6}, Landroid/content/ContentResolver;->openFileDescriptor(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;

    move-result-object v4

    .line 971
    .local v4, "pduFd":Landroid/os/ParcelFileDescriptor;
    new-instance v3, Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;

    invoke-direct {v3, v4}, Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;-><init>(Landroid/os/ParcelFileDescriptor;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 972
    .end local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .local v3, "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    :try_start_1
    iget-object v5, p0, Lcom/android/mms/service/MmsService$3;->val$pdu:[B

    invoke-virtual {v3, v5}, Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;->write([B)V

    .line 973
    sget-object v5, Ljava/lang/Boolean;->TRUE:Ljava/lang/Boolean;
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_4
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 977
    if-eqz v3, :cond_0

    .line 979
    :try_start_2
    invoke-virtual {v3}, Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_2

    :cond_0
    :goto_0
    move-object v2, v3

    .line 981
    .end local v0    # "cr":Landroid/content/ContentResolver;
    .end local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .end local v4    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .restart local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    :cond_1
    :goto_1
    return-object v5

    .line 974
    :catch_0
    move-exception v1

    .line 975
    .local v1, "ex":Ljava/io/IOException;
    :goto_2
    :try_start_3
    sget-object v5, Ljava/lang/Boolean;->FALSE:Ljava/lang/Boolean;
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 977
    if-eqz v2, :cond_1

    .line 979
    :try_start_4
    invoke-virtual {v2}, Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_1

    goto :goto_1

    .line 980
    :catch_1
    move-exception v6

    goto :goto_1

    .line 977
    .end local v1    # "ex":Ljava/io/IOException;
    :catchall_0
    move-exception v5

    :goto_3
    if-eqz v2, :cond_2

    .line 979
    :try_start_5
    invoke-virtual {v2}, Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;->close()V
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_3

    .line 981
    :cond_2
    :goto_4
    throw v5

    .line 980
    .end local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v0    # "cr":Landroid/content/ContentResolver;
    .restart local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v4    # "pduFd":Landroid/os/ParcelFileDescriptor;
    :catch_2
    move-exception v6

    goto :goto_0

    .end local v0    # "cr":Landroid/content/ContentResolver;
    .end local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .end local v4    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .restart local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    :catch_3
    move-exception v6

    goto :goto_4

    .line 977
    .end local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v0    # "cr":Landroid/content/ContentResolver;
    .restart local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v4    # "pduFd":Landroid/os/ParcelFileDescriptor;
    :catchall_1
    move-exception v5

    move-object v2, v3

    .end local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    goto :goto_3

    .line 974
    .end local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    :catch_4
    move-exception v1

    move-object v2, v3

    .end local v3    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    .restart local v2    # "outStream":Landroid/os/ParcelFileDescriptor$AutoCloseOutputStream;
    goto :goto_2
.end method

.method public bridge synthetic call()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 965
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService$3;->call()Ljava/lang/Boolean;

    move-result-object v0

    return-object v0
.end method
