package com.ingoo.ingoos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ingoo.ingoos.thread.TestThread;
import com.ingoo.ingoos.thread.ThreadPool;
import com.ingoo.ingoos.utils.AppUtil;
import com.ingoo.ingoos.utils.LogTime;

import java.util.concurrent.ThreadPoolExecutor;


public class IngooActivity extends Activity {
	public static String TAG = "ingoo/MainActivity";
	
	private static int sCount = 0;
	
	private MainHandler mMainHandler = null;
	private ThreadPoolExecutor mThreadPool;
	private ListView mListView;
	private boolean mPause = false;
	
	public class MainHandler extends Handler {
	    @Override
	    public void handleMessage(Message msg) {
			Log.d(TAG, "<handleMessage> msg.what: " + msg.what);
	        super.handleMessage(msg);
	        switch (msg.what) {
			case IngooHelper.MSG_UPDATE_DATA:
				break;
			case IngooHelper.MSG_ERROR:
				break;
			default:
				break;
			}
	    }  
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "<onCreate> APP_VERSION: " + IngooHelper.APP_VERSION);

		long startTime = LogTime.getLogTime();
		setContentView(R.layout.ingoo_outlook);
		mListView = (ListView) findViewById(R.id.outlook_List);
		mListView.setAdapter(new IngooAdapter(this));
		
		initializeData();
		LogTime.logTimeMsg("onCreate ", startTime);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (1 == sCount) {
			(new TestThread()).inlet();
		}
		sCount++;
	}

	@Override
	protected void onPause() {
		super.onPause();
		mPause = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void initializeData() {
		mMainHandler = new MainHandler();
		mThreadPool = ThreadPool.getThreadPool();
		Log.d("onCreate mMainHandler & mThreadPool: ",
						mMainHandler + " " + mThreadPool);
		
		mThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				Log.d(TAG, "<run> System info: ");
				//TODO Need fix je
				//AppUtil.buildSystemInfo(IngooActivity.this);
			}
		});
	}
}
