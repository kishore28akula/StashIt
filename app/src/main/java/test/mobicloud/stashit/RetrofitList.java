package test.mobicloud.stashit;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static test.mobicloud.stashit.R.id.map;

public class RetrofitList extends FragmentActivity implements OnMapReadyCallback {

    private RecyclerView recyclerView;
//    private ArrayList<Locationdetailpojo> data_info;
    private LocationAdapter adapter;
    List<Locationdetailpojo> data_one=new ArrayList<>();
    private ProgressDialog pDialog;

    private String TAG = RetrofitList.class.getSimpleName();
    GoogleMap mMap;

    ArrayList<Double> latiinformation = new ArrayList<Double>();
    ArrayList<Double> longiinformation = new ArrayList<Double>();

    ArrayList<String> currentstatusinfo = new ArrayList<String>();
    ArrayList<String> stationnameinfo = new ArrayList<String>();

    ArrayList<Double> latiinformation1 = new ArrayList<Double>();
    ArrayList<Double> longiinformation1 = new ArrayList<Double>();
    int postioninfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrofit_list);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.recyler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
//        loadJSON();
        new GetLocationinfo().execute();
    }



    /*  private void loadJSON() {

          Gson gson = new GsonBuilder().setLenient().create();

          OkHttpClient client = new OkHttpClient();

          Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.learn2crack.com/android/jsonandroid/")
                  .client(client)
                  .addConverterFactory(GsonConverterFactory.create(gson)).build();
          RequestInterface request = retrofit.create(RequestInterface.class);
          Call<JSONResponse> call = request.getJSON();
          call.enqueue(new Callback<JSONResponse>() {
              @Override
              public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                  JSONResponse jsonResponse = response.body();
  //                data_info = new ArrayList<>(Arrays.asList(jsonResponse.getLocation()));
  //                adapter = new LocationAdapter(data_info);
  //                recyclerView.setAdapter(adapter);
              }

              @Override
              public void onFailure(Call<JSONResponse> call, Throwable t) {
                  Log.d("Error",t.getMessage());
              }
          });

      }
  */
    private class GetLocationinfo extends AsyncTask<Object, Object, List<Locationdetailpojo>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(RetrofitList.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected List<Locationdetailpojo> doInBackground(Object... params) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("http://www.test4mcis.com/map/map_info.php?state=ca");

            Log.e("st", "Response from url: " + jsonStr);

            if (jsonStr != null)
            {
                try{
                    JSONObject jsonobject = new JSONObject(jsonStr);
                    JSONArray jsonarray = jsonobject.getJSONArray("posts");
                    for (int i=0 ; i<jsonarray.length();i++)
                    {
                        JSONObject c = jsonarray.getJSONObject(i);

                        Locationdetailpojo loc = new Locationdetailpojo();
                        loc.fuel_type = c.getString("fuel_type");
                        loc.station_name = c.getString("station_name");
                        loc.id = c.getString("id");
                        loc.street_address = c.getString("street_ address");
                        loc.city = c.getString("city");
                        loc.state = c.getString("state");
                        loc.zip = c.getString("zip");
                        loc.station_phone = c.getString("station_phone");
                        loc.latitude = c.getString("latitude");
                        loc.longitude = c.getString("longitude");
                        loc.current_status = c.getString("current_status");

                        data_one.add(loc);
                      Log.i("testingi",""+data_one);

                        //for map information.....

//                        double  latitude=Double.parseDouble(c.getString("latitude"));
//                        double longitude= Double.parseDouble(c.getString("longitude"));
//
//                        latiinformation.add(latitude);
//                        longiinformation.add(longitude);
//                        currentstatusinfo.add(c.getString("current_status"));
//                        stationnameinfo.add(c.getString("station_name"));

                    }
//                  Recylerdetails();
                }catch (final JSONException e){
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            }
            return data_one;
        }

        @Override
        protected void onPostExecute(List<Locationdetailpojo> result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();
            Log.i("response",""+result);
            adapter = new LocationAdapter((ArrayList<Locationdetailpojo>) result);
             recyclerView.setAdapter(adapter);
//            latiinformation1 = latiinformation;
//            longiinformation1 = longiinformation;

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(map);
            mapFragment.getMapAsync(RetrofitList.this);

        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int a = latiinformation1.size();
//        Log.i("finalarra",""+"\n"+longiinformation1+"\n"+currentstatusinfo+"\n"+data_one);
        Log.i("finalarra",""+"\n"+data_one);
        // Add a marker in Sydney and move the camera
        for (int i=0;i<data_one.size();i++){
            double  latitude=Double.parseDouble(data_one.get(i).getLatitude());
            double longitude= Double.parseDouble(data_one.get(i).getLongitude());
            LatLng sydney = new LatLng(latitude, longitude);
//            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

            if (data_one.get(i).getCurrent_status().equals("operational")){

                postioninfo = currentstatusinfo.indexOf(i);
                Log.i("indexinfo",""+postioninfo);
                mMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title(data_one.get(i).getStation_name())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.greenbubble1))
                        .draggable(true));

            }else if (data_one.get(i).getCurrent_status().equals("Under construction")){

                mMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title(data_one.get(i).getStation_name())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.greybubble1))
                        .draggable(true));

            }else if (data_one.get(i).getCurrent_status().equals("Proposed")){


                mMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title(data_one.get(i).getStation_name())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.yellowbubble1))
                        .draggable(true));

            }else if (data_one.get(i).getCurrent_status().equals("maintenance")){

                mMap.addMarker(new MarkerOptions()
                        .position(sydney)
                        .title(data_one.get(i).getStation_name())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.redbubble1))
                        .draggable(true));

            }
            CameraUpdate zoom=CameraUpdateFactory.zoomTo(10);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(zoom);

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    LatLng PlaceLocation = marker.getPosition();
                    Log.i("markingpos",""+postioninfo);

                    int selected_marker=-1;
                    for (int i = 0; i < data_one.size(); i++) {
                        if (PlaceLocation.latitude == Double.parseDouble(data_one
                                .get(i).getLatitude())
                                && PlaceLocation.longitude == Double
                                .parseDouble(data_one.get(i).getLongitude())) {
                            selected_marker=i;

                        }
                    }
                    Log.i("markingindex",""+selected_marker);
//                    recyclerView.scrollToPosition(selected_marker);
                    recyclerView.smoothScrollToPosition(selected_marker);
                    return false;
                }
            });
        }

    }
}
