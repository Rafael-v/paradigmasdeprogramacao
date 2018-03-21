-- 1 --
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = []
geraTabela x = (x,x*x):(geraTabela (x-1))
