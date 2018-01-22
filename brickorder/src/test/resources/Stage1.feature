Feature: Stage 1

  Scenario: create an order
    Given the db is empty
    When client request POST /orders with json data:
    """
    {"bricks":"8"}
    """
    Then the response code should be 201
    Then the following header should present "Location" with value "http://localhost/orders/0"
    
  Scenario: retrieve an existing order
    Given the db is empty
    Given the following orders exist:
    | id  | bricks | status |
    | 0   | 8      |  NEW   |
    When client request GET /orders/0
    Then the response code should be 200
    Then the result json should be:
    """
    {"id":0,"bricks":8,"status":"NEW"}
    """
  Scenario: retrieve a non-existing order
    Given the db is empty
    Given the following orders exist:
    | id  | bricks | status |
    | 0   | 8      |  NEW   |
    When client request GET /orders/1
    Then the response code should be 200
    Then the response should be empty    
  
  Scenario: retrieve all orders
    Given the db is empty
    Given the following orders exist:
    | id  | bricks | status |
    | 0   | 8      |  NEW   |
    | 1   | 9      |  NEW   |
    When client request GET /orders
    Then the response code should be 200
    Then the result json should be:
    """
    [{"id":0,"bricks":8,"status":"NEW"},{"id":1,"bricks":9,"status":"NEW"}]
    """