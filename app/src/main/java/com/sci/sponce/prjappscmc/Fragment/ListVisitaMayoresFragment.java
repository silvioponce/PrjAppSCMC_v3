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

import com.sci.sponce.prjappscmc.DetVisitaMayoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.ArrayList;

import Adapter.VisitaNinosMayoresAdapter;
import BL.VisitasNinosMayorBL;
import DAO.CCMNinoDao;
import Entidades.VisitasNinosMayor;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListVisitaMayoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListVisitaMayoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListVisitaMayoresFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    VisitaNinosMayoresAdapter adapter;

    int idCCMNino = 0;

    VisitasNinosMayorBL visitasNinosMayorBL = new VisitasNinosMayorBL();
    CCMNinoDao ccmNinoDao = new CCMNinoDao();

    ListView lstVistaNinosMayores;
    ArrayList<VisitasNinosMayor> arrayOfVisitasNinosMayor;

    EditText txtBuscarVisitaNinoMayores;
    ImageButton btnBuscarVisitaNinoMayores;


    public ListVisitaMayoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListVisitaMayoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListVisitaMayoresFragment newInstance(String param1, String param2) {
        ListVisitaMayoresFragment fragment = new ListVisitaMayoresFragment();
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

        View view = inflater.inflate(R.layout.fragment_list_visita_mayores, container, false);

        lstVistaNinosMayores = (ListView) view.findViewById(R.id.lstVisitaNinosMayores);

/*        txtBuscarVisitaNinoMayores = (EditText) view.findViewById(R.id.txtBuscarVisitaNinoMayores);
        btnBuscarVisitaNinoMayores = (ImageButton) view.findViewById(R.id.btnBuscarVisitaNinoMayores);

        btnBuscarVisitaNinoMayores.setOnClickListener(this);*/

        arrayOfVisitasNinosMayor = new ArrayList<VisitasNinosMayor>();
        try {

            if (idCCMNino > 0) {
                arrayOfVisitasNinosMayor = visitasNinosMayorBL.getAllVisitasNinosMayoresArrayListCustom(getActivity(), "IdCCMNino = " + idCCMNino);
            } else {
                arrayOfVisitasNinosMayor = visitasNinosMayorBL.getAllVisitasNinosMayoresArrayList(getActivity());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new VisitaNinosMayoresAdapter(getActivity(), arrayOfVisitasNinosMayor);
        lstVistaNinosMayores.setAdapter(adapter);


        getActivity().setTitle("Buscar Visita Nino(a) 2 Meses");

        registerForContextMenu(lstVistaNinosMayores);

/*        if (!mParam2){
            txtBuscarVisitaNinoMayores.setVisibility(View.GONE);
            btnBuscarVisitaNinoMayores.setVisibility(View.GONE);
        }*/


        return  view;
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
        inflater.inflate(R.menu.menu_ctx_lista_visita_ninos_mayores, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.ctxActualizarVisitaMayores:

                int idnino = 0, idVisitaNinosMayores, idCasoNinos;

                idVisitaNinosMayores = adapter.getItem(info.position).get_id();

                idCasoNinos = adapter.getItem(info.position).getIdCCMNino();

                try {
                    idnino = ccmNinoDao.getCCMNinoById(getActivity(), String.valueOf(idCasoNinos)).getIdNino();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CatVisitaNinos().newInstance(idnino, idCasoNinos, idVisitaNinosMayores, true), "CatVistaNinosMayores").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DetVisitaMayoresActivity.class);
                intent.putExtra(DetVisitaMenoresFragment.ID, idVisitaNinosMayores);
                intent.putExtra("idCCMRecienNacido", idCasoNinos);
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
