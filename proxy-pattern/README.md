# Proxy Design Pattern

<img src="proxy.png" width="100%" height="auto">

In the realm of test automation, the `Proxy Design Pattern` serves as a strategic approach to manage and control access to objects, particularly when working across different environments like `development, staging, and production`. At its core, the proxy pattern introduces a placeholder or intermediary object that stands in for the actual object, effectively controlling and sometimes restricting the operations performed on that object.

To draw a parallel from everyday scenarios, think of accessing the internet through an office network. Here, a proxy server might allow you to browse certain websites like `Google or StackOverflow`, but restrict access to others, like `social media platforms`. Although it appears as though you have direct access to the internet, the proxy server is actually mediating all requests, determining what is allowed and what is not.

This pattern is particularly beneficial in `test automation` where scripts are run across various environments. For instance, while certain operations, such as `placing or canceling orders`, may be permissible in a development environment, they might be restricted in staging or production due to limited user permissions. Traditionally, this challenge has been addressed using conditional logic within the test scripts, resulting in numerous `if-else` statements to handle different environments. However, this approach can lead to code that is difficult to maintain and prone to errors.

The `Proxy Design Pattern` offers a more elegant solution by allowing controlled access to objects without cluttering test scripts with environment-specific conditions. By implementing a proxy object, you can encapsulate the logic for environment-specific access control, thereby simplifying your test automation code and making it more robust and maintainable.

This pattern is invaluable when you need to manage access rights across various environments without compromising the integrity and reliability of your test automation framework.

`Still confused?` No Worries!


## Application overview

<img src="app.png" width="100%" height="auto">

In the given application architecture, the workflow for automating the process of placing an order is structured to accommodate different payment methods and environment-specific conditions. Here's how the application flow is structured:

<img src="proxy-pattern-app-flow.jpg" width="100%" height="auto">


The automation process consists of the following steps:

### 1. User Details Input
The process begins by capturing the user's details. This step is common across all test cases and ensures that the necessary user information is gathered for the subsequent steps.

### 2. Payment Method Selection
Based on the test scenario, the user will be prompted to provide payment details. The payment method can vary as follows:

- **Credit Card Test Flow (CC TC)**: The user enters their credit card information.
- **Net Banking Test Flow (NB TC)**: The user provides their net banking details.
- **PayPal Test Flow (PP TC)**: The user submits their PayPal account information.

### 3. Environment Check
After the payment method is selected, the application checks the current environment where the test is being executed. This step is critical as it determines whether the order can be placed:

- **QA/Development**: If the environment is QA or Development, the system will proceed to place the order. Placing an order here does not have real-world implications.
- **Staging/Production**: If the environment is Staging or Production, the system will **not** proceed to place the order. These higher environments are closer to live conditions, and placing an order could have unintended consequences, so the action is restricted.

### 4. Order Placement
In the QA/Dev environment, after passing the environment check, the system proceeds to place the order. This involves processing the payment and confirming the transaction.

### 5. Order Confirmation
Once the order is placed in the QA/Dev environment, the system generates an Order ID, which serves as confirmation of the purchase.

### 6. End of Process
The process concludes after the order confirmation. In cases where the environment is Staging or Production, the process ends without placing the order.




