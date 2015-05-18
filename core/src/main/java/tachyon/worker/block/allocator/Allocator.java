package tachyon.worker.block.allocator;

import tachyon.worker.block.meta.BlockMeta;

/**
 * Interface for the allocation policy of Tachyon managed data.
 */
public interface Allocator {
  BlockMeta allocateBlock(int userId, long blockId, long blockSize, int tierHint);
}
