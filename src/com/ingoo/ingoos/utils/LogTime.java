package com.ingoo.ingoos.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

/**
 * For logging elapsed real time in millisecond.
 */
public final class LogTime {
	private static String TAG = "ingoo/LogTime";
	
	private static final double MILLIS_MULTIPLIER = Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT
								? 1d / Math.pow(10, 6) : 1d;

	private LogTime() {
	}

	/**
	 * Returns the current time in either millisecond or nanosecond depending on
	 * the API level to be used with {@link #getElapsedMillis(long)}.
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static long getLogTime() {
		if (Build.VERSION_CODES.JELLY_BEAN_MR1 <= Build.VERSION.SDK_INT) {
			return SystemClock.elapsedRealtimeNanos();
		} else {
			return System.currentTimeMillis();
		}
	}

	/**
	 * Returns the time elapsed since the given logTime in millisecond.
	 * @param logTime the start time of the event.
	 */
	public static double getElapsedMillis(long logTime) {
		return (getLogTime() - logTime) * MILLIS_MULTIPLIER;
	}

	public static void logTimeMsg(String msg, final long startTime) {
		Log.d(TAG, msg + " costs: " + getElapsedMillis(startTime));
	}
	
	/**
	 * Print log for both of eclipse and logcat.
	 * @param Tag tag
	 * @param message log message
	 */
	public static void logd(final String tag, final String message) {
		System.out.println(message);
		// For android log
		Log.d(tag, message);
	}
}

