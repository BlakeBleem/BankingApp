package com.example.bankingapp;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingapp.Model.Profile;
import com.google.gson.Gson;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DashboardFragment extends Fragment {

    private TextView txtWelcome;
    private TextView txtMessage;
    private Button btnAddAccount;

    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        txtWelcome = rootView.findViewById(R.id.txt_welcome);
        txtMessage = rootView.findViewById(R.id.txt_details_msg);
        btnAddAccount = rootView.findViewById(R.id.btn_add_account);

        setupViews();
        return rootView;

    }

    private void setupViews() {

        SharedPreferences userPreferences = getActivity().getSharedPreferences("LastProfileUsed", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = userPreferences.getString("LastProfileUsed", "");
        Profile userProfile = gson.fromJson(json, Profile.class);

        btnAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("DisplayAccountDialog", true);
                ((DrawerActivity) getActivity()).manualNavigation(DrawerActivity.manualNavID.ACCOUNTS_ID, bundle);
            }
        });

        if (userProfile.getAccounts().size() == 0) {
            txtMessage.setVisibility(View.VISIBLE);
            btnAddAccount.setVisibility(View.VISIBLE);
            txtMessage.setText("Click below to add an account");
        } else {
            txtMessage.setVisibility(View.GONE);//TEMP to clear field
            btnAddAccount.setVisibility(View.GONE);

            //TODO: Have message say some kind of statistic about how many transactions they have made today
        }

        StringBuilder welcomeString = new StringBuilder();

        Calendar calendar = Calendar.getInstance();

        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);


        welcomeString.append(", ")
                .append(userProfile.getFirstName())
                .append(". Welcome to the Banking App . ")
                .append(getString(R.string.happy))
                .append(" ");

        int day = calendar.get(Calendar.DAY_OF_WEEK);

        String[] days = getResources().getStringArray(R.array.days);
        String dow = "";

        switch(day) {
            case Calendar.SUNDAY:
                dow = days[0];
                break;
            case Calendar.MONDAY:
                dow = days[1];
                break;
            case Calendar.TUESDAY:
                dow = days[2];
                break;
            case Calendar.WEDNESDAY:
                dow = days[3];
                break;
            case Calendar.THURSDAY:
                dow = days[4];
                break;
            case Calendar.FRIDAY:
                dow = days[5];
                break;
            case Calendar.SATURDAY:
                dow = days[6];
                break;
            default:
                break;
        }

        welcomeString.append(dow)
                .append(".");

        txtWelcome.setText(welcomeString.toString());
    }

}
