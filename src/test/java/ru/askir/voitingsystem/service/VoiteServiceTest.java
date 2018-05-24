package ru.askir.voitingsystem.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.askir.voitingsystem.model.Voite;
import ru.askir.voitingsystem.util.DateTimeUtil;
import ru.askir.voitingsystem.util.exception.NotFoundException;

import static java.time.LocalTime.of;
import static ru.askir.voitingsystem.data.MenuTestData.*;
import static ru.askir.voitingsystem.data.VoiteTestData.*;
import static ru.askir.voitingsystem.data.UserTestData.USER_ID;
import static ru.askir.voitingsystem.data.CommonTestData.WRONG_ID;

@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
@ContextConfiguration({"classpath:spring/spring-app.xml", "classpath:spring/spring-db.xml"})
public class VoiteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoiteService service;

    @Autowired
    private static DateTimeUtil dateTimeUtil;

    @BeforeClass
    public static void before() {
        dateTimeUtil.setMockDate(null);
    }

    @Test
    public void setVoite() throws Exception {
        dateTimeUtil.setMockDate(rightDate);
        service.setVoite(USER_ID, MENU2_1_ID);
        assertMatch(service.get(USER_ID, rightDate.toLocalDate()), getVoited());
    }

    @Test
    public void getAllForDate() throws Exception {
        dateTimeUtil.setMockDate(rightDate);
        assertMatch(service.getAll(VOITE1_1.getMenu().getDateSet()), VOITE1_1, VOITE2_1);
    }

    @Test
    public void getAll() throws Exception {
        dateTimeUtil.setMockDate(VOITE1_1.getMenu().getDateSet().atStartOfDay());
        assertMatch(service.getAll(), VOITE1_1, VOITE2_1);
    }

    @Test
    public void get() throws Exception {
        Voite actual = service.get(USER_ID, MENU1_1.getDateSet());
        assertMatch(actual, VOITE1_1);
    }
}
