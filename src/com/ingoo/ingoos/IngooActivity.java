package com.ingoo.ingoos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ingoo.ingoos.thread.ThreadPool;
import com.ingoo.ingoos.utils.AppUtil;
import com.ingoo.ingoos.utils.LogTime;

import java.util.concurrent.ThreadPoolExecutor;

import javax.net.ssl.SSLException;


public class IngooActivity extends Activity {
	public static String TAG = "ingoo/MainActivity";
	
	private MainHandler mMainHandler = null;
	private ThreadPoolExecutor mThreadPool;
	private ListView mListView;
	
	public class MainHandler extends Handler {
	    @Override
	    public void handleMessage(Message msg) {
			Log.d(TAG, "<handleMessage> msg.what: " + msg.what);
	        super.handleMessage(msg);
	        switch (msg.what) {
			case IngooHelper.MSG_UPDATE_DATA:
				updateData();
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
		mListView = (ListView) findViewById(R.id.arrayList);
		mListView.setAdapter(new IngooAdapter(this));
		
		mMainHandler = new MainHandler();
		mThreadPool = ThreadPool.getThreadPool();
		Log.d("onCreate mMainHandler & mThreadPool: ",
						mMainHandler + " " + mThreadPool);
		initializeData();
		LogTime.logTimeMsg("onCreate ", startTime);
		//AppUtil.buildSystemInfo(this);
	}

	private void initializeData() {
		mThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				
				Log.d(TAG, "<run> APP_VERSION: " + IngooHelper.APP_VERSION);
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
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

	public void updateData() {
		Log.d(TAG, "<updateData>");
		
	}
}
