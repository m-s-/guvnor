/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.guvnor.editors.guided.client.editor.templates;

import com.google.web.bindery.event.shared.EventBus;
import org.kie.guvnor.widgets.decoratedgrid.client.widget.AbstractVerticalMergableGridWidget;
import org.kie.guvnor.widgets.decoratedgrid.client.widget.ResourcesProvider;
import org.kie.guvnor.editors.guided.client.editor.templates.events.SetInternalTemplateDataModelEvent;
import org.kie.guvnor.editors.guided.model.templates.TemplateModel;

/**
 * A Vertical implementation of MergableGridWidget, that renders columns as erm,
 * columns and rows as rows. Supports merging of cells between rows.
 */
public class VerticalMergableTemplateDataGridWidget extends AbstractVerticalMergableGridWidget<TemplateModel, TemplateDataColumn> {

    public VerticalMergableTemplateDataGridWidget( ResourcesProvider<TemplateDataColumn> resources,
                                                   TemplateDataCellFactory cellFactory,
                                                   TemplateDataCellValueFactory cellValueFactory,
                                                   TemplateDropDownManager dropDownManager,
                                                   boolean isReadOnly,
                                                   EventBus eventBus ) {
        super( resources,
               cellFactory,
               cellValueFactory,
               dropDownManager,
               isReadOnly,
               eventBus );

        //Wire-up event handlers
        eventBus.addHandler( SetInternalTemplateDataModelEvent.TYPE,
                             this );
    }

}