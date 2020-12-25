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
import com.cloud.dc.dao.ControlAppStoreDao;
import com.cloud.utils.Pair;
import com.cloud.utils.db.DB;
import com.cloud.utils.db.Filter;
import com.cloud.utils.db.SearchBuilder;
import com.cloud.utils.db.SearchCriteria;
import org.apache.cloudstack.api.command.user.CreateAppStoreCmd;
import org.apache.cloudstack.api.command.user.ListAppStoreCmd;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ApiControlAppStoreServiceImpl implements ApiControlAppStoreService {
    private static final Logger s_logger = Logger.getLogger(ApiControlAppStoreServiceImpl.class);

    @Inject
    private ControlAppStoreDao _controlAppStoreDao;

    protected ApiControlAppStoreServiceImpl() {
        super();
    }

    @Override
    public Pair<List<? extends ControlAppStoreVO>, Integer> listAppStores(ListAppStoreCmd cmd) {
        final Long id = cmd.getId();
        final String name = cmd.getName();
        final Long uuid = cmd.getUuid();
        final String description = cmd.getDescription();
        final String state = cmd.getState();

        final Filter searchFilter = new Filter(ControlAppStoreVO.class, "id", true, cmd.getStartIndex(), cmd.getPageSizeVal());
        final SearchBuilder<ControlAppStoreVO> sb = _controlAppStoreDao.createSearchBuilder();
        sb.and("id", sb.entity().getId(), SearchCriteria.Op.EQ);
        sb.and("name", sb.entity().getName(), SearchCriteria.Op.LIKE);
        sb.and("uuid", sb.entity().getUuid(), SearchCriteria.Op.EQ);
        sb.and("description", sb.entity().getDescription(), SearchCriteria.Op.LIKE);
        sb.and("state", sb.entity().getState(), SearchCriteria.Op.EQ);

        final SearchCriteria<ControlAppStoreVO> sc = sb.create();

        if (id != null) {
            sc.setParameters("id", id);
        }

        if (name != null) {
            sc.setParameters("name", "%" + name + "%");
        }

        if (uuid != null) {
            sc.setParameters("uuid", uuid);
        }

        if (description != null) {
            sc.setParameters("description", description);
        }

        if (state != null) {
            sc.setParameters("state", state);
        }

        final Pair<List<ControlAppStoreVO>, Integer> result = _controlAppStoreDao.searchAndCount(sc, searchFilter);
        return new Pair<List<? extends ControlAppStoreVO>, Integer>(result.first(), result.second());
    }

    @Override
    @DB
    public ControlAppStoreVO createAppStore(String name, String description, String icon, String runScript, int state, String remark) {
        ControlAppStoreVO controlAppStoreVO = new ControlAppStoreVO();
        controlAppStoreVO.setName(name);
        controlAppStoreVO.setDescription(description);
        controlAppStoreVO.setIcon(icon);
        controlAppStoreVO.setRunScript(runScript);
        controlAppStoreVO.setState(state);
        controlAppStoreVO.setRemark(remark);

        controlAppStoreVO.setUuid(UUID.randomUUID().toString().replaceAll("-",""));

        _controlAppStoreDao.persist(controlAppStoreVO);

        return controlAppStoreVO;
    }

    @Override
    public List<Class<?>> getCommands() {
        final List<Class<?>> cmdList = new ArrayList<Class<?>>();
        cmdList.add(ListAppStoreCmd.class);
        cmdList.add(CreateAppStoreCmd.class);
        return cmdList;
    }

}
