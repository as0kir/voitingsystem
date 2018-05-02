package ru.askir.voitingsystem.data;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.askir.voitingsystem.model.Restaurant;
import ru.askir.voitingsystem.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.askir.voitingsystem.model.AbstractBaseEntity.START_SEQ;
import static ru.askir.voitingsystem.web.json.JsonUtil.writeIgnoreProps;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "1Утюг");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "2Шашлык");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menus");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("menus").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Restaurant... expected) {
        return content().json(writeIgnoreProps(Arrays.asList(expected), "menus"));
    }

    public static ResultMatcher contentJson(Restaurant expected) {
        return content().json(writeIgnoreProps(expected, "menus"));
    }
}
