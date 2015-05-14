package tachyon.worker.block.allocator;

import tachyon.worker.block.meta.BlockMeta;

/**
 * Naive allocation strategy
 */
public class NaiveAllocator implements Allocator {
  @Override
  public BlockMeta allocateBlock(long userId, long blockId, long blockSize, int tierHint) {
    return null;
  }
}
