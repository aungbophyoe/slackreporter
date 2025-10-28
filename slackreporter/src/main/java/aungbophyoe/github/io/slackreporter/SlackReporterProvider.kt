package aungbophyoe.github.io.slackreporter

import android.util.Log

/**
 * Created by aungb on 10/28/2025.
 */

object SlackReporterProvider {
    @Volatile
    private var reporter: SlackReporter? = null

    fun init(reporterInstance: SlackReporter) {
        reporter = reporterInstance
        Log.e("SlackReporterProvider", "Initialized SlackReporter")
    }

    fun log(message: String) {
        val r = reporter
        if (r == null) {
            Log.e("SlackReporterProvider", "SlackReporter not initialized. Message not sent: $message")
        } else {
            r.sendLog(message)
        }
    }
}