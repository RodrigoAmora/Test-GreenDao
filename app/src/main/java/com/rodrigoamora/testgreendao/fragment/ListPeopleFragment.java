package com.rodrigoamora.testgreendao.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.rodrigoamora.testgreendao.listener.OnItemClickListener;
import com.rodrigoamora.testgreendao.service.PersonService;
import com.rodrigoamora.testgreendao.util.FragmentUtil;
import com.rodrigoamora.testgreendao.util.ShareUtil;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ListPeopleFragment extends Fragment {

    @BindView(R.id.fab_share)
    FloatingActionButton fabShare;

    @BindView(R.id.fab_save_person)
    FloatingActionButton fabSavePerson;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private PersonAdapter adapter = null;
    private List<Person> personList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_people, container,	false);

        unbinder = ButterKnife.bind(this, rootView);

        configureRecyclerView();
        getAllPeople();

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        unbinder.unbind();
    }

    private void getAllPeople() {
        personList = PersonService.getAllPeolplo(getActivity());
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
        adapter = new PersonAdapter(getActivity(), personList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int viewId, Person person) {
                switch (viewId) {
                    case R.id.delete_person:
                        deletePerson(person);
                        break;

                    case R.id.edit_person:
                        editPErson(person);
                        break;
                }
            }
        });
    }

    @OnClick(R.id.fab_share)
    public void share() {
        String textShare = "APP Test-GreenDao\n"+
                "URL: https://github.com/RodrigoAmora/test-greendao";
        ShareUtil.directShare(getActivity(), getString(R.string.share), textShare);
    }

    @OnClick(R.id.fab_save_person)
    public void savePerson() {
        FragmentUtil.changeFragment(R.id.conatiner, SavePersonFragment.class, getFragmentManager(), false, null);
    }

    private void deletePerson(Person person) {
        PersonDao pesonDao = DaoFactory.createSession(getActivity()).getPersonDao();
        pesonDao.delete(person);
        personList.remove(person);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Toast.makeText(getActivity(), getActivity().getString(R.string.person_deleted), Toast.LENGTH_LONG).show();
    }

    private void editPErson(Person person) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("person", (Serializable) person);
        FragmentUtil.changeFragment(R.id.conatiner, SavePersonFragment.class, getFragmentManager(), false, bundle);
    }

}