/*
 * Copyright 2011 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.drools.ide.common.shared.workitems;

import org.drools.guvnor.shared.api.PortableObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


/**
 * A WorkDefinition used in Guvnor.
 * 
 * @see org.kie.process.core.WorkDefinition
 */
public class PortableWorkDefinition
    implements
        PortableObject {

    private static final long                        serialVersionUID = 540L;

    private String                                   name;
    private String                                   displayName;
    private Map<String, PortableParameterDefinition> parameters       = new HashMap<String, PortableParameterDefinition>();
    private Map<String, PortableParameterDefinition> results          = new HashMap<String, PortableParameterDefinition>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<PortableParameterDefinition> getParameters() {
        return new HashSet<PortableParameterDefinition>( parameters.values() );
    }

    public void setParameters(Set<PortableParameterDefinition> parameters) {
        this.parameters.clear();
        Iterator<PortableParameterDefinition> iterator = parameters.iterator();
        while ( iterator.hasNext() ) {
            addParameter( iterator.next() );
        }
    }

    public void addParameter(PortableParameterDefinition parameter) {
        parameters.put( parameter.getName(),
                        parameter );
    }

    public void removeParameter(String name) {
        parameters.remove( name );
    }

    public String[] getParameterNames() {
        return parameters.keySet().toArray( new String[parameters.size()] );
    }

    public PortableParameterDefinition getParameter(String name) {
        return parameters.get( name );
    }

    public Set<PortableParameterDefinition> getResults() {
        return new HashSet<PortableParameterDefinition>( results.values() );
    }

    public void setResults(Set<PortableParameterDefinition> results) {
        this.results.clear();
        Iterator<PortableParameterDefinition> it = results.iterator();
        while ( it.hasNext() ) {
            addResult( it.next() );
        }
    }

    public void addResult(PortableParameterDefinition result) {
        results.put( result.getName(),
                     result );
    }

    public void removeResult(String name) {
        results.remove( name );
    }

    public String[] getResultNames() {
        return results.keySet().toArray( new String[results.size()] );
    }

    public PortableParameterDefinition getResult(String name) {
        return results.get( name );
    }

}
