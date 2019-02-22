package okedroid.com.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private TextView txtData;
    private Button btnGetall;
    private String allKickBoxers;
    private EditText edtName,edtPunchSpeed,edtPunchPower,edtKickSpeed,edtKickPower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSave = findViewById(R.id.btnsave);
        btnSave.setOnClickListener(SignUpActivity.this);
        edtName = findViewById(R.id.edtName);
        edtPunchSpeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower  = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtPunchPower = findViewById(R.id.edtKickPower);
        edtKickPower = findViewById(R.id.edtKickPower);
        txtData = findViewById(R.id.txtData);
        btnGetall = findViewById(R.id.btnGetall);
        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("eBkqL0SVy2", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object!=null && e==null) {
                            txtData.setText(object.get("name").toString());
                        }
                        }
                });
            }
        });
        btnGetall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null)
                        {
                            if(objects.size()>0)
                            {
                                for(ParseObject kickBoxer : objects)
                                {
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUpActivity.this,allKickBoxers ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                            }
                            else
                            {
                                FancyToast.makeText(SignUpActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name",edtName.getText().toString());
        kickBoxer.put("punch_speed",Integer.parseInt(edtPunchSpeed.getText().toString()));
        kickBoxer.put("punch_power",Integer.parseInt(edtPunchPower.getText().toString()));
        kickBoxer.put("kick_speed",Integer.parseInt(edtKickSpeed.getText().toString()));
        kickBoxer.put("kick_power",Integer.parseInt(edtKickPower.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null)
                {
                    FancyToast.makeText(SignUpActivity.this,kickBoxer.get("name") + " is saved to server" ,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                }
                else
                {
                    FancyToast.makeText(SignUpActivity.this, e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                }
            }
        });
    }
}
