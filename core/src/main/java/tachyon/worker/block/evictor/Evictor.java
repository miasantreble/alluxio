package tachyon.worker.block.evictor;

/**
 * Interface for the eviction policy in Tachyon
 */
public interface Evictor {
  EvictionSummary freeSpace(long bytes, int tierHint);
}
