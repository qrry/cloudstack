// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.apache.cloudstack.control;

import com.cloud.dc.ControlAppStoreVO;
import com.cloud.utils.Pair;
import com.cloud.utils.component.PluggableService;
import org.apache.cloudstack.api.command.user.ListAppStoreCmd;

import java.util.List;

public interface ApiControlAppStoreService extends PluggableService {
    Pair<List<? extends ControlAppStoreVO>, Integer> listAppStores(ListAppStoreCmd cmd);

    ControlAppStoreVO createAppStore(String name, String description, String icon, String runScript, int state, String remark);
}
