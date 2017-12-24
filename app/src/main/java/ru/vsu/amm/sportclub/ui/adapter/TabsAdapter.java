package ru.vsu.amm.sportclub.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ru.vsu.amm.sportclub.Const;
import ru.vsu.amm.sportclub.ui.fragment.CoachFragment;
import ru.vsu.amm.sportclub.ui.fragment.CompetitionFragment;
import ru.vsu.amm.sportclub.ui.fragment.SportsmanFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[] {
                Const.SPORTSMAN_TITLE,
                Const.COACH_TITLE,
                Const.COMPETITION_TITLE
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case Const.SPORTSMAN_TAB:
                return SportsmanFragment.getInstance();
            case Const.COACH_TAB:
                return CoachFragment.getInstance();
            case Const.COMPETITION_TAB:
                return CompetitionFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return Const.COUNT_OF_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
