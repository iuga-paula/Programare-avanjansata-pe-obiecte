# Programare avansata pe obiecte
Teme si proiect in Java

# [Proiect](https://github.com/iuga-paula/Programare-avansata-pe-obiecte/tree/master/Proiect)
## _Platforma food delivery_

### 1. Definirea sistemului
Acțiuni:
* Un utilizator normal poate să facă o comanda de mai multe produse.
* Un utilizator normal poate să vadă o listă cu localuri. Dacă selectează un local poate vedea lista cu produse. 
* Un utlizator normal poate căuta un produs și poate adăuga sau șterge un produs din comanda curentă.
* Poate vedea totalul din comanda curentă și o poate trimite.
* Poate adauga sau sterge un local de la preferințe.
* Când se trimite o comandă, i se asignează un șofer care să livreze comanda la adresa utilizatorului.
* Utilizatorul își poate modifica adresa curentă. 
* Produsele dintr-o comanda pot aparține doar aceluiași local.
* Un local oferă diferite produse culinare: fel principal, desert, băuturi.
* Un local poate să ofere discounturi pentru anumite produse, să adauge produse sau să șterga produse.
* Un user își poate viziualiza istoricul comenzilor.
* Un user poate repeta o comanda din istoricul său.
* Un manager de local poate să își adauge propriul local pe platformă.


Obiecte:
* _User_ care conține un ID care i se asignează la „înregistrare” , un email un username și o parolă.
* _Utilizator normal_ care are o adresă un array de istoric comenzi și un array de preferințe localuri.
* _Utilizator manager de local_ care are un local și un cod de autorizație pentru local. 
* _Manager de useri_ care permite logarea și înregistrarea userilor.
* _Adresa_ - câmpurile localitate, număr, județ
* _Adresa validator_ care validează adresa.
* _Produs_ apartine unui local
  -  _Fel principal_ 
  -   _Băutura_ 
  -   _Desert_
* _Sofer_ are un nume prenume și un salariu - tansportă o comandă la adresa clinetului.
* _Comanda_ are un id, un timp estimat de livrare, un sofer și conține un array de produse, corespunzătoare unui singur local.
<br/>
<br/>


![diagrama](https://github.com/iuga-paula/Programare-avanjansata-pe-obiecte/blob/master/Proiect/Diagrama%20Platforma%20Food%20Delivery.jpg)

### 2. Aplicația în Java:
* Clasele și realțiile dintre ele(https://github.com/iuga-paula/Programare-avansata-pe-obiecte/tree/master/Proiect)
* Realizarea persistenței cu fișiere CSV
* Realizarea unui serviciu de audit
* Baza de date cu JDBC
