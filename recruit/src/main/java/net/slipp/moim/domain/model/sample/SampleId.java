/**
 * Created by joenggyu0@gmail.com on 4/7/20
 * Github : http://github.com/lenkim
 */

package net.slipp.moim.domain.model.sample;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(staticName = "of")
@ToString
@EqualsAndHashCode
public class SampleId {

    @Id
    @NotNull @NonNull
    private Long id;

}
