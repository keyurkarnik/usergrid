/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.usergrid.corepersistence;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.usergrid.persistence.Entity;
import org.apache.usergrid.persistence.EntityManager;
import org.apache.usergrid.persistence.model.entity.Id;
import org.apache.usergrid.persistence.model.entity.SimpleId;


public class EntityWriteHelper {


    /**
     * Write some generic entity data into a collection with the entity manager and type specified
     * @param em
     * @param type
     * @param size
     * @return
     * @throws Exception
     */
    public static Set<Id> createTypes( final EntityManager em, final String type, final int size ) throws Exception {

        final Set<Id> identities = new HashSet<>();

        for ( int i = 0; i < size; i++ ) {
            final Entity entity = em.create( type, new HashMap<String, Object>() {{
                put( "property", "value" );
            }} );
            final Id createdId = new SimpleId( entity.getUuid(), entity.getType() );

            identities.add( createdId );
        }

        return identities;
    }
}