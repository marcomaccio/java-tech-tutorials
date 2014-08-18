$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('name/marmac/java/tutorial/cukes/cukessimple/simpletest/helloworldcucumber.feature');
formatter.feature({
  "id": "hello-world",
  "description": "",
  "name": "Hello World",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "hello-world;say-hello",
  "description": "",
  "name": "Say Hello",
  "keyword": "Scenario",
  "line": 3,
  "type": "scenario"
});
formatter.step({
  "name": "I have a hello app with \"Marco\"",
  "keyword": "Given ",
  "line": 4
});
formatter.step({
  "name": "I ask it to say hi",
  "keyword": "When ",
  "line": 5
});
formatter.step({
  "name": "it should answer with \"Hello World, Marco\"",
  "keyword": "Then ",
  "line": 6
});
formatter.match({
  "arguments": [
    {
      "val": "Marco",
      "offset": 25
    }
  ],
  "location": "HelloWorldStepdefs.i_have_a_hello_app_with(String)"
});
formatter.result({
  "duration": 177129000,
  "status": "passed"
});
formatter.match({
  "location": "HelloWorldStepdefs.I_ask_it_to_say_hi()"
});
formatter.result({
  "duration": 68000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Hello World, Marco",
      "offset": 23
    }
  ],
  "location": "HelloWorldStepdefs.it_should_answer_with(String)"
});
formatter.result({
  "duration": 2548000,
  "status": "passed"
});
});