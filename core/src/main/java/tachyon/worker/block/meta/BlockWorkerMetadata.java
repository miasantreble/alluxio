package tachyon.worker.block.meta;

import tachyon.StorageLevelAlias;

import java.util.Map;

/**
 * Class that provides metadata operations for the worker.
 */
public class BlockWorkerMetadata {

  private Map<StorageLevelAlias, StorageTier> mTiers;

  public BlockWorkerMetadata() {

  }

}
