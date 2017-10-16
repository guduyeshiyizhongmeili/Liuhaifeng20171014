package com.bwie.test.liuhaifeng20171014.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bwie.test.liuhaifeng20171014.R;
import com.bwie.test.liuhaifeng20171014.model.bean.ListBean;
import com.bwie.test.liuhaifeng20171014.model.utils.Api;
import com.bwie.test.liuhaifeng20171014.view.adapter.ListAdapter;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SearchActivity extends AppCompatActivity {
    //商品列表页
    private TextView aaa;
    private int i=1;
    private Boolean isCheck=false;
    private String page=i+"";
    private String url= Api.SPLIST;
    private  String pscid;
    private ListAdapter adapter;
    private RadioButton horver;
    private RecyclerView listRecyclerView;
    private TextView sort_price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        horver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheck=!isCheck;
                horver.setChecked(!isCheck);
                if(horver.isChecked()) {
                    GridLayoutManager mLayoutManager = new GridLayoutManager(SearchActivity.this,2);
                    listRecyclerView.setLayoutManager(mLayoutManager);
                    listRecyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                }else{
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(SearchActivity.this);
                    listRecyclerView.setLayoutManager(mLayoutManager);
                    listRecyclerView.setHasFixedSize(true);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(SearchActivity.this);
        listRecyclerView.setLayoutManager(mLayoutManager);
        listRecyclerView.setHasFixedSize(true);


        getdata();
    }
    public void getdata(){
        RequestParams params=new RequestParams(url);

        params.addBodyParameter("page",page);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if(result!=null){
                    Gson gson=new Gson();
                    final ListBean listBean = gson.fromJson(result, ListBean.class);
                    adapter=new ListAdapter(listBean.getData(),SearchActivity.this);
                    listRecyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {

                        }
                    });
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }
    public void initView(){
        listRecyclerView= (RecyclerView) findViewById(R.id.list_RecyclerView);
        horver= (RadioButton) findViewById(R.id.horver);
    }
}
