USE HouseBreak;

select * FROM Salvataggio WHERE emailUtente= 'gianmarco@ya.it'

SELECT * FROM StatsUtente WHERE codSalvataggio= 1361;

SELECT su.stanzaCorrente , o.nomeOggetto FROM StatsUtente su, Salvataggio s, OggettoInventario o WHERE  su.codSalvataggio=1361 and s.codStatsUtente != su.codStats and o.codInventario=su.codInventario

SELECT su.vita, su.armaEquipaggiata, su.bloccato, su.numeroMunizioni, su.stanzaCorrente
FROM StatsUtente su, Salvataggio s
WHERE s.codSalvataggio = 1361 AND su.codSalvataggio = s.codSalvataggio AND su.codStats != s.codStatsUtente;