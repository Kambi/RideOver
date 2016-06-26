package com.example.rahultheboss.rideover;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyRides.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyRides#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRides extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //String new_username = getIntent().getStringExtra("Username");
    ListView listView;
    ListDataAdapter listDataAdapter;

    String new_username;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private ListView mListView;
    private ListDataAdapter adapter;

    DatabaseHelper db;

    private List<Rides> detailsList = new ArrayList<>();

    //public static String login_username = username_string;

    private OnFragmentInteractionListener mListener;

    public MyRides() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRides.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRides newInstance(String param1, String param2) {

        MyRides fragment = new MyRides();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Fragment", "Myrides up");

        if (getArguments() != null) {
            new_username = getArguments().getString("Username");
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Debug: ", " logged in as " + new_username);
        View rootView = inflater.inflate(R.layout.fragment_my_rides, container, false);
        mListView = (ListView)rootView.findViewById(R.id.frag_details_listView);
        db = new DatabaseHelper(getContext());
        //EditText sr_name = (EditText) sr_name.findViewById();
        //String sr_name_string = sr_name.getText().toString();

        //setContentView(R.layout.activity_data_list);
        //listView = (ListView)findViewById(R.id.list_view);
        //listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout, new_user);


        //Log.d("Debug: ", new_username);

        ArrayList<String> ridesFromDB = new ArrayList<String>();

        Cursor res2 = db.getAllTheData();
        /*if(res2.getCount() == 0) {

            //show message
            //showMessage("Sorry", "No rides found");
            return;
        }*/
        String name = "", leavingfrom = "", goingto = "", date = "", time = "", seats  = "", price = "";
        String result= "";
        result += "\n";
        ridesFromDB.add(result);
        while(res2.moveToNext()) {
                     /*       //buffer.append(res2.getString(0));
                            buffer.append(res2.getString(1) + "\n");
                            buffer.append("Leaving from: " + res2.getString(2) + "\n");
                            buffer.append("Going to: " + res2.getString(3) + "\n");
                            buffer.append("Date: " + res2.getString(4) + "\n");
                            buffer.append("Time: " + res2.getString(5) + "\n");
                            buffer.append("Seats: " + res2.getString(6) + "\n");
                            buffer.append("Price: " + res2.getString(7) + "\n\n");
                    */
            name = res2.getString(1);
            leavingfrom = res2.getString(2);
            goingto = res2.getString(3);
            date = res2.getString(4);
            time = res2.getString(5);
            seats = res2.getString(6);
            price = ("$" + res2.getString(7));

            result = "Name: " + name + "\n";
            result += ("Leaving From: " + leavingfrom + "\n");
            result += ("Going to: " + goingto + "\n");
            result += ("Date: " + date + "\n");
            result += ("Time: " + time + "\n");
            result += ("Seats: " + seats + "\n");


            Log.d("Debug: ", result);

            if(name.equals(new_username)) {
                ridesFromDB.add(result);
                Log.d("Debug: ", "Same username");
            }
            else{}
        }

        Log.d("Debug: ", "Done entering in array list");

        for(int i = 0; i < ridesFromDB.size();i++) {
            Log.d("Debug: ", ridesFromDB.get(i));
        }

        //String [] list = new String[ridesFromDB.size()];
        //list = ridesFromDB.toArray(list);
        //String[]list = {"element 1", "element 2", "element 3"};
        String[]list = new String[ridesFromDB.size()];
        for(int i = 0; i < ridesFromDB.size();i++) {
            list[i] = ridesFromDB.get(i);
            Log.d("Debug: ", "list[i] -> " + list[i]);
        }
        if(list.length == 0){
            Log.d("Debug: ", "list length is zero");
            Toast.makeText(getContext(), "Username Field is Empty! Please Type in Username.", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("Debug: ", "list length is !zero");
        }
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), R.layout.mytextview, list);

        mListView.setAdapter(listViewAdapter);
        //mListView.setCacheColorHint(Color.WHITE);
        //mListView.setBackgroundColor(Color.WHITE);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

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
