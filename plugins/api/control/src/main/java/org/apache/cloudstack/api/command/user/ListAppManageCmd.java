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
package org.apache.cloudstack.api.command.user;

import com.cloud.dc.ControlAppManageVO;
import com.cloud.dc.ControlAppStoreVO;
import com.cloud.utils.Pair;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.acl.RoleType;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.BaseListCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.response.AppManageResponse;
import org.apache.cloudstack.api.response.ListResponse;
import org.apache.cloudstack.control.ApiControlAppManageService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@APICommand(name = "listAppManage",
        description = "应用管理：获取应用列表",
        since = "4.15.0",
        responseObject = AppManageResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false,
        authorized = {RoleType.Admin, RoleType.ResourceAdmin, RoleType.DomainAdmin, RoleType.User})
public class ListAppManageCmd extends BaseListCmd {

    public static final Logger s_logger = Logger.getLogger(ListAppManageCmd.class.getName());
    private static final String s_name = "listAppManageResponse";

    @Inject
    ApiControlAppManageService _apiControlAppManageService;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ControlConstants.AppManage.ID, type = CommandType.LONG, entityType = AppManageResponse.class, description = "应用管理ID")
    private Long id;

    @Parameter(name = ControlConstants.AppManage.APP_STORE_ID, type = CommandType.LONG, entityType = AppManageResponse.class, description = "应用商店ID")
    private Long appStoreId;

    @Parameter(name = ControlConstants.AppManage.UUID, type = CommandType.UUID, entityType = AppManageResponse.class, description = "应用管理应用UUID")
    private Long uuid;

    @Parameter(name = ControlConstants.AppManage.DESCRIPTION, type = CommandType.STRING, description = "应用管理应用描述")
    private String description;

    @Parameter(name = ControlConstants.AppManage.INSTANCE_ID, type = CommandType.STRING,  description = "应用管理实例ID")
    private String instanceId;

    @Parameter(name = ControlConstants.AppManage.INSTANCE_NAME, type = CommandType.STRING,  description = "应用管理实例名称")
    private String instanceName;

    @Parameter(name = ControlConstants.AppManage.RUN_SCRIPT, type = CommandType.STRING,  description = "应用管理应用执行脚本")
    private String runScript;

    @Parameter(name = ControlConstants.AppManage.IP, type = CommandType.STRING,  description = "应用管理实例管理IP")
    private String ip;

    @Parameter(name = ControlConstants.AppManage.PORT, type = CommandType.STRING,  description = "应用管理实例管理端口")
    private String port;

    @Parameter(name = ControlConstants.AppManage.LOGIN_USER, type = CommandType.STRING,  description = "应用管理实例登录用户名")
    private String loginUser;

    @Parameter(name = ControlConstants.AppManage.LOGIN_PASSWORD, type = CommandType.STRING,  description = "应用管理实例登录密码")
    private String loginPassword;

    @Parameter(name = ControlConstants.AppManage.STATE, type = CommandType.STRING, description = "应用管理应用状态")
    private String state;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////
    public Long getId() {
        return id;
    }

    public Long getAppStoreId() {
        return appStoreId;
    }

    public Long getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public String getRunScript() {
        return runScript;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getState() {
        return state;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////
    @Override
    public String getCommandName() {
        return s_name;
    }

    @Override
    public void execute() {
        Pair<List<? extends ControlAppManageVO>, Integer> result = _apiControlAppManageService.listAppManages(this);
        ListResponse<AppManageResponse> response = new ListResponse<AppManageResponse>();
        List<AppManageResponse> appManageResponses = new ArrayList<AppManageResponse>();
        for (ControlAppManageVO controlAppManageVO : result.first()) {

            //获取应用商店数据
            long appStoreId = controlAppManageVO.getAppStoreId();
            String appStoreName = "";
            ControlAppStoreVO controlAppStore = _apiControlAppManageService.findByAppStoreId(appStoreId);
            if (controlAppStore != null) {
                appStoreName = controlAppStore.getName();
            }

            AppManageResponse appManageResponse = new AppManageResponse();
            appManageResponse.setId(controlAppManageVO.getId());
            appManageResponse.setAppStoreId(appStoreId);
            appManageResponse.setAppStoreName(appStoreName);
            appManageResponse.setUuid(controlAppManageVO.getUuid());
            appManageResponse.setDescription(controlAppManageVO.getDescription());
            appManageResponse.setInstanceId(controlAppManageVO.getInstanceId());
            appManageResponse.setInstanceName(controlAppManageVO.getInstanceName());
            appManageResponse.setRunScript(controlAppManageVO.getRunScript());
            appManageResponse.setIp(controlAppManageVO.getIp());
            appManageResponse.setPort(controlAppManageVO.getPort());
            appManageResponse.setLoginUser(controlAppManageVO.getLoginUser());
            appManageResponse.setLoginPassword(controlAppManageVO.getLoginPassword());
            appManageResponse.setRemoved(controlAppManageVO.getRemoved());
            appManageResponse.setOwner(controlAppManageVO.getOwner());
            appManageResponse.setCreated(controlAppManageVO.getCreated());
            appManageResponse.setLastUpdated(controlAppManageVO.getLastUpdated());
            appManageResponse.setState(controlAppManageVO.getState());
            appManageResponse.setRemark(controlAppManageVO.getRemark());

            //设置返回对象列表的Key
            appManageResponse.setObjectName("appManage");

            appManageResponses.add(appManageResponse);
        }

        response.setResponses(appManageResponses, result.second());

        //设置返回Response对象的Key
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
    }

}
