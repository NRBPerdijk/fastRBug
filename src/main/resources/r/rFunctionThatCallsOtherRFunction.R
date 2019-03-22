firstR <- function(pathToRFile) {
    source(pathToRFile)

    print(pathToRFile)

    otherRFunctionInJar("Hello Indirectly")
}

