package ru.kpfu.itis.android.android25_26weatherapp.analytics

import android.util.Log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import ru.kpfu.itis.android.core.analytics.AnalyticsTracker
import javax.inject.Inject

class FirebaseAnalyticsTracker @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsTracker {

    override fun trackScreen(screenName: String) {
        firebaseAnalytics.logEvent("screen_open") {
            param("screen_name", screenName)
        }
    }
}