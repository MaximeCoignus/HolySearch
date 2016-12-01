package holysearch.holysearch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void resetPassword (View view)
    {
        try
        {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        finally
        {
        }
    }
}
