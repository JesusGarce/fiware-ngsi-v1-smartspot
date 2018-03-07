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
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for the NGSI 9/10 convenient REST requests
 * Deviation from standard:
 *  - no support for attributeDomains requests
 *  - only NGSI 10 REST requests are supported
 */
@Path("")
public class NgsiRestBaseController {

    private static Logger logger = LoggerFactory.getLogger(NgsiRestBaseController.class);

    private NgsiValidation ngsiValidation;

    public NgsiRestBaseController() {
        ngsiValidation = new NgsiValidation();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}")
    public Response appendContextElement(@PathParam("entityID") String entityID,
                                         AppendContextElement appendContextElement,
                                         @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkAppendContextElement(appendContextElement);
        return Response.ok().entity(appendContextElement(entityID, appendContextElement)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes")
    public Response appendContextElementAttributes(@PathParam("entityID") String entityID,
                                                   AppendContextElement appendContextElement,
                                                   @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkAppendContextElement(appendContextElement);
        return Response.ok().entity(appendContextElement(entityID, appendContextElement)).build();
    }

    @PUT
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}")
    public Response updateContextElement(@PathParam("entityID") String entityID,
                                         UpdateContextElement updateContextElement,
                                         @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkUpdateContextElement(updateContextElement);
        return Response.ok().entity(updateContextElement(entityID, updateContextElement)).build();
    }

    @PUT
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes")
    public Response updateContextElementAttributes(@PathParam("entityID") String entityID,
                                                   UpdateContextElement updateContextElement,
                                                   @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkUpdateContextElement(updateContextElement);
        return Response.ok().entity(updateContextElement(entityID, updateContextElement)).build();
    }


    // Primera redefinición
    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}")
    public Response getContextEntity(@PathParam("entityID") String entityID,
                                     @Context HttpServletRequest httpServletRequest) throws Exception {
        ContextElementResponse contextElementResponse = getContextElement(entityID);
        System.out.println(contextElementResponse.toString());

        return Response.ok().entity(getContextElement(entityID)).build();
    }

    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes")
    public Response getContextEntityAttributes(@PathParam("entityID") String entityID,
                                     @Context HttpServletRequest httpServletRequest) throws Exception {
       return Response.ok().entity(getContextElement(entityID)).build();
    }

    @DELETE
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}")
    public Response deleteContextEntity(@PathParam("entityID") String entityID,
                                        UpdateContextElement updateContextElement,
                                        @Context HttpServletRequest httpServletRequest) throws Exception {

        ngsiValidation.checkUpdateContextElement(updateContextElement);
        return Response.ok().entity(deleteContextElement(entityID)).build();
    }

    @DELETE
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes")
    public Response deleteContextEntityAttributes(@PathParam("entityID") String entityID,
                                        UpdateContextElement updateContextElement,
                                        @Context HttpServletRequest httpServletRequest) throws Exception {

        ngsiValidation.checkUpdateContextElement(updateContextElement);
        return Response.ok().entity(deleteContextElement(entityID)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}")
    public Response appendContextAttributeValue(@PathParam("entityID") String entityID,
                                                UpdateContextAttribute updateContextAttribute,
                                                @PathParam("attributeName") String attributeName,
                                                @Context HttpServletRequest httpServletRequest) throws Exception {

        ngsiValidation.checkUpdateContextAttribute(entityID,attributeName,null,updateContextAttribute);
        return Response.ok().entity(appendContextAttribute(entityID,attributeName,updateContextAttribute)).build();
    }

    @PUT
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}")
    public Response updateContextAttribute(@PathParam("entityID") String entityID,
                                           UpdateContextAttribute updateContextAttribute,
                                           @PathParam("attributeName") String attributeName,
                                           @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkUpdateContextAttribute(entityID,attributeName,null,updateContextAttribute);
        return Response.ok().entity(updateContextAttribute(entityID,attributeName,updateContextAttribute)).build();
    }

    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}")
    public Response getContextAttribute(@PathParam("entityID") String entityID,
                                        @PathParam("attributeName") String attributeName,
                                        @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(getContextAttribute(entityID,attributeName)).build();
    }


    @DELETE
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}")
    public Response deleteContextAttribute(@PathParam("entityID") String entityID,
                                           @PathParam("attributeName") String attributeName,
                                           @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(deleteContextAttribute(entityID,attributeName)).build();
    }

    @PUT
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}/{valueID}")
    public Response updateContextAttributeValue(@PathParam("entityID") String entityID,
                                                UpdateContextAttribute updateContextAttribute,
                                                @PathParam("attributeName") String attributeName,
                                                @PathParam("valueID") String valueID,
                                                @Context HttpServletRequest httpServletRequest) throws Exception {

        ngsiValidation.checkUpdateContextAttribute(entityID, attributeName, valueID, updateContextAttribute);
        return Response.ok().entity(updateContextAttributeValue(entityID,attributeName,valueID,updateContextAttribute)).build();
    }

    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}/{valueID}")
    public Response getContextAttributeValue(@PathParam ("entityID") String entityID,
                                             @PathParam ("attributeName") String attributeName,
                                             @PathParam ("valueID") String valueID,
                                             @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(getContextAttributeValue(entityID,attributeName,valueID)).build();
    }

    @DELETE
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntities/{entityID}/attributes/{attributeName}/{valueID}")
    public Response deleteContextAttributeValue(@PathParam("entityID") String entityID,
                                                @PathParam("attributeName") String attributeName,
                                                @PathParam("valueID") String valueID,
                                                @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(deleteContextAttributeValue(entityID, attributeName, valueID)).build();
    }

    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntityTypes/{typeName}")
    public Response getContextEntityTypes(@PathParam("typeName") String typeName,
                                          @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(getContextEntitiesType(typeName)).build();
    }

    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntityTypes/{typeName}/attributes")
    public Response getContextEntityTypesAttribute(@PathParam("typeName") String typeName,
                                                   @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(getContextEntitiesType(typeName)).build();
    }

    @GET
    @Produces({"application/json", "plain/text"})
    @Path("/contextEntityTypes/{typeName}/attributes/{attributeName}")
    public Response getContextEntityTypes(@PathParam("typeName") String typeName,
                                          @PathParam("attributeName") String attributeName,
                                          @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(getContextEntitiesType(typeName, attributeName)).build();
    }

    @POST
    @Produces({"application/json", "plain/text"})
    @Path("/contextSubscriptions")
    public Response createSubscription(SubscribeContext subscribeContext,
                                       @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkSubscribeContext(subscribeContext);

        return Response.ok().entity(createSubscription(subscribeContext)).build();
    }

    @PUT
    @Produces({"application/json", "plain/text"})
    @Path("/contextSubscriptions/{subscriptionID}")
    public Response updateSubscription(@PathParam("subscriptionID") String subscriptionID,
                                       UpdateContextSubscription updateContextSubscription,
                                       @Context HttpServletRequest httpServletRequest) throws Exception {
        ngsiValidation.checkUpdateContextSubscription(updateContextSubscription);

        return Response.ok().entity(updateSubscription(updateContextSubscription)).build();
    }

    @DELETE
    @Produces({"application/json", "plain/text"})
    @Path("/contextSubscriptions/{subscriptionID}")
    public Response deleteSubscription(@PathParam("subscriptionID") String subscriptionID,
                                       @Context HttpServletRequest httpServletRequest) throws Exception {
        return Response.ok().entity(deleteSubscription(subscriptionID)).build();
    }


    /*
     * Methods overridden by child classes to handle the NGSI v1 convenient REST requests
     */

    // OK
    protected AppendContextElementResponse appendContextElement(String entityID,
                                                               AppendContextElement appendContextElement) throws Exception {
        AppendContextElementResponse appendContextElementResponse = new AppendContextElementResponse();

        EntityId entityId = new EntityId();
        entityId.setId("Prueba_append_servidor");
        entityId.setType("SmartSpot");
        entityId.setIsPattern(false);

        appendContextElementResponse.setEntityId(entityId);

        ContextAttributeResponse contextAttributeResponse = new ContextAttributeResponse();

        ContextAttribute contextAttribute = new ContextAttribute();
        contextAttribute.setName("temperature");
        contextAttribute.setType("Number");
        contextAttribute.setValue("9.12");

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();
        contextAttributeList.add(contextAttribute);
        contextAttributeResponse.setContextAttributeList(contextAttributeList);

        List<ContextAttributeResponse> contextAttributeResponseList = new LinkedList<ContextAttributeResponse>();
        contextAttributeResponseList.add(contextAttributeResponse);
        appendContextElementResponse.setContextAttributeResponses(contextAttributeResponseList);

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");
        contextAttributeResponse.setStatusCode(statusCode);

        appendContextElementResponse.setContextAttributeResponses(contextAttributeResponseList);

        return appendContextElementResponse;
            //throw new UnsupportedOperationException("appendContextElement");
    }

    //OK
    protected UpdateContextElementResponse updateContextElement(String entityID,
                                                                UpdateContextElement updateContextElement) throws Exception {

        UpdateContextElementResponse updateContextElementResponse = new UpdateContextElementResponse();

        List<ContextAttributeResponse> contextAttributeResponseList = new LinkedList<ContextAttributeResponse>();

        ContextAttributeResponse contextAttributeResponse = new ContextAttributeResponse();

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute contextAttribute = new ContextAttribute();
        contextAttribute.setName("temperature");
        contextAttribute.setType("Number");
        contextAttribute.setValue("5.12");

        contextAttributeList.add(contextAttribute);

        contextAttributeResponse.setContextAttributeList(contextAttributeList);

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        contextAttributeResponse.setStatusCode(statusCode);

        contextAttributeResponseList.add(contextAttributeResponse);

        updateContextElementResponse.setContextAttributeResponses(contextAttributeResponseList);

        return updateContextElementResponse;
        //throw new UnsupportedOperationException("updateContextElement");
    }

    //OK
    protected ContextElementResponse getContextElement(String entityID) throws Exception {
        ContextElementResponse contextElementResponse = new ContextElementResponse();

        ContextElement contextElement = new ContextElement();
        contextElement.setId(entityID);
        contextElement.setType("SmartSpot");

        List<ContextAttribute> contextList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("15.23");

        ContextAttribute humidity = new ContextAttribute();
        humidity.setName("humidity");
        humidity.setType("Number");
        humidity.setValue("2.321");

        contextList.add(temperature);
        contextList.add(humidity);

        contextElement.setContextAttributeList(contextList);

        StatusCode st = new StatusCode();
        st.setCode("200");
        st.setReasonPhrase("OK");

        contextElementResponse.setContextElement(contextElement);
        contextElementResponse.setStatusCode(st);

        return contextElementResponse;
    }

    // OK
    protected StatusCode deleteContextElement(String entityID) throws Exception {

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        return statusCode;
        //throw new UnsupportedOperationException("deleteContextElement");
    }

    // OK
    protected StatusCode appendContextAttribute(String entityID, String attributeName,
            UpdateContextAttribute updateContextAttribute) throws Exception {

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        return statusCode;
        //throw new UnsupportedOperationException("appendContextAttribute");

    }

    // OK
    protected StatusCode updateContextAttribute(final String entityID, String attributeName,
            UpdateContextAttribute updateContextElementRequest) throws Exception {

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        return statusCode;
        //throw new UnsupportedOperationException("updateContextAttribute");
    }

    // OK
    protected ContextAttributeResponse getContextAttribute( String entityID, String attributeName) throws Exception {
        ContextAttributeResponse contextAttributeResponse = new ContextAttributeResponse();

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("11.20");

        ContextAttribute humidity = new ContextAttribute();
        humidity.setName("humidity");
        humidity.setType("Number");
        humidity.setValue("10.32");

        //contextAttributeList.add(temperature);
        contextAttributeList.add(humidity);

        contextAttributeResponse.setContextAttributeList(contextAttributeList);

        StatusCode statusCode = new StatusCode();
        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        contextAttributeResponse.setStatusCode(statusCode);

        return contextAttributeResponse;
    }

    // OK
    protected StatusCode deleteContextAttribute(String entityID, String attributeName) throws Exception {
        StatusCode statusCode = new StatusCode();

        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        return statusCode;
    }

    // 500
    protected StatusCode updateContextAttributeValue(final String entityID, String attributeName, String valueID,
            UpdateContextAttribute updateContextElementRequest) throws Exception {

        StatusCode statusCode = new StatusCode();
        statusCode.setCode("200");
        statusCode.setReasonPhrase("OK");

        return statusCode;
        //throw new UnsupportedOperationException("updateContextAttributeValue");
    }

    // OK
    protected ContextAttributeResponse getContextAttributeValue( String entityID, String attributeName, String valueID) throws Exception {
        ContextAttributeResponse contextAttributeResponse = new ContextAttributeResponse();

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("13.20");

        ContextAttribute humidity = new ContextAttribute();
        humidity.setName("humidity");
        humidity.setType("Number");
        humidity.setValue("10.32");

        contextAttributeList.add(temperature);
        //contextAttributeList.add(humidity);

        contextAttributeResponse.setContextAttributeList(contextAttributeList);

        StatusCode statusCode = new StatusCode();
        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        contextAttributeResponse.setStatusCode(statusCode);

        return contextAttributeResponse;
    }

    // OK
    protected StatusCode deleteContextAttributeValue(String entityID, String attributeName, String valueID) throws Exception {

        StatusCode statusCode = new StatusCode();

        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        return statusCode;

    }

    // OK
    protected QueryContextResponse getContextEntitiesType(String typeName) throws Exception {

        QueryContextResponse queryContextResponse = new QueryContextResponse();

        List<ContextElementResponse> contextElementResponseList = new LinkedList<ContextElementResponse>();

        ContextElementResponse contextElementResponse = new ContextElementResponse();

        ContextElement contextElement = new ContextElement();
        contextElement.setId("Prueba_query_servidor");
        contextElement.setType("SmartSpot");

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("13.10");

        contextAttributeList.add(temperature);

        contextElement.setContextAttributeList(contextAttributeList);

        contextElementResponse.setContextElement(contextElement);

        StatusCode statusCode = new StatusCode();
        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        contextElementResponse.setStatusCode(statusCode);

        contextElementResponseList.add(contextElementResponse);

        queryContextResponse.setContextElementResponses(contextElementResponseList);
        return queryContextResponse;

    }

    // OK
    protected QueryContextResponse getContextEntitiesType(String typeName, String attributeName) throws Exception {

        QueryContextResponse queryContextResponse = new QueryContextResponse();

        List<ContextElementResponse> contextElementResponseList = new LinkedList<ContextElementResponse>();

        ContextElementResponse contextElementResponse = new ContextElementResponse();

        ContextElement contextElement = new ContextElement();
        contextElement.setId("Prueba_query_servidor");
        contextElement.setType("SmartSpot");

        List<ContextAttribute> contextAttributeList = new LinkedList<ContextAttribute>();

        ContextAttribute temperature = new ContextAttribute();
        temperature.setName("temperature");
        temperature.setType("Number");
        temperature.setValue("13.22");

        contextAttributeList.add(temperature);

        contextElement.setContextAttributeList(contextAttributeList);

        contextElementResponse.setContextElement(contextElement);

        StatusCode statusCode = new StatusCode();
        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        contextElementResponse.setStatusCode(statusCode);

        contextElementResponseList.add(contextElementResponse);

        queryContextResponse.setContextElementResponses(contextElementResponseList);
        return queryContextResponse;

    }

    // OK
    protected SubscribeContextResponse createSubscription(final SubscribeContext subscribeContext) throws Exception {
        SubscribeContextResponse subscribeContextResponse = new SubscribeContextResponse();


        SubscribeResponse subscribeResponse = new SubscribeResponse();
        subscribeResponse.setDuration(subscribeContext.getDuration());
        subscribeResponse.setSubscriptionId(String.valueOf(Math.random()* (999999999 - 1) + 1));
        subscribeResponse.setThrottling(subscribeContext.getThrottling());

        subscribeContextResponse.setSubscribeResponse(subscribeResponse);

        return subscribeContextResponse;
    }

    // OK
    protected UpdateContextSubscriptionResponse updateSubscription(
            UpdateContextSubscription updateContextSubscription) throws Exception {
        UpdateContextSubscriptionResponse updateContextSubscriptionResponse = new UpdateContextSubscriptionResponse();

        SubscribeResponse subscribeResponse = new SubscribeResponse();
        subscribeResponse.setDuration(updateContextSubscription.getDuration());
        subscribeResponse.setSubscriptionId(String.valueOf(Math.random()* (999999999 - 1) + 1));
        subscribeResponse.setThrottling(updateContextSubscription.getThrottling());

        updateContextSubscriptionResponse.setSubscribeResponse(subscribeResponse);

        return updateContextSubscriptionResponse;
    }

    // OK
    protected UnsubscribeContextResponse deleteSubscription(String subscriptionID) throws Exception {
        UnsubscribeContextResponse unsubscribeContextResponse = new UnsubscribeContextResponse();

        unsubscribeContextResponse.setSubscriptionId(subscriptionID);

        StatusCode statusCode = new StatusCode();
        statusCode.setReasonPhrase("OK");
        statusCode.setCode("200");

        unsubscribeContextResponse.setStatusCode(statusCode);

        return unsubscribeContextResponse;
    }

}
