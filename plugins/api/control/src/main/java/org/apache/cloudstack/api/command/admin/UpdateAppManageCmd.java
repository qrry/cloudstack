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
package org.apache.cloudstack.api.command.admin;

import com.cloud.dc.ControlAppManageVO;
import com.cloud.user.Account;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiErrorCode;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.AppManageResponse;
import org.apache.cloudstack.api.response.AppStoreResponse;
import org.apache.cloudstack.control.ApiControlAppManageService;
import org.apache.log4j.Logger;

import javax.inject.Inject;

@APICommand(name = "updateAppManage",
        description = "应用管理：更新应用",
        since = "4.15.0",
        responseObject = AppManageResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false)
public class UpdateAppManageCmd extends BaseCmd {
    public static final Logger s_logger = Logger.getLogger(UpdateAppManageCmd.class.getName());

    private static final String s_name = "updateAppManageResponse";

    @Inject
    ApiControlAppManageService _apiControlAppManageService;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ControlConstants.AppStore.ID, type = CommandType.LONG,
            entityType = AppStoreResponse.class, required = true, description = "应用商店应用ID")
    private Long id;

    @Parameter(name = ControlConstants.AppManage.APP_STORE_ID, type = CommandType.LONG, required = true, description = "应用商店ID")
    private Long appStoreId;

    @Parameter(name = ControlConstants.AppManage.DESCRIPTION, type = CommandType.STRING,  description = "应用管理应用描述")
    private String description;

    @Parameter(name = ControlConstants.AppManage.INSTANCE_ID, type = CommandType.LONG, required = true, description = "应用管理实例ID")
    private Long instanceId;

    @Parameter(name = ControlConstants.AppManage.RUN_SCRIPT, type = CommandType.STRING,  description = "应用管理应用执行脚本")
    private String runScript;

    @Parameter(name = ControlConstants.AppManage.STATE, type = CommandType.INTEGER, description = "应用管理应用状态")
    private int state;

    @Parameter(name = ControlConstants.AppManage.REMARK, type = CommandType.STRING, description = "应用管理应用备注")
    private String remark;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public Long getAppStoreId() {
        return appStoreId;
    }

    public String getDescription() {
        return description;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public String getRunScript() {
        return runScript;
    }

    public int getState() {
        return state;
    }

    public String getRemark() {
        return remark;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @Override
    public String getCommandName() {
        return s_name;
    }

    @Override
    public long getEntityOwnerId() {
        return Account.ACCOUNT_ID_SYSTEM;
    }

    @Override
    public void execute() {
        ControlAppManageVO result = _apiControlAppManageService.editAppManage(
                getId(), getAppStoreId(), getDescription(), getInstanceId(), getRunScript(), getState(), getRemark());
        if (result != null) {
            AppManageResponse response = new AppManageResponse();
            response.setId(result.getId());
            response.setAppStoreId(result.getAppStoreId());
            response.setUuid(result.getUuid());
            response.setDescription(result.getDescription());
            response.setInstanceId(result.getInstanceId());
            response.setRunScript(result.getRunScript());
            response.setRemoved(result.getRemoved());
            response.setOwner(result.getOwner());
            response.setCreated(result.getCreated());
            response.setLastUpdated(result.getLastUpdated());
            response.setState(result.getState());
            response.setRemark(result.getRemark());

            //设置返回对象的Key
            response.setObjectName("appManage");
            //设置返回Response对象的Key
            response.setResponseName(getCommandName());

            this.setResponseObject(response);
        } else {
            throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "应用管理：更新应用失败");
        }
    }
}
