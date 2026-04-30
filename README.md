Framework Structure -

project-root
│
├── src/main/java
│   ├── base
│   │   └── Browser.java
│   ├── utils
│   │    └── Utility.java
│   ├── reports
│   │   ├── ExtentManager.java
│   │   ├── ExtentTestManager.java
│   │   └── TestListener.java
│   └── pages
│       ├── LoginPage.java
│       ├── homePage.java
|       ├── CheckoutPage.java
|       └── YourCartPage.java
|
├── src/test/java
│   ├── dataFile
|   |     └── test1Data
|   ├── extendReport
|   |     ├── extendManager.java
|   |     ├── extendTestManager.java
|   |     └─ testListener.java
|   ├── testPackage
|   |      └── test1.java
|   └── Utils
|         └── Utility.java
│
├── test-output
|   ├── screenshots
|   |      └── test1
|   |            ├── chrome
|   |            ├── firefox
|   |            └── msedge
│   └── ExtentReport.html    
│
└── pom.xml
└── Suite.xml
