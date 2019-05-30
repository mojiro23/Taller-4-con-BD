package com.example.taller_4.Fragments;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taller_4.Adapters.PersonaAdapter;
import com.example.taller_4.Models.Persona;
import com.example.taller_4.Models.Persona_Dao;
import com.example.taller_4.Models.Persona_Database;
import com.example.taller_4.R;

import java.util.ArrayList;

public class FrgListView extends Fragment {

    ArrayList<Persona> mPersonas = new ArrayList<Persona>();
    String TAG = "persona";
    Persona_Database bdpersona;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bdpersona  = Room.databaseBuilder(getContext(), Persona_Database.class, "localDB").allowMainThreadQueries().build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frg_list_view, container, false);
        //FirstList (view);
        //SecondList(view);
        muestraLista(view);
        setupUI(view);
        return view;
    }



    public void setupUI (final View view)
    {

        Button btnAgp = (Button) view.findViewById(R.id.AgregaPersona);
        btnAgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui va el codigo
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                FrgDatosPersona datosPersona = new FrgDatosPersona();
                //Bundle bundle = new Bundle();
                //datosPersona.setArguments(bundle);
                ft.replace(android.R.id.content, datosPersona);
                ft.addToBackStack(null);     //Add fragment in back stack
                ft.commit();
            }
        });

        FloatingActionButton fabAgrega = (FloatingActionButton) view.findViewById(R.id.fbAgrega);
        fabAgrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                FrgDatosPersona datosPersona = new FrgDatosPersona();
                ft.replace(android.R.id.content, datosPersona);
                //ft.addToBackStack(null);     //Add fragment in back stack
                ft.commit();
            }
        });

        Button btnAgpR = (Button) view.findViewById(R.id.AgregaPersonaR);
        btnAgpR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aqui va el codigo
                Persona_Dao personaDao = bdpersona.getItemDAO();
                Persona persona = new Persona();
                persona.setNombre("Rodrigo");
                persona.setApellido("Moncada");
                persona.setEmail("Monca@gmail.com");
                persona.setTelefono("88877308");
                personaDao.insert(persona);
                int CantidadItmen = personaDao.getICount();
                //Snackbar.make(view, "Cantidad de personas: " + CantidadItmen, Snackbar.LENGTH_LONG).show();
                SharedPreferences sharedPref = getContext().getSharedPreferences("preferences", getContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("var", CantidadItmen);
                editor.commit();

                int size = sharedPref.getInt("var", 0);
                Snackbar.make(view, "Cantidad de personas: " + size, Snackbar.LENGTH_LONG).show();

                PersonaAdapter adapter = new PersonaAdapter(getContext(), R.layout.list_person_element,mPersonas);
                adapter.clear();
                adapter.addAll(personaDao.getItems());
                ListView listView = (ListView) view.findViewById(R.id.lstView2);
                listView.setAdapter(adapter);

            }
        });

        //SecondList(view);
    }

    public void cargarPersonas(){

        Bundle bundle = getArguments();
        Persona objPersona = (Persona) bundle.getSerializable("Persona");

        if (objPersona != null)
        {
            mPersonas = new ArrayList<Persona>();
            mPersonas.add(new Persona(objPersona.getNombre(),objPersona.getApellido(),objPersona.getEmail(),objPersona.getTelefono()));
            PersonaAdapter adapter = new PersonaAdapter(getContext(), R.layout.list_person_element, mPersonas);
            ListView mListview = (ListView) getActivity().findViewById(R.id.lstView2);
            mListview.setAdapter(adapter);

        }
        else
        {
            Toast.makeText(getContext(),"No se agregaron los datos",Toast.LENGTH_SHORT).show();
        }

    }

    private void FirstList(View view)
    {
       ArrayList<String> mItems = new ArrayList<String>();
        for (int i = 1; i <= 2; i++)
        {
            mItems.add("Item : " + i);
        }
        int layoutId = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getContext(), layoutId, mItems);
        ListView mListview = (ListView) view.findViewById(R.id.lstView);
        mListview.setAdapter(mAdapter);
    }

    private void SecondList(View view)
    {
        Bundle bundle = getArguments();

        if (bundle != null)
        {
            Persona clsPersona = (Persona) bundle.getSerializable("Persona");

            String Nombre =   "Nombre:    "+clsPersona.getNombre().toString();
            String Apellido = "Apellidos: "+clsPersona.getApellido().toString();
            String Email =    "Email:      "+clsPersona.getEmail().toString();
            String Telefono = "Telefono:  "+clsPersona.getTelefono().toString();

            if (clsPersona != null)
            {
                PersonaAdapter adapter = new PersonaAdapter(getContext(), R.layout.list_person_element,mPersonas);
                Persona dPersona = new Persona(Nombre, Apellido, Email,Telefono);
                adapter.add(dPersona);
                ListView listView = (ListView) view.findViewById(R.id.lstView2);
                listView.setAdapter(adapter);
            }

        }
    }

    private void muestraLista(View view)
    {
        Persona_Dao personaDao = bdpersona.getItemDAO();
        PersonaAdapter adapter = new PersonaAdapter(getContext(), R.layout.list_person_element,mPersonas);
        adapter.clear();
        adapter.addAll(personaDao.getItems());
        ListView listView = (ListView) view.findViewById(R.id.lstView2);
        listView.setAdapter(adapter);
    }


}
