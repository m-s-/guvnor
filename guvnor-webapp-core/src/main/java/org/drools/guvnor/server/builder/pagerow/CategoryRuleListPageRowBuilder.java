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
package org.drools.guvnor.server.builder.pagerow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.drools.guvnor.client.rpc.CategoryPageRow;
import org.drools.guvnor.client.rpc.PageRequest;
import org.drools.repository.AssetItem;
import org.drools.guvnor.client.rpc.Path;
import org.drools.guvnor.client.rpc.PathImpl;

public class CategoryRuleListPageRowBuilder
    implements
    PageRowBuilder<PageRequest, Iterator<AssetItem>> {

    private PageRequest         pageRequest;
    private Iterator<AssetItem> iterator;

    public List<CategoryPageRow> build() {
        validate();
        List<CategoryPageRow> rowList = new ArrayList<CategoryPageRow>();

        // Filtering and skipping records to the required page is handled in
        // repository.findAssetsByState() so we only need to simply copy
        while ( iterator.hasNext() ) {
            AssetItem assetItem = iterator.next();
            rowList.add( makeCategoryPageRow( assetItem ) );
        }
        return rowList;
    }

    private CategoryPageRow makeCategoryPageRow(AssetItem assetItem) {
        CategoryPageRow row = new CategoryPageRow();
        //REVISIT: get a Path instance from drools-repository-vfs
        Path path = new PathImpl();
        path.setUUID(assetItem.getUUID());
        row.setPath( path );
        row.setFormat( assetItem.getFormat() );
        row.setName( assetItem.getName() );
        row.setDescription( assetItem.getDescription() );
        row.setAbbreviatedDescription( StringUtils.abbreviate( assetItem.getDescription(),
                                                               80 ) );
        row.setLastModified( assetItem.getLastModified().getTime() );
        row.setStateName( assetItem.getState().getName() );
        row.setPackageName( assetItem.getModuleName() );
        return row;
    }

    public CategoryRuleListPageRowBuilder withPageRequest(final PageRequest pageRequest) {
        this.pageRequest = pageRequest;
        return this;
    }

    public CategoryRuleListPageRowBuilder withContent(Iterator<AssetItem> iterator) {
        this.iterator = iterator;
        return this;
    }

    public void validate() {
        if ( pageRequest == null ) {
            throw new IllegalArgumentException( "PageRequest cannot be null" );
        }

        if ( iterator == null ) {
            throw new IllegalArgumentException( "Content cannot be null" );
        }

    }

}
