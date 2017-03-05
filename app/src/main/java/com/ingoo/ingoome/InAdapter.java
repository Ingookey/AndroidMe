package com.ingoo.ingoome;

/**
 * Created by Alex.he on 17.3.5.
 */
public class InAdapter {
	private static String TAG = "Ingoo/InAdapter";

    public InListener mInListener;

    interface  InListener {
        public void inListen();
    }
	
	public void setLinstener(InListener inListener) {
		mInListener = inListener; 
	}
	
	public void moreBitmap() {
		mInListener.inListen();
	}
}
