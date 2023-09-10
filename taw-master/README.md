# AGH Inyżnieria Oprogramowania 2022/23 | Technologie Aplikacji Webowych | Projekt

* Użyto kontener dockera: docker pull mwydmanski/taw_docker1 

## Warunki zaliczenia
Warunkiem zaliczenia jest utworzenie serwera aplikacyjnego obsługującego żądania HTTP (opartej o REST API) oraz raportu komunikacji z tym serwerem według podanego scenariusza.
Raport komunikacji z serwerem zawiera zestawienie wywołanych żądań do serwera wraz z adresem zasobu, metodą http, body żądania, nagłówkami żądania oraz odpowiedzią serwera na wysłane żądanie w postaci kodu odpowiedzi http oraz body odpowiedzi

*Przykładowa komunikacja do serwera:

```
Metoda: POST
Adres zasobu: http://localhost:8080/api/courses
Nagłówki: Content-Type: application/json
Request Body:
{
"nazwa": "Technologie i Aplikacje Webowe",
"prowadzący": "Prof Mateusz Wydmański",
"ocena": "5",
"egzamin": true
}
Odpowiedź:
HTTP Code: 200 OK
Body: brak
```

W ramach projektu należy przygotować REST API bazujące na zasobie kursów na studiach. 
Każdy kurs powinien zawierać informacje o:

- nazwie przedmiotu,
- nazwie prowadzącego,
- ocenie jaką uzyskano na koniec kursu, 
- informacji czy kurs kończy się testem.

* Serwer powinien umożliwiać wprowadzanie nowych kursów, pobieranie kursów oraz ich usuwanie.
* Podstawowa implementacja serwera umożliwia wprowadzanie nowego kursu, pobieranie wszystkich kursów i usuwanie wszystkich kursów. 
* W rozszerzonej wersji serwer pozwala na filtrację pod kątem informacji o tym czy kurs kończy się testem. Serwer powinien także udostępniać możliwość pobierania konkretnego zasobu w oparciu o identyfikator oraz usuwanie konkretnego przedmiotu w oparciu o identyfikator.

## Scenariusz do raportu:

1. Dodawanie kursów do systemu w podanej kolejności:

- Nazwa: Technologie i aplikacje webowe, Prowadzący: Prof Mateusz Wydmański, Ocena: 5, Test: nie,
- Nazwa: Metodologie obiektowe, Prowadzący: Dr Radosław Klimek, Ocena: 5, Test: tak,
- Nazwa: Testowanie oprogramowania, Prowadzący: Dr Katarzyna Mazur, Ocena: 4, Test: nie,
- Nazwa: Zarządzanie projektem informatycznym, Prowadzący: Dr Jan Szulc, Ocena: 3, Test: nie,
- Nazwa: Zaawansowane technologie bazodanowe, Prowadzący: Dr Anna Mickiewicz, Ocena: 5, Test: nie,
- Nazwa: Technologie komponentowe i sieciowe, Prowadzący: Prof Beata Kozidrak, Ocena: 4, Test: tak

2. Pobranie wszystkich kursów

3. Pobranie przedmiotów, które mają test.

4. Pobranie przedmiotu o identyfikatorze 5

5. Pobranie przedmiotu o identyfikatorze 17

6. Usunięcie przedmiotu o identyfikatorze 2

7. Pobranie wszystkich przedmiotów

8. Usunięcie wszystkich przedmiotów

9. Pobranie wszystkich przedmiotów


## Opis API Endpoints
### Punkty końcowe API (API Endpoints)
`api/courses` 
	`GET` - lista przedmiotów; możliwe argumenty filtrowania
		 `nazwa=<str>`
		 `prowadzący=<str>`
		 `ocena=<int>`
		 `test=<true/false>`
	
	`POST` - dodawanie kursu, wymagane argumenty:
		`nazwa=<str>&prowadzący=<str>&ocena=<int>&test=<true/false>`
	`DELETE` - usunięcie wszystkich kursów
 
`api/courses/{id}` 
	`GET` - pobranie informacji o konkretnym kursie na podstawie jego id
	`DELETE` - usunięcie konkretnego kursu na podstawie jego id

## Raport
### Dodawanie kursów w kolejności

```
Metoda: POST
Adres zasobu: http://localhost:8080/api/courses
Nagłówki: Content-Type: application/json
Request Body:
{"nazwa":"Technologie i Aplikacje Webowe", "prowadzący":"Prof Mateusz Wydmański", "ocena":5, "test":"false"}

Odpowiedź:
HTTP Code: 200 OK
Body: brak
```

#### Podobnie dla kolejnych request body:

{"nazwa":"Metodologie Obiektowe", "prowadzący":"Dr Radosław Klimek", "ocena":5, "test":"true"}
{"nazwa":"Testowanie Oprogramowania", "prowadzący":"Dr Katarzyna Mazur", "ocena":4, "test":"false"}
{"nazwa":"Zarządzanie Projektem Informatycznym", "prowadzący":"Dr Jan Szulc", "ocena":3, "test":"false"}
{"nazwa":"Zaawansowane Technologie Bazodanowe", "prowadzący":"Dr Anna Mickiewicz", "ocena":5, "test":"false"}
{"nazwa":"Technologie Komponentowe i Sieciowe", "prowadzący":"Prof Beata Kozidrak", "ocena":4, "test":"true"}

### Pobieranie wszystkich kursów

```
Metoda: GET
Adres zasobu: http://localhost:8080/api/courses
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: 
[
    {
        "id": 1,
        "nazwa": "Technologie i Aplikacje Webowe",
        "prowadzący":"Prof Mateusz Wydmański",
        "ocena": 5,
        "test": true
    },
    // ... [reszta kursów]
]
```

### Pobieranie kursów, które mają test

```
Metoda: GET
Adres zasobu: http://localhost:8080/api/courses?test=true
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: 
[
    {
        "id": 1,
        "nazwa": "Technologie i Aplikacje Webowe",
        "prowadzący":"Prof Mateusz Wydmański",
        "ocena": 5,
        "test": true
    },
    // ... [reszta kursów]
]
```

### Pobieranie kursu o id = 5

```
Metoda: GET
Adres zasobu: http://localhost:8080/api/courses/5
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: 
{
    "id": 5,
    "nazwa": "Technologie Komponentowe i Sieciowe",
    "prowadzący":"Prof Beata Kozidrak",
    "ocena": 5,
    "test": true
}
```

### Pobieranie kursu o id = 17

```
Metoda: GET
Adres zasobu: http://localhost:8080/api/courses/17
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 404 Not found
Body: brak
```

### Usunięcie kursu o id = 2

```
Metoda: DELETE
Adres zasobu: http://localhost:8080/api/courses/2
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: brak
```

### Pobranie wszystkich kursów

```
Metoda: GET
Adres zasobu: http://localhost:8080/api/courses
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: 
[
    {
        "id": 1,
        "nazwa": "Technologie i Aplikacje Webowe",
        "prowadzący":"Prof Mateusz Wydmański",
        "ocena": 5,
        "test": true
    },
    // ... [reszta kursów]
]
```

### Usunięcie wszystkich kursów

```
Metoda: DELETE
Adres zasobu: http://localhost:8080/api/courses
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: brak
```

### Pobranie wszystkich kursów 

```
Metoda: GET
Adres zasobu: http://localhost:8080/api/courses
Nagłówki: Content-Type: application/json
Request Body: brak

Odpowiedź:
HTTP Code: 200 OK
Body: 
[ ]
```

