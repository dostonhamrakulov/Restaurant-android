package com.menu;
/**
 * Created by Doston Hamrakulov doston.hamrakulov@gmail.com on 5/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.menu.Database.ProductAdapter;
import com.menu.Model.Product;

import java.text.DecimalFormat;
import java.util.Random;

public class MenuDetailActivity extends AppCompatActivity {
    TextView menuTitleView;
    TextView descriptionView;
    TextView menuCostView;
    TextView totalOrderView;
    TextView totalCostView;
    String menuTitleString;
    String menuDescriptionString;
    String menuCostString;
    String totalCostString;
    String totalOrderString;
    Button increaseTotalCostButton;
    Button decreaseTotalCostButton;
    Button addToListButton;
    double menuCostDouble;
    double totalCostDouble;
    int totalOrder;
    String imageTitleString;
    private android.support.v7.widget.ShareActionProvider mShareActionProvider;
    RatingBar rating;
    double ratingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        Intent intent = getIntent();

        increaseTotalCostButton = (Button)findViewById(R.id.increaseTotalCostButton);
        addToListButton = (Button)findViewById(R.id.addToListButton);
        decreaseTotalCostButton = (Button)findViewById(R.id.decreaseTotalCostButton);
        menuTitleString = intent.getStringArrayExtra(MenuActivity.TAG)[0];
        menuDescriptionString = intent.getStringArrayExtra(MenuActivity.TAG)[1];
        menuCostString = intent.getStringArrayExtra(MenuActivity.TAG)[2];
        menuCostDouble = totalCostDouble = Double.parseDouble(menuCostString);
        totalCostString = intent.getStringArrayExtra(MenuActivity.TAG)[3];
        totalCostDouble = Double.parseDouble(totalCostString);
        totalOrderString = intent.getStringArrayExtra(MenuActivity.TAG)[4];
        totalOrder = Integer.parseInt(totalOrderString);
        imageTitleString = intent.getStringArrayExtra(MenuActivity.TAG)[5];
        rating =(RatingBar)findViewById(R.id.ratingBar);
        displayRating();
        displayData();

        increaseTotalCostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseTotalCost();
            }
        });

        decreaseTotalCostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseTotalCost();
            }
        });

        addToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToList();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        MenuItem menuItem1 = menu.findItem(R.id.action_refresh);
        // Get the provider and hold onto it to set/change the share intent.
        mShareActionProvider = (android.support.v7.widget.ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        // If onLoadFinished happens before this, we can go ahead and set the share intent now.
        if (menuDescriptionString != null && menuCostString != null && menuTitleString !=null) {
            mShareActionProvider.setShareIntent(createShareForecastIntent());
        }
        return true;
    }


    private Intent createShareForecastIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("text/plain");
        String inentMsg = "Хэе, ?\n" + menuTitleString + "\n" + menuDescriptionString +".\n" + "Үнэ: $" + menuCostString;
        shareIntent.putExtra(Intent.EXTRA_TEXT, inentMsg);
        return shareIntent;
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


    public void increaseTotalCost() {
        totalOrder++;
        totalCostDouble += menuCostDouble;
        displayUpdate();
    }

    public void decreaseTotalCost() {
        totalOrder = (--totalOrder < 1) ? 1 : totalOrder;
        totalCostDouble -= menuCostDouble;
        totalCostDouble = (totalCostDouble < menuCostDouble) ? menuCostDouble : totalCostDouble;
        displayUpdate();
    }

    public void displayData() {
        menuTitleView = (TextView) findViewById(R.id.menu_title);

        menuTitleView.setText(menuTitleString);

        menuCostView = (TextView) findViewById(R.id.cost_text_view);
        menuCostView.setText("Үнэ: $ " + menuCostString);

        displayUpdate();
    }

    public void displayRating(){
        Random r = new Random();
        ratingValue = (r.nextDouble()*5.0);
        rating.setRating((float)ratingValue);
    }

    public void displayUpdate() {
        totalCostView = (TextView) findViewById(R.id.total_cost_text_view);
        totalCostView.setText("Нийт дүн: $ " + new DecimalFormat("#.##").format(totalCostDouble));

        totalOrderView = (TextView) findViewById(R.id.total_item_number);
        totalOrderView.setText("Нийт тоо: " + totalOrder);

        descriptionView = (TextView) findViewById(R.id.description_text_view);
        descriptionView.setText(menuDescriptionString);

    }

    public void addToList() {
        ProductAdapter productAdapter = new ProductAdapter(this);;
        ratingValue = rating.getRating();
        Product item = new Product(menuTitleString, menuDescriptionString, menuCostDouble, imageTitleString, totalCostDouble, totalOrder);
        item.setRatinng(ratingValue);
        productAdapter.addProduct(item);
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
