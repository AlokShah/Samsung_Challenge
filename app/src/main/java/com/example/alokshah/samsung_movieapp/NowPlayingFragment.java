package com.example.alokshah.samsung_movieapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by alokshah on 3/17/18.
 */

public class NowPlayingFragment extends Fragment implements DataInterface, GenreListInerface {

    private HashMap<String, String> hmap = new HashMap<>();

    private String mData;
    private String mGenre;
    private GetDataTask asyncTask =new GetDataTask();
    private GetGenreList genrelist =new GetGenreList();
    private ListView listView;
    private List<RowItem> rowItems;
    private JSONObject mJson;
    private JSONObject mGenreObject;
    private View view;

    String genreURL = "https://api.themoviedb.org/3/genre/movie/list?api_key=aac62d20d9f166da034a9053f3da7e99&language=en-US";

    String nowplayingURL = "https://api.themoviedb.org/3/movie/now_playing?api_key=aac62d20d9f166da034a9053f3da7e99&language=en-US&page=1";

    public static final Integer[] images = { R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        asyncTask.delegate = this;

        genrelist.delegate = this;

        genrelist.execute(genreURL);

        asyncTask.execute(nowplayingURL);







        view = inflater.inflate(R.layout.now_playing, container, false);



       return view;
    }



    @Override
    public void processFinish(String output) {
        mData = output;
        try {
            mJson = new JSONObject(mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setList(mJson);
    }

    private void setList(JSONObject mJson)
    {
        rowItems = new ArrayList<RowItem>();
        JSONArray jArr = null;
        try {
            jArr = mJson.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i < jArr.length(); i++) {

            try {
                JSONObject obj = jArr.getJSONObject(i);


                String genre = "";

                for(int k = 0; k < obj.getJSONArray("genre_ids").length(); k++)
                {
                    genre = genre + " " + hmap.get(obj.getJSONArray("genre_ids").getString(k));
                }

                        RowItem item = new RowItem(images[0], obj.getString("title"),obj.getString("popularity"), genre);
                        rowItems.add(item);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }




        listView = (ListView)view.findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(getActivity(),
                R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void finish(String output) {
   mGenre = output;
        try {
            mGenreObject = new JSONObject(mGenre);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jArr = null;
        try {
            jArr = mGenreObject.getJSONArray("genres");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i=0; i < jArr.length(); i++) {

            try {
                JSONObject obj = jArr.getJSONObject(i);

               hmap.put(obj.getString("id"), obj.getString("name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
