package tachyon.worker.block.allocator;

import tachyon.worker.block.meta.BlockMeta;
import tachyon.worker.block.meta.BlockWorkerMetadata;

/**
 * Naive allocation strategy
 */
public class NaiveAllocator implements Allocator {
  private final BlockWorkerMetadata mMetadata;

  public NaiveAllocator(BlockWorkerMetadata metadata) {
    mMetadata = metadata;
  }

  @Override
  public BlockMeta allocateBlock(long userId, long blockId, long blockSize, int tierHint) {
    return null;
  }
}
