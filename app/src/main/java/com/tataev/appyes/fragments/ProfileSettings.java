package com.tataev.appyes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.tataev.appyes.Defaults;
import com.tataev.appyes.R;
import com.tataev.appyes.helper.SQLiteHandlerUser;
import com.tataev.appyes.helper.SessionManager;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileSettings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileSettings extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String codeOutput;
    private SQLiteHandlerUser db;
    private SessionManager session;

    private OnFragmentInteractionListener mListener;
    private ImageView imageLogoSettings;
    private EditText editNameSettings;
    private EditText editSurnameSettings;
    private EditText daySettings;
    private Spinner spinnerMonthsSettings;
    private EditText yearSettings;
    private RadioGroup radioGroupSettings;
    private int radioMale = R.id.radioMaleSetting;
    private int radioFemale = R.id.radioFemaleSettings;
    private EditText editAddressSettings;
    private EditText editEmailSettings;
    private CheckBox checkBoxShowHistorySettings;
    private CheckBox checkShowRecomSettings;
    private TextView textCodeSettings;
    private Button buttonSave;


    public ProfileSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileSettings newInstance(String param1, String param2) {
        ProfileSettings fragment = new ProfileSettings();
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
        View rootView = inflater.inflate(R.layout.fragment_profile_settings, container, false);

        imageLogoSettings = (ImageView)rootView.findViewById(R.id.imageLogoSettings);
        editNameSettings = (EditText)rootView.findViewById(R.id.editNameSettings);
        editSurnameSettings = (EditText)rootView.findViewById(R.id.editSurnameSettings);
        daySettings = (EditText)rootView.findViewById(R.id.daySettings);
        spinnerMonthsSettings = (Spinner)rootView.findViewById(R.id.spinnerMonthsSettings);
        yearSettings = (EditText)rootView.findViewById(R.id.yearSettings);
        radioGroupSettings = (RadioGroup)rootView.findViewById(R.id.radioGroupSettings);
        editAddressSettings = (EditText)rootView.findViewById(R.id.editAddressSettings);
        editEmailSettings = (EditText)rootView.findViewById(R.id.editEmailSettings);
        checkBoxShowHistorySettings = (CheckBox)rootView.findViewById(R.id.checkBoxShowHistorySettings);
        checkShowRecomSettings = (CheckBox)rootView.findViewById(R.id.checkShowRecomSettings);
        textCodeSettings = (TextView)rootView.findViewById(R.id.textCodeSettings);
        buttonSave = (Button)rootView.findViewById(R.id.buttonSave);

        // SqLite database handler
        db = new SQLiteHandlerUser(getActivity().getApplicationContext());
        // session manager
        session = new SessionManager(getActivity().getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        String name = user.get("name");
        String surname = user.get("surname");
        String birthday = user.get("birthday");
        String gender = user.get("gender");
        String address = user.get("address");
        String email = user.get("email");
        String history = user.get("history");
        String recommendations = user.get("recommendations");

        if (birthday != null) {
            String year = birthday.split("-")[0];
            String month = birthday.split("-")[1];
            String day = birthday.split("-")[2];
            daySettings.setText(day);
            yearSettings.setText(year);
            spinnerMonthsSettings.setSelection(Integer.parseInt(month) - 1);
        }

        editNameSettings.setText(name);
        editSurnameSettings.setText(surname);
        editAddressSettings.setText(address);
        if (gender.equals("м")) radioGroupSettings.check(radioMale);
        if (gender.equals("ж")) radioGroupSettings.check(radioFemale);
        editEmailSettings.setText(email);
        if (history.equals("1")) checkBoxShowHistorySettings.setChecked(true);
        if (recommendations.equals("1")) checkShowRecomSettings.setChecked(true);

        //Setting random string to textCodeSettings field
        codeOutput = Defaults.generateRandomCode();
        textCodeSettings.setText(codeOutput);

        buttonSave.setOnClickListener(this);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSave:
                break;
        }

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
