Feature: Features of the action methods class for a flying nomad object

  Scenario: validate flying nomad dies when going out of the map from the north limit
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a flying nomad at rowIdx 3 and coldIdx 5
    And the flying nomad is flying to the north
    And the flying nomad is out of the map from the north limit
    When processing the flying nomad
    Then the flying nomad should die

  Scenario: validate flying nomad dies when going out of the map from the south limit
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a flying nomad at rowIdx 3 and coldIdx 5
    And the flying nomad is flying to the south
    And the flying nomad is out of the map from the south limit
    When processing the flying nomad
    Then the flying nomad should die

  Scenario: validate flying nomad dies when going out of the map from the west limit
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a flying nomad at rowIdx 3 and coldIdx 5
    And the flying nomad is flying to the west
    And the flying nomad is out of the map from the west limit
    When processing the flying nomad
    Then the flying nomad should die

  Scenario: validate flying nomad dies when going out of the map from the east limit
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a flying nomad at rowIdx 3 and coldIdx 5
    And the flying nomad is flying to the east
    And the flying nomad is out of the map from the east limit
    When processing the flying nomad
    Then the flying nomad should die

  Scenario: validate flying nomad is marked as removable when dead
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a flying nomad at rowIdx 3 and coldIdx 5
    And the flying nomad is dead
    When processing the flying nomad
    Then the flying nomad should be marked as removable from the sprite list