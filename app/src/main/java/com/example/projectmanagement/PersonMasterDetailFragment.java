package com.example.projectmanagement;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PersonMasterDetailFragment extends Fragment {
    public PersonMasterDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_master_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                /*
        if (getArguments() != null){
            PersonsFragmentArgs args = PersonsFragmentArgs.fromBundle(getArguments());

            String message = args.getTestArgument();
            Log.i(TAG, "Argument ------------------------> " + message);
        }

        action_personMasterDetailFragment_to_personRoleAddFragment
         */

        final NavController navController = Navigation.findNavController(view);

        Button personsBackButton = view.findViewById(R.id.DodajNovoZaduzenje);
        personsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_personMasterDetailFragment_to_personRoleAddFragment);
            }
        });

    }
}