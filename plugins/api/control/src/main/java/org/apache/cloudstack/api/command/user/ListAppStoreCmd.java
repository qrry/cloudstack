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

import com.cloud.dc.ControlAppStoreVO;
import com.cloud.utils.Pair;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.acl.RoleType;
import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.BaseListCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.response.AppStoreResponse;
import org.apache.cloudstack.api.response.ListResponse;
import org.apache.cloudstack.control.ApiControlAppStoreService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@APICommand(name = "listAppStoreApis",
        description = "应用商店：获取应用列表",
        since = "4.15.0",
        responseObject = AppStoreResponse.class,
        requestHasSensitiveInfo = false,
        responseHasSensitiveInfo = false,
        authorized = {RoleType.Admin, RoleType.ResourceAdmin, RoleType.DomainAdmin, RoleType.User})
public class ListAppStoreCmd extends BaseListCmd {

    public static final Logger s_logger = Logger.getLogger(ListAppStoreCmd.class.getName());
    private static final String s_name = "listAppStoreResponse";

    @Inject
    ApiControlAppStoreService _apiControlAppStoreService;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name = ControlConstants.AppStore.ID, type = CommandType.UUID, entityType = AppStoreResponse.class, description = "应用商店ID")
    private Long id;

    @Parameter(name = ControlConstants.AppStore.NAME, type = CommandType.STRING, description = "应用商店应用名称")
    private String name;

    @Parameter(name = ControlConstants.AppStore.UUID, type = CommandType.UUID, entityType = AppStoreResponse.class, description = "应用商店应用UUID")
    private Long uuid;

    @Parameter(name = ControlConstants.AppStore.DESCRIPTION, type = CommandType.STRING,  description = "应用商店应用描述")
    private String description;

    @Parameter(name = ControlConstants.AppStore.STATE, type = CommandType.STRING, description = "应用商店应用状态")
    private String state;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getUuid() {
        return uuid;
    }

    public String getDescription() { return description; }

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
        Pair<List<? extends ControlAppStoreVO>, Integer> result = _apiControlAppStoreService.listAppStores(this);
        ListResponse<AppStoreResponse> response = new ListResponse<AppStoreResponse>();
        List<AppStoreResponse> appStoreResponses = new ArrayList<AppStoreResponse>();
        for (ControlAppStoreVO controlAppStoreVO : result.first()) {

            AppStoreResponse appStoreResponse = new AppStoreResponse();
            appStoreResponse.setId(controlAppStoreVO.getId());
            appStoreResponse.setName(controlAppStoreVO.getName());
            appStoreResponse.setUuid(controlAppStoreVO.getUuid());
            appStoreResponse.setDescription(controlAppStoreVO.getDescription());
            appStoreResponse.setIcon(controlAppStoreVO.getIcon());
            appStoreResponse.setRunScript(controlAppStoreVO.getRunScript());
            appStoreResponse.setInstanceCount(controlAppStoreVO.getInstanceCount());
            appStoreResponse.setRemoved(controlAppStoreVO.getRemoved());
            appStoreResponse.setOwner(controlAppStoreVO.getOwner());
            appStoreResponse.setCreated(controlAppStoreVO.getCreated());
            appStoreResponse.setLastUpdated(controlAppStoreVO.getLastUpdated());
            appStoreResponse.setState(controlAppStoreVO.getState());
            appStoreResponse.setRemark(controlAppStoreVO.getRemark());

            //设置返回对象列表的Key
            appStoreResponse.setObjectName("appStore");

            appStoreResponses.add(appStoreResponse);
        }

        response.setResponses(appStoreResponses, result.second());

        //设置返回Response对象的Key
        response.setResponseName(getCommandName());
        this.setResponseObject(response);
    }

}
