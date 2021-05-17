package pwr.awt.demo.domain.book.pentalties;

import java.time.LocalDate;

public interface PentaltiesCalculator {
    double calculatePenalty(LocalDate deadline, LocalDate returnedAt);
}
