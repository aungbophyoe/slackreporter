# SlackReporter
**Koin-based Android library to send logs and error reports to Slack**

Send logs and error messages from your Android app directly to Slack. Includes **Koin DI support**, **OkHttp logging**, and **coroutine-safe reporting**.

---

## Slack Setup

1. Go to your Slack workspace.
2. Navigate to **Apps → Manage → Create New App → From scratch**.
3. Name your app and choose a workspace.
4. Go to **Incoming Webhooks** → **Activate Incoming Webhooks**.
5. Click **Add New Webhook to Workspace**, choose a channel, and copy the **Webhook URL**.

> Example Webhook URL:  
> ```'https://hooks.slack.com/services/sample/webhook/url'```

---

## Gradle Setup

Add JitPack repository:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

## Add library dependency:

```
dependencies {
implementation("com.github.aungbophyoe:slackreporter:1.0.0")
}
```

## Initialize SlackReporter in Application
```
class MyApp : Application() {
        override fun onCreate() {
            super.onCreate()
            SlackReporterInitializer.init(
                app = this,
                webhookUrl = "https://hooks.slack.com/services/YOUR/WEBHOOK/URL",
                enableLogging = true
            )
    
            SlackLogger.logInfo("MyApp", "App started successfully")
        }
}
```
## Send Logs Anywhere
```
SlackLogger.logInfo("MainActivity", "App started successfully")
SlackLogger.logError("MainActivity", Throwable("Test error"))
```

### logInfo() → informational messages
### logError() → errors with stack traces