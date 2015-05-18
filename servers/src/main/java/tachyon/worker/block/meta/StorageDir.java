/*
 * Licensed to the University of California, Berkeley under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package tachyon.worker.block.meta;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * Represents one isolated storage unit.
 */
public class StorageDir {
  private Map<Long, BlockMeta> mBlocks;
  private Map<Long, Set<BlockMeta>> mUsers2Blocks;
  private long mCapacityBytes;
  private long mAvailableBytes;
  private String mDirPath;

  public StorageDir(long capacityBytes, String dirPath) {
    mCapacityBytes = capacityBytes;
    mAvailableBytes = capacityBytes;
    mDirPath = dirPath;
    mBlocks = new HashMap<Long, BlockMeta>(200);
    mUsers2Blocks = new HashMap<Long, Set<BlockMeta>>(20);
  }

  public synchronized BlockMeta createBlock(long userId, long blockId, long blockSize) {
    Preconditions.checkArgument(!mBlocks.containsKey(blockId), "Block already exists.");
    if (mAvailableBytes < blockSize) {
      return null;
    }
    BlockMeta toAdd = new BlockMeta(blockId, blockSize, mDirPath);
    Set<BlockMeta> userBlocks = mUsers2Blocks.get(userId);
    if (null == userBlocks) {
      mUsers2Blocks.put(userId, Sets.newHashSet(toAdd));
    } else {
      userBlocks.add(toAdd);
    }
    mCapacityBytes += blockSize;
    mAvailableBytes -= blockSize;
    return toAdd;
  }
}
