package by.epam.training.bank.dao.impl;

import by.epam.training.bank.dao.root.HeadBankDataDaoImpl;
import by.epam.training.bank.entity.Event;
import by.epam.training.bank.entity.HeadBankData;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

public class EventDaoImpl extends HeadBankDataDaoImpl<Event> {
    @Override
    protected List<Event> getDataEntities() {
        return getRootObject().getData().getEvents();
    }

    @Override
    protected HeadBankData setDataEntities(List<Event> list) {
        HeadBankData root = getRootObject();
        root.getData().setEvents(list);
        return root;
    }
}
