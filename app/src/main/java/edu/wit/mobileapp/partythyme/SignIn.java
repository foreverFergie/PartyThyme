package edu.wit.mobileapp.partythyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.support.v7.widget.CardView;
//import android.support.v7.widget.Toolbar;
//import android.text.Editable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ExpandableListView;
//import android.widget.ListView;
//import android.widget.Switch;
//import android.widget.TextView;
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.Scopes;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.common.api.Scope;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import android.support.annotation.NonNull;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AlertDialog;
//import java.util.ArrayList;
//import java.util.List;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    //private GoogleSignInClient signInClient;
//    private TextView signInStatus;
//    private TextView userName;
//    //private GoogleSignInAccount account;
//    public String account;
//    public String email;
//    public User[] users = new User[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        findViewById(R.id.continueToMain).setOnClickListener(this);

        DatabaseHelper db = new DatabaseHelper(this);
        try{
            db.createDatabase();
        }catch(Exception ignored){
            //Ignore problem. May occur again if user tries to add a plant
        }

//        signInStatus = findViewById(R.id.sign_in_status);
//        userName = findViewById(R.id.userName);
//
//        findViewById(R.id.sign_out_button).setOnClickListener(this);
//        findViewById(R.id.add_new_user).setOnClickListener(this);
//        findViewById(R.id.login).setOnClickListener(this);
//        findViewById(R.id.sign_in_button).setOnClickListener(this);
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestServerAuthCode(getString(R.string.server_client_id))
//                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
//                .requestEmail()
//                .build();
//
//        signInClient = GoogleSignIn.getClient(this, gso);

    }
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.add_new_user:
//                addNewUser();
//                break;
//            case R.id.login:
//                //signIn();
//                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
            case R.id.continueToMain:
                continueToMain();
                break;
        }

    }

    private void continueToMain() {
        Intent send = new Intent();
        send.setClass(SignIn.this, MainActivity.class);
        //Bundle bundle = new Bundle();
        //bundle.putString("user",account);
        //send.putExtras(bundle);
        startActivity(send);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //this.account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI();
//
//    }

//    private void updateUI() {
//
//        //Button mainPage = (Button)findViewById(R.id.continueToMain);
//        if (account != null) {
//            signInStatus.setText(getString(R.string.signed_in));
//            userName.setText(account);
//            //mainPage.setVisibility(View.VISIBLE);
//            findViewById(R.id.continueToMain).setVisibility(View.VISIBLE);
//            //findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//            findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.login).setVisibility(View.GONE);
//            findViewById(R.id.add_new_user).setVisibility(View.GONE);
//
////            mainPage.setOnClickListener(new View.OnClickListener(){
////                public void onClick(View v){
////                    Intent send = new Intent();
////                    send.setClass(SignIn.this,MainActivity.class);
////                    Bundle bundle= new Bundle();
////                    bundle.putString("email",account.getEmail());
////                    send.putExtras(bundle);
////                    startActivity(send);
////                }
////
////
////            });
//
//
//        } else {
//            signInStatus.setText(getString(R.string.signed_out));
//            userName.setText("");
//            findViewById(R.id.continueToMain).setVisibility(View.GONE);
//            //findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.sign_out_button).setVisibility(View.GONE);
//            findViewById(R.id.login).setVisibility(View.VISIBLE);
//            findViewById(R.id.add_new_user).setVisibility(View.VISIBLE);
//        }
//
//
//    }




//    private void addNewUser() {
//
//        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.fragment_add_user, null);
//
//        final EditText enterUser = (EditText) dialogView.findViewById(R.id.user_enter);
//        final EditText enterEmail = (EditText) dialogView.findViewById(R.id.email_enter);
//        Button create = (Button) dialogView.findViewById(R.id.create_user);
//
//        create.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Editable enteredEmail = enterEmail.getText();
//                Editable user = enterUser.getText();
//
//                account = user.toString();
//                email = enteredEmail.toString();
//
//                User userTemp = new User(account, email);
//                if (users[0] != null) {
//                    users[0] = userTemp;
//                } else if (users[1] != null) {
//                    users[1] = userTemp;
//                } else {
//                    //too many users
//                }
//                dialogBuilder.dismiss();
//                updateUI();
//
//            }
//
//
//        });
//
//        dialogBuilder.setView(dialogView);
//
//        dialogBuilder.show();
//
//    }

//    private void signIn() {
////        Intent signInIntent = signInClient.getSignInIntent();
////        startActivityForResult(signInIntent, RC_SIGN_IN);
//
//
//        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
//        LayoutInflater dialogInflater = this.getLayoutInflater();
//        View signInView = dialogInflater.inflate(R.layout.user_dialog, null);
//
////        UserAdapter adapter;
////        adapter=new UserAdapter(this,0,users);
////
////        ListView userList=(ListView)findViewById(R.id.users);
////        userList.setAdapter(adapter);
//
//        final TextView user1 = (TextView) findViewById(R.id.user1);
//        user1.setText(users[0].username);
//        TextView user2 = (TextView) findViewById(R.id.user2);
//        user2.setText(users[1].username);
//
//        CardView user1Card = (CardView) findViewById(R.id.userCard1);
//        CardView user2Card = (CardView) findViewById(R.id.userCard2);
//
//        user1Card.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                account=users[0].username;
//                email=users[0].email;
//
//                dialogBuilder.dismiss();
//                updateUI();
//
//            }
//        });
//
//
//        dialogBuilder.setView(signInView);
//        dialogBuilder.show();
//
//
//    }

//    private void signOut() {
////        signInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
////            @Override
////            public void onComplete(@NonNull Task<Void> task) {
////                account=null;
////                updateUI();
////            }
////        });
//        account = null;
//        email = null;
//        updateUI();
//
//    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }
//    }

//    private void handleSignInResult(Task<GoogleSignInAccount> complete) {
//
//        try {
//
//            this.account = complete.getResult(ApiException.class);
//            String idToken = account.getServerAuthCode();
//            updateUI();
//
//        } catch (ApiException e) {
//            Log.w(TAG,"handleSignInResult",e);
//            account=null;
//            updateUI();
//        }
//    }





}
