package br.com.carv.finances.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.carv.finances.entities.PostingRecords;

public interface PostingRecordsRepository extends JpaRepository<PostingRecords, Long>{

	
	@Query(value = 
			"SELECT sum(p.valueRegister) FROM PostingRecords p join p.user u where u.idUser =: iduser and  p.typeRegister =: typeregister GROUP BY u")
	BigDecimal getBalanceTypeRegister(@Param("iduser") Long idUser, @Param("typeregister") String typeRegister);

}
