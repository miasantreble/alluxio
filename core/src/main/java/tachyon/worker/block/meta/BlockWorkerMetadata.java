package tachyon.worker.block.meta;

import tachyon.Constants;
import tachyon.StorageLevelAlias;
import tachyon.conf.TachyonConf;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that provides metadata operations for the worker.
 */
public class BlockWorkerMetadata {

  private long mAvailableSpace;
  private Map<Integer, StorageTier> mTiers;

  public BlockWorkerMetadata(TachyonConf tachyonConf) {
    // Initialize storage tiers
    int totalTiers = tachyonConf.getInt(Constants.WORKER_MAX_TIERED_STORAGE_LEVEL, 1);
    mTiers = new HashMap<Integer, StorageTier>(totalTiers);
    for (int i = 0; i < totalTiers; i ++) {
      mTiers.put(i, new StorageTier(tachyonConf, i));
    }
  }

  public BlockMeta createBlockInTier(int userId, long blockId, long blockSize, int tierAlias) {
    StorageTier tier = mTiers.get(tierAlias);
    if (tier == null) {
      return null;
    }
    return tier.createBlock(userId, blockId, blockSize);
  }

}
