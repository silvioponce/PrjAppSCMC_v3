package com.sci.sponce.prjappscmc.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
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
import android.widget.TextView;
import android.widget.Toast;

import com.sci.sponce.prjappscmc.DetCasoMenoresActivity;
import com.sci.sponce.prjappscmc.DetNinoActivity;
import com.sci.sponce.prjappscmc.ListCasoMenoresActivity;
import com.sci.sponce.prjappscmc.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Adapter.NinosAdapter;
import BL.CCMNinoBL;
import BL.CCMRecienNacidoBL;
import BL.NinoBL;
import Entidades.Nino;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListNinosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListNinosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListNinosFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private NinoBL ninoBL = new NinoBL();
    private ListView lista;
    EditText txtBuscar;
    NinosAdapter adapter;
    ImageButton btnBuscar;

    private ActionMode mActionMode;
    public int selectedItem = -1;
    ArrayList<Nino> arrayOfNinos;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListNinosFragment() {
        // Required empty public constructor
    }

    public static Fragment crear() {

        return new ListNinosFragment();
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListNinosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListNinosFragment newInstance(String param1, String param2) {
        ListNinosFragment fragment = new ListNinosFragment();
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
        View v = inflater.inflate(R.layout.fragment_list_ninos, container, false);
        lista = (ListView) v.findViewById(R.id.lstNinos);
        txtBuscar = (EditText) v.findViewById(R.id.txtBuscar);
        btnBuscar = (ImageButton) v.findViewById(R.id.btnBuscar);

        lista.setSelected(true);

        getActivity().setTitle("Buscar Nino(a)");

        arrayOfNinos = new ArrayList<Nino>();
        try {
            arrayOfNinos = ninoBL.getAllNinosArrayList(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adapter = new NinosAdapter(getActivity(), arrayOfNinos);
        lista.setAdapter(adapter);

        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = adapter.getItem(position).get_id();
                view.setSelected(true);
                if (mActionMode != null) mActionMode.finish();

            }
        });

        registerForContextMenu(lista);

        adapter.notifyDataSetChanged();



        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;

        String titulo = (String) ((TextView) info.targetView
                .findViewById(R.id.viewNomNino)).getText();

        menu.setHeaderTitle(titulo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_ctx_lista_ninos, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.CtxLstOpc1:
                selectedItem = adapter.getItem(info.position).get_id();

                Intent intent = new Intent(getContext(), DetNinoActivity.class);
                intent.putExtra(DetNinoFragment.ID, String.valueOf(selectedItem));
                startActivity(intent);

                return true;

            case R.id.CtxLstOpc2:
                selectedItem = adapter.getItem(info.position).get_id();

                Date fechaNacimiento = adapter.getItem(info.position).getFechaNac();

                Calendar calendar1 = Calendar.getInstance();
                GregorianCalendar fechaActual = new GregorianCalendar();
                calendar1.setTime(fechaNacimiento);

                long meses = ((fechaActual.getTimeInMillis() - calendar1.getTimeInMillis()) / 262975000) / 10;

                //Toast.makeText(getActivity(), String.valueOf((int) meses), Toast.LENGTH_SHORT).show();

                if (meses < 2) {
                    Intent inten = new Intent(getActivity(), DetCasoMenoresActivity.class);
                    inten.putExtra(DetCasoMenoresFragment.ID, String.valueOf(selectedItem));
                    inten.putExtra("modoEdit", false);
                    inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    inten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(inten);
                    //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CasoNinosMenoresFragment().newInstance(selectedItem), "CasoNinosMenores").addToBackStack(null).commit();
                } else {
                    //getFragmentManager().beginTransaction().replace(R.id.Contenedor, new CatCasoNinosFragment().newInstance(selectedItem, 0, false), "CatCasoNinos").addToBackStack(null).commit();
                }

                return true;
            case R.id.ctxListCasoNinosMenores:
                selectedItem = adapter.getItem(info.position).get_id();

                verificaCasos(selectedItem);

                //showDialog();


                return true;

            default:
                return super.onContextItemSelected(item);
        }


    }

    private void verificaCasos(int idNino) {
        boolean flag = false;
        boolean flag2 = false;

        CCMRecienNacidoBL ccmRecienNacidoBL = new CCMRecienNacidoBL();
        CCMNinoBL ccmNinoBL = new CCMNinoBL();

        try {
            flag = ccmRecienNacidoBL.getExisteCCMRecienNacidoByCustomer(getActivity(), "IdNino = " + idNino);
            flag2 = ccmNinoBL.getExisteCCMNinoByCustomer(getActivity(), "IdNino = " + idNino);
            if (flag && flag2) {
                showDialog();
                return;
            }

            if (flag) {
                Intent intent = new Intent(getActivity(), ListCasoMenoresActivity.class);
                intent.putExtra("IdNino", String.valueOf(selectedItem));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                return;
            }

            if (flag2) {
                return;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showDialog() {
        final String[] commandArray = new String[]{"Casos Recien Nacidos", "", "Casos 2 Meses"};
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Single Choice");
        builder.setItems(commandArray, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),
                        commandArray[which] + " Selected", Toast.LENGTH_LONG)
                        .show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
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
