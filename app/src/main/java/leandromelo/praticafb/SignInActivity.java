package leandromelo.praticafb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText edEmail, edPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edEmail = findViewById(R.id.edit_email);
        edPassword = findViewById(R.id.edit_password);

    }

    public void buttonSignInClick(View view) {
        String login = edEmail.getText().toString();
        String passwd = edPassword.getText().toString();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(login, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String msg = task.isSuccessful() ? "SIGN IN OK!":
                                "SIGN IN ERROR!";
                        if(task.isSuccessful()){
                            abrirTelaPrincipal();
                        }
                        Toast.makeText(SignInActivity.this, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void abrirTelaCadastro(View view){
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity( intent );
    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity( intent );
    }

}