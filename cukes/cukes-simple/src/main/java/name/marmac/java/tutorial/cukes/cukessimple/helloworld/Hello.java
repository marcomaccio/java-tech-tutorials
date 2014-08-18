package name.marmac.java.tutorial.cukes.cukessimple.helloworld;

/**
 * Created by maccio on 29/01/14.
 */
public class Hello {

    private final String greeting;

    public Hello(String greeting) {
        this.greeting = greeting;
    }

    public String sayHi() {
        return "Hello World, " + greeting ;
    }
}
