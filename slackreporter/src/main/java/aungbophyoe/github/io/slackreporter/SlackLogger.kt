package aungbophyoe.github.io.slackreporter

/**
 * Created by aungb on 10/28/2025.
 */


object SlackLogger {
    fun logError(source: String, throwable: Throwable?) {
        val message = buildString {
            append("🚨 *Slack Error Report* 🚨\n")
            append("*Source:* $source\n")
            append("*Error:* ${throwable?.message ?: "Unknown"}\n")
            append("*Stacktrace:* ```\n${throwable?.stackTraceToString()?.take(2000)}```")
        }
        SlackReporterProvider.log(message)
    }

    fun logInfo(source: String, info: String) {
        val message = "ℹ️ *Info* • $source\n$info"
        SlackReporterProvider.log(message)
    }
}