package com.example.projectmanagement;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoleAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoleAddFragment extends Fragment {
    EditText nazUloge;
    Button pohrani;
    private Context context;
    DbHandler db;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RoleAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UlogaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoleAddFragment newInstance(String param1, String param2) {
        RoleAddFragment fragment = new RoleAddFragment();
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
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_role_add, container, false); }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nazUloge = (EditText) view.findViewById(R.id.NazivUlogeUnos);
        pohrani = (Button) view.findViewById(R.id.PohraniGumb);

        view.findViewById(R.id.PohraniGumb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _nazUloge = nazUloge.getText().toString();

                Role novaUloga = new Role(_nazUloge);

                db = new DbHandler(context);
                db.addUloga(novaUloga);

                Toast.makeText(context, "Dodana nova uloga" + _nazUloge, Toast.LENGTH_LONG).show();

                NavHostFragment.findNavController(RoleAddFragment.this)
                        .navigate(R.id.action_ulogaFragment_to_rolesFragment); }});


        final NavController navController = Navigation.findNavController(view);

        Button rolesBackButton = view.findViewById(R.id.PovratniGumb);
        rolesBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_ulogaFragment_to_rolesFragment);
            }});
    }
}