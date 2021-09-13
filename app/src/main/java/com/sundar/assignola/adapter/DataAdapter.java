package com.sundar.assignola.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sundar.assignola.R;
import com.sundar.assignola.classes.HelperClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private Activity activity;
    private JSONArray dataList;
    private LayoutInflater inflater;

    public DataAdapter(Activity activity, JSONArray dataList) {
        this.activity = activity;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataAdapter.ViewHolder(inflater.inflate(R.layout.data_adapter_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        try {

            final JSONObject current = dataList.getJSONObject(position);
            holder.txtBookAuthorName.setText(current.getString("xingshaocheng"));
            holder.txtBookName.setText(current.getString("architect-awesome"));
            HelperClass.setBannerImage(current.getString("avatar"), holder.profile_image, activity);
            holder.txtdescription.setText(current.getString("description"));
            holder.txtlanguage.setText(current.getString("language"));

            holder.layout_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (holder.layout_item.getVisibility() == View.VISIBLE){
                        holder.layout_two.setVisibility(View.GONE);
                    }else {
                        holder.layout_two.setVisibility(View.VISIBLE);
                    }

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return dataList.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_image;
        LinearLayout layout_item, layout_two;
        TextView txtBookAuthorName, txtBookName, txtdescription, txtlanguage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            txtBookAuthorName = itemView.findViewById(R.id.txtBookAuthorName);
            txtBookName = itemView.findViewById(R.id.txtBookName);
            txtdescription = itemView.findViewById(R.id.txtdescription);
            txtlanguage = itemView.findViewById(R.id.txtlanguage);
            layout_item = itemView.findViewById(R.id.layout_item);
            layout_two = itemView.findViewById(R.id.layout_two);


        }
    }
}
