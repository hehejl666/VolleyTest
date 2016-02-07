package com.example.heiton.volleytest;

import android.support.v4.app.Fragment;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 17930 on 2016/2/7.
 */
public class Photos500px {
    private int Current_Page;
    private int Total_Pages;
    private int Total_Items;
    private String jsondata=null;

    public int getCurrent_Page() {
        return Current_Page;
    }

    public void setCurrent_Page(int current_Page) {
        Current_Page = current_Page;
    }

    public int getTotal_Pages() {
        return Total_Pages;
    }

    public void setTotal_Pages(int total_Pages) {
        Total_Pages = total_Pages;
    }

    public int getTotal_Items() {
        return Total_Items;
    }

    public void setTotal_Items(int total_Items) {
        Total_Items = total_Items;
    }

    public List<Photos> getPhotosList() {

        return photosList;
    }

    public void setPhotosList(List<Photos> photosList) {
        this.photosList = photosList;
    }

    public Photos500px(String jsondata) {
        this.jsondata = jsondata;
        Fromjson(jsondata);
    }
    private void Fromjson(String data){

        String josndata=data;
        try {
            photosList=new ArrayList<>();
            JSONObject json=new JSONObject(josndata);
            setCurrent_Page(json.getInt("current_page"));
            setTotal_Items(json.getInt("total_items"));
            setTotal_Pages(json.getInt("total_pages"));
            JSONArray jsonArray=json.getJSONArray("photos");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                Photos p=new Photos();
//                p.setCamera(jsonObject.getString("camera"));
//                p.setCreated_at(jsonObject.getString("created_at"));
//                p.setDescription(jsonObject.getString("description"));
                p.setImageUrl(jsonObject.getString("image_url"));
                p.setName(jsonObject.getString("name"));
//                p.setId(jsonObject.getInt("id"));
//                p.setUser_id(jsonObject.getInt("user_id"));
//                p.setTanke_at(jsonObject.getString("tanke_at"));
                photosList.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private List<Photos> photosList;

    class Photos{
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCamera() {
            return Camera;
        }

        public void setCamera(String camera) {
            Camera = camera;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getTanke_at() {
            return tanke_at;
        }

        public void setTanke_at(String tanke_at) {
            this.tanke_at = tanke_at;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private int id;
        private int user_id;
        private String name;
        private String description;
        private String Camera;
        private String created_at;
        private String tanke_at;
        private String imageUrl;
    }





}
