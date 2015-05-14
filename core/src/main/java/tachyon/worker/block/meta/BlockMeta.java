package tachyon.worker.block.meta;

import tachyon.util.CommonUtils;

import java.util.UUID;

/**
 * Data structure for holding the metadata of a block.
 */
public class BlockMeta {
  private final long mBlockId;
  private final long mBlockSize;
  private final String mPath;
  private final String mTmpPath;

  public BlockMeta(long blockId, long blockSize, String localPath) {
    mBlockId = blockId;
    mBlockSize = blockSize;
    mPath = CommonUtils.concatPath(localPath, blockId);
    mTmpPath = CommonUtils.concatPath(localPath, UUID.randomUUID());
  }

  public long getBlockId() {
    return mBlockId;
  }

  public long getBlockSize() {
    return mBlockSize;
  }

  public String getPath() {
    return mPath;
  }

  public String getTmpPath() {
    return mTmpPath;
  }
}
