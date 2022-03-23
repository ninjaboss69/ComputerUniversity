package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudentSupport extends AppCompatActivity {
    ArrayList<Department> departments=new ArrayList<>();
    RecyclerView recyclerView,generalRecycleView,recyclerViewForServices;
    CardView expandableCardView,parentCardView,ucsmclinicCardivew,parentClinic,parentCardViewGeneral,goneViewGeneral,parentService,childService;
    ImageButton expandableImage,expandableClinic,expandableGeneral,expandableService;
    TextView clinicPhone,clinicLocation,clinicOpenHour;
    ArrayList<GeneralData> data=new ArrayList<>();
    SharedPreferences sharedPreferences;
    ArrayList<ServicesModel> servicesModels=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_support);
        sharedPreferences = this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show = sharedPreferences.getBoolean("auth", false);
        if (show == false) {
            CoverDialog coverDialog = new CoverDialog(this, false);
            coverDialog.showDialog();
        } else {
            recyclerView = findViewById(R.id.recyclerViewHum);
            recyclerViewForServices=findViewById(R.id.recyclerViewForServices);
            generalRecycleView = findViewById(R.id.recyclerViewGeneral);
            parentService=findViewById(R.id.parentCardViewForServices);
            childService=findViewById(R.id.childCardViewOfServices);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewForServices.setLayoutManager(layoutManager2);
            generalRecycleView.setLayoutManager(layoutManager1);
            expandableService=findViewById(R.id.expandableImageForServices);
            addDepartment("Rector Office", "09797111333106", "rectoroffice@ucsm.edu.mm");
            addDepartment("Student Affairs", "09797111333126", "studentaffairs@ucsm.edu.mm");
            addDepartment("Head of Student Affairs", "09797111333127", "sintsintaung@ucsm.edu.mm");
            addGeneralData("U Khin Mg", "091232422", "Cleaner");
            addGeneralData("U Zaw Win", "091232422", "Cleaner");
            addGeneralData("U Mg Naing", "091232422", "Librarian");
            addGeneralData("U Zaw Win", "091232422", "Night Duty");
            addGeneralData("U Mg Naing", "091232422", "Security");
            addService("Laundry Hostel Sabal \n Room 35 ","3.3","");
            addService("Laundry Hostel Sabal \n Room 47 ","4.6","");
            addService("Printing Main Building \n Room 55 ","5.0","");
            ServiceAdapter serviceAdapter=new ServiceAdapter(this,servicesModels);
            recyclerViewForServices.setAdapter(serviceAdapter);
            DepartmentAdapter adapter = new DepartmentAdapter(this, departments);
            GeneralAdapter adapter1 = new GeneralAdapter(this, data);
            recyclerView.setAdapter(adapter);
            generalRecycleView.setAdapter(adapter1);
            parentCardViewGeneral = findViewById(R.id.parentCardViewGeneral);
            goneViewGeneral = findViewById(R.id.goneCardViewGeneral);
            expandableGeneral = findViewById(R.id.expandableGeneral);
            expandableCardView = findViewById(R.id.noVisibleCardView);
            expandableCardView.setVisibility(View.GONE);
            expandableImage = findViewById(R.id.expandableImage);
            parentCardView = findViewById(R.id.parentCardView);
            clinicLocation = findViewById(R.id.clinicLocation);
            clinicOpenHour = findViewById(R.id.clinicTime);
            clinicPhone = findViewById(R.id.clinicPhoneNumber);
            expandableClinic = findViewById(R.id.expandableClinic);
            parentClinic = findViewById(R.id.parentClinic);
            ucsmclinicCardivew = findViewById(R.id.ucsmclinicCardView);
            setClinicData("097797111128", "Room B4", "9:00 - 10:00 (Week Day)");
            parentCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (expandableCardView.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(parentCardView, new AutoTransition());
                        expandableImage.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        expandableCardView.setVisibility(View.VISIBLE);
                    } else {
                        TransitionManager.beginDelayedTransition(parentCardView, new AutoTransition());
                        expandableCardView.setVisibility(View.GONE);
                        expandableImage.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }
                }
            });
            parentService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(childService.getVisibility()==View.GONE){
                        TransitionManager.beginDelayedTransition(parentService, new AutoTransition());
                        expandableService.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        childService.setVisibility(View.VISIBLE);
                        DisplayToast("hello");
                    }else{
                        TransitionManager.beginDelayedTransition(parentService,new AutoTransition());
                        expandableService.setImageResource(R.drawable.ic_baseline_expand_more_24);
                        childService.setVisibility(View.GONE);
                    }
                }
            });
            parentClinic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ucsmclinicCardivew.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(parentClinic, new AutoTransition());
                        expandableClinic.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        ucsmclinicCardivew.setVisibility(View.VISIBLE);
                    } else {
                        TransitionManager.beginDelayedTransition(parentClinic, new AutoTransition());
                        ucsmclinicCardivew.setVisibility(View.GONE);
                        expandableClinic.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }
                }
            });
            parentCardViewGeneral.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (goneViewGeneral.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(parentCardViewGeneral, new AutoTransition());
                        expandableGeneral.setImageResource(R.drawable.ic_baseline_expand_less_24);
                        goneViewGeneral.setVisibility(View.VISIBLE);
                    } else {
                        TransitionManager.beginDelayedTransition(parentCardViewGeneral, new AutoTransition());
                        goneViewGeneral.setVisibility(View.GONE);
                        expandableGeneral.setImageResource(R.drawable.ic_baseline_expand_more_24);
                    }
                }
            });


        }
    }

    private void DisplayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    void addDepartment(String departmentName,String phoneNumber,String emailAddress){
        Department department=new Department();
        department.setDepartmentName(departmentName);
        department.setPhoneNumber(phoneNumber);
        department.setEmailAddress(emailAddress);
        departments.add(department);
    }
    void addGeneralData(String name,String phoneNumber,String status){
        GeneralData generalData=new GeneralData();
        generalData.setName(name);
        generalData.setPhoneNumber(phoneNumber);
        generalData.setStatus(status);
        data.add(generalData);
    }
    void addService(String text,String rating,String image){
        ServicesModel model=new ServicesModel();
        model.setImage(image);
        model.setText(text);
        model.setRating(rating);
        servicesModels.add(model);

    }
    class DepartmentViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView departmentName,phoneNumber,emailAddress;
        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            departmentName=mView.findViewById(R.id.departmentName);
            phoneNumber=mView.findViewById(R.id.phoneNumber);
            emailAddress=mView.findViewById(R.id.emailAddress);
        }
    }
    class GeneralViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView name,phoneNumber,status;
        public GeneralViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            name=mView.findViewById(R.id.name);
            phoneNumber=mView.findViewById(R.id.phoneNumber);
            status=mView.findViewById(R.id.status);

        }
    }
    class GeneralAdapter extends RecyclerView.Adapter<GeneralViewHolder>{
        Context c;
        ArrayList<GeneralData> generalData;
        public GeneralAdapter(Context c,ArrayList<GeneralData> generalData){
            this.c=c;
            this.generalData=generalData;
        }

        @NonNull
        @Override
        public GeneralViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_general,parent,false);
            return new GeneralViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GeneralViewHolder holder, int position) {
                holder.name.setText(generalData.get(position).name);
                holder.phoneNumber.setText(generalData.get(position).phoneNumber);
                holder.phoneNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callNow(generalData.get(position).phoneNumber.trim());
                    }
                });
                holder.status.setText(generalData.get(position).status);
        }

        @Override
        public int getItemCount() {
            return generalData.size();
        }
    }
    class DepartmentAdapter extends RecyclerView.Adapter<DepartmentViewHolder>{


        Context c;
        ArrayList<Department> departments;
        public DepartmentAdapter(Context c, ArrayList<Department> departments){
            this.c=c;
            this.departments=departments;
        }

        @NonNull
        @Override
        public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_department,parent,false);
          return new DepartmentViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
            holder.departmentName.setText(departments.get(position).getDepartmentName());
            holder.phoneNumber.setText(departments.get(position).getPhoneNumber());
            holder.phoneNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callNow(departments.get(position).getPhoneNumber().trim());
                }
            });
            holder.emailAddress.setText(departments.get(position).getEmailAddress());
            holder.emailAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    emailNow(departments.get(position).getEmailAddress(),"","");
                }
            });
        }

        @Override
        public int getItemCount() {
            return departments.size();
        }
    }
    public void setClinicData(String phoneNumber,String location,String openHour){
        clinicPhone.setText(phoneNumber);
        clinicPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNow(phoneNumber.trim());
            }
        });
        clinicOpenHour.setText(openHour);
        clinicLocation.setText(location);
    }
   private void callNow(String phoneNumber){
        try{
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + phoneNumber.trim();
            i.setData(Uri.parse(p));
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Invalid Phone Number",Toast.LENGTH_SHORT).show();
        }
    }
    private void emailNow(String recipient,String subject,String message){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT,new String[]{subject});
        intent.putExtra(Intent.EXTRA_TEXT,new String[]{message});
        try{
            startActivity(Intent.createChooser(intent,"Please Describe Details"));
        }catch (Exception e){
            DisplayToast(e.getMessage());
        }
    }
    class ServicesHolder extends RecyclerView.ViewHolder{
        RatingBar serviceRating;
        ImageButton serviceImageButton;
        TextView servicesTextView;
        public ServicesHolder(@NonNull View itemView) {
            super(itemView);
            serviceRating=itemView.findViewById(R.id.serviceRating);
            serviceImageButton=itemView.findViewById(R.id.serviceImageButton);
            servicesTextView=itemView.findViewById(R.id.serviceTextView);
        }
    }
    class ServiceAdapter extends RecyclerView.Adapter<ServicesHolder>{
        Context c;
        ArrayList<ServicesModel> models;

        public ServiceAdapter(Context c, ArrayList<ServicesModel> models) {
            this.c = c;
            this.models = models;
        }

        @NonNull
        @Override
        public ServicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_cardview_services,parent,false);
            return new ServicesHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ServicesHolder holder, int position) {
           ServicesModel model=models.get(position);
            holder.serviceRating.setRating(Float.parseFloat(model.getRating()));
            holder.servicesTextView.setText(model.getText());
            holder.serviceImageButton.setImageResource(R.drawable.aggotalgo);
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }
}