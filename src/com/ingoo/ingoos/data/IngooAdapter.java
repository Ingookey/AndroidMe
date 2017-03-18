/**
 * Descrption: Ingoo adapter.
 */
package com.ingoo.ingoos.data;

import com.ingoo.ingoos.R;
import com.ingoo.ingoos.R.drawable;
import com.ingoo.ingoos.R.id;
import com.ingoo.ingoos.R.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class IngooAdapter extends BaseAdapter implements OnClickListener {
	public static String TAG = "ingoo/IngooAdapter";

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	
	class ViewHolder {
		ImageView img;
		TextView title;
		TextView info;
		Button button;
	}

	public IngooAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

	@Override
	public int getCount() {
		return getData().size();
	}
	

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.ingoo_item, null);
			viewHolder.img = (ImageView) convertView.findViewById(R.id.item_image);
			viewHolder.title = (TextView) convertView.findViewById(R.id.item_title);
			viewHolder.info = (TextView) convertView.findViewById(R.id.item_info);
			viewHolder.button = (Button) convertView.findViewById(R.id.item_button);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Log.d(TAG, "<getView> viewHolder: " + viewHolder);
		viewHolder.img.setBackgroundResource((Integer) getData().get(position).get("image"));
		viewHolder.title.setText((CharSequence) getData().get(position).get("title"));
		viewHolder.info.setText((CharSequence) getData().get(position).get("info"));
		viewHolder.button.setOnClickListener(this);

		return convertView;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.item_button:
			showItemInfo();
			break;
		default :
				break;
		}
	}
	
    private List<HashMap<String, Object>> getData() {
        List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        
        map.put("image", R.drawable.ingoo_launcher);
        map.put("title", "G1");
        map.put("info", "google 1");
        maps.add(map);
        
        map = new HashMap<String, Object>();
        map.put("image", R.drawable.ingoo_launcher);
        map.put("title", "G2");
        map.put("info", "google 2");
        maps.add(map);
        
        map = new HashMap<String, Object>();
        map.put("image", R.drawable.ingoo_launcher);
        map.put("title", "G3");
        map.put("info", "google 3");
        maps.add(map);
        return maps;
    }

	private void showItemInfo() {
		new AlertDialog.Builder(mContext).setTitle("my listview").setMessage("introduce....")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();
	}
}
