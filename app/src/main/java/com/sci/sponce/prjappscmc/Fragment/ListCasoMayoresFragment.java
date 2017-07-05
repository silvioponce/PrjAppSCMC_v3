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

import com.sci.sponce.prjappscmc.DetCasoMayoresActivity;
import com.sci.sponce.prjappscmc.DetVisitaMayoresActivity;
import com.sci.sponce.prjappscmc.ListVisitaMayoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.ArrayList;

import Adapter.CasoNinosAdapter;
import BL.CCMNinoBL;
import Entidades.CCMNino;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListCasoMayoresFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListCasoMayoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCasoMayoresFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Boolean mParam2;

    CCMNinoBL ccmNinoBL = new CCMNinoBL();
    CasoNinosAdapter adapter;

    EditText txtBuscarCasoNino;

    ImageButton btnBuscarCasoNino;

    int idNino = 0;

    public int selectedItem = -1;

    private ListView lstCasoNinos;
    ArrayList<CCMNino> arrayOfCasoNinos;

    private OnFragmentInteractionListener mListener;

    public ListCasoMayoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListCasoMayoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCasoMayoresFragment newInstance(String param1, String param2) {
        ListCasoMayoresFragment fragment = new ListCasoMayoresFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getArguments().getString("IdNino")!=null)
            idNino = Integer.parseInt(getArguments().getString("IdNino"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_caso_mayores, container, false);

        lstCasoNinos = (ListView) view.findViewById(R.id.lstCasoNinosMayores);

        txtBuscarCasoNino = (EditText) view.findViewById(R.id.txtBuscarCasoNinoMayores);
        btnBuscarCasoNino = (ImageButton) view.findViewById(R.id.btnBuscarCasoNinoMayores);

        btnBuscarCasoNino.setOnClickListener(this);

        arrayOfCasoNinos = new ArrayList<CCMNino>();
        try {

            if (idNino>0){
                arrayOfCasoNinos = ccmNinoBL.getAllCCMNinosArrayListCustom(getActivity(), "IdNino = " + idNino);
            }else {
                arrayOfCasoNinos = ccmNinoBL.getAllCCMNinosArrayList(getActivity());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new CasoNinosAdapter(getActivity(), arrayOfCasoNinos);
        lstCasoNinos.setAdapter(adapter);

        lstCasoNinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = adapter.getItem(position).get_id();
            }
        });

        getActivity().setTitle("Caso Nino(a) 2 Meses");

        registerForContextMenu(lstCasoNinos);

        if (idNino>0){
            txtBuscarCasoNino.setVisibility(View.GONE);
            btnBuscarCasoNino.setVisibility(View.GONE);
        }

        return view;
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
        inflater.inflate(R.menu.menu_ctx_lista_casos_ninos, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.ctxModificarCasoNino:
                selectedItem = adapter.getItem(info.position).get_id();
                int idnino = adapter.getItem(info.position).getIdNino();

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CatCasoNinosFragment().newInstance(idnino, selectedItem,true), "CatCasoNinos").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), DetCasoMayoresActivity.class);
                intent.putExtra(DetCasoMayoresFragment.ID, idnino);
                intent.putExtra("idCCMRecienNacido", String.valueOf(selectedItem));
                intent.putExtra("modoEdit",true);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);

                return true;

            case R.id.ctxCrearNuevaVisitaNinos:
                selectedItem = adapter.getItem(info.position).get_id();
                idNino  = adapter.getItem(info.position).getIdNino();

                Intent intent1 = new Intent(getActivity(), DetVisitaMayoresActivity.class);
                intent1.putExtra(DetVisitaMenoresFragment.ID, idNino);
                intent1.putExtra("idCCMRecienNacido", selectedItem);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent1);


                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CatVisitaNinos().newInstance(idNino, selectedItem), "CatVistaNinos").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ctxVerVisitaNinos:
                selectedItem = adapter.getItem(info.position).get_id();
                idNino  = adapter.getItem(info.position).getIdNino();

                //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new BuscarVisitasNinosMayores().newInstance(selectedItem, false), "BuscarVistaNinosMayores").addToBackStack(null).commit();
                //Toast.makeText(getActivity(), "Lista[" + info.position + " " + selectedItem + ": Opcion 1 pulsada!",Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(getActivity(), ListVisitaMayoresActivity.class);
                intent2.putExtra("IdNino", String.valueOf(selectedItem));
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent2);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    private void BuscarCasoNinosMayores() {
        arrayOfCasoNinos = new ArrayList<CCMNino>();
        try {
            arrayOfCasoNinos = ccmNinoBL.getAllCCMNinosArrayListByName(getActivity(), txtBuscarCasoNino.getText().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new CasoNinosAdapter(getActivity(), arrayOfCasoNinos);
        lstCasoNinos.setAdapter(adapter);
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
            case R.id.btnBuscarCasoNinoMayores:
                BuscarCasoNinosMayores();

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
