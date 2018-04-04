package com.rodrigoamora.testgreendao.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rodrigoamora.testgreendao.R;
import com.rodrigoamora.testgreendao.adapter.PersonAdapter;
import com.rodrigoamora.testgreendao.dao.DaoFactory;
import com.rodrigoamora.testgreendao.entity.Person;
import com.rodrigoamora.testgreendao.entity.PersonDao;

import java.util.List;

public class ListPeopleFragment extends Fragment {

    private List<Person> personList;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_people, container,	false);
        recyclerView = rootView.findViewById(R.id.recycler_view);
        configureRecyclerView();
        getAllPeople();
        return rootView;
    }

    private void getAllPeople() {
        PersonDao pesonDao = DaoFactory.createSession(getActivity()).getPersonDao();
        personList = pesonDao.loadAll();
        String msg = getString(R.string.total)+" "+personList.size()+" "+getString(R.string.people);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
        populateRecyclerView();
    }

    private void configureRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(linearLayout);
    }

    private void populateRecyclerView() {
        PersonAdapter adapter = new PersonAdapter(getActivity(), personList);
        recyclerView.setAdapter(adapter);
    }

}
