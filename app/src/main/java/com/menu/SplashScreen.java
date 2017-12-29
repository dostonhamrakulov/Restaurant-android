package com.menu;
/**
 * Created by Doston Hamrakulov doston.hamrakulov@gmail.com on 5/10/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.menu.Database.MenuAdapter;
import com.menu.Model.Product;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;


    public  static int currentCount = 0;

    Product item1 = new Product("Сайхан",
            "Сайхан хоол байна лээ",
            5.50,
            "saihan");

    Product item2 = new Product("Сайхан1",
            "Сайхан хоол байна лээ",
            5.50,
            "saihan");

    Product item3 = new Product("Сайха2",
            "Сайхан хоол байна лээ",
            5.50,
            "saihan");

    Product item4 = new Product("Сайхан3",
            "Сайхан хоол байна лээ",
            5.50,
            "saihan");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MenuAdapter menu = new MenuAdapter(this);
        menu.addDataToALLMenu(item1);
        menu.addDataToALLMenu(item2);
        menu.addDataToALLMenu(item3);
        menu.addDataToALLMenu(item4);
        menu.close();

        try {
            Thread timerThread = new Thread(){
                public void run(){
                    try {
                        sleep(1000);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            timerThread.start();
        }catch (Exception e){
        }
    }
}
