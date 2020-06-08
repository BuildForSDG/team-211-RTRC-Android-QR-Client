package com.andela.buildsdgs.rtrc.revcollector.ui.main.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andela.buildsdgs.rtrc.revcollector.MainActivity;
import com.andela.buildsdgs.rtrc.revcollector.R;
import com.andela.buildsdgs.rtrc.revcollector.models.User;
import com.andela.buildsdgs.rtrc.revcollector.models.UserDetail;
import com.andela.buildsdgs.rtrc.revcollector.services.RTRCService;
import com.andela.buildsdgs.rtrc.revcollector.services.ServiceUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private View parent_view;
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        parent_view = findViewById(android.R.id.content);
        usernameEditText = findViewById(R.id.edt_login_username);
        passwordEditText = findViewById(R.id.edt_login_password);
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(v -> {
            if (usernameEditText.getText().toString().trim().isEmpty()) {
                Snackbar.make(parent_view, "Email field must not be empty", Snackbar.LENGTH_SHORT).show();
            } else if (passwordEditText.getText().toString().trim().isEmpty()) {
                Snackbar.make(parent_view, "Password field must not be empty", Snackbar.LENGTH_SHORT).show();
            } else {

                String userEmail = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                RTRCService rtrcService = ServiceUtil.buildService(RTRCService.class);
                System.out.println("Login Details : Email : " + userEmail + "\n Password : " + password);

                Call<UserDetail> userLoginCall = rtrcService.loginUser(new User(userEmail, password));
                userLoginCall.enqueue(new Callback<UserDetail>() {

                    @Override
                    public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {

                        if (response.code() == 200) {
                            System.out.println(" debuggin starts 2....");
                            System.out.println("Login Details ::: " + response.body().toString());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                System.out.println("Error Occurred ...." + response.errorBody().toString());
                                Snackbar.make(parent_view, jObjError.toString(), Snackbar.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Snackbar.make(parent_view, "Failed; Reason : " + e.toString(), Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UserDetail> call, Throwable t) {
                        System.out.println(" debuggin starts 3....");
                        System.out.println("Error Occurred ::: " + t.toString());
                    }
                });
            }
        });
    }

}

