package com.example.projectmanagement;

import android.content.Context;
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
import android.widget.ListView;

import java.util.ArrayList;

public class ProjectRoleByProjectListFragment extends Fragment {
    private Context context;
    ArrayList<String> listaZaduzenja;

    public ProjectRoleByProjectListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_project_role_by_project_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lstview = (ListView) view.findViewById(R.id.project_roles_by_person_list);

        PersonRolesByProjectInfoList personRolesByProjectInfoList = new PersonRolesByProjectInfoList(context, view);
        listaZaduzenja = personRolesByProjectInfoList.getList();

        PersonRolesByProjectListAdapter adapter=new PersonRolesByProjectListAdapter(context,
                R.layout.assignment_project_row,
                R.id.txt,
                listaZaduzenja);
        // Bind data to the ListView
        lstview.setAdapter(adapter);

        final NavController navController = Navigation.findNavController(view);

        Button personMasterDetailsBackButton = view.findViewById(R.id.person_master_details_back_button);
        personMasterDetailsBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_projectRoleByPersonListFragment_to_personMasterDetailFragment);
            }
        });
    }
}