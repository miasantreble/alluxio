package tachyon.worker.block;

import tachyon.worker.block.allocator.Allocator;
import tachyon.worker.block.allocator.NaiveAllocator;
import tachyon.worker.block.meta.BlockMeta;

/**
 * Central management for block level operations.
 */
public class BlockWorker {
  private final Allocator mAllocator;

  public BlockWorker() {
    mAllocator = new NaiveAllocator();
  }

  public String createBlock(long userId, long blockId, long blockSize, int tierHint) {
    BlockMeta meta = mAllocator.allocateBlock(userId, blockId, blockSize, tierHint);
    if (meta == null) {
      Evictor.freeSpace(blockSize, tierHint);
      meta = Allocator.allocate(userId, blockId, blockSize, tierHint);
    }
    return meta.getTmpPath();
  }

}
