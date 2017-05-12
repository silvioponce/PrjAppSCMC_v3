package com.sci.sponce.prjappscmc.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.ArrayList;

import Adapter.CasoNinosMenoresAdapter;
import BL.CCMRecienNacidoBL;
import Entidades.CCMRecienNacido;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListCasoMenoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListCasoMenoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCasoMenoresFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    CCMRecienNacidoBL ccmRecienNacidoBL = new CCMRecienNacidoBL();
    CasoNinosMenoresAdapter adapter;

    EditText txtBuscarCasoNinoMenores;

    ImageButton btnBuscarCasoNinoMenores;

    int idNino = 0;

    public int selectedItem = -1;

    private ListView lstCasoNinosMenores;
    ArrayList<CCMRecienNacido> arrayOfCasoNinosMenores;

    public ListCasoMenoresFragment() {
        // Required empty public constructor
    }


    public static Fragment crear() {

        return new ListCasoMenoresFragment();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListCasoMenoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCasoMenoresFragment newInstance(String param1, String param2) {
        ListCasoMenoresFragment fragment = new ListCasoMenoresFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_caso_menores, container, false);

        lstCasoNinosMenores = (ListView) view.findViewById(R.id.lstCasoNinosMenores);

        txtBuscarCasoNinoMenores = (EditText) view.findViewById(R.id.txtBuscarCasoNinoMenores);
        btnBuscarCasoNinoMenores = (ImageButton) view.findViewById(R.id.btnBuscarCasoNinoMenores);

        btnBuscarCasoNinoMenores.setOnClickListener(this);

        arrayOfCasoNinosMenores = new ArrayList<CCMRecienNacido>();



        try {

            if (idNino>0){
                arrayOfCasoNinosMenores = ccmRecienNacidoBL.getAllCCMRecienNacidosArrayListCustom(getActivity(), "IdNino = " + idNino);
            }else {
                arrayOfCasoNinosMenores = ccmRecienNacidoBL.getAllCCMRecienNacidosArrayList(getActivity());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new CasoNinosMenoresAdapter(getActivity(), arrayOfCasoNinosMenores);
        lstCasoNinosMenores.setAdapter(adapter);

        lstCasoNinosMenores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = adapter.getItem(position).get_id();
            }
        });

        return view;
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

    @Override
    public void onClick(View v) {

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
