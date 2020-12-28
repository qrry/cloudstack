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

import com.cloud.dc.ControlAppManageVO;
import com.cloud.dc.dao.ControlAppManageDao;
import com.cloud.exception.InvalidParameterValueException;
import com.cloud.utils.Pair;
import com.cloud.utils.db.DB;
import com.cloud.utils.db.Filter;
import com.cloud.utils.db.SearchBuilder;
import com.cloud.utils.db.SearchCriteria;
import com.cloud.utils.exception.CloudRuntimeException;
import org.apache.cloudstack.api.command.admin.CreateAppManageCmd;
import org.apache.cloudstack.api.command.admin.DeleteAppManageCmd;
import org.apache.cloudstack.api.command.admin.InstallAppManageCmd;
import org.apache.cloudstack.api.command.admin.UninstallAppManageCmd;
import org.apache.cloudstack.api.command.admin.UpdateAppManageCmd;
import org.apache.cloudstack.api.command.user.ListAppManageCmd;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class ApiControlAppManageServiceImpl implements ApiControlAppManageService {
    private static final Logger s_logger = Logger.getLogger(ApiControlAppManageServiceImpl.class);

    @Inject
    private ControlAppManageDao _controlAppManageDao;

    protected ApiControlAppManageServiceImpl() {
        super();
    }

    @Override
    public Pair<List<? extends ControlAppManageVO>, Integer> listAppManages(ListAppManageCmd cmd) {
        final Long id = cmd.getId();
        final Long appStoreId = cmd.getAppStoreId();
        final Long uuid = cmd.getUuid();
        final String description = cmd.getDescription();
        final Long instanceId = cmd.getInstanceId();
        final String state = cmd.getState();

        final Filter searchFilter = new Filter(ControlAppManageVO.class, "id", true, cmd.getStartIndex(), cmd.getPageSizeVal());
        final SearchBuilder<ControlAppManageVO> sb = _controlAppManageDao.createSearchBuilder();
        sb.and("id", sb.entity().getId(), SearchCriteria.Op.EQ);
        sb.and("appStoreId", sb.entity().getAppStoreId(), SearchCriteria.Op.EQ);
        sb.and("uuid", sb.entity().getUuid(), SearchCriteria.Op.EQ);
        sb.and("description", sb.entity().getDescription(), SearchCriteria.Op.LIKE);
        sb.and("instanceId", sb.entity().getInstanceId(), SearchCriteria.Op.EQ);
        sb.and("state", sb.entity().getState(), SearchCriteria.Op.EQ);

        final SearchCriteria<ControlAppManageVO> sc = sb.create();

        if (id != null) {
            sc.setParameters("id", id);
        }

        if (appStoreId != null) {
            sc.setParameters("appStoreId", appStoreId);
        }

        if (uuid != null) {
            sc.setParameters("uuid", uuid);
        }

        if (description != null) {
            sc.setParameters("description", description);
        }

        if (instanceId != null) {
            sc.setParameters("instanceId", instanceId);
        }

        if (state != null) {
            sc.setParameters("state", state);
        }

        final Pair<List<ControlAppManageVO>, Integer> result = _controlAppManageDao.searchAndCount(sc, searchFilter);
        return new Pair<List<? extends ControlAppManageVO>, Integer>(result.first(), result.second());
    }

    @Override
    @DB
    public ControlAppManageVO createAppManage(long appStoreId, String description, long instanceId, String runScript, int state, String remark) {
        ControlAppManageVO controlAppManageVO = new ControlAppManageVO();
        controlAppManageVO.setAppStoreId(appStoreId);
        controlAppManageVO.setDescription(description);
        controlAppManageVO.setInstanceId(instanceId);
        controlAppManageVO.setRunScript(runScript);
        controlAppManageVO.setState(state);
        controlAppManageVO.setRemark(remark);

        controlAppManageVO.setUuid(UUID.randomUUID().toString().replaceAll("-",""));

        _controlAppManageDao.persist(controlAppManageVO);

        return controlAppManageVO;
    }

    @Override
    public boolean deleteAppManage(DeleteAppManageCmd cmd) {
        final Long id = cmd.getId();
        if (!_controlAppManageDao.remove(id)) {
            throw new CloudRuntimeException("应用管理：删除应用[" + id + "]失败");
        }
        return true;
    }

    @Override
    public ControlAppManageVO editAppManage(Long id, long appStoreId, String description, long instanceId, String runScript, int state, String remark) {

        //判断输入的id是否存在？
        final ControlAppManageVO controlAppManageVO = _controlAppManageDao.findById(id);

        if (controlAppManageVO == null) {
            throw new InvalidParameterValueException("不能找到ID=[" + id + "]");
        }

        controlAppManageVO.setAppStoreId(appStoreId);
        controlAppManageVO.setDescription(description);
        controlAppManageVO.setInstanceId(instanceId);
        controlAppManageVO.setRunScript(runScript);
        controlAppManageVO.setState(state);
        controlAppManageVO.setRemark(remark);

        controlAppManageVO.setLastUpdated(new Date());

        _controlAppManageDao.update(id, controlAppManageVO);

        return controlAppManageVO;
    }

    @Override
    public boolean installAppManage(InstallAppManageCmd cmd) {
        final Long id = cmd.getId();
        throw new CloudRuntimeException("应用管理：安装应用[" + id + "]失败");
    }

    @Override
    public boolean uninstallAppManage(UninstallAppManageCmd cmd) {
        final Long id = cmd.getId();
        throw new CloudRuntimeException("应用管理：卸载应用[" + id + "]失败");
    }

    @Override
    public List<Class<?>> getCommands() {
        final List<Class<?>> cmdList = new ArrayList<Class<?>>();
        cmdList.add(ListAppManageCmd.class);
        cmdList.add(CreateAppManageCmd.class);
        cmdList.add(DeleteAppManageCmd.class);
        cmdList.add(UpdateAppManageCmd.class);
        cmdList.add(InstallAppManageCmd.class);
        cmdList.add(UninstallAppManageCmd.class);
        return cmdList;
    }

}
