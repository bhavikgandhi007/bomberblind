Feature: Features of the action methods class

  # bomber:

  Scenario: validate bomber dies when reaching a burning case
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5
    Given a burning case at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should die

  Scenario: validate bomber dies when crossing an enemy
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5
    Given an enemy at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should die

  Scenario: validate bomber does not die when crossing an enemy and invicible
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    And the bomber is invincible
    Given an enemy at rowIdx 3 and coldIdx 5
    When processing the bomber
    Then the bomber should not die

  Scenario: validate bomber re-inits after dying
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given a bomber at rowIdx 3 and coldIdx 5
    And the bomber is dead
    When processing the bomber
    Then the bomber is re-init

  # enemy:

  Scenario: validate enemy dies when reaching a burning case
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given an enemy at rowIdx 3 and coldIdx 5
    Given a burning case at rowIdx 3 and coldIdx 5
    When processing the enemy
    Then the enemy should die

  Scenario: validate enemy gets another direction when reaching an obstacle
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given an enemy at rowIdx 3 and coldIdx 5
    And the enemy is walking to the south
    Given an obstacle case at rowIdx 4 and coldIdx 5
    When processing the enemy
    Then the enemy get another direction

  Scenario: validate enemy should be removed from the sprite list when dead
    Given a MapPoint matrix of 5 rows and 10 cols built with pathway cases
    Given an enemy at rowIdx 3 and coldIdx 5
    And the enemy is dead
    When processing the enemy
    Then the enemy should be removed from the sprite list

    # bomb:

  Scenario: validate bomb explodes when time is done
    Given a MapPoint matrix of 10 rows and 10 cols built with pathway cases
    Given a bomb at rowIdx 4 and coldIdx 4 and a flame size of 3
    Then the bomb is exploding
    When processing the bomb
    Then the bomb has exploded adding the following flames:
      | rowIdx | colIdx |
      | 1      | 4      |
      | 2      | 4      |
      | 3      | 4      |
      | 4      | 4      |
      | 5      | 4      |
      | 6      | 4      |
      | 7      | 4      |
      | 4      | 1      |
      | 4      | 2      |
      | 4      | 3      |
      | 4      | 5      |
      | 4      | 6      |
      | 4      | 7      |
    Then the bomb should be removed from the sprite list

  Scenario: validate bomb explodes when reaching a burning case
    Given a MapPoint matrix of 10 rows and 10 cols built with pathway cases
    Given a bomb at rowIdx 4 and coldIdx 4 and a flame size of 3
    Given a burning case at rowIdx 4 and coldIdx 4
    When processing the bomb
    Then the bomb has exploded adding the following flames:
      | rowIdx | colIdx |
      | 1      | 4      |
      | 2      | 4      |
      | 3      | 4      |
      | 4      | 4      |
      | 5      | 4      |
      | 6      | 4      |
      | 7      | 4      |
      | 4      | 1      |
      | 4      | 2      |
      | 4      | 3      |
      | 4      | 5      |
      | 4      | 6      |
      | 4      | 7      |
    Then the bomb should be removed from the sprite list
