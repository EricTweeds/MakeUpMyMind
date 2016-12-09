package tweedle.eric.makeupmymind;

import android.content.Intent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.seismic.ShakeDetector;
import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener{
    boolean ThreeOptions = false;
    boolean FourOptions = false;
    boolean questionAsked = false;
    boolean running = true;
    public static String winner = "";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreeOptions = false;
        running = true;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ShakeDetector sd = new ShakeDetector(this);
        sd.start(sensorManager);
        Button less = (Button) findViewById(R.id.addLess);
        less.setVisibility(View.INVISIBLE);
        myToolbar.setVisibility(View.VISIBLE);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater findMenuItems = getMenuInflater();
        findMenuItems.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void hearShake() {
        if (!questionAsked && running) {
            boolean badEntry = false;
            EditText Option1 = (EditText) findViewById(R.id.Option1);
            EditText Option2 = (EditText) findViewById(R.id.Option2);
            EditText Option3 = (EditText) findViewById(R.id.Option3);
            EditText Option4 = (EditText) findViewById(R.id.Option4);
            EditText Question = (EditText) findViewById(R.id.Question);
            DecisionMaker mDecision = new DecisionMaker();
            List<String> choices = new ArrayList<>();

            if (Option1.getText().toString().length() != 0) {
                if (Option1.getText().toString().equalsIgnoreCase("Kill Myself"))
                {badEntry = true;}
                choices.add(Option1.getText().toString());
            }
            if (Option2.getText().toString().length() != 0) {
                if (Option1.getText().toString().equalsIgnoreCase("Kill Myself"))
                {badEntry = true;}
                choices.add(Option2.getText().toString());
            }
            if (Option3.getText().toString().length() != 0) {
                if (Option1.getText().toString().equalsIgnoreCase("Kill Myself"))
                {badEntry = true;}
                choices.add(Option3.getText().toString());
            }
            if (Option4.getText().toString().length() != 0) {
                if (Option1.getText().toString().equalsIgnoreCase("Kill Myself"))
                {badEntry = true;}
                choices.add(Option4.getText().toString());
            }

            if (choices.size() < 2) {
                Toast.makeText(this, "Please enter a minimum of 2 options", Toast.LENGTH_SHORT).show();
            } else if (Question.getText().toString().length() == 0){
                Toast.makeText(this, "Please enter a question", Toast.LENGTH_SHORT).show();
            } else if (badEntry){
                Toast.makeText(this, "We Don't Condone Suicidal Thoughts", Toast.LENGTH_SHORT).show();
            }
            else {
                winner = mDecision.Simple(choices);
                //Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                questionAsked = true;
                Intent intent = new Intent(this, Results.class);
                startActivity(intent);
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (running){
            Toast.makeText(this, "Don't Touch That", Toast.LENGTH_SHORT).show();
        }
    }

    public void addOption(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText Option3 = (EditText) findViewById(R.id.Option3);
        EditText Option4 = (EditText) findViewById(R.id.Option4);
        Button less = (Button) findViewById(R.id.addLess);
        Button more = (Button) findViewById(R.id.addMore);
        if(ThreeOptions && !FourOptions){
            Option4.setVisibility(View.VISIBLE);
            FourOptions=true;
            more.setVisibility(View.INVISIBLE);
        }
        else if(!FourOptions){
            Option3.setVisibility(View.VISIBLE);
            ThreeOptions = true;
            less.setVisibility(View.VISIBLE);
        }
    }
    public void RemoveOption(View view){
        Intent intent = new Intent(this, MainActivity.class);
        EditText Option3 = (EditText) findViewById(R.id.Option3);
        EditText Option4 = (EditText) findViewById(R.id.Option4);
        Button less = (Button) findViewById(R.id.addLess);
        Button more = (Button) findViewById(R.id.addMore);
        if(ThreeOptions && !FourOptions){
            Option3.setVisibility(View.GONE);
            ThreeOptions=false;
            less.setVisibility(View.INVISIBLE);
        }
        else if(FourOptions){
            Option4.setVisibility(View.GONE);
            FourOptions = false;
            more.setVisibility(View.VISIBLE);
        }

    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
        questionAsked = false;
        running = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        running = false;
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
