package com.sailaminoak.computeruniversity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PendingOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PendingOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PendingOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PendingOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PendingOrderFragment newInstance(String param1, String param2) {
        PendingOrderFragment fragment = new PendingOrderFragment();
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
    RecyclerView pendingOrderRecyclerView;
    PendingOrderAdapter adapter;
    ArrayList<PendingOrderData> models=new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_pending_order, container, false);
        String flag=this.getArguments().getString("history");
        pendingOrderRecyclerView=view.findViewById(R.id.pendingOrderRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        pendingOrderRecyclerView.setLayoutManager(layoutManager);
        if(flag==null || flag.length()==0 || flag.equals("noflag")){

            models=new ArrayList<>();
            //Example Model
            PendingOrderData pendingOrderData=new PendingOrderData("Sai La Min Oak","1500","1","url of image","Room B2,T-7,UCSM Canteen","yes");
            PendingOrderData pendingOrderData1=new PendingOrderData("Sai La Min Oak","1500","1","url of image","Room B2,66,Male Hostle","yes");
            PendingOrderData pendingOrderData2=new PendingOrderData("Sai La Min Oak","1500","1","url of image","Room B2,T-7,Femal Hostle","yes");
            models.add(pendingOrderData);
            models.add(pendingOrderData1);
            models.add(pendingOrderData2);
            models.add(pendingOrderData);
            models.add(pendingOrderData1);
            models.add(pendingOrderData2);
            adapter=new PendingOrderAdapter(getContext(),models);
            pendingOrderRecyclerView.setAdapter(adapter);
        }else{
            models=new ArrayList<>();
            PendingOrderData pendingOrderData=new PendingOrderData("Sai La Min Oak-History","150","1","url of image","Delivered-Room B2,T-7,UCSM Canteen","no");
            PendingOrderData pendingOrderData1=new PendingOrderData("Sai La Min Oak-History","1120","2","url of image","Delivered-Room B2,66,Male Hostle","no");
            PendingOrderData pendingOrderData2=new PendingOrderData("Sai La Min Oak-History","1160","3","url of image","Delivered","no");
            models.add(pendingOrderData);
            models.add(pendingOrderData1);
            models.add(pendingOrderData2);
            models.add(pendingOrderData);
            models.add(pendingOrderData1);
            models.add(pendingOrderData2);
            adapter=new PendingOrderAdapter(getContext(),models);
            pendingOrderRecyclerView.setAdapter(adapter);
        }

        return  view;
    }
}