package hr.vsite.map.taxivodstvo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import hr.vsite.map.taxivodstvo.Adapter.SectionPagerAdapter;




public class IzvjescaFragment extends Fragment {

    View myFragment;

    View viewPager;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // ------ Fragmenti ------
        // Load default fragment
        myFragment = inflater.inflate(R.layout.fragment_izvjesca, container, false);

        viewPager = myFragment.findViewById(R.id.viewPagerIzvjesca);
        tabLayout = myFragment.findViewById(R.id.tabLayoutIzvjesca);


        return myFragment;
    }

    // Call onActivity Create method

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager((ViewPager) viewPager);
        tabLayout.setupWithViewPager((ViewPager) viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        // Fragmenti
        adapter.addFragment(new DnevnoIzvjesceFragment(),"Tjedno");
       // adapter.addFragment(new DnevnoIzvjesceFragment(),"Mjesečno");
       // adapter.addFragment(new DnevnoIzvjesceFragment(),"Godišnje");

        viewPager.setAdapter(adapter);
    }


}
