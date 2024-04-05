# Factory Design Pattern

## Introduction

Factory Pattern is one of the creation Patterns. It is mostly used when we need to create an object from one of several possible classes that share a common implements an interface. It creates objects without exposing the instantiation logic to the user. We, as the user, refer to the newly created object through a common interface.

![Alt Text](driver.png)

Here interface is **WebDriver** and all these **Chrome/Firefox/Safari/IE** driver concrete classes implement this WebDriver interface. We are able to refer to the, ChromeDriver/FirefoxDriver etc, instance through the WebDriver interface without much change in the code.