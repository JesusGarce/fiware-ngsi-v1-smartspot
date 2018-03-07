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

package eu.hopu;

import eu.hopu.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for the NGSI 9/10 requests
 */
@Path("")
public class NgsiBaseController {

    private static Logger logger = LoggerFactory.getLogger(NgsiBaseController.class);

    public NgsiBaseController() {
        System.out.println("me inicializo");
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/notifyContext")
    public Response notifyContextRequest(
            NotifyContext notify, @Context HttpServletRequest httpServletRequest) throws Exception {
        System.out.println("al menos llego");
        return Response.ok().entity(notifyContext(notify)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/updateContext")
    public Response updateContextRequest(
            UpdateContext updateContext, @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(updateContext(updateContext)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/registry/registerContext")
    public Response registerContextRequest(
            RegisterContext registerContext, @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(registerContext(registerContext)).build();
    }


    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/subscribeContext")
    public Response subscribeContextRequest(SubscribeContext subscribeContext, @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(subscribeContext(subscribeContext)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/updateContextSubscription")
    public Response updateContextSubscription(UpdateContextSubscription updateContextSubscription,
                                              @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(updateContextSubscription(updateContextSubscription)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/unsubscribeContext")
    public Response unsubscribeContextRequest(UnsubscribeContext unsubscribeContext,
                                              @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(unsubscribeContext(unsubscribeContext)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/queryContext")
    public Response queryContextRequest(QueryContext queryContext, @Context HttpServletRequest httpServletRequest) throws Exception {

        return Response.ok().entity(queryContext(queryContext)).build();
    }


    protected NotifyContextResponse notifyContext(final NotifyContext notify) throws Exception {
        NotifyContextResponse notifyContextResponse = new NotifyContextResponse();

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        notifyContextResponse.setResponseCode(statusCode);

        return notifyContextResponse;

        //throw new UnsupportedOperationException("notifyContext");
    }

    // OK
    protected UpdateContextResponse updateContext(final UpdateContext update) throws Exception {
        UpdateContextResponse updateContextResponse = new UpdateContextResponse();

        ContextElementResponse contextElementResponse = new ContextElementResponse();

        ContextElement contextElement = new ContextElement();

        contextElement.setId("Ubicacion_undefined");
        contextElement.setType("SmartSpot");

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("16.23");

        ContextAttribute humidity = new ContextAttribute();
        humidity.setName("humidity");
        humidity.setType("Number");
        humidity.setValue("2.321");

        contextAttributeList.add(temperature);
        contextAttributeList.add(humidity);

        contextElement.setContextAttributeList(contextAttributeList);

        contextElementResponse.setContextElement(contextElement);

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        contextElementResponse.setStatusCode(statusCode);

        List<ContextElementResponse> contextElementResponseList = new LinkedList<ContextElementResponse>();

        contextElementResponseList.add(contextElementResponse);

        updateContextResponse.setContextElementResponses(contextElementResponseList);

        return updateContextResponse;
    }

    // OK
    protected RegisterContextResponse registerContext(final RegisterContext register) throws Exception {
        RegisterContextResponse registerContextResponse = new RegisterContextResponse();

        registerContextResponse.setRegistrationId(register.getRegistrationId());
        registerContextResponse.setDuration(register.getDuration());

        return registerContextResponse;
        //throw new UnsupportedOperationException("registerContext");
    }

    // OK
    protected SubscribeContextResponse subscribeContext(final SubscribeContext subscribe) throws Exception {
        SubscribeContextResponse subscribeContextResponse = new SubscribeContextResponse();

        SubscribeResponse subscribeResponse = new SubscribeResponse();
        subscribeResponse.setSubscriptionId(String.valueOf(Math.random()* (999999999 - 1) + 1));
        subscribeResponse.setDuration(subscribe.getDuration());
        subscribeResponse.setThrottling(subscribe.getThrottling());

        subscribeContextResponse.setSubscribeResponse(subscribeResponse);

        return subscribeContextResponse;
        //throw new UnsupportedOperationException("subscribeContext");
    }

    // OK
    protected UpdateContextSubscriptionResponse updateContextSubscription(final UpdateContextSubscription updateContextSubscription) throws Exception {
        UpdateContextSubscriptionResponse updateContextSubscriptionResponse = new UpdateContextSubscriptionResponse();


        SubscribeResponse subscribeResponse = new SubscribeResponse();
        subscribeResponse.setSubscriptionId(updateContextSubscription.getSubscriptionId());
        subscribeResponse.setDuration(updateContextSubscription.getDuration());
        subscribeResponse.setThrottling(updateContextSubscription.getThrottling());

        updateContextSubscriptionResponse.setSubscribeResponse(subscribeResponse);

        return updateContextSubscriptionResponse;
        //throw new UnsupportedOperationException("updateContextSubscription");
    }

    // OK
    protected UnsubscribeContextResponse unsubscribeContext(final UnsubscribeContext unsubscribe) throws Exception {
        UnsubscribeContextResponse unsubscribeContextResponse = new UnsubscribeContextResponse();

        unsubscribeContextResponse.setSubscriptionId(unsubscribe.getSubscriptionId());

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        unsubscribeContextResponse.setStatusCode(statusCode);

        return unsubscribeContextResponse;
        //throw new UnsupportedOperationException("unsubscribeContext");
    }

    // OK
    protected QueryContextResponse queryContext(final QueryContext query) throws Exception {

        ContextElementResponse contextElementResponse = new ContextElementResponse();

        ContextElement contextElement = new ContextElement();

        contextElement.setId("Ubicacion_undefined");
        contextElement.setType("SmartSpot");

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("16.23");

        ContextAttribute humidity = new ContextAttribute();
        humidity.setName("humidity");
        humidity.setType("Number");
        humidity.setValue("2.321");

        contextAttributeList.add(temperature);
        contextAttributeList.add(humidity);

        contextElement.setContextAttributeList(contextAttributeList);

        contextElementResponse.setContextElement(contextElement);

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        contextElementResponse.setStatusCode(statusCode);

        QueryContextResponse queryContextResponse = new QueryContextResponse();

        List<ContextElementResponse> contextElementResponseList = new LinkedList<ContextElementResponse>();

        contextElementResponseList.add(contextElementResponse);

        queryContextResponse.setContextElementResponses(contextElementResponseList);

        return queryContextResponse;
    }
}
