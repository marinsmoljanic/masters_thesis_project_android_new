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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class ProjectRoleByPersonUpdateFragment extends Fragment {
    private Context context;

    ArrayList<String> listaProjekata;
    ArrayList<String> listaProjekataFiltered  = new ArrayList<>();
    final HashMap<String, String> projectsMap = new HashMap<String, String>();

    ArrayList<String> listaUloga;
    ArrayList<String> listaUlogaFiltered  = new ArrayList<>();
    final HashMap<String, String> rolesMap = new HashMap<String, String>();

    public ProjectRoleByPersonUpdateFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_project_role_by_person_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);

        DbHandler db = new DbHandler(context);

        String activePersonRoleTag = db.readActivePersonRole();
        String[] parts = activePersonRoleTag.split(";");

        String sifProjekta = parts[0];
        String idOsobe = parts[1];
        String idUloge = parts[2];
        String personName = parts[3];

        final Spinner projektSpinner = (Spinner) view.findViewById(R.id.SifProjektaUnos);
        final Spinner ulogaSpinner = (Spinner) view.findViewById(R.id.IdUlogeUnos);

        ProjectInfoList projectInfoList = new ProjectInfoList(context, view);
        listaProjekata = projectInfoList.getList();


        for (int i=0; i<listaProjekata.size(); i++){
            String[] partsProjects = listaProjekata.get(i).split(";");
            String nazivProjekta = partsProjects[1];
            projectsMap.put(partsProjects[1], partsProjects[0]);
            listaProjekataFiltered.add(nazivProjekta);
        }

        RoleInfoList roleInfoList = new RoleInfoList(context, view);
        listaUloga = roleInfoList.getList();

        for (int i=0; i<listaUloga.size(); i++){
            String[] partsRoles = listaUloga.get(i).split(";");
            String nazivUloge = partsRoles[1];
            rolesMap.put(partsRoles[1], partsRoles[0]);
            listaUlogaFiltered.add(nazivUloge);
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spinnerArrayAdapterProjects = new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, listaProjekataFiltered);
        ArrayAdapter<String> spinnerArrayAdapterRoles = new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, listaUlogaFiltered);

        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapterProjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArrayAdapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        projektSpinner.setAdapter(spinnerArrayAdapterProjects);
        ulogaSpinner.setAdapter(spinnerArrayAdapterRoles);


        TextView nazivOsobe = view.findViewById(R.id.nazivOsobe);
        nazivOsobe.setText(personName);

        Button updatePersonRole = view.findViewById(R.id.PohraniGumb);
        updatePersonRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_projectRoleByPersonUpdateFragment_to_projectRoleByPersonListFragment);
            }
        });
    }
}