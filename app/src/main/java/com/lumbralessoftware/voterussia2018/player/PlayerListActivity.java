package com.lumbralessoftware.voterussia2018.player;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.lumbralessoftware.voterussia2018.ElementList;
import com.lumbralessoftware.voterussia2018.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lumbralessoftware.voterussia2018.Constants.ARGENTINA;
import static com.lumbralessoftware.voterussia2018.Constants.AUSTRALIA;
import static com.lumbralessoftware.voterussia2018.Constants.BELGIUM;
import static com.lumbralessoftware.voterussia2018.Constants.BRAZIL;
import static com.lumbralessoftware.voterussia2018.Constants.COLOMBIA;
import static com.lumbralessoftware.voterussia2018.Constants.COSTA_RICA;
import static com.lumbralessoftware.voterussia2018.Constants.CROATIA;
import static com.lumbralessoftware.voterussia2018.Constants.DENAMARK;
import static com.lumbralessoftware.voterussia2018.Constants.EGYPT;
import static com.lumbralessoftware.voterussia2018.Constants.ENGLAND;
import static com.lumbralessoftware.voterussia2018.Constants.FRANCE;
import static com.lumbralessoftware.voterussia2018.Constants.GERMANY;
import static com.lumbralessoftware.voterussia2018.Constants.ICELAND;
import static com.lumbralessoftware.voterussia2018.Constants.IRAN;
import static com.lumbralessoftware.voterussia2018.Constants.JAPAN;
import static com.lumbralessoftware.voterussia2018.Constants.MEXICO;
import static com.lumbralessoftware.voterussia2018.Constants.MOROCCO;
import static com.lumbralessoftware.voterussia2018.Constants.NIGERIA;
import static com.lumbralessoftware.voterussia2018.Constants.PANAMA;
import static com.lumbralessoftware.voterussia2018.Constants.PERU;
import static com.lumbralessoftware.voterussia2018.Constants.POLAND;
import static com.lumbralessoftware.voterussia2018.Constants.PORTUGAL;
import static com.lumbralessoftware.voterussia2018.Constants.RUSSIA;
import static com.lumbralessoftware.voterussia2018.Constants.SAUDI_ARABIA;
import static com.lumbralessoftware.voterussia2018.Constants.SENEGAL;
import static com.lumbralessoftware.voterussia2018.Constants.SERBIA;
import static com.lumbralessoftware.voterussia2018.Constants.SOUTH_KOREA;
import static com.lumbralessoftware.voterussia2018.Constants.SPAIN;
import static com.lumbralessoftware.voterussia2018.Constants.SWEDEN;
import static com.lumbralessoftware.voterussia2018.Constants.SWITZERLAND;
import static com.lumbralessoftware.voterussia2018.Constants.TUNISIA;
import static com.lumbralessoftware.voterussia2018.Constants.URUGUAY;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListActivity extends AppCompatActivity implements PlayerListMenuAdapter.MyClickListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.left_drawer)
    RelativeLayout drawerRelativeLayout;
    @BindView(R.id.list_view_drawer)
    RecyclerView recyclerView;

    private String[] optionMenu;
    private ActionBarDrawerToggle mDrawerToggle;
    private PlayerListPresenter presenter;
    private CharSequence titleSection;
    private CharSequence mTitleApp;
    HashMap<Integer, String> mMapIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_list_activity);

        ButterKnife.bind(this);
        initFragment();
        initDrawer();
    }

    private void initFragment() {
        PlayerListFragment playerListFragment = (PlayerListFragment) getSupportFragmentManager().
                findFragmentById(R.id.player_list_activity_container);
        if (playerListFragment == null) {
            playerListFragment = playerListFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(),
                    playerListFragment, R.id.player_list_activity_container);
        }
        presenter = new PlayerListPresenter(playerListFragment, this);

    }

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                             Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    private void initDrawer() {

        recyclerView.setScrollbarFadingEnabled(true);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        optionMenu = new String[]{getString(R.string.position_goalkeeper),
                getString(R.string.position_defender),
                getString(R.string.position_midfield),
                getString(R.string.position_forward),

                getString(R.string.country_russia),
                getString(R.string.country_saudi),
                getString(R.string.country_egypt),
                getString(R.string.country_uruguay),

                getString(R.string.country_portugal),
                getString(R.string.country_spain),
                getString(R.string.country_morocco),
                getString(R.string.country_iran),

                getString(R.string.country_france),
                getString(R.string.country_australia),
                getString(R.string.country_peru),
                getString(R.string.country_denmark),

                getString(R.string.country_argentina),
                getString(R.string.country_iceland),
                getString(R.string.country_croatia),
                getString(R.string.country_nigeria),

                getString(R.string.country_brazil),
                getString(R.string.country_switzerland),
                getString(R.string.country_costa),
                getString(R.string.country_serbia),

                getString(R.string.country_germany),
                getString(R.string.country_mexico),
                getString(R.string.country_sweden),
                getString(R.string.country_korea),

                getString(R.string.country_belgium),
                getString(R.string.country_panama),
                getString(R.string.country_tunisia),
                getString(R.string.country_england),

                getString(R.string.country_poland),
                getString(R.string.country_senegal),
                getString(R.string.country_colombia),
                getString(R.string.country_japan)};

        PlayerListMenuAdapter adapter = new PlayerListMenuAdapter(this, getDataSet());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        //recyclerView.setItemChecked(0, true);
        titleSection = getString(R.string.position_goalkeeper);
        mTitleApp = getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(titleSection);
                ActivityCompat.invalidateOptionsMenu(PlayerListActivity.this);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.app_name);
                ActivityCompat.invalidateOptionsMenu(PlayerListActivity.this);
            }
        };

        drawerLayout.addDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<ElementList> getDataSet() {

        getListIndexed();

        ArrayList results = new ArrayList<>();
        ElementList obj;
        int section = 0, normal = 0;
        int total = optionMenu.length + mMapIndex.size();

        for (int index = 0; index < total; index++) {
            switch (index) {
                case 0:
                case 5:
                case 10:
                case 15:
                case 20:
                case 25:
                case 30:
                case 35:
                case 40:
                    obj = new ElementList(mMapIndex.get(section).toString(), true, false);
                    section ++;
                    break;
                case 4:
                case 9:
                case 14:
                case 19:
                case 24:
                case 29:
                case 34:
                case 39:
                    obj = new ElementList(optionMenu[normal], false, true);
                    normal++;
                    break;
                default:
                    obj = new ElementList(optionMenu[normal], false, false);
                    normal++;
                    break;
            }

            results.add(index, obj);
        }
        return results;
    }

    public void getListIndexed() {

        mMapIndex = new LinkedHashMap<>();
        mMapIndex.put(0, getString(R.string.position));
        mMapIndex.put(1, getString(R.string.group_a));
        mMapIndex.put(2, getString(R.string.group_b));
        mMapIndex.put(3, getString(R.string.group_c));
        mMapIndex.put(4, getString(R.string.group_d));
        mMapIndex.put(5, getString(R.string.group_e));
        mMapIndex.put(6, getString(R.string.group_f));
        mMapIndex.put(7, getString(R.string.group_g));
        mMapIndex.put(8, getString(R.string.group_h));
    }


    @Override
    public void onItemClick(int position, boolean addItem) {
        switch (position) {
            case 1:
                presenter.fetchPlayerWithPosition(0);
                titleSection = optionMenu[0];
                break;
            case 2:
                presenter.fetchPlayerWithPosition(1);
                titleSection = optionMenu[1];
                break;
            case 3:
                presenter.fetchPlayerWithPosition(2);
                titleSection = optionMenu[2];
                break;
            case 4:
                presenter.fetchPlayerWithPosition(3);
                titleSection = optionMenu[3];
                break;
            //A
            case 6:
                presenter.fetchPlayerWithTeam(RUSSIA);
                titleSection = optionMenu[4];
                break;
            case 7:
                presenter.fetchPlayerWithTeam(SAUDI_ARABIA);
                titleSection = optionMenu[5];
                break;
            case 8:
                presenter.fetchPlayerWithTeam(EGYPT);
                titleSection = optionMenu[6];
                break;
            case 9:
                presenter.fetchPlayerWithTeam(URUGUAY);
                titleSection = optionMenu[7];
                break;
            //B
            case 11:
                presenter.fetchPlayerWithTeam(PORTUGAL);
                titleSection = optionMenu[8];
                break;
            case 12:
                presenter.fetchPlayerWithTeam(SPAIN);
                titleSection = optionMenu[9];
                break;
            case 13:
                presenter.fetchPlayerWithTeam(MOROCCO);
                titleSection = optionMenu[10];
                break;
            case 14:
                presenter.fetchPlayerWithTeam(IRAN);
                titleSection = optionMenu[11];
                break;
            //C
            case 16:
                presenter.fetchPlayerWithTeam(FRANCE);
                titleSection = optionMenu[12];
                break;
            case 17:
                presenter.fetchPlayerWithTeam(AUSTRALIA);
                titleSection = optionMenu[13];
                break;
            case 18:
                presenter.fetchPlayerWithTeam(PERU);
                titleSection = optionMenu[14];
                break;
            case 19:
                presenter.fetchPlayerWithTeam(DENAMARK);
                titleSection = optionMenu[15];
                break;
            //D
            case 21:
                presenter.fetchPlayerWithTeam(ARGENTINA);
                titleSection = optionMenu[16];
                break;
            case 22:
                presenter.fetchPlayerWithTeam(ICELAND);
                titleSection = optionMenu[17];
                break;
            case 23:
                presenter.fetchPlayerWithTeam(CROATIA);
                titleSection = optionMenu[18];
                break;
            case 24:
                presenter.fetchPlayerWithTeam(NIGERIA);
                titleSection = optionMenu[19];
                break;
            //E
            case 26:
                presenter.fetchPlayerWithTeam(BRAZIL);
                titleSection = optionMenu[20];
                break;
            case 27:
                presenter.fetchPlayerWithTeam(SWITZERLAND);
                titleSection = optionMenu[21];
                break;
            case 28:
                presenter.fetchPlayerWithTeam(COSTA_RICA);
                titleSection = optionMenu[22];
                break;
            case 29:
                presenter.fetchPlayerWithTeam(SERBIA);
                titleSection = optionMenu[23];
                break;

            //F
            case 31:
                presenter.fetchPlayerWithTeam(GERMANY);
                titleSection = optionMenu[24];
                break;
            case 32:
                presenter.fetchPlayerWithTeam(MEXICO);
                titleSection = optionMenu[25];
                break;
            case 33:
                presenter.fetchPlayerWithTeam(SWEDEN);
                titleSection = optionMenu[26];
                break;
            case 34:
                presenter.fetchPlayerWithTeam(SOUTH_KOREA);
                titleSection = optionMenu[27];
                break;
            //G
            case 36:
                presenter.fetchPlayerWithTeam(BELGIUM);
                titleSection = optionMenu[28];
                break;
            case 37:
                presenter.fetchPlayerWithTeam(PANAMA);
                titleSection = optionMenu[29];
                break;
            case 38:
                presenter.fetchPlayerWithTeam(TUNISIA);
                titleSection = optionMenu[30];
                break;
            case 39:
                presenter.fetchPlayerWithTeam(ENGLAND);
                titleSection = optionMenu[31];
                break;
            //H
            case 41:
                presenter.fetchPlayerWithTeam(POLAND);
                titleSection = optionMenu[32];
                break;
            case 42:
                presenter.fetchPlayerWithTeam(SENEGAL);
                titleSection = optionMenu[33];
                break;
            case 43:
                presenter.fetchPlayerWithTeam(COLOMBIA);
                titleSection = optionMenu[34];
                break;
            case 44:
                presenter.fetchPlayerWithTeam(JAPAN);
                titleSection = optionMenu[35];
                break;
        }
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}
