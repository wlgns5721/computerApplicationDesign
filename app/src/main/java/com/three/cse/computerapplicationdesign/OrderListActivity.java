package com.three.cse.computerapplicationdesign;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.EditTextPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;

import com.three.cse.computerapplicationdesign.adapters.OrderListAdapter;
import com.three.cse.computerapplicationdesign.requests.OrderInfoRequest;
import com.three.cse.computerapplicationdesign.response.OrderInfoResponse;
import com.three.cse.computerapplicationdesign.utils.APIClient;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListActivity extends AppCompatActivity {

    private OrderListAdapter mAdapter;

    Button searchStartDate;
    Button searchEndDate;
    Button orderListResultButton;

    String startData;
    String endData;

    int startYear, startMonth, startDate, lastYear, lastMonth, lastDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("주문 내역 조회");
        setContentView(R.layout.activity_order_list);

        searchStartDate = (Button) findViewById(R.id.search_start_date);
        searchEndDate = (Button) findViewById(R.id.search_end_date);

        final Calendar startCalendar = Calendar.getInstance();
        final Calendar lastCalendar = Calendar.getInstance();

        startYear = startCalendar.get(Calendar.YEAR);
        startMonth = startCalendar.get(Calendar.MONTH);
        startDate = startCalendar.get(Calendar.DAY_OF_MONTH);

        lastYear = lastCalendar.get(Calendar.YEAR);
        lastMonth = lastCalendar.get(Calendar.MONTH);
        lastDate = lastCalendar.get(Calendar.DAY_OF_MONTH);

        searchStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OrderListActivity.this, dateSetListener1, startYear, startMonth, startDate).show();
                updateStartDate(startYear, startMonth, startDate);
            }
        });

        searchEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OrderListActivity.this, dateSetListener2, lastYear, lastMonth, lastDate).show();
                updateEndDate(lastYear, lastMonth, lastDate);
                endData = lastYear + "-" + (lastMonth+1) + "-" + lastDate;
            }
        });

        RecyclerView orderListRecyclerView = (RecyclerView) findViewById(R.id.order_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        orderListRecyclerView.setHasFixedSize(true);
        orderListRecyclerView.setLayoutManager(layoutManager);
        orderListRecyclerView.setAdapter(mAdapter);

        orderListResultButton = (Button) findViewById(R.id.order_list_result);
        orderListResultButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestOrderInfo(startData, endData);
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            startYear = year;
            startMonth = month + 1;
            startDate = day;
            updateStartDate(startYear, startMonth, startDate);
        }
    };

    private DatePickerDialog.OnDateSetListener dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            lastYear = year;
            lastMonth = month + 1;
            lastDate = day;
            updateEndDate(lastYear, lastMonth, lastDate);
        }
    };

    private void updateStartDate(int year, int month, int date) {
        startData = year + "-" + (month) + "-" + date;
        searchStartDate.setText(startData);
    }

    private void updateEndDate(int year, int month, int date) {
        endData = year + "-" + (month) + "-" + date;
        searchEndDate.setText(endData);
    }


    public void requestOrderInfo(String startData, String endData) {
        APIClient.getInstance().create(OrderInfoRequest.class).orderInfoProduct(startData, endData)
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
                        Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다.", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
