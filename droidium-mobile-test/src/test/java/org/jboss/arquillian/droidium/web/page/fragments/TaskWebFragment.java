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
package org.jboss.arquillian.droidium.web.page.fragments;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.arquillian.droidium.model.Task;
import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TaskWebFragment {

    @FindBy(css = ".add-task.task")
    private WebElement addButton;

    @FindBy(id = "task-title")
    private WebElement title;

    @FindBy(id = "task-date")
    private WebElement date;

    @FindBy(id = "task-desc")
    private WebElement description;

    @FindBy(className = "submit-btn")
    private WebElement submitButton;

    @FindByJQuery("#task-list-container div.task div.task-upper div.task-title")
    private List<WebElement> taskTitleList;

    @FindBy(id = "task-project-select")
    private Select projectSelectList;

    public void addTask(Task task) {
        waitGui().until().element(addButton).is().visible();
        addButton.click();
        waitGui().until().element(submitButton).is().visible();
        title.sendKeys(task.getTitle());
        date.clear();
        date.sendKeys(task.getYear());
        date.sendKeys(task.getMonth());
        date.sendKeys(task.getDay());
        description.sendKeys(task.getDescription());
        projectSelectList.selectByVisibleText(task.getProject().getTitle());
        guardAjax(submitButton).click();
    }

    public boolean taskExists(Task task) {
        if (task != null && !StringUtils.isEmpty(task.getTitle()))
            for (WebElement p : taskTitleList) {
                if (task.getTitle().equals(p.getText())) {
                    return true;
                }
            }
        return false;
    }
}
