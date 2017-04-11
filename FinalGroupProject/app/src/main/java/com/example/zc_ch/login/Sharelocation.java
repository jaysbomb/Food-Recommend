package com.example.zc_ch.login;

/**
 * Created by ZOU Zijie on 2017/4/9.
 */
import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;

public class Sharelocation extends Fragment implements View.OnClickListener {

    private final static int PERMISSION_REQUEST = 1;

    private Button gpsButton;
    private TextView progressTitle;
    private ProgressBar progressBar;
    private TextView detailsText;

    private Button shareButton;
    private Button copyButton;
    private Button viewButton;

    private LocationManager locManager;
    private Location lastLocation;

    private final LocationListener locListener = new LocationListener() {
        public void onLocationChanged(Location loc) {
            updateLocation(loc);
        }

        public void onProviderEnabled(String provider) {
            updateLocation();
        }

        public void onProviderDisabled(String provider) {
            updateLocation();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    // ----------------------------------------------------
    // Android Lifecycle
    // ----------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =inflater.inflate(R.layout.activity_sharelocation,container,false);

        // Display area
        gpsButton = (Button) view.findViewById(R.id.gpsButton);
        progressTitle = (TextView) view.findViewById(R.id.progressTitle);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        detailsText = (TextView) view.findViewById(R.id.detailsText);

        // Button area
        shareButton = (Button) view.findViewById(R.id.shareButton);
        copyButton = (Button) view.findViewById(R.id.copyButton);
        viewButton = (Button) view.findViewById(R.id.viewButton);

        //gpsButton.setOnClickListener(s);
        gpsButton.setOnClickListener(this);
        shareButton.setOnClickListener(this);
        copyButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);

        locManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            locManager.removeUpdates(locListener);
        } catch (SecurityException ignored) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startRequestingLocation();
        updateLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST &&
                grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startRequestingLocation();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
    }

    // ----------------------------------------------------
    // UI
    // ----------------------------------------------------
    private void updateLocation() {
        // Trigger a UI update without changing the location
        updateLocation(lastLocation);
    }

    private void updateLocation(Location location) {
        boolean locationEnabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean waitingForLocation = locationEnabled && !validLocation(location);
        boolean haveLocation = locationEnabled && !waitingForLocation;

        // Update display area
        gpsButton.setVisibility(locationEnabled ? View.GONE : View.VISIBLE);
        progressTitle.setVisibility(waitingForLocation ? View.VISIBLE : View.GONE);
        progressBar.setVisibility(waitingForLocation ? View.VISIBLE : View.GONE);
        detailsText.setVisibility(haveLocation ? View.VISIBLE : View.GONE);

        // Update buttons
        shareButton.setEnabled(haveLocation);
        copyButton.setEnabled(haveLocation);
        viewButton.setEnabled(haveLocation);

        if (haveLocation) {
            String newline = System.getProperty("line.separator");
            detailsText.setText(String.format("%s: %s%s%s: %s%s%s: %s",
                    getString(R.string.accuracy), getAccuracy(location), "m",newline,
                    getString(R.string.latitude), getLatitude(location),newline,
                    getString(R.string.longitude), getLongitude(location)));

            lastLocation = location;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shareButton:
                if (!validLocation(lastLocation)) {
                    return;
                }
                new AlertDialog.Builder(this.getActivity()).setTitle(R.string.choose_link)
                        .setCancelable(true)
                        .setItems(R.array.link_names, new onClickShareListener())
                        .create()
                        .show();
                break;
            case R.id.gpsButton:
                if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
                break;
            case R.id.copyButton:
                if (!validLocation(lastLocation)) {
                    return;
                }
                new AlertDialog.Builder(this.getActivity()).setTitle(R.string.choose_link)
                        .setCancelable(true)
                        .setItems(R.array.link_names, new onClickCopyListener())
                        .create()
                        .show();
                break;
            case R.id.viewButton:
                if (!validLocation(lastLocation)) {
                    return;
                }

                String uri = formatLocation(lastLocation, "geo:{0},{1}?q={0},{1}");

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(Intent.createChooser(intent, getString(R.string.view_location_via)));
                break;
        }
    }

    // ----------------------------------------------------
    // DialogInterface Listeners
    // ----------------------------------------------------
    private class onClickShareListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int i) {
            String link = formatLocation(lastLocation, getResources().getStringArray(R.array.link_templates)[i]);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, link);
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, getString(R.string.share_location_via)));
        }
    }

    private class onClickCopyListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int i) {
            String text = formatLocation(lastLocation, getResources().getStringArray(R.array.link_templates)[i]);

            Object clipService = getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                @SuppressWarnings("deprecation")
                android.text.ClipboardManager clipboard = (android.text.ClipboardManager) clipService;
                clipboard.setText(text);
            } else {
                ClipboardManager clipboard = (ClipboardManager) clipService;
                ClipData clip = ClipData.newPlainText(getString(R.string.app_name), text);
                clipboard.setPrimaryClip(clip);
            }

            Toast.makeText(getActivity().getApplicationContext(), R.string.copied, Toast.LENGTH_SHORT).show();
        }
    }



    // ----------------------------------------------------
    // Helper functions
    // ----------------------------------------------------
    private void startRequestingLocation() {
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
            return;
        }

        // GPS enabled and have permission - start requesting location updates
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
    }

    private boolean validLocation(Location location) {
        if (location == null) {
            return false;
        }

        // Location must be from less than 30 seconds ago to be considered valid
        if (Build.VERSION.SDK_INT < 17) {
            return System.currentTimeMillis() - location.getTime() < 30e3;
        } else {
            return SystemClock.elapsedRealtime() - location.getElapsedRealtimeNanos() < 30e9;
        }

    }

    private String getAccuracy(Location location) {
        float accuracy = location.getAccuracy();
        if (accuracy < 0.01) {
            return "?";
        } else if (accuracy > 99) {
            return "99+";
        } else {
            return String.format(Locale.US, "%2.0fm", accuracy);
        }
    }

    private String getLatitude(Location location) {
        return String.format(Locale.US, "%2.5f", location.getLatitude());
    }

    private String getLongitude(Location location) {
        return String.format(Locale.US, "%3.5f", location.getLongitude());
    }

    private String formatLocation(Location location, String format) {
        return MessageFormat.format(format,
                getLatitude(location), getLongitude(location));
    }
}
