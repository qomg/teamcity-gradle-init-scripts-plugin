/*
 * Copyright 2017 Rod MacKenzie.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.rodm.teamcity.gradle.scripts.server.health;

import jetbrains.buildServer.web.openapi.PagePlaces;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.healthStatus.HealthStatusItemPageExtension;
import org.jetbrains.annotations.NotNull;

import static com.github.rodm.teamcity.gradle.scripts.GradleInitScriptsPlugin.PLUGIN_NAME;

public class InitScriptsHealthStatusItemExtension extends HealthStatusItemPageExtension {

    public InitScriptsHealthStatusItemExtension(@NotNull PagePlaces pagePlaces, PluginDescriptor descriptor) {
        super(PLUGIN_NAME, pagePlaces);
        setIncludeUrl(descriptor.getPluginResourcesPath("report.jsp"));
        addCssFile("/css/admin/buildTypeForm.css");
        setVisibleOutsideAdminArea(true);
        register();
    }
}
