package net.onelitefeather.vulpes.api.generator;

import com.github.f4b6a3.uuid.UuidCreator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

import static org.hibernate.generator.EventTypeSets.INSERT_ONLY;

/**
 * The {@link UuidV7Generator} class implements a generator that creates time-ordered UUIDs (version 7) for new entities.
 *
 * @author TheMeinerLP
 * @version 1.0.0
 * @since 0.1.0
 */
public final class UuidV7Generator implements BeforeExecutionGenerator {

    /**
     * Generates a time-ordered UUID (version 7) for the given entity.
     *
     * @param session      the session in which the entity is being created
     * @param owner        the owner of the entity
     * @param currentValue the current value of the UUID field (not used)
     * @param eventType    the type of event triggering this generation (should be INSERT)
     * @return a new time-ordered UUID
     */
    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return UuidCreator.getTimeOrderedEpoch();
    }

    /**
     * Returns the set of event types for which this generator is applicable.
     *
     * @return an EnumSet containing the INSERT_ONLY event type
     */
    @Override
    public EnumSet<EventType> getEventTypes() {
        return INSERT_ONLY;
    }
}
