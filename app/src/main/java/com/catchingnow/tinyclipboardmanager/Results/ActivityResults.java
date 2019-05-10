package com.catchingnow.tinyclipboardmanager.Results;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.catchingnow.tinyclipboardmanager.MyActionBarActivity;
import com.catchingnow.tinyclipboardmanager.R;

public class ActivityResults extends Activity {
    private ImageView ivAlma;
    private ImageView ivSegu;
    private TextView tvBateriaDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_results);
        super.onCreate(savedInstanceState);
        ivAlma = (ImageView) findViewById(R.id.alma);
        ivAlma.setOnClickListener(mCorkyListener);
        ivSegu = (ImageView) findViewById(R.id.segu);
        ivSegu.setOnClickListener(seguridadActivity);
        tvBateriaDos = (TextView) findViewById(R.id.tvBateriaBajaDos);
        BroadcastReceiver bateriaReciever = new PowerConnectionReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                int level = -1;
                if (currentLevel >=0 && scale > 0) {
                    level = (currentLevel * 100) / scale;
                    if (level <= 10) {
                        tvBateriaDos.setText(R.string.texto_uno + " " + R.string.texto_dos);
                    } else {
                        tvBateriaDos.setText(R.string.texto_tres);
                    }
                }

            }
        };
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(bateriaReciever,batteryFilter);


    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener mCorkyListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent (v.getContext(), ActivityAlmacenamiento.class);
            startActivityForResult(intent, 0);
            //startActivity(new Intent(this, ActivityAlmacenamiento.class));
        }
    };

    private View.OnClickListener seguridadActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(),ActivitySeguridad.class);
            startActivity(intent);
        }
    };

}
