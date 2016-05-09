package com.fithou.friendeverywhere.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fithou.friendeverywhere.R;
import com.fithou.friendeverywhere.object.FriendObject;
import com.fithou.friendeverywhere.object.UserObject;
import com.fithou.friendeverywhere.ultis.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap googleMap;
    private MapView mMapView;
    private View marker;
    private RelativeLayout view_btm;
    private Button btn_exit;
    private ArrayList<FriendObject> listFriend;
    private ImageView img_ava;
    private TextView tv_name, tv_about;
    private Button btn_call, btn_sms, btn_chat, btn_info;

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
//        googleMap.setMyLocationEnabled(true);
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
        fakeData();

        for (FriendObject f : listFriend) {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(f.getFriend_object().getLatitude(), f.getFriend_object().getLongtitude()))
                    .title(f.getFriend_object().getFullname())
                    .snippet(f.getFriend_pk())
                    .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this.getActivity(), marker))));
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(21.027020, 105.838166), 13));

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

    public void fakeData() {
        listFriend = new ArrayList<>();
        UserObject userObject = new UserObject();
        UserObject userObject1 = new UserObject();
        UserObject userObject2 = new UserObject();
        userObject.setLatitude((float) 21.027020);
        userObject.setLongtitude((float) 105.838166);
        userObject.setFullname("Hung Vu");

        userObject1.setLatitude((float) 21.048021);
        userObject1.setLongtitude((float) 105.859167);
        userObject1.setFullname("Hoa Hoang");

        userObject2.setLatitude((float) 21.038021);
        userObject2.setLongtitude((float) 105.849167);
        userObject2.setFullname("Long Nguyen");

        FriendObject friendObject = new FriendObject();
        friendObject.setFriend_object(userObject);
        friendObject.setFriend_pk("1462352174");
        friendObject.setFriend_status(0);

        FriendObject friendObject1 = new FriendObject();
        friendObject1.setFriend_object(userObject1);
        friendObject1.setFriend_pk("1462352175");
        friendObject1.setFriend_status(2);

        FriendObject friendObject2 = new FriendObject();
        friendObject2.setFriend_object(userObject2);
        friendObject2.setFriend_pk("1462362175");
        friendObject2.setFriend_status(2);

        listFriend.add(friendObject);
        listFriend.add(friendObject1);
        listFriend.add(friendObject2);
    }

    public void showViewByFriend(String friendID) {
        FriendObject friendObject = null;
        for (FriendObject f : listFriend) {
            if (f.getFriend_pk().equals(friendID)) {
                friendObject = f;
                break;
            }
        }
        if (friendObject != null) {
            tv_name.setText(friendObject.getFriend_object().getFullname());
            tv_about.setText(friendObject.getFriend_object().getAbout_me());
            if (friendObject.getFriend_status()==Constants.FRIEND_STATUS_NONE || friendObject.getFriend_status()==Constants.FRIEND_STATUS_REQUESTED){
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

                break;
            case R.id.btn_sms_map:

                break;
            case R.id.btn_chat_map:

                break;
            case R.id.btn_info_map:

                break;
            default:
                break;
        }
    }

}
