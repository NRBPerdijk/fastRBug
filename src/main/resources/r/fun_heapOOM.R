heapOOM <- function(string) {
    library(dplyr)
    library(tidyr)

    df <- tibble(
      x = 1:3,
      y = c("a", "d,e,f", "g,h")
    )

    df %>% unnest(y = strsplit(y, ","))

    string
}