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
import android.widget.EditText;
import android.widget.TextView;


public class ProjectMasterDetailsFragment extends Fragment {
    private Context context;

    public ProjectMasterDetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_project_master_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        DbHandler db = new DbHandler(context);

        int activeProjectId = db.readActiveProject();
        String projekt = db.getProjekt(activeProjectId);

        String[] parts = projekt.split(";");

        String imeProjekta = parts[1];
        String opisProjekta = parts[2];
        String datumPocetka = parts[3];
        String datumZavrsetka = parts[4];

        EditText editTextNaziv = view.findViewById(R.id.NazivUnos);
        EditText editTextOpis = view.findViewById(R.id.OpisUnos);
        TextView datumPocetkaText = view.findViewById(R.id.trenutniDatumPocetka);
        TextView datumZavrsetkaText = view.findViewById(R.id.trenutniDatumZavrsetka);

        editTextNaziv.setText(imeProjekta);
        editTextOpis.setText(opisProjekta);

        datumPocetkaText.setText(datumPocetkaText.getText().toString() + datumPocetka);
        datumZavrsetkaText.setText(datumZavrsetkaText.getText().toString() + datumZavrsetka);


        Button personRoleButton = view.findViewById(R.id.DodajNovoZaduzenje);
        personRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_projectMasterDetailsFragment_to_personRoleAddFragment);
            }
        });

        Button otvoriListuZaduzenja = view.findViewById(R.id.OtvoriListuZaduzenjaProjekta);
        otvoriListuZaduzenja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_projectMasterDetailsFragment_to_projectRoleByProjectListFragment);
            }
        });
    }
}