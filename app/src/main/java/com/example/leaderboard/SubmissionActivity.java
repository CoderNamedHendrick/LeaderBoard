package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText githubLink;
    private Button submissionBtn;
    Toolbar toolbar;
    private Button mButton;
    private ImageView mCancel;
    private SubmitProjectService mService;
    private AlertDialog.Builder mBuilder;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        toolbar = findViewById(R.id.submit_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        firstName = findViewById(R.id.first_name_edt);
        lastName = findViewById(R.id.last_name_edt);
        email = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.project_link_edt);
        submissionBtn = findViewById(R.id.submission_btn);

        Button.OnClickListener submitBtnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService = RetrofitClientInstance.getRetrofitInstance().create(SubmitProjectService.class);
                mBuilder = new AlertDialog.Builder(SubmissionActivity.this);
                mInflater = SubmissionActivity.this.getLayoutInflater();
                final AlertDialog alertDialog = buildSubmitDialog(mBuilder, mInflater);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Alert Dialog", " user clicked yes.");
                        alertDialog.dismiss();
                        runSubmitService(mService);
                    }
                });
                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("Alert Dialog", " user clicked cancel.");
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        };

        submissionBtn.setOnClickListener(submitBtnListener);
    }

    private void runSubmitService(SubmitProjectService service) {
        Call<Void> call = service.submitProject(email.getText().toString(), firstName.getText().toString(),
                lastName.getText().toString(), githubLink.getText().toString());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    Log.d("SubmissionActivity", " post submitted to form " + response.body());
                    buildSuccessDialog(mBuilder, mInflater);
                }else {
                    buildFailureDialog(mBuilder, mInflater);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                buildFailureDialog(mBuilder, mInflater);
            }
        });
    }

    private AlertDialog buildSubmitDialog(AlertDialog.Builder builder, LayoutInflater inflater) {
        final AlertDialog dialog = (AlertDialog) builder.create();

        View dialogView = inflater.inflate(R.layout.confirm_dialog, null);
        dialog.setView(dialogView);
        mButton = (Button) dialogView.findViewById(R.id.yesBtn);
        mCancel = (ImageView) dialogView.findViewById(R.id.cancelBtn);

        return dialog;
    }

    private void buildSuccessDialog(AlertDialog.Builder builder, LayoutInflater inflater){
        final AlertDialog dialog = (AlertDialog) builder.create();

        View dialogView = inflater.inflate(R.layout.success_dialog, null);
        dialog.setView(dialogView);

        dialog.show();
    }

    private void buildFailureDialog(AlertDialog.Builder builder, LayoutInflater inflater){
        final AlertDialog dialog = (AlertDialog) builder.create();

        View dialogView = inflater.inflate(R.layout.failed_dialog, null);
        dialog.setView(dialogView);

        dialog.show();
    }
}