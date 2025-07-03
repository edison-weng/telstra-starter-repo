Feature: SIM Card Activator

  Scenario Outline: SIM Card Activation
    Given I have a SIM card with ICCID "<iccid>"
    And the customer email is "<email>"
    When I submit a SIM card activation request
    Then the activation request response should return "<expected_result>"
    And I should receive a record ID which is <expected_record_id>

    When I query the activation status for record ID <expected_record_id>
    Then the SIM card status should be "<expected_result>"

    Examples:
      | scenario_type | iccid               | email                           | expected_result | expected_record_id |
      | Successful    | 1255789453849037777 | successful.customer@example.com | successful      | 1                  |
      | Failed        | 8944500102198304826 | failed.customer@example.com     | failed          | 2                  |