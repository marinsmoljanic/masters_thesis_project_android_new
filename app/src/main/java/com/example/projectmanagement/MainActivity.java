package com.example.projectmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void deletePersonClick(View view){
        Button bt=(Button)view;
        String osobaId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intOsobaId  = Integer.valueOf(osobaId);
        Toast.makeText(this, "Obrisana osoba",Toast.LENGTH_LONG).show();
        db.deleteOsoba(intOsobaId);
    }

    public void editPersonClick(View view){
        Button bt=(Button)view;
        String personId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intPersonId  = Integer.valueOf(personId);

        // POSPREMITI U BAZU TRENUTNO AKTIVNU OSOBU PREMA intPERSONId

        db.saveActivePerson(intPersonId);

        /*
        readDb.dropAndCreate();

        PersonRole novaUlogaOsobe = new PersonRole(1, 1, 1, "2020-11-11");
        readDb.addUlogaOsobe(novaUlogaOsobe);
        */

        final NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_personsFragment_to_personMasterDetailFragment);


        // StartFragmentDirections.ActionStartFragmentToPersonsFragment action = StartFragmentDirections.actionStartFragmentToPersonsFragment();
        // StartFragmentDirections.ActionStartFragmentToPersonsFragment action = StartFragmentDirections.actionStartFragmentToPersonsFragment();
        // action.setMessage("Neka poruka");
        // action.setTestArgument("MOj argument");
        // navController.navigate(action);
    }


    public void deleteRoleClick(View view){
        Button bt=(Button)view;
        String roleId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intRoleId  = Integer.valueOf(roleId);
        Toast.makeText(this, "Obrisana uloga", Toast.LENGTH_LONG).show();
        db.deleteRole(intRoleId);
    }

    public void editRoleClick(View view){
        Button bt=(Button)view;
        String roleId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intRoleId  = Integer.valueOf(roleId);

        // POSPREMITI U BAZU TRENUTNO AKTIVNU ULOGU PREMA intROLEId

        db.saveActiveRole(intRoleId);

        final NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_rolesFragment_to_roleEditFragment);
    }

    public void deleteProjectClick(View view){
        Button bt=(Button)view;
        String projectId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intProjectId  = Integer.valueOf(projectId);
        Toast.makeText(this, "Obrisan projekt", Toast.LENGTH_LONG).show();
        db.deleteProject(intProjectId);
    }

    public void editProjectClick(View view){
        String projectId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intProjectId  = Integer.valueOf(projectId);

        // POSPREMITI U BAZU TRENUTNO AKTIVNI PROJEKT PREMA intProjectId

        db.saveActiveProject(intProjectId);

        final NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_projectsFragment_to_projectMasterDetailsFragment);
    }

    public void deleteProjectRoleByPersonClick(View view){
        Button bt=(Button)view;
        String personRoleId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intPersonRoleId  = Integer.valueOf(personRoleId);
        Toast.makeText(this, "Obrisano zaduzenje: " + personRoleId, Toast.LENGTH_LONG).show();
        // db.deleteUlogaOsobe(intPersonRoleId);
    }

    public void editProjectRoleByPersonClick(View view){
        String projectId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intProjectId  = Integer.valueOf(projectId);

        // POSPREMITI U BAZU TRENUTNO AKTIVNO ZADUZENJE PREMA idZaduzenja

        db.saveActiveProject(intProjectId);

        final NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_projectsFragment_to_projectMasterDetailsFragment);
    }

    public void deleteProjectRoleByProjectClick(View view){
        Button bt=(Button)view;
        String personRoleId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intPersonRoleId  = Integer.valueOf(personRoleId);
        Toast.makeText(this, "Obrisano zaduzenje: " + personRoleId, Toast.LENGTH_LONG).show();
        // db.deleteUlogaOsobe(intPersonRoleId);
    }

    public void editProjectRoleByProjectClick(View view){
        String projectId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intProjectId  = Integer.valueOf(projectId);

        // POSPREMITI U BAZU TRENUTNO AKTIVNI PROJEKT PREMA idZaduzenja

        db.saveActiveProject(intProjectId);

        final NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_projectsFragment_to_projectMasterDetailsFragment);
    }


}