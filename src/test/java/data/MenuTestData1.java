package data;

import ru.askir.voitingsystem.model.Menu;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;

public class MenuTestData1 {
    public static final int MENU1_ID = START_SEQ + 4;

    public static final Menu MENU1_1 = new Menu(MENU1_ID, of(2018, Month.APRIL, 7));
    public static final Menu MENU1_2 = new Menu(MENU1_ID + 1, of(2018, Month.APRIL, 8));
    public static final Menu MENU2_1 = new Menu(MENU1_ID + 2, of(2018, Month.APRIL, 7));
    public static final Menu MENU2_2 = new Menu(MENU1_ID + 3, of(2018, Month.APRIL, 8));

    public static final List<Menu> MENUS = Arrays.asList(MENU1_2, MENU1_1);

    public static Menu getCreated() {
        return new Menu(null,of(2018, Month.APRIL, 9));
    }

    public static Menu getUpdated() {
        return new Menu(MENU1_ID, MENU1_1.getDateSet());
    }

    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}