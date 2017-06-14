package com.nadina.android.testtask;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nadina.android.testtask.database.UserContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nadina on 27.05.2017.
 * Adapter for recyclerView
 */

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Cursor mCursor;
    private Context mContext;


    public Adapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String username = mCursor.getString(mCursor.getColumnIndex(UserContract.UserEntry.USERNAME_COLUMN));
        String last_login = mCursor.getString(mCursor.getColumnIndex(UserContract.UserEntry.LAST_LOGIN_COLUMN));

        holder.usernameTV.setText(username);
        holder.lastLoginTV.setText(last_login);

    }

    @Override
    public int getItemCount() {
        if(mCursor == null)
        {
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (mCursor != null) {
            this.notifyDataSetChanged();
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.usernameTV) TextView usernameTV;
        @BindView(R.id.last_loginTV) TextView lastLoginTV;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
