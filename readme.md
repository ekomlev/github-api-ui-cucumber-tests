# Github test automation project
![badge-jdk-8] ![badge-tool-gradle]


## Description
This is an automation test framework for [github.com][github_com] using:
```
 Java
 Cucumber
 TestNG
 Selenium Webdriver
 Gradle
```

## Test scenarios
* User can change public profile settings on the following page: [github.com/settings/profile][github_profile]
* User can create new repository using the following page: [github.com/new][github_new_rep]
* User can create new organization using the following page: [github.com/organizations/new][github_new_org]
* User can create new gist using the following page: [gist.github.com][github_new_gist]
* User can comment the gist

## Getting started
* Git clone the project
* Install browser driver: e.g. **[ChromeDriver]** or **[FirefoxDriver]** and put execute file to the folder `/github_test_testng_cuc/webdrivers/`.

*NOTE*: framework expects `.exe` file. If you are using *MacOs* than you should edit `CHROMEDRIVER_PATH` or `GECKODRIVER_PATH` variables at **`BrowserFactory.class`**.
* Install **[Gradle]**
* If it necessary put your custom `.properties` file (see below).
* Run tests

### Framework properties
All properties are stored at `.properties` file in `github_test/src/main/resources/` directory.

`Test.properties` is already exist by default. File has the following structure:

```java
 environment.variables.base_url=https://www.github.com
 environment.variables.browser=chrome
 test.variables.default.pageLoadTimeout=30
 test.variables.default.elementNotDisplayedTimeout=2
 test.variables.default.conditionTimeout=5
```

You can put your **custom** property file to the appropriate directory with another settings.
Set `environment.variables.browser=firefox` to use **FirefoxDriver** while executing tests.


## Execution
* To run tests from the command line using standard `.property` file type:
 ```
 gradle test
 ```

* To run tests from the command line using **custom** `.property` file type and specifying your own path to exec file of web driver:
 ```
 gradle test -Dtst.pr=your_file.properties -Dwdr.ps=./your_path
 ```

[github_com]: https://www.github.com
[github_profile]: https://github.com/settings/profile
[github_new_rep]: https://github.com/new
[github_new_org]: https://github.com/organizations/new
[github_new_gist]: https://gist.github.com/
[badge-jdk-8]: https://img.shields.io/badge/jdk-8-yellow.svg "JDK-8"
[badge-tool-gradle]: https://img.shields.io/badge/tool-gradle-blue.svg "Gradle wrapper included"
[badge-junit-jupiter]: https://img.shields.io/badge/junit-jupiter-green.svg "JUnit Jupiter Engine"
[ChromeDriver]: https://sites.google.com/a/chromium.org/chromedriver/getting-started
[FirefoxDriver]: https://developer.mozilla.org/en-US/docs/Mozilla/QA/Marionette/WebDriver
[Gradle]: https://gradle.org/install/