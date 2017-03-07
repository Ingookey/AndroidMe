package com.ingoo.ingoos.utils;

import android.os.Trace;
import android.util.Log;

/**
 * Trace utility for performance issue.</br>
 */
public final class TraceUtil {
	private static String TAG = "ingoo/TraceUtil";
	
	private static String sSectionName = null;
	
	public static void beginTrace(String sectionName) {
		sSectionName = sectionName;
		Log.d(TAG, "<beginTrace> sectionName: " + sSectionName);
		Trace.beginSection(sSectionName);
	}
	
	public static void endTrace() {
		Log.d(TAG, "<endTrace> sectionName: " + sSectionName);
		Trace.endSection();
	}
}
