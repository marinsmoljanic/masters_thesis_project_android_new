package com.example.projectmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText nazProjekta, opisProjekta;
    DatePicker datPocetka, datZavrsetka;
    Button pohrani;
    private Context context;
    DbHandler db;

    public ProjectAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectAddFragment newInstance(String param1, String param2) {
        ProjectAddFragment fragment = new ProjectAddFragment();
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
        return inflater.inflate(R.layout.fragment_project_add, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nazProjekta = (EditText) view.findViewById(R.id.NazivUnos);
        opisProjekta = (EditText) view.findViewById(R.id.OpisUnos);
        datPocetka = (DatePicker) view.findViewById(R.id.DatPocetkaUnos);
        datZavrsetka = (DatePicker) view.findViewById(R.id.DatZavrsetkaUnos);

        pohrani = (Button) view.findViewById(R.id.PohraniGumb);

        view.findViewById(R.id.PohraniGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _nazProjekta = nazProjekta.getText().toString();
                String _opisProjekta = opisProjekta.getText().toString();
                Date datumPocetka = parseDatePicker(datPocetka);
                Date datumZavrsetka = parseDatePicker(datZavrsetka);

                Project noviProjekt = new Project(_nazProjekta, _opisProjekta, datumPocetka, datumZavrsetka);

                db = new DbHandler(context);

                db.addProjekt(noviProjekt);

                Toast.makeText(context, "Dodan novi projekt", Toast.LENGTH_LONG).show();

                NavHostFragment.findNavController(ProjectAddFragment.this)
                        .navigate(R.id.action_projectAddFragment_to_projectsFragment);
            }

        });



        view.findViewById(R.id.PovratniGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProjectAddFragment.this)
                        .navigate(R.id.action_projectAddFragment_to_projectsFragment);
            }
        });
    }

    private Date parseDatePicker(DatePicker datePicker){
        String datumDan = String.valueOf(datePicker.getDayOfMonth());
        String datumMjesec = String.valueOf(datePicker.getMonth() + 1);
        String datumGodina = String.valueOf(datePicker.getYear());

        Date datum = new Date();

        try {
            datum=new SimpleDateFormat("dd/MM/yyyy").parse(format("%s/%s/%s",
                    datumDan, datumMjesec, datumGodina));


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return datum;
    }

}