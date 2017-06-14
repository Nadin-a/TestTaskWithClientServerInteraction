package com.nadina.android.testtask;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nadina.android.testtask.database.UserContract;
import com.nadina.android.testtask.database.UserDbHelper;
import com.nadina.android.testtask.login.StartActivity;
import com.nadina.android.testtask.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity with table of users.
 * Show username and last login.
 */
public class UserActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindString(R.string.error_network) String error_network;
    private SQLiteDatabase mDb;
    Cursor cursor;
    Adapter mAdapter;
    List<UserModel> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ButterKnife.bind(this);

        users = new ArrayList<>();
        UserDbHelper noteDbHelper = new UserDbHelper(this);
        mDb = noteDbHelper.getWritableDatabase();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        cursor = getAllUsers();
        mAdapter = new Adapter(this, cursor);
        recyclerView.setAdapter(mAdapter);


        App.getServerApi().getData().enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(response.body()!=null) {
                    users.addAll(response.body());
                }

                for(UserModel u : users) {
                    mDb.insert(UserContract.UserEntry.TABLE_NAME, null, toContentValues(u));
                }
                mAdapter.swapCursor(getAllUsers());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(UserActivity.this, error_network, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Cursor getAllUsers() {
        return mDb.query(UserContract.UserEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

    }

    public static ContentValues toContentValues(@NonNull UserModel user) {
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.USERNAME_COLUMN, user.getUsername());
        values.put(UserContract.UserEntry.EMAIL_COLUMN, user.getEmail());
        values.put(UserContract.UserEntry.LAST_LOGIN_COLUMN, user.getLastLogin());
        values.put(UserContract.UserEntry.STATUS_COLUMN,user.getStatus());
        values.put(UserContract.UserEntry.FRIENDS_COLUMN,user.getFriends());
        values.put(UserContract.UserEntry.HANDS_PLAYED_COLUMN,user.getHandsPlayed());
        values.put(UserContract.UserEntry.HANDS_WON_COLUMN,user.getHandsWon());
        values.put(UserContract.UserEntry.BIGGEST_POT_WON_COLUMN,user.getBiggestPotWon());
        values.put(UserContract.UserEntry.SITN_GO_WINS_COLUMN,user.getSitNGoWins());
        values.put(UserContract.UserEntry.SITN_GO_LOSES_COLUMN,user.getSitNGoLoses());
        values.put(UserContract.UserEntry.COINS_COLUMN,user.getCoins());
        values.put(UserContract.UserEntry.LOCATION_COLUMN,user.getLocation());
        values.put(UserContract.UserEntry.BEST_HAND_COLUMN,user.getBestHand());
        values.put(UserContract.UserEntry.PHOTO_COLUMN,user.getPhoto());
        return values;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sigh_out:
                App.getServerApi().LogOut().enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Intent intent = new Intent(UserActivity.this, StartActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Toast.makeText(UserActivity.this, error_network, Toast.LENGTH_SHORT).show();

                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        users.clear();
        mDb.delete(UserContract.UserEntry.TABLE_NAME,null,null);
    }
}
