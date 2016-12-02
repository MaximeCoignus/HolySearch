package holysearch.holysearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void connexion (View view)
    {

        boolean connected = false;

        // Traitement java de connexion

        connected = true;

        if(connected){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
    }

    public void createAccount (View view)
    {
        try
        {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        }
        finally
        {
        }
    }


    public void resetPassword (View view)
    {
        try
        {
            Intent intent = new Intent(this, ResetPasswordActivity.class);
            startActivity(intent);
        }
        finally
        {
        }
    }

}
