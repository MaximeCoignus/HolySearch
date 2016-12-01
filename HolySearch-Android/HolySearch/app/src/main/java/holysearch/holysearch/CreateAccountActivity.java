package holysearch.holysearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void creerCompte (View view)
    {
        boolean compteCreer = false;
        // Traitement java de création du compte

        compteCreer = true;

        if(compteCreer){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
    }
}
