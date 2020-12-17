package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        Button.OnClickListener submitBtnListener = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SubmitProjectService service = RetrofitClientInstance.getRetrofitInstance().create(SubmitProjectService.class);
                Call<Void> call = service.submitProject(email.getText().toString(), firstName.getText().toString(),
                        lastName.getText().toString(), githubLink.getText().toString());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Log.d("SubmissionActivity", " post submitted to form " + response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        });

        submissionBtn.setOnClickListener(submitBtnListener);
    }
}