package com.base.yun.myproject.helper;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.base.yun.myproject.MyItemRecyclerViewAdapter;

import static android.content.ContentValues.TAG;

/**
 * Created by YoungHyup on 2017-06-15.
 */

public class SimpleItemTouchHelper extends ItemTouchHelper.Callback {

    private float mOriginalElevation = 0.0f;
    private boolean isElevation = false;
    private float mDragElevation;


    public SimpleItemTouchHelper(float dragElevation, float originalElevation){
        mDragElevation = dragElevation;
        mOriginalElevation = originalElevation;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled(); //true
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled(); //true
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlag = 0;
            return makeMovementFlags(dragFlag, swipeFlag);
        }

        return 0;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //different item type, return false.
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

        if (recyclerView.getAdapter() instanceof MyItemRecyclerViewAdapter) {
            ((MyItemRecyclerViewAdapter) recyclerView.getAdapter()).onMoveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        switch (actionState) {
            case ItemTouchHelper.ACTION_STATE_IDLE:
                break;
            case ItemTouchHelper.ACTION_STATE_DRAG:
                //TODO low Api support
                if (isCurrentlyActive && !isElevation) {
                    ViewCompat.setElevation(viewHolder.itemView, mDragElevation);
                    isElevation = true;
                }
                break;
            case ItemTouchHelper.ACTION_STATE_SWIPE:
                break;
            default:
                break;
        }
    }

    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.d("onChildDraw", actionState + " / " + isCurrentlyActive);

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (isElevation) {
            ViewCompat.setElevation(viewHolder.itemView, mOriginalElevation);
            isElevation = false;
        }
    }
}
