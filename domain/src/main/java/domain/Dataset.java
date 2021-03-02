package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Dataset {

    private Long id;

    private String country;

    private String isoCode;

    private Date date;

    private Long totalVaccinations;

    private Long peopleVaccinated;

    private Long peopleFullyVaccinated;

    private Integer dailyVaccinationsRaw;

    private Integer dailyVaccinations;

    private Double totalVaccinationsPerHundred;

    private Double peopleVaccinatedPerHundred;

    private Double peopleFullyVaccinatedPerHundred;

    private Double dailyVaccinationsPerMillion;

    private String vaccines;

    private String sourceName;

    private String sourceWebsite;

}
