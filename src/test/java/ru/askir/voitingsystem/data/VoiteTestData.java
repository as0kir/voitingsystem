package ru.askir.voitingsystem.data;

import ru.askir.voitingsystem.model.Voite;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.askir.voitingsystem.data.MenuTestData.*;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;
import static ru.askir.voitingsystem.data.UserTestData.USER;
import static ru.askir.voitingsystem.data.UserTestData.ADMIN;

public class VoiteTestData {
    public static final int VOITE1_ID = START_SEQ + 12;

    public static final Voite VOITE1_1 = new Voite(VOITE1_ID, USER, MENU1_1, of(2018, Month.APRIL, 7, 9, 30));
    public static final Voite VOITE1_2 = new Voite(VOITE1_ID + 1, USER, MENU1_2, of(2018, Month.APRIL, 8, 10, 20));
    public static final Voite VOITE2_1 = new Voite(VOITE1_ID + 2, ADMIN, MENU1_1, of(2018, Month.APRIL, 7, 8, 10));

    public static final LocalDateTime rightDate = LocalDateTime.of(2018, Month.APRIL, 7, 9, 30);
    public static final LocalDateTime wrongDate = LocalDateTime.of(2018, Month.APRIL, 7, 11, 15);

    public static final List<Voite> VOITES = Arrays.asList(VOITE1_1);

    public static Voite getCreated() {
        return new Voite(null, ADMIN, MENU2_2, of(2018, Month.APRIL, 8, 10, 15));
    }

    public static Voite getUpdated() {
        return new Voite(VOITE1_ID, VOITE1_1.getUser(), VOITE1_1.getMenu(), VOITE1_1.getDateSet());
    }

    public static Voite getVoited() {
        return new Voite(VOITE1_ID, VOITE1_1.getUser(), MENU2_1, rightDate);
    }

    public static void assertMatch(Voite actual, Voite expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Voite> actual, Voite... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Voite> actual, Iterable<Voite> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user", "menu").isEqualTo(expected);
    }    
}
