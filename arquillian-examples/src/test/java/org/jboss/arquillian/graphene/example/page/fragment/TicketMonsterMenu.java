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
package org.jboss.arquillian.graphene.example.page.fragment;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;

import org.jboss.arquillian.graphene.findby.FindByJQuery;
import org.openqa.selenium.WebElement;

public class TicketMonsterMenu {

    @FindByJQuery("ul.nav li:eq(1) a")
    private WebElement eventsLink;

    @FindByJQuery("ul.nav li:eq(2) a")
    private WebElement venuesLink;

    @FindByJQuery("ul.nav li:eq(3) a")
    private WebElement bookingsLink;

    public void navigateToEvents() {
        guardAjax(eventsLink).click();
    }

    public void navigateToVenues() {
        guardAjax(venuesLink).click();
    }

    public void navigateToBookings() {
        guardAjax(bookingsLink).click();
    }
}
