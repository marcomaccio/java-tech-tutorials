Feature: Hello World

    Scenario: Say Hello
    Given I have a hello app with "Marco"
    When I ask it to say hi
    Then it should answer with "Hello World, Marco"