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
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText prezimeOsobe, imeOsobe, OIBOsobe;
    Button pohrani;
    private Context context;
    DbHandler db, readDb;
    ArrayList<String> listaOsoba;

    public PersonAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonAddFragment newInstance(String param1, String param2) {
        PersonAddFragment fragment = new PersonAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();

        return inflater.inflate(R.layout.fragment_person_add, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prezimeOsobe = (EditText) view.findViewById(R.id.PrezimeUnos);
        imeOsobe = (EditText) view.findViewById(R.id.ImeUnos);
        OIBOsobe = (EditText) view.findViewById(R.id.OIBUnos);
        pohrani = (Button) view.findViewById(R.id.PohraniGumb);

        view.findViewById(R.id.PohraniGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String prezime = prezimeOsobe.getText().toString();
                String ime = imeOsobe.getText().toString();
                String OIB = OIBOsobe.getText().toString();

                Person novaOsoba = new Person(prezime, ime, OIB);

                Toast.makeText(context, "Dodana nova osoba" + prezime, Toast.LENGTH_LONG).show();

                db = new DbHandler(context);
                db.addOsoba(novaOsoba);
            }
        });

       /*
        if (getArguments() != null){
            PersonsFragmentArgs args = PersonsFragmentArgs.fromBundle(getArguments());

            String message = args.getTestArgument();
            Log.i(TAG, "Argument ------------------------> " + message);
        }*/

        final NavController navController = Navigation.findNavController(view);

        Button backToPersonsbutton = view.findViewById(R.id.PovratniGumb);
        backToPersonsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_personAddFragment_to_personsFragment);
            }
        });

    }
}