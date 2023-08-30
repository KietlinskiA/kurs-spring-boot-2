package pl.kietlinski.kursspringboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        saveCar(new Car("Audi", "A5", 2009));
//        saveCar(new Car("Audi", "A6", 2012));
//        saveCar(new Car("BMW", "E92", 2008));
//        System.out.println(getCarList());
//        System.out.println(updateCar(new Car(1L, "Audi", "A5", 2009)));
        deleteCar(1L);
    }

    @Override
    public void saveCar(Car newCar) {
        String sql = "insert into cars values(0,?,?,?)";
        jdbcTemplate.update(sql, newCar.getBrand(), newCar.getModel(), newCar.getProduction_year());
    }

    @Override
    public List<Map<String, Object>> getCarList() {
        String sql = "select * from cars";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public Car updateCar(Car toUpdateCar) {
        String sql = "update cars set cars.brand=?, cars.model=?, cars.production_year=? where cars.id=?";
        jdbcTemplate.update(sql, toUpdateCar.getBrand(), toUpdateCar.getModel(), toUpdateCar.getProduction_year(), toUpdateCar.getId());
        String sql2 = "SELECT * from cars where cars.id=" + toUpdateCar.getId();
        return jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(Car.class)).stream().findFirst().get();
    }

    @Override
    public void deleteCar(long carId) {
        String sql = "delete from cars where cars.id=?";
        jdbcTemplate.update(sql, carId);
    }
}
