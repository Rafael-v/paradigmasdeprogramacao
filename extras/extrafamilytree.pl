% Desenho dessa árvore genealógica: https://i.imgur.com/r1imj3r.png

masculino(joao).
masculino(jorge).
masculino(angelo).
masculino(vinicius).
masculino(enzo).
masculino(thiago).
masculino(gustavo).
feminino(cristina).
feminino(maria).
feminino(nathalia).
feminino(claudia).
feminino(ana).
feminino(isabel).

descendente(cristina, nathalia).
descendente(cristina, claudia).
descendente(joao,nathalia).
descendente(joao,claudia).
descendente(maria,angelo).
descendente(maria,vinicius).
descendente(jorge,angelo).
descendente(jorge,vinicius).
descendente(claudia,isabel).
descendente(claudia,enzo).
descendente(angelo,isabel).
descendente(angelo,enzo).
descendente(vinicius,thiago).
descendente(vinicius,gustavo).
descendente(ana,thiago).
descendente(ana,gustavo).

pai(X,Y) :- masculino(X), descendente(X,Y).
mae(X,Y) :- feminino(X), descendente(X,Y).
filho(X,Y) :- masculino(X), descendente(Y,X).
filha(X,Y) :- feminino(X), descendente(Y,X).
irmao(X,Y) :- masculino(X).
irma(X,Y) :- feminino(X).
primo(X,Y) :- masculino(X).
prima(X,Y) :- feminino(X).
tio(X,Y) :- masculino(X).
tia(X,Y) :- feminino(X).
sobrinho(X,Y) :- masculino(X).
sobrina(X,Y) :- feminino(X).
