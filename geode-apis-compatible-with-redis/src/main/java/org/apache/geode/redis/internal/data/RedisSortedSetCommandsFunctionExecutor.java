/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
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
 *
 */

package org.apache.geode.redis.internal.data;


import java.util.List;

import org.apache.geode.redis.internal.RegionProvider;
import org.apache.geode.redis.internal.executor.sortedset.RedisSortedSetCommands;
import org.apache.geode.redis.internal.executor.sortedset.SortedSetLexRangeOptions;
import org.apache.geode.redis.internal.executor.sortedset.SortedSetRankRangeOptions;
import org.apache.geode.redis.internal.executor.sortedset.SortedSetScoreRangeOptions;
import org.apache.geode.redis.internal.executor.sortedset.ZAddOptions;

public class RedisSortedSetCommandsFunctionExecutor extends RedisDataCommandsFunctionExecutor
    implements RedisSortedSetCommands {

  public RedisSortedSetCommandsFunctionExecutor(RegionProvider regionProvider) {
    super(regionProvider);
  }

  private RedisSortedSet getRedisSortedSet(RedisKey key, boolean updateStats) {
    return getRegionProvider().getTypedRedisData(RedisDataType.REDIS_SORTED_SET, key, updateStats);
  }

  @Override
  public Object zadd(RedisKey key, List<byte[]> scoresAndMembersToAdd, ZAddOptions options) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, false).zadd(getRegion(), key, scoresAndMembersToAdd, options));
  }

  public long zcard(RedisKey key) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zcard());
  }

  @Override
  public long zcount(RedisKey key, SortedSetScoreRangeOptions rangeOptions) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zcount(rangeOptions));
  }

  @Override
  public byte[] zincrby(RedisKey key, byte[] increment, byte[] member) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, false).zincrby(getRegion(), key, increment, member));
  }

  @Override
  public long zlexcount(RedisKey key, SortedSetLexRangeOptions lexOptions) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zlexcount(lexOptions));
  }

  @Override
  public List<byte[]> zpopmax(RedisKey key, int count) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, false).zpopmax(getRegion(), key, count));
  }

  @Override
  public List<byte[]> zpopmin(RedisKey key, int count) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, false).zpopmin(getRegion(), key, count));
  }

  public List<byte[]> zrange(RedisKey key, SortedSetRankRangeOptions rangeOptions) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zrange(rangeOptions));
  }

  @Override
  public List<byte[]> zrangebylex(RedisKey key, SortedSetLexRangeOptions rangeOptions) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zrangebylex(rangeOptions));
  }

  @Override
  public List<byte[]> zrangebyscore(RedisKey key, SortedSetScoreRangeOptions rangeOptions) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zrangebyscore(rangeOptions));
  }

  @Override
  public long zrank(RedisKey key, byte[] member) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zrank(member));
  }

  @Override
  public long zrem(RedisKey key, List<byte[]> membersToRemove) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, false).zrem(getRegion(), key, membersToRemove));
  }

  @Override
  public long zremrangebyscore(RedisKey key, SortedSetScoreRangeOptions rangeOptions) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, false).zremrangebyscore(getRegion(), key, rangeOptions));
  }

  @Override
  public List<byte[]> zrevrange(RedisKey key, SortedSetRankRangeOptions rangeOptions) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zrevrange(rangeOptions));
  }

  @Override
  public List<byte[]> zrevrangebylex(RedisKey key, SortedSetLexRangeOptions rangeOptions) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, true).zrevrangebylex(rangeOptions));
  }

  @Override
  public List<byte[]> zrevrangebyscore(RedisKey key, SortedSetScoreRangeOptions rangeOptions) {
    return stripedExecute(key,
        () -> getRedisSortedSet(key, true).zrevrangebyscore(rangeOptions));
  }

  @Override
  public long zrevrank(RedisKey key, byte[] member) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zrevrank(member));
  }

  @Override
  public byte[] zscore(RedisKey key, byte[] member) {
    return stripedExecute(key, () -> getRedisSortedSet(key, true).zscore(member));
  }
}