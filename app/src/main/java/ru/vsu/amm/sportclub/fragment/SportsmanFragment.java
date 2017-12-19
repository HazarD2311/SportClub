package ru.vsu.amm.sportclub.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.vsu.amm.sportclub.R;


public class SportsmanFragment extends Fragment {

    private static final int LAYOUT = R.layout.sportsmans_fragment;

    private View view;

    public static SportsmanFragment getInstance() {
        Bundle args = new Bundle();
        SportsmanFragment fragment = new SportsmanFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}
