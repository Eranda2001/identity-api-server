/*
 * Copyright (c) 2025, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.impl;

import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.UsersApiService;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.core.UsersApiServiceCore;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.factories.UsersApiServiceCoreFactory;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserShareRequestBody;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserShareWithAllRequestBody;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserUnshareRequestBody;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserUnshareWithAllRequestBody;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.api.server.organization.user.sharing.management.common.constants.UserSharingMgtConstants.ErrorMessage.ERROR_INITIATING_USERS_API_SERVICE;

/**
 * Implementation of the user sharing management APIs.
 */
public class UsersApiServiceImpl implements UsersApiService {

    private final UsersApiServiceCore usersApiServiceCore;

    public UsersApiServiceImpl() {

        try {
            this.usersApiServiceCore = UsersApiServiceCoreFactory.getUsersApiServiceCore();
        } catch (IllegalStateException e) {
            throw new RuntimeException(ERROR_INITIATING_USERS_API_SERVICE.getMessage(), e);
        }
    }

    @Override
    public Response processUserSharing(UserShareRequestBody userShareRequestBody) {

        return usersApiServiceCore.shareUser(userShareRequestBody);
    }

    @Override
    public Response processUserSharingAll(UserShareWithAllRequestBody userShareWithAllRequestBody) {

        return usersApiServiceCore.shareUserWithAll(userShareWithAllRequestBody);
    }

    @Override
    public Response processUserUnsharing(UserUnshareRequestBody userUnshareRequestBody) {

        return usersApiServiceCore.unshareUser(userUnshareRequestBody);
    }

    @Override
    public Response removeUserSharing(UserUnshareWithAllRequestBody userUnshareWithAllRequestBody) {

        return usersApiServiceCore.unshareUserWithAll(userUnshareWithAllRequestBody);
    }

    @Override
    public Response usersUserIdSharedOrganizationsGet(String userId, String after, String before, Integer limit,
                                                      String filter, Boolean recursive) {

        return usersApiServiceCore.getSharedOrganizations(userId, after, before, limit, filter, recursive);
    }

    @Override
    public Response usersUserIdSharedRolesGet(String userId, String orgId, String after, String before, Integer limit,
                                              String filter, Boolean recursive) {

        return usersApiServiceCore.getSharedRoles(userId, orgId, after, before, limit, filter, recursive);
    }
}
