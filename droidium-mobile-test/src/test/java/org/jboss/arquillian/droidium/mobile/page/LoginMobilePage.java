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

import static org.jboss.arquillian.graphene.Graphene.waitGui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginMobilePage {
    
    private static final String ACTIVITY = "org.jboss.aerogear.todo.activities.LoginActivity";

    @FindBy(id = "username_field")
    private WebElement usernameField;

    @FindBy(id = "password_field")
    private WebElement passwordField;

    @FindBy(id = "login_button")
    private WebElement loginButton;

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
    
    public String getActivity() {
        return ACTIVITY;
    }

    public void waitUntilPageIsLoaded() {
        waitGui().until().element(loginButton).is().visible();
    }
}
