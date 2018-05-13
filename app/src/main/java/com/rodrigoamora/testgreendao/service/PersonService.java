package com.rodrigoamora.testgreendao.service;

import android.content.Context;

import com.rodrigoamora.testgreendao.dao.DaoFactory;
import com.rodrigoamora.testgreendao.entity.Person;
import com.rodrigoamora.testgreendao.entity.PersonDao;

import java.util.List;

public class PersonService {

    public static List<Person> getAllPeolplo(Context activity) {
        PersonDao pesonDao = DaoFactory.createSession(activity).getPersonDao();
        return pesonDao.loadAll();
    }

    public static Person findByName(Context context, String name) {
        PersonDao pesonDao = DaoFactory.createSession(context).getPersonDao();
        return pesonDao.queryBuilder().where(PersonDao.Properties.Name.eq(name)).list().get(0);
    }

    public static void savePerson(Context activity, Person person) {
        PersonDao pesonDao = DaoFactory.createSession(activity).getPersonDao();
        pesonDao.save(person);
    }

}
