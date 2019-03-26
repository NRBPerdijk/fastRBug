firstR <- function(passedValue) {
    resourceFinder$sourceResource("r/otherRFunctionInJar.R")

    otherRFunctionInJar("Hello Indirectly")
}

