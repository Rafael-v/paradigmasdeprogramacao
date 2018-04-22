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

regra1(CD) :- CD = [_,_,_,s,_,_,_].

regra2(CD) :-
    nth0(IdxS,CD,s),
    nth0(IdxW,CD,w),
    nth0(IdxY,CD,y),
    IdxY < IdxS,
    IdxW < IdxS.
    
regra3(CD) :-
    nth0(IdxT,CD,t),
    nth0(IdxW,CD,w),
    IdxT < IdxW.

regra4(X,CD) :- nth1(6,CD,X).

regra6(CD) :- regra4(z,CD).
