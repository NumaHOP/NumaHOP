package org.numahop.numahop.domain.util;

import org.numahop.numahop.domain.AbstractDomainObject;
import java.lang.reflect.Member;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.id.factory.spi.CustomIdGeneratorCreationContext;
import org.hibernate.id.uuid.UuidGenerator;

/**
 * CustomUUIDGenerator returns the current identifier if it is defined, or generate a new
 * one with UUIDGenerator
 *
 * @author Sébastien DITER
 */
public class CustomUUIDGenerator extends UuidGenerator {

	public CustomUUIDGenerator(final org.hibernate.annotations.UuidGenerator config, final Member idMember,
			final CustomIdGeneratorCreationContext creationContext) {
		super(config, idMember, creationContext);
	}

	@Override
	public Object generate(final SharedSessionContractImplementor session, final Object object,
			final Object currentValue, final EventType eventType) throws HibernateException {
		if (object != null && object instanceof AbstractDomainObject) {
			final String identifier = ((AbstractDomainObject) object).getIdentifier();

			if (identifier != null) {
				return identifier;
			}
		}
		return super.generate(session, object, currentValue, eventType);
	}

}
