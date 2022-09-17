package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link mainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class mainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public mainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment mainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static mainFragment newInstance(String param1, String param2) {
        mainFragment fragment = new mainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    DrawerLayout drawerLayout;
    String[] gridData={"Mentor","Student","Club","Canteen","Timetable","Result","Calendar","Top"};
    CardView onlineCourses;
    Button todo,event,support;
    NavigationView navigationView;
    SharedPreferences sharedPreferences;
    String promotionList="",token="dardardee";
    ArrayList<HeaderTopicModel> list=new ArrayList<>();
    ArrayList<helperHeadTopic> niceModel=new ArrayList<>();
    TextView userNameTextView,announcementTextView;
    Button shopButton,rateButton;
    int[] imageData={R.drawable.teachercolor,R.drawable.studentcolor,R.drawable.club,R.drawable.canteencolor,R.drawable.timetable,R.drawable.result1,R.drawable.calendar,R.drawable.topgrader};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_main, container, false);
        GridView gridView=view.findViewById(R.id.grid_view);
        todo=view.findViewById(R.id.todo);
        userNameTextView=view.findViewById(R.id.userNameTextView);
        announcementTextView=view.findViewById(R.id.announcementTextView);
        announcementTextView.setSelected(true);
        //announcementTextView.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.left_to_right));

        sharedPreferences =getContext().getSharedPreferences("Main", Context.MODE_PRIVATE);
        String mainString=sharedPreferences.getString("userName","");
        if(mainString.length()!=0){
            userNameTextView.setText(mainString);
        }
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),todolist.class));
            }
        });
        onlineCourses=view.findViewById(R.id.onlineCourses);
        onlineCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        getFirebaseMessagingToken();
        shopButton=view.findViewById(R.id.shopButton);
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all","Fuck Me","Read 18+ news on the blah blah blah . Click to see more.",getContext(),
                      //  getActivity());
                //notificationsSender.SendNotifications();
                startActivity(new Intent(getContext(),UCSMShop.class));

            }
        });
        rateButton=view.findViewById(R.id.rateButton);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FcmNotificationsSender notificationsSender=new FcmNotificationsSender("/topics/all","English","Unit 3 \n 1.1 To Read \n 1.5 To Answer" +
                        "1.5 to practice listening \n https://www.facebook.com/sailaminoak",getContext(),
                        getActivity());
                notificationsSender.SendNotifications();
            }
        });
        sharedPreferences =getActivity().getSharedPreferences("Main", Context.MODE_PRIVATE);
        event=view.findViewById(R.id.eventCardView);
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Announcement.class));
            }
        });
        support=view.findViewById(R.id.supportCardview);
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),StudentSupport.class));
            }
        });
        onlineCourses=view.findViewById(R.id.onlineCourses);
        onlineCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),courses_online.class));
            }
        });
        RecyclerView recyclerView=view.findViewById(R.id.recy);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
       /* HeaderTopicModel model=new HeaderTopicModel("hello 1","hello 2 abcdejalsdjlajldals dfklajslfjad");
        HeaderTopicModel model1=new HeaderTopicModel("hello 3","hello 4s sljlajsldjfaljsd");
        HeaderTopicModel model2=new HeaderTopicModel("hello 1","hello 2sdfjslfs");
        HeaderTopicModel model3=new HeaderTopicModel("hello 3","hello");
        ArrayList<HeaderTopicModel> list=new ArrayList<>();
        list.add(model);
        list.add(model1);
        list.add(model2);
        list.add(model3);
        */

        FirebaseDatabase.getInstance().getReference("Promotion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    promotionList = snapshot.getValue(String.class);
                    String[] promoteList = promotionList.split(",");
                    for (int i = 0; i < promoteList.length; i++) {
                        if (i == 6) {
                            break;
                        }
                        String[] eachData = promoteList[i].split("#");
                        HeaderTopicModel model = new HeaderTopicModel(eachData[0], eachData[2]);
                        list.add(model);
                        helperHeadTopic model00 = new helperHeadTopic(eachData[2], eachData[0], eachData[1]);
                        niceModel.add(model00);

                    }

                headerTopicAdapter adapter = new headerTopicAdapter(getContext(), list, niceModel);
                recyclerView.setAdapter(adapter);
                }catch (Exception e){

                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //headerTopicAdapter adapter=new headerTopicAdapter(getContext(),list);
       // recyclerView.setAdapter(adapter);
        Toolbar toolbar=view.findViewById(R.id.toolbar);
        drawerLayout=view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.open_navigation,R.string.close_navigation);
        drawerLayout.addDrawerListener(toogle);
        navigationView=view.findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        MenuItem nav_verify=menu.findItem(R.id.nav_verification);
        if(sharedPreferences.getBoolean("auth",false)==true){
            nav_verify.setIcon(R.drawable.check);
            nav_verify.setTitle("Already Verified!");

        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_favorite:
                        drawerLayout.closeDrawer(Gravity.LEFT,false);
                        startActivity(new Intent(getContext(),favoritePostsInformation.class));
                        break;
                    case R.id.nav_setting:
                        drawerLayout.closeDrawer(Gravity.LEFT,false);
                        startActivity(new Intent(getContext(),Setting.class));
                        break;
                    case R.id.nav_software:
                        drawerLayout.closeDrawer(Gravity.LEFT,false);
                        startActivity(new Intent(getContext(),AboutSoftware.class));
                        break;
                    case R.id.nav_rate:
                        drawerLayout.closeDrawer(Gravity.LEFT,false);
                        startActivity(new Intent(getContext(),UCSMShop.class));
                        break;
                    default:
                        if(sharedPreferences.getBoolean("auth",false)==true){
                            displayToast("Already Verify");
                        }
                       else {
                            drawerLayout.closeDrawer(Gravity.LEFT, false);
                            startActivity(new Intent(getContext(), Verification.class));
                        }
                        break;
                }
                return  false;
            }
        });
        toogle.syncState();
        MainAdapter mainAdapter=new MainAdapter(getContext(),gridData,imageData);
        gridView.setAdapter(mainAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0)
                        startActivity(new Intent(getActivity(),Mentor_Information.class));
                    if(position==1)
                        startActivity(new Intent(getActivity(),Student_Information.class));
                    if(position==2)
                        startActivity(new Intent(getActivity(),Club.class));
                    if(position==3)
                        startActivity(new Intent(getActivity(),Canteen.class));
                    if(position==6)
                         startActivity(new Intent(getActivity(),AcademicCalendar.class));
                    if(position==4)
                        startActivity(new Intent(getActivity(),WeekViewActivity.class));
                   if(position==5)
                         startActivity(new Intent(getActivity(),Result.class));
                   if(position==7)
                       startActivity(new Intent(getActivity(),Tops.class));
            }
        });
        return view;
    }
    void DisplayToast(String msg){
        Toast.makeText(this.getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
    public static class headerTopicViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView headerTitle,description;
        MaterialCardView cardView;
        public headerTopicViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            headerTitle =mView.findViewById(R.id.h);
            description=mView.findViewById(R.id.d);
            cardView=mView.findViewById(R.id.wholePage);
        }
    }
    public class headerTopicAdapter extends RecyclerView.Adapter<headerTopicViewHolder>{
        Context c;
        ArrayList<HeaderTopicModel> models;
        ArrayList<helperHeadTopic> nice00;
        public headerTopicAdapter(Context c, ArrayList<HeaderTopicModel> models,ArrayList<helperHeadTopic> nice00){
            this.c=c;
            this.models=models;
            this.nice00=nice00;
        }
        @NonNull
        @Override
        public headerTopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.promotion_tab, parent, false);
            return  new headerTopicViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull headerTopicViewHolder holder, int position) {
            setScaleAnimation(holder.itemView);

           holder.headerTitle.setText(models.get(position).header);
           holder.description.setText(models.get(position).description);
          // holder.cardView.setBackgroundResource(R.drawable.card_view_border);
           holder.cardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                  String tableName=nice00.get(position).tableName;
                  String postName=nice00.get(position).name;
                  String categoryName=nice00.get(position).getCategory();
                   Intent intent=new Intent(getActivity(),Read.class);
                   intent.putExtra("t",tableName);
                   intent.putExtra("c",categoryName);
                   intent.putExtra("n",postName);
                   startActivity(intent);
               }
           });

        }
        @Override
        public int getItemCount() {
            return models.size();
        }
    }
    private void displayToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private final static int FADE_DURATION = 1000;
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    public void getFirebaseMessagingToken ( ) {
        FirebaseMessaging.getInstance ().getToken ()
                .addOnCompleteListener ( task -> {
                    if (!task.isSuccessful ()) {
                        //Could not get FirebaseMessagingToken
                       // displayToast("no token available");
                        return;
                    }
                    if (null != task.getResult ()) {
                        //Got FirebaseMessagingToken
                        String firebaseMessagingToken = Objects.requireNonNull ( task.getResult () );
                        token=firebaseMessagingToken;

                        FirebaseDatabase.getInstance().getReference("userToken").getRef().setValue(token).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                              //  DisplayToast("success");
                            }
                        });
                        //Log.d("token",token);
                        //Use firebaseMessagingToken further
                    }
                } );
    }
    String getRandomText(int length){
        char[] ch="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLNMOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<length;i++){
            sb.append(ch[ThreadLocalRandom.current().nextInt(0,52)]);
        }
        return sb.toString();
    }
}