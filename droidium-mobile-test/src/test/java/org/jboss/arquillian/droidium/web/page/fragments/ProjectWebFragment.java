/**
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.droidium.web.page.fragments;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.droidium.web.model.Project;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectWebFragment {

    @FindBy(className = "add-project")
    private WebElement addButton;

    @FindBy(id = "project-title")
    private WebElement titleTextField;

    @FindBy(className = "submit-btn")
    private WebElement submitButton;

    @FindByJQuery("#project-container div.project")
    private List<WebElement> projectList;

    public void addProject(Project project) {
        waitGui().until().element(addButton).is().visible();
        addButton.click();
        waitGui().until().element(submitButton).is().visible();
        titleTextField.sendKeys(project.getTitle());
        guardAjax(submitButton).click();
    }

    public boolean projectExists(Project project) {
        if (project != null && !StringUtils.isEmpty(project.getTitle()))
            for (WebElement p : projectList) {
                if (project.getTitle().equals(p.getText())) {
                    return true;
                }
            }
        return false;
    }
}
