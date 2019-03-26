import java.io.IOException;
import java.util.function.Function;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

public class Main {

    public static void main(String... args) throws IOException {
        Context graalContext = Context.newBuilder().allowAllAccess(true).build();

        graalContext.getBindings("R").putMember("resourceFinder", new ResourceFinder());

        Source sourceThatDependsOnOtherSource  = Source.newBuilder("R", Main.class.getResource("r/rFunctionThatCallsOtherRFunction.R")).build();
        final Function<String, String> callToFunctionThatCallsOtherFunction = graalContext.eval(sourceThatDependsOnOtherSource).as(Function.class);

        System.out.println(callToFunctionThatCallsOtherFunction.apply("Passed Value"));
    }

}
