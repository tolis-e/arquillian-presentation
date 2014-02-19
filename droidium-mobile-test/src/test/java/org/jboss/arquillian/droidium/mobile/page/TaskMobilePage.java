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
package org.jboss.arquillian.droidium.mobile.page;

import org.jboss.arquillian.droidium.web.model.Task;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TaskMobilePage extends MainMobilePage {

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "date")
    private WebElement date;

    @FindBy(id = "description")
    private WebElement description;

    @FindBy(id = "buttonSave")
    private WebElement saveButton;

    @FindBy(id = "add")
    private WebElement addButton;

    public void addTask(Task task) {
        addButton.click();
        name.sendKeys(task.getTitle());
        date.sendKeys(task.getYear() + "-");
        date.sendKeys(task.getMonth()+ "-");
        date.sendKeys(task.getDay());
        description.sendKeys(task.getDescription());
        saveButton.click();
    }
}