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

public class RoleEditFragment extends Fragment {
    private Context context;

    public RoleEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = this.getActivity();
        return inflater.inflate(R.layout.fragment_role_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        final DbHandler db = new DbHandler(context);

        final int activeRoleId = db.readActiveRole();
        String uloga = db.getUloga(activeRoleId);

        final EditText ulogaUnos = view.findViewById(R.id.NazivUlogeUnos);
        ulogaUnos.setText(uloga);

        Button updateRoleButton = view.findViewById(R.id.PohraniGumb);
        updateRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.UpdateUloga(ulogaUnos.getText().toString(), activeRoleId);
                Toast.makeText(context, "Updated: " + ulogaUnos.getText().toString(), Toast.LENGTH_LONG).show();
                navController.navigate(R.id.action_roleEditFragment_to_rolesFragment);

            }
        });

    }
}