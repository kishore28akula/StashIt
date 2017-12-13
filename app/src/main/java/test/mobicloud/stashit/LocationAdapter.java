package test.mobicloud.stashit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by venkat kishore on 07-12-2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private ArrayList<Locationdetailpojo> android;

    public String strlati,strlongi;
    public LocationAdapter(ArrayList<Locationdetailpojo> android) {
        this.android = android;
    }

    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LocationAdapter.ViewHolder viewHolder, int i) {

        strlati = android.get(i).getLatitude();
        strlongi = android.get(i).getLongitude();
        viewHolder.tv_name.setText(android.get(i).getStation_name());
        viewHolder.tv_status.setText(android.get(i).getCurrent_status());
        viewHolder.tv_lati.setText(android.get(i).getLatitude());
        viewHolder.tv_longi.setText(android.get(i).getLongitude());
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_status,tv_lati,tv_longi;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_status = (TextView)view.findViewById(R.id.tv_status);
            tv_lati = (TextView)view.findViewById(R.id.tv_latitute);
            tv_longi = (TextView)view.findViewById(R.id.tv_longi);

        }
    }

}