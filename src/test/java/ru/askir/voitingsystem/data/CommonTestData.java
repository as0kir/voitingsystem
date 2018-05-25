package ru.askir.voitingsystem.data;

import java.time.LocalDate;

import static java.time.LocalDate.of;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;

public class CommonTestData {
    public static final int WRONG_ID = START_SEQ + 10000;
    public static final LocalDate WRONG_DATE = of(2020, 10, 10);
}
