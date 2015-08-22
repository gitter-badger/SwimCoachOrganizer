package ch.tiim.sco.database.mapper;

import ch.tiim.sco.database.model.IndexedSet;
import ch.tiim.sco.database.model.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.*;
import org.jooq.impl.DefaultRecordMapper;

import java.util.UUID;

public class RecordMapperProviderImpl implements RecordMapperProvider {
    private static final Logger LOGGER = LogManager.getLogger(RecordMapperProviderImpl.class.getName());

    @SuppressWarnings("unchecked")
    @Override
    public <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> type) {
        // UUID mappers will always try to find the ID column
        if (type == UUID.class) {
            Field<?> f = null;
            for (int i = 0; i < recordType.size(); i++) {
                Field<?> field = recordType.field(i);
                if (field.getName().endsWith("_id")) {
                    f = field;
                    break;
                }
            }
            final Field<?> finalF = f;
            return record -> (E) record.getValue(finalF);
        }
        if (type == Set.class) {
            return (RecordMapper<R, E>) new SetMapper();
        } else if (type == IndexedSet.class) {
            return (RecordMapper<R, E>) new IndexedSetMapper();
        }
        return new DefaultRecordMapper(recordType, type);
    }
}
