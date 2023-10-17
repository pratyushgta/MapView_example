/**
 * This class contains methods for the home screen of the app
 * MAD-E9
 *
 * @author Pratyush Kumar (github.com/pratyushgta)
 */

package com.example.mad_e8_mapview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends Fragment {
    Button nextBtn;
    TextView desc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        nextBtn = root.findViewById(R.id.home_nextBtn);
        desc = root.findViewById(R.id.home_textView);

        desc.setText(R.string.home_desc);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapsFragment mapsFragment = new MapsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.home_framelayout, mapsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
                bottomNavigationView.setSelectedItemId(R.id.nav_maps);
            }
        });
        return root;
    }
}