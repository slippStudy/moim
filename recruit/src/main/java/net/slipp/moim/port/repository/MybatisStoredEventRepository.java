package net.slipp.moim.port.repository;

import net.slipp.ddd.events.StoredEvent;
import net.slipp.ddd.events.StoredEventRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MybatisStoredEventRepository extends StoredEventRepository {

    @Override
    default StoredEvent save(StoredEvent anEvent) {

        this.insert(anEvent);
        return this.findStoredEventByEventId(anEvent.eventId());
    };

    @Insert("insert into stored_event(event_id, event_body, occurred_on, type_name) values (#{eventId}, #{eventBody}, #{occurredOn}, #{typeName})")
    void insert(StoredEvent anEvent);

    @Select("select * from stored_event where event_id = #{eventId}")
    StoredEvent findStoredEventByEventId(String eventId);
}
