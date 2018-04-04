package com.rodrigoamora.testgreendao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rodrigoamora.testgreendao.R;
import com.rodrigoamora.testgreendao.entity.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context context;
    private List<Person> personList;

    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_person, parent, false);
        PersonViewHolder holder = new PersonViewHolder(view);

        holder.emailPerson = view.findViewById(R.id.email_personn);
        holder.namePerson = view.findViewById(R.id.name_personn);
        holder.phonePerson = view.findViewById(R.id.phone_personn);

        return holder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.PersonViewHolder holder, int position) {
        holder.emailPerson.setText(personList.get(position).getEmail());
        holder.namePerson.setText(personList.get(position).getName());
        holder.phonePerson.setText(personList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView emailPerson, namePerson, phonePerson;

        public PersonViewHolder(View itemView) {
            super(itemView);
        }

    }

}
