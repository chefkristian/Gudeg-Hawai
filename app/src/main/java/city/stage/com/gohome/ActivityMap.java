package city.stage.com.gohome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by indomegabyte on 18/03/16.
 */
public class ActivityMap extends AppCompatActivity {

public String text;

    public Boolean isHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isHome = getIntent().getBooleanExtra("isHome",true);
        setContentView(R.layout.activity_map);


    }
    public void balikan(){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("text", text);
        setResult(Activity.RESULT_OK, returnIntent);

        finish();
    }

//    public void balikan2(){
//        Intent returnIntent1 = new Intent();
//        returnIntent1.putExtra("pppppppp",textoffice);
//        setResult(Activity.RESULT_OK, returnIntent1);
//        finish();
//
//    }


}
