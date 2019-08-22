package matc89.exercicio1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView displayedMessage;
    private Button bCumprimentar;
    private EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCumprimentar = (Button)findViewById(R.id.btnCumprimentar);
        displayedMessage = (TextView)findViewById(R.id.labelMensagem);
        input = (EditText)findViewById(R.id.editNome);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void greet(View v) {
        String typed = input.getText().toString();
        if (typed.equals("Manoel")) typed = "Magnata";
        else if (typed.equals("Eduardo")) typed = "Philipe";
        displayedMessage.setText("Alô, " + typed + '!');
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("savedText", displayedMessage.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String savedText = savedInstanceState.getString("savedText");
        displayedMessage.setText(savedText);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("texto", input.getText().toString());
        editor.commit();
    }
}
