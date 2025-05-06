Feature: Analytics Service

  Scenario: Retrieve all click analytics
    Given the analytics service is running
    When I request the list of click analytics
    Then the analytics response should be successful
    And the analytics response should include records
