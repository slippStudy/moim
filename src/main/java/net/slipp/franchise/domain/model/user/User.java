/**
 * Created by joenggyu0@gmail.com on 5/11/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.user;

import net.slipp.franchise.domain.model.recruit.RecruitId;

import javax.validation.constraints.NotNull;

public class User {

    @NotNull
    private final UserId id;

    @NotNull
    private final String name;

    public User(@NotNull UserId id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
}
