package fitnesscompanion.com.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import fitnesscompanion.com.R;
import fitnesscompanion.com.View.Activity.ActivityFragment;
import fitnesscompanion.com.View.Food.FoodFragment;
import fitnesscompanion.com.View.Profile.ProfileFragment;

public class MenuActivity extends AppCompatActivity {

    private int backButtonCount=0;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(getIntent().getIntExtra("index",0));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icon_dob);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_dob);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_dob);
        tabLayout.getTabAt(3).setIcon(R.drawable.icon_dob);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new HomeFragment(getApplicationContext());
                case 1: return new ActivityFragment(getApplicationContext());
                case 2: return new FoodFragment(getApplicationContext());
                case 3: return new ProfileFragment(getApplicationContext());
                default: return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.home);
                case 1:
                    return getString(R.string.actvity);
                case 2:
                    return getString(R.string.food);
                case 3:
                    return getString(R.string.profile);
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            System.exit(0);


        }
        else
        {
            Toast.makeText(this,getString(R.string.exitMsg), Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}
