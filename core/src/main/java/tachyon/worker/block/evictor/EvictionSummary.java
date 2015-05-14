package tachyon.worker.block.evictor;

import tachyon.Pair;

import java.util.List;

/**
 * This class provides information about the blocks that need to be moved when evicting.
 */
public class EvictionSummary {
  private final List<Pair<Long, Long>> mToTransfer;
  private final List<Long> mToEvict;

  public EvictionSummary(List<Pair<Long, Long>> toTransfer, List<Long> toEvict) {
    mToTransfer = toTransfer;
    mToEvict = toEvict;
  }
}
