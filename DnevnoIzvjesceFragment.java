package hr.vsite.map.taxivodstvo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class DnevnoIzvjesceFragment extends Fragment {
    String korisnik, godina, mjesec, dan, ukpromet, danPetlja;
    // Baza
    FirebaseDatabase rootNode;
    private DatabaseReference reference, mjesecPutanja, danPutanja;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dnevno_izvjesce, container, false);

        BarChart barChart = view.findViewById(R.id.barChart);

        // Sadašnji datum
        String sadasnjiDatum = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        String[] separated = sadasnjiDatum.split("/");
        dan = separated[0];
        mjesec = separated[1];
        godina = separated[2];

        //Integeri za arraylistu
        int danasdd = 0;
        int mjesecdd = 0;
        int godinadd = 0;

        danasdd = Integer.parseInt(dan.toString());
        mjesecdd = Integer.parseInt(mjesec.toString());
        godinadd = Integer.parseInt(godina.toString());

        int sedamprije = danasdd - 4;

        String korisnik = ((HomeActivity) getActivity()).korisnickoIme;
        // Putanja do baze smjena vozaca
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Vozaci");
        mjesecPutanja = reference.child(korisnik).child("smjene").child(godina).child(mjesec);

        ArrayList<BarEntry> tjedan = new ArrayList<>();
/*
        for (int i = sedamprije; i <= danasdd; i++) {
            danPetlja = String.valueOf(i);
            int dan = i;
            int promet = 0;
            danPutanja = mjesecPutanja.child(danPetlja);
            danPutanja.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // Iz baze
                    ukpromet = snapshot.child("ukPromet").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            promet = Integer.parseInt(ukpromet.toString());


            tjedan.add(new BarEntry(dan, promet));
        }
*/

        // TODO: testni podatci
        tjedan.add (new BarEntry(7, 1485));
        tjedan.add (new BarEntry(8, 650));
        tjedan.add (new BarEntry(9, 783));
        tjedan.add (new BarEntry(10, 495));
        tjedan.add (new BarEntry(11, 989));
        tjedan.add (new BarEntry(12, 657));


        BarDataSet barDataSet = new BarDataSet(tjedan, "Tjedno izvješće");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Testno tjedno izvješće");
        barChart.animateY(2000);


        return view;
    }
}
