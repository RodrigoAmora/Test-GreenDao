package com.rodrigoamora.testgreendao.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rodrigoamora.testgreendao.R;
import com.rodrigoamora.testgreendao.dao.DaoFactory;
import com.rodrigoamora.testgreendao.entity.Person;
import com.rodrigoamora.testgreendao.entity.PersonDao;

public class SavePersonFragment extends Fragment implements View.OnClickListener {

    private Button btSave;
    private EditText inputEmail, inputName, inputPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save_person, container,	false);

        btSave = rootView.findViewById(R.id.bt_save);
        btSave.setOnClickListener(this);

        inputEmail = rootView.findViewById(R.id.input_email);
        inputPhone = rootView.findViewById(R.id.input_phone);
        inputName = rootView.findViewById(R.id.input_name);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view == btSave) {
            savePerson();
        }
    }

    private void savePerson() {
        Person person = new Person();
        person.setEmail(inputEmail.getText().toString());
        person.setName(inputName.getText().toString());
        person.setPhone(inputPhone.getText().toString());

        PersonDao pesonDao = DaoFactory.createSession(getActivity()).getPersonDao();
        pesonDao.save(person);
    }

}
