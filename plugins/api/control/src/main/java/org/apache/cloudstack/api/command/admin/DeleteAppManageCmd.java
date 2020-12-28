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

import com.cloud.user.Account;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiErrorCode;
import org.apache.cloudstack.api.BaseCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.AppManageResponse;
import org.apache.cloudstack.api.response.SuccessResponse;
import org.apache.cloudstack.control.ApiControlAppManageService;
import org.apache.log4j.Logger;

import javax.inject.Inject;

@APICommand(name = "deleteAppManage",
        description = "应用管理：删除应用",
        since = "4.15.0",
        responseObject = AppManageResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false)
public class DeleteAppManageCmd extends BaseCmd {
    public static final Logger s_logger = Logger.getLogger(DeleteAppManageCmd.class.getName());

    private static final String s_name = "deleteAppManageResponse";

    @Inject
    ApiControlAppManageService _apiControlAppManageService;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ControlConstants.AppManage.ID, type = CommandType.LONG,
            entityType = AppManageResponse.class, required = true, description = "应用管理应用ID")
    private Long id;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public Long getId() {
        return id;
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
        boolean result = _apiControlAppManageService.deleteAppManage(this);
        if (result) {
            SuccessResponse response = new SuccessResponse(getCommandName());
            this.setResponseObject(response);
        } else {
            throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "应用管理：删除应用失败");
        }
    }
}
