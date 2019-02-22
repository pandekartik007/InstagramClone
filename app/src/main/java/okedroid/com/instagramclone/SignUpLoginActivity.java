package okedroid.com.instagramclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {
    private EditText edtUserName, edtPassword, edtUsernameLogin,getEdtPasswordLogin;
    private Button btnSingup, btnLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
        edtUserName = findViewById(R.id.edtUserName);
        edtUsernameLogin = findViewById(R.id.edtUsernameLogin);
        edtPassword = findViewById(R.id.edtPassword);
        getEdtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSingup = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username") + " is Signed up succesfully" ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        }
                        else
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(edtUsernameLogin.getText().toString(), getEdtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null && e==null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username") + " is logged in succesfully" ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }
                        else
                        {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
            }
        });
    }
}
