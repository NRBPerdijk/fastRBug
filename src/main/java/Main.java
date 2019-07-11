import java.io.IOException;
import java.util.function.Function;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

public class Main {

    public static void main(String... args) throws IOException {
        Context graalContext = Context.newBuilder().allowAllAccess(true).build();

        Source source = Source.newBuilder("R", Main.class.getResource("r/fun_heapOOM.R")).build();
        final Function<String, String> createHeapOOM = graalContext.eval(source).as(Function.class);

        while (true) {
            String response = createHeapOOM.apply("Hello there!");
            System.out.println(response);
        }
    }

}
