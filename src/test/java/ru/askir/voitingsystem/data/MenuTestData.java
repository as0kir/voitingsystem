package ru.askir.voitingsystem.data;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.askir.voitingsystem.model.Menu;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;
import static ru.askir.voitingsystem.web.json.JsonUtil.writeIgnoreProps;

public class MenuTestData {
    public static final int MENU1_1_ID = START_SEQ + 4;
    public static final int MENU1_2_ID = START_SEQ + 5;
    public static final int MENU2_1_ID = START_SEQ + 6;
    public static final int MENU2_2_ID = START_SEQ + 7;

    public static final Menu MENU1_1 = new Menu(MENU1_1_ID, of(2018, Month.APRIL, 7));
    public static final Menu MENU1_2 = new Menu(MENU1_1_ID + 1, of(2018, Month.APRIL, 8));
    public static final Menu MENU2_1 = new Menu(MENU1_1_ID + 2, of(2018, Month.APRIL, 7));
    public static final Menu MENU2_2 = new Menu(MENU1_1_ID + 3, of(2018, Month.APRIL, 8));

    public static final List<Menu> MENUS = Arrays.asList(MENU1_2, MENU1_1);

    public static Menu getCreated() {
        return new Menu(null,of(2018, Month.APRIL, 9));
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_1_ID, MENU1_1.getDateSet());
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "dishes");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "dishes").isEqualTo(expected);
    }


    public static ResultMatcher contentJson(Menu... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "restaurant", "dishes"));
    }

    public static ResultMatcher contentJson(Menu expected) {
        return content().json(writeIgnoreProps(expected, "restaurant", "dishes"));
    }
}
