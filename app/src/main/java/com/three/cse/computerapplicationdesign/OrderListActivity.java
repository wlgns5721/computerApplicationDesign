
package com.three.cse.computerapplicationdesign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.three.cse.computerapplicationdesign.adapters.OrderListAdapter;
import com.three.cse.computerapplicationdesign.requests.OrderInfoRequest;
import com.three.cse.computerapplicationdesign.response.OrderInfoResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity {

    private OrderListAdapter mAdapter;
    

    Button orderListResultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        RecyclerView orderListRecyclerView = (RecyclerView) findViewById(R.id.order_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        orderListRecyclerView.setHasFixedSize(true);
        orderListRecyclerView.setLayoutManager(layoutManager);
        orderListRecyclerView.setAdapter(mAdapter);

        orderListResultButton = (Button) findViewById(R.id.order_list_result);
        orderListResultButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestOrderInfo();
            }
        });
    }


    public void requestOrderInfo() {
        APIClient.getInstance().create(OrderInfoRequest.class).orderInfoProduct()
                .enqueue(new Callback<OrderInfoResponse>() {
                    @Override
                    public void onResponse(Call<OrderInfoResponse> call, Response<OrderInfoResponse> response) {
                        //여기에 처리할 부분을 넣어주세요
                        if (response.isSuccessful()) {
                            mAdapter.clearOrderList();
                            for (int i = 0; i < response.body().getMessage().size(); i++)
                                mAdapter.getOrderList(response.body().getMessage().get(i));
                            mAdapter.notifyDataSetChanged();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<OrderInfoResponse> call, Throwable t) {
                        //실패했을 때의 부분
                    }
                });
    }
}

