# Github test automation project
![badge-jdk-8] ![badge-tool-gradle]


## Description
This is an automation test framework for [github.com][github_com] using:
```
 Gradle
 Java
 TestNG
 Selenium Webdriver
 Cucumber
 Guice
 Apache
```
Test case execution results are stored at [reportportal.io][reportportal_io]

## UI Test scenarios
* User can change public profile settings on the following page: [github.com/settings/profile][github_profile]
* User can create new repository using the following page: [github.com/new][github_new_rep]
* User can create new organization using the following page: [github.com/organizations/new][github_new_org]
* User can create new gist using the following page: [gist.github.com][github_new_gist]
* User can comment the gist

## REST API v3 Test scenarios
* There is ability to check service availability using `GET https://api.github.com` request
* There is ability to change public profile settings using `PATCH /user` request
* There is ability to create new repository using `POST /user/repos` request
* There is ability to check existance of organization using `GET /orgs/:org` request
* There is ability to create new gist using `POST /gists` request
* There is ability to the gist using `POST /gists/:gist_id/comments` request

## Getting started
* Git clone the project
* Install browser driver: e.g. **[ChromeDriver]** or **[FirefoxDriver]** and put execute file to the folder `/github_test_testng_cuc/webdrivers/`.

*NOTE*: framework expects `.exe` file. If you are using *MacOs* than you should edit `wdr.pr` systemProperty at **`build.gradle`**.
* Install **[Gradle]**
* Edit `test.properties` and `reportportal.properties` files (see below).
* Edit personal data in `user_data.json` in `./src/test/resources` to make tests authorize
* Run tests

### Framework properties
All test properties are stored at `test.properties` file in `./src/main/resources/` directory.

`Test.properties` is already exists by default. File has the following structure:

```text
environment.variables.base_url=https://github.com
environment.variables.browser=chrome

test.variables.default.pageLoadTimeout=30
test.variables.default.implicitlyWaitTime=40
test.variables.default.elementNotDisplayedTimeout=2
test.variables.default.conditionTimeout=5

Url = https://api.github.com
gistsApiUrl = /gists
commentApiUrl = /comments
organizationApiUrl = /orgs
repositoryApiUrl = /repos
userApiUrl = /user
apiToken = ****
gistId = ****
```

`reportportal.properties` is also exists:
```text
#REQUIRED
rp.endpoint = https://web.demo.reportportal.io
rp.uuid = [uui]
rp.launch = [launch]
rp.project = [project]
```

Use can put your **custom** property file to the appropriate directory with another settings.
Set `environment.variables.browser=firefox` to use **FirefoxDriver** while executing tests.


## Execution
* To run tests from the command line using standard `.properties` file:
 ```
 gradle test
 ```

* To run tests from the command line using **custom** `.properties` file and specifying your own path to exec file of web driver:
 ```
 gradle test -Dtst.pr=your_file.properties -Dwdr.pr=./your_path
 ```

[github_com]: https://www.github.com
[reportportal_io]: https://reportportal.io
[github_profile]: https://github.com/settings/profile
[github_new_rep]: https://github.com/new
[github_new_org]: https://github.com/organizations/new
[github_new_gist]: https://gist.github.com
[badge-jdk-8]: https://img.shields.io/badge/jdk-8-yellow.svg "JDK-8"
[badge-tool-gradle]: https://img.shields.io/badge/tool-gradle-blue.svg "Gradle wrapper included"
[badge-junit-jupiter]: https://img.shields.io/badge/junit-jupiter-green.svg "JUnit Jupiter Engine"
[ChromeDriver]: https://sites.google.com/a/chromium.org/chromedriver/getting-started
[FirefoxDriver]: https://developer.mozilla.org/en-US/docs/Mozilla/QA/Marionette/WebDriver
[Gradle]: https://gradle.org/install/