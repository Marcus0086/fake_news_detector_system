package com.addictox.fndx.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.addictox.fndx.R;

public class ProfileFragment extends Fragment {

    private EditText fName;
    private EditText lName;
    private EditText Email;
    private EditText Phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        fName = view.findViewById(R.id.etFirstName);
        lName = view.findViewById(R.id.etLastName);
        Email = view.findViewById(R.id.etEmail);
        Phone = view.findViewById(R.id.etPhone);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.profile_toolbar_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Edit: {
                fName.setEnabled(true);
                lName.setEnabled(true);
                Email.setEnabled(true);
                Phone.setEnabled(true);
                break;
            }
            case R.id.action_logout: {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("Confirmation")
                        .setMessage("Are you sure you want exit?")
                        .setPositiveButton("Yes", (dialogInterface, i) -> new Activity().finishAffinity())
                        .setNegativeButton("No", (dialogInterface, i) -> new Activity().moveTaskToBack(true))
                        .create().show();
                break;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }
}
