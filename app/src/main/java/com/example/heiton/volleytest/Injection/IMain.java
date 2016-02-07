package com.example.heiton.volleytest.Injection;

import android.support.v4.view.PagerAdapter;
import android.widget.BaseAdapter;

/**
 * Created by 17930 on 2016/2/8.
 */
public interface IMain {
    void SetPageText(String data);
    void SetAllPageText(String data);
    void SetALLtotalText(String data);
    void SetPagerAdapter(BaseAdapter adapter);
}
