package com.three.cse.computerapplicationdesign.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.adapters.SearchAdapter;
import com.three.cse.computerapplicationdesign.requests.SearchRequest;
import com.three.cse.computerapplicationdesign.response.OrderInfo;
import com.three.cse.computerapplicationdesign.response.SearchResponse;
import com.three.cse.computerapplicationdesign.response.SearchResult;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultActivity extends BaseActivity {
    private SearchAdapter mAdapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        Button search_btn = (Button)findViewById(R.id.search_btn);
        final EditText searchString_text = (EditText)findViewById(R.id.searchString_text);

        Intent intent = new Intent(this.getIntent());
        String searchString = intent.getStringExtra("searchString");
        searchString_text.setText(searchString);

        search_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressBar.setVisibility(View.VISIBLE);
                APIClient.getInstance().create(SearchRequest.class).searchProduct(searchString_text.getText().toString())
                        .enqueue(new Callback<SearchResponse>() {
                            @Override
                            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                                List<SearchResult> searchInfoList = response.body().getSearchResult();
                                for(int i=0; i<searchInfoList.size(); i++)
                                    mAdapter.addSearchInfo(searchInfoList.get(i));
                                progressBar.setVisibility(View.GONE);
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<SearchResponse> call, Throwable t) {

                            }
                        });
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_product_list);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

//        int img[] = {
//                R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,
//                R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,
//        };
//
//        MyAdapter adapter = new MyAdapter (
//                getApplicationContext(),
//                R.layout.row,
//                img);
//
//        GridView gv = (GridView)findViewById(R.id.gridView1);
//        gv.setAdapter(adapter);
//
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                //tv.setText("position : " + position);
//                Intent intent = new Intent(SearchResultActivity.this, ProductInfoActivity.class);
//                intent.putExtra("itemID", position); // item id pass
//                startActivity(intent);
//            }
//        });
    }
}
