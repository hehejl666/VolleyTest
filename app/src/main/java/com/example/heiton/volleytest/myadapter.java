package com.example.heiton.volleytest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 17930 on 2016/2/7.
 */
public class myadapter extends BaseAdapter {

    private Photos500px photos500px;

    public myadapter(Context mcontext, Photos500px photos500px) {
        this.mcontext = mcontext;
        this.photos500px = photos500px;
    }

    private Context mcontext;

    @Override
    public int getCount() {

        return photos500px.getPhotosList().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(mcontext).inflate(R.layout.item,null,false);
        SimpleDraweeView simp= (SimpleDraweeView) view.findViewById(R.id.item_image);
        Uri uri= Uri.parse(photos500px.getPhotosList().get(i).getImageUrl());
        simp.setImageURI(uri);
        return view;
    }
}
