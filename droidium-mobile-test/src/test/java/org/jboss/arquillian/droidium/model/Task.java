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
package org.jboss.arquillian.droidium.model;

public class Task {

    public Task(Project project, String title, String year, String month, String day, String description) {
        this.project = project;
        this.title = title;
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }

    private Project project;

    private String title;

    private String year;

    private String month;

    private String day;

    private String description;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(super.toString())
                .append(" [ project=")
                .append(project.toString())
                .append(" title=")
                .append(title)
                .append(" year=")
                .append(year)
                .append(" month=")
                .append(month)
                .append(" day=")
                .append(day)
                .append(" description=")
                .append(description)
                .append(" ]")
                .toString();
    }
}
