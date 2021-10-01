package br.com.carv.finances.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.com.carv.finances.entities.PostingRecords;
import br.com.carv.finances.entities.enums.StatusRegister;
import br.com.carv.finances.entities.enums.TypeRegister;
import br.com.carv.finances.exceptions.BusinessRuleException;
import br.com.carv.finances.repositories.PostingRecordsRepository;


@Service
public class PostingRecordsServicesImpl implements PostingRecordsService {

	private final PostingRecordsRepository postingRepository;
	
	public PostingRecordsServicesImpl(PostingRecordsRepository postingRecordsRepository) {
		this.postingRepository = postingRecordsRepository;
	}
	
	@Override
	@Transactional
	public PostingRecords savePostingRecords(PostingRecords postingRecords) {
		validate(postingRecords);
		postingRecords.setStatusRegister(StatusRegister.PENDING);
		return postingRepository.save(postingRecords);
	}

	@Override
	@Transactional
	public PostingRecords updatePostingRecords(PostingRecords postingRecords) {
		Objects.requireNonNull(postingRecords.getIdpr());
		validate(postingRecords);
		return postingRepository.save(postingRecords);
	}

	@Override
	@Transactional
	public void deletePostingRecords(PostingRecords postingRecords) {
		Objects.requireNonNull(postingRecords.getIdpr());
		postingRepository.delete(postingRecords);
		
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public List<PostingRecords> findPosting(PostingRecords postingRecords) {
		Example<PostingRecords> example = Example.of(postingRecords, ExampleMatcher.matching().
				withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return postingRepository.findAll(example);
	}

	@Override
	public void updateStatus(PostingRecords postingRecords, StatusRegister statusRegister) {
		postingRecords.setStatusRegister(statusRegister);
		updatePostingRecords(postingRecords);
		
	}

	@Override
	public void validate(PostingRecords postingRecords) {
		
		if(postingRecords.getDescription() == null || postingRecords.getDescription().trim().equals("")) {
			throw new BusinessRuleException("The description field cannot be empty.");
		}
		
		if(postingRecords.getMonthRegister() == null || postingRecords.getMonthRegister() < 1 || postingRecords.getMonthRegister() > 12) {
			throw new BusinessRuleException("Please enter a valid month.");
		}
		
		if (postingRecords.getYearRegister() == null || postingRecords.getYearRegister().toString().length() != 4) {
			throw new BusinessRuleException("Please enter a valid year.");
		}
		
		if (postingRecords.getUser() == null || postingRecords.getUser().getIdUser() == null) {
			throw new BusinessRuleException("Please enter a valid user.");
		}
		
		if (postingRecords.getValueRegister() == null || postingRecords.getValueRegister().compareTo(BigDecimal.ZERO) < 1) {
			throw new BusinessRuleException("Please enter a valid value.");
		}
		
		if (postingRecords.getTypeRegister() == null) {
			throw new BusinessRuleException("Please enter a valid type of posting.");
		}
	}

	@Override
	public Optional<PostingRecords> findByIdPostingRecords(Long id) {
		return postingRepository.findById(id);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public BigDecimal getBalanceByUser(Long idUser) {
		BigDecimal revenues = postingRepository.getBalanceTypeRegister(idUser, TypeRegister.REVENUE.name());
		BigDecimal expenses = postingRepository.getBalanceTypeRegister(idUser, TypeRegister.EXPENSE.name());
		
		if (revenues == null) {
			revenues = BigDecimal.ZERO;
		}
		
		if (expenses == null) {
			expenses = BigDecimal.ZERO;
		}
		
		return revenues.subtract(revenues);
	}

}
