package hr.vsite.map.taxivodstvo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class VozaciFragment extends Fragment {

    private View view;

    // Baza
    FirebaseDatabase rootNode;
    private DatabaseReference reference;

    // Lista
    private RecyclerView sviVozaci;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_vozaci, container, false);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Vozaci");


        sviVozaci = (RecyclerView) view.findViewById(R.id.vozaci_lista);
        sviVozaci.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<Vozaci>()
                        .setQuery(reference, Vozaci.class)
                        .build();

        FirebaseRecyclerAdapter<Vozaci, VozaciViewHolder> adapter
                = new FirebaseRecyclerAdapter<Vozaci, VozaciViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final VozaciViewHolder holder, int position, @NonNull Vozaci model) {
                String vozacID = getRef(position).getKey();
                reference.child(vozacID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            if (snapshot.hasChild("korisnickoIme")) {

                                final String vozacKorisnickoIme = snapshot.child("korisnickoIme").getValue().toString();
                                String vozacIme = snapshot.child("ime").getValue().toString();
                                String vozacPrezime = snapshot.child("prezime").getValue().toString();
                                String vozacBrojMobitela = snapshot.child("brMob").getValue().toString();
                                String vozacEmail = snapshot.child("email").getValue().toString();

                                // Spajanje imena i prezimena
                                String vozacImePrezime = vozacIme + " " + vozacPrezime;

                                //Prikaz
                                holder.korisnickoIme.setText(vozacKorisnickoIme);
                                holder.imeIprezime.setText(vozacImePrezime);
                                holder.brojMobitela.setText(vozacBrojMobitela);
                                holder.email.setText(vozacEmail);

                                holder.itemView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Bundle bundle= new Bundle();
                                        bundle.putString("key",vozacKorisnickoIme);
                                        VozacFragment vozacFragment = new VozacFragment();
                                        vozacFragment.setArguments(bundle);
                                        getActivity().getSupportFragmentManager().beginTransaction()
                                                .replace(R.id.container_fragment,vozacFragment,"vozacFragment")
                                                .addToBackStack(null)
                                                .commit();
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public VozaciViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                // Prikaz prilagođenog layouta za listu
                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.vozaci_prikaz, parent, false);
                VozaciViewHolder vozaciViewHolder = new VozaciViewHolder(view1);
                return vozaciViewHolder;
            }
        };

        sviVozaci.setAdapter(adapter);
        adapter.startListening();
    }

    public static class VozaciViewHolder extends RecyclerView.ViewHolder {

        TextView korisnickoIme, imeIprezime, brojMobitela, email;


        public VozaciViewHolder(@NonNull View itemView) {
            super(itemView);
            korisnickoIme = itemView.findViewById(R.id.vp_korIme);
            imeIprezime = itemView.findViewById(R.id.vp_imePrezime);
            brojMobitela = itemView.findViewById(R.id.vp_brMob);
            email = itemView.findViewById(R.id.vp_email);
        }
    }

}