package tachyon.worker.block;

import tachyon.thrift.*;

/**
 * Handles all thrift RPC calls to the worker.
 */
public class BlockWorkerServiceHandler implements WorkerService.Iface {

  private final BlockWorker mBlockWorker;

  public BlockWorkerServiceHandler(BlockWorker blockWorker) {
    mBlockWorker = blockWorker;
  }

  public String createBlock(long userId, long blockId, long blockSize, int tierHint)
      throws OutOfSpaceException, FileAlreadyExistException {
    return mBlockWorker.createBlock(userId, blockId, blockSize, tierHint);
  }

  // ================================ WORKER V1 INTERFACE =======================================
  public void accessBlock(long blockId) throws org.apache.thrift.TException {

  }

  public void addCheckpoint(long userId, int fileId) throws FileDoesNotExistException,
      SuspectedFileSizeException, FailedToCheckpointException, BlockInfoException,
      org.apache.thrift.TException {

  }

  public boolean asyncCheckpoint(int fileId) throws TachyonException, org.apache.thrift.TException {
    return false;
  }

  /**
   * Used to cache a block into Tachyon space, worker will move the temporary block file from user
   * folder to data folder, and update the space usage information related. then update the block
   * information to master.
   *
   * @param userId
   * @param blockId
   */
  public void cacheBlock(long userId, long blockId) throws FileDoesNotExistException,
      BlockInfoException, org.apache.thrift.TException {

  }

  /**
   * Used to cancel a block which is being written. worker will delete the temporary block file and
   * the location and space information related, then reclaim space allocated to the block.
   *
   * @param userId
   * @param blockId
   */
  public void cancelBlock(long userId, long blockId) throws org.apache.thrift.TException {

  }

  /**
   * Used to get user's temporary folder on under file system, and the path of the user's temporary
   * folder will be returned.
   *
   * @param userId
   */
  public String getUserUfsTempFolder(long userId) throws org.apache.thrift.TException {
    return null;
  }

  /**
   * Lock the file in Tachyon's space while the user is reading it, and the path of the block file
   * locked will be returned, if the block file is not found, FileDoesNotExistException will be
   * thrown.
   *
   * @param blockId
   * @param userId
   */
  public String lockBlock(long blockId, long userId) throws FileDoesNotExistException,
      org.apache.thrift.TException {
    return null;
  }

  /**
   * Used to promote block on under storage layer to top storage layer when there are more than one
   * storage layers in Tachyon's space. return true if the block is successfully promoted, false
   * otherwise.
   *
   * @param blockId
   */
  public boolean promoteBlock(long blockId) throws org.apache.thrift.TException {
    return false;
  }

  /**
   * Used to allocate location and space for a new coming block, worker will choose the appropriate
   * storage directory which fits the initial block size by some allocation strategy, and the
   * temporary file path of the block file will be returned. if there is no enough space on Tachyon
   * storage OutOfSpaceException will be thrown, if the file is already being written by the user,
   * FileAlreadyExistException will be thrown.
   *
   * @param userId
   * @param blockId
   * @param initialBytes
   */
  public String requestBlockLocation(long userId, long blockId, long initialBytes)
      throws OutOfSpaceException, FileAlreadyExistException, org.apache.thrift.TException {
    return null;
  }

  /**
   * Used to request space for some block file. return true if the worker successfully allocates
   * space for the block on block’s location, false if there is no enough space, if there is no
   * information of the block on worker, FileDoesNotExistException will be thrown.
   *
   * @param userId
   * @param blockId
   * @param requestBytes
   */
  public boolean requestSpace(long userId, long blockId, long requestBytes)
      throws FileDoesNotExistException, org.apache.thrift.TException {
    return false;
  }

  /**
   * Used to unlock a block after the block is accessed, if the block is to be removed, delete the
   * block file. return true if successfully unlock the block, return false if the block is not
   * found or failed to delete the block.
   *
   * @param blockId
   * @param userId
   */
  public boolean unlockBlock(long blockId, long userId) throws org.apache.thrift.TException {
    return false;
  }

  /**
   * Local user send heartbeat to local worker to keep its temporary folder.
   *
   * @param userId
   */
  public void userHeartbeat(long userId) throws org.apache.thrift.TException {

  }
}
