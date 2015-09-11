package lv.andrise.laptopscrapper.model.repositories;

import lv.andrise.laptopscrapper.model.LaptopPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Andris on 15.10.9.
 */
@RepositoryRestResource(collectionResourceRel = "laptopprice", path = "laptopprices")
public interface LaptopPriceRepository extends CrudRepository<LaptopPrice, Long> {
    List<LaptopPrice> findByLaptopId(@Param("laptopId") Long laptopId);
}
