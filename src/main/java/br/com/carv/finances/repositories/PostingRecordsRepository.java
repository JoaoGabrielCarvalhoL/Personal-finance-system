package br.com.carv.finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.finances.entities.PostingRecords;

public interface PostingRecordsRepository extends JpaRepository<PostingRecords, Long>{

}
