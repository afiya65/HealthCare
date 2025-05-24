package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private  String[][] packages =
            {
                    {"Uprise-D3 1000IU Capsule","","","","50"},
                    {"Vitamin B Complex Capsules","","","","305"},
                    {"Paracetamol 500mg Tablets","","",""," 120"},
                    {"Ibuprofen 400mg Tablets","","",""," 150"},
                    {"Multivitamin Syrup","","",""," 275"},
            };
    private String[] package_details = {
            "Helps in building and keeping the bones & teeth strong\n" +
                    "Reduces fatigue/stress and muscular pains\n" +
                    "Boosts immunity and increases resistance against infection",
            "Provides relief from vitamin B deficiencies\n" +
                    "Helps in formation of red blood cells\n" +
                    "Maintains healthy nervous system",
            "Helps relieve fever and bring down a high temperature\n" +
                    "Provides relief from mild to moderate pain such as headache, muscle aches, or toothache",
            "Reduces inflammation, swelling, and pain\n" +
                    "Commonly used for arthritis, menstrual cramps, or minor injuries",

            "Promotes overall health and well-being\n" +
                    "Reduces risk of vitamin deficiencies\n" +
                    "Supports immune function and energy metabolism"
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0; i<packages.length; i++)
        {
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5", "Total Cost:"+packages[i][4]+"/-");
            list.add(item);

        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1", "line2", "line3", "line4", "line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

    }
}