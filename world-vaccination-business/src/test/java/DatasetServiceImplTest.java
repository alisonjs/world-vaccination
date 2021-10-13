import com.alisonjs.business.domain.Dataset;
import com.alisonjs.business.repository.DatasetRepository;
import com.alisonjs.business.service.DatasetService;
import com.alisonjs.business.service.impl.DatasetServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

/**
 * @author Alison Sousa (alison.sousa@ufrn.br)
 * @since 13/10/2021
 */
public class DatasetServiceImplTest {

    private DatasetService datasetService;

    @Mock
    private DatasetRepository datasetRepository;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        this.autoCloseable = MockitoAnnotations.openMocks(this);
        datasetService = new DatasetServiceImpl(datasetRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.autoCloseable.close();
    }

    @Nested
    class crud{

        @Test
        void save(){
            var dataset = getDatasetInstance();
            datasetService.save(dataset);
            Mockito.verify(datasetRepository, Mockito.times(1)).save(ArgumentMatchers.any(Dataset.class));
        }

    }

    public Dataset getDatasetInstance(){
        return Dataset.builder()
                .country("Brazil")
                .dailyVaccinations(1)
                .dailyVaccinationsPerMillion(1D)
                .isoCode("112")
                .peopleFullyVaccinated(1L)
                .peopleFullyVaccinatedPerHundred(1D)
                .sourceName("source")
                .sourceWebsite("site")
                .vaccines("vaccines")
                .dailyVaccinationsPerMillion(1D)
                .date(LocalDate.now())
                .build();
    }
}
