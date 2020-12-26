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

import com.cloud.dc.ControlAppStoreVO;
import com.cloud.user.Account;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiErrorCode;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.AppStoreResponse;
import org.apache.cloudstack.api.response.PodResponse;
import org.apache.cloudstack.control.ApiControlAppStoreService;
import org.apache.log4j.Logger;

import javax.inject.Inject;

@APICommand(name = "updateAppStore",
        description = "应用商店：更新应用",
        responseObject = PodResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false)
public class UpdateAppStoreCmd extends BaseCmd {
    public static final Logger s_logger = Logger.getLogger(UpdateAppStoreCmd.class.getName());

    private static final String s_name = "updateAppStoreResponse";

    @Inject
    ApiControlAppStoreService _apiControlAppStoreService;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ControlConstants.AppStore.ID, type = CommandType.UUID,
            entityType = AppStoreResponse.class, required = true, description = "应用商店应用ID")
    private Long id;

    @Parameter(name = ControlConstants.AppStore.NAME, type = CommandType.STRING, required = true, description = "应用名称")
    private String name;

    @Parameter(name = ControlConstants.AppStore.DESCRIPTION, type = CommandType.STRING,  description = "应用商店应用描述")
    private String description;

    @Parameter(name = ControlConstants.AppStore.ICON, type = CommandType.STRING, required = true, description = "应用商店应用图标")
    private String icon;

    @Parameter(name = ControlConstants.AppStore.RUN_SCRIPT, type = CommandType.STRING,  description = "应用商店应用默认执行脚本")
    private String runScript;

    @Parameter(name = ControlConstants.AppStore.STATE, type = CommandType.INTEGER, description = "应用商店应用状态")
    private int state;

    @Parameter(name = ControlConstants.AppStore.REMARK, type = CommandType.STRING, description = "应用商店应用备注")
    private String remark;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
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
        ControlAppStoreVO result = _apiControlAppStoreService.editAppStore(
                getId(), getName(), getDescription(), getIcon(), getRunScript(), getState(), getRemark());
        if (result != null) {
            AppStoreResponse response = new AppStoreResponse();
            response.setId(result.getId());
            response.setName(result.getName());
            response.setUuid(result.getUuid());
            response.setDescription(result.getDescription());
            response.setIcon(result.getIcon());
            response.setRunScript(result.getRunScript());
            response.setInstanceCount(result.getInstanceCount());
            response.setRemoved(result.getRemoved());
            response.setOwner(result.getOwner());
            response.setLastUpdated(result.getLastUpdated());
            response.setState(result.getState());
            response.setRemark(result.getRemark());

            //设置返回对象的Key
            response.setObjectName("appStore");
            //设置返回Response对象的Key
            response.setResponseName(getCommandName());

            this.setResponseObject(response);
        } else {
            throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "应用商店：更新应用失败");
        }
    }
}
