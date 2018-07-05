package com.example.charl.lostnfound.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charl.lostnfound.Adapters.LostAdapter;
import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.R;
import com.example.charl.lostnfound.Room.ViewModels.LostViewModels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rv;
    LostAdapter adapter;
    LostViewModels LViewModel;
    GridLayoutManager gManager;
    SwipeRefreshLayout Swipe;


    private OnFragmentInteractionListener mListener;

    public LostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LostFragment newInstance(String param1, String param2) {
        LostFragment fragment = new LostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View vista = inflater.inflate(R.layout.fragment_lost, container, false);


        Swipe= vista.findViewById(R.id.Swipe);

        Swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                new Handler().postDelayed(new Runnable() { //Un Hilo
                    @Override
                    public void run() {
                        try {
                            LViewModel = new LostViewModels(getActivity().getApplication());   //Creamos una nueva instancia
                            Swipe.setRefreshing(false);
                        } catch (Exception e) {
                        }

                    }
                }, 2000); //Cuanto va cargar
            }


        });


        rv = vista.findViewById(R.id.recycler);



            LViewModel = ViewModelProviders.of(this).get(LostViewModels.class);
            LViewModel.getAllobjects().observe(this, new Observer<List<Lobjects>>() {
                @Override
                public void onChanged(@Nullable List<Lobjects> news) {
                    adapter = new LostAdapter((ArrayList<Lobjects>) news, getContext());
                    gManager = new GridLayoutManager(getActivity(), 2);
                    gManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position % 3 == 0) {
                                return 2;
                            } else {
                                return 1;
                            }
                        }
                    });


                    rv.setLayoutManager(gManager);
                    rv.setAdapter(adapter);
                }
            });



        return vista;
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
        void onFragmentInteraction(Uri uri);
    }
}
