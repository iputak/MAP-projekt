package hr.vsite.map.taxivodstvo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {

    // Varijable
    EditText korisnickoIme, zaporka;
    Button prijava;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Kuke (Hooks)
        korisnickoIme = findViewById(R.id.et_korisnicko_ime);
        zaporka = findViewById(R.id.et_zaporka);
        prijava = findViewById(R.id.bt_prijava);

    }

    private Boolean potvrdaKorisnika() {
        String val = korisnickoIme.getText().toString();

        if (val.isEmpty()) {
            korisnickoIme.setError("Unesite korisničko ime");
            return false;
        } else {
            korisnickoIme.setError(null);
            return true;
        }

    }

    private Boolean potvrdaZaporke() {
        String val = zaporka.getText().toString();

        if (val.isEmpty()) {
            zaporka.setError("Unesite zaporku");
            return false;
        } else {
            zaporka.setError(null);
            return true;
        }
    }

    public void loginUser(View view) {

        // Validate Login Info
        if (!potvrdaKorisnika() | !potvrdaZaporke()) {
            return;
        } else{
            korisnik();
        }


    }

    private void korisnik() {
        final String unesenoKorisnickoIme = korisnickoIme.getText().toString().trim();
        final String unesenaZaporka = zaporka.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vozaci");

        Query provjeraKorisnika= reference.orderByChild("korisnickoIme").equalTo(unesenoKorisnickoIme);
        provjeraKorisnika.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    korisnickoIme.setError(null);

                    String zaporkaIzBaze = snapshot.child(unesenoKorisnickoIme).child("zaporka").getValue(String.class);

                    if(zaporkaIzBaze.equals(unesenaZaporka)){
                        korisnickoIme.setError(null);

                        String autentifikator = snapshot.child(unesenoKorisnickoIme).child("rangBool").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                        intent.putExtra("korime",unesenoKorisnickoIme);
                        intent.putExtra("autentifikator",autentifikator);

                        startActivity(intent);
                    }
                    else{
                        Toast
                                .makeText(getBaseContext(), "Kriva zaporka!", Toast.LENGTH_LONG)
                                .show();
                        zaporka.setText("");

                        zaporka.requestFocus();
                    }
                }
                else{
                    Toast
                            .makeText(getBaseContext(), "Ne postoji korisnik s tim imenom!", Toast.LENGTH_LONG)
                            .show();
                    korisnickoIme.setText("");
                    korisnickoIme.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
