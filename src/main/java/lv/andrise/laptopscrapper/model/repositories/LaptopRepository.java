package lv.andrise.laptopscrapper.model.repositories;

import lv.andrise.laptopscrapper.model.Laptop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by andris on 15.28.8.
 */
@RepositoryRestResource(collectionResourceRel = "laptop", path = "laptops")
public interface LaptopRepository extends CrudRepository<Laptop, Long> {
//public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findLaptopsByRazotajs(@Param("razotajs") String razotajs);
    List<Laptop> findLaptopsByAktualaCena(@Param("aktualaCena") double aktualaCena);
}
