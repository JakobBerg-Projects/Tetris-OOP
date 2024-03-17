For å fullføre laben, ber vi deg om å svare på følgende spørsmål. Svar på spørsmålene ved å fylle ut denne filen.

## Hva har du lært om Java og objekt-orientert programmering under arbeidet med denne oppgaven? Beskriv hvilke deloppgave(r) du jobbet med i læringsøyeblikket.

I denne oppgaven har jeg lært veldig mye om forskjellige prinsipper i java, og objekt-orientert programmering. Har lært mye om funksjonaliteten ved klasser gjennom hele oppgaven. Lært viktigheten av å ha private vs public metoder, for å unngå endring av variabler slik at programmet ikke fungerer optimalt. Blant annet ved tegning av brikker, og flytting/rotering av brikken, var det viktig at alle metoder hadde den minste mulige tilgang muligheter men at programmet fortsatt funket. I tillegg har jeg lært mye om javadocs, og viktigheten av dokumentering av koden. Blant annet bruken av interface for å skape et bilde av hva en trenger for at programmet skal funke, og forklaringen av disse. I tillegg har jeg lært bruken av model-view-controller design prinsippet for å ha en viss ryddighet med klasser og metoder som gjør mye av det samme. Har også lært litt om grafikk i java, og hvordan man kan ha en modell som regner ut verdier osv, men en view-pakke som visualiserer alt dette.

## Hva er det neste du ønsker å lære om Java og programmering?

Det neste jeg ønsker å lære om java og og programmering er litt mer om interaktiv grafikk for å kunne lage mer avanserte spill. I tillegg ønsker jeg å få en bedre generell forståelse av programmering. Etterhvert ønsker jeg også å lære om programmering ved bruk i data science som jeg føler er enda mer relevant for min studieretning.

## Hvilke grep gjør vi for å øke modulariteten i koden? Gi noen eksempeler.

For å øke modulariteten i koden ønsker vi å dele et stort program inn i mindre selvstendige moduler. Dette gjør vi for å forenkle feilsøkingen og vedlikeholdingen av programmet. I vårt program har vi som sagt delt inn i model-view-controller design prinsippet. Dette for å holde styr på de klassene som har ganske like funksjoner. I model pakken har vi samlet alt som regnes ut i "bakgrunnen" for å ha en modell av hvordan brettet er til ethvert tidspunkt. View pakken vil visualisere modellen for at det skal være mulig å spille. Blant annet har vi en DefaultColorTheme klasse, som holder styr på alt av farger for å ha en ryddigere kode i selve tetrisview. I tillegg vil controller pakken stå for alt av tastetrykk som skal gi et utslag på modellen, som igjen skal visualises i view. Over det hele har vi TetrisMain som starter hele programmet.
