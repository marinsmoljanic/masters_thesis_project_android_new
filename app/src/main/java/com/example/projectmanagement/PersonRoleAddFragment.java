package com.example.projectmanagement;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class PersonRoleAddFragment extends Fragment {
    DatePicker datDodjele;
    Button pohrani;
    private Context context;
    DbHandler db;


    public PersonRoleAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_person_role_add, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // sifProjekta = (EditText) view.findViewById(R.id.SifProjektaUnos);

        Spinner sifProjektaSpinner = (Spinner) view.findViewById(R.id.SifProjektaUnos);
        Spinner osobaSpinner = (Spinner) view.findViewById(R.id.IdOsobeUnos);
        Spinner ulogaSpinner = (Spinner) view.findViewById(R.id.IdUlogeUnos);
        datDodjele = (DatePicker) view.findViewById(R.id.DatDodjeleUnos);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterProjekti = ArrayAdapter.createFromResource(context,
                R.array.projekti, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterOsobe = ArrayAdapter.createFromResource(context,
                R.array.osobe, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterUloge = ArrayAdapter.createFromResource(context,
                R.array.uloge, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterProjekti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterOsobe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterUloge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        sifProjektaSpinner.setAdapter(adapterProjekti);
        osobaSpinner.setAdapter(adapterOsobe);
        ulogaSpinner.setAdapter(adapterUloge);

        // spinner.setOnItemSelectedListener();

        datDodjele = (DatePicker) view.findViewById(R.id.DatDodjeleUnos);

        pohrani = (Button) view.findViewById(R.id.PohraniGumb);

        view.findViewById(R.id.PohraniGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int _sifProjekta = Integer.parseInt(sifProjekta.getText().toString());
                //int _idOsobe = Integer.parseInt(idOsobe.getText().toString());
                //int _idUloge = Integer.parseInt(idUloge.getText().toString());
                //int _datDodjele = Integer.parseInt(datDodjele.getText().toString());

                // TU JE SADA U VARIJABLE POTREBNO IZVUCI ODABRANE VRIJEDNOSTI

                // UlogaOsobe novaUlogaOsobe = new UlogaOsobe(_sifProjekta, _idOsobe, _idUloge, _datDodjele);

                // db = new DbHandler(context);
                // db.addUlogaOsobe(novaUlogaOsobe);

                Toast.makeText(context, "Dodana nova uloga osobe", Toast.LENGTH_LONG).show();


            }

        });


    }
}