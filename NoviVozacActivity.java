package hr.vsite.map.taxivodstvo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoviVozacActivity extends AppCompatActivity {

    // Varijable
    Button prekid, spremi;
    EditText nIme, nPrezime, nUlica, nKBr, nPostBr, nMjesto, nBrMob, nEmail, nKorisnickoIme, nZaporka;
    ToggleButton nSpol, nRang;
    String rangBool;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novi_vozac);

        // --- Kuke (Hooks) ---
        // Podatci o novom vozaču/ici (New driver data)
        nIme = findViewById(R.id.et_ime_vozaca);
        nPrezime = findViewById(R.id.et_prezimeVozaca);
        nUlica = findViewById(R.id.et_ulica);
        nKBr = findViewById(R.id.et_kucni_broj);
        nPostBr = findViewById(R.id.et_postbr);
        nMjesto = findViewById(R.id.et_mjesto);
        nBrMob = findViewById(R.id.et_brojMobitela);
        nEmail = findViewById(R.id.et_mail);
        nKorisnickoIme = findViewById(R.id.et_korisnicko_ime);
        nZaporka = findViewById(R.id.et_zaporka);

        // Gumbi (Buttons)

        prekid = findViewById(R.id.bt_prekid);
        spremi = findViewById(R.id.bt_spremi);

        // Spol
        nSpol = (ToggleButton) findViewById(R.id.toggleButtonSpol);

        // Rang
        nRang = (ToggleButton) findViewById(R.id.toggleButtonRang);

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Vozaci");

                // Svi uneseni podatci
                String ime = nIme.getText().toString();
                String prezime = nPrezime.getText().toString();
                String spol = nSpol.getText().toString();
                String ulica = nUlica.getText().toString();

                String kBr = nKBr.getText().toString();
                String postBr = nPostBr.getText().toString();
                String mjesto = nMjesto.getText().toString();
                String brMob = nBrMob.getText().toString();
                String email = nEmail.getText().toString();
                String korisnickoIme = nKorisnickoIme.getText().toString();
                String zaporka = nZaporka.getText().toString();
                String rang = nRang.getText().toString();

                if (rang.equals("Voditelj")){
                    rangBool = "1";
                } else {
                    rangBool = "2";
                }

                VozacHelperClass helperClass = new VozacHelperClass(ime, prezime, spol, ulica, kBr, postBr, mjesto, brMob, email, korisnickoIme, zaporka, rangBool);

                reference.child(korisnickoIme).setValue(helperClass);
                reference.child(korisnickoIme).child("Smjene").setValue("14");
                Toast
                        .makeText(getBaseContext(), "Korisnik uspješno unesen!", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
        });

        prekid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
