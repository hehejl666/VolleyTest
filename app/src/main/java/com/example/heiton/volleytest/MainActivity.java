package com.example.heiton.volleytest;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.etsy.android.grid.StaggeredGridView;
import com.example.heiton.volleytest.Injection.IMain;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMain {


    @Bind(R.id.idstaggergridView)
    StaggeredGridView idstaggergridView;
    @Bind(R.id.id_swipe)
    SwipeRefreshLayout idSwipe;
    @Bind(R.id.id_text_pagenow)
    TextView idTextPagenow;
    @Bind(R.id.id_text_allpage)
    TextView idTextAllpage;
    @Bind(R.id.id_text_alltotal)
    TextView idTextAlltotal;


    private Photos500px photos500px;
    private String url = "https://api.500px.com/v1/photos?feature=popular";
    private String appKey = "consumer_key=cznYzJkfHuxFRZZ2IkmBXAYHMf2l7FB2wAgCmGGx";
    private String page = "page=";
    private int times = 1;
    private IMain iMain;
    private Map<String, String> postdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iMain=this;
        url = url + "&" + appKey + "&" + page + times;
        volleyDoget(url);


        idSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                times++;
                url = url + "&" + appKey + "&" + page + times;

                volleyDoget(url);
            }
        });

        idstaggergridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("name",photos500px.getPhotosList().get(i).getName());

            }
        });
    }

    private void volleyDoget(String url) {
        RequestQueue req = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
                photos500px = new Photos500px(response);
                if (photos500px.getPhotosList().size() > 0) {
                    Log.e("Json解析", "true");



                    myadapter myadapter = new myadapter(MainActivity.this, photos500px);
                    iMain.SetPagerAdapter(myadapter);
                    iMain.SetPageText(photos500px.getCurrent_Page() + "");
                    iMain.SetAllPageText(photos500px.getTotal_Pages() + "");
                    iMain.SetALLtotalText(photos500px.getTotal_Items() + "");

                    idSwipe.setRefreshing(false);

                } else {
                    Log.e("Json解析", "false");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volleyError", error.getMessage());
            }
        });
        req.add(sr);

    }

    private void volleyDoPost(String url, Map<String, String> postdata) {

    }

    @Override
    public void SetPageText(String data) {

        idTextPagenow.setText("当前页面："+data);
    }

    @Override
    public void SetAllPageText(String data) {

        idTextAllpage.setText("共有："+data+"页面");
    }

    @Override
    public void SetALLtotalText(String data) {

        idTextAlltotal.setText("共有:"+data+"条数据");
    }

    @Override
    public void SetPagerAdapter(BaseAdapter adapter) {

        idstaggergridView.setAdapter(adapter);
    }
}
