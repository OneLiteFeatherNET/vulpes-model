package net.onelitefeather.vulpes.api.generator;

import com.github.f4b6a3.uuid.UuidCreator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

public final class UuidV7Generator implements BeforeExecutionGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return UuidCreator.getTimeOrderedEpoch();
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }
}
