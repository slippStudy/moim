package net.slipp.common.domain.model;

import net.slipp.ddd.events.DomainEventPublisher;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DomainEventResetExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        DomainEventPublisher.instance().reset();
    }
}
