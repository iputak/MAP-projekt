package hr.vsite.map.taxivodstvo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DogovoreneVoznjeFragment extends Fragment {
    Button b1;
    public DogovoreneVoznjeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dogovorene_voznje,container,false);
        b1=view.findViewById(R.id.bt_novi_podsjetnik);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity() ,NoviPodsjetnikActivity.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }


}
