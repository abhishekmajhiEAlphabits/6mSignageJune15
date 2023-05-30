package com.digitalsln.project6mSignage.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.digitalsln.project6mSignage.ForegroundService

/**
 * receiver to show display overlay screen
 */
class DisplayOverlayReceiver : BroadcastReceiver() {
    private val TAG = "TvTimer"
    override fun onReceive(context: Context, intent: Intent) {
        try {
            Log.d(TAG,"inside overlay ")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // check if the user has already granted
                // the Draw over other apps permission
                if (Settings.canDrawOverlays(context)) {
                    // start the service based on the android version
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(
                            Intent(
                                context,
                                ForegroundService::class.java
                            )
                        )
                    } else {
                        context.startService(Intent(context, ForegroundService::class.java))
                    }
                }
            } else {
                context.startService(Intent(context, ForegroundService::class.java))
            }


        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Modify System Settings permissions not granted",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}