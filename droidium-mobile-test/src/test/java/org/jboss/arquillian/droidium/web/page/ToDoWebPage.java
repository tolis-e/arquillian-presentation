/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.droidium.web.page;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

import org.jboss.arquillian.droidium.model.Project;
import org.jboss.arquillian.droidium.model.Task;
import org.jboss.arquillian.droidium.web.page.fragments.ProjectWebFragment;
import org.jboss.arquillian.droidium.web.page.fragments.TaskWebFragment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ToDoWebPage {

    @FindBy(id = "project-list")
    private ProjectWebFragment projectFragment;

    @FindBy(id = "task-container")
    private TaskWebFragment taskFragment;

    @FindBy(id = "logout-btn")
    private WebElement logout;

    @FindBy(tagName = "footer")
    private WebElement footer;

    public void addProject(Project project) {
        projectFragment.addProject(project);
    }

    public boolean projectExists(Project project) {
        return projectFragment.projectExists(project);
    }

    public boolean taskExists(Task task) {
        return taskFragment.taskExists(task);
    }

    public void addTask(Task task) {
        taskFragment.addTask(task);
    }

    public void logout() {
        guardAjax(logout).click();
    }

    public void waitUntilPageIsLoaded() {
        waitGui().until().element(footer).is().visible();
    }
}
