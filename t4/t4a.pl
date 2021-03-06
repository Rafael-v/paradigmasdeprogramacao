/*   
     T4 - Rafael Vales
     Resolvendo problemas da OBI em Prolog
     
     Problema: CD Independente (https://olimpiada.ic.unicamp.br/static/extras/obi2017/provas/ProvaOBI2017_f1i2.pdf)
     Uma banda formada por alunos e alunas da escola está gravando um CD com exatamente sete músicas
     distintas – S, T, V, W, X, Y e Z. Cada música ocupa exatamente uma das sete faixas contidas no
     CD. Algumas das músicas são sucessos antigos de rock; outras são composições da própria banda. As
     seguintes restrições devem ser obedecidas:
     - S ocupa a quarta faixa do CD.
     - Tanto W como Y precedem S no CD (ou seja, W e Y estão numa faixa que é tocada antes de S no CD).
     - T precede W no CD (ou seja, T está numa faixa que é tocada antes de W).
     - Um sucesso de rock ocupa a sexta faixa do CD.
     - Cada sucesso de rock é imediatamente precedido no CD por uma composição da banda (ou seja,
     no CD cada sucesso de rock toca imediatamente após uma composição da banda).
     - Z é um sucesso de rock.
*/

regra1(CD) :-
    CD = [_,_,_,s,_,_,_].

regra2(CD) :-
    precede(CD,y,s),
    precede(CD,w,s).
    
regra3(CD) :-
    precede(CD,t,w).

regra4(CD,R,RN,B,BN) :-
    nth1(6,CD,X),
    append([X],R,RN),
    nth1(5,CD,Y),
    nth1(7,CD,Z),
    append([Y,Z],B,BN).

regra5(_,[],_,[]).
regra5(CD,[RH|RT],B,BN) :-
    nextto(Banda,RH,CD),
    regra5(CD,RT,B,B1),
    append([Banda],B1,BN).

regra6(CD,R,RN) :-
    append([z],R,RN),
    nth1(IdxZ,CD,z),
    IdxZ \= 5,
    IdxZ \= 7.

precede(CD,A,B) :-
    nth0(Idx1,CD,A),
    nth0(Idx2,CD,B),
    Idx1 < Idx2.

cdindependente(CD,R,B) :-
    regra1(CD),
    regra2(CD),
    regra3(CD),
    regra4(CD,[],R2,[],B2),
    regra5(CD,R2,B2,B),
    regra6(CD,R2,R).

/*
    Questão 11. Qual das seguintes alternativas poderia
    ser a ordem das músicas no CD, da primeira para a
    sétima faixa?
    (A) T, W, V, S, Y, X, Z
    (B) V, Y, T, S, W, Z, X
    (C) X, Y, W, S, T, Z, S
    (D) Y, T, W, S, X, Z, V
    (E) Z, T, X, W, V, Y, S
*/
 
/*
  ? - questao11([t,w,v,s,y,x,z]).
  ? - questao11([v,y,t,s,w,z,x]).
  ? - questao11([x,y,w,s,t,z,s]).
  ? - questao11([y,t,w,s,x,z,v]).
  ? - questao11([z,t,x,w,v,y,s]).
  Correta: Letra D
*/

questao11(CD) :-
    cdindependente(CD,_,_).

/*
    Questão 13. Qual das seguintes músicas é
    necessariamente uma composição da banda?
    (A) S
    (B) T
    (C) X
    (D) Y
    (E) W
*/
 
/*
  ? - questao13(s).
  ? - questao13(t).
  ? - questao13(x).
  ? - questao13(y).
  ? - questao13(w).
  Correta: Letra C
*/

questao13(X) :-
    permutation(Perm,[s,t,v,w,x,y,z]),
    cdindependente(Perm,_,B),
    member(X,B),
    !.
