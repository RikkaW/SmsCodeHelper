.class Lcom/android/mms/service/MmsService$2;
.super Ljava/lang/Object;
.source "MmsService.java"

# interfaces
.implements Ljava/util/concurrent/Callable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/android/mms/service/MmsService;->readPduFromContentUri(Landroid/net/Uri;I)[B
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Ljava/util/concurrent/Callable",
        "<[B>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lcom/android/mms/service/MmsService;

.field final synthetic val$contentUri:Landroid/net/Uri;

.field final synthetic val$maxSize:I


# direct methods
.method constructor <init>(Lcom/android/mms/service/MmsService;Landroid/net/Uri;I)V
    .locals 0

    .prologue
    .line 913
    iput-object p1, p0, Lcom/android/mms/service/MmsService$2;->this$0:Lcom/android/mms/service/MmsService;

    iput-object p2, p0, Lcom/android/mms/service/MmsService$2;->val$contentUri:Landroid/net/Uri;

    iput p3, p0, Lcom/android/mms/service/MmsService$2;->val$maxSize:I

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public bridge synthetic call()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/Exception;
        }
    .end annotation

    .prologue
    .line 913
    invoke-virtual {p0}, Lcom/android/mms/service/MmsService$2;->call()[B

    move-result-object v0

    return-object v0
.end method

.method public call()[B
    .locals 10

    .prologue
    const/4 v7, 0x0

    .line 915
    const/4 v3, 0x0

    .line 917
    .local v3, "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    :try_start_0
    iget-object v8, p0, Lcom/android/mms/service/MmsService$2;->this$0:Lcom/android/mms/service/MmsService;

    invoke-virtual {v8}, Lcom/android/mms/service/MmsService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v1

    .line 918
    .local v1, "cr":Landroid/content/ContentResolver;
    iget-object v8, p0, Lcom/android/mms/service/MmsService$2;->val$contentUri:Landroid/net/Uri;

    const-string v9, "r"

    invoke-virtual {v1, v8, v9}, Landroid/content/ContentResolver;->openFileDescriptor(Landroid/net/Uri;Ljava/lang/String;)Landroid/os/ParcelFileDescriptor;

    move-result-object v5

    .line 919
    .local v5, "pduFd":Landroid/os/ParcelFileDescriptor;
    new-instance v4, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;

    invoke-direct {v4, v5}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;-><init>(Landroid/os/ParcelFileDescriptor;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 921
    .end local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .local v4, "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    :try_start_1
    iget v8, p0, Lcom/android/mms/service/MmsService$2;->val$maxSize:I

    add-int/lit8 v8, v8, 0x1

    new-array v6, v8, [B

    .line 922
    .local v6, "tempBody":[B
    const/4 v8, 0x0

    iget v9, p0, Lcom/android/mms/service/MmsService$2;->val$maxSize:I

    add-int/lit8 v9, v9, 0x1

    invoke-virtual {v4, v6, v8, v9}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;->read([BII)I

    move-result v0

    .line 923
    .local v0, "bytesRead":I
    if-nez v0, :cond_2

    .line 924
    const-string v8, "MmsService"

    const-string v9, "MmsService.readPduFromContentUri: empty PDU"

    invoke-static {v8, v9}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_6
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 937
    if-eqz v4, :cond_0

    .line 939
    :try_start_2
    invoke-virtual {v4}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;->close()V
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_2

    :cond_0
    :goto_0
    move-object v3, v4

    .line 941
    .end local v0    # "bytesRead":I
    .end local v1    # "cr":Landroid/content/ContentResolver;
    .end local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .end local v5    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .end local v6    # "tempBody":[B
    .restart local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    :cond_1
    :goto_1
    return-object v7

    .line 927
    .end local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v0    # "bytesRead":I
    .restart local v1    # "cr":Landroid/content/ContentResolver;
    .restart local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v5    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .restart local v6    # "tempBody":[B
    :cond_2
    :try_start_3
    iget v8, p0, Lcom/android/mms/service/MmsService$2;->val$maxSize:I

    if-gt v0, v8, :cond_4

    .line 928
    invoke-static {v6, v0}, Ljava/util/Arrays;->copyOf([BI)[B
    :try_end_3
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_3} :catch_6
    .catchall {:try_start_3 .. :try_end_3} :catchall_1

    move-result-object v7

    .line 937
    if-eqz v4, :cond_3

    .line 939
    :try_start_4
    invoke-virtual {v4}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;->close()V
    :try_end_4
    .catch Ljava/io/IOException; {:try_start_4 .. :try_end_4} :catch_3

    :cond_3
    :goto_2
    move-object v3, v4

    .line 941
    .end local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    goto :goto_1

    .line 930
    .end local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    :cond_4
    :try_start_5
    const-string v8, "MmsService"

    const-string v9, "MmsService.readPduFromContentUri: PDU too large"

    invoke-static {v8, v9}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_5
    .catch Ljava/io/IOException; {:try_start_5 .. :try_end_5} :catch_6
    .catchall {:try_start_5 .. :try_end_5} :catchall_1

    .line 937
    if-eqz v4, :cond_5

    .line 939
    :try_start_6
    invoke-virtual {v4}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;->close()V
    :try_end_6
    .catch Ljava/io/IOException; {:try_start_6 .. :try_end_6} :catch_4

    :cond_5
    :goto_3
    move-object v3, v4

    .line 941
    .end local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    goto :goto_1

    .line 932
    .end local v0    # "bytesRead":I
    .end local v1    # "cr":Landroid/content/ContentResolver;
    .end local v5    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .end local v6    # "tempBody":[B
    :catch_0
    move-exception v2

    .line 933
    .local v2, "ex":Ljava/io/IOException;
    :goto_4
    :try_start_7
    const-string v8, "MmsService"

    const-string v9, "MmsService.readPduFromContentUri: IO exception reading PDU"

    invoke-static {v8, v9, v2}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    :try_end_7
    .catchall {:try_start_7 .. :try_end_7} :catchall_0

    .line 937
    if-eqz v3, :cond_1

    .line 939
    :try_start_8
    invoke-virtual {v3}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;->close()V
    :try_end_8
    .catch Ljava/io/IOException; {:try_start_8 .. :try_end_8} :catch_1

    goto :goto_1

    .line 940
    :catch_1
    move-exception v8

    goto :goto_1

    .line 937
    .end local v2    # "ex":Ljava/io/IOException;
    :catchall_0
    move-exception v7

    :goto_5
    if-eqz v3, :cond_6

    .line 939
    :try_start_9
    invoke-virtual {v3}, Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;->close()V
    :try_end_9
    .catch Ljava/io/IOException; {:try_start_9 .. :try_end_9} :catch_5

    .line 941
    :cond_6
    :goto_6
    throw v7

    .line 940
    .end local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v0    # "bytesRead":I
    .restart local v1    # "cr":Landroid/content/ContentResolver;
    .restart local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v5    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .restart local v6    # "tempBody":[B
    :catch_2
    move-exception v8

    goto :goto_0

    :catch_3
    move-exception v8

    goto :goto_2

    :catch_4
    move-exception v8

    goto :goto_3

    .end local v0    # "bytesRead":I
    .end local v1    # "cr":Landroid/content/ContentResolver;
    .end local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .end local v5    # "pduFd":Landroid/os/ParcelFileDescriptor;
    .end local v6    # "tempBody":[B
    .restart local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    :catch_5
    move-exception v8

    goto :goto_6

    .line 937
    .end local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v1    # "cr":Landroid/content/ContentResolver;
    .restart local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v5    # "pduFd":Landroid/os/ParcelFileDescriptor;
    :catchall_1
    move-exception v7

    move-object v3, v4

    .end local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    goto :goto_5

    .line 932
    .end local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    :catch_6
    move-exception v2

    move-object v3, v4

    .end local v4    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    .restart local v3    # "inStream":Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
    goto :goto_4
.end method
