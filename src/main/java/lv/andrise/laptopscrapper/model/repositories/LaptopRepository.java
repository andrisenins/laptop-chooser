package lv.andrise.laptopscrapper.model.repositories;

import lv.andrise.laptopscrapper.model.Laptop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Andris on 15.28.8.
 */
@RepositoryRestResource(collectionResourceRel = "laptop", path = "laptops")
public interface LaptopRepository extends CrudRepository<Laptop, Long> {
//public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findLaptopsByNosaukums(@Param("nosaukums") String nosaukums);

    List<Laptop> findLaptopsByRazotajs(@Param("razotajs") String razotajs);
    List<Laptop> findLaptopsByAktualaCena(@Param("aktualaCena") double aktualaCena);

//    @Query("FROM laptop WHERE aktualaCena >= ?1 AND aktualaCena <= ?2")
    List<Laptop> findByaktualaCenaBetween(Double apaksejaRobeza, Double augsejaRobeza);

//    List<Laptop> findByekranaIzmersAndmaksIzskirtspejaAndprocesoraSaimeAndprocesoraPaaudze(String ekranaIzmers, String maksIzskirtspeja, String procesoraSaime, String procesoraPaaudze);
}
