/*
 * Copyright (C) 2015 Orange
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hopu.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by pborscia on 17/07/2015.
 */
public class SubscribeResponse {

    String subscriptionId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String duration;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String throttling;

    public SubscribeResponse() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThrottling() {
        return throttling;
    }

    public void setThrottling(String throttling) {
        this.throttling = throttling;
    }

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "subscriptionId='" + subscriptionId + '\'' +
                ", duration='" + duration + '\'' +
                ", throttling='" + throttling + '\'' +
                '}';
    }
}
