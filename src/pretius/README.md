# File Transformer 
  
## 1.	Opis i cel zadania  
Twoim zadaniem jest napisanie programu, który będzie umożliwiał segregowanie plików. Program powinien:  
  
### * Stworzyć strukturę katalogów:  
  +	HOME  
  + DEV  
  + TEST  
  
### * W momencie pojawienia się w katalogu HOME pliku w zależności od rozszerzenia przeniesie go do folderu wg następujących reguł:  
  + Plik z rozszerzeniem .jar, którego godzina utworzenia jest parzysta przenosimy do folderu DEV.
  +	Plik z rozszerzeniem .jar, którego godzina utworzenia jest nieparzysta przenosimy do folderu TEST.
  +	Plik z rozszerzeniem .xml, przenosimy do folderu DEV.  
    
### * Dodatkowo w nowo stworzonym pliku /home/count.txt należy przechowywać liczbę przeniesionych plików (wszystkich i w podziale na katalogi), plik powinien w każdym momencie działania programu przechowywać aktualną liczbę przetworzonych plików.  
  
## 2. Wymagania techniczne
*	Można użyć dowolnych bibliotek i frameworków na licencjach otwartych (np. MIT, Apache, itp.).  
* Projekt powinien być budowany i uruchamiany przez wybrany system do budowania (np. maven, gradle, itp..).  
* Do projektu powinna być dołączona instrukcja budowania i uruchamiania  
  


