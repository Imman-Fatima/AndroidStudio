package com.example.imman;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin, btnGoogle;
    private TextView tvForgotPassword, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        setClickListeners();
    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoogle = findViewById(R.id.btnGoogle);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
    }

    private void setClickListeners() {
        btnLogin.setOnClickListener(v -> attemptLogin());

        btnGoogle.setOnClickListener(v -> signInWithGoogle());

        tvForgotPassword.setOnClickListener(v -> {
            // Agar ForgotPasswordActivity banai hai to yeh line uncomment karein
            // startActivity(new Intent(this, ForgotPasswordActivity.class));
            Toast.makeText(this, "Forgot password screen abhi nahi bani", Toast.LENGTH_SHORT).show();
        });

        tvSignUp.setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class)));
    }

    private void attemptLogin() {
        String email = etEmail.getText() == null ? "" : etEmail.getText().toString().trim();
        String password = etPassword.getText() == null ? "" : etPassword.getText().toString();

        // Validation
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email zaroori hai");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Sahi email likhein");
            etEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password zaroori hai");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password kam az kam 6 characters ka ho");
            etPassword.requestFocus();
            return;
        }

        loginUser(email, password);
    }

    private void loginUser(String email, String password) {
        setLoading(true);

        // TODO: Yahan apna backend login lagayein.
        // Firebase use kar rahe hain to:
        //
        // FirebaseAuth.getInstance()
        // .signInWithEmailAndPassword(email, password)
        // .addOnCompleteListener(task -> {
        // setLoading(false);
        // if (task.isSuccessful()) {
        // goToHome();
        // } else {
        // Toast.makeText(this, "Login fail: "
        // + task.getException().getMessage(), Toast.LENGTH_LONG).show();
        // }
        // });

        // Abhi sirf testing ke liye:
        setLoading(false);
        Toast.makeText(this, "Backend abhi connect nahi hai", Toast.LENGTH_SHORT).show();
    }

    private void signInWithGoogle() {
        // TODO: Google Sign-In yahan lagayein (Firebase + Google Sign-In setup chahiye)
        Toast.makeText(this, "Google sign-in abhi setup nahi hai", Toast.LENGTH_SHORT).show();
    }

    private void goToHome() {
        // MainActivity ko apni main screen ke naam se badal dein
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void setLoading(boolean loading) {
        btnLogin.setEnabled(!loading);
        btnGoogle.setEnabled(!loading);
        btnLogin.setText(loading ? "Please wait..." : "LOGIN");
    }
}



