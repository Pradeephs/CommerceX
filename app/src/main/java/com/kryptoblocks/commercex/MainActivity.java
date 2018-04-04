package com.kryptoblocks.commercex;

//import android.app.FragmentManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.widget.ShareDialog;
import com.kryptoblocks.commercex.fragmentss.CategoriesFragment;
import com.kryptoblocks.commercex.fragmentss.ElectronicsFragment;
import com.kryptoblocks.commercex.fragmentss.FashionFragment;
import com.kryptoblocks.commercex.fragmentss.HomeFragment;
import com.kryptoblocks.commercex.fragmentss.MobilesFragment;
import com.kryptoblocks.commercex.fragmentss.MyOrdersFragment;
import com.kryptoblocks.commercex.fragmentss.OffersFragment;
import com.kryptoblocks.commercex.fragmentss.WalletFragment;
import com.kryptoblocks.commercex.payment.PaymentFragment;
import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    String username;
    ImageView nav_image_user;
    SearchView searchView;
    TextView u_name;
    // TabLayout tabb;
    String user, image;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ShareDialog shareDialog;

    public void PaymentFragment() {

        PaymentFragment paymentFragment = new PaymentFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, paymentFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_main);
        PaymentFragment();

        shareDialog = new ShareDialog(this);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        View nav_Viewer = navigationView.getHeaderView(0);

        // tabb = findViewById(R.id.tabLayout);
        searchView = findViewById(R.id.homeSearchView);
        u_name = nav_Viewer.findViewById(R.id.navigation_header_user_name);
        nav_image_user = nav_Viewer.findViewById(R.id.nav_img);

        Bundle bundle = getIntent().getExtras();
//Extract the data…
        user = bundle.getString("username");
        image = bundle.getString("userimage");


        Picasso.with(this)
                .load(image)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(nav_image_user);


        u_name.setText(user);
        initToolBar();
        initNavigationDrawer();
        OffersFragment();


     /*   Bundle inBundle = getIntent().getExtras();
        String name = inBundle.get("name").toString();
        String surname = inBundle.get("surname").toString();
        String imageUrl = inBundle.get("imageUrl").toString();


        u_name.setText("" + name + " " + surname);*/
        // new MainActivity.DownloadImage(nav_image_user).execute(imageUrl);
    }

    public void OffersFragment() {
        OffersFragment offersFragment = new OffersFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, offersFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void ElectronicsFragment() {
        ElectronicsFragment electronicsFragment = new ElectronicsFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, electronicsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void FashionFragment() {
        FashionFragment fashionFragment = new FashionFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fashionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void MobilesFragment() {
        MobilesFragment mobilesFragment = new MobilesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, mobilesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void CategoriesFragment() {
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, categoriesFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void HomeFragment() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void WalletFragment() {
        WalletFragment walletFragment = new WalletFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, walletFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void MyOrdersFragment() {
        MyOrdersFragment myOrdersFragment = new MyOrdersFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, myOrdersFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //////////////// for cart option
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
        if (id == R.id.cart) {
            Intent i = new Intent(MainActivity.this, CartActivity.class);
            startActivity(i);
            // MyCartFragment();

            return true;
        } else if (id == R.id.scan) {
            Intent i = new Intent(MainActivity.this, QRCodeScannerActivity.class);
            startActivity(i);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initNavigationDrawer() {

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.home_menu:
                        OffersFragment();
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.categories_menu:
                        CategoriesFragment();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.wishlist_menu:
                        // WishlistFragment();
                        Intent musicIntent = new Intent(MainActivity.this, WishlistActivity.class);
                        startActivity(musicIntent);
                        drawerLayout.closeDrawers();
                        /*Intent musicIntent = new Intent(getApplicationContext(),Music_Movies_Activity.class);
                        startActivity(musicIntent);*/
                        break;

                    case R.id.cart_menu:
                        Intent i = new Intent(MainActivity.this, CartActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.oders_menu:
                        MyOrdersFragment();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.wallet_menu:
                        WalletFragment();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.offers_menu:
                        OffersFragment();
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.sign_in_menu:
                        Intent ii = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(ii);
                        //finish();
                }

                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());

                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
//        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        //  tv_email.setText("raj.amalw@learn2crack.com");
        drawerLayout = findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
//////////////////////////////

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void initToolBar() {
        toolbar = findViewById(R.id.tool_try);
        toolbar.setTitle(R.string.tool_main);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        Fragment fragment = null;
        switch (tab.getPosition()) {
            case 0:
                OffersFragment();
                break;
            case 1:
                MobilesFragment();
                break;
            case 2:
                FashionFragment();
                break;
            case 3:
                ElectronicsFragment();
                break;
            case 4:
                HomeFragment();
                break;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

   /* public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(R.string.tool_main);
    }*/

}
