# PCBE-news

Rulati main-ul din clasa Main in care se initializeaza un mai multi editori si mai multi cititori (ambii "actori" vor avea diferite actiuni generate random). 

Dispatcher-ul este in cadrul NewsSystem-ului. 
Pot avea loc mai multe evenimente:
- PUBLISHED -> apare in cazul in care un editor publica un articol nou: este salvat noul articol in NewsSystem si este adaugat evenimentul in coada dispatcher-ului. Vor fi notificati doar cititorii abonati la tipul acela de stiri (sectiune, autor, etc).
- UPDATED -> apare in cazul in care un editor editeaza un anumit articol. Vor fi notificati cititorii abonati la stirea respectiva.
- READ -> cand un cititor citeste un articol, se lanseaza un eveniment si e notificat NewsSystem-ul. Este actualizat numarul de citiri din newsSystem (aici, pt ca un editor sa afle in timp real de cate ori a fost citit un articol poate apela functia getNoViews() al NewsSystem-ului.)

Logica ce tine de dispatcher si evenimente este independenta de restul proiectului. Pentru a refolosi codul in viitor trebuie:
- sa se extinda clasa Event la un eveniment personalizat (aici NewsEvent)
- sa se extinda clasa NewsFilter in cazul in care se doreste folosirea unor filtre
- sa se implementeze EventHandler pentru cei care se aboneaza
- la adaugarea unui eveniment, se apeleaza dispatcher.addEvent(..)
- pentru inregistrare se apeleaza dispatcher.registerListener(..)


Echipa:

Boboi Tomas Adrian
Brumar Raul
Dobre Ewald-Emanuel
Grosu Mihai-Alexandru

