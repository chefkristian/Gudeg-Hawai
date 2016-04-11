package city.stage.com.gohome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import city.stage.com.gohome.Util.MapFragment;
import city.stage.com.gohome.Util.MapGeo;

/**
 * Created by indomegabyte on 18/03/16.
 */
public class ActivityInit extends AppCompatActivity implements View.OnClickListener {

    Button button_current_home, button_set_map_home, button_current_office, button_set_map_office, button_go;
    TextView text_home, text_office;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        SharedPreferences prefs = getSharedPreferences("lokasi", MODE_PRIVATE);
//        String restoredText = prefs.getString("text", null);
        String a = prefs.getString("lokasi home", null);//"No name defined" is the default value.
        String b = prefs.getString("lokasi office", null); //0 is the default value.


        if (a != null && b != null ) {
            Intent intent=new Intent(this,ActiivityResult.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

        button_current_home = (Button) findViewById(R.id.button_current_home);
        button_set_map_home = (Button) findViewById(R.id.button_set_map_home);
        button_current_office = (Button) findViewById(R.id.button_current_office);
        button_set_map_office = (Button) findViewById(R.id.button_set_map_office);
        button_go = (Button) findViewById(R.id.button_go);
        text_home = (TextView) findViewById(R.id.text_home);
        text_office = (TextView) findViewById(R.id.text_office);

        button_current_home.setOnClickListener(this);
        button_set_map_home.setOnClickListener(this);
        button_current_office.setOnClickListener(this);
        button_set_map_office.setOnClickListener(this);
        button_go.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_current_home) {
            MapGeo mg = new MapGeo(this);
            text_home.setText("Home : " + mg.getDistrictCity());
            Toast.makeText(this, mg.getAddress(), Toast.LENGTH_SHORT).show();
        }
        if (view.getId() == R.id.button_current_office) {
            MapGeo mg = new MapGeo(this);
            text_office.setText("Office : " + mg.getDistrictCity());
            Toast.makeText(this, mg.getAddress(), Toast.LENGTH_SHORT).show();

        }
        if (view.getId() == R.id.button_set_map_home) {

            Intent i = new Intent(this, ActivityMap.class);
            i.putExtra("isHome", true);
            startActivityForResult(i, 1);
        }
        if (view.getId() == R.id.button_set_map_office) {

            Intent i = new Intent(this, ActivityMap.class);
            i.putExtra("isHome", false);
            startActivityForResult(i, 2);
        }
        if (view.getId() == R.id.button_go) {
            String a = text_home.getText().toString();
            String b = text_office.getText().toString();
//
            SharedPreferences.Editor editor = getSharedPreferences("lokasi", MODE_PRIVATE).edit();
            editor.putString("lokasi home",a);
            editor.putString("lokasi office", b);
            editor.commit();
            text_home.setText(String.valueOf(a));
            text_office.setText(String.valueOf(b));


            Intent i = new Intent(this, ActiivityResult.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);



//            i.putExtra("loaksi home", a);
//            i.putExtra("lokasi office", b);



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String text = data.getStringExtra("text");

                text_home.setText(text);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                String text = data.getStringExtra("text");
                text_office.setText(text);

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult
}
