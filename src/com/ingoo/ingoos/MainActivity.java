package com.ingoo.ingoos;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ingoo.ingoos.utils.AppUtil;


public class MainActivity extends Activity {
	public static String TAG = "ingoo/MainActivity";
	
	public static int MSG_ITEM1 = 1; 
	
	private SyncHandler mSyncHandler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.w(TAG, "<onCreate> begin");
		setContentView(R.layout.activity_main);
		
		mSyncHandler = new SyncHandler();
		Message msg = new Message();
		msg.what = MSG_ITEM1;
		mSyncHandler.sendMessage(msg);
		//AppUtil.buildSystemInfo(this);
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
	
	public class SyncHandler extends Handler {
		public SyncHandler() {  
	    }  
	 
	    @Override  
	    public void handleMessage(Message msg) {  
	        super.handleMessage(msg);
	        switch (msg.what) {
			case MSG_ITEM1:
				
				break;
			default:
				break;
			}
	    }  
	}
}
