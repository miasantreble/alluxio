package tachyon.worker.block.meta;

import tachyon.Constants;
import tachyon.conf.TachyonConf;
import tachyon.util.CommonUtils;

import java.util.Set;

/**
 * Represents a tier of storage, for example memory or SSD.
 */
public class StorageTier {
  private Set<StorageDir> mStorageDirs;

  public StorageTier(TachyonConf tachyonConf, int tier) {

    String tierDirPathConf =
        String.format(Constants.WORKER_TIERED_STORAGE_LEVEL_DIRS_PATH_FORMAT, tier);
    String[] dirPaths = tachyonConf.get(tierDirPathConf, "/mnt/ramdisk").split(",");

    String tierDirCapacityConf =
        String.format(Constants.WORKER_TIERED_STORAGE_LEVEL_DIRS_QUOTA_FORMAT, tier);
    String[] dirQuotas = tachyonConf.get(tierDirCapacityConf, "0").split(",");

    for (int i = 0; i < dirPaths.length; i ++) {
      int index = i >= dirQuotas.length ? dirQuotas.length - 1 : i;
      long capacity = CommonUtils.parseSpaceSize(dirQuotas[index]);
      mStorageDirs.add(new StorageDir(capacity, dirPaths[i]));
    }
  }

  public BlockMeta createBlock(int userId, long blockId, long blockSize) {
    for (StorageDir dir : mStorageDirs) {
      BlockMeta newBlock = dir.createBlock(userId, blockId, blockSize);
      if (newBlock != null) {
        return newBlock;
      }
    }
    return null;
  }
}
