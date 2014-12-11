/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.usergrid.rest.test.resource2point0;

import org.apache.usergrid.rest.test.resource2point0.endpoints.mgmt.ManagementResource;
import org.apache.usergrid.rest.test.resource2point0.endpoints.OrganizationResource;
import org.apache.usergrid.rest.test.resource2point0.endpoints.UrlResource;
import org.apache.usergrid.rest.test.resource2point0.state.ClientContext;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


/**
 * This REST client was made to be able to send calls to any backend system that accepts calls. To do this It needs to
 * work independently of the existing REST test framework.
 */
public class RestClient implements UrlResource {

    private final String serverUrl;
    private ClientContext context;

    public WebResource resource;
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create( config );

    /**
     *
     * @param serverUrl
     */
    public RestClient( final String serverUrl ) {
        this.serverUrl = serverUrl;
        this.context = new ClientContext();
        resource = client.resource( serverUrl );
        resource.path( serverUrl );
    }


    /**
     * TODO: should this method return the base path or the total path we have built?
     */
    @Override
    public String getPath() {
        return resource.getURI().toString();
    }

    @Override
    public WebResource getResource() {
        return client.resource( serverUrl );
    }

    public ClientContext getContext() {
        return context;
    }


    /**
     * Get the management resource
     */
    public ManagementResource management() {
        return new ManagementResource( context, this );
    }


    /**
     * Get hte organization resource
     */
    public OrganizationResource org( final String orgName ) {
        return new OrganizationResource( orgName, context, this );
    }


    //todo:fix this method for the client.
//    public void loginAdminUser( final String username, final String password ) {
//        //Post isn't implemented yet, but using the method below we should be able to get a superuser password as well.
//        //final String token = management().token().post(username, password);
//
//        //context.setToken( token );
//    }

//
//    public void createOrgandOwner


}