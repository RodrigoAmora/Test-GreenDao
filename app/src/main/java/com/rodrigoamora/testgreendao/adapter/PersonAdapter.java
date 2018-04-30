package com.rodrigoamora.testgreendao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rodrigoamora.testgreendao.R;
import com.rodrigoamora.testgreendao.entity.Person;
import com.rodrigoamora.testgreendao.listener.OnItemClickListener;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context context;
    private List<Person> personList;
    private OnItemClickListener onItemClickListener;

    public PersonAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_person, parent, false);
        PersonViewHolder holder = new PersonViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.PersonViewHolder holder, final int position) {
        holder.setPerson(personList.get(position));
        holder.emailPerson.setText(personList.get(position).getEmail());
        holder.namePerson.setText(personList.get(position).getName());
        holder.phonePerson.setText(personList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageButton btDelete;
        private TextView emailPerson, namePerson, phonePerson;

        private Person person;

        public PersonViewHolder(View itemView) {
            super(itemView);
            btDelete = itemView.findViewById(R.id.delete_person);
            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(person);
                }
            });

            emailPerson = itemView.findViewById(R.id.email_personn);
            namePerson = itemView.findViewById(R.id.name_personn);
            phonePerson = itemView.findViewById(R.id.phone_personn);
        }

        public void setPerson(Person person) {
            this.person = person;
        }

    }

}
