package com.example.taller_4.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.taller_4.Models.Persona;
import com.example.taller_4.R;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Persona> {
    int mLayautId;
    public PersonaAdapter(Context context, int layoutid, List<Persona> items) {
        super(context, layoutid, items);
        mLayautId = layoutid;
    }

        @Override
        public View getView (int position, View view, ViewGroup parent)
        {
          Persona persona = getItem(position);
          String name = persona.getNombre();
          String lastname = persona.getApellido();
          String email = persona.getEmail();
          String telefono = persona.getTelefono();

          if (view == null)
          {
              LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              view = inflater.inflate(mLayautId,parent,false);
          }

          TextView nameView = (TextView) view.findViewById(R.id.txtName);
          TextView lastnameView = (TextView) view.findViewById(R.id.txtLastName);
          TextView Email = (TextView) view.findViewById(R.id.txtemail);
          TextView Telefono = (TextView) view.findViewById(R.id.txttelefono);

          nameView.setText(name);
          lastnameView.setText(lastname);
          Email.setText(email);
          Telefono.setText(telefono);

          return view;

        }
}
