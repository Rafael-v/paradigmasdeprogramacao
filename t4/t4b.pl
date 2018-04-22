/*   
     T4b - Rafael Vales
     Resolvendo problemas da OBI em Prolog
     
     Problema: Revezamento (https://olimpiada.ic.unicamp.br/static/extras/obi2014/provas/ProvaOBI2014_f1i1.pdf)
     Oito alunos – Beto, Dulce, Guto, Júlia, Kelly, Neto, Silvia e Vivian decidiram tentar quebrar o recorde
     da tradicional prova de revezamento e resistência de natação que acontece todos os anos na escola.
     Nessa prova, cada um dos oito competidores da equipe deve nadar mil metros, em estilo livre, na
     forma de revezamento: cada nadador cai na piscina para nadar apenas uma vez, um de cada vez. O
     objetivo é que todos nadem no menor tempo possível. Depois de muita discussão, os competidores
     decidiram que a ordem em que cairão na piscina deve obedecer às seguintes condições:
     - Silvia não nada por último.
     - Vivian nada após Júlia e Neto nadarem.
     - O primeiro a nadar é ou Beto ou Dulce.
     - Guto nada antes de Júlia, com exatamente uma pessoa nadando entre eles.
     - Kelly nada antes de Neto, com exatamente duas pessoas nadando entre eles.
*/

regra1(X) :- not(X = [_,_,_,_,_,_,_,s]).

regra2(X) :-
    nth0(PosVivian,X,v),
    nth0(PosJulia,X,j),
    nth0(PosNeto,X,n),
    PosVivian > PosJulia,
    PosVivian > PosNeto.

regra3([X|_]) :-
    X = b;
    X = d.

regra4(X) :-
    nth0(PosGuto,X,g),
    PosJulia is PosGuto + 2,
    nth0(PosJulia,X,j).

regra5(X) :-
    nth0(PosKelly,X,k),
    PosNeto is PosKelly + 3,
    nth0(PosNeto,X,n).
    
revezamento(X) :-
    X = [_,_,_,_,_,_,_,_],
    regra1(X),
    regra2(X),
    regra3(X),
    regra4(X),
    regra5(X).

/*
     Questão 21. Qual das seguintes alternativas é
     uma possível lista completa e correta dos nadadores
     do primeiro para o último?
     (A) Dulce, Kelly, Silvia, Guto, Neto, Beto, Júlia, Vivian
     (B) Dulce, Silvia, Kelly, Guto, Neto, Júlia, Beto, Vivian
     (C) Beto, Kelly, Silvia, Guto, Neto, Júlia, Vivian, Dulce
     (D) Beto, Guto, Kelly, Júlia, Dulce, Neto, Vivian, Silvia
     (E) Beto, Silvia, Dulce, Kelly, Vivian, Guto, Neto, Júlia
 */
 
 /*
  ?- revezamento([d,k,s,g,n,b,j,v]).
  ?- revezamento([d,s,k,g,n,j,b,v]).
  ?- revezamento([b,k,s,g,n,j,v,d]).
  ?- revezamento([b,g,k,j,d,n,v,s]).
  ?- revezamento([b,s,d,k,v,g,n,j]).
 */
 
