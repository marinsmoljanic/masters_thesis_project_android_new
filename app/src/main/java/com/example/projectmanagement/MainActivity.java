package com.example.projectmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
        String projectId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intProjectId  = Integer.valueOf(projectId);

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

    public void deleteProjectClick(View view){
        Button bt=(Button)view;
        String projectId = (String) view.getTag();
        DbHandler db = new DbHandler(this);
        int intProjectId  = Integer.valueOf(projectId);
        Toast.makeText(this, "Obrisan projekt", Toast.LENGTH_LONG).show();
        db.deleteProject(intProjectId);
    }
}