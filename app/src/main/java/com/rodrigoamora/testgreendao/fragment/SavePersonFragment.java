package com.rodrigoamora.testgreendao.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rodrigoamora.testgreendao.R;
import com.rodrigoamora.testgreendao.entity.Person;
import com.rodrigoamora.testgreendao.service.PersonService;
import com.rodrigoamora.testgreendao.util.FragmentUtil;
import com.rodrigoamora.testgreendao.validator.EmailValitador;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SavePersonFragment extends Fragment {

    @BindView(R.id.bt_save)
    Button btSave;

    @BindView(R.id.input_email)
    EditText inputEmail;

    @BindView(R.id.input_name)
    EditText inputName;

    @BindView(R.id.input_phone)
    EditText inputPhone;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_save_person, container,	false);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    @OnClick(R.id.bt_save)
    public void savePerson() {
        if (validateInputs()) {
            Person person = new Person();
            person.setEmail(inputEmail.getText().toString());
            person.setName(inputName.getText().toString());
            person.setPhone(inputPhone.getText().toString());

            PersonService.savePerson(getActivity(), person);

            Toast.makeText(getActivity(), getString(R.string.person_saved), Toast.LENGTH_LONG).show();
            FragmentUtil.changeFragment(R.id.conatiner, ListPeopleFragment.class, getFragmentManager(), false, null);
        }
    }

    private boolean validateInputs() {
        if (!EmailValitador.validateEmail(inputEmail.getText().toString())) {
            Toast.makeText(getActivity(), getString(R.string.alert_email_invalid), Toast.LENGTH_LONG).show();
            return false;
        }
        if (inputEmail.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.alert_email_empty), Toast.LENGTH_LONG).show();
            return false;
        }
        if (inputPhone.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.alert_phone_empty), Toast.LENGTH_LONG).show();
            return false;
        }
        if (inputName.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), getString(R.string.alert_name_empty), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
