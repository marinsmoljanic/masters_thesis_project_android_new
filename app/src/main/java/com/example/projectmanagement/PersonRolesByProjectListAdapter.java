package com.example.projectmanagement;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class PersonRolesByProjectListAdapter extends ArrayAdapter<String> {
    int groupid;
    ArrayList<String> item_list;
    ArrayList<String> desc;
    Context context;
}
