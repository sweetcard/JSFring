package fr.pinguet62.jsfring.model.sql;

import static fr.pinguet62.jsfring.model.sql.Profile.builder;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/** @see Profile */
public final class ProfileTest {

    /** @see Profile#equals(Object) */
    @Test
    public void test_equals() {
        assertThat(builder().build(), is(equalTo(builder().build())));
        assertThat(builder().id(42).build(), is(equalTo(builder().id(42).build())));
        assertThat(builder().id(42).title("a title").build(), is(equalTo(builder().id(42).title("other value").build())));

        assertThat(builder().id(1), is(not(equalTo(builder().id(2).build()))));
        assertThat(builder().build(), is(not(equalTo("other type"))));
        assertThat("other type", is(not(equalTo(builder().build()))));
    }

}