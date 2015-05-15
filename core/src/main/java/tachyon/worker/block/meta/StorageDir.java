package tachyon.worker.block.meta;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents one isolated storage unit.
 */
public class StorageDir {
  private Map<Long, BlockMeta> mBlocks;
  private Map<Long, Set<BlockMeta>> mUsers2Blocks;
  private long mCapacityBytes;
  private long mAvailableBytes;
  private String mDirPath;

  public StorageDir(long capacityBytes, String dirPath) {
    mCapacityBytes = capacityBytes;
    mAvailableBytes = capacityBytes;
    mDirPath = dirPath;
    mBlocks = new HashMap<Long, BlockMeta>(200);
  }

  public synchronized BlockMeta createBlock(long userId, long blockId, long blockSize) {
    Preconditions.checkArgument(!mBlocks.containsKey(blockId), "Block already exists.");
    if (mAvailableBytes < blockSize) {
      return null;
    }
    BlockMeta toAdd = new BlockMeta(blockId, blockSize, mDirPath);
    Set<BlockMeta> userBlocks = mUsers2Blocks.get(userId);
    if (null == userBlocks) {
      mUsers2Blocks.put(userId, Sets.newHashSet(toAdd));
    } else {
      userBlocks.add(toAdd);
    }

  }
}
