package city.stage.com.gohome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by indomegabyte on 22/03/16.
 */
public class ActiivityResult extends AppCompatActivity {

    TextView text_jarak, text_waktu;
    String x, y, namaUser;
    SharedPreferences prefs, prefs2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        text_jarak = (TextView) findViewById(R.id.text_jarak);
        text_waktu = (TextView) findViewById(R.id.text_waktu);

        prefs = getSharedPreferences("lokasi", MODE_PRIVATE);
        x = prefs.getString("lokasi home", "No name defined");
        y = prefs.getString("lokasi office", "No name defined");
//        x = this.getIntent().getExtras().getString("loaksi home");
//        y = this.getIntent().getExtras().getString("lokasi office");

        loadmap(y, x);

        prefs2 = getSharedPreferences("id_user", MODE_PRIVATE);
//        String restoredText = prefs.getString("text", null);
        namaUser = prefs2.getString("nama user", "No name defined");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_action, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.edit_location:
                prefs.edit().remove("lokasi home").commit();
                prefs.edit().remove("lokasi office").commit();

                Intent intent = new Intent(ActiivityResult.this, ActivityInit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);


                // search action
                return true;
            case R.id.logout:
                prefs2.edit().remove("nama user").commit();
                finish();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
//
//                Intent i = new Intent(ActiivityResult.this, MainActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(i);
                return true;
//
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Launching new activity
     */
//    private void LocationFound() {
//        Intent i = new Intent(MainActivity.this, LocationFound.class);
//        startActivity(i);
//    }
    private void loadmap(String origin, String destination) {

        final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metrics&origins=" + origin + "&destinations=" + destination + "&key=AIzaSyAPlPQNm-2SoSwNFwG5tTccPTBLlxmFuEU";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject jsonObject) {
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                        // Toast.makeText(getApplicationContext(), jsonObject.optString("picture"), Toast.LENGTH_SHORT).show();
                        Log.i("aplikasi-mobile.com", jsonObject.toString());
                        Log.d("wewew", jsonObject.toString());
//                        mJSONAdapter.masukin(jsonObject);
                        Log.d("url", url);


                        JSONArray row = jsonObject.optJSONArray("rows");
                        try {
                            JSONObject x = row.getJSONObject(0);
                            JSONArray element = x.optJSONArray("elements");
                            JSONObject y = element.getJSONObject(0);

                            JSONObject distance = y.optJSONObject("distance");
                            String jarak = distance.optString("text");

                            Log.d("text", jarak);

                            JSONObject duration = y.optJSONObject("duration");
                            String waktu = duration.optString("text");

                            Log.d("text", waktu);


                            text_jarak.setText(jarak);
                            text_waktu.setText(waktu);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


//                        JSONObject element = row.optJSONObject("elements");
//
//                        JSONArray elemen = row.optJSONArray("elements");


///intent
                        //  jsonObject.toString();

                    }

                    @Override
                    public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                        // 15. Dismiss the ProgressDialog
//                        mDialog.dismiss();

                        //16. Keluarkan toast
                        Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();

                        //17. Print Log
                        Log.e("aplikasi-mobile.com", statusCode + " " + throwable.getMessage());
                    }

                });
    }


}
