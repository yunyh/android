package com.base.yun.myproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.base.yun.myproject.dummy.DummyContent;
import com.base.yun.myproject.dummy.DummyContent.DummyItem;
import com.base.yun.myproject.helper.SimpleItemTouchHelper;

import java.util.List;

public class ItemFragment extends Fragment implements View.OnTouchListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;

    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, this));
            ItemTouchHelper.Callback callback = new SimpleItemTouchHelper();

            final ItemTouchHelper touchHelper = new ItemTouchHelper(callback);


           final GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                   /* if (MotionEventCompat.getActionMasked(e) == MotionEvent.ACTION_DOWN) {
                        View childView = rv.findChildViewUnder(e.getX(), e.getY());
                        if (childView != null) {
                            RecyclerView.ViewHolder viewHolder = rv.getChildViewHolder(childView);
                            if (viewHolder != null) {
                                touchHelper.startDrag(viewHolder);
                            }
                        }
                    }*/
                    Log.d("ItemFragment", "onSingleTapUp : " + e.toString());
                    return super.onSingleTapUp(e);
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    Log.d("ItemFragment", "onLongPress : " + e.toString());
                    super.onLongPress(e);
                }

               @Override
               public void onShowPress(MotionEvent e) {
                   super.onShowPress(e);
               }
           });


            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                 //   Log.d("ItemFragment", "onInterceptTouchEvent : " + e.toString());
                    gestureDetector.onTouchEvent(e);
                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                   // Log.d("ItemFragment", "onTouchEvent : " + e.toString());

                }

                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                }
            });
            touchHelper.attachToRecyclerView(recyclerView);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
