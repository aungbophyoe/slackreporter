package aungbophyoe.github.io.slackreporter

/**
 * Created by aungb on 10/28/2025.
 */

object SlackReporterInitializer {

    fun init(webhookUrl: String, enableLogging: Boolean = false) {
        val reporter = SlackReporter.create(webhookUrl, enableLogging)

        SlackReporterProvider.init(reporter)
    }
}