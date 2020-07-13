package hr.vsite.map.taxivodstvo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment  {
    public static final String CALCULATOR_PACKAGE ="com.android.calculator2";
    public static final String CALCULATOR_CLASS ="com.android.calculator2.Calculator";

    public static final int REQUEST_CODE = 18; // Used to identify the result

    RelativeLayout kalkulator, kalendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String korisnik= ((HomeActivity)getActivity()).korisnickoIme;
        String id = ((HomeActivity)getActivity()).autentifikator;
        TextView datum = (TextView) view.findViewById(R.id.home_datum);

        TextView korisnikHomeFragment = (TextView) view.findViewById(R.id.imePrezime);
        korisnikHomeFragment.setText(korisnik);


        String sadasnjiDatum = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        datum.setText(sadasnjiDatum);



        kalkulator = view.findViewById(R.id.kalkulator);
        kalkulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
                startActivity(intent);
            }
        });

        // get fragment manager so we can launch from fragment
        final FragmentManager frm = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        kalendar = view.findViewById(R.id.kalendar);
        kalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the datePickerFragment
                AppCompatDialogFragment newFragment = new DatePickerFragment();
                // set the targetFragment to receive the results, specifying the request code
                newFragment.setTargetFragment(HomeFragment.this, REQUEST_CODE);
                // show the datePicker
                newFragment.show(frm, "datePicker");
            }
        });

        return view;
    }


}
