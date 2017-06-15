package com.base.yun.myproject.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.base.yun.myproject.MyItemRecyclerViewAdapter;

/**
 * Created by YoungHyup on 2017-06-15.
 */

public class SimpleItemTouchHelper extends ItemTouchHelper.Callback {

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
        if (recyclerView.getAdapter() instanceof MyItemRecyclerViewAdapter) {
            ((MyItemRecyclerViewAdapter) recyclerView.getAdapter()).onMoveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
