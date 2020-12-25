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

import com.cloud.dc.ControlAppStoreVO;
import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;
import org.apache.cloudstack.ControlConstants;
import org.apache.cloudstack.api.BaseResponse;
import org.apache.cloudstack.api.EntityReference;

import java.util.Date;

@EntityReference(value = ControlAppStoreVO.class)
public class AppStoreResponse extends BaseResponse{

    @SerializedName(ControlConstants.AppStore.ID)
    @Param(description = "应用商店ID")
    long id;

    @SerializedName(ControlConstants.AppStore.NAME)
    @Param(description = "应用商店应用名称")
    private String name;

    @SerializedName(ControlConstants.AppStore.UUID)
    @Param(description = "应用商店UUID")
    private String uuid;

    @SerializedName(ControlConstants.AppStore.DESCRIPTION)
    @Param(description = "应用商店应用描述")
    private String description;

    @SerializedName(ControlConstants.AppStore.ICON)
    @Param(description = "应用商店应用图标")
    private String icon;

    @SerializedName(ControlConstants.AppStore.RUN_SCRIPT)
    @Param(description = "应用商店应用运行脚本")
    private String runScript;

    @SerializedName(ControlConstants.AppStore.INSTANCE_COUNT)
    @Param(description = "应用商店应用所属实例数")
    private int instanceCount;

    @SerializedName(ControlConstants.AppStore.REMOVED)
    @Param(description = "应用商店应用删除时间")
    private Date removed;

    @SerializedName(ControlConstants.AppStore.OWNER)
    @Param(description = "应用商店应用创建人")
    private String owner;

    @SerializedName(ControlConstants.AppStore.CREATED)
    @Param(description = "应用商店应用创建时间")
    private Date created;

    @SerializedName(ControlConstants.AppStore.LAST_UPDATED)
    @Param(description = "应用商店应用更新时间")
    private Date lastUpdated;

    @SerializedName(ControlConstants.AppStore.STATE)
    @Param(description = "应用商店应用状态")
    private int state;

    @SerializedName(ControlConstants.AppStore.REMARK)
    @Param(description = "应用商店应用备注")
    private String remark;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRunScript() {
        return runScript;
    }

    public void setRunScript(String runScript) {
        this.runScript = runScript;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
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
