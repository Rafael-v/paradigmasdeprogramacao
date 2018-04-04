% Desenho da árvore genealógica criada:
% https://i.imgur.com/r1imj3r.png

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

descendente(nathalia,cristina).
descendente(nathalia,joao).
descendente(claudia,cristina).
descendente(claudia,joao).
descendente(angelo,maria).
descendente(angelo,jorge).
descendente(vinicius,maria).
descendente(vinicius,jorge).
descendente(isabel,claudia).
descendente(isabel,angelo).
descendente(enzo,claudia).
descendente(enzo,angelo).
descendente(thiago,vinicius).
descendente(thiago,ana).
descendente(gustavo,vinicius).
descendente(gustavo,ana).

filho(X,Y) :- masculino(X), descendente(X,Y).
