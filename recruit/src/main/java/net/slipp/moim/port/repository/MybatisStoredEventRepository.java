package net.slipp.moim.port.repository;

import net.slipp.ddd.events.StoredEvent;
import net.slipp.ddd.events.StoredEventRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisStoredEventRepository {

    @Insert("insert into stored_event(event_body, occurred_on, type_name) values (#{eventBody}, #{occurredOn}, #{typeName})")
    void save(StoredEvent anEvent);

    @Select("")
    List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);

    @Select("select * from stored_event where event_id > #{aStoredEventId}")
    List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    @Select("")
    long countStoredEvents();

    @Select("")
    Optional<StoredEvent> findByEventId(Long eventId);
}
