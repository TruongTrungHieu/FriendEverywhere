package com.fithou.friendeverywhere.activity;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.asynctask.CreateGroupAsyncTask;
import com.fithou.friendeverywhere.asynctask.GetUserMapAsyncTask;
import com.fithou.friendeverywhere.asynctask.UpdateCurrentLocationAsyncTask;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.GroupObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Callback;
import com.fithou.friendeverywhere.ultis.Constants;
import com.fithou.friendeverywhere.ultis.LocationService;
import com.fithou.friendeverywhere.ultis.MyLocation;
import com.fithou.friendeverywhere.ultis.StringSupport;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONObject;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener,
        LocationListener {

    private GoogleMap googleMap;
    private MapView mMapView;
    private View marker;
    private RelativeLayout view_btm;
    private Button btn_exit;
    private ArrayList<FriendObject> listFriend;
    private ImageView img_ava;
    private TextView tv_name, tv_about;
    private Button btn_call, btn_sms, btn_chat, btn_info;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    private double lat1, lng1;
    private FriendObject friend_map_obj;

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(provider);
        }
        MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
            @Override
            public void gotLocation(Location location) {
                lat1 = location.getLatitude();
                lng1 = location.getLongitude();
                Log.d("11111111111111111", lat1+"");

            }
        };
        MyLocation myLocation = new MyLocation(getActivity());
        myLocation.getLocation(getActivity(), locationResult);
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            lat = (double) (location.getLatitude());
            lng = (double) (location.getLongitude());
            Toast.makeText(getActivity(), lat + "", Toast.LENGTH_LONG).show();
            new UpdateCurrentLocationAsyncTask(getActivity()).setCallback(new Callback() {
                @Override
                public void onPreExecute() {

                }

                @Override
                public void onPostExecute(Object o) {
                    final JSONObject data = (JSONObject) o;
                    if (o != null) {
                        try {
                            Constants.savePreference(getActivity().getApplicationContext(), Constants.XML_LATITUDE, lat + "");
                            Constants.savePreference(getActivity().getApplicationContext(), Constants.XML_LONGTITUDE, lng + "");
                        } catch (Exception e) {
                            Log.d("login service", e.getMessage());
                        }
                    }
                }
            }).execute(Constants.getPreference(getActivity(), Constants.XML_USER_ID), lat + "", lng + "");
            mMapView.invalidate();
        } else {

        }

        mMapView.getMapAsync(this);

        UiSettings settings = mMapView.getMap().getUiSettings();
        settings.setMapToolbarEnabled(false);

        img_ava = (ImageView) v.findViewById(R.id.img_map_fragment);
        tv_name = (TextView) v.findViewById(R.id.txt_name_map_fragment);
        tv_about = (TextView) v.findViewById(R.id.txt_aboutme_map);
        btn_call = (Button) v.findViewById(R.id.btn_call_map);
        btn_sms = (Button) v.findViewById(R.id.btn_sms_map);
        btn_chat = (Button) v.findViewById(R.id.btn_chat_map);
        btn_info = (Button) v.findViewById(R.id.btn_info_map);

        btn_call.setOnClickListener(this);
        btn_sms.setOnClickListener(this);
        btn_chat.setOnClickListener(this);
        btn_info.setOnClickListener(this);

        view_btm = (RelativeLayout) v.findViewById(R.id.rl_view_map_fragment);
        btn_exit = (Button) v.findViewById(R.id.btn_exit_map_fragment);
        marker = ((LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_btm.animate().translationY(view_btm.getHeight());
            }
        });

        return v;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            }
        } else {
            googleMap.setMyLocationEnabled(true);
        }
        googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                new UpdateCurrentLocationAsyncTask(getActivity()).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        final JSONObject data = (JSONObject) o;
                        if (o != null) {
                            try {
                                Constants.savePreference(getActivity().getApplicationContext(), Constants.XML_LATITUDE, lat1 + "");
                                Constants.savePreference(getActivity().getApplicationContext(), Constants.XML_LONGTITUDE, lng1 + "");
                            } catch (Exception e) {
                                Log.d("login service", e.getMessage());
                            }
                        }
                    }
                }).execute(Constants.getPreference(getActivity(), Constants.XML_USER_ID), lat1 + "", lng1 + "");
                Toast.makeText(getActivity(), Constants.getPreference(getActivity(), Constants.XML_LATITUDE)
                        +"---"+Constants.getPreference(getActivity(),Constants.XML_LONGTITUDE), Toast.LENGTH_LONG).show();
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat1, lng1), 13));
                return false;
            }
        });
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                showViewByFriend(marker.getSnippet());
                view_btm.setVisibility(View.VISIBLE);
                view_btm.animate().translationY(0);

                return true;
            }
        });
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                view_btm.animate().translationY(view_btm.getHeight());
            }
        });

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.027020, 105.838166), 13));

        new GetUserMapAsyncTask(this.getActivity()).setCallback(new Callback<ArrayList<FriendObject>>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(ArrayList<FriendObject> list) {
                if (list != null) {
                    listFriend = new ArrayList<>();
                    listFriend = list;
                    reloaMarkerMap();
                }
            }
        }).execute(Constants.getPreference(this.getActivity(), Constants.XML_USER_ID));
    }

    private void reloaMarkerMap() {
        if (listFriend != null) {
            for (FriendObject f : listFriend) {
                if (f.getFriend_object().getLatitude() > 0 && f.getFriend_object().getLongtitude() > 0) {
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(f.getFriend_object().getLatitude(), f.getFriend_object().getLongtitude()))
                            .title(f.getFriend_object().getFullname())
                            .snippet(f.getFriend_pk())
                            .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this.getActivity(), marker))));
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    public void showViewByFriend(String friendID) {
        for (FriendObject f : listFriend) {
            if (f.getFriend_pk().equals(friendID)) {
                friend_map_obj = f;
                break;
            }
        }
        if (friend_map_obj != null) {
            if (StringSupport.isNullOrEmpty(friend_map_obj.getFriend_object().getFullname())) {
                tv_name.setText("");
            } else {
                tv_name.setText(friend_map_obj.getFriend_object().getFullname());
            }
            if (StringSupport.isNullOrEmpty(friend_map_obj.getFriend_object().getAbout_me())) {
                tv_about.setText("");
            } else {
                tv_about.setText(friend_map_obj.getFriend_object().getAbout_me());
            }

            if (friend_map_obj.getFriend_status() == Constants.FRIEND_STATUS_NONE || friend_map_obj.getFriend_status() == Constants.FRIEND_STATUS_REQUESTED) {
                btn_call.setVisibility(View.GONE);
                btn_sms.setVisibility(View.GONE);
                btn_chat.setVisibility(View.GONE);
            } else {
                btn_call.setVisibility(View.VISIBLE);
                btn_sms.setVisibility(View.VISIBLE);
                btn_chat.setVisibility(View.VISIBLE);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_call_map:
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                if (!StringSupport.isNullOrEmpty(friend_map_obj.getFriend_object().getPhone())) {
                    callIntent.setData(Uri.parse("tel:" + friend_map_obj.getFriend_object().getPhone()));
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    getActivity().startActivity(callIntent);
                }
                break;
            case R.id.btn_sms_map:
                if (!StringSupport.isNullOrEmpty(friend_map_obj.getFriend_object().getPhone())) {
                    Uri uri = Uri.parse("smsto:" + friend_map_obj.getFriend_object().getPhone());
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    getActivity().startActivity(it);
                }
                break;
            case R.id.btn_chat_map:
                new CreateGroupAsyncTask(this.getContext()).setCallback(new Callback() {
                    @Override
                    public void onPreExecute() {

                    }

                    @Override
                    public void onPostExecute(Object o) {
                        if (o != null) {
                            final JSONObject jsonObject = (JSONObject) o;
                            try {
                                GroupObject groupObject = GroupObject.parseJsonToObject(jsonObject);
                                Intent chat = new Intent(getActivity(), MessageActivity.class);
                                chat.putExtra("GROUP", groupObject);
                                getActivity().startActivity(chat);
                            } catch (Exception e) {
                                e.getMessage();
                                return;
                            }
                        }
                    }
                }).execute(friend_map_obj.getFriend_object().getFullname(), Constants.getPreference(this.getContext(), Constants.XML_USER_ID), friend_map_obj.getFriend_object().getUser_id());
                break;
            case R.id.btn_info_map:
                Intent info = new Intent(getActivity(), FriendProfileActivity.class);
                info.putExtra("FRIEND", friend_map_obj);
                getActivity().startActivity(info);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
