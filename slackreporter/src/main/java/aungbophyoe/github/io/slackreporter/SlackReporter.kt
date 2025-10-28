package aungbophyoe.github.io.slackreporter

import android.util.Log
import aungbophyoe.github.io.slackreporter.model.SlackRequestBody
import aungbophyoe.github.io.slackreporter.network.SlackApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 * Created by aungb on 10/28/2025.
 */

class SlackReporter(
    private val apiService: SlackApiService,
    private val webhookUrl: String,
    private val enableLogging: Boolean = false
) {
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun sendLog(message: String) {
        scope.launch {
            try {
                apiService.postMessage(fullUrl = webhookUrl,SlackRequestBody(text = message))
                if (enableLogging) Log.e("SlackReporter", "Slack message sent.")
            } catch (t: Throwable) {
                if (enableLogging) Log.e("SlackReporter", "Failed to send Slack message: ${t.message}")
            }
        }
    }

    fun shutdown() {
        scope.cancel()
    }

    companion object {
        fun create(webhookUrl: String, enableLogging: Boolean = false): SlackReporter {
            val apiService = SlackApiService.create(webhookUrl)
            return SlackReporter(apiService, webhookUrl,enableLogging)
        }
    }
}