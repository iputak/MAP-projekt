package hr.vsite.map.taxivodstvo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SmjenaFragment extends Fragment {
    Button netDatum, prekid, spremi;
    EditText npk1, npk2, npk3, npk4, npk5, npk6, nzk1, nzk2, nzk3, nzk4, nzk5, nzk6, nukpromet, nkartice, npotvrde, nfiskal;
    String selectedDate, korisnikId, dan, mjesec, godina;
    ToggleButton nSmjena;
    TextView test;

    DialogFragment dialogFragment;
    DatePickerDialog.OnDateSetListener setListener;

    FirebaseDatabase rootNode;
    DatabaseReference reference, korisnik, smjene;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public static final int REQUEST_CODE = 11; // Used to identify the result

    private Fragment mListener;

    public SmjenaFragment() {
        // Required empty public constructor
    }

    public static SmjenaFragment newInstance() {
        SmjenaFragment fragment = new SmjenaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {

        View view = inflater.inflate(R.layout.fragment_smjena, container, false);

        korisnikId = ((HomeActivity) getActivity()).korisnickoIme;

        netDatum = (Button) view.findViewById(R.id.odabirDatuma);

        // TODO: "Ovo maknuti!"
        test= (TextView) view.findViewById(R.id.tv_smjena_datum);

        nSmjena = (ToggleButton) view.findViewById(R.id.smjena);
        // Početni kilometri
        npk1 = (EditText) view.findViewById((R.id.pkm1));
        npk2 = (EditText) view.findViewById((R.id.pkm2));
        npk3 = (EditText) view.findViewById((R.id.pkm3));
        npk4 = (EditText) view.findViewById((R.id.pkm4));
        npk5 = (EditText) view.findViewById((R.id.pkm5));
        npk6 = (EditText) view.findViewById((R.id.pkm6));

        // Završni kilometri
        nzk1 = (EditText) view.findViewById((R.id.zkm1));
        nzk2 = (EditText) view.findViewById((R.id.zkm2));
        nzk3 = (EditText) view.findViewById((R.id.zkm3));
        nzk4 = (EditText) view.findViewById((R.id.zkm4));
        nzk5 = (EditText) view.findViewById((R.id.zkm5));
        nzk6 = (EditText) view.findViewById((R.id.zkm6));

        nukpromet = (EditText) view.findViewById((R.id.ukpromet));
        nkartice = (EditText) view.findViewById((R.id.kartice));
        npotvrde = (EditText) view.findViewById((R.id.potvrde));
        nfiskal = (EditText) view.findViewById((R.id.fiskal));

        // Gumbi
        prekid = (Button) view.findViewById(R.id.bt_prekid);
        spremi = (Button) view.findViewById(R.id.bt_spremi_smjenu);


        // get fragment manager so we can launch from fragment
        final FragmentManager fm = ((AppCompatActivity) getActivity()).getSupportFragmentManager();


        netDatum.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(SmjenaFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(fm, "datePicker");

            }
        });

        spremi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Vozaci");
                korisnik = reference.child(korisnikId);
                smjene = korisnik.child("smjene");

                String etDatum = netDatum.getText().toString();
                String smjena = nSmjena.getText().toString();

                // Početni kilometri
                String pk1 = npk1.getText().toString();
                String pk2 = npk2.getText().toString();
                String pk3 = npk3.getText().toString();
                String pk4 = npk4.getText().toString();
                String pk5 = npk5.getText().toString();
                String pk6 = npk6.getText().toString();
                String pk = pk1 + pk2 + pk3 + pk4 + pk5 + pk6;


                // Završni kilometri
                String zk1 = nzk1.getText().toString();
                String zk2 = nzk2.getText().toString();
                String zk3 = nzk3.getText().toString();
                String zk4 = nzk4.getText().toString();
                String zk5 = nzk5.getText().toString();
                String zk6 = nzk6.getText().toString();
                String zk = zk1 + zk2 + zk3 + zk4 + zk5 + zk6;

                // Razlika kilometara
                int uzk = Integer.parseInt(zk);
                int upk = Integer.parseInt(pk);
                int uk = uzk - upk;
                String ukupnikm= String.valueOf(uk);

                // Promet
                String ukpromet= nukpromet.getText().toString();
                String kartice= nkartice.getText().toString();
                String potvrde= npotvrde.getText().toString();
                String fiskal= nfiskal.getText().toString();

                SmjenaHelperClass helperClass = new SmjenaHelperClass(etDatum, smjena, pk, zk, ukupnikm, ukpromet, kartice, potvrde, fiskal);

                smjene.child(godina).child(mjesec).child(dan).setValue(helperClass);
                Toast
                        .makeText(getContext(), "Smjena uspješno unesena!", Toast.LENGTH_LONG)
                        .show();


            }
        });


        prekid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // get date from string
            selectedDate = data.getStringExtra("selectedDate");
            // set the value of the button
            netDatum.setText(selectedDate);

            String[] separated = selectedDate.split("/");
            dan = separated[0];
            mjesec=separated[1];
            godina = separated[2];


        }
    }


}
