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
package org.jboss.arquillian.droidium.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.arquillian.droidium.container.api.AndroidDevice;
import org.arquillian.droidium.native_.api.Instrumentable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.arquillian.droidium.drones.Browser;
import org.jboss.arquillian.droidium.drones.Mobile;
import org.jboss.arquillian.droidium.mobile.page.LoginMobilePage;
import org.jboss.arquillian.droidium.mobile.page.TaskMobilePage;
import org.jboss.arquillian.droidium.model.Project;
import org.jboss.arquillian.droidium.model.Task;
import org.jboss.arquillian.droidium.web.page.LoginWebPage;
import org.jboss.arquillian.droidium.web.page.ToDoWebPage;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Arquillian.class)
@RunAsClient
public class DroidiumTestCase {

    @Drone
    @Browser
    WebDriver browser;

    @Browser
    @Page
    ToDoWebPage toDoWebPage;

    @Browser
    @Page
    LoginWebPage loginWebPage;

    @Deployment(name = "todo-ear-app")
    @TargetsContainer("arq-jboss-managed")
    public static EnterpriseArchive getJBossASDeployment() {
        return ShrinkWrap.createFromZipFile(EnterpriseArchive.class, new File("src/test/resources/assets/todo-ear.ear"));
    }

    @Drone
    @Mobile
    WebDriver mobile;

    @Deployment(name = "todo-mobile-app")
    @Instrumentable(viaPort = 8081)
    @TargetsContainer("android")
    public static JavaArchive getAndroidDeployment() {
        return ShrinkWrap.createFromZipFile(JavaArchive.class, new File("src/test/resources/assets/android-todos.apk"));
    }

    @Mobile
    @Page
    private LoginMobilePage loginMobilePage;

    @Mobile
    @Page
    private TaskMobilePage taskMobilePage;

    @Test
    @OperateOnDeployment("todo-ear-app")
    public void webScenario(@ArquillianResource URL context) {
        // go to login page
        loginWebPage.go(browser, context);
        // wait until login page is loaded
        loginWebPage.waitUntilPageIsLoaded();
        // login
        loginWebPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
        // wait until the to-do page is loaded
        toDoWebPage.waitUntilPageIsLoaded();
        // create a new project
        Project newProject = new Project(PROJECT_1_TITLE);
        // add a new project
        toDoWebPage.addProject(newProject);
        // project should have been created
        assertTrue(toDoWebPage.projectExists(newProject));
        // create a new task
        Task newTask = new Task(newProject, WEB_TASK_1_TITLE, WEB_TASK_1_YEAR, WEB_TASK_1_MONTH, WEB_TASK_1_DAY,
                WEB_TASK_1_DESC);
        // add a new task
        toDoWebPage.addTask(newTask);
        // project should have been created
        assertTrue(toDoWebPage.taskExists(newTask));
        // logout
        toDoWebPage.logout();
        // wait until login page is loaded
        loginWebPage.waitUntilPageIsLoaded();
    }

    @Test
    @OperateOnDeployment("todo-mobile-app")
    public void mobileScenario(@ArquillianResource AndroidDevice device) {
        // start activity
        device.getActivityManagerProvider().getActivityManager().startActivity(loginMobilePage.getActivity());
        // login
        loginMobilePage.login("john", "123");
        // create task
        Task mobileTask = new Task(null, MOBILE_TASK_1_TITLE, MOBILE_TASK_1_YEAR, MOBILE_TASK_1_MONTH, MOBILE_TASK_1_DAY,
                MOBILE_TASK_1_DESC);
        // register task
        taskMobilePage.addTask(mobileTask);
        // logout
        taskMobilePage.logout();
    }

    private static final String LOGIN_USERNAME = "john";
    private static final String LOGIN_PASSWORD = "123";

    private static final String PROJECT_1_TITLE = "Arquillian";
    private static final String WEB_TASK_1_TITLE = "Web Task";
    private static final String WEB_TASK_1_YEAR = "2014";
    private static final String WEB_TASK_1_MONTH = "11";
    private static final String WEB_TASK_1_DAY = "11";
    private static final String WEB_TASK_1_DESC = "My Web Arquillian task";

    private static final String MOBILE_TASK_1_TITLE = "Mobile Task";
    private static final String MOBILE_TASK_1_YEAR = "2014";
    private static final String MOBILE_TASK_1_MONTH = "12";
    private static final String MOBILE_TASK_1_DAY = "01";
    private static final String MOBILE_TASK_1_DESC = "My Mobile Arquillian task";
}