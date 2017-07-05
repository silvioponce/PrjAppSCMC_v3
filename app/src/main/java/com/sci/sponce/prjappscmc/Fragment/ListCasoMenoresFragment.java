package com.sci.sponce.prjappscmc.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.sci.sponce.prjappscmc.DetCasoMenoresActivity;
import com.sci.sponce.prjappscmc.DetVisitaMenoresActivity;
import com.sci.sponce.prjappscmc.ListCasoMenoresActivity;
import com.sci.sponce.prjappscmc.ListVistaMenoresActivity;
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

        if(getArguments().getString("IdNino")!=null)
            idNino = Integer.parseInt(getArguments().getString("IdNino"));


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

        lstCasoNinosMenores.setSelected(true);

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

        getActivity().setTitle("Caso Nino(a) Menores");

        if (idNino>0){
            txtBuscarCasoNinoMenores.setVisibility(View.GONE);
            btnBuscarCasoNinoMenores.setVisibility(View.GONE);
        }

        registerForContextMenu(lstCasoNinosMenores);

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        //inflater.inflate(R.menu.menu_cat_ninos, menu);

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
        inflater.inflate(R.menu.menu_ctx_lista_casos_ninos_menores, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.ctxModificarCasoNinoMenor:
                selectedItem = adapter.getItem(info.position).get_id();
                int idnino = adapter.getItem(info.position).getIdNino();

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CasoNinosMenoresFragment().newInstance(idnino, selectedItem,true), "CasoNinosMenores").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DetCasoMenoresActivity.class);
                intent.putExtra(DetCasoMenoresFragment.ID, String.valueOf(idnino));
                intent.putExtra("idCCMRecienNacido", String.valueOf(selectedItem));
                intent.putExtra("modoEdit", String.valueOf(true));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

                return true;

            case R.id.ctxCrearNuevaVisitaMenores:
                selectedItem = adapter.getItem(info.position).get_id();
                int idNino = adapter.getItem(info.position).getIdNino();

                Intent intent1 = new Intent(getActivity(), DetVisitaMenoresActivity.class);
                intent1.putExtra(DetVisitaMenoresFragment.ID, idNino);
                intent1.putExtra("idCCMRecienNacido", selectedItem);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent1);

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CatVisitaNinosMenores().newInstance(idNino, selectedItem), "CatVistaNinosMenores").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ctxVerVisitaMenores:
                selectedItem = adapter.getItem(info.position).get_id();

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new BuscarVisitaNinosMenores().newInstance(selectedItem, false), "BuscarVistaNinosMenores").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(getActivity(), ListVistaMenoresActivity.class);
                intent2.putExtra("IdNino", String.valueOf(selectedItem));
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuscarCasoNinoMenores:
                BuscarCasoNinosMenores();

            default:
                return;
        }
    }

    private void BuscarCasoNinosMenores() {
        arrayOfCasoNinosMenores = new ArrayList<CCMRecienNacido>();
        try {
            arrayOfCasoNinosMenores = ccmRecienNacidoBL.getAllCCMRecienNacidosArrayListByNomNino(getActivity(), txtBuscarCasoNinoMenores.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new CasoNinosMenoresAdapter(getActivity(), arrayOfCasoNinosMenores);
        lstCasoNinosMenores.setAdapter(adapter);
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
