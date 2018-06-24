package com.three.cse.computerapplicationdesign.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.R;
import com.three.cse.computerapplicationdesign.adapters.SearchAdapter;
import com.three.cse.computerapplicationdesign.requests.SearchRequest;
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
        progressBar.setVisibility(View.GONE);
        Button search_btn = (Button)findViewById(R.id.search_btn);
        final EditText searchString_text = (EditText)findViewById(R.id.searchString_text);
        mAdapter = new SearchAdapter();
        Intent intent = new Intent(this.getIntent());
        String searchString = intent.getStringExtra("searchString");
        if(searchString!=null) {
            searchString_text.setText(searchString);
            searchProduct(searchString_text);
        }
        search_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                searchProduct(searchString_text);
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv_search_result);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
//        mAdapter.notifyDataSetChanged();

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

    private void searchProduct(EditText searchString_text) {
        progressBar.setVisibility(View.VISIBLE);
        mAdapter.clearSearchInfo();
        APIClient.getInstance().create(SearchRequest.class).searchProduct(searchString_text.getText().toString())
                .enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        List<List<String>> searchResultList = response.body().getMessage();
                        if(searchResultList.size()==0)
                            Toast.makeText(getApplicationContext(),"검색 결과가 없습니다.",Toast.LENGTH_LONG).show();
                        for(int i=0; i<searchResultList.size(); i++) {
                            SearchResult searchResult = new SearchResult(searchResultList.get(i));
                            mAdapter.addSearchInfo(searchResult);
                        }
                        progressBar.setVisibility(View.GONE);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
