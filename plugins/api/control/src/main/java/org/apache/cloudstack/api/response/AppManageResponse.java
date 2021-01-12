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
package org.apache.cloudstack.api.response;

import com.cloud.dc.ControlAppManageVO;
import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.api.BaseResponse;
import org.apache.cloudstack.api.EntityReference;

import java.util.Date;

@EntityReference(value = ControlAppManageVO.class)
public class AppManageResponse extends BaseResponse{

    @SerializedName(ControlConstants.AppManage.ID)
    @Param(description = "应用管理ID")
    private long id;

    @SerializedName(ControlConstants.AppManage.APP_STORE_ID)
    @Param(description = "应用商店ID")
    private long appStoreId;

    @SerializedName(ControlConstants.AppManage.APP_STORE_NAME)
    @Param(description = "应用商店名称")
    private String appStoreName;

    @SerializedName(ControlConstants.AppManage.UUID)
    @Param(description = "应用管理UUID")
    private String uuid;

    @SerializedName(ControlConstants.AppManage.DESCRIPTION)
    @Param(description = "应用管理应用描述")
    private String description;

    @SerializedName(ControlConstants.AppManage.INSTANCE_ID)
    @Param(description = "应用管理实例ID")
    private long instanceId;

    @SerializedName(ControlConstants.AppManage.RUN_SCRIPT)
    @Param(description = "应用管理应用运行脚本")
    private String runScript;

    @SerializedName(ControlConstants.AppManage.REMOVED)
    @Param(description = "应用管理应用删除时间")
    private Date removed;

    @SerializedName(ControlConstants.AppManage.OWNER)
    @Param(description = "应用管理应用创建人")
    private String owner;

    @SerializedName(ControlConstants.AppManage.CREATED)
    @Param(description = "应用管理应用创建时间")
    private Date created;

    @SerializedName(ControlConstants.AppManage.LAST_UPDATED)
    @Param(description = "应用管理应用更新时间")
    private Date lastUpdated;

    @SerializedName(ControlConstants.AppManage.STATE)
    @Param(description = "应用管理应用状态")
    private int state;

    @SerializedName(ControlConstants.AppStore.REMARK)
    @Param(description = "应用管理应用备注")
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAppStoreId() {
        return appStoreId;
    }

    public void setAppStoreId(long appStoreId) {
        this.appStoreId = appStoreId;
    }

    public String getAppStoreName() {
        return appStoreName;
    }

    public void setAppStoreName(String appStoreName) {
        this.appStoreName = appStoreName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(long instanceId) {
        this.instanceId = instanceId;
    }

    public String getRunScript() {
        return runScript;
    }

    public void setRunScript(String runScript) {
        this.runScript = runScript;
    }

    public Date getRemoved() {
        return removed;
    }

    public void setRemoved(Date removed) {
        this.removed = removed;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
