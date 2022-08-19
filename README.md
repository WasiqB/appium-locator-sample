# Appium Locator sample

This repository is used for demonstrating different locator strategies supported by Appium.

## Prerequisites

You must have Java JDK 11 installed on your machine.

For running the tests on LambdaTest cloud platform, you must have a valid LambdaTest account.
Also, you must save your username and access key in the environment variables of your `.zshrc` / `.bash_profile`. If you
are on Windows, you must save your API key and secret in the System environment variables.

```shell
export LT_USERNAME=<cloud-username>
export LT_ACCESS_KEY=<cloud-key>
export LT_APP_ANDROID=<proverbial-app-cloud-url>
export LT_APP_ANDROID_2=<api-demo-app-cloud-url>
export LT_APP_IOS=<proverbial-app-ios-cloud-url>
```

## What tests are there?

### `LocatorsAndroidEspressoCloudTest.java` and `LocatorsAndroidEspressoTest.java`

This test class contains the tests for,

- `testByDataMatcher`
- `testByViewMatcher`

Class `LocatorsAndroidEspressoTest.java` was created because Espresso automation type is not yet supported on
LambdaTest as well as many other cloud platforms. Hence, this class will execute the test on local Android emulator.

### `LocatorsAndroidUiAutomatorTest.java`

This test class contains the tests for,

- `testByClassName`
- `testById`
- `testByUiSelector`
- `testByXpath`

### `LocatorsIOSTest.java`

This test class contains the tests for,

- `testByAccessibilityId`
- `testByClassChain`
- `testByPredicate`

## How to run the tests?

You can run the tests by running the following command:

```bash
> mvn clean install
```

You can also run from the IDE by opening `testng.xml` file and right-clicking on it and selecting `Run`.
