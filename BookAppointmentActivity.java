package com.example.healthcare;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton,btnBook,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);
        //mapping
        dateButton = findViewById(R.id.buttonAppDate);
        timeButton = findViewById(R.id.buttonAppTime);

        btnBook = findViewById(R.id.buttonBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        ed1.setKeyListener(null); //users can not type this fields
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        //fetching data from previous activity
        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String Address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        //setting data to textview and edittext
        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(Address);
        ed3.setText(contact);
        ed4.setText("Cons Fees:"+fees+"/-");

        //datepicker
        initDatePicker();

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //timePicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                if(db.checkAppoinmentExists(username,title+" => "+fullname,Address,contact,dateButton.getText().toString(),timeButton.getText().toString())==1)
                {
                    Toast.makeText(getApplicationContext(),"Appoinment already booked",Toast.LENGTH_LONG).show();
                }
                else{
                    db.addOrder(username, title+" => "+fullname,Address,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(fees),"appointment");
                    Toast.makeText(getApplicationContext(),"Your Appoinment is done successfully",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));

                }

            }
        });


    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i+"/"+i1+"/"+i2);
            }
        };
        Calendar cal = Calendar.getInstance();
        //we set here current date
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = android.R.style.Theme_Material_Dialog_Alert; // Dark theme
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

        //  Change button colors here
        datePickerDialog.setOnShowListener(dialog -> {
            int tealColor = Color.parseColor("#00BCD4"); // or ContextCompat.getColor(this, R.color.teal_button);
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(tealColor); // OK
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(tealColor); // CANCEL
        });

    }
    private  void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeButton.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mints = cal.get(Calendar.MINUTE);

        int style = android.R.style.Theme_Material_Dialog_Alert; // Dark theme
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mints, true);

        //  Change button colors here
        timePickerDialog.setOnShowListener(dialog -> {
            int tealColor = Color.parseColor("#00BCD4"); // or ContextCompat.getColor(this, R.color.teal_button);
            timePickerDialog.getButton(TimePickerDialog.BUTTON_POSITIVE).setTextColor(tealColor); // OK
            timePickerDialog.getButton(TimePickerDialog.BUTTON_NEGATIVE).setTextColor(tealColor); // CANCEL
        });
    }
}