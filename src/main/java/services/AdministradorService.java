package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AdminRepository;
@Service
@Transactional
public class AdministradorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdminRepository adminRepository;


	// Constructors -----------------------------------------------------------

	public AdministradorService() {
		super();
	}

	public Statistics calculateCourseStatisticsByAcademia() {
        List<Object[]> results = adminRepository.findCourseCountsByAcademia();
        return calculateStatistics(results);
    }

    public Statistics calculateSolicitudStatisticsByCurso() {
        List<Object[]> results = adminRepository.findSolicitudCountsByCurso();
        return calculateStatistics(results);
    }

    private Statistics calculateStatistics(List<Object[]> results) {
        if (results.isEmpty()) {
            return new Statistics(0, 0, 0, 0);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        double sum = 0;
        double sumOfSquares = 0;
        int count = results.size();

        for (Object[] result : results) {
            int value = ((Long) result[1]).intValue();
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
            sum += value;
            sumOfSquares += value * value;
        }

        double mean = sum / count;
        double variance = (sumOfSquares / count) - (mean * mean);
        double stddev = Math.sqrt(variance);

        return new Statistics(min, mean, stddev, max);
    }

    public static class Statistics {
        private final int min;
        private final double mean;
        private final double stddev;
        private final int max;

        public Statistics(int min, double mean, double stddev, int max) {
            this.min = min;
            this.mean = mean;
            this.stddev = stddev;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public double getMean() {
            return mean;
        }

        public double getStddev() {
            return stddev;
        }

        public int getMax() {
            return max;
        }
    }

	
}
