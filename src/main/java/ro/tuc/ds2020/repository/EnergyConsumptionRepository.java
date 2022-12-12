package ro.tuc.ds2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entity.EnergyConsumption;

import java.util.UUID;

@Repository
public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, UUID>, JpaSpecificationExecutor<EnergyConsumption> {

}
