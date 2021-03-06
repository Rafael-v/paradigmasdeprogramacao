/*
  Problema: Triângulo (https://olimpiada.ic.unicamp.br/pratique/p1/2014/f1/triangulo/)
  Ana e suas amigas estão fazendo um trabalho de geometria para o colégio, em que precisam formar
  vários triângulos, numa cartolina, com algumas varetas de comprimentos diferentes. Logo elas
  perceberam que não dá para formar triângulos com três varetas de comprimentos quaisquer. Se uma
  das varetas for muito grande em relação às outras duas, não dá para formar o triângulo. Ana fez
  uma pesquisa na internet e aprendeu que com três varetas é possível formar um triângulo quando,
  para todas as varetas, vale a seguinte relação: o comprimento da vareta é menor do que a soma
  dos comprimentos das outras duas varetas. Por exemplo, se os comprimentos forem 6, 9 e 5, vai
  dar para formar o triângulo, pois a relação vale para as três varetas: 6<9+5, 9<6+5 e 5<6+9.
  Mas, se os comprimentos forem, por exemplo, 4, 10 e 3, não vai dar para formar um triângulo,
  porque a relação não vale para uma das varetas (pois 10 não é menor do que 3+4).
  
  Neste problema, você precisa ajudar Ana e suas amigas a descobrir se, dados os comprimentos de quatro
  varetas, é ou não é possível selecionar três varetas, dentre as quatro, e formar um triângulo!

  Entrada: A entrada é composta por apenas uma linha contendo quatro números inteiros.
  Saída: Seu programa deve produzir apenas uma linha contendo o caractere "S", caso seja possível formar
  o triângulo; ou o caractere "N", caso não seja possível formar o triângulo.
*/

checa(X,Y,Z) :-
    X < (Y + Z),
    Y < (X + Z),
    Z < (X + Y).
    
triangulo(A,B,C,D) :-
    checa(A,B,C);
    checa(A,B,D);
    checa(A,C,D);
    checa(B,C,D).
