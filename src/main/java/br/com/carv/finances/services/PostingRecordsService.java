package br.com.carv.finances.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import br.com.carv.finances.entities.PostingRecords;
import br.com.carv.finances.entities.enums.StatusRegister;

public interface PostingRecordsService {
	
	PostingRecords savePostingRecords(PostingRecords postingRecords);
	
	PostingRecords updatePostingRecords(PostingRecords postingRecords);
	
	void deletePostingRecords(PostingRecords postinRecords);
	
	List<PostingRecords> findPosting (PostingRecords postingRecords);
	
	void updateStatus(PostingRecords postingRecords, StatusRegister statusRegister);
	
	void validate(PostingRecords postingRecords);

	Optional<PostingRecords> findByIdPostingRecords(Long id);
	
	BigDecimal getBalanceByUser(Long idUser);
}
