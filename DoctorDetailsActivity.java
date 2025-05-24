package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name : M.S. Selimuzzaman","Hospital Address : Rupnagar Abashik","Experience : 10yrs","Mobile: 01717-123456","600"},
            {"Doctor Name : M. Anisur Rahman","Hospital Address : Mirpur 1","Experience : 15yrs","Mobile: 01717-673493","800"},
            {"Doctor Name : Kaniz Fatema Anonna","Hospital Address : Rupnagar Abashik","Experience : 5yrs","Mobile: 01717-123468","700"},
            {"Doctor Name : Ayesha Siddika","Hospital Address : Mirpur 2","Experience : 12yrs","Mobile: 01717-983476","700"},
            {"Doctor Name : Farhana Rahat","Hospital Address : Rupnagar Abashik","Experience : 8yrs","Mobile: 01717-353490","600"}
    };
    private String[][] doctor_details2 = {
            {"Doctor Name : Emran Ahmed","Hospital Address : Shymoli","Experience : 10yrs","Mobile: 01717-123456","600"},
            {"Doctor Name : Sumaiya Rahman","Hospital Address : Dhanmondi","Experience : 15yrs","Mobile: 01717-673493","800"},
            {"Doctor Name : M.A. Momen Khan","Hospital Address : Rupnagar Abashik","Experience : 14yrs","Mobile: 01717-123468","700"},
            {"Doctor Name : M. Asadujjaman","Hospital Address : Mirpur 2","Experience : 12yrs","Mobile: 01717-983476","700"},
            {"Doctor Name : Masud Reza","Hospital Address : Rupnagar Abashik","Experience : 8yrs","Mobile: 01717-353490","600"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : M. Nabid Alam","Hospital Address : Rupnagar Abashik","Experience : 10yrs","Mobile: 01717-123456","600"},
            {"Doctor Name : Tofayel Ahamed","Hospital Address : Mirpur 1","Experience : 15yrs","Mobile: 01717-673493","800"},
            {"Doctor Name : Kaniz Fatema","Hospital Address : Rupnagar Abashik","Experience : 5yrs","Mobile: 01717-123468","700"},
            {"Doctor Name : Ayesha Rahman","Hospital Address : Mirpur 2","Experience : 12yrs","Mobile: 01717-983476","700"},
            {"Doctor Name : Farhana Khan","Hospital Address : Rupnagar Abashik","Experience : 8yrs","Mobile: 01717-353490","600"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Mithila Akhter","Hospital Address : Rupnagar Abashik","Experience : 10yrs","Mobile: 01717-123456","600"},
            {"Doctor Name : Farhana Islam Doli","Hospital Address : Mirpur 1","Experience : 15yrs","Mobile: 01717-673493","800"},
            {"Doctor Name : Amatus Salam Nimmi","Hospital Address : Rupnagar Abashik","Experience : 5yrs","Mobile: 01717-123468","700"},
            {"Doctor Name : Shamsun Nahar","Hospital Address : Mirpur 2","Experience : 12yrs","Mobile: 01717-983476","700"},
            {"Doctor Name : Sultan Mahmud","Hospital Address : Rupnagar Abashik","Experience : 8yrs","Mobile: 01717-353490","600"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : M. Jahid Firdous","Hospital Address : Rupnagar Abashik","Experience : 10yrs","Mobile: 01717-123456","600"},
            {"Doctor Name : Abdullah Al Mamun","Hospital Address : Mirpur 1","Experience : 15yrs","Mobile: 01717-673493","900"},
            {"Doctor Name : Susanto Kumar Saha","Hospital Address : Rupnagar Abashik","Experience : 5yrs","Mobile: 01717-123468","500"},
            {"Doctor Name : M. Al Amin","Hospital Address : Mirpur 2","Experience : 12yrs","Mobile: 01717-983476","700"},
            {"Doctor Name : M. Abdul Mazid","Hospital Address : Rupnagar Abashik","Experience : 8yrs","Mobile: 01717-353490","700"}
    };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
        {
            doctor_details = doctor_details1;
        }
        else if(title.compareTo("Dietician")==0)
        {
            doctor_details = doctor_details2;
        }
        else if(title.compareTo("Dentist")==0)
        {
            doctor_details = doctor_details3;
        }
        else if(title.compareTo("Surgeon")==0)
        {
            doctor_details = doctor_details4;
        }
        else
        {
            doctor_details = doctor_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees : \n"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines, //resources
                new String[]{"line1","line2","line3","line4","line5"}, //from
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e} //to
        );
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,int i,long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                //experience no need . so index 2 is skipped.
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}