# FURPS – vores filmsamling

|   | Krav | Opfyldt? | Hvor                                           |
|---|---|---|------------------------------------------------|
| F | Man kan oprette, vise, søge, rette og slette film | Ja | US4, `MovieCollection.searchMovies`            |
| U | Tom samling og ingen søgematch giver en besked | Ja | I `showAllMovies.searchMovies.selectMovie`     |
| R | Bogstaver i stedet for tal giver en besked og nyt forsøg | Nej | Del-6                                          |
| P | Søgning i 10.000 film tager under ét sekund | Måske | Ikke målt, men en enkelt løkke burde klare det |
| F | Givet at jeg vælger "Markér en film som set" og vælger en film (som ved redigering), så gemmes dagens dato som den dato, jeg sidst så filmen. | Ja | US8 `Movie.markAsWatched`                      |
| F | Når en film vises, står der, hvornår den sidst blev set, og hvor mange dage siden det er – fx Sidst set: 26-10-2026 (for 0 dage siden) | Ja | US8 `Movie.printMovie`                         |
| S | System.out og Scanner findes kun i UserInterface | Ja |                                                |
| F | En film, jeg aldrig har set, viser Sidst set: aldrig – ikke null | Ja | US8 `Movie.printMovie`                         |
| F | ivet at programmet startes, og `movies.csv` findes, så indlæses filmene, og jeg får at vide, hvor mange der blev indlæst. | Ja | US13 `Userinterface.loadMovies`                |
| F | Alle oplysninger overlever – også æ, ø og å i titlerne, og datoen for, hvornår jeg sidst så filmen. | Ja | US13 `FileHandler`                             |
| F | Givet at filen ikke findes (første gang programmet kører), så starter jeg med en tom samling – programmet går ikke ned. | Ja | US13 `UserInterface.loadMovies`                |
| F | Givet at en linje i filen er ødelagt, så springes den over, jeg får en advarsel, og resten af filmene indlæses. | Ja | US13 `Filehandler.loadMovies`                  |
| P | Givet at jeg hverken har oprettet, rettet, slettet eller markeret en film som set, når jeg afslutter, så bliver filen ikke skrevet, og jeg får at vide, at der ikke var noget at gemme.  | Ja | US14 `Controller.saveMoviesIfChanged`          |