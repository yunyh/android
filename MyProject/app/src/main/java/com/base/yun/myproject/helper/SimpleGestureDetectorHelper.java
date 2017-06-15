package com.base.yun.myproject.helper;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.base.yun.myproject.MyItemRecyclerViewAdapter;

/**
 * Created by YoungHyup on 2017-06-16.
 */

public class SimpleGestureDetectorHelper extends GestureDetector {

    private RecyclerView.ViewHolder mCurrentViewholder;

    public SimpleGestureDetectorHelper(Context context){
        this(context, new SimpleGestureDetectorListener(new OnGestureLongPressListener() {
            @Override
            public void onLongPress() {

            }
        }));
    }

    private SimpleGestureDetectorHelper(Context context, OnGestureListener listener) {
        super(context, listener);
    }

    private SimpleGestureDetectorHelper(Context context, OnGestureListener listener, Handler handler) {
        super(context, listener, handler);
    }

    private SimpleGestureDetectorHelper(Context context, OnGestureListener listener, Handler handler, boolean unused) {
        super(context, listener, handler, unused);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    public boolean onTouchEvent(RecyclerView rv, MotionEvent ev){
        View view = rv.findChildViewUnder(ev.getX(), ev.getY());
        if(view != null){
            RecyclerView.ViewHolder viewHolder = rv.getChildViewHolder(view);
            if(viewHolder != null && viewHolder instanceof MyItemRecyclerViewAdapter.ViewHolder){
                mCurrentViewholder = viewHolder;
            }
        }
        return onTouchEvent(ev);

    }

    public static class SimpleGestureDetectorListener extends GestureDetector.SimpleOnGestureListener{
        private OnGestureLongPressListener mListener;

        public SimpleGestureDetectorListener(OnGestureLongPressListener listener){
            mListener = listener;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d("onSingleTapUp", e.toString());
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            Log.d("onLongPress", e.toString());
            if(mListener != null){
                mListener.onLongPress();
            }
        }
    }

    public interface OnGestureLongPressListener{
        void onLongPress();
    }
}
