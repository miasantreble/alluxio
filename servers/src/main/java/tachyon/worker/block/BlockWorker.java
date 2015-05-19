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

package tachyon.worker.block;

import com.google.common.base.Optional;

import tachyon.conf.TachyonConf;
import tachyon.worker.block.allocator.Allocator;
import tachyon.worker.block.allocator.NaiveAllocator;
import tachyon.worker.block.evictor.Evictor;
import tachyon.worker.block.evictor.NaiveEvictor;
import tachyon.worker.block.meta.BlockMeta;
import tachyon.worker.block.meta.BlockWorkerMetadata;

/**
 * Central management for block level operations.
 */
public class BlockWorker {
  private final TachyonConf mTachyonConf;
  private final BlockWorkerMetadata mMetadata;

  private final Allocator mAllocator;
  private final Evictor mEvictor;

  public BlockWorker() {
    mTachyonConf = new TachyonConf();
    mMetadata = new BlockWorkerMetadata(mTachyonConf);

    mAllocator = new NaiveAllocator(mMetadata);
    mEvictor = new NaiveEvictor(mMetadata);
  }

  public String createBlock(long userId, long blockId, long blockSize, int tierHint) {
    Optional<BlockMeta> meta = mAllocator.allocateBlock(userId, blockId, blockSize, tierHint);
    if (!meta.isPresent()) {
      mEvictor.freeSpace(blockSize, tierHint);
      meta = mAllocator.allocateBlock(userId, blockId, blockSize, tierHint);
    }
    return meta.get().getTmpPath();
  }
}
