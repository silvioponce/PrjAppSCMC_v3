package com.sci.sponce.prjappscmc.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.sci.sponce.prjappscmc.DetVisitaMenoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.ArrayList;

import Adapter.VisitaNinosMenoresAdapter;
import BL.VisitasNinosMenorBL;
import DAO.CCMRecienNacidoDao;
import Entidades.VisitasNinosMenor;


public class ListVisitaMenoresFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Boolean mParam2;

    private OnFragmentInteractionListener mListener;

    VisitaNinosMenoresAdapter adapter;

    int idCCMRecienNacido = 0;

    VisitasNinosMenorBL visitasNinosMenorBL = new VisitasNinosMenorBL();
    CCMRecienNacidoDao ccmRecienNacidoDao = new CCMRecienNacidoDao();

    ListView lstVistaNinosMenores;
    ArrayList<VisitasNinosMenor> arrayOfVisitasNinosMenor;

    EditText txtBuscarVisitaNinoMenores;
    ImageButton btnBuscarVisitaNinoMenores;

    public ListVisitaMenoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListVisitaMenoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListVisitaMenoresFragment newInstance(String param1, String param2) {
        ListVisitaMenoresFragment fragment = new ListVisitaMenoresFragment();
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
            idCCMRecienNacido = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getBoolean(ARG_PARAM2);
        }

        if(getArguments().getString("IdNino")!=null)
            idCCMRecienNacido = Integer.parseInt(getArguments().getString("IdNino"));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_list_visita_menores, container, false);

        lstVistaNinosMenores = (ListView) view.findViewById(R.id.lstVisitaNinosMenores);

        txtBuscarVisitaNinoMenores = (EditText) view.findViewById(R.id.txtBuscarVisitaNinoMenores);
        btnBuscarVisitaNinoMenores = (ImageButton) view.findViewById(R.id.btnBuscarVisitaNinoMenores);

        btnBuscarVisitaNinoMenores.setOnClickListener(this);

        arrayOfVisitasNinosMenor = new ArrayList<VisitasNinosMenor>();
        try {

            if (idCCMRecienNacido > 0) {
                arrayOfVisitasNinosMenor = visitasNinosMenorBL.getAllVisitasNinosMenoresArrayListCustom(getActivity(), "IdCCMRecienNacido = " + idCCMRecienNacido);
            } else {
                arrayOfVisitasNinosMenor = visitasNinosMenorBL.getAllVisitasNinosMenoresArrayList(getActivity());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new VisitaNinosMenoresAdapter(getActivity(), arrayOfVisitasNinosMenor);
        lstVistaNinosMenores.setAdapter(adapter);

        if (idCCMRecienNacido > 0){
            txtBuscarVisitaNinoMenores.setVisibility(View.GONE);
            btnBuscarVisitaNinoMenores.setVisibility(View.GONE);
        }

        getActivity().setTitle("Buscar Visita Nino(a) Menores");

        registerForContextMenu(lstVistaNinosMenores);

        return view;
    }

    private void BuscarVisitaNinosMenores() {
        arrayOfVisitasNinosMenor = new ArrayList<VisitasNinosMenor>();
        try {
            arrayOfVisitasNinosMenor = visitasNinosMenorBL.getAllVisitasNinosMenoresArrayListByNomNino(getActivity(), txtBuscarVisitaNinoMenores.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new VisitaNinosMenoresAdapter(getActivity(), arrayOfVisitasNinosMenor);
        lstVistaNinosMenores.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;

        String titulo;
        //titulo = (String) ((TextView) info.targetView.findViewById(R.id.viewNomNino)).getText();
        titulo = "Seleccione una Opción";

        menu.setHeaderTitle(titulo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_lista_visita_ninos_menores, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.ctxActualizarVisitaMenores:

                int idnino = 0, idVisitaNinosMenores, idCasoRecienNacido;

                idVisitaNinosMenores = adapter.getItem(info.position).get_id();

                idCasoRecienNacido = adapter.getItem(info.position).getIdCCMRecienNacido();

                try {
                    idnino = ccmRecienNacidoDao.getCCMRecienNacidoById(getActivity(), String.valueOf(idCasoRecienNacido)).getIdNino();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CatVisitaNinosMenores().newInstance(idnino, idCasoRecienNacido, idVisitaNinosMenores, true), "CatVistaNinosMenores").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DetVisitaMenoresActivity.class);
                intent.putExtra(DetVisitaMenoresFragment.ID, idVisitaNinosMenores);
                intent.putExtra("idCCMRecienNacido", idCasoRecienNacido);
                intent.putExtra("idNino", idnino);
                intent.putExtra("modoEdit", true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
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
        switch (v.getId()) {
            case R.id.btnBuscarVisitaNinoMenores:
                BuscarVisitaNinosMenores();

            default:
                return;
        }
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
