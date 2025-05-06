Feature: Dynamic Link Service

  Scenario: Retrieve all dynamic links
    Given the link service is running
    When I request the list of dynamic links
    Then I should receive a successful response
    And the response should include links