package io.helidon.examples.quickstart.mp.repository;

import java.util.List;

import io.helidon.examples.quickstart.mp.model.DynamicLink;

public interface DynamicLinkRepository {

    public DynamicLink create(DynamicLink link);

    public List<DynamicLink> findAll();
}
