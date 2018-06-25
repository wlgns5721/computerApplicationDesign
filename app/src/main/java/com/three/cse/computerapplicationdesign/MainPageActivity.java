package com.three.cse.computerapplicationdesign;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.activities.BaseActivity;
import com.three.cse.computerapplicationdesign.activities.SaleProductListActivity;
import com.three.cse.computerapplicationdesign.activities.SearchResultActivity;
import com.three.cse.computerapplicationdesign.adapters.MainPageItemListAdapter;
import com.three.cse.computerapplicationdesign.requests.DownloadImageRequest;
import com.three.cse.computerapplicationdesign.requests.MainPageItemRequest;
import com.three.cse.computerapplicationdesign.response.MainPageItemResponse;
import com.three.cse.computerapplicationdesign.response.MainPageItem;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private MainPageItemListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Button searchButton = (Button)findViewById(R.id.main_page_search_button);
        final EditText edtSearchKeyword = (EditText)findViewById(R.id.main_page_keyword);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchResultIntent = new Intent(MainPageActivity.this, SearchResultActivity.class);
                searchResultIntent.putExtra("searchString",edtSearchKeyword.getText().toString());
                startActivity(searchResultIntent);
            }
        });

        mAdapter = new MainPageItemListAdapter();

        searchProduct();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_main_search);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(!BaseActivity.isSeller)
            navigationView.getMenu().findItem(R.id.nav_seller_page).setVisible(false);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_order_list) {
            Intent orderListIntent = new Intent(MainPageActivity.this, OrderListActivity.class);
            startActivity(orderListIntent);
        } else if (id == R.id.nav_product_qna) {
            Intent productQnaIntent = new Intent(MainPageActivity.this, ProductQnaActivity.class);
            startActivity(productQnaIntent);
        } else if (id == R.id.nav_seller_page) {
            Intent orderListIntent = new Intent(MainPageActivity.this, SaleProductListActivity.class);
            startActivity(orderListIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void searchProduct() {
        mAdapter.clearMainPageItemInfo();
        APIClient.getInstance().create(MainPageItemRequest.class).searchProduct()
                .enqueue(new Callback<MainPageItemResponse>() {
                    @Override
                    public void onResponse(Call<MainPageItemResponse> call, Response<MainPageItemResponse> response) {
                        if(!response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다.", Toast.LENGTH_LONG).show();
                            return;
                        }
                        List<List<String>> MainPageItemList = response.body().getMessage();
                        final int size = MainPageItemList.size();
                        if(size==0)
                            Toast.makeText(getApplicationContext(),"검색 결과가 없습니다.",Toast.LENGTH_LONG).show();
                        final int[] count = {0};
                        for(int i=0; i<size; i++) {
                            final int index = i;
                            final MainPageItem mainPageItem = new MainPageItem(MainPageItemList.get(i));
                            APIClient.getInstance().create(DownloadImageRequest.class).downloadImageRequest(mainPageItem.getProductid())
                                    .enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            try {
                                                byte[] bytes = response.body().bytes();
                                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                                mAdapter.addImage(bitmap);
                                                mAdapter.addMainPageItemInfo(mainPageItem);

                                                count[0]++;
                                                if(count[0]==size) {
                                                    mAdapter.notifyDataSetChanged();
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                                        }
                                    });

                        }

                    }

                    @Override
                    public void onFailure(Call<MainPageItemResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
