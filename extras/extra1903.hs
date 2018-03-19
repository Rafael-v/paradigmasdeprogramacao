{- Extra dado na aula 19/03
Dada uma lista de tuplas inteiro, criar lista com os primeiros elementos da tupla que forem pares

Input:  [(1,2),(4,3),(8,9)]
Output: [4, 8] -}

filtraTupla :: [(Int,Int)] -> [Int]
filtraTupla list = filter even (map fst list)
