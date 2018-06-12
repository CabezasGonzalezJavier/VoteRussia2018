package com.lumbralessoftware.voterussia2018.player;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lumbralessoftware.voterussia2018.R;

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

public class PlayerListActivity extends AppCompatActivity {

    private String[] mOptionMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.left_drawer)
    RelativeLayout mDrawerRelativeLayout;
    @BindView(R.id.list_view_drawer)
    ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private PlayerListPresenter presenter;
    private CharSequence mTitleSection;
    private CharSequence mTitleApp;

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
        mOptionMenu = new String[]{getString(R.string.position_goalkeeper),
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
        mDrawerList.setAdapter(new ArrayAdapter<>(getSupportActionBar()
                .getThemedContext(), android.R.layout.simple_list_item_1,
                mOptionMenu));

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                switch (position) {
                    case 0:
                        presenter.fetchPlayerWithPosition(0);
                        break;
                    case 1:
                        presenter.fetchPlayerWithPosition(1);
                        break;
                    case 2:
                        presenter.fetchPlayerWithPosition(2);
                        break;
                    case 3:
                        presenter.fetchPlayerWithPosition(3);
                        break;
                    //A
                    case 4:
                        presenter.fetchPlayerWithTeam(RUSSIA);
                        break;
                    case 5:
                        presenter.fetchPlayerWithTeam(SAUDI_ARABIA);
                        break;
                    case 6:
                        presenter.fetchPlayerWithTeam(EGYPT);
                        break;
                    case 7:
                        presenter.fetchPlayerWithTeam(URUGUAY);
                        break;
                    //B
                    case 8:
                        presenter.fetchPlayerWithTeam(PORTUGAL);
                        break;
                    case 9:
                        presenter.fetchPlayerWithTeam(SPAIN);
                        break;
                    case 10:
                        presenter.fetchPlayerWithTeam(MOROCCO);
                        break;
                    case 11:
                        presenter.fetchPlayerWithTeam(IRAN);
                        break;
                    //C
                    case 12:
                        presenter.fetchPlayerWithTeam(FRANCE);
                        break;
                    case 13:
                        presenter.fetchPlayerWithTeam(AUSTRALIA);
                        break;
                    case 14:
                        presenter.fetchPlayerWithTeam(PERU);
                        break;
                    case 15:
                        presenter.fetchPlayerWithTeam(DENAMARK);
                        break;
                        //D
                    case 16:
                        presenter.fetchPlayerWithTeam(ARGENTINA);
                        break;
                    case 17:
                        presenter.fetchPlayerWithTeam(ICELAND);
                        break;
                    case 18:
                        presenter.fetchPlayerWithTeam(CROATIA);
                        break;
                    case 19:
                        presenter.fetchPlayerWithTeam(NIGERIA);
                        break;
                    //E
                    case 20:
                        presenter.fetchPlayerWithTeam(BRAZIL);
                        break;
                    case 21:
                        presenter.fetchPlayerWithTeam(SWITZERLAND);
                        break;
                    case 22:
                        presenter.fetchPlayerWithTeam(COSTA_RICA);
                        break;
                    case 23:
                        presenter.fetchPlayerWithTeam(SERBIA);
                        break;

                      //F
                    case 24:
                        presenter.fetchPlayerWithTeam(GERMANY);
                        break;
                    case 25:
                        presenter.fetchPlayerWithTeam(MEXICO);
                        break;
                    case 26:
                        presenter.fetchPlayerWithTeam(SWEDEN);
                        break;
                    case 27:
                        presenter.fetchPlayerWithTeam(SOUTH_KOREA);
                        break;
                        //G
                    case 28:
                        presenter.fetchPlayerWithTeam(BELGIUM);
                        break;
                    case 29:
                        presenter.fetchPlayerWithTeam(PANAMA);
                        break;
                    case 30:
                        presenter.fetchPlayerWithTeam(TUNISIA);
                        break;
                    case 31:
                        presenter.fetchPlayerWithTeam(ENGLAND);
                        break;
                        //H
                    case 32:
                        presenter.fetchPlayerWithTeam(POLAND);
                        break;
                    case 33:
                        presenter.fetchPlayerWithTeam(SENEGAL);
                        break;
                    case 34:
                        presenter.fetchPlayerWithTeam(COLOMBIA);
                        break;
                    case 35:
                        presenter.fetchPlayerWithTeam(JAPAN);
                        break;
                }

                mDrawerList.setItemChecked(position, true);

                mTitleSection = mOptionMenu[position];
                getSupportActionBar().setTitle(mTitleSection);

                mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
            }
        });
        mDrawerList.setItemChecked(0, true);
        mTitleSection = getString(R.string.position_goalkeeper);
        mTitleApp = getTitle();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open,
                R.string.drawer_close) {
            //this, mDrawerLayout,
            //R.drawable.ic_drawer, R.string.drawer_open,
            //R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitleSection);
                ActivityCompat.invalidateOptionsMenu(PlayerListActivity.this);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.app_name);
                ActivityCompat.invalidateOptionsMenu(PlayerListActivity.this);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

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

}
