package tachyon.worker.block.evictor;

import tachyon.worker.block.meta.BlockWorkerMetadata;

public class NaiveEvictor implements Evictor {
  private final BlockWorkerMetadata mMetadata;

  public NaiveEvictor(BlockWorkerMetadata metadata) {
    mMetadata = metadata;
  }

  public EvictionSummary freeSpace(long bytes, int tierHint) {
    return new EvictionSummary(null, null);
  }
}
