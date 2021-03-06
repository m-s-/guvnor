/*
 * Copyright 2011 JBoss Inc
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package org.drools.guvnor.client.explorer.drools;

import org.drools.guvnor.client.GuvnorEventBus;
import org.drools.guvnor.client.asseteditor.RefreshAssetEditorEvent;
import org.drools.guvnor.client.explorer.AbstractClientFactoryImpl;
import org.drools.guvnor.client.explorer.GuvnorActivityMapper;
import org.drools.guvnor.client.widgets.drools.wizards.WizardFactoryImpl;
import org.drools.guvnor.client.widgets.drools.wizards.assets.NewGuidedDecisionTableAssetWizardContext;
import org.drools.guvnor.client.widgets.wizards.WizardContext;
import org.drools.guvnor.client.widgets.wizards.WizardFactory;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.widgets.events.NotificationEvent;
import org.uberfire.security.Identity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Map;

@ApplicationScoped
public class ClientFactoryImpl
        extends AbstractClientFactoryImpl {

    private WizardFactory wizardFactory;

    @Inject
    PlaceManager placeManager;

    @Inject
    Event<RefreshAssetEditorEvent> refreshAssetEditorEvents;

    @Inject
    Event<NotificationEvent> notifications;

    @Inject
    Identity identity;



    @Inject
    public ClientFactoryImpl(GuvnorEventBus eventBus) {
        super(eventBus);
    }

    @Override
    public PlaceManager getPlaceManager() {
        return placeManager;
    }

    /*
      * TODO: Alternatively, we can do below:
      * <generate-with class="org.drools.guvnor.client.util.ActivityMapper">
      *     <when-type-assignable class="org.drools.guvnor.client.explorer.GuvnorDroolsActivityMapper"/>
      * </generate-with>
      * We will revisit this code to decide which way is better later.
      */
    public GuvnorActivityMapper getActivityMapper() {
        return new GuvnorDroolsActivityMapper(this);
    }

    public WizardFactory getWizardFactory() {
        if (wizardFactory == null) {
            wizardFactory = new WizardFactoryImpl(this,
                    eventBus);
        }
        return wizardFactory;
    }

    @Override
    public Event<RefreshAssetEditorEvent> getRefreshAssetEditorEvents() {
        return refreshAssetEditorEvents;
    }

    @Override
    public Event<NotificationEvent> getNotificationEvents() {
        return notifications;
    }

    @Override
    public WizardContext makeContext(final Map<String, Object> parameters) {
        if (NewGuidedDecisionTableAssetWizardContext.isInstance(parameters)) {
            return NewGuidedDecisionTableAssetWizardContext.create(parameters);
        } else {
            return new WizardContext() {
                @Override
                public Map<String, Object> getParameters() {
                    return parameters;
                }
            };
        }
    }

    @Override
    public Identity getIdentity() {
        return identity;
    }

}
