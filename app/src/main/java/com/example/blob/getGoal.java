package com.example.blob;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link getGoal.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link getGoal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class getGoal extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_GOAL = "goal";
    private static final String ARG_SAVED = "saved";

    private String mGoal;
    private String mSaved;

    private OnFragmentInteractionListener mListener;

    private static TextView tvGoal;
    private static TextView tvSaved;


    public getGoal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment getGoal.
     */

    public static getGoal newInstance(String param1, String param2) {
        getGoal fragment = new getGoal();
        Bundle args = new Bundle();
        args.putString(ARG_GOAL, param1);
        args.putString(ARG_SAVED, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGoal = getArguments().getString(ARG_GOAL);
            mSaved = getArguments().getString(ARG_SAVED);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_get_goal, container, false);
        try {
            tvGoal = inf.findViewById(R.id.fragment);
            tvGoal.setText(mGoal);
            tvSaved = inf.findViewById(R.id.saved);
            tvSaved.setText(mSaved);
        } catch(Exception e) {}
        return inf;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String test) {
        if (mListener != null) {
            mListener.onFragmentInteraction(test);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String test);
    }
}
