package hr.vsite.map.taxivodstvo;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class VozacFragment extends Fragment {

    // Baza
    FirebaseDatabase rootNode;
    private DatabaseReference reference, vozac;

    String data;
    TextView ime, prezime, spol, ulica, kucniBr, postBr, mjesto, mobitel, email, korIme, rang;
    Button povratak;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_vozac, container, false);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Vozaci");

        // TODO: Korisničko ime pritisnuto na prvom fragmentu

        Bundle bundle = this.getArguments();
        data = bundle.getString("key");


        // -- Povezivanje s layout-om --
        // Osobni podatci
        ime = view.findViewById(R.id.tv_ime_vozaca);
        prezime = view.findViewById(R.id.tv_prezimeVozaca);
        spol = view.findViewById(R.id.tv_spol);
        // Adresa
        ulica = view.findViewById(R.id.tv_ulica);
        kucniBr = view.findViewById(R.id.tv_kucni_broj);
        postBr = view.findViewById(R.id.tv_postbr);
        mjesto = view.findViewById(R.id.tv_mjesto);
        // Kontakt
        mobitel = view.findViewById(R.id.tv_brojMobitela);
        email = view.findViewById(R.id.tv_mail);
        // Korisnički podatci
        korIme = view.findViewById(R.id.tv_korisnicko_ime);
        rang = view.findViewById(R.id.tv_rang);

        //
        povratak=view.findViewById(R.id.bt_prekid);

        // Putanja do korisnika u bazi
        vozac = reference.child(data);
        vozac.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Iz baze
                String imeBaza = snapshot.child("ime").getValue().toString();
                String prezimeBaza = snapshot.child("prezime").getValue().toString();
                String spolBaza = snapshot.child("spol").getValue().toString();
                String ulicaBaza = snapshot.child("ulica").getValue().toString();
                String kucniBrBaza = snapshot.child("kBr").getValue().toString();
                String postBrBaza = snapshot.child("postBr").getValue().toString();
                String mjestoBaza = snapshot.child("mjesto").getValue().toString();
                String brMobBaza = snapshot.child("brMob").getValue().toString();
                String emailBaza = snapshot.child("email").getValue().toString();
                String korImeBaza = snapshot.child("korisnickoIme").getValue().toString();
                String rangBaza = snapshot.child("rangBool").getValue().toString();

                ime.setText(imeBaza);
                prezime.setText(prezimeBaza);
                spol.setText(spolBaza);
                ulica.setText(ulicaBaza);
                kucniBr.setText(kucniBrBaza);
                postBr .setText(postBrBaza);
                mjesto.setText(mjestoBaza);
                mobitel.setText(brMobBaza);
                email.setText(emailBaza);
                korIme.setText(korImeBaza);

                if (rangBaza.equals("1")){
                    rang.setText("Voditelj");
                } else {
                    rang.setText("Vozač");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        povratak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                Toast.makeText(getActivity(),"Test123", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
            }
        });

        return view;
    }



}