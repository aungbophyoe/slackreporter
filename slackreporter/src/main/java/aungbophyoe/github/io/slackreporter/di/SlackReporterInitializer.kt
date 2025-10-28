package aungbophyoe.github.io.slackreporter.di

import android.app.Application
import aungbophyoe.github.io.slackreporter.SlackReporter
import aungbophyoe.github.io.slackreporter.SlackReporterProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
/**
 * Created by aungb on 10/28/2025.
 */

object SlackReporterInitializer {

    fun init(app: Application, webhookUrl: String, enableLogging: Boolean = false) {
        startKoin {
            androidContext(app)
            modules(
                module {
                    single { SlackReporter.create(webhookUrl, enableLogging) }
                }
            )
        }

        // Initialize provider with the reporter instance
        SlackReporterProvider.init(SlackReporter.create(webhookUrl, enableLogging))
    }
}