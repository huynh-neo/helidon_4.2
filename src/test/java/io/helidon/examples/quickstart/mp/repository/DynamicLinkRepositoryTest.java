package io.helidon.examples.quickstart.mp.repository;

import io.helidon.microprofile.testing.AddBean;
import io.helidon.microprofile.testing.junit5.HelidonTest;
import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import io.helidon.examples.quickstart.mp.model.DynamicLink;

@HelidonTest
@AddBean(MockDataSource.class) // Optional, if mocking DB
class DynamicLinkRepositoryTest {

    @Inject
    DynamicLinkRepository repository;

    @ParameterizedTest
    @CsvFileSource(resources = "/csv/repository/dynamiclink/test-data.csv", numLinesToSkip = 1)
    void testInsertAndFind(String url, String description) {
        DynamicLink link = new DynamicLink(0l, url, description);
        repository.create(link);
        List<DynamicLink> links = repository.findAll();

        assertTrue(links.stream().anyMatch(l -> l.getUrl().equals(url) && l.getDescription().equals(description)));
    }
}
