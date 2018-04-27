package data;

import ru.askir.voitingsystem.model.Dish;
import ru.askir.voitingsystem.model.Dish;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 8;

    public static final Dish DISH1_1 = new Dish(DISH1_ID, "1Борщ", 50.00f);
    public static final Dish DISH1_2 = new Dish(DISH1_ID + 1, "2Котлета", 30.00f);
    public static final Dish DISH1_3 = new Dish(DISH1_ID + 2, "3Компот", 20.50f);
    public static final Dish DISH2_1 = new Dish(DISH1_ID + 3, "1Оливье", 72.30f);

    public static final List<Dish> DISHES = Arrays.asList(DISH1_3, DISH1_2, DISH1_1);

    public static Dish getCreated() {
        return new Dish(null, "4Новое блюдо", 100.00f);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, DISH1_1.getName(), DISH1_1.getPrice());
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menu").isEqualTo(expected);
    }
}
