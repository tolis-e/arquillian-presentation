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

import java.net.URL;

import org.jboss.arquillian.droidium.common.AppConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginWebPage {

    @FindBy(id = "login-box")
    private WebElement loginBox;

    @FindBy(id = "login-username")
    private WebElement usernameTextField;

    @FindBy(id = "login-password")
    private WebElement passwordTextField;

    @FindBy(id = "login-submit")
    private WebElement loginButton;

    public void go(WebDriver browser, URL context) {
        browser.get(context.toExternalForm() + "/" + AppConstants.TODO_APP_NAME);
    }

    public void login(String username, String password) {
        usernameTextField.sendKeys(username);
        passwordTextField.sendKeys(password);
        guardAjax(loginButton).click();
    }

    public void waitUntilPageIsLoaded() {
        waitGui().until().element(loginBox).is().visible();
    }
}
