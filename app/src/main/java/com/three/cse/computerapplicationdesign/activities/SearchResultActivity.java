package com.three.cse.computerapplicationdesign.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.three.cse.computerapplicationdesign.R;

public class SearchResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        Button search_btn = (Button)findViewById(R.id.search_btn);
        EditText searchString_text = (EditText)findViewById(R.id.searchString_text);

        Intent intent = new Intent(this.getIntent());
        String searchString = intent.getStringExtra("searchString");
        searchString_text.setText(searchString);

        search_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(SearchResultActivity.this, SearchResultActivity.class);
                intent.putExtra("searchString", String.valueOf(searchString_text.getText()));
                startActivity(intent);
            }
        });

        int img[] = {
                R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,
                R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,R.drawable.c,R.drawable.e,R.drawable.j,R.drawable.q,
        };

        MyAdapter adapter = new MyAdapter (
                getApplicationContext(),
                R.layout.row,
                img);

        GridView gv = (GridView)findViewById(R.id.gridView1);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //tv.setText("position : " + position);
                Intent intent = new Intent(SearchResultActivity.this, ProductInfoActivity.class);
                intent.putExtra("itemID", position); // item id pass
                startActivity(intent);
            }
        });
    }
}

class MyAdapter extends BaseAdapter {
    Context context;
    int layout;
    int img[];
    LayoutInflater inf;

    public MyAdapter(Context context, int layout, int[] img) {
        this.context = context;
        this.layout = layout;
        this.img = img;
        inf = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inf.inflate(layout, null);
        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
        iv.setImageResource(img[position]);

        return convertView;
    }
}