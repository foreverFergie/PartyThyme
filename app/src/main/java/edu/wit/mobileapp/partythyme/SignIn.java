package edu.wit.mobileapp.partythyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    private GoogleSignInClient signInClient;
    private TextView signInStatus;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        signInStatus = findViewById(R.id.sign_in_status);

        findViewById(R.id.sign_in_button).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        signInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

    }

    private void updateUI(GoogleSignInAccount account) {

        final GoogleSignInAccount accounSend = account;

        if (account != null) {
            signInStatus.setText(getString(R.string.signed_in, account.getDisplayName()));

            Button mainPage = (Button)findViewById(R.id.continueToMain);

            mainPage.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent send = new Intent();
                    send.setClass(SignIn.this,MainActivity.class);
                    Bundle bundle= new Bundle();
                    bundle.putString("email",accounSend.getEmail());
                    send.putExtras(bundle);
                    startActivity(send);
                }


            });



        } else {
            signInStatus.setText(getString(R.string.signed_out));
        }


    }

    private void signIn() {
        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> complete) {

        try {

            GoogleSignInAccount account = complete.getResult(ApiException.class);
            updateUI(account);

        } catch (ApiException e) {
            updateUI(null);
        }
    }


    public void onClick(View v) {
        switch(v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;

        }

    }


}
