package ru.askir.voitingsystem.data;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.askir.voitingsystem.model.Dish;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;
import static ru.askir.voitingsystem.web.json.JsonUtil.writeIgnoreProps;

public class DishTestData {
    public static final int DISH1_ID = START_SEQ + 8;

    public static final Dish DISH1_1 = new Dish(DISH1_ID, "1Борщ", new BigDecimal("50.00"));
    public static final Dish DISH1_2 = new Dish(DISH1_ID + 1, "2Котлета", new BigDecimal("30.00"));
    public static final Dish DISH1_3 = new Dish(DISH1_ID + 2, "3Компот", new BigDecimal("20.50"));
    public static final Dish DISH2_1 = new Dish(DISH1_ID + 3, "1Оливье", new BigDecimal("72.30"));

    public static final List<Dish> DISHES = Arrays.asList(DISH1_1, DISH1_2, DISH1_3);

    public static Dish getCreated() {
        return new Dish(null, "4Новое блюдо", new BigDecimal("100.00"));
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

    public static ResultMatcher contentJson(Dish... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "menu"));
    }

    public static ResultMatcher contentJson(Dish expected) {
        return content().json(writeIgnoreProps(expected, "menu"));
    }
}
