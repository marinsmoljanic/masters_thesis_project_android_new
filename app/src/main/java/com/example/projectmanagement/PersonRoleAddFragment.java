package com.example.projectmanagement;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class PersonRoleAddFragment extends Fragment {
    DatePicker datDodjele;
    Button pohrani;
    private Context context;
    DbHandler db;

    ArrayList<String> listaOsoba;
    ArrayList<String> listaOsobaFiltered = new ArrayList<>();
    final HashMap<String, String> personsMap = new HashMap<String, String>();

    ArrayList<String> listaProjekata;
    ArrayList<String> listaProjekataFiltered  = new ArrayList<>();
    final HashMap<String, String> projectsMap = new HashMap<String, String>();

    ArrayList<String> listaUloga;
    ArrayList<String> listaUlogaFiltered  = new ArrayList<>();
    final HashMap<String, String> rolesMap = new HashMap<String, String>();


    public PersonRoleAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_person_role_add, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Spinner projektSpinner = (Spinner) view.findViewById(R.id.SifProjektaUnos);
        final Spinner osobaSpinner = (Spinner) view.findViewById(R.id.IdOsobeUnos);
        final Spinner ulogaSpinner = (Spinner) view.findViewById(R.id.IdUlogeUnos);
        datDodjele = (DatePicker) view.findViewById(R.id.DatDodjeleUnos);

        ProjectInfoList projectInfoList = new ProjectInfoList(context, view);
        listaProjekata = projectInfoList.getList();


        for (int i=0; i<listaProjekata.size(); i++){
            String[] parts = listaProjekata.get(i).split(";");
            String nazivProjekta = parts[1];
            projectsMap.put(parts[1], parts[0]);
            listaProjekataFiltered.add(nazivProjekta);
        }

        PersonInfoList personInfoList = new PersonInfoList(context, view);
        listaOsoba = personInfoList.getList();

        for (int i=0; i<listaOsoba.size(); i++){
            String[] parts = listaOsoba.get(i).split(";");
            String nazivOsobe = parts[1] + " " + parts[2];
            personsMap.put(parts[1] + " " + parts[2], parts[0]);
            listaOsobaFiltered.add(nazivOsobe);
        }

        RoleInfoList roleInfoList = new RoleInfoList(context, view);
        listaUloga = roleInfoList.getList();

        for (int i=0; i<listaUloga.size(); i++){
            String[] parts = listaUloga.get(i).split(";");
            String nazivUloge = parts[1];
            rolesMap.put(parts[1], parts[0]);
            listaUlogaFiltered.add(nazivUloge);
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> spinnerArrayAdapterPersons = new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, listaOsobaFiltered);
        ArrayAdapter<String> spinnerArrayAdapterProjects = new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, listaProjekataFiltered);
        ArrayAdapter<String> spinnerArrayAdapterRoles = new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, listaUlogaFiltered);

        // Specify the layout to use when the list of choices appears
        spinnerArrayAdapterPersons.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArrayAdapterProjects.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerArrayAdapterRoles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        osobaSpinner.setAdapter(spinnerArrayAdapterPersons);
        projektSpinner.setAdapter(spinnerArrayAdapterProjects);
        ulogaSpinner.setAdapter(spinnerArrayAdapterRoles);

        datDodjele = (DatePicker) view.findViewById(R.id.DatDodjeleUnos);
        pohrani = (Button) view.findViewById(R.id.PohraniGumb);

        view.findViewById(R.id.PohraniGumb).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                String _nazivProjekta = projektSpinner.getSelectedItem().toString();
                String _nazivOsobe = osobaSpinner.getSelectedItem().toString();
                String _nazivUloge = ulogaSpinner.getSelectedItem().toString();
                int month = datDodjele.getMonth() + 1;

                String _datDodjele = datDodjele.getDayOfMonth() + "." + month + "." + datDodjele.getYear();
                String projectId = Objects.requireNonNull(projectsMap.get(_nazivProjekta));
                Integer projectIdInt = Integer.parseInt(projectId);
                String personId = Objects.requireNonNull(personsMap.get(_nazivOsobe));
                Integer personIdInt = Integer.parseInt(personId);
                String roleId = Objects.requireNonNull(rolesMap.get(_nazivUloge));
                Integer roleIdInt = Integer.parseInt(roleId);

                // TU JE SADA U VARIJABLE POTREBNO IZVUCI ODABRANE VRIJEDNOSTI

                PersonRole novaUlogaOsobe = new PersonRole(projectIdInt, personIdInt, roleIdInt, _datDodjele);

                db = new DbHandler(context);
                db.addUlogaOsobe(novaUlogaOsobe);

                Toast.makeText(context, "Dodano novo zaduzenje", Toast.LENGTH_LONG).show();
            }

        });
    }
}