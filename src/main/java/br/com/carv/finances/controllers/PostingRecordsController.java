package br.com.carv.finances.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.carv.finances.dto.PostingRecordsDto;
import br.com.carv.finances.dto.UpdateStatusDto;
import br.com.carv.finances.entities.PostingRecords;
import br.com.carv.finances.entities.User;
import br.com.carv.finances.entities.enums.StatusRegister;
import br.com.carv.finances.entities.enums.TypeRegister;
import br.com.carv.finances.exceptions.BusinessRuleException;
import br.com.carv.finances.services.PostingRecordsService;
import br.com.carv.finances.services.UserService;

@RestController
@RequestMapping(value = "/postingRecords")
public class PostingRecordsController {

	private final PostingRecordsService postingService;
	private final UserService userService;
	
	public PostingRecordsController(PostingRecordsService postingService, UserService userService) {
		this.postingService = postingService;
		this.userService = userService;
	}
	
	private PostingRecords converter(PostingRecordsDto postingRecordsDto) {
		PostingRecords postingRecords = new PostingRecords(); 
		
		postingRecords.setDescription(postingRecordsDto.getDescription());
		postingRecords.setMonthRegister(postingRecordsDto.getMonthRegister());
		postingRecords.setYearRegister(postingRecordsDto.getYearRegister());
		postingRecords.setValueRegister(postingRecordsDto.getValueRegister());
		
		User user =	userService.findByIdUser(postingRecordsDto.getIdUser()).orElseThrow( () -> new BusinessRuleException("User not found for entered id."));
		
		postingRecords.setUser(user);
		
		if(postingRecordsDto.getTypePosting()!= null){
			postingRecords.setTypeRegister(TypeRegister.valueOf(postingRecordsDto.getTypePosting()));
		}
		
		if(postingRecordsDto.getStatusPosting()!= null) {
			postingRecords.setStatusRegister(StatusRegister.valueOf(postingRecordsDto.getStatusPosting()));
		}
		
		return postingRecords;
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/savePostingRecords")
	public ResponseEntity<?> savePostingRecords(@RequestBody PostingRecordsDto postingRecordsDto) {
		
		try {
			
			PostingRecords postingRecords = converter(postingRecordsDto);
			postingRecords = postingService.savePostingRecords(postingRecords);
			return new ResponseEntity<PostingRecords>(postingRecords, HttpStatus.CREATED);
			
		} catch (BusinessRuleException err) {
			return ResponseEntity.badRequest().body(err.getMessage());
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updatePostingRecords/{id}")
	public ResponseEntity<?> updatePostingRecords(@PathVariable("id") Long id, @RequestBody PostingRecordsDto postingRecordsDto) {
		return postingService.findByIdPostingRecords(id).map( postingRecords -> {
			try {

				PostingRecords posting = converter(postingRecordsDto);
				posting.setIdpr(postingRecords.getIdpr());
				postingService.updatePostingRecords(posting); 
				return ResponseEntity.ok(posting);
				
			} catch (BusinessRuleException err) {
				return ResponseEntity.badRequest().body(err.getMessage());
			}
			
		}).orElseGet(() -> new ResponseEntity<String>("Release not found.", HttpStatus.BAD_REQUEST));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/updateStatus/{id}")
	public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @RequestBody UpdateStatusDto updateStatusDto) {
		
		return postingService.findByIdPostingRecords(id).map(postingRecords -> {
				
			StatusRegister status = StatusRegister.valueOf(updateStatusDto.getStatus());
			
			if (status == null) {
				return  ResponseEntity.badRequest().body("Unable to update status. ");
			} 
			
			try {
				postingRecords.setStatusRegister(status);
				postingService.updatePostingRecords(postingRecords); 
				return ResponseEntity.ok(postingRecords);
			} catch (BusinessRuleException err) {
				return ResponseEntity.badRequest().body(err.getMessage());
			}			
		}).orElseGet(() -> new ResponseEntity<String>("Release not found.", HttpStatus.BAD_REQUEST));
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/deletePosting/{id}")
	public ResponseEntity<?> deletePostingById(@PathVariable("id") Long id, PostingRecordsDto postingRecordsDto	) {
		return postingService.findByIdPostingRecords(id).map(posting -> {
			postingService.deletePostingRecords(posting);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity<String>("Release not found.", HttpStatus.BAD_REQUEST));
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ResponseEntity<?> findPostinRecords(
			@RequestParam(value = "description", required = false) String description, 
			@RequestParam(value = "month", required = false) Integer month, 
			@RequestParam(value = "year", required = false) Integer year, 
			@RequestParam("user") Long id) {
		
		PostingRecords postingFilter = new PostingRecords();
		postingFilter.setDescription(description);
		postingFilter.setMonthRegister(month);
		postingFilter.setYearRegister(year);
		
		Optional<User> user = userService.findByIdUser(id);
		
		if(user == null) {
			return ResponseEntity.badRequest().body("User not found!");
		} else {
			postingFilter.setUser(user.get());
		}

		List<PostingRecords> postingListFilter = postingService.findPosting(postingFilter);
		
		return ResponseEntity.ok(postingListFilter);
	}
	
	
}
