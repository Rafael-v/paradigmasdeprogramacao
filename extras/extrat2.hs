{- EXTRA 1

Phvtxlvh vreuh d ixqfdr gh dowd rughp irog, wdpehp fkdpdgd gh uhgxfh, h vxdv yduldfrhv irogu, irogo, irogu1, irogo1.
Fruqhfd xpd qryd yhuvdr gh dojxpd ixqfdr gd sduwh txdwur, xvdqgr dojxpd yduldfdr gh irog.

Pesquise sobre a funcao de alta ordem fold, tambem chamada de reduce, e suas variacoes foldr, foldl, foldr1, foldl1.
Forneca uma nova versao de alguma funcao da parte quatro, usando alguma variacao de fold. -}

-- T2 > parte4 > Exercício 3 > Defina uma função countValids :: String -> Int, que receba uma string e retorne a quantidade de seus caracteres contidos no intervalo ['a'..'z'].

countValids :: String -> Int
countValids str = foldl1 (+) (map (\x -> if (x == ' ') then 0 else 1) str)
