package leandromelo.praticafb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.text.BreakIterator;

public class SignUpActivity extends AppCompatActivity {

    //private EditText edEmail = findViewById(R.id.edit_email);
    //private EditText edPassword = findViewById(R.id.edit_password);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    
    public void buttonSignUpClick(View view) {

        final EditText edEmail = findViewById(R.id.edit_email);
        final EditText edPassword = findViewById(R.id.edit_password);

        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String msg = task.isSuccessful() ? "SIGN UP OK!":
                                "SIGN UP ERROR!";
                        Toast.makeText(SignUpActivity.this, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}