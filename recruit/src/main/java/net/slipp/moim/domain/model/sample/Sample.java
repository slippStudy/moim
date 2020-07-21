package net.slipp.moim.domain.model.sample;

import com.google.common.collect.Sets;
import lombok.*;
import net.slipp.ddd.domain.DomainEventPublisher;
import net.slipp.moim.domain.model.recruit.*;
import net.slipp.moim.domain.model.user.UserId;
import net.slipp.utils.Assertions;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static net.slipp.moim.domain.model.recruit.Status.*;

@Table("sample")
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@Getter
@Setter
public class Sample {

    @Embedded.Nullable
    private SampleId id;

    @NotNull @NonNull
    private String name;

    @Embedded.Nullable
    private Address address;

//    private Set<SampleChild> sampleChildren = Sets.newHashSet();

}
