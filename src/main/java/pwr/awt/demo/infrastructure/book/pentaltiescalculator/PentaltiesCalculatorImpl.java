package pwr.awt.demo.infrastructure.book.pentaltiescalculator;

import org.springframework.stereotype.Component;
import pwr.awt.demo.domain.book.pentalties.PentaltiesCalculator;

import java.time.LocalDate;

@Component
public class PentaltiesCalculatorImpl implements PentaltiesCalculator {

    @Override
    public double calculatePenalty(LocalDate deadline, LocalDate returnedAt) {
        long diffInDays =  returnedAt.toEpochDay() - deadline.toEpochDay();
        if(diffInDays < 0)
            return 0;
        return diffInDays*0.2;
    }
}
