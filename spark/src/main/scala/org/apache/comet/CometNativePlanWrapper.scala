/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.comet

/**
 * A wrapper class for Comet native plan. It is used to release the plan faithfully without
 * double-free.
 */
class CometNativePlanWrapper(
    val plan: Long,
    val native: Native,
    taskAttemptId: Long,
    poolAddress: Long) {
  private var isClosed = false

  def close(): Unit = {
    if (!isClosed) {
      // scalastyle:off println
      println(s"[TASK $taskAttemptId] Release native plan $plan, poolAddress: $poolAddress")
      native.releasePlan(plan)
      isClosed = true
    }
  }
}
