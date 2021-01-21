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

public class PersonMasterDetailFragment extends Fragment {

    // ISCITAM IZ BAZE TRENUTNO AKTIVNU OSOBU
    private Context context;

    public PersonMasterDetailFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
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

        DbHandler db = new DbHandler(context);

        int activePersonId = db.readActivePerson();
        String osoba = db.getOsoba(activePersonId);

        String[] parts = osoba.split(";");

        String idOsobe = parts[0];
        String imeOsobe = parts[1];
        String prezimeOsobe = parts[2];
        String oibOsobe = parts[3];

        EditText editTextPrezime = view.findViewById(R.id.PrezimeUnos);
        EditText editTextIme = view.findViewById(R.id.ImeUnos);
        TextView myAwesomeTextView = (TextView)view.findViewById(R.id.OIB);

        editTextPrezime.setText(prezimeOsobe);
        editTextIme.setText(imeOsobe);
        myAwesomeTextView.setText(oibOsobe);

        Button dodajNovoZaduzenje = view.findViewById(R.id.DodajNovoZaduzenje);
        dodajNovoZaduzenje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_personMasterDetailFragment_to_personRoleAddFragment);
            }
        });

        Button otvoriListuZaduzenja = view.findViewById(R.id.OtvoriListuZaduzenjaOsobe);
        otvoriListuZaduzenja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_personMasterDetailFragment_to_projectRoleByPersonListFragment);
            }
        });

    }
}