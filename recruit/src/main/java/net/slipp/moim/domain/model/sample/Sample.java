package net.slipp.moim.domain.model.sample;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Sample {

    private Long id;
    private String name;
    private String address;

}
