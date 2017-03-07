package com.ingoo.ingoos.utils;

import java.io.File;

import android.util.Log;

/**
 * File utility.</br>
 */
public final class FileUtil {
	private static String TAG = "ingoo/FileUtil";
	
	/** 
	 * Delete directory include file.
	 * @param file	file name or folder name</br>
	 */ 
	public static void deleteCatalog(File file) {
		Log.d(TAG, "<deleteCatalog> file: " + file);
		// For file is normal file
		if (null == file) {
			return; 
		}
		if (file.isFile()) {
			file.delete();
			return; 
		} 
 
		// For file is directory
		File[] files = file.listFiles();
		if (null == files) {
			return; 
		} 
		for (File item : files) {
			if (item.isDirectory()) {
				deleteCatalog(item);
			} else { 
				item.delete();
			} 
		} 
		file.delete();
	} 
} 