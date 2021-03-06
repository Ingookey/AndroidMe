package com.ingoo.ingoos;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ingoo.ingoos.data.IngooAdapter;
import com.ingoo.ingoos.data.UserInfo;
import com.ingoo.ingoos.thread.TestThread;
import com.ingoo.ingoos.thread.ThreadPool;
import com.ingoo.ingoos.utils.LogTime;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
			LogTime.logd(TAG, "<handleMessage> msg.what: " + msg.what);
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
		LogTime.logd(TAG, "<onCreate> APP_VERSION: " + IngooHelper.APP_VERSION);

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
		LogTime.logd("onCreate mMainHandler & mThreadPool: ",
							   mMainHandler + " " + mThreadPool);
		ActionBar bar = getActionBar();
		bar.setDisplayHomeAsUpEnabled(true);
		
		mThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				LogTime.logd(TAG, "<run> System info: ");
				//TODO Need fix je
				//AppUtil.buildSystemInfo(IngooActivity.this);
				try {
					//Class<UserInfo> classRef1 = UserInfo.class;
					Class<?> clazz = Class.forName("com.ingoo.ingoos.thread.UserInfo");
					LogTime.logd(TAG, "<initializeData> classRef1 & clazz: " +
													    " " + clazz);
					Field field = clazz.getDeclaredField("mUserId");
					Method method = clazz.getDeclaredMethod("getId", int.class);
					LogTime.logd(TAG, "<initializeData> field & method: " +
														field + " " + method);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
