package school.hei.patrimoine.cas.example;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.TransfertArgent;

public class PatrimoineBako implements Supplier<Patrimoine> {

    private final LocalDate ajd = LocalDate.of(2025, APRIL, 8);
    private final LocalDate finAnnee = LocalDate.of(2025, DECEMBER, 31);

    @Override
    public Patrimoine get() {
        var bako = new Personne("Bako");

        // Comptes initiaux
        var compteBNI = new Compte("BNI", ajd, ariary(2_000_000));
        var compteBMOI = new Compte("BMOI", ajd, ariary(625_000));
        var coffreFort = new Compte("Coffre Fort", ajd, ariary(1_750_000));

        // Salaire
        new FluxArgent(
                "Salaire",
                compteBNI,
                LocalDate.of(2025, APRIL, 2),
                finAnnee,
                1, // Mensuel
                ariary(2_125_000));

        // Virement épargne (le lendemain du salaire)
        new TransfertArgent(
                "Virement épargne",
                compteBNI,
                compteBMOI,
                LocalDate.of(2025, APRIL, 3),
                finAnnee,
                1, // Mensuel
                ariary(200_000));

        // Dépenses
        new FluxArgent(
                "Loyer colocation",
                compteBNI,
                LocalDate.of(2025, APRIL, 26),
                finAnnee,
                1, // Mensuel
                ariary(-600_000));

        new FluxArgent(
                "Dépenses courantes",
                compteBNI,
                LocalDate.of(2025, MAY, 1), // Prochain 1er du mois
                finAnnee,
                1, // Mensuel
                ariary(-700_000));

        // Matériel
        var ordinateur = new Materiel(
                "Ordinateur portable",
                ajd,
                finAnnee,
                ariary(3_000_000),
                -0.12); // Dépriciation annuelle de 12%

        return Patrimoine.of(
                "Bako",
                MGA,
                finAnnee,
                bako,
                Set.of(compteBNI, compteBMOI, coffreFort, ordinateur));
    }
}