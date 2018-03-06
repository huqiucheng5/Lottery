package com.hu.lottery.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.hu.lottery.Constances.LotteryConstance;
import com.hu.lottery.R;
import com.hu.lottery.network.NetTools;
import com.hu.lottery.ui.ChooseLotteryFragment;
import com.hu.lottery.ui.LotteryInformationFragment;
import com.hu.lottery.ui.SelectBallFragment;
import com.hu.lottery.ui.base.BaseActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_CHOOSE_LOTTERY = 1;
    private static final int FRAGMENT_SELECT_BALL = 2;
    private static final int FRAGMENT_LOTTERY_INFORMATION = 3;


    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ChooseLotteryFragment chooseLotteryFragment;
    private SelectBallFragment selectBallFragment;
    private LotteryInformationFragment lotteryInformationFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initData();
    }

    private void initData() {
//        NetTools.getInstance(this).getHistoryString(LotteryConstance.DALETOU_GID,1);
//        NetTools.getInstance(this).getDetailString(LotteryConstance.DALETOU_GID,"2017124");
//        NetTools.getInstance(this).getLostData(LotteryConstance.DALETOU_GID);
        transctFragment(FRAGMENT_CHOOSE_LOTTERY);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void transctFragment(int type) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (type) {
            case FRAGMENT_CHOOSE_LOTTERY:
                if (null == chooseLotteryFragment) {
                    chooseLotteryFragment = new ChooseLotteryFragment();
                }
                fragmentTransaction.replace(R.id.container, chooseLotteryFragment, ChooseLotteryFragment.class.getSimpleName());
                break;
            case FRAGMENT_SELECT_BALL:
                if (null == selectBallFragment) {
                    selectBallFragment = new SelectBallFragment();
                }
                fragmentTransaction.replace(R.id.container, selectBallFragment, SelectBallFragment.class.getSimpleName());
                break;
            case FRAGMENT_LOTTERY_INFORMATION:
                if (null == lotteryInformationFragment) {
                    lotteryInformationFragment = new LotteryInformationFragment();
                }
                fragmentTransaction.replace(R.id.container, lotteryInformationFragment, LotteryInformationFragment.class.getSimpleName());
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            transctFragment(FRAGMENT_CHOOSE_LOTTERY);
        } else if (id == R.id.nav_gallery) {
            transctFragment(FRAGMENT_SELECT_BALL);
        } else if (id == R.id.nav_slideshow) {
            transctFragment(FRAGMENT_LOTTERY_INFORMATION);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
