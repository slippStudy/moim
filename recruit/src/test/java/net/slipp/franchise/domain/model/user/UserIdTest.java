package net.slipp.franchise.domain.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class UserIdTest {


    @Test
    @DisplayName("user id 생성")
    void userId_is_email() {

        final String email = "test@gmail.com";

        assertThatIllegalStateException().isThrownBy(() -> UserId.of(null));
        assertThatIllegalStateException().isThrownBy(() -> UserId.of(EMPTY));
        assertThatIllegalStateException().isThrownBy(() -> UserId.of(SPACE));
        assertThatIllegalStateException().isThrownBy(() -> UserId.of("This is not email!!"));


        assertThat(UserId.of(email)).hasToString(email);
        assertThat(UserId.of(email + SPACE)).hasToString(email);
        assertThat(UserId.of(SPACE + email)).hasToString(email);


    }
}