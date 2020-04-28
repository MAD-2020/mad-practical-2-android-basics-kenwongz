package sg.edu.np.WhackAMole;

import androidx.appcompat.app.AppCompatActivity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button RightButton;
    private Button MiddleButton;
    private Button LeftButton;
    private TextView scoretext;
    private int Score = 0;
    private static final String TAG = "ButtonActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RightButton = (Button) findViewById(R.id.RightButton);
        MiddleButton = (Button) findViewById(R.id.MiddleButton);
        LeftButton = (Button) findViewById(R.id.LeftButton);
        scoretext = (TextView) findViewById(R.id.scoretext);

        Log.v(TAG, "Finished Pre-Initialisation!");
    }

    @Override
    protected void onStart(){
        super.onStart();
        setNewMole();
        Log.v(TAG, "Starting GUI!");
    }

    public void setNewMole()
    {
        Random ran = new Random();
        int randomLocation = ran.nextInt(3);
        Button[] allbutton = {RightButton, MiddleButton, LeftButton};
        Button cmole = allbutton[randomLocation];
        for (Button b : allbutton)
        {
            if (b == cmole)
            {
                b.setText("*");
            }
            else
            {
                b.setText("O");
            }
        }
    }

    public boolean checkmole(String s)
    {
        if (s == "*")
        {
            return true;
        }
        return false;
    }

    public void UpdateScore()
    {
        String count = String.valueOf(Score);
        scoretext.setText(count);
    }

    public void whenClicked(View v)
    {
        switch(v.getId())
        {
            case R.id.RightButton:
                Log.v(TAG, "Right button clicked");
                break;

            case R.id.LeftButton:
                Log.v(TAG, "Left button clicked");
                break;

            case R.id.MiddleButton:
                Log.v(TAG, "Middle button clicked");
        }

        //downcast v to button
        Button button = (Button) v;
        if (checkmole((String) button.getText().toString()))
        {
            Score++;
            Log.v(TAG, "Hit, score added!");
        }
        else
        {
            Score--;
            Log.v(TAG, "Missed, score deducted!");
        }
        setNewMole();
        UpdateScore();
    }
}