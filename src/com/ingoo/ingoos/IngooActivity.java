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
import android.widget.Toast;

import com.ingoo.ingoos.thread.ThreadPool;
import com.ingoo.ingoos.utils.AppUtil;
import com.ingoo.ingoos.utils.LogTime;

import java.util.concurrent.ThreadPoolExecutor;


public class IngooActivity extends Activity implements OnClickListener {
	public static String TAG = "ingoo/MainActivity";
	
	private SyncHandler mSyncHandler = null;
	private ThreadPoolExecutor mThreadPool;
	private Button mButton1;
	
	public class SyncHandler extends Handler {
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
		setContentView(R.layout.activity_main);
		
		long startTime = LogTime.getLogTime();
		initializeData();
		setupViews();
		LogTime.logTimeMsg("onCreate ", startTime);
		//AppUtil.buildSystemInfo(this);
	}

	private void initializeData() {
		mSyncHandler = new SyncHandler();
		mThreadPool = ThreadPool.getThreadPool();
		
		mThreadPool.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.d(TAG, "<run> APP_VERSION: " + IngooHelper.APP_VERSION);
			}
		});
	}
	
	private void setupViews() {
		mButton1 = (Button) findViewById(R.id.item_test2);
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

	@Override
	protected void onResume() {
		super.onResume();
		//mSyncHandler.sendEmptyMessage(IngooHelper.MSG_ERROR);
	}

	@Override
	public void onClick(View view) {
		Log.d(TAG, "<onClick> view: " + view);
		if (view == mButton1) {
			Toast toast = Toast.makeText(this, "…Ë÷√≥…π¶", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
		} else {

		}
	}
}
