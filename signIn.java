package tk.snap52.snap_it;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by asus on 1/20/2018.
 */

public class signIn extends Activity{
    databaseHelper helper= new databaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
    }
    public void onSigninClick(View v) {
        if (v.getId() == R.id.btsignin) {
            EditText email2 = (EditText) findViewById(R.id.etemail1);
            EditText pass2 = (EditText) findViewById(R.id.etpass1);
            String email2str = email2.getText().toString();
            String pass2str = pass2.getText().toString();
            String password = helper.searchpass(email2str);
           if (pass2str.equals(password)) {
                Intent i = new Intent(signIn.this, dashboard.class);
                startActivity(i);
            } else {
                Toast passmsg = Toast.makeText(signIn.this, "Email address or password incorrect", Toast.LENGTH_SHORT);
                passmsg.show();

            }
        }

    }
}
