package school.hei.patrimoine.cas;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.PatrimoineBako;
import school.hei.patrimoine.modele.Patrimoine;

import java.time.LocalDate;

import static java.time.Month.DECEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.hei.patrimoine.modele.Argent.ariary;

@Slf4j
public class BakoTest {
    private final PatrimoineBako patrimoineDeBakoAu8AvrilSupplier = new PatrimoineBako();

    private Patrimoine patrimoineDeBako8Avril() {
        return patrimoineDeBakoAu8AvrilSupplier.get();
    }

    @Test
    void patrimoine_bako_le_8_avril_et_31_decembre_2025() {
        var patrimoine = patrimoineDeBako8Avril();

        // État initial au 8 avril 2025
        var etatInitial = patrimoine;
        assertEquals(ariary(7_375_000), etatInitial.getValeurComptable());

        // Projection au 31 décembre 2025
        var projete = patrimoine.projectionFuture(LocalDate.of(2025, DECEMBER, 31));
        System.out.println("Valeur au 31 décembre 2025 : " + projete.getValeurComptable());

        assertEquals(ariary(13_975_000), projete.getValeurComptable());
    }
}
