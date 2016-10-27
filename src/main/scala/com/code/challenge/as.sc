

val a = "1 | 2 | 3 | 4"

val Array(k, l, m, n)  = a.split("|").map(_.trim).filterNot(x =>  x == "|" | x == "")


k