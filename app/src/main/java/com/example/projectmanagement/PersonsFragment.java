package com.example.projectmanagement;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class PersonsFragment extends Fragment {
    DbHandler readDb;
    private Context context;
    ArrayList<String> listaOsoba;
    ListView listView;
    Button povratak;

    private static final String TAG = "PersonsFragment";


    public PersonsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_persons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lstview = (ListView) view.findViewById(R.id.persons_list);
        //lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        //        Toast.makeText(context, "An item of the ListView is clicked.", Toast.LENGTH_LONG).show();
        //    }
        //});

        PersonInfoList osobaInfoList = new PersonInfoList(context, view);
        listaOsoba = osobaInfoList.getList();

        PersonsListAdapter adapter=new PersonsListAdapter(context,
                R.layout.fragment_person_row,
                R.id.txt,
                listaOsoba);
        // Bind data to the ListView
        lstview.setAdapter(adapter);

        /*
        if (getArguments() != null){
            PersonsFragmentArgs args = PersonsFragmentArgs.fromBundle(getArguments());

            String message = args.getTestArgument();
            Log.i(TAG, "Argument ------------------------> " + message);
        }
         */

        final NavController navController = Navigation.findNavController(view);

        Button personsBackButton = view.findViewById(R.id.persons_back_button);
        personsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_personsFragment_to_startFragment);
            }
        });

        Button addPersonButton = view.findViewById(R.id.add_new_person_button);
        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_personsFragment_to_personAddFragment);
            }
        });

    }
}