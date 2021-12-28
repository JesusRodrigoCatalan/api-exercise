Feature: Check pet store

  Scenario: Get available pets and assert result
    When I get "available" pets
    Then I assert I get "available" pets as expected

  Scenario: Post a new available pet to the store
    When I send a POST with a new pet with id 100
    Then I assert the new pet is added

  Scenario: Update our pet status to sold
    When I send an UPDATE to the new pet with id 100 and new status "sold"
    Then I assert the existing pet is updated to "sold"

  Scenario: Delete an existing pet
    When I send a DELETE the new pet with id 100
    Then I assert the existing pet is deleted



