/**
 * Created by joenggyu0@gmail.com on 5/11/20
 * Github : http://github.com/lenkim
 */

package net.slipp.franchise.domain.model.user;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class UserId {

    private String value;

    public static UserId of(String value){
        return new UserId(value);
    }

    UserId(String value) {
        this.value = value;
    }
}
