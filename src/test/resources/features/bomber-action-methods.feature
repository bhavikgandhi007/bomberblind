Feature: Features of the action methods class for a bomber object

  Scenario: validate bomber dies when reaching a burning case
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5 with 5 lifes
    Given a burning case at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should die

  Scenario: validate bomber dies when crossing a walking enemy
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5 with 5 lifes
    Given a walking enemy at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should die

  Scenario: validate bomber dies when crossing a breaking enemy
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5 with 5 lifes
    Given a breaking enemy at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should die

  Scenario: validate bomber does not die when crossing an enemy and invincible
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    And the bomber is invincible
    Given a walking enemy at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should not die

  Scenario: validate bomber loses a life after dying
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5 with 5 lifes
    And the bomber is dead
    When processing the bomber
    Then the bomber should has 4 lifes

  Scenario: validate bomber re-inits after dying (but not definitively dead)
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5 with 5 lifes
    And the bomber is dead
    When processing the bomber
    Then the bomber is re-init

  Scenario: validate bomber is marked as removable when definively dead
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5 with 0 lifes
    And the bomber is dead
    When processing the bomber
    Then the bomber should be marked as removable from the sprite list